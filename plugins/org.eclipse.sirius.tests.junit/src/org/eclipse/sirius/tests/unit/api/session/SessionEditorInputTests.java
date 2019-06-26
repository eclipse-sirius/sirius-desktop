/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CopyRepresentationCommand;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorInput;
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
        Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        commandStack.execute(new CopyRepresentationCommand(domain, representationDescriptors, "copy", session));
        DAnalysis dAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        dView = dAnalysis.getOwnedViews().get(0);
        dRepresentation1 = new DViewQuery(dView).getLoadedRepresentations().get(0);
        dRepresentation2 = new DViewQuery(dView).getLoadedRepresentations().get(1);
        session.save(new NullProgressMonitor());
    }

    /**
     * Test that opening a {@link DRepresentation} whose {@link URI} has changed to be same as an already opened
     * {@link DRepresentation} doesn't open the already opened {@link DRepresentation} but a new editor for the
     * requested {@link DRepresentation}.
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
        DRepresentationQuery query = new DRepresentationQuery(dRepresentation1);
        DRepresentationDescriptor descriptor1 = query.getRepresentationDescriptor();
        query = new DRepresentationQuery(dRepresentation2);
        DRepresentationDescriptor descriptor2 = query.getRepresentationDescriptor();
        URI dRepresentation1InitialURI = EcoreUtil.getURI(dRepresentation1);
        URI dRepresentation2InitialURI = EcoreUtil.getURI(dRepresentation2);

        URI representation1GMFDiagramInitialURI = EcoreUtil.getURI(new DDiagramGraphicalQuery((DDiagram) dRepresentation1).getAssociatedGMFDiagram().get());
        URI representation2GMFDiagramInitialURI = EcoreUtil.getURI(new DDiagramGraphicalQuery((DDiagram) dRepresentation2).getAssociatedGMFDiagram().get());

        Command moveCmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                dView.eResource().getContents().move(1, dRepresentation2);
                descriptor1.updateRepresentation(dRepresentation1);
                descriptor2.updateRepresentation(dRepresentation2);
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(moveCmd);

        URI dRepresentation1URI = EcoreUtil.getURI(dRepresentation1);
        URI dRepresentation2URI = EcoreUtil.getURI(dRepresentation2);
        URI representation1GMFDiagramURI = EcoreUtil.getURI(new DDiagramGraphicalQuery((DDiagram) dRepresentation1).getAssociatedGMFDiagram().get());
        URI representation2GMFDiagramURI = EcoreUtil.getURI(new DDiagramGraphicalQuery((DDiagram) dRepresentation2).getAssociatedGMFDiagram().get());

        String representationAssertMessage = "DRepresentation's URI should not have changed as they are stored in a InMemoryResourceImpl with uid based uriFragment";
        assertEquals(representationAssertMessage, dRepresentation1InitialURI, dRepresentation1URI);
        assertEquals(representationAssertMessage, dRepresentation2InitialURI, dRepresentation2URI);
        String gmfAssertMessage = "GMF diagram's URI should have changed as they are stored in a InMemoryResourceImpl with xpath based uriFragment";
        assertEquals(gmfAssertMessage, representation1GMFDiagramInitialURI, representation2GMFDiagramURI);
        assertEquals(gmfAssertMessage, representation2GMFDiagramInitialURI, representation1GMFDiagramURI);

        IEditorPart newEditor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation1, new NullProgressMonitor());
        assertNotSame(editor1, newEditor);
        assertEquals("Editor for representation1 should be opened", 2, EclipseUIUtil.getActivePage().getEditorReferences().length);
        IEditorInput newEditorInput = newEditor.getEditorInput();
        IEditorInput editor2Input = editor2.getEditorInput();
        assertTrue(newEditorInput instanceof SessionEditorInput);
        assertTrue(editor2Input instanceof SessionEditorInput);
        SessionEditorInput sessionNewEditorInput = (SessionEditorInput) newEditor.getEditorInput();
        SessionEditorInput sessionEditor2Input = (SessionEditorInput) editor2.getEditorInput();
        assertEquals(representation1GMFDiagramURI, sessionNewEditorInput.getURI());
        assertEquals(representation2GMFDiagramURI, sessionEditor2Input.getURI());
        assertEquals(new SessionEditorInput(representation1GMFDiagramURI, new DRepresentationQuery(dRepresentation1).getRepresentationDescriptor().getName(), session), sessionNewEditorInput);
        assertEquals(new SessionEditorInput(representation2GMFDiagramURI, new DRepresentationQuery(dRepresentation2).getRepresentationDescriptor().getName(), session), sessionEditor2Input);

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
