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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests on delete from model action on entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class DeleteFromModelActionTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    private DDiagramEditor editor;

    private EPackage ePackage;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

        ePackage = (EPackage) semanticModel;
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, ePackage).iterator().next();
        editor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    public void testDeleteContainerEditPart() throws Exception {
        applyNodeCreationTool(TOOL_CREATION_CLASS_NAME, diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        final IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));
        assertNotNull("No container edit part instance found", parent);

        delete(parent);

        assertNull("Container edit part instance was not deleted", getFirstDiagramElement(diagram, eClass));
    }

    public void testDeleteRelationEdgeEditPartWithDeleteTool() throws Exception {
        Predicate<DEdge> relation = new Predicate<DEdge>() {

            @Override
            public boolean apply(DEdge input) {
                IEdgeMapping actualMapping = input.getActualMapping();
                return !new IEdgeMappingQuery(actualMapping).getEdgeMapping().get().isUseDomainElement();
            }
        };
        List<DEdge> relationBasedEdge = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertFalse("Test data should have relation based edges.", relationBasedEdge.isEmpty());

        DEdge edgeToTry = relationBasedEdge.get(0);
        IGraphicalEditPart edgeEditPart = getEditPart(edgeToTry);
        assertNotNull("No edge edit part instance found", edgeEditPart);
        assertTrue("No edge edit part instance found", edgeEditPart instanceof IDiagramEdgeEditPart);

        delete(edgeEditPart);

        List<DEdge> relEdgesAfterDeleteTry = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertEquals("Delete should occurs", relationBasedEdge.size() - 1, relEdgesAfterDeleteTry.size());
        assertFalse("Deletion should occurs", relEdgesAfterDeleteTry.contains(edgeToTry));
    }

    public void testDeleteElementEdgeEditPart() throws Exception {
        Predicate<DEdge> relation = new Predicate<DEdge>() {

            @Override
            public boolean apply(DEdge input) {
                IEdgeMapping actualMapping = input.getActualMapping();
                return new IEdgeMappingQuery(actualMapping).getEdgeMapping().get().isUseDomainElement();
            }
        };
        List<DEdge> elementBasedEdges = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertFalse("Test data should have element based edges.", elementBasedEdges.isEmpty());

        DEdge edgeToTry = elementBasedEdges.get(0);
        IGraphicalEditPart edgeEditPart = getEditPart(edgeToTry);
        assertNotNull("No edge edit part instance found", edgeEditPart);
        assertTrue("No edge edit part instance found", edgeEditPart instanceof IDiagramEdgeEditPart);

        delete(edgeEditPart);

        List<DEdge> eltEdgesAfterDeleteTry = Lists.newArrayList(Iterables.filter(diagram.getEdges(), relation));
        assertFalse("Deletion should occurs", eltEdgesAfterDeleteTry.contains(edgeToTry));
        assertEquals("Delete should occurs", elementBasedEdges.size() - 1, eltEdgesAfterDeleteTry.size());

    }

    public void testDeleteViaKeyboardContainerEditPart() throws Exception {
        applyNodeCreationTool(TOOL_CREATION_CLASS_NAME, diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        final IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));
        assertNotNull("No container edit part instance found", parent);

        deleteViaKeyboard(parent);

        assertNull("Container edit part instance was not deleted", getFirstDiagramElement(diagram, eClass));
    }

    public void testDoNotDeleteContainerLabelEditPart() throws Exception {
        applyNodeCreationTool(TOOL_CREATION_CLASS_NAME, diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));
        final IGraphicalEditPart listNamePart = getListNameEditPart(parent);
        assertNotNull("No NodeListNameEditPart instance found", listNamePart);

        // listnamePart is not selectable anymore. See
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=424417
        assertFalse("The NodeListNameEditPart should be selectable", listNamePart.isSelectable());
    }

    /**
     * Test label deletion (delete from model) of an edge. Verify bug VP-1027
     * 
     * @throws Exception
     */
    public void testDeleteEdgeLabel() throws Exception {
        applyNodeCreationTool(TOOL_CREATION_CLASS_NAME, diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        final EReference eReference = Iterables.filter(((EClass) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class).iterator().next();

        final IGraphicalEditPart edge = getEditPart(getFirstDiagramElement(diagram, eReference));
        final IGraphicalEditPart listNamePart = getEdgeNameEditPart(edge);
        assertNotNull("No EdgeNameEditPart instance found", listNamePart);

        delete(listNamePart);

        IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not deleted", parent.getSourceConnections().size() == 0);

        Iterable<EReference> eReferences = Iterables.filter(((EClassImpl) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class);

        assertFalse("semantic reference instance was not deleted", eReferences.iterator().hasNext());

        session.getTransactionalEditingDomain().getCommandStack().undo();

        parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not reinserted", parent.getSourceConnections().size() > 0);

        eReferences = Iterables.filter(((EClassImpl) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class);

        assertTrue("semantic reference instance was not reinserted", eReferences.iterator().hasNext());

        session.getTransactionalEditingDomain().getCommandStack().redo();

        parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not deleted", parent.getSourceConnections().size() == 0);

        eReferences = Iterables.filter(((EClassImpl) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class);

        assertFalse("semantic reference instance was not deleted", eReferences.iterator().hasNext());
    }

    /**
     * Test label deletion (key board "Suppr") of an edge. Verify bug VP-1027
     * 
     * @throws Exception
     */
    public void testDeleteViaKeyBoardEdgeLabel() throws Exception {
        applyNodeCreationTool(TOOL_CREATION_CLASS_NAME, diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        final EReference eReference = Iterables.filter(((EClass) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class).iterator().next();

        final IGraphicalEditPart edge = getEditPart(getFirstDiagramElement(diagram, eReference));
        final IGraphicalEditPart listNamePart = getEdgeNameEditPart(edge);
        assertNotNull("No EdgeNameEditPart instance found", listNamePart);

        deleteViaKeyboard(listNamePart);

        IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not deleted", parent.getSourceConnections().size() == 0);

        Iterable<EReference> eReferences = Iterables.filter(((EClassImpl) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class);

        assertFalse("semantic reference instance was not deleted", eReferences.iterator().hasNext());

        session.getTransactionalEditingDomain().getCommandStack().undo();

        parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not reinserted", parent.getSourceConnections().size() > 0);

        eReferences = Iterables.filter(((EClassImpl) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class);

        assertTrue("semantic reference instance was not reinserted", eReferences.iterator().hasNext());

        session.getTransactionalEditingDomain().getCommandStack().redo();

        parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not deleted", parent.getSourceConnections().size() == 0);

        eReferences = Iterables.filter(((EClassImpl) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class);

        assertFalse("semantic reference instance was not deleted", eReferences.iterator().hasNext());
    }

    public void testDoNotDeleteViaKeyboardContainerLabelEditPart() throws Exception {
        applyNodeCreationTool(TOOL_CREATION_CLASS_NAME, diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        final IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));
        final IGraphicalEditPart listNamePart = getListNameEditPart(parent);
        assertNotNull("No NodeListNameEditPart instance found", listNamePart);

        // listnamePart is not selectable anymore. See
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=424417
        assertFalse("The NodeListNameEditPart should be selectable", listNamePart.isSelectable());
    }

    private IGraphicalEditPart getListNameEditPart(final IGraphicalEditPart parent) {
        for (final EditPart child : (List<EditPart>) parent.getChildren()) {
            if (child instanceof DNodeListNameEditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }

    private IGraphicalEditPart getEdgeNameEditPart(final IGraphicalEditPart parent) {
        for (final EditPart child : (List<EditPart>) parent.getChildren()) {
            if (child instanceof DEdgeNameEditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
