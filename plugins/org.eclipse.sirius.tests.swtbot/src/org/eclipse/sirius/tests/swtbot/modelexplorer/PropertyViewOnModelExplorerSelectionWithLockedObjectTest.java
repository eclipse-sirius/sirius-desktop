/*******************************************************************************
 * Copyright (c) 2015, 2024 Obeo.
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
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.DefaultPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors.StandalonePermissionProviderDescriptor;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.TableRow;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test the use of property view on ModelExplorerView {@link EObject} selection
 * for locked object.
 * 
 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=480203">Bug
 *      480203</a>
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class PropertyViewOnModelExplorerSelectionWithLockedObjectTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3832.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3832.aird";

    private static final String PATH = "data/unit/VP-3832/";

    private UIResource sessionAirdResource;

    private SWTBot modelExplorerViewBot;

    private SWTBot propertyViewBot;

    private PermissionProviderDescriptor permissionProviderDescriptor;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        ReadOnlyPermissionAuthority readOnlyPermissionAuthority = new ReadOnlyPermissionAuthority();
        readOnlyPermissionAuthority.activate();
        IPermissionProvider permissionProvider = new DefaultPermissionProvider(readOnlyPermissionAuthority);
        permissionProviderDescriptor = new StandalonePermissionProviderDescriptor("org.eclipse.sirius.tests.swtbot.lockModelExplorerPermissionAuthorityProvider", ExtenderConstants.HIGHEST_PRIORITY,
                permissionProvider);
        PermissionService.addExtension(permissionProviderDescriptor);

        sessionAirdResource = new UIResource(designerProject, REPRESENTATIONS_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        modelExplorerView.setFocus();
        modelExplorerViewBot = modelExplorerView.bot();

        SWTBotView propertyView = bot.viewById("org.eclipse.ui.views.PropertySheet");
        propertyView.setFocus();
        propertyViewBot = propertyView.bot();
    }

    /**
     * Test the display and the edition of properties in properties view on
     * ModelExplorerView EObject selection.
     */
    public void testPropertyViewEditionOnModelExplorerViewSelectionForLockedObject() {
        // Check that properties view display something on treeItem selection of
        // semantic resource treeItem
        SWTBotTreeItem projectTreeItemBot = modelExplorerViewBot.tree().expandNode(getProjectName(), true);
        SWTBotTreeItem representationsResourceTreeItemBot = projectTreeItemBot.getNode(REPRESENTATIONS_RESOURCE_NAME);
        SWTBotTreeItem semanticResourceTreeItemBot = representationsResourceTreeItemBot.getNode(2);
        semanticResourceTreeItemBot.select();
        assertEmptyPropertiesView(true);
        SWTBotTreeItem rootEPackageTreeItemBot = semanticResourceTreeItemBot.getNode(0);
        rootEPackageTreeItemBot.select();
        assertEmptyPropertiesView(false);
        SWTBotTreeItem packageEntitiesRepTreeItemBot = rootEPackageTreeItemBot.getNode(0);
        packageEntitiesRepTreeItemBot.select();
        assertEmptyPropertiesView(true);
        SWTBotTreeItem newEClass1TreeItemBot = rootEPackageTreeItemBot.getNode("NewEClass1");
        newEClass1TreeItemBot.select();
        assertEmptyPropertiesView(false);

        // Check that properties of semantic element named "NewEClass1" are
        // editable
        TableRow row = propertyViewBot.tree().getTreeItem("Name").select().doubleClick().row();
        String currentName = row.get(1);
        String newName = "renamedEClass";
        try {
            propertyViewBot.text().setText(newName);
            fail("Property should not be editable");
        } catch (WidgetNotFoundException e) {

        }
        newEClass1TreeItemBot.select();
        assertEquals("The semantic element should have its name unchanged", currentName, newEClass1TreeItemBot.getText());

        PermissionService.removeExtension(permissionProviderDescriptor);
    }

    private void assertEmptyPropertiesView(boolean emptyPropertiesView) {
        List<Tree> findedTrees = propertyViewBot.getFinder().findControls(IsInstanceOf.instanceOf(Tree.class));
        boolean result = findedTrees.isEmpty() || (!findedTrees.isEmpty() && !propertyViewBot.tree().hasItems());
        if (emptyPropertiesView) {
            assertTrue("The properties view is expected to be empty.", result);
        } else {
            assertFalse("The properties view is not expected to be empty.", result);
        }
    }

    @Override
    public void tearDown() throws Exception {
        PermissionService.removeExtension(permissionProviderDescriptor);
        permissionProviderDescriptor = null;
        propertyViewBot = null;
        modelExplorerViewBot = null;
        sessionAirdResource = null;
        super.tearDown();
    }
}
