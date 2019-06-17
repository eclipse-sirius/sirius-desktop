/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.migration;

import java.util.UUID;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * <p>
 * Clients wishing to participate in the migration process of a representations file must subclass this class, and
 * implement {@link #getAttribute(org.eclipse.emf.ecore.EClass, String, String)},
 * {@link #getLocalElement(org.eclipse.emf.ecore.EClass, String, String)},
 * {@link #getType(org.eclipse.emf.ecore.EPackage, String, String)}, etc.
 * </p>
 * <p>
 * This class is intended to be subclassed by clients.
 * </p>
 * 
 * @author lredor
 */
public abstract class AbstractRepresentationsFileMigrationParticipant extends AbstractMigrationParticipant {
    /**
     * {@inheritDoc}
     */
    @Override
    public void postLoad(XMLResource resource, String loadedVersion) {
        super.postLoad(resource, loadedVersion);
        // The migration often uses dAnalysis and loaded version at osgi form
        if (resource instanceof AirdResource) {
            AirDResouceQuery query = new AirDResouceQuery((AirdResource) resource);
            Option<DAnalysis> optionalAnalysis = query.getDAnalysis();
            if (optionalAnalysis.some()) {
                DAnalysis dAnalysis = optionalAnalysis.get();
                postLoad(dAnalysis, Version.parseVersion(loadedVersion));
            }
        }
    }

    /**
     * Returns the {@link DRepresentationDescriptor} pointing at given {@link DRepresentation} and contained by given
     * {@link DAnalysis}.
     * 
     * @param dAnalysis
     *            the analysis containing the representation.
     * @param representation
     *            the {@link DRepresentation} from which we want to retrieve the associated
     *            {@link DRepresentationDescriptor}.
     * @return the {@link DRepresentationDescriptor} pointing at the given {@link DRepresentation} and contained by the
     *         given {@link DAnalysis}.Null if no descriptor pointing at the given representation exists.
     */
    private DRepresentationDescriptor getRepresentationDescriptor(DAnalysis dAnalysis, DRepresentation representation) {
        EList<DView> ownedViews = dAnalysis.getOwnedViews();
        for (DView dView : ownedViews) {
            EList<DRepresentationDescriptor> ownedRepresentationDescriptors = dView.getOwnedRepresentationDescriptors();
            for (DRepresentationDescriptor representationDescriptor : ownedRepresentationDescriptors) {
                if (representation.equals(representationDescriptor.getRepresentation())) {
                    return representationDescriptor;
                }
            }
        }
        return null;
    }

    /**
     * Update the change id of the {@link DRepresentationDescriptor} associated to the given {@link DRepresentation}.
     * 
     * @param dAnalysis
     *            the analysis containing the representation.
     * @param representation
     *            the {@link DRepresentation} from which we want to update change id of associated
     *            {@link DRepresentationDescriptor}.
     */
    protected void updateChangeId(DAnalysis dAnalysis, DRepresentation representation) {
        DRepresentationDescriptor representationDescriptor = getRepresentationDescriptor(dAnalysis, representation);
        if (representationDescriptor != null) {
            representationDescriptor.setChangeId(UUID.randomUUID().toString());
        }
    }

    /**
     * The post load step often uses {@link DAnalysis} and loaded version at osgi form so this method is called by the
     * {@link #postLoad(XMLResource, String)} method, to avoid boring job of getting dAnalysis and parsing version.
     * 
     * @param dAnalysis
     *            The analysis of the resource to migrate
     * @param loadedVersion
     *            the osgi form of the loaded version
     */
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        // Nothing to migrate by default.
    }
}
