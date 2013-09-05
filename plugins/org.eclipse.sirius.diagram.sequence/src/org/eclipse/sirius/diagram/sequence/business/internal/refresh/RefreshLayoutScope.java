/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.notation.NotationPackage;

import com.google.common.base.Predicate;

import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.WorkspaceImage;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.util.BendpointsHelper;
import org.eclipse.sirius.diagram.sequence.util.NotificationQuery;

/**
 * Default refresh layout scope for sequence diagram. This predicate decides
 * whether or not we need to refresh the graphical layout, i.e. launch a
 * non-packing layout
 * 
 * @author mporhel
 */
public class RefreshLayoutScope implements Predicate<Notification> {

    private final Predicate<Notification> isLayoutConstraintNotationChange = new Predicate<Notification>() {
        Object[] features = new Object[] { NotationPackage.eINSTANCE.getRelativeBendpoints_Points(), NotationPackage.eINSTANCE.getLocation_Y(), NotationPackage.eINSTANCE.getLocation_X(),
                NotationPackage.eINSTANCE.getSize_Width(), NotationPackage.eINSTANCE.getSize_Height(), };

        public boolean apply(Notification input) {
            NotificationQuery nq = new NotificationQuery(input);
            return nq.isNotationChange() && isLayout(input.getFeature()) && !input.isTouch();
        }

        private boolean isLayout(Object feature) {
            for (Object feature2 : features) {
                if (feature == feature2) {
                    return true;
                }
            }
            return false;
        }
    };

    private final Predicate<Notification> isSructuralNotationChange = new Predicate<Notification>() {
        int[] types = new int[] { Notification.ADD, Notification.ADD_MANY, Notification.MOVE, Notification.REMOVE, Notification.REMOVE_MANY };

        public boolean apply(Notification input) {
            NotificationQuery nq = new NotificationQuery(input);
            return nq.isNotationChange() && isStructural(input.getEventType());
        }

        private boolean isStructural(int eventType) {
            for (int type : types) {
                if (eventType == type) {
                    return true;
                }
            }
            return false;
        }
    };

    /**
     * {@inheritDoc}
     */
    public boolean apply(Notification input) {
        return needsLayout(input);
    }

    private boolean needsLayout(Notification notification) {
        return !isLayoutTouch(notification) && (containsStructuralNotationChanges(notification) || containsLayoutConstraintNotationChanges(notification) || containsSetWkpImgApplication(notification));
    }

    private boolean containsLayoutConstraintNotationChanges(Notification notification) {
        return isLayoutConstraintNotationChange.apply(notification);
    }

    private boolean containsStructuralNotationChanges(Notification notification) {

        return isSructuralNotationChange.apply(notification);
    }

    private boolean isLayoutTouch(Notification notification) {
        boolean result = true;

        boolean isTouch = notification.isTouch();
        if (!isTouch) {
            if (NotationPackage.eINSTANCE.getRelativeBendpoints_Points().equals(notification.getFeature())) {
                isTouch = BendpointsHelper.areSameBendpoints(notification.getOldValue(), notification.getNewValue());
            }
        }

        if (!isTouch) {
            result = false;
        }

        return result;
    }

    private boolean containsSetWkpImgApplication(Notification notification) {
        boolean newStyle = false;
        boolean wkpImageCustomization = false;
        boolean wkpImageDeCustomization = false;

        if (notification.getEventType() == Notification.SET && SiriusPackage.eINSTANCE.getDNode_OwnedStyle().equals(notification.getFeature()) && hasSequenceMapping(notification.getNotifier())) {
            newStyle = true;
        } else if (SiriusPackage.eINSTANCE.getCustomizable_CustomFeatures().equals(notification.getFeature()) && notification.getNotifier() instanceof WorkspaceImage) {
            WorkspaceImage workspaceImage = (WorkspaceImage) notification.getNotifier();
            wkpImageCustomization = !workspaceImage.getCustomFeatures().isEmpty();
            wkpImageDeCustomization = !wkpImageCustomization;
        }

        return newStyle && (wkpImageCustomization || wkpImageDeCustomization);
    }

    private boolean hasSequenceMapping(Object notifier) {
        if (notifier instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) notifier;
            return AbstractNodeEvent.viewpointElementPredicate().apply(dde) || EndOfLife.viewpointElementPredicate().apply(dde) || InstanceRole.viewpointElementPredicate().apply(dde);
        }
        return false;
    }
}
