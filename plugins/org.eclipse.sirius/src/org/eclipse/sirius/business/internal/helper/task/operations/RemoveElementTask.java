/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.IDeletionTask;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;

/**
 * This task remove an element and unset all the references an element may have
 * DRepresentationElement on this one.
 * 
 * @author Cedric Brun (cbrun)
 * 
 */
public class RemoveElementTask extends AbstractOperationTask implements IDeletionTask {

    private EObject toBeRemoved;

    private boolean deleteView;

    /**
     * Default constructor.
     * 
     * @param context
     *            the command context
     * @param extPackage
     *            the extended package
     * @param op
     *            the operation
     * @param session
     *            the {@link Session} to be used to this task
     */
    public RemoveElementTask(final CommandContext context, final ModelAccessor extPackage, final RemoveElement op, final Session session) {
        super(context, extPackage, session.getInterpreter());
    }

    /**
     * Default constructor.
     * 
     * @param extPackage
     *            the extended package
     * @param context
     *            the command context
     * @param op
     *            the operation
     * @param session
     *            the {@link Session} to be used to this task
     */
    public RemoveElementTask(final ModelAccessor extPackage, final CommandContext context, final DeleteView op, final Session session) {
        super(context, extPackage, session.getInterpreter());
        deleteView = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        this.toBeRemoved = context.getCurrentTarget();
        if (deleteView) {
            if (!(toBeRemoved instanceof DRepresentationElement)) {
                SiriusPlugin.getDefault().warning("The element is not a view ! Do not delete !", new IllegalArgumentException());
                return;
            }
        }
        if (toBeRemoved != null && this.toBeRemoved.eContainingFeature() != null) {
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(toBeRemoved).eRemove(toBeRemoved);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "Remove an element";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IDeletionTask#getDeletedElements()
     */
    public Collection<EObject> getDeletedElements() {
        final Collection<EObject> result = new ArrayList<EObject>();
        if (this.toBeRemoved != null) {
            result.add(this.toBeRemoved);
            final Iterator<EObject> it = this.toBeRemoved.eAllContents();
            while (it.hasNext()) {
                result.add(it.next());
            }
        }
        return result;
    }
}
