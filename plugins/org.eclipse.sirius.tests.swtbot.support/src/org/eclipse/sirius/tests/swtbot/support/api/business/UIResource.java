/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenSessionAction;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * Object to manage graphical resources.
 * 
 * @author dlecan
 */
public class UIResource {

    private final UIProject project;

    private final String path;

    private final List<String> nodePath;

    private final String name;

    /**
     * Constructor for a resource at the root of the project.
     * 
     * @param pUIProject
     *            Project where is located resource.
     * @param name
     *            Name of resource.
     */
    public UIResource(final UIProject pUIProject, final String name) {
        this(pUIProject, "", name);
    }

    /**
     * Constructor.
     * 
     * @param pUIProject
     *            Project where is located resource.
     * @param pPath
     *            Path in the project to the resource.
     * @param name
     *            Name of resource.
     */
    public UIResource(final UIProject pUIProject, final String pPath, final String name) {
        project = pUIProject;
        path = pPath;
        this.name = name;
        if (pPath == null || pPath.length() == 0) {
            nodePath = new ArrayList<>();
        } else {
            nodePath = Lists.newArrayList(pPath.split("/"));
        }
    }

    /**
     * Create {@link UIResource} from {@link Resource}.
     * 
     * @param resource
     *            Resource to use.
     * @return New {@link UIResource}.
     */
    public static UIResource createFromResource(final Resource resource) {
        return UIResource.createFromURI(resource.getURI());
    }

    /**
     * Create {@link UIResource} from {@link URI} of a {@link Resource}.
     * 
     * @param uri
     *            URi of resource to use.
     * @return New {@link UIResource}.
     */
    public static UIResource createFromURI(final URI uri) {
        UIResource uiResource = null;

        if (uri.isPlatform()) {
            final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
            final IProject project = file.getProject();

            // removeLastSegments meth. removes file name in relative path.
            final String resourcePath = file.getProjectRelativePath().removeLastSegments(1).toOSString();

            uiResource = new UIResource(new UIProject(project.getName()), resourcePath, file.getName());
        }
        return uiResource;
    }

    /**
     * Returns the project.
     * 
     * @return The project.
     */
    public UIProject getProject() {
        return project;
    }

    /**
     * Returns the path.
     * 
     * @return The path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the long name of this resource, including path and name, relative
     * to {@link #getProject()} project.
     * 
     * @return The long name of this resource.
     */
    public String getLongName() {
        String longName = "";
        final String separator = "/";
        final String thePath = getPath();
        if (!"".equals(thePath) && !separator.equals(thePath)) {
            longName += getPath() + separator;
        }
        return longName + getName();
    }

    /**
     * Returns the full path of this file (that path is relative to the
     * containing workspace).
     * 
     * @return a workspace relative path
     */
    public String getFullPath() {
        return getProject().getName() + File.separator + getLongName();
    }

    /**
     * Returns the nodePath.
     * 
     * @return The nodePath.
     */
    public List<String> getNodePath() {
        return nodePath;
    }

    /**
     * Returns the name.
     * 
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Launch to "Open Session" action on the file corresponding to this
     * UIResource.
     */
    public void openSession() {
        final IFile fileToOpen = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(getFullPath()));
        if (!fileToOpen.exists()) {
            Assert.fail("The session could not be open on " + fileToOpen.getFullPath() + ", because this file does not exist.");
        }
        UIThreadRunnable.syncExec(SWTUtils.display(), new VoidResult() {
            @Override
            public void run() {
                OpenSessionAction action = new OpenSessionAction("Open session");
                action.selectionChanged(new StructuredSelection(fileToOpen));
                action.run();
            }
        });
    }

}
