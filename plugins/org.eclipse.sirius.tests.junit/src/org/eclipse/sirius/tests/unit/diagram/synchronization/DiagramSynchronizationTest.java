/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import java.lang.reflect.Field;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.SubStatusLineManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.SynchronizeStatusFigure;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorActionBarContributor;

public class DiagramSynchronizationTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/packages.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/ecore_with_blank.odesign";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_DESC_NAME = "Blank Entities";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramSynchronized() throws Exception {
        initViewpoint(VIEWPOINT_NAME);
        final DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;
        final EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, root).iterator().next();

        assertTrue("This DDiagram should be synchronized.", rootdiagram.isSynchronized());

        dropSemantic(root.getEClassifier("Root"), rootdiagram, null);

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);
        // We expect duplicates of the previous edges, with the new
        // representation of Child.
        assertEquals("There are not as many edges as expected.", 2, rootdiagram.getEdges().size());
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test that the diagram is not refreshed if not synchronized.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramUnsynchronized() throws Exception {
        initViewpoint(VIEWPOINT_NAME);
        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;
        final EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, root).iterator().next();

        assertTrue("Error while toggling synchronization mode.", setDDiagramAttribute(session.getTransactionalEditingDomain(), rootdiagram, "synchronized", false));

        dropSemantic(root.getEClassifier("Root"), rootdiagram, null);

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);

        EList<DEdge> edges = rootdiagram.getEdges();

        assertEquals("The diagram should not have any edges.", 0, edges.size());

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramUnsynchronizedOnDiagramCreation() throws Exception {
        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);
        initViewpoint(VIEWPOINT_NAME);
        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;
        final EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, root).iterator().next();

        assertFalse("This DDiagram should not be synchronized.", rootdiagram.isSynchronized());

        dropSemantic(root.getEClassifier("Root"), rootdiagram, null);

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);

        EList<DEdge> edges = rootdiagram.getEdges();

        assertEquals("The diagram should not have any edges.", 0, edges.size());
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Check the status bar message according to the diagram synchronize status.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStatusBarMessage() throws Exception {
        initViewpoint(VIEWPOINT_NAME);
        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, semanticModel).iterator().next();

        assertEquals("Bad status line message :", Messages.SiriusStatusLineContributionItemProvider_diagramSynchronized, getStatusLineMessage(editor));
        assertTrue("Error while toggling synchronization mode.", setDDiagramAttribute(session.getTransactionalEditingDomain(), rootdiagram, "synchronized", false));
        assertEquals("Bad status line message :", Messages.SiriusStatusLineContributionItemProvider_diagramUnsynchronized, getStatusLineMessage(editor));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Check the synchronize status decorator according to the diagram
     * synchronize status and the preference to display it (or not).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSynchronizeStatusDecorator() throws Exception {
        // Change preference to display decorator
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_SYNCHRONIZE_STATUS_DECORATOR.name(), Boolean.TRUE);
        // Create a new diagram and open it
        initViewpoint(VIEWPOINT_NAME);
        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Check the decorator
        Optional<SynchronizeStatusFigure> syncStatusDecorator = getDiagramSynchronizeStatusFigure(editor);
        assertTrue("The diagram synchronize status decorator is not displayed.", syncStatusDecorator.isPresent());
        assertEquals("Wrong color for decorator icon.", SynchronizeStatusFigure.BORDER_COLOR_SYNC_DIAG, syncStatusDecorator.get().getForegroundColor().getRGB());
        // Change status and check that decorator has been changed
        assertTrue("Error while toggling synchronization mode.", setDDiagramAttribute(session.getTransactionalEditingDomain(), diagram, "synchronized", false));
        syncStatusDecorator = getDiagramSynchronizeStatusFigure(editor);
        assertEquals("Wrong color for decorator icon.", SynchronizeStatusFigure.BORDER_COLOR_UNSYNC_DIAG, syncStatusDecorator.get().getForegroundColor().getRGB());

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        // Reset preference to not display decorator
        resetDiagramUiPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_SYNCHRONIZE_STATUS_DECORATOR.name());
        // Open the editor
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Check that the decorator is not here
        assertFalse("The diagram synchronize status decorator is displayed.", getDiagramSynchronizeStatusFigure(editor).isPresent());
    }

    /**
     * Returns the diagram sync status figure (if any).
     * 
     * @param editor
     *            the editor to test
     * @return an optional diagram sync figure (if any)
     */
    public Optional<SynchronizeStatusFigure> getDiagramSynchronizeStatusFigure(IEditorPart editor) {
        assertTrue("The editor is expected to be a DiagramEditor.", editor instanceof DiagramEditor);
        DiagramEditor diagramEditor = (DiagramEditor) editor;
        RootEditPart rootEditPart = diagramEditor.getDiagramGraphicalViewer().getRootEditPart();
        assertTrue("The root editpart is expected to be a DiagramRootEditPart.", rootEditPart instanceof DiagramRootEditPart);
        return SynchronizeStatusFigure.getDiagramSynchronizeStatusFigure((DiagramRootEditPart) rootEditPart);
    }

    /**
     * Returns the status line message of this editor.
     * 
     * @return the message
     * 
     */
    private String getStatusLineMessage(IEditorPart editor) {
        String message = "";
        IEditorActionBarContributor contributor = editor.getEditorSite().getActionBarContributor();
        if (!(contributor instanceof EditorActionBarContributor))
            return null;

        IActionBars actionBars = ((EditorActionBarContributor) contributor).getActionBars();
        if (actionBars == null)
            return null;

        IStatusLineManager statusLineManager = actionBars.getStatusLineManager();

        if (statusLineManager instanceof SubStatusLineManager) {
            Field declaredField;
            try {
                declaredField = SubStatusLineManager.class.getDeclaredField("message");
                declaredField.setAccessible(true);
                message = (String) declaredField.get(statusLineManager);
                declaredField.setAccessible(false);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                // do nothing
            }
        }
        return message;
    }
}
