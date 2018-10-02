/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others,
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
package org.eclipse.sirius.business.api.session.danalysis;

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
     * Indicates if this {@link DRepresentationLocationRule} provides a custom URI. If not and if there is no other
     * {@link DRepresentationLocationRule} that provides, Sirius fall back to the default implementation.
     * 
     * @param representation
     *            the representation
     * @param dViewResource
     *            the DView resource
     * @return the value
     */
    boolean providesURI(DRepresentation representation, Resource dViewResource);

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

    /**
     * Indicates if the given file extension is considered as a representation file.
     * 
     * @param fileExtension
     *            the file extension
     * 
     * @return true if the fileExtension is known as a representation file
     */
    Boolean isARepresentationFileExtension(String fileExtension);
}
