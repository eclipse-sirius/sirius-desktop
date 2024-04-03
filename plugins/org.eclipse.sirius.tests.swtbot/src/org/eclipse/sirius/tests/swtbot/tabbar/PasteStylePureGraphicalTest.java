/*******************************************************************************
 * Copyright (c) 2016, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.tabbar;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusStyleClipboard;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This test makes sure that the copy and paste style (not semantic) action works.
 * 
 * @author Florian Barbin
 * @author Séraphin Costa <seraphin.costa@obeosoft.com>
 *
 */
public class PasteStylePureGraphicalTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "490444.ecore";

    private static final String SESSION_FILE = "490444.aird";

    private static final String ODESIGN_FILE = "My.odesign";

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
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {

        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    void openDiagramSimple() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
    }

    void openDiagramComplex() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "testLabelEdgeDiagram", "new testLabelEdgeDiagram", DDiagram.class);
    }

    private void performCopy(String name, Class<? extends EditPart> type) {
        editor.select(editor.getEditPart(name, type));
        SWTBotUtils.waitAllUiEvents();
        performCopy(false);
    }

    private void performCopy(boolean useTabbar) {
        if (useTabbar) {
            editor.bot().toolbarButtonWithTooltip(Messages.CopyFormatAction_toolTipText_diagramElements).click();
        } else {
            editor.clickContextMenu(Messages.CopyFormatAction_text);
        }
    }

    private void performPaste(String name, Class<? extends EditPart> type) {
        editor.select(editor.getEditPart(name, type));
        SWTBotUtils.waitAllUiEvents();
        performPaste(false);
    }

    private void performPaste(boolean useTabbar) {
        if (useTabbar) {
            editor.bot().toolbarDropDownButtonWithTooltip(Messages.PasteStylePureGraphicalAction_toolTipText).click();
        } else {
            editor.clickContextMenu(Messages.PasteStylePureGraphicalAction_text);
        }
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

    /**
     * This test copies the style of a non-customized element and pastes it onto two other elements: customized and
     * non-customized (using toolbar action).
     */
    public void testCopyUncustomAndPasteWithTabbar() {
        testCopyUncustomAndPaste(true);
    }

    /**
     * This test copies the style of a non-customized element and pastes it onto two other elements: customized and
     * non-customized (using context menu action).
     */
    public void testCopyUncustomAndPasteWithContextMenu() {
        testCopyUncustomAndPaste(false);
    }

    /**
     * This test copies the style of a non-customized element and pastes it onto two other elements: customized and
     * non-customized (using toolbar action).
     */
    public void testCopyCustomAndPasteWithTabbar() {
        testCopyCustomAndPaste(true);
    }

    /**
     * This test copies the style of a non-customized element and pastes it onto two other elements: customized and
     * non-customized (using context menu action).
     */
    public void testCopyCustomAndPasteWithContextMenu() {
        testCopyCustomAndPaste(false);
    }

    private void testCopyUncustomAndPaste(boolean useTabbar) {
        openDiagramSimple();

        SWTBotGefEditPart eClass1BotGefEditPart = editor.getEditPart("NewEClass1", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart eClass2BotGefEditPart = editor.getEditPart("NewEClass2", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart eClass3BotGefEditPart = editor.getEditPart("NewEClass3", AbstractDiagramElementContainerEditPart.class);

        checkCustomStyle(eClass1BotGefEditPart);
        checkStyleBeforeCustom(eClass2BotGefEditPart);
        checkStyleBeforeCustom(eClass3BotGefEditPart);

        editor.select(eClass3BotGefEditPart);
        performCopy(useTabbar);
        editor.select(eClass1BotGefEditPart, eClass2BotGefEditPart);
        performPaste(useTabbar);
        SWTBotUtils.waitAllUiEvents();

        checkStyleBeforeCustom(eClass1BotGefEditPart);
        checkStyleBeforeCustom(eClass2BotGefEditPart);
        checkStyleBeforeCustom(eClass3BotGefEditPart);
    }

    private void testCopyCustomAndPaste(boolean useTabbar) {
        openDiagramSimple();

        SWTBotGefEditPart eClass1BotGefEditPart = editor.getEditPart("NewEClass1", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart eClass2BotGefEditPart = editor.getEditPart("NewEClass2", AbstractDiagramElementContainerEditPart.class);
        SWTBotGefEditPart eClass3BotGefEditPart = editor.getEditPart("NewEClass3", AbstractDiagramElementContainerEditPart.class);

        checkCustomStyle(eClass1BotGefEditPart);
        checkStyleBeforeCustom(eClass2BotGefEditPart);
        checkStyleBeforeCustom(eClass3BotGefEditPart);

        editor.select(eClass1BotGefEditPart);
        performCopy(useTabbar);
        editor.select(eClass2BotGefEditPart, eClass3BotGefEditPart);
        performPaste(useTabbar);
        SWTBotUtils.waitAllUiEvents();

        checkCustomStyle(eClass1BotGefEditPart);
        checkCustomStyle(eClass2BotGefEditPart);
        checkCustomStyle(eClass3BotGefEditPart);
    }

    private void lockDiagram() {
        // activate the ReadOnlyPermission Authority on the representation
        DialectEditor dialectEditor = (DialectEditor) editor.getReference().getEditor(false);
        DRepresentation representation = dialectEditor.getRepresentation();
        ReadOnlyPermissionAuthority permissionAuthority = (ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation);
        permissionAuthority.activate();
        permissionAuthority.notifyLock(Collections.singleton(representation));
    }

    private void assertPasteStyleEnable(String message) {
        var pasteMenu = getPasteMenu();
        assertTrue(message, pasteMenu.isEnabled() && pasteMenu.getToolTipText().equals(Messages.PasteStylePureGraphicalAction_toolTipText));
    }

    private void assertPasteStyleDisable(String message) {
        var pasteMenu = getPasteMenu();
        assertFalse(message, pasteMenu.isEnabled() && pasteMenu.getToolTipText().equals(Messages.PasteStylePureGraphicalAction_toolTipText));
    }

    public void testPasteEnablement() {
        openDiagramSimple();

        var styleClipboard = SiriusStyleClipboard.getInstance();
        SWTBotGefEditPart diagram = editor.mainEditPart();
        SWTBotGefEditPart elementPartBot = editor.getEditPart("NewEClass1", AbstractDiagramElementContainerEditPart.class);
        View elementGMF = (View) elementPartBot.part().getModel();
        DDiagramElement elementSirius = (DDiagramElement) elementGMF.getElement();
        editor.setFocus();

        styleClipboard.clear();
        SWTBotUtils.waitAllUiEvents();

        // check without diagram selection
        editor.select(diagram);
        assertPasteStyleDisable("With empty clipboard and without selection, Paste Style must be disabled");

        // check with selection
        editor.select(elementPartBot);
        assertPasteStyleDisable("With empty clipboard and a selection, Paste Style must be disabled");

        // check update on store data
        styleClipboard.store(elementGMF, elementSirius.getStyle());
        SWTBotUtils.waitAllUiEvents();
        assertPasteStyleEnable("With data in clipboard and a selection, Paste Style must be enabled");

        // check update on change selection
        editor.select(diagram);
        SWTBotUtils.waitAllUiEvents();
        assertPasteStyleDisable("With data in clipboard, Paste Style must be disabled when the selection changes to no selection.");

        editor.select(elementPartBot);
        SWTBotUtils.waitAllUiEvents();
        assertPasteStyleEnable("With data in clipboard, Paste Style must be enabled when the selection changes to an element.");

        // check update on change permission
        lockDiagram();
        SWTBotUtils.waitAllUiEvents();
        assertPasteStyleDisable("With data in clipboard and a selection, Paste Style must be disabled when the diagram locks.");
    }

    private static final RGBValues BLACK = RGBValues.create(0, 0, 0);

    private static final RGBValues WHITE = RGBValues.create(255, 255, 255);

    private static final RGBValues GREY = RGBValues.create(136, 136, 136);

    private static final RGBValues LIGHT_GREY = RGBValues.create(209, 209, 209);

    private static final RGBValues RED = RGBValues.create(239, 41, 41);

    private static final RGBValues RED_LEGACY = RGBValues.create(227, 164, 156);

    private static final RGBValues BLUE = RGBValues.create(114, 159, 207);

    private static final RGBValues YELLOW = RGBValues.create(255, 245, 181);

    private static final RGBValues LIGHT_BLUE = RGBValues.create(194, 239, 255);

    private static final RGBValues DARK_ORANGE = RGBValues.create(224, 133, 3);

    private static final ExpectedFontStyle DEFAULT_FONT = new ExpectedFontStyle(false, false, false, false, BLACK);

    /**
     * This record contains style data for node.
     */
    record ExpectedNodeStyle(ExpectedFontStyle font, RGBValues backgroundColor) {
    };

    /**
     * This record contains style data for container.
     */
    record ExpectedContainerStyle(ExpectedFontStyle font, RGBValues backgroundColor, RGBValues foregroundColor) {
    };

    /**
     * This record contains style data for edge with 3 label.
     */
    record ExpectedEdgeStyle(ExpectedFontStyle gmf, ExpectedFontStyle begin, ExpectedFontStyle middle, ExpectedFontStyle end) {
    };

    /**
     * This record contains font style data.
     */
    record ExpectedFontStyle(boolean isBold, boolean isItalic, boolean isUnderline, boolean isStrikeThrough, RGBValues color) {
    };

    /**
     * Check if the Sirius label style {@link BasicLabelStyle} match to the expected font style.
     */
    private void assertSiriusFontStyle(String message, BasicLabelStyle actual, ExpectedFontStyle expected) {
        assertEquals(message + ": wrong expected bold custom feature.", expected.isBold, actual.getLabelFormat().contains(FontFormat.BOLD_LITERAL));
        assertEquals(message + ": wrong expected italic custom feature.", expected.isItalic, actual.getLabelFormat().contains(FontFormat.ITALIC_LITERAL));
        assertEquals(message + ": wrong expected strike through custom feature.", expected.isStrikeThrough, actual.getLabelFormat().contains(FontFormat.STRIKE_THROUGH_LITERAL));
        assertEquals(message + ": wrong expected underline custom feature.", expected.isUnderline, actual.getLabelFormat().contains(FontFormat.UNDERLINE_LITERAL));
        assertEquals(message + ": wrong expected color.", expected.color, actual.getLabelColor());
    }

    /**
     * Check if the node style match to the expected style (Sirius and GMF verification).
     * 
     * @param name
     *            The name (see the label) of the EditPart to check.
     * @param type
     *            The type of the EditPart to check.
     * @param expected
     *            The expected node style.
     */
    private void assertNodeStyle(String name, Class<? extends EditPart> type, ExpectedNodeStyle expected) {
        SWTBotGefEditPart botGefEditPart = editor.getEditPart(name, type);
        IGraphicalEditPart editPart = (IGraphicalEditPart) botGefEditPart.part();
        View model = (View) editPart.getModel();
        List<?> gmfStyles = model.getStyles();

        // GMF Style
        ShapeStyle shapeStyle = gmfStyles.stream() //
                .filter(ShapeStyle.class::isInstance) //
                .map(ShapeStyle.class::cast) //
                .findAny() //
                .orElseThrow();
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected bold status.", expected.font.isBold, shapeStyle.isBold());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected italic status.", expected.font.isItalic, shapeStyle.isItalic());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected strike through status.", expected.font.isStrikeThrough, shapeStyle.isStrikeThrough());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected underline status.", expected.font.isUnderline, shapeStyle.isUnderline());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected gmf font color.", expected.font.color.toInteger(), shapeStyle.getFontColor());

        Square siriusStyle = (Square) ((DDiagramElement) editPart.resolveSemanticElement()).getStyle();
        assertSiriusFontStyle(name + "(" + type.getSimpleName() + ")", siriusStyle, expected.font);
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected background color.", expected.backgroundColor, siriusStyle.getColor());
    }

    /**
     * Check if the container style match to the expected style (Sirius and GMF verification).
     * 
     * @param name
     *            The name (see the label) of the EditPart to check.
     * @param type
     *            The type of the EditPart to check.
     * @param expected
     *            The expected container style.
     */
    private void assertContainerStyle(String name, Class<? extends EditPart> type, ExpectedContainerStyle expected) {
        SWTBotGefEditPart botGefEditPart = editor.getEditPart(name, type);
        IGraphicalEditPart editPart = (IGraphicalEditPart) botGefEditPart.part();
        View model = (View) editPart.getModel();
        List<?> gmfStyles = model.getStyles();

        // GMF Style
        ShapeStyle shapeStyle = gmfStyles.stream() //
                .filter(ShapeStyle.class::isInstance) //
                .map(ShapeStyle.class::cast) //
                .findAny() //
                .orElseThrow();
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected bold status.", expected.font.isBold, shapeStyle.isBold());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected italic status.", expected.font.isItalic, shapeStyle.isItalic());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected strike through status.", expected.font.isStrikeThrough, shapeStyle.isStrikeThrough());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected underline status.", expected.font.isUnderline, shapeStyle.isUnderline());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected gmf font color.", expected.font.color.toInteger(), shapeStyle.getFontColor());

        FlatContainerStyle siriusStyle = (FlatContainerStyle) ((DDiagramElement) editPart.resolveSemanticElement()).getStyle();
        assertSiriusFontStyle(name + "(" + type.getSimpleName() + ")", siriusStyle, expected.font);
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected background color.", expected.backgroundColor, siriusStyle.getBackgroundColor());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected foreground color.", expected.foregroundColor, siriusStyle.getForegroundColor());
    }

    /**
     * Check if the edge style with 3 labels match to the expected style (Sirius and GMF verification).
     * 
     * @param name
     *            The name (see the label) of the EditPart to check.
     * @param type
     *            The type of the EditPart to check.
     * @param expected
     *            The expected node style.
     */
    private void assertEdgeStyle(String name, Class<? extends EditPart> type, ExpectedEdgeStyle expected) {
        SWTBotGefEditPart botGefEditPart = editor.getEditPart(name, type);
        IGraphicalEditPart editPart = (IGraphicalEditPart) botGefEditPart.part();
        View model = (View) editPart.getModel();

        // GMF Style
        FontStyle shapeStyle = (FontStyle) model.getStyles().stream() //
                .filter(FontStyle.class::isInstance) //
                .map(FontStyle.class::cast) //
                .findAny() //
                .orElseThrow();
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected bold status.", expected.gmf.isBold, shapeStyle.isBold());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected gmf italic status.", expected.gmf.isItalic, shapeStyle.isItalic());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected gmf strike through status.", expected.gmf.isStrikeThrough, shapeStyle.isStrikeThrough());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected gmf underline status.", expected.gmf.isUnderline, shapeStyle.isUnderline());
        assertEquals(name + "(" + type.getSimpleName() + "): wrong expected gmf font color.", expected.gmf.color.toInteger(), shapeStyle.getFontColor());

        EdgeStyle siriusStyle = (EdgeStyle) ((DDiagramElement) editPart.resolveSemanticElement()).getStyle();
        assertSiriusFontStyle(name + "(" + type.getSimpleName() + "), begin label", siriusStyle.getBeginLabelStyle(), expected.begin);
        assertSiriusFontStyle(name + "(" + type.getSimpleName() + "), center label", siriusStyle.getCenterLabelStyle(), expected.middle);
        assertSiriusFontStyle(name + "(" + type.getSimpleName() + "), end label", siriusStyle.getEndLabelStyle(), expected.end);
    }

    public void testCopyBoldLabelToNodeLabel() {
        openDiagramComplex();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass5", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
    }

    public void testCopyBoldNodeLabelToNode() {
        openDiagramComplex();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass5", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
    }

    public void testCopyBoldNodeToNodeLabel() {
        openDiagramComplex();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass5", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
    }

    public void testCopyBoldNodeToNode() {
        openDiagramComplex();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass5", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass5", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), RED_LEGACY));
    }

    public void testCopyDefaultNodeLabelToNodeLabel() {
        openDiagramComplex();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass4", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
    }

    public void testCopyDefaultNodeLabelToNode() {
        openDiagramComplex();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass4", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
    }

    public void testCopyDefaultNodeToNodeLabel() {
        openDiagramComplex();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass4", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
    }

    public void testCopyDefaultNodeToNode() {
        openDiagramComplex();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("NewEClass4", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass4", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
    }

    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyDefaultEdgeToNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference2- CENTER", DEdgeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }

    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyDefaultLabelEdgeToNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference2- CENTER", DEdgeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }

    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyDefaultLabelEdgeToLabelNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference2- CENTER", DEdgeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }

    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyDefaultEdgeToLabelNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference2- CENTER", DEdgeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference2- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(false, false, false, false, BLACK),
                new ExpectedFontStyle(false, false, true, false, DARK_ORANGE), new ExpectedFontStyle(true, true, true, true, LIGHT_BLUE), new ExpectedFontStyle(false, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }

    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyBoldEdgeToNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference1- CENTER", DEdgeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }


    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyBoldLabelEdgeToNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference1- BEGIN", DEdgeBeginNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }

    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyBoldEdgeToLabelNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference1- CENTER", DEdgeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }

    // Note: Copy Paste Style from edge to node or from edge to container doen't work for the moment
    public void testCopyBoldLabelEdgeToLabelNode() {
        openDiagramComplex();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));

        performCopy("Reference1- BEGIN", DEdgeBeginNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass2", DNodeNameEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertEdgeStyle("Reference1- CENTER", DEdgeEditPart.class, new ExpectedEdgeStyle(new ExpectedFontStyle(true, false, false, false, BLACK),
                new ExpectedFontStyle(true, false, false, false, DARK_ORANGE), new ExpectedFontStyle(true, false, false, false, LIGHT_BLUE), new ExpectedFontStyle(true, false, false, false, BLACK)));
        assertNodeStyle("NewEClass2", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), WHITE));
    }

    private void testCopyDefaultStyleRefresh(String sourceContainer, String targetContainer, ExpectedContainerStyle sourceStyle, ExpectedContainerStyle targetStyle) {
        openDiagramSimple();

        assertContainerStyle(sourceContainer, DNodeListEditPart.class, sourceStyle);
        assertContainerStyle(targetContainer, DNodeListEditPart.class, targetStyle);

        performCopy(sourceContainer, DNodeListEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste(targetContainer, DNodeListEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertContainerStyle(sourceContainer, DNodeListEditPart.class, sourceStyle);
        assertContainerStyle(targetContainer, DNodeListEditPart.class, sourceStyle);

        editor.click(4, 4); // select diagram background
        SWTBotUtils.waitAllUiEvents();
        editor.refresh();
        SWTBotUtils.waitAllUiEvents();

        assertContainerStyle(sourceContainer, DNodeListEditPart.class, sourceStyle);
        assertContainerStyle(targetContainer, DNodeListEditPart.class, sourceStyle);

        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();

        openDiagramSimple();

        assertContainerStyle(sourceContainer, DNodeListEditPart.class, sourceStyle);
        assertContainerStyle(targetContainer, DNodeListEditPart.class, sourceStyle);
    }

    public void testCopyDefaultStyleToDefaultAndRefresh1() {
        testCopyDefaultStyleRefresh("NewEnum1", "NewEClass2", new ExpectedContainerStyle(DEFAULT_FONT, YELLOW, WHITE), new ExpectedContainerStyle(DEFAULT_FONT, WHITE, LIGHT_GREY));
    }

    public void testCopyDefaultStyleToDefaultAndRefresh2() {
        testCopyDefaultStyleRefresh("NewDataType2", "NewEClass2", new ExpectedContainerStyle(DEFAULT_FONT, LIGHT_BLUE, WHITE), new ExpectedContainerStyle(DEFAULT_FONT, WHITE, LIGHT_GREY));
    }

    public void testCopyBoldToDefaultAndRefresh() {
        var boldItalic = new ExpectedFontStyle(true, true, false, false, BLACK);
        testCopyDefaultStyleRefresh("NewEnum2", "NewEClass2", new ExpectedContainerStyle(boldItalic, YELLOW, WHITE), new ExpectedContainerStyle(DEFAULT_FONT, WHITE, LIGHT_GREY));
    }

    public void testCopyGreyBGToDefaultAndRefresh() {
        testCopyDefaultStyleRefresh("NewDataType3", "NewEClass2", new ExpectedContainerStyle(DEFAULT_FONT, LIGHT_GREY, LIGHT_GREY), new ExpectedContainerStyle(DEFAULT_FONT, WHITE, LIGHT_GREY));
    }

    public void testCopyDefaultStyleToBoldAndRefresh() {
        var boldItalic = new ExpectedFontStyle(true, true, false, false, BLACK);
        testCopyDefaultStyleRefresh("NewDataType2", "NewEnum2", new ExpectedContainerStyle(DEFAULT_FONT, LIGHT_BLUE, WHITE), new ExpectedContainerStyle(boldItalic, YELLOW, WHITE));
    }

    public void testCopyDefaultStyleToGreyBGAndRefresh() {
        testCopyDefaultStyleRefresh("NewDataType2", "NewDataType3", new ExpectedContainerStyle(DEFAULT_FONT, LIGHT_BLUE, WHITE), new ExpectedContainerStyle(DEFAULT_FONT, LIGHT_GREY, LIGHT_GREY));
    }

    public void testCopyBoldToGreyBGAndRefresh() {
        var boldItalic = new ExpectedFontStyle(true, true, false, false, BLACK);
        testCopyDefaultStyleRefresh("NewEnum2", "NewDataType3", new ExpectedContainerStyle(boldItalic, YELLOW, WHITE), new ExpectedContainerStyle(DEFAULT_FONT, LIGHT_GREY, LIGHT_GREY));
    }

    public void testCopyGreyBGToBoldAndRefresh() {
        var boldItalic = new ExpectedFontStyle(true, true, false, false, BLACK);
        testCopyDefaultStyleRefresh("NewDataType3", "NewEnum2", new ExpectedContainerStyle(DEFAULT_FONT, LIGHT_GREY, LIGHT_GREY), new ExpectedContainerStyle(boldItalic, YELLOW, WHITE));
    }

    // Node -> Container
    public void testCopyDefaultNodeToDefaultContainer() {
        openDiagramComplex();

        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE, LIGHT_GREY));

        performCopy("NewEClass6", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("newPackage2", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY, LIGHT_GREY));
    }

    public void testCopyStylishNodeToDefaultContainer() {
        openDiagramComplex();

        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE, LIGHT_GREY));

        performCopy("NewEClass3", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("newPackage2", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY, LIGHT_GREY));
    }

    public void testCopyStylishNodeToStylishContainer() {
        openDiagramComplex();

        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE, LIGHT_GREY));

        performCopy("NewEClass3", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("newPackage1", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY, LIGHT_GREY));
    }

    public void testCopyDefaultNodeToStylishContainer() {
        openDiagramComplex();

        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE, LIGHT_GREY));

        performCopy("NewEClass6", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("newPackage1", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));
        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY, LIGHT_GREY));
    }

    // Container -> Node
    public void testCopyDefaultContainerToDefaultNode() {
        openDiagramComplex();

        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));

        performCopy("newPackage2", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass6", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE));
    }

    public void testCopyDefaultContainerToStylishNode() {
        openDiagramComplex();

        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY));

        performCopy("newPackage2", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass3", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertContainerStyle("newPackage2", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, false, true, false, BLACK), BLUE));
    }

    public void testCopyStylishContainerToStylishNode() {
        openDiagramComplex();

        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, true, false, false, BLACK), GREY));

        performCopy("newPackage1", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass3", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass3", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE));
    }

    public void testCopyStylishContainerToDefaultNode() {
        openDiagramComplex();

        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(false, true, false, false, BLACK), GREY));

        performCopy("newPackage1", DNodeContainerEditPart.class);
        SWTBotUtils.waitAllUiEvents();
        performPaste("NewEClass6", DNodeEditPart.class);
        SWTBotUtils.waitAllUiEvents();

        assertContainerStyle("newPackage1", DNodeContainerEditPart.class, new ExpectedContainerStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE, LIGHT_GREY));
        assertNodeStyle("NewEClass6", DNodeEditPart.class, new ExpectedNodeStyle(new ExpectedFontStyle(true, false, true, false, RED), BLUE));
    }
}
