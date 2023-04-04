/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToggleButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Tests the label font modifications.
 *
 * @author mporhel
 */
public class LabelFontModificationsTest extends AbstractFontModificationTest {
    private static final String BOLD_FONT_STYLE = "Bold Font Style";

    private static final String ITALIC_FONT_STYLE = "Italic Font Style";

    private static final String FONTS_COLORS_GROUP = "Fonts and Colors:";

    private static final Predicate<SWTBotGefEditPart> NORMAL_FONT_STATE_PREDICATE = new Predicate<SWTBotGefEditPart>() {
        /**
         * {@inheritDoc}
         */
        @Override
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
        @Override
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
        @Override
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
        @Override
        public boolean apply(SWTBotGefEditPart input) {
            try {
                List<FontFormat> format = new ArrayList<FontFormat>();
                FontFormatHelper.setFontFormat(format, FontFormat.UNDERLINE_LITERAL);
                checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, format, true, false);
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
        @Override
        public boolean apply(SWTBotGefEditPart input) {
            try {
                List<FontFormat> format = new ArrayList<FontFormat>();
                FontFormatHelper.setFontFormat(format, FontFormat.STRIKE_THROUGH_LITERAL);
                checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, format, false, true);
                return true;
            } catch (AssertionError e) {
                return false;
            }
        }
    };

    private static final Predicate<SWTBotGefEditPart> STATE_WHEN_LABEL_COLOR_IS_CHANGED_TO_GRAY_PREDICATE = new Predicate<SWTBotGefEditPart>() {

        @Override
        public boolean apply(SWTBotGefEditPart input) {
            try {
                // Use same Color as
                // org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ColorPalettePopup.GRAY
                Integer color = FigureUtilities.RGBToInteger(new RGB(209, 209, 209));
                checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, new ArrayList<FontFormat>(), false, false, null, -1, color.intValue());
                return true;
            } catch (AssertionError e) {
                return false;
            }
        }
    };

    private final Predicate<SWTBotGefEditPart> STATE_WHEN_LABEL_COLOR_IS_UNCHANGED_PREDICATE = new Predicate<SWTBotGefEditPart>() {

        @Override
        public boolean apply(SWTBotGefEditPart input) {
            return NORMAL_FONT_STATE_PREDICATE.apply(input) && !STATE_WHEN_LABEL_COLOR_IS_CHANGED_TO_GRAY_PREDICATE.apply(input);
        }
    };

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, "testLabelProperties.odesign"); //$NON-NLS-1$
    }

    /**
     * Ensures that changing the color of a label from the appearance page works as expected (and also tests that the
     * style is considered as custom).
     */
    public void testToolbarActionWithNodeLabelSelection() {
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "ClassDiagram", "new ClassDiagram", DDiagram.class);
        // Root Node
        doTestItalicFromToolbar("myEClass", DNodeNameEditPart.class);
        doTestBoldFromToolbar("myEClass", DNodeNameEditPart.class);
        doTestChangeLabelColorFromTabbar("myEClass", DNodeNameEditPart.class);

        // SubNode
        doTestItalicFromToolbar("newAttribute3", DNodeNameEditPart.class);
        doTestBoldFromToolbar("newAttribute3", DNodeNameEditPart.class);
        doTestChangeLabelColorFromTabbar("newAttribute3", DNodeNameEditPart.class);

        // BorderNode
        doTestItalicFromToolbar("myOperation", DNodeNameEditPart.class);
        doTestBoldFromToolbar("myOperation", DNodeNameEditPart.class);
        doTestChangeLabelColorFromTabbar("myOperation", DNodeNameEditPart.class);

        // check that buttons associated to the node are not enabled
        getSetStyleToWorkspaceImageButton(true, false);
    }

    /**
     * Ensures that changing the color of a label from the appearance page works as expected (and also tests that the
     * style is considered as custom).
     */
    public void testChangeLabelColorFromAppearanceSection() {
        doTestChangeLabelColorFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestChangeLabelColorFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestChangeLabelColorFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestChangeLabelColorFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestChangeLabelColorFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
        doTestChangeLabelColorFromAppearanceSection("[0..1] newEReference1", DEdgeEditPart.class);
        doTestChangeLabelColorFromAppearanceSection("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing the color of a label from the appearance page works as expected (and also tests that the
     * style is considered as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    private void doTestChangeLabelColorFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        doTestStyleCustomizationThroughColorSelectionFromAppearanceSection(selectedEditPart, FONTS_COLORS_GROUP, new int[] { 0 }, new int[] { 0 }, STATE_WHEN_LABEL_COLOR_IS_UNCHANGED_PREDICATE,
                STATE_WHEN_LABEL_COLOR_IS_CHANGED_TO_GRAY_PREDICATE);
    }

    /**
     * Ensures that changing a label as bold from tabbar works as expected (and also tests that the style is considered
     * as custom).
     */
    public void testBoldFromToolbar() {
        doTestBoldFromToolbar("myEClass", DNodeList2EditPart.class);
        doTestBoldFromToolbar("myEClass3", DNodeListEditPart.class);
        doTestBoldFromToolbar("myAttribute", DNodeListElementEditPart.class);
        doTestBoldFromToolbar("myPackage", DNodeContainerEditPart.class);
        doTestBoldFromToolbar("myPackage2", DNodeContainer2EditPart.class);
        doTestBoldFromToolbar("[0..1] newEReference1", DEdgeEditPart.class);
        doTestBoldFromToolbar("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing a label as bold from tabbar works as expected (and also tests that the style is considered
     * as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestBoldFromToolbar(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        doTestStyleCustomizationThroughTabbar(selectedEditPart, BOLD_FONT_STYLE, NORMAL_FONT_STATE_PREDICATE, BOLD_FONT_STATE_PREDICATE);
    }

    /**
     * Ensures that changing a label as stroke from tabbar works as expected (and also tests that the style is
     * considered as custom).
     */
    public void testItalicFromToolbar() {
        doTestItalicFromToolbar("myEClass", DNodeList2EditPart.class);
        doTestItalicFromToolbar("myEClass3", DNodeListEditPart.class);
        doTestItalicFromToolbar("myAttribute", DNodeListElementEditPart.class);
        doTestItalicFromToolbar("myPackage", DNodeContainerEditPart.class);
        doTestItalicFromToolbar("myPackage2", DNodeContainer2EditPart.class);
        doTestItalicFromToolbar("[0..1] newEReference1", DEdgeEditPart.class);
        doTestItalicFromToolbar("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing a label as stroke from tabbar works as expected (and also tests that the style is
     * considered as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestItalicFromToolbar(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        doTestStyleCustomizationThroughTabbar(selectedEditPart, ITALIC_FONT_STYLE, NORMAL_FONT_STATE_PREDICATE, ITALIC_FONT_STATE_PREDICATE);
    }

    /**
     * Ensures that changing the font color of a label from the tabbar works as expected (and also tests that the style
     * is considered as custom).
     */
    public void testChangeLabelColorFromTabbar() {
        doTestChangeLabelColorFromTabbar("myEClass", DNodeList2EditPart.class);
        doTestChangeLabelColorFromTabbar("myEClass3", DNodeListEditPart.class);
        doTestChangeLabelColorFromTabbar("myAttribute", DNodeListElementEditPart.class);
        doTestChangeLabelColorFromTabbar("myPackage", DNodeContainerEditPart.class);
        doTestChangeLabelColorFromTabbar("myPackage2", DNodeContainer2EditPart.class);
        doTestChangeLabelColorFromTabbar("[0..1] newEReference1", DEdgeEditPart.class);
        doTestChangeLabelColorFromTabbar("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing the font color of a label from the tabbar works as expected (and also tests that the style
     * is considered as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestChangeLabelColorFromTabbar(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        final Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                try {
                    checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, new ArrayList<FontFormat>(), false, false, null, -1, 8905185);
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
     */
    public void testItalicAndBoldFromToolbar() {
        // Not available in 4.x specific Tabbar
        if (TestsUtil.isEclipse4xPlatform()) {
            return;
        }

        doTestItalicAndBoldFromToolbar("myEClass", DNodeList2EditPart.class);
        doTestItalicAndBoldFromToolbar("myEClass3", DNodeListEditPart.class);
        doTestItalicAndBoldFromToolbar("myAttribute", DNodeListElementEditPart.class);
        doTestItalicAndBoldFromToolbar("myPackage", DNodeContainerEditPart.class);
        doTestItalicAndBoldFromToolbar("myPackage2", DNodeContainer2EditPart.class);
        doTestItalicAndBoldFromToolbar("[0..1] newEReference1", DEdgeEditPart.class);
        doTestItalicAndBoldFromToolbar("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing a label as stroke and bold from tabbar works as expected.
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestItalicAndBoldFromToolbar(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        SWTBotToolbarToggleButton boldButton = getTabbarBoldButton();
        SWTBotToolbarToggleButton italicButton = getTabbarItalicButton();
        SWTBotToolbarButton resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();

        // Check initial state
        checkNormalFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(false, false), false);

        // Enable bold and check result
        boldButton = getTabbarBoldButton();
        boldButton.click();
        checkBoldFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(true, false), true);

        // Enable italic and check result
        italicButton = getTabbarItalicButton();
        italicButton.click();
        checkBoldItalicFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(true, true), true);

        // Disable bold and check result
        boldButton = getTabbarBoldButton();
        boldButton.click();
        checkItalicFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(false, true), true);

        // Disable italic and check result
        italicButton = getTabbarItalicButton();
        italicButton.click();
        checkNormalFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(false, false), true);

        // Enable bold and check result
        boldButton = getTabbarBoldButton();
        boldButton.click();
        checkBoldFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(true, false), true);

        // Enable italic and check result
        italicButton = getTabbarItalicButton();
        italicButton.click();
        checkBoldItalicFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(true, true), true);

        // Cancel custom style and check result
        resetStylePropertiesToDefaultValuesButton.click();
        checkNormalFontStyle(selectedEditPart);
        checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(false, false), false);
    }

    /**
     * Ensures that changing a label as bold from the appearance page works as expected (and also tests that the style
     * is considered as custom).
     */
    public void testBoldFromAppearanceSection() {
        doTestBoldFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestBoldFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestBoldFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestBoldFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestBoldFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
        doTestBoldFromAppearanceSection("[0..1] newEReference1", DEdgeEditPart.class);
        doTestBoldFromAppearanceSection("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing a label as bold from the appearance page works as expected (and also tests that the style
     * is considered as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestBoldFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(selectedEditPart, FONTS_COLORS_GROUP, 0, NORMAL_FONT_STATE_PREDICATE, BOLD_FONT_STATE_PREDICATE, false);
    }

    /**
     * Ensures that changing a label as italic from the appearance page works as expected (and also tests that the style
     * is considered as custom).
     */
    public void testItalicFromAppearanceSection() {
        doTestItalicFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestItalicFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestItalicFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestItalicFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestItalicFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
        doTestItalicFromAppearanceSection("[0..1] newEReference1", DEdgeEditPart.class);
        doTestItalicFromAppearanceSection("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing a label as italic from the appearance page works as expected (and also tests that the style
     * is considered as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestItalicFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(selectedEditPart, FONTS_COLORS_GROUP, 1, NORMAL_FONT_STATE_PREDICATE, ITALIC_FONT_STATE_PREDICATE, false);
    }

    /**
     * Ensures that striking a label from the appearance page works as expected (and also tests that the style is
     * considered as customized).
     */
    public void testStrikeFromAppearanceSection() {
        doTestStrikeFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestStrikeFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestStrikeFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestStrikeFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestStrikeFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
    }

    /**
     * Ensures that striking a label from the appearance page works as expected (and also tests that the style is
     * considered as customized).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestStrikeFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(selectedEditPart, FONTS_COLORS_GROUP, 3, NORMAL_FONT_STATE_PREDICATE, STRIKE_FONT_STATE_PREDICATE, false);
    }

    /**
     * Ensures that underlining a label from the appearance page works as expected (and also tests that the style is
     * considered as customized).
     */
    public void testUnderlineFromAppearanceSection() {
        doTestUnderlineFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestUnderlineFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestUnderlineFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestUnderlineFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestUnderlineFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
    }

    /**
     * Check if the menu "Font Color" is available on edge.
     */
    public void testFontColorMenuAvailabilityOnEdge() {
        selectAndCheckEditPart("[0..1] newEReference1", DEdgeEditPart.class);
        try {
            editor.clickContextMenu("Font Color");
        } catch (WidgetNotFoundException e) {
            fail("The contextual menu \"Font Color\" should exist for edge.");
        }
    }

    /**
     * Check if the menu "Font Color" is available on edge label.
     */
    public void testFontColorMenuAvailabilityOnEdgeLabel() {
        selectAndCheckEditPart("[0..1] newEReference1", DEdgeNameEditPart.class);
        try {
            editor.clickContextMenu("Font Color");
        } catch (WidgetNotFoundException e) {
            fail("The contextual menu \"Font Color\" should exist for edge label.");
        }
    }

    /**
     * Check if the menu "Font..." is available on edge.
     */
    public void testFontMenuAvailabilityOnEdge() {
        testFontMenuAvailability("[0..1] newEReference1", DEdgeEditPart.class);
    }

    /**
     * Check if the menu "Font..." is available on edge label.
     */
    public void testFontMenuAvailabilityOnEdgeLabel() {
        testFontMenuAvailability("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Check if the menu "Font..." is available on the edit part with the given name and of the given type.
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    protected void testFontMenuAvailability(String name, Class<? extends EditPart> type) {
        selectAndCheckEditPart(name, type);
        try {
            editor.clickContextMenu("Font...");
            // Wait that system Font dialog opens
            SWTUtils.sleep(100);
            // Close the system Font dialog by pressing Esc key
            try {
                final Robot awtRobot = new Robot();
                UIThreadRunnable.syncExec(bot.getDisplay(), new VoidResult() {
                    @Override
                    public void run() {
                        awtRobot.keyPress(KeyEvent.VK_ESCAPE);
                        awtRobot.keyRelease(KeyEvent.VK_ESCAPE);
                    }
                });
            } catch (final AWTException e) {
                throw new RuntimeException(e);
            }
        } catch (WidgetNotFoundException e) {
            fail("The contextual menu \"Font...\" should exist for " + type.getSimpleName() + ".");
        }
    }

    /**
     * Ensures that underlining a label from the appearance page works as expected (and also tests that the style is
     * considered as customized).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestUnderlineFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(selectedEditPart, FONTS_COLORS_GROUP, 2, NORMAL_FONT_STATE_PREDICATE, UNDERLINE_FONT_STATE_PREDICATE, false);
    }

    /**
     * Ensures that changing a label as italic and bold from the appearance page works as expected.
     */
    public void testItalicAndBoldFromAppearanceSection() {
        doTestItalicAndBoldFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestItalicAndBoldFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestItalicAndBoldFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestItalicAndBoldFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestItalicAndBoldFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
        doTestItalicAndBoldFromAppearanceSection("[0..1] newEReference1", DEdgeEditPart.class);
        doTestItalicAndBoldFromAppearanceSection("[0..1] newEReference1", DEdgeNameEditPart.class);
    }

    /**
     * Ensures that changing a label as italic and bold from the appearance page works as expected.
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestItalicAndBoldFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotButton resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        SWTBotToggleButton boldButton = propertiesBot.toggleButtonInGroup(FONTS_COLORS_GROUP, 0);
        SWTBotToggleButton italicButton = propertiesBot.toggleButtonInGroup(FONTS_COLORS_GROUP, 1);

        // Check initial state
        checkNormalFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(false, false), false);

        // Enable bold and check result
        boldButton.click();
        checkBoldFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(true, false), true);

        // Enable italic and check result
        italicButton.click();
        checkBoldItalicFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(true, true), true);

        // Disable bold and check result
        boldButton.click();
        checkItalicFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(false, true), true);

        // Disable italic and check result
        italicButton.click();
        checkNormalFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(false, false), true);

        // Enable bold and check result
        boldButton.click();
        checkBoldFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(true, false), true);

        // Enable italic and check result
        italicButton.click();
        checkBoldItalicFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(true, true), true);

        // Cancel custom style and check result
        resetStylePropertiesToDefaultValuesButtonFromAppearanceTab.click();
        checkNormalFontStyle(selectedEditPart);
        checkButtonAppearanceChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(false, false), false);
    }

    /**
     * Test change to bold for all selected elements, with undo/redo.
     */
    public void testBoldAllSelectedElementsFromToolBarWithUndoRedo() {
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
            checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(true, false), true);
        }

        // Undo the change
        bot.menu("Edit").menu("Undo ").click();
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        for (SWTBotGefEditPart swtBotGefEditPart : listSwtBotGefEditPart) {
            checkNormalFontStyle(swtBotGefEditPart);
            checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(false, false), false);
        }

        // Redo the change
        bot.menu("Edit").menu("Redo ").click();
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        for (SWTBotGefEditPart swtBotGefEditPart : listSwtBotGefEditPart) {
            checkBoldFontStyle(swtBotGefEditPart);
            checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(true, false), true);
        }

        // Cancel style customization
        ((SWTBotToolbarButton) getResetStylePropertiesToDefaultValuesButton(true, true)).click();
        SWTBotUtils.waitAllUiEvents();
        for (SWTBotGefEditPart swtBotGefEditPart : listSwtBotGefEditPart) {
            checkNormalFontStyle(swtBotGefEditPart);
            checkButtonTabbarChecked(Arrays.asList(boldButton, italicButton), resetStylePropertiesToDefaultValuesButton, Arrays.asList(false, false), false);
        }
    }

    /**
     * Ensures that changing the font size of a label from the appearance page works as expected (and also tests that
     * the style is considered as custom).
     */
    public void testChangeFontSizeFromAppearanceSection() {
        doTestChangeFontSizeFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestChangeFontSizeFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestChangeFontSizeFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestChangeFontSizeFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestChangeFontSizeFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
    }

    /**
     * Ensures that changing the font size of a label from the appearance page works as expected (and also tests that
     * the style is considered as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestChangeFontSizeFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                try {
                    checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, new ArrayList<FontFormat>(), false, false, null, 12, -1);
                    return true;
                } catch (AssertionError e) {
                    return false;
                }
            }

        };

        doTestStyleCustomizationThroughComboBoxInAppearanceSection(selectedEditPart, NORMAL_FONT_STATE_PREDICATE, stateWhenButtonIsCheckedPredicate, 1, "12", false);
    }

    /**
     * Ensures that changing the font of a label from the appearance page works as expected (and also tests that the
     * style is considered as custom).
     */
    public void testChangeFontFromAppearanceSection() {
        doTestChangeFontFromAppearanceSection("myEClass", DNodeList2EditPart.class);
        doTestChangeFontFromAppearanceSection("myEClass3", DNodeListEditPart.class);
        doTestChangeFontFromAppearanceSection("myAttribute", DNodeListElementEditPart.class);
        doTestChangeFontFromAppearanceSection("myPackage", DNodeContainerEditPart.class);
        doTestChangeFontFromAppearanceSection("myPackage2", DNodeContainer2EditPart.class);
    }

    /**
     * Ensures that changing the font of a label from the appearance page works as expected (and also tests that the
     * style is considered as custom).
     *
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     */
    public void doTestChangeFontFromAppearanceSection(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart selectedEditPart = selectAndCheckEditPart(name, type);
        /*
         * Last found common fonts : Arial, Arial Black, Comic Sans MS, Courier New, DejaVu Sans, DejaVu Sans Mono,
         * DejaVu Serif, Georgia, Impact, Times New Roma, Trebuchet MS, Verdana , Webdings.
         */
        final String modifiedFont = "Comic Sans MS";
        Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate = new Predicate<SWTBotGefEditPart>() {

            @Override
            public boolean apply(SWTBotGefEditPart input) {
                try {
                    checkFontStyle(input, SWT.NORMAL, SWT.NORMAL, new ArrayList<FontFormat>(), false, false, modifiedFont, -1, -1);
                    return true;
                } catch (AssertionError e) {
                    return false;
                }
            }

        };

        doTestStyleCustomizationThroughComboBoxInAppearanceSection(selectedEditPart, NORMAL_FONT_STATE_PREDICATE, stateWhenButtonIsCheckedPredicate, 0, modifiedFont, true);
    }
}
