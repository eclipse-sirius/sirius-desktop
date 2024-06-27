/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;

import com.google.common.collect.Sets;

/**
 * A class aggregating all the queries (read-only!) having a paste target as a
 * starting point.
 * 
 * @author mporhel
 * 
 */
public class PasteTargetQuery {

    private DSemanticDecorator semDec;

    /**
     * Create a new query.
     * 
     * @param decorator
     *            the element to query.
     */
    public PasteTargetQuery(DSemanticDecorator decorator) {
        this.semDec = decorator;
    }

    /**
     * Return all available paste tools for the current decorator, regarding its
     * description.
     * 
     * @return all available paste tools for the specified
     *         PasteTargetDescription.
     */
    public Collection<PasteDescription> getAvailablePasteTools() {
        final Collection<PasteDescription> result = new ArrayList<>();
        if (semDec instanceof DDiagram) {
            DDiagram diag = (DDiagram) semDec;
            result.addAll(getActivatedPasteTools(diag, diag.getDescription()));
        } else if (semDec instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) semDec;
            result.addAll(getActivatedPasteTools(dde.getParentDiagram(), dde.getDiagramElementMapping()));
        }
        // No other cases : DDiagram could only handle paste operation on itself
        // or on of its element.
        return result;
    }

    /**
     * Returns the paste tools of the pasteTargetDescription.
     * 
     * @param pasteTargetDescription
     * @return the paste tools of the pasteTargetDescription
     */
    private Collection<PasteDescription> getAllPasteTools(final PasteTargetDescription pasteTargetDescription) {
        Collection<PasteDescription> pasteTools = new HashSet<>();
        if (pasteTargetDescription instanceof DiagramElementMapping) {
            pasteTools = Sets.newHashSet(new DiagramElementMappingQuery((DiagramElementMapping) pasteTargetDescription).getAllPasteTools());
        } else if (pasteTargetDescription instanceof DiagramDescription) {
            pasteTools = Sets.newHashSet(new DiagramDescriptionQuery((DiagramDescription) pasteTargetDescription).getAllPasteTools());
        } else {
            pasteTools = Sets.newHashSet(pasteTargetDescription.getPasteDescriptions());
        }
        return pasteTools;
    }

    /**
     * Returns the list of the activated drop tools for the <code>mapping</code>. An activated drop tool is:
     * <UL>
     * <LI>in a default layer of any diagram,</LI>
     * <LI>or in an activated layer of the current diagram (contained in or reused by).</LI>
     * </UL>
     * 
     * @param diagram
     *            The current diagram
     * @param pasteTargetDesc
     *            The current paste target description
     * @return the list of the activated paste tools.
     */
    private Collection<PasteDescription> getActivatedPasteTools(final DDiagram diagram, final PasteTargetDescription pasteTargetDesc) {
        Collection<PasteDescription> pasteTools = getAllPasteTools(pasteTargetDesc);
        return pasteTools.stream().filter(desc -> isActive(desc, diagram)).collect(Collectors.toCollection(HashSet<PasteDescription>::new));
    }

    /**
     * Tests whether a {@link PasteDescription} belongs to a default layer or to an activated layer of the current
     * diagram.
     * 
     * @param pasteDesc
     *            the current {@link PasteDescription} of the drag'n'dropped element.
     * @param diagram
     *            the current diagram
     * @return <code>true</code> if the PasteDescription is active, false otherwise.
     */
    private static boolean isActive(final PasteDescription pasteDesc, final DDiagram diagram) {
        boolean result = false;
        Optional<Layer> optionalParentLayer = getLayer(pasteDesc);
        if (optionalParentLayer.isPresent()) {
            if (!(optionalParentLayer.get() instanceof AdditionalLayer)) {
                // This layer is a default layer.
                result = true;
            } else {
                List<Layer> activatedLayers = new DDiagramQuery(diagram).getAllActivatedLayers();
                if (activatedLayers.contains(optionalParentLayer.get()) || activatedLayers.stream().anyMatch(layer -> layer.getReusedTools().contains(pasteDesc))) {
                    // The pasteDesc is in an activated layer of the current diagram or it is reused by an activated
                    // layer of the current diagram.
                    result = true;
                }
            }
        }
        return result;
    }

    private static Optional<Layer> getLayer(PasteDescription containerDropDescription) {
        EObject current = containerDropDescription;
        while (current != null) {
            if (current instanceof Layer) {
                return Optional.of((Layer) current);
            }
            current = current.eContainer();
        }
        return Optional.empty();
    }
}
