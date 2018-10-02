/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.dialog;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * A class to allow cleaning of AbstractExportRepresentationsAsImagesDialog
 * settings.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ExportAsImageHelper {

    /**
     * The id for the persistent settings for the export representations dialog.
     */
    private static final String DIALOG_SETTINGS_ID = "ExportRepresentationsAsImagesDialog"; //$NON-NLS-1$     

    /**
     * The id for the persistent folder setting for the export representations
     * dialog.
     */
    private static final String DIALOG_SETTINGS_FOLDER = "ExportRepresentationsAsImagesDialog.folder"; //$NON-NLS-1$

    /**
     * The id for the persistent file setting for the export representations
     * dialog.
     */
    private static final String DIALOG_SETTINGS_FILE = "ExportRepresentationsAsImagesDialog.file"; //$NON-NLS-1$

    /**
     * Default constructor.
     */
    public ExportAsImageHelper() {
    }

    /**
     * Retrieves the persistent settings for this dialog.
     * 
     * @return the persistent settings for this dialog.
     */
    protected IDialogSettings getDialogSettings() {
        IDialogSettings settings = SiriusEditPlugin.getPlugin().getDialogSettings();
        settings = settings.getSection(ExportAsImageHelper.DIALOG_SETTINGS_ID);
        if (settings == null) {
            settings = SiriusEditPlugin.getPlugin().getDialogSettings().addNewSection(ExportAsImageHelper.DIALOG_SETTINGS_ID);
        }
        return settings;
    }

    /**
     * Reset all dialog settings.
     */
    public void resetDialogSettings() {
        final IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings != null) {
            dialogSettings.put(ExportAsImageHelper.DIALOG_SETTINGS_FOLDER, new String[0]);
            dialogSettings.put(ExportAsImageHelper.DIALOG_SETTINGS_FILE, new String[0]);
        }
    }

    /**
     * Reset all folders setting dialog.
     */
    public void resetDialogFolderSettings() {
        final IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings != null) {
            dialogSettings.put(ExportAsImageHelper.DIALOG_SETTINGS_FOLDER, new String[0]);
        }
    }

    /**
     * Reset file setting dialog.
     */
    public void resetDialogFileSettings() {
        final IDialogSettings dialogSettings = getDialogSettings();
        if (dialogSettings != null) {
            dialogSettings.put(ExportAsImageHelper.DIALOG_SETTINGS_FILE, new String[0]);
        }
    }
}
