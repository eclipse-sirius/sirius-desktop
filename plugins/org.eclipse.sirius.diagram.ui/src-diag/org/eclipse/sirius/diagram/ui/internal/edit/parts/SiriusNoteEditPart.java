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
 *    Felix Dorner <felix.dorner@gmail.com> - Bug 533002
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DescriptionCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.OpenDRepresentationEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SnapToAllDragEditPartsTracker;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.Iterables;

/**
 * A specific NoteEditPart to handle the snapToAllShapes.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusNoteEditPart extends NoteEditPart {

    /**
     * Default constructor.
     * 
     * @param view
     *            the view controlled by this edit part
     */
    public SiriusNoteEditPart(View view) {
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
        if (isRepresentationLink() && !isRepresentationLinkBroken()) {
            DRepresentationDescriptor linkedDescriptor = (DRepresentationDescriptor) getNotationView().getElement();
            if (linkedDescriptor != null) {
                addListenerFilter("DiagramLink_RepresentationNameChange", this, linkedDescriptor); //$NON-NLS-1$
            }
        }

    }

    @Override
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("ShowingMode"); //$NON-NLS-1$
        removeListenerFilter("DiagramLink_RepresentationNameChange"); //$NON-NLS-1$
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

    @Override
    protected NodeFigure createNodeFigure() {
        // we call super to set diagramLinkMode to true but use our own figure to be able to set it with transparency.
        super.createNodeFigure();
        IMapMode mm = getMapMode();
        int insetSize = mm.DPtoLP(5);
        Insets insets = new Insets(insetSize, insetSize, insetSize, mm.DPtoLP(NoteFigure.CLIP_MARGIN_DP));
        NoteFigure noteFigure = new NoteFigure(mm.DPtoLP(100), mm.DPtoLP(56), insets) {
            @Override
            public void paint(Graphics graphics) {
                ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, (View) getModel());
                try {
                    CommonEditPartOperation.setGraphicsTraceabilityId(graphics, () -> resolveSemanticElement());
                    super.paint(graphics);
                    CommonEditPartOperation.setGraphicsTraceabilityId(graphics, null);
                    graphics.restoreState();
                } finally {
                    graphics.popState();
                }
            }
        };
        Object model = getModel();
        if (model != null && model instanceof View) {
            View notationView = (View) model;
            if (notationView != null && (notationView.getEAnnotation(Properties.DIAGRAMLINK_ANNOTATION) != null || notationView.getType() == null || notationView.getType().length() == 0)) {
                noteFigure.setDiagramLinkMode(true);
                insets.right = insetSize; // there is no dangling corner in diagram link, set right margin to be the
                                          // same as left

                // The default size is the minimum.
                noteFigure.setDefaultSize(insetSize, insetSize);
            }
        }
        return noteFigure;
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
        if (notification.getNotifier() == getNotationView().getElement() && feature == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__NAME) {
            refreshDiagramNameCompartmentEditPart();
        } else if (NotationPackage.eINSTANCE.getNode_LayoutConstraint().equals(feature)) {
            refreshBounds();
        }
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // replace the default open policy with one that handles DRepresentationDescriptors
        if (isRepresentationLink()) {
            removeEditPolicy(EditPolicyRoles.OPEN_ROLE);
            installEditPolicy(EditPolicyRoles.OPEN_ROLE, new OpenDRepresentationEditPolicy());
        }
    }

    /**
     * Is this a diagram link or is it a 'simple' note?
     * 
     * @see {@link ViewQuery#isRepresentationLink()}
     * @return true if this is a diagram link, false otherwise
     */
    public boolean isRepresentationLink() {
        boolean result = false;
        View view = getNotationView();
        if (view != null) {
            ViewQuery query = new ViewQuery(view);
            result = query.isRepresentationLink();
        }
        return result;
    }

    /**
     * For a representation link, check if it refers to a deleted representation descriptor. Invocations should be
     * guarded by {@link #isRepresentationLink()}.
     * 
     * @see {@link ViewQuery#isRepresentationLinkBroken()}
     * @return true if the link note view has a dangling target, false otherwise.
     */
    boolean isRepresentationLinkBroken() {
        boolean result = false;
        View view = getNotationView();
        if (view != null) {
            ViewQuery query = new ViewQuery(view);
            result = query.isRepresentationLinkBroken();
        }
        return result;
    }

    /**
     * Refresh the name compartment of this note.
     */
    public void refreshDiagramNameCompartmentEditPart() {
        for (Object ep : getChildren()) {
            IGraphicalEditPart gep = (IGraphicalEditPart) ep;
            if (gep.getNotationView() != null && ViewType.DIAGRAM_NAME.equals(gep.getNotationView().getType())) {
                gep.refresh();
            }
        }
    }
}
