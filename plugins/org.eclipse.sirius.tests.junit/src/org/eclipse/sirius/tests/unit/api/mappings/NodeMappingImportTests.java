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
package org.eclipse.sirius.tests.unit.api.mappings;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart.SquareFigure;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.refresh.ColorFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.graphics.Color;

import com.google.common.collect.Iterables;

/**
 * Tests for imported mapping
 * 
 * @author nlepine
 */
public class NodeMappingImportTests extends SiriusDiagramTestCase {

    private String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/VP-748/748.ecore";

    private String MODELER_PATH1 = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/VP-748/748-21.odesign";

    private String MODELER_PATH2 = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/VP-748/748-22.odesign";

    private String MODELER_PATH3 = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/VP-748/748-23.odesign";

    private String MODELER_PATH4 = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/VP-748/748-24.odesign";

    private String MODELER_PATH5 = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/VP-748/748-25.odesign";

    private String MODELER_PATH6 = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/VP-748/748-26.odesign";

    private String DEFAULT_VIEWPOINT_NAME = "VP-748";

    private static final String NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL = "No IGraphicalEditPart found with the label : ";

    private DDiagram diagram;

    private DiagramEditor editor;

    private VisualBindingManager colorManager;

    private ColorFactory colorFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        colorManager = new VisualBindingManager();
        colorManager.init(10, 10);
        colorFactory = new ColorFactory(colorManager);
    }

    /**
     * Test the color of the nodes mappings. Mapping order: Datatype Class Enum
     * 
     * @throws Exception
     */
    public void testImportedNodeMapping1() throws Exception {
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH1);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
        checkImportedNodeMapping();
    }

    /**
     * Test the color of the nodes mappings. Mapping order: Datatype Enum Class
     * 
     * @throws Exception
     */
    public void testImportedNodeMapping2() throws Exception {
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH2);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
        checkImportedNodeMapping();
    }

    /**
     * Test the color of the nodes mappings. Mapping order: Class Datatype Enum
     * 
     * @throws Exception
     */
    public void testImportedNodeMapping3() throws Exception {
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH3);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
        checkImportedNodeMapping();
    }

    /**
     * Test the color of the nodes mappings. Mapping order: Class Enum Datatype
     * 
     * @throws Exception
     */
    public void testImportedNodeMapping4() throws Exception {
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH4);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
        checkImportedNodeMapping();
    }

    /**
     * Test the color of the nodes mappings. Mapping order: Enum Class Datatype
     * 
     * @throws Exception
     */
    public void testImportedNodeMapping5() throws Exception {
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH5);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
        checkImportedNodeMapping();
    }

    /**
     * Test the color of the nodes mappings. Mapping order: Enum Datatype Class
     * 
     * @throws Exception
     */
    public void testImportedNodeMapping6() throws Exception {
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH6);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
        checkImportedNodeMapping();
    }

    /**
     * Check the color of the nodes mappings.
     */
    private void checkImportedNodeMapping() {
        diagram = (DDiagram) createRepresentation(DEFAULT_VIEWPOINT_NAME, semanticModel);
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        // A class
        List<DDiagramElement> class1 = getDiagramElementsFromLabel(diagram, "A");
        assertFalse("No diagram element found with the label : " + "A", class1.isEmpty());
        DDiagramElement diagramElementClass1 = class1.get(0);

        IGraphicalEditPart class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + "A", class1EP);
        checkColor(class1EP, colorFactory.green());

        // B datatype
        class1 = getDiagramElementsFromLabel(diagram, "B");
        assertFalse("No diagram element found with the label : " + "B", class1.isEmpty());
        diagramElementClass1 = class1.get(0);

        class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + "B", class1EP);
        checkColor(class1EP, colorFactory.orange());

        // C enum
        class1 = getDiagramElementsFromLabel(diagram, "C");
        assertFalse("No diagram element found with the label : " + "C", class1.isEmpty());
        diagramElementClass1 = class1.get(0);

        class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + "C", class1EP);
        checkColor(class1EP, colorFactory.purple());

        // create a class, enum and datatype with creation tool
        // Class
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        class1 = getDiagramElementsFromLabel(diagram, "class");
        assertFalse("No diagram element found with the label : " + "class", class1.isEmpty());

        diagramElementClass1 = class1.get(0);
        class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + "class", class1EP);
        checkColor(class1EP, colorFactory.green());

        // Datatype
        assertTrue(applyNodeCreationTool("DataType", diagram, diagram));
        class1 = getDiagramElementsFromLabel(diagram, "datatype");
        assertFalse("No diagram element found with the label : " + "datatype", class1.isEmpty());
        diagramElementClass1 = class1.get(0);

        class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + "datatype", class1EP);
        checkColor(class1EP, colorFactory.orange());

        // Enum
        assertTrue(applyNodeCreationTool("Enum", diagram, diagram));
        class1 = getDiagramElementsFromLabel(diagram, "enum");
        assertFalse("No diagram element found with the label : " + "enum", class1.isEmpty());
        diagramElementClass1 = class1.get(0);

        class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + "enum", class1EP);
        checkColor(class1EP, colorFactory.purple());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Check if the color of the label edit part is the same as color
     * 
     * @param editPart
     * @param color
     */
    private void checkColor(IGraphicalEditPart editPart, Color color) {
        SquareFigure squareFigureChild = getSquareFigureChild(editPart.getFigure());
        assertEquals("Wrong color for the figure ", squareFigureChild.getBackgroundColor(), color);
    }

    /**
     * Finds the SquareFigure children that contains the Color informations.
     * 
     * @param figure
     * @return
     */
    private SquareFigure getSquareFigureChild(IFigure figure) {
        while (!(figure instanceof SquareFigure) && figure.getChildren() != null && !figure.getChildren().isEmpty()) {
            if (!(Iterables.isEmpty(Iterables.filter(figure.getChildren(), SquareFigure.class)))) {
                return Iterables.get((Iterables.filter(figure.getChildren(), SquareFigure.class)), 0);
            }
            for (IFigure childFigure : Iterables.filter(figure.getChildren(), IFigure.class)) {
                SquareFigure squareFigureChild = getSquareFigureChild(childFigure);
                if (squareFigureChild != null) {
                    return squareFigureChild;
                }
            }
        }

        return null;
    }
}
