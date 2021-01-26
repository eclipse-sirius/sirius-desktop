/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.ClassLoading;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.JavaExtensionsManager;
import org.eclipse.sirius.common.tools.internal.interpreter.ClassLoadingService;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.api.editor.ITableCellEditorFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * The {@link CellEditorFactoryManager} loads an instance of {@link ITableCellEditorFactory} corresponding to a
 * qualified name and based on the current search scope (defined by the session). The class is searched in all plug-ins
 * containing the activated viewpoints (and in plug-in dependencies).<BR/>
 * Inspired from class {@link JavaExtensionsManager}.
 * 
 * @author lredor
 */
public final class CellEditorFactoryManager {

    /**
     * This will be updated with the list of accessible viewpoint plugins, if
     * any.
     */
    private Set<String> viewpointPlugins = new LinkedHashSet<>();

    /**
     * This will be updated with the list of accessible viewpoint projects
     * present in the workspace, if any.
     */
    private Set<String> viewpointProjects = new LinkedHashSet<>();


    private ClassLoading classLoading;

    /**
     * Create a new CellEditorFactoryManager.
     * 
     * @param session
     *            The current session to initialize the scope (necessary to load factory class).
     * 
     */
    private CellEditorFactoryManager(DAnalysisSessionImpl session) {
        classLoading = ClassLoadingService.getClassLoading();
        configure(session);
    }

    /**
     * Get the ITableCellEditorFactory corresponding to the <code>qualifiedClassName</code>.
     * 
     * @param session
     *            The current session to initialize the scope (necessary to load the CellEditorFactory class).
     * @param qualifiedClassName
     *            the Java qualified name of a class to consider as a {@link ITableCellEditorFactory}
     * @return the ITableCellEditorFactory (IllegalArgumentException is returned with cause if the CellEditorFactory can
     *         not be returned)
     * @throws IllegalArgumentException
     *             in case of the method can not get the CellEditorFactory corresponding to the
     *             <code>qualifiedClassName</code>.
     */
    public static ITableCellEditorFactory getCellEditorFactory(DAnalysisSessionImpl session, String qualifiedClassName) throws IllegalArgumentException {
        CellEditorFactoryManager cellEditorFactoryManager = new CellEditorFactoryManager(session);
        ITableCellEditorFactory result;
        try {
            result = cellEditorFactoryManager.instantiateCellEditorFactory(qualifiedClassName);
            return result;
        } finally {
            cellEditorFactoryManager.dispose();
        }
    }

    /**
     * Configure the searched scope corresponding to this session (the selected viewpoints, more precisely their
     * plug-ins and their dependencies, are used to retrieve accessible classes).
     */
    void configure(DAnalysisSessionImpl dasi) {
        // Calculate paths of the activated representation description files.
        List<String> filePaths = new ArrayList<String>();
        for (Viewpoint vp : dasi.getSelectedViewpointsSpecificToGeneric()) {
            Resource vpResource = vp.eResource();
            if (vpResource != null) {
                filePaths.add(vpResource.getURI().toPlatformString(true));
            }
        }
        updateScope(filePaths);
    }

    /**
     * Clear the resources used by the {@link CellEditorFactoryManager}.
     */
    public void dispose() {
        classLoading.dispose();
        this.viewpointPlugins.clear();
        this.viewpointProjects.clear();
    }

    /**
     * Instantiate the ITableCellEditorFactory corresponding to the <code>qualifiedClassName</code>.
     * 
     * @param qualifiedClassName
     *            the Java qualified name of a class to consider as a {@link ITableCellEditorFactory}
     * @return a new instance of {@link ITableCellEditorFactory} (IllegalArgumentException is returned with cause if a
     *         {@link ITableCellEditorFactory} can not be returned)
     * @throws IllegalArgumentException
     *             in case of the method can not get the {@link ITableCellEditorFactory} corresponding to the
     *             <code>qualifiedClassName</code>.
     */
    public ITableCellEditorFactory instantiateCellEditorFactory(String qualifiedClassName) throws IllegalArgumentException {
        Class<?> found = classLoading.findClass(viewpointProjects, viewpointPlugins, qualifiedClassName);

        if (found != null) {
            Object instance = null;
            try {
                instance = found.newInstance();
            } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
                // The class can not be instantiated, throw an global exception.
                throw new IllegalArgumentException(MessageFormat.format(Messages.CelEditorFactoryManager_impossibleInstantiation, qualifiedClassName), e);
            }
            if (instance instanceof ITableCellEditorFactory) {
                return (ITableCellEditorFactory) instance;
            } else {
                throw new IllegalArgumentException(MessageFormat.format(Messages.CelEditorFactoryManager_wrongImplementation, qualifiedClassName));
            }
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.CelEditorFactoryManager_notFound, qualifiedClassName));
        }
    }

    /**
     * Consider a list of odesign paths to update the scope as sent to the {@link IInterpreter} instances through the
     * setProperty() method with IInterpreter.FILES key.
     * 
     * @param value
     *            can be null, or a list of String each being the identifier of a project which can be in the workspace
     *            or not.
     */
    public void updateScope(Collection<String> value) {
        if (value != null) {
            for (final String odesignPath : value) {
                final URI workspaceCandidate = URI.createPlatformResourceURI(odesignPath, true);
                final URI pluginCandidate = URI.createPlatformPluginURI(odesignPath, true);
                if (existsInWorkspace(workspaceCandidate.toPlatformString(true))) {
                    viewpointProjects.add(workspaceCandidate.segment(1));
                } else if (existsInPlugins(URI.decode(pluginCandidate.toString()))) {
                    viewpointPlugins.add(pluginCandidate.segment(1));
                }
            }
        }
    }

    /**
     * Checks whether the given path exists in the plugins.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing plugin
     *         resource, <code>false</code> otherwise.
     */
    private static boolean existsInPlugins(String path) {
        try {
            URL url = new URL(path);
            return FileLocator.find(url) != null;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Checks whether the given path exists in the workspace.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing workspace
     *         resource, <code>false</code> otherwise.
     */
    private static boolean existsInWorkspace(String path) {
        if (path == null || path.length() == 0 || EcorePlugin.getWorkspaceRoot() == null) {
            return false;
        }
        return ResourcesPlugin.getWorkspace().getRoot().exists(new Path(path));
    }
}
