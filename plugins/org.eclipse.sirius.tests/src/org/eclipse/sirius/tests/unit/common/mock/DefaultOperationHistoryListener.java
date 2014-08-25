/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.mock;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.ResourceUndoContext;

import com.google.common.collect.Iterables;

/**
 * This class is an operation listener.
 * 
 * @author mchauvin
 */
public class DefaultOperationHistoryListener implements IOperationHistoryListener {

    /** The undo context. */
    private IUndoContext context;

    /** The editing domain */
    private EditingDomain domain;

    /**
     * Default constructor.
     * 
     * @param context
     *            The undo context
     * @param domain
     *            The editing domain
     */
    public DefaultOperationHistoryListener(IUndoContext context, EditingDomain domain) {
        this.context = context;
        this.domain = domain;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
     */
    public void historyNotification(final OperationHistoryEvent event) {

        if (event.getEventType() == OperationHistoryEvent.DONE) {
            IUndoableOperation operation = event.getOperation();

            if (shouldAddUndoContext(operation)) {
                // add my undo context to populate my undo menu
                operation.addContext(this.context);
            }
        }
    }

    /**
     * Answers whether or not I should add my undo context to the undoable
     * <code>operation</code>, thereby making the operation available from my
     * undo menu.
     * <P>
     * The default implementation adds my context to any operation that affected
     * the same editing domain that has loaded the resource that contains my
     * diagram element. Subclasses can override this method if they wish to add
     * their context to operations for different reasons.
     * 
     * @param operation
     *            the operation
     * @return <code>true</code> if the operation should appear on my undo menu,
     *         <code>false</code> otherwise.
     */
    protected boolean shouldAddUndoContext(IUndoableOperation operation) {

        if (domain != null) {
            for (Resource nextResource : Iterables.filter(ResourceUndoContext.getAffectedResources(operation), Resource.class)) {
                ResourceSet resourceSet = nextResource.getResourceSet();
                if (resourceSet != null) {
                    TransactionalEditingDomain resourceSetEditingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(resourceSet);
                    if (domain.equals(resourceSetEditingDomain)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
