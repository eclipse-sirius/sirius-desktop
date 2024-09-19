/*******************************************************************************
 * Copyright (c) 2015, 2024 Obeo. 
 * All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which accompanies this distribution, and is
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.SavingPolicy;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.resource.AirDResourceFactory;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Test for Bugzilla 445603.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SaverTest extends SiriusTestCase {

    private File tempFile;

    private Session session;

    Object previousAirdFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        previousAirdFactory = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(SiriusUtil.SESSION_RESOURCE_EXTENSION);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(SiriusUtil.SESSION_RESOURCE_EXTENSION, new AirDResourceFactory());
        tempFile = File.createTempFile("test", "." + SiriusUtil.SESSION_RESOURCE_EXTENSION);
        tempFile.delete();
        URI sessionResourceURI = URI.createFileURI(tempFile.getCanonicalPath());
        session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
    }

    /**
     * Test that a session saving in middle of a EMF Transaction is done only
     * after transaction closing.
     */
    public void testSaveInMiddleOfTransaction() {
        // Make the session dirty
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        DAnalysis dAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        Command updateDAnalysisCmd = new ChangeDAnalysisCmd(domain, dAnalysis);
        domain.getCommandStack().execute(updateDAnalysisCmd);
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        // Test that session is saved only after transaction closing
        ResourceSetListener saverInMiddleOfTx = new SaverInMiddleOfTx(session, false);
        domain.addResourceSetListener(saverInMiddleOfTx);
        updateDAnalysisCmd = new ChangeDAnalysisCmd(domain, dAnalysis);
        domain.getCommandStack().execute(updateDAnalysisCmd);
        assertEquals(SessionStatus.SYNC, session.getStatus());
        domain.removeResourceSetListener(saverInMiddleOfTx);

        // Test also with a rollback
        saverInMiddleOfTx = new SaverInMiddleOfTx(session, true);
        domain.addResourceSetListener(saverInMiddleOfTx);

        updateDAnalysisCmd = new ChangeDAnalysisCmd(domain, dAnalysis);
        domain.getCommandStack().execute(updateDAnalysisCmd);
        assertEquals(SessionStatus.SYNC, session.getStatus());
        domain.removeResourceSetListener(saverInMiddleOfTx);

        updateDAnalysisCmd = new ChangeDAnalysisCmd(domain, dAnalysis);
        domain.getCommandStack().execute(updateDAnalysisCmd);
        assertEquals(SessionStatus.DIRTY, session.getStatus());
    }

    /**
     * Test that a session saving in middle of a EMF Transaction is done only
     * after transaction closing and with a {@link SavingPolicy} executing a EMF
     * Command does not throw exception "IllegalStateException: Cannot activate
     * read/write transaction in read-only transaction context".
     */
    public void testSaveInMiddleOfTransactionWithSavingPolicyExecutingAEMFCommand() {
        ((DAnalysisSessionImpl) session).setSaveInExclusiveTransaction(false);
        final SavingPolicy savingPolicy = session.getSavingPolicy();
        session.setSavingPolicy(new SavingPolicy() {

            @Override
            public Collection<Resource> save(Iterable<Resource> resourcesToSave, Map<?, ?> options, IProgressMonitor monitor) {
                TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                DAnalysis dAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
                Command updateDAnalysisCmd = new ChangeDAnalysisCmd(domain, dAnalysis);
                domain.getCommandStack().execute(updateDAnalysisCmd);
                return savingPolicy.save(resourcesToSave, options, monitor);
            }
        });
        // Make the session dirty
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        DAnalysis dAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        Command updateDAnalysisCmd = new ChangeDAnalysisCmd(domain, dAnalysis);
        domain.getCommandStack().execute(updateDAnalysisCmd);
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        // Test that session is saved only after transaction closing
        ResourceSetListener saverInMiddleOfTx = new SaverInMiddleOfTx(session, false);
        domain.addResourceSetListener(saverInMiddleOfTx);
        updateDAnalysisCmd = new ChangeDAnalysisCmd(domain, dAnalysis);
        domain.getCommandStack().execute(updateDAnalysisCmd);
        assertEquals(SessionStatus.SYNC, session.getStatus());
        domain.removeResourceSetListener(saverInMiddleOfTx);
    }

    private static class ChangeDAnalysisCmd extends RecordingCommand {

        private DAnalysis dAnalysis;

        public ChangeDAnalysisCmd(TransactionalEditingDomain domain, DAnalysis dAnalysis) {
            super(domain);
            this.dAnalysis = dAnalysis;
        }

        @Override
        protected void doExecute() {
            dAnalysis.setVersion(dAnalysis.getVersion() + "Changed");
        }
    }

    private static class SaverInMiddleOfTx extends ResourceSetListenerImpl {

        private Session session;

        private boolean rollback;

        public SaverInMiddleOfTx(Session session, boolean rollback) {
            this.session = session;
            this.rollback = rollback;
        }

        @Override
        public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
            session.save(new NullProgressMonitor());
            if (rollback) {
                throw new RollbackException(Status.CANCEL_STATUS);
            } else {
                assertEquals(SessionStatus.DIRTY, session.getStatus());
            }
            return super.transactionAboutToCommit(event);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        session.close(new NullProgressMonitor());
        session = null;
        tempFile.delete();
        tempFile = null;
        // Store locally the factory because the field is cleaned by the
        // super.tearDown()
        Object factory = previousAirdFactory;
        super.tearDown();
        if (factory == null) {
            Registry.INSTANCE.getExtensionToFactoryMap().remove(SiriusUtil.SESSION_RESOURCE_EXTENSION);
        } else {
            Registry.INSTANCE.getExtensionToFactoryMap().put(SiriusUtil.SESSION_RESOURCE_EXTENSION, factory);
        }
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
