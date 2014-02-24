/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.filter.spec;

import java.util.Iterator;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.impl.CompositeFilterDescriptionImpl;

/**
 * Customizations for the <code>CompositeFilterDescription</code>
 * implementation.
 * 
 * @author cbrun
 */
public class CompositeFilterDescriptionSpec extends CompositeFilterDescriptionImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible(final DDiagramElement element) {
        /* Here we delegate to all the contained filters */
        final Iterator<Filter> it = getFilters().iterator();
        while (it.hasNext()) {
            final Filter filter = it.next();
            /*
             * element should be invisible and filter kind should be hide =>
             * when collapse element is visible
             */
            if (!filter.isVisible(element) && filter.getFilterKind() == FilterKind.HIDE_LITERAL) {
                return false;
            }
        }
        return true;

    }
}
