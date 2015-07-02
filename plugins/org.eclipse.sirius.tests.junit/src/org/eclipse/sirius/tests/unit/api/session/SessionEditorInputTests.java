/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.session;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CopyRepresentationCommand;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;

import junit.framework.TestCase;

/**
 * Tests about https://bugs.eclipse.org/bugs/show_bug.cgi?id=471456.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SessionEditorInputTests extends TestCase {

    private static final String TEMPORARY_PROJECT_NAME = "DesignerTestProject";

    private static final String SESSION_MODEL_FILENAME = "My.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "My.ecore";

    private Session session;

    private DView dView;

    private DRepresentation dRepresentation1;

    private DRepresentation dRepresentation2;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        URI sessionResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
        session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        URI semanticResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        Resource semanticResource = new ResourceSetImpl().createResource(semanticResourceURI);
        semanticResource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());
        semanticResource.save(Collections.emptyMap());
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CommandStack commandStack = domain.getCommandStack();
        commandStack.execute(addSemanticResourceCmd);
        URI designViewpointURI = URI.createURI("viewpoint:/org.eclipse.sirius.sample.ecore.design/Design");
        Viewpoint viewpoint = ViewpointRegistry.getInstance().getViewpoint(designViewpointURI);
        viewpoint = (Viewpoint) session.getTransactionalEditingDomain().getResourceSet().getEObject(EcoreUtil.getURI(viewpoint), true);
        commandStack.execute(
                new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(), Collections.singleton(viewpoint), Collections.<Viewpoint> emptySet(), new NullProgressMonitor()));
        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        commandStack.execute(new CopyRepresentationCommand(domain, allRepresentations, "copy", session));
        DAnalysis dAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        dView = dAnalysis.getOwnedViews().get(0);
        dRepresentation1 = dView.getOwnedRepresentations().get(0);
        dRepresentation2 = dView.getOwnedRepresentations().get(1);
        session.save(new NullProgressMonitor());
    }

    /**
     * Test that opening a {@link DRepresentation} whose {@link URI} has changed
     * to be same a already opened {@link DRepresentation} doesn't open the
     * already opened {@link DRepresentation} but a new editor for the requested
     * {@link DRepresentation}.
     */
    public void testSessionEditorInputWithInputURIChange() {
        IEditorPart editor1 = DialectUIManager.INSTANCE.openEditor(session, dRepresentation1, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        IEditorPart editor2 = DialectUIManager.INSTANCE.openEditor(session, dRepresentation2, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals(2, EclipseUIUtil.getActivePage().getEditorReferences().length);
        DialectUIManager.INSTANCE.closeEditor(editor1, false);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(1, EclipseUIUtil.getActivePage().getEditorReferences().length);
        // Change URI of DRepresentations by changing order as uriFragments are
        // xpath based
        URI dRepresentation1URI = EcoreUtil.getURI(dRepresentation1);
        URI dRepresentation2URI = EcoreUtil.getURI(dRepresentation2);
        Command moveCmd = MoveCommand.create(session.getTransactionalEditingDomain(), dView, ViewpointPackage.Literals.DVIEW__OWNED_REPRESENTATIONS, dRepresentation2, 0);
        session.getTransactionalEditingDomain().getCommandStack().execute(moveCmd);
        String assertMessage = "DRepresentation's URI should have changed as they are stored in a InMemoryResourceImpl with xpath based uriFragment";
        assertEquals(assertMessage, dRepresentation1URI, EcoreUtil.getURI(dRepresentation2));
        assertEquals(assertMessage, dRepresentation2URI, EcoreUtil.getURI(dRepresentation1));
        IEditorPart newEditor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation1, new NullProgressMonitor());
        assertNotSame(editor1, newEditor);
        assertEquals("Editor for representation1 should be opened", 2, EclipseUIUtil.getActivePage().getEditorReferences().length);
        DialectUIManager.INSTANCE.closeEditor(editor1, false);
        DialectUIManager.INSTANCE.closeEditor(editor2, false);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    public void tearDown() throws Exception {
        dRepresentation2 = null;
        dRepresentation1 = null;
        dView = null;
        session.close(new NullProgressMonitor());
        session = null;
        super.tearDown();
    }
}
