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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.util.BendpointsHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.util.NotificationQuery;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.base.Predicate;

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
            boolean isLayout = false;
            Object feature = input.getFeature();
            for (Object feature2 : features) {
                if (feature == feature2) {
                    isLayout = true;
                    break;
                }
            }
            return isLayout;
        }

    };

    private final Predicate<Notification> isSructuralNotationChange = new Predicate<Notification>() {
        int[] types = new int[] { Notification.ADD, Notification.ADD_MANY, Notification.MOVE, Notification.REMOVE, Notification.REMOVE_MANY };

        public boolean apply(Notification input) {
            return isStructural(input.getEventType());
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

    @Override
    public boolean apply(Notification input) {
        return !input.isTouch() && needLayout(input);
    }

    /**
     * Sequence layout must be triggered only if it is a change about sequence
     * related GMF notation model or a workspace image set on viewpoint model
     * about a sequence element.
     * 
     * @param notification
     *            the change to analyze
     * @return true if it is a change which need sequence layout, false
     *         otherwise
     */
    private boolean needLayout(Notification notification) {
        if (isSequenceChange(notification)) {
            return !isLayoutTouch(notification) && (containsStructuralNotationChanges(notification) || containsLayoutConstraintNotationChanges(notification));
        } else {
            return containsSetWkpImgApplication(notification);
        }
    }

    private boolean isSequenceChange(Notification input) {
        return new NotificationQuery(input).isNotationChange() && isSequenceElementChange(input);
    }

    private boolean isSequenceElementChange(Notification notification) {
        boolean isSequenceElement = false;
        Object notifier = notification.getNotifier();
        View view = getView(notifier);
        isSequenceElement = view != null && ISequenceElementAccessor.isPartOfSequenceElement(view);
        if (isSequenceElement) {
            Object value = getValue(notification);
            if (value != null) {
                boolean valueIsSequenceElt = false;
                if (value instanceof EObject) {
                    view = getView(value);
                    valueIsSequenceElt = view == null || ISequenceElementAccessor.isPartOfSequenceElement(view);
                } else if (value instanceof Collection<?>) {
                    Collection<?> values = (Collection<?>) value;
                    for (Object val : values) {
                        view = getView(val);
                        if (view == null || ISequenceElementAccessor.isPartOfSequenceElement(view)) {
                            valueIsSequenceElt = true;
                            break;
                        }
                    }
                }
                isSequenceElement = valueIsSequenceElt;
            }
        }
        return isSequenceElement;
    }

    private Object getValue(Notification notification) {
        Object value = null;
        Object newValue = notification.getNewValue();
        if (newValue instanceof EObject || newValue instanceof Collection<?>) {
            value = newValue;
        } else {
            Object oldValue = notification.getOldValue();
            if (oldValue instanceof EObject || oldValue instanceof Collection<?>) {
                value = oldValue;
            }
        }
        return value;
    }

    private View getView(Object notifier) {
        View view = null;
        if (notifier instanceof EObject) {
            if (notifier instanceof View) {
                view = (View) notifier;
            } else if (((EObject) notifier).eContainer() instanceof View) {
                // Needed for GMF Style and LayoutConstraint
                view = (View) ((EObject) notifier).eContainer();
            }
        }
        return view;
    }

    private boolean containsLayoutConstraintNotationChanges(Notification notification) {
        return isLayoutConstraintNotationChange.apply(notification);
    }

    private boolean containsStructuralNotationChanges(Notification notification) {
        return isSructuralNotationChange.apply(notification);
    }

    private boolean isLayoutTouch(Notification notification) {
        boolean isLayoutTouch = false;
        if (NotationPackage.eINSTANCE.getRelativeBendpoints_Points().equals(notification.getFeature())) {
            isLayoutTouch = BendpointsHelper.areSameBendpoints(notification.getOldValue(), notification.getNewValue());
        }
        return isLayoutTouch;
    }

    private boolean containsSetWkpImgApplication(Notification notification) {
        boolean newStyle = false;
        boolean wkpImageCustomization = false;
        boolean wkpImageDeCustomization = false;

        if (notification.getEventType() == Notification.SET && DiagramPackage.eINSTANCE.getDNode_OwnedStyle().equals(notification.getFeature()) && hasSequenceMapping(notification.getNotifier())) {
            newStyle = true;
        } else if (ViewpointPackage.eINSTANCE.getCustomizable_CustomFeatures().equals(notification.getFeature()) && notification.getNotifier() instanceof WorkspaceImage) {
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
