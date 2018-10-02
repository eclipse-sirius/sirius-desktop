/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
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
package org.eclipse.sirius.diagram.formatdata.tools;

import org.eclipse.sirius.diagram.formatdata.FormatDataPlugin;
import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, FormatDataPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String EdgeConfigurationImpl_toString;

    @TranslatableMessage
    public static String ConfigurationImpl_toString;

    @TranslatableMessage
    public static String FormatHelperImpl_formatDifferenceMessage;

    @TranslatableMessage
    public static String NodeConfigurationImpl_distanceAroundPoint;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
