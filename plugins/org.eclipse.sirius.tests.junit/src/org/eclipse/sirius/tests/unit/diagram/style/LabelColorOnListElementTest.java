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
package org.eclipse.sirius.tests.unit.diagram.style;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.refresh.ColorFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.swt.graphics.Color;

/**
 * Test the label colors
 * 
 * @author nlepine
 */
public class LabelColorOnListElementTest extends SiriusDiagramTestCase {
    private static final String THE_LABEL_MUST_BE_BLACK = "The label must be black";

    private static final String NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL = "No IGraphicalEditPart found with the label : ";

    private static final String THE_LABEL_MUST_BE_NULL = "The label must be null";

    private static final String THE_DIAGRAM_ELEMENT_HAS_NO_LABEL_STYLE = "The diagram element has no label style";

    private static final String NEW_E_CLASS_1 = "new EClass 1";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2403/2403.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2403/1487.aird";

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private DDiagram diagram;

    private DiagramEditor editor;

    VisualBindingManager colorManager;

    ColorFactory colorFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        colorManager = new VisualBindingManager();
        colorManager.init(10, 10);
        colorFactory = new ColorFactory(colorManager);
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

    }

    /**
     * Test the initialization and the refresh of the label color
     * 
     * @throws Exception
     */
    public void testLabelColorOnListElementOnNewRepresentation() throws Exception {
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        IGraphicalEditPart editPart = getEditPart(diagram);
        assertTrue(editPart instanceof DDiagramEditPart);
        arrangeAll((DiagramEditPart) editPart);

        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        EOperation operation = eClass.getEOperations().get(0);

        DDiagramElement diagramElementClass1 = getFirstDiagramElement(diagram, operation);
        assertNotNull("No diagram element found with the label : " + "operation", diagramElementClass1);

        checkLabelColorCreatedAndBlack(diagramElementClass1);

        IGraphicalEditPart class1EP = getEditPart(diagramElementClass1);
        // select and aply direct edit tool on the operation
        editor.getDiagramGraphicalViewer().setSelection(new StructuredSelection(class1EP));
        applyDirectEditTool("Operation Name", diagram, diagramElementClass1, "operationrenamed");
        checkLabelColorCreatedAndBlack(diagramElementClass1);

        // select the diagram and check the color
        editor.getDiagramGraphicalViewer().setSelection(new StructuredSelection(editPart));
        checkLabelColorCreatedAndBlack(diagramElementClass1);

        // close and reopen the editor : colors do not change
        closeAndReopenEditor();
        checkLabelColorCreatedAndBlack(diagramElementClass1);
    }

    private void closeAndReopenEditor() {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    private void checkLabelColorCreatedAndBlack(DDiagramElement diagramElementClass1) {
        IGraphicalEditPart class1EP;
        assertTrue(THE_DIAGRAM_ELEMENT_HAS_NO_LABEL_STYLE, diagramElementClass1.getStyle() instanceof LabelStyle);
        assertNotNull(THE_LABEL_MUST_BE_NULL, ((LabelStyle) diagramElementClass1.getStyle()).getLabelColor());
        assertEquals(THE_LABEL_MUST_BE_BLACK, colorFactory.black(), VisualBindingManager.getDefault().getLabelColorFromRGBValues(((LabelStyle) diagramElementClass1.getStyle()).getLabelColor()));
        class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.black());
    }

    /**
     * Check if the color of the label edit part is the same as color
     * 
     * @param editPart
     * @param color
     */
    private void checkColor(IGraphicalEditPart editPart, Color color) {
        assertNotNull("No label edit part found", editPart);
        assertTrue("The figure is not a label: " + ((SiriusWrapLabel) editPart.getFigure()).getText(), editPart.getFigure() instanceof SiriusWrapLabel);
        assertEquals("Wrong color for the label " + ((SiriusWrapLabel) editPart.getFigure()).getText(), ((SiriusWrapLabel) editPart.getFigure()).getForegroundColor(), color);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = null;
        editor = null;
        colorManager = null;
        colorFactory = null;

        super.tearDown();
    }
}
