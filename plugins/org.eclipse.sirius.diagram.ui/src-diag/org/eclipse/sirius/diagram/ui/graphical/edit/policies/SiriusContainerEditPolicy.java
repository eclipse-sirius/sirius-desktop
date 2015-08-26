/*******************************************************************************
 * Copyright (c) 2002, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Obeo - Adaptations.
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.util.ObjectAdapter;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.layout.IInternalLayoutRunnable;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.layout.LayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.CenterEdgeLayoutCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DistributeCommand;
import org.eclipse.sirius.diagram.ui.tools.api.requests.DistributeRequest;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.SnapCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.GMFRuntimeCompatibility;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A specific class to override the arrangeCommand for the GMF runtime version
 * older that 1.4.0.<BR>
 * This class launches the arrange even for one element.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusContainerEditPolicy extends ContainerEditPolicy {

    private final Predicate<Object> isRegionEditPart = new Predicate<Object>() {
        @Override
        public boolean apply(Object input) {
            return input instanceof AbstractDiagramElementContainerEditPart && ((AbstractDiagramElementContainerEditPart) input).isRegion();
        }
    };

    @Override
    public Command getCommand(Request request) {
        if (org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_DISTRIBUTE.equals(request.getType())) {
            return getDistributeCommand((DistributeRequest) request);
        }

        return super.getCommand(request);
    }

    // CHECKSTYLE:OFF
    /**
     * Override this method for version before GMF 1.5.0 with Eclipse 3.6.
     * Indeed, in the previous version there is a test that launch arrange only
     * with more that one element. But we want to launch arrange even with one
     * element to have always the same result (one or more new elements) : to
     * avoid overlaps with pinned elements for example.<BR>
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy#getArrangeCommand(org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest)
     */
    @Override
    protected Command getArrangeCommand(ArrangeRequest request) {
        Command commandToReturn = null;
        if (GMFRuntimeCompatibility.hasGMFPluginReleaseBetween1_2_0_And_1_3_3()) {
            if (RequestConstants.REQ_ARRANGE_DEFERRED.equals(request.getType())) {
                String layoutType = request.getLayoutType();
                TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                return new ICommandProxy(new DeferredLayoutCommand(editingDomain, request.getViewAdaptersToArrange(), (IGraphicalEditPart) getHost(), layoutType));
            }

            String layoutDesc = request.getLayoutType() != null ? request.getLayoutType() : LayoutType.DEFAULT;
            boolean offsetFromBoundingBox = false;
            List editparts = new ArrayList();

            if ((ActionIds.ACTION_ARRANGE_ALL.equals(request.getType())) || (ActionIds.ACTION_TOOLBAR_ARRANGE_ALL.equals(request.getType()))) {
                editparts = ((IGraphicalEditPart) getHost()).getChildren();
                request.setPartsToArrange(editparts);
            }
            if ((ActionIds.ACTION_ARRANGE_SELECTION.equals(request.getType())) || (ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION.equals(request.getType()))) {
                editparts = request.getPartsToArrange();
                // Just comment this line to launch an arrange command even with
                // one elemnt
                // if (editparts.size() < 2
                // || !(((GraphicalEditPart) ((EditPart) editparts.get(0))
                // .getParent()).getContentPane().getLayoutManager() instanceof
                // XYLayout)) {
                // return null;
                // }
                offsetFromBoundingBox = true;
            }
            if (RequestConstants.REQ_ARRANGE_RADIAL.equals(request.getType())) {
                editparts = request.getPartsToArrange();
                offsetFromBoundingBox = true;
                layoutDesc = LayoutType.RADIAL;
            }
            if (editparts.isEmpty())
                return null;
            List nodes = new ArrayList(editparts.size());
            ListIterator li = editparts.listIterator();
            while (li.hasNext()) {
                IGraphicalEditPart ep = (IGraphicalEditPart) li.next();
                View view = ep.getNotationView();
                if (ep.isActive() && view instanceof Node) {
                    Rectangle bounds = ep.getFigure().getBounds();
                    nodes.add(new LayoutNode((Node) view, bounds.width, bounds.height));
                }
            }
            if (nodes.isEmpty()) {
                return null;
            }

            List hints = new ArrayList(2);
            hints.add(layoutDesc);
            hints.add(getHost());
            IAdaptable layoutHint = new ObjectAdapter(hints);
            final Runnable layoutRun = layoutNodes(nodes, offsetFromBoundingBox, layoutHint);

            boolean isSnap = true;
            // retrieves the preference store from the first edit part
            IGraphicalEditPart firstEditPart = (IGraphicalEditPart) editparts.get(0);
            if (firstEditPart.getViewer() instanceof DiagramGraphicalViewer) {
                IPreferenceStore preferenceStore = ((DiagramGraphicalViewer) firstEditPart.getViewer()).getWorkspaceViewerPreferenceStore();
                if (preferenceStore != null) {
                    isSnap = preferenceStore.getBoolean(WorkspaceViewerProperties.SNAPTOGRID);
                }
            }

            // the snapCommand still invokes proper calculations if snap to grid
            // is turned off, this additional check
            // is intended to make the code more appear more logical

            TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, StringStatics.BLANK);
            if (layoutRun instanceof IInternalLayoutRunnable) {
                ctc.add(new CommandProxy(((IInternalLayoutRunnable) layoutRun).getCommand()));
            } else {
                ctc.add(new AbstractTransactionalCommand(editingDomain, StringStatics.BLANK, null) {
                    @Override
                    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
                        layoutRun.run();
                        return CommandResult.newOKCommandResult();
                    }
                });
            }
            if (isSnap) {
                Command snapCmd = getSnapCommand(request);
                if (snapCmd != null) {
                    ctc.add(new CommandProxy(getSnapCommand(request)));
                }
            }
            commandToReturn = new ICommandProxy(ctc);
        } else {
            commandToReturn = super.getArrangeCommand(request);
        }

        if ((ActionIds.ACTION_ARRANGE_SELECTION.equals(request.getType())) || (ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION.equals(request.getType()))) {
            if (Iterables.any(request.getPartsToArrange(), isRegionEditPart)) {
                return UnexecutableCommand.INSTANCE;
            }
        }

        // We add a Command to center edges that need to be at the end of the
        // layout.
        if (commandToReturn != null) {
            EditPart host = getHost();
            if (host instanceof GraphicalEditPart) {
                CenterEdgeLayoutCommand centerEdgeLayoutCommand = new CenterEdgeLayoutCommand((GraphicalEditPart) host);
                commandToReturn = commandToReturn.chain(new ICommandProxy(centerEdgeLayoutCommand));
            }
        }
        return commandToReturn;
    }

    /**
     * Duplicates method from
     * {@link org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy}
     * for the duplication of getArrangeCommand.
     * 
     * @param request
     *            The current request.
     * @return A command to snap the edit parts of the request.
     */
    private Command getSnapCommand(Request request) {

        List editparts = null;
        if (request instanceof GroupRequest) {
            editparts = ((GroupRequest) request).getEditParts();
        } else if (request instanceof ArrangeRequest) {
            editparts = ((ArrangeRequest) request).getPartsToArrange();
        }

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        if (editparts != null) {
            return new ICommandProxy(new SnapCommand(editingDomain, editparts));
        }
        return null;
    }

    // CHECKSTYLE:ON

    /**
     * Gets a distribute command.
     * 
     * @param request
     *            The distribute request
     * @return command
     */
    protected Command getDistributeCommand(DistributeRequest request) {
        List<IGraphicalEditPart> editparts = request.getEditParts();
        if (!editparts.isEmpty() && getHost() instanceof IGraphicalEditPart) {
            return new ICommandProxy(new DistributeCommand(((IGraphicalEditPart) getHost()).getEditingDomain(), editparts, request.getDistributeType()));
        }
        return null;
    }
}
