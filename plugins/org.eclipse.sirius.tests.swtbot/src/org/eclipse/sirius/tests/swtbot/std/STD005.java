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

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class for STD 005.
 * 
 * @author nlepine
 */
public class STD005 extends AbstractSiriusSwtBotGefTestCase {

    private final int DEFAULT_SLEEP_TIMER = 500;

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private static final String MODEL = "STD-TEST-005.ecore";

    private static final String DATA_UNIT_DIR = "data/unit/std/005/";

    private static final String FILE_DIR = "/";

    private static final String MODEL_PACKAGE = "RootSTDTestCase";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootSTDTestCase package entities";

    private static final String NEW_E_CLASS = "NewEClass3";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSTD005() throws Exception {

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
        // Add a class
        editor.activateTool("Class");
        editor.click(50, 100);
        bot.sleep(DEFAULT_SLEEP_TIMER);

        /* Open Properties view */
        final SWTBotView propertiesView = designerViews.openView("General", "Properties");

        final SWTBot propertiesViewbot = propertiesView.bot();
        propertiesView.setFocus();
        SWTBotTreeItem item = propertiesViewbot.tree(0).getTreeItem(NEW_E_CLASS).getNode("Name").select();
        item.setFocus();

        assertEquals(item.cell(1), NEW_E_CLASS);

        localSession.close(false);

    }

}
