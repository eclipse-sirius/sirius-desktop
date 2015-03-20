/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.Maps;

/**
 * A {@link Adapter} to update the collection of semantic resources in a
 * Session.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SemanticResourcesUpdater extends AdapterImpl implements Adapter {

    private DAnalysisSessionImpl dAnalysisSessionImpl;

    private Collection<Resource> semanticResources;

    /**
     * This map allows to retrieve the eObject of the
     * {@link DAnalysis.getModels} from its resource. This is useful in the
     * event of the resource has been unloaded and eObject.eResource()==null
     */
    private Map<String, EObject> resourceToRootEObjectMap = Maps.newHashMap();

    /**
     * Default constructor.
     * 
     * @param dAnalysisSessionImpl
     *            the {@link DAnalysisSessionImpl} referencing the semantic
     *            resource
     */
    public SemanticResourcesUpdater(DAnalysisSessionImpl dAnalysisSessionImpl) {
        this.dAnalysisSessionImpl = dAnalysisSessionImpl;
    }

    /**
     * Initialize the semantic resources of the session.
     * 
     * @param semanticResources
     *            the collection of semantic resources of the Session
     */
    public void setSemanticResources(Collection<Resource> semanticResources) {
        for (DAnalysis dAnalysis : this.dAnalysisSessionImpl.allAnalyses()) {
            if (!dAnalysis.eAdapters().contains(this)) {
                dAnalysis.eAdapters().add(this);
            }
        }
        this.semanticResources = semanticResources;
    }

    @Override
    public void notifyChanged(Notification msg) {
        // CHECKSTYLE:OFF
        if (msg.getEventType() != Notification.REMOVING_ADAPTER
                && (msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__REFERENCED_ANALYSIS
                        || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__SEMANTIC_RESOURCES || msg
                        .getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES)) {
            // CHECKSTYLE:ON

            Collection<Resource> updatedSemanticResources = SemanticResourceGetter.collectTopLevelSemanticResources(dAnalysisSessionImpl);

            boolean newSemanticResourceAdded = false;
            for (Resource semanticResource : updatedSemanticResources) {
                if (!semanticResources.contains(semanticResource)) {
                    newSemanticResourceAdded = true;

                    // Ensure that the cross referencer adapter is on the
                    // semantic resource.
                    dAnalysisSessionImpl.registerResourceInCrossReferencer(semanticResource);
                }
            }

            // The size comparison is useful when a semantic resource is removed
            // (newSemanticResourceAdded is false but the size has changed)
            if (newSemanticResourceAdded || semanticResources.size() != updatedSemanticResources.size()) {
                semanticResources.clear();
                semanticResources.addAll(updatedSemanticResources);

                dAnalysisSessionImpl.notifyListeners(SessionListener.SEMANTIC_CHANGE);
            }
        }
    }

    /**
     * Dispose this updater.
     */
    public void dispose() {
        for (DAnalysis dAnalysis : this.dAnalysisSessionImpl.allAnalyses()) {
            if (dAnalysis.eAdapters().contains(this)) {
                dAnalysis.eAdapters().remove(this);
            }
        }
        dAnalysisSessionImpl = null;
        semanticResources = null;
        resourceToRootEObjectMap.clear();
    }
}
