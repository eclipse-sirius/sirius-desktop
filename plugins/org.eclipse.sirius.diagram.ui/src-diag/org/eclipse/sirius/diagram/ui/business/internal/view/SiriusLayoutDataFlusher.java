/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * The SiriusLayoutDataFlusher which listens IOperationHistory events to flush the list of {@link RootLayoutData} of the
 * {@link SiriusLayoutDataManagerImpl} after a {@link OperationHistoryEvent#DONE} event.
 * 
 * @author edugueperoux
 */
public class SiriusLayoutDataFlusher implements IOperationHistoryListener {

    private SiriusLayoutDataManagerImpl viewPointLayoutDataManagerImpl;

    /**
     * Default constructor for the SiriusLayoutDataFlusher.
     * 
     * @param viewPointLayoutDataManagerImpl
     *            the {@link SiriusLayoutDataManagerImpl} on which to flush the list of {@link RootLayoutData}
     */
    public SiriusLayoutDataFlusher(SiriusLayoutDataManagerImpl viewPointLayoutDataManagerImpl) {
        this.viewPointLayoutDataManagerImpl = viewPointLayoutDataManagerImpl;
    }

    /**
     * Overridden to flush the list of {@link RootLayoutData} of the {@link SiriusLayoutDataManagerImpl}.
     * 
     * {@inheritDoc}
     */
    public void historyNotification(OperationHistoryEvent event) {
        if (event.getEventType() == OperationHistoryEvent.DONE || event.getEventType() == OperationHistoryEvent.OPERATION_NOT_OK) {
            if (event.getOperation() instanceof EMFCommandOperation) {
                EMFCommandOperation oper = (EMFCommandOperation) event.getOperation();
                TransactionalEditingDomain domain = oper.getEditingDomain();
                if (domain instanceof org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl
                        && ((org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl) domain).getActiveTransaction() != null) {
                    // Ignore operations which corresponds to intermediate sub-transactions: the layout data should be
                    // kept around until the full end of the top-level user interaction.
                    return;
                }
            }

            viewPointLayoutDataManagerImpl.flushRootLayoutDatas();
            Map<Diagram, Set<View>> createdViewsToLayout = viewPointLayoutDataManagerImpl.getCreatedViewForLayoutAll();
            for (Diagram diagram : new ArrayList<Diagram>(createdViewsToLayout.keySet())) {
                if (diagram.eIsProxy() || diagram.eResource() == null || diagram.eResource().getResourceSet() == null) {
                    createdViewsToLayout.remove(diagram);
                }
            }
        }
    }

}
