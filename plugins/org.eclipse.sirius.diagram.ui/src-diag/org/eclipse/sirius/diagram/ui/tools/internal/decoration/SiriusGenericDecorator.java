/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ImageFigureEx;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.SiriusDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.tools.api.profiler.SiriusTasks;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.swt.graphics.Image;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * This IDecorator handles core Sirius decorations and decorations provided in the VSM.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
@SuppressWarnings("restriction")
public class SiriusGenericDecorator extends AbstractDecorator {

    /**
     * Margin in pixels used to display decoration.
     */
    private static final Integer DEFAULT_MARGIN = 2;

    /**
     * If the EditPart contains IBorderedShapeEditPart, the margin, used to shift the decoration from the border, is the
     * same as IBorderItemOffsets.DEFAULT_OFFSET. It allows to avoid overlap between the decoration and the border node.
     */
    private static final Integer MARGIN_FOR_BORDERNODE = IBorderItemOffsets.DEFAULT_OFFSET.height;

    /**
     * Space, in pixels, between two decorators
     */
    private static final Integer SPACE = 1;

    /**
     * Height and width of the list decoration.
     */
    private static final Integer LIST_DECORATION_SIZE = 16;

    private static final ProfilerTask DECORATOR_REFRESH = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, Messages.SubDiagramDecorator_taskName, SiriusTasks.IMAGES_VIEWPOINT);

    private GraphicalEditPart editPart;

    /** the decorations being displayed */
    private List<IDecoration> decorations = Collections.<IDecoration> emptyList();

    private Set<SiriusDecorationDescriptorProvider> decorationDescriptorProviders;

    /**
     * Indicates if the decorations should be set on a printable layer.
     */
    private boolean printDecoration;

    private Session session;

    /**
     * Create a decorator.
     * 
     * @param decoratorTarget
     *            target to decorate.
     * @param decorationDescriptorProviders
     *            the decorationDescriptorProviders
     */
    public SiriusGenericDecorator(IDecoratorTarget decoratorTarget, Set<SiriusDecorationDescriptorProvider> decorationDescriptorProviders) {
        super(decoratorTarget);

        IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        printDecoration = prefs.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name());

        this.decorationDescriptorProviders = decorationDescriptorProviders;
        editPart = (GraphicalEditPart) getDecoratorTarget().getAdapter(GraphicalEditPart.class);
        View view = (View) getDecoratorTarget().getAdapter(View.class);
        EObject model = view.getElement();
        if (model instanceof DRepresentationElement) {
            DRepresentationElement repElement = (DRepresentationElement) model;
            this.session = SessionManager.INSTANCE.getSession(repElement.getTarget());
        }
    }

    @Override
    public void activate() {
        for (SiriusDecorationDescriptorProvider siriusDecorationDescriptorProvider : decorationDescriptorProviders) {
            siriusDecorationDescriptorProvider.activate(getDecoratorTarget(), this, editPart);
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        for (SiriusDecorationDescriptorProvider siriusDecorationDescriptorProvider : decorationDescriptorProviders) {
            siriusDecorationDescriptorProvider.deactivate(this, editPart);
        }
        removeDecorations();
    }

    @Override
    public void refresh() {
        DslCommonPlugin.PROFILER.startWork(DECORATOR_REFRESH);
        removeDecorations();

        if (editPart == null || editPart.getParent() == null || editPart.getViewer() == null) {
            return;
        }
        if (editPart instanceof IDiagramElementEditPart) {
            IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) editPart;
            Map<Position, List<DecorationDescriptor>> positionToDecorators = new HashMap<>();

            // @formatter:off
            List<DecorationDescriptor> decorationDescriptors = decorationDescriptorProviders.stream()
                    .filter(provider -> provider.provides(diagramElementEditPart))
                    .flatMap(provider -> provider.getDecorationDescriptors(diagramElementEditPart, session).stream())
                    .collect(Collectors.toList());
            // @formatter:on

            if (!decorationDescriptors.isEmpty()) {
                // compute the margin
                int margin = editPart.getChildren().stream().filter(IBorderedShapeEditPart.class::isInstance).findFirst().isPresent() ? MARGIN_FOR_BORDERNODE : DEFAULT_MARGIN;

                if (editPart instanceof AbstractConnectionEditPart) {
                    // put all decorations at the same central position
                    positionToDecorators.put(Position.CENTER_LITERAL, decorationDescriptors);
                    for (DecorationDescriptor decoDesc : decorationDescriptors) {
                        decoDesc.setDistributionDirection(DecorationDistributionDirection.HORIZONTAL);
                    }

                    Map<Position, IFigure> figureAtPosition = computeGroupDecorationsFigure(positionToDecorators, margin);
                    if (figureAtPosition != null) {
                        boolean isPrintableDeco = isPrintableDecoration(positionToDecorators.get(Position.CENTER_LITERAL));
                        addDecoration(getDecoratorTarget().addConnectionDecoration(figureAtPosition.get(Position.CENTER_LITERAL), 50, !isPrintableDeco));
                    }
                } else {
                    for (DecorationDescriptor decorationDescriptor : decorationDescriptors) {
                        Position position = decorationDescriptor.getPosition();
                        List<DecorationDescriptor> list = positionToDecorators.get(position);
                        if (list == null) {
                            list = Lists.newArrayList(decorationDescriptor);
                            positionToDecorators.put(position, list);
                        } else {
                            list.add(decorationDescriptor);
                        }
                    }

                    Map<Position, IFigure> figureAtPosition = computeGroupDecorationsFigure(positionToDecorators, margin);
                    if (figureAtPosition != null) {
                        for (Position position : figureAtPosition.keySet()) {
                            boolean isPrintableDeco = isPrintableDecoration(positionToDecorators.get(position));
                            addDecoration(getDecoratorTarget().addShapeDecoration(figureAtPosition.get(position), getGMFPosition(position), -margin, !isPrintableDeco));
                        }
                    }
                }
            }
        }
        DslCommonPlugin.PROFILER.stopWork(DECORATOR_REFRESH);

    }

    /**
     * The set of decorations is considered printable if there is at least one printable DecorationDescriptor and if the
     * global printDecoration is true.
     * 
     * @param decorationDescs
     * @return
     */
    private boolean isPrintableDecoration(List<DecorationDescriptor> decorationDescs) {
        boolean atLeastOnePrintableDecorator = decorationDescs.stream().filter(deco -> deco.isPrintable()).findFirst().isPresent();
        return printDecoration && atLeastOnePrintableDecorator;
    }

    private Map<Position, IFigure> computeGroupDecorationsFigure(Map<Position, List<DecorationDescriptor>> decosDescAtPosition, int margin) {
        // order the decoration for each position
        orderDecorationDescriptor(decosDescAtPosition);

        // initialize group decoration figure
        Dimension figureDimension = getFigureDimension(margin);

        Map<Position, Rectangle> groupBoundsAtPosition = initializeDisplayedDecoratorsGroup(decosDescAtPosition, figureDimension);

        // create a figure representing a group of decorations pour each
        // position
        Map<Position, List<Rectangle>> decosBoundsAtPosition = Maps.newHashMap();
        Map<Rectangle, List<DecorationDescriptor>> decosBoundsToDecoDescriptors = Maps.newIdentityHashMap();
        Map<Position, Map<DecorationDistributionDirection, List<Rectangle>>> decosBoundsPerDistribDirectionAtPosition = Maps.newHashMap();
        for (Position position : groupBoundsAtPosition.keySet()) {
            Map<DecorationDistributionDirection, List<Rectangle>> decoBBPerDistribDirection = Maps.newHashMap();
            decosBoundsPerDistribDirectionAtPosition.put(position, decoBBPerDistribDirection);
            List<Rectangle> brotherDecosBounds = Lists.newArrayList();
            List<Rectangle> decosHoriz = Lists.newArrayList();
            List<Rectangle> decosVert = Lists.newArrayList();
            decoBBPerDistribDirection.put(DecorationDistributionDirection.HORIZONTAL, decosHoriz);
            decoBBPerDistribDirection.put(DecorationDistributionDirection.VERTICAL, decosVert);

            // initialize group figure at position
            Rectangle groupBounds = groupBoundsAtPosition.get(position);

            List<DecorationDescriptor> decoDescs = decosDescAtPosition.get(position);
            for (DecorationDescriptor decorationDescriptor : decoDescs) {
                IFigure decorationFigure = getDecorationFigure(decorationDescriptor, false);
                Rectangle decorationBounds = decorationFigure.getBounds();
                decorationBounds.x = 0;
                decorationBounds.y = 0;

                // update decoration figure position relative to group figure
                DecorationDistributionDirection distributionDirection = decorationDescriptor.getDistributionDirection();
                if (distributionDirection.equals(DecorationDistributionDirection.HORIZONTAL)) {
                    addHorizontalDecoration(groupBounds, decorationBounds, decosHoriz, decosVert, position);
                } else if (distributionDirection.equals(DecorationDistributionDirection.VERTICAL)) {
                    addVerticalDecoration(groupBounds, decorationBounds, decosHoriz, decosVert, position);
                }

                // update group dimension according to contained decoration
                // figures
                brotherDecosBounds.add(decorationBounds);
                decosBoundsToDecoDescriptors.put(decorationBounds, Lists.newArrayList(decorationDescriptor));

                updateGroupBoundsSize(groupBounds, brotherDecosBounds);
            }
            updateGroupBoundsLocation(groupBounds, position, figureDimension);
            decosBoundsAtPosition.put(position, brotherDecosBounds);
        }

        List<Position> positionWithListDecoration = null;
        if (!DiagramUIPlugin.getPlugin().getDynamicPreferences().authorizeDecorationOverlap()) {
            // Merge decorations in group according to available space
            positionWithListDecoration = mergeDecorationsInGoups(figureDimension, groupBoundsAtPosition, decosBoundsAtPosition, decosBoundsToDecoDescriptors, decosDescAtPosition);

            // Merge group according to available space
            mergeGroups(figureDimension, groupBoundsAtPosition, decosBoundsAtPosition, decosBoundsToDecoDescriptors, decosDescAtPosition, positionWithListDecoration);
        }

        // create figure
        Map<Position, IFigure> figureAtPosition = createFigure(groupBoundsAtPosition, decosBoundsAtPosition, decosBoundsToDecoDescriptors, positionWithListDecoration);

        return figureAtPosition;
    }

    /**
     * Get the figure dimension reduced with the margin used to display decorations.
     * 
     * @return the figure dimension
     */
    private Dimension getFigureDimension(int margin) {
        Dimension figureDimension = editPart.getFigure().getBounds().getSize().getCopy();
        // When resizing the node the figure is not painted yet so we get the
        // resized width or height from the GMF node
        if (editPart.getModel() instanceof Node) {
            LayoutConstraint layoutConstraint = ((Node) editPart.getModel()).getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                int width = ((Bounds) layoutConstraint).getWidth();
                int height = ((Bounds) layoutConstraint).getHeight();
                if (width > 0) {
                    figureDimension.width = width;
                }
                if (height > 0) {
                    figureDimension.height = height;
                }
            }
        }

        // remove the margin
        figureDimension.width = figureDimension.width - 2 * margin >= 0 ? figureDimension.width - 2 * margin : 0;
        figureDimension.height = figureDimension.height - 2 * margin >= 0 ? figureDimension.height - 2 * margin : 0;

        return figureDimension;
    }

    private Map<Position, IFigure> createFigure(Map<Position, Rectangle> groupBoundsAtPosition, Map<Position, List<Rectangle>> decosBoundsAtPosition,
            Map<Rectangle, List<DecorationDescriptor>> decosBoundsToDecoDescriptors, List<Position> positionWithListDecoration) {
        Map<Position, IFigure> figureAtPosition = Maps.newHashMap();
        for (Position position : groupBoundsAtPosition.keySet()) {
            // create groupFigure
            Rectangle groupBounds = groupBoundsAtPosition.get(position);
            Figure groupFigure = new Figure();
            groupFigure.setSize(groupBounds.getSize());
            figureAtPosition.put(position, groupFigure);

            // create contained figure
            List<Rectangle> decosBounds = decosBoundsAtPosition.get(position);
            for (Rectangle rectangle : decosBounds) {
                List<DecorationDescriptor> decoDescs = decosBoundsToDecoDescriptors.get(rectangle);
                IFigure decorationFigure = null;
                if (positionWithListDecoration == null || !positionWithListDecoration.contains(position)) {
                    decorationFigure = getDecorationFigure(decoDescs.get(0), true);
                    decorationFigure.setBounds(rectangle);
                } else {
                    // create a list decoration figure
                    decorationFigure = new ListDecorationFigure(decoDescs);
                    decorationFigure.setBounds(rectangle);

                }
                groupFigure.add(decorationFigure);
            }
        }
        return figureAtPosition;
    }

    private void mergeGroups(Dimension figureDimension, Map<Position, Rectangle> groupBoundsAtPosition, Map<Position, List<Rectangle>> decosBoundsAtPosition,
            Map<Rectangle, List<DecorationDescriptor>> decosBoundsToDecoDescriptors, Map<Position, List<DecorationDescriptor>> positionToDecorators, List<Position> positionWithListDecoration) {
        List<Position> positionsToTest = Lists.newArrayList(groupBoundsAtPosition.keySet());

        while (!positionsToTest.isEmpty()) {
            Position position = positionsToTest.get(0);
            positionsToTest.remove(position);

            // check if the group bounding box overlaps another one
            Rectangle groupBounds = groupBoundsAtPosition.get(position);
            Iterator<Position> iterator = positionsToTest.iterator();
            while (iterator.hasNext()) {
                Position otherPosition = iterator.next();
                if (groupBounds.intersects(groupBoundsAtPosition.get(otherPosition))) {
                    iterator.remove();

                    // If the group is not a list decorator yet, we create one
                    if (!positionWithListDecoration.contains(position)) {
                        positionWithListDecoration.add(position);
                        decosBoundsAtPosition.get(position).clear();
                        Rectangle listDecoBounds = new Rectangle(0, 0, LIST_DECORATION_SIZE, LIST_DECORATION_SIZE);
                        updateGroupBoundsSize(groupBounds, Lists.newArrayList(listDecoBounds));
                        updateGroupBoundsLocation(groupBounds, position, figureDimension);
                        decosBoundsAtPosition.get(position).add(listDecoBounds);

                        decosBoundsToDecoDescriptors.put(listDecoBounds, positionToDecorators.get(position));
                    }

                    // transfer all decorations of overlapping group into the
                    // overlapped group
                    Rectangle existingListDecoBounds = decosBoundsAtPosition.get(position).get(0);
                    decosBoundsToDecoDescriptors.get(existingListDecoBounds).addAll(positionToDecorators.get(otherPosition));

                    groupBoundsAtPosition.remove(otherPosition);
                    decosBoundsAtPosition.remove(otherPosition);
                    positionToDecorators.remove(otherPosition);
                    positionWithListDecoration.remove(otherPosition);
                }
            }
        }

    }

    private List<Position> mergeDecorationsInGoups(Dimension figureDimension, Map<Position, Rectangle> groupBoundsAtPosition, Map<Position, List<Rectangle>> decosBoundsAtPosition,
            Map<Rectangle, List<DecorationDescriptor>> decosBoundsToDecoDescriptors, Map<Position, List<DecorationDescriptor>> decosDescAtPosition) {
        List<Position> positionWithListDecoration = Lists.newArrayList();
        Collection<Rectangle> groupBBsToCompareOverlapWith = Lists.newArrayList(groupBoundsAtPosition.values());
        for (Position position : groupBoundsAtPosition.keySet()) {
            Rectangle groupBounds = groupBoundsAtPosition.get(position);
            // check if the group bounding box overlaps the diagram element
            // figure
            boolean overlapFigure = false;
            List<Rectangle> overlappingGroupBBs = Lists.newArrayList();
            if (groupBounds.width > figureDimension.width || groupBounds.height > figureDimension.height()) {
                overlapFigure = true;
            } else {
                // check if the group bounding box overlaps another one
                for (Rectangle bounds : groupBBsToCompareOverlapWith) {
                    if (groupBounds.intersects(bounds) && !groupBounds.equals(bounds)) {
                        overlappingGroupBBs.add(bounds);
                    }
                }
            }
            if (overlappingGroupBBs.size() > 0 || overlapFigure) {
                // merge decorations in group until there is no more overlap
                if (groupBounds.height > LIST_DECORATION_SIZE || groupBounds.width > LIST_DECORATION_SIZE) {
                    positionWithListDecoration.add(position);
                    decosBoundsAtPosition.get(position).clear();
                    Rectangle listDecoBounds = new Rectangle(0, 0, LIST_DECORATION_SIZE, LIST_DECORATION_SIZE);
                    updateGroupBoundsSize(groupBounds, Lists.newArrayList(listDecoBounds));
                    updateGroupBoundsLocation(groupBounds, position, figureDimension);
                    decosBoundsAtPosition.get(position).add(listDecoBounds);
                    decosBoundsToDecoDescriptors.put(listDecoBounds, decosDescAtPosition.get(position));
                }
            } else {
                groupBBsToCompareOverlapWith.remove(groupBounds);
            }
        }
        return positionWithListDecoration;
    }

    private Map<Position, Rectangle> initializeDisplayedDecoratorsGroup(Map<Position, List<DecorationDescriptor>> positionToDecorators, Dimension figureDimension) {
        Map<Position, Rectangle> groupBoundsAtPosition = Maps.newHashMap();
        List<Position> positions = Lists.newArrayList(positionToDecorators.keySet());
        Collections.sort(positions, (p1, p2) -> {
            // We display group starting from center, then west, turning clock_wise ending with south west
            int p1Priority = getPositionPriority(p1);
            int p2Priority = getPositionPriority(p2);

            return p2Priority - p1Priority;
        });
        // initialize group bounding box at location
        for (Position position : positions) {
            Rectangle groupBounds = initializeDecorationsRelativeBoundsAtPosition(position, figureDimension);
            groupBoundsAtPosition.put(position, groupBounds);
        }

        return groupBoundsAtPosition;
    }

    private int getPositionPriority(Position position) {
        int priority = 0;
        switch (position.getValue()) {
        case Position.SOUTH_WEST:
            priority = 1;
            break;
        case Position.SOUTH:
            priority = 2;
            break;
        case Position.SOUTH_EAST:
            priority = 3;
            break;
        case Position.EAST:
            priority = 4;
            break;
        case Position.NORTH_EAST:
            priority = 5;
            break;
        case Position.NORTH:
            priority = 6;
            break;
        case Position.NORTH_WEST:
            priority = 7;
            break;
        case Position.WEST:
            priority = 8;
            break;
        case Position.CENTER:
            priority = 9;
            break;
        default:
            break;
        }
        return priority;
    }

    private void addVerticalDecoration(Rectangle groupBounds, Rectangle decorationBounds, List<Rectangle> decosHoriz, List<Rectangle> decosVert, Position position) {
        Rectangle previousDecoBoundsInTheDistribDirection = decosVert.size() > 0 ? decosVert.get(decosVert.size() - 1) : decosHoriz.size() > 0 ? decosHoriz.get(0) : null;
        List<Rectangle> brotherDecosBounds = Lists.newArrayList(decosHoriz);
        brotherDecosBounds.addAll(decosVert);

        switch (position.getValue()) {
        case Position.NORTH_WEST:
        case Position.WEST:
        case Position.NORTH_EAST:
        case Position.EAST:
            // position decoration on the bottom of the previous one
            // in the vertical distribution direction
            if (previousDecoBoundsInTheDistribDirection != null) {
                decorationBounds.y = previousDecoBoundsInTheDistribDirection.y + previousDecoBoundsInTheDistribDirection.height + SPACE;
            }
            break;
        case Position.SOUTH_WEST:
        case Position.SOUTH_EAST:
            // position decoration on the top to previous one in
            // the vertical distribution direction. Then shift all
            // existing decoration to the bottom
            if (previousDecoBoundsInTheDistribDirection != null) {
                int yShift = previousDecoBoundsInTheDistribDirection.y - decorationBounds.height;
                decorationBounds.y = Math.max(0, yShift);
                int yShiftExistingFigureInGroup = Math.max(0, -yShift);
                for (Rectangle decoBounds : brotherDecosBounds) {
                    decoBounds.y = decoBounds.y + SPACE + yShiftExistingFigureInGroup;
                }
            }
            break;
        default:
            break;
        }

        switch (position.getValue()) {
        case Position.NORTH_EAST:
        case Position.EAST:
        case Position.SOUTH_EAST:
            // shift all existing decoration to stick to the right
            int xShift = groupBounds.width - decorationBounds.width;
            decorationBounds.x = Math.max(0, xShift);
            int xShiftExistingFigureInGroup = Math.max(0, -xShift);
            for (Rectangle decoBounds : brotherDecosBounds) {
                decoBounds.x = decoBounds.x + xShiftExistingFigureInGroup;
            }
            break;
        default:
            break;
        }

        decosVert.add(decorationBounds);
    }

    private void addHorizontalDecoration(Rectangle groupBounds, Rectangle decorationBounds, List<Rectangle> decosHoriz, List<Rectangle> decosVert, Position position) {
        Rectangle previousDecoBoundsInTheDistribDirection = decosHoriz.size() > 0 ? decosHoriz.get(decosHoriz.size() - 1) : decosVert.size() > 0 ? decosVert.get(0) : null;
        List<Rectangle> brotherDecosBounds = Lists.newArrayList(decosHoriz);
        brotherDecosBounds.addAll(decosVert);
        switch (position.getValue()) {
        case Position.NORTH_WEST:
        case Position.NORTH:
        case Position.SOUTH_WEST:
        case Position.SOUTH:
        case Position.CENTER:
            // position decoration on the right of the previous one
            // in the horizontal distribution direction
            if (previousDecoBoundsInTheDistribDirection != null) {
                decorationBounds.x = previousDecoBoundsInTheDistribDirection != null ? previousDecoBoundsInTheDistribDirection.x + SPACE + previousDecoBoundsInTheDistribDirection.width : 0;
            }
            break;
        case Position.NORTH_EAST:
        case Position.SOUTH_EAST:
            // position decoration on the left to previous one in
            // the horizontal distribution direction. Then shift all
            // existing decoration to the right
            if (previousDecoBoundsInTheDistribDirection != null) {
                int xShift = previousDecoBoundsInTheDistribDirection.x - decorationBounds.width;
                decorationBounds.x = Math.max(0, xShift);
                int xShiftExistingFigureInGroup = Math.max(0, -xShift);
                for (Rectangle decoBounds : brotherDecosBounds) {
                    decoBounds.x = decoBounds.x + SPACE + xShiftExistingFigureInGroup;
                }
            }
            break;
        default:
            break;
        }

        switch (position.getValue()) {
        case Position.SOUTH_WEST:
        case Position.SOUTH:
        case Position.SOUTH_EAST:
            // shift all existing decoration to stick to the bottom
            int yShift = groupBounds.height - decorationBounds.height;
            decorationBounds.y = Math.max(0, yShift);
            int yShiftExistingFigureInGroup = Math.max(0, -yShift);
            for (Rectangle decoBounds : brotherDecosBounds) {
                decoBounds.y = decoBounds.y + yShiftExistingFigureInGroup;
            }
            break;
        default:
            break;
        }

        decosHoriz.add(decorationBounds);
    }

    private void orderDecorationDescriptor(Map<Position, List<DecorationDescriptor>> positionToDecorators) {
        for (Position position : positionToDecorators.keySet()) {
            List<DecorationDescriptor> decoDescriptors = positionToDecorators.get(position);
            Collections.sort(decoDescriptors, new Comparator<DecorationDescriptor>() {
                @Override
                public int compare(DecorationDescriptor d1, DecorationDescriptor d2) {
                    // First we display the decoration with higher prority
                    int diff = d1.getDisplayPriority().intValue() - d2.getDisplayPriority().intValue();
                    // sort with alphabetical order
                    if (diff == 0) {
                        diff = d1.getName().compareTo(d2.getName());
                    }
                    return diff;
                }
            });
        }
    }

    private IFigure getDecorationFigure(DecorationDescriptor decorationDescriptor, boolean withTooltip) {
        IFigure figure = null;
        IFigure innerFigure = null;
        Image decorationAsImage = decorationDescriptor.getDecorationAsImage();
        if (decorationAsImage != null) {
            IMapMode mm = MapModeUtil.getMapMode(editPart.getFigure());
            ImageFigureEx figureImage = new ImageFigureEx();
            figureImage.setImage(decorationAsImage);
            figureImage.setSize(mm.DPtoLP(decorationAsImage.getBounds().width), mm.DPtoLP(decorationAsImage.getBounds().height));
            innerFigure = figureImage;
        } else {
            innerFigure = decorationDescriptor.getDecorationAsFigure();
        }
        if (withTooltip) {
            if (innerFigure != null) {
                innerFigure.setLocation(new Point(0, 0));
                figure = new FigureWithLazyTooltip(decorationDescriptor);
                figure.setSize(innerFigure.getSize());
                // needed so that innerFigure is displayed relatively to its
                // father
                figure.setLayoutManager(new BorderLayout());
                figure.add(innerFigure, BorderLayout.LEFT);
            }
        } else {
            figure = innerFigure;
        }
        return figure;
    }

    private IFigure getDecorationFigureForTooltip(DecorationDescriptor decorationDescriptor) {
        IFigure figure = null;
        Image decorationAsImage = decorationDescriptor.getDecorationAsImage();
        if (decorationAsImage != null) {
            figure = new Label("", decorationAsImage); //$NON-NLS-1$ ;
        } else {
            figure = decorationDescriptor.getDecorationAsFigure();
        }
        return figure;
    }

    private IFigure getDecorationTooltip(DecorationDescriptor decorationDescriptor) {
        IFigure tooltipFigure = null;
        IFigure tooltipAsFigure = decorationDescriptor.getTooltipAsFigure();
        if (tooltipAsFigure != null) {
            tooltipFigure = tooltipAsFigure;
        } else {
            String tooltipAsString = decorationDescriptor.getTooltipAsString();
            if (!StringUtil.isEmpty(tooltipAsString)) {
                tooltipFigure = new Label(tooltipAsString);
            }
        }
        return tooltipFigure;
    }

    /**
     * A decoration figure which tooltip is computed when required the first time.
     * 
     * @author lfasani
     *
     */
    private class FigureWithLazyTooltip extends Figure {
        private DecorationDescriptor decoDescriptor;

        FigureWithLazyTooltip(DecorationDescriptor decorationDescriptor) {
            this.decoDescriptor = decorationDescriptor;
        }

        @SuppressWarnings("deprecation")
        @Override
        public IFigure getToolTip() {
            if (toolTip == null) {
                toolTip = getDecorationTooltip(decoDescriptor);
            }
            return toolTip;
        }
    }

    /**
     * A decoration figure which represents a list of decorations. The tooltip is computed when required the first time.
     * 
     * @author lfasani
     *
     */
    private class ListDecorationFigure extends RectangleFigure {
        private List<DecorationDescriptor> decoDescriptors;

        ListDecorationFigure(List<DecorationDescriptor> decoDescriptors) {
            super();
            this.decoDescriptors = decoDescriptors;
        }

        @Override
        public void paintFigure(Graphics graphics) {
            super.paintFigure(graphics);
            Rectangle bounds = getBounds();
            graphics.setForegroundColor(ColorConstants.black);
            graphics.drawText("...", new Point(bounds.x + 3, bounds.y - bounds.height / 2)); //$NON-NLS-1$
        }

        @SuppressWarnings("deprecation")
        @Override
        public IFigure getToolTip() {
            if (toolTip == null) {
                Figure composite = new Figure();
                GridLayout fl = new GridLayout(2, false);
                fl.horizontalSpacing = 1;
                fl.verticalSpacing = 1;
                composite.setLayoutManager(fl);
                for (DecorationDescriptor decoDescriptor : decoDescriptors) {
                    IFigure decorationFigureForTooltip = getDecorationFigureForTooltip(decoDescriptor);
                    if (decorationFigureForTooltip != null) {
                        composite.add(decorationFigureForTooltip);
                        IFigure decorationTooltip = getDecorationTooltip(decoDescriptor);
                        if (decorationTooltip != null) {
                            composite.add(getDecorationTooltip(decoDescriptor));
                        } else {
                            composite.add(new Figure());
                        }
                    }
                }
                toolTip = composite;
            }
            return toolTip;
        }
    }

    private Rectangle initializeDecorationsRelativeBoundsAtPosition(Position position, Dimension figureDimension) {
        int x = 0;
        int y = 0;
        switch (position.getValue()) {
        case Position.NORTH:
        case Position.CENTER:
        case Position.SOUTH:
            x = figureDimension.width / 2;
            break;

        case Position.NORTH_EAST:
        case Position.EAST:
        case Position.SOUTH_EAST:
            x = figureDimension.width;
            break;
        default:
            break;
        }

        switch (position.getValue()) {
        case Position.WEST:
        case Position.CENTER:
        case Position.EAST:
            y = figureDimension.height / 2;
            break;

        case Position.SOUTH_WEST:
        case Position.SOUTH:
        case Position.SOUTH_EAST:
            y = figureDimension.height;
            break;
        default:
            break;
        }

        return new Rectangle(x, y, 0, 0);
    }

    private void updateGroupBoundsSize(Rectangle groupBounds, Iterable<Rectangle> decoBBsInGroup) {
        groupBounds.width = 0;
        groupBounds.height = 0;
        for (Rectangle decoBound : decoBBsInGroup) {
            groupBounds.width = Math.max(groupBounds.width, decoBound.x + decoBound.width);
            groupBounds.height = Math.max(groupBounds.height, decoBound.y + decoBound.height);
        }
    }

    /**
     * Define position of the group relatively to the edit part figure bounding box.
     * 
     * @param groupBounds
     * @param position
     */
    private void updateGroupBoundsLocation(Rectangle groupBounds, Position position, Dimension figureDimension) {
        switch (position.getValue()) {
        case Position.NORTH:
        case Position.CENTER:
        case Position.SOUTH:
            groupBounds.x = (figureDimension.width - groupBounds.width) / 2;
            break;

        case Position.NORTH_EAST:
        case Position.EAST:
        case Position.SOUTH_EAST:
            groupBounds.x = figureDimension.width - groupBounds.width;
            break;
        default:
            break;
        }

        switch (position.getValue()) {
        case Position.WEST:
        case Position.CENTER:
        case Position.EAST:
            groupBounds.y = (figureDimension.height - groupBounds.height) / 2;
            break;

        case Position.SOUTH_WEST:
        case Position.SOUTH:
        case Position.SOUTH_EAST:
            groupBounds.y = figureDimension.height - groupBounds.height;
            break;
        default:
            break;
        }
    }

    /**
     * Get the IDecorations of this DescribedDecorator.
     * 
     * @return Returns the decorations.
     */
    public List<IDecoration> getDecorations() {
        return decorations;
    }

    /**
     * Add an IDecoration to this DescribedDecorator.
     * 
     * @param decoration
     *            IDecoration to add.
     */
    public void addDecoration(final IDecoration decoration) {
        decorations.add(decoration);
    }

    private void removeDecorations() {
        for (final IDecoration decoration : decorations) {
            if (decoration instanceof IFigure && ((IFigure) decoration).getParent() != null) {
                ((IFigure) decoration).getParent().remove((IFigure) decoration);
            }

            final GraphicalEditPart ownerEditPart = (GraphicalEditPart) getDecoratorTarget().getAdapter(GraphicalEditPart.class);
            if (ownerEditPart != null && ownerEditPart.getRoot() != null && ownerEditPart.getViewer() != null) {
                ownerEditPart.getViewer().getVisualPartMap().remove(decoration);
            }
        }
        decorations = new ArrayList<IDecoration>();
    }

    /**
     * Converts the position to a Direction.
     * 
     * @param direction
     * @return the int as defined in PositionConstant
     */
    private Direction getGMFPosition(final Position position) {
        Direction direction = Direction.SOUTH_WEST;
        switch (position.getValue()) {
        case Position.CENTER:
            direction = Direction.CENTER;
            break;
        case Position.NORTH:
            direction = Direction.NORTH;
            break;
        case Position.SOUTH:
            direction = Direction.SOUTH;
            break;
        case Position.WEST:
            direction = Direction.WEST;
            break;
        case Position.EAST:
            direction = Direction.EAST;
            break;
        case Position.NORTH_EAST:
            direction = Direction.NORTH_EAST;
            break;
        case Position.NORTH_WEST:
            direction = Direction.NORTH_WEST;
            break;
        case Position.SOUTH_EAST:
            direction = Direction.SOUTH_EAST;
            break;
        case Position.SOUTH_WEST:
            direction = Direction.SOUTH_WEST;
            break;
        default:
            break;
        }
        return direction;
    }
}
