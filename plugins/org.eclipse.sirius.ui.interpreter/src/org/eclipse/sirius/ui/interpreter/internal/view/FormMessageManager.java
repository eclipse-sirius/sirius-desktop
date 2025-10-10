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
package org.eclipse.sirius.ui.interpreter.internal.view;

import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.Form;

/**
 * This implementation of an IFormMessageManager <b>does not compile in Eclipse Ganymede!</b> However it will never be
 * called or instantiated if running within Eclipse 3.4, and is thus safe.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @see FormMessageManagerFactory
 */
public class FormMessageManager implements IFormMessageManager {
    /** The form for which we manage messages. */
    private final Form managedForm;

    /**
     * Instantiates our message managed given its target form.
     * 
     * @param managedForm
     *            The form for which we should manage messages.
     */
    public FormMessageManager(Form managedForm) {
        this.managedForm = managedForm;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.view.IFormMessageManager#addMessage(java.lang.Object,
     *      java.lang.String, int)
     */
    public void addMessage(Object key, String message, int type) {
        if (!managedForm.isDisposed()) {
            managedForm.getMessageManager().addMessage(key, message, null, type);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.view.IFormMessageManager#addMessage(java.lang.String,
     *      java.lang.String, int, org.eclipse.swt.widgets.Control)
     */
    public void addMessage(String key, String message, int type, Control control) {
        if (!managedForm.isDisposed()) {
            managedForm.getMessageManager().addMessage(key, message, null, type, control);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.view.IFormMessageManager#removeAllMessages()
     */
    public void removeAllMessages() {
        if (!managedForm.isDisposed()) {
            managedForm.getMessageManager().removeAllMessages();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.view.IFormMessageManager#removeMessage(java.lang.Object)
     */
    public void removeMessage(Object key) {
        if (!managedForm.isDisposed()) {
            managedForm.getMessageManager().removeMessage(key);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.view.IFormMessageManager#removeMessages(org.eclipse.swt.widgets.Control)
     */
    public void removeMessages(Control control) {
        if (!managedForm.isDisposed()) {
            managedForm.getMessageManager().removeMessages(control);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.view.IFormMessageManager#setDecorationPosition(int)
     */
    public void setDecorationPosition(int position) {
        if (!managedForm.isDisposed()) {
            managedForm.getMessageManager().setDecorationPosition(position);
        }
    }
}
