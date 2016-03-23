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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class.
 * 
 * @author smonnier
 */
public class InitializeEmptySessionTest extends AbstractScenarioTestCase {

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testInitializeSession() throws Exception {

        final UIResource ecoreEcoreResource = new UIResource(designerProject, MODELS_DIR, "Ecore.ecore");
        final UIResource ecoreAirdResource = new UIResource(designerProject, MODELS_DIR, "Ecore.aird");

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        final UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode("ecore");
        UIDiagramRepresentation openedDiagram = localSession.newDiagramRepresentation("ecore package entities", "Entities").on(ecoreTreeItem).withDefaultName().ok();

        // Set focus in 4.x
        openedDiagram.getEditor().click(0, 0);

        // Save and close the editor
        SWTBotCommonHelper.saveCurrentEditor(localSession.getOpenedSession());

        // save the editor
        // SWTBotCommonHelper.saveCurrentEditor();
        SWTBotCommonHelper.closeCurrentEditor();

        // bot.button("No").click();

        // Create an entity diagram on the top element of the model
        // modelContentView = bot.viewByTitle("Model Content");

        localSession.closeNoDirty();
        designerProject.deleteResource(ecoreAirdResource);
    }

}
