/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.contentassist;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;

import com.google.common.base.Preconditions;

/**
 * This class contains some informations about the context.
 * 
 * @author mchauvin
 */
public class ContentContext {

    private String contents;

    private int position;

    private IInterpreterContext interpreterContext;

    /**
     * This class contains some informations about the context.
     * 
     * 
     * @param text
     *            the text content.
     * @param position
     *            the cursor position.
     * @param interpreterContext
     *            the context to compute proposal for the given content and
     *            cursor position. computed
     */
    public ContentContext(final String text, final int position, IInterpreterContext interpreterContext) {
        Preconditions.checkNotNull(interpreterContext);
        this.contents = text;
        this.position = position;
        this.interpreterContext = interpreterContext;
    }

    public String getContents() {
        return contents;
    }

    public int getPosition() {
        return position;
    }

    /**
     * Get the interpreter context.
     * 
     * @return the interpreter context.
     * 
     * @since 0.9.0
     */
    public IInterpreterContext getInterpreterContext() {
        return interpreterContext;
    }
}
