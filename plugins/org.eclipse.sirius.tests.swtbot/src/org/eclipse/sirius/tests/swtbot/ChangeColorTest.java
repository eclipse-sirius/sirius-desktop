/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class to check the behavior of the ColorPalettePopup invoked when changing the font color of an element, the
 * line color, or the fill color. See issue https://github.com/eclipse-sirius/sirius-desktop/issues/152.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class ChangeColorTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String ELEMENT_NAME = "EPackage1";

    private static final RGB[] BASIC_COLORS = { new RGB(0, 0, 0), new RGB(209, 209, 209), new RGB(255, 255, 255), new RGB(239, 41, 41), new RGB(252, 175, 62), new RGB(252, 233, 79),
            new RGB(138, 226, 52), new RGB(102, 204, 255), new RGB(0, 51, 128), new RGB(128, 0, 128)};

    private static final RGB[] ALL_SUGGESTED_COLORS = { new RGB(0, 0, 0), new RGB(96, 96, 96), new RGB(180, 180, 180), new RGB(255, 255, 255), new RGB(252, 82, 82), new RGB(128, 64, 0),
            new RGB(255, 134, 13), new RGB(255, 255, 0), new RGB(49, 217, 0), new RGB(0, 202, 101), new RGB(60, 255, 250), new RGB(47, 198, 255), new RGB(145, 56, 241), new RGB(255, 66, 255),
            new RGB(247, 51, 85), };

    private static final String FILL_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getFillStyle_FillColor());

    private static final String LINE_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getLineStyle_LineColor());

    private static final String FONT_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getFontStyle_FontColor());

    private static final String DATA_UNIT_DIR = "data/unit/style/github-152/";

    private static final String MODEL = "github-152.ecore";

    private static final String VSM = "github-152.odesign";

    private static final String REPRESENTATION = "github-152.aird";

    protected UILocalSession localSession;

    protected SWTBotTreeItem rootEPackage;

    protected SWTBotTreeItem mainEPackage;

    private SWTBotGefEditPart selection;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, VSM, REPRESENTATION);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, REPRESENTATION);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "github-152", "github-152", DDiagram.class);
        selection = editor.getEditPart(ELEMENT_NAME, DNodeContainerEditPart.class);
        editor.select(selection);
    }

    public void testChangeFillCustomColor() {
        changeCustomColor(FILL_COLOR_PROPERTY_ID, new RGB(255, 0, 0));
    }

    public void testChangeLineCustomColor() {
        changeCustomColor(LINE_COLOR_PROPERTY_ID, new RGB(0, 255, 0));
    }

    public void testChangeFontCustomColor() {
        changeCustomColor(FONT_COLOR_PROPERTY_ID, new RGB(0, 0, 255));
    }

    private void changeCustomColor(String propertyId, RGB alreadyExistingCustomColor) {
        String alreadyExistingCustomColorString = ColorManager.getDefault().rgbToString(alreadyExistingCustomColor);
        RGB originalColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);

        // Open "Custom Colors" dialog and click OK.
        SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        clickOK(customColorsDialogShell);

        // Check that the color of the element has not been updated since we didn't select any color.
        RGB newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(originalColor, newColor);

        // Open "Custom Colors" dialog, select a color and click Cancel.
        colorPaletteShell = changeColorMenu(propertyId);
        customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        customColorsDialogShell.bot().buttonWithTooltip(alreadyExistingCustomColorString).click();
        clickCancel(customColorsDialogShell);

        // Check that the color of the element has not been updated since we click on Cancel
        newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(originalColor, newColor);

        // Open "Custom Colors" dialog, select a color and click OK.
        colorPaletteShell = changeColorMenu(propertyId);
        customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        customColorsDialogShell.bot().buttonWithTooltip(alreadyExistingCustomColorString).click();
        clickOK(customColorsDialogShell);

        // Check that the color of the element has been updated.
        newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertNotEquals(originalColor, newColor);
        assertEquals(alreadyExistingCustomColor, newColor);
    }

    public void testRemoveFillCustomColor() {
        removeCustomColor(FILL_COLOR_PROPERTY_ID, new RGB(255, 0, 0));
    }

    public void testRemoveLineCustomColor() {
        removeCustomColor(LINE_COLOR_PROPERTY_ID, new RGB(0, 255, 0));
    }

    public void testRemoveFontCustomColor() {
        removeCustomColor(FONT_COLOR_PROPERTY_ID, new RGB(0, 0, 255));
    }

    private void removeCustomColor(String propertyId, RGB alreadyExistingCustomColor) {
        String alreadyExistingCustomColorString = ColorManager.getDefault().rgbToString(alreadyExistingCustomColor);
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(localSession.getOpenedSession(), List.of((IGraphicalEditPart) selection.part()),
                propertyId);
        RGB originalColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);

        // Remove a custom color and click on Cancel.
        SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        customColorsDialogShell.bot().buttonWithTooltip(alreadyExistingCustomColorString).click();
        customColorsDialogShell.bot().button(Messages.ColorSelectionDialog_removeButtonLabel).click();
        clickCancel(customColorsDialogShell);

        // Check that the color of the element didn't changed since we clicked on Cancel.
        RGB newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(originalColor, newColor);
        assertFalse(colorCategoryManager.getCustomColors().isEmpty());

        // Remove a custom color and click on OK
        colorPaletteShell = changeColorMenu(propertyId);
        customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        customColorsDialogShell.bot().buttonWithTooltip(alreadyExistingCustomColorString).click();
        customColorsDialogShell.bot().button(Messages.ColorSelectionDialog_removeButtonLabel).click();
        clickOK(customColorsDialogShell);

        // Check that the color of the element didn't changed since the deletion of a color is not interpreted as a
        // selection. Check also that the custom color have properly been removed.
        newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(originalColor, newColor);
        // localSession.save();
        assertTrue(colorCategoryManager.getCustomColors().isEmpty());
    }

    public void testAddFillCustomColorAndClickOnPalette() {
        addCustomColorAndClickOnPalette(FILL_COLOR_PROPERTY_ID, new RGB(68, 142, 125));
    }

    public void testAddLineCustomColorAndClickOnPalette() {
        addCustomColorAndClickOnPalette(LINE_COLOR_PROPERTY_ID, new RGB(68, 142, 125));
    }

    public void testAddFontCustomColorAndClickOnPalette() {
        addCustomColorAndClickOnPalette(FONT_COLOR_PROPERTY_ID, new RGB(68, 142, 125));
    }

    private void addCustomColorAndClickOnPalette(String propertyId, RGB newCustomColor) {
        String newCustomColorString = ColorManager.getDefault().rgbToString(newCustomColor);
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(localSession.getOpenedSession(), List.of((IGraphicalEditPart) selection.part()),
                propertyId);
        RGB originalColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);

        // Change the list of "Custom Colors"
        List<RGB> newCustomColors = List.of(newCustomColor);
        colorCategoryManager.setCustomColors(newCustomColors);

        // Check that the new custom color can be used in the popup and that the color is properly applied.
        SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
        colorPaletteShell.bot().buttonWithTooltip(newCustomColorString).click();
        RGB newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertNotEquals(originalColor, newColor);
        assertEquals(newCustomColor, newColor);

        // Check the new Custom color has properly been added in "Custom colors", in the "Custom Colors" Dialog.
        colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        customColorsDialogShell.bot().buttonWithTooltip(newCustomColorString).click();
        clickCancel(customColorsDialogShell);
    }

    public void testModifyFillSuggestedColors() {
        applyAndModifySuggestedColors(FILL_COLOR_PROPERTY_ID);
    }

    public void testModifyLineSuggestedColors() {
        applyAndModifySuggestedColors(LINE_COLOR_PROPERTY_ID);
    }

    public void testModifyFontSuggestedColors() {
        applyAndModifySuggestedColors(FONT_COLOR_PROPERTY_ID);
    }

    private void applyAndModifySuggestedColors(String propertyId) {
        RGB black = ALL_SUGGESTED_COLORS[0];
        RGB darkGray = ALL_SUGGESTED_COLORS[1];
        RGB lightGray = ALL_SUGGESTED_COLORS[2];
        String blackString = ColorManager.getDefault().rgbToString(black);
        String darkGrayString = ColorManager.getDefault().rgbToString(darkGray);
        String lightGrayString = ColorManager.getDefault().rgbToString(lightGray);
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(localSession.getOpenedSession(), List.of((IGraphicalEditPart) selection.part()),
                propertyId);
        RGB originalColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);

        // Double-click on 3 colors in "All suggestions" group to add them to "Displayed suggestions" group.
        SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell suggestedColorsDialogShell = invokeSuggestedColorsDialog(colorPaletteShell);
        SWTBotButton buttonColor = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(blackString, Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel);
        doubleClickButton(buttonColor);
        buttonColor = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(darkGrayString, Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel);
        doubleClickButton(buttonColor);
        buttonColor = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(lightGrayString, Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel);
        doubleClickButton(buttonColor);
        clickOK(suggestedColorsDialogShell);

        // Check the 3 new colors have properly been added, and the last double-clicked color has been apply on the
        // element
        assertArrayEquals(new RGB[] { black, darkGray, lightGray }, colorCategoryManager.getSuggestedColors().toArray());
        RGB newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertNotEquals(originalColor, newColor);
        assertEquals(lightGray, newColor);

        // Check that one of the new color in the "Suggested" color category in the popup can be applied
        colorPaletteShell = changeColorMenu(propertyId);
        colorPaletteShell.bot().buttonWithTooltip(darkGrayString).click();
        newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(darkGray, newColor);

        // Double-click on the 3 colors of the "Displayed suggestions" group to remove these colors
        colorPaletteShell = changeColorMenu(propertyId);
        suggestedColorsDialogShell = invokeSuggestedColorsDialog(colorPaletteShell);
        buttonColor = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(lightGrayString, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        doubleClickButton(buttonColor);
        buttonColor = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(darkGrayString, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        doubleClickButton(buttonColor);
        buttonColor = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(blackString, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        doubleClickButton(buttonColor);
        clickOK(suggestedColorsDialogShell);

        // Check that double-clicked colors in "Displayed suggestions" have properly been removed and the last
        // double-clicked color has been apply on the element
        assertTrue(colorCategoryManager.getSuggestedColors().isEmpty());
        newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(black, newColor);
    }

    public void testApplyAllBasicFillColors() {
        applyAllBasicColors(FILL_COLOR_PROPERTY_ID);
    }

    public void testApplyAllBasicLineColors() {
        applyAllBasicColors(LINE_COLOR_PROPERTY_ID);
    }

    public void testApplyAllBasicFontColors() {
        applyAllBasicColors(FONT_COLOR_PROPERTY_ID);
    }

    private void applyAllBasicColors(String propertyId) {
        // Select all pre-defined basic colors and check that the color of the selected element is updated
        for (RGB basicColor : BASIC_COLORS) {
            String colorString = ColorManager.getDefault().rgbToString(basicColor);
            SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
            colorPaletteShell.bot().buttonWithTooltip(colorString).click();
            RGB newColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
            assertEquals(basicColor, newColor);
        }
    }

    public void testReorderFillCustomColor() {
        reorderCustomColors(FILL_COLOR_PROPERTY_ID);
    }

    public void testReorderLineCustomColor() {
        reorderCustomColors(LINE_COLOR_PROPERTY_ID);
    }

    public void testReorderFontCustomColor() {
        reorderCustomColors(FONT_COLOR_PROPERTY_ID);
    }

    private void reorderCustomColors(String propertyId) {
        RGB red = new RGB(255, 0, 0);
        RGB green = new RGB(0, 255, 0);
        RGB blue = new RGB(0, 0, 255);
        String redString = ColorManager.getDefault().rgbToString(red);
        String greenString = ColorManager.getDefault().rgbToString(green);
        String blueString = ColorManager.getDefault().rgbToString(blue);
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(localSession.getOpenedSession(), List.of((IGraphicalEditPart) selection.part()),
                propertyId);

        // Change the list of "Custom Colors"
        List<RGB> newCustomColors = List.of(red, green, blue);
        colorCategoryManager.setCustomColors(newCustomColors);

        // Reorder "red, green, blue" in "blue, green, red"
        SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        SWTBotButton colorButton1 = customColorsDialogShell.bot().buttonWithTooltipInGroup(redString, Messages.ColorSelectionDialog_groupAllCustomColorsLabel);
        SWTBotButton colorButton2 = customColorsDialogShell.bot().buttonWithTooltipInGroup(blueString, Messages.ColorSelectionDialog_groupAllCustomColorsLabel);
        colorButton1.dragAndDrop(colorButton2);
        clickOK(customColorsDialogShell);
        assertArrayEquals(new RGB[] { blue, green, red }, colorCategoryManager.getCustomColors().toArray());

        // Reorder "blue, green, red" in "green, blue, red"
        colorPaletteShell = changeColorMenu(propertyId);
        customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        colorButton1 = customColorsDialogShell.bot().buttonWithTooltipInGroup(greenString, Messages.ColorSelectionDialog_groupAllCustomColorsLabel);
        colorButton2 = customColorsDialogShell.bot().buttonWithTooltipInGroup(blueString, Messages.ColorSelectionDialog_groupAllCustomColorsLabel);
        colorButton1.dragAndDrop(colorButton2);
        clickOK(customColorsDialogShell);
        assertArrayEquals(new RGB[] { green, blue, red }, colorCategoryManager.getCustomColors().toArray());
    }

    public void testDragAndDropFillSuggestedColors() {
        dragAndDropSuggestedColors(FILL_COLOR_PROPERTY_ID);
    }

    public void testDragAndDropLineSuggestedColors() {
        dragAndDropSuggestedColors(LINE_COLOR_PROPERTY_ID);
    }

    public void testDragAndDropFontSuggestedColors() {
        dragAndDropSuggestedColors(FONT_COLOR_PROPERTY_ID);
    }

    private void dragAndDropSuggestedColors(String propertyId) {
        RGB black = ALL_SUGGESTED_COLORS[0];
        RGB darkGray = ALL_SUGGESTED_COLORS[1];
        RGB lightGray = ALL_SUGGESTED_COLORS[2];
        String blackString = ColorManager.getDefault().rgbToString(black);
        String darkGrayString = ColorManager.getDefault().rgbToString(darkGray);
        String lightGrayString = ColorManager.getDefault().rgbToString(lightGray);
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(localSession.getOpenedSession(), List.of((IGraphicalEditPart) selection.part()),
                propertyId);

        // Change the list of "Suggested Colors"
        List<RGB> newSuggestedColors = List.of(black, darkGray, lightGray);
        colorCategoryManager.setSuggestedColors(newSuggestedColors);

        // Reorder "black, darkGray, lightGray" in "lightGray, darkGray, black"
        SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell suggestedColorsDialogShell = invokeSuggestedColorsDialog(colorPaletteShell);
        SWTBotButton colorButton1 = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(blackString, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        SWTBotButton colorButton2 = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(lightGrayString, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        colorButton1.dragAndDrop(colorButton2);
        clickOK(suggestedColorsDialogShell);
        assertArrayEquals(new RGB[] { lightGray, darkGray, black }, colorCategoryManager.getSuggestedColors().toArray());

        // Add a new color "white" from "All suggestions" at the second place of "Displayed suggestions": "lightGray,
        // white, darkGray, black"
        RGB white = ALL_SUGGESTED_COLORS[3];
        String whiteString = ColorManager.getDefault().rgbToString(white);
        colorPaletteShell = changeColorMenu(propertyId);
        suggestedColorsDialogShell = invokeSuggestedColorsDialog(colorPaletteShell);
        colorButton1 = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(darkGrayString, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        colorButton2 = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(whiteString, Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel);
        colorButton2.dragAndDrop(colorButton1);
        clickOK(suggestedColorsDialogShell);
        assertArrayEquals(new RGB[] { lightGray, white, darkGray, black }, colorCategoryManager.getSuggestedColors().toArray());

        // Remove color "white" by DnD it from "Displayed suggestions" to any other color in "All suggestions".
        // Remaining colors: "lightGray, darkGray, black"
        colorPaletteShell = changeColorMenu(propertyId);
        suggestedColorsDialogShell = invokeSuggestedColorsDialog(colorPaletteShell);
        colorButton1 = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(whiteString, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        colorButton2 = suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(lightGrayString, Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel);
        colorButton1.dragAndDrop(colorButton2);
        clickOK(suggestedColorsDialogShell);
        assertArrayEquals(new RGB[] { lightGray, darkGray, black }, colorCategoryManager.getSuggestedColors().toArray());
    }

    public void testAddAndUseFillLastUsedColors() {
        addAndUseLastUsedColors(FILL_COLOR_PROPERTY_ID, new RGB(255, 0, 0));
    }

    public void testAddAndUseLineLastUsedColors() {
        addAndUseLastUsedColors(LINE_COLOR_PROPERTY_ID, new RGB(0, 255, 0));
    }

    public void testAddAndUseFontLastUsedColors() {
        addAndUseLastUsedColors(FONT_COLOR_PROPERTY_ID, new RGB(0, 0, 255));
    }

    private void addAndUseLastUsedColors(String propertyId, RGB alreadyExistingCustomColor) {
        RGB yellow = ALL_SUGGESTED_COLORS[7];
        String yellowString = ColorManager.getDefault().rgbToString(yellow);
        RGB purple = BASIC_COLORS[9];
        String purpleString = ColorManager.getDefault().rgbToString(purple);
        String alreadyExistingCustomColorString = ColorManager.getDefault().rgbToString(alreadyExistingCustomColor);
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(localSession.getOpenedSession(), List.of((IGraphicalEditPart) selection.part()),
                propertyId);

        // Click on a custom color to add at the first place of "Last used" colors.
        SWTBotShell colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell customColorsDialogShell = invokeCustomColorsDialog(colorPaletteShell);
        customColorsDialogShell.bot().buttonWithTooltipInGroup(alreadyExistingCustomColorString, Messages.ColorSelectionDialog_groupAllCustomColorsLabel).click();
        clickOK(customColorsDialogShell);
        assertEquals(alreadyExistingCustomColor, colorCategoryManager.getLastUsedColors().get(0));

        // Click on a suggested color to add it at the first place of "Last used" colors.
        colorPaletteShell = changeColorMenu(propertyId);
        SWTBotShell suggestedColorsDialogShell = invokeSuggestedColorsDialog(colorPaletteShell);
        suggestedColorsDialogShell.bot().buttonWithTooltipInGroup(yellowString, Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel).click();
        clickOK(suggestedColorsDialogShell);
        assertEquals(yellow, colorCategoryManager.getLastUsedColors().get(0));
        assertEquals(alreadyExistingCustomColor, colorCategoryManager.getLastUsedColors().get(1));

        // Click on a basic color to add it at the first place of "Last used" colors.
        colorPaletteShell = changeColorMenu(propertyId);
        colorPaletteShell.bot().buttonWithTooltip(purpleString).click();
        assertEquals(purple, colorCategoryManager.getLastUsedColors().get(0));
        assertEquals(yellow, colorCategoryManager.getLastUsedColors().get(1));
        assertEquals(alreadyExistingCustomColor, colorCategoryManager.getLastUsedColors().get(2));
        RGB newElementColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(purple, newElementColor);

        // Click on a last used color to change the color of the element and change the order of "Last used" colors: the
        // color clicked is at the first place, without duplicate.
        colorPaletteShell = changeColorMenu(propertyId);
        colorPaletteShell.bot().buttonWithTooltip(yellowString).click();
        assertEquals(yellow, colorCategoryManager.getLastUsedColors().get(0));
        assertEquals(purple, colorCategoryManager.getLastUsedColors().get(1));
        assertEquals(alreadyExistingCustomColor, colorCategoryManager.getLastUsedColors().get(2));
        newElementColor = getPropertyColorForEditPart((IGraphicalEditPart) selection.part(), propertyId);
        assertEquals(yellow, newElementColor);
    }

    private void doubleClickButton(SWTBotButton swtBotButton) {
        swtBotButton.click();
        Display.getDefault().syncExec(() -> {
            Event event = new Event();
            event.time = (int) System.currentTimeMillis();
            event.widget = swtBotButton.widget;
            event.display = swtBotButton.display;
            event.x = swtBotButton.widget.getBounds().x;
            event.y = swtBotButton.widget.getBounds().y;
            event.button = 1;
            event.stateMask = SWT.NONE;
            event.count = 2;
            swtBotButton.widget.notifyListeners(SWT.MouseDoubleClick, event);
        });
    }

    private void clickOK(SWTBotShell customColorsDialogShell) {
        customColorsDialogShell.bot().button("OK").click();
    }

    private void clickCancel(SWTBotShell customColorsDialogShell) {
        customColorsDialogShell.bot().button("Cancel").click();
    }

    private SWTBotShell invokeCustomColorsDialog(SWTBotShell colorPaletteShell) {
        colorPaletteShell.bot().buttonWithTooltip(Messages.ColorPalettePopup_customCategoryMoreButtonTooltip).click();
        SWTBotShell customColorsDialogShell = bot.shell(Messages.ColorSelectionDialog_customColorsDialogTitle);
        return customColorsDialogShell;
    }

    private SWTBotShell invokeSuggestedColorsDialog(SWTBotShell colorPaletteShell) {
        colorPaletteShell.bot().buttonWithTooltip(Messages.ColorPalettePopup_suggestedCategoryMoreButtonTooltip).click();
        SWTBotShell customColorsDialogShell = bot.shell(Messages.ColorSelectionDialog_suggestedColorsDialogTitle);
        return customColorsDialogShell;
    }

    private SWTBotShell changeColorMenu(String propertyId) {
        SWTBotShell colorPaletteShell = null;
        if (FILL_COLOR_PROPERTY_ID.equals(propertyId)) {
            colorPaletteShell = SWTBotSiriusHelper.changeFillColorNavigationBar(bot);
        } else if (FONT_COLOR_PROPERTY_ID.equals(propertyId)) {
            colorPaletteShell = SWTBotSiriusHelper.changeFontColorNavigationBar(bot);
        } else if (LINE_COLOR_PROPERTY_ID.equals(propertyId)) {
            colorPaletteShell = SWTBotSiriusHelper.changeLineColorNavigationBar(bot);
        }
        return colorPaletteShell;
    }

    private RGB getPropertyColorForEditPart(IGraphicalEditPart ep, String propertyId) {
        final EStructuralFeature feature = (EStructuralFeature) PackageUtil.getElement(propertyId);
        Integer colorInteger = (Integer) ep.getStructuralFeatureValue(feature);
        return FigureUtilities.integerToRGB(colorInteger);
    }

}
