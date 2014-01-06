/*******************************************************************************
 * Copyright (c) 2007 - 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.utils;

import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * TextWithContentAssistChangeHelper notifies the listener of text with content
 * assist lifecycle events on behalf of the widget(s) it listens to.
 * 
 * @author smonnier
 */
public abstract class TextWithContentAssistChangeHelper extends TextChangeHelper {
    /**
     * {@inheritDoc}
     */
    @Override
    public void handleEvent(Event event) {
        IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class);
        TriggerSequence[] contentAssistBindings = bindingService.getActiveBindingsFor(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);

        if (contentAssistBindings != null && contentAssistBindings.length > 0) {
            final KeyStroke keyStroke = getKeyStroke(contentAssistBindings[0]);
            switch (event.type) {
            case SWT.KeyDown:
                boolean isCR = event.character == SWT.CR && notifyOnCarriageReturn;
                if (isCR || (keyStroke != null && event.keyCode == keyStroke.getNaturalKey() && event.stateMask == keyStroke.getModifierKeys())) {
                    textChanged((Text) event.widget);
                }
                break;
            case SWT.FocusOut:
                textChanged((Text) event.widget);
                break;
            default:
                break;
            }
        }
    }

    private KeyStroke getKeyStroke(TriggerSequence sequence) {
        for (Trigger trigger : sequence.getTriggers()) {
            if (trigger instanceof KeyStroke) {
                return (KeyStroke) trigger;
            }
        }
        return null;
    }
}
