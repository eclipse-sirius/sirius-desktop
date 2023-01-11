/*******************************************************************************
 * Copyright (c) 2015, 2023 Obeo.
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
    public static String AbstractDTreeItemRunnableWithProgress_emptyDTreeItemsParameter;

    @TranslatableMessage
    public static String AbstractDTreeItemRunnableWithProgress_nullDTreeItemParameter;

    @TranslatableMessage
    public static String CollapseDTreeItemRunnableWithProgress_collapseAllTreeItems;

    @TranslatableMessage
    public static String CollapseDTreeItemRunnableWithProgress_collapseTreeItem;

    @TranslatableMessage
    public static String DTreeEditor_editorToBeClosedAndReopenedSinceContentIsNotAccessible;

    @TranslatableMessage
    public static String DTreeEditor_RepresentationRefreshFailed;

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
    public static String EditorRefresh_error;

    @TranslatableMessage
    public static String EditorRefresh_refreshCancelled;

    @TranslatableMessage
    public static String EditorRefresh_treeRefreshError;

    @TranslatableMessage
    public static String ExpandDTreeItemRunnableWithProgress_expandAllTreeItems;

    @TranslatableMessage
    public static String ExpandDTreeItemRunnableWithProgress_expandTreeItem;

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

    @TranslatableMessage
    public static String SiriusTreePreferencePage_alwaysUseStandardFont;

    @TranslatableMessage
    public static String SiriusTreePreferencePage_globalGroupName;

    @TranslatableMessage
    public static String SiriusTreePreferencePage_alwaysUseStandardFont_help;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
