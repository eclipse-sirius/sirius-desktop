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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.internal.query.DAnalysisesInternalQuery;
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
        Collection<DAnalysis> allAnalyses = new DAnalysisesInternalQuery(dAnalysisSessionImpl.getAnalyses()).getAllAnalyses();
        for (DAnalysis dAnalysis : allAnalyses) {
            if (!dAnalysis.eAdapters().contains(this)) {
                dAnalysis.eAdapters().add(this);
            }
        }
        this.semanticResources = semanticResources;
    }

    @Override
    public void notifyChanged(Notification msg) {
        // CHECKSTYLE:OFF
        if (!msg.isTouch()
                && (msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__REFERENCED_ANALYSIS
                        || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__MODELS || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES)) {
            // CHECKSTYLE:ON

            // update the map resource-EObject
            if (msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__MODELS) {
                int eventType = msg.getEventType();
                if (eventType == Notification.ADD || eventType == Notification.ADD_MANY) {
                    addAssociatedResource(msg.getNewValue());
                } else if (eventType == Notification.REMOVE || eventType == Notification.REMOVE_MANY) {
                    removeAssociatedResource(msg.getOldValue());
                }
            }

            RunnableWithResult<Collection<Resource>> semanticResourcesGetter = new SemanticResourceGetter(dAnalysisSessionImpl);
            semanticResourcesGetter.run();
            boolean hasNbOfSemanticResourcesChanged = semanticResources.size() != semanticResourcesGetter.getResult().size();
            semanticResources.clear();
            semanticResources.addAll(semanticResourcesGetter.getResult());
            if (hasNbOfSemanticResourcesChanged) {
                dAnalysisSessionImpl.transfertNotification(SessionListener.SEMANTIC_CHANGE);
            }
        }
    }

    private void addAssociatedResource(Object value) {
        if (value instanceof EObject) {
            Resource eResource = ((EObject) value).eResource();
            if (eResource != null) {
                resourceToRootEObjectMap.put(eResource.getURI().toString(), (EObject) value);
            }
        } else if (value instanceof EList<?>) {
            EList<?> eListNotifier = (EList<?>) value;
            for (Object object : eListNotifier) {
                addAssociatedResource(object);
            }
        }
    }

    private void removeAssociatedResource(Object value) {
        if (value instanceof EObject) {
            for (String resource : resourceToRootEObjectMap.keySet()) {
                if (value.equals(resourceToRootEObjectMap.get(resource))) {
                    resourceToRootEObjectMap.remove(resource);
                    break;
                }
            }
        } else if (value instanceof EList<?>) {
            EList<?> eListNotifier = (EList<?>) value;
            for (Object object : eListNotifier) {
                removeAssociatedResource(object);
            }
        }
    }

    /**
     * Return the root EObject associated to the resource. The root EObject is
     * part of {@link DAnalysis.getModels}
     * 
     * @param resourceURI
     *            the URI of the resource
     * @return the eObject
     */
    public EObject getRootObjectFromResourceURI(String resourceURI) {
        return resourceToRootEObjectMap.get(resourceURI);
    }

    /**
     * Dispose this updater.
     */
    public void dispose() {
        Collection<DAnalysis> allAnalyses = new DAnalysisesInternalQuery(dAnalysisSessionImpl.getAnalyses()).getAllAnalyses();
        for (DAnalysis dAnalysis : allAnalyses) {
            if (dAnalysis.eAdapters().contains(this)) {
                dAnalysis.eAdapters().remove(this);
            }
        }
        dAnalysisSessionImpl = null;
        semanticResources = null;
        resourceToRootEObjectMap.clear();
    }
}
