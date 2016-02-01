/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others,
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * An interface used by the DRepresentationLocationManager extension point to customize the location of a new
 * Representation.
 * 
 * @author fbarbin
 *
 */
public interface DRepresentationLocationRule {

    /**
     * The extension point id.
     */
    String ID = "org.eclipse.sirius.dRepresentationLocationRule"; //$NON-NLS-1$

    /**
     * The class attribute.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Indicates if this {@link DRepresentationLocationRule} provides a custom URI. If not and if there is no other
     * {@link DRepresentationLocationRule} that provides, Sirius fall back to the default implementation.
     * 
     * @param representation
     *            the representation
     * @param dViewResource
     *            the DView resource
     * @return the value
     */
    boolean provides(DRepresentation representation, Resource dViewResource);

    /**
     * Provides the new URI for the given representation.
     * 
     * @param representation
     *            the representation we need to attach to a new resource.
     * @param dViewResource
     *            the dView resource in which the RepresentationDescriptor is referenced.
     * @return the new {@link URI}
     */
    URI getResourceURI(DRepresentation representation, Resource dViewResource);
}
