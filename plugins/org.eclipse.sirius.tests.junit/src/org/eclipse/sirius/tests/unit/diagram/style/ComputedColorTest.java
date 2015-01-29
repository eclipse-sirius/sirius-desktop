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

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.refresh.ColorFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.swt.graphics.Color;

/**
 * Test the computed colors
 * 
 * Test in disable test suite because of the undo/redo on a refresh : VP-1120
 * 
 * @author nlepine
 */
public class ComputedColorTest extends SiriusDiagramTestCase {
    private static final String NEW_E_CLASS_2 = "new EClass 2";

    private static final String NEW_E_CLASS_1 = "new EClass 1";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2230/2230.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2230/2230.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2230/2230.odesign";

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
     * Test the initialization and the refresh of the computed color
     * 
     * @throws Exception
     */
    public void testInitAndRefreshComputedColor() throws Exception {
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> class1 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_1);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_1, class1.isEmpty());
        IGraphicalEditPart class1EP = getEditPart(class1.get(0));
        assertNotNull("No IGraphicalEditPart found with the label : " + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.white());

        List<DDiagramElement> class2 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_2);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_2, class2.isEmpty());
        IGraphicalEditPart class2EP = getEditPart(class2.get(0));
        assertNotNull("No IGraphicalEditPart found with the label : " + NEW_E_CLASS_2, class2EP);
        checkColor(class2EP, colorFactory.black());

        // update the color in the semantic element
        EPackage p = (EPackage) semanticModel;
        final EClass eClass1 = (EClass) p.getEClassifier(NEW_E_CLASS_1);
        assertTrue("the class must have 3 attibutes red, blue and green :" + NEW_E_CLASS_1, eClass1.getEAllAttributes().size() == 3);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                eClass1.getEAllAttributes().get(0).setDefaultValue("0");
                eClass1.getEAllAttributes().get(1).setDefaultValue("0");
                eClass1.getEAllAttributes().get(2).setDefaultValue("0");
            }
        });

        refresh(diagram);

        class1EP = getEditPart(class1.get(0));
        assertNotNull("No IGraphicalEditPart found with the label : " + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.black());

        session.save(new NullProgressMonitor());

        // close and reopen the editor : colors do not change
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        class1 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_1);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_1, class1.isEmpty());
        class1EP = getEditPart(class1.get(0));
        assertNotNull("No IGraphicalEditPart found with the label : " + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.black());

        class2 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_2);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_2, class2.isEmpty());
        class2EP = getEditPart(class2.get(0));
        assertNotNull("No IGraphicalEditPart found with the label : " + NEW_E_CLASS_2, class2EP);
        checkColor(class2EP, colorFactory.black());
    }

    /**
     * Check if the color of the label edit part is the same as color
     * 
     * @param editPart
     * @param color
     */
    private void checkColor(IGraphicalEditPart editPart, Color color) {
        IGraphicalEditPart class1LabelEP = (IGraphicalEditPart) editPart.getPrimaryChildEditPart();
        assertNotNull("No label edit part found", class1LabelEP);
        assertTrue("The figure is not a label: " + ((SiriusWrapLabel) class1LabelEP.getFigure()).getText(), class1LabelEP.getFigure() instanceof SiriusWrapLabel);
        assertEquals("Wrong color for the label " + ((SiriusWrapLabel) class1LabelEP.getFigure()).getText(), ((SiriusWrapLabel) class1LabelEP.getFigure()).getForegroundColor(), color);
    }

    /**
     * Test the default color
     * 
     * @throws Exception
     */
    public void testDefaultComputedColor() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> class1s = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_1);
        assertFalse(class1s.isEmpty());
        IGraphicalEditPart class1EP = getEditPart(class1s.get(0));
        assertNotNull("No IGraphicalEditPart found with the label : " + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.white());
        final DDiagramElement class1 = class1s.get(0);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            protected void doExecute() {
                ComputedColor color = DescriptionFactory.eINSTANCE.createComputedColor();
                ((ContainerMapping) class1.getMapping()).getStyle().setLabelColor(color);
            }
        });

        refresh(diagram);

        assertTrue(class1.getStyle() instanceof FlatContainerStyle);
        assertEquals(colorFactory.create(((FlatContainerStyle) class1.getStyle()).getLabelColor()), colorFactory.black());

        class1EP = getEditPart(class1s.get(0));
        assertNotNull("No IGraphicalEditPart found with the label : " + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.black());
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
