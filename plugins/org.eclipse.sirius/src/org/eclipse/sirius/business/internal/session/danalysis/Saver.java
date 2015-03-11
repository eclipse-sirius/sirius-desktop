/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES, Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomainEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomainListener;
import org.eclipse.emf.transaction.TransactionalEditingDomainListenerImpl;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;

/**
 * Encapsulates the decision of *when* to actually save the session's state
 * when Session.save() is called. If Session.save() is called while a
 * transaction is in progress and deferSaveToPostCommit is true, the actual
 * saving will be performed after the current transaction has been
 * successfully commited. Otherwise it is performed immediatly.
 * 
 * @author pcdavid
 */
final class Saver extends ResourceSetListenerImpl {

    
    boolean deferSaveToPostCommit;
    
    boolean saveInExclusiveTransaction;
    
    AtomicBoolean domainDisposed = new AtomicBoolean(false);
    private final DAnalysisSessionImpl session;

    private AtomicBoolean saveOnPostCommit = new AtomicBoolean(false);

    private Map<?, ?> savedOptions;

    private IProgressMonitor savedMonitor;

    /**
     * Make sure the Saver's state is reset after the transaction is
     * finished, even in case of rollback (in which case #resourceSetChanged
     * will not have need called).
     */
    private TransactionalEditingDomainListener domainListener = new TransactionalEditingDomainListenerImpl() {
        @Override
        public void transactionClosed(TransactionalEditingDomainEvent event) {
            disarm();
        }

        @Override
        public void editingDomainDisposing(TransactionalEditingDomainEvent event) {
            domainDisposed.set(true);
        }
    };

    /**
     * Create a new Saver for the specified session.
     * 
     * @param session
     *            the session to save.
     */
    public Saver(DAnalysisSessionImpl session) {
        this.session = session;
    }

    public void initialize() {
        TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
        if (ted instanceof TransactionalEditingDomain.Lifecycle) {
            TransactionalEditingDomain.Lifecycle lc = (TransactionalEditingDomain.Lifecycle) ted;
            lc.addTransactionalEditingDomainListener(domainListener);
        }
    }

    public void dispose() {
        TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
        if (ted instanceof TransactionalEditingDomain.Lifecycle) {
            TransactionalEditingDomain.Lifecycle lc = (TransactionalEditingDomain.Lifecycle) ted;
            lc.removeTransactionalEditingDomainListener(domainListener);
        }
        disarm();
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        if (saveOnPostCommit.get()) {
            saveNow(this.savedOptions, this.savedMonitor, true);
        }
    }

    public void save(Map<?, ?> options, IProgressMonitor monitor) {
        boolean tip = transactionInProgress();
        if (tip && deferSaveToPostCommit) {
            saveOnPostCommit(options, monitor);
        } else {
            saveNow(options, monitor, saveInExclusiveTransaction && !tip && !domainDisposed.get());
        }
    }

    /**
     * Arm the trigger so that the saving is performed on the next
     * post-commit.
     */
    private void saveOnPostCommit(Map<?, ?> options, IProgressMonitor monitor) {
        this.savedOptions = options;
        this.savedMonitor = monitor;
        this.saveOnPostCommit.set(true);
    }

    /**
     * Save immediately and disarm the trigger.
     */
    private void saveNow(Map<?, ?> options, IProgressMonitor monitor, boolean runExclusive) {
        try {
            session.doSave(options, monitor, runExclusive);
        } finally {
            disarm();
        }
    }

    protected void disarm() {
        this.savedOptions = null;
        this.savedMonitor = null;
        this.saveOnPostCommit.set(false);
    }

    private boolean transactionInProgress() {
        if (session.getTransactionalEditingDomain() instanceof InternalTransactionalEditingDomain) {
            InternalTransaction tx = ((InternalTransactionalEditingDomain) session.getTransactionalEditingDomain()).getActiveTransaction();
            return tx != null;
        }
        return false;
    }

}