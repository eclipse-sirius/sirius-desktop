/*******************************************************************************
 * Copyright (c) 2006, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class to access externalized Strings throughout the Interpreter plugin.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class InterpreterMessages {
    /** Qualified path to the properties file in which to seek the keys. */
    private static final String BUNDLE_NAME = "org.eclipse.sirius.ui.interpreter.internal.interpretermessages"; //$NON-NLS-1$

    /** Contains the locale specific {@link String}s needed by this plug-in. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Utility classes don't need to (and shouldn't) be instantiated.
     */
    private InterpreterMessages() {
        // prevents instantiation
    }

    /**
     * Returns the specified {@link String} from the resource bundle.
     * 
     * @param key
     *            Key of the String we seek.
     * @return The String from the resource bundle associated with <code>key</code>. <code>'!' + key + '!'</code> will
     *         be returned in case we didn't find it in the bundle.
     */
    public static String getString(String key) {
        // Pass through MessageFormat so that we're consistent in the handling of special chars such as the
        // apostrophe
        return MessageFormat.format(internalGetString(key), new Object[] {});
    }

    /**
     * Returns a String from the resource bundle bound with the given arguments.
     * 
     * @param key
     *            Key of the String we seek.
     * @param arguments
     *            Arguments for the String formatting.
     * @return formatted {@link String}.
     * @see MessageFormat#format(String, Object[])
     */
    public static String getString(String key, Object... arguments) {
        if (arguments == null) {
            return getString(key);
        }
        return MessageFormat.format(internalGetString(key), arguments);
    }

    /**
     * This will return an unformatted String from the resource bundle.
     * 
     * @param key
     *            Key of the String we seek.
     * @return An unformatted String from the bundle.
     */
    private static String internalGetString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
