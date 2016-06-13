/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DRepresentation} as a starting point.
 * 
 * @author mporhel
 * 
 */
public final class DRepresentationDescriptorInternalHelper {

    /**
     * Prevent instantiation.
     */
    private DRepresentationDescriptorInternalHelper() {
    }

    /**
     * Create a new representation descriptor.
     * 
     * Initialize it from the current representation. Note that caller will have
     * to attach it to the model.
     * 
     * @param representation
     *            the given representation
     * @return a new DRepresentationDescriptor for the given representation
     *         DRepresentation
     */
    public static DRepresentationDescriptor createDescriptor(DRepresentation representation) {
        DRepresentationDescriptor descriptor = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();

        RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
        descriptor.setRepresentation(representation);

        // Update descriptor.
        descriptor.setDescription(description);
        descriptor.setName(representation.getName());
        if (representation instanceof DSemanticDecorator) {
            // avoid the semantic load if not necessary
            descriptor.setTarget((EObject) representation.eGet(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, false));
        }

        return descriptor;
    }
}
