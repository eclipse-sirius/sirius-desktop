/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.graphical.edit.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.core.service.ProviderChangeEvent;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.collect.Lists;

import org.eclipse.sirius.SiriusPackage;

/**
 * Provides Edit Policy for Note Attachment.
 * 
 * @author ymortier
 */
public class AirNoteAttachmentEditPolicyProvider implements IEditPolicyProvider {

    /** the property change support. */
    private List<IProviderChangeListener> listeners;

    /**
     * Create a new {@link AirNoteAttachmentEditPolicyProvider}.
     */
    public AirNoteAttachmentEditPolicyProvider() {
        this.listeners = Lists.newArrayListWithCapacity(2);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
     */
    public void createEditPolicies(final EditPart editPart) {
        if (editPart instanceof NoteAttachmentEditPart) {
            editPart.installEditPolicy(EditPolicy.CONNECTION_ROLE, new AirNoteAttachmentEditPolicy());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#addProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void addProviderChangeListener(final IProviderChangeListener listener) {
        this.listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(final IOperation operation) {
        if (operation instanceof CreateEditPoliciesOperation) {
            final CreateEditPoliciesOperation castedOperation = (CreateEditPoliciesOperation) operation;
            final EditPart editPart = castedOperation.getEditPart();
            final Object model = editPart.getModel();
            if (model instanceof View) {
                final View view = (View) model;
                if (view.getDiagram() != null && view.getDiagram().getElement() != null
                        && view.getDiagram().getElement().eClass().getEPackage().getNsURI().equals(SiriusPackage.eINSTANCE.getNsURI())) {
                    if ("NoteAttachment".equals(view.getType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#removeProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void removeProviderChangeListener(final IProviderChangeListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Fire a {@link ProviderChangeEvent}.
     */
    protected void fireProviderChanged() {
        final ProviderChangeEvent event = new ProviderChangeEvent(this);
        for (IProviderChangeListener listener : this.listeners) {
            listener.providerChanged(event);
        }
    }

}
