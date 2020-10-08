/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
import java.util.Collections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
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
 * Default refresh layout scope for sequence diagram. This predicate decides whether or not we need to refresh the
 * graphical layout, i.e. launch a non-packing layout
 * 
 * @author mporhel
 */
public class RefreshLayoutScope implements Predicate<Notification> {

    private final Diagram diagram;

    private final DDiagram dDiagram;

    private final EObject semanticElement;

    private final Predicate<Notification> isLayoutConstraintNotationChange = new Predicate<Notification>() {
        Object[] features = new Object[] { NotationPackage.eINSTANCE.getRelativeBendpoints_Points(), NotationPackage.eINSTANCE.getLocation_Y(), NotationPackage.eINSTANCE.getLocation_X(),
                NotationPackage.eINSTANCE.getSize_Width(), NotationPackage.eINSTANCE.getSize_Height(), };

        @Override
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

        @Override
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

    /**
     * Constructor.
     * 
     * @param notationDiagram
     *            the diagram of interest.
     */
    public RefreshLayoutScope(Diagram notationDiagram) {
        this.diagram = notationDiagram;
        this.dDiagram = diagram != null ? (DDiagram) diagram.getElement() : null;
        this.semanticElement = dDiagram instanceof DSemanticDiagram ? ((DSemanticDiagram) dDiagram).getTarget() : null;
    }

    @Override
    public boolean apply(Notification input) {
        boolean validScopeContext = diagram != null && !diagram.eIsProxy();
        validScopeContext = validScopeContext && dDiagram != null && !dDiagram.eIsProxy();
        validScopeContext = validScopeContext && semanticElement != null && !semanticElement.eIsProxy();
        return validScopeContext && !input.isTouch() && needLayout(input);
    }

    /**
     * Sequence layout must be triggered only if it is a change about sequence related GMF notation model or a workspace
     * image set on viewpoint model about a sequence element.
     * 
     * @param notification
     *            the change to analyze
     * @return true if it is a change which need sequence layout, false otherwise
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
        View notifierView = getView(notifier);
        isSequenceElement = notifierView != null && ISequenceElementAccessor.isPartOfSequenceElement(notifierView) && shouldTriggerLayoutForChangeOn(notifierView.getDiagram());
        if (isSequenceElement) {
            Collection<?> values = getValues(notification);
            if (values != null) {
                boolean valueIsSequenceElt = false;
                for (Object val : values) {
                    View valueView = getView(val);
                    if (valueView == null || valueView == notifierView || ISequenceElementAccessor.isPartOfSequenceElement(valueView)) {
                        valueIsSequenceElt = true;
                        break;
                    }
                }
                isSequenceElement = valueIsSequenceElt;
            }
        }
        return isSequenceElement;

    }

    private Collection<?> getValues(Notification notification) {
        Collection<?> values = null;
        Object newValue = notification.getNewValue();
        if (newValue instanceof EObject) {
            values = Collections.singletonList(newValue);
        } else if (newValue instanceof Collection<?>) {
            values = (Collection<?>) newValue;
        } else {
            Object oldValue = notification.getOldValue();
            if (oldValue instanceof EObject) {
                values = Collections.singletonList(oldValue);
            } else if (oldValue instanceof Collection<?>) {
                values = (Collection<?>) oldValue;
            }
        }
        return values;
    }

    private View getView(Object obj) {
        View view = null;
        if (obj instanceof View) {
            view = (View) obj;
        } else if (obj instanceof EObject && ((EObject) obj).eContainer() instanceof View) {
            // Needed for GMF Style and LayoutConstraint
            view = (View) ((EObject) obj).eContainer();
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

        Object notifier = notification.getNotifier();
        if (notification.getEventType() == Notification.SET && DiagramPackage.eINSTANCE.getDNode_OwnedStyle().equals(notification.getFeature()) && hasSequenceMapping(notifier)) {
            newStyle = true;
        } else if (ViewpointPackage.eINSTANCE.getCustomizable_CustomFeatures().equals(notification.getFeature()) && notifier instanceof WorkspaceImage
                && hasSequenceMapping(((WorkspaceImage) notifier).eContainer())) {
            WorkspaceImage workspaceImage = (WorkspaceImage) notifier;
            wkpImageCustomization = !workspaceImage.getCustomFeatures().isEmpty();
            wkpImageDeCustomization = !wkpImageCustomization;
        }

        return newStyle && (wkpImageCustomization || wkpImageDeCustomization);
    }

    private boolean hasSequenceMapping(Object notifier) {
        if (notifier instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) notifier;
            boolean hasSequenceMapping = AbstractNodeEvent.viewpointElementPredicate().apply(dde) || EndOfLife.viewpointElementPredicate().apply(dde)
                    || InstanceRole.viewpointElementPredicate().apply(dde);
            return hasSequenceMapping && shouldTriggerLayoutForChangeOn(dde.getParentDiagram());
        }
        return false;
    }

    private boolean shouldTriggerLayoutForChangeOn(Diagram impactedDiagram) {
        boolean needsLayoutOnCurrentDiagram = impactedDiagram == diagram;
        if (!needsLayoutOnCurrentDiagram && impactedDiagram != null) {
            EObject element = impactedDiagram.getElement();
            needsLayoutOnCurrentDiagram = element instanceof DDiagram && shouldTriggerLayoutForChangeOn((DDiagram) element);
        }
        return needsLayoutOnCurrentDiagram;
    }

    private boolean shouldTriggerLayoutForChangeOn(DDiagram impactedDDiagram) {
        boolean needsLayoutOnCurrentDiagram = impactedDDiagram == dDiagram;
        if (!needsLayoutOnCurrentDiagram && impactedDDiagram instanceof DSemanticDiagram) {
            needsLayoutOnCurrentDiagram = semanticElement == ((DSemanticDiagram) impactedDDiagram).getTarget();
        }

        return needsLayoutOnCurrentDiagram;
    }
}
