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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToggleButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;

import com.google.common.collect.Lists;

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
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }
        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        SWTBotToolbarToggleButton tabbarBoldButton = getTabbarBoldButton();
        SWTBotToolbarToggleButton tabbarItalicButton = getTabbarItalicButton();

        // Verify that the font by default is normal.
        checkNormalFontStyle(myEClassEP);

        // Click on bold and Italic button on tabbar.
        tabbarBoldButton.click();
        tabbarItalicButton = getTabbarItalicButton();
        tabbarItalicButton.click();
        checkBoldItalicFontStyle(myEClassEP);

        // Verify that buttons italic and bold is also selected on property
        // Appearance tab and that cancel custom style is enabled.
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotToggleButton boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        SWTBotToggleButton italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        SWTBotButton cancelCustomStyle = propertiesBot.buttonInGroup("Fonts and Colors:", 4);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), cancelCustomStyle, Lists.newArrayList(true, true), true);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        // Unchecked the bold button and verify that the button bold is also
        // unchecked on property Appearance tab.
        tabbarBoldButton = getTabbarBoldButton();
        tabbarBoldButton.click();
        checkItalicFontStyle(myEClassEP);
        propertiesBot = selectAppearanceTab();
        boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        cancelCustomStyle = propertiesBot.buttonInGroup("Fonts and Colors:", 4);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), cancelCustomStyle, Lists.newArrayList(false, true), true);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        // Unchecked the italic button and verify that the button italic is also
        // unchecked on property Appearance tab and that the button cancel
        // custom style is also disabled.
        tabbarItalicButton = getTabbarItalicButton();
        tabbarItalicButton.click();
        checkNormalFontStyle(myEClassEP);
        propertiesBot = selectAppearanceTab();
        boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        cancelCustomStyle = propertiesBot.buttonInGroup("Fonts and Colors:", 4);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), cancelCustomStyle, Lists.newArrayList(false, false), true);

    }

    /**
     * Test BoldItalic element from appearance section and check that BoldItalic
     * button is selected on tabbar.
     */
    public void testBoldItalicSynchronizationFromAppearanceSection() {
        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotToggleButton boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        SWTBotToggleButton italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        SWTBotButton cancelCustomStyleButton = propertiesBot.buttonInGroup("Fonts and Colors:", 4);

        // Verify that the font by default is normal.
        checkNormalFontStyle(myEClassEP);

        // Click on bold and Italic button on appearance section and check
        // buttons in appearance tab.
        boldButton.click();
        italicButton.click();
        checkBoldItalicFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), cancelCustomStyleButton, Lists.newArrayList(true, true), true);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }

        // Verify that buttons italic and bold is also selected on tabbar
        SWTBotToolbarToggleButton boldButtonTabbar = getTabbarBoldButton();
        SWTBotToolbarToggleButton italicButtonTabbar = getTabbarItalicButton();
        SWTBotToolbarButton resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        checkButtonTabbarChecked(Lists.newArrayList(boldButtonTabbar, italicButtonTabbar), resetStylePropertiesToDefaultValuesButtonFromTabbar, Lists.newArrayList(true, true), true);

        // Unchecked the bold button and verify that the button bold is also
        // unchecked on property Appearance tab.
        propertiesBot = selectAppearanceTab();
        boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        boldButton.click();
        checkItalicFontStyle(myEClassEP);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        // Verify that buttons italic is also selected on tabbar
        boldButtonTabbar = getTabbarBoldButton();
        italicButtonTabbar = getTabbarItalicButton();
        resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        checkButtonTabbarChecked(Lists.newArrayList(boldButtonTabbar, italicButtonTabbar), resetStylePropertiesToDefaultValuesButtonFromTabbar, Lists.newArrayList(false, true), true);

        // Unchecked the italic button and verify that the button italic is also
        // unchecked on property Appearance tab and that the button cancel
        // custom style is also disabled.
        propertiesBot = selectAppearanceTab();
        italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        italicButton.click();
        checkNormalFontStyle(myEClassEP);

        editor.click(new Point(0, 0));
        selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        // Verify that buttons italic is also unselected on tabbar
        boldButtonTabbar = getTabbarBoldButton();
        italicButtonTabbar = getTabbarItalicButton();
        resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        checkButtonTabbarChecked(Lists.newArrayList(boldButtonTabbar, italicButtonTabbar), resetStylePropertiesToDefaultValuesButtonFromTabbar, Lists.newArrayList(false, false), true);
    }
}
