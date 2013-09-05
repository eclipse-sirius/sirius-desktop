/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.List;

import org.eclipse.gmf.runtime.notation.View;

/**
 * Represents a type that is able to order views.
 * 
 * @author ymortier
 */
public interface ViewOrdering extends GridViewOrdering {

    /**
     * Return the views ordered by this {@link ViewOrdering}. The resulted list
     * might not be modified by the caller. An unmodifiable list will throws an
     * {@link UnsupportedOperationException} if the caller attempts to modify
     * the list.
     * 
     * @return the views ordered by this {@link ViewOrdering}.
     */
    List<View> getSortedViews();

}
