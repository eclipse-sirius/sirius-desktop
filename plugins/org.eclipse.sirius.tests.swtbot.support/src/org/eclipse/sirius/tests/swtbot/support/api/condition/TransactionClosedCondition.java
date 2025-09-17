/**
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Lifecycle;
import org.eclipse.emf.transaction.TransactionalEditingDomainEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomainListenerImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * A {@link ICondition} to wait that a transaction is closed.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TransactionClosedCondition extends DefaultCondition implements ICondition {

    private final TransactionListener transactionListener;

    private boolean transactionClosed;

    /**
     * Default constructor.
     * 
     * @param domain
     *            The {@link TransactionalEditingDomain} on which listens
     *            Tranasaction's close.
     */
    public TransactionClosedCondition(TransactionalEditingDomain domain) {
        Lifecycle lifecycle = TransactionUtil.getAdapter(domain, Lifecycle.class);
        transactionListener = new TransactionListener();
        lifecycle.addTransactionalEditingDomainListener(transactionListener);
    }

    @Override
    public boolean test() throws Exception {
        return transactionClosed;
    }

    @Override
    public String getFailureMessage() {
        return "Transaction close haven't happend";
    }

    private final class TransactionListener extends TransactionalEditingDomainListenerImpl {
        @Override
        public void transactionClosed(TransactionalEditingDomainEvent event) {
            transactionClosed = true;
            Lifecycle lifecycle = TransactionUtil.getAdapter(event.getSource(), Lifecycle.class);
            lifecycle.removeTransactionalEditingDomainListener(transactionListener);
        }
    }

}
