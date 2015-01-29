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
package org.eclipse.sirius.tests.swtbot.table;

import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.DefaultPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors.StandalonePermissionProviderDescriptor;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.tree.TreeUtils;
import org.eclipse.sirius.tests.swtbot.tree.AbstractTreeSiriusSWTBotGefTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

/**
 * Tests on ui (swt {@link TreeItem}) to see if widget behaves according to
 * {@link IPermissionAuthority} acceptance.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TableUIPermissionAuthorityTests extends AbstractTreeSiriusSWTBotGefTestCase {

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

    private SWTBotEditor tableEditorBot;

    private UITableRepresentation tableRepresentation;

    private DTable dTable;

    private DLine firstDLine;

    private DColumn firstDColumn;

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

        tableRepresentation = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(EcoreModeler.DESIGN_VIEWPOINT_NAME).selectRepresentation(EcoreModeler.TABLES_DESC_NAME)
                .selectRepresentationInstance("new Classes", UITableRepresentation.class).open();
        tableEditorBot = tableRepresentation.getEditor();
        DTableEditor dTableEditor = (DTableEditor) tableEditorBot.getReference().getEditor(false);
        dTable = (DTable) dTableEditor.getRepresentation();
        firstDLine = dTable.getLines().get(0);
        firstDColumn = dTable.getColumns().get(0);
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
     * Test that changing the feature {@link TablePackage#DLINE__COLLAPSED} of a
     * {@link DLine}, expand correctly the corresponding SWT {@link TreeItem}.
     */
    public void testDLineExpansion() {
        readOnlyPermissionAuthority.activate();

        // Test expansion
        TreeUtils.collapseTreeItem(tableEditorBot, firstDLine);

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);

    }

    /**
     * Test that changing the feature {@link TreePackage#DTREE_ITEM__EXPANDED}
     * of a {@link DTreeItem}, collapse correctly the corresponding SWT
     * {@link TreeItem}.
     */
    public void testTreeItemCollapse() {
        TreeUtils.collapseTreeItem(tableEditorBot, firstDLine);
        readOnlyPermissionAuthority.activate();

        // Test collapse
        TreeUtils.expandTreeItem(tableEditorBot, firstDLine);

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);

    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DTABLE__HEADER_COLUMN_WIDTH} of a {@link DTable},
     * update correctly the ColumnItem's header width.
     */
    public void testColumnItemHeaderWidth() {
        int headerColumnWidth = dTable.getHeaderColumnWidth();
        int newHeaderColumnWidth = headerColumnWidth + 40;

        // Test header column width resize
        TreeUtils.resizeTreeHeaderColumnWidth(tableEditorBot, dTable, newHeaderColumnWidth);

        TreeUtils.checkTreeHeaderColumnWidth(tableEditorBot, dTable);
    }

    /**
     * Test that changing the feature {@link TablePackage#DCOLUMN__WIDTH} of a
     * {@link DTable}, update correctly the ColumnItem's width.
     */
    public void testColumnItemWidth() {
        int columnWidth = firstDColumn.getWidth();
        int newColumnWidth = columnWidth + 40;
        // Test column width resize
        TreeUtils.resizeTreeColumnWidth(tableEditorBot, firstDColumn, newColumnWidth);

        TreeUtils.checkTreeColumnWidth(tableEditorBot, firstDColumn);

    }

    @Override
    protected void tearDown() throws Exception {

        PermissionService.removeExtension(permissionProviderDescriptor);
        permissionProviderDescriptor = null;
        readOnlyPermissionAuthority = null;

        tableEditorBot.close();
        tableEditorBot = null;
        tableRepresentation = null;
        dTable = null;
        firstDLine = null;

        super.tearDown();
    }
}
