/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.migration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.query.DRepresentationDescriptorInternalHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;

/**
 * This class handle multiple migrations:</br>
 * * Migration to handle the move of {@link DRepresentation} from DView.ownedRepresentations to the root objects of the
 * aird resource.</br>
 * * This migration participant is about setting the repPath feature instead of representation. The
 * DRepresentationDescriptor#representation feature is now derived, transient and volatile. It has been replaced by the
 * repPath feature that contains the URI as a String. </br>
 * * Migration to have an uid on DRpresentation which is used to reference DRpresentation from the
 * DRpresentationDescriptor using the part of DRpresentationDescriptor.repPath. Also moves the name attribute from
 * {@link DRepresentation} to {@link DRepresentationDescriptor}.
 * 
 * @author lfasani
 */
public class DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The version corresponds to the move of DRep owned by DRepDesc to root objects.
     */
    public static final Version MIGRATION_VERSION_REP_IN_DVIEW_TO_ROOT_OBJECT = new Version("11.1.0.201608251200"); //$NON-NLS-1$

    /**
     * This version corresponds to repPath migration. The repPath is initialized from the old DRepDesc.representation
     */
    public static final Version MIGRATION_VERSION_REP_PATH_XMIID = new Version("12.0.0.201704191300"); //$NON-NLS-1$

    /**
     * This version corresponds to repPath migration. The repPath fragment is changed from rep xmiid to rep uid.
     */
    public static final Version MIGRATION_VERSION_REP_PATH_UID = new Version("12.1.0.201707281200"); //$NON-NLS-1$

    /**
     * The name of the feature DView.ownedRepresentations which has been deleted.
     */
    public static final String DVIEW_OWNED_REPRESENTATIONS_UNKNOWN_FEATURE = "ownedRepresentations"; //$NON-NLS-1$

    /**
     * The label of the feature name of a DRepresentation when serialized.
     */
    protected static final String FEATURE_NAME_LABEL = "name"; //$NON-NLS-1$

    /**
     * A map associating {@link DRepresentation} to their name.
     */
    protected Map<DRepresentation, String> representationToNameMap;

    private Map<DRepresentation, DRepresentationDescriptor> representation2RepresentationDescriptorMap;

    /**
     * Init maps.
     */
    public DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant() {
        super();
        representation2RepresentationDescriptorMap = new HashMap<>();
        representationToNameMap = new HashMap<>();
    }

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION_REP_PATH_UID;
    }

    @Override
    protected void handleFeature(EObject owner, EStructuralFeature unkownFeature, Object valueOfUnknownFeature) {
        if (DVIEW_OWNED_REPRESENTATIONS_UNKNOWN_FEATURE.equals(unkownFeature.getName())) {
            if (valueOfUnknownFeature instanceof DRepresentation && owner instanceof DView) {
                DRepresentation representation = (DRepresentation) valueOfUnknownFeature;
                // First, move the DRepresentation as root object of the resource so that its URI can be computed
                // correctly
                owner.eResource().getContents().add(representation);

                // Create DRepresentationDescriptor
                // The method will call DRepresentationDescriptor.setRepresentation that will, in fact, get the
                // DRepresentation URI and set it on DRepresentationDescriptor.repPath
                String representationName = representationToNameMap.get(representation);
                DRepresentationDescriptor newDescriptor = DRepresentationDescriptorInternalHelper.createDescriptor(representation, representationName);
                ((DView) owner).getOwnedRepresentationDescriptors().add(newDescriptor);
                if (representationName == null) {
                    // we save the representation and its descriptor to access it later when we will have the
                    // representation name.
                    representation2RepresentationDescriptorMap.put(representation, newDescriptor);
                }
            }
        } else if (owner instanceof DRepresentation && FEATURE_NAME_LABEL.equals(unkownFeature.getName())) {
            DRepresentationDescriptor dRepresentationDescriptor = representation2RepresentationDescriptorMap.get(owner);
            // A representation descriptor has been created before we had the name of the representation so we update
            // it.
            if (dRepresentationDescriptor != null) {
                dRepresentationDescriptor.setName((String) valueOfUnknownFeature);
            } else {
                representationToNameMap.put((DRepresentation) owner, (String) valueOfUnknownFeature);
            }

        }
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION_REP_IN_DVIEW_TO_ROOT_OBJECT) >= 0 && Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION_REP_PATH_XMIID) < 0) {
            if (ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation().equals(feature)) {
                // Thanks to the getAffiliation overload, the return value will
                // be used to create a ResourceDescriptor.
                // The representation uri has already been resolved and is absolute.
                if (value instanceof DRepresentation) {
                    DRepresentation rep = (DRepresentation) value;

                    // create the rep URI (with rep uid as fragment) which will be set as DRepDesc.repPath
                    // DRep.uid is initialized in constructor but DRepDesc.repPath has never been initialized with that
                    // uid
                    URI repURI = EcoreUtil.getURI(rep);
                    URI newRepURI = URI.createHierarchicalURI(repURI.scheme(), repURI.authority(), repURI.device(), repURI.segments(), repURI.query(), rep.getUid());

                    return newRepURI.toString();
                }
            }
        }
        return super.getValue(object, feature, value, loadedVersion);
    }

    @Override
    public EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION_REP_IN_DVIEW_TO_ROOT_OBJECT) >= 0 && Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION_REP_PATH_XMIID) < 0) {
            if (ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation().equals(eStructuralFeature)) {
                return ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_RepPath();
            }
        }
        return super.getAffiliation(eClass, eStructuralFeature, loadedVersion);
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION_REP_PATH_XMIID) >= 0 && loadedVersion.compareTo(MIGRATION_VERSION_REP_PATH_UID) < 0) {
            dAnalysis.getOwnedViews().stream().flatMap(view -> view.getOwnedRepresentationDescriptors().stream()).forEach(repDesc -> {
                Optional.ofNullable(getRepresentationFromURI(repDesc)).ifPresent(rep -> {
                    // DRep.uid is initialized in constructor but DRepDesc.repPath has never been initialized with that
                    // uid
                    repDesc.updateRepresentation(rep);
                });
            });
            super.postLoad(dAnalysis, loadedVersion);
        }
    }

    /**
     * Try to get the DRepresentation supposing that DRepresentationDescriptor.repPath is a real URI, having the DRep
     * xmiid as fragment which should be the case when this participant is executed.</br>
     * If
     * 
     * @param repDescriptor
     * @return the representation
     */
    private DRepresentation getRepresentationFromURI(DRepresentationDescriptor repDescriptor) {
        ResourceDescriptor resourceDescriptor = repDescriptor.getRepPath();
        Resource resource = repDescriptor.eResource();
        if (resourceDescriptor != null) {
            try {
                return Optional.ofNullable(resource).map(Resource::getResourceSet).map(rSet -> rSet.getEObject(resourceDescriptor.getResourceURI(), true)).filter(DRepresentation.class::isInstance)
                        .map(DRepresentation.class::cast).orElse(null);
            } catch (WrappedException e) {
            }
        }
        return null;
    }
}
