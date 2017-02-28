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
package org.eclipse.sirius.ui.editor;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author pcdavid
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SessionEditorPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String UI_SessionEditor_header_title;

    @TranslatableMessage
    public static String UI_SessionEditor_page_loading_error_message;

    @TranslatableMessage
    public static String UI_SessionEditor_session_loading_error_message;

    @TranslatableMessage
    public static String UI_SessionEditor_session_loading_task_title;

    @TranslatableMessage
    public static String UI_SessionEditor_default_page_tab_label;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
