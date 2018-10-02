/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
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
