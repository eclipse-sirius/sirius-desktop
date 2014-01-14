/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.utils;

import org.eclipse.sirius.ext.swt.TextChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

/**
 * TextChangeHelper notifies the listener of text lifecycle events on behalf of
 * the widget(s) it listens to.
 */
public abstract class TextChangeHelper implements TextChangeListener {

    protected boolean notifyOnCarriageReturn = true;

    public TextChangeHelper() {
    }

    public TextChangeHelper(boolean notifyOnCarriageReturn) {
        this.notifyOnCarriageReturn = notifyOnCarriageReturn;
    }

    /**
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.KeyDown:
            if (event.character == SWT.CR && notifyOnCarriageReturn)
                textChanged((Text) event.widget);
            break;
        case SWT.FocusOut:
            textChanged((Text) event.widget);
            break;
        default:
            break;
        }
    }

    /**
     * Abstract method notified when a text field has been changed.
     * 
     * @param control
     *            the given control.
     */
    public abstract void textChanged(Text control);

    /**
     * Registers this helper with the given control to listen for events which
     * indicate that a change is in progress (or done).
     * 
     * @param control
     *            the given control.
     */
    public void startListeningTo(Text control) {
        control.addListener(SWT.FocusOut, this);
        control.addListener(SWT.Modify, this);
    }

    /**
     * Registers this helper with the given control to listen for the Enter key.
     * When Enter is pressed, the change is considered done (this is only
     * appropriate for single-line Text widgets).
     * 
     * @param control
     *            the given control.
     */
    public void startListeningForEnter(Text control) {
        // NOTE: KeyDown rather than KeyUp, because of similar usage in CCombo.
        control.addListener(SWT.KeyDown, this);
    }

    /**
     * Unregisters this helper from a control previously passed to
     * startListeningTo() and/or startListeningForEnter().
     * 
     * @param control
     *            the given control.
     */
    public void stopListeningTo(Text control) {
        if ((control != null) && !control.isDisposed()) {
            control.removeListener(SWT.FocusOut, this);
            control.removeListener(SWT.Modify, this);
            control.removeListener(SWT.KeyDown, this);
        }
    }
}
