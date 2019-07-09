/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.ExtendableLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.ArrangeAllWithAutoSize;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.PinnedElementsHandler;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * A layout provider which handles pinned elements after an initial layout.
 * 
 * @author pcdavid
 */
public class PinnedElementsLayoutProvider extends DefaultLayoutProvider {

    /**
     * Key to store that the move request is sent from this specific layout provider to reset bounds of edit part to its
     * origin location and size. <BR>
     * The move request sets only the location but has, as side effect, to also use the same
     * {@link org.eclipse.gmf.runtime.notation.Size} as the origin. <BR>
     * For example, the move of a region is forbidden (see
     * {@link org.eclipse.sirius.diagram.ui.graphical.edit.policies.RegionResizableEditPolicy#getMoveCommand(ChangeBoundsRequest)}
     * ). The user has not the authorization to move a region of a container. But if this flag exists in the extended
     * data of the request, the move is "forced".
     * 
     */
    public static final String PINNED_ELEMENTS_MOVE = "sirius.pinned.elements.move.request"; //$NON-NLS-1$

    /**
     * The layout provider which was executed before us. Needed to obtain the new
     */
    private final ExtendableLayoutProvider baseProvider;

    private Predicate<Object> validateAllElementInArrayListAreIDiagramElementEditPart = new Predicate<Object>() {

        @Override
        public boolean apply(Object input) {
            return input instanceof IDiagramElementEditPart;
        }
    };

    /**
     * Creates a <code>FixOverlapsProvider</code>.
     * 
     * @param baseProvider
     *            the layout provider which computed the initial locations.
     */
    public PinnedElementsLayoutProvider(final ExtendableLayoutProvider baseProvider) {
        this.baseProvider = baseProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        final Map<IGraphicalEditPart, Rectangle> initialBounds = new HashMap<IGraphicalEditPart, Rectangle>(baseProvider.getExtender().getUpdatedBounds());

        // Finds if there are unpinned diagram elements to keep fixed stored in
        // the LayoutHint as a Collection
        ArrayList<IDiagramElementEditPart> elementsToKeepFixed = new ArrayList<>();
        if (layoutHint.getAdapter(Collection.class) instanceof ArrayList<?>
                && Iterables.all((ArrayList<?>) layoutHint.getAdapter(Collection.class), validateAllElementInArrayListAreIDiagramElementEditPart)) {
            elementsToKeepFixed = (ArrayList<IDiagramElementEditPart>) layoutHint.getAdapter(Collection.class);
        }

        if (ArrangeAllWithAutoSize.isEnabled()) {
            adjustAutoSizedContainers(initialBounds, elementsToKeepFixed);
        }
        final Collection<IGraphicalEditPart> editParts = Lists.newArrayList(Iterables.filter(selectedObjects, IGraphicalEditPart.class));
        // Removes the connectionEditPart
        editParts.removeAll(Lists.newArrayList(Iterables.filter(selectedObjects, ConnectionEditPart.class)));
        final CompoundCommand cc = new CompoundCommand();

        handlePinnedElements(editParts, initialBounds, cc, elementsToKeepFixed);
        return cc.unwrap();
    }

    private void adjustAutoSizedContainers(final Map<IGraphicalEditPart, Rectangle> initialBounds, ArrayList<IDiagramElementEditPart> elementstToKeepFixed) {
        PinHelper pinHelper = new PinHelper();
        for (Entry<IGraphicalEditPart, Rectangle> entry : initialBounds.entrySet()) {
            if (entry.getKey() instanceof AbstractDiagramElementContainerEditPart) {
                final AbstractDiagramElementContainerEditPart container = (AbstractDiagramElementContainerEditPart) entry.getKey();
                if (!pinHelper.isPinned(container.resolveDiagramElement()) && !elementstToKeepFixed.contains(container)) {
                    final Rectangle bounds = entry.getValue().getCopy();
                    final Rectangle autoSizedBounds = container.getAutosizedDimensions();
                    bounds.width = autoSizedBounds.width;
                    bounds.height = autoSizedBounds.height;
                    entry.setValue(bounds);
                }
            }
        }
    }

    private void handlePinnedElements(final Collection<IGraphicalEditPart> editParts, final Map<IGraphicalEditPart, Rectangle> initialBounds, final CompoundCommand cmd,
            ArrayList<IDiagramElementEditPart> elementstToKeepFixed) {
        /*
         * Depth-first recursion on containers.
         */
        for (IGraphicalEditPart part : editParts) {
            final Collection<IGraphicalEditPart> children = getChildrenOfInterest(part);
            if (!children.isEmpty()) {
                handlePinnedElements(children, initialBounds, cmd, elementstToKeepFixed);
            }
        }
        /*
         * Base case: handle pinned elements at this particular level.
         */
        final Map<IGraphicalEditPart, Rectangle> initialBoundsForThisLevel = Maps.filterEntries(initialBounds, new Predicate<Map.Entry<IGraphicalEditPart, Rectangle>>() {
            @Override
            public boolean apply(final Entry<IGraphicalEditPart, Rectangle> input) {
                return editParts.contains(input.getKey());
            }
        });
        final PinnedElementsHandler handler = new PinnedElementsHandler(editParts, initialBoundsForThisLevel, elementstToKeepFixed);
        final Map<IGraphicalEditPart, Point> newPositions = handler.computeSolution();
        for (Entry<IGraphicalEditPart, Point> entry : newPositions.entrySet()) {
            final IGraphicalEditPart part = entry.getKey();
            final Point position = entry.getValue();
            final Command cbc = createChangeBoundsCommand(part, position);
            cmd.add(cbc);
        }
    }

    /**
     * Finds the "real" children of the specified edit part that needs to be laid out.
     */
    private Collection<IGraphicalEditPart> getChildrenOfInterest(final IGraphicalEditPart gep) {
        final Iterable<IGraphicalEditPart> rawChildren = Iterables.filter(gep.getChildren(), IGraphicalEditPart.class);
        // Ignore these, which are technically children edit parts but not
        // "inside" the container.
        final Predicate<Object> invalidChildKind = Predicates.or(Predicates.instanceOf(IDiagramBorderNodeEditPart.class), Predicates.instanceOf(IDiagramNameEditPart.class));
        // These are OK.
        final Predicate<Object> validChildKind = Predicates.or(Predicates.instanceOf(IDiagramNodeEditPart.class), Predicates.instanceOf(IDiagramContainerEditPart.class),
                Predicates.instanceOf(IDiagramListEditPart.class));
        final Predicate<Object> isProperChild = Predicates.and(validChildKind, Predicates.not(invalidChildKind));
        final Collection<IGraphicalEditPart> result = Lists.newArrayList(Iterables.filter(rawChildren, isProperChild));
        // Containers have an intermediate level of children edit parts. We
        // ignore these "wrapper" parts, but must look inside for proper
        // children of the container.
        for (IGraphicalEditPart part : Iterables.filter(rawChildren, Predicates.not(isProperChild))) {
            if (part instanceof DNodeContainerViewNodeContainerCompartmentEditPart || part instanceof DNodeContainerViewNodeContainerCompartment2EditPart) {
                result.addAll(getChildrenOfInterest(part));
            }
        }
        return result;
    }

    /**
     * Create the command that changes the bounds of the specified edit part.
     * 
     * @param editPart
     *            the specified edit part
     * 
     * @param newPosition
     *            the new position of the figure.
     * @return the command that changes the bounds of the specified edit part.
     */
    protected Command createChangeBoundsCommand(final IGraphicalEditPart editPart, final Point newPosition) {
        Command result = null;
        final Object existingRequest = this.findRequest(editPart, org.eclipse.gef.RequestConstants.REQ_MOVE);
        ChangeBoundsRequest request = null;
        double scale = 1.0;
        RootEditPart root = editPart.getRoot();
        if (root instanceof DiagramRootEditPart) {
            final ZoomManager zoomManager = ((DiagramRootEditPart) root).getZoomManager();
            scale = zoomManager.getZoom();
        }
        if (existingRequest instanceof ChangeBoundsRequest) {
            request = (ChangeBoundsRequest) existingRequest;
        } else if (existingRequest == null) {
            request = new ChangeBoundsRequest();
            request.setEditParts(editPart);
            result = this.buildCommandWrapper(request, editPart);
        }
        if (newPosition != null) {
            final Dimension delta = newPosition.getDifference(editPart.getFigure().getBounds().getLocation());
            delta.width *= scale;
            delta.height *= scale;
            if (request != null) {
                request.setMoveDelta(new Point(delta.width, delta.height));
                request.setLocation(newPosition);
                request.setType(org.eclipse.gef.RequestConstants.REQ_MOVE);
                request.getExtendedData().put(PinnedElementsLayoutProvider.PINNED_ELEMENTS_MOVE, Boolean.TRUE);
            } else {
                // no move, return null.
                return null;
            }
        }
        return result;
    }
}
