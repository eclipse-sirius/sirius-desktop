/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Utility class used for the internationalization.
 * 
 * @author sbegaudeau
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusUIPropertiesPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String SiriusTabDescriptorProvider_UnsupportedMultipleSelection;

    @TranslatableMessage
    public static String SiriusTabDescriptorProvider_UndefinedSemanticElement;

    @TranslatableMessage
    public static String SiriusToolServices_DefaultCategoryName;

    @TranslatableMessage
    public static String TransactionalEditingDomainContextAdapter_errorDuringCommand;

    @TranslatableMessage
    public static String DialogTask_label;

    @TranslatableMessage
    public static String WizardTask_label;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instantiation.
    }
}
