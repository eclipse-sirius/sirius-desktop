/*******************************************************************************
 * Copyright (c) 2008, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.util;

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Tools for edit part.
 * 
 * @author ymortier
 */
public final class EditPartTools {

    /**
     * Avoid instantiation.
     */
    private EditPartTools() {
    }

    /**
     * Returns the first edit part that is an instance of the class
     * <code>editPartType</code>.
     * 
     * @param root
     *            the root edit part.
     * @param editPartType
     *            the type of the edit part.
     * @return the first edit part that is an instance of the class
     *         <code>editPartType</code>.
     */
    public static EditPart getEditPartOfType(final EditPart root, final Class<?> editPartType) {
        if (root == null || editPartType == null) {
            throw new IllegalArgumentException(Messages.EditPartTools_nullParameterMsg);
        }
        EditPart result = null;
        if (editPartType.isInstance(root)) {
            result = root;
        }
        if (result == null) {
            var iterChildren = root.getChildren().iterator();
            while (iterChildren.hasNext() && result == null) {
                result = EditPartTools.getEditPartOfType((EditPart) iterChildren.next(), editPartType);
            }
        }
        return result;
    }

    /**
     * Returns an ancestor of the given edit part.
     * 
     * @param <T>
     *            the type of the ancestor to return.
     * @param aChild
     *            a child edit part.
     * @param type
     *            the type of the ancestor to return.
     * @return the found edit part.
     */
    public static <T extends EditPart> T getParentOfType(final EditPart aChild, final Class<T> type) {
        T result = null;
        EditPart current = aChild;
        while (result == null && current != null) {
            if (type.isInstance(current)) {
                result = type.cast(current);
            } else {
                current = current.getParent();
            }
        }
        return result;
    }

}
