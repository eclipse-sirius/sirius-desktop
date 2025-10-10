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
package org.eclipse.sirius.ui.interpreter.internal.language;

/**
 * If one of the languages implemented for the interpreter needs to know about the interpreter context in order to
 * display its content assists, this interface should be added to its source viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public interface IInterpreterSourceViewer {
    /**
     * This will be called by the interpreter view with the current interpreter context whenever content assist is
     * required by the user.
     * 
     * @param context
     *            The current interpreter context.
     */
    void showContentAssist(InterpreterContext context);
}
