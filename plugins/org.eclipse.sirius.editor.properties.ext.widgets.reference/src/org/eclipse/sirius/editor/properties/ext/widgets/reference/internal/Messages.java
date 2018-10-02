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
package org.eclipse.sirius.editor.properties.ext.widgets.reference.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author sbegaudeau
 */
public class Messages {
    static {
        I18N.initializeMessages(Messages.class, SiriusEditorPropertiesExtWidgetsReferencePlugin.INSTANCE);
    }

    @TranslatableMessage
    public static String DefaultReferenceDescriptionFactory_name;

    @TranslatableMessage
    public static String DefaultReferenceDescriptionFactory_widgetLabel;
}
