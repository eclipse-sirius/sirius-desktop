/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingItem;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemExpanded;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This abstract test class contains all common items related to the swtbot GroupingContentProviderTest test cases.
 */
public class AbstractGroupingContentProviderTest extends AbstractSiriusSwtBotGefTestCase {

    protected static final String DATA_UNIT_DIR = "data/unit/vp-4112/";

    protected static final String MODEL = "sample8.ecore";

    protected static final String VSM_FILE = "vp-4112.odesign";

    protected static final String SESSION_FILE_TO_CREATE = "vp-4112.aird";

    protected static final String REPRESENTATION_NAME = "vp-4112";

    protected static final String REPRESENTATION_DESCRIPTION_NAME = "vp-4112";

    private boolean isGroupEnable;

    private boolean isGroupByContainingFeature;

    private int groupSize;

    private int groupTrigger;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.swtbot.support.api.AbstractViewpointSwtBotGefTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // store pref values
        this.isGroupEnable = SiriusTransPlugin.getPlugin().getPreferenceStore().getBoolean(CommonPreferencesConstants.PREF_GROUP_ENABLE);
        this.isGroupByContainingFeature = SiriusTransPlugin.getPlugin().getPreferenceStore().getBoolean(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE);
        this.groupSize = SiriusTransPlugin.getPlugin().getPreferenceStore().getInt(CommonPreferencesConstants.PREF_GROUP_SIZE);
        this.groupTrigger = SiriusTransPlugin.getPlugin().getPreferenceStore().getInt(CommonPreferencesConstants.PREF_GROUP_TRIGGER);
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE_TO_CREATE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        designerProject.convertToModelingProject();
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE_TO_CREATE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.swtbot.support.api.AbstractViewpointSwtBotGefTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        // Restore pref values.
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_ENABLE, this.isGroupEnable);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, this.isGroupByContainingFeature);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_SIZE, this.groupSize);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_TRIGGER, this.groupTrigger);

        super.tearDown();
    }

    protected void setCommonUIPrefAt(boolean isGroupEnable, boolean isGroupByContainingFeature, int groupSize, int groupTrigger) {
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_ENABLE, isGroupEnable);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, isGroupByContainingFeature);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_SIZE, groupSize);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_TRIGGER, groupTrigger);
    }

    protected SWTBotTreeItem[] getPaneBasedSelectionWizardTreeitems() {
        SWTBotSiriusDiagramEditor representation = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME,
                DDiagram.class);
        representation.setFocus();
        representation.activateTool("Pane Based Selection");
        representation.click(50, 100);
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Pane Based");
        SWTBotTree tree = wizardBot.tree().select(0);
        SWTBotTreeItem swtBotTreeItem = tree.getAllItems()[0];
        SWTBotTreeItem[] items = swtBotTreeItem.getItems();
        return items;
    }

    protected SWTBotTreeItem[] getSelectionWizardTreeitems() {
        SWTBotSiriusDiagramEditor representation = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME,
                DDiagram.class);
        representation.setFocus();
        representation.activateTool("Tree Selection");
        representation.click(50, 100);
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Selection Wizard");
        SWTBotTree tree = wizardBot.tree().select(0);
        SWTBotTreeItem swtBotTreeItem = tree.getAllItems()[0];
        SWTBotTreeItem[] items = swtBotTreeItem.getItems();
        return items;
    }

    protected SWTBotTreeItem[] expandModelExplorerTree() {
        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        SWTBotTreeItem projectItem = projectExplorerBot.tree().expandNode(getProjectName());
        SWTBotTreeItem fileNode = projectItem.expandNode(MODEL);
        String[] split = MODEL.split("\\.", 2);
        String packageName = split[0];

        SWTBotTreeItem packageItem = fileNode.getNode(packageName);
        packageItem.select();
        TreeItemExpanded treeItemExpanded = new TreeItemExpanded(packageItem, packageName);

        packageItem.expand();
        bot.waitUntil(treeItemExpanded);
        SWTBotTreeItem[] items = packageItem.getItems();
        return items;
    }

    protected void assertOnTreeItem(SWTBotTreeItem[] items, boolean areGroupingItems) {
        for (final SWTBotTreeItem swtBotTreeItem : items) {
            final Object data = UIThreadRunnable.syncExec(new Result<Object>() {
                /** {@inheritDoc} */
                @Override
                public Object run() {
                    return swtBotTreeItem.widget.getData();
                }
            });
            if (areGroupingItems) {
                assertTrue(data instanceof GroupingItem);
            } else {
                assertFalse(data instanceof GroupingItem);
            }
        }
    }
}
