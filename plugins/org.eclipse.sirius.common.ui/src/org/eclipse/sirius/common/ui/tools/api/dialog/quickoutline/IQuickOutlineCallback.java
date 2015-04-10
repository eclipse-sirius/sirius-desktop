/*******************************************************************************
 * Copyright (c) 2011, 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline;

/**
 * Callback for quick outline.
 * 
 * @author ymortier
 */
public interface IQuickOutlineCallback {

    /**
     * This method is invoked when the given object is invoked in the the quick
     * outline.
     * 
     * @param selectedElement
     *            the selected element.
     */
    void select(Object selectedElement);
}
