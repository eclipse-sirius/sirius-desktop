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
package org.eclipse.sirius.tests.unit.api.mappings;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirResizableEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.RegionContainerResizableEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.RegionResizableEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart.RegionContainerLayoutManager;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeListCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AlphaDropShadowBorder;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.collect.Iterables;

/**
 * Container mapping imports, children presentation, compartments related tests.
 * 
 * @author mporhel
 */
public class ContainerMappingImportWithChildrenPresentationChangesTests extends SiriusDiagramTestCase {
    private static final String COMMON_PATH = "/data/unit/mappings/childrenPresentationChanges/compartments";

    private static final String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + COMMON_PATH + ".ecore";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + COMMON_PATH + ".aird";

    private static final String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + COMMON_PATH + ".odesign";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "ChildrenPresentationChangeDiagram";

    private DDiagram dDiagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, SESSION_PATH);
        dDiagram = (DDiagram) getRepresentations(REPRESENTATION_DESCRIPTION_NAME).iterator().next();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    public void testDiagramAfterChildrenPresentationChange() throws Exception {

        // Default layer mapping is configured as a FreeForm container.
        checkLayerChangeEffect(ContainerLayout.FREE_FORM);

        activateLayer(dDiagram, "VStackByImport");
        checkLayerChangeEffect(ContainerLayout.VERTICAL_STACK);

        activateLayer(dDiagram, "ListByImport");
        checkLayerChangeEffect(ContainerLayout.LIST);

        activateLayer(dDiagram, "HStackByImport");
        checkLayerChangeEffect(ContainerLayout.HORIZONTAL_STACK);
        
        undo();
        checkLayerChangeEffect(ContainerLayout.LIST);
        
        undo();
        //Second undo for the "Arrange created views" command.
        undo();
        checkLayerChangeEffect(ContainerLayout.VERTICAL_STACK);
        
        undo();
        checkLayerChangeEffect(ContainerLayout.FREE_FORM);
    }

    private void checkLayerChangeEffect(ContainerLayout expectedContainerLayout) {
        assertEquals("Only one diagram element should be created from the container mapping import chain.", 1, dDiagram.getOwnedDiagramElements().size());
        DDiagramElementContainer ddec = (DDiagramElementContainer) dDiagram.getOwnedDiagramElements().iterator().next();
        AbstractDiagramElementContainerEditPart editPart = (AbstractDiagramElementContainerEditPart) getEditPart(ddec);
        EditPolicy primaryDragPolicy = editPart.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        IResizableCompartmentEditPart compartmentPart = (IResizableCompartmentEditPart) editPart.getChildren().get(1);

        if (ContainerLayout.LIST == expectedContainerLayout) {
            assertTrue("List children presentation should produce a DNodeList.", ddec instanceof DNodeList);
            assertTrue("Wrong edit part type for a DNodeList.", editPart instanceof IDiagramListEditPart);
            assertEquals("Wrong primary drag policy for a DNodeList.", AirResizableEditPolicy.class, primaryDragPolicy.getClass());

            assertEquals("The sepcified DNodeList should only contains its style.", 1, ddec.eContents().size());
            assertTrue("Wrong type of compartment part for the DNodeList.", compartmentPart instanceof AbstractDNodeListCompartmentEditPart);
            assertEquals("Wrong content pane for a List compartment.", editPart.getContentPane(), compartmentPart.getFigure().getParent());
            assertTrue("The DNodeList compartment should be empty.", compartmentPart.getChildren().isEmpty());
        } else {
            assertTrue(expectedContainerLayout.getName() + " children presentation should produce a DNodeContainer", ddec instanceof DNodeContainer);
            assertTrue("Wrong edit part type for a DNodeDNodeContainer.", editPart instanceof IDiagramContainerEditPart);

            assertEquals("Wrong children presentation for the DNodeContainer.", expectedContainerLayout, ((DNodeContainer) ddec).getChildrenPresentation());
            assertTrue("Wrong type of compartment part for the DNodeContainer.", compartmentPart instanceof AbstractDNodeContainerCompartmentEditPart);
            assertFalse("The DNodeContainer compartment should not be empty.", compartmentPart.getChildren().isEmpty());

            LayoutManager layoutManager = ((AbstractDNodeContainerCompartmentEditPart) compartmentPart).getContentPane().getLayoutManager();

            if (ContainerLayout.FREE_FORM == expectedContainerLayout) {
                assertEquals("Wrong primary drag policy for a FreeForm container.", AirResizableEditPolicy.class, primaryDragPolicy.getClass());
                assertEquals("Wrong layout manager for a FreeForm container.", FreeFormLayoutEx.class, layoutManager.getClass());
                assertEquals("Wrong content pane for a FreeForm container compartment.", editPart.getMainFigure(), compartmentPart.getFigure().getParent());

                for (AbstractDiagramElementContainerEditPart subContainerPart : Iterables.filter(compartmentPart.getChildren(), AbstractDiagramElementContainerEditPart.class)) {
                    assertEquals("Wrong primary drag policy for a FreeForm container child.", AirResizableEditPolicy.class, subContainerPart.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE).getClass());
                    assertTrue("A sub container which is not a Region should have a shadow border.", subContainerPart.getMainFigure().getBorder() instanceof AlphaDropShadowBorder);
                }
            } else {
                assertEquals("Wrong primary drag policy for a RegionContainer.", RegionContainerResizableEditPolicy.class, primaryDragPolicy.getClass());
                assertEquals("Wrong content pane for a RegionContainer compartment.", editPart.getContentPane(), compartmentPart.getFigure().getParent());
                assertEquals("Wrong layout manager for a RegionContainer.", RegionContainerLayoutManager.class, layoutManager.getClass());
                assertEquals("Invalid configuration of the RegionContainer layout manager.", ContainerLayout.VERTICAL_STACK == expectedContainerLayout,
                        ((RegionContainerLayoutManager) layoutManager).isVertical());

                for (AbstractDiagramElementContainerEditPart subContainerPart : Iterables.filter(compartmentPart.getChildren(), AbstractDiagramElementContainerEditPart.class)) {
                    assertEquals("Wrong primary drag policy for a Region.", RegionResizableEditPolicy.class, subContainerPart.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE).getClass());
                    assertNull("Region should not have a shadow border.", subContainerPart.getMainFigure().getBorder());
                }
            }
        }
    }
}
