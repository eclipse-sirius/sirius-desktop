/*******************************************************************************
 * Copyright (c) 2008, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tools.internal.uri.ViewpointProtocolParser;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * This class helps to use the Imported Diagram and Diagram Extension on Sirius .
 * 
 * @author amartin
 */
public final class DiagramComponentizationHelper {

    private DiagramComponentizationHelper() {
    }

    /**
     * Give the set of layers from a DiagramDescription. useful for Diagram Extension.
     * 
     * @param diagramDescription
     *            the Diagram description
     * @param viewpoints
     *            the available viewpoints
     * @return the layer set of a given DiagramDescription
     */
    public static Set<Layer> getContributedLayers(final DiagramDescription diagramDescription, final Collection<Viewpoint> viewpoints) {

        /*
         * We browse all the representationExtension of viewpoints registered through the Sirius Registry in order to
         * retrieve all the Layers concerning the diagramDescription given in parameter. To know how a
         * representationExtension contribute to the given diagramDescription, it should contains : -the URI of the
         * diagramDescription -the name of the diagramDescription
         */
        final Set<Layer> result = new LinkedHashSet<Layer>();
        processGetContributedLayers(diagramDescription, viewpoints, result, false);

        return Sets.newLinkedHashSet(Iterables.filter(result, Layer.class));
    }

    /**
     * Give the set of layers from a DiagramDescription. useful for Diagram Extension.
     * 
     * @param diagramDescription
     *            the Diagram description
     * @param viewpoints
     *            the available viewpoints
     * @return the layer set of a given DiagramDescription
     */
    private static void processGetContributedLayers(final DiagramDescription diagramDescription, final Collection<Viewpoint> viewpoints, Set<Layer> result, boolean addTransientLayers) {

        /*
         * We browse all the representationExtension of viewpoints registered through the Sirius Registry in order to
         * retrieve all the Layers concerning the diagramDescription given in parameter. To know how a
         * representationExtension contribute to the given diagramDescription, it should contains : -the URI of the
         * diagramDescription -the name of the diagramDescription
         */
        final List<RepresentationExtensionDescription> foundDiagramContributors = new BasicEList<RepresentationExtensionDescription>();
        final List<RepresentationExtensionDescription> newDiagramContributors = new BasicEList<RepresentationExtensionDescription>();
        // result.addAll(diagramDescription.getOptionalLayers());
        RepresentationExtensionDescription previouscontributorDiagramRepresentation;

        Iterator<RepresentationExtensionDescription> it;

        /*
         * We browse a first time, all the DiagramRepresentation in all Viewpoints to find contribution to the
         * "diagramDescription"
         */
        for (final Viewpoint viewpoint : viewpoints) {
            for (final RepresentationExtensionDescription representationExtension : viewpoint.getOwnedRepresentationExtensions()) {
                if (representationExtension instanceof DiagramExtensionDescription) {
                    final DiagramExtensionDescription diagramExtension = (DiagramExtensionDescription) representationExtension;
                    if (DiagramComponentizationHelper.match(diagramDescription, diagramExtension)) {
                        result.addAll(diagramExtension.getLayers());
                        foundDiagramContributors.add(diagramExtension);
                    }
                }
            }
        }

        /*
         * We browse all the DiagramRepresentation in all Viewpoints to find contribution to the DiagramRepresentation
         * which already contribute.
         */
        while (!foundDiagramContributors.isEmpty()) {
            for (final Viewpoint viewpoint : viewpoints) {
                for (final RepresentationExtensionDescription representationExtension : viewpoint.getOwnedRepresentationExtensions()) {
                    if (representationExtension instanceof DiagramExtensionDescription) {
                        final DiagramExtensionDescription diagramExtension = (DiagramExtensionDescription) representationExtension;

                        it = foundDiagramContributors.iterator();
                        while (it.hasNext()) {
                            previouscontributorDiagramRepresentation = it.next();
                            // to avoid loop we check that instances are
                            // different
                            if (previouscontributorDiagramRepresentation != diagramExtension && DiagramComponentizationHelper.match(previouscontributorDiagramRepresentation, diagramExtension)) {
                                result.addAll(diagramExtension.getLayers());
                                newDiagramContributors.add(representationExtension);
                                break;
                            }
                        }
                    }
                }
            }
            foundDiagramContributors.clear();
            foundDiagramContributors.addAll(newDiagramContributors);
            newDiagramContributors.clear();
        }
    }

    /**
     * Get the diagram description associated with the diagram extension description.
     * 
     * @param diagramExtensionDescription
     *            the diagram extension description
     * @param viewpoints
     *            the available viewpoints
     * @return the diagram extension description
     */
    public static DiagramDescription getDiagramDescription(final DiagramExtensionDescription diagramExtensionDescription, final Collection<Viewpoint> viewpoints) {

        DiagramDescription diagramDescriptionFound = null;
        for (Iterator<Viewpoint> iterator = viewpoints.iterator(); iterator.hasNext() && diagramDescriptionFound == null; /* */) {
            Viewpoint viewpoint = iterator.next();
            for (Iterator<RepresentationDescription> iterator2 = new ViewpointQuery(viewpoint).getAllRepresentationDescriptions().iterator(); iterator2.hasNext()
                    && diagramDescriptionFound == null; /* */) {
                RepresentationDescription representation = iterator2.next();
                if (representation instanceof DiagramDescription) {
                    if (DiagramComponentizationHelper.match(representation, diagramExtensionDescription)) {
                        diagramDescriptionFound = (DiagramDescription) representation;
                    }
                }
            }
            if (diagramDescriptionFound == null) {
                for (final RepresentationExtensionDescription representation : new ViewpointQuery(viewpoint).getAllRepresentationExtensionDescriptions()) {
                    if (representation instanceof DiagramExtensionDescription) {
                        final DiagramExtensionDescription diagramExtensionDescription2 = (DiagramExtensionDescription) representation;
                        if (DiagramComponentizationHelper.match(diagramExtensionDescription2, diagramExtensionDescription)) {
                            diagramDescriptionFound = DiagramComponentizationHelper.getDiagramDescription(diagramExtensionDescription2, viewpoints);

                        }
                    }
                }
            }
        }
        return diagramDescriptionFound;
    }

    private static boolean match(final RepresentationExtensionDescription representationExtensionDescription, final RepresentationExtensionDescription representationExtensionDescription2) {
        return DiagramComponentizationHelper.match(representationExtensionDescription, representationExtensionDescription.getName(), representationExtensionDescription2);
    }

    private static boolean match(final RepresentationDescription representationDescription, final RepresentationExtensionDescription representationExtensionDescription) {
        return DiagramComponentizationHelper.match(representationDescription, representationDescription.getName(), representationExtensionDescription);
    }

    private static boolean match(final EObject desc, final String descName, final RepresentationExtensionDescription representationExtensionDescription) {
        /*
         * desc.eContainer might be null if desc is a proxy and we can't resolve it.
         */
        final EObject container = desc.eContainer();
        if (container != null) {
            String representationExtensionSiriusURI = representationExtensionDescription.getViewpointURI();
            if (URI.decode(EcoreUtil.getURI(container).toString()).equals(representationExtensionSiriusURI)
                    || ViewpointProtocolParser.match(EcoreUtil.getURI(container), representationExtensionSiriusURI)) {
                if (descName.matches(representationExtensionDescription.getRepresentationName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
