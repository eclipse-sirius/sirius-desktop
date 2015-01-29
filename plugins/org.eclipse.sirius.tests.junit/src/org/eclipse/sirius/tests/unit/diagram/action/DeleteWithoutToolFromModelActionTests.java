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
package org.eclipse.sirius.tests.unit.diagram.action;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelWithHookAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests on delete from model action for elements without delete tool or
 * semantic elements.
 * 
 * @author mchauvin
 */
public class DeleteWithoutToolFromModelActionTests extends SiriusDiagramTestCase {

    private static final String PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/relationEdgeWithoutSemantic/";//$NON-NLS-1$ $NON-NLS-2$

    private static final String MODELER_PATH = PATH + "RelationalEdgeWithoutSemantic.odesign"; //$NON-NLS-1$ $NON-NLS-2$

    private static final String SEMANTIC_MODEL_PATH = PATH + "RelationalEdgeWithoutSemantic.ecore"; //$NON-NLS-1$ $NON-NLS-2$

    private static final String AIRD_MODEL_PATH = PATH + "RelationalEdgeWithoutSemantic.aird"; //$NON-NLS-1$ $NON-NLS-2$

    private static final String DESC_NAME = "RelationalEdgeWithoutSemantic";//$NON-NLS-1$

    private DDiagram diagram;

    private DDiagramEditor editor;

    private EPackage ePackage;

    private Predicate<DEdge> relation = new Predicate<DEdge>() {

        public boolean apply(DEdge input) {
            IEdgeMapping actualMapping = input.getActualMapping();
            return !new IEdgeMappingQuery(actualMapping).getEdgeMapping().get().isUseDomainElement();
        }
    };

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, AIRD_MODEL_PATH);

        ePackage = (EPackage) semanticModel;
        diagram = (DDiagram) getRepresentations(DESC_NAME, ePackage).iterator().next();
        editor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    public void testNoDeleteRelationEdgeEditPartWithoutDeleteTool() throws Exception {
        List<DEdge> relationBasedEdge = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertFalse("Test data should have relation based edges.", relationBasedEdge.isEmpty());

        DEdge edgeToTry = relationBasedEdge.get(0);
        assertTrue("Test relation based edge should not have semantic elements.", edgeToTry.getSemanticElements().isEmpty());

        IGraphicalEditPart edgeEditPart = getEditPart(edgeToTry);
        assertNotNull("No edge edit part instance found", edgeEditPart);
        assertTrue("No edge edit part instance found", edgeEditPart instanceof IDiagramEdgeEditPart);

        delete(edgeEditPart);
        // Delete will not occurs allows tabbar to be refreshed now. Otherwise a
        // NPE will be raised, workbench page will be null.
        TestsUtil.synchronizationWithUIThread();

        List<DEdge> relEdgesAfterDeleteTry = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertEquals("Delete should not occurs", relationBasedEdge.size(), relEdgesAfterDeleteTry.size());
        assertTrue("Deletion should notoccurs", relEdgesAfterDeleteTry.contains(edgeToTry));
    }

    public void testDeleteDisabledRelationEdgeEditPart() throws Exception {
        List<DEdge> relationBasedEdge = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertFalse("Test data should have relation based edges.", relationBasedEdge.isEmpty());

        DEdge edgeToTry = relationBasedEdge.get(0);
        assertTrue("Test relation based edge should not have semantic elements.", edgeToTry.getSemanticElements().isEmpty());

        IGraphicalEditPart edgeEditPart = getEditPart(edgeToTry);
        assertNotNull("No edge edit part instance found", edgeEditPart);
        assertTrue("No edge edit part instance found", edgeEditPart instanceof IDiagramEdgeEditPart);

        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        final DeleteFromModelWithHookAction del = new DeleteFromModelWithHookAction(editor);
        final GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
        viewer.appendSelection(edgeEditPart);

        // Delete will not occurs allows tabbar to be refreshed now. Otherwise a
        // NPE will be raised, workbench page will be null.
        TestsUtil.synchronizationWithUIThread();

        del.refresh();

        assertFalse("Delete From Model action should be disabled.", del.isEnabled());
    }

    public void testDeleteViaKeyboardContainerEditPart() throws Exception {
        List<DEdge> relationBasedEdge = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertFalse("Test data should have relation based edges.", relationBasedEdge.isEmpty());

        DEdge edgeToTry = relationBasedEdge.get(0);
        assertTrue("Test relation based edge should not have semantic elements.", edgeToTry.getSemanticElements().isEmpty());

        IGraphicalEditPart edgeEditPart = getEditPart(edgeToTry);
        assertNotNull("No edge edit part instance found", edgeEditPart);
        assertTrue("No edge edit part instance found", edgeEditPart instanceof IDiagramEdgeEditPart);

        deleteViaKeyboard(edgeEditPart);
        // Delete will not occurs allows tabbar to be refreshed now. Otherwise a
        // NPE will be raised, workbench page will be null.
        TestsUtil.synchronizationWithUIThread();

        List<DEdge> relEdgesAfterDeleteTry = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertEquals("Delete should not occurs", relationBasedEdge.size(), relEdgesAfterDeleteTry.size());
        assertTrue("Deletion should notoccurs", relEdgesAfterDeleteTry.contains(edgeToTry));
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
