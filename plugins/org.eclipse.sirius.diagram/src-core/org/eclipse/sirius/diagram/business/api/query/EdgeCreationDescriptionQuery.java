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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.viewpoint.DMappingBased;

/**
 * A class aggregating all the queries (read-only!) having a
 * EdgeCreationDescription as a starting point.
 * 
 * @author cbrun
 * 
 */
public class EdgeCreationDescriptionQuery {

    private EdgeCreationDescription tool;

    /**
     * Create a new query.
     * 
     * @param tool
     *            the element to query.
     */
    public EdgeCreationDescriptionQuery(EdgeCreationDescription tool) {
        this.tool = tool;
    }

    /**
     * return true if the edge could be created on the current source and target
     * checking the mapping consistency without considering the extra mapping.
     * 
     * @param source
     *            any source element
     * @param target
     *            any target element
     * @return true if the edge could be created on the current source and
     *         target checking the mapping consistency.
     */
    public boolean canCreate(DMappingBased source, DMappingBased target) {
        Collection<DiagramElementMapping> sources = new ArrayList<>();
        Collection<DiagramElementMapping> targets = new ArrayList<>();
        for (EdgeMapping edge : tool.getEdgeMappings()) {
            sources.addAll(edge.getSourceMapping());
            targets.addAll(edge.getTargetMapping());
        }

        return doCheckAtLeastOneIsInstanceOf(source, sources.iterator()) && doCheckAtLeastOneIsInstanceOf(target, targets.iterator());
    }

    /**
     * return true if the tool can be applied on the given elements, this method
     * leverage the extra mappings information.
     * 
     * @param source
     *            any source element
     * @param target
     *            any target element
     * @return true if the edge could be created on the current source and
     *         target checking the mapping consistency.
     */
    public boolean canBeAppliedOn(DMappingBased source, DMappingBased target) {
        return isValidAsSourceElement(source) && isValidAsTargetElement(target);
    }

    /**
     * return true if the given element is valid as a source of the tool
     * application.
     * 
     * @param element
     *            any element.
     * @return true if the given element is valid as a source of a potential
     *         edge.
     */
    public boolean isValidAsSourceElement(DMappingBased element) {
        Iterator<DiagramElementMapping> it = collectApplicableToolSourceMappings().iterator();
        return doCheckAtLeastOneIsInstanceOf(element, it);
    }

    /**
     * return true if the given element is valid as a target of the tool
     * application.
     * 
     * @param element
     *            any element.
     * @return true if the given element is valid as a target of a potential
     *         edge.
     */
    public boolean isValidAsTargetElement(DMappingBased element) {
        Iterator<DiagramElementMapping> it = collectApplicableToolTargetMappings().iterator();
        return doCheckAtLeastOneIsInstanceOf(element, it);
    }

    private boolean doCheckAtLeastOneIsInstanceOf(DMappingBased element, Iterator<DiagramElementMapping> it) {
        while (it.hasNext()) {
            DiagramElementMapping next = it.next();
            if (new DiagramElementMappingQuery(next).isInstanceOf(element)) {
                return true;
            }
        }
        return false;
    }

    private Iterable<DiagramElementMapping> collectApplicableToolSourceMappings() {
        Collection<DiagramElementMapping> sources = new ArrayList<>();
        for (EdgeMapping edge : tool.getEdgeMappings()) {
            sources.addAll(edge.getSourceMapping());
        }
        sources.addAll(tool.getExtraSourceMappings());
        return sources;
    }

    private Iterable<DiagramElementMapping> collectApplicableToolTargetMappings() {
        Collection<DiagramElementMapping> targets = new ArrayList<>();
        for (EdgeMapping edge : tool.getEdgeMappings()) {
            targets.addAll(edge.getTargetMapping());
        }
        targets.addAll(tool.getExtraTargetMappings());
        return targets;
    }
}
