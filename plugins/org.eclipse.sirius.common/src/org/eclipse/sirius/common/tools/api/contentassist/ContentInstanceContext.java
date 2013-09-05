/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * ContentInstanceContext is used in the Request Interpreter view.
 * 
 * @author smonnier
 * 
 */
public class ContentInstanceContext {

    private EObject currentSelected;

    private String textSoFar;

    private int cursorPosition;

    private EditingDomain editingDomain;

    /**
     * Constructor.
     * 
     * @param currentSelected
     *            EObject we want the completion
     * @param textSoFar
     *            currently written text
     * @param cursorPosition
     *            position of the cursor on the currently written text
     */
    public ContentInstanceContext(final EObject currentSelected, final String textSoFar, final int cursorPosition) {
        this.currentSelected = currentSelected;
        this.textSoFar = textSoFar;
        this.cursorPosition = cursorPosition;
    }

    /**
     * Constructor.
     * 
     * @param currentSelected
     *            EObject we want the completion
     * @param textSoFar
     *            currently written text
     * @param cursorPosition
     *            position of the cursor on the currently written text
     * @param editingDomain
     *            editingDomain of the currentSelected EObject
     */
    public ContentInstanceContext(final EObject currentSelected, final String textSoFar, final int cursorPosition, final EditingDomain editingDomain) {
        this.currentSelected = currentSelected;
        this.textSoFar = textSoFar;
        this.cursorPosition = cursorPosition;
        this.editingDomain = editingDomain;
    }

    public EObject getCurrentSelected() {
        return currentSelected;
    }

    public String getTextSoFar() {
        return textSoFar;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public EditingDomain getEditingDomain() {
        return editingDomain;
    }
}
