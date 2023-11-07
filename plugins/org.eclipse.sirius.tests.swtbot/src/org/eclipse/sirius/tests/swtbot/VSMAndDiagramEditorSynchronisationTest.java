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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;

/**
 * Test editor opening when the VSM is modified
 * 
 * @author nlepine
 */
public class VSMAndDiagramEditorSynchronisationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String NEW_E_CLASS_1TT = "new EClass 1TT";

    private static final String DARK_PURPLE = "dark_purple";

    private static final String DARK_RED = "dark_red";

    private static final String PROPERTIES = "Properties";

    private static final String FLAT_CONTAINER_STYLE_DESCRIPTION_1 = "Gradient dark_red to white";

    private static final String EC_E_CLASS = "EC EClass";

    private static final String DEFAULT = "Default";

    /** Sirius Specific Model. */
    private static final String VSM = "diagram.odesign";

    /** Test repository. */
    private static final String DATA_UNIT_DIR = "data/unit/session/vsmChange/";

    /** Session file. */
    private static final String SESSION_FILE = "diagram.aird";

    /** UML File. */
    private static final String ECORE_FILE = "diagram.ecore";

    /** File directory. */
    private static final String FILE_DIR = "/";

    /** Sirius Group. */
    private static final String GROUP = "Ecore Editing Workbench V4.6";

    /** Properties view tab Color. */
    private static final String COLOR = "Color";

    /** Sirius name. */
    private static final String VIEWPOINT_NAME = "Diagram";

    /** Representation name. */
    private static final String REPRESENTATION_NAME = "Entities";

    /** Semantic model instance. */
    private static final String REPRESENTATION_INSTANCE_NAME = "Diagram";

    /** Current diagram. */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, ECORE_FILE, SESSION_FILE, VSM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * Test with opened session. - open and modify VSM without opened editors
     */
    public void testVSMChangesWithoutOpenedEditor() {
        // Open odesign file
        openVSM();

        // Select a node mapping
        SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();
        activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(REPRESENTATION_NAME).expandNode(DEFAULT)
                .expandNode(EC_E_CLASS).select(FLAT_CONTAINER_STYLE_DESCRIPTION_1);

        // Change the color
        modifyVSM(DARK_RED, DARK_PURPLE);

        // save the VSM
        activeEditor.setFocus();
        activeEditor.save();

        // Open diagram editor
        openDiagramEditor();

        // Check diagram opening
        checkDiagramIsCorrectlyOpened(DARK_PURPLE);

        // Check that the editor is correctly opened.
        bot.waitUntil(new EditorCorrectlyOpenedCondition(editor));

        // Close session
        editor.close();

        localSession.closeAndDiscardChanges();

    }

    private class EditorCorrectlyOpenedCondition extends DefaultCondition {

        SWTBotSiriusDiagramEditor editor;

        public EditorCorrectlyOpenedCondition(SWTBotSiriusDiagramEditor editor) {
            this.editor = editor;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean test() throws Exception {
            return editor.isDirty();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getFailureMessage() {
            return null;
        }

    }

    /**
     * Test with opened session. - open and modify VSM with opened editors
     */
    public void testVSMChangesWithOpenedEditor() {
        // Open diagram editor
        openDiagramEditor();

        // Check diagram opening
        checkDiagramIsCorrectlyOpened(DARK_RED);

        // Open odesign file
        openVSM();

        // Select a node mapping
        SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();
        activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(REPRESENTATION_NAME).expandNode(DEFAULT)
                .expandNode(EC_E_CLASS).select(FLAT_CONTAINER_STYLE_DESCRIPTION_1);

        // Change the color
        modifyVSM(DARK_RED, DARK_PURPLE);

        // save the VSM
        activeEditor.setFocus();
        activeEditor.saveAndClose();

        // Check diagram opening
        editor.setFocus();
        editor.mainEditPart().select();
        editor.mainEditPart().click();
        editor.mainEditPart().focus();
        editor.refresh();
        checkDiagramIsCorrectlyOpened(DARK_PURPLE);

        // Close session
        localSession.closeAndDiscardChanges();

    }

    /**
     * Check that the editor is correctly opened.
     */
    private void checkDiagramIsCorrectlyOpened(String color) {
        SWTBotUtils.waitAllUiEvents();
        SWTBotGefEditPart editPart = selectAndCheckEditPart(NEW_E_CLASS_1TT, AbstractDiagramListEditPart.class);
        IFigure figure = ((AbstractGraphicalEditPart) editPart.part()).getFigure();
        assertThat(figure.getBackgroundColor(), equalTo(VisualBindingManager.getDefault().getColorFromName(color)));
    }

    /**
     * Open the diagram editor
     */
    private void openDiagramEditor() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Modify the VSM
     */
    private void modifyVSM(String oldColor, String color) {
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(COLOR, propertiesBot.bot());
        SWTBotUtils.waitAllUiEvents();
        SWTBotCCombo comboBox = propertiesBot.bot().ccomboBox(oldColor);
        comboBox.setFocus();
        comboBox.setSelection(color);
    }

    /**
     * Open the VSM
     */
    private void openVSM() {
        SWTBotView projectExplorer = bot.viewByTitle("Model Explorer");
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM).doubleClick();
    }

    /**
     * Return the selected edit part.
     * 
     * @param name
     * @param type
     * @return the selected edit part
     */
    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);

        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();

        return botPart;
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

}
