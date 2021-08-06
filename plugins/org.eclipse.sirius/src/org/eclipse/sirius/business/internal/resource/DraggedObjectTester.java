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
package org.eclipse.sirius.business.internal.resource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.resource.support.LoadEMFResource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Tester for object to drag in a drag'n drop operation.
 * 
 * @author mchauvin
 */
public class DraggedObjectTester {

    private Object obj;

    private EObject eObj;

    private boolean alreadyTested;

    /**
     * Default constructor which take the Object to test in parameter.
     * 
     * @param obj
     *            the object to test
     */
    public DraggedObjectTester(Object obj) {
        this.obj = obj;
    }

    /**
     * Check if dragged element is an instance of {@link EObject} or could be
     * adapted to an {@link EObject} one.
     * 
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public boolean isEObject() {
        return getEObject() != null;
    }

    /**
     * Get the EObject from the initial object.
     * 
     * @return the {@link EObject} instance.
     */
    public EObject getEObject() {
        if (!alreadyTested) {
            alreadyTested = true;
            if (obj instanceof EObject)
                eObj = (EObject) obj;
            else if (obj instanceof IAdaptable)
                eObj = ((IAdaptable) obj).getAdapter(EObject.class);
        }
        return eObj;
    }

    /**
     * Check if dragged element is in session.
     * 
     * @param session
     *            session, could be <code>null</code>
     * @return <code>true</code> if session is not <code>null</code> and it
     *         contains dragged elements, <code>false</code> otherwise
     */
    public boolean isInSession(Session session) {
        boolean isInSession = false;
        if (session != null) {
            final EObject eObject = getEObject();
            if (eObject != null) {
                Resource semanticResource = eObject.eResource();
                isInSession = session.getSemanticResources().contains(semanticResource) || ((DAnalysisSessionEObject) session).getControlledResources().contains(semanticResource);
            }
        }
        return isInSession;
    }

    /**
     * Check if dragged element is an instance of {@link IResource}.
     * 
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public boolean isWorkspaceResource() {
        return obj instanceof IResource;
    }

    /**
     * Check if dragged element is an instance of {@link IFile}.
     * 
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public boolean isWorkspaceFile() {
        return obj instanceof IFile;
    }

    /**
     * Check if dragged element is loadable model.
     * 
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public boolean isLoadableModel() {
        if (isWorkspaceFile()) {
            ResourceSet set = new ResourceSetImpl();
            LoadEMFResource runnable = new LoadEMFResource(set, (IFile) obj);
            runnable.run();
            return runnable.getLoadedResource() != null;
        }
        return false;
    }

    /**
     * Check if dragged element is an instance of {@link DSemanticDecorator} and
     * if his target is in the same session.
     * 
     * @param session
     *            the session to test against.
     * @return <code>true</code> if is an instance of {@link DSemanticDecorator}
     *         , <code>false</code> otherwise
     */
    public boolean isDSemanticDecoratorAndTargetIsInSession(Session session) {
        return (obj instanceof DSemanticDecorator) && session != null && SessionManager.INSTANCE.getSession(((DSemanticDecorator) obj).getTarget()) == session;
    }

}
