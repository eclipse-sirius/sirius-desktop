/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.RepresentationHelper;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.SessionService;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionService;
import org.eclipse.sirius.business.internal.representation.DRepresentationLocationManager;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import com.google.common.base.Preconditions;

/**
 * The session services for DAnalysis.
 * 
 * @author cbrun
 */
public class DAnalysisSessionServicesImpl implements SessionService, DAnalysisSessionService {

    private final DAnalysisSessionImpl session;

    private DAnalysisSelector analysisSelector;

    private DRepresentationLocationManager representationLocationManager;

    /**
     * Create the services for analysis based session.
     * 
     * @param session
     *            the session.
     */
    public DAnalysisSessionServicesImpl(DAnalysisSessionImpl session) {
        this.session = Preconditions.checkNotNull(session);
        this.representationLocationManager = new DRepresentationLocationManager();
    }

    @Override
    public void clearCustomData(final EObject associatedInstance) {
        // Do nothing.
    }

    @Override
    public void clearCustomData(final String key, final EObject associatedInstance) {
        final Collection<DAnalysis> analysisAndReferenced = session.allAnalyses();
        final Collection<Resource> resources = getResources(analysisAndReferenced);
        if (CustomDataConstants.GMF_DIAGRAMS.equals(key)) {
            clearGMFDiagramsData(associatedInstance, resources, analysisAndReferenced);
        }
    }

    private void clearGMFDiagramsData(final EObject associatedInstance, final Collection<Resource> resources, Collection<DAnalysis> analysisAndReferenced) {
        final Collection<EObject> toRemove = new ArrayList<>();
        for (final Resource res : resources) {
            toRemove.addAll(getGMFDiagramsData(associatedInstance, res));
        }
        if (associatedInstance instanceof DRepresentation) {
            for (AnnotationEntry annotation : ((DRepresentation) associatedInstance).getOwnedAnnotationEntries()) {
                if (annotation.getSource().equals(CustomDataConstants.GMF_DIAGRAMS)) {
                    toRemove.add(annotation);
                }
            }
        }
        for (final EObject eObject : toRemove) {
            SiriusUtil.delete(eObject);
        }
    }

    @Override
    public Collection<EObject> getCustomData(final String key, final EObject associatedInstance) {

        final Collection<DAnalysis> analysisAndReferenced = session.allAnalyses();
        final Collection<Resource> resources = getResources(analysisAndReferenced);

        Collection<EObject> datas = Collections.emptySet();
        if (CustomDataConstants.GMF_DIAGRAMS.equals(key)) {
            datas = getGMFDiagramsData(associatedInstance);
            datas.addAll(getGMFDiagramsData(associatedInstance, resources));
        } else if (CustomDataConstants.DREPRESENTATION_FROM_DESCRIPTION.equals(key)) {
            datas = getRepresentationFromDescData(associatedInstance, analysisAndReferenced);
        } else if (CustomDataConstants.DFEATUREEXTENSION.equals(key)) {
            datas = getFeatureExtensionsData(associatedInstance, resources);
        } else {
            // if the key isn't identified,
            // we return all DAnalysisCustomDatas associated to the given key
            datas = getCustomKeyData(resources);
        }
        return datas;
    }

    private Collection<EObject> getCustomKeyData(Collection<Resource> resources) {
        final Collection<EObject> datas = new ArrayList<>();
        for (final Resource res : resources) {
            for (final EObject object : res.getContents()) {
                if (object instanceof DAnalysisCustomData) {
                    datas.add(((DAnalysisCustomData) object).getData());
                }
            }
        }
        return datas;
    }

    private Collection<EObject> getRepresentationFromDescData(final EObject associatedInstance, Collection<DAnalysis> analysisAndReferenced) {
        final Collection<EObject> datas = new ArrayList<>();
        for (DAnalysis analysis : analysisAndReferenced) {
            for (final DView view : analysis.getOwnedViews()) {
                final Iterator<DRepresentation> it = new DViewQuery(view).getLoadedRepresentations().iterator();
                while (it.hasNext()) {
                    final DRepresentation rep = it.next();
                    final RepresentationDescription currentRepresentationDescription = DialectManager.INSTANCE.getDescription(rep);
                    if (EqualityHelper.areEquals(currentRepresentationDescription, associatedInstance)) {
                        datas.add(rep);
                    }
                }
            }
        }
        return datas;
    }

    private Collection<EObject> getGMFDiagramsData(final EObject associatedInstance, Collection<Resource> resources) {
        final Collection<EObject> datas = new ArrayList<>();
        for (final Resource res : resources) {
            datas.addAll(getGMFDiagramsData(associatedInstance, res));
        }
        return datas;
    }

    private Collection<EObject> getGMFDiagramsData(final EObject representation) {
        final Collection<EObject> datas = new ArrayList<>();
        if (representation instanceof DRepresentation) {
            DRepresentation dRepresentation = (DRepresentation) representation;
            for (AnnotationEntry annotation : new DRepresentationQuery(dRepresentation).getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
                datas.add(annotation.getData());
            }
        }
        return datas;
    }

    private Collection<EObject> getGMFDiagramsData(final EObject associatedInstance, final Resource res) {
        final Collection<EObject> datas = new ArrayList<>();
        for (final EObject object : res.getContents()) {
            if (isAGMFDiagramOnAssociatedInstance(object, associatedInstance)) {
                datas.add(object);
            }
        }
        return datas;
    }

    private Collection<EObject> getFeatureExtensionsData(final EObject associatedInstance, Collection<Resource> resources) {
        final Collection<EObject> datas = new ArrayList<>();
        Collection<Resource> allResources = new LinkedHashSet<>(); // avoid resources duplications
        allResources.addAll(resources);
        // We also need to looking for the data in the given associatedInstance resource. (srm file for instance)
        if (associatedInstance != null) {
            Resource associatedInstanceResource = associatedInstance.eResource();
            if (associatedInstanceResource != null) {
                allResources.add(associatedInstanceResource);
            }
        } else {
            // getting all srm from aird to add them to allResources
            resources.stream().findFirst().ifPresent(resource -> {
                List<Resource> srms = resource.getResourceSet().getResources().stream().filter(res -> new ResourceQuery(res).isSrmResource()).collect(Collectors.toList());
                allResources.addAll(srms);
            });
            allResources.addAll(session.getAllSessionResources());
        }
        for (final Resource res : allResources) {
            for (final EObject object : res.getContents()) {
                if (object instanceof DFeatureExtension) {
                    datas.add(object);
                }
            }
        }
        return datas;
    }

    @Override
    public void putCustomData(final String key, final EObject associatedInstance, final EObject data) {
        if (CustomDataConstants.GMF_DIAGRAMS.equals(key)) {
            if (associatedInstance instanceof DRepresentation) {
                RepresentationHelper.getOrCreateAnnotation(CustomDataConstants.GMF_DIAGRAMS, (DRepresentation) associatedInstance, data);
            }

        } else if (CustomDataConstants.DFEATUREEXTENSION.equals(key)) {
            final Resource resource = associatedInstance.eResource();
            if (resource != null) {
                resource.getContents().add(data);
            }
        } else {
            // if the key isn't identified, we add a new
            // DAnalysisCustomData to the resource
            final Resource resource = associatedInstance.eResource();
            if (resource != null) {
                DAnalysisCustomData customData = ViewpointFactory.eINSTANCE.createDAnalysisCustomData();
                customData.setKey(key);
                customData.setData(data);
                resource.getContents().add(customData);
            }
        }
    }

    private Collection<Resource> getResources(Collection<DAnalysis> analysisAndReferenced) {
        final Collection<Resource> resources = new ArrayList<>();
        for (DAnalysis analysis : analysisAndReferenced) {
            final Resource res = analysis.eResource();
            if (res != null) {
                resources.add(res);
            }
        }
        return resources;
    }

    private boolean isAGMFDiagramOnAssociatedInstance(final EObject object, final EObject associatedInstance) {
        EClass eClass = object.eClass();
        EPackage ePackage = eClass.getEPackage();
        if ("Diagram".equals(eClass.getName()) && ePackage != null && "notation".equals(ePackage.getNsPrefix())) { //$NON-NLS-1$ //$NON-NLS-2$
            final EObject element = (EObject) object.eGet(eClass.getEStructuralFeature("element")); //$NON-NLS-1$
            if (element != null && element == associatedInstance) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setAnalysisSelector(final DAnalysisSelector selector) {
        this.analysisSelector = selector;
    }

    public DAnalysisSelector getAnalysisSelector() {
        return analysisSelector;
    }

    public DRepresentationLocationManager getRepresentationLocationManager() {
        return representationLocationManager;
    }
}
