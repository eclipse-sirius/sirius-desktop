/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A task to delete a representation.
 * 
 * @author mchauvin
 */
public class DeleteDRepresentationTask extends AbstractCommandTask {

    /** The representation to delete. */
    protected DRepresentation representation;

    /** Also delete the dangling references. */
    protected boolean deleteReferences;

    /**
     * Default constructor.
     * 
     * @param representation
     *            the representation to delete
     */
    public DeleteDRepresentationTask(final DRepresentation representation) {
        this.representation = representation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {

        /* only destroy attached elements */
        if (representation != null) {
            Resource resource = representation.eResource();
            if (resource != null) {
                ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);

                if (deleteReferences) {
                    final Session session;
                    if (representation instanceof DSemanticDecorator) {
                        session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
                    } else {
                        session = SessionManager.INSTANCE.getSession(representation);
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
        if (representation != null) {
            return MessageFormat.format(Messages.DeleteDRepresentationTask_label, representation.getName());
        }
        return Messages.DeleteRepresentationCommand_label;
    }
}
