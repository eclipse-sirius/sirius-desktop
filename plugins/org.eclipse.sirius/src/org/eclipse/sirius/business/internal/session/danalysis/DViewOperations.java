/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES, Obeo
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.extender.MetamodelDescriptorManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.internal.metamodel.helper.ComponentizationHelper;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Operations to manage a DAnalysis's DViews and Viewpoint enablement in a consistent way.
 * 
 * @author pcdavid
 */
final class DViewOperations {
    private final DAnalysisSessionImpl session;

    private DViewOperations(DAnalysisSessionImpl session) {
        this.session = session;
    }

    public static DViewOperations on(DAnalysisSessionImpl session) {
        return new DViewOperations(Objects.requireNonNull(session));
    }

    public Collection<Viewpoint> getSelectedViewpoints(boolean includeReferencedAnalysis) {
        SortedSet<Viewpoint> result = new TreeSet<Viewpoint>(new ViewpointRegistry.ViewpointComparator());
        Collection<DAnalysis> scope = includeReferencedAnalysis ? session.allAnalyses() : Collections.singleton(session.getMainAnalysis());
        for (DView view : getSelectedViews(scope)) {
            Viewpoint viewpoint = view.getViewpoint();
            if (viewpoint != null && !viewpoint.eIsProxy()) {
                result.add(viewpoint);
            }
        }
        return Collections.unmodifiableSet(result);
    }

    public Collection<DView> getSelectedViews(Iterable<DAnalysis> analyses) {
        Collection<DView> selectedViews = new LinkedHashSet<>();
        for (DAnalysis analysis : analyses) {
            if (analysis != null) {
                selectedViews.addAll(analysis.getSelectedViews());
            }
        }
        return selectedViews;
    }

    /**
     * Get the selected viewpoints sorted form more specifis to generics.
     * 
     * @return a collection of selected viewpoints for this session.
     */
    public Collection<Viewpoint> getSelectedViewpointsSpecificToGeneric() {
        Collection<Viewpoint> viewpoints = getSelectedViewpoints(false);
        // Then orders specific to generic
        final List<Viewpoint> orderedViewpoints = new ArrayList<Viewpoint>(viewpoints.size());
        for (final Viewpoint viewpoint : viewpoints) {
            int insertPosition = orderedViewpoints.size();
            for (final Viewpoint viewpoint2 : orderedViewpoints) {
                if (ComponentizationHelper.isExtendedBy(viewpoint, viewpoint2)) {
                    insertPosition = orderedViewpoints.indexOf(viewpoint2);
                } else if (ComponentizationHelper.isExtendedBy(viewpoint2, viewpoint)) {
                    insertPosition = orderedViewpoints.indexOf(viewpoint2) + 1;
                }
            }
            orderedViewpoints.add(insertPosition, viewpoint);
        }
        return Collections.unmodifiableCollection(orderedViewpoints);
    }

    public void createView(final Viewpoint viewpoint, final Collection<EObject> semantics, final boolean createNewRepresentations, IProgressMonitor monitor) {
        try {
            monitor.beginTask(MessageFormat.format(Messages.DViewOperations_createViewMsg, viewpoint.getName()), 3 + 10 * semantics.size());
            Set<DView> intializedDViews = new LinkedHashSet<DView>();
            for (DAnalysis analysis : session.allAnalyses()) {
                if (!hasAlreadyDViewForViewpoint(analysis, viewpoint)) {
                    DView view = getOrCreateFreshDView(analysis);
                    view.setViewpoint(viewpoint);
                    analysis.getOwnedViews().add(view);
                    analysis.getSelectedViews().add(view);
                    intializedDViews.add(view);
                }
            }
            monitor.worked(1);

            session.configureInterpreter();
            if (createNewRepresentations) {
                monitor.subTask(Messages.DViewOperations_initRepresentationMsg);
                for (final EObject semantic : semantics) {
                    DialectManager.INSTANCE.initRepresentations(viewpoint, semantic, new SubProgressMonitor(monitor, 10));
                }
            }
            updateSelectedViewpointsData(new SubProgressMonitor(monitor, 1));
            monitor.worked(1);
            /* DVIew created are automatically selected */
            if (!intializedDViews.isEmpty()) {
                session.notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            }
            monitor.worked(1);
        } finally {
            monitor.done();
        }
    }

    public void addSelectedView(DView view, IProgressMonitor monitor) throws IllegalArgumentException {
        try {
            monitor.beginTask(Messages.DViewOperations_addSelectedViewMsg, 3);
            DAnalysis foundAnalysis = null;
            for (final DAnalysis dAnalysis : session.allAnalyses()) {
                if (dAnalysis.getOwnedViews().contains(view)) {
                    foundAnalysis = dAnalysis;
                    break;
                }
            }
            if (foundAnalysis == null) {
                throw new IllegalArgumentException(Messages.DViewOperations_notContainedErrorMsg);
            }
            foundAnalysis.getSelectedViews().add(view);
            monitor.worked(1);
            updateSelectedViewpointsData(new NullProgressMonitor());
            monitor.worked(1);
            session.notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            monitor.worked(1);
            session.configureInterpreter();
        } finally {
            monitor.done();
        }
    }

    public void removeSelectedView(final DView view, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DViewOperations_removeSelectedViewMsg, 1);
            session.getMainAnalysis().getSelectedViews().remove(view);
            updateSelectedViewpointsData(new SubProgressMonitor(monitor, 1));
            session.notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            session.configureInterpreter();
        } finally {
            monitor.done();
        }
    }

    public Collection<DView> getOwnedViews() {
        Collection<DView> ownedViews = new LinkedHashSet<>();
        for (DAnalysis analysis : session.allAnalyses()) {
            ownedViews.addAll(analysis.getOwnedViews());
        }
        return ownedViews;
    }

    public void updateSelectedViewpointsData(IProgressMonitor monitor) {
        try {
            Set<Viewpoint> selectedViewpoints = new LinkedHashSet<>();
            for (Viewpoint viewpoint : getSelectedViewpoints(false)) {
                if (viewpoint.eResource() != null) {
                    selectedViewpoints.add(SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint));
                } else {
                    selectedViewpoints.add(viewpoint);
                }
            }
            SetView<Viewpoint> difference = Sets.difference(Sets.newHashSet(session.getActivatedViewpoints()), Sets.newHashSet(selectedViewpoints));
            monitor.beginTask(Messages.DViewOperations_updateSelectedVPDataMsg, selectedViewpoints.size() + difference.size() + 1);
            // FIXME : it is useful?
            for (Viewpoint viewpoint : selectedViewpoints) {
                Resource viewpointResource = viewpoint.eResource();
                if (viewpointResource != null) {
                    session.registerResourceInCrossReferencer(viewpointResource);
                }
                monitor.worked(1);
            }
            for (Viewpoint viewpoint : difference) {
                Resource viewpointResource = viewpoint.eResource();
                if (viewpointResource != null) {
                    session.unregisterResourceInCrossReferencer(viewpointResource);
                }
                monitor.worked(1);
            }
            session.getActivatedViewpoints().addAll(selectedViewpoints);
            session.getActivatedViewpoints().retainAll(selectedViewpoints);
            // tell the accessor and the interpreter which metamodels we know
            // of.
            final ModelAccessor accessor = session.getModelAccessor();
            Collection<MetamodelDescriptor> metamodels = MetamodelDescriptorManager.INSTANCE.provides(getSelectedViewpoints(false));
            if (accessor != null) {
                accessor.activateMetamodels(metamodels);
            }
            session.getInterpreter().activateMetamodels(metamodels);
        } finally {
            monitor.done();
        }
    }

    private DView getOrCreateFreshDView(DAnalysis analysis) {
        DView orphan = new DAnalysisQuery(analysis).getFirstOrphanDView();
        if (orphan != null) {
            return orphan;
        } else {
            return ViewpointFactory.eINSTANCE.createDView();
        }
    }

    private boolean hasAlreadyDViewForViewpoint(DAnalysis dAnalysis, Viewpoint viewpoint) {
        for (DView ownedDView : dAnalysis.getOwnedViews()) {
            Viewpoint vp = ownedDView.getViewpoint();
            if (vp != null && EqualityHelper.areEquals(vp, viewpoint)) {
                return true;
            }
        }
        return false;
    }
}
