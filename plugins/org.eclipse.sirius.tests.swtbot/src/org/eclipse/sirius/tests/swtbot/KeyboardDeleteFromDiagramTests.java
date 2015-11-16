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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test that the delete from diagram key shortcut works correctly. see VP-4067.
 * 
 * @author fbarbin
 */
public class KeyboardDeleteFromDiagramTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/delete/VP-4067/";

    private static final String SEMANTIC_RESOURCE_NAME = "vp-4067.ecore";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    /** Bot for the DiagramEditPart */
    protected SWTBotGefEditPart diagramEditPartBot;

    private SWTBotGefEditPart eClassBot;

    private SWTBotGefEditPart eClassBot2;

    private DSemanticDiagram dSemanticDiagram;

    private DDiagramElement dDiagramElementOfEClassToDelete;

    private DDiagramElement dDiagramElementOfEClassToDelete2;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + SEMANTIC_RESOURCE_NAME, getProjectName() + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + SESSION_RESOURCE_NAME, getProjectName() + "/" + SESSION_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        diagramEditPartBot = editor.rootEditPart().children().get(0);
        eClassBot = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(AbstractDiagramListEditPart.class)).get(0);
        eClassBot2 = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(AbstractDiagramListEditPart.class)).get(1);
        dSemanticDiagram = (DSemanticDiagram) ((IGraphicalEditPart) diagramEditPartBot.part()).resolveSemanticElement();
        dDiagramElementOfEClassToDelete = dSemanticDiagram.getOwnedDiagramElements().get(0);
        dDiagramElementOfEClassToDelete2 = dSemanticDiagram.getOwnedDiagramElements().get(2);
    }

    /**
     * Test that Ctrl + Shift + D deletes the diagram element.
     */
    public void testDeleteFromDiagramUnSynchronized() {
        eClassBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithCtrlShiftDShortcut();
        SWTBotUtils.waitAllUiEvents();
        assertNull("the ddiagram element should be deleted from semantic diagram", dDiagramElementOfEClassToDelete.eContainer());
    }

    /**
     * Test that if the diagram is synchronized, Ctrl + Shift + D doesn't delete
     * the diagram element
     */
    public void testDeleteFromDiagramSynchronized() {

        // Unsynchronized the diagram
        Command command = SetCommand.create(localSession.getOpenedSession().getTransactionalEditingDomain(), dSemanticDiagram, DiagramPackage.Literals.DDIAGRAM__SYNCHRONIZED, true);
        localSession.getOpenedSession().getTransactionalEditingDomain().getCommandStack().execute(command);
        eClassBot2.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithCtrlShiftDShortcut();
        SWTBotUtils.waitAllUiEvents();
        assertNotNull("the ddiagram element should not be deleted from semantic diagram", dDiagramElementOfEClassToDelete2.eContainer());
    }

    /**
     * Delete with Ctrl+Shift+D shortcut.
     */
    private void deleteWithCtrlShiftDShortcut() {
        bot.activeEditor();
        SWTBotUtils.pressKeyboardKey(editor.getCanvas().widget, SWT.CTRL | SWT.SHIFT, 'd');
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        diagramEditPartBot = null;
        eClassBot = null;
        eClassBot2 = null;
        dSemanticDiagram = null;
        dDiagramElementOfEClassToDelete = null;
        dDiagramElementOfEClassToDelete2 = null;

        super.tearDown();

    }
}
