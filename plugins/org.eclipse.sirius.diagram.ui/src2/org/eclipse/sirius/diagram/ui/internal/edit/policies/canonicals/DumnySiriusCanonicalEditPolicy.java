/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.policies.canonicals;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.util.EditPartUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.internal.refresh.IsOrphanedSwitch;
import org.eclipse.sirius.diagram.part.SiriusDiagramUpdater;
import org.eclipse.sirius.diagram.part.SiriusNodeDescriptor;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;

/**
 * Dumny {@link CanonicalEditPolicy} to have CanonicalRefresh in post-commit
 * deprecated (in favour of canonicalRefresh in precommit :
 * DDiagramCanonicalSynchronizer). This {@link CanonicalEditPolicy} desactivates
 * the {@link Transaction#OPTION_UNPROTECTED} mode use to better detects bugs of
 * canonicalRefresh in precommit.
 * 
 * @author edugueperoux
 */
public class DumnySiriusCanonicalEditPolicy extends CanonicalEditPolicy {

    /**
     * Overridden to desactivates the use of {@link
     * Transaction#OPTION_UNPROTECTED.
     */
    @Override
    protected void executeCommand(final Command cmd) {
        Map<String, Boolean> options = null;
        EditPart ep = getHost();
        boolean isActivating = true;
        // use the viewer to determine if we are still initializing the diagram
        // do not use the DiagramEditPart.isActivating since
        // ConnectionEditPart's
        // parent will not be a diagram edit part
        EditPartViewer viewer = ep.getViewer();
        if (viewer instanceof DiagramGraphicalViewer) {
            isActivating = ((DiagramGraphicalViewer) viewer).isInitializing();
        }

        if (isActivating || !EditPartUtil.isWriteTransactionInProgress((IGraphicalEditPart) getHost(), false, false))
            options = Collections.emptyMap();
        // options = Collections.singletonMap(Transaction.OPTION_UNPROTECTED,
        // Boolean.TRUE);

        AbstractEMFOperation operation = new AbstractEMFOperation(((IGraphicalEditPart) getHost()).getEditingDomain(), StringStatics.BLANK, options) {

            protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

                cmd.execute();

                return Status.OK_STATUS;
            }
        };
        try {
            operation.execute(new NullProgressMonitor(), null);
        } catch (ExecutionException e) {
            Trace.catching(DiagramUIPlugin.getInstance(), DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(), "executeCommand", e); //$NON-NLS-1$
            Log.warning(DiagramUIPlugin.getInstance(), DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING, "executeCommand", e); //$NON-NLS-1$
        }
    }

    @Override
    protected boolean shouldHandleNotificationEvent(Notification event) {
        return true;
    }

    @Override
    protected List getSemanticChildrenList() {
        View viewObject = (View) getHost().getModel();
        List<Object> result = new LinkedList<Object>();
        for (Iterator<SiriusNodeDescriptor> it = SiriusDiagramUpdater.getSemanticChildren(viewObject).iterator(); it.hasNext();) {
            result.add(it.next().getModelElement());
        }
        return result;
    }

    /**
     * Overridden to check if a {@link View} has its {@link View#getElement()}
     * feature to null.
     */
    @Override
    protected boolean isOrphaned(Collection<EObject> semanticChildren, View view) {
        boolean isOrphaned = false;
        View parentView = (View) view.eContainer();
        int visualID = SiriusVisualIDRegistry.getVisualID(parentView);
        isOrphaned = new IsOrphanedSwitch(view, semanticChildren, parentView).doSwitch(visualID);
        return isOrphaned;
    }

}
