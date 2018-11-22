/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A {@link Adapter} to update the collection of semantic resources in a Session.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SemanticResourcesUpdater extends AdapterImpl implements Adapter {
    // @formatter:off
    private static final Collection<EStructuralFeature> FEATURES_OF_INTEREST = Arrays.asList(
            ViewpointPackage.Literals.DANALYSIS__REFERENCED_ANALYSIS,
            ViewpointPackage.Literals.DANALYSIS__SEMANTIC_RESOURCES,
            ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES,
            ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES);
    // @formatter:on

    private DAnalysisSessionImpl session;

    private Collection<Resource> semanticResources;

    /**
     * This map allows to retrieve the eObject of the {@link DAnalysis.getModels} from its resource. This is useful in
     * the event of the resource has been unloaded and eObject.eResource()==null
     */
    private Map<String, EObject> resourceToRootEObjectMap = new HashMap<>();

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link DAnalysisSessionImpl} referencing the semantic resource
     */
    public SemanticResourcesUpdater(DAnalysisSessionImpl session) {
        this.session = session;
    }

    /**
     * Initialize the semantic resources of the session.
     * 
     * @param semanticResources
     *            the collection of semantic resources of the Session
     */
    public void setSemanticResources(Collection<Resource> semanticResources) {
        for (DAnalysis dAnalysis : this.session.allAnalyses()) {
            if (!dAnalysis.eAdapters().contains(this)) {
                dAnalysis.eAdapters().add(this);
            }
        }
        this.semanticResources = semanticResources;
    }

    @Override
    public void notifyChanged(Notification msg) {
        if (isEventOfInterest(msg)) {
            if (isRemoveSemanticResourceNotification(msg) && isRemoveManyStringNotification(msg)) {
                Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) semanticResources.iterator().next().getResourceSet()).getURIResourceMap();
                BasicEList<?> oldValue = (BasicEList<?>) msg.getOldValue();
                String uriString = (String) oldValue.get(0);
                URI oldResourceURI = URI.createURI(uriString);
                // Check in the URIResourceMap if the URI of the removed resource is associated with a different
                // resource
                if (uriResourceMap.containsKey(oldResourceURI) && !uriResourceMap.get(oldResourceURI).getURI().toString().equals(uriString)) {
                    // The resource was not removed but renamed, the URIResourceMap needs to be updated to avoid
                    // loading the resource twice
                    Resource resource = uriResourceMap.get(oldResourceURI);
                    uriResourceMap.remove(oldResourceURI);
                    uriResourceMap.put(resource.getURI(), resource);
                }
            }

            Collection<Resource> updatedSemanticResources = SemanticResourceGetter.collectTopLevelSemanticResources(session);

            boolean newSemanticResourceAdded = false;
            for (Resource semanticResource : updatedSemanticResources) {
                if (!semanticResources.contains(semanticResource)) {
                    newSemanticResourceAdded = true;

                    // Ensure that the cross referencer adapter is on the
                    // semantic resource.
                    session.registerResourceInCrossReferencer(semanticResource);
                }
            }

            // The size comparison is useful when a semantic resource is removed
            // (newSemanticResourceAdded is false but the size has changed)
            if (newSemanticResourceAdded || semanticResources.size() != updatedSemanticResources.size()) {
                semanticResources.clear();
                semanticResources.addAll(updatedSemanticResources);

                session.notifyListeners(SessionListener.SEMANTIC_CHANGE);
            }
        }
    }

    private boolean isEventOfInterest(Notification msg) {
        return (msg.getEventType() != Notification.REMOVING_ADAPTER) && FEATURES_OF_INTEREST.contains(msg.getFeature());
    }

    private boolean isRemoveSemanticResourceNotification(Notification msg) {
        return msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__SEMANTIC_RESOURCES && !semanticResources.isEmpty();
    }

    private boolean isRemoveManyStringNotification(Notification msg) {
        return msg.getEventType() == Notification.REMOVE_MANY && msg.getOldValue() instanceof BasicEList<?> && !((BasicEList<?>) msg.getOldValue()).isEmpty()
                && ((BasicEList<?>) msg.getOldValue()).get(0) instanceof String;
    }

    /**
     * Dispose this updater.
     */
    public void dispose() {
        for (DAnalysis dAnalysis : this.session.allAnalyses()) {
            if (dAnalysis.eAdapters().contains(this)) {
                dAnalysis.eAdapters().remove(this);
            }
        }
        session = null;
        semanticResources = null;
        resourceToRootEObjectMap.clear();
    }
}
