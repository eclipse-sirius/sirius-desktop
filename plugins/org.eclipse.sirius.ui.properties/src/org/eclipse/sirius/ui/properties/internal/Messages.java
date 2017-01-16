/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instantiation.
    }
}
