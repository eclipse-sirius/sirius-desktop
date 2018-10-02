/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.swt;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * Common interface for TextChangeListener.
 * 
 * @author cbrun
 * 
 */
public interface TextChangeListener extends Listener {
    /**
     * Start listening to the text.
     * 
     * @param text
     *            the text to listen.
     */
    void startListeningTo(Text text);

    /**
     * Start listening for Enter.
     * 
     * @param text
     *            the text to listen.
     */
    void startListeningForEnter(Text text);

    /**
     * Stop listening to .
     * 
     * @param text
     *            the text to listen.
     */
    void stopListeningTo(Text text);

}
