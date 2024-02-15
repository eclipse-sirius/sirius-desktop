/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.internal.EMFTransactionPlugin;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;

import junit.framework.TestCase;

/**
 * This class contains tests for EMF transaction. It should not be added directly to automated tests until they are
 * corrected or target platform contains fork to correct them.
 * 
 * @author mchauvin
 */
public class EMFTransansactionTests extends TestCase {

    private TransactionalEditingDomain editingDomain;

    private UncaughtExceptionHandler exceptionHandler;

    private ILogListener listener;

    private boolean error;

    private synchronized boolean doesAnErrorOccurs() {
        return error;
    }

    private synchronized void errorOccurs() {
        error = true;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        exceptionHandler = new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                errorOccurs();
            }
        };
    }

    /**
     * Test IllegalArgumentException when collecting notifications https://bugs.eclipse.org/bugs/show_bug.cgi?id=285734
     */
    public void testBug285734() {

        /* create a resource set */
        ResourceSet rset = new ResourceSetImpl();
        /* create an editing domain */
        editingDomain = createEditingDomain(rset);

        /* initialize the model */
        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("First class");
        ePackage.getEClassifiers().add(eClass);

        /* create resource and and add it initialized model */
        final URI fileUri = URI.createFileURI(new File("test.ecore").getAbsolutePath());
        final Resource rs = rset.createResource(fileUri);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                rs.getContents().add(ePackage);
            }

        });

        /*
         * add a listener on remove operation, which launch a model read only transaction
         */
        addListenerToLaunchROTransactionOnAdd(ePackage);

        /* add a listener, to rollback all transactions */
        addPrecommitListenerToRollbackTransaction(editingDomain);

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                ePackage.getEClassifiers().remove(eClass);
                rs.setModified(true);
            }

        });
    }

    private TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
        IOperationHistory history = new DefaultOperationHistory();
        return WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain(rset, history);
    }

    private void addListenerToLaunchROTransactionOnAdd(EObject objectToListen) {
        objectToListen.eAdapters().add(new AdapterImpl() {
            @Override
            public void notifyChanged(final Notification msg) {
                switch (msg.getEventType()) {
                case Notification.ADD:
                    startReadOnlyTransaction();
                default:
                    break;
                }
            }
        });
    }

    private void startReadOnlyTransaction() {
        try {
            editingDomain.runExclusive(new Runnable() {
                @Override
                public void run() {
                    // do nothing
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addPrecommitListenerToRollbackTransaction(TransactionalEditingDomain editingDomain) {
        editingDomain.addResourceSetListener(new ResourceSetListenerImpl() {
            @Override
            public boolean isPrecommitOnly() {
                return true;
            }

            @Override
            public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
                throw new RollbackException(Status.CANCEL_STATUS);
            }
        });
    }

    /**
     * Due to threads synchronization complexity, this test may succeed. It should be run several times (at least 4) to
     * be sure than the bug is not present.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=288442"
     * 
     * @throws Exception
     */
    public void testSynchronizationBug() throws Exception {
        /* create a resource set */
        final ResourceSet rset = new ResourceSetImpl();
        /* create an editing domain */
        editingDomain = createEditingDomain(rset);

        /* initialize the first model */
        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        ePackage.getEClassifiers().add(eClass);
        final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eClass.getEStructuralFeatures().add(eReference);

        /* create resource and and add it initialized model */
        final URI fileUri = URI.createFileURI(new File("test.ecore").getAbsolutePath());
        final Resource rs = rset.createResource(fileUri);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                rs.getContents().add(ePackage);
            }

        });

        /* initialize the second model */
        final EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
        final EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
        ePackage2.getEClassifiers().add(eClass2);
        final EReference eReference2 = EcoreFactory.eINSTANCE.createEReference();
        eClass2.getEStructuralFeatures().add(eReference2);

        /* create resource and and add it initialized model */
        final URI file2Uri = URI.createFileURI(new File("test2.ecore").getAbsolutePath());
        final Resource rs2 = rset.createResource(file2Uri);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                rs2.getContents().add(ePackage2);
            }

        });

        /* set a link between first and second class */
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                eReference.setEOpposite(eReference2);
            }
        });

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

        listener = new ILogListener() {

            @Override
            public void logging(IStatus status, String plugin) {
                if (status.getSeverity() == IStatus.ERROR)
                    errorOccurs();
            }
        };

        EMFTransactionPlugin.getPlugin().getLog().addLogListener(listener);

        final Collection<Thread> threads = new ArrayList<Thread>();
        /* Launch a unload and a resolution */
        for (int i = 0; i < 100; i++) {
            threads.add(launchNotificationInANewThread(eReference, eReference2));
        }

        for (final Thread thread : threads) {
            thread.join();
        }

        EMFTransactionPlugin.getPlugin().getLog().removeLogListener(listener);

        /* an exception occurs in another thread */
        if (doesAnErrorOccurs()) {
            fail();
        }
    }

    private Thread launchNotificationInANewThread(final EReference ref1, final EReference ref2) throws Exception {
        final Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    ref1.eNotify(new ENotificationImpl((InternalEObject) ref1, Notification.RESOLVE, EcorePackage.EREFERENCE__EOPPOSITE, null, ref2));
                }
            }
        };
        t.start();
        return t;
    }

}
