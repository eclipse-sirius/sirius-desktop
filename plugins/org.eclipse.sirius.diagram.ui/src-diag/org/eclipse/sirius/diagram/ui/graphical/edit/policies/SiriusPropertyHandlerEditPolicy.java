/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PropertyHandlerEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ApplyAppearancePropertiesRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.swt.graphics.RGB;

/**
 * 
 * Edit policy to handle any property change requests and add viewpoint support.
 * 
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.PropertyHandlerEditPolicy
 * 
 * @author mPorhel
 * 
 */
@SuppressWarnings("restriction")
public class SiriusPropertyHandlerEditPolicy extends PropertyHandlerEditPolicy {

    private static final String APPLY_VIEWPOINT_APPEARANCE_PROPERTIES_UNDO_COMMAND_NAME = "Apply viewpoint appearance properties"; //$NON-NLS-1$

    private static final String APPLY_VIEWPOINT_TOOLBAR_PROPERTIES_UNDO_COMMAND_NAME = "Apply viewpoint toolbar properties"; //$NON-NLS-1$

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.PropertyHandlerEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        final Command c = super.getCommand(request);

        Command styleCommand = null;

        if (request.getType().equals(RequestConstants.REQ_PROPERTY_CHANGE)) {
            styleCommand = buildPropertyChangeCommand(request);
        } else if (request instanceof ApplyAppearancePropertiesRequest) {
            styleCommand = buildApplyAppearancePropertiesCommand((ApplyAppearancePropertiesRequest) request);
        }

        if (styleCommand != null) {
            final CompoundCommand cc = new CompoundCommand();
            cc.setDebugLabel("Chained Style Command"); //$NON-NLS-1$
            cc.add(c);
            cc.add(styleCommand);
            return cc;
        } else {
            return c;
        }
    }

    private Command buildApplyAppearancePropertiesCommand(final ApplyAppearancePropertiesRequest request) {
        DStylizable styleReceiver = null;
        Style styleToCopy = null;

        if (getHost() instanceof IGraphicalEditPart) {
            final View targetView = ((IGraphicalEditPart) getHost()).getNotationView();
            if (targetView.getElement() instanceof DStylizable) {
                styleReceiver = (DStylizable) targetView.getElement();
            }
        }

        final View viewToCopy = request.getViewToCopyFrom();
        if (viewToCopy.getElement() instanceof DStylizable) {
            styleToCopy = EcoreUtil.copy(((DStylizable) viewToCopy.getElement()).getStyle());
        }

        ICommand viewStyleCommand = null;

        if (styleReceiver instanceof DEdge && styleToCopy instanceof EdgeStyle) {
            final DEdge edge = (DEdge) styleReceiver;
            final EdgeStyle edgeStyle = (EdgeStyle) styleToCopy;

            viewStyleCommand = new AbstractTransactionalCommand(getEditingDomain(), APPLY_VIEWPOINT_APPEARANCE_PROPERTIES_UNDO_COMMAND_NAME, null) {
                @Override
                protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
                    edge.setOwnedStyle(edgeStyle);
                    return CommandResult.newOKCommandResult();
                }
            };
        } else if (styleReceiver instanceof DNode && styleToCopy instanceof NodeStyle) {
            final DNode node = (DNode) styleReceiver;
            final NodeStyle nodeStyle = (NodeStyle) styleToCopy;

            viewStyleCommand = new AbstractTransactionalCommand(getEditingDomain(), APPLY_VIEWPOINT_APPEARANCE_PROPERTIES_UNDO_COMMAND_NAME, null) {
                @Override
                protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
                    node.setOwnedStyle(nodeStyle);
                    return CommandResult.newOKCommandResult();
                }
            };
        } else if (styleReceiver instanceof DDiagramElementContainer && styleToCopy instanceof ContainerStyle) {
            final DDiagramElementContainer container = (DDiagramElementContainer) styleReceiver;
            final ContainerStyle containerStyle = (ContainerStyle) styleToCopy;

            viewStyleCommand = new AbstractTransactionalCommand(getEditingDomain(), APPLY_VIEWPOINT_APPEARANCE_PROPERTIES_UNDO_COMMAND_NAME, null) {
                @Override
                protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
                    container.setOwnedStyle(containerStyle);
                    return CommandResult.newOKCommandResult();
                }
            };

        }

        if (viewStyleCommand == null) {
            return null;
        }
        return new ICommandProxy(viewStyleCommand);
    }

    private Command buildPropertyChangeCommand(final Request request) {
        Command propertyChangeCommand = null;
        final EditPart ep = getHost();
        if (ep instanceof IGraphicalEditPart) {
            final View view = ((IGraphicalEditPart) ep).getNotationView();
            final ChangePropertyValueRequest cpvr = (ChangePropertyValueRequest) request;

            if (Properties.ID_FILLCOLOR.equals(cpvr.getPropertyID()) || Properties.ID_LINECOLOR.equals(cpvr.getPropertyID()) || Properties.ID_FONTCOLOR.equals(cpvr.getPropertyID())) {
                propertyChangeCommand = buildColorPropertyChangeCommand(view, cpvr, ep);
            }
        }
        return propertyChangeCommand;
    }

    private Command buildColorPropertyChangeCommand(final View view, final ChangePropertyValueRequest cpvr, final EditPart ep) {
        if (view != null && ViewUtil.isPropertySupported(view, cpvr.getPropertyID()) && ep instanceof IDiagramElementEditPart) {
            ICommand colorCommand = new AbstractTransactionalCommand(getEditingDomain(), APPLY_VIEWPOINT_TOOLBAR_PROPERTIES_UNDO_COMMAND_NAME, null) {
                @Override
                protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
                    if (cpvr.getValue() instanceof Integer) {
                        final RGB finalColor = FigureUtilities.integerToRGB((Integer) cpvr.getValue());
                        final UserFixedColor newColor = DescriptionFactory.eINSTANCE.createUserFixedColor();
                        newColor.setName("<anonymous>"); //$NON-NLS-1$
                        newColor.setBlue(finalColor.blue);
                        newColor.setGreen(finalColor.green);
                        newColor.setRed(finalColor.red);

                        IInterpreter interpreter = new EObjectQuery(view).getSession().getInterpreter();
                        new ViewPropertiesSynchronizer().synchronizeDDiagramElementStyleColorProperties(view, newColor, cpvr.getPropertyID(), interpreter);
                    }
                    return CommandResult.newOKCommandResult();
                }

            };
            return new ICommandProxy(colorCommand);
        }
        return null;
    }
}
