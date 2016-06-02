/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.dialect;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.tests.support.api.DummyDialectEditorDialogFactory;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

/**
 * Test case for Bug 481403.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DialectEditorCloserTest extends SiriusTestCase {

    private CommandStack commandStack;

    private EObject target;

    private TransactionalEditingDomain domain;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        URI semanticResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/test.ecore", true);
        Resource ecoreResource = new ResourceSetImpl().createResource(semanticResourceURI);
        ecoreResource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());
        ecoreResource.save(null);
        URI sessionResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/test.aird", true);
        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        domain = session.getTransactionalEditingDomain();
        commandStack = domain.getCommandStack();
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        commandStack.execute(addSemanticResourceCmd);
        target = session.getSemanticResources().iterator().next().getContents().get(0);
    }

    public void testDialectEditorCloserOnDViewAdditionUndo() {
        DAnalysis dAnalysis = new AirDResouceQuery((AirdResource) session.getSessionResource()).getDAnalysis().get();
        URI designViewpointURI = URI.createURI("viewpoint:/org.eclipse.sirius.sample.ecore.design/Design");
        Viewpoint viewpoint = ViewpointRegistry.getInstance().getViewpoint(designViewpointURI);
        viewpoint = (Viewpoint) domain.getResourceSet().getEObject(EcoreUtil.getURI(viewpoint), true);

        DView dView = ViewpointFactory.eINSTANCE.createDView();
        DTree dTree = TreeFactory.eINSTANCE.createDTree();
        dTree.setTarget(target);
        new DViewQuery(dView).getLoadedRepresentations().add(dTree);
        dView.setViewpoint(viewpoint);
        Command cmd = AddCommand.create(domain, dAnalysis, ViewpointPackage.Literals.DANALYSIS__OWNED_VIEWS, dView);
        commandStack.execute(cmd);

        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTree, new NullProgressMonitor());
        assertTrue(openedEditor instanceof DialectEditor);
        DialectEditor dialectEditor = (DialectEditor) openedEditor;
        dialectEditor.setDialogFactory(new DummyDialectEditorDialogFactory());
        TestsUtil.synchronizationWithUIThread();

        commandStack.undo();
        TestsUtil.synchronizationWithUIThread();

        IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        assertEquals("The dialect editor should be closed by DialectEditorCloser since DRepresentation addition has been undone", 0, editorReferences.length);
    }

    @Override
    protected void tearDown() throws Exception {
        domain = null;
        target = null;
        commandStack = null;
        session.close(new NullProgressMonitor());
        session = null;
        super.tearDown();
    }
}
