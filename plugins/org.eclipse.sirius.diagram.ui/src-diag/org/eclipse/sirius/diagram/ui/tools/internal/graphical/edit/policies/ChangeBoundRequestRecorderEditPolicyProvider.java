/*******************************************************************************
 * Copyright (c) 2010, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.ISiriusEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;

/**
 * An Edit Policy provider installing a RequestRecorderEditPolicy on every edit
 * part having a SiriusDiagramGraphicalViewer graphical viewer.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class ChangeBoundRequestRecorderEditPolicyProvider implements IEditPolicyProvider {

    /** The role of the request. */
    private static final String RECORD_REQUEST_ROLE = "RecordRequest"; //$NON-NLS-1$

    /** the property change support. */
    private final List<IProviderChangeListener> listeners;

    /**
     * Create a new {@link ChangeBoundRequestRecorderEditPolicyProvider}.
     */
    public ChangeBoundRequestRecorderEditPolicyProvider() {
        this.listeners = new ArrayList<IProviderChangeListener>();
    }

    /**
     * {@inheritDoc}
     */
    public boolean provides(IOperation operation) {
        if (operation instanceof CreateEditPoliciesOperation) {
            CreateEditPoliciesOperation castedOperation = (CreateEditPoliciesOperation) operation;
            EditPart editPart = castedOperation.getEditPart();
            return editPart instanceof ISiriusEditPart;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void createEditPolicies(EditPart editPart) {
        EditPartViewer viewer = editPart.getViewer();
        if (viewer instanceof SiriusDiagramGraphicalViewer) {
            RequestRecorderEditPolicy ep = new RequestRecorderEditPolicy(((SiriusDiagramGraphicalViewer) viewer).getChangeBoundRequestRecorder());
            editPart.installEditPolicy(RECORD_REQUEST_ROLE, ep);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#addProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void addProviderChangeListener(IProviderChangeListener listener) {
        this.listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#removeProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void removeProviderChangeListener(IProviderChangeListener listener) {
        this.listeners.remove(listener);
    }

}
