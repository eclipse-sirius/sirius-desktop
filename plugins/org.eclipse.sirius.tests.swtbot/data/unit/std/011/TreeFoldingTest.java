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
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.PlatformUI;

import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotDesignerEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper.EdgeData;

/**
 * Test class for tree folding.
 *
 * @author smonnier
 */
public class TreeFoldingTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "TC 1852";

    private static final String REPRESENTATION_NAME = "Package Hierarchy With Nodes Target";

    private static final String VIEWPOINT_NAME = "Design TC1852";

    private static final String MODEL = "tc1852.ecore";

    private static final String SESSION_FILE = "tc1852.aird";

    private static final String VSM_FILE = "tc1852.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/TreeFolding/";

    private static final String FILE_DIR = "/";

    private static final String[] FIRST_TREE = {"P1A1", "P1A2", "P1A3", "P1A1A", "P1A1B"};

    private static final String[] SECOND_TREE = {"P1B1", "P1B2"};

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current editor.
     */
    protected SWTBotDesignerEditor editor;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(MODEL, SESSION_FILE, VSM_FILE);

    }

    private void copyFileToTestProject(final String... fileNames) {
        for (final String fileName : fileNames) {
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + fileName, getProjectName() + "/" + fileName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        diagram = localSession.getLocalSessionBrowser().perCategory().selectSirius(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME).selectRepresentationInstance(
                REPRESENTATION_INSTANCE_NAME, UIDiagramRepresentation.class).open();

        editor = diagram.getEditor();
    }

    /**
     * Test method.
     *
     * @throws Exception
     *             Test error.
     */
    public void testCheckLabelSelection() throws Exception {
        SWTBotPreferences.TIMEOUT = 1000;

        editor.drag("P3", 150, 140);
        bot.sleep(500);

        checkEditPartAreVisible(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        editor.click(400, 255);
        bot.sleep(500);
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        editor.click(690, 255);
        bot.sleep(500);
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreHidden(SECOND_TREE);

        editor.click(695, 240);
        bot.sleep(500);
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        bot.menu("Edit").menu("Undo Change Fold Property").click();
        bot.sleep(500);
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreHidden(SECOND_TREE);

        bot.menu("Edit").menu("Undo Change Fold Property").click();
        bot.sleep(500);
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        bot.menu("Edit").menu("Undo Change Fold Property").click();
        bot.sleep(500);
        checkEditPartAreVisible(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        bot.menu("Edit").menu("Undo Set Location or Size").click();
        bot.sleep(500);

        bot.menu("Edit").menu("Redo Set Location or Size").click();
        bot.sleep(500);

        bot.menu("Edit").menu("Redo Change Fold Property").click();
        bot.sleep(500);
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);

        bot.menu("Edit").menu("Redo Change Fold Property").click();
        bot.sleep(500);
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreHidden(SECOND_TREE);

        bot.menu("Edit").menu("Redo Change Fold Property").click();
        checkEditPartAreHidden(FIRST_TREE);
        checkEditPartAreVisible(SECOND_TREE);
    }

    private void checkEditPartAreVisible(String[] labels) {
        for (String label : labels) {
            // Check edit part is came back
            final SWTBotGefEditPart editPart = editor.getEditPart(label);
            assertThat("The edit part named "+label+" should be visible but is not.", editPart, notNullValue());
        }
    }

    private void checkEditPartAreHidden(String[] labels) {
        for (String label : labels) {
            try {
                editor.getEditPart(label);
                fail(WidgetNotFoundException.class + " expected");

            } catch (final WidgetNotFoundException e) {
                // Expected, the edit part must not be found
            }
        }
    }
}
