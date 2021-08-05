/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.properties.filter.AbstractPropertyFilter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessorsRegistry;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Filters for semantic elements.
 * 
 * @author ymortier
 */
public class SemanticPropertyFilter extends AbstractPropertyFilter {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.filter.AbstractPropertyFilter#select(java.lang.Object)
     */
    @Override
    public boolean select(final Object toTest) {
        final boolean select = super.select(toTest);
        boolean areAnnotation = true;
        ModelAccessorsRegistry modelAccessorRegistry = SiriusPlugin.getDefault().getModelAccessorRegistry();
        final Iterator<EObject> iterSemantics = this.semanticElements.iterator();
        while (iterSemantics.hasNext() && areAnnotation) {
            final EObject next = iterSemantics.next();
            ModelAccessor modelAccessor = modelAccessorRegistry.getModelAccessor(next);
            if (!modelAccessor.isExtension(next)) {
                areAnnotation = false;
            }
        }
        return select && !areAnnotation;
    }
}
