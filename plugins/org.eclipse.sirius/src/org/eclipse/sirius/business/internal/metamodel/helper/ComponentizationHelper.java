/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.helper;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.SiriusQuery;
import org.eclipse.sirius.tools.internal.uri.SiriusProtocolParser;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DiagramExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Layer;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This class helps to use the Imported Diagram and Diagram Extension on Sirius
 * .
 * 
 * @author amartin
 */
public final class ComponentizationHelper {

    private ComponentizationHelper() {
    }

    /**
     * Give the set of layers from a DiagramDescription. useful for Diagram
     * Extension.
     * 
     * @param diagramDescription
     *            the Diagram description
     * @param viewpoints
     *            the available viewpoints
     * @return the layer set of a given DiagramDescription
     */
    public static Set<Layer> getContributedLayers(final DiagramDescription diagramDescription, final Collection<Viewpoint> viewpoints) {

        /*
         * We browse all the representationExtension of viewpoints registered
         * through the Sirius Registry in order to retrieve all the Layers
         * concerning the diagramDescription given in parameter.
         * 
         * To know how a representationExtension contribute to the given
         * diagramDescription, it should contains : -the URI of the
         * diagramDescription -the name of the diagramDescription
         */
        final Set<Layer> result = new LinkedHashSet<Layer>();
        final List<RepresentationExtensionDescription> foundDiagramContributors = new BasicEList<RepresentationExtensionDescription>();
        final List<RepresentationExtensionDescription> newDiagramContributors = new BasicEList<RepresentationExtensionDescription>();
        // result.addAll(diagramDescription.getOptionalLayers());
        RepresentationExtensionDescription previouscontributorDiagramRepresentation;

        Iterator<RepresentationExtensionDescription> it;

        /*
         * We browse a first time, all the DIagramRepresentation in all Siriuss
         * to find contribution to the "diagramDescription"
         */
        for (final Viewpoint viewpoint : viewpoints) {
            for (final RepresentationExtensionDescription representationExtension : viewpoint.getOwnedRepresentationExtensions()) {
                if (representationExtension instanceof DiagramExtensionDescription) {
                    final DiagramExtensionDescription diagramExtension = (DiagramExtensionDescription) representationExtension;
                    if (ComponentizationHelper.match(diagramDescription, diagramExtension)) {
                        result.addAll(diagramExtension.getLayers());
                        foundDiagramContributors.add(diagramExtension);
                    }
                }
            }
        }

        /*
         * We browse all the DIagramRepresentation in all Siriuss to find
         * contribution to the DiagramRepresentation which already contribute.
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
                            if (previouscontributorDiagramRepresentation != diagramExtension && ComponentizationHelper.match(previouscontributorDiagramRepresentation, diagramExtension)) {
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

        return result;
    }

    /**
     * Get the diagram description associated with the diagram extension
     * description.
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
            for (Iterator<RepresentationDescription> iterator2 = new SiriusQuery(viewpoint).getAllRepresentationDescriptions().iterator(); iterator2.hasNext() && diagramDescriptionFound == null; /* */) {
                RepresentationDescription representation = iterator2.next();
                if (representation instanceof DiagramDescription) {
                    if (ComponentizationHelper.match(representation, diagramExtensionDescription)) {
                        diagramDescriptionFound = (DiagramDescription) representation;
                    }
                }
            }
            if (diagramDescriptionFound == null) {
                for (final RepresentationExtensionDescription representation : new SiriusQuery(viewpoint).getAllRepresentationExtensionDescriptions()) {
                    if (representation instanceof DiagramExtensionDescription) {
                        final DiagramExtensionDescription diagramExtensionDescription2 = (DiagramExtensionDescription) representation;
                        if (ComponentizationHelper.match(diagramExtensionDescription2, diagramExtensionDescription)) {
                            diagramDescriptionFound = ComponentizationHelper.getDiagramDescription(diagramExtensionDescription2, viewpoints);

                        }
                    }
                }
            }
        }
        return diagramDescriptionFound;
    }

    /**
     * Tests whether a representation extension applies to an existing
     * representation.
     * 
     * @param extension
     *            the extension
     * @param representation
     *            the existing representation
     * @return <code>true</code> if the extension applies to the specified
     *         representation.
     */
    public static boolean extensionAppliesTo(final RepresentationExtensionDescription extension, final DRepresentation representation) {
        return ComponentizationHelper.match(DialectManager.INSTANCE.getDescription(representation), extension);
    }

    private static boolean match(final RepresentationExtensionDescription representationExtensionDescription, final RepresentationExtensionDescription representationExtensionDescription2) {
        return ComponentizationHelper.match(representationExtensionDescription, representationExtensionDescription.getName(), representationExtensionDescription2);
    }

    private static boolean match(final RepresentationDescription representationDescription, final RepresentationExtensionDescription representationExtensionDescription) {
        return ComponentizationHelper.match(representationDescription, representationDescription.getName(), representationExtensionDescription);
    }

    private static boolean match(final EObject desc, final String descName, final RepresentationExtensionDescription representationExtensionDescription) {
        /*
         * desc.eContainer might be null if desc is a proxy and we can't resolve
         * it.
         */
        final EObject container = desc.eContainer();
        if (container != null) {
            String representationExtensionSiriusURI = representationExtensionDescription.getViewpointURI();
            if (URI.decode(EcoreUtil.getURI(container).toString()).equals(representationExtensionSiriusURI)
                    || SiriusProtocolParser.match(EcoreUtil.getURI(container), representationExtensionSiriusURI)) {
                if (descName.matches(representationExtensionDescription.getRepresentationName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if one of the representation descriptions of the given baseSirius
     * is extended by at least one representation extension descriptions of the
     * given extensionSirius.
     * 
     * @param extensionSirius
     *            the extension Sirius that may extends the given base viewpoint
     * @param baseSirius
     *            the base Sirius which may be extended by the given extension
     *            Sirius
     * @return true if the extensionSirius extends the baseSirius
     */
    public static boolean isExtendedBy(final Viewpoint extensionSirius, final Viewpoint baseSirius) {
        boolean result = false;
        for (RepresentationExtensionDescription representationExtensionDescription : extensionSirius.getOwnedRepresentationExtensions()) {
            for (RepresentationDescription representationDescription : new SiriusQuery(baseSirius).getAllRepresentationDescriptions()) {
                result = result || ComponentizationHelper.match(representationDescription, representationExtensionDescription);
            }
        }
        return result;
    }
}
