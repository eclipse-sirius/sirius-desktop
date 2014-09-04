/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.IdentityCommand;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionEndsCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.SetPropertyCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.TreeGraphicalNodeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.EdgeCreationDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.ReconnectEdgeDescriptionQuery;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.tools.internal.command.builders.EdgeCreationCommandBuilder;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.command.SiriusSetConnectionAnchorsCommand;
import org.eclipse.sirius.diagram.ui.business.internal.command.TreeLayoutSetConnectionAnchorsCommand;
import org.eclipse.sirius.diagram.ui.business.internal.query.DEdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramEdgeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * This class manages the reconnection of an edge.
 * 
 * @author ymortier
 */
public class SiriusGraphicalNodeEditPolicy extends TreeGraphicalNodeEditPolicy {

    /**
     * Constant used to store a {@link EdgeCreationDescription} in a
     * Request.getExtendedData().
     */
    protected static final String GMF_EDGE_CREATION_DESCRIPTION = "edge.creation.description";

    /**
     * Constant used to store the {@link EdgeTarget} source.
     */
    protected static final String GMF_EDGE_TARGET_SOURCE = "edgeTarget.source";

    /**
     * Constant used to store the location in GMF relative coordinates of the
     * click on the {@link EdgeTarget} source.
     */
    protected static final String GMF_EDGE_LOCATION_SOURCE = "edge.location.source";

    /**
     * Constant use to store source terminal.
     */
    protected static final String GMF_EDGE_SOURCE_TERMINAL = "edge.newSourceTerminal";

    /**
     * Extra width edge for this feedback.
     */
    private static final int WIDTH_FEEDBACK = 2;

    /**
     * Figure for highlight figure feedback.
     */
    private RectangleFigure highlightFigure;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectSourceCommand(final ReconnectRequest request) {
        if (request.getConnectionEditPart().getModel() instanceof Edge) {
            final Edge edge = (Edge) request.getConnectionEditPart().getModel();
            if (GMFNotationHelper.isNoteAttachment(edge)) {
                return super.getReconnectSourceCommand(request);
            }
        }
        DEdge edge = null;
        EdgeTarget target = null;
        final ConnectionEditPart connectionEditPart = request.getConnectionEditPart();
        if (connectionEditPart.getModel() instanceof View && ((View) connectionEditPart.getModel()).getElement() instanceof DEdge) {
            edge = (DEdge) ((View) connectionEditPart.getModel()).getElement();
        }
        EdgeTarget source = null;
        if (edge != null) {
            source = edge.getSourceNode();
        }
        if (request.getTarget().getModel() instanceof View && ((View) request.getTarget().getModel()).getElement() instanceof EdgeTarget) {
            target = (EdgeTarget) ((View) request.getTarget().getModel()).getElement();
        }

        Command cmd = UnexecutableCommand.INSTANCE;

        if (source != null && target != null) {
            if (target != source) {
                Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(edge.getActualMapping()).getEdgeMapping();
                final ReconnectEdgeDescription tool = edgeMapping.some() ? getBestTool(edgeMapping.get(), true, source, target, edge) : null;
                if (tool != null) {
                    final CompoundCommand result = new CompoundCommand();
                    result.add(this.getToolCommand(tool, edge, source, target));
                    result.add(getReconnectSourceCommandAfterTool(request));
                    cmd = result;
                }
            } else if (isCenteredEnd(edge, CenteringStyle.SOURCE)) {
                cmd = UnexecutableCommand.INSTANCE;
            } else if (applySpecificTreeLayout(request.getConnectionEditPart())) {
                cmd = getReconnectSourceForTreeLayoutCommand(request);
            } else {
                ConnectionEditPartQuery cepq = new ConnectionEditPartQuery(request.getConnectionEditPart());
                if (cepq.isEdgeWithObliqueRoutingStyle() || cepq.isEdgeWithRectilinearRoutingStyle()) {
                    cmd = getReconnectSourceOrTargetForObliqueOrRectilinearCommand(request, true);
                } else {
                    cmd = super.getReconnectSourceCommand(request);
                }
            }

        }

        return cmd;
    }

    /**
     * Same behavior has super.getReconnectSourceCommand but using a custom
     * SetConnectionAnchorsCommand because modification on reconnected edge are
     * now done in precommit.
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    private Command getReconnectSourceCommandAfterTool(ReconnectRequest request) {
        INodeEditPart node = getConnectableEditPart();
        if (node == null)
            return null;

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        ConnectionAnchor sourceAnchor = node.getSourceConnectionAnchor(request);
        View sourceView = (View) request.getTarget().getModel();
        @SuppressWarnings("unchecked")
        SiriusSetConnectionAnchorsCommand scaCommand = new SiriusSetConnectionAnchorsCommand(editingDomain, DiagramUIMessages.Commands_SetConnectionEndsCommand_Source, sourceView,
                sourceView.getSourceEdges(), ReconnectionKind.RECONNECT_SOURCE_LITERAL);
        scaCommand.setNewSourceTerminal(node.mapConnectionAnchorToTerminal(sourceAnchor));
        CompositeCommand cc = new CompositeCommand(DiagramUIMessages.Commands_SetConnectionEndsCommand_Source);
        cc.compose(scaCommand);
        return new ICommandProxy(scaCommand);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectTargetCommand(final ReconnectRequest request) {
        if (request.getConnectionEditPart().getModel() instanceof Edge) {
            final Edge edge = (Edge) request.getConnectionEditPart().getModel();
            if (GMFNotationHelper.isNoteAttachment(edge)) {
                return super.getReconnectTargetCommand(request);
            }
        }
        DEdge edge = null;
        EdgeTarget target = null;
        final ConnectionEditPart connectionEditPart = request.getConnectionEditPart();
        if (connectionEditPart.getModel() instanceof View && ((View) connectionEditPart.getModel()).getElement() instanceof DEdge) {
            edge = (DEdge) ((View) connectionEditPart.getModel()).getElement();
        }
        EdgeTarget source = null;
        if (edge != null) {
            source = edge.getTargetNode();
        }
        if (request.getTarget().getModel() instanceof View && ((View) request.getTarget().getModel()).getElement() instanceof EdgeTarget) {
            target = (EdgeTarget) ((View) request.getTarget().getModel()).getElement();
        }

        Command cmd = UnexecutableCommand.INSTANCE;
        if (source != null && target != null) {
            if (target != source) {
                Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(edge.getActualMapping()).getEdgeMapping();
                final ReconnectEdgeDescription tool = edgeMapping.some() ? getBestTool(edgeMapping.get(), false, source, target, edge) : null;
                if (tool != null) {
                    final CompoundCommand result = new CompoundCommand();
                    result.add(this.getToolCommand(tool, edge, source, target));
                    result.add(getReconnectTargetCommandAfterTool(request));
                    cmd = result;
                }
            } else if (isCenteredEnd(edge, CenteringStyle.TARGET)) {
                cmd = UnexecutableCommand.INSTANCE;
            } else {
                ConnectionEditPartQuery cepq = new ConnectionEditPartQuery(request.getConnectionEditPart());
                if (cepq.isEdgeWithTreeRoutingStyle() && applySpecificTreeLayout(request.getConnectionEditPart())) {
                    cmd = getReconnectTargetForTreeLayoutCommand(request);
                } else if (cepq.isEdgeWithObliqueRoutingStyle() || cepq.isEdgeWithRectilinearRoutingStyle()) {
                    cmd = getReconnectSourceOrTargetForObliqueOrRectilinearCommand(request, false);
                } else {
                    cmd = super.getReconnectTargetCommand(request);
                }
            }
        }
        return cmd;
    }

    private Command getReconnectSourceOrTargetForObliqueOrRectilinearCommand(ReconnectRequest request, boolean source) {
        INodeEditPart node = getConnectableEditPart();
        INodeEditPart targetEP = getConnectionCompleteEditPart(request);
        if (node == null || targetEP == null)
            return null;

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        ConnectionAnchor targetAnchor = getConnectionTargetAnchor(request);

        // Creation of the command that set the connection end points (source
        // and target)
        SetConnectionEndsCommand sceCommand = new SetConnectionEndsCommand(editingDomain, StringStatics.BLANK);
        sceCommand.setEdgeAdaptor(new EObjectAdapter((EObject) request.getConnectionEditPart().getModel()));
        if (source) {
            sceCommand.setNewSourceAdaptor(targetEP);
        } else {
            sceCommand.setNewTargetAdaptor(targetEP);
        }
        // Creation of the command that set the connection anchors (also source
        // and target)
        SetConnectionAnchorsCommand scaCommand = new SetConnectionAnchorsCommand(editingDomain, StringStatics.BLANK);
        scaCommand.setEdgeAdaptor(new EObjectAdapter((EObject) request.getConnectionEditPart().getModel()));
        if (source) {
            scaCommand.setNewSourceTerminal(targetEP.mapConnectionAnchorToTerminal(targetAnchor));
        } else {
            scaCommand.setNewTargetTerminal(targetEP.mapConnectionAnchorToTerminal(targetAnchor));
        }
        // Both command are composed in a composite command
        CompositeCommand cc = new CompositeCommand(DiagramUIMessages.Commands_SetConnectionEndsCommand_Target);
        cc.compose(sceCommand);
        cc.compose(scaCommand);
        // Set points of Edge as they are graphically
        Connection connection = (Connection) ((GraphicalEditPart) request.getConnectionEditPart()).getFigure();
        Point tempSourceRefPoint = connection.getSourceAnchor().getReferencePoint();
        connection.translateToRelative(tempSourceRefPoint);

        Point tempTargetRefPoint = connection.getTargetAnchor().getReferencePoint();
        connection.translateToRelative(tempTargetRefPoint);

        PointList connectionPointList = connection.getPoints().getCopy();

        // Set the connection bendpoints with a PointList using a command
        SetConnectionBendpointsCommand sbbCommand = new SetConnectionBendpointsCommand(editingDomain);
        sbbCommand.setEdgeAdapter(request.getConnectionEditPart());
        sbbCommand.setNewPointList(connectionPointList, tempSourceRefPoint, tempTargetRefPoint);
        cc.compose(sbbCommand);

        return new ICommandProxy(cc);
    }

    private boolean isCenteredEnd(DEdge edge, CenteringStyle centeringStyle) {
        EdgeStyle edgeStyle = edge.getOwnedStyle();
        if (edgeStyle != null) {
            return edgeStyle.getCentered() == CenteringStyle.BOTH || edgeStyle.getCentered() == centeringStyle;
        }
        return false;
    }

    /**
     * Same behavior has super.getReconnectTargetCommand but using a custom
     * SetConnectionAnchorsCommand because modification on reconnected edge are
     * now done in precommit.
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    @SuppressWarnings("restriction")
    private Command getReconnectTargetCommandAfterTool(ReconnectRequest request) {
        INodeEditPart node = getConnectableEditPart();
        if (node == null || getConnectionCompleteEditPart(request) == null)
            return null;

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        ConnectionAnchor targetAnchor = getConnectionTargetAnchor(request);
        INodeEditPart targetEP = getConnectionCompleteEditPart(request);

        View targetView = (View) targetEP.getModel();
        @SuppressWarnings("unchecked")
        SiriusSetConnectionAnchorsCommand scaCommand = new SiriusSetConnectionAnchorsCommand(editingDomain, DiagramUIMessages.Commands_SetConnectionEndsCommand_Target, targetView,
                targetView.getTargetEdges(), ReconnectionKind.RECONNECT_TARGET_LITERAL);
        scaCommand.setNewTargetTerminal(targetEP.mapConnectionAnchorToTerminal(targetAnchor));
        Command cmd = new ICommandProxy(scaCommand);
        EditPart cep = request.getConnectionEditPart();
        RoutingStyle style = (RoutingStyle) ((View) cep.getModel()).getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        Routing currentRouter = Routing.MANUAL_LITERAL;
        if (style != null) {
            currentRouter = style.getRouting();
        }
        Command cmdRouter = getRoutingAdjustment(request.getConnectionEditPart(), getSemanticHint(request), currentRouter, request.getTarget());
        if (cmdRouter != null) {
            cmd = cmd == null ? cmdRouter : cmd.chain(cmdRouter);
            // reset the bendpoints
            ConnectionAnchor sourceAnchor = node.getSourceConnectionAnchor(request);
            PointList pointList = new PointList();
            pointList.addPoint(sourceAnchor.getLocation(targetAnchor.getReferencePoint()));
            pointList.addPoint(targetAnchor.getLocation(sourceAnchor.getReferencePoint()));

            SetConnectionBendpointsCommand sbbCommand = new SetConnectionBendpointsCommand(editingDomain);
            sbbCommand.setEdgeAdapter(request.getConnectionEditPart());
            sbbCommand.setNewPointList(pointList, sourceAnchor.getReferencePoint(), targetAnchor.getReferencePoint());
            Command cmdBP = new ICommandProxy(sbbCommand);
            if (cmdBP != null) {
                cmd = cmd == null ? cmdBP : cmd.chain(cmdBP);
            }
        }
        return cmd;
    }

    private ReconnectEdgeDescription getBestTool(final EdgeMapping mapping, final boolean source, final EdgeTarget oldTarget, final EdgeTarget newTarget, final DEdge edge) {
        final List<ReconnectEdgeDescription> candidateTool = new ArrayList<ReconnectEdgeDescription>(mapping.getReconnections());

        ReconnectEdgeDescription bestTool = null;
        EObject semanticSource = null;
        EObject semanticTarget = null;
        EObject semanticElement = null;

        if (oldTarget instanceof DSemanticDecorator) {
            semanticSource = ((DSemanticDecorator) oldTarget).getTarget();
        }
        if (newTarget instanceof DSemanticDecorator) {
            semanticTarget = ((DSemanticDecorator) newTarget).getTarget();
        }
        semanticElement = ((DSemanticDecorator) edge).getTarget();

        // get the tool that applies on source or target.
        selectReconnectionToolCandidates(candidateTool, source);

        if (!candidateTool.isEmpty()) {
            Iterator<ReconnectEdgeDescription> toolIterator = candidateTool.iterator();
            // Check precondtion
            while (toolIterator.hasNext()) {
                final ReconnectEdgeDescription myTool = toolIterator.next();
                final String precondition = myTool.getPrecondition();
                if (precondition != null && !StringUtil.isEmpty(precondition)) {

                    final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticElement);

                    interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, edge.getParentDiagram());
                    interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, semanticSource);
                    interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, oldTarget);
                    interpreter.setVariable(IInterpreterSiriusVariables.TARGET, semanticTarget);
                    interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, newTarget);
                    interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, semanticElement);

                    final boolean preconditionOK = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semanticElement, myTool,
                            ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
                    if (!preconditionOK) {
                        toolIterator.remove();
                    }

                    interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW);
                }
            }
            // Check that the mapping of the new end is in the
            // authorized list.
            toolIterator = candidateTool.iterator();
            while (toolIterator.hasNext()) {
                final ReconnectEdgeDescription myTool = toolIterator.next();
                if (!new ReconnectEdgeDescriptionQuery(myTool).isEndAuthorized(source, newTarget)) {
                    toolIterator.remove();
                }
            }

        }
        if (!candidateTool.isEmpty()) {
            bestTool = candidateTool.get(0);
        }

        return bestTool;

    }

    private void selectReconnectionToolCandidates(final List<ReconnectEdgeDescription> candidateTool, final boolean source) {
        final Iterator<ReconnectEdgeDescription> iterTools = candidateTool.iterator();
        while (iterTools.hasNext()) {
            final ReconnectEdgeDescription currentTool = iterTools.next();
            if ((source && currentTool.getReconnectionKind() == ReconnectionKind.RECONNECT_TARGET_LITERAL)
                    || (!source && currentTool.getReconnectionKind() == ReconnectionKind.RECONNECT_SOURCE_LITERAL)) {
                iterTools.remove();
            }
        }
    }

    private Command getToolCommand(final ReconnectEdgeDescription tool, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor != null) {
            final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
            final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
            final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(source);
            final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(domain);
            return new ICommandProxy(new GMFCommandWrapper(domain, emfCommandFactory.buildReconnectEdgeCommandFromTool(tool, edge, source, target)));
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        Command connectionCreateCommand = null;
        if (request instanceof CreateConnectionViewRequest) {
            connectionCreateCommand = super.getConnectionCreateCommand(request);
        } else {

            @SuppressWarnings("unchecked")
            Map<Object, Object> extendedData = request.getExtendedData();

            ConnectionAnchor sourceAnchor = getConnectableEditPart().getSourceConnectionAnchor(request);
            String newSourceTerminal = getConnectableEditPart().mapConnectionAnchorToTerminal(sourceAnchor);

            Point sourceLocation = getConvertedLocation(request);

            extendedData.put(GMF_EDGE_LOCATION_SOURCE, sourceLocation.getCopy());
            extendedData.put(GMF_EDGE_SOURCE_TERMINAL, newSourceTerminal);

            org.eclipse.gef.GraphicalEditPart graphicalEditPart = (org.eclipse.gef.GraphicalEditPart) this.getHost();
            DSemanticDecorator decorateSemanticElement = null;
            if (graphicalEditPart.getModel() instanceof View) {
                View view = (View) graphicalEditPart.getModel();
                if (view.getElement() instanceof DSemanticDecorator) {
                    decorateSemanticElement = (DSemanticDecorator) view.getElement();
                }
            }
            if (decorateSemanticElement instanceof EdgeTarget && request.getNewObject() instanceof AbstractToolDescription) {
                AbstractToolDescription abstractToolDescription = (AbstractToolDescription) request.getNewObject();
                if (abstractToolDescription instanceof EdgeCreationDescription) {
                    EdgeCreationDescription edgeCreationDescription = (EdgeCreationDescription) abstractToolDescription;

                    boolean canCreate = new EdgeCreationDescriptionQuery(edgeCreationDescription).isValidAsSourceElement((DMappingBased) decorateSemanticElement);
                    canCreate = canCreate && new EdgeCreationCommandBuilder(edgeCreationDescription, (EdgeTarget) decorateSemanticElement, null).checkStartPrecondition();
                    if (canCreate) {
                        extendedData.put(GMF_EDGE_CREATION_DESCRIPTION, edgeCreationDescription);
                        extendedData.put(GMF_EDGE_TARGET_SOURCE, decorateSemanticElement);
                        connectionCreateCommand = new ICommandProxy(IdentityCommand.INSTANCE);
                    }
                }
            }
        }
        if (connectionCreateCommand == null) {
            connectionCreateCommand = super.getConnectionCreateCommand(request);
        }
        return connectionCreateCommand;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.TreeGraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getConnectionCompleteCommand(final CreateConnectionRequest request) {
        Command connectionCompleteCommand = null;

        if (new RequestQuery(request).isNoteAttachmentCreationRequest()) {
            connectionCompleteCommand = super.getConnectionCompleteCommand(request);
        } else {
            connectionCompleteCommand = buildSiriusConnectionCreationCmd(request);
        }
        return connectionCompleteCommand;
    }

    private Command buildSiriusConnectionCreationCmd(CreateConnectionRequest request) {
        Command viewpointConnectionCreationCmd = null;
        INodeEditPart targetEP = getConnectionCompleteEditPart(request);
        if (targetEP != null) {

            INodeEditPart sourceEditPart = (INodeEditPart) request.getSourceEditPart();

            // Location relative to the source: Position where the user
            // clicked, but snap to grid if this feature is enabled
            Point sourceLocation = getEdgeLocationSource(request);
            // Location relative to the target: Position where the user
            // clicked, but snap to grid if this feature is enabled
            Point targetLocation = getConvertedLocation(request);

            EdgeLayoutData edgeLayoutData;
            if (GraphicalHelper.isSnapToGridEnabled(sourceEditPart)) {
                edgeLayoutData = getEdgeLayoutDataWithSnapToGrid(request, sourceEditPart, targetEP, sourceLocation, targetLocation);
            } else {
                edgeLayoutData = getEdgeLayoutData(request, sourceEditPart, targetEP, sourceLocation, targetLocation);
            }

            DSemanticDecorator decorateSemanticElement = null;
            if (this.getHost().getModel() instanceof View) {
                View view = (View) this.getHost().getModel();
                if (view.getElement() instanceof DSemanticDecorator) {
                    decorateSemanticElement = (DSemanticDecorator) view.getElement();
                }
            }
            if (decorateSemanticElement instanceof EdgeTarget && request.getExtendedData().get(GMF_EDGE_CREATION_DESCRIPTION) instanceof EdgeCreationDescription) {
                EdgeTarget source = (EdgeTarget) request.getExtendedData().get(GMF_EDGE_TARGET_SOURCE);
                if (source != null) {
                    EdgeTarget target = (EdgeTarget) decorateSemanticElement;
                    EdgeCreationDescription edgeCreationDescription = (EdgeCreationDescription) request.getExtendedData().get(GMF_EDGE_CREATION_DESCRIPTION);
                    boolean canCreate = new EdgeCreationDescriptionQuery(edgeCreationDescription).canBeAppliedOn((DMappingBased) source, (DMappingBased) target);
                    if (canCreate) {
                        DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                        IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                        viewpointConnectionCreationCmd = buildCreateEdgeCommand(request, source, target, edgeCreationDescription, cmdFactoryProvider, edgeLayoutData);
                    }
                }
            }
        }
        return viewpointConnectionCreationCmd;
    }

    /**
     * Create the edge layout data that will be used later after the refresh.
     * 
     * @param request
     *            The original creation request
     * @param sourceEditPart
     *            the {@link EditPart} that the source end of the connection
     *            should be connected to.
     * @param targetEditPart
     *            the {@link EditPart} that the target end of the connection
     *            should be connected to.
     * @param sourceLocation
     *            the location of the first click (relative to the source edit
     *            part)
     * @param targetLocation
     *            the location of the second click (relative to the target edit
     *            part)
     * @return The edge layout data corresponding to the creation request.
     */
    protected EdgeLayoutData getEdgeLayoutData(CreateConnectionRequest request, INodeEditPart sourceEditPart, INodeEditPart targetEditPart, Point sourceLocation, Point targetLocation) {
        String newSourceTerminal = getEdgeTerminalSource(request);
        ConnectionAnchor sourceAnchor = sourceEditPart.mapTerminalToConnectionAnchor(newSourceTerminal);

        ConnectionAnchor targetAnchor = targetEditPart.getTargetConnectionAnchor(request);
        String newTargetTerminal = targetEditPart.mapConnectionAnchorToTerminal(targetAnchor);

        Point sourceRefPoint = sourceAnchor.getReferencePoint();
        Point targetRefPoint = targetAnchor.getReferencePoint();

        PointList pointList = new PointList();
        if (request.getLocation() == null) {
            pointList.addPoint(sourceAnchor.getLocation(targetAnchor.getReferencePoint()));
            pointList.addPoint(targetAnchor.getLocation(sourceAnchor.getReferencePoint()));
        } else {
            pointList.addPoint(sourceAnchor.getLocation(request.getLocation()));
            pointList.addPoint(targetAnchor.getLocation(request.getLocation()));
        }

        final LayoutData sourceLayoutData = new RootLayoutData(sourceEditPart, sourceLocation.getCopy(), null);
        final LayoutData targetLayoutData = new RootLayoutData(targetEditPart, targetLocation.getCopy(), null);
        EdgeLayoutData edgeLayoutData = new EdgeLayoutData(sourceLayoutData, targetLayoutData);

        edgeLayoutData.setSourceTerminal("" + newSourceTerminal);
        edgeLayoutData.setTargetTerminal("" + newTargetTerminal);

        edgeLayoutData.setPointList(pointList.getCopy());
        edgeLayoutData.setSourceRefPoint(sourceRefPoint.getCopy());
        edgeLayoutData.setTargetRefPoint(targetRefPoint.getCopy());
        return edgeLayoutData;
    }

    /**
     * Create the edge layout data that will be used later after the refresh.
     * According to
     * {@link #getEdgeLayoutData(CreateConnectionRequest, INodeEditPart, INodeEditPart, Point, Point)}
     * this method ensures that the first point and the last point of the edge
     * will be snap to the grid (at least one of there coordinates. The other is
     * constrained by the side of the source (or the target).<BR>
     * This is not possible to do it earlier (in feedback for example) because
     * we should know source and target data to compute the new source and
     * target location.
     * 
     * @param request
     *            The original creation request
     * @param sourceEditPart
     *            the {@link EditPart} that the source end of the connection
     *            should be connected to.
     * @param targetEditPart
     *            the {@link EditPart} that the target end of the connection
     *            should be connected to.
     * @param sourceLocation
     *            the location of the first click (relative to the source edit
     *            part)
     * @param targetLocation
     *            the location of the second click (relative to the target edit
     *            part)
     * @return The edge layout data corresponding to the creation request and to
     *         the snapToGrid state.
     */
    protected EdgeLayoutData getEdgeLayoutDataWithSnapToGrid(CreateConnectionRequest request, INodeEditPart sourceEditPart, INodeEditPart targetEditPart, Point sourceLocation, Point targetLocation) {
        // Get the absolute source and target location but in 100% to facilitate
        // the computing
        Rectangle absoluteSourceBoundsIn100Percent = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceEditPart);
        Point absoluteSourceLocationIn100Percent = sourceLocation.getTranslated(absoluteSourceBoundsIn100Percent.getTopLeft());
        Rectangle absoluteTargetBoundsIn100Percent = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetEditPart);
        Point absoluteTargetLocationIn100Percent = targetLocation.getTranslated(absoluteTargetBoundsIn100Percent.getTopLeft());

        // Compute intersection between the line (source location<-->target
        // location) and the source node
        Option<Point> intersectionSourcePoint = GraphicalHelper.getIntersection(absoluteSourceLocationIn100Percent, absoluteTargetLocationIn100Percent, (IGraphicalEditPart) sourceEditPart, false);
        // Compute intersection between the line (source location<-->target
        // location) and the target node
        Option<Point> intersectionTargetPoint = GraphicalHelper.getIntersection(absoluteSourceLocationIn100Percent, absoluteTargetLocationIn100Percent, (IGraphicalEditPart) targetEditPart, true);
        // Compute the snap source location and the snap target location
        Point absoluteSourceLocationSnapIn100Percent;
        Point absoluteTargetLocationSnapIn100Percent;
        if (intersectionSourcePoint.some() && intersectionTargetPoint.some()) {
            absoluteSourceLocationSnapIn100Percent = snapLocationToGridAndToParentBorder(absoluteSourceLocationIn100Percent, absoluteSourceBoundsIn100Percent, intersectionSourcePoint.get());
            absoluteTargetLocationSnapIn100Percent = snapLocationToGridAndToParentBorder(absoluteTargetLocationIn100Percent, absoluteTargetBoundsIn100Percent, intersectionTargetPoint.get());
        } else {
            // There is probably a case not handle, use the default layout data
            return getEdgeLayoutData(request, sourceEditPart, targetEditPart, sourceLocation, targetLocation);
        }

        // Make snap source point relative to the source edit part
        Point sourceLocationSnapIn100Percent = getTranslatedToRelative(absoluteSourceLocationSnapIn100Percent, absoluteSourceBoundsIn100Percent);
        final LayoutData sourceLayoutData = new RootLayoutData(sourceEditPart, sourceLocationSnapIn100Percent, null);
        // Make snap target point relative to the source edit part
        Point targetLocationSnapIn100Percent = getTranslatedToRelative(absoluteTargetLocationSnapIn100Percent, absoluteTargetBoundsIn100Percent);
        final LayoutData targetLayoutData = new RootLayoutData(targetEditPart, targetLocationSnapIn100Percent, null);
        EdgeLayoutData edgeLayoutData = new EdgeLayoutData(sourceLayoutData, targetLayoutData);
        // Compute the new source terminal anchor
        PrecisionPoint sourceTerminalPosition = new PrecisionPoint((double) sourceLocationSnapIn100Percent.x / absoluteSourceBoundsIn100Percent.width, (double) sourceLocationSnapIn100Percent.y
                / absoluteSourceBoundsIn100Percent.height);
        String sourceTerminal = new SlidableAnchor(null, sourceTerminalPosition).getTerminal();
        edgeLayoutData.setSourceTerminal("" + sourceTerminal);
        // Compute the new target terminal anchor
        PrecisionPoint targetTerminalPosition = new PrecisionPoint((double) targetLocationSnapIn100Percent.x / absoluteTargetBoundsIn100Percent.width, (double) targetLocationSnapIn100Percent.y
                / absoluteTargetBoundsIn100Percent.height);
        String targetTerminal = new SlidableAnchor(null, targetTerminalPosition).getTerminal();
        edgeLayoutData.setTargetTerminal("" + targetTerminal);
        // Applied the zoom of the current diagram to set the pointList, the
        // source reference point and the target reference point.
        PrecisionPoint absoluteSourceLocationSnap = new PrecisionPoint(absoluteSourceLocationSnapIn100Percent);
        GraphicalHelper.applyInverseZoomOnPoint((IGraphicalEditPart) sourceEditPart, absoluteSourceLocationSnap);
        PrecisionPoint absoluteTargteLoactionSnap = new PrecisionPoint(absoluteTargetLocationSnapIn100Percent);
        GraphicalHelper.applyInverseZoomOnPoint((IGraphicalEditPart) targetEditPart, absoluteTargteLoactionSnap);

        edgeLayoutData.setSourceRefPoint(absoluteSourceLocationSnap);
        edgeLayoutData.setTargetRefPoint(absoluteTargteLoactionSnap);

        PointList pointList = new PointList();
        pointList.addPoint(absoluteSourceLocationSnap.getCopy());
        pointList.addPoint(absoluteTargteLoactionSnap.getCopy());
        edgeLayoutData.setPointList(pointList.getCopy());

        return edgeLayoutData;
    }

    /**
     * * @param absoluteLocation The location in absolute coordinates (and in
     * 100%)
     * 
     * @param absoluteParentBounds
     *            The parent bounds in absolute coordinates (and in 100%)
     * @param intersectionPoint
     *            The intersection location in absolute coordinates (and in
     *            100%)
     * @return
     */
    private Point snapLocationToGridAndToParentBorder(Point absoluteLocation, Rectangle absoluteParentBounds, Point intersectionPoint) {
        Point absoluteSourceLocationSnapIn100Percent;
        if (intersectionPoint.x == absoluteParentBounds.x || intersectionPoint.x == (absoluteParentBounds.x + absoluteParentBounds.width)) {
            int yCoordinate = absoluteLocation.y;
            // If y coordinate of absoluteLocation is outside the parent
            // (possible if the snapToGrid "has attached" the location outside),
            // we use the nearer parent side has coordinate.
            if (absoluteParentBounds.y > yCoordinate) {
                yCoordinate = absoluteParentBounds.y;
            } else if (yCoordinate > (absoluteParentBounds.y + absoluteParentBounds.height)) {
                yCoordinate = absoluteParentBounds.y + absoluteParentBounds.height;
            }
            absoluteSourceLocationSnapIn100Percent = new Point(intersectionPoint.x, yCoordinate);
        } else {
            int xCoordinate = absoluteLocation.x;
            // If x coordinate of absoluteLocation is outside the parent
            // (possible if the snapToGrid "has attached" the location outside),
            // we use the nearer parent side has coordinate.
            if (absoluteParentBounds.x > xCoordinate) {
                xCoordinate = absoluteParentBounds.x;
            } else if (xCoordinate > (absoluteParentBounds.x + absoluteParentBounds.width)) {
                xCoordinate = absoluteParentBounds.x + absoluteParentBounds.width;
            }
            absoluteSourceLocationSnapIn100Percent = new Point(xCoordinate, intersectionPoint.y);
        }
        return absoluteSourceLocationSnapIn100Percent;
    }

    /**
     * Get a new location point relative to the parent.
     * 
     * @param absoluteLocation
     *            The location in absolute coordinates (and in 100%)
     * @param absoluteParentBounds
     *            The parent bounds in absolute coordinates (and in 100%)
     * @return The location relative to the parent
     */
    private Point getTranslatedToRelative(Point absoluteLocation, Rectangle absoluteParentBounds) {
        return new Point(absoluteLocation.x - absoluteParentBounds.x, absoluteLocation.y - absoluteParentBounds.y);
    }

    private Point getConvertedLocation(final CreateRequest request) {
        //
        // Retrieves the location.
        Point location = request.getLocation().getCopy();
        final Point realLocation;
        if (location != null && getHost() instanceof GraphicalEditPart) {
            final IFigure fig = ((GraphicalEditPart) getHost()).getFigure();
            fig.translateToRelative(location);
            final Point containerLocation = fig.getBounds().getLocation();
            location = new Point(location.x - containerLocation.x, location.y - containerLocation.y);
            if (fig instanceof ResizableCompartmentFigure) {
                final Point scrollOffset = ((ResizableCompartmentFigure) fig).getScrollPane().getViewport().getViewLocation();
                realLocation = new Point(location.x + scrollOffset.x, location.y + scrollOffset.y);
            } else {
                realLocation = location;
            }

        } else {
            realLocation = location;
        }
        return realLocation;
    }

    private String getEdgeTerminalSource(CreateConnectionRequest request) {
        // By default take the source terminal put in request.getExtendedData()
        // by the getConnectionCreateCommand()
        String edgeTerminalSource = (String) request.getExtendedData().get(GMF_EDGE_SOURCE_TERMINAL);
        if (edgeTerminalSource == null) {
            // else take the one set in SetConnectionAnchorsCommand by the GMF
            // GraphicalNodeEditPolicy
            Command startCommand = request.getStartCommand();
            if (startCommand instanceof ICommandProxy) {
                ICommandProxy commandProxy = (ICommandProxy) startCommand;
                if (commandProxy.getICommand() instanceof CompositeCommand) {
                    CompositeCommand compositeCommand = (CompositeCommand) commandProxy.getICommand();
                    Iterator<?> iterator = compositeCommand.iterator();
                    while (iterator.hasNext()) {
                        Object cmd = iterator.next();
                        if (cmd instanceof SetConnectionAnchorsCommand) {
                            SetConnectionAnchorsCommand setConnectionAnchorsCommand = (SetConnectionAnchorsCommand) cmd;
                            edgeTerminalSource = setConnectionAnchorsCommand.getNewSourceTerminal();
                        }
                    }
                }
            }
        }
        return edgeTerminalSource;
    }

    private Point getEdgeLocationSource(CreateConnectionRequest request) {
        // By default take the location of the first click of the edge creation
        // put in request.getExtendedData() by the getConnectionCreateCommand()
        Point edgeLocationSource = (Point) request.getExtendedData().get(GMF_EDGE_LOCATION_SOURCE);
        return edgeLocationSource;
    }

    /**
     * . @ param request .
     * 
     * @param source
     *            .
     * @param target
     *            .
     * @param edgeCreationDescription
     *            .
     * @param cmdFactoryProvider
     *            .
     * @return .
     * 
     *         {@inheritDoc}
     */
    protected Command buildCreateEdgeCommand(final CreateConnectionRequest request, EdgeTarget source, EdgeTarget target, EdgeCreationDescription edgeCreationDescription,
            IDiagramCommandFactoryProvider cmdFactoryProvider, EdgeLayoutData edgeLayoutData) {
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(source);
        CompoundCommand result = new CompoundCommand(edgeCreationDescription.getName());
        // Store location hints so that the new view can be put as the proper
        // location after the refresh.
        addStoreLayoutDataCommand(result, edgeLayoutData);
        // Create the actual edge
        org.eclipse.emf.common.command.Command emfCommand = cmdFactoryProvider.getCommandFactory(domain).buildCreateEdgeCommandFromTool(source, target, edgeCreationDescription);
        result.add(new ICommandProxy(new GMFCommandWrapper(domain, emfCommand)));
        return result;
    }

    /**
     * .
     * 
     * @param result
     *            The compound command
     * @param edgeLayoutData
     *            The layout data to add to the SiriusLayoutDataManager
     */
    protected void addStoreLayoutDataCommand(CompoundCommand result, final EdgeLayoutData edgeLayoutData) {
        result.add(new Command() {
            @Override
            public void execute() {
                SiriusLayoutDataManager.INSTANCE.addData(edgeLayoutData);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getRoutingAdjustment(IAdaptable connection, String connectionHint, Routing currentRouterType, EditPart target) {
        Command cmd = null;
        if (connectionHint == null || target == null || target.getModel() == null || ((View) target.getModel()).getElement() == null) {
            return null;
        }
        // check if router needs to change type due to reorient.
        Routing newRouterType = null;
        if (connection instanceof IDiagramEdgeEditPart) {
            DDiagramElement element = ((IDiagramEdgeEditPart) connection).resolveDiagramElement();
            if (element instanceof DEdge) {
                DEdge dEdge = (DEdge) element;
                newRouterType = new DEdgeQuery(dEdge).getRouting();
                if (newRouterType != null && !currentRouterType.equals(newRouterType)) {
                    // add commands for line routing. Convert the new connection
                    // and also the targeted connection.
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
                    ICommand spc = new SetPropertyCommand(domain, connection, Properties.ID_ROUTING, StringStatics.BLANK, newRouterType);
                    Command cmdRouter = new ICommandProxy(spc);
                    if (cmdRouter != null) {
                        cmd = cmdRouter;
                    }
                }
            }
        } else {
            cmd = super.getRoutingAdjustment(connection, connectionHint, currentRouterType, target);
        }
        return cmd;
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to be able to slide the target bendpoint of an edge when
     * targeting another edge. This is the same code as in the super super class
     * GraphicalNodeEditPolicy.
     */
    @Override
    protected ConnectionAnchor getConnectionTargetAnchor(Request request) {
        if (request instanceof ReconnectRequest) {
            ReconnectRequest reconnectRequest = (ReconnectRequest) request;
            if (reconnectRequest.getTarget() instanceof DEdgeEditPart && reconnectRequest.getConnectionEditPart() instanceof DEdgeEditPart) {
                INodeEditPart node = getConnectableEditPart();
                if (node != null)
                    return node.getTargetConnectionAnchor(request);
            }
        }
        return super.getConnectionTargetAnchor(request);
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to be able to slide the target bendpoint of an edge when
     * targeting another edge. This is the same code as in the super super class
     * GraphicalNodeEditPolicy.
     */
    @Override
    protected INodeEditPart getConnectionCompleteEditPart(Request request) {
        if (request instanceof ReconnectRequest) {
            ReconnectRequest reconnectRequest = (ReconnectRequest) request;
            if (reconnectRequest.getTarget() instanceof DEdgeEditPart && reconnectRequest.getConnectionEditPart() instanceof DEdgeEditPart) {
                if (getHost() instanceof INodeEditPart) {
                    return (INodeEditPart) getHost();
                }
            }
        }
        return super.getConnectionCompleteEditPart(request);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#showTargetConnectionFeedback(org.eclipse.gef.requests.DropRequest)
     */
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        removeHighlight();
        addHighlight();
    }

    /**
     * Add a highlight feedback figure on element reconnect. Change too the edge
     * (highlight blue) if there is a reconnect on edge.
     */
    private void addHighlight() {
        Rectangle bounds = getHostFigure().getBounds().getCopy();
        getHostFigure().getParent().translateToAbsolute(bounds);
        getFeedbackLayer().translateToRelative(bounds);

        if (getHostFigure() instanceof ViewEdgeFigure) {

            if (getHostFigure() != null && Display.getCurrent() != null) {
                getHostFigure().setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION));
                ((ViewEdgeFigure) getHostFigure()).setLineWidth(DiagramEdgeEditPartOperation.getLineWidth((IDiagramEdgeEditPart) getHost()) + WIDTH_FEEDBACK);
                for (final Object child : getHostFigure().getChildren()) {
                    if (child instanceof PolylineDecoration) {
                        final PolylineDecoration decoration = (PolylineDecoration) child;
                        decoration.setLineWidth(DiagramEdgeEditPartOperation.getLineWidth((IDiagramEdgeEditPart) getHost()) + WIDTH_FEEDBACK);
                    }
                }
            }
        } else {

            highlightFigure = new RectangleFigure() {
                public void paint(Graphics graphics) {
                    graphics.setAlpha(128);
                    super.paint(graphics);
                }
            };
            highlightFigure.setBounds(bounds);
            highlightFigure.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION));
            addFeedback(highlightFigure);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#eraseTargetConnectionFeedback(org.eclipse.gef.requests.DropRequest)
     */
    @Override
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        removeHighlight();
    }

    /**
     * Remove hightLight Figure.
     */
    private void removeHighlight() {
        if (highlightFigure != null) {
            removeFeedback(highlightFigure);
            highlightFigure = null;
        }
        if (getHostFigure() instanceof ViewEdgeFigure) {
            ((IDiagramEdgeEditPart) getHost()).refreshForegroundColor();
            ((IDiagramEdgeEditPart) getHost()).refreshLineStyle();
        }
    }

    /**
     * Check if this connectionEditPart has been described in the VSM with in a
     * diagram with orderedTreeLayout or with compositeLayout and that it does
     * not have another edge as extremity.
     * 
     * @param connectionEditPart
     *            the edit part to check
     * @return true if a specific tree layout must be apply (to compute GMF
     *         constraints according to draw2d), false otherwise
     */
    private boolean applySpecificTreeLayout(ConnectionEditPart connectionEditPart) {
        boolean isLayoutComponent = false;
        if (!isSourceOrTargetIsEdge(connectionEditPart)) {
            Diagram diagram = getDiagram(connectionEditPart);
            if (diagram != null && diagram.getElement() instanceof DSemanticDiagram) {
                DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) diagram.getElement();
                Layout layout = dSemanticDiagram.getDescription().getLayout();
                isLayoutComponent = isOrderedTreeLayoutOrCompositeLayout(layout);
            }
        }
        return isLayoutComponent;
    }

    /**
     * Check if the source or the target of this connection is another
     * connection.
     * 
     * @param connectionEditPart
     *            the edit part to check
     * @return true if the source or the target of this connection is another
     *         connection, false otherwise.
     */
    private boolean isSourceOrTargetIsEdge(ConnectionEditPart connectionEditPart) {
        return connectionEditPart.getSource() instanceof ConnectionEditPart || connectionEditPart.getTarget() instanceof ConnectionEditPart;
    }

    private Diagram getDiagram(ConnectionEditPart connectionEditPart) {
        Diagram diagram = null;
        if (connectionEditPart.getParent() instanceof DiagramRootEditPart) {
            DiagramRootEditPart diagramRootEditPart = (DiagramRootEditPart) connectionEditPart.getParent();
            if (diagramRootEditPart.getChildren().get(0) instanceof DiagramEditPart) {
                DiagramEditPart diagramEditPart = (DiagramEditPart) diagramRootEditPart.getChildren().get(0);
                if (diagramEditPart.getModel() instanceof Diagram) {
                    diagram = (Diagram) diagramEditPart.getModel();
                }
            }
        }
        return diagram;
    }

    /**
     * Check if this layout corresponds to tree so code must be call to modify
     * GMF edges according to draw2d points.
     * 
     * @param layout
     *            The layout to check
     * @return true if this layout is an OrderedTreeLayout or a CompositeLayout,
     *         false otherwise
     */
    private boolean isOrderedTreeLayoutOrCompositeLayout(Layout layout) {
        boolean isLayout = false;
        if (layout instanceof OrderedTreeLayout || layout instanceof CompositeLayout) {
            if (layout instanceof CompositeLayout) {
                // This code is commented because left to right run not
                // correctly see ticket.
                // CompositeLayout compositeLayout = (CompositeLayout) layout;
                // if
                // (!LayoutDirection.LEFT_TO_RIGHT.getLiteral().equals(compositeLayout.getDirection().getName()))
                // {
                isLayout = true;
                // }
            } else {
                isLayout = true;
            }
        }
        return isLayout;
    }

    /**
     * Launch a specific command ({@link TreeLayoutSetConnectionAnchorsCommand}
     * instead of the classical {@link SetConnectionAnchorsCommand}) to handle
     * with tree layout and setting correctly all the anchors of the edge of the
     * same tree.
     * 
     * @param request
     *            The ReconnectRequest
     * @return a Command
     */
    private Command getReconnectTargetForTreeLayoutCommand(ReconnectRequest request) {
        INodeEditPart node = getConnectableEditPart();
        Command cmd = null;
        if (node != null) {
            TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

            ConnectionAnchor targetAnchor = getConnectionTargetAnchor(request);
            INodeEditPart targetEP = getConnectionCompleteEditPart(request);
            if (targetEP != null) {
                SetConnectionEndsCommand sceCommand = new SetConnectionEndsCommand(editingDomain, StringStatics.BLANK);
                sceCommand.setEdgeAdaptor(new EObjectAdapter((EObject) request.getConnectionEditPart().getModel()));
                sceCommand.setNewTargetAdaptor(targetEP);
                TreeLayoutSetConnectionAnchorsCommand scaCommand = new TreeLayoutSetConnectionAnchorsCommand(editingDomain, StringStatics.BLANK);
                scaCommand.setEdgeAdaptor(request.getConnectionEditPart());
                scaCommand.setNewTargetTerminal(targetEP.mapConnectionAnchorToTerminal(targetAnchor));
                CompositeCommand cc = new CompositeCommand(DiagramUIMessages.Commands_SetConnectionEndsCommand_Target);
                cc.compose(sceCommand);
                cc.compose(scaCommand);
                cmd = new ICommandProxy(cc);
                // TODO Check what it does in the above code ... If removed the
                // target of the first tree edge is not on the middle (not sure)
                // EditPart cep = request.getConnectionEditPart();
                // RoutingStyle style = (RoutingStyle) ((View)
                // cep.getModel()).getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
                // Routing currentRouter = Routing.MANUAL_LITERAL;
                // if (style != null) {
                // currentRouter = style.getRouting();
                // }
                // Command cmdRouter =
                // getRoutingAdjustment(request.getConnectionEditPart(),
                // getSemanticHint(request), currentRouter,
                // request.getTarget());
                // if (cmdRouter != null) {
                // cmd = cmd == null ? cmdRouter : cmd.chain(cmdRouter);
                // // reset the bendpoints
                // ConnectionAnchor sourceAnchor =
                // node.getSourceConnectionAnchor(request);
                // PointList pointList = new PointList();
                // pointList.addPoint(sourceAnchor.getLocation(targetAnchor.getReferencePoint()));
                // pointList.addPoint(targetAnchor.getLocation(sourceAnchor.getReferencePoint()));
                //
                // SetConnectionBendpointsCommand sbbCommand = new
                // SetConnectionBendpointsCommand(editingDomain);
                // sbbCommand.setEdgeAdapter(request.getConnectionEditPart());
                // sbbCommand.setNewPointList(pointList,
                // sourceAnchor.getReferencePoint(),
                // targetAnchor.getReferencePoint());
                // Command cmdBP = new ICommandProxy(sbbCommand);
                // if (cmdBP != null) {
                // cmd = cmd == null ? cmdBP : cmd.chain(cmdBP);
                // }
                // }
            }
        }
        return cmd;
    }

    /**
     * Launch a specific command ({@link TreeLayoutSetConnectionAnchorsCommand}
     * instead of the classical {@link SetConnectionAnchorsCommand}) to handle
     * with tree layout and setting correctly all the anchors of the edge of the
     * same tree.
     * 
     * @param request
     *            The ReconnectRequest
     * @return a Command
     */
    private Command getReconnectSourceForTreeLayoutCommand(ReconnectRequest request) {
        INodeEditPart node = getConnectableEditPart();
        if (node == null)
            return null;

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        ConnectionAnchor sourceAnchor = node.getSourceConnectionAnchor(request);
        SetConnectionEndsCommand sceCommand = new SetConnectionEndsCommand(editingDomain, StringStatics.BLANK);
        sceCommand.setEdgeAdaptor(new EObjectAdapter((View) request.getConnectionEditPart().getModel()));
        sceCommand.setNewSourceAdaptor(new EObjectAdapter((View) node.getModel()));
        TreeLayoutSetConnectionAnchorsCommand scaCommand = new TreeLayoutSetConnectionAnchorsCommand(editingDomain, StringStatics.BLANK);
        scaCommand.setEdgeAdaptor(request.getConnectionEditPart());
        scaCommand.setNewSourceTerminal(node.mapConnectionAnchorToTerminal(sourceAnchor));
        CompositeCommand cc = new CompositeCommand(DiagramUIMessages.Commands_SetConnectionEndsCommand_Source);
        cc.compose(sceCommand);
        cc.compose(scaCommand);
        return new ICommandProxy(cc);
    }

}
