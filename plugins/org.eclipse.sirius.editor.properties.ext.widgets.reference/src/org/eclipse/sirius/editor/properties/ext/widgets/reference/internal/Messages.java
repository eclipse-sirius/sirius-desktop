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
