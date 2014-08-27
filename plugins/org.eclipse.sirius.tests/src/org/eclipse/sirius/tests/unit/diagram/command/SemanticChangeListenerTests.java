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
package org.eclipse.sirius.tests.unit.diagram.command;

import java.io.File;
import java.util.Collection;

import junit.framework.TestCase;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.sirius.tools.api.command.listener.ChangeListenerFactory;
import org.eclipse.sirius.tools.api.command.listener.IChangeListener;
import org.eclipse.sirius.tools.api.command.listener.TriggerOperation;

public class SemanticChangeListenerTests extends TestCase {

    private TransactionalEditingDomain editingDomain;

    private EClass eClass;

    private EPackage ePackage;

    private EPackage ePackage2;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        /* create a resource set */
        ResourceSet rset = new ResourceSetImpl();
        /* create an editing domain */
        editingDomain = createEditingDomain(rset);

        /* initialize the model */
        ePackage = EcoreFactory.eINSTANCE.createEPackage();
        eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("First class");
        ePackage.getEClassifiers().add(eClass);

        ePackage2 = EcoreFactory.eINSTANCE.createEPackage();

        /* create resource and and add it initialized model */
        final URI fileUri = URI.createFileURI(new File("test.ecore").getAbsolutePath());
        final Resource rs = rset.createResource(fileUri);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                rs.getContents().add(ePackage);
                rs.getContents().add(ePackage2);
            }

        });
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        editingDomain = null;
        eClass = null;
        ePackage = null;
        super.tearDown();
    }

    private TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
        IOperationHistory history = new DefaultOperationHistory();
        return WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain(rset, history);
    }

    public void testCreate() throws Exception {

        final EStructuralFeature eFeature = EcoreFactory.eINSTANCE.createEReference();

        final IChangeListener listener = ChangeListenerFactory.INSTANCE.getNewChangeListener();

        listener.setTriggerOperation(new TriggerOperation() {
            public void run(Collection<Object> createdElement, Collection<Object> modifiedElements, Collection<Object> deletedElements) {
                assertTrue(createdElement.contains(eFeature));
                assertTrue(modifiedElements.contains(eClass));
                assertTrue(deletedElements.isEmpty());
            }

        });

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                editingDomain.getResourceSet().eAdapters().add(listener);
                listener.activate();
                eClass.getEStructuralFeatures().add(eFeature);
                listener.deactivate();
                editingDomain.getResourceSet().eAdapters().remove(listener);
                listener.launchTriggerOperation();
            }
        });
    }

    public void testDelete() throws Exception {

        final IChangeListener listener = ChangeListenerFactory.INSTANCE.getNewChangeListener();

        listener.setTriggerOperation(new TriggerOperation() {
            public void run(Collection<Object> createdElements, Collection<Object> modifiedElements, Collection<Object> deletedElements) {
                assertTrue(deletedElements.contains(eClass));
            }
        });

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                editingDomain.getResourceSet().eAdapters().add(listener);
                listener.activate();
                EcoreUtil.delete(eClass);
                listener.deactivate();
                editingDomain.getResourceSet().eAdapters().remove(listener);
                listener.launchTriggerOperation();
            }
        });
    }

    public void testModify() throws Exception {

        final IChangeListener listener = ChangeListenerFactory.INSTANCE.getNewChangeListener();

        listener.setTriggerOperation(new TriggerOperation() {
            public void run(Collection<Object> createdElements, Collection<Object> modifiedElements, Collection<Object> deletedElements) {
                assertTrue(modifiedElements.contains(eClass));
                assertFalse(modifiedElements.contains(ePackage));
                assertFalse(modifiedElements.contains(ePackage2));
                assertTrue(createdElements.isEmpty());
                assertTrue(deletedElements.isEmpty());
            }
        });

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                editingDomain.getResourceSet().eAdapters().add(listener);
                listener.activate();
                eClass.setName("I am modified");
                listener.deactivate();
                editingDomain.getResourceSet().eAdapters().remove(listener);
                listener.launchTriggerOperation();
            }
        });
    }

    public void testMoveFromContainer() throws Exception {

        final IChangeListener listener = ChangeListenerFactory.INSTANCE.getNewChangeListener();

        listener.setTriggerOperation(new TriggerOperation() {
            public void run(Collection<Object> createdElements, Collection<Object> modifiedElements, Collection<Object> deletedElements) {
                assertTrue(createdElements.isEmpty());
                assertTrue(deletedElements.isEmpty());
                assertTrue(modifiedElements.contains(ePackage));
                assertTrue(modifiedElements.contains(ePackage2));
                assertTrue(modifiedElements.contains(eClass));
            }
        });

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                editingDomain.getResourceSet().eAdapters().add(listener);
                listener.activate();
                ePackage.getEClassifiers().remove(eClass);
                ePackage2.getEClassifiers().add(eClass);
                listener.deactivate();
                editingDomain.getResourceSet().eAdapters().remove(listener);
                listener.launchTriggerOperation();
            }
        });
    }

}
