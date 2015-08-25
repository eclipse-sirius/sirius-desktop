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
package org.eclipse.sirius.diagram.ui.edit.internal.part.listener;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
/**
 * A ResourceSet listener
 * 
 * . to deactivate the visibility updater (safe !autoRefresh mode)
 * 
 * . to refresh of the edit parts on the end of edges whose visibility has
 * changed to make sure node and edges edit parts are properly connected.
 * 
 * @author pcdavid
 */
public class VisibilityPostCommitListener extends ResourceSetListenerImpl {

    private DiagramEditPart diagramEditPart;

    /**
     * Default constructor.
     * 
     * @param diagramEditPart
     *            listened part.
     */
    public VisibilityPostCommitListener(DiagramEditPart diagramEditPart) {
        super();
        this.diagramEditPart = diagramEditPart;
        diagramEditPart.getEditingDomain().addResourceSetListener(this);
    }

    @Override
    public boolean isPrecommitOnly() {
        return false;
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    /**
     * Forces a refresh of the edit parts on the end of edges whose visibility
     * has changed to make sure node and edges edit parts are properly
     * connected.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        disableVisibilityUpdater();

        forceNodeAndEdgeEditPartsRefresh(event);
    }

    private void disableVisibilityUpdater() {
        DDiagram dDiagram = getDDiagram();

        if (dDiagram != null) {
            NotificationUtil.sendNotification(dDiagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.STOP, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
        }
    }

    private void forceNodeAndEdgeEditPartsRefresh(ResourceSetChangeEvent event) {
        final Set<View> changedElements = getElementsWhoseVisibilityChanged(event);
        final Set<IGraphicalEditPart> parts = Sets.newHashSet();
        for (View view : changedElements) {
            Option<IGraphicalEditPart> elementParts = getEditPartsFor(view);
            if (elementParts.some()) {
                parts.add(elementParts.get());
            }
        }
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            public void run() {
                for (DEdgeEditPart edgePart : Iterables.filter(parts, DEdgeEditPart.class)) {
                    Edge edge = (Edge) edgePart.getNotationView();
                    Option<IGraphicalEditPart> sourcePart = getEditPartsFor(edge.getSource());
                    if (sourcePart.some()) {
                        sourcePart.get().refresh();
                    }
                    Option<IGraphicalEditPart> targetPart = getEditPartsFor(edge.getTarget());
                    if (targetPart.some()) {
                        targetPart.get().refresh();
                    }
                }
            }
        });
    }

    private Option<IGraphicalEditPart> getEditPartsFor(View view) {
        Option<IGraphicalEditPart> result = Options.newNone();
        if (diagramEditPart != null && diagramEditPart.getRoot() != null && diagramEditPart.getViewer() != null && diagramEditPart.getViewer().getEditPartRegistry() != null) {
            Map<?, ?> registry = diagramEditPart.getViewer().getEditPartRegistry();
            if (view != null && registry.containsKey(view) && registry.get(view) instanceof IGraphicalEditPart) {
                result = Options.newSome((IGraphicalEditPart) registry.get(view));
            }
        }
        return result;
    }

    private Set<View> getElementsWhoseVisibilityChanged(ResourceSetChangeEvent event) {
        Iterable<Notification> changes = getVisibilityChanges(event);
        Set<View> result = Sets.newHashSet(Iterables.filter(Iterables.transform(changes, new Function<Notification, View>() {
            public View apply(Notification from) {
                if (from.getNotifier() instanceof View) {
                    return (View) from.getNotifier();
                } else {
                    return null;
                }
            }
        }), Predicates.notNull()));
        return result;
    }

    private Iterable<Notification> getVisibilityChanges(ResourceSetChangeEvent event) {
        return Iterables.filter(Iterables.filter(event.getNotifications(), Notification.class), new Predicate<Notification>() {
            public boolean apply(Notification input) {
                return NotationPackage.eINSTANCE.getView_Visible().equals(input.getFeature()) && !input.isTouch() && input.getNewBooleanValue();
            }
        });
    }

    private DDiagram getDDiagram() {
        DDiagram dDiagram = null;
        if (diagramEditPart != null) {
            EObject element = diagramEditPart.getDiagramView().getElement();
            if (element instanceof DDiagram) {
                dDiagram = (DDiagram) element;
            }
        }
        return dDiagram;
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
    }
}
