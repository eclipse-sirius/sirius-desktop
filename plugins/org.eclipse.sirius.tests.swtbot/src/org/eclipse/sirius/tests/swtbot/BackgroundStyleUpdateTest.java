/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the background style is correctly changed after the semantic change.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class BackgroundStyleUpdateTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "TestDiagram.xmi";

    private static final String META_MODEL = "dynamic_background.ecore";

    private static final String SESSION_FILE = "TestDiagram.aird";

    private static final String VSM_FILE = "background.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/changeBackgroundStyle/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "new Diagram";

    private static final String REPRESENTATION_NAME = "Diagram";

    private static final String CONTAINER_NAME = "I am a test";

    private static final String PROPERTIES = "Properties";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE, META_MODEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Ensure that the style of FlatContainerStyle directly after the semantic change.
     */
    public void testStyleCustomizationOfFlatContainerStyle() {
        // Ensure that the background style do not change on representation
        // opening
        SWTBotGefEditPart editPart = selectAndCheckEditPart(CONTAINER_NAME, DNodeContainerEditPart.class);
        checkBackgroundStyle(editPart, "GradientTopToBottom");
        // Change the container background style
        changeBackgroundStyle(true);
        // Check if the background style is correctly changed
        checkBackgroundStyle(editPart, "Liquid");
        // Reset the container background style
        changeBackgroundStyle(false);
        // Check if the background style is correctly changed
        checkBackgroundStyle(editPart, "GradientTopToBottom");
    }

    private void checkBackgroundStyle(SWTBotGefEditPart editPart, String backgroundStyleName) {
        DDiagramElement diagramElement = DiagramElementEditPartOperation.resolveDiagramElement((IDiagramElementEditPart) editPart.part());
        BackgroundStyle backgroundStyle = ((FlatContainerStyle) diagramElement.getStyle()).getBackgroundStyle();
        assertEquals("Wrong background style of the container '" + diagramElement.getName() + "'", backgroundStyleName, backgroundStyle.getName());
    }

    private void changeBackgroundStyle(boolean liquid) {
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Semantic", propertiesBot.bot());
        SWTBotTreeItem style = propertiesBot.bot().tree(0).getAllItems()[0].getNode("Liquid");
        style.click();
        // In photon, the feature widget is not a combo anymore.
        if (TestsUtil.isPhotonPlatformOrLater()) {
            SWTBotCheckBox lineStyle = bot.viewByTitle(PROPERTIES).bot().checkBox();
            if (liquid) {
                lineStyle.select();
            } else {
                lineStyle.deselect();
            }
        } else {
            SWTBotCCombo lineStyle = bot.viewByTitle(PROPERTIES).bot().ccomboBox();
            if (liquid) {
                lineStyle.setSelection(1);
            } else {
                lineStyle.setSelection(0);
            }
        }
        editor.setFocus();
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);
        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();
        bot.waitUntil(new CheckSelectedCondition(editor, name));
        return botPart;
    }

}
