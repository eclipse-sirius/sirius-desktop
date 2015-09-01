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
package org.eclipse.sirius.common.ui;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author pcdavid
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusTransPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String AbstractFolderSelectionDialog_newFolder;

    @TranslatableMessage
    public static String AbstractSelectionWizardPage_label;

    @TranslatableMessage
    public static String AbstractSelectionWizardPage_message;

    @TranslatableMessage
    public static String DynamicConfigurationHelper_unknownField;

    @TranslatableMessage
    public static String EObjectPaneBasedSelectionWizard_askSelect;

    @TranslatableMessage
    public static String EObjectPaneBasedSelectionWizard_choiceMessage;

    @TranslatableMessage
    public static String EObjectPaneBasedSelectionWizard_selectedMessage;

    @TranslatableMessage
    public static String EObjectPaneBasedSelectionWizard_title;

    @TranslatableMessage
    public static String EObjectSelectionWizard_message;

    @TranslatableMessage
    public static String EObjectSelectionWizard_title;
    
    @TranslatableMessage
    public static String FolderSelectionDialog_title;

    @TranslatableMessage
    public static String EclipseLabelProviderProviderDescriptor_errorLoadingExtension;

    @TranslatableMessage
    public static String EclipseUIUtil_showView_error;

    @TranslatableMessage
    public static String FeatureProposalProvider_newFeatureExpression;

    @TranslatableMessage
    public static String ModelElementChooserDialog_title;
    
    @TranslatableMessage
    public static String NewFileDialog_message;

    @TranslatableMessage
    public static String NewFileDialog_title;

    @TranslatableMessage
    public static String NewFileDialog_fileLabel;

    @TranslatableMessage
    public static String NewFileDialog_selectFileStatus;

    @TranslatableMessage
    public static String QuickOutlineAdapterFactoryLabelProvider_foundIn;

    @TranslatableMessage
    public static String QuickOutlineControl_errorNoPage;

    @TranslatableMessage
    public static String RenameDialog_askNewName_caseInsensitive;

    @TranslatableMessage
    public static String RenameDialog_askNewName_caseSensitive;

    @TranslatableMessage
    public static String RenameDialog_errorNameClash;

    @TranslatableMessage
    public static String RenameDialog_title;

    @TranslatableMessage
    public static String SWTUtil_askToSaveChanges;

    @TranslatableMessage
    public static String SWTUtil_askToSaveChanges_openElseWhere;

    @TranslatableMessage
    public static String SWTUtil_saveDialog_title;
    
    @TranslatableMessage
    public static String SelectFilesWizardPage_title;
    
    @TranslatableMessage
    public static String SelectFilesWizardPage_notEnoughFiles;

    @TranslatableMessage
    public static String SelectFilesWizardPage_tooManyFiles;

    @TranslatableMessage
    public static String SelectModelElementWizardPage_invalidFile;

    @TranslatableMessage
    public static String ServiceProposalProvider_invalidContext;

    @TranslatableMessage
    public static String ServiceProposalProvider_newVariableExpression;

    @TranslatableMessage
    public static String TimeProfiler_action_print;

    @TranslatableMessage
    public static String TimeProfiler_action_refresh;

    @TranslatableMessage
    public static String TimeProfiler_action_reinit;

    @TranslatableMessage
    public static String TimeProfiler_column_avg;

    @TranslatableMessage
    public static String TimeProfiler_column_category;

    @TranslatableMessage
    public static String TimeProfiler_column_max;

    @TranslatableMessage
    public static String TimeProfiler_column_min;

    @TranslatableMessage
    public static String TimeProfiler_column_occ;

    @TranslatableMessage
    public static String TimeProfiler_column_task;

    @TranslatableMessage
    public static String TimeProfiler_column_time;

    @TranslatableMessage
    public static String VariableProposalProvider_newVariableExpression;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
