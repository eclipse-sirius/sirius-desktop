/*******************************************************************************
 * Copyright (c) 2024 OBEO.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   OBEO - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Collections;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * Tracer of diagram layout.
 * <p>
 * This tracer saves ELK Graph as XMI or textual format in a file.
 * </p>
 *
 * @author nperansin
 */
public class ElkDiagramLayoutTracer {

    // Built-in Java property.
    private static String JAVA_TEMP_DIR_PROPERTY_NAME = "java.io.tmpdir"; //$NON-NLS-1$

    private static String XMI_EXTENSION = ".elkg"; //$NON-NLS-1$

    private static ILog LOGGER = Platform.getLog(ElkDiagramLayoutTracer.class);

    private final boolean inDebug;

    /**
     * Constructor.
     * 
     * @param debug
     *            activate debug trace
     */
    public ElkDiagramLayoutTracer(boolean debug) {
        this.inDebug = debug;
    }

    /**
     * Traces the given layout graph.
     * 
     * @param graph
     *            the layout graph to store.
     * @param diagramName
     *            the name of diagram.
     * @param suffix
     *            a suffix that can be used in the file name.
     */
    void debug(final ElkNode diagramNode, String suffix) {
        if (inDebug) {
            saveAsGraph(diagramNode, diagramNode.getIdentifier(), suffix);
        }
    }

    private Path getTargetFile(String diagramName, String suffix, String extension) {
        String fileName;
        if (StringUtil.isEmpty(suffix)) {
            fileName = diagramName + extension;
        } else {
            fileName = diagramName + "_" + suffix + extension; //$NON-NLS-1$
        }
        Path path = Path.of(System.getProperty(JAVA_TEMP_DIR_PROPERTY_NAME) + fileName);
        return path;
    }

    /**
     * Exports the given layout graph in a file.
     * <p>
     * If the folder does not exist, it may be created even if the export fails.
     * </p>
     * 
     * @param graph
     *            the layout graph to store.
     * @param diagramName
     *            the name of diagram.
     * @param suffix
     *            a suffix that can be used in the file name.
     */
    public Path saveAsGraph(final ElkNode graph, final String diagramName, String suffix) {
        Path file = getTargetFile(diagramName, suffix, XMI_EXTENSION);

        return saveTo(cleanContentBeforeExport(graph), file);
    }

    /**
     * Return a copy of the inputGraph with some necessary cleanup before serialization. The copy avoids side effects on
     * the inputGraph.
     * 
     * @param inputGraph
     *            The graph to copy and cleanup.
     * @return A clean graph ready to be serialized.
     */
    private ElkNode cleanContentBeforeExport(ElkNode inputGraph) {
        ElkNode copy = EcoreUtil.copy(inputGraph);
        // Disable the layout stored in this graph to avoid an automatic layout during the opening in "Layout Graph"
        // view of in interactive web editor (https://rtsys.informatik.uni-kiel.de/elklive/).
        copy.setProperty(CoreOptions.NO_LAYOUT, true);
        return copy;
    }

    /**
     * Saves an EObject into a file.
     * 
     * @param content
     *            object to save
     * @param target
     *            file to save in
     * @throws IOException
     *             if file cannot be written.
     */
    private static Path saveTo(EObject content, Path target) {
        try {
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.createResource(URI.createFileURI(target.toString()));
            resource.getContents().add(content);

            Files.createDirectories(target.getParent());
            resource.save(Collections.emptyMap());

            return target;
        } catch (IOException e) {
            // Failure is not critical unless caller need the file.
            LOGGER.error(MessageFormat.format(Messages.ElkDiagramLayoutTracer_saveNotPossible, target.toString()), e);
            return null;
        }
    }

}
