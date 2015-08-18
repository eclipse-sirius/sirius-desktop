/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.DeleteFromDiagramEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.HideSiriusElementEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchBehaviorToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeDeletionEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.RefreshSiriusElementEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPopupBarEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPropertyHandlerEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.BorderItemLocatorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.part.NodePlateProvider;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of {@link IAbstractDiagramNodeEditPart}.
 * 
 * @author ymortier
 */
public final class AbstractDiagramNodeEditPartOperation {

    /**
     * Avoid instantiation.
     */
    private AbstractDiagramNodeEditPartOperation() {

    }

    /**
     * This method is invoked when an event is thrown.
     * 
     * @param self
     *            the edit part.
     * @param notification
     *            the notification.
     */
    public static void handleNotificationEvent(final IAbstractDiagramNodeEditPart self, final Notification notification) {
        if (notification.getEventType() == Notification.SET || notification.getEventType() == Notification.UNSET || notification.getEventType() == Notification.ADD) {
            AbstractDiagramNodeEditPartOperation.safeRefresh(self);
        }
        // To handle REMOVE type as above might have side-effect. Handle it only
        // for DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS feature
        if (notification.getEventType() == Notification.REMOVE && DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS.equals(notification.getFeature())) {
            AbstractDiagramNodeEditPartOperation.safeRefresh(self);
        }
        if (notification.getEventType() == Notification.SET && notification.getFeature() instanceof EAttribute) {
            final EAttribute feature = (EAttribute) notification.getFeature();
            if ("visible".equals(feature.getName())) { //$NON-NLS-1$
                final EditPart parent = self.getParent();
                if (parent != null) {
                    AbstractDiagramNodeEditPartOperation.safeRefresh(parent);
                }
            }
        }
    }

    private static void safeRefresh(final EditPart editPart) {
        if (editPart.getParent() != null) {
            editPart.refresh();
        }
    }

    /**
     * Installs default edit policies.
     * 
     * @param self
     *            the edit part instance
     */
    public static void createDefaultEditPolicies(final IAbstractDiagramNodeEditPart self) {
        self.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
        self.installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, new SiriusPopupBarEditPolicy());
        self.removeEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE);
        self.installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new SiriusGraphicalNodeEditPolicy());
        final CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        compoundEditPolicy.addEditPolicy(new RefreshSiriusElementEditPolicy());
        compoundEditPolicy.addEditPolicy(new HideSiriusElementEditPolicy());
        compoundEditPolicy.addEditPolicy(new NodeDeletionEditPolicy(self.getEditingDomain()));
        compoundEditPolicy.addEditPolicy(new LaunchBehaviorToolEditPolicy());
        compoundEditPolicy.addEditPolicy(new DeleteFromDiagramEditPolicy());
        self.installEditPolicy(EditPolicy.COMPONENT_ROLE, compoundEditPolicy);
        final CompoundEditPolicy compoundEditPolicy2 = new CompoundEditPolicy();
        compoundEditPolicy2.addEditPolicy(new NodeCreationEditPolicy());
        if (self.getEditPolicy(EditPolicy.CONTAINER_ROLE) != null) {
            compoundEditPolicy2.addEditPolicy(self.getEditPolicy(EditPolicy.CONTAINER_ROLE));
            self.removeEditPolicy(EditPolicy.CONTAINER_ROLE);
        }
        self.installEditPolicy(EditPolicy.CONTAINER_ROLE, compoundEditPolicy2);
        self.installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());

        // Enables Font and Style action
        self.removeEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE);
        self.installEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE, new SiriusPropertyHandlerEditPolicy());

    }

    /**
     * Create a {@link IBorderItemLocator} for the specified figure.
     * 
     * @param self
     *            the edit part.
     * @param figure
     *            the figure.
     * @param vpElementBorderItem
     *            the view point element to add on the border.
     * @return a {@link IBorderItemLocator} for the specified figure.
     */
    public static IBorderItemLocator createBorderItemLocator(final IAbstractDiagramNodeEditPart self, final IFigure figure, final DDiagramElement vpElementBorderItem) {
        final EObject semantic = self.resolveSemanticElement();
        if (semantic instanceof DDiagramElement) {
            final DDiagramElement element = (DDiagramElement) semantic;
            final DiagramElementMapping mapping = element.getDiagramElementMapping();

            final Style style = ((DStylizable) semantic).getStyle();
            final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(mapping, style);
            final BorderItemLocatorProvider provider = styleConfiguration.getBorderItemLocatorProvider();
            return provider.getBorderItemLocator(figure, (DDiagramElement) semantic, vpElementBorderItem);
        }
        return null;
    }

    /**
     * Set the children size.
     * 
     * @param self
     *            the IAbstractDiagramNodeEditPart instance
     * @param d
     *            the dimension.
     */
    public static void setChildrenSize(final IAbstractDiagramNodeEditPart self, final Dimension d) {
        final Iterator<EditPart> it = self.getChildren().iterator();
        while (it.hasNext()) {
            final EditPart child = it.next();
            if (child instanceof ShapeNodeEditPart && !AbstractDiagramNodeEditPartOperation.isBordered(child)) {
                ((ShapeNodeEditPart) child).getContentPane().setSize(d.width, d.height);
                ((ShapeNodeEditPart) child).getContentPane().setMinimumSize(d);
                ((ShapeNodeEditPart) child).getContentPane().setMaximumSize(d);
                ((ShapeNodeEditPart) child).getContentPane().setPreferredSize(d);
                ((ShapeNodeEditPart) child).getFigure().setSize(d.width, d.height);
                ((ShapeNodeEditPart) child).getFigure().setMinimumSize(d);
                ((ShapeNodeEditPart) child).getFigure().setMaximumSize(d);
                ((ShapeNodeEditPart) child).getFigure().setPreferredSize(d);
                ((ShapeNodeEditPart) child).getContentPane().setBounds(new Rectangle(((ShapeNodeEditPart) child).getContentPane().getBounds().getLocation(), d));

                // we should not set the layout on a ViewNode2EditPart
                // or we'll get a classcast exception
                self.setLayoutConstraint(child, ((ShapeNodeEditPart) child).getFigure(), new Rectangle(0, 0, d.width, d.height));
            }
            if (child instanceof NodePlateProvider && ((NodePlateProvider) child).getNodePlate() != null) {
                ((NodePlateProvider) child).getNodePlate().setSize(d.width, d.height);
                ((NodePlateProvider) child).getNodePlate().setMaximumSize(d);
                ((NodePlateProvider) child).getNodePlate().setMinimumSize(d);
                ((NodePlateProvider) child).getNodePlate().setPreferredSize(d);
            }
        }
    }

    /**
     * Check if an editPart is a bordered edit part.
     * 
     * @param editPart
     *            the editPart to test
     * @return <code>true</code> if the editPart is a bordered Edit part,
     *         <code>false</code> otherwise
     */
    public static boolean isBordered(final EditPart editPart) {
        return editPart instanceof IDiagramBorderNodeEditPart;
    }

    /**
     * Return the node plate of the edit part.
     * 
     * @param viewNodeEditPart
     *            the edit part
     * @return the node plate of the edit part.
     */
    public static DefaultSizeNodeFigure getNodePlate(final IAbstractDiagramNodeEditPart viewNodeEditPart) {
        IFigure nodePlate = null;
        if (viewNodeEditPart instanceof DNode2EditPart) {
            nodePlate = ((DNode2EditPart) viewNodeEditPart).getNodePlate();
        } else if (viewNodeEditPart instanceof DNode3EditPart) {
            nodePlate = ((DNode3EditPart) viewNodeEditPart).getNodePlate();
        } else if (viewNodeEditPart instanceof DNodeEditPart) {
            nodePlate = ((DNodeEditPart) viewNodeEditPart).getNodePlate();
        } else if (viewNodeEditPart instanceof DNode4EditPart) {
            nodePlate = ((DNode4EditPart) viewNodeEditPart).getNodePlate();
        }
        return (DefaultSizeNodeFigure) nodePlate;
    }

    /**
     * Sets the tooltip of an {@link IAbstractDiagramNodeEditPart} to the
     * specified text.
     * 
     * @param self
     *            the IAbstractDiagramNodeEditPart
     * @param text
     *            the tooltip's text.
     */
    public static void setTooltipText(final IAbstractDiagramNodeEditPart self, final String text) {
        if (!StringUtil.isEmpty(text)) {
            final IFigure tt = self.getFigure().getToolTip();
            if (tt instanceof Label) {
                ((Label) tt).setText(text);
            } else {
                self.getFigure().setToolTip(new Label(text));
            }
        } else {
            self.getFigure().setToolTip(null);
        }
    }

    /**
     * Get the zoom manager.
     * 
     * @param self
     *            the IAbstractDiagramNodeEditPart
     * @return the zoom manager
     */
    public static ZoomManager getZoomManager(final IAbstractDiagramNodeEditPart self) {
        return (ZoomManager) self.getViewer().getProperty(ZoomManager.class.toString());
    }

}
