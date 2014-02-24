/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
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
 * Doesn't sort the list.
 * 
 * @author ymortier
 */
public class SimpleViewOrdering extends AbstractViewOrdering {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractViewOrdering#sortViews(java.util.List)
     */
    @Override
    protected List<View> sortViews(final List<View> views) {
        return views;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#isAbleToManageView(org.eclipse.gmf.runtime.notation.View)
     */
    public boolean isAbleToManageView(final View view) {
        return true;
    }

}
