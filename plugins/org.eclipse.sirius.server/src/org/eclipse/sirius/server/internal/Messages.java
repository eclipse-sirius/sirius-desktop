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
package org.eclipse.sirius.server.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Utility class used for the internationalization.
 *
 * @author sbegaudeau
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusServerPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String SiriusServerManager_cannotStartServer;

    @TranslatableMessage
    public static String SiriusServerManager_cannotStopServer;

    @TranslatableMessage
    public static String SiriusServerConfigurator_wrongPropertyTypeWarning;

    @TranslatableMessage
    public static String SiriusServerConfigurator_headerConfig;
    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instantiation.
    }
}
