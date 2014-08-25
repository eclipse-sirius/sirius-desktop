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

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.support.utils.dnd.DndUtil;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Tests for drag'n'dropping elements from the model content view on a diagram.
 * 
 * @author pcdavid
 */
public class DragAndDropFromControlledResourceTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "Root.ecore";

    private static final String CONTROLLED_MODEL = "Root_Package1.ecore";

    private static final String SESSION_FILE = "Root.aird";

    private static final String VSM_FILE = "dnd_from_model_content.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/vp-2692/";

    private static final String FILE_DIR = "/";

    private SWTBotSiriusDiagramEditor editor;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private UIDiagramRepresentation diagram;

    private UIResource ecoreEcoreResource;

    private SWTBotTreeItem semanticResourceNode;

    /**
     * {@inheritDoc}
     */
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
    public void testDragAndDropClassFromControlledResourceOntoDiagram() throws Exception {
        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("dnd_from_model_content").selectRepresentation("dnd_from_model_content")
                .selectRepresentationInstance("new dnd_from_model_content", UIDiagramRepresentation.class).open();
        editor = diagram.getEditor();

        // Read the initial state.
        Set<SWTBotGefEditPart> allEditPartsBefore = Sets.newHashSet(editor.mainEditPart().children());
        SWTBotUtils.waitAllUiEvents();
        Thread.sleep(500);
        // Perform the DnD
        DndUtil util = new DndUtil(bot.getDisplay());
        SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode("root").expandNode("Package1").getNode("EClass3");

        ICondition done = new OperationDoneCondition();
        util.dragAndDrop(ecoreTreeItem, editor.getCanvas());
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(done);

        // Check the final state: we should have exactly one new edit part on
        // the diagram.
        Set<SWTBotGefEditPart> allEditPartsAfter = Sets.newHashSet(editor.mainEditPart().children());
        SetView<SWTBotGefEditPart> newParts = Sets.difference(allEditPartsAfter, allEditPartsBefore);
        assertEquals("Expected exactly one new element on the diagram.", 1, newParts.size());
        EObject semanticTarget = ((DDiagramElement) ((View) newParts.iterator().next().part().getModel()).getElement()).getTarget();
        assertTrue(semanticTarget instanceof EClass);
        assertEquals("EClass3", ((EClass) semanticTarget).getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Reopen outline
        new DesignerViews(bot).openOutlineView();

        super.tearDown();
    }
}
