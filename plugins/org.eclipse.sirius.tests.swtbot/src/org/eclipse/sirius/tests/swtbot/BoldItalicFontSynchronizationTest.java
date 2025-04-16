/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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

import java.util.Arrays;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToggleButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;

/**
 * Tests that we can set labels with both "bold" and "italic" styles at the same
 * time. We have three ways to set these styles: from the tabbar, from the
 * property view (Appearance section) and from the context menu. We can not test
 * the context menu with SWTbot as it uses native font dialogs; see VP-2538 for
 * the manual test for this part. For the other two ways (tabbar and property
 * view), this class also tests that they are always in sync.
 * 
 * Test VP-2538
 * 
 * @author jdupont
 */
public class BoldItalicFontSynchronizationTest extends AbstractFontModificationTest {

    /**
     * Test BoldItalic element from Tabbar and check that BoldItalic button is
     * selected for property appearance.
     */
    public void testBoldItalicSynchronizationFromTabbar() {
        doTestBoldItalicSynchronizationFromTabbar("myEClass", DNodeList2EditPart.class);
        doTestBoldItalicSynchronizationFromTabbar("myEClass3", DNodeListEditPart.class);
        doTestBoldItalicSynchronizationFromTabbar("myAttribute", DNodeListElementEditPart.class);
        doTestBoldItalicSynchronizationFromTabbar("myPackage", DNodeContainerEditPart.class);
        doTestBoldItalicSynchronizationFromTabbar("myPackage2", DNodeContainer2EditPart.class);
        doTestBoldItalicSynchronizationFromTabbar("[0..1] newEReference1", DEdgeEditPart.class);
        doTestBoldItalicSynchronizationFromTabbar("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Test BoldItalic element from Tabbar and check that BoldItalic button is
     * selected for property appearance.
     * 
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestBoldItalicSynchronizationFromTabbar(String name, Class<? extends EditPart> type) {
        editor.click(new Point(0, 0));
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);

        // Verify that the font by default is normal.
        checkNormalFontStyle(selectedEditPart);

        // Click on bold and Italic button on tabbar.
        getTabbarBoldButton().click();
        getTabbarItalicButton().click();
        checkBoldItalicFontStyle(selectedEditPart);

        // Verify that buttons italic and bold is also selected on property
        // Appearance tab and that cancel custom style is enabled.
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotToggleButton boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        SWTBotToggleButton italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        SWTBotButton cancelCustomStyle = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), cancelCustomStyle, Arrays.asList(true, true), true);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart(name, type);

        // Unchecked the bold button and verify that the button bold is also
        // unchecked on property Appearance tab.
        getTabbarBoldButton().click();
        checkItalicFontStyle(selectedEditPart);
        propertiesBot = selectAppearanceTab();
        boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        cancelCustomStyle = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), cancelCustomStyle, Arrays.asList(false, true), true);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart(name, type);

        // Unchecked the italic button and verify that the button italic is also
        // unchecked on property Appearance tab and that the button cancel
        // custom style is also disabled.
        getTabbarItalicButton().click();
        checkNormalFontStyle(selectedEditPart);
        propertiesBot = selectAppearanceTab();
        boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        cancelCustomStyle = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), cancelCustomStyle, Arrays.asList(false, false), true);

    }

    /**
     * Test BoldItalic element from appearance section and check that BoldItalic
     * button is selected on tabbar.
     */
    public void testBoldItalicSynchronizationFromAppearanceSection() {
        doTestBoldItalicSynchronizationFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestBoldItalicSynchronizationFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestBoldItalicSynchronizationFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestBoldItalicSynchronizationFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestBoldItalicSynchronizationFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
        doTestBoldItalicSynchronizationFromAppearanceSection("[0..1] newEReference1", DEdgeEditPart.class);
        doTestBoldItalicSynchronizationFromAppearanceSection("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Test BoldItalic element from appearance section and check that BoldItalic
     * button is selected on tabbar.
     * 
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestBoldItalicSynchronizationFromAppearanceSection(String name, Class<? extends EditPart> type) {
        editor.click(new Point(0, 0));
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);

        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotToggleButton boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        SWTBotToggleButton italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        SWTBotButton cancelCustomStyleButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();

        // Verify that the font by default is normal.
        checkNormalFontStyle(selectedEditPart);

        // Click on bold and Italic button on appearance section and check
        // buttons in appearance tab.
        boldButton.click();
        italicButton.click();
        checkBoldItalicFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), cancelCustomStyleButton, Arrays.asList(true, true), true);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart(name, type);

        // Verify that buttons italic and bold is also selected on tabbar
        SWTBotToolbarToggleButton boldButtonTabbar = getTabbarBoldButton();
        SWTBotToolbarToggleButton italicButtonTabbar = getTabbarItalicButton();
        SWTBotToolbarButton resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        checkButtonTabbarChecked(Arrays.asList(boldButtonTabbar, italicButtonTabbar), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(true, true), true);

        // Unchecked the bold button and verify that the button bold is also
        // unchecked on property Appearance tab.
        propertiesBot = selectAppearanceTab();
        boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        boldButton.click();
        checkItalicFontStyle(selectedEditPart);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart(name, type);

        // Verify that buttons italic is also selected on tabbar
        boldButtonTabbar = getTabbarBoldButton();
        italicButtonTabbar = getTabbarItalicButton();
        resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        checkButtonTabbarChecked(Arrays.asList(boldButtonTabbar, italicButtonTabbar), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(false, true), true);

        // Unchecked the italic button and verify that the button italic is also
        // unchecked on property Appearance tab and that the button cancel
        // custom style is also disabled.
        propertiesBot = selectAppearanceTab();
        italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        italicButton.click();
        checkNormalFontStyle(selectedEditPart);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart(name, type);

        // Verify that buttons italic is also unselected on tabbar
        boldButtonTabbar = getTabbarBoldButton();
        italicButtonTabbar = getTabbarItalicButton();
        resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        checkButtonTabbarChecked(Arrays.asList(boldButtonTabbar, italicButtonTabbar), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(false, false), true);
    }
}
