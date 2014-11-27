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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.refresh.ColorFactory;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;

/**
 * Test the label colors
 * 
 * @author nlepine
 */
public class LabelColorTest extends SiriusDiagramTestCase {

    private static final String THE_LABEL_MUST_BE_BLACK = "The label must be black";

    private static final String NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL = "No IGraphicalEditPart found with the label : ";

    private static final String THE_DIAGRAM_ELEMENT_HAS_NO_LABEL_STYLE = "The diagram element has no label style";

    private static final String NEW_E_CLASS_2 = "new EClass 2";

    private static final String NEW_E_CLASS_1 = "new EClass 1";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/2403.ecore";

    private static final String REPRESENTATION_MODEL_NAME = "2403.aird";

    private static final String REPRESENTATION_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + REPRESENTATION_MODEL_NAME;

    private static final String MODELER_PATH = EcoreModeler.MODELER_PATH;

    private static final String REPRESENTATION_DESC_NAME = EcoreModeler.ENTITIES_DESC_NAME;

    private DDiagram diagram;

    private DiagramEditor editor;

    VisualBindingManager colorManager;

    ColorFactory colorFactory;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        colorManager = new VisualBindingManager();
        colorManager.init(10, 10);
        colorFactory = new ColorFactory(colorManager);
        TestsUtil.emptyEventsFromUIThread();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/style/ticket2403/2403.ecore", "/" + SEMANTIC_MODEL_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/style/ticket2403/2403.aird", "/" + REPRESENTATION_MODEL_PATH);
        TestsUtil.emptyEventsFromUIThread();

        // Uncheck the auto refresh and refresh on opening on preferences
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

    }

    /**
     * Test the initialization and the refresh of the label color
     * 
     * @throws Exception
     */
    public void testInitAndRefreshLabelColor() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        TestsUtil.emptyEventsFromUIThread();

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> class1 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_1);
        List<DDiagramElement> class2 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_2);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_1, class1.isEmpty());
        DDiagramElement diagramElementClass1 = class1.get(0);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_2, class2.isEmpty());
        DDiagramElement diagramElementClass2 = class1.get(0);

        checkLabelColorDoesNotExistAndBlack(diagramElementClass1, diagramElementClass2);

        // close and reopen the editor : colors do not change
        closeAndReopenEditor();

        checkLabelColorDoesNotExistAndBlack(diagramElementClass1, diagramElementClass2);

        // refresh the diagram
        refresh(diagram);

        checkLabelColorCreatedAndBlack(diagramElementClass1, diagramElementClass2);

        session.save(new NullProgressMonitor());

        // close and reopen the editor : colors do not change
        closeAndReopenEditor();

        checkLabelColorCreatedAndBlack(diagramElementClass1, diagramElementClass2);
    }

    /**
     * Test the initialization and the refresh of the label color
     * 
     * @throws Exception
     */
    public void testInitAndRefreshLabelColorWithMigration() throws Exception {
        // Step 1 : run a migration
        runMigrationProcess();

        // as migration refreshes representations, we do not test state before
        // refresh
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        TestsUtil.synchronizationWithUIThread();

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> class1 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_1);
        List<DDiagramElement> class2 = getDiagramElementsFromLabel(diagram, NEW_E_CLASS_2);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_1, class1.isEmpty());
        DDiagramElement diagramElementClass1 = class1.get(0);
        assertFalse("No diagram element found with the label : " + NEW_E_CLASS_2, class2.isEmpty());
        DDiagramElement diagramElementClass2 = class1.get(0);

        checkLabelColorCreatedAndBlack(diagramElementClass1, diagramElementClass2);

        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // close and reopen the editor : colors do not change
        closeAndReopenEditor();

        checkLabelColorCreatedAndBlack(diagramElementClass1, diagramElementClass2);
    }

    private void closeAndReopenEditor() {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    private void checkLabelColorDoesNotExistAndBlack(DDiagramElement diagramElementClass1, DDiagramElement diagramElementClass2) {
        assertTrue(THE_DIAGRAM_ELEMENT_HAS_NO_LABEL_STYLE, diagramElementClass1.getStyle() instanceof LabelStyle);
        // Default label color of the style.
        assertEquals(THE_LABEL_MUST_BE_BLACK, RGBValues.create(0, 0, 0), ((LabelStyle) diagramElementClass1.getStyle()).getLabelColor());
        IGraphicalEditPart class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.black());

        assertTrue(THE_DIAGRAM_ELEMENT_HAS_NO_LABEL_STYLE, diagramElementClass2.getStyle() instanceof LabelStyle);
        // Default label color of the style.
        assertEquals(THE_LABEL_MUST_BE_BLACK, RGBValues.create(0, 0, 0), ((LabelStyle) diagramElementClass2.getStyle()).getLabelColor());
        IGraphicalEditPart class2EP = getEditPart(diagramElementClass2);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + NEW_E_CLASS_2, class2EP);
        checkColor(class2EP, colorFactory.black());
    }

    private void checkLabelColorCreatedAndBlack(DDiagramElement diagramElementClass1, DDiagramElement diagramElementClass2) {
        IGraphicalEditPart class1EP;
        assertTrue(THE_DIAGRAM_ELEMENT_HAS_NO_LABEL_STYLE, diagramElementClass1.getStyle() instanceof LabelStyle);
        assertEquals(THE_LABEL_MUST_BE_BLACK, RGBValues.create(0, 0, 0), ((LabelStyle) diagramElementClass2.getStyle()).getLabelColor());
        assertEquals(THE_LABEL_MUST_BE_BLACK, colorFactory.black(), VisualBindingManager.getDefault().getLabelColorFromRGBValues(((LabelStyle) diagramElementClass1.getStyle()).getLabelColor()));
        class1EP = getEditPart(diagramElementClass1);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + NEW_E_CLASS_1, class1EP);
        checkColor(class1EP, colorFactory.black());

        assertTrue(THE_DIAGRAM_ELEMENT_HAS_NO_LABEL_STYLE, diagramElementClass2.getStyle() instanceof LabelStyle);
        assertEquals(THE_LABEL_MUST_BE_BLACK, RGBValues.create(0, 0, 0), ((LabelStyle) diagramElementClass2.getStyle()).getLabelColor());
        assertEquals(THE_LABEL_MUST_BE_BLACK, colorFactory.black(), VisualBindingManager.getDefault().getLabelColorFromRGBValues(((LabelStyle) diagramElementClass2.getStyle()).getLabelColor()));
        class1EP = getEditPart(diagramElementClass2);
        assertNotNull(NO_I_GRAPHICAL_EDIT_PART_FOUND_WITH_THE_LABEL + NEW_E_CLASS_2, class1EP);
        checkColor(class1EP, colorFactory.black());
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
     * Method to run migration process.
     * 
     * @param fileName
     *            File name on which to run migration process.
     * @throws Exception
     */
    protected void runMigrationProcess() throws Exception {
        IFile iFile = getIFile(REPRESENTATION_MODEL_NAME);
        assertTrue(iFile.exists());

        // migration is now done at loading time. We only run a repair here.
        final SiriusRepairProcess process = new SiriusRepairProcess(iFile, false);
        process.run(new NullProgressMonitor());
        process.dispose();
    }

    protected IFile getIFile(final String fileName) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getFile("/" + fileName);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        diagram = null;
        editor = null;
        colorManager = null;
        colorFactory = null;
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
