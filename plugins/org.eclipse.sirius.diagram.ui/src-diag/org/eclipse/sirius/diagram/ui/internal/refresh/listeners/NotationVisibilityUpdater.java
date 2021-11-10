/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * This class update the notation model views visibility attribute when it
 * receives a notification about a DDiagramElement visibility change.
 * 
 * Make sure to attach and detach the updater to the editing domain.
 * 
 * @author cbrun
 */
public class NotationVisibilityUpdater extends ResourceSetListenerImpl {
    private Session session;

    /**
     * Create a new updater.
     * 
     * @param session
     *            the current session.
     */
    public NotationVisibilityUpdater(Session session) {
        super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDDiagramElement_Visible()).or(
                NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDEdge_IsFold()).or(
                        NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters())))));
        this.session = session;
        session.getTransactionalEditingDomain().addResourceSetListener(this);
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    @Override
    public Command transactionAboutToCommit(final ResourceSetChangeEvent event) throws RollbackException {
        Command cmd = null;
        Map<View, Boolean> viewsToUpdate = new HashMap<View, Boolean>();
        boolean removeHideNote = DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name());
        for (Notification notification : event.getNotifications()) {
            if (notification.getNotifier() instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = (DDiagramElement) notification.getNotifier();
                View referencingView = getReferencingView(dDiagramElement);
                if (referencingView != null) {
                    if (notification.getFeature() == DiagramPackage.eINSTANCE.getDDiagramElement_Visible()) {
                        viewsToUpdate.put(referencingView, Boolean.valueOf(notification.getNewBooleanValue()));
                        if (removeHideNote) {
                            Set<View> allViewsToHide = new HashSet<View>();
                            allContentsViewsVisibilityScope(referencingView, allViewsToHide, viewsToUpdate);
                            Iterators.filter(referencingView.eAllContents(), View.class);
                            final Iterator<View> allContents = Iterators.filter(referencingView.eAllContents(), View.class);
                            while (allContents.hasNext()) {
                                final Object next = allContents.next();
                                allContentsViewsVisibilityScope((View) next, allViewsToHide, viewsToUpdate);
                            }
                            for (View view : allViewsToHide) {
                                viewsToUpdate.put(view, notification.getNewBooleanValue());
                            }
                        }
                    } else if (notification.getFeature() == DiagramPackage.eINSTANCE.getDEdge_IsFold()) {
                        viewsToUpdate.put(referencingView, !Boolean.valueOf(notification.getNewBooleanValue()));
                    } else if (notification.getFeature() == DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters()) {
                        List<View> correspondingLabelView = lookForCorrespondingLabelView(dDiagramElement);
                        if (notification.getOldValue() instanceof HideLabelFilter) {
                            for (View view : correspondingLabelView) {
                                viewsToUpdate.put(view, Boolean.TRUE);
                            }
                        } else if (notification.getNewValue() instanceof HideLabelFilter) {
                            HideLabelFilter hideLabelFilter = (HideLabelFilter) notification.getNewValue();
                            for (View view : getViewToHide(correspondingLabelView, hideLabelFilter)) {
                                viewsToUpdate.put(view, Boolean.FALSE);
                            }
                        }
                    }
                }
            }
        }
        if (!viewsToUpdate.isEmpty()) {
            cmd = new VisibilityUpdateCommand(session.getTransactionalEditingDomain(), viewsToUpdate);
        }
        return cmd;
    }

    private Set<View> getViewToHide(List<View> views, HideLabelFilter hideLabelFilter) {
        Set<View> viewToHide = new HashSet<>();
        if (hideLabelFilter.getHiddenLabels().isEmpty()) {
            viewToHide.addAll(views);
        } else {
            for (View view : views) {
                if (hideLabelFilter.getHiddenLabels().contains(SiriusVisualIDRegistry.getVisualID(view.getType()))) {
                    viewToHide.add(view);
                }
            }
        }
        return viewToHide;
    }

    private Set<View> getAllRelatedNotesVisibilityScope(View viewToDelete, Map<View, Boolean> viewsToUpdate) {
        Set<View> linkedViews = new HashSet<>();

        for (Edge sourceEdge : Iterables.filter(viewToDelete.getSourceEdges(), Edge.class)) {
            View target = sourceEdge.getTarget();
            if (GMFNotationHelper.isNoteAttachment(sourceEdge)) {
                Set<View> linked = new HashSet<>();
                linked.add(viewToDelete);
                collectLinkedViews(target, linked, viewsToUpdate);
                linked.remove(viewToDelete);

                linkedViews.addAll(getSafe(linked));

            }
        }

        for (Edge targetEdge : Iterables.filter(viewToDelete.getTargetEdges(), Edge.class)) {
            View source = targetEdge.getSource();
            if (GMFNotationHelper.isNoteAttachment(targetEdge)) {
                Set<View> linked = new HashSet<>();
                linked.add(viewToDelete);
                collectLinkedViews(source, linked, viewsToUpdate);
                linked.remove(viewToDelete);

                linkedViews.addAll(getSafe(linked));

            }
        }

        return linkedViews;

    }

    /**
     * Remove all contains set if the set contains an element that is not a note
     * 
     * @param linked
     *            set of notes to delete.
     * @return a collection of notes
     */
    private Collection<? extends View> getSafe(Set<View> linked) {
        for (View linkedView : linked) {
            if (!(linkedView instanceof Node && GMFNotationHelper.isNote((Node) linkedView))) {
                return Collections.emptySet();
            }
        }

        return linked;
    }

    private void collectLinkedViews(final View v, Set<View> linkedViews, Map<View, Boolean> viewsToUpdate) {
        linkedViews.add(v);
        for (Edge sourceEdge : Iterables.filter(v.getSourceEdges(), Edge.class)) {
            View target = sourceEdge.getTarget();
            boolean sourceIsNotViewToUpdate = true;
            if (viewsToUpdate.get(target) != null) {
                sourceIsNotViewToUpdate = viewsToUpdate.get(target);
            }
            if (GMFNotationHelper.isNoteAttachment(sourceEdge)) {
                if (!linkedViews.contains(target) && target instanceof Node && GMFNotationHelper.isNote((Node) target)) {
                    collectLinkedViews(target, linkedViews, viewsToUpdate);
                } else if (target.isVisible() && sourceIsNotViewToUpdate) {
                    linkedViews.add(target);
                }
            }
        }

        for (Edge targetEdge : Iterables.filter(v.getTargetEdges(), Edge.class)) {
            View source = targetEdge.getSource();
            boolean sourceIsNotViewToUpdate = true;
            if (viewsToUpdate.get(source) != null) {
                sourceIsNotViewToUpdate = viewsToUpdate.get(source);
            }
            if (GMFNotationHelper.isNoteAttachment(targetEdge)) {
                if (!linkedViews.contains(source) && source instanceof Node && GMFNotationHelper.isNote((Node) source)) {
                    collectLinkedViews(source, linkedViews, viewsToUpdate);
                } else if (source.isVisible() && sourceIsNotViewToUpdate) {
                    linkedViews.add(source);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void allContentsViewsVisibilityScope(View viewToDelete, Set<View> allViewsToDelete, Map<View, Boolean> viewsToUpdate) {
        allViewsToDelete.addAll(getAllRelatedNotesVisibilityScope(viewToDelete, viewsToUpdate));

        for (Edge edge : Iterables.filter(Iterables.concat(viewToDelete.getSourceEdges(), viewToDelete.getTargetEdges()), Edge.class)) {
            if (GMFNotationHelper.isNoteAttachment(edge)) {
                allViewsToDelete.add(edge);
            }
        }
    }

    private View getReferencingView(DDiagramElement dDiagramElement) {
        View referencingView = null;
        EObjectQuery eObjectQuery = new EObjectQuery(dDiagramElement);
        Collection<EObject> inverseReferences = eObjectQuery.getInverseReferences(NotationPackage.Literals.VIEW__ELEMENT);
        if (!inverseReferences.isEmpty()) {
            EObject referencingEObject = inverseReferences.iterator().next();
            if (referencingEObject instanceof View) {
                referencingView = (View) referencingEObject;
            }
        }
        return referencingView;
    }

    private List<View> lookForCorrespondingLabelView(final DDiagramElement elem) {
        /*
         * Here we try to get a cross referencer adapter scoped to the diagram
         * instead of a resource wise one but there isn't right now. The
         * resource wise one is added by the session.
         */
        final ECrossReferenceAdapter crossReferencer = session.getSemanticCrossReferencer();
        List<View> result = new ArrayList<View>();
        if (crossReferencer != null) {
            final Iterator<EStructuralFeature.Setting> it = crossReferencer.getInverseReferences(elem).iterator();
            while (result.isEmpty() && it.hasNext()) {
                final EStructuralFeature.Setting setting = it.next();
                if (setting.getEObject() instanceof View) {
                    EList<View> children = ((View) setting.getEObject()).getChildren();
                    for (View view : children) {
                        ViewQuery query = new ViewQuery(view);
                        if (query.isForNameEditPart()) {
                            result.add(view);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Specific command to update view visibility.
     * 
     * @author mporhel
     * 
     */
    private static class VisibilityUpdateCommand extends RecordingCommand {

        private final Map<View, Boolean> viewsToUpdate;

        public VisibilityUpdateCommand(TransactionalEditingDomain domain, Map<View, Boolean> viewsToUpdate) {
            super(domain, Messages.VisibilityUpdateCommand_label);
            this.viewsToUpdate = viewsToUpdate;
        }

        @Override
        protected void doExecute() {
            for (final Map.Entry<View, Boolean> entry : viewsToUpdate.entrySet()) {
                entry.getKey().setVisible(entry.getValue().booleanValue());
            }
        }
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        session = null;
    }
}
