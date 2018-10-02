/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.properties.filter;

import org.eclipse.sirius.diagram.ui.tools.api.properties.filter.AbstractPropertyFilter;

/**
 * Filters the Extension property section. Shows the section only if the
 * semantic element has extended properties.
 * 
 * @author ymortier
 */
public class SemanticExtensionPropertyFilter extends AbstractPropertyFilter {

    private SemanticPropertyFilter semanticPropertyFilter = new SemanticPropertyFilter();

    private ExtendedPropertyFilter extendedPropertyFilter = new ExtendedPropertyFilter();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.filter.AbstractPropertyFilter#select(java.lang.Object)
     */
    @Override
    public boolean select(final Object toTest) {
        return super.select(toTest) && semanticPropertyFilter.select(toTest) && extendedPropertyFilter.select(toTest);
    }

}
