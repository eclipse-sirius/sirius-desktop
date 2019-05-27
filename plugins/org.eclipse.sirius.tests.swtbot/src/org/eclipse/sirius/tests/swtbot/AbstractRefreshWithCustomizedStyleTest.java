/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.properties.ResetStylePropertiesToDefaultValuesSelectionAdapter;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.condition.WidgetIsDisabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.WidgetIsEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToggleButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

/**
 * An abstract class providing facilities for testing the style customization features.
 * 
 * <p>
 * Relevant issues :
 * <ul>
 * <li>VP-3535: finer style customization</li>
 * </ul>
 * </p>
 * 
 * @author alagarde
 */
public abstract class AbstractRefreshWithCustomizedStyleTest extends AbstractSiriusSwtBotGefTestCase {
    /**
     * 
     */
    private static final String BAD_CURRENT_COLOR_BUTTON = "The default button in the color palette does not corresponds to the current color";

    /**
     * Style Customized predicate
     */
    protected static final Predicate<SWTBotGefEditPart> CUSTOMIZED_PREDICATE = new Predicate<SWTBotGefEditPart>() {
        @Override
        public boolean apply(SWTBotGefEditPart input) {
            return ResetStylePropertiesToDefaultValuesSelectionAdapter.isCustomizedView((View) input.part().getModel());
        }
    };

    /**
     * Style not customized predicate
     */
    protected static final Predicate<SWTBotGefEditPart> NOT_CUSTOMIZED_PREDICATE = Predicates.not(CUSTOMIZED_PREDICATE);

    private String oldDefaultFontName;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();

        // Set the default fontName to have tests on
        // "Reset style properties to default values" button works.
        oldDefaultFontName = changeDefaultFontName("Times New Roman");
    }

    /**
     * Selects and returns the edit part with the given name and of the given type.
     * 
     * @param name
     *            the edit part name
     * @param type
     *            the expected editpart type.
     * @return the selected edit part with the given name and of the given type
     */
    protected SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);

        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();

        return botPart;
    }

    /**
     * Selects the appearance section.
     * 
     * @return the bot corresponding to the selected appearance section
     */
    protected SWTBot selectAppearanceTab() {
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBot propertiesBot = propertiesView.bot();
        assertTrue("The appearance tab should be visible", SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesBot));
        return propertiesBot;
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having modified the given combobox with the given newValue from the appearance section, the given
     * predicate is checked and the cancel custom style button is enabled</li>
     * <li>Undoing the combo-box modification should bring us back to initial state</li>
     * <li>Re-doing the combo-box modification again should bring us back to step 2</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param editPartName
     *            the name of the selected edit part
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenRadioIsModifiedPredicate
     *            the predicate that should be checked when the combobox selection is modified
     * @param radioGroupName
     *            the name of the group containing the radio to select
     * @param radioIndexInGroup
     *            the value that should be selected in the combobox
     */
    protected void doTestStyleCustomizationThroughRadioInAppearanceSection(SWTBotGefEditPart selectedEditPart, String editPartName, Predicate<SWTBotGefEditPart> initialStatePredicate,
            Predicate<SWTBotGefEditPart> stateWhenRadioIsModifiedPredicate, String radioGroupName, int radioIndexInGroup) {
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotButton resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        SWTBotRadio radioToTest = propertiesBot.radioInGroup(radioGroupName, radioIndexInGroup);
        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        assertFalse("Radio should not be selected", radioToTest.isSelected());
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);

        // Step 2: Enable button and check result
        new WrappedSWTBotRadio(radioToTest).click();
        assertTrue(radioToTest.isSelected());
        assertTrue("The radio " + radioToTest.getToolTipText() + " has been modified, so the initial state should not be checked anymore", stateWhenRadioIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 3: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        selectedEditPart = editor.getEditPart(editPartName, selectedEditPart.part().getClass());

        selectedEditPart.select();
        propertiesBot = selectAppearanceTab();
        radioToTest = propertiesBot.radioInGroup(radioGroupName, radioIndexInGroup);
        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();

        // Check result: should be identical to the before-refresh state
        assertTrue(radioToTest.isSelected());
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenRadioIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 4: Cancel the custom style and check result (should be back to
        // initial state)
        resetStyleCustomizationButton.click();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);
        assertFalse("Radio should not be selected", radioToTest.isSelected());

    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having modified the given combobox with the given newValue from the appearance section, the given
     * predicate is checked and the cancel custom style button is enabled</li>
     * <li>Undoing the combo-box modification should bring us back to initial state</li>
     * <li>Re-doing the combo-box modification again should bring us back to step 2</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param editPartName
     *            the name of the selected edit part
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenRadioIsModifiedPredicate
     *            the predicate that should be checked when the combobox selection is modified
     * @param checkboxGroupName
     *            the name of the group containing the checkbox to check
     * @param checkBoxIndexInGroup
     *            the index of the checkbox to check inside its group
     */
    protected void doTestStyleCustomizationThroughCheckboxInAppearanceSection(SWTBotGefEditPart selectedEditPart, String editPartName, Predicate<SWTBotGefEditPart> initialStatePredicate,
            Predicate<SWTBotGefEditPart> stateWhenRadioIsModifiedPredicate, String checkboxGroupName, int checkBoxIndexInGroup) {
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotButton resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        SWTBotCheckBox checkboxToTest = propertiesBot.checkBoxInGroup(checkboxGroupName, checkBoxIndexInGroup);
        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        assertFalse("Checkbox should not be checked", checkboxToTest.isChecked());
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);

        // Step 2: Enable button and check result
        checkboxToTest.click();
        assertTrue("Checkbox should be checked", checkboxToTest.isChecked());
        assertTrue("The radio " + checkboxToTest.getToolTipText() + " has been modified, so the initial state should not be checked anymore",
                stateWhenRadioIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 3: Refresh
        editor.show();
        editor.setFocus();
        editor.select(editor.rootEditPart().children().get(0));
        editor.refresh();

        // Check result: should be identical to the before-refresh state
        editor.reveal(selectedEditPart.part());
        selectedEditPart.select();
        propertiesBot = selectAppearanceTab();
        checkboxToTest = propertiesBot.checkBoxInGroup(checkboxGroupName, checkBoxIndexInGroup);
        assertTrue("Checkbox should be checked", checkboxToTest.isChecked());
        assertTrue("After a refresh, the edit part should not have changed", stateWhenRadioIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 4: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        selectedEditPart = editor.getEditPart(editPartName, selectedEditPart.part().getClass());

        selectedEditPart.select();
        propertiesBot = selectAppearanceTab();
        checkboxToTest = propertiesBot.checkBoxInGroup(checkboxGroupName, checkBoxIndexInGroup);
        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();

        // Check result: should be identical to the before-refresh state
        assertTrue("Checkbox should be checked", checkboxToTest.isChecked());
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenRadioIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 5: Cancel the custom style and check result (should be back to
        // initial state)
        resetStyleCustomizationButton.click();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);
        assertFalse("Checkbox should not be checked", checkboxToTest.isChecked());
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having modified the given combobox with the given newValue from the appearance section, the given
     * predicate is checked and the cancel custom style button is enabled</li>
     * <li>Undoing the combo-box modification should bring us back to initial state</li>
     * <li>Re-doing the combo-box modification again should bring us back to step 2</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param comboBoxIdIngroup
     *            the index of the combobox to toggle inside the group
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenComboIsModifiedPredicate
     *            the predicate that should be checked when the combobox selection is modified
     * @param modifiedComboValue
     *            the value that should be selected in the combobox
     * @param customizationRevertable
     *            true for underline and strikeThrough and not for bold and italic because for these two last the
     *            de-selection of buttons doesn't remove the customization flag (See VP-3625)
     */
    protected void doTestStyleCustomizationThroughComboBoxInAppearanceSection(SWTBotGefEditPart selectedEditPart, Predicate<SWTBotGefEditPart> initialStatePredicate,
            Predicate<SWTBotGefEditPart> stateWhenComboIsModifiedPredicate, int comboBoxIdIngroup, String modifiedComboValue, boolean customizationRevertable) {
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotButton resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        SWTBotCCombo comboBoxToTest = propertiesBot.ccomboBoxInGroup("Fonts and Colors:", comboBoxIdIngroup);
        final String comboInitialValue = comboBoxToTest.getText();
        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        assertNotSame(modifiedComboValue, comboBoxToTest.getText());
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);

        // Step 2: Enable button and check result
        comboBoxToTest.setSelection(modifiedComboValue);
        assertTrue("The combo " + comboBoxToTest.getToolTipText() + " has been modified, so the initial state should not be checked anymore",
                stateWhenComboIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);
        assertEquals(modifiedComboValue, comboBoxToTest.getText());

        // Step 3: Disable button and check result
        comboBoxToTest.setSelection(comboInitialValue);
        assertTrue("The modifications on combo " + comboBoxToTest.getToolTipText() + " have been undone, so the initial state should be checked again", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), !customizationRevertable);
        assertNotSame(modifiedComboValue, comboBoxToTest.getText());

        // Step 4: re-enable button and check result
        comboBoxToTest.setSelection(modifiedComboValue);
        assertTrue("The combo " + comboBoxToTest.getToolTipText() + " has been modified, so the initial state should not be checked anymore",
                stateWhenComboIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);
        assertEquals(modifiedComboValue, comboBoxToTest.getText());

        // Step 5: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        selectedEditPart = editor.getEditPart(editor.getBounds(selectedEditPart).getCenter(), selectedEditPart.part().getClass());
        selectedEditPart.select();
        propertiesBot = selectAppearanceTab();
        comboBoxToTest = propertiesBot.ccomboBoxInGroup("Fonts and Colors:", comboBoxIdIngroup);
        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();

        // Check result: should be identical to the before-refresh state
        assertEquals(modifiedComboValue, comboBoxToTest.getText());
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenComboIsModifiedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 6: Cancel the custom style and check result (should be back to
        // initial state)
        resetStyleCustomizationButton.click();
        assertNotSame(modifiedComboValue, comboBoxToTest.getText());
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having modified the given color selection button with the given newColor from the appearance section,
     * the given predicate is checked and the cancel custom style button is enabled</li>
     * <li>Undoing the color selection modification should bring us back to initial state</li>
     * <li>Re-doing the color selection modification again should bring us back to step 2</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param buttonToToggleGroupName
     *            the name of the group containing the button to toggle
     * @param buttonToToggleIndexInGroup
     *            the index of the button to toggle inside the group(It must corresponds to text, line or fill color for
     *            example)
     * @param defaultButtonInColorPalette
     *            the index of the button that should be toggled by default inside the color palette(each color in the
     *            palette corresponds to a button)
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenButtonIsCheckedPredicate
     *            the predicate that should be checked when the button is toggled
     */
    protected void doTestStyleCustomizationThroughColorSelectionFromAppearanceSection(SWTBotGefEditPart selectedEditPart, String buttonToToggleGroupName, int[] buttonToToggleIndexInGroup,
            int[] defaultButtonInColorPalette, Predicate<SWTBotGefEditPart> initialStatePredicate, Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate) {
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotButton resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Lists.<Boolean> newArrayList(), false);

        for (int i : buttonToToggleIndexInGroup) {
            SWTBotButton buttonFromAppearanceSectionToTest = propertiesBot.buttonInGroup(buttonToToggleGroupName, buttonToToggleIndexInGroup[i]);

            // Step 2: Enable button and check result
            buttonFromAppearanceSectionToTest.click();
            assertTrue(BAD_CURRENT_COLOR_BUTTON, propertiesBot.button(defaultButtonInColorPalette[i]).isActive());
            // Click on the GRAY button
            propertiesBot.button(3).click();
            assertTrue("The button " + buttonFromAppearanceSectionToTest.getToolTipText() + " has been applied, so the initial state should not be checked anymore",
                    stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
            checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);
            buttonFromAppearanceSectionToTest.click();
            assertTrue(BAD_CURRENT_COLOR_BUTTON, propertiesBot.button(3).isActive());

            // Step 3: "Reset style properties to default values" and check
            // result
            resetStyleCustomizationButton.click();
            assertTrue("The button " + buttonFromAppearanceSectionToTest.getToolTipText() + " has been disabled, so the initial state should be checked again",
                    initialStatePredicate.apply(selectedEditPart));
            checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);

            // Step 4: re-enable button and check result
            buttonFromAppearanceSectionToTest.click();
            assertTrue(BAD_CURRENT_COLOR_BUTTON, propertiesBot.button(defaultButtonInColorPalette[i]).isActive());
            // Click on the GRAY button
            propertiesBot.button(3).click();
            assertTrue("The button " + buttonFromAppearanceSectionToTest.getToolTipText() + " has been applied, so the initial state should not be checked anymore",
                    stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
            checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);
        }

        // Step 5: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        if (DEdgeEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge in the new editor
            Edge gmfEdge = (Edge) ((DEdgeEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfEdge.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeEditPart.class);
        } else if (DEdgeNameEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge name in the new editor
            Node gmfNode = (Node) ((DEdgeNameEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfNode.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeNameEditPart.class);
        } else {
            selectedEditPart = editor.getEditPart(editor.getBounds(selectedEditPart).getCenter(), selectedEditPart.part().getClass());
        }
        selectedEditPart.select();
        propertiesBot = selectAppearanceTab();
        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();

        // Check result: should be identical to the before-refresh state
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 6: "Reset style properties to default values" and check
        // result
        // (should be back to initial state)
        resetStyleCustomizationButton.click();
        SWTBotUtils.waitAllUiEvents();

        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having modified the given color selection button with the given newColor from the appearance section,
     * the given predicate is checked and the cancel custom style button is enabled</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenButtonIsCheckedPredicate
     *            the predicate that should be checked when the button is toggled
     * @param newImagePath
     *            the path of the workspace image to set (from the current project root)
     */
    protected void doTestStyleCustomizationThroughBackgroundImageFromAppearanceSection(SWTBotGefEditPart selectedEditPart, Predicate<SWTBotGefEditPart> initialStatePredicate,
            Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate, String newImagePath) {
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotButton resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        SWTBotButton buttonFromAppearanceSectionToTest = propertiesBot.buttonInGroup("Fonts and Colors:", 3);

        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Lists.<Boolean> newArrayList(), false);

        // Step 2: Enable button and check result
        buttonFromAppearanceSectionToTest.click();
        SWTBotShell activeShell = bot.activeShell();
        activeShell.bot().text().setText(getProjectName() + "/" + newImagePath);
        activeShell.bot().button("OK").click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        bot.activeEditor().setFocus();
        assertTrue("The button " + buttonFromAppearanceSectionToTest.getToolTipText() + " has been applied, so the initial state should not be checked anymore",
                stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(true), true);

        // Step 3: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        selectedEditPart = editor.getEditPart(editor.getBounds(selectedEditPart).getCenter(), selectedEditPart.part().getClass());
        selectedEditPart.select();
        propertiesBot = selectAppearanceTab();
        resetStylePropertiesToDefaultValuesButtonFromAppearanceTab = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();

        // Check result: should be identical to the before-refresh state
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(true), true);

        // Step 4: Cancel the custom style and check result (should be back to
        // initial state)
        resetStylePropertiesToDefaultValuesButtonFromAppearanceTab.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Lists.<SWTBotToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromAppearanceTab, Arrays.asList(false), false);
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having toggled the given button from the appearance section, the given predicate is checked and the
     * cancel custom style button is enabled</li>
     * <li>Re-toggling the button should bring us back to initial state</li>
     * <li>Toggling the button again should bring us back to step 2</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param buttonToToggleGroupName
     *            the name of the group containing the button to toggle
     * @param buttonToToggleIndexInGroup
     *            the index of the button to toggle inside the group
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenButtonIsCheckedPredicate
     *            the predicate that should be checked when the button is toggled
     * @param customizationRevertable
     *            true for underline and strikeThrough and not for bold and italic because for these two last the
     *            de-selection of buttons doesn't remove the customization flag (See VP-3625)
     */
    protected void doTestStyleCustomizationThroughToggleButtonFromAppearanceSection(SWTBotGefEditPart selectedEditPart, String buttonToToggleGroupName, int buttonToToggleIndexInGroup,
            Predicate<SWTBotGefEditPart> initialStatePredicate, Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate, boolean customizationRevertable) {
        SWTBot propertiesBot = selectAppearanceTab();
        SWTBotButton resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        SWTBotToggleButton buttonFromAppearanceSectionToTest = propertiesBot.toggleButtonInGroup(buttonToToggleGroupName, buttonToToggleIndexInGroup);

        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Arrays.asList(buttonFromAppearanceSectionToTest), resetStyleCustomizationButton, Arrays.asList(false), false);
        bot.waitUntil(new WidgetIsDisabledCondition(resetStyleCustomizationButton));

        // Step 2: Enable button and check result
        buttonFromAppearanceSectionToTest.click();
        bot.waitUntil(new WidgetIsEnabledCondition(resetStyleCustomizationButton));
        assertTrue("The button " + buttonFromAppearanceSectionToTest.getToolTipText() + " has been applied, so the initial state should not be checked anymore",
                stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));

        checkButtonAppearanceChecked(Arrays.asList(buttonFromAppearanceSectionToTest), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 3: Disable button and check result
        buttonFromAppearanceSectionToTest.click();
        if (customizationRevertable) {
            bot.waitUntil(new WidgetIsDisabledCondition(resetStyleCustomizationButton));
        } else {
            bot.waitUntil(new WidgetIsEnabledCondition(resetStyleCustomizationButton));
        }
        assertTrue("The button " + buttonFromAppearanceSectionToTest.getToolTipText() + " has been disabled, so the initial state should be checked again",
                initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Arrays.asList(buttonFromAppearanceSectionToTest), resetStyleCustomizationButton, Arrays.asList(false), !customizationRevertable);

        // Step 4: re-enable button and check result
        buttonFromAppearanceSectionToTest.click();
        bot.waitUntil(new WidgetIsEnabledCondition(resetStyleCustomizationButton));
        assertTrue("The button " + buttonFromAppearanceSectionToTest.getToolTipText() + " has been applied, so the initial state should not be checked anymore",
                stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Arrays.asList(buttonFromAppearanceSectionToTest), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 5: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        if (DEdgeEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge in the new editor
            Edge gmfEdge = (Edge) ((DEdgeEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfEdge.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeEditPart.class);
        } else if (DEdgeNameEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge name in the new editor
            Node gmfNode = (Node) ((DEdgeNameEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfNode.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeNameEditPart.class);
        } else {
            selectedEditPart = editor.getEditPart(editor.getBounds(selectedEditPart).getCenter(), selectedEditPart.part().getClass());
        }
        selectedEditPart.select();
        propertiesBot = selectAppearanceTab();
        buttonFromAppearanceSectionToTest = propertiesBot.toggleButtonInGroup(buttonToToggleGroupName, buttonToToggleIndexInGroup);
        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromAppearanceTab();
        bot.waitUntil(new WidgetIsEnabledCondition(resetStyleCustomizationButton));
        // Check result: should be identical to the before-refresh state
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Arrays.asList(buttonFromAppearanceSectionToTest), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 6: Cancel the custom style and check result (should be back to
        // initial state)
        resetStyleCustomizationButton.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonAppearanceChecked(Arrays.asList(buttonFromAppearanceSectionToTest), resetStyleCustomizationButton, Arrays.asList(false), false);
    }

    /**
     * Ensures that each of the given {@link SWTBotToggleButton} has the expected checked state. If one of the
     * 
     * @param buttons
     *            the {@link SWTBotToggleButton}s to test
     * @param resetStyleCustomizationButton
     *            the cancel custom style button
     * @param expectedButtonCheckState
     *            the expected check state for each {@link SWTBotToggleButton} given in parameter
     * @param customStyleButtonShouldBeChecked
     *            indicates if the 'cancelCustomStyle' button should be checked or not
     */
    protected void checkButtonAppearanceChecked(Collection<SWTBotToggleButton> buttons, SWTBotButton resetStyleCustomizationButton, Collection<Boolean> expectedButtonCheckState,
            boolean customStyleButtonShouldBeChecked) {
        Iterator<Boolean> expectedButtonCheckStateIterator = expectedButtonCheckState.iterator();

        // Check the checked state of each button
        for (SWTBotToggleButton button : buttons) {
            boolean shouldBeChecked = expectedButtonCheckStateIterator.next();
            // If one of the button should be checked, then the
            // cancelcustomstyle button should be checked too
            doTestToggleButtonIsChecked(shouldBeChecked, button);
        }

        // Check the state of the cancel custom style button
        if (customStyleButtonShouldBeChecked) {
            assertTrue(resetStyleCustomizationButton.getToolTipText() + " button should be enabled.", resetStyleCustomizationButton.isEnabled());
        } else {
            assertFalse(resetStyleCustomizationButton.getToolTipText() + " button should not be enabled.", resetStyleCustomizationButton.isEnabled());
        }
    }

    /**
     * Checks that the given {@link SWTBotToggleButton} is checked or not, according to the given boolean.
     * 
     * @param buttonShouldBeChecked
     *            indicates if the {@link SWTBotToggleButton} should be checked or not
     * @param button
     *            the {@link SWTBotToggleButton} to test
     */
    private void doTestToggleButtonIsChecked(boolean buttonShouldBeChecked, SWTBotToggleButton button) {
        if (buttonShouldBeChecked) {
            assertTrue(button.getToolTipText() + " button should be pressed.", button.isPressed());
        } else {
            assertFalse(button.getToolTipText() + " button should not be pressed.", button.isPressed());
        }
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having toggled the given button from the tabbar, the given predicate is checked and the cancel custom
     * style button is enabled</li>
     * <li>Re-toggling the button should bring us back to initial state</li>
     * <li>Toggling the button again should bring us back to step 2</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param tabbarButtonTooltip
     *            the tooltip of the tabbar button to test
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenButtonIsCheckedPredicate
     *            the predicate that should be checked when the button is toggled
     */
    protected void doTestStyleCustomizationThroughTabbar(SWTBotGefEditPart selectedEditPart, String tabbarButtonTooltip, Predicate<SWTBotGefEditPart> initialStatePredicate,
            Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate) {
        SWTBotToolbarButton resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        SWTBotToolbarToggleButton buttonFromTabbarToTest = bot.toolbarToggleButtonWithTooltip(tabbarButtonTooltip);

        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Arrays.asList(buttonFromTabbarToTest), resetStyleCustomizationButton, Arrays.asList(false), false);

        // Step 2: Enable button and check result
        buttonFromTabbarToTest.click();
        assertTrue("The button " + buttonFromTabbarToTest.getToolTipText() + " has been applied, so the initial state should not be checked anymore",
                stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Arrays.asList(buttonFromTabbarToTest), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 3: Disable button and check result
        buttonFromTabbarToTest.click();
        assertTrue("The button " + buttonFromTabbarToTest.getToolTipText() + " has been disabled, so the initial state should be checked again", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Arrays.asList(buttonFromTabbarToTest), resetStyleCustomizationButton, Arrays.asList(false), true);

        // Step 4: re-enable button and check result
        buttonFromTabbarToTest.click();
        assertTrue("The button " + buttonFromTabbarToTest.getToolTipText() + " has been applied, so the initial state should not be checked anymore",
                stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Arrays.asList(buttonFromTabbarToTest), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 5: Refresh
        editor.click(0, 0);
        editor.refresh();
        selectedEditPart.select();
        buttonFromTabbarToTest = bot.toolbarToggleButtonWithTooltip(tabbarButtonTooltip);
        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();

        // Check result: should be identical to the before-refresh state
        assertTrue("After a refresh, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Arrays.asList(buttonFromTabbarToTest), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 6: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        if (DEdgeEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge in the new editor
            Edge gmfEdge = (Edge) ((DEdgeEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfEdge.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeEditPart.class);
        } else if (DEdgeNameEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge name in the new editor
            Node gmfNode = (Node) ((DEdgeNameEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfNode.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeNameEditPart.class);
        } else {
            selectedEditPart = editor.getEditPart(editor.getBounds(selectedEditPart).getCenter(), selectedEditPart.part().getClass());
        }
        selectedEditPart.select();
        buttonFromTabbarToTest = bot.toolbarToggleButtonWithTooltip(tabbarButtonTooltip);
        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();

        // Check result: should be identical to the before-refresh state
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Arrays.asList(buttonFromTabbarToTest), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 7: Cancel the custom style and check result (should be back to
        // initial state)
        resetStyleCustomizationButton.click();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Arrays.asList(buttonFromTabbarToTest), resetStyleCustomizationButton, Arrays.asList(false), false);
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having toggled the given button from the tabbar, the given predicate is checked and the cancel custom
     * style button is enabled</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param colorButtonName
     *            the name of the color selection button to click on
     * @param newColorName
     *            the new color name (e.g. "Yellow")
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenButtonIsCheckedPredicate
     *            the predicate that should be checked when the button is toggled
     */
    protected void doTestStyleCustomizationThroughColorSelectionFromTabbar(SWTBotGefEditPart selectedEditPart, String colorButtonName, Predicate<SWTBotGefEditPart> initialStatePredicate,
            final Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate, String newColorName) {
        editor.setFocus();
        selectedEditPart.select();
        SWTBotUtils.waitAllUiEvents();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        // Check initial state
        SWTBotToolbarButton resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStyleCustomizationButton, Lists.<Boolean> newArrayList(), false);

        // Step 2: Enable button and check result
        final SWTBotMenu menuItem = editor.bot().toolbarDropDownButtonWithTooltip(colorButtonName).menuItem(newColorName);
        menuItem.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("The font color has been changed, so the initial state should not be checked anymore", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 3: Reopen diagram
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        if (DEdgeEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge in the new editor
            Edge gmfEdge = (Edge) ((DEdgeEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfEdge.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeEditPart.class);
        } else if (DEdgeNameEditPart.class.isInstance(selectedEditPart.part())) {
            // Select the corresponding DEdge name in the new editor
            Node gmfNode = (Node) ((DEdgeNameEditPart) selectedEditPart.part()).getModel();
            DEdge dEdge = (DEdge) gmfNode.getElement();
            selectedEditPart = editor.getEditPart(dEdge.getName(), DEdgeNameEditPart.class);
        } else {
            selectedEditPart = editor.getEditPart(editor.getBounds(selectedEditPart).getCenter(), selectedEditPart.part().getClass());
        }
        selectedEditPart.select();

        resetStyleCustomizationButton = getResetStylePropertiesToDefaultValuesButtonFromTabbar();

        // Check result: should be identical to the before-refresh state
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(true), true);

        // Step 4: Cancel the custom style and check result (should be back to
        // initial state)
        resetStyleCustomizationButton.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStyleCustomizationButton, Arrays.asList(false), false);
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having toggled the given button from the tabbar, the given predicate is checked and the cancel custom
     * style button is enabled</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param edgeEditPartName
     *            the name of the selected edit part
     * @param newRoutingStyleName
     *            the new name for the routing style
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenButtonIsCheckedPredicate
     *            the predicate that should be checked when the button is toggled
     */
    protected void doTestStyleCustomizationThroughRoutingStyleSelectionFromTabbar(SWTBotGefEditPart selectedEditPart, String edgeEditPartName, Predicate<SWTBotGefEditPart> initialStatePredicate,
            final Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate, String newRoutingStyleName) {
        editor.setFocus();
        selectedEditPart.select();
        SWTBotUtils.waitAllUiEvents();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        // Check initial state
        SWTBotToolbarButton resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Lists.<Boolean> newArrayList(), false);

        // Step 2: Enable button and check result
        final SWTBotMenu menuItem = editor.bot().toolbarDropDownButtonWithTooltip("Line Style").menuItem(newRoutingStyleName);
        menuItem.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("The font color has been changed, so the initial state should not be checked anymore", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(true), true);

        // Step 3: Reopen diagram
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        selectedEditPart = editor.getEditPart(edgeEditPartName, selectedEditPart.part().getClass());
        selectedEditPart.select();

        resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();

        // Check result: should be identical to the before-refresh state
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(true), true);

        // Step 4: Cancel the custom style and check result (should be back to
        // initial state)
        resetStylePropertiesToDefaultValuesButtonFromTabbar.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(false), false);
    }

    /**
     * Ensures that when selecting the given edit part :
     * <ol>
     * <li>The initial state predicated is checked</li>
     * <li>After having modified the given color selection button with the given newColor from the tabbar, the given
     * predicate is checked and the cancel custom style button is enabled</li>
     * <li>Refreshing the representation does not change this state</li>
     * <li>Reopening the representation does not change this state</li>
     * <li>Canceling the custom style reverts modifications (initial state should be checked again)</li>
     * </ol>
     * 
     * @param selectedEditPart
     *            the edit part to select
     * @param initialStatePredicate
     *            the initial state predicate
     * @param stateWhenButtonIsCheckedPredicate
     *            the predicate that should be checked when the button is toggled
     * @param newImagePath
     *            the path of the workspace image to set (from the current project root)
     */
    protected void doTestStyleCustomizationThroughBackgroundImageFromTabbar(SWTBotGefEditPart selectedEditPart, Predicate<SWTBotGefEditPart> initialStatePredicate,
            Predicate<SWTBotGefEditPart> stateWhenButtonIsCheckedPredicate, String newImagePath) {
        editor.setFocus();
        selectedEditPart.select();
        SWTBotUtils.waitAllUiEvents();
        DDiagram parentDiagram = ((DDiagramElement) ((View) selectedEditPart.part().getModel()).getElement()).getParentDiagram();
        final String representationName = parentDiagram.getName();
        final String representationDescriptionName = parentDiagram.getDescription().getName();

        SWTBotToolbarButton resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();
        // Check initial state
        assertTrue("Wrong initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Lists.<Boolean> newArrayList(), false);

        // Step 2: Enable button and check result
        editor.bot().toolbarButtonWithTooltip("Set style to workspace image").click();
        SWTBotShell activeShell = bot.activeShell();
        activeShell.bot().text().setText(getProjectName() + "/" + newImagePath);
        activeShell.bot().button("OK").click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        assertTrue("The background image has been changed, so the initial state should not be checked anymore", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(true), true);

        // Step 3: Reopen diagram
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(SessionManager.INSTANCE.getSessions().iterator().next(), representationDescriptionName, representationName, DDiagram.class);
        selectedEditPart = editor.getEditPart(editor.getBounds(selectedEditPart).getCenter(), selectedEditPart.part().getClass());
        selectedEditPart.select();

        resetStylePropertiesToDefaultValuesButtonFromTabbar = getResetStylePropertiesToDefaultValuesButtonFromTabbar();

        // Check result: should be identical to the before-refresh state
        assertTrue("After having reopened the editor, the edit part should not have changed", stateWhenButtonIsCheckedPredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(true), true);

        // Step 4: Cancel the custom style and check result (should be back to
        // initial state)
        resetStylePropertiesToDefaultValuesButtonFromTabbar.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("After having cancelled the custom style, we should be back to the initial state", initialStatePredicate.apply(selectedEditPart));
        checkButtonTabbarChecked(Lists.<SWTBotToolbarToggleButton> newArrayList(), resetStylePropertiesToDefaultValuesButtonFromTabbar, Arrays.asList(false), false);
    }

    /**
     * Ensures that each of the given {@link SWTBotToolbarToggleButton} has the expected checked state. If one of the
     * 
     * @param buttons
     *            the {@link SWTBotToolbarToggleButton}s to test
     * @param resetStyleCustomizationButton
     *            the cancel custom style button
     * @param expectedButtonCheckState
     *            the expected check state for each {@link SWTBotToolbarToggleButton} given in parameter
     * @param resetStylePropertiesToDefaultValuesButtonShouldBeChecked
     *            indicates if the 'cancelCustomStyle' button should be checked or not
     */
    protected void checkButtonTabbarChecked(Collection<SWTBotToolbarToggleButton> buttons, SWTBotToolbarButton resetStyleCustomizationButton, Collection<Boolean> expectedButtonCheckState,
            boolean resetStylePropertiesToDefaultValuesButtonShouldBeChecked) {
        Iterator<Boolean> expectedButtonCheckStateIterator = expectedButtonCheckState.iterator();

        // Check the checked state of each button
        for (SWTBotToolbarToggleButton button : buttons) {
            boolean shouldBeChecked = expectedButtonCheckStateIterator.next();
            // If one of the button should be checked, then the
            // "Reset style properties to default values" button should be
            // checked too
            doTestTabbarButtonIsChecked(shouldBeChecked, button);
        }

        // Check the state of the "Reset style properties to default values"
        // button
        if (resetStylePropertiesToDefaultValuesButtonShouldBeChecked) {
            assertTrue(resetStyleCustomizationButton.getToolTipText() + " button should be enabled.", resetStyleCustomizationButton.isEnabled());
        } else {
            assertFalse(resetStyleCustomizationButton.getToolTipText() + " button should not be enabled.", resetStyleCustomizationButton.isEnabled());
        }
    }

    /**
     * Checks that the given {@link SWTBotToolbarToggleButton} is checked or not, according to the given boolean.
     * 
     * @param shouldBeChecked
     *            indicates if the {@link SWTBotToolbarToggleButton} should be checked or not
     * @param button
     *            the {@link SWTBotToolbarToggleButton} to test
     */
    private void doTestTabbarButtonIsChecked(boolean shouldBeChecked, SWTBotToolbarToggleButton button) {
        if (shouldBeChecked) {
            assertTrue(button.getText() + " button should be checked.", button.isChecked());
        } else {
            assertFalse(button.getText() + " button should not be checked.", button.isChecked());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Reset the default fontName
        changeDefaultFontName(oldDefaultFontName);

        super.tearDown();
    }
}
