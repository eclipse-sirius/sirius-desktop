/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.provider;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtain translated strings.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, TreeUIPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String DTreeEditor_editorToBeClosedAndReopenedSinceContentIsNotAccessible;

    @TranslatableMessage
    public static String DTreeEditor_treeModelUnsaved;

    @TranslatableMessage
    public static String DTreeItemDropListener_ambigousDropWarning;

    @TranslatableMessage
    public static String DTreeItemDropListener_dragAndDropCommand;

    @TranslatableMessage
    public static String DTreeItemEditingSupport_directEditCommand;

    @TranslatableMessage
    public static String DTreeItemLabelProvider_iconFileNotFound;

    @TranslatableMessage
    public static String DTreeMenuListener_new;

    @TranslatableMessage
    public static String DTreeMenuListener_open;

    @TranslatableMessage
    public static String DeleteTreeItemsAction_deleteTargetSemanticElement;

    @TranslatableMessage
    public static String DeleteTreeItemsAction_deleteTargetSemanticElements;

    @TranslatableMessage
    public static String DeleteTreeItemsAction_deleteTreeItem;

    @TranslatableMessage
    public static String DeleteTreeItemsAction_deleteTreeItems;

    @TranslatableMessage
    public static String EditorCreateTreeItemMenuAction_name;

    @TranslatableMessage
    public static String EditorRefreshAction_error;

    @TranslatableMessage
    public static String EditorRefreshAction_refreshCancelled;

    @TranslatableMessage
    public static String EditorRefreshAction_treeRefreshError;

    @TranslatableMessage
    public static String ExpandDTreeItemRunnableWithProgress_expandTreeItem;

    @TranslatableMessage
    public static String ExpandDTreeItemRunnableWithProgress_treeItemCollapsing;

    @TranslatableMessage
    public static String ExpandDTreeItemRunnableWithProgress_treeItemExpanding;

    @TranslatableMessage
    public static String HierarchyLabelTreeProvider_elementWithoutName;

    @TranslatableMessage
    public static String RefreshAction_refreshTreeElement;

    @TranslatableMessage
    public static String TreeDialectUIServices_errorOpeningEditor;

    @TranslatableMessage
    public static String TreeDialectUIServices_errorClosingEditor;

    @TranslatableMessage
    public static String TreeDialectUIServices_newTree;

    @TranslatableMessage
    public static String TreeDialectUIServices_treeOpening;

    @TranslatableMessage
    public static String TreeItemExpansionManager_expandOrCollaseError;

    @TranslatableMessage
    public static String TreeItemExpansionManager_treeCollapsing;

    @TranslatableMessage
    public static String TreeItemExpansionManager_treeExpanding;

    @TranslatableMessage
    public static String TreeItemStyleDescriptionItemProvider_noLabel;

    @TranslatableMessage
    public static String TreeQuickOutlineHandler_quickOutline;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
