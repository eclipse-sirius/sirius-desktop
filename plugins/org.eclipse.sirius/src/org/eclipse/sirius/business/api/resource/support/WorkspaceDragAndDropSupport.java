/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.resource.support;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.resource.DraggedObjectTester;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DResource;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointFactory;

/**
 * Support for drag and drop of files from a view to a diagram.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class WorkspaceDragAndDropSupport {

    /**
     * Creates a new instance.
     */
    public WorkspaceDragAndDropSupport() {
    }

    /**
     * Convert the the dragged element to a workspace specific one, depending if
     * the file is workspace resource.
     * 
     * @param draggedElement
     *            the initially dragged element.
     * @param session
     *            the session, could be <code>null</code>.
     * 
     * @return the instance to use as dragged element for the drop tool,
     *         <code>null</code> if none
     */
    public EObject convert(final Object draggedElement, final Session session) {

        EObject eObject = null;

        DraggedObjectTester tester = new DraggedObjectTester(draggedElement);
        if (tester.isEObject()) {
            if (tester.isInSession(session)) {
                eObject = tester.getEObject();
            } else if (tester.isDSemanticDecoratorAndTargetIsInSession(session)) {
                eObject = ((DSemanticDecorator) draggedElement).getTarget();
            }
        } else if (tester.isWorkspaceResource()) {
            if (tester.isWorkspaceFile() && tester.isLoadableModel()) {
                eObject = convertModel((IFile) draggedElement, session);
            } else {
                eObject = convertResource((IResource) draggedElement, session);
            }
        }

        return eObject;
    }

    private DResource convertResource(final IResource resource, final Session session) {

        DResource dResource = null;
        if (resource instanceof IContainer) {
            dResource = convertContainer((IContainer) resource, session);
        } else if (resource instanceof IFile) {
            dResource = convertFile((IFile) resource, session);
        }
        return dResource;
    }

    private DResourceContainer convertContainer(final IContainer container, final Session session) {

        DResourceContainer dContainer = null;
        if (container instanceof IProject) {
            dContainer = createDProject((IProject) container);
        } else if (container instanceof IFolder) {
            dContainer = createDFolder((IFolder) container);
        }
        if (dContainer != null) {
            attachSession(dContainer, session);

            if (container.isAccessible()) {
                try {
                    for (final IResource resource : container.members()) {
                        DResource dResource = convertResource(resource, session);
                        if (dResource != null) {
                            dContainer.getMembers().add(dResource);
                        }
                    }
                } catch (CoreException e) {
                    /* do nothing */
                }
            }
        }
        return dContainer;
    }

    private DModel convertModel(final IFile file, final Session session) {
        final DModel model = createDModel(file);
        attachSession(model, session);
        return model;
    }

    private DModel createDModel(final IFile file) {
        final DModel model = ViewpointFactory.eINSTANCE.createDModel();
        model.setName(file.getName());
        model.setPath(convertPathToString(file));
        return model;
    }

    /**
     * Convert a dropped IFile into a DFile one.
     * 
     * @param file
     *            the dropped IFile instance
     * @return an IFile instance
     */
    private DFile convertFile(final IFile file, final Session session) {
        final DFile dFile = createDFile(file);
        attachSession(dFile, session);
        return dFile;
    }

    private void attachSession(final EObject eObject, final Session session) {
        eObject.eAdapters().add(new SessionTransientAttachment(session));
    }

    private DFile createDFile(final IFile file) {
        final DFile dFile = ViewpointFactory.eINSTANCE.createDFile();
        dFile.setName(file.getName());
        dFile.setPath(convertPathToString(file));
        return dFile;
    }

    private DFolder createDFolder(final IFolder folder) {
        final DFolder dFolder = ViewpointFactory.eINSTANCE.createDFolder();
        dFolder.setName(folder.getName());
        dFolder.setPath(convertPathToString(folder));
        return dFolder;
    }

    private DProject createDProject(final IProject project) {
        final DProject dProject = ViewpointFactory.eINSTANCE.createDProject();
        dProject.setName(project.getName());
        dProject.setPath(convertPathToString(project));
        return dProject;
    }

    /**
     * Add a semantic resource to a session.
     * 
     * @param resource
     *            the resource to add
     * @param session
     *            the target session
     */
    public void addSemanticResourceToSession(final Resource resource, final Session session) {
        if (session != null && !hasURIInSemanticResources(session.getSemanticResources(), resource.getURI())) {
            session.addSemanticResource(resource.getURI(), new NullProgressMonitor());
        }
    }

    private boolean hasURIInSemanticResources(final Iterable<Resource> resources, final URI uri) {
        boolean found = false;
        if (uri != null) {
            for (final Resource resource : resources) {
                if (resource.getURI().equals(uri)) {
                    found = true;
                }
            }
        }
        return found;
    }

    private String convertPathToString(final IResource resource) {
        return resource.getLocation().toString();
    }

    /**
     * Get the workspace file from a {@link DFile} instance.
     * 
     * @param file
     *            the file
     * @return the workspace file
     */
    public IFile getWorkspaceFile(DFile file) {
        return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(file.getPath()));
    }
}
