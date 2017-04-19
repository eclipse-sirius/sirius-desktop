/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.representation;

import java.util.Optional;

import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * This interface allows to extend the way the DRepresentationDescriptor#repPath URI fragment is set and interpreted.
 * This interface is not intended to be implemented by clients.
 * 
 * @author fbarbin
 *
 */
public interface DRepresentationURIFragmentStrategy {

    /**
     * The extension point ID.
     */
    String ID = "org.eclipse.sirius.dRepresentationURIFragmentStrategy"; //$NON-NLS-1$

    /**
     * The class attribute.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Whether this strategy can by applied on this kind of descriptor before calling
     * {@link DRepresentationURIFragmentStrategy#getRepresentation(DRepresentationDescriptor, boolean)}.
     * 
     * @param descriptor
     *            the current descriptor. The descriptor is contained in a {@link Resource}.
     * @return true if this strategy can be applied, false otherwise.
     */
    boolean providesGetter(DRepresentationDescriptor descriptor);

    /**
     * Whether this strategy can by applied on this kind of representation before calling
     * {@link DRepresentationURIFragmentStrategy#setRepresentation(DRepresentationDescriptor, DRepresentation)}.
     * 
     * @param representation
     *            the current representation. The representation must be contained in a {@link Resource}.
     * @return true if this strategy can be applied, false otherwise.
     */
    boolean providesSetter(DRepresentation representation);

    /**
     * Provides the DRepresentation referenced by the {@link DRepresentationDescriptor#getRepPath()} feature. This
     * method is called in the context of {@link DRepresentationDescriptor#getRepresentation()} method.
     * 
     * @param descriptor
     *            the {@link DRepresentationDescriptor} that references the {@link DRepresentation} to return. The
     *            descriptor must be contained in a {@link Resource}.
     * @param loadOnDemand
     *            whether to create and load the resource, if it doesn't already exists.
     * @return an optional DRepresentation.
     */
    Optional<DRepresentation> getRepresentation(DRepresentationDescriptor descriptor, boolean loadOnDemand);

    /**
     * Set the provided {@link DRepresentation} to the given {@link DRepresentationDescriptor}. This method is called in
     * the context of {@link DRepresentationDescriptor#setRepresentation(DRepresentation)} method. The implementation
     * must set the repPath value by calling
     * {@link DRepresentationDescriptor#setRepPath(org.eclipse.sirius.business.api.resource.ResourceDescriptor)} and may
     * also perform model changes so that the representation can be retrieved.
     * 
     * @param descriptor
     *            the {@link DRepresentationDescriptor} for which we want to set the repPath.
     * @param representation
     *            the {@link DRepresentation} argument from
     *            {@link DRepresentationDescriptor#setRepresentation(DRepresentation)}. It must be contained in a
     *            {@link Resource}.
     */
    void setRepresentation(DRepresentationDescriptor descriptor, DRepresentation representation);

}
