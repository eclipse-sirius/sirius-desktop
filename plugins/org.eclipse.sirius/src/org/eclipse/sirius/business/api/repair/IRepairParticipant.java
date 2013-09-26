/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.repair;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DView;

/**
 * Repair Participant to contribute to repair process.
 * 
 * @author fbarbin
 * 
 */
public interface IRepairParticipant {

    /**
     * Call when a repair has started.
     */
    void repairStarted();

    /**
     * Call when a repair has completed.
     */
    void repairCompeleted();

    /**
     * Restores the state of all the model's DRepresentationElement.
     * 
     * @param view
     *            The view model which state is to be restored.
     * @param monitor
     *            the progress monitor
     */
    void restoreModelElementState(DView view, IProgressMonitor monitor);

    // /**
    // * Actions should be done before execute refresh on given {@link DView}.
    // *
    // * @param view
    // * the view containing representations which going to be
    // * refreshed.
    // */
    // void setUpRefreshOperation(DView view);
    //
    // /**
    // * Any object should be disposed after refresh operation.
    // */
    // void disposeRefreshOperation();

    /**
     * Do some post refresh operations such as removing invalid elements.
     * 
     * @param domain
     *            the transactional editing domain
     * @param resource
     *            the resource
     */
    void postRefreshOperations(TransactionalEditingDomain domain, Resource resource);

    /**
     * Save the sate of the model's
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement}.
     * 
     * @param view
     *            The view model which state is to be saved.
     * @param monitor
     *            the progress monitor
     */
    void saveModelElementState(DView view, IProgressMonitor monitor);

    /**
     * Called after repair action on view.
     */
    void endRepairOnView();

    /**
     * Called before execute repair action on given {@link DView}.
     * 
     * @param session
     *            the current session.
     * @param view
     *            the current view.
     */
    void startRepairOnView(Session session, DView view);

}
