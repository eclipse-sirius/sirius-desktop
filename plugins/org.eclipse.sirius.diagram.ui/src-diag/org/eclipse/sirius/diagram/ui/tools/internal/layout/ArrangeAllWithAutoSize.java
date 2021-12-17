/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.internal.graph.VirtualNode;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.RegionContainerUpdateLayoutOperation;
import org.eclipse.sirius.diagram.ui.internal.view.factories.AbstractContainerViewFactory;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.AbstractCompositeLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This class capture all the needed changes to make to the arrange all behavior so that auto-size is automatically
 * applied on containers.
 * 
 * @author cbrun
 */
public class ArrangeAllWithAutoSize {
    /**
     * Return true if this part should be autosized.
     * 
     * @param part
     *            The concern part
     * @return Return true if this part should be auto-size
     */
    public static boolean shouldBeAutosized(final IGraphicalEditPart part) {
        boolean enabled = ArrangeAllWithAutoSize.isEnabled() && (ArrangeAllWithAutoSize.isContainer(part) || ArrangeAllWithAutoSize.isList(part)) && !ArrangeAllWithAutoSize.isPinned(part);
        enabled = enabled && !ArrangeAllWithAutoSize.isDefaultSizeHasJustBeenSet(part);
        if (enabled && ArrangeAllWithAutoSize.isRegion(part)) {
            EditPart regionContainerCompartment = part.getParent();
            if (regionContainerCompartment != null) {
                EditPart regionContainer = regionContainerCompartment.getParent();
                enabled = regionContainer instanceof IGraphicalEditPart && !ArrangeAllWithAutoSize.isPinned((IGraphicalEditPart) regionContainer);
            }
        }
        return enabled;

    }

    private static boolean isPinned(final IGraphicalEditPart part) {
        if (part instanceof IDiagramElementEditPart) {
            IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) part;
            DDiagramElement dDiagramElement = diagramElementEditPart.resolveDiagramElement();
            return new PinHelper().isPinned(dDiagramElement);
        }
        return false;
    }

    /**
     * Tests if the gmf view attached to this part contained the
     * <code>{@link AbstractContainerViewFactory} JUST_CREATED</code> marker adapter. The marker is added by the
     * DNodeContainerViewFactory to distinguish the views which have just been created to keep their default dimension
     * setted by the view factory. We removed this marker if it's found.
     * 
     * @param part
     *            the graphical edit part.
     * @return true if the marker is found.
     */
    private static boolean isDefaultSizeHasJustBeenSet(final IGraphicalEditPart part) {
        View view = part.getNotationView();
        if (view != null) {
            Iterator<Adapter> iterator = view.eAdapters().iterator();
            while (iterator.hasNext()) {
                Adapter adapter = iterator.next();
                if (adapter.isAdapterForType(AbstractContainerViewFactory.class)) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isContainer(final IGraphicalEditPart part) {
        return part instanceof IDiagramContainerEditPart;
    }

    private static boolean isList(final IGraphicalEditPart part) {
        return part instanceof IDiagramListEditPart;
    }

    /**
     * return true if the auto-size on arrange all feature is enabled in the preferences.
     * 
     * @return true if the auto-size on arrange all feature is enabled in the preferences.
     */
    public static boolean isEnabled() {
        return DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name());
    }

    /**
     * Prepare the auto-size support for an incoming arrange all. This preparation involve activating specific behavior
     * on edit parts figures so that they are able, later on, to provide their 'incoming size after auto-size has been
     * applied' so that the layout can leverage this information.
     * 
     * @param unmodifiableIterator
     *            list iterator of edit parts.
     */
    public void prepareForArrangeAll(final Iterator<AbstractDiagramElementContainerEditPart> unmodifiableIterator) {
        prepareForArrangeAll(unmodifiableIterator, null);
    }

    /**
     * Prepare the auto-size support for an incoming arrange all. This preparation involve activating specific behavior
     * on edit parts figures so that they are able, later on, to provide their 'incoming size after auto-size has been
     * applied' so that the layout can leverage this information.
     * 
     * @param unmodifiableIterator
     *            list iterator of edit parts.
     * @param elementsToKeepFixed
     *            list of elements to keep fixed even if they are not pinned.
     */
    public void prepareForArrangeAll(Iterator<AbstractDiagramElementContainerEditPart> unmodifiableIterator, ArrayList<IDiagramElementEditPart> elementsToKeepFixed) {
        final Set<IFigure> parentFiguresToValidateToGetAutosizeDimensions = new LinkedHashSet<>();
        while (unmodifiableIterator.hasNext()) {
            final AbstractDiagramElementContainerEditPart ep = unmodifiableIterator.next();
            if ((elementsToKeepFixed == null || !elementsToKeepFixed.contains(ep)) && ArrangeAllWithAutoSize.shouldBeAutosized(ep)) {
                ep.forceFigureAutosize();
                if (ep.getParent() instanceof AbstractGraphicalEditPart) {
                    parentFiguresToValidateToGetAutosizeDimensions.add(((AbstractGraphicalEditPart) ep.getParent()).getFigure());
                }
            }
        }
        for (final IFigure parentFig : parentFiguresToValidateToGetAutosizeDimensions) {
            parentFig.validate();
        }
    }

    /**
     * Return the dimension this edit part is going to take after application of the auto-size behavior.
     * 
     * @param ep
     *            any edit part part of the initialization process.
     * @return the dimension this edit part is going to take after application of the auto-size behavior.
     */
    public Dimension getSizeToConsiderDuringArrangeAll(final IGraphicalEditPart ep) {
        final Dimension size;
        if (ArrangeAllWithAutoSize.shouldBeAutosized(ep) && ep instanceof AbstractDiagramElementContainerEditPart) {
            size = ((AbstractDiagramElementContainerEditPart) ep).getAutosizedDimensions().getSize();
        } else {
            size = ep.getFigure().getBounds().getSize();
        }
        return size;
    }

    // CHECKSTYLE:OFF
    /**
     * Create the "Arrange All" sub-commands considering setting all the containers as auto-sized.
     * 
     * @param globalDiff
     *            current diff.
     * @param vi
     *            iterator of views
     * @param cc
     *            command to update.
     * @param provider
     *            a layouter responsible to provide the node metrics.
     * @param minxX
     *            the X coordinate of the reference point.
     * @param minxY
     *            the Y coordinate of the reference point.
     */
    public void createSubCommands(Point globalDiff, ListIterator vi, final CompoundCommand cc, final AbstractCompositeLayoutProvider provider, final int minX, final int minY) {
        List<Node> nodes = Lists.newArrayList(vi);
        vi = nodes.listIterator();
        // Now set the position of the icons. This causes the
        // arc connection points to be recalculated
        Point ptLayoutMin = computePointLayoutMin(nodes, provider);
        while (vi.hasNext()) {
            final Node node = (Node) vi.next();

            if (node.data instanceof ShapeEditPart) {
                createSubCommands(globalDiff, cc, provider, minX, minY, ptLayoutMin, node);
            }
        }
    }

    private void createSubCommands(Point globalDiff, final CompoundCommand cc, final AbstractCompositeLayoutProvider provider, final int minX, final int minY, Point ptLayoutMin, final Node node) {
        Point diffP = diff(minX, minY, ptLayoutMin, provider, node);
        diffP.setX(Math.max(diffP.x, globalDiff.x));
        diffP.setY(Math.max(diffP.y, globalDiff.y));
        if (diffP.x > -node.getPadding().left) {
            diffP.setX(-node.getPadding().left);
        }
        if (diffP.y > -node.getPadding().bottom) {
            diffP.setY(-node.getPadding().bottom);
        }

        Rectangle nodeExt = provider.translateToGraph(provider.provideNodeMetrics(node));

        createSubCommands(cc, node, diffP, nodeExt);
    }

    private void createSubCommands(final CompoundCommand cc, final Node node, Point diff, final Rectangle nodeExt) {
        if (node.getParent() instanceof VirtualNode) {
            if (nodeExt.x > node.getPadding().left) {
                nodeExt.setX(nodeExt.x - node.getPadding().left);
            }
            if (nodeExt.y > node.getPadding().top) {
                nodeExt.setY(nodeExt.y - node.getPadding().top);
            }
        }

        final Point pt = new Point();
        if (nodeExt.x + diff.x > 0) {
            pt.setX(nodeExt.x + diff.x);
        } else {
            pt.setX(nodeExt.x);
        }
        if (nodeExt.y + diff.y > 0) {
            pt.setY(nodeExt.y + diff.y);
        } else {
            pt.setY(nodeExt.y);
        }
        final Point ptLocation = new Point(pt.x, pt.y);
        final IGraphicalEditPart gep = (IGraphicalEditPart) node.data;
        final Point ptOldLocation = gep.getFigure().getBounds().getLocation();

        gep.getFigure().translateToAbsolute(ptOldLocation);
        gep.getFigure().translateToAbsolute(ptLocation);

        if (gep.getParent() instanceof DDiagramEditPart) {
            if (node.x == node.getPadding().left) {
                ptLocation.setX(ptLocation.x - node.getPadding().left);
            }
            if (node.y == node.getPadding().bottom) {
                ptLocation.setY(ptLocation.y - node.getPadding().bottom);
            }
        }

        final Dimension delta = ptLocation.getDifference(ptOldLocation);
        final ChangeBoundsRequest request = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);

        final boolean isRegion = isRegion(gep);
        if (isRegion) {
            final Dimension size = nodeExt.getSize().getCopy();
            final Dimension oldSize = gep.getFigure().getBounds().getSize();

            gep.getFigure().translateToAbsolute(oldSize);
            gep.getFigure().translateToAbsolute(size);
            final Dimension deltaSize = size.getShrinked(oldSize);

            request.setType(org.eclipse.gef.RequestConstants.REQ_RESIZE);
            request.setConstrainedResize(true);
            request.setSizeDelta(deltaSize);
        }

        final CompoundCommand cmd = new CompoundCommand();
        if (!(delta.height == 0 && delta.width == 0) || isRegion && !(request.getSizeDelta().height == 0 && request.getSizeDelta().width == 0)) {
            request.setEditParts(gep);
            request.setMoveDelta(new Point(delta.width, delta.height));
            request.setLocation(ptLocation);
            cmd.add(gep.getCommand(request));
        }
        if (ArrangeAllWithAutoSize.shouldBeAutosized(gep)) {
            cmd.add(gep.getCommand(new Request(RequestConstants.REQ_AUTOSIZE)));
        }
        if (cmd.canExecute()) {
            cc.add(cmd);
        }

        if (isRegionContainer(gep)) {
            cc.add(new ICommandProxy(CommandFactory.createICommand(gep.getEditingDomain(), new RegionContainerUpdateLayoutOperation((org.eclipse.gmf.runtime.notation.Node) gep.getModel()))));
        }

    }

    private static boolean isRegion(IGraphicalEditPart gep) {
        if (gep instanceof AbstractDiagramElementContainerEditPart) {
            DDiagramElement resolveDiagramElement = ((AbstractDiagramElementContainerEditPart) gep).resolveDiagramElement();
            if (resolveDiagramElement instanceof DDiagramElementContainer) {
                return new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) resolveDiagramElement).isRegion();
            }
        }
        return false;
    }

    private static boolean isRegionContainer(IGraphicalEditPart gep) {
        if (gep instanceof IDiagramContainerEditPart) {
            DDiagramElement resolveDiagramElement = ((IDiagramContainerEditPart) gep).resolveDiagramElement();
            if (resolveDiagramElement instanceof DNodeContainer) {
                return new DNodeContainerExperimentalQuery((DNodeContainer) resolveDiagramElement).isRegionContainer();
            }
        }
        return false;
    }

    private Point diff(final int minX, final int minY, Point ptLayoutMin, AbstractCompositeLayoutProvider provider, Node firstNode) {
        if (ptLayoutMin.x == firstNode.x) {
            return provider.translateFromGraph(new Rectangle(0, 0, 0, 0)).getLocation();
        }
        return provider.translateFromGraph(new Rectangle(minX - ptLayoutMin.x, minY - ptLayoutMin.y, 0, 0)).getLocation();
        // return new Point(minX - ptLayoutMin.x, minY - ptLayoutMin.y);
    }

    private Point computePointLayoutMin(List<Node> nodes, AbstractCompositeLayoutProvider provider) {
        Point ptLayoutMin = new Point(-1, -1);
        for (Node node : nodes) {
            // ignore ghost node
            if (node.data != null) {
                Rectangle nodeExt = provider.provideNodeMetrics(node);
                nodeExt = ArrangeAllWithAutoSize.extendBoundingBoxWithBorderedNodes(node, nodeExt);
                if (ptLayoutMin.x == -1) {
                    ptLayoutMin.setX(nodeExt.x);
                    ptLayoutMin.setY(nodeExt.y);
                } else {
                    ptLayoutMin.setX(Math.min(ptLayoutMin.x, nodeExt.x));
                    ptLayoutMin.setY(Math.min(ptLayoutMin.y, nodeExt.y));
                }
            }

        }
        return ptLayoutMin;
    }

    static Rectangle extendBoundingBoxWithBorderedNodes(Node node, Rectangle box) {
        Rectangle extendedBox = box.getCopy();
        if (node.data instanceof IGraphicalEditPart) {
            IGraphicalEditPart gep = (IGraphicalEditPart) node.data;
            /*
             * 'delta' is how much the bordered node's parent has moved from its original position. For the bordered
             * nodes, we can only get their original position (through the figure), but we assume they are moved along
             * with their parent of the same delta.
             */
            Dimension delta = box.getLocation().getDifference(gep.getFigure().getBounds().getLocation());
            for (AbstractDiagramBorderNodeEditPart borderedNode : Iterables.filter(gep.getChildren(), AbstractDiagramBorderNodeEditPart.class)) {
                Rectangle translate = borderedNode.getFigure().getBounds().getCopy().translate(delta.width, delta.height);
                extendedBox.union(translate);
            }
            Rectangle extendedBoxName = new Rectangle();
            for (AbstractDiagramNameEditPart nameNode : Iterables.filter(gep.getChildren(), AbstractDiagramNameEditPart.class)) {
                Rectangle translate = nameNode.getFigure().getBounds().getCopy();
                extendedBoxName.union(new Dimension(0, translate.height));
            }
            for (SquareEditPart nameNode : Iterables.filter(gep.getChildren(), SquareEditPart.class)) {
                Dimension translate = gep.getFigure().getMinimumSize().getCopy();
                extendedBoxName.union(new Dimension(0, translate.height));
            }

            if (extendedBoxName.height > extendedBox.y) {
                extendedBox.translate(extendedBoxName.width, extendedBoxName.height);
            }
        }
        if (extendedBox.x < node.getPadding().left) {
            extendedBox.setX(node.getPadding().left);
        }
        if (extendedBox.y < node.getPadding().bottom) {
            extendedBox.setY(node.getPadding().bottom);
        }
        return extendedBox;
    }
}
