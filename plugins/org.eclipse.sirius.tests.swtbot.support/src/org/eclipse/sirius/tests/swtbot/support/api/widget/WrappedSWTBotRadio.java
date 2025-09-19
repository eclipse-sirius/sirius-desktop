/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.MessageFormat;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;

/**
 * A temporary class used to work around a bug (see
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=344484).
 * 
 * @author alagarde
 */
public class WrappedSWTBotRadio extends SWTBotRadio {

    /**
     * Default constructor.
     * 
     * @param wrapped
     *            The wrapped SWTBot radio to override click method.
     */
    public WrappedSWTBotRadio(SWTBotRadio wrapped) {
        super(wrapped.widget);
    }

    // CHECKSTYLE:OFF
    /**
     * Selects the radio button.
     */
    @Override
    public SWTBotRadio click() {
        if (isSelected()) {
            log.debug(MessageFormat.format("Widget {0} is already selected, not clicking again.", this).toString()); //$NON-NLS-1$
            return this;
        }
        waitForEnabled();
        log.debug(MessageFormat.format("Clicking on {0}", this).toString()); //$NON-NLS-1$
        asyncExec(new VoidResult() {
            @Override
            public void run() {
                deselectOtherRadioButtons();
                log.debug(MessageFormat.format("Clicking on {0}", this).toString()); //$NON-NLS-1$
                widget.setSelection(true);
            }

            /**
             * @see "http://dev.eclipse.org/viewcvs/index.cgi/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet224.java?view=co"
             */
            private void deselectOtherRadioButtons() {
                if (hasStyle(widget.getParent(), SWT.NO_RADIO_GROUP)) {
                    return;
                }
                Widget[] siblings = SWTUtils.siblings(widget);
                for (Widget widget : siblings) {
                    if (widget instanceof Button && hasStyle(widget, SWT.RADIO)) {
                        ((Button) widget).setSelection(false);
                    }
                }
            }
        });
        notify(SWT.MouseEnter);
        notify(SWT.MouseMove);
        notify(SWT.Activate);
        notify(SWT.FocusIn);
        notify(SWT.MouseDown);
        notify(SWT.MouseUp);
        notify(SWT.Selection);
        notify(SWT.MouseHover);
        notify(SWT.MouseMove);
        notify(SWT.MouseExit);
        notify(SWT.Deactivate);
        notify(SWT.FocusOut);
        log.debug(MessageFormat.format("Clicked on {0}", this).toString()); //$NON-NLS-1$
        return this;
    }
    // CHECKSTYLE:ON
}
