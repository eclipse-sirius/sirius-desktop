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
package org.eclipse.sirius.ecore.extender.business.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtain translated strings.
 * 
 * @author pcdavid
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, ExtenderPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String AbstractProviderDescriptor_attributeMissing;

    @TranslatableMessage
    public static String ExtenderProviderDescriptor_errorLoadingExtenderProvider;

    @TranslatableMessage
    public static String FeatureNotFoundException_message;

    @TranslatableMessage
    public static String IllegalTypeForValueException_message;

    @TranslatableMessage
    public static String LockedInstanceException_message;

    @TranslatableMessage
    public static String ModelAccessor_error_featureNotFound;

    @TranslatableMessage
    public static String ModelAccessorsRegistry_noResourceFound;

    @TranslatableMessage
    public static String ModelUtils_missingInputStream;

    @TranslatableMessage
    public static String ModelUtils_nullSerializationError;

    @TranslatableMessage
    public static String PermissionAuthorityRegistryImpl_noResourceMessage;

    @TranslatableMessage
    public static String ReferencesResolver_resolveNonContainedReferencesTask;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
