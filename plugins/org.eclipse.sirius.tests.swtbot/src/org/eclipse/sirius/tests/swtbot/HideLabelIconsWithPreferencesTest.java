/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.junit.Assert;

/**
 *
 * @author alagarde
 */
public class HideLabelIconsWithPreferencesTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * The {@link SWTBotSiriusDiagramEditor} to use.
     */
    protected SWTBotSiriusDiagramEditor editor;

    private static final String REPRESENTATION_INSTANCE_NAME = "VP1257";

    private static final String REPRESENTATION_NAME = "VP1257Diagram";

    private static final String MODEL = "vp1257.ecore";

    private static final String SESSION_FILE = "vp1257.aird";

    private static final String VSM_FILE = "vp1257.odesign";

    private static final String DATA_UNIT_DIR = "/data/unit/tools/hide-reveal/tc-1257/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private boolean oldPrefValueForConnectors;

    private boolean oldPrefValueForShapes;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // We restore the old peferences values
        changeHideLabeIconPreferencesUsingAPI(oldPrefValueForShapes, oldPrefValueForConnectors);
        super.tearDown();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        // We store the old preferences values
        IPreferenceStore pluginPreferences = DiagramUIPlugin.getPlugin().getPreferenceStore();
        oldPrefValueForConnectors = pluginPreferences.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_CONNECTORS.name());
        oldPrefValueForShapes = pluginPreferences.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_SHAPES.name());

    }

    /**
     * Ensures that label icon are correctly displayed when modifying the preferences using API.
     */
    public void testChangeHidingLabelIconsUsingAPI() {
        // Test 1
        // We first test that all is visible when preference have not been
        // changed
        checkLabelIconsAreCorrectlyDisplayed(false, false);

        // Test 2
        // We change the label icon preferences by hiding everything
        changeHideLabeIconPreferencesUsingAPI(true, true);
        // This should not change the current editor
        checkLabelIconsAreCorrectlyDisplayed(false, false);
        checkHideLabelIconPreferences(true, true);
        // We close the old editor and open a new One
        closeOldEditorAndOpenNewOne();
        // Preferences should have been used
        checkLabelIconsAreCorrectlyDisplayed(true, true);

        // Test 3
        // We change the label icon preferences by hiding only connectors
        changeHideLabeIconPreferencesUsingAPI(true, false);
        // This should not change the current editor
        checkLabelIconsAreCorrectlyDisplayed(true, true);
        checkHideLabelIconPreferences(true, false);
        // We close the old editor and open a new One
        closeOldEditorAndOpenNewOne();
        // Preferences should have been used
        checkLabelIconsAreCorrectlyDisplayed(true, false);

        // Test 4
        // We change the label icon preferences by hiding only shapes
        changeHideLabeIconPreferencesUsingAPI(false, true);
        // This should not change the current editor
        checkLabelIconsAreCorrectlyDisplayed(true, false);
        checkHideLabelIconPreferences(false, true);
        // Test 5
        // We finally test that newly created elements are correctly displayed
        createNewDiagramElementAndCheckDisplay(false, true);

        // We close the old editor and open a new One
        closeOldEditorAndOpenNewOne();
        // Preferences should have been used
        checkLabelIconsAreCorrectlyDisplayed(false, true);

    }

    /**
     * Ensures that label icon are correctly displayed when modifying the preferences using API, faced to filters
     * activations/deactivations.
     */
    public void testChangeLabelIconsHidingWithFilters() {
        // We activate the oldUi to be able to active filters

        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        // Test 1 :
        // Activate and deactivate filters with hidden shapes label icons
        changeHideLabeIconPreferencesUsingAPI(true, false);
        closeOldEditorAndOpenNewOne();
        activateFilter();
        deactivateFilter();
        checkLabelIconsAreCorrectlyDisplayed(true, false);

        // Test 2 :
        // Activate filter, hide connectors label icons and then deactivate
        // filter
        activateFilter();
        changeHideLabeIconPreferencesUsingAPI(false, true);
        closeOldEditorAndOpenNewOne();
        deactivateFilter();
        checkLabelIconsAreCorrectlyDisplayed(false, true);
    }

    /**
     * Ensures that label icon are correctly displayed when modifying the preferences using Eclipse UI.
     */

    public void testChangeLabelIconsHidingUsingPreferencesUI() {
        // Test 1
        // We first test that all is visible when preference have not been
        // changed
        checkLabelIconsAreCorrectlyDisplayed(false, false);

        // Test 2
        // We change the label icon preferences by hiding everything
        changeHideLabelIconPreferences(true, true);
        // This should not change the current editor
        checkLabelIconsAreCorrectlyDisplayed(false, false);
        checkHideLabelIconPreferences(true, true);
        // We close the old editor and open a new One
        closeOldEditorAndOpenNewOne();
        // Preferences should have been used
        checkLabelIconsAreCorrectlyDisplayed(true, true);

        // Test 3
        // We change the label icon preferences by hiding only connectors
        changeHideLabelIconPreferences(true, false);
        // This should not change the current editor
        checkLabelIconsAreCorrectlyDisplayed(true, true);
        // We close the old editor and open a new One
        closeOldEditorAndOpenNewOne();
        // Preferences should have been used
        checkLabelIconsAreCorrectlyDisplayed(true, false);

        // Test 4
        // We change the label icon preferences by hiding only shapes
        changeHideLabelIconPreferences(false, true);
        // This should not change the current editor
        checkLabelIconsAreCorrectlyDisplayed(true, false);
        // Test 5
        // We test that newly created elements are correctly displayed
        createNewDiagramElementAndCheckDisplay(false, true);
        // We close the old editor and open a new One
        closeOldEditorAndOpenNewOne();
        // Preferences should have been used
        checkLabelIconsAreCorrectlyDisplayed(false, true);

        // Test 6
        // We change the label icon preferences by hiding only shapes
        changeHideLabelIconPreferences(true, true);
        // This should not change the current editor
        checkLabelIconsAreCorrectlyDisplayed(false, true);
        // We close the old editor and open a new One
        closeOldEditorAndOpenNewOne();
        // Preferences should have been used
        checkLabelIconsAreCorrectlyDisplayed(true, true);
    }

    /**
     * Ensures that label icons are correctly displayed according to the given parameters.
     *
     * @param hideLabelIconsForShapes
     *            indicates if all label icons of shapes (node and containers) should be hidden
     * @param hideLabelIconsForConnectors
     *            indicates if all label icons of connectors (edges) should be hidden
     */
    protected void checkLabelIconsAreCorrectlyDisplayed(boolean hideLabelIconsForShapes, boolean hideLabelIconsForConnectors) {

        Image containerLabelIcon = getLabelIconOfEditPart("p2");
        Assert.assertEquals("The container's label icon isn't correctly displayed : ", hideLabelIconsForShapes, (containerLabelIcon == null));
        Image nodeLabelIcon = getLabelIconOfEditPart("C2");
        Assert.assertEquals("The node label icon isn't correctly displayed : ", hideLabelIconsForShapes, (nodeLabelIcon == null));
        Image edgeLabelIcon = getLabelIconOfEditPart("isSuperTypeOfC2");
        Assert.assertEquals("The edge's label icon isn't correctly displayed : ", hideLabelIconsForConnectors, (edgeLabelIcon == null));
    }

    /**
     * Returns the current icon of the edit part with the given name (null if no icon).
     *
     * @param editPartName
     *            the name of the edit part
     * @return the current icon of the edit part with the given name (null if no icon)
     */
    private Image getLabelIconOfEditPart(String editPartName) {
        // We must not call , that would recalculate the icon according to
        // preferences
        IFigure editPartFigure = ((AbstractDiagramNameEditPart) editor.getEditPart(editPartName).part()).getFigure();
        Image labelIcon = null;
        if (editPartFigure instanceof SiriusWrapLabel) {
            labelIcon = ((SiriusWrapLabel) editPartFigure).getIcon();
        } else {
            labelIcon = ((Label) editPartFigure).getIcon();
        }
        return labelIcon;
    }

    /**
     * Changes the values of preferences related to label icons hiding using standard API.
     *
     * @param hideLabelIconsForShapes
     *            indicates if all label icons of shapes (node and containers) should be hidden
     * @param hideLabelIconsForConnectors
     *            indicates if all label icons of connectors (edges) should be hidden
     */
    protected void changeHideLabeIconPreferencesUsingAPI(final boolean hideLabelIconsForShapes, final boolean hideLabelIconsForConnectors) {
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                IPreferenceStore pluginPreferences = DiagramUIPlugin.getPlugin().getPreferenceStore();
                pluginPreferences.setValue(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_CONNECTORS.name(), hideLabelIconsForConnectors);
                pluginPreferences.setValue(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_SHAPES.name(), hideLabelIconsForShapes);
            }
        });
    }

    /**
     * Changes the values of preferences related to label icons hiding using the preferences UI.
     *
     * @param hideLabelIconsForShapes
     *            indicates if all label icons of shapes (node and containers) should be hidden
     * @param hideLabelIconsForConnectors
     *            indicates if all label icons of connectors (edges) should be hidden
     */
    protected void changeHideLabelIconPreferences(boolean hideLabelIconsForShapes, boolean hideLabelIconsForConnectors) {
        bot.menu("Window").menu("Preferences").click();
        SWTBot preferenceBot = SWTBotSiriusHelper.getShellBot("Preferences");
        preferenceBot.tree().getTreeItem("Sirius").expand().select().getNode("Sirius Diagram").expand().select().getNode("Appearance").select();

        if (hideLabelIconsForShapes) {
            preferenceBot.checkBoxInGroup(Messages.DiagramAppearancePreferencePage_hideShapeLabelIconsLabel, Messages.DiagramAppearancePreferencePage_labelIconsGroupText).select();
        } else {
            preferenceBot.checkBoxInGroup(Messages.DiagramAppearancePreferencePage_hideShapeLabelIconsLabel, Messages.DiagramAppearancePreferencePage_labelIconsGroupText).deselect();
        }

        if (hideLabelIconsForConnectors) {
            preferenceBot.checkBoxInGroup(Messages.DiagramAppearancePreferencePage_hideConnectorLabelIconsLabel, Messages.DiagramAppearancePreferencePage_labelIconsGroupText).select();
        } else {
            preferenceBot.checkBoxInGroup(Messages.DiagramAppearancePreferencePage_hideConnectorLabelIconsLabel, Messages.DiagramAppearancePreferencePage_labelIconsGroupText).deselect();
        }

        preferenceBot.button(TestsUtil.isOxygenPlatform() ? JFaceResources.getString("PreferencesDialog.okButtonLabel") : IDialogConstants.OK_LABEL).click();

    }

    /**
     * Tests that label incons hiding preferences are correctly displayed in the preference UI.
     *
     * @param expectedHideLabelIconsForShapes
     *            expected value for the hide label icons for shapes properties
     * @param expectedHideLabelIconsForConnectors
     *            expected value for the hide label icons for connectors properties
     */
    protected void checkHideLabelIconPreferences(boolean expectedHideLabelIconsForShapes, boolean expectedHideLabelIconsForConnectors) {
        bot.menu("Window").menu("Preferences").click();
        SWTBot preferenceBot = SWTBotSiriusHelper.getShellBot("Preferences");
        preferenceBot.tree().getTreeItem("Sirius").expand().select().getNode("Sirius Diagram").expand().select().getNode("Appearance").select();

        Assert.assertEquals("Preference " + Messages.DiagramAppearancePreferencePage_hideShapeLabelIconsLabel + " has not expected value ", expectedHideLabelIconsForShapes,
                preferenceBot.checkBoxInGroup(Messages.DiagramAppearancePreferencePage_hideShapeLabelIconsLabel, Messages.DiagramAppearancePreferencePage_labelIconsGroupText).isChecked());
        Assert.assertEquals("Preference " + " Hide label icons on connectors" + " has not expected value ", expectedHideLabelIconsForConnectors,
                preferenceBot.checkBoxInGroup(Messages.DiagramAppearancePreferencePage_hideConnectorLabelIconsLabel, Messages.DiagramAppearancePreferencePage_labelIconsGroupText).isChecked());
        preferenceBot.shell("Preferences").close();
    }

    /**
     * Closes the diagram editor and opens a new one.
     */
    protected void closeOldEditorAndOpenNewOne() {
        editor.close();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Creates a new node and ensures it is displayed correctly.
     *
     * @param mustHideNodeLabelIcon
     *            indicates if the new node's label icon must be hidden
     * @param mustHideEdgeLabelIcon
     *            indicates if the new edge's label icon must be hidden
     */
    protected void createNewDiagramElementAndCheckDisplay(boolean mustHideNodeLabelIcon, boolean mustHideEdgeLabelIcon) {
        editor.activateTool("CreateEClass");
        editor.click(15, 15);

        Image nodeLabelIcon = getLabelIconOfEditPart("newTestClass");
        Assert.assertEquals("The new Node's label icon isn't correctly displayed : ", mustHideNodeLabelIcon, (nodeLabelIcon == null));

        Image edgeLabelIcon = getLabelIconOfEditPart("isSuperTypeOfnewTestClass");
        Assert.assertEquals("The new Edge's label icon isn't correctly displayed : ", mustHideEdgeLabelIcon, (edgeLabelIcon == null));
    }

    private void activateFilter() {
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        SWTBotUtils.waitAllUiEvents();
        outlineView.filters();
        SWTBotUtils.waitAllUiEvents();
        outlineView.activateFilter("myClassAndEdgeFilter");
        SWTBotUtils.waitAllUiEvents();
    }

    private void deactivateFilter() {
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        SWTBotUtils.waitAllUiEvents();
        outlineView.filters();
        SWTBotUtils.waitAllUiEvents();
        outlineView.activateFilter("myClassAndEdgeFilter");
        SWTBotUtils.waitAllUiEvents();
    }

}
