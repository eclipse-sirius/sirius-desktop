/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.std;

import java.text.MessageFormat;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class for STD 006.
 * 
 * @author nlepine
 */
public class STD006 extends AbstractSiriusSwtBotGefTestCase {

    private final int DEFAULT_SLEEP_TIMER = 500;

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private static final String MODEL = "STD-TEST-006.ecore";

    private static final String DATA_UNIT_DIR = "data/unit/std/006/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootSTDTestCase package entities";

    private static final String MODEL_PACKAGE = "RootSTDTestCase";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_PACKAGE_INSTANCE_NAME_DIGRAM = "New Package Entities";

    private static final String NEW_PACKAGE_REPRESENTATION = "APackage package entities";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    /**
     * Test method. <BR>
     * TODO : Complete this test to navigate :
     * <UL>
     * <LI>from diagram to diagram</LI>
     * <LI>from diagram to table</LI>
     * <LI>from table to diagram</LI>
     * <LI>from table to table</LI>
     * </UL>
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSTD006() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException:
             * Could not find node with text: STD-TEST-006.ecore at
             * org.eclipse.swtbot
             * .swt.finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem
             * .java:334) at
             * org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
             * .getNode(SWTBotTreeItem.java:308) at
             * org.eclipse.swtbot.swt.finder
             * .widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:346) at
             * org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.
             * getUIItemFromResource(UIProject.java:152) at
             * org.eclipse.sirius.tests
             * .swtbot.support.api.business.UIProject.selectResource
             * (UIProject.java:122) at
             * org.eclipse.sirius.tests.swtbot.support.api
             * .business.UIPerspective
             * .openSessionCreationWizardFromSemanticResource
             * (UIPerspective.java:188)
             */
            return;
        }

        final UIResource ecoreEcoreResource = new UIResource(designerProject, FILE_DIR, MODEL);

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        final UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode(MODEL_PACKAGE);
        final UIDiagramRepresentation representation = localSession.newDiagramRepresentation(REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_DESCRIPTION_NAME).on(ecoreTreeItem).withDefaultName()
                .ok();

        // Eclipse 4.x, set focus.
        final SWTBotSiriusDiagramEditor editor = representation.getEditor();
        editor.click(100, 100);

        // see Bug 424429
        SWTBotView modelExplorerView = bot.viewByTitle("Model Explorer");
        modelExplorerView.setFocus();
        editor.setFocus();
        editor.save();

        // Adding an item of the palette on the diagram
        // Add a package
        editor.activateTool("Package");
        editor.click(50, 100);
        bot.sleep(DEFAULT_SLEEP_TIMER);

        /* Navigate to entities diagram */
        editor.click(50, 50);
        editor.clickContextMenu(new Point(50, 50), REPRESENTATION_PACKAGE_INSTANCE_NAME_DIGRAM);
        bot.shell(MessageFormat.format(Messages.createRepresentationInputDialog_Title, REPRESENTATION_DESCRIPTION_NAME)).activate();
        bot.button("OK").click();

        assertNotSame(editor, bot.activeEditor());
        assertEditorIsNotError("Right click navigation editor did not opened correctly", bot.activeEditor());
        assertEquals(bot.activeEditor().getTitle(), NEW_PACKAGE_REPRESENTATION);

        localSession.close(false);

    }

}
