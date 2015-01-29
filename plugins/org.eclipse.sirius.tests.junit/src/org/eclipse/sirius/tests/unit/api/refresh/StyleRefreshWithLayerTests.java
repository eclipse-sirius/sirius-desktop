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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerParallelogram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Refresh style tests for Entities diagram of ecore modeler with different
 * layers and conditional style.
 * 
 * @author nlepine
 */
public class StyleRefreshWithLayerTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/style/layers/test861.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/style/layers/ecore.odesign";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/style/layers/test861.aird";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private DDiagram diagram;

    private IEditorPart editorPart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    public void testShapeContainerStyle() {
        Layer layer = getLayer(diagram, "Default");
        ContainerMapping containerMapping = getContainerMapping(layer, "EC EClass");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertNotNull(currentDescription);

        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        DDiagramElement element = getFirstDiagramElement(diagram, eClass);
        assertNotNull(element);

        EClass eClass2 = (EClass) ePackage.getEClassifiers().get(1);
        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, eClass2.getName());
        assertFalse(diagramElementsFromLabel.isEmpty());
        DDiagramElement element2 = diagramElementsFromLabel.get(0);

        assertTrue(containerMapping.getStyle() instanceof ShapeContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), currentDescription);

        IGraphicalEditPart editPart = getEditPart(element, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        AbstractDiagramContainerEditPart ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof ViewNodeContainerParallelogram);

        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof ViewNodeContainerParallelogram);

        // shape to flat
        ContainerStyleDescription description = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);

        assertTrue(containerMapping.getStyle() instanceof FlatContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);

        editPart = getEditPart(element, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof ViewNodeContainerParallelogram);

        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof GradientRoundedRectangle);

        // flat to shape
        description = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);

        assertTrue(containerMapping.getStyle() instanceof ShapeContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);

        editPart = getEditPart(element, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof ViewNodeContainerParallelogram);

        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof ViewNodeContainerParallelogram);

        // flat to rounded flat
        description = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);

        assertTrue(containerMapping.getStyle() instanceof FlatContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);

        editPart = getEditPart(element, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof ViewNodeContainerParallelogram);

        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        assertTrue(ep.getPrimaryShape() instanceof GradientRoundedRectangle);

        // test for rounded corner
        setDescriptionCorner(description, true, 26, 26);
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        ep.refresh();
        assertTrue(ep.getPrimaryShape() instanceof GradientRoundedRectangle);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerHeight() == 26);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerWidth() == 26);

        // suppr rounded corner
        setDescriptionCorner(description, false, 26, 26);
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        ep.refresh();
        assertTrue(ep.getPrimaryShape() instanceof GradientRoundedRectangle);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerHeight() == 0);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerWidth() == 0);

        // add rounded corner
        setDescriptionCorner(description, true, 26, 26);
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        ep.refresh();
        assertTrue(ep.getPrimaryShape() instanceof GradientRoundedRectangle);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerHeight() == 26);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerWidth() == 26);

        // suppr rounded corner
        setDescriptionCorner(description, false, 0, 0);
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        editPart = getEditPart(element2, editorPart);
        assertTrue(editPart instanceof AbstractDiagramContainerEditPart);
        ep = (AbstractDiagramContainerEditPart) editPart;
        ep.refresh();
        assertTrue(ep.getPrimaryShape() instanceof GradientRoundedRectangle);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerHeight() == 0);
        assertTrue(((GradientRoundedRectangle) ep.getPrimaryShape()).getCornerWidth() == 0);
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

    private void setDescriptionCorner(final ContainerStyleDescription style, final boolean b, final int i, final int j) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                style.setRoundedCorner(b);
                style.setArcHeight(i);
                style.setArcWidth(j);
            }

        });
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
