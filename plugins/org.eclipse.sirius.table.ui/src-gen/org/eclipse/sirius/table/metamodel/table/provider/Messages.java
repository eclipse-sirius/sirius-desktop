/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.provider;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, TableUIPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String AbstractDTableEditor_tableNotSaved;

    @TranslatableMessage
    public static String AbstractDTableEditor_editorToBeClosedAndReopenedSinceContentIsNotAccessible;

    @TranslatableMessage
    public static String AbstractDTreeEditor_RepresentationRefreshFailed;

    @TranslatableMessage
    public static String Refresh_cancelled;

    @TranslatableMessage
    public static String Refresh_error;

    @TranslatableMessage
    public static String Action_setValue;

    @TranslatableMessage
    public static String Action_setValues;

    @TranslatableMessage
    public static String CelEditorFactoryManager_impossibleInstantiation;

    @TranslatableMessage
    public static String CelEditorFactoryManager_notFound;

    @TranslatableMessage
    public static String CelEditorFactoryManager_wrongImplementation;

    @TranslatableMessage
    public static String DeleteLinesAction_label;

    @TranslatableMessage
    public static String DeleteLinesAction_labelMany;

    @TranslatableMessage
    public static String DeleteLinesAction_tooltip;

    @TranslatableMessage
    public static String DeleteLinesAction_tooltipMany;

    @TranslatableMessage
    public static String DeleteTargetColumnAction_label;

    @TranslatableMessage
    public static String DeleteTargetColumnAction_tooltip;

    @TranslatableMessage
    public static String DFeatureColumnEditingSupport_errorGettingPropertyValue;

    @TranslatableMessage
    public static String DFeatureColumnEditingSupport_errorSettingPropertyValue;

    @TranslatableMessage
    public static String DFeatureColumnEditingSupport_notValidValue;

    @TranslatableMessage
    public static String DFeatureColumnEditingSupport_nullCellEditor;

    @TranslatableMessage
    public static String DFeatureColumnEditingSupport_notJavaQualifiedName;

    @TranslatableMessage
    public static String DFeatureColumnEditingSupport_unusableCellEditor;

    @TranslatableMessage
    public static String DTableEditor_ErrorSaveDeletedMessage;

    @TranslatableMessage
    public static String DTableEditor_ErrorSaveDeletedTitle;

    @TranslatableMessage
    public static String DTableEditor_handleElementContentChanged;

    @TranslatableMessage
    public static String DTableEditor_NoTableInResourceError;

    @TranslatableMessage
    public static String DTableEditor_SaveAsErrorMessage;

    @TranslatableMessage
    public static String DTableEditor_SaveAsErrorTitle;

    @TranslatableMessage
    public static String DTableEditor_SaveErrorMessage;

    @TranslatableMessage
    public static String DTableEditor_SaveErrorTitle;

    @TranslatableMessage
    public static String DTableEditor_SaveNextResourceTask;

    @TranslatableMessage
    public static String DTableEditor_SaveTableTask;

    @TranslatableMessage
    public static String DTableEditor_SavingDeletedFile;

    @TranslatableMessage
    public static String DTableEditor_TableLoadingError;

    @TranslatableMessage
    public static String DTableMenuListener_cvsExportException;

    @TranslatableMessage
    public static String DTableMenuListener_exportMenuName;

    @TranslatableMessage
    public static String DTableMenuListener_newMenuName;

    @TranslatableMessage
    public static String DTableMenuListener_openMenuName;

    @TranslatableMessage
    public static String DTableMenuListener_showHideMenuName;

    @TranslatableMessage
    public static String DTableViewerListener_collapseLine;

    @TranslatableMessage
    public static String DTableViewerListener_expandLine;

    @TranslatableMessage
    public static String EditorCreateLineMenuAction_label;

    @TranslatableMessage
    public static String EditorCreateTargetColumnMenuAction_label;

    @TranslatableMessage
    public static String HideColumnAction_label;

    @TranslatableMessage
    public static String HideLinesAction_label;

    @TranslatableMessage
    public static String HideLinesAction_labelMany;

    @TranslatableMessage
    public static String HideRevealColumnsAction_dialogMsg;

    @TranslatableMessage
    public static String HideRevealColumnsAction_dialogTitle;

    @TranslatableMessage
    public static String HideRevealColumnsAction_label;

    @TranslatableMessage
    public static String HideRevealLinesAction_dialogMsg;

    @TranslatableMessage
    public static String HideRevealLinesAction_dialogTitle;

    @TranslatableMessage
    public static String HideRevealLinesAction_label;

    @TranslatableMessage
    public static String HierarchyLabelTableProvider_elementWithoutName;

    @TranslatableMessage
    public static String PrintAction_errorDuringPrinting;

    @TranslatableMessage
    public static String PrintAction_tableWithoutName;

    @TranslatableMessage
    public static String PrintAsImageCallback_pageFooter;

    @TranslatableMessage
    public static String Refresh_errorDuringRefresh;

    @TranslatableMessage
    public static String RefreshAction_label;

    @TranslatableMessage
    public static String RefreshAtOpeningActivator_refreshTableCmdName;

    @TranslatableMessage
    public static String ShowAllColumnsAction_label;

    @TranslatableMessage
    public static String ShowAllLinesAction_label;

    @TranslatableMessage
    public static String ShowPropertiesViewAction_error;

    @TranslatableMessage
    public static String ShowPropertiesViewAction_label;

    @TranslatableMessage
    public static String SortColumnsByLineAction_label;

    @TranslatableMessage
    public static String SortDColumnsCommand_ascendingSorting;

    @TranslatableMessage
    public static String SortDColumnsCommand_descendingSorting;

    @TranslatableMessage
    public static String SortDLinesCommand_ascendingSorting;

    @TranslatableMessage
    public static String SortDLinesCommand_descendingSorting;

    @TranslatableMessage
    public static String SortLinesByColumnAction_label;

    @TranslatableMessage
    public static String Table_CellEditorResult;

    @TranslatableMessage
    public static String Table_CellElement;

    @TranslatableMessage
    public static String Table_CurrentSemanticElement;

    @TranslatableMessage
    public static String Table_LineElement;

    @TranslatableMessage
    public static String Table_SemanticColumnElement;

    @TranslatableMessage
    public static String Table_SemanticLineElement;

    @TranslatableMessage
    public static String Table_SemanticRootElement;

    @TranslatableMessage
    public static String Table_TableElement;

    @TranslatableMessage
    public static String TableDialectUIServices_newTableName;

    @TranslatableMessage
    public static String TableDialectUIServices_tableOpening;

    @TranslatableMessage
    public static String TableDialectUIServices_tableOpeningError;

    @TranslatableMessage
    public static String TableDialectUIServices_tableOpeningVar;

    @TranslatableMessage
    public static String TableQuickOutlineHandler_selectColumn;

    @TranslatableMessage
    public static String TableQuickOutlineHandler_selectLine;

    @TranslatableMessage
    public static String TableUIHelper_undefinedContent;

    @TranslatableMessage
    public static String TableUIHelper_unsupportedColor;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
