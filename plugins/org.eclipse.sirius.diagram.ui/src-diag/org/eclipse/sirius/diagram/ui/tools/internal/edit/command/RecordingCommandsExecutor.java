/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.edit.command;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * A Recording Command used in CommandFactory to execute a collection of
 * AbstractModelChangeOperation<T>.
 * 
 * @author smonnier
 */
public class RecordingCommandsExecutor<T> extends RecordingCommand {

    private Collection<AbstractModelChangeOperation<T>> operations;

    /**
     * Constructor.
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param operations
     *            the array of operation to convert.
     */
    public RecordingCommandsExecutor(TransactionalEditingDomain domain, String label, Collection<AbstractModelChangeOperation<T>> operations) {
        super(domain, label);
        this.operations = operations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        for (AbstractModelChangeOperation<T> operation : operations) {
            operation.execute();
        }
    }

}
