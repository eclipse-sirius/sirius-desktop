/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
     * Default constructor.
     * 
     * @param dAnalysisSessionImpl
     *            the {@link DAnalysisSessionImpl} referencing the semantic
     *            resource
     * 
     * @param semanticResources
     *            the collection of semantic resources of the Session
     */
    public SemanticResourcesUpdater(DAnalysisSessionImpl dAnalysisSessionImpl, Collection<Resource> semanticResources) {
        this.dAnalysisSessionImpl = dAnalysisSessionImpl;
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
        if (!msg.isTouch()
                && (msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__REFERENCED_ANALYSIS
                        || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__ANALYSES || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS__MODELS || msg.getFeature() == ViewpointPackage.Literals.DANALYSIS_SESSION_EOBJECT__CONTROLLED_RESOURCES)) {
            // CHECKSTYLE:ON
            RunnableWithResult<Collection<Resource>> semanticResourcesGetter = new SemanticResourceGetter(dAnalysisSessionImpl);
            semanticResourcesGetter.run();
            boolean hasNbOfSemanticResourcesChanged = semanticResources.size() != semanticResourcesGetter.getResult().size();
            semanticResources.clear();
            semanticResources.addAll(semanticResourcesGetter.getResult());
            if (hasNbOfSemanticResourcesChanged) {
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
    }
}
