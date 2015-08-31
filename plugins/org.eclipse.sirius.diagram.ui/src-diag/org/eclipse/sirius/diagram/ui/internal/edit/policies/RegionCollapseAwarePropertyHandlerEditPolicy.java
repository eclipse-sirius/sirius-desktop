/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PropertyHandlerEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;

import com.google.common.collect.Iterables;

/**
 * Specific {@link PropertyHandlerEditPolicy} able to manage collapse of Region:
 * <ul>
 * <li>when the drawer style is marked as collapsed, the expanded GMF size is
 * stored in an {@link AbsoluteBoundsFilter}.</li>
 * <li>when the drawer style is marked as expanded, the GMF size is updated to
 * retrieve its previous collapsed dimension, height or width regarding the
 * parent stack direction.</li>
 * </ul>
 * 
 * @author mporhel
 *
 */
public class RegionCollapseAwarePropertyHandlerEditPolicy extends PropertyHandlerEditPolicy {

    @Override
    public Command getCommand(Request request) {
        Command command = super.getCommand(request);

        if (request instanceof ChangePropertyValueRequest) {
            ChangePropertyValueRequest cpvr = (ChangePropertyValueRequest) request;
            if (Properties.ID_COLLAPSED.equals(cpvr.getPropertyID()) && cpvr.getValue() instanceof Boolean) {
                command = getLayoutConstraintsCommand((Boolean) cpvr.getValue(), command);
            }
        }
        return command;
    }

    private Command getLayoutConstraintsCommand(boolean collapsed, Command initialCommand) {
        if (getHost() instanceof IResizableCompartmentEditPart && getHost().getParent() instanceof AbstractDiagramElementContainerEditPart) {
            AbstractDiagramElementContainerEditPart regionPart = (AbstractDiagramElementContainerEditPart) getHost().getParent();
            View notationView = regionPart.getNotationView();
            if (notationView instanceof Node && ((Node) notationView).getLayoutConstraint() instanceof Size && notationView.getElement() instanceof DDiagramElementContainer) {
                TransactionalEditingDomain editingDomain = getEditingDomain();
                CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, collapsed ? Messages.RegionCollapseAwarePropertyHandlerEditPolicy_collapseRegionCommandLabel
                        : Messages.RegionCollapseAwarePropertyHandlerEditPolicy_expandRegionCommandLabel);
                Command result = new ICommandProxy(ctc);
                if (initialCommand != null) {
                    ctc.add(new CommandProxy(initialCommand));
                }

                Size size = (Size) ((Node) notationView).getLayoutConstraint();
                DDiagramElementContainer ddec = (DDiagramElementContainer) notationView.getElement();
                Iterable<AbsoluteBoundsFilter> boundsFilters = Iterables.filter(ddec.getGraphicalFilters(), AbsoluteBoundsFilter.class);
                AbsoluteBoundsFilter expandedBoundsMarker = Iterables.isEmpty(boundsFilters) ? null : boundsFilters.iterator().next();

                // Update GMF size
                Dimension newGmfSize = new Dimension(size.getWidth(), size.getHeight());
                int parentStackDirection = regionPart.getParentStackDirection();
                if (parentStackDirection == PositionConstants.NORTH_SOUTH) {
                    if (!collapsed) {
                        newGmfSize.setHeight(expandedBoundsMarker == null ? -1 : expandedBoundsMarker.getHeight());
                    } else if (size.getHeight() != -1) {
                        newGmfSize.setHeight(LayoutUtils.COLLAPSED_VERTICAL_REGION_HEIGHT);
                    }
                } else if (parentStackDirection == PositionConstants.EAST_WEST) {
                    if (!collapsed) {
                        newGmfSize.setWidth(expandedBoundsMarker == null ? -1 : expandedBoundsMarker.getWidth());
                    } else if (!isTruncatedLabel(regionPart)) {
                        // Change the GMF width only when label is not truncated
                        // to avoid to have a collapsed Region bigger than the
                        // expanded size (resized by the user).
                        // Do not specify a collapsed width as it will depend on
                        // the label size.
                        newGmfSize.setWidth(-1);
                    }
                }

                SetBoundsCommand setBoundsCommand = new SetBoundsCommand(editingDomain, Messages.RegionCollapseAwarePropertyHandlerEditPolicy_gmfSizeUpdateCommandLabel, new EObjectAdapter(
                        notationView), newGmfSize);
                ctc.add(setBoundsCommand);

                // Remember expanded size: create/update/remove an
                // AbsoluteBoundsFilter marker on the DDiagramElement.
                if (collapsed) {
                    if (expandedBoundsMarker != null) {
                        ctc.add(new GMFCommandWrapper(editingDomain, new SetCommand(editingDomain, expandedBoundsMarker, DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Height(), size.getHeight())));
                        ctc.add(new GMFCommandWrapper(editingDomain, new SetCommand(editingDomain, expandedBoundsMarker, DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Width(), size.getWidth())));
                    } else {
                        expandedBoundsMarker = DiagramFactory.eINSTANCE.createAbsoluteBoundsFilter();
                        expandedBoundsMarker.setHeight(size.getHeight());
                        expandedBoundsMarker.setWidth(size.getWidth());
                        ctc.add(new GMFCommandWrapper(editingDomain, new AddCommand(editingDomain, ddec.getGraphicalFilters(), expandedBoundsMarker)));
                    }
                } else if (expandedBoundsMarker != null) {
                    ctc.add(new GMFCommandWrapper(editingDomain, new RemoveCommand(editingDomain, ddec.getGraphicalFilters(), expandedBoundsMarker)));
                }

                return result;
            }

        }
        return initialCommand;
    }

    private boolean isTruncatedLabel(AbstractDiagramElementContainerEditPart regionPart) {
        SiriusWrapLabel nodeLabel = regionPart.getNodeLabel();
        return nodeLabel != null && (nodeLabel.isTextTruncated() || nodeLabel.getText() != null && !nodeLabel.getText().equals(nodeLabel.getSubStringText()));
    }
}
