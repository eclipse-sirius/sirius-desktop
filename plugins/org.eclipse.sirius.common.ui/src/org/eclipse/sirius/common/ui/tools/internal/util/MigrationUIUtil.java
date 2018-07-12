/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.util;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.Messages;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.ui.ISaveablePart2;

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
     * Test if the resource has been automatically migrated. If it is the case and this is due to a direct user action,
     * ask user if he want to save the resource
     * 
     * @param resource
     *            The resource to test
     * @return <code>true</code> if the user want to save the session, <code>false</code> otherwise
     */
    public static boolean shouldMigratedElementBeSaved(Resource resource) {
        String migrateFrom = hasBeenMigratedFrom(resource);
        if (migrateFrom != null && shouldUserBeWarnedAboutMigration(resource)) {
            return doAskUserIfElementShouldBeSaved(resource);
        }
        return false;
    }

    /**
     * Test if the session resource has been automatically migrated. If it is the case and this is due to a direct user
     * action, ask user if he want to save the session
     * 
     * @param session
     *            The session to test
     * @return <code>true</code> if the user want to save the session, <code>false</code> otherwise
     */
    public static boolean shouldMigratedElementBeSaved(Session session) {
        for (Resource resource : session.getAllSessionResources()) {
            String migrateFrom = hasBeenMigratedFrom(resource);
            if (migrateFrom != null && shouldUserBeWarnedAboutMigration(resource)) {
                return doAskUserIfElementShouldBeSaved(resource);
            }
        }
        return false;
    }

    private static boolean doAskUserIfElementShouldBeSaved(Resource resource) {
        String message = MessageFormat.format(Messages.MigrationUIUtil_askToSaveChanges, resource.getURI().lastSegment());
        return SWTUtil.showSaveDialogWithMessage(resource, message, true) == ISaveablePart2.YES;
    }

    /**
     * Test if the given resource has been migrated due to a direct user action.
     * 
     * @param resource
     *            The given resource
     * @return <code>true</code> if the resource as been migrated because of a direct user action, <code>false</code>
     *         otherwise
     */
    public static boolean hasBeenMigratedAndUserShouldBeWarned(Resource resource) {
        return hasBeenMigratedFrom(resource) != null && shouldUserBeWarnedAboutMigration(resource);
    }

    private static String hasBeenMigratedFrom(Resource resource) {
        String migrateFrom = null;
        if (resource instanceof XMLResource) {
            Object result = ((XMLResource) resource).getDefaultLoadOptions().get(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION);
            if (result instanceof String) {
                migrateFrom = (String) result;
            }
        }
        return migrateFrom;
    }

    private static boolean shouldUserBeWarnedAboutMigration(Resource resource) {
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
