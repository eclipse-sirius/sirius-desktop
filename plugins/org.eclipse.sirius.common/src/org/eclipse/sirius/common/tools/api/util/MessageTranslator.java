/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

/**
 * A singleton helper dedicated to process a translatable String and return the
 * message in the active language.
 *
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 *
 */
public class MessageTranslator {

    /**
     * Singleton of MessageTranslator.
     */
    public static final MessageTranslator INSTANCE = new MessageTranslator();

    private static final String PERCENT = "%"; //$NON-NLS-1$

    /**
     * Service dedicated to process a translatable String and return the message
     * in the active language. If the String is not a translatable one, it will
     * not be processed but return it as is.
     *
     * @param value
     *            String value wrote in the VSM to process.
     * @return the available translation, or the message if not translatable.
     */
    public String getMessage(String value) {
        if (value != null && value.startsWith(PERCENT)) {
            // TODO find the corresponding message
        }
        return value;
    }

}
