/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor;

import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author Florian Barbin
 *
 */
public final class Messages {
    static {
        I18N.initializeMessages(Messages.class, SiriusEditorPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String ServiceNavigator_targetInitialization_error;

    @TranslatableMessage
    public static String ServiceNavigator_serviceNavigationDialog_title;

    @TranslatableMessage
    public static String ServiceNavigator_serviceNavigationDialog_description;

    @TranslatableMessage
    public static String CustomSiriusEditor_failedNavigationTitle;

    @TranslatableMessage
    public static String CustomSiriusEditor_failedNavigationMessage;

    @TranslatableMessage
    public static String CustomSiriusEditor_failedNavigationExceptionMessage;

    // CHECKSTYLE:ON
    private Messages() {
        // Prevents instanciation.
    }
}
