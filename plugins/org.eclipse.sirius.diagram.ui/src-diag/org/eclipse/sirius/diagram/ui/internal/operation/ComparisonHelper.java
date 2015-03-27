/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

/**
 * 
 * @author pcdavid
 *
 */
public abstract class ComparisonHelper {

    public final void sort(List<? extends View> views) {
        /*
         * The main sort criterion is based on the elements' mapping's position
         * in the VSM, so that all instances of the same mapping are grouped
         * together, and if a mapping M1 appears before another M2 in the
         * specification, all instances of M1 appear before those of M2.
         */
        final List<? extends RepresentationElementMapping> allMappings = getMappingsToSort();
        Function<View, Integer> mappingIndex = new Function<View, Integer>() {
            @Override
            public Integer apply(View view) {
                if (view != null) {
                    EObject element = view.getElement();
                    if (element instanceof DMappingBased) {
                        RepresentationElementMapping mapping = ((DMappingBased) element).getMapping();
                        /*
                         * Use a plain indexOf search here, assuming that in
                         * practice there are never more than a handful of
                         * mappings inside a list container.
                         */
                        return allMappings.indexOf(mapping);
                    }
                }
                return Integer.MAX_VALUE;
            }
        };
        /*
         * Inside a group of elements from the same mapping, use the
         * DRepresentationElement order. As opposed to the mappings, the number
         * of actual items can grow very large, so we pre-compute the elements'
         * indices with a linear scan to avoid repeated calls to indexOf for
         * each comparison.
         */
        final Map<DRepresentationElement, Integer> indices = Maps.newHashMap();
        Collection<? extends DRepresentationElement> elements = getDElementsToSort();

        int i = 0;
        for (DRepresentationElement current : elements) {
            indices.put(current, i);
            i++;
        }
        Function<View, Integer> nodeIndex = new Function<View, Integer>() {
            @Override
            public Integer apply(View view) {
                if (view != null) {
                    EObject sem = ViewUtil.resolveSemanticElement(view);
                    if (sem != null && indices.containsKey(sem)) {
                        return indices.get(sem);
                    }
                }
                return Integer.MAX_VALUE;
            }
        };
        /*
         * Perform the actual sort, combining the two criteria above.
         */
        Collections.sort(views, Ordering.natural().onResultOf(mappingIndex).compound(Ordering.natural().onResultOf(nodeIndex)));
    }

    protected abstract List<? extends DRepresentationElement> getDElementsToSort();

    protected abstract List<? extends RepresentationElementMapping> getMappingsToSort();
}
