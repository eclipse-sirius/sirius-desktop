/*******************************************************************************
 * Copyright (c) 2016, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.query;

import java.text.MessageFormat;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * A class aggregating all the queries (read-only!) having a {@link DRepresentationDescriptor} as a starting point.
 * 
 * @author lfasani
 * 
 */
public class DRepresentationDescriptorQuery {

    private DRepresentationDescriptor repDescriptor;

    /**
     * This adapter holds information if the {@link DRepresentationDescriptor} is valid.
     * 
     * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
     */
    public class DRepresentationDescriptorValidityAdapter extends AdapterImpl implements Adapter {
        private boolean validity;

        /**
         * Default constructor.
         * 
         * @param representationDescriptor
         *            the {@link DRepresentationDescriptor} on which the adapter will be added.
         */
        public DRepresentationDescriptorValidityAdapter(DRepresentationDescriptor representationDescriptor) {
            representationDescriptor.eAdapters().add(this);
            validity = computeRepresentationValid(false);
        }

        /**
         * Triggers a representation validation.
         */
        public void triggerRepresentationValidation() {
            validity = computeRepresentationValid(false);
        }

        /**
         * Triggers a representation validation.
         * 
         * @param forceLoadRepresentation
         *            true will force the loading of the representation EObject for further validation.
         */
        public void triggerRepresentationValidation(boolean forceLoadRepresentation) {
            validity = computeRepresentationValid(forceLoadRepresentation);
        }

        /**
         * Returns if the {@link DRepresentationDescriptor} is valid.</br>
         * 
         * @return the value
         */
        public boolean isValid() {
            return validity;
        }

        @Override
        public void notifyChanged(Notification notification) {
            // The computation of the validity is triggered by the Post-Commit listener DRepresentationChangeListener
        }
    }

    /**
     * Create a new query.
     * 
     * @param repDescriptor
     *            the element to query.
     */
    public DRepresentationDescriptorQuery(DRepresentationDescriptor repDescriptor) {
        this.repDescriptor = repDescriptor;
    }

    /**
     * Check if the current representationDescriptor is a dangling one, ie if its target element is null or if it does
     * not belong to any session.
     * 
     * @return true if the current representation is orphan.
     */
    public boolean isDangling() {
        return repDescriptor.getTarget() == null || SessionManager.INSTANCE.getSession(repDescriptor.getTarget()) == null;
    }

    /**
     * Check if the current representationDescriptor can be found, that is, if its repPath is not null and allows to
     * retrieve the corresponding DRepresentation.
     * 
     * @return true if the representation is reachable
     */
    public boolean isRepresentationReachable() {
        return isRepresentationReachable(false);
    }

    /**
     * Check if the current representationDescriptor can be found, that is, if its repPath is not null and allows to
     * retrieve the corresponding DRepresentation.
     * 
     * @param forceLoadRepresentation
     *            true will force the loading of the representation EObject for further validation.
     * @return true if the representation is reachable
     */
    public boolean isRepresentationReachable(boolean forceLoadResource) {
        boolean isRepresentationReachable = repDescriptor.isLoadedRepresentation();
        if (!isRepresentationReachable && repDescriptor.getRepPath() != null) {
            // if the diagram URI prefix indicates that the representation is stored in an aird, it implies that if
            // the representation exists then it would be yet loaded then we can rely on isLoadedRepresentation.
            String extention = repDescriptor.getRepPath().getResourceURI().fileExtension();
            if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(extention)) {
                // need to call getRepresentation() and not rely only on isLoadedRepresentation() because, if the
                // representation has been moved from a resource to another it may be seen as not loaded yet as it is
                // though reachable.
                return repDescriptor.getRepresentation() != null;
            }
            Resource eResource = repDescriptor.eResource();
            if (eResource != null) {
                ResourceSet resourceSet = eResource.getResourceSet();
                try {
                    if (!forceLoadResource) {
                        isRepresentationReachable = resourceSet.getURIConverter().exists(repDescriptor.getRepPath().getResourceURI(), null);
                    } else if (resourceSet.getURIConverter().exists(repDescriptor.getRepPath().getResourceURI(), null)) {
                        Resource resource = resourceSet.getResource(repDescriptor.getRepPath().getResourceURI().trimFragment(), true);
                        EObject eObject = resource.getEObject(repDescriptor.getRepPath().getResourceURI().fragment());
                        if (eObject == null) {
                            // Representation resource reachable but the fragment is not
                            SiriusPlugin.getDefault().getLog()
                                    .log(new Status(Status.WARNING, SiriusPlugin.ID, MessageFormat.format(Messages.DRepresentationDescriptorQuery_representationError_broken_fragment_path,
                                            repDescriptor.getName(), resource.getURI().path(), repDescriptor.getRepPath().getResourceURI().fragment())));
                        }
                    } else {
                        // Representation resource unreachable
                        SiriusPlugin.getDefault().getLog()
                                .log(new Status(Status.WARNING, SiriusPlugin.ID, MessageFormat.format(Messages.DRepresentationDescriptorQuery_representationError_broken_srm_path,
                                        repDescriptor.getName(), repDescriptor.getRepPath().getResourceURI().trimFragment())));
                    }
                    // CHECKSTYLE:OFF
                } catch (RuntimeException e) {
                    // CHECKSTYLE:ON
                    // we may encounter a CDOException if it fails retrieving a CDORevision for example
                    // in this case the representation must be considered as non reachable
                    SiriusPlugin.getDefault()
                            .warning(MessageFormat.format(Messages.DRepresentationDescriptorQuery_representationError, repDescriptor.getName(), repDescriptor.getRepPath(), repDescriptor.getUid()), e);
                }
            }
        }
        return isRepresentationReachable;
    }

    /**
     * Check if the representation is valid that is, both not {@link isDangling} and {@link isRepresentationReachable}.
     * In case the representation is loaded, it also checks if the representation target is a dangling reference.
     * 
     * @return true if the representation is valid
     */
    public boolean isRepresentationValid() {
        return isRepresentationValid(false);
    }

    /**
     * Check if the representation is valid that is, both not {@link isDangling} and {@link isRepresentationReachable}.
     * In case the representation is loaded, it also checks if the representation target is a dangling reference.
     * 
     * @param forceLoadRepresentation
     *            true will force the loading of the representation EObject for further validation.
     * @return true if the representation is valid
     */
    public boolean isRepresentationValid(boolean forceLoadRepresentation) {
      //@formatter:off
        DRepresentationDescriptorValidityAdapter dRepDescriptorValidityAdapter = (DRepresentationDescriptorValidityAdapter) repDescriptor.eAdapters().stream()
                .filter(DRepresentationDescriptorValidityAdapter.class::isInstance)
                .findFirst()
                .orElseGet(() -> new DRepresentationDescriptorValidityAdapter(repDescriptor));
      //@formatter:on
        if (forceLoadRepresentation) {
            dRepDescriptorValidityAdapter.triggerRepresentationValidation(forceLoadRepresentation);
        }

        return dRepDescriptorValidityAdapter.isValid();
    }

    /**
     * Check if the representation is valid that is, both not {@link isDangling} and {@link isRepresentationReachable}.
     * In case the representation is loaded, it also checks if the representation target is a dangling reference. If the
     * {@link RepresentationDescription} is proxy, the representation is also considered as invalid.
     * 
     * @param forceLoadRepresentation
     *            true will force the loading of the representation EObject for further validation.
     * @return true if the representation is valid
     */
    private boolean computeRepresentationValid(boolean forceLoadRepresentation) {
        try {
            boolean isValid = !isDangling() && isRepresentationReachable(forceLoadRepresentation);
            if (isValid && repDescriptor.isLoadedRepresentation()) {
                isValid = !(new DRepresentationQuery(repDescriptor.getRepresentation()).isDanglingRepresentation());
            }
            isValid = isValid && (repDescriptor.getDescription() == null || !repDescriptor.getDescription().eIsProxy());
            return isValid;
        } catch (IllegalStateException e) {
            if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                return false;
            } else {
                throw e;
            }
        }
    }

}
