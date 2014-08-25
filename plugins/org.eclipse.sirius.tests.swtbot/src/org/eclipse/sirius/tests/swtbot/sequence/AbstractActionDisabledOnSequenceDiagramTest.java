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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;

/**
 * Abstract class to test that Pin/Unpin, Show/Hide and Copy/Paste Layout
 * actions are disabled on a Sequence Diagram. Sequence diagram. The
 * getEditPartsToCheckDisabledActionsOn and
 * getElementPathsToCheckNoEffectInWizard abstract methods will allow sbclasses
 * to define the elements on which the tests needs to be done.
 * 
 * @author jdupont, mporhel
 */
public abstract class AbstractActionDisabledOnSequenceDiagramTest extends AbstractDefaultModelSequenceTests {

    private static final String PIN = "Pin selected elements";

    private static final String UNPIN = "Unpin selected elements";

    private static final String HIDE = "Hide element";

    private static final String COPY_LAYOUT = "Copy the layout of the selected diagram elements";

    private static final String PIN_UNPIN_WIZARD = "Pin/Unpin";

    private static final String DIALOG_PINNING = "Diagram elements pinning";

    private static final String SHOW_HIDE_WIZARD = "Show/Hide";

    private static final String DIALOG_SHOWING = "Diagram elements visibility";

    /**
     * Method to retrieve the part to check.
     * 
     * @return the part to check.
     */
    protected abstract Collection<SWTBotGefEditPart> getEditPartsToCheckDisabledActionsOn();

    /**
     * Method to retrieve the part to check.
     * 
     * @return the part to check.
     */
    protected abstract Collection<List<String>> getElementPathsToCheckNoEffectInWizard();

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, getSessionModel());
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        Option<String> dRepresentationName = getDRepresentationName();
        if (dRepresentationName.some()) {
            editor = openDiagram(localSession.getOpenedSession(), getRepresentationId(), dRepresentationName.get(), DDiagram.class);
        }

        initEditor();
    }

    /**
     * Test Pin action is disabled on tabbar for execution, states, messages,
     * interactionUses, combinedFragments, Lost messages and found messages.
     */
    public void testPinFromTabbarOnSequenceDiagramsComponents() {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }
        testActionFromTabbarOnSequenceDiagramComponents("The pin action in tabbar should not be enabled", PIN);
    }

    /**
     * Test Unpin action is disabled on tabbar for execution, states, messages,
     * interactionUses, combinedFragments, Lost messages and found messages.
     */
    public void testUnpinFromTabbarOnSequenceDiagramsComponents() {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }
        testActionFromTabbarOnSequenceDiagramComponents("The unPin action in tabbar should not be enabled", UNPIN);
    }

    /**
     * Test Hide action is disabled on tabbar for execution, states, messages,
     * interactionUses, combinedFragments, Lost messages and found messages.
     */
    public void testHideFromTabbarOnSequenceDiagramsComponents() {
        testActionFromTabbarOnSequenceDiagramComponents("The Hide action in tabbar should not be enabled", HIDE);
    }

    /**
     * Test Copy layout action is disabled on tabbar for execution, states,
     * messages, interactionUses, combinedFragments, Lost messages and found
     * messages.
     */
    public void testCopyLayoutFromTabbarOnSequenceDiagramsComponents() {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }
        testActionFromTabbarOnSequenceDiagramComponents("The Copy Layout action in tabbar should not be enabled", COPY_LAYOUT);
    }

    /**
     * Test Pin/Unpin action wizard from tabbar. Select for all components
     * execution, states, messages, interactionUses, combinedFragments, Lost
     * messages and Found messages. Check that the selection have no effect.
     * Session should not be in dirty.
     */
    public void testPinUnPinWizardFromTabbarOnSequenceDiagramsComponents() {
        testActionWizardFromTabbarOnSequenceDiagramComponents(PIN_UNPIN_WIZARD, DIALOG_PINNING, "The elements in wizard Pin/UnPin should be show grayed and should have no effect");
    }

    /**
     * Test Show/Hide action wizard from tabbar. Select for all components
     * execution, states, messages, interactionUses, combinedFragments, Lost
     * messages and Found messages. Check that the selection have no effect.
     * Session should not be in dirty.
     */
    public void testShowHideWizardFromTabbarOnSequenceDiagramsComponents() {
        testActionWizardFromTabbarOnSequenceDiagramComponents(SHOW_HIDE_WIZARD, DIALOG_SHOWING, "The elements in wizard Show/Hide should be show grayed and should have no effect");
    }

    private void testActionWizardFromTabbarOnSequenceDiagramComponents(String toolTipAction, String dialogName, String messageError) {
        // Session opening in dirty
        localSession.save();
        editor.setFocus();
        editor.mainEditPart().select();
        editor.mainEditPart().click();

        Collection<List<String>> pathsToTest = getElementPathsToCheckNoEffectInWizard();
        assertFalse("There should be some path to test.", pathsToTest.isEmpty());

        // Select and check actions.
        for (List<String> pathToTest : pathsToTest) {
            editor.bot().toolbarButtonWithTooltip(toolTipAction).click();
            // The tabbar action should trigger the opening of a wizard.
            bot.waitUntilWidgetAppears(Conditions.shellIsActive(dialogName));

            assertFalse("The path should not de empty.", pathToTest.isEmpty());

            SWTBotTreeItem treeItem = null;
            for (String elementName : pathToTest) {
                if (treeItem == null) {
                    treeItem = bot.tree().getTreeItem(elementName);
                } else {
                    treeItem.expand();
                    treeItem = treeItem.getNode(elementName);
                }
            }
            assertNotNull("Please review test data: no found tree item for path: " + pathToTest, treeItem);
            treeItem.select().toggleCheck();

            // Retrieve element
            bot.button("OK").click();
            // Check that the session is not dirty
            assertEquals(messageError, false, localSession.isDirty());

        }
    }

    private void testActionFromTabbarOnSequenceDiagramComponents(String messageError, String toolTipAction) {
        Collection<SWTBotGefEditPart> partsToTest = getEditPartsToCheckDisabledActionsOn();

        assertFalse("There should be some part to test.", partsToTest.isEmpty());

        // Select and check actions.
        for (SWTBotGefEditPart partToTest : partsToTest) {
            selectAndWaitSelected(partToTest);
            assertEquals(messageError, false, editor.bot().toolbarButtonWithTooltip(toolTipAction).isEnabled());
        }
    }

    private void selectAndWaitSelected(SWTBotGefEditPart swtBotGefEditPart) {
        ICondition selected = new CheckSelectedCondition(editor, swtBotGefEditPart.part());
        swtBotGefEditPart.select();
        bot.waitUntil(selected);
        SWTBotUtils.waitAllUiEvents();
    }
}
