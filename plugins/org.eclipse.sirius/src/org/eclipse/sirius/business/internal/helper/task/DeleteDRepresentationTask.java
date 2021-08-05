/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.helper.task;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A task to delete a representation.
 * 
 * @author mchauvin
 */
public class DeleteDRepresentationTask extends AbstractCommandTask {

    /** The representation to delete. */
    protected DRepresentationDescriptor representationDescriptor;

    /** Also delete the dangling references. */
    protected boolean deleteReferences;

    /**
     * Default constructor.
     * 
     * @param representationDescriptor
     *            the {@link DRepresentationDescriptor} referencing the representation to delete.
     */
    public DeleteDRepresentationTask(final DRepresentationDescriptor representationDescriptor) {
        this.representationDescriptor = representationDescriptor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {

        /* only destroy attached elements */
        if (representationDescriptor != null) {
            Resource resource = representationDescriptor.eResource();
            if (resource != null) {
                DRepresentation representation = representationDescriptor.getRepresentation();
                ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);

                if (deleteReferences) {
                    final Session session;
                    if (representation instanceof DSemanticDecorator) {
                        session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
                    } else {
                        session = SessionManager.INSTANCE.getSession(representationDescriptor);
                    }
                    accessor.eDelete(representation, session != null ? session.getSemanticCrossReferencer() : null);
                } else {
                    // remove the object from its container
                    accessor.eRemove(representation);
                }
            }
        }
    }

    public void setDeleteIncomingReferences(final boolean value) {
        deleteReferences = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        if (representationDescriptor != null) {
            return MessageFormat.format(Messages.DeleteDRepresentationTask_label, representationDescriptor.getName());
        }
        return Messages.DeleteRepresentationCommand_label;
    }
}
