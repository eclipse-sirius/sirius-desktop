/*******************************************************************************
 * Copyright (c) 2015, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DescriptionCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SnapToAllDragEditPartsTracker;

import com.google.common.collect.Iterables;

/**
 * A specific TextEditPart to handle the snapToAllShapes.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusTextEditPart extends TextEditPart {

    /**
     * Default constructor.
     * 
     * @param view
     *            the view controlled by this edit part
     */
    public SiriusTextEditPart(View view) {
        super(view);
    }

    @Override
    public DragTracker getDragTracker(Request request) {
        return new SnapToAllDragEditPartsTracker(this);
    }

    /**
     * Redirect the direct edit request to the {@link DescriptionCompartmentEditPart}.
     * 
     * @param request
     *            the direct edit request
     */
    @Override
    protected void performDirectEditRequest(Request request) {
        Iterable<DescriptionCompartmentEditPart> descriptionCompartmentEditPartsfilter = Iterables.filter(this.getChildren(), DescriptionCompartmentEditPart.class);
        if (Iterables.size(descriptionCompartmentEditPartsfilter) == 1) {
            DescriptionCompartmentEditPart descriptionCompartmentEditPart = Iterables.getOnlyElement(descriptionCompartmentEditPartsfilter);
            descriptionCompartmentEditPart.performRequest(request);
        }
    }

    @Override
    protected List getModelChildren() {
        return ShowingViewUtil.getModelChildren(getModel());
    }

    @Override
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        if (hasNotationView()) {
            ViewQuery viewQuery = new ViewQuery((View) getModel());
            Optional<DDiagram> diagram = viewQuery.getDDiagram();
            if (diagram.isPresent()) {
                addListenerFilter("ShowingMode", this, diagram.get(), DiagramPackage.eINSTANCE.getDDiagram_IsInShowingMode()); //$NON-NLS-1$
            }
        }
    }

    @Override
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("ShowingMode"); //$NON-NLS-1$
    }

    @Override
    protected List getModelSourceConnections() {
        return ShowingViewUtil.getSourceConnectionsConnectingVisibleViews((View) getModel());
    }

    @Override
    protected List getModelTargetConnections() {
        return ShowingViewUtil.getTargetConnectionsConnectingVisibleViews((View) getModel());
    }

    @Override
    protected void setConnectionsVisibility(boolean visibility) {
        ShowingViewUtil.setConnectionsVisibility(this, (View) getModel(), SELECTED_NONE, visibility);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#setVisibility(boolean)
     */
    @Override
    protected void setVisibility(boolean vis) {
        ShowingViewUtil.setVisibility(this, vis, SELECTED_NONE, getFlag(FLAG__AUTO_CONNECTIONS_VISIBILITY));
    }

    @Override
    protected void handleNotificationEvent(Notification notification) {
        super.handleNotificationEvent(notification);
        Object feature = notification.getFeature();
        if (NotationPackage.eINSTANCE.getNode_LayoutConstraint().equals(feature)) {
            refreshBounds();
        }
    }

}
