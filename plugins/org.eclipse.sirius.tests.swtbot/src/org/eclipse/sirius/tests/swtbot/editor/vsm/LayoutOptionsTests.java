/*******************************************************************************
 * Copyright (c) 2018, 2020 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests layout option modifications for a layout algorithm in a VSM.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 */
public class LayoutOptionsTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String LAYOUT_ALGORITHM_LABEL = "ELK Layered";

    private static final String PATH = "data/unit/odesign/VP-2651/";

    private static final String MODELER_RESOURCE_NAME = "VP-2651.odesign";

    private static final String ECORE_RESOURCE_NAME = "EcoreResourceWithNullEPackageProperties.ecore";

    private static final String GROUP_NAME = "VP-2651";

    private static final String VIEWPOINT_NAME = "VP-2651_Viewpoint";

    private static final String DIAGRAM_DESCRIPTION_NAME = "VP-2651_DiagramDescription";

    private SWTBotEditor odesignEditorBot;

    private SWTBotView propertiesView;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + MODELER_RESOURCE_NAME, getProjectName() + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + ECORE_RESOURCE_NAME, getProjectName() + "/" + ECORE_RESOURCE_NAME);

        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(MODELER_RESOURCE_NAME).doubleClick();

        odesignEditorBot = bot.activeEditor();
        odesignEditorBot.setFocus();
        SWTBotTreeItem selectedItem = odesignEditorBot.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + MODELER_RESOURCE_NAME).expandNode(GROUP_NAME).expandNode(VIEWPOINT_NAME)
                .expandNode(DIAGRAM_DESCRIPTION_NAME).getNode(LAYOUT_ALGORITHM_LABEL);
        selectedItem.click();
        SWTBotUtils.waitAllUiEvents();

    }

    /**
     * Tests that layout option addition and removal for a layout algorithm is working as expected.
     */
    public void testLayoutOptionModifications() {

        propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();

        SWTBot propertyBot = propertiesView.bot();

        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                try {
                    propertyBot.button(0);
                    return true;
                } catch (WidgetNotFoundException e) {
                    return false;
                }
            }

            @Override
            public String getFailureMessage() {
                return "Properties view has not been initialized correctly.";
            }

        });

        SWTBotButton addOptionButton = propertyBot.button(0);

        addOptionButton.click();
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                SWTBotShell activeShell = bot.activeShell();

                return "Override options".equals(activeShell.getText());
            }

            @Override
            public String getFailureMessage() {
                return "The layout option dialog should have opened.";
            }
        });

        SWTBot optionOverrideBot = bot.activeShell().bot();
        SWTBotTable table = optionOverrideBot.table(0);

        assertEquals("Not all layout options are available", 99, table.rowCount());

        SWTBotTableItem tableItem = table.getTableItem(1);
        tableItem.check();
        tableItem = table.getTableItem(2);
        tableItem.check();
        optionOverrideBot.button("OK").click();

        SWTBotCheckBox checkBox = propertyBot.checkBox(0);
        checkBox.click();

        SWTBotText text = propertyBot.text(0);
        text.setFocus();
        text.setText("1.5");

        propertiesView.setFocus();

        CustomSiriusEditor customSiriusEditor = (CustomSiriusEditor) odesignEditorBot.getReference().getEditor(true);
        List<LayoutOption> layoutOptions = getLayoutOptions(customSiriusEditor);
        assertEquals("Layout options have not been added to the VSM.", 2, layoutOptions.size());
        for (LayoutOption layoutOption : layoutOptions) {
            if (layoutOption instanceof BooleanLayoutOption) {
                BooleanLayoutOption booleanLayoutOption = (BooleanLayoutOption) layoutOption;
                assertTrue("The option value has not been changed from properties view.", booleanLayoutOption.isValue());
            } else if (layoutOption instanceof DoubleLayoutOption) {
                DoubleLayoutOption doubleLayoutOption = (DoubleLayoutOption) layoutOption;
                assertEquals("The option value has not been changed from properties view.", 1.5, doubleLayoutOption.getValue());
            } else {
                fail("Wrong layout options have been added.");
            }
        }

        propertyBot.button(2).click();

        try {
            propertyBot.text(0);
            fail("The double layout option should have been removed.");
        } catch (WidgetNotFoundException e) {
            propertyBot.checkBox(0);
        }

        layoutOptions = getLayoutOptions(customSiriusEditor);
        assertEquals("The double layout option should have been removed", 1, layoutOptions.size());
    }

    private List<LayoutOption> getLayoutOptions(CustomSiriusEditor customSiriusEditor) {
        List<LayoutOption> layoutOptions = new ArrayList<>();
        customSiriusEditor.getEditingDomain().getResourceSet().getResources().get(0).getAllContents().forEachRemaining((elmt) -> {
            if (elmt instanceof LayoutOption) {
                layoutOptions.add((LayoutOption) elmt);
            }
        });
        return layoutOptions;
    }

    @Override
    protected void tearDown() throws Exception {
        odesignEditorBot.close();
        odesignEditorBot = null;
        propertiesView = null;
        super.tearDown();
    }
}
