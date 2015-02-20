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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Lifecycle;
import org.eclipse.emf.transaction.TransactionalEditingDomainEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomainListenerImpl;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * Encapsulates the decision of *when* to actually save the session's state when
 * Session.save() is called. If Session.save() is called while a transaction is
 * in progress and deferSaveToPostCommit is true, the actual saving will be
 * performed after the current transaction has been successfully commited.
 * Otherwise it is performed immediatly.
 * 
 * @author pcdavid
 */
final class Saver extends TransactionalEditingDomainListenerImpl {

    boolean deferSaveToPostCommit;

    boolean saveInExclusiveTransaction;

    AtomicBoolean domainDisposed = new AtomicBoolean(false);

    private TransactionalEditingDomain domain;

    private final DAnalysisSessionImpl session;

    private AtomicBoolean saveOnPostCommit = new AtomicBoolean(false);

    private Map<?, ?> savedOptions;

    private IProgressMonitor savedMonitor;

    private AtomicBoolean isSaving = new AtomicBoolean();

    /**
     * Create a new Saver for the specified session.
     * 
     * @param session
     *            the session to save.
     */
    public Saver(DAnalysisSessionImpl session) {
        this.session = session;
        domain = session.getTransactionalEditingDomain();
        Lifecycle lifecycle = TransactionUtil.getAdapter(domain, Lifecycle.class);
        if (lifecycle != null) {
            lifecycle.addTransactionalEditingDomainListener(this);
        }
    }

    /**
     * Do saving after transaction closing in case the SavingPolicy trigger
     * another transaction by executing a EMF Command.
     */
    @Override
    public void transactionClosed(TransactionalEditingDomainEvent event) {
        if (!event.getTransaction().isReadOnly()) {
            if (saveOnPostCommit.get()) {
                saveNow(this.savedOptions, this.savedMonitor, saveInExclusiveTransaction);
            }
        }
    }

    public void save(Map<?, ?> options, IProgressMonitor monitor) {
        boolean tip = transactionInProgress();
        if (tip && deferSaveToPostCommit) {
            saveAfterTransactionClosing(options, monitor);
        } else {
            saveNow(options, monitor, saveInExclusiveTransaction && !tip && !domainDisposed.get());
        }
    }

    /**
     * Arm the trigger so that the saving is performed after transaction
     * closing.
     */
    private void saveAfterTransactionClosing(Map<?, ?> options, IProgressMonitor monitor) {
        this.savedOptions = options;
        this.savedMonitor = monitor;
        this.saveOnPostCommit.set(true);
    }

    /**
     * Save immediately and disarm the trigger.
     */
    private void saveNow(Map<?, ?> options, IProgressMonitor monitor, boolean runExclusive) {
        // This allows to have session saving thread safe, i.e. only one thread
        // can do a save at a time
        synchronized (isSaving) {
            // In addition if the session saving or more specifically its
            // SavingPolicy execute a EMF Command, and we have saveOnPostCommit
            // at true, we risk a StackOverflow then to avoid that we check if
            // we are already in a session saving call
            if (!isSaving.get()) {
                try {
                    isSaving.set(true);
                    session.doSave(options, monitor, runExclusive);
                } finally {
                    disarm();
                    isSaving.set(false);

                }
            }
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

    public void dispose() {
        Lifecycle lifecycle = TransactionUtil.getAdapter(domain, Lifecycle.class);
        if (lifecycle != null) {
            lifecycle.removeTransactionalEditingDomainListener(this);
        }
        disarm();
    }

}
