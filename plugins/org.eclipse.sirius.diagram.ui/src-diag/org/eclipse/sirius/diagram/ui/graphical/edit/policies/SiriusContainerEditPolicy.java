/*******************************************************************************
 * Copyright (c) 2002, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Obeo - Adaptations.
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.common.core.util.ObjectAdapter;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.commands.ArrangeCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.CenterEdgeLayoutCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.DistributeCommand;
import org.eclipse.sirius.diagram.ui.internal.layout.GenericLayoutProvider;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.requests.DistributeRequest;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.SnapCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A specific class to override the arrangeCommand to handles edge centering, snapping, ...<BR>
 * This class launches the arrange even for one element.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
@SuppressWarnings("restriction")
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

    @SuppressWarnings("unchecked")
    @Override
    protected Command getArrangeCommand(ArrangeRequest request) {
        if ((ActionIds.ACTION_ARRANGE_SELECTION.equals(request.getType())) || (ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION.equals(request.getType()))) {
            if (Iterables.any(request.getPartsToArrange(), isRegionEditPart)) {
                return UnexecutableCommand.INSTANCE;
            }
        }
        Command commandToReturn = doDuplicatedGetArrangeCommand(request);
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
     * Duplicated from
     * org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy.getArrangeCommand(ArrangeRequest) to override
     * the SnapCommand.
     * 
     * @param request
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Command doDuplicatedGetArrangeCommand(ArrangeRequest request) {
        if (RequestConstants.REQ_ARRANGE_DEFERRED.equals(request.getType())) {
            String layoutType = request.getLayoutType();
            TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
            return new ICommandProxy(new DeferredLayoutCommand(editingDomain, request.getViewAdaptersToArrange(), (IGraphicalEditPart) getHost(), layoutType));
        }

        String layoutDesc = request.getLayoutType() != null ? request.getLayoutType() : LayoutType.DEFAULT;

        boolean offsetFromBoundingBox = false;
        List<IGraphicalEditPart> editparts = new ArrayList<IGraphicalEditPart>();

        if ((ActionIds.ACTION_ARRANGE_ALL.equals(request.getType())) || (ActionIds.ACTION_TOOLBAR_ARRANGE_ALL.equals(request.getType()))) {
            editparts = ((IGraphicalEditPart) getHost()).getChildren();
            request.setPartsToArrange(editparts);
        }
        if ((ActionIds.ACTION_ARRANGE_SELECTION.equals(request.getType())) || (ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION.equals(request.getType()))) {
            editparts = request.getPartsToArrange();
            offsetFromBoundingBox = true;
        }
        if (RequestConstants.REQ_ARRANGE_RADIAL.equals(request.getType())) {
            editparts = request.getPartsToArrange();
            offsetFromBoundingBox = true;
            layoutDesc = LayoutType.RADIAL;
        }

        if (editparts.isEmpty())
            return null;

        List hints = new ArrayList(2);
        hints.add(layoutDesc);
        hints.add(getHost());
        IAdaptable layoutHint = new ObjectAdapter(hints);

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, StringStatics.BLANK);
        // Label added to facilitate debugging and to identify this command in undo context.
        ctc.setLabel(Messages.SiriusContainerEditPolicy_arrangeCommandLabel);
        ctc.add(new ArrangeCommand(editingDomain, StringStatics.BLANK, null, editparts, layoutHint, offsetFromBoundingBox));

        // retrieves the preference store from the first edit part
        IGraphicalEditPart firstEditPart = editparts.get(0);
        if (firstEditPart.getViewer() instanceof DiagramGraphicalViewer) {
            IPreferenceStore preferenceStore = ((DiagramGraphicalViewer) firstEditPart.getViewer()).getWorkspaceViewerPreferenceStore();
            if (preferenceStore != null && preferenceStore.getBoolean(WorkspaceViewerProperties.SNAPTOGRID)) {
                Command snapCmd = getSnapCommand(request);
                if (snapCmd != null) {
                    ctc.add(new CommandProxy(getSnapCommand(request)));
                }
            }
        }

        return new ICommandProxy(ctc);

    }

    /**
     * Duplicates method from {@link org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy} for the
     * duplication of getArrangeCommand.
     * 
     * @param request
     *            The current request.
     * @return A command to snap the edit parts of the request.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Command getSnapCommand(Request request) {

        List editparts = null;
        if (request instanceof GroupRequest) {
            editparts = ((GroupRequest) request).getEditParts();
        } else if (request instanceof ArrangeRequest) {
            editparts = ((ArrangeRequest) request).getPartsToArrange();
        }

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        if (editparts != null && shouldLaunchSnapTo(editparts)) {
            return new ICommandProxy(new SnapCommand(editingDomain, editparts));
        }
        return null;
    }

    private boolean shouldLaunchSnapTo(List<? extends IGraphicalEditPart> editparts) {
        Optional<? extends IGraphicalEditPart> optionalFirstEditPart = editparts.stream().findFirst();
        if (optionalFirstEditPart.isPresent()) {
            LayoutProvider layoutProvider = LayoutService.getProvider(optionalFirstEditPart.get());
            if (layoutProvider instanceof GenericLayoutProvider) {
                return ((GenericLayoutProvider) layoutProvider).shouldLaunchSnapTo(optionalFirstEditPart.get());
            }
        }
        return true;
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
