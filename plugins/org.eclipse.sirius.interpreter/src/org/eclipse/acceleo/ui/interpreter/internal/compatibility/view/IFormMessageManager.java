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

import org.eclipse.swt.widgets.Control;

/**
 * As the IMessageManager for the forms APIs has been added in Eclipse 3.5, we need this indirection layer in order to
 * be compatible with Eclipse 3.4.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public interface IFormMessageManager {
    /**
     * Adds a new message to the form.
     * 
     * @param key
     *            Key for this message. Will only be used in 3.5 and above.
     * @param message
     *            The actual message to display.
     * @param type
     *            Type of the message. Either {@link org.eclipse.jface.dialogs.IMessageProvider#NONE},
     *            {@link org.eclipse.jface.dialogs.IMessageProvider#INFORMATION},
     *            {@link org.eclipse.jface.dialogs.IMessageProvider#WARNING} or
     *            {@link org.eclipse.jface.dialogs.IMessageProvider#ERROR}.
     */
    void addMessage(Object key, String message, int type);

    /**
     * Adds a new message to the form.
     * 
     * @param key
     *            Key for this message. Will only be used in 3.5 and above.
     * @param message
     *            The actual message to display.
     * @param type
     *            Type of the message. Either {@link org.eclipse.jface.dialogs.IMessageProvider#NONE},
     *            {@link org.eclipse.jface.dialogs.IMessageProvider#INFORMATION},
     *            {@link org.eclipse.jface.dialogs.IMessageProvider#WARNING} or
     *            {@link org.eclipse.jface.dialogs.IMessageProvider#ERROR}.
     * @param control
     *            The control that is to be decorated.
     */
    void addMessage(String key, String message, int type, Control control);

    /** Clears the message list. */
    void removeAllMessages();

    /**
     * Removes the message associated with the specified key.
     * 
     * @param key
     *            Key of the message we are to remove.
     */
    void removeMessage(Object key);

    /**
     * Removes all messages associated with the specified control.
     * 
     * @param control
     *            Control which messages are to be removed.
     */
    void removeMessages(Control control);

    /**
     * Sets the decoration position for this form. Ignored in Ganymede.
     * 
     * @param position
     *            The new position of decorections.
     */
    void setDecorationPosition(int position);
}
