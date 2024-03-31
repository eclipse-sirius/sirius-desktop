/*******************************************************************************
 * Copyright (c) 2024 OBEO and others.
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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Collections;


/**
 * Tracer of diagram layout.
 * <p>
 * This tracer saves ELK Graph as XMI or textual format in a file. File location 
 * depends of value of trace option '{@value ElkDiagramLayoutTracer#PATH_PROPERTY}'.
 * </p>
 *
 * @author nperansin
 */
public class ElkDiagramLayoutTracer {
    
    // Built-in Java property.
    private static String TMPDIR_PROPERTY = "java.io.tmpdir"; //$NON-NLS-1$
    private static String XMI_EXTENSION = ".elkg"; //$NON-NLS-1$
    private static String TEXT_EXTENSION = ".elkt"; //$NON-NLS-1$
    
    private static ILog LOGGER = Platform.getLog(ElkDiagramLayoutTracer.class);

    /** Flag to trace as text. */
    public static String TEXT_PROPERTY = DiagramElkPlugin.PLUGIN_ID + "/debug/as_text";
    /** Location of trace : unset tmp, absolute from workspace or relative so AIRD file. */
    public static String PATH_PROPERTY = DiagramElkPlugin.PLUGIN_ID + "/debug/path";
    

    private final Path workspace;
    private final Path root;
    
    private final boolean asText;
    private final boolean inDebug;

    /** 
     * Constructor.
     * 
     * @param debug activate debug trace
     */
    public ElkDiagramLayoutTracer(boolean debug) {
        this.inDebug = debug;
        asText = Boolean.parseBoolean(Platform.getDebugOption(TEXT_PROPERTY));
        
        IResource ws = ResourcesPlugin.getWorkspace().getRoot();
        workspace = Path.of(ws.getRawLocation().toOSString());
        
        // Resolve root path
        String pathValue = Platform.getDebugOption(PATH_PROPERTY);
        Path path = null;
        if (pathValue != null && !pathValue.isEmpty()) {
            try {
                if (pathValue.startsWith("/")) {
                    path = workspace.resolve(pathValue.substring(1));
                } else {
                    path = Path.of(pathValue);
                }
            } catch (InvalidPathException e) {
                // To default location
            }
        } 
        
        root = path != null ? path : Path.of(System.getenv(TMPDIR_PROPERTY));
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
     *                     the layout graph to store.
     * @param diagramName
     *                     the name of diagram.
     * @param suffix
     *                     a suffix that can be used in the file name.
     */
    void debug(final URI sessionUri, final ElkNode diagramNode, String suffix) {
        if (inDebug) {
            // Graph must to be cleaned to be exported.
            ElkNode copy = EcoreUtil.copy(diagramNode);
            if (asText) {
                saveAsText(sessionUri, copy, diagramNode.getIdentifier(), suffix);
            } else {
                saveAsGraph(sessionUri, copy, diagramNode.getIdentifier(), suffix);
            }
        }
    }

    private Path getTargetFile(final URI sessionUri, final String diagramName, String suffix, String extension) {
        Path file = root.resolve(getFilename(diagramName, suffix, TEXT_EXTENSION));
        if (sessionUri == null || root.isAbsolute()) {
            return file;
        }
        
        Path parent = workspace;
        if (sessionUri.isFile()) {
            parent = Path.of(sessionUri.toFileString()).getParent();
        } else if (sessionUri.isPlatformResource()) {
            IWorkspaceRoot ws = ResourcesPlugin.getWorkspace().getRoot();
            IFile iFile = ws.getFile(
                    new org.eclipse.core.runtime.Path(sessionUri.toPlatformString(true))
                    );
            parent = Path.of(iFile.getRawLocation().toOSString()).getParent();
            
        }
        return parent.resolve(file);
    }
    
    /**
     * Exports the given layout graph in a file. 
     * 
     * @param graph
     *                     the layout graph to store.
     * @param diagramName
     *                     the name if the diagram used as file name.
     * @param prefix
     *                     a prefix that can be used in the file name.
     */
    public Path saveAsText(final URI sessionUri, final ElkNode graph, final String diagramName, String suffix) {
        Path file = getTargetFile(sessionUri, diagramName, suffix, TEXT_EXTENSION);
        
        return saveTo(cleanContent(graph), file);
    }

    /**
     * Exports the given layout graph in a file. The file will be saved in the
     * provided directory.
     * <p>
     * If the folder does not exist, it may be created even if the export fails.
     * </p>
     * 
     * @param session
     *                     session of diagram.
     * @param graph
     *                     the layout graph to store.
     * @param diagramName
     *                     the name of diagram.
     * @param suffix
     *                     a suffix that can be used in the file name.
     */
    public Path saveAsGraph(URI sessionUri, final ElkNode graph, final String diagramName,
            String suffix) {
        Path file = getTargetFile(sessionUri, diagramName, suffix, XMI_EXTENSION);
        
        return saveTo(cleanContent(graph), file);
    }
 
    private ElkNode cleanContent(ElkNode content) {
        // Inspired from org.eclipse.elk.graph.text.ui.ConvertGraphHandler
        // ElkNode content = EcoreUtil.copy(layoutMapping.getLayoutGraph())
        content.setProperty(CoreOptions.NO_LAYOUT, true);
        
        // Unsupported by rendering
        removeProperty(content, CoreOptions.RESOLVED_ALGORITHM);
        
        // Write missing/fix identifiers into the graph.
        GraphIdentifierGenerator.forGraph(content)
            .assertValid()
            .assertExists()
            .assertUnique()
            .execute();
        return content;
    }

    private static void removeProperty(ElkNode it, IProperty<?> key) {
        it.setProperty(key, null);
        it.getChildren().forEach(child -> removeProperty(child, key));
    }

    /**
     * Saves a EObject into a file.
     * 
     * @param content 
     *                     object to save
     * @param target
     *                     file to save in
     * @throws IOException
     *                     if file cannot be written.
     */
    private static Path saveTo(EObject content, Path target) {
        try {
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.createResource(URI.createFileURI(target.toString()));
            // Disable the layout stored in this graph to avoid an automatic layout during
            // the opening in "Layout Graph" view
            resource.getContents().add(content);
    
            Files.createDirectories(target.getParent());
            resource.save(Collections.emptyMap());
            
            return target;
        } catch (IOException e) {
            // Failure is not critical unless caller need the file.
            LOGGER.error(e.getLocalizedMessage(), e);
            return null;
        }
    }

}
