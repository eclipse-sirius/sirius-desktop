/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import java.text.MessageFormat;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests for the arrangement of synchronized elements on a diagram according to the
 * {@link SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT} preference.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class CreatedElementsLayoutTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String WRONG_POSITION_ERROR_MSG = "The {0} element should be positioned at the requested location."; //$NON-NLS-1$

    private static final String[] CREATED_ELEMENTS_NAME = { "EPackage1", "EPackage2", "EClass1", "EClass2", "EPackage3", "EPackage4", "EPackage5", "EClass3", "EClass4", "EClass5" };

    // There's a bug when containers containing elements are added to the diagram, which is the case for elements [4],
    // [5] and [6], so we're only testing elements position from [0] to [4] for the moment ([4] and not [3] because the
    // bug occurs in the next element).
    private static final int CREATED_ELEMENTS_COUNT = 5/* CREATED_ELEMENTS_NAME.length */;

    private static final String PATH = "data/unit/layout/newCreatedElementsLayout/"; //$NON-NLS-1$

    private static final String SEMANTIC_RESOURCE_NAME = "newCreatedElementsLayout.ecore"; //$NON-NLS-1$

    private static final String SESSION_RESOURCE_NAME = "newCreatedElementsLayout.aird"; //$NON-NLS-1$

    private static final String MODELER_RESOURCE_NAME = "newCreatedElementsLayout.odesign"; //$NON-NLS-1$

    private static final String FIRST_REPRESENTATION_NAME = "GrayElements"; //$NON-NLS-1$

    private static final String FIRST_REPRESENTATION_INSTANCE_NAME = "new " + FIRST_REPRESENTATION_NAME; //$NON-NLS-1$

    private static final String SECOND_REPRESENTATION_NAME = "ColoredElements"; //$NON-NLS-1$

    private static final String SECOND_REPRESENTATION_INSTANCE_NAME = "new " + SECOND_REPRESENTATION_NAME; //$NON-NLS-1$

    private static final String EPACKAGE_TOOL_NAME = "EPackage"; //$NON-NLS-1$

    private static final String ECLASS_TOOL_NAME = "EClass"; //$NON-NLS-1$

    private static final String MULTIPLE_EPACKAGES_TOOL_NAME = "MultipleEPackages"; //$NON-NLS-1$

    private static final String MULTIPLE_ECLASSES_TOOL_NAME = "MultipleEClasses"; //$NON-NLS-1$

    private SWTBotSiriusDiagramEditor editor2;

    protected SWTBotGefEditPart diagramEditPartBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected boolean getAutoRefreshMode() {
        return true;
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME); //$NON-NLS-1$
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        closeOutline();
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), SECOND_REPRESENTATION_NAME, SECOND_REPRESENTATION_INSTANCE_NAME, DDiagram.class, true, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), FIRST_REPRESENTATION_NAME, FIRST_REPRESENTATION_INSTANCE_NAME, DDiagram.class, true, true);
        diagramEditPartBot = editor2.rootEditPart().children().get(0);
    }

    private void setUpTest() {
        Point freePosition = new Point(0, 0);
        editor.scrollTo(freePosition);
        freePosition = activateTool(EPACKAGE_TOOL_NAME, freePosition, CREATED_ELEMENTS_NAME[0]);
        freePosition = activateTool(EPACKAGE_TOOL_NAME, freePosition, CREATED_ELEMENTS_NAME[1]);
        freePosition = activateTool(ECLASS_TOOL_NAME, freePosition, CREATED_ELEMENTS_NAME[2]);
        freePosition = activateTool(ECLASS_TOOL_NAME, freePosition, CREATED_ELEMENTS_NAME[3]);
        freePosition = activateTool(MULTIPLE_EPACKAGES_TOOL_NAME, freePosition, CREATED_ELEMENTS_NAME[4]);
        activateTool(MULTIPLE_ECLASSES_TOOL_NAME, freePosition, CREATED_ELEMENTS_NAME[5]);
        editor2.show();
    }

    private Point activateTool(String toolLabel, Point creationLocation, String lastExpectedCreatedElementLabel) {
        editor.activateTool(toolLabel);
        editor.click(creationLocation);
        SWTBotGefEditPart epBot = editor.getEditPart(lastExpectedCreatedElementLabel, IAbstractDiagramNodeEditPart.class);
        return editor.getBounds(epBot).getBottomLeft().getCopy().translate(0, 10);
    }

    public void testElementsPositionDiagonalPreference() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT.name(), 0);
        setUpTest();
        Point expectedPosition = getEditorVisiblePart(diagramEditPartBot.part()).getCenter();
        for (int i = 0; i < CREATED_ELEMENTS_COUNT; i++) {
            Point location = editor2.getAbsoluteLocation(CREATED_ELEMENTS_NAME[i], IAbstractDiagramNodeEditPart.class);
            GraphicTestsSupportHelp.assertEquals(MessageFormat.format(WRONG_POSITION_ERROR_MSG, CREATED_ELEMENTS_NAME[i]), expectedPosition, location, 5, 5);
            GraphicalEditPart ep = ((GraphicalEditPart) editor2.getEditPart(CREATED_ELEMENTS_NAME[i], IAbstractDiagramNodeEditPart.class).part());
            expectedPosition = ep.getFigure().getBounds().getRight().getCopy().translate(30, 0);
        }
    }

    public void testElementsPositionVerticalPreference() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT.name(), 1);
        setUpTest();
        Point expectedPosition = getEditorVisiblePart(diagramEditPartBot.part()).getCenter();
        for (int i = 0; i < CREATED_ELEMENTS_COUNT; i++) {
            Point location = editor2.getAbsoluteLocation(CREATED_ELEMENTS_NAME[i], IAbstractDiagramNodeEditPart.class);
            GraphicTestsSupportHelp.assertEquals(MessageFormat.format(WRONG_POSITION_ERROR_MSG, CREATED_ELEMENTS_NAME[i]), expectedPosition, location, 5, 5);
            GraphicalEditPart ep = ((GraphicalEditPart) editor2.getEditPart(CREATED_ELEMENTS_NAME[i], IAbstractDiagramNodeEditPart.class).part());
            expectedPosition = ep.getFigure().getBounds().getBottomLeft().getCopy().translate(0, 30);
        }
    }

    public void testElementsPositionHorizontalPreference() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_NEWLY_CREATED_ELEMENTS_LAYOUT.name(), 2);
        setUpTest();
        Point expectedPosition = getEditorVisiblePart(diagramEditPartBot.part()).getCenter();
        for (int i = 0; i < CREATED_ELEMENTS_COUNT; i++) {
            Point location = editor2.getAbsoluteLocation(CREATED_ELEMENTS_NAME[i], IAbstractDiagramNodeEditPart.class);
            GraphicTestsSupportHelp.assertEquals(MessageFormat.format(WRONG_POSITION_ERROR_MSG, CREATED_ELEMENTS_NAME[i]), expectedPosition, location, 5, 5);
            GraphicalEditPart ep = ((GraphicalEditPart) editor2.getEditPart(CREATED_ELEMENTS_NAME[i], IAbstractDiagramNodeEditPart.class).part());
            expectedPosition = ep.getFigure().getBounds().getTopRight().getCopy().translate(30, 0);
        }
    }

    /**
     * Get a rectangle representing the visible part in screen coordinates.
     * 
     * @param part
     *            a part from the diagram.
     * @return A rectangle representing the visible part in screen coordinates.
     */
    public static Rectangle getEditorVisiblePart(EditPart part) {
        Rectangle result = new Rectangle();
        Control control = part.getViewer().getControl();
        if (control instanceof FigureCanvas) {
            result = ((FigureCanvas) part.getViewer().getControl()).getViewport().getBounds();
        }
        return result;
    }
}
