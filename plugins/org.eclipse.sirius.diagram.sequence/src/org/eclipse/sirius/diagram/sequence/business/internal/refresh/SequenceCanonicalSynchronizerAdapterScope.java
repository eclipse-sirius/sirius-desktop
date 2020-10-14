/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.base.Predicate;

/**
 * Predicate to notify SequenceCanonicalSynchronizerAdapter only for adding of GMF View.
 * 
 * @author edugueperoux
 */
public class SequenceCanonicalSynchronizerAdapterScope implements Predicate<Notification> {

    private Diagram diagramView;

    /**
     * Constructor.
     * 
     * @param diagramView
     *            the diagram of interest.
     */
    public SequenceCanonicalSynchronizerAdapterScope(Diagram diagramView) {
        this.diagramView = diagramView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean apply(Notification notification) {
        return (isNotificationForNodeAdding(notification) || isNotificationForEdgeAdding(notification)) && isDiagramOfInterestConcerned(notification);
    }

    private boolean isDiagramOfInterestConcerned(Notification notification) {
        Object notifier = notification.getNotifier();
        return diagramView != null && notifier instanceof View && diagramView == ((View) notifier).getDiagram();
    }

    /**
     * Checks if this notification is about a add of a {@link Node} to the parent {@link Node}.
     * 
     * @param notification
     *            {@link Notification} to check
     * 
     * @return true if this notification is about a add of a {@link Node} to the parent {@link Node}
     */
    public static boolean isNotificationForNodeAdding(Notification notification) {
        boolean isNotificationForNodeAdding = false;
        Object newValue = notification.getNewValue();
        Object feature = notification.getFeature();
        isNotificationForNodeAdding = NotationPackage.Literals.VIEW__PERSISTED_CHILDREN.equals(feature) && Notification.ADD == notification.getEventType() && newValue instanceof Node
                && isNotANote(newValue);
        return isNotificationForNodeAdding;
    }

    /**
     * Test that newValue is not a GMF Shape which represents a Note.
     * 
     * @param newValue
     *            the object to test
     * 
     * @return true if newValue is not a GMF Shape which represents a Note, false else
     */
    private static boolean isNotANote(Object newValue) {
        return ((Node) newValue).getElement() != null;
    }

    /**
     * Checks if this notification is about a add of a {@link Edge} to the parent
     * {@link org.eclipse.gmf.runtime.notation.Diagram.Diagram}.
     * 
     * @param notification
     *            {@link Notification} to check
     * 
     * @return true if this notification is about a add of a {@link Edge} to the parent
     *         {@link org.eclipse.gmf.runtime.notation.Diagram}
     */
    public static boolean isNotificationForEdgeAdding(Notification notification) {
        boolean isNotificationForEdgeAdding = false;
        Object newValue = notification.getNewValue();
        Object feature = notification.getFeature();
        isNotificationForEdgeAdding = NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES.equals(feature) && Notification.ADD == notification.getEventType() && newValue instanceof Edge;
        return isNotificationForEdgeAdding;
    }

}
