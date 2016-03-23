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

import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckToolIsActivated;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class for STD 004.
 * 
 * @author nlepine
 */
public class STD004 extends AbstractSiriusSwtBotGefTestCase {

    private static final String CANCEL = "Cancel";

    private static final String ACLASS = "Aclass";

    private static final String DYNAMIC_INSTANCE = "Dynamic instance";

    private static final String DYNAMIC = "Dynamic";

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private static final String PATH = "data/unit/std/004/";

    private static final String SEMANTIC_MODEL_FILENAME = "STD-TEST-004.ecore";

    private static final String FILE_DIR = "/";

    private static final String MODEL_PACKAGE = "RootSTDTestCase";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootSTDTestCase package entities";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + getProjectName() + "/" + SEMANTIC_MODEL_FILENAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSTD004() throws Exception {

        final UIResource ecoreEcoreResource = new UIResource(designerProject, FILE_DIR, SEMANTIC_MODEL_FILENAME);

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        final UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode(MODEL_PACKAGE);
        final UIDiagramRepresentation representation = localSession.newDiagramRepresentation(REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_DESCRIPTION_NAME).on(ecoreTreeItem).withDefaultName()
                .ok();

        // Eclipse 4.x, set focus.
        representation.getEditor().click(100, 100);

        // Initialization of a bot on the diagram
        final SWTBotSiriusDiagramEditor editor = representation.getEditor();

        // see Bug 424429
        SWTBotView modelExplorerView = bot.viewByTitle("Model Explorer");
        modelExplorerView.setFocus();
        editor.setFocus();
        editor.save();

        /* activate dynamic layer */
        representation.changeLayerActivation(DYNAMIC);

        // Add external java action
        CheckToolIsActivated ctia = new CheckToolIsActivated(editor, DYNAMIC_INSTANCE);
        editor.activateTool(DYNAMIC_INSTANCE);
        bot.waitUntil(ctia);

        SWTBotGefEditPart aClassEditPartBot = editor.getEditPart(ACLASS).parent();
        aClassEditPartBot.click();

        bot.waitUntil(Conditions.shellIsActive("Create Dynamic Instance"));
        bot.button(CANCEL).click();

        localSession.close(false);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
