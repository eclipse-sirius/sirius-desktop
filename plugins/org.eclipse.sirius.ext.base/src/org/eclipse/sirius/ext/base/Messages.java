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
package org.eclipse.sirius.ext.base;

import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author pcdavid
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, BasePlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String DependencyTracker_error_untrackedElement;

    @TranslatableMessage
    public static String Option_absent;

    @TranslatableMessage
    public static String Option_present;

    @TranslatableMessage
    public static String TransitiveClosure_message;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
