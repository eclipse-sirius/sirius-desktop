/*******************************************************************************
 * Copyright (c) 2015-2020 Obeo.
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tools.internal.command.PrepareNewAnalysisCommand;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Ensure that some actions on representation are disabled when the
 * {@link DView} is locked by using the permission authority
 * {@link ReadOnlyPermissionAuthority}
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class LockedRepresentationContainerTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "tc2216.ecore";

    private static final String MODEL_URI = "platform:/resource/DesignerTestProject/tc2216.ecore";

    private static final String SESSION_FILE = "tc2216.aird";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-2216/";

    private static final String FILE_DIR = "/";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_NAME = "aaaa package entities";

    private static final String NEXT = "Next >";

    private static final String CANCEL = "Cancel";

    private static final String OK = "OK";

    private static final String FINISH = "Finish";

    private static final String EXTRACT_TO_AIRD_FILE = "Extract to .aird file ...";

    private static final String CREATE_REPRESENTATION = "Create Representation";

    private static final String CREATE_REPRESENTATION_WIZARD = "Create Representation Wizard";

    private static final String NEW_REPRESENTATION = "New Representation";

    private static final String DELETE_ACTION = "Delete";

    private static final String COPY_ACTION = "Copy";

    private static final String MOVE_ACTION = "Move";

    private static final String PACKAGE_NAME = "aaaa";

    private static final String PACKAGE_SUB_NAME = "0";

    private static final String CONTROL_ACTION = "Control...";

    private static final String CONTROL_DIALOG = "Control";

    private static final String CONTROL_WIZARD = "Wizard of representations selection";

    private UIResource semanticModel;

    /**
     * The dView
     */
    protected DView dView = null;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Open the entity diagram & retrieve the representation container
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);

        dView = (DView) new DRepresentationQuery(editor.getDRepresentation()).getRepresentationDescriptor().eContainer();

        // Get the semantic model
        semanticModel = new UIResource(designerProject, FILE_DIR, MODEL);
    }

    /**
     * Ensure that the creation of a new representation is forbidden when the
     * representation container is locked by using permission authority.
     */
    public void testCreateRepresentation() {
        SWTBotTreeItem semanticPackageNode = getSelectedSemanticPackageNode();

        // Before locking the representation container
        assertTrue("The creation of new representation should be enabled", semanticPackageNode.contextMenu(NEW_REPRESENTATION).menu(REPRESENTATION_NAME).isEnabled());

        // Lock the representation container
        lockRepresentationContainer();

        // After locking the representation container
        assertFalse("The creation of new representation should be disabled when the representation container is locked",
                semanticPackageNode.contextMenu(NEW_REPRESENTATION).menu(REPRESENTATION_NAME).isEnabled());
    }

    /**
     * Ensure that the creation of a new representation from the session is
     * forbidden when the representation container is locked by using permission
     * authority.
     */
    public void testCreateRepresentationFromSession() {
        SWTBotTreeItem sessionTreeItem = localSession.getRootSessionTreeItem();

        try {
            // Open the "Create Representation" Wizard from the session
            clickContextMenu(sessionTreeItem, CREATE_REPRESENTATION);

            SWTBotShell shell = bot.shell(CREATE_REPRESENTATION_WIZARD);
            shell.activate();

            // Select the representation description
            bot.tree().expandNode(VIEWPOINT_NAME, REPRESENTATION_DESCRIPTION_NAME).select();
            assertTrue("The representation creation should be allowed", bot.button(NEXT).isEnabled());

            // Lock the representation container
            lockRepresentationContainer();
            // After locking the representation container, the node must be
            // selected
            // again to update the button status
            bot.tree().expandNode(VIEWPOINT_NAME, REPRESENTATION_DESCRIPTION_NAME).select();
            assertFalse("The representation creation should be forbidden when the representation container is locked", bot.button(NEXT).isEnabled());

        } finally {
            // Close the wizard
            bot.button(CANCEL).click();
        }
    }

    /**
     * Ensure that the creation of a new representation from the session is
     * forbidden when the representation container is locked by using permission
     * authority.
     */
    public void testControlSemanticModel() {
        SWTBotTreeItem semanticPackageNode = getSelectedSemanticSubPackageNode();

        try {
            // Call the control action
            clickContextMenu(semanticPackageNode, CONTROL_ACTION);

            // Click "Ok" on the first dialog
            SWTBotShell shell = bot.shell(CONTROL_DIALOG);
            shell.activate();
            bot.button(OK).click();

            // Activate the selection wizard
            shell = bot.shell(CONTROL_WIZARD);
            shell.activate();

            // Select the representation description
            SWTBotTreeItem item = bot.tree().expandNode(MODEL_URI, PACKAGE_NAME, REPRESENTATION_NAME).select();
            item.check();

            assertTrue("The representation extraction should be allowed", bot.button(FINISH).isEnabled());

            // Lock the representation container
            lockRepresentationContainer();

            // After locking the representation container, the node must be
            // selected
            // again to update the button status
            item = bot.tree().expandNode(MODEL_URI, PACKAGE_NAME, REPRESENTATION_NAME).select();
            item.check();

            assertFalse("The representation extraction should be forbidden when the representation container is locked", bot.button(FINISH).isEnabled());
        } finally {
            // Close the wizard
            bot.button(CANCEL).click();
        }
    }

    /**
     * Ensure that the deletion of a representation is forbidden when the
     * representation container is locked by using permission authority.
     */
    public void testDeleteRepresentation() {
        doTestRepresentationAction(DELETE_ACTION);
    }

    /**
     * Ensure that the copy of a representation is forbidden when the
     * representation container is locked by using permission authority.
     */
    public void testCopyRepresentation() {
        doTestRepresentationAction(COPY_ACTION);
    }

    /**
     * Ensure that the extract of a representation is forbidden when the
     * representation container is locked by using permission authority.
     */
    public void testExtractRepresentation() {
        doTestRepresentationAction(EXTRACT_TO_AIRD_FILE);
    }

    /**
     * Ensure that the move of a representation is forbidden when the
     * representation container is locked by using permission authority.
     */
    public void testMoveRepresentation() {
        // First create a new ".aird" file to have the "Move" action
        Session session = localSession.getOpenedSession();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        ResourceSet resourceSet = domain.getResourceSet();
        URI airdUri = URI.createPlatformResourceURI("/" + getProjectName() + "/move.aird", true);
        Resource airdResource = resourceSet.createResource(airdUri);
        DAnalysis slaveAnalysis = ViewpointFactory.eINSTANCE.createDAnalysis();
        domain.getCommandStack().execute(new PrepareNewAnalysisCommand(domain, airdResource, slaveAnalysis, session));

        // Before locking the representation container
        assertTrue("The action 'Move' should be enabled", getSelectedDiagramNode().contextMenu(MOVE_ACTION).isEnabled());

        // Lock the representation container
        lockRepresentationContainer();

        // After locking the representation container
        assertFalse("The action 'Move' should be disabled when the representation container is locked", getSelectedDiagramNode().contextMenu(MOVE_ACTION).isEnabled());
    }

    /**
     * Ensure that an action on a representation is forbidden when the
     * representation container is locked by using permission authority.
     * 
     * @param action
     *            the action to check
     */
    private void doTestRepresentationAction(String action) {
        SWTBotTreeItem diagramNode = getSelectedDiagramNode();

        // Before locking the representation container
        assertTrue("The action '" + action + "' should be enabled", diagramNode.contextMenu(action).isEnabled());

        // Lock the representation container
        lockRepresentationContainer();

        // After locking the representation container
        assertFalse("The action '" + action + "' should be disabled when the representation container is locked", diagramNode.contextMenu(action).isEnabled());
    }

    /**
     * Get and select the semantic package node from the tree item.
     * 
     * @return semantic package node
     */
    private SWTBotTreeItem getSelectedSemanticPackageNode() {
        SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        return semanticResourceNode.expandNode(PACKAGE_NAME).select();
    }

    /**
     * Get and select the semantic package node from the tree item.
     * 
     * @return semantic package node
     */
    private SWTBotTreeItem getSelectedSemanticSubPackageNode() {
        SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        return semanticResourceNode.expandNode(PACKAGE_NAME, PACKAGE_SUB_NAME).select();
    }

    /**
     * Get and select the diagram node from the tree item
     * 
     * @return diagram node
     */
    private SWTBotTreeItem getSelectedDiagramNode() {
        SWTBotTreeItem semanticPackageNode = getSelectedSemanticPackageNode();
        return semanticPackageNode.expandNode(REPRESENTATION_NAME).select();
    }

    /**
     * Lock the representation container.
     */
    protected void lockRepresentationContainer() {
        // Activate the ReadOnlyPermission Authority on the representation
        // container to lock it
        ((ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dView)).activate();
    }

    /**
     * Click on a context menu action.
     * 
     * @param treeItem
     *            selection tree item
     * @param action
     *            action to call
     */
    private void clickContextMenu(SWTBotTreeItem treeItem, String action) {
        try {
            treeItem.contextMenu(action).click();
        } catch (WidgetNotFoundException e) {
            SWTBotUtils.clickContextMenu(treeItem, CREATE_REPRESENTATION);
        }
    }
}
