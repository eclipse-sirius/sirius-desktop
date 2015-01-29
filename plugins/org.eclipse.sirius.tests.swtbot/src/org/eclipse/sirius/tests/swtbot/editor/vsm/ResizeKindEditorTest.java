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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the editor contains checkBox for editor and that the semantic model
 * was updated when VSM was modified. Test also that all resize were activated
 * by default. Test VP-2694 VP-2637
 * 
 * @author jdupont
 */
public class ResizeKindEditorTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Sirius Specific Model.
     */
    private static final String VSM = "resizeKind.odesign";

    /**
     * VSM path.
     */
    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/editor/vsm/";

    /**
     * Sirius Group.
     */
    private static final String GROUP = "Group";

    /**
     * Properties view tab Label.
     */
    private static final String ADVANCED = "Advanced";

    /**
     * Properties view.
     */
    protected static final String PROPERTIES = "Properties";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    /**
     * Test that the resizeKind field have 2 checkBox named Vertically and
     * Horizontally. And that the check modify the model value.
     */
    public void testCheckBoxResizeKind() {
        // Opened VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // Modify VSM, change resizeKind value.
        modifyVSM(odesignEditor);
    }

    /**
     * Modify resize kind field (Allow resizing field in properties view).
     * viewpoint specific model (.odesign).
     * 
     * @param odesignEditor
     *            the odesign editor.
     */
    private void modifyVSM(SWTBotVSMEditor odesignEditor) {
        // expand the tree : Node Mapping
        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotTreeItem nodeMappingClass = tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("testResizeKind").expandNode("Diagram").expandNode("Class").select();

        // Create new style description (square description)
        SWTBotUtils.clickContextMenu(tree, "Square");
        SWTBotTreeItem squareDescription = nodeMappingClass.expandNode("Square gray");

        // check that the 2 checkBox of resize kind is selected by default
        checkCheckBoxSelected(0, true);
        checkCheckBoxSelected(1, true);

        // Change the first checkBox checked
        bot.viewByTitle(PROPERTIES).bot().checkBox(0).click();

        // Check that the first checkBox is not selected.
        checkCheckBoxSelected(0, false);

        // Create a conditional style
        nodeMappingClass.select();
        SWTBotUtils.clickContextMenu(tree, "Conditional Style");
        
        // Create a bundled image description in conditional style
        nodeMappingClass.select("Conditional Style");
        SWTBotUtils.clickContextMenu(tree, "Basic Shape");

        // check that the 2 checkBox of resize kind are selected by default
        checkCheckBoxSelected(0, true);
        checkCheckBoxSelected(1, true);

        // Select square description style
        squareDescription.select();

        // Check that the first checkBox is not checked and the second is
        // checked
        checkCheckBoxSelected(0, false);
        checkCheckBoxSelected(1, true);
    }

    /**
     * Check that the checkBox passed in parameter is checked.
     * 
     * @param checkboxNumber
     *            number of checkBox
     * @param isSelected
     *            is selected
     */
    private void checkCheckBoxSelected(int checkboxNumber, boolean isSelected) {
        // accesses to property view
        bot.viewByTitle(PROPERTIES).setFocus();
        // accesses to tab Advanced
        SWTBotSiriusHelper.selectPropertyTabItem(ADVANCED);
        // Check if checkBox 'horizontally' and 'vertically' were checked
        if (checkboxNumber == 0) {
            if (isSelected) {
                assertEquals("The checkbox horizontally must be checked", true, bot.viewByTitle(PROPERTIES).bot().checkBox(checkboxNumber).isChecked());
            } else {
                assertEquals("The checkbox horizontally should not be checked", false, bot.viewByTitle(PROPERTIES).bot().checkBox(checkboxNumber).isChecked());
            }
            assertEquals("The name of checkBox must be : 'Horizontally'", "Horizontally", bot.viewByTitle(PROPERTIES).bot().checkBox(checkboxNumber).getText());
        } else {
            if (isSelected) {
                assertEquals("The checkbox vertically must be checked", true, bot.viewByTitle(PROPERTIES).bot().checkBox(checkboxNumber).isChecked());
            } else {
                assertEquals("The checkbox vertically should not be checked", false, bot.viewByTitle(PROPERTIES).bot().checkBox(checkboxNumber).isChecked());
            }
            assertEquals("The name of checkBox must be : 'Vertically'", "Vertically", bot.viewByTitle(PROPERTIES).bot().checkBox(checkboxNumber).getText());
        }
    }

}
