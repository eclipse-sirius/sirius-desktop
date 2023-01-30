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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.DefaultPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors.StandalonePermissionProviderDescriptor;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.tree.TreeUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

/**
 * Tests on ui (swt {@link TreeItem}) to see if widget behaves according to
 * {@link IPermissionAuthority} acceptance.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeUIPermissionAuthorityTests extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "ecore.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "tree.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "tree.ecore";

    private PermissionProviderDescriptor permissionProviderDescriptor;

    private ReadOnlyPermissionAuthority readOnlyPermissionAuthority;

    private SWTBotEditor treeEditorBot;

    private UITreeRepresentation treeRepresentation;

    private DTree dTree;

    private DTreeItem firstDTreeItem;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, SESSION_RESOURCE_FILE, SEMANTIC_RESOURCE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        initCustomPermissionAuthority();
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        localSession.getOpenedSession().getModelAccessor().getPermissionAuthority();

        treeRepresentation = openEditor(localSession, EcoreModeler.DESIGN_VIEWPOINT_NAME, "Tree", "new Tree");
        treeEditorBot = treeRepresentation.getEditor();
        DTreeEditor dTreeEditor = (DTreeEditor) treeEditorBot.getReference().getEditor(false);
        dTree = (DTree) dTreeEditor.getRepresentation();
        firstDTreeItem = dTree.getOwnedTreeItems().get(0);

    }

    /**
     * Init Sirius with a {@link ReadOnlyPermissionAuthority}.
     */
    private void initCustomPermissionAuthority() {
        readOnlyPermissionAuthority = new ReadOnlyPermissionAuthority();
        IPermissionProvider permissionProvider = new DefaultPermissionProvider(readOnlyPermissionAuthority);
        permissionProviderDescriptor = new StandalonePermissionProviderDescriptor("org.eclipse.sirius.tree.tests.forbiddenPermissionAuthorityProvider", ExtenderConstants.HIGHEST_PRIORITY,
                permissionProvider);
        PermissionService.addExtension(permissionProviderDescriptor);
    }

    /**
     * Test that changing the feature {@link TreePackage#DTREE_ITEM__EXPANDED}
     * of a {@link DTreeItem}, expand correctly the corresponding SWT
     * {@link TreeItem}.
     */
    public void testTreeItemExpansion() {
        readOnlyPermissionAuthority.activate();

        // Test expansion
        TreeUtils.expandTreeItem(treeEditorBot, firstDTreeItem);

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);

    }

    /**
     * Test that changing the feature {@link TreePackage#DTREE_ITEM__EXPANDED}
     * of a {@link DTreeItem}, collapse correctly the corresponding SWT
     * {@link TreeItem}.
     */
    public void testTreeItemCollapse() {
        TreeUtils.collapseTreeItem(treeEditorBot, firstDTreeItem);
        readOnlyPermissionAuthority.activate();

        // Test collapse
        TreeUtils.collapseTreeItem(treeEditorBot, firstDTreeItem);

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);

    }

    @Override
    protected void tearDown() throws Exception {

        PermissionService.removeExtension(permissionProviderDescriptor);
        permissionProviderDescriptor = null;
        readOnlyPermissionAuthority = null;

        treeEditorBot.close();
        treeEditorBot = null;
        treeRepresentation = null;
        dTree = null;
        firstDTreeItem = null;

        super.tearDown();
    }
}
