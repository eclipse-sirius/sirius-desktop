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
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.util.GraphIdentifierGenerator;
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
 * This tracer saves ELK Graph as XMI or textual format in a file. File location depends of the value of the system
 * property {@value #TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY}.
 * </p>
 *
 * @author nperansin
 */
public class ElkDiagramLayoutTracer {

    /**
     * The name of the system property to define the target folder path used to store the trace files (exported files
     * for example). The value of this variable can contains <a href=
     * "https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.cdt.doc.user%2Ftasks%2Fcdt_t_variables.htm">variables
     * names</a>, like in the definition of the workspace data folder in a launch configuration. For example:
     * <UL>
     * <LI>${container_loc}: Returns the absolute file system path of the session's container (container of the AIRD
     * file),</LI>
     * <LI>${project_loc}: Returns the absolute file system path of a session's project,</LI>
     * <LI>or ${workspace_loc}: Returns the absolute file system path of the workspace root.</LI>
     * </UL>
     * <UL>
     * If this system property is not set or contains error, the Java temp folder is used as target folder. <BR/>
     * <B>Warning:</B> If you use a the "VM arguments" field in the launch configuration, you must replace the "$" by a
     * "%" to avoid a replacement of the variables during the launch (and not by ElkDiagramLayoutTracer during export).
     */
    public static final String TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY = "org.eclipse.sirius.diagram.elk.targetFolderPath"; //$NON-NLS-1$

    private static String JAVA_TEMP_DIR_PROPERTY_NAME = "java.io.tmpdir"; //$NON-NLS-1$

    private static String XMI_EXTENSION = ".elkg"; //$NON-NLS-1$

    private static String TEXT_EXTENSION = ".elkt"; //$NON-NLS-1$

    private static ILog LOGGER = Platform.getLog(ElkDiagramLayoutTracer.class);

    /**
     * The target folder path used to store the trace files (exported files for example). It can contains variables
     * names such as:
     * <UL>
     * <LI>${container_loc}: Returns the absolute file system path of the session's container (container of the AIRD
     * file),</LI>
     * <LI>${project_loc}: Returns the absolute file system path of a session's project,</LI>
     * <LI>or ${workspace_loc}: Returns the absolute file system path of the workspace root.</LI>
     * </UL>
     * These variables will be interpreted when necessary (for example before exporting a diagram as text).
     */
    private final String targetFolderPath;

    private final boolean inDebug;

    /**
     * Constructor.
     * 
     * @param debug
     *            activate debug trace
     */
    public ElkDiagramLayoutTracer(boolean debug) {
        this.inDebug = debug;

        // Get export folder path (through specific system property or by using Java temporary folder)
        String value = System.getProperty(TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY);
        if (value == null || value.trim().isEmpty()) {
            targetFolderPath = System.getProperty(JAVA_TEMP_DIR_PROPERTY_NAME);
        } else {
            // % can be used instead of $ in the value to avoid to be replaced by the variables system during launch (and not by ElkDiagramLayoutTracer during export)
            targetFolderPath = value.trim().replaceAll("%\\{", "\\$\\{"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    private static String getFilename(final String diagramName, final String suffix, String extension) {
        if (StringUtil.isEmpty(suffix)) {
            return diagramName + extension;
        }
        return diagramName + '_' + suffix + extension;
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
        Exception exceptionThrown = null;
        String abosluteExportFolderPath;
        try {
            abosluteExportFolderPath = VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(targetFolderPath);
        } catch (CoreException e) {
            // Default location will be used
            exceptionThrown = e;
            abosluteExportFolderPath = System.getProperty(JAVA_TEMP_DIR_PROPERTY_NAME);
        }
        Path parentPath;
        try {
            parentPath = Path.of(abosluteExportFolderPath);
        } catch (InvalidPathException e) {
            // Default location will be used
            exceptionThrown = e;
            parentPath = Path.of(System.getProperty(JAVA_TEMP_DIR_PROPERTY_NAME));
        }
        if (exceptionThrown != null) {
            LOGGER.warn(MessageFormat.format(Messages.ElkDiagramLayoutTracer_defaultJavaFolderWillBeUsedMsg, TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY, JAVA_TEMP_DIR_PROPERTY_NAME), exceptionThrown);
        }
        return parentPath.resolve(getFilename(diagramName, suffix, extension));
    }

    /**
     * Exports the given layout graph in a file using ELK text format. The file will be saved in the provided directory.
     * <p>
     * If the folder does not exist, it may be created even if the export fails.
     * </p>
     * 
     * @param graph
     *            the layout graph to store.
     * @param diagramName
     *            the name if the diagram used as file name.
     * @param suffix
     *            a suffix that can be used in the file name.
     */
    public Path saveAsText(final ElkNode graph, final String diagramName, String suffix) {
        Path file = getTargetFile(diagramName, suffix, TEXT_EXTENSION);

        return saveTo(cleanContentBeforeExport(graph, TEXT_EXTENSION), file);
    }

    /**
     * Exports the given layout graph in a file using ELK XMI format. The file will be saved in the provided directory.
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

        return saveTo(cleanContentBeforeExport(graph, XMI_EXTENSION), file);
    }

    /**
     * Returns a copy of the inputGraph with any necessary cleanups before serialization. The copy avoids side effects
     * on the inputGraph.
     * 
     * @param inputGraph
     *            The graph to copy and cleanup.
     * @param extension
     *            The extension used to store the file (some cleanups are specific to extension of file to store)
     * @return A clean graph ready to be serialized.
     */
    private ElkNode cleanContentBeforeExport(ElkNode inputGraph, String extension) {
        ElkNode copy = EcoreUtil.copy(inputGraph);
        // Disable the layout stored in this graph to avoid an automatic layout during the opening in "Layout Graph"
        // view of in interactive web editor (https://rtsys.informatik.uni-kiel.de/elklive/).
        copy.setProperty(CoreOptions.NO_LAYOUT, true);
        if (TEXT_EXTENSION.equals(extension)) {
            // Inspired from org.eclipse.elk.graph.text.ui.ConvertGraphHandler
            // Unsupported by rendering
            removeProperty(copy, CoreOptions.RESOLVED_ALGORITHM);

            // Write missing/fix identifiers into the graph.
            GraphIdentifierGenerator.forGraph(copy).assertValid().assertExists().assertUnique().execute();
        }
        return copy;
    }

    private static void removeProperty(ElkNode it, IProperty<?> key) {
        it.setProperty(key, null);
        it.getChildren().forEach(child -> removeProperty(child, key));
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
