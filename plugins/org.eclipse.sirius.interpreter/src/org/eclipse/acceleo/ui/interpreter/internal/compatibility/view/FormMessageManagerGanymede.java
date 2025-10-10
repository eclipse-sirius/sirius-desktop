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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.widgets.Form;

/**
 * This implementation of an IFormMessageManager will only be used in Eclipse 3.4.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class FormMessageManagerGanymede implements IFormMessageManager {
    /**
     * These will be used as the summary when multiple messages are to be added to the form. This is specific to
     * galileo.
     */
    private static final String[] MULTIPLE_MESSAGE_SUMMARY_KEYS = new String[] { "interpreter.message.summary.information", //$NON-NLS-1$
            "interpreter.message.summary.information", //$NON-NLS-1$
            "interpreter.message.summary.warning", //$NON-NLS-1$
            "interpreter.message.summary.error", //$NON-NLS-1$
    };

    /** The form for which we manage messages. */
    private final Form managedForm;

    /** Messages currently displayed on the form. */
    private List<Message> messages = new ArrayList<Message>();

    /**
     * Instantiates our message managed given its target form.
     * 
     * @param managedForm
     *            The form for which we should manage messages.
     */
    public FormMessageManagerGanymede(Form managedForm) {
        this.managedForm = managedForm;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.IFormMessageManager#addMessage(java.lang.Object,
     *      java.lang.String, int)
     */
    public synchronized void addMessage(Object key, String message, int type) {
        int index = -1;
        for (int i = 0; i < messages.size() && index == -1; i++) {
            if (key.equals(messages.get(i).getKey())) {
                index = i;
            }
        }
        if (index != -1) {
            messages.remove(index);
        }
        messages.add(new Message(key, message, type));
        updateMessages();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.IFormMessageManager#addMessage(java.lang.String,
     *      java.lang.String, int, org.eclipse.swt.widgets.Control)
     */
    public synchronized void addMessage(String key, String message, int type, Control control) {
        int index = -1;
        for (int i = 0; i < messages.size() && index == -1; i++) {
            if (key.equals(messages.get(i).getKey())) {
                index = i;
            }
        }
        if (index != -1) {
            messages.remove(index);
        }
        messages.add(new Message(key, message, type, control));
        updateMessages();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.IFormMessageManager#removeAllMessages()
     */
    public synchronized void removeAllMessages() {
        messages.clear();
        updateMessages();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.IFormMessageManager#removeMessage(java.lang.Object)
     */
    public synchronized void removeMessage(Object key) {
        int index = -1;
        for (int i = 0; i < messages.size() && index == -1; i++) {
            if (key.equals(messages.get(i).getKey())) {
                index = i;
            }
        }
        if (index != -1) {
            messages.remove(index);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.IFormMessageManager#removeMessages(org.eclipse.swt.widgets.Control)
     */
    public synchronized void removeMessages(Control control) {
        for (Message message : new ArrayList<Message>(messages)) {
            if (control.equals(message.getControl())) {
                messages.remove(message);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.ui.interpreter.internal.compatibility.view.IFormMessageManager#setDecorationPosition(int)
     */
    public void setDecorationPosition(int position) {
        // Ignored in Ganymede
    }

    /**
     * Updates the form with our current list of messages.
     */
    private synchronized void updateMessages() {
        if (messages.isEmpty()) {
            managedForm.setMessage(null, IMessageProvider.NONE);
        } else if (messages.size() == 1) {
            Message message = messages.get(0);
            managedForm.setMessage(message.getMessage(), message.getMessageType());
        } else {
            Collections.sort(messages);
            final int type = messages.get(0).getMessageType();
            final String summary = InterpreterMessages.getString(MULTIPLE_MESSAGE_SUMMARY_KEYS[type], messages.size());
            managedForm.setMessage(summary, type, messages.toArray(new IMessage[messages.size()]));
        }
    }

    /**
     * Represents one of the messages we are to add to the form. This is Ganymede specific.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    private class Message implements Comparable<Message>, IMessage {
        /** Control with which this message is associated. */
        private Control control;

        /** The unique message key. */
        private Object key;

        /** The actual message. */
        private String message;

        /** Type of our message. */
        private int type;

        /**
         * Constructs a message.
         * 
         * @param key
         *            The unique key of this message.
         * @param message
         *            The actual message.
         * @param type
         *            Type of this message.
         */
        Message(Object key, String message, int type) {
            this(key, message, type, null);
        }

        /**
         * Constructs a message.
         * 
         * @param key
         *            The unique key of this message.
         * @param message
         *            The actual message.
         * @param type
         *            Type of this message.
         * @param control
         *            Control with which this message is to be associated
         */
        Message(Object key, String message, int type, Control control) {
            this.key = key;
            this.message = message;
            this.type = type;
            this.control = control;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(Message o) {
            // Errors come before warnings, warnings before information, info before normal
            if (this.type < o.type) {
                return 1;
            }
            // Otherwise, use the natural ordering of Strings
            return this.message.compareTo(o.message);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.forms.IMessage#getControl()
         */
        public Control getControl() {
            return control;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.forms.IMessage#getData()
         */
        public Object getData() {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.forms.IMessage#getKey()
         */
        public Object getKey() {
            return key;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.dialogs.IMessageProvider#getMessage()
         */
        public String getMessage() {
            return message;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.dialogs.IMessageProvider#getMessageType()
         */
        public int getMessageType() {
            return type;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.forms.IMessage#getPrefix()
         */
        public String getPrefix() {
            return null;
        }
    }
}
