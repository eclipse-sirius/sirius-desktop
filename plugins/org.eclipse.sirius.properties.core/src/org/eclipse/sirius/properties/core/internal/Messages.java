/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Utility class used for the internationalization.
 * 
 * @author sbegaudeau
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusPropertiesCorePlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String DefaultRulesProvider_DefaultPropertiesNotFound;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instantiation.
    }
}
