/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.table;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.widget.ContextualMenuItemAvailable;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Ensures that the "Open" menu correctly displays the navigation links provided
 * by
 * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription}
 * for Tables and Trees.
 * 
 * <p>
 * Relevant tickets :<br/>
 * <ul>
 * <li>VP-2659:NavigationDescription from table and tree are not shown if no
 * default navigation is available</li>
 * </ul>
 * 
 * @author alagarde
 */
public class NavigationDescriptionFromTableAndTreeTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/navigation/vp2659/";

    private static final String MODELER_MODEL_FILENAME = "vp-2659.odesign";

    private static final String SEMANTIC_MODEL_FILENAME = "semantic.ecore";

    private static final String SESSION_MODEL_FILENAME = "representations.aird";

    private static final String FILE_DIR = "/";

    private static final String TABLE_REPRESENTATION_NAME = "EPackage editionTable";

    private static final String CROSS_TABLE_REPRESENTATION_NAME = "EPackage crossTable";

    private static final String TREE_REPRESENTATION_NAME = "EPackage tree";

    private static final String VIEWPOINT_NAME = "NavigationDescriptions";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + "/" + MODELER_MODEL_FILENAME, "/" + getProjectName() + "/" + MODELER_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + getProjectName() + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME, "/" + getProjectName() + "/" + SESSION_MODEL_FILENAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_MODEL_FILENAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();

        if ("".equals(localSession.getOpenedSession().getSelectedViewpoints(false).iterator().next().getName().trim())) {
            Set<String> set = new HashSet<String>();
            set.add(VIEWPOINT_NAME);
            Set<String> setEmpty = new HashSet<String>();
            localSession.changeViewpointSelection(set, setEmpty);
        }
    }

    /**
     * Ensures that the "Open" menu correctly displays the open link for Trees.
     */
    public void testOpenDescriptionAvailableFromTrees() {
        // Step 1 : save the previous session and open Tree
        localSession.save();
        UITreeRepresentation tree = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(TREE_REPRESENTATION_NAME)
                .selectRepresentationInstance("new " + TREE_REPRESENTATION_NAME, UITreeRepresentation.class).open();

        try {
            // Step 2 : select the C2 tree item
            // => open menu should not be available (only defined on
            // EAttributes)
            SWTBotTreeItem c2 = tree.getTree().getTreeItem("C2").select();
            checkOpenMenuIsAvailable(tree.getTree(), c2, false);

            // Step 3 : select the at1 child of C1
            // => open menu should be available
            SWTBotTreeItem a1 = tree.getTree().getTreeItem("C1").expand().getItems()[0].select();
            checkOpenMenuIsAvailable(tree.getTree(), a1, true);
        } finally {
            tree.close();
        }
    }

    /**
     * Ensures that the "Open" menu correctly displays the open link for Edition
     * Tables. Also checks that preconditions of NavigationDescriptions are
     * evaluated.
     */
    public void testOpenDescriptionAvailableFromEditionTablesWithPrecondition() {
        // Step 1 : open Table
        UITableRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(TABLE_REPRESENTATION_NAME)
                .selectRepresentationInstance("new " + TABLE_REPRESENTATION_NAME, UITableRepresentation.class).open();

        try {
            // Step 2 : select the C1 line
            // => open representation menu should be available
            SWTBotTreeItem c1 = table.getTable().getTreeItem("C1").select();
            checkOpenMenuIsAvailable(table.getTable(), c1, true);

            // Step 3 : select the C2 line
            // => open representation menu should not be available as
            // precondition is
            // false
            SWTBotTreeItem c2 = table.getTable().getTreeItem("C2").select();
            checkOpenMenuIsAvailable(table.getTable(), c2, false);
            localSession.save();
        } finally {
            table.getEditor().setFocus();
            table.close();
        }
    }

    /**
     * Ensures that the "Open" menu correctly displayed for CrossTables.
     */
    public void testOpenDescriptionAvailableFromCrossTables() {
        // Step 1 : open cross Table
        UITableRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(CROSS_TABLE_REPRESENTATION_NAME)
                .selectRepresentationInstance("new " + CROSS_TABLE_REPRESENTATION_NAME, UITableRepresentation.class).open();

        try {
            // Step 2 : select the C1 line
            // => open menu should be available
            SWTBotTreeItem c2 = table.getTable().getTreeItem("C2").select();
            checkOpenMenuIsAvailable(table.getTable(), c2, true);
        } finally {
            table.close();
        }
    }

    /**
     * Checks that the "Open.." menu is available or not (according to the given
     * parameter), and contains the custom navigation description.
     * 
     * @param swtBotTree
     *            the bot for the Tree on which we d the check
     * @param treeItem
     *            the treeItem from which open the "Open" menu
     * 
     * @param shouldBeAvailable
     *            indicates if the open menu should be available
     */
    protected void checkOpenMenuIsAvailable(final SWTBotTree swtBotTree, final SWTBotTreeItem treeItem, final boolean shouldBeAvailable) {
        SWTBotUtils.waitAllUiEvents();
        // We only check if the custom open is available
        Result<Boolean> menuItemGetter = new ContextualMenuItemAvailable(swtBotTree.widget, new String[] { "Open", "custom navigation" });
        final Boolean customNavigationIsAvailable = UIThreadRunnable.syncExec(menuItemGetter);

        if (shouldBeAvailable) {
            assertTrue("The provided open description should be available under the 'Open' menu", customNavigationIsAvailable);
        } else {
            assertFalse("The provided open description should not be available", customNavigationIsAvailable);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        localSession.closeAndDiscardChanges();
        super.tearDown();
    }
}
