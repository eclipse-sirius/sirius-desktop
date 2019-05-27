/*******************************************************************************
 * Copyright (c) 2014, 2019 Obeo.
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
package org.eclipse.sirius.tests.swtbot.crossTable;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Test that the default value of the Label Expression for Intersection in cross Table is setted to "X".
 * 
 * @author jmallet
 */
public class CrossTableIntersectionTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Sirius Specific Model.
     */
    private static final String VSM = "vp2733.odesign";

    /**
     * VSM path.
     */
    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/crossTable/labelDefaultValue/vp-2733/";

    /**
     * Sirius Group.
     */
    private static final String GROUP = "Group";

    /**
     * Properties view tab Label.
     */
    private static final String LABEL = "Label";

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
     * Test that the label Expression has value "X".
     */
    public void testDefaultLabelExpressionValue() {
        // Opened VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // expand the tree : Node Mapping
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("testLabelDefaultValue").expandNode("Cross Table Description").select();
        SWTBotUtils.clickContextMenu(tree, "Intersection");
        // check label Expression value
        checkLabelDefaultValue();
    }

    /**
     * Check that the label Expression value is given by "X"..
     * 
     */
    private void checkLabelDefaultValue() {
        // accesses to property view
        SWTBotView propertiesView = bot.viewByTitle(PROPERTIES);
        propertiesView.setFocus();
        // accesses to tab Label
        SWTBotSiriusHelper.selectPropertyTabItem(LABEL, propertiesView.bot());
        // Check if value of the Label Expression is given by "X"
        assertEquals("Wrong value for LabelExpression.", "X", propertiesView.bot().text(0).getText());
    }
}
