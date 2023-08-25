/*******************************************************************************
 * Copyright (c) 2012, 2023 THALES GLOBAL SERVICES and others.
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
import java.util.stream.Stream;

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
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;

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
                        NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters())
                                .or(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getHideLabelFilter_HiddenLabels()))))));
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
                        boolean isVisible = notification.getNewBooleanValue();
                        
                        // hide incoming or outgoing NoteAttachment links
                        List<Edge> attachedEdge = GMFHelper.getAttachedEdgesRecursively(Collections.singleton(referencingView));
                        attachedEdge = attachedEdge.stream().filter(GMFNotationHelper::isNoteAttachment).toList();

                        viewsToUpdate.put(referencingView, isVisible);
                        for (Edge view : attachedEdge) {
                            viewsToUpdate.put(view, isVisible);
                        }

                        if (removeHideNote) {
                            // PGE = Pure Graphical Element
                            // Get PGE attached to all element that are about to change visibility
                            List<Node> attachedPGE = Stream.concat(GMFHelper.getAttachedPGE(referencingView).stream(), GMFHelper.getAttachedPGE(attachedEdge).stream()).toList();
                            List<Node> needChangePGE;
                            if (isVisible) {
                                // all attached PGE need to be visible
                                needChangePGE = attachedPGE;
                            } else {
                                // only PGE attached only to hidden elements must be hidden
                                needChangePGE = GMFHelper.getElementWithoutVisibleConnection(attachedPGE, attachedEdge);
                            }

                            for (Node node : needChangePGE) {
                                viewsToUpdate.put(node, isVisible);
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
                            for (View view : getHiddenViews(correspondingLabelView, hideLabelFilter)) {
                                viewsToUpdate.put(view, Boolean.FALSE);
                            }
                        }
                    }
                }
            } else if (notification.getNotifier() instanceof HideLabelFilter && ((HideLabelFilter) notification.getNotifier()).eContainer() != null) {
                HideLabelFilter hideLabelFilter = (HideLabelFilter) notification.getNotifier();
                DDiagramElement dDiagramElement = (DDiagramElement) hideLabelFilter.eContainer();
                // Gather a list of element to hide and another one of element to show
                List<View> correspondingLabelView = lookForCorrespondingLabelView(dDiagramElement);
                Set<View> viewToHide = getHiddenViews(correspondingLabelView, hideLabelFilter);
                List<View> viewToShow = new ArrayList<View>(correspondingLabelView);
                viewToShow.removeAll(viewToHide);
                for (View view : getHiddenViews(correspondingLabelView, hideLabelFilter)) {
                    viewsToUpdate.put(view, Boolean.FALSE);
                }
                for (View view : viewToShow) {
                    viewsToUpdate.put(view, Boolean.TRUE);
                }
            }
        }
        if (!viewsToUpdate.isEmpty()) {
            cmd = new VisibilityUpdateCommand(session.getTransactionalEditingDomain(), viewsToUpdate);
        }
        return cmd;
    }

    private Set<View> getHiddenViews(List<View> views, HideLabelFilter hideLabelFilter) {
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
