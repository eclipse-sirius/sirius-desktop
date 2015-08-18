/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusHelper;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.SessionService;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionService;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * The session services for DAnalysis.
 * 
 * @author cbrun
 */
public class DAnalysisSessionServicesImpl implements SessionService, DAnalysisSessionService {

    private final DAnalysisSessionImpl session;

    private DAnalysisSelector analysisSelector;

    /**
     * Create the services for analysis based session.
     * 
     * @param session
     *            the session.
     */
    public DAnalysisSessionServicesImpl(DAnalysisSessionImpl session) {
        this.session = Preconditions.checkNotNull(session);
    }

    @Override
    public void clearCustomData(final EObject associatedInstance) {
        // Do nothing.
    }

    @Override
    public void clearCustomData(final String key, final EObject associatedInstance) {
        final Collection<DAnalysis> analysisAndReferenced = session.allAnalyses();
        final Collection<Resource> resources = getResources(analysisAndReferenced);
        if (CustomDataConstants.DREPRESENTATION.equals(key)) {
            clearRepresentationData(associatedInstance, analysisAndReferenced);
        } else if (CustomDataConstants.GMF_DIAGRAMS.equals(key)) {
            clearGMFDiagramsData(associatedInstance, resources, analysisAndReferenced);
        }
    }

    private void clearRepresentationData(final EObject associatedInstance, final Collection<DAnalysis> analysisAndReferenced) {
        for (DRepresentation rep : getAllRepresentations(analysisAndReferenced)) {
            if (rep instanceof DSemanticDecorator && ((DSemanticDecorator) rep).getTarget() == associatedInstance) {
                SiriusUtil.delete(rep);
            }
        }
    }

    private Collection<DRepresentation> getAllRepresentations(final Collection<DAnalysis> analysisAndReferenced) {
        final Collection<DRepresentation> representations = Lists.newArrayList();
        for (DAnalysis analysis : analysisAndReferenced) {
            for (final DView view : analysis.getOwnedViews()) {
                representations.addAll(view.getOwnedRepresentations());
            }
        }
        return representations;
    }

    private void clearGMFDiagramsData(final EObject associatedInstance, final Collection<Resource> resources, Collection<DAnalysis> analysisAndReferenced) {
        final Collection<EObject> toRemove = Lists.newArrayList();
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
        } else if (CustomDataConstants.DREPRESENTATION.equals(key)) {
            datas = getRepresentationData(associatedInstance, analysisAndReferenced);
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
        final Collection<EObject> datas = Lists.newArrayList();
        for (final Resource res : resources) {
            for (final EObject object : res.getContents()) {
                if (object instanceof DAnalysisCustomData) {
                    datas.add(((DAnalysisCustomData) object).getData());
                }
            }
        }
        return datas;
    }

    private Collection<EObject> getRepresentationData(final EObject associatedInstance, final Collection<DAnalysis> analysisAndReferenced) {
        RunnableWithResult<Collection<EObject>> runnable = new RunnableWithResult.Impl<Collection<EObject>>() {

            @Override
            public void run() {
                Collection<EObject> datas = new ArrayList<EObject>();
                for (DAnalysis analysis : analysisAndReferenced) {
                    for (final DView view : analysis.getOwnedViews()) {
                        final Iterator<DRepresentation> it = view.getOwnedRepresentations().iterator();
                        while (it.hasNext()) {
                            final DRepresentation rep = it.next();
                            if (rep instanceof DSemanticDecorator) {
                                final EObject element = ((DSemanticDecorator) rep).getTarget();
                                if (element != null && element == associatedInstance) {
                                    datas.add(rep);
                                } else if (associatedInstance == null) {
                                    datas.add(rep);
                                }
                            }
                        }
                    }
                }
                setResult(datas);
            }

        };
        if (associatedInstance != null) {
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(associatedInstance);
            if (domain != null) {
                try {
                    TransactionUtil.runExclusive(domain, runnable);
                } catch (InterruptedException e) {
                    SiriusPlugin.getDefault().error(e.getLocalizedMessage(), e);
                }
            } else {
                runnable.run();
            }
        } else {
            runnable.run();
        }
        return runnable.getResult();
    }

    private Collection<EObject> getRepresentationFromDescData(final EObject associatedInstance, Collection<DAnalysis> analysisAndReferenced) {
        final Collection<EObject> datas = Lists.newArrayList();
        for (DAnalysis analysis : analysisAndReferenced) {
            for (final DView view : analysis.getOwnedViews()) {
                final Iterator<DRepresentation> it = view.getOwnedRepresentations().iterator();
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
        final Collection<EObject> datas = Lists.newArrayList();
        for (final Resource res : resources) {
            datas.addAll(getGMFDiagramsData(associatedInstance, res));
        }
        return datas;
    }

    private Collection<EObject> getGMFDiagramsData(final EObject representation) {
        final Collection<EObject> datas = Lists.newArrayList();
        if (representation instanceof DRepresentation) {
            DRepresentation dRepresentation = (DRepresentation) representation;
            for (AnnotationEntry annotation : new DRepresentationQuery(dRepresentation).getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
                datas.add(annotation.getData());
            }
        }
        return datas;
    }

    private Collection<EObject> getGMFDiagramsData(final EObject associatedInstance, final Resource res) {
        final Collection<EObject> datas = Lists.newArrayList();
        for (final EObject object : res.getContents()) {
            if (isAGMFDiagramOnAssociatedInstance(object, associatedInstance)) {
                datas.add(object);
            }
        }
        return datas;
    }

    private Collection<EObject> getFeatureExtensionsData(final EObject associatedInstance, Collection<Resource> resources) {
        final Collection<EObject> datas = Lists.newArrayList();
        for (final Resource res : resources) {
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
                SiriusHelper.getOrCreateAnnotation(CustomDataConstants.GMF_DIAGRAMS, (DRepresentation) associatedInstance, data);
            }

        } else if (CustomDataConstants.DREPRESENTATION.equals(key)) {
            if (data instanceof DRepresentation) {
                final EObject semanticTarget = associatedInstance;
                if (semanticTarget != null) {
                    final Resource res = semanticTarget.eResource();
                    if (res != null) {
                        addRepresentationToContainer((DRepresentation) data, res);
                    }
                }

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
        final Collection<Resource> resources = Lists.newArrayList();
        for (DAnalysis analysis : analysisAndReferenced) {
            final Resource res = analysis.eResource();
            if (res != null) {
                resources.add(res);
            }
        }
        return resources;
    }

    private boolean isAGMFDiagramOnAssociatedInstance(final EObject object, final EObject associatedInstance) {
        if ("Diagram".equals(object.eClass().getName()) && object.eClass().getEPackage() != null && "notation".equals(object.eClass().getEPackage().getNsPrefix())) { //$NON-NLS-1$ //$NON-NLS-2$
            final EObject element = (EObject) object.eGet(object.eClass().getEStructuralFeature("element")); //$NON-NLS-1$
            if (element != null && element == associatedInstance) {
                return true;
            }
        }
        return false;
    }

    private void addRepresentationToContainer(final DRepresentation representation, final Resource res) {
        final EObject semanticRoot = res.getContents().iterator().next();
        final Viewpoint viewpoint = new RepresentationDescriptionQuery(DialectManager.INSTANCE.getDescription(representation)).getParentViewpoint();
        DRepresentationContainer existingContainer = DAnalysisSessionHelper.findContainerForAddedRepresentation(semanticRoot, viewpoint, session.allAnalyses(), analysisSelector, representation);

        if (existingContainer == null) {
            existingContainer = DAnalysisSessionHelper.findFreeContainerForAddedRepresentation(viewpoint, semanticRoot, session.getAnalyses(), analysisSelector, representation);
            if (existingContainer != null) {
                existingContainer.setViewpoint(viewpoint);
            }
        }
        if (existingContainer == null) {
            existingContainer = ViewpointFactory.eINSTANCE.createDRepresentationContainer();
            existingContainer.setViewpoint(viewpoint);
            final DAnalysis analysis = DAnalysisSessionHelper.selectAnalysis(viewpoint, session.allAnalyses(), analysisSelector, representation);
            analysis.getOwnedViews().add(existingContainer);
        }

        existingContainer.getOwnedRepresentations().add(representation);
    }

    @Override
    public void setAnalysisSelector(final DAnalysisSelector selector) {
        this.analysisSelector = selector;
    }
}
