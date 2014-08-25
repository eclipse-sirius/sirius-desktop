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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToggleButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

/**
 * Tests the label font modifications.
 * 
 * @author mporhel
 */
public class LabelFontModificationsTest extends AbstractFontModificationTest {

    private static final Predicate<SWTBotGefEditPart> NORMAL_FONT_STATE_PREDICATE = new Predicate<SWTBotGefEditPart>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(SWTBotGefEditPart input) {
            try {
                checkNormalFontStyle(input);
            } catch (AssertionError e) {
                return false;
            }
            return true;
        }
    };

    private static final Predicate<SWTBotGefEditPart> BOLD_FONT_STATE_PREDICATE = new Predicate<SWTBotGefEditPart>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(SWTBotGefEditPart input) {
            try {
                checkBoldFontStyle(input);
                return true;
            } catch (AssertionError e) {
                return false;
            }
        }
    };

    private static final Predicate<SWTBotGefEditPart> ITALIC_FONT_STATE_PREDICATE = new Predicate<SWTBotGefEditPart>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(SWTBotGefEditPart input) {
            try {
                checkItalicFontStyle(input);
                return true;
            } catch (AssertionError e) {
                return false;
            }
        }
    };

    private static final Predicate<SWTBotGefEditPart> UNDERLINE_FONT_STATE_PREDICATE = new Predicate<SWTBotGefEditPart>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(SWTBotGefEditPart input) {
            try {
                checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, FontFormat.NORMAL, true, false);
                return true;
            } catch (AssertionError e) {
                return false;
            }
        }
    };

    private static final Predicate<SWTBotGefEditPart> STRIKE_FONT_STATE_PREDICATE = new Predicate<SWTBotGefEditPart>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(SWTBotGefEditPart input) {
            try {
                checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, FontFormat.NORMAL, false, true);
                return true;
            } catch (AssertionError e) {
                return false;
            }
        }
    };

    private static final Predicate<SWTBotGefEditPart> STATE_WHEN_LABEL_COLOR_IS_CHANGED_PREDICATE = new Predicate<SWTBotGefEditPart>() {

        public boolean apply(SWTBotGefEditPart input) {
            try {
                checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, FontFormat.NORMAL, false, false, null, -1, 10011046);
                return true;
            } catch (AssertionError e) {
                return false;
            }
        }
    };

    private final Predicate<SWTBotGefEditPart> STATE_WHEN_LABEL_COLOR_IS_UNCHANGED_PREDICATE = new Predicate<SWTBotGefEditPart>() {

        public boolean apply(SWTBotGefEditPart input) {
            return NORMAL_FONT_STATE_PREDICATE.apply(input) && !STATE_WHEN_LABEL_COLOR_IS_CHANGED_PREDICATE.apply(input);
        }
    };

    /**
     * Ensures that changing the color of a label from the appearance page works
     * as expected (and also tests that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeLabelColorFromAppearanceSection() throws Exception {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        doTestStyleCustomizationThroughColorSelectionFromAppearanceSection(selectedEditPart, "Fonts and Colors:", 0, STATE_WHEN_LABEL_COLOR_IS_UNCHANGED_PREDICATE,
                STATE_WHEN_LABEL_COLOR_IS_CHANGED_PREDICATE);
    }

    /**
     * Ensures that changing a label as bold from tabbar works as expected (and
     * also tests that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBoldFromToolbar() throws Exception {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }

        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        doTestStyleCustomizationThroughTabbar(myEClassEP, "Bold Font Style", NORMAL_FONT_STATE_PREDICATE, BOLD_FONT_STATE_PREDICATE);
    }

    /**
     * Ensures that changing a label as stroke from tabbar works as expected
     * (and also tests that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testItalicFromToolbar() throws Exception {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }

        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        doTestStyleCustomizationThroughTabbar(myEClassEP, "Italic Font Style", NORMAL_FONT_STATE_PREDICATE, ITALIC_FONT_STATE_PREDICATE);
    }

    /**
     * Ensures that changing the font color of a label from the tabbar works as
     * expected (and also tests that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeLabelColorFromTabbar() throws Exception {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        final Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate = new Predicate<SWTBotGefEditPart>() {

            public boolean apply(SWTBotGefEditPart input) {
                try {
                    checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, FontFormat.NORMAL, false, false, null, -1, 8905185);
                    return true;
                } catch (AssertionError e) {
                    return false;
                }
            }

        };
        Predicate<SWTBotGefEditPart> initialStatePredicate = Predicates.not(stateWhenButtonIsCheckedPredicate);

        doTestStyleCustomizationThroughColorSelectionFromTabbar(selectedEditPart, "Font Color", initialStatePredicate, stateWhenButtonIsCheckedPredicate, "Yellow");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testItalicAndBoldFromToolbar() throws Exception {
        // Not available in 4.x specific Tabbar
        if (TestsUtil.isEclipse4xPlatform()) {
            return;
        }

        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        SWTBotToolbarToggleButton boldButton = getTabbarBoldButton();
        SWTBotToolbarToggleButton italicButton = getTabbarItalicButton();
        SWTBotToolbarButton resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();

        // Check initial state
        checkNormalFontStyle(myEClassEP);
        checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(false, false), false);

        // Enable bold and check result
        boldButton = getTabbarBoldButton();
        boldButton.click();
        checkBoldFontStyle(myEClassEP);
        checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(true, false), true);

        // Enable italic and check result
        italicButton = getTabbarItalicButton();
        italicButton.click();
        checkBoldItalicFontStyle(myEClassEP);
        checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(true, true), true);

        // Disable bold and check result
        boldButton = getTabbarBoldButton();
        boldButton.click();
        checkItalicFontStyle(myEClassEP);
        checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(false, true), true);

        // Disable italic and check result
        italicButton = getTabbarItalicButton();
        italicButton.click();
        checkNormalFontStyle(myEClassEP);
        checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(false, false), true);

        // Enable bold and check result
        boldButton = getTabbarBoldButton();
        boldButton.click();
        checkBoldFontStyle(myEClassEP);
        checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(true, false), true);

        // Enable italic and check result
        italicButton = getTabbarItalicButton();
        italicButton.click();
        checkBoldItalicFontStyle(myEClassEP);
        checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(true, true), true);

        // Cancel custom style and check result
        resetStylePropertiesToDefaultValuesButton.click();
        // TODO: re-enable this check once VP-3626 will be fixed
        // checkNormalFontStyle(myEClassEP);
        // checkButtonTabbarChecked(Lists.newArrayList(boldButton,
        // italicButton), resetStylePropertiesToDefaultValuesButton,
        // Lists.newArrayList(false, false), false);
    }

    /**
     * Ensures that changing a label as bold from the appearance page works as
     * expected (and also tests that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBoldFromAppearanceSection() throws Exception {
        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(myEClassEP, "Fonts and Colors:", 0, NORMAL_FONT_STATE_PREDICATE, BOLD_FONT_STATE_PREDICATE, false);
    }

    /**
     * Ensures that changing a label as italic from the appearance page works as
     * expected (and also tests that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testItalicFromAppearanceSection() throws Exception {
        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(myEClassEP, "Fonts and Colors:", 1, NORMAL_FONT_STATE_PREDICATE, ITALIC_FONT_STATE_PREDICATE, false);
    }

    /**
     * Ensures that striking a label from the appearance page works as expected
     * (and also tests that the style is considered as customized).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStrikeFromAppearanceSection() throws Exception {
        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(myEClassEP, "Fonts and Colors:", 3, NORMAL_FONT_STATE_PREDICATE, STRIKE_FONT_STATE_PREDICATE, true);
    }

    /**
     * Ensures that underlining a label from the appearance page works as
     * expected (and also tests that the style is considered as customized).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testUnderlineFromAppearanceSection() throws Exception {
        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(myEClassEP, "Fonts and Colors:", 2, NORMAL_FONT_STATE_PREDICATE, UNDERLINE_FONT_STATE_PREDICATE, true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testItalicAndBoldFromAppearanceSection() throws Exception {
        SWTBotGefEditPart myEClassEP = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);

        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotToggleButton boldButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 0);
        SWTBotToggleButton italicButton = propertiesBot.toggleButtonInGroup("Fonts and Colors:", 1);
        SWTBotButton resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();

        // Check initial state
        checkNormalFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.newArrayList(false, false), false);

        // Enable bold and check result
        boldButton.click();
        checkBoldFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.newArrayList(true, false), true);

        // Enable italic and check result
        italicButton.click();
        checkBoldItalicFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.newArrayList(true, true), true);

        // Disable bold and check result
        boldButton.click();
        checkItalicFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.newArrayList(false, true), true);

        // Disable italic and check result
        italicButton.click();
        checkNormalFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.newArrayList(false, false), true);

        // Enable bold and check result
        boldButton.click();
        checkBoldFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.newArrayList(true, false), true);

        // Enable italic and check result
        italicButton.click();
        checkBoldItalicFontStyle(myEClassEP);
        checkButtonAppearanceChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.newArrayList(true, true), true);

        // Cancel custom style and check result
        resetStylePropertiesToDefaultValuesButtonFromAppearanceTab.click();
        // TODO: re-enable this check once VP-3626 will be fixed
        // checkNormalFontStyle(myEClassEP);
        // checkButtonAppearanceChecked(Lists.newArrayList(boldButton,
        // italicButton),
        // resetStylePropertiesToDefaultValuesButtonFromAppearanceTab,
        // Lists.newArrayList(false, false), false);
    }

    /**
     * Test change to bold for all selected elements, with undo/redo.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBoldAllSelectedElementsFromToolBarWithUndoRedo() throws Exception {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        editor.bot().toolbarDropDownButtonWithTooltip("Select &All").click();

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        SWTBotToolbarToggleButton boldButton = getTabbarBoldButton();
        SWTBotToolbarToggleButton italicButton = getTabbarItalicButton();
        SWTBotToolbarButton resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        boldButton.click();

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        SWTBotGefEditPart myEclass = editor.getEditPart("myEClass", AbstractDiagramListEditPart.class);
        assertNotNull("The requested edit part should not be null", myEclass);

        SWTBotGefEditPart superClass = editor.getEditPart("SuperClass", AbstractDiagramListEditPart.class);
        assertNotNull("The requested edit part should not be null", superClass);

        SWTBotGefEditPart myEclass2 = editor.getEditPart("myEClass2", AbstractDiagramListEditPart.class);
        assertNotNull("The requested edit part should not be null", myEclass2);

        SWTBotGefEditPart newEReference1 = editor.getEditPart("[0..1] newEReference1", AbstractDiagramEdgeEditPart.class);
        assertNotNull("The requested edit part should not be null", newEReference1);

        List<SWTBotGefEditPart> listSwtBotGefEditPart = new ArrayList<SWTBotGefEditPart>();
        listSwtBotGefEditPart.add(myEclass);
        listSwtBotGefEditPart.add(superClass);
        listSwtBotGefEditPart.add(myEclass2);
        listSwtBotGefEditPart.add(newEReference1);

        for (SWTBotGefEditPart swtBotGefEditPart : listSwtBotGefEditPart) {
            checkBoldFontStyle(swtBotGefEditPart);
            checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(true, false), true);
        }

        // Undo the change
        bot.menu("Edit").menu("Undo ").click();
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        for (SWTBotGefEditPart swtBotGefEditPart : listSwtBotGefEditPart) {
            checkNormalFontStyle(swtBotGefEditPart);
            checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(false, false), false);
        }

        // Redo the change
        bot.menu("Edit").menu("Redo ").click();
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        for (SWTBotGefEditPart swtBotGefEditPart : listSwtBotGefEditPart) {
            checkBoldFontStyle(swtBotGefEditPart);
            checkButtonTabbarChecked(Lists.newArrayList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Lists.newArrayList(true, false), true);
        }

        // Cancel style customization
        ((SWTBotToolbarButton) getResetStylePropertiesToDefaultValuesButton(true, true)).click();
        SWTBotUtils.waitAllUiEvents();
        for (SWTBotGefEditPart swtBotGefEditPart : listSwtBotGefEditPart) {
            // TODO: re-enable this check once VP-3626 will be fixed
            // checkNormalFontStyle(swtBotGefEditPart);
            // checkButtonTabbarChecked(Lists.newArrayList(boldButton,
            // italicButton), resetStylePropertiesToDefaultValuesButton,
            // Lists.newArrayList(false, false), false);
        }
    }

    /**
     * Ensures that changing the font size of a label from the appearance page
     * works as expected (and also tests that the style is considered as
     * custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeFontSizeFromAppearanceSection() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
            junit.framework.AssertionFailedError: Reset style properties to default values button should be enabled.
            at junit.framework.Assert.fail(Assert.java:57)
            at junit.framework.Assert.assertTrue(Assert.java:22)
            at junit.framework.TestCase.assertTrue(TestCase.java:192)
            at org.eclipse.sirius.tests.swtbot.AbstractRefreshWithCustomizedStyleTest.checkButtonAppearanceChecked(AbstractRefreshWithCustomizedStyleTest.java:665)
            at org.eclipse.sirius.tests.swtbot.AbstractRefreshWithCustomizedStyleTest.doTestStyleCustomizationThroughComboBoxInAppearanceSection(AbstractRefreshWithCustomizedStyleTest.java:331)
            at org.eclipse.sirius.tests.swtbot.LabelFontModificationsTest.testChangeFontSizeFromAppearanceSection(LabelFontModificationsTest.java:479)
             */
            return;
        }
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate = new Predicate<SWTBotGefEditPart>() {

            public boolean apply(SWTBotGefEditPart input) {
                try {
                    checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, FontFormat.NORMAL, false, false, null, 12, -1);
                    return true;
                } catch (AssertionError e) {
                    return false;
                }
            }

        };

        doTestStyleCustomizationThroughComboBoxInAppearanceSection(selectedEditPart, NORMAL_FONT_STATE_PREDICATE, stateWhenButtonIsCheckedPredicate, 1, "12", false);
    }

    /**
     * Ensures that changing the font of a label from the appearance page works
     * as expected (and also tests that the style is considered as custom).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeFontFromAppearanceSection() throws Exception {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart("myEClass", AbstractDiagramListEditPart.class);
        /*
         * Last found common fonts : Arial, Arial Black, Comic Sans MS, Courier
         * New, DejaVu Sans, DejaVu Sans Mono, DejaVu Serif, Georgia, Impact,
         * Times New Roma, Trebuchet MS, Verdana , Webdings.
         */
        final String modifiedFont = "Courier New";
        Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate = new Predicate<SWTBotGefEditPart>() {

            public boolean apply(SWTBotGefEditPart input) {
                try {
                    checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, FontFormat.NORMAL, false, false, modifiedFont, -1, -1);
                    return true;
                } catch (AssertionError e) {
                    return false;
                }
            }

        };

        doTestStyleCustomizationThroughComboBoxInAppearanceSection(selectedEditPart, NORMAL_FONT_STATE_PREDICATE, stateWhenButtonIsCheckedPredicate, 0, modifiedFont, true);
    }

}
