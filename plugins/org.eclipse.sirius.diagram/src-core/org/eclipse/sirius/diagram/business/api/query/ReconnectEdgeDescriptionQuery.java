/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.viewpoint.DMappingBased;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link ReconnectEdgeDescription} as a starting point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class ReconnectEdgeDescriptionQuery {
    private ReconnectEdgeDescription reconnectEdgeDescription;

    /**
     * Create a new query.
     * 
     * @param reconnectEdgeDescription
     *            the element to query.
     */
    public ReconnectEdgeDescriptionQuery(ReconnectEdgeDescription reconnectEdgeDescription) {
        this.reconnectEdgeDescription = reconnectEdgeDescription;
    }

    /**
     * Check if the mapping of the <code>end</code> is in the source or target
     * list of the edgeMappings of this reconnectDescription.
     * 
     * @param source
     *            true if the <code>end</code> is the sourceEnd, false otherwise
     * @param end
     *            The end candidate to check.
     * @return true if this end is authorized for this reconnect tool, false
     *         otherwise.
     */
    public boolean isEndAuthorized(boolean source, EdgeTarget end) {
        boolean result = false;
        if (end instanceof DMappingBased) {
            result = isEndAuthorized(source, (DMappingBased) end);
        }
        return result;
    }

    /**
     * Check if the mapping of the <code>end</code> is in the source or target
     * list of the edgeMappings of this reconnectDescription.
     * 
     * @param source
     *            true if the <code>end</code> is the sourceEnd, false otherwise
     * @param end
     *            The end candidate to check.
     * @return true if this end is authorized for this reconnect tool, false
     *         otherwise.
     */
    public boolean isEndAuthorized(boolean source, DMappingBased end) {
        boolean result = false;
        if (source) {
            result = isValidAsSourceElement(end);
        } else {
            result = isValidAsTargetElement(end);
        }
        return result;
    }

    /**
     * return true if the given element is valid as a source for the reconnect
     * tool.
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
     * return true if the given element is valid as a target for the reconnect
     * tool.
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

    private Iterable<DiagramElementMapping> collectApplicableToolSourceMappings() {
        Collection<DiagramElementMapping> sources = new ArrayList<>();
        for (EdgeMapping edge : reconnectEdgeDescription.getMappings()) {
            sources.addAll(edge.getSourceMapping());
        }
        return sources;
    }

    private Iterable<DiagramElementMapping> collectApplicableToolTargetMappings() {
        Collection<DiagramElementMapping> targets = new ArrayList<>();
        for (EdgeMapping edge : reconnectEdgeDescription.getMappings()) {
            targets.addAll(edge.getTargetMapping());
        }
        return targets;
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
}
