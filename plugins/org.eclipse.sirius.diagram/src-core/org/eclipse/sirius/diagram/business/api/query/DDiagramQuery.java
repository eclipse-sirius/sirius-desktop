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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.business.internal.query.model.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.description.Layer;

/**
 * A class aggregating all the queries (read-only!) having a {@link DDiagram} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class DDiagramQuery extends DRepresentationQuery {

    /**
     * The element to query.
     */
    protected DDiagram dDiagram;

    /**
     * Create a new query.
     * 
     * @param dDiagram
     *            the element to query.
     */
    public DDiagramQuery(DDiagram dDiagram) {
        super(dDiagram);
        this.dDiagram = dDiagram;
    }

    /**
     * Returns all diagram elements (directly and indirectly contained) of the given diagram.
     * 
     * @return all diagram elements of the given diagram.
     */
    public Collection<DDiagramElement> getAllDiagramElements() {
        return new DDiagramInternalQuery(dDiagram).getDiagramElements();
    }

    /**
     * Find the hidden elements in the current {@link DDiagram} that can be displayed if revealed.
     * 
     * @return The hidden elements or empty list if not found. Cannot be <code>null</code>.
     */
    public Set<DDiagramElement> findHiddenElements() {
        final Set<DDiagramElement> result = new HashSet<>();
        if (dDiagram != null) {
            Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) dDiagram).getTarget());
            for (final DDiagramElement diagramElement : dDiagram.getDiagramElements()) {
                if (isHidden(session, diagramElement)) {
                    result.add(diagramElement);
                }
            }
        }
        return result;
    }

    /**
     * Check if this {@link DDiagramElement} is directly hidden, not filtered and in activated layers.
     * 
     * @param session
     *            the current session.
     * @param dDiagramElement
     *            the {@link DDiagramElement} to check.
     * @return true if the given element is hidden.
     */
    public boolean isHidden(Session session, DDiagramElement dDiagramElement) {
        DDiagramElementQuery query = new DDiagramElementQuery(dDiagramElement);
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, dDiagram);
        return query.isHidden() && LayerHelper.isInActivatedLayer(mappingManager, dDiagramElement, dDiagram) && !query.isFiltered();
    }

    /**
     * Check if the label of this {@link DDiagramElement} is directly hidden, not filtered and in activated layers.
     * 
     * @param session
     *            the current session.
     * @param dDiagramElement
     *            the {@link DDiagramElement} to check.
     * @return true if the label of the given element is hidden.
     */
    public boolean isLabelHidden(Session session, DDiagramElement dDiagramElement) {
        DDiagramElementQuery query = new DDiagramElementQuery(dDiagramElement);
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, dDiagram);
        return query.isLabelHidden() && LayerHelper.isInActivatedLayer(mappingManager, dDiagramElement, dDiagram) && !query.isFiltered();
    }

    /**
     * Check if this {@link DDiagram} contains hidden elements that can be displayed if revealed.
     * 
     * @return true if the diagram contains hidden elements
     */
    public boolean containsHiddenElements() {
        return !(dDiagram == null) && !(dDiagram.getHiddenElements() == null) && !dDiagram.getHiddenElements().isEmpty();
    }

    /**
     * Returns all the activated layers (transient or not) of the given diagram.
     * 
     * @return all the activated layers (transient or not) of the given diagram.
     */
    public List<Layer> getAllActivatedLayers() {
        final List<Layer> activatedLayers = new ArrayList<Layer>();
        activatedLayers.addAll(dDiagram.getActivatedLayers());
        activatedLayers.addAll(dDiagram.getActivatedTransientLayers());
        return activatedLayers;
    }
}
