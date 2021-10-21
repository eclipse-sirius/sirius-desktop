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
package org.eclipse.sirius.diagram.ui.tools.internal.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.HideFilter;

/**
 * Queries on EMF Notifications to identify what they are about.
 * 
 * @author pcdavid
 */
public class NotificationQuery extends org.eclipse.sirius.common.tools.api.query.NotificationQuery {
    
    private static final Collection<EStructuralFeature> NOTATION_LAYOUT_FEATURES = new HashSet<EStructuralFeature>(Arrays.asList(NotationPackage.eINSTANCE.getRelativeBendpoints_Points(), NotationPackage.eINSTANCE.getEdge_Bendpoints(),
            NotationPackage.eINSTANCE.getLocation_Y(), NotationPackage.eINSTANCE.getLocation_X(), NotationPackage.eINSTANCE.getSize_Width(), NotationPackage.eINSTANCE.getSize_Height(),
            NotationPackage.eINSTANCE.getNode_LayoutConstraint()));

    private final Notification notif;

    /**
     * Constructor.
     * 
     * @param notif
     *            the notification to query.
     */
    public NotificationQuery(Notification notif) {
        super(notif);
        this.notif = Objects.requireNonNull(notif);
    }

    /**
     * Tests whether the notification concerns a GMF Notation element.
     * 
     * @return <code>true</code> if the notification concerns a GMF Notation
     *         element.
     */
    public boolean isNotationChange() {
        Object feature = notif.getFeature();
        return (feature instanceof EStructuralFeature) && ((EStructuralFeature) feature).getEContainingClass().getEPackage().equals(NotationPackage.eINSTANCE);
    }

    /**
     * Tests whether the notification concerns a GMF Notation layout element.
     * 
     * @return <code>true</code> if the notification concerns a GMF Notation layout
     *         element.
     */
    public boolean isNotationLayoutChange() {
        return NOTATION_LAYOUT_FEATURES.contains(notif.getFeature());
    }

    /**
     * Tests whether the notification is a GMF view becoming invisible.
     * 
     * @return <code>true</code> if the notification is a GMF view becoming
     *         invisible.
     */
    public boolean isViewBecomingInvisibleEvent() {
        Object feature = notif.getFeature();
        boolean isViewVisibleFalseEvent = NotationPackage.eINSTANCE.getView_Visible().equals(feature);
        isViewVisibleFalseEvent = isViewVisibleFalseEvent && !notif.getNewBooleanValue();
        isViewVisibleFalseEvent = isViewVisibleFalseEvent && Notification.SET == notif.getEventType();
        return isViewVisibleFalseEvent;

    }

    /**
     * Tests whether the notification is about the addition of a "Hide" filter
     * on a diagram element (which would make the element invisible).
     * 
     * @return <code>true</code> if the notification is about the addition of a
     *         "Hide" filter on a diagram element (which would make the element
     *         invisible).
     */
    public boolean isHideFilterAddEvent() {
        Object feature = notif.getFeature();
        boolean isHideFilterAddEvent = DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters().equals(feature);
        isHideFilterAddEvent = isHideFilterAddEvent && notif.getNewValue() instanceof HideFilter;
        isHideFilterAddEvent = isHideFilterAddEvent && Notification.ADD == notif.getEventType();
        return isHideFilterAddEvent;
    }
}
