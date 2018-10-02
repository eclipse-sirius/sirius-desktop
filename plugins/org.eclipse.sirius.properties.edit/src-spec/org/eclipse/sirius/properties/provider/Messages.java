/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.properties.provider;

import org.eclipse.sirius.properties.provider.I18N.TranslatableMessage;

/**
 * Helper class to obtain translated strings.
 * 
 * @author sbegaudeau
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, PropertiesEditPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String ViewExtensionDescription__name;

    @TranslatableMessage
    public static String Category__name;

    @TranslatableMessage
    public static String PageDescription__name;

    @TranslatableMessage
    public static String GroupDescription__name;

    @TranslatableMessage
    public static String GridLayoutDescription__label;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instantiation.
    }
}
