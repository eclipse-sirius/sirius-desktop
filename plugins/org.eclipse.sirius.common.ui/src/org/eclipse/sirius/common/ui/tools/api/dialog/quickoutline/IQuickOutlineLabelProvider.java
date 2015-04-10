/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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

import org.eclipse.jface.viewers.ILabelProvider;

/**
 * Interface for label providers of quick outline.
 * 
 * @author bgrouhan
 *
 */
public interface IQuickOutlineLabelProvider extends ILabelProvider {

    /**
     * Delete all added matches.
     */
    void clear();

    /**
     * Method to signal that there is an element matching with one of its
     * attributes.
     * 
     * @param matchingElement
     *            the matching element.
     * @param attributeName
     *            the name of the matching attribute.
     */
    void addMatch(Object matchingElement, String attributeName);
}
