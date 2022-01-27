/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.tools.internal.editor.SelectDRepresentationElementsListener;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.base.Predicate;

/**
 * A post commit listener which selects the representation elements specified through the "Elements To Select"
 * expression and "Inverse Selection Order" tag of the tool. </br>
 * Elements will be selected only on the active editor. </br>
 * 
 * This post-commit listener adds the reconnection of edge in the filter (compared to default
 * {@link SelectDRepresentationElementsListener}).
 * 
 * @author lredor
 */
public class DiagramSelectDRepresentationElementsListener extends SelectDRepresentationElementsListener {

    /**
     * Filter {@link Notification}s which are not an DEdge reconnect. A DEdge reconnect is:
     * <UL>
     * <LI>a set notification concerning sourceNode or targetNode of DEdge, </LI>
     * <LI>and with a not null value for newValue and oldValue,</LI>
     * <LI>and oldValue != newValue.</LI>
     * </UL>
     */
    public static final Predicate<Notification> IS_RECONNECT = new Predicate<Notification>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean apply(Notification input) {
            if (input.getEventType() == Notification.SET && 
                    (DiagramPackage.eINSTANCE.getDEdge_SourceNode().equals(input.getFeature()) || DiagramPackage.eINSTANCE.getDEdge_TargetNode().equals(input.getFeature()))) {
                return input.getOldValue() != null && input.getNewValue() != null && !(input.getOldValue().equals(input.getNewValue()));
            }
            return false;
        }

    };

    /**
     * Same notification filter than default one
     * ({@link SelectDRepresentationElementsListener#DEFAULT_NOTIFICATION_FILTER}) but with the edge reconnect added.
     */
    private static final NotificationFilter DEFAULT_NOTIFICATION_FILTER_FOR_DIAGRAM = NotificationFilter.createFeatureFilter(ViewpointPackage.Literals.UI_STATE__ELEMENTS_TO_SELECT)
            .or(NotificationFilter.NOT_TOUCH.and(SessionEventBrokerImpl.asFilter(DanglingRefRemovalTrigger.IS_ATTACHMENT))
                    .and(NotificationFilter.createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION)
                            .or(NotificationFilter.createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION_ELEMENT))))
            .or(NotificationFilter.NOT_TOUCH.and(NotificationFilter.createNotifierTypeFilter(DiagramPackage.Literals.DEDGE).and(SessionEventBrokerImpl.asFilter(IS_RECONNECT))));

    /**
     * Default constructor.
     * 
     * @param editor
     *            the editor on which the representation elements should be selected, if the editor is active.
     * @param selectOnlyViewWithNewSemanticTarget
     *            true to select only created view whose semantic target has also been created otherwise select also
     *            created view whose semantic target was already existing
     */
    public DiagramSelectDRepresentationElementsListener(DialectEditor editor, boolean selectOnlyViewWithNewSemanticTarget) {
        super(editor, selectOnlyViewWithNewSemanticTarget, DEFAULT_NOTIFICATION_FILTER_FOR_DIAGRAM);
    }

    /**
     * Analyze the notifications to retrieve the elements to select by default. The difference with the method of super
     * class is that the element can be a reconnected edge.
     * 
     * @param event
     *            current event to retrieve notifications
     * @param currentRep
     *            The current representation
     * @param keptNotifiedElements
     *            List of elements to complete
     * @return true if the element must be updated according to UI State, false otherwise.
     */
    protected boolean analyseNotifications(ResourceSetChangeEvent event, DRepresentation currentRep, List<DRepresentationElement> keptNotifiedElements) {
        boolean elementsToSelectUpdated = false;
        Collection<EObject> attachedEObjects = null;
        for (Notification n : event.getNotifications()) {
            Object feature = n.getFeature();
            if (!ViewpointPackage.Literals.UI_STATE__ELEMENTS_TO_SELECT.equals(feature) && !ViewpointPackage.Literals.DREPRESENTATION__UI_STATE.equals(feature)) {
                Set<DRepresentationElement> notificationValues;
                if (DiagramPackage.eINSTANCE.getDEdge_SourceNode().equals(n.getFeature()) || DiagramPackage.eINSTANCE.getDEdge_TargetNode().equals(n.getFeature())) {
                    // Notification concerns an edge reconnection
                    notificationValues = Set.of((DEdge) n.getNotifier());
                } else {
                    notificationValues = getAttachmentNotificationValues(n);
                }
                for (DRepresentationElement elt : notificationValues) {
                    if (currentRep == new DRepresentationElementQuery(elt).getParentRepresentation()) {
                        if (attachedEObjects == null && isSelectOnlyViewWithNewSemanticTarget()) {
                            // Compute the change description effect only once.
                            TransactionChangeDescription changeDescription = event.getTransaction().getChangeDescription();
                            // Get the objects attached during the current
                            // transaction from the change description. The
                            // getObjectsToDetach() compute those elements which
                            // will be detached in case of rollback or undo.
                            attachedEObjects = changeDescription.getObjectsToDetach();
                        }
                        // EcoreUtil.isAncestor() used to only select top level
                        // created views.
                        if ((!isSelectOnlyViewWithNewSemanticTarget() || isViewWithNewSemanticTarget(attachedEObjects, elt)) && !EcoreUtil.isAncestor(keptNotifiedElements, elt)) {
                            keptNotifiedElements.add(elt);
                        }
                    }
                }
            } else {
                elementsToSelectUpdated = true;
            }
        }
        return elementsToSelectUpdated;
    }
}
