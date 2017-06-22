/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.representation;

import java.text.MessageFormat;
import java.util.Optional;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * This class is intended to manage the link between the {@link DRepresentationDescriptor} and its
 * {@link DRepresentation} through the {@link DRepresentationDescriptor#repPath} attribute.
 * 
 * @author fbarbin
 *
 */
public class DRepresentationDescriptorToDRepresentationLinkManager {
    private DRepresentationDescriptor repDescriptor;

    /**
     * Default constructor.
     * 
     * @param repDescriptor
     *            the {@link DRepresentationDescriptor}.
     */
    public DRepresentationDescriptorToDRepresentationLinkManager(DRepresentationDescriptor repDescriptor) {
        this.repDescriptor = repDescriptor;
    }

    /**
     * Set the repPath attribute according to the given newRepresentation. This method should not be called by client.
     * Call {@link DRepresentationDescriptor#setRepresentation(DRepresentation)} instead.
     * 
     * @param newRepresentation
     *            the new representation to set. Can be null. If the newRepresentation is not null,
     *            newRepresentation.eResource must not be null.
     */
    public void setRepresentation(DRepresentation newRepresentation) {
        if (newRepresentation != null) {
            Optional.ofNullable(newRepresentation).ifPresent(rep -> Assert.isNotNull(rep.eResource()));
            Optional<DRepresentationURIFragmentStrategy> fragmentStrategy = EclipseUtil
                    .getExtensionPlugins(DRepresentationURIFragmentStrategy.class, DRepresentationURIFragmentStrategy.ID, DRepresentationURIFragmentStrategy.CLASS_ATTRIBUTE).stream()
                    .filter(strategy -> strategy.providesSetter(newRepresentation)).findFirst();
            if (fragmentStrategy.isPresent()) {
                fragmentStrategy.get().setRepresentation(repDescriptor, newRepresentation);
            } else {
                setRepresentationPathFromURI(newRepresentation);
            }
        } else {
            repDescriptor.setRepPath(null);
        }
    }

    private void setRepresentationPathFromURI(DRepresentation newRepresentation) {
        URI uri = EcoreUtil.getURI(newRepresentation);
        if (uri != null) {
            repDescriptor.setRepPath(new ResourceDescriptor(uri));
        }
    }

    /**
     * Retrieves the DRepresentation using the repPath attribute. This method should not be called by client. Call
     * {@link DRepresentationDescriptor#getRepresentation()} instead.
     * 
     * @param loadOnDemand
     *            whether to create and load the resource, if it doesn't already exists.
     * @return an Optional DRepresentation.
     */
    public Optional<DRepresentation> getRepresentation(boolean loadOnDemand) {
        Optional<DRepresentation> representation = getRepresentationInternal(false);
        if (loadOnDemand && !representation.isPresent()) {
            representation = getRepresentationInternal(true);

            representation.ifPresent(rep -> Optional.ofNullable(new EObjectQuery(repDescriptor).getSession()).map(Session::getSemanticCrossReferencer).ifPresent(crossRef -> {
                crossRef.setTarget(repDescriptor);
                rep.eAdapters().add(crossRef);
            }));
        }

        return representation;
    }

    private Optional<DRepresentation> getRepresentationInternal(boolean loadOnDemand) {
        Optional<DRepresentationURIFragmentStrategy> fragmentStrategy = EclipseUtil
                .getExtensionPlugins(DRepresentationURIFragmentStrategy.class, DRepresentationURIFragmentStrategy.ID, DRepresentationURIFragmentStrategy.CLASS_ATTRIBUTE).stream()
                .filter(strategy -> strategy.providesGetter(repDescriptor)).findFirst();
        if (fragmentStrategy.isPresent()) {
            return fragmentStrategy.get().getRepresentation(repDescriptor, loadOnDemand);
        }
        return Optional.ofNullable(getRepresentationFromURI(loadOnDemand));
    }

    private DRepresentation getRepresentationFromURI(boolean loadOnDemand) {
        ResourceDescriptor resourceDescriptor = repDescriptor.getRepPath();
        Resource resource = repDescriptor.eResource();
        if (resourceDescriptor != null) {
            try {
                // @formatter:off
                return Optional.ofNullable(resource).map(Resource::getResourceSet)
                        .map(rSet -> rSet.getEObject(resourceDescriptor.getResourceURI(), loadOnDemand))
                        .filter(DRepresentation.class::isInstance)
                        .map(DRepresentation.class::cast)
                        .orElse(null);
                // @formatter:on
             // CHECKSTYLE:OFF
            } catch (Exception e) {
             // CHECKSTYLE:ON
                SiriusPlugin.getDefault().error(MessageFormat.format(Messages.DRepresentationDescriptorToDRepresentationLinkManager_repLoading, resourceDescriptor.getResourceURI()), e);
            }
        }
        return null;
    }
}
