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

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
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

    /**
     * Remove elements from {@link DView} that will be recreated by the refresh
     * (call later).
     * 
     * @param view
     *            The view model which elements are to be removed.
     * @param domain
     *            the transactional editing domain
     * @param monitor
     *            the progress monitor
     */
    void removeElements(DView view, TransactionalEditingDomain domain, IProgressMonitor monitor);

    /**
     * Clean representations (specific representation states like filters) and
     * eventually return some of them if they need to be removed.
     * 
     * @param representations
     *            List of representations to clean.
     * @return The list of representation to removed
     */
    List<DRepresentation> cleanRepresentations(EList<DRepresentation> representations);

    /**
     * This method has been created during the split of diagram-specific
     * EClasses into their own plug-in. It manages core and diagram stuffs. But
     * to simplify work, all method has been moved to diagram participant. This
     * method should be clean up once the split is OK.
     * 
     * @param dAnalysis
     *            The current dAnalysis
     * @param view
     *            The current view
     */
    void refreshRepresentations(DAnalysis dAnalysis, DView view);
}
