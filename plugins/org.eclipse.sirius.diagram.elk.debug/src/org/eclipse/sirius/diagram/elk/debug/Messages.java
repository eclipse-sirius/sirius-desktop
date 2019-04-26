/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.diagram.elk.debug;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 *
 * @author lredor
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, DiagramElkDebugPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String ExportToElkGraphHandler_elkExportDialogTitle;

    @TranslatableMessage
    public static String ExportToElkGraphHandler_elkExportDialogMessage;

    @TranslatableMessage
    public static String ExportToElkGraphHandler_elkExportDialogNoAssociatedLayoutMessage;

    // CHECKSTYLE:ON
    private Messages() {
        // Prevents instanciation.
    }
}
