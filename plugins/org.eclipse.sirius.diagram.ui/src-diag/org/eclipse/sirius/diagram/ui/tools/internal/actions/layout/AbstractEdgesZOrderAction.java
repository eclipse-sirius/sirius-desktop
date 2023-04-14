/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.Disposable;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.gmf.runtime.draw2d.ui.figures.SiriusPolylineConnectionEx;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * A specific abstract {@link Action} for all "z-order actions" on edges. It contains common behaviors for all z-order
 * actions.<BR>
 * Class inspired from {@link HideDDiagramElementAction} but finally "cleaned" because only used in contextual menus.
 * 
 * @author lredor
 */
public abstract class AbstractEdgesZOrderAction implements IObjectActionDelegate, Disposable {

    /** The selection. */
    private ISelection selection;

    /**
     * Constructor.
     */
    public AbstractEdgesZOrderAction() {
        super();
    }

    @Override
    public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
        // empty.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(final IAction action) {
        if (this.selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
            final Set<Object> minimizedSelection = new HashSet<Object>(Arrays.asList(structuredSelection.toArray()));
            if (minimizedSelection.size() > 0) {
                final Object nextSelected = minimizedSelection.iterator().next();
                if (nextSelected instanceof IDiagramEdgeEditPart) {
                    // In theory, it is always the case as the action is available only if selected elements are a list
                    // of IDiagramEdgeEditPart.

                    final DDiagramEditor diagramEditor = (DDiagramEditor) ((EditPart) nextSelected).getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                    List<IDiagramEdgeEditPart> selectedEdges = objectsListToEdgeEditPart(Arrays.asList(structuredSelection.toArray()));
                    final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                    final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
                    final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(diagramEditor.getEditingDomain().getResourceSet());
                    final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
                    try {
                        Command a = buildZOrderCommand(emfCommandFactory, selectedEdges);
                        ((TransactionalEditingDomain) diagramEditor.getAdapter(EditingDomain.class)).getCommandStack().execute(a);
                    } catch (UnsupportedOperationException e) {
                        // Inform end-user why the action has no effect.
                        MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.AbstractEdgesZOrderAction_noExecutioninformationDialogTitle,
                                e.getMessage());
                    }
                }

            }
        }
    }

    /**
     * Get the command to execute.
     *
     * @param commandFactory
     *            The command factory to use to get the command.
     * @param selectedEdges
     *            List of edges to move.
     * @return the command to execute
     * @throws UnsupportedOperationException
     *             in case of a not supported operation
     */
    protected abstract Command getCommandToExecute(IDiagramCommandFactory commandFactory, List<IDiagramEdgeEditPart> selectedEdges) throws UnsupportedOperationException;

    /**
     * Get the list of GMF edge corresponding to the list of edge edit parts.
     * 
     * @param edgeEditParts
     *            The list of edge edit parts.
     * 
     * @return the list of GMF edge
     */
    protected List<Edge> partsToEdge(final List<IDiagramEdgeEditPart> edgeEditParts) {
        final List<Edge> result = new ArrayList<Edge>();
        for (IDiagramEdgeEditPart edgeEditPart : edgeEditParts) {
            final View gmfView = edgeEditPart.getNotationView();
            if (gmfView instanceof Edge) {
                result.add((Edge) gmfView);
            }
        }
        return result;
    }

    /**
     * Get the list of GMF edge corresponding to the list of edge edit parts.
     * 
     * @param edgeEditParts
     *            The list of edge edit parts.
     * 
     * @return the list of GMF edge
     */
    protected List<SiriusPolylineConnectionEx> partsToConnection(final List<IDiagramEdgeEditPart> edgeEditParts) {
        final List<SiriusPolylineConnectionEx> result = new ArrayList<SiriusPolylineConnectionEx>();
        for (IDiagramEdgeEditPart edgeEditPart : edgeEditParts) {
            if (edgeEditPart.getFigure() instanceof SiriusPolylineConnectionEx) {
                result.add((SiriusPolylineConnectionEx) edgeEditPart.getFigure());
            }
        }
        return result;
    }

    private List<IDiagramEdgeEditPart> objectsListToEdgeEditPart(final List<Object> aList) {
        return aList.stream().filter(IDiagramEdgeEditPart.class::isInstance).map(IDiagramEdgeEditPart.class::cast).collect(Collectors.toList());
    }

    @Override
    public void selectionChanged(final IAction action, final ISelection s) {
        this.selection = s;
    }

    @Override
    public void dispose() {
        this.selection = null;
    }

    private Command buildZOrderCommand(IDiagramCommandFactory commandFactory, List<IDiagramEdgeEditPart> edgesToMove) {
        View firstView = edgesToMove.iterator().next().getNotationView();
        EObject container = firstView.eContainer();
        if (container instanceof Diagram) {
            // Even if the change concerns only GMF diagram, if the corresponding DDiagram can not be editable, this
            // action is not executed.
            if (PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container).canEditInstance(container)
                    && PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container).canEditInstance(((Diagram) container).getElement())) {
                return getCommandToExecute(commandFactory, edgesToMove);
            } else {
                throw new UnsupportedOperationException("This z-order action can not be launched as the diagram can not be modified."); //$NON-NLS-1$
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Compute upper or lower edge index that crosses one of the selected edges.
     * 
     * @param selectedEdges
     *            The selected edegs
     * @param above
     *            true if we search an edge that is above selected edges, false otherwise
     * @return the index of the edge in GMF list, -1 if there is no intersection.
     */
    protected int computeNextCrossingEdgeIndex(List<IDiagramEdgeEditPart> selectedEdges, boolean above) {
        int result = above ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<SiriusPolylineConnectionEx> connections = partsToConnection(selectedEdges);
        for (SiriusPolylineConnectionEx connection : connections) {
            int newIndex = getIndexOfFirstIntersection(connection, above, connections);
            result = above ? Math.max(result, newIndex) : Math.min(result, newIndex);
        }
        if (result == Integer.MIN_VALUE || result == Integer.MAX_VALUE) {
            // No intersection has been found
            result = -1;
        }
        return result;
    }

    /**
     * Method inspired from
     * org.eclipse.sirius.ext.gmf.runtime.draw2d.ui.figures.SiriusPolylineConnectionEx.JumpLinkSet.calculateIntersections(Connection),
     * but instead of calculate the intersections that occur between this connection and all the other connections on
     * the diagram, only the index of the first is returned.
     * 
     * @param connection
     *            connection to calculate intersections with other connections in the layer.
     * @param above
     *            true if we search an edge that is above selected edges, false otherwise
     * @param connectionsToIgnore
     *            If one of these connections intersects with <code>connection</code>, it is ignored.
     * @param return
     *            the index of the first intersection, -1 if there is no intersection
     */
    private int getIndexOfFirstIntersection(SiriusPolylineConnectionEx connection, boolean above, List<SiriusPolylineConnectionEx> connectionsToIgnore) {
        // regenerate the points where jump links will occur
        IFigure pParent = connection.getParent();

        PointList tmpLine = connection.getSmoothPoints();

        // only check intersections with connect views which are above or below this one.
        List<? extends IFigure> children = pParent.getChildren();
        int nIndex = children.indexOf(connection);
        ListIterator<? extends IFigure> childIter = children.listIterator(nIndex);

        while (above ? childIter.hasNext() : childIter.hasPrevious()) {
            IFigure figure = above ? childIter.next() : childIter.previous();
            if (!connectionsToIgnore.contains(figure)) {
                PointList checkLine = null;

                if (figure instanceof SiriusPolylineConnectionEx) {
                    checkLine = ((SiriusPolylineConnectionEx) figure).getSmoothPoints();
                } else if (figure instanceof Connection) {
                    checkLine = PointListUtilities.copyPoints(((Connection) figure).getPoints());
                }

                if (checkLine != null) {
                    PointList intersections = new PointList();
                    PointList distances = new PointList();

                    if (PointListUtilities.findIntersections(tmpLine, checkLine, intersections, distances)) {
                        if (intersections.size() > 0) {
                            return children.indexOf(figure);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
