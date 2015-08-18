/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.core.util.Proxy;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.BorderItemLocatorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.LayoutUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Useful operations.
 * 
 * @author ymortier
 */
public final class LayoutUtils {

    /**
     * Scale factor for width and height from diagram node size to draw2d
     * bounds.
     */
    public static final int SCALE = 10;

    /**
     * The default container dimension.
     */
    public static final Dimension DEFAULT_CONTAINER_DIMENSION = new Dimension(150, 70);

    /**
     * The default container dimension (since 4.0).
     */
    public static final Dimension NEW_DEFAULT_CONTAINER_DIMENSION = new Dimension(40, 40);

    /**
     * Default width.
     */
    public static final int DEFAULT_WIDTH = 10;

    private static final int TOP_MARGIN = 50;

    private static final int LEFT_MARGIN = 50;

    private static Map<IFigure, List<IFigure>> dummys = new HashMap<IFigure, List<IFigure>>();

    private static Map<IFigure, Integer> dummysStack = new HashMap<IFigure, Integer>();

    /**
     * Avoid instantiation.
     */
    private LayoutUtils() {

    }

    /**
     * Return a valid location for the specified border item view.
     * 
     * @param borderView
     *            the border item view.
     * @param proposedLocation
     *            the proposed location.
     * @param proposedDimension
     *            the proposed dimension.
     * @param owner
     *            the owner.
     * @param borderItemContainer
     *            the container figure.
     * @param mainFigure
     *            the figure that contains border items.
     * @return a valid location for the specified border item view.
     */
    public static Rectangle getValidLocation(final DDiagramElement borderView, final Point proposedLocation, final Dimension proposedDimension, final DDiagramElement owner,
            final IFigure borderItemContainer, final IFigure mainFigure) {
        if (mainFigure.getBounds().getSize().height == 0 && mainFigure.getBounds().getSize().width == 0 && owner instanceof DNode) {
            final Dimension defaultDimension = new DNodeQuery((DNode) owner).getDefaultDimension();
            mainFigure.getBounds().height = defaultDimension.height;
            mainFigure.getBounds().width = defaultDimension.width;
        } else if (mainFigure.getBounds().getSize().height == 0 && mainFigure.getBounds().getSize().width == 0 && owner instanceof DDiagramElementContainer) {
            final Dimension defaultDimension = LayoutUtils.DEFAULT_CONTAINER_DIMENSION;
            mainFigure.getBounds().height = defaultDimension.height;
            mainFigure.getBounds().width = defaultDimension.width;
        }
        final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(owner.getDiagramElementMapping(), ((DStylizable) owner).getStyle());
        final BorderItemLocatorProvider locatorProvider = styleConfiguration.getBorderItemLocatorProvider();
        final IFigure dummyFigure = new Figure();
        final IBorderItemLocator locator = locatorProvider.getBorderItemLocator(mainFigure, owner, borderView);
        final Rectangle constraint = new Rectangle(proposedLocation, proposedDimension);
        locator.setConstraint(constraint);
        dummyFigure.setVisible(true);
        borderItemContainer.add(dummyFigure);
        final Rectangle rect = new Rectangle(constraint);
        rect.translate(mainFigure.getBounds().getLocation().x, mainFigure.getBounds().getLocation().y);
        mainFigure.translateToAbsolute(rect);
        dummyFigure.setBounds(rect);
        final Rectangle realLocation = locator.getValidLocation(rect, dummyFigure);
        final Point parentOrigin = mainFigure.getBounds().getTopLeft();
        final Dimension d = realLocation.getTopLeft().getDifference(parentOrigin);
        final Point location = new Point(d.width, d.height);
        realLocation.setLocation(location);

        // dummy
        final List<IFigure> dummysList = dummys.get(borderItemContainer);
        if (dummysList != null) {
            dummysList.add(dummyFigure);
        }

        locator.relocate(dummyFigure);
        realLocation.setLocation(dummyFigure.getBounds().getLocation());
        realLocation.setSize(proposedDimension);

        return new Rectangle(realLocation).getTranslated(-parentOrigin.x, -parentOrigin.y);

    }

    /**
     * Indicates that the figure will receive dummy figures.
     * 
     * @param figure
     *            the figure.
     */
    public static void prepareFigureForDummyAdds(final IFigure figure) {
        if (!dummys.containsKey(figure)) {
            dummys.put(figure, new LinkedList<IFigure>());
        } else {
            Integer currentValue = dummysStack.get(figure);
            if (currentValue == null) {
                currentValue = Integer.valueOf(0);
            }
            currentValue = Integer.valueOf(currentValue.intValue() + 1);
            dummysStack.put(figure, currentValue);
        }
    }

    /**
     * Remove all children that has been added since the last call of
     * {@link #prepareFigureForDummyAdds(IFigure)} for the specified figure.
     * 
     * @param figure
     *            the figure.
     */
    public static void releaseDummys(final IFigure figure) {
        if (dummysStack.containsKey(figure)) {
            Integer currentValue = dummysStack.get(figure);
            currentValue = Integer.valueOf(currentValue.intValue() - 1);
            if (currentValue.intValue() == 0) {
                dummysStack.remove(figure);
            } else {
                dummysStack.put(figure, currentValue);
            }
        } else {
            Collection<IFigure> dummysChildren = dummys.get(figure);
            if (dummysChildren == null) {
                dummysChildren = Collections.emptyList();
            }
            final Iterator<IFigure> iterRemove = dummysChildren.iterator();
            while (iterRemove.hasNext()) {
                final IFigure next = iterRemove.next();
                figure.remove(next);
            }
            dummys.remove(figure);
        }
    }

    /**
     * Return the default dimension according to the specified descriptor.
     * 
     * @param viewDescriptor
     *            the descriptor.
     * @return the default dimension according to the specified descriptor.
     */
    public static Dimension getDefaultDimension(final CreateViewRequest.ViewDescriptor viewDescriptor) {
        Dimension result = new Dimension(-1, -1);
        final IAdaptable adapt = viewDescriptor.getElementAdapter();
        if (adapt instanceof Proxy) {
            final Object element = ((Proxy) adapt).getRealObject();
            if (element instanceof DNode) {
                result = new DNodeQuery((DNode) element).getDefaultDimension();
            }
        }
        return result;
    }

    /**
     * Initializes the layout of the diagram of <code>target</code> with the
     * layout of the the diagram <code>source</code>.
     * 
     * @param source
     *            the source diagram.
     * @param target
     *            the target diagram.
     */
    public static void initializeDiagramLayout(final Diagram source, final DRepresentation target) {
        //
        // Do refresh
        DialectManager.INSTANCE.refresh(target, new NullProgressMonitor());

        final Shell shell = new Shell();
        //
        // Initializes diagram.
        final DiagramEditPart diagramEditPart = LayoutUtils.createDiagramEditPart(target, shell);
        final Diagram gmfTarget;
        if (diagramEditPart == null) {
            gmfTarget = null;
        } else {
            gmfTarget = diagramEditPart.getDiagramView();
        }
        //
        // dispose ui resources.
        if (diagramEditPart != null) {
            diagramEditPart.deactivate();
        }

        Display.getCurrent().asyncExec(new Runnable() {
            public void run() {
                shell.dispose();
            }
        });
        //
        // Maps a real semantic element with only one view.
        final Map<EObject, List<View>> realSemanticToView = new HashMap<EObject, List<View>>();
        //
        // initializes view bounds
        if (gmfTarget != null) {
            final List<View> sourceCandidates = new LinkedList<View>();
            LayoutUtils.computeSourceCandidates(source, sourceCandidates);
            sourceCandidates.addAll(source.getEdges());
            LayoutUtils.initializeBounds(source, gmfTarget, realSemanticToView, sourceCandidates);
            for (final Edge edge : (Iterable<Edge>) gmfTarget.getEdges()) {
                LayoutUtils.initializeBounds(source, edge, realSemanticToView, sourceCandidates);
            }
            LayoutUtils.optimizeLayout(gmfTarget);
            LayoutUtils.moveToUpperLeftCorner(gmfTarget);
        }
    }

    private static void moveToUpperLeftCorner(final View gmfTarget) {
        final Dimension minDistanceToUpperLeftCorner = new Dimension(-1, -1);
        for (final View view : (Iterable<View>) gmfTarget.getChildren()) {
            if (view instanceof Node) {
                final Node node = (Node) view;
                if (node.getLayoutConstraint() instanceof Location) {
                    final Location location = (Location) node.getLayoutConstraint();
                    minDistanceToUpperLeftCorner.width = minDistanceToUpperLeftCorner.width < 0 || location.getX() < minDistanceToUpperLeftCorner.width ? location.getX()
                            : minDistanceToUpperLeftCorner.width;
                    minDistanceToUpperLeftCorner.height = minDistanceToUpperLeftCorner.height < 0 || location.getY() < minDistanceToUpperLeftCorner.height ? location.getY()
                            : minDistanceToUpperLeftCorner.height;
                }
            }
        }
        final Dimension delta = new Dimension(0, 0);
        if (minDistanceToUpperLeftCorner.width > LEFT_MARGIN) {
            delta.width = minDistanceToUpperLeftCorner.width - LEFT_MARGIN;
        }
        if (minDistanceToUpperLeftCorner.height > TOP_MARGIN) {
            delta.height = minDistanceToUpperLeftCorner.height - TOP_MARGIN;
        }

        if (delta.height > 0 || delta.width > 0) {
            for (final View view : (Iterable<View>) gmfTarget.getChildren()) {
                if (view instanceof Node) {
                    final Node node = (Node) view;
                    if (node.getLayoutConstraint() instanceof Location) {
                        final Location location = (Location) node.getLayoutConstraint();
                        location.setX(location.getX() - delta.width);
                        location.setY(location.getY() - delta.height);
                    }
                }
            }
        }

    }

    private static void computeSourceCandidates(final View source, final List<View> candidates) {
        final EObject gmfPOVSemanticElement = ViewUtil.resolveSemanticElement(source);
        if (gmfPOVSemanticElement instanceof DSemanticDecorator && gmfPOVSemanticElement instanceof DDiagramElement) {
            candidates.add(source);
        }
        for (final View child : (Iterable<View>) source.getChildren()) {
            LayoutUtils.computeSourceCandidates(child, candidates);
        }
    }

    private static void initializeBounds(final Diagram source, final View target, final Map<EObject, List<View>> realSemanticToView, final List<View> sourceCandidates) {
        final EObject gmfPOVSemanticElement = ViewUtil.resolveSemanticElement(target);
        if (gmfPOVSemanticElement instanceof DDiagramElement && gmfPOVSemanticElement instanceof DSemanticDecorator) {
            final View sourceView = LayoutUtils.findSourceView(target, realSemanticToView, sourceCandidates);
            if (sourceView != null) {
                LayoutUtils.copyConstraints(sourceView, target);
            }
        }
        for (final View child : (Iterable<View>) target.getChildren()) {
            LayoutUtils.initializeBounds(source, child, realSemanticToView, sourceCandidates);
        }
    }

    /**
     * Copy constraints of source view and affect them to target view.
     * 
     * @param sourceView
     *            the source view
     * @param targetView
     *            the target view
     */
    public static void copyConstraints(final View sourceView, final View targetView) {
        if (sourceView instanceof Node && targetView instanceof Node) {
            final Node nodeSource = (Node) sourceView;
            final Node nodeTarget = (Node) targetView;
            final LayoutConstraint sourceConstraint = nodeSource.getLayoutConstraint();
            if (sourceConstraint != null) {
                nodeTarget.setLayoutConstraint(EcoreUtil.copy(sourceConstraint));
            }
        } else if (sourceView instanceof Edge && targetView instanceof Edge) {
            final Edge edgeSource = (Edge) sourceView;
            final Edge edgeTarget = (Edge) targetView;
            if (edgeSource.getBendpoints() != null) {
                edgeTarget.setBendpoints(EcoreUtil.copy(edgeSource.getBendpoints()));
            }
            if (edgeSource.getSourceAnchor() != null) {
                edgeTarget.setSourceAnchor(EcoreUtil.copy(edgeSource.getSourceAnchor()));
            }
            if (edgeSource.getTargetAnchor() != null) {
                edgeTarget.setTargetAnchor(EcoreUtil.copy(edgeSource.getTargetAnchor()));
            }
            final RoutingStyle rstyle = (RoutingStyle) edgeSource.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
            if (rstyle != null) {
                edgeTarget.getStyles().add(EcoreUtil.copy(rstyle));
            }
        }
    }

    private static View findSourceView(final View target, final Map<EObject, List<View>> realSemanticToView, final List<View> sourceCandidates) {
        View result = null;
        final EObject gmfPOVSemanticElement = ViewUtil.resolveSemanticElement(target);
        if (gmfPOVSemanticElement instanceof DDiagramElement && gmfPOVSemanticElement instanceof DSemanticDecorator) {
            final EObject targetRealSemanticElement = ((DSemanticDecorator) gmfPOVSemanticElement).getTarget();
            List<View> views = realSemanticToView.get(targetRealSemanticElement);
            if (views == null) {
                views = new ArrayList<View>(1);
            }
            // Search if one a the view is similar with target
            for (final View sourceView : views) {
                if (LayoutUtils.areSimilars(sourceView, target)) {
                    result = sourceView;
                    break;
                }
            }
            // If any, search in the sourceCandidates
            if (result == null) {
                for (final View candidate : sourceCandidates) {
                    if (LayoutUtils.areSimilars(candidate, target)) {
                        result = candidate;
                        break;
                    }
                }
                if (result != null) {
                    sourceCandidates.remove(result);
                    views.add(result);
                    realSemanticToView.put(targetRealSemanticElement, views);
                }
            }

            // if (result == null &&
            // !realSemanticToView.containsKey(targetRealSemanticElement)) {
            // for (final View candidate : sourceCandidates) {
            // if (areSimilars(candidate, target)) {
            // result = candidate;
            // break;
            // }
            // }
            // if (result != null) {
            // sourceCandidates.remove(result);
            // views.add(result);
            // realSemanticToView.put(targetRealSemanticElement, views);
            // }
            // }
        }
        return result;
    }

    private static DiagramEditPart createDiagramEditPart(final DRepresentation designerDiagram, final Shell shell) {
        if (designerDiagram instanceof DSemanticDiagram) {
            final DSemanticDiagram diag = (DSemanticDiagram) designerDiagram;

            final DiagramCreationUtil util = new DiagramCreationUtil(diag);
            if (!util.findAssociatedGMFDiagram()) {
                util.createNewGMFDiagram();
                SessionManager.INSTANCE.getSession(diag.getTarget()).getServices().putCustomData(CustomDataConstants.GMF_DIAGRAMS, diag, util.getAssociatedGMFDiagram());
            }

            final Diagram gmfDiag = util.getAssociatedGMFDiagram();

            if (gmfDiag != null) {
                final DiagramEditPartService tool = new DiagramEditPartService();
                final DiagramEditPart diagramEditPart = tool.createDiagramEditPart(gmfDiag, shell, new PreferencesHint("DView")); //$NON-NLS-1$
                diagramEditPart.refresh();

                // performs an arrange all for canonical views.
                LayoutUtil.arrange(diagramEditPart);

                // validate to have all nodes in the right position
                // flush the viewer to have all connections and ports
                return diagramEditPart;
            }
        }
        return null;
    }

    private static boolean areSimilars(final View source, final View target) {
        boolean result = false;
        // Check if the view type is the same
        if (source != null && target != null && source.eClass() == target.eClass()) {
            // Check if the representation is the same
            final EObject representationSource = ViewUtil.resolveSemanticElement(source);
            final EObject representationTarget = ViewUtil.resolveSemanticElement(target);

            if (representationSource != null && representationTarget != null && representationSource.eClass() == representationTarget.eClass()) {
                // Check if the semantic target is the same
                if (representationSource instanceof DSemanticDecorator && representationTarget instanceof DSemanticDecorator) {
                    final EObject semSource = ((DSemanticDecorator) representationSource).getTarget();
                    final EObject semTarget = ((DSemanticDecorator) representationTarget).getTarget();

                    if (semSource != null && semSource == semTarget) {
                        // Check if the source and the target is
                        boolean sourceIsLabel = false;
                        boolean targetIsLabel = false;
                        try {
                            sourceIsLabel = new ViewQuery(source).isForNameEditPart();
                        } catch (final NumberFormatException e) {
                            // do nothing
                        }
                        try {
                            targetIsLabel = new ViewQuery(target).isForNameEditPart();
                        } catch (final NumberFormatException e) {
                            // do nothing
                        }
                        // Test if the source and the target is the same type
                        // (label or not)
                        result = sourceIsLabel == targetIsLabel;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Optimize the layout of the view.
     * 
     * @param view
     *            the view to optimize.
     */
    private static void optimizeLayout(final View view) {
        for (final Object object : view.getChildren()) {
            if (object instanceof View) {
                LayoutUtils.optimizeLayout((View) object);
            }
        }
        final EObject gmfPOVSemanticElement = ViewUtil.resolveSemanticElement(view);
        if (gmfPOVSemanticElement instanceof DNodeContainer) {
            LayoutUtils.optimizeContainerLayout(view);
        }
    }

    /**
     * Optimize the layout of this container.
     * 
     * @param view
     *            the container to optimize.
     */
    private static void optimizeContainerLayout(final View view) {

        final Bounds containerBounds;
        if (view instanceof Node && ((Node) view).getLayoutConstraint() instanceof Bounds) {
            containerBounds = (Bounds) ((Node) view).getLayoutConstraint();
        } else {
            containerBounds = null;
        }

        if (containerBounds != null) {

            //
            // Gets the size of the container
            final Dimension minSize = new Dimension(-1, -1);
            //
            // Gets the compartment.
            View compartment = null;
            for (final Object object : view.getChildren()) {
                if (object instanceof View) {
                    final View child = (View) object;
                    final int id;
                    try {
                        id = Integer.parseInt(child.getType());
                        if (id == DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID || id == DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID) {
                            compartment = child;
                        }
                    } catch (final NumberFormatException nfe) {
                        // silent.
                    }
                }
            }

            if (compartment != null) {
                LayoutUtils.moveToUpperLeftCorner(compartment);
                for (final Object object : compartment.getChildren()) {
                    if (object instanceof Node) {
                        final Node child = (Node) object;
                        if (child.getLayoutConstraint() instanceof Bounds) {
                            final Bounds bounds = (Bounds) child.getLayoutConstraint();
                            final int height = bounds.getY() + bounds.getHeight() + TOP_MARGIN;
                            final int width = bounds.getWidth() + bounds.getX() + LEFT_MARGIN;
                            minSize.width = minSize.width < 0 || width > minSize.width ? width : minSize.width;
                            minSize.height = minSize.height < 0 || height > minSize.height ? height : minSize.height;
                        }
                    }
                }
            }

            final Dimension delta = new Dimension(0, 0);

            int height = containerBounds.getHeight();
            if (height != -1 && minSize.height > height) {
                delta.height = minSize.height - height;
                containerBounds.setHeight(minSize.height);
            }
            int width = containerBounds.getWidth();
            if (width != -1 && minSize.width > width) {
                delta.width = minSize.width - width;
                containerBounds.setWidth(minSize.width);
            }

            if (delta.width > 0 || delta.height > 0) {
                final View containerView = ViewUtil.getContainerView(view);
                if (containerView != null) {
                    for (final Object object : containerView.getChildren()) {
                        if (object instanceof Node) {
                            final Node child = (Node) object;
                            LayoutUtils.moveChild(containerBounds, delta, child);
                        }
                    }
                }
            }

        }

    }

    private static void moveChild(final Bounds containerBounds, final Dimension delta, final Node child) {
        if (child.getLayoutConstraint() instanceof Location) {
            final Location location = (Location) child.getLayoutConstraint();
            if (location.getX() > containerBounds.getX() && delta.width > 0) {
                location.setX(location.getX() + delta.width);
            }
            if (location.getY() > containerBounds.getY() && delta.height > 0) {
                location.setY(location.getY() + delta.height);
            }
        }
    }
}
