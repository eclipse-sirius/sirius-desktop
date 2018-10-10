/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.common.ui.tools.internal.util;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;

/**
 * Utility class used for displaying UI elements about resource migration.
 * 
 * @author mcartaud
 *
 */
public final class MigrationUIUtil {

    private MigrationUIUtil() {
        // Prevent instantiation
    }

    /**
     * Returns true if the user should be warned about migration. False otherwise.
     * 
     * @param resource
     *            resource containing the VSM that is potentially migrated.
     * @return true if the user should be warned about migration. False otherwise.
     */
    public static boolean shouldUserBeWarnedAboutMigration(Resource resource) {
        boolean prefAskToSave = SiriusTransPlugin.getPlugin().getPreferenceStore().getBoolean(CommonPreferencesConstants.PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION);
        boolean directUserAction = false;
        if (resource instanceof XMLResource) {
            Object option = ((XMLResource) resource).getDefaultLoadOptions().get(AbstractSiriusMigrationService.OPTION_RESOURCE_NON_BATCH_MIGRATION);
            if (option instanceof Boolean) {
                directUserAction = (boolean) option;
            } else if (option instanceof String) {
                directUserAction = Boolean.parseBoolean((String) option);
            }
        }
        return prefAskToSave && directUserAction;
    }

}
