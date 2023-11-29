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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Abstract class to test that Pin/Unpin, Show/Hide and Copy/Paste Layout actions are disabled on a Sequence Diagram.
 * Sequence diagram. The getEditPartsToCheckDisabledActionsOn and getElementPathsToCheckNoEffectInWizard abstract
 * methods will allow sbclasses to define the elements on which the tests needs to be done.
 * 
 * @author jdupont, mporhel
 */
public abstract class AbstractActionDisabledOnSequenceDiagramTest extends AbstractDefaultModelSequenceTests {

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
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationId(), dRepresentationName.get(), DDiagram.class);
        }

        initEditor();
    }

    /**
     * Test Pin action is disabled on tabbar for execution, states, messages, interactionUses, combinedFragments, Lost
     * messages and found messages.
     */
    public void testPinFromTabbarOnSequenceDiagramsComponents() {

        Collection<SWTBotGefEditPart> partsToTest = getEditPartsToCheckDisabledActionsOn();

        assertFalse("There should be some part to test.", partsToTest.isEmpty());

        // Select and check actions.
        for (SWTBotGefEditPart partToTest : partsToTest) {
            selectAndWaitSelected(partToTest);
            assertEquals("The pin action in tabbar should not be enabled", false, editor.bot().toolbarToggleButtonWithTooltip(Messages.PinElementsEclipseAction_text).isEnabled());
        }
    }

    /**
     * Test Hide action is disabled on tabbar for execution, states, messages, interactionUses, combinedFragments, Lost
     * messages and found messages.
     */
    public void testHideFromTabbarOnSequenceDiagramsComponents() {
        testActionFromTabbarOnSequenceDiagramComponents("The Hide action in tabbar should not be enabled", Messages.SiriusDiagramActionBarContributor_hideElement);
    }

    /**
     * Test Copy layout action is disabled on tabbar for execution, states, messages, interactionUses,
     * combinedFragments, Lost messages and found messages.
     */
    public void testCopyLayoutFromTabbarOnSequenceDiagramsComponents() {
        testActionFromTabbarOnSequenceDiagramComponents("The Copy Format action in tabbar should not be enabled", Messages.CopyFormatAction_toolTipText_diagramElements);
    }

    /**
     * Test Pin/Unpin action wizard from tabbar. Select for all components execution, states, messages, interactionUses,
     * combinedFragments, Lost messages and Found messages. Check that the selection have no effect. Session should not
     * be in dirty.
     */
    public void testPinUnPinWizardFromTabbarOnSequenceDiagramsComponents() {
    	if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
		testActionWizardFromTabbarOnSequenceDiagramComponents(Messages.SelectPinnedElementsAction_tooltip, Messages.SelectPinnedElementsAction_label,
				"The elements in wizard Pin/UnPin should be show grayed and should have no effect");
    }

    /**
     * Test Show/Hide action wizard from tabbar. Select for all components execution, states, messages, interactionUses,
     * combinedFragments, Lost messages and Found messages. Check that the selection have no effect. Session should not
     * be in dirty.
     */
    public void testShowHideWizardFromTabbarOnSequenceDiagramsComponents() {
    	if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
		testActionWizardFromTabbarOnSequenceDiagramComponents(Messages.SelectHiddenElementsAction_tooltip, Messages.HiddenElementsSelectionCommand_dialogTitle,
                "The elements in wizard Show/Hide should be show grayed and should have no effect");
    }

    private void testActionWizardFromTabbarOnSequenceDiagramComponents(String toolTipAction, String dialogName, String messageError) {
        // Session opening in dirty
        localSession.save();
        editor.setFocus();
        editor.mainEditPart().select();
        editor.mainEditPart().click();
        SWTBotUtils.waitAllUiEvents();

        Collection<List<String>> pathsToTest = getElementPathsToCheckNoEffectInWizard();
        assertFalse("There should be some path to test.", pathsToTest.isEmpty());

        // Select and check actions.
        for (List<String> pathToTest : pathsToTest) {
            editor.bot().toolbarButtonWithTooltip(toolTipAction).click();
            // The tabbar action should trigger the opening of a wizard.
            bot.waitUntilWidgetAppears(Conditions.shellIsActive(dialogName));
            // Set the focus to the new shell (in Eclipse Photon and before, it was "automatic", but not after on some
            // IC server).
            bot.shell(dialogName).setFocus();

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
