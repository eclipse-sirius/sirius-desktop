/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Tests for drag'n'dropping elements from the model content view on a diagram.
 * 
 * @author pcdavid
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DragAndDropFromControlledResourceTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "Root.ecore";

    private static final String CONTROLLED_MODEL = "Root_Package1.ecore";

    private static final String SESSION_FILE = "Root.aird";

    private static final String VSM_FILE = "dnd_from_model_content.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/vp-2692/";

    private static final String FILE_DIR = "/";

    private UIResource ecoreEcoreResource;

    private SWTBotTreeItem semanticResourceNode;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, CONTROLLED_MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        ecoreEcoreResource = new UIResource(designerProject, MODEL);
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        new SWTWorkbenchBot().viewById("org.eclipse.ui.views.ContentOutline").close();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
    }

    /**
     * Drag a semantic element contained in a controlled resource from the model
     * content view on the diagram, and check that the corresponding edit part
     * is created (indicating that the DnD operation was correctly applied).
     * This is a regression test for VP-2692.
     * 
     * @throws Exception
     *             if an error occurs.
     */
    @Test
    public void testDragAndDropClassFromControlledResourceOntoDiagram() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "dnd_from_model_content", "new dnd_from_model_content", DDiagram.class);

        // Read the initial state.
        Set<SWTBotGefEditPart> allEditPartsBefore = new HashSet<SWTBotGefEditPart>(editor.mainEditPart().children());
        SWTBotUtils.waitAllUiEvents();
        Thread.sleep(500);
        // Perform the DnD
        SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode("root").expandNode("Package1").getNode("EClass3");

        ICondition done = new OperationDoneCondition();
        ecoreTreeItem.dragAndDrop(editor.getCanvas());
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(done);

        // Check the final state: we should have exactly one new edit part on
        // the diagram.
        Set<SWTBotGefEditPart> allEditPartsAfter = new HashSet<SWTBotGefEditPart>(editor.mainEditPart().children());
        SetView<SWTBotGefEditPart> newParts = Sets.difference(allEditPartsAfter, allEditPartsBefore);
        assertEquals("Expected exactly one new element on the diagram.", 1, newParts.size());
        EObject semanticTarget = ((DDiagramElement) ((View) newParts.iterator().next().part().getModel()).getElement()).getTarget();
        assertTrue(semanticTarget instanceof EClass);
        assertEquals("EClass3", ((EClass) semanticTarget).getName());
    }

    @Override
    @After
    public void tearDown() throws Exception {
        // Reopen outline
        new DesignerViews(bot).openOutlineView();

        super.tearDown();
    }
}
