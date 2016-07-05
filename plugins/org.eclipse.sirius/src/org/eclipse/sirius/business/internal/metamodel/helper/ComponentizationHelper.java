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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.tools.internal.uri.ViewpointProtocolParser;
import org.eclipse.sirius.viewpoint.DRepresentation;
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

    private static boolean match(final RepresentationDescription representationDescription, final RepresentationExtensionDescription representationExtensionDescription) {
        if (representationDescription == null) {
            return false;
        }
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
                    || ViewpointProtocolParser.match(EcoreUtil.getURI(container), representationExtensionSiriusURI)) {
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
            for (RepresentationDescription representationDescription : new ViewpointQuery(baseSirius).getAllRepresentationDescriptions()) {
                result = result || ComponentizationHelper.match(representationDescription, representationExtensionDescription);
            }
        }
        return result;
    }
}
