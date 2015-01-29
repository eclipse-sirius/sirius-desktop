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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Refresh style tests for Entities diagram of ecore modeler.
 * 
 * @author nlepine
 */
public class EntitiesDiagramStyleRefreshTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/directEdit/testOperation.ecore";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/directEdit/testOperation.aird";

    private static final String IMAGE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/directEdit/viewpoint.gif";

    private DDiagram diagram;

    IEditorPart diagramEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        diagramEditor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that when the style of a container mapping change, the style of the
     * diagram element is also changed and that the figure is of the correct
     * type.
     */
    public void testContainerStyle() {
        Layer layer = getLayer(diagram, LAYER_PACKAGE_NAME);
        ContainerMapping containerMapping = getContainerMapping(layer, "Design Package");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertTrue(currentDescription instanceof FlatContainerStyleDescription);

        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, "Package 1");
        DDiagramElement element = null;
        if (!diagramElementsFromLabel.isEmpty()) {
            element = diagramElementsFromLabel.get(0);
        }
        assertNotNull(element);

        IGraphicalEditPart editPart = getEditPart(element);
        assertNotNull("An edit part must represent the DDiagramElement.", editPart);
        assertTrue("The edit part is not of type IDiagramContainerEditPart.", editPart instanceof IDiagramContainerEditPart);
        IDiagramContainerEditPart diagramContainerEditPart = (IDiagramContainerEditPart) editPart;

        // Flat to shape
        ContainerStyleDescription description = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        TestsUtil.synchronizationWithUIThread();
        assertTrue(containerMapping.getStyle() instanceof ShapeContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        assertEquals("Bad figure for this style", "ViewNodeContainerParallelogram", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // shape to workspace
        description = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        ((WorkspaceImageDescription) description).setWorkspacePath(IMAGE_PATH);
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue("ContainerMapping's style " + containerMapping.getStyle() + " should be instance of WorkspaceImage", containerMapping.getStyle() instanceof WorkspaceImageDescription);
        assertTrue(element.getStyle() instanceof WorkspaceImage);
        assertEquals(containerMapping.getStyle(), description);
        assertEquals("Bad figure for this style", "ViewNodeContainerRectangleFigureDesc", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // workspace to shape
        description = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof ShapeContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        assertEquals("Bad figure for this style", "ViewNodeContainerParallelogram", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // shape to flat
        description = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof FlatContainerStyleDescription);
        assertTrue(element.getStyle() instanceof FlatContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // flat to workspace
        description = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        ((WorkspaceImageDescription) description).setWorkspacePath(IMAGE_PATH);
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof WorkspaceImageDescription);
        assertTrue("Element style should be a WorkspaceImage but was " + element.getStyle(), element.getStyle() instanceof WorkspaceImage);
        assertEquals(containerMapping.getStyle(), description);
        assertEquals("Bad figure for this style", "ViewNodeContainerRectangleFigureDesc", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // workspace to flat
        description = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof FlatContainerStyleDescription);
        assertTrue(element.getStyle() instanceof FlatContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());
    }

    /**
     * Tests that when the style of a list container mapping change, the style
     * of the diagram element is also changed and that the figure is of the
     * correct type.
     */
    public void testListContainerStyle() {
        Layer layer = getLayer(diagram, "Default");
        ContainerMapping containerMapping = getContainerMapping(layer, "EC EClass");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertTrue(currentDescription instanceof FlatContainerStyleDescription);

        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, "Class1");
        DDiagramElement element = null;
        if (!diagramElementsFromLabel.isEmpty()) {
            element = diagramElementsFromLabel.get(0);
        }
        assertNotNull(element);

        IGraphicalEditPart editPart = getEditPart(element);
        assertNotNull("An edit part must represent the DDiagramElement.", editPart);
        assertTrue("The edit part is not of type DNodeListEditPart.", editPart instanceof AbstractDiagramListEditPart);
        AbstractDiagramListEditPart diagramContainerEditPart = (AbstractDiagramListEditPart) editPart;

        // Flat to shape
        ContainerStyleDescription description = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof ShapeContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        // TODO : Change the figure name when the ticket #1105 is OK
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // shape to workspace
        description = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue("ContainerMapping's style " + containerMapping.getStyle() + " should be instance of WorkspaceImageDescription", containerMapping.getStyle() instanceof WorkspaceImageDescription);
        assertTrue("Element style should be a WorkspaceImage but was " + element.getStyle(), element.getStyle() instanceof WorkspaceImage);
        assertEquals(containerMapping.getStyle(), description);
        // TODO : Change the figure name when the ticket #1105 is OK
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // workspace to shape
        description = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof ShapeContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        // TODO : Change the figure name when the ticket #1105 is OK
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // shape to flat
        description = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof FlatContainerStyleDescription);
        assertTrue(element.getStyle() instanceof FlatContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        // TODO : Change the figure name when the ticket #1105 is OK
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // flat to workspace
        description = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof WorkspaceImageDescription);
        assertTrue(element.getStyle() instanceof WorkspaceImage);
        assertEquals(containerMapping.getStyle(), description);
        // TODO : Change the figure name when the ticket #1105 is OK
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());

        // workspace to flat
        description = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof FlatContainerStyleDescription);
        assertTrue(element.getStyle() instanceof FlatContainerStyle);
        assertEquals(containerMapping.getStyle(), description);
        // TODO : Change the figure name when the ticket #1105 is OK
        assertEquals("Bad figure for this style", "GradientRoundedRectangle", diagramContainerEditPart.getPrimaryShape().getClass().getSimpleName());
    }

    private void setDescriptionStyle(final ContainerMapping containerMapping, final ContainerStyleDescription style) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                containerMapping.setStyle(style);
            }

        });
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
