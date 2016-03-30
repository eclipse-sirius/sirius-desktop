/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tabbar;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This test makes sure that the copy appearance properties action takes the
 * last selected element as reference.
 * 
 * @author Florian Barbin
 *
 */
@SuppressWarnings("restriction")
public class CopyAppearanceActionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "490444.ecore";

    private static final String SESSION_FILE = "490444.aird";

    private static final String DATA_UNIT_DIR = "data/unit/copyAppearance/";

    private static final int EXPECTED_CUSTOM_FONT_COLOR = 12560536;

    private static final int EXPECTED_CUSTOM_LINE_COLOR = 8047085;

    private static final int EXPECTED_CUSTOM_FILL_COLOR = 10011046;

    private static final int EXPECTED_CUSTOM_FONT_HEIGHT = 16;

    private static final int EXPECTED_FONT_COLOR = 0;

    private static final int EXPECTED_LINE_COLOR = 11579568;

    private static final int EXPECTED_FILL_COLOR = 16777215;

    private static final int EXPECTED_FONT_HEIGHT = 8;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {

        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Open the editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
    }

    /**
     * Tests that the Copy Appearance Action works as expected with several
     * element selected. In this case, the custom style element is the first
     * selected one and it should have the same style than the last selection
     * after the action execution.
     */
    public void testSeveralSelectedElementWithFirstSelectionWithTabbar() {
        testSeveralSelectedElementWithFirstSelection(true);
    }

    /**
     * Tests that the Copy Appearance Action works as expected with several
     * element selected. In this case, the custom style element is the first
     * selected one and it should have the same style than the last selection
     * after the action execution.
     */
    public void testSeveralSelectedElementWithFirstSelectionWithContextMenu() {
        testSeveralSelectedElementWithFirstSelection(false);
    }

    /**
     * Tests that the Copy Appearance Action works as expected with several
     * element selected. In this case, the custom style element is the last
     * selected one and other elements should have the same style than the
     * custom one.
     */
    public void testSeveralSelectedElementWithLastSelectionWithTabbar() {
        testSeveralSelectedElementWithLastSelection(true);

    }

    /**
     * Tests that the Copy Appearance Action works as expected with several
     * element selected. In this case, the custom style element is the last
     * selected one and other elements should have the same style than the
     * custom one.
     */
    public void testSeveralSelectedElementWithLastSelectionWithContextMenu() {
        testSeveralSelectedElementWithLastSelection(false);

    }

    private void testSeveralSelectedElementWithFirstSelection(boolean useTabbar) {
        SWTBotGefEditPart eClass1BotGefEditPart = editor.getEditPart("NewEClass1", AbstractDiagramElementContainerEditPart.class);
        checkCustomStyle(eClass1BotGefEditPart);

        SWTBotGefEditPart eClass2BotGefEditPart = editor.getEditPart("NewEClass2", AbstractDiagramElementContainerEditPart.class);
        checkStyleBeforeCustom(eClass2BotGefEditPart);
        SWTBotGefEditPart eClass3BotGefEditPart = editor.getEditPart("NewEClass3", AbstractDiagramElementContainerEditPart.class);
        checkStyleBeforeCustom(eClass3BotGefEditPart);
        editor.select(eClass1BotGefEditPart, eClass2BotGefEditPart, eClass3BotGefEditPart);
        performAction(useTabbar);
        SWTBotUtils.waitAllUiEvents();
        checkStyleBeforeCustom(eClass1BotGefEditPart);
        checkStyleBeforeCustom(eClass2BotGefEditPart);
        checkStyleBeforeCustom(eClass3BotGefEditPart);
    }

    private void performAction(boolean useTabbar) {
        if (useTabbar) {
            editor.bot().toolbarButtonWithTooltip(Messages.SiriusCopyAppearancePropertiesAction_tooltipMessage).click();
        } else {
            editor.clickContextMenu(DiagramUIActionsMessages.CopyAppearancePropertiesAction_text);
        }
    }

    private void testSeveralSelectedElementWithLastSelection(boolean useTabbar) {
        SWTBotGefEditPart eClass1BotGefEditPart = editor.getEditPart("NewEClass1", AbstractDiagramElementContainerEditPart.class);
        checkCustomStyle(eClass1BotGefEditPart);

        SWTBotGefEditPart eClass2BotGefEditPart = editor.getEditPart("NewEClass2", AbstractDiagramElementContainerEditPart.class);
        checkStyleBeforeCustom(eClass2BotGefEditPart);
        SWTBotGefEditPart eClass3BotGefEditPart = editor.getEditPart("NewEClass3", AbstractDiagramElementContainerEditPart.class);
        checkStyleBeforeCustom(eClass3BotGefEditPart);
        editor.select(eClass2BotGefEditPart, eClass3BotGefEditPart, eClass1BotGefEditPart);

        performAction(useTabbar);
        SWTBotUtils.waitAllUiEvents();
        checkCustomStyle(eClass1BotGefEditPart);
        checkCustomStyle(eClass2BotGefEditPart);
        checkCustomStyle(eClass3BotGefEditPart);
    }

    private void checkCustomStyle(SWTBotGefEditPart botGefEditPart) {
        EditPart editPart = botGefEditPart.part();
        Node model = (Node) editPart.getModel();
        ShapeStyle shapeStyle = (ShapeStyle) model.getStyles().get(0);
        assertEquals("Wrong expected fill color.", EXPECTED_CUSTOM_FILL_COLOR, shapeStyle.getFillColor());
        assertEquals("Wrong expected font height", EXPECTED_CUSTOM_FONT_HEIGHT, shapeStyle.getFontHeight());
        assertEquals("Wrong expected font color", EXPECTED_CUSTOM_FONT_COLOR, shapeStyle.getFontColor());
        assertEquals("Wrong expected line color", EXPECTED_CUSTOM_LINE_COLOR, shapeStyle.getLineColor());
    }

    private void checkStyleBeforeCustom(SWTBotGefEditPart botGefEditPart) {
        EditPart editPart = botGefEditPart.part();
        Node model = (Node) editPart.getModel();
        ShapeStyle shapeStyle = (ShapeStyle) model.getStyles().get(0);
        assertEquals("Wrong expected fill color.", EXPECTED_FILL_COLOR, shapeStyle.getFillColor());
        assertEquals("Wrong expected font height", EXPECTED_FONT_HEIGHT, shapeStyle.getFontHeight());
        assertEquals("Wrong expected font color", EXPECTED_FONT_COLOR, shapeStyle.getFontColor());
        assertEquals("Wrong expected line color", EXPECTED_LINE_COLOR, shapeStyle.getLineColor());
    }
}
