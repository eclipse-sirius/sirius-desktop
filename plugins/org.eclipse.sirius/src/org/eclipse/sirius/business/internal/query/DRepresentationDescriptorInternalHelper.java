/*******************************************************************************
 * Copyright (c) 2016, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.business.internal.representation.DRepresentationLocationManager;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionServicesImpl;
import org.eclipse.sirius.model.business.internal.DRepresentationDescriptorAdapter;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class aggregating all the queries (read-only!) having a {@link DRepresentation} as a starting point.
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
     * Initialize it from the current representation. Note that caller will have to attach it to the model.
     * 
     * @param representation
     *            the given representation
     * @param representationName
     *            the name of the representation.
     * @param representationDocumentation
     *            the documentation of the representation.
     * @return a new DRepresentationDescriptor for the given representation DRepresentation
     */
    public static DRepresentationDescriptor createDescriptor(DRepresentation representation, String representationName, String representationDocumentation) {
        DRepresentationDescriptor descriptor = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();

        RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
        descriptor.setRepresentation(representation);

        // Update descriptor.
        descriptor.setDescription(description);
        descriptor.setDocumentation(representationDocumentation);
        descriptor.setName(representationName);
        if (representation instanceof DSemanticDecorator) {
            // avoid the semantic load if not necessary
            descriptor.setTarget((EObject) representation.eGet(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, false));
        }

        return descriptor;
    }

    /**
     * Create the {@link DRepresentationDescriptor} in the {@link DView} and the associated {@link DRepresentation} as
     * root object of the {@link DAnalysis} resource.
     * 
     * @param representation
     *            the {@link DRepresentation} to add
     * @param session
     *            the current session containing the representation.
     * @param semanticResource
     *            the semantic resource.
     * @param representationName
     *            the name of the representation.
     * @param representationDocumentation
     *            the documentation of the representation.
     * @return the new {@link DRepresentationDescriptor}.
     */
    public static DRepresentationDescriptor createDRepresentationDescriptor(DRepresentation representation, DAnalysisSessionImpl session, Resource semanticResource, String representationName,
            String representationDocumentation) {
        DRepresentationDescriptor dRepresentationDescriptor = null;
        if (semanticResource != null) {

            final EObject semanticRoot = semanticResource.getContents().iterator().next();
            RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);

            // Add an adapter in the representation referencing the descriptor
            dRepresentationDescriptor = DRepresentationDescriptorInternalHelper.createDescriptor(representation, representationName, representationDocumentation);
            DRepresentationDescriptorAdapter representationDescriptorAdaptor = new DRepresentationDescriptorAdapter(dRepresentationDescriptor);
            representation.eAdapters().add(representationDescriptorAdaptor);

            DAnalysisSelector analysisSelector = ((DAnalysisSessionServicesImpl) session.getServices()).getAnalysisSelector();

            final Viewpoint viewpoint = new RepresentationDescriptionQuery(description).getParentViewpoint();
            DView dView = DAnalysisSessionHelper.findContainerForAddedRepresentation(semanticRoot, viewpoint, session.allAnalyses(), analysisSelector, representation);

            if (dView == null) {
                dView = DAnalysisSessionHelper.findFreeContainerForAddedRepresentation(viewpoint, semanticRoot, session.getAnalyses(), analysisSelector, representation);
                if (dView != null) {
                    dView.setViewpoint(viewpoint);
                }
            }
            if (dView == null) {
                dView = ViewpointFactory.eINSTANCE.createDView();
                dView.setViewpoint(viewpoint);
                final DAnalysis analysis = DAnalysisSessionHelper.selectAnalysis(viewpoint, session.allAnalyses(), analysisSelector, representation);
                analysis.getOwnedViews().add(dView);
            }
            DRepresentationLocationManager representationLocationManager = ((DAnalysisSessionServicesImpl) session.getServices()).getRepresentationLocationManager();
            Resource resourceforRepresentation = representationLocationManager.getOrCreateRepresentationResource(representation, dView.eResource());
            if (resourceforRepresentation != null) {
                session.registerResourceInCrossReferencer(resourceforRepresentation);
                resourceforRepresentation.getContents().add(representation);
                // Now that the representation is in a resource, the DRepresentationDescriptor can compute its path and
                // reference it.
                dRepresentationDescriptor.setRepresentation(representation);
            }

            dView.getOwnedRepresentationDescriptors().add(dRepresentationDescriptor);

        }
        return dRepresentationDescriptor;
    }
}
