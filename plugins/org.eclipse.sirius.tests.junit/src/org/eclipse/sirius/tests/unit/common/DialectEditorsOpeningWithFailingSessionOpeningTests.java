/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableCrossEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableEditionEditor;
import org.eclipse.sirius.tests.support.api.DefaultTestMemento;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInputFactory;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * A test class to test that {@link DialectEditor} are opened with a end-user
 * message in background and without exceptions when the session opening fails.
 * The tests simulates editors opening from memento at Eclipse restart.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DialectEditorsOpeningWithFailingSessionOpeningTests extends SiriusTestCase {

    private IMemento memento;

    private IElementFactory elementFactory;

    private SessionOpeningFailureListener sessionOpeningFailureListener;

    private IEditorPart openedEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        closeWelcomePage();

        String factoryID = SessionEditorInputFactory.class.getName();
        elementFactory = PlatformUI.getWorkbench().getElementFactory(factoryID);
        memento = new DefaultTestMemento();
        memento.putString("bundle", SiriusEditPlugin.ID);
        memento.putString("class", SessionEditorInput.class.getName());
        URI sessionResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/representationsSet.aird");
        Resource resource = new ResourceSetImpl().createResource(sessionResourceURI);
        resource.getContents().add(ViewpointFactory.eINSTANCE.createDAnalysis());
        resource.save(null);
        memento.putString("name", "dummyRepName");
        memento.putString("SESSION_RESOURCE_URI", sessionResourceURI.toString());
        memento.putString("uri", sessionResourceURI.appendFragment("dummyFragment").toString());
        sessionOpeningFailureListener = new SessionOpeningFailureListener();
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    public void testDiagramEditorOpeningWithFailingSessionOpening() throws Exception {
        String exceptionMessage = "FAILING SESSION OPENING MESSAGE ON DIAGRAM EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, false, DDiagramEditor.EDITOR_ID);
    }

    public void testDiagramEditorOpeningWithCanceledSessionOpening() throws Exception {
        String exceptionMessage = "CANCEL SESSION OPENING MESSAGE ON DIAGRAM EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, true, DDiagramEditor.EDITOR_ID);
    }

    public void testTreeEditorOpeningWithFailingSessionOpening() throws Exception {
        String exceptionMessage = "FAILING SESSION OPENING MESSAGE ON TREE EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, false, DTreeEditor.ID);
    }

    public void testTreeEditorOpeningWithCanceledSessionOpening() throws Exception {
        String exceptionMessage = "CANCEL SESSION OPENING MESSAGE ON TREE EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, true, DTreeEditor.ID);
    }

    public void testEditionTableEditorOpeningWithFailingSessionOpening() throws Exception {
        String exceptionMessage = "FAILING SESSION OPENING MESSAGE ON EDITION TABLE EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, false, DTableEditionEditor.ID);
    }

    public void testEditionTableEditorOpeningWithCanceledSessionOpening() throws Exception {
        String exceptionMessage = "CANCEL SESSION OPENING MESSAGE ON EDITION TABLE EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, true, DTableEditionEditor.ID);
    }

    public void testCrossTableEditorOpeningWithFailingSessionOpening() throws Exception {
        String exceptionMessage = "FAILING SESSION OPENING MESSAGE ON CROSS TABLE EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, false, DTableCrossEditor.ID);
    }

    public void testCrossTableEditorOpeningWithCanceledSessionOpening() throws Exception {
        String exceptionMessage = "CANCEL SESSION OPENING MESSAGE ON CROSS TABLE EDITOR OPENING";
        testDiagramEditorOpeningOnSessionOpening(exceptionMessage, true, DTableCrossEditor.ID);
    }

    public void testDiagramEditorOpeningOnSessionOpening(String exceptionMessage, boolean cancel, String editorID) throws Exception {
        sessionOpeningFailureListener.setThrowOperationCancelExceptionOnSessionOpening(cancel);
        sessionOpeningFailureListener.setMessage(exceptionMessage);

        IAdaptable editorInputAdaptable = elementFactory.createElement(memento);
        assertTrue("The SessionEditorInputFactory must create a SessionEditorInput, even if session is not opened", editorInputAdaptable instanceof SessionEditorInput);
        SessionEditorInput sessionEditorInput = (SessionEditorInput) editorInputAdaptable;

        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        openedEditor = IDE.openEditor(activePage, sessionEditorInput, editorID);
        TestsUtil.synchronizationWithUIThread();

        String expectedMessage = null;
        String displayedMessage = null;
        if (editorID == DDiagramEditor.EDITOR_ID) {
            assertTrue(openedEditor instanceof DDiagramEditorImpl);
            DDiagramEditorImpl dDiagramEditorImpl = (DDiagramEditorImpl) openedEditor;
            RootEditPart rootEditPart = dDiagramEditorImpl.getDiagramGraphicalViewer().getRootEditPart();
            assertTrue(rootEditPart instanceof LayerManager);
            LayerManager layerManager = (LayerManager) rootEditPart;
            IFigure layer = layerManager.getLayer(LayerConstants.PRINTABLE_LAYERS);
            assertEquals(3, layer.getChildren().size());
            Object child = layer.getChildren().get(layer.getChildren().size() - 1);
            assertTrue(child instanceof Label);
            Label statusLabel = (Label) child;
            expectedMessage = MessageFormat.format(org.eclipse.sirius.diagram.ui.provider.Messages.DDiagramEditorImpl_editorToBeClosedAndReopenedSinceContentIsNotAccessible, exceptionMessage);
            displayedMessage = statusLabel.getText();
        } else if (editorID == DTreeEditor.ID) {
            assertTrue(openedEditor instanceof DTreeEditor);
            DTreeEditor dTreeEditor = (DTreeEditor) openedEditor;
            Control control = dTreeEditor.getControl();
            assertTrue(control instanceof Composite);
            Composite composite = (Composite) control;
            assertEquals(1, composite.getChildren().length);
            Control child = composite.getChildren()[0];
            assertTrue(child instanceof StyledText);
            StyledText label = (StyledText) child;
            expectedMessage = MessageFormat.format(org.eclipse.sirius.tree.ui.provider.Messages.DTreeEditor_editorToBeClosedAndReopenedSinceContentIsNotAccessible, exceptionMessage);
            displayedMessage = label.getText();
        } else if (editorID == DTableEditionEditor.ID) {
            assertTrue(openedEditor instanceof DTableEditionEditor);
            DTableEditionEditor dTableEditionEditor = (DTableEditionEditor) openedEditor;
            Control control = dTableEditionEditor.getControl();
            assertTrue(control instanceof Composite);
            Composite composite = (Composite) control;
            assertEquals(1, composite.getChildren().length);
            Control child = composite.getChildren()[0];
            assertTrue(child instanceof StyledText);
            StyledText label = (StyledText) child;
            expectedMessage = MessageFormat.format(org.eclipse.sirius.table.metamodel.table.provider.Messages.AbstractDTableEditor_editorToBeClosedAndReopenedSinceContentIsNotAccessible,
                    exceptionMessage);
            displayedMessage = label.getText();
        } else if (editorID == DTableCrossEditor.ID) {
            assertTrue(openedEditor instanceof DTableCrossEditor);
            DTableCrossEditor dTableCrossEditor = (DTableCrossEditor) openedEditor;
            Control control = dTableCrossEditor.getControl();
            assertTrue(control instanceof Composite);
            Composite composite = (Composite) control;
            assertEquals(1, composite.getChildren().length);
            Control child = composite.getChildren()[0];
            assertTrue(child instanceof StyledText);
            StyledText label = (StyledText) child;
            expectedMessage = MessageFormat.format(org.eclipse.sirius.table.metamodel.table.provider.Messages.AbstractDTableEditor_editorToBeClosedAndReopenedSinceContentIsNotAccessible,
                    exceptionMessage);
            displayedMessage = label.getText();
        }
        assertEquals("The exception message should be displayed in background of opened dialect editor to explain why the session opening has failed", expectedMessage, displayedMessage);
    }

    @Override
    protected void tearDown() throws Exception {
        if (openedEditor != null) {
            DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
            TestsUtil.synchronizationWithUIThread();
        }
        sessionOpeningFailureListener.dispose();
        sessionOpeningFailureListener = null;
        elementFactory = null;
        memento = null;
        super.tearDown();
    }

    class SessionOpeningFailureListener extends SessionManagerListener.Stub {

        private boolean throwOperationCancelExceptionOnSessionOpening;

        private String message;

        public SessionOpeningFailureListener() {
            SessionManager.INSTANCE.addSessionsListener(this);
        }

        public void setThrowOperationCancelExceptionOnSessionOpening(boolean throwOperationCancelExceptionOnSessionOpening) {
            this.throwOperationCancelExceptionOnSessionOpening = throwOperationCancelExceptionOnSessionOpening;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public void notifyAddSession(Session newSession) {
            if (throwOperationCancelExceptionOnSessionOpening) {
                throw new OperationCanceledException(message);
            } else {
                throw new RuntimeException(message);
            }
        }

        public void dispose() {
            SessionManager.INSTANCE.removeSessionsListener(this);
        }
    }
}
