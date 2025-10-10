/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.compatibility.view;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.eclipse.ui.forms.widgets.Form;

/**
 * This utility class is only used in order to create a FormMessageManager for the current platform. In Ganymede, forms
 * did not have their own managers, we'll thus have to create a specific one. Otherwise we'll simply create a manager
 * that delegates all calls to the form itself.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class FormMessageManagerFactory {
    /** Utility classes don't need a constructor. */
    private FormMessageManagerFactory() {
        // Hides default constructor
    }

    /**
     * Creates a message manager for the current platform.
     * 
     * @param managedForm
     *            The form for which we are to manage the messages.
     * @return The message manager for the current platform.
     */
    public static IFormMessageManager createFormMessageManager(Form managedForm) {
        for (Method method : Form.class.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && "getMessageManager".equals(method.getName())) { //$NON-NLS-1$
                return new FormMessageManager(managedForm);
            }
        }
        return new FormMessageManagerGanymede(managedForm);
    }
}
