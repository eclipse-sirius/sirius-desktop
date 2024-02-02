/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.view;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.util.ObjectAdapter;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.LayoutHelper;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.layout.IInternalLayoutRunnable;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.layout.LayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutService;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.graphical.figures.SiriusLayoutHelper;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.BorderItemAwareLayoutProvider;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Manage the LayoutData during node creation or drag'n'drop.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public final class SiriusLayoutDataManagerImpl implements SiriusLayoutDataManager {

    /**
     * An adapter to mark the View as layout to default normal by the SiriusLayoutDataManager.
     */
    private static final Adapter LAYOUT_MARKER_ADAPTER = new Adapter() {

        @Override
        public void setTarget(final Notifier newTarget) {
        }

        @Override
        public void notifyChanged(final Notification notification) {
        }

        @Override
        public boolean isAdapterForType(final Object type) {
            return type instanceof SiriusLayoutDataManager;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

    };

    /**
     * An adapter to mark the View as layout by the SiriusLayoutDataManager.
     */
    private static final Adapter LAYOUT_MARKER_ADAPTER_ON_OPENING = new Adapter() {

        @Override
        public void setTarget(final Notifier newTarget) {
        }

        @Override
        public void notifyChanged(final Notification notification) {
        };

        @Override
        public boolean isAdapterForType(final Object type) {
            return type instanceof SiriusLayoutDataManager;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

    };

    /**
     * An adapter to mark the View as layout by the SiriusLayoutDataManager.
     */
    private static final Adapter CENTER_LAYOUT_MARKER_ADAPTER = new Adapter() {

        @Override
        public void setTarget(final Notifier newTarget) {
        }

        @Override
        public void notifyChanged(final Notification notification) {
        }

        @Override
        public boolean isAdapterForType(final Object type) {
            return type instanceof SiriusLayoutDataManager;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

    };

    /**
     * An adapter to mark the View as reference for center layout by the SiriusLayoutDataManager.
     * 
     * @see #getReferenceAdapterMarker()
     */
    private static final Adapter REFERENCE_LAYOUT_MARKER_ADAPTER = new Adapter() {

        @Override
        public void setTarget(final Notifier newTarget) {
        }

        @Override
        public void notifyChanged(final Notification notification) {
        }

        @Override
        public boolean isAdapterForType(final Object type) {
            return type instanceof SiriusLayoutDataManager;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

    };

    /**
     * An adapter to mark the View as layout with border node algorithm by the SiriusLayoutDataManager.
     */
    private static final Adapter BORDER_NODE_LAYOUT_MARKER_ADAPTER = new Adapter() {

        @Override
        public void setTarget(final Notifier newTarget) {
        }

        @Override
        public void notifyChanged(final Notification notification) {
        }

        @Override
        public boolean isAdapterForType(final Object type) {
            return type instanceof SiriusLayoutDataManager;
        }

        @Override
        public Notifier getTarget() {
            return null;
        }

    };

    /**
     * A list of layout data (the root can be an AbstractDNode or a DDiagram). The layout data are removed from this
     * list when all the layout data in its are consumed.
     */
    List<AbstractLayoutData> rootsLayoutData = new ArrayList<AbstractLayoutData>();

    /**
     * Predicate to filter created views to layout on diagram opening.
     */
    Predicate<View> predicate = new Predicate<View>() {

        @Override
        public boolean apply(View input) {
            return hasToArrange(input);
        }
    };

    private Map<Diagram, Set<View>> createdViewForLayoutAll = new HashMap<Diagram, Set<View>>();

    private IOperationHistoryListener viewPointLayoutDataFlusher;

    private boolean ignoreConsumeState;

    /**
     * List of created view to arrange with a center layout : case where one or several views are created and a position
     * has been registered in the layoutDataManager : the view must be created in the center location of the visible
     * part of its container.
     */
    private Map<Diagram, Set<View>> createdViewForInitPositionLayout = new HashMap<Diagram, Set<View>>();

    /**
     * Lists of views with defined coordinates, to be used as reference positions for the other views to layout in
     * algorithm centerLayout.
     * 
     * @see #createdViewForInitPositionLayout
     */
    private Map<Diagram, Set<View>> createdViewAsReferenceLayout = new HashMap<Diagram, Set<View>>();

    private Map<Diagram, Set<View>> createdViewForBorderNodeLayout = new HashMap<Diagram, Set<View>>();

    /**
     * Avoid instantiation.
     */
    private SiriusLayoutDataManagerImpl() {
        OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(getSiriusLayoutDataFlusher());
    }

    /**
     * Construct a new instance.
     * 
     * @return a new instance
     */
    public static SiriusLayoutDataManager init() {
        return new SiriusLayoutDataManagerImpl();
    }

    /**
     * Add a new AbstractLayoutData of this SiriusLayoutDataManagerImpl.
     * 
     * @param aLayoutData
     *            The rootLayoutData
     */
    @Override
    public void addData(final AbstractLayoutData aLayoutData) {
        // if a rootLayoutData exist with the same target, we delete the
        // previous rootLayoutData
        final Iterator<AbstractLayoutData> iterator = rootsLayoutData.iterator();
        while (iterator.hasNext()) {
            final AbstractLayoutData existingRootLayoutData = iterator.next();
            if (existingRootLayoutData instanceof LayoutData && aLayoutData instanceof LayoutData) {
                LayoutData existingLayoutData = (LayoutData) existingRootLayoutData;
                LayoutData aLayoutDataInParam = (LayoutData) aLayoutData;
                if (existingLayoutData.getTarget().equals(aLayoutDataInParam.getTarget())) {
                    iterator.remove();
                    // DiagramPlugin.getDefault().logWarning("The previous layout data were replaced by new ones. It may
                    // leads to unexpected layout behavior.");
                }
            }
        }
        this.rootsLayoutData.add(aLayoutData);
    }

    /**
     * Search recursively in all the LayoutData is there is one which have the node for target.
     * 
     * @param node
     *            The search element
     * @param searchParent
     *            true if the data must be retrieve from the node parent
     * @return the corresponding LayoutData or null if not found.
     */
    @Override
    public LayoutData getData(final AbstractDNode node, final boolean searchParent) {
        LayoutData result = null;
        if (node != null) {
            AbstractDNode searchNode = node;
            if (searchParent) {
                if (node.eContainer() instanceof AbstractDNode) {
                    searchNode = (AbstractDNode) node.eContainer();
                } else if (node.eContainer() instanceof DDiagram) {
                    result = getData((DDiagram) node.eContainer());
                } else {
                    DiagramPlugin.getDefault().logWarning(MessageFormat.format(Messages.SiriusLayoutDataManagerImpl_unhandledContainerKind, node.eContainer().getClass().getName()));
                }
            }
            if (result == null) {
                // Search the node in all rootsLayoutData
                for (final AbstractLayoutData abstractLayoutData : rootsLayoutData) {
                    if (abstractLayoutData instanceof LayoutData) {
                        LayoutData layoutData = (LayoutData) abstractLayoutData;
                        result = layoutData.getData(searchNode, ignoreConsumeState);
                        if (result != null) {
                            break;
                        }
                    }
                }
            }
            if (result == null) {
                // Search on EdgeLayoutData
                result = getLayoutDataFromEdgeLayoutData(searchNode);
            }
        }
        if (result != null) {
            result.setConsume(true);
        }
        return result;

    }

    private LayoutData getLayoutDataFromEdgeLayoutData(AbstractDNode searchNode) {
        LayoutData result = null;
        for (final AbstractLayoutData abstractLayoutData : rootsLayoutData) {
            if (abstractLayoutData instanceof EdgeLayoutData) {
                EdgeLayoutData edgeLayoutData = (EdgeLayoutData) abstractLayoutData;
                // Search if the specific layout data for border nodes is the good one.
                EdgeLayoutData edgeLayoutDataBN = edgeLayoutData.getEdgeLayoutDataForBorderNodes();
                if (edgeLayoutDataBN != null) {
                    result = getLayoutDataFromEdgeLayoutData(edgeLayoutDataBN, searchNode);
                    if (result != null) {
                        break;
                    }
                }
                // Search in the "standard" layout data
                result = getLayoutDataFromEdgeLayoutData(edgeLayoutData, searchNode);
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    private LayoutData getLayoutDataFromEdgeLayoutData(EdgeLayoutData edgeLayoutData, AbstractDNode searchNode) {
        LayoutData result = null;
        if (edgeLayoutData != null) {
            LayoutData edgeSourceLayoutData = edgeLayoutData.getEdgeSourceLayoutData();
            if (edgeSourceLayoutData != null) {
                result = edgeSourceLayoutData.getData(searchNode, ignoreConsumeState);
            }
            if (result == null) {
                LayoutData edgeTargetLayoutData = edgeLayoutData.getEdgeTargetLayoutData();
                if (edgeTargetLayoutData != null) {
                    result = edgeTargetLayoutData.getData(searchNode, ignoreConsumeState);
                }
            }
        }
        return result;
    }

    /**
     * Search recursively in all the LayoutData is there is one which have the edge for target.
     * 
     * @param edge
     *            The search element
     * @param searchParent
     *            true if the data must be retrieve from the node parent
     * @return the corresponding EdgeLayoutData or null if not found.
     */
    @Override
    public EdgeLayoutData getData(final DEdge edge, final boolean searchParent) {
        Option<EdgeLayoutData> noEdgeLayoutData = Options.newNone();
        return getData(edge, searchParent, noEdgeLayoutData);
    }

    @Override
    public EdgeLayoutData getOppositeEdgeLayoutData(EdgeLayoutData edgeLayoutData, boolean searchParent) {
        DEdge edge = edgeLayoutData.getTarget();
        if (edge == null) {
            return null;
        }
        return getData(edge, searchParent, Options.newSome(edgeLayoutData));
    }

    private EdgeLayoutData getData(DEdge edge, boolean searchParent, Option<EdgeLayoutData> optionalOppositeEdgeLayoutData) {
        EdgeLayoutData result = null;
        if (result == null) {
            // Search the edge in all rootsLayoutData
            for (final AbstractLayoutData abstractLayoutData : rootsLayoutData) {
                if (abstractLayoutData instanceof LayoutData) {
                    LayoutData layoutData = (LayoutData) abstractLayoutData;
                    result = layoutData.getData(edge, ignoreConsumeState);
                    if (result != null && (!optionalOppositeEdgeLayoutData.some() || !(optionalOppositeEdgeLayoutData.get().equals(result)))) {
                        break;
                    }
                } else if (abstractLayoutData instanceof EdgeLayoutData && (!optionalOppositeEdgeLayoutData.some() || !(optionalOppositeEdgeLayoutData.get().equals(abstractLayoutData)))) {
                    EdgeLayoutData edgeLayoutData = (EdgeLayoutData) abstractLayoutData;
                    EdgeTarget edgeSource = edge.getSourceNode();
                    EdgeTarget edgeTarget = edge.getTargetNode();
                    LayoutData edgeSourceLayoutData = edgeLayoutData.getEdgeSourceLayoutData();
                    LayoutData edgeTargetLayoutData = edgeLayoutData.getEdgeTargetLayoutData();

                    if (edgeSource != null && edgeSourceLayoutData instanceof RootLayoutData) {
                        RootLayoutData edgeSourceRootLayoutData = (RootLayoutData) edgeSourceLayoutData;
                        if ((ignoreConsumeState || !edgeSourceRootLayoutData.isConsume()) && edgeSource.equals(edgeSourceRootLayoutData.getTarget())) {
                            result = edgeLayoutData;
                            break;
                        }
                    }
                    if (result == null && edgeTarget != null && edgeTargetLayoutData instanceof RootLayoutData) {
                        RootLayoutData edgeTargetRootLayoutData = (RootLayoutData) edgeTargetLayoutData;
                        if ((ignoreConsumeState || !edgeTargetRootLayoutData.isConsume()) && edgeTarget.equals(edgeTargetRootLayoutData.getTarget())) {
                            result = edgeLayoutData;
                            break;
                        }
                    }
                }
            }
        }

        // Virtual consumption as this edge has no port.
        if (result == null) {
            final EdgeTarget sourceNode = edge.getSourceNode();
            if (sourceNode instanceof AbstractDNode) {
                getData((AbstractDNode) sourceNode);
            }
            final EdgeTarget targetNode = edge.getTargetNode();
            if (targetNode instanceof AbstractDNode) {
                getData((AbstractDNode) targetNode);
            }
        }
        if (result != null) {
            result.setConsume(true);
        }
        return result;

    }

    /**
     * Search recursively in all the LayoutData is there is one which have the diagram for target.
     * 
     * @param diagram
     *            The search element
     * @return the corresponding LayoutData or null if not found.
     */
    protected LayoutData getData(final DDiagram diagram) {
        LayoutData result = null;
        for (final AbstractLayoutData layoutData : rootsLayoutData) {
            if (layoutData instanceof RootLayoutData) {
                RootLayoutData rootLayoutData = (RootLayoutData) layoutData;
                result = rootLayoutData.getData(diagram, ignoreConsumeState);
            }
            if (result != null) {
                break;
            }
        }
        if (result != null) {
            result.setConsume(true);
        }
        return result;

    }

    @Override
    public LayoutData getData(final AbstractDNode node) {
        return getData(node, false);
    }

    @Override
    public Adapter getAdapterMarker() {
        return LAYOUT_MARKER_ADAPTER;
    }

    @Override
    public Adapter getCenterAdapterMarker() {
        return CENTER_LAYOUT_MARKER_ADAPTER;
    }

    @Override
    public Adapter getReferenceAdapterMarker() {
        return REFERENCE_LAYOUT_MARKER_ADAPTER;
    }

    @Override
    public Adapter getBorderNodeMarker() {
        return BORDER_NODE_LAYOUT_MARKER_ADAPTER;
    }

    @Override
    public AbstractTransactionalCommand getAddAdapterMakerCommand(final TransactionalEditingDomain domain, final IAdaptable viewAdapter) {
        return new AbstractTransactionalCommand(domain, Messages.SiriusLayoutDataManagerImpl_addLayoutMarkerCommandLabel, null) {
            @Override
            protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                final View view = viewAdapter.getAdapter(View.class);
                if (view != null && !view.eAdapters().contains(LAYOUT_MARKER_ADAPTER)) {
                    view.eAdapters().add(LAYOUT_MARKER_ADAPTER);
                }
                return CommandResult.newOKCommandResult();
            }
        };
    }

    @Override
    public AbstractTransactionalCommand getAddCenterAdapterMakerCommand(final TransactionalEditingDomain domain, final IAdaptable viewAdapter) {
        return new AbstractTransactionalCommand(domain, Messages.SiriusLayoutDataManagerImpl_addCenterLayoutMarkerCommandLabel, null) {
            @Override
            protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                final View view = viewAdapter.getAdapter(View.class);
                if (view != null && !view.eAdapters().contains(CENTER_LAYOUT_MARKER_ADAPTER)) {
                    view.eAdapters().add(CENTER_LAYOUT_MARKER_ADAPTER);
                }
                return CommandResult.newOKCommandResult();
            }
        };
    }

    @Override
    public AbstractTransactionalCommand getAddBorderNodeMakerCommand(final TransactionalEditingDomain domain, final IAdaptable viewAdapter) {
        return new AbstractTransactionalCommand(domain, Messages.SiriusLayoutDataManagerImpl_addCenterLayoutMarkerCommandLabel, null) {
            @Override
            protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                final View view = viewAdapter.getAdapter(View.class);
                if (view != null && !view.eAdapters().contains(BORDER_NODE_LAYOUT_MARKER_ADAPTER)) {
                    view.eAdapters().add(BORDER_NODE_LAYOUT_MARKER_ADAPTER);
                }
                return CommandResult.newOKCommandResult();
            }
        };
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.view.SiriusLayoutDataManager#getAddAdapterMakerOnOpeningCommand(org.eclipse.emf.transaction.TransactionalEditingDomain,
     *      org.eclipse.gmf.runtime.notation.View)
     */
    @Override
    public AbstractTransactionalCommand getAddAdapterMakerOnOpeningCommand(final TransactionalEditingDomain domain, final View view) {
        return new AbstractTransactionalCommand(domain, Messages.SiriusLayoutDataManagerImpl_addLayoutMarkerOnOpeningCommandLabel, null) {
            @Override
            protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                if (view != null && !view.eAdapters().contains(LAYOUT_MARKER_ADAPTER_ON_OPENING)) {
                    view.eAdapters().add(LAYOUT_MARKER_ADAPTER_ON_OPENING);
                }
                return CommandResult.newOKCommandResult();
            }
        };
    }

    @Override
    public EdgeLabelLayoutData getLabelData(final DEdge edge) {
        EdgeLabelLayoutData result = null;

        if (edge != null) {
            final EdgeLayoutData data = getData(edge, false);

            if (data != null) {
                result = data.getEdgeLabelLayoutData();
                // We consume edgeLayoutData only for its labelData so remove
                // the consume status
                data.setConsume(false);
            }
        }
        if (result != null) {
            result.setConsume(true);

        }
        return result;
    }

    @Override
    public Command getArrangeCreatedViewsCommand(List<IAdaptable> createdViews, List<IAdaptable> borderedCreatedViews, List<IAdaptable> centeredCreatedViews,
            List<IAdaptable> referenceChildViewsAdapters, IGraphicalEditPart host) {
        return getArrangeCreatedViewsCommand(createdViews, borderedCreatedViews, centeredCreatedViews, referenceChildViewsAdapters, host, false);
    }

    @Override
    public Command getArrangeCreatedViewsCommand(List<IAdaptable> createdViews, List<IAdaptable> borderedCreatedViews, List<IAdaptable> centeredCreatedViews,
            List<IAdaptable> referenceChildViewsAdapters, IGraphicalEditPart host, boolean useSpecificLayoutType) {
        String layoutType = useSpecificLayoutType ? LAYOUT_TYPE_ARRANGE_AT_OPENING : LayoutType.DEFAULT;
        return getArrangeCreatedViewsCommand(createdViews, borderedCreatedViews, centeredCreatedViews, referenceChildViewsAdapters, host, layoutType);
    }

    /**
     * Get the arrange command depending the layout type.
     * 
     * @param createdViews
     *            the new created views
     * @param centeredCreatedViews
     *            the new created views which must be layouted in the center of their containers
     * @param host
     *            container edit part
     * @param specificLayoutType
     *            the layout type (see {@link LayoutType#DEFAULT},
     *            {@link SiriusLayoutDataManager#LAYOUT_TYPE_ARRANGE_AT_OPENING},
     *            {@link SiriusLayoutDataManager#KEEP_FIXED})
     * @return the layout command
     */
    @Override
    public Command getArrangeCreatedViewsCommand(List<IAdaptable> createdViews, List<IAdaptable> borderedCreatedViews, List<IAdaptable> centeredCreatedViews,
            List<IAdaptable> referenceChildViewsAdapters, IGraphicalEditPart host, String specificLayoutType) {
        CompoundCommand cc = new CompoundCommand();
        // Center Layout case
        if (centeredCreatedViews != null) {
            Point previousCenterLocation = getReferencePosition(host, referenceChildViewsAdapters);
            // We compute the center location for the first view then we shift
            // the others
            Collection<IGraphicalEditPart> partsToLayout = centeredCreatedViews.stream().map(adaptable -> {
                View view = adaptable.getAdapter(View.class);
                return (IGraphicalEditPart) host.getViewer().getEditPartRegistry().get(view);
            }).filter(Objects::nonNull).toList();
            Collection<IFigure> figuresToLayout = partsToLayout.stream().map(IGraphicalEditPart::getFigure).toList();
            for (IGraphicalEditPart part : partsToLayout) {
                previousCenterLocation = calculateCenterLocation(host, cc, part, previousCenterLocation, figuresToLayout);
            }
        }
        if (createdViews != null || borderedCreatedViews != null) {
            // "normal" layout case
            cc.add(arrangeSeveralCreatedViews(createdViews, borderedCreatedViews, host, specificLayoutType));
        }
        return cc;
    }

    private IFigure adapterToFigure(IGraphicalEditPart host, IAdaptable iAdaptable) {
        IGraphicalEditPart part = (IGraphicalEditPart) host.getViewer().getEditPartRegistry().get(iAdaptable.getAdapter(View.class));
        return part.getFigure();
    }

    private Point getReferencePosition(IGraphicalEditPart host, List<IAdaptable> referenceViews) {
        if (referenceViews == null) {
            return null;
        } else {
            SiriusLayoutHelper layoutHelper = new SiriusLayoutHelper(host);
            return referenceViews.stream() //
                    // get all figures
                    .map(adaptable -> adapterToFigure(host, adaptable))
                    // get the rightmost figure
                    .max((figure1, figure2) -> figure1.getBounds().right() - figure2.getBounds().right())
                    // get coordinates for second view (i.e. the first unplaced view)
                    .map(layoutHelper::calculateNextPoint)
                    .orElse(null);
        }
    }

    /**
     * Calculate the center location of the visible part of the container. Do the same work than
     * XYLayoutEditPolicy.getConstraintFor(request).
     * 
     * @param host
     * @param cc
     * @param iAdaptable
     */
    private Point calculateCenterLocation(IGraphicalEditPart host, CompoundCommand cc, IGraphicalEditPart part, Point previousCenterLocation, Collection<IFigure> figuresToLayout) {
        Rectangle rect = new Rectangle();
        rect.setSize(LayoutHelper.UNDEFINED.getSize());
        Point centerLocation;
        SiriusLayoutHelper layoutHelper = new SiriusLayoutHelper(host);
        IFigure figure = part.getFigure();
        if (previousCenterLocation == null) {
            // Get center (reference point)
            Point referencePoint;
            if (host instanceof DiagramEditPart) {
                // When the element is placed at the bottom of the diagram, it is placed in the centre
                referencePoint = layoutHelper.getReferencePosition(host.getContentPane(), ((FigureCanvas) host.getViewer().getControl()).getViewport(), host);
            } else {
                // When the element is placed in a container, it is placed at (20, 20)
                referencePoint = new Point(20, 20);
            }
            rect.setLocation(referencePoint);
            rect.setSize(figure.getSize());
            // Get the first free location
            Point point = layoutHelper.validatePosition(host.getContentPane(), rect, figuresToLayout);
            centerLocation = point.getCopy();
        } else {
            centerLocation = previousCenterLocation;
        }
        cc.add(new ICommandProxy(new SetBoundsCommand(host.getEditingDomain(), DiagramUIMessages.SetLocationCommand_Label_Resize, part, centerLocation)));
        Point figureReferencePoint = layoutHelper.determineReferencePoint(figure);
        figureReferencePoint.translate(centerLocation);
        centerLocation = layoutHelper.computeTranslatedPoint(figureReferencePoint, figure, false);
        return centerLocation.getCopy();
    }


    /**
     * Arrange views.
     * 
     * @param createdViewsAdapters
     *            the new created views
     * @param host
     *            the container edit part
     * @param specificLayoutType
     *            the layout type (see {@link LayoutType#DEFAULT},
     *            {@link SiriusLayoutDataManager#LAYOUT_TYPE_ARRANGE_AT_OPENING},
     *            {@link SiriusLayoutDataManager#KEEP_FIXED})
     * @return the arrange command
     */
    private Command arrangeSeveralCreatedViews(List<IAdaptable> createdViewsAdapters, List<IAdaptable> borderedCreatedViews, IGraphicalEditPart host, String specificLayoutType) {
        CompoundCommand cc = new CompoundCommand();
        if (createdViewsAdapters != null && createdViewsAdapters.size() > 0) {
            // perform a layout of the container
            final List<IAdaptable> createdViewsToLayoutAdapters = new LinkedList<IAdaptable>();
            for (IAdaptable viewAdapter : createdViewsAdapters) {
                if (!isAlreadyArrange(viewAdapter)) {
                    createdViewsToLayoutAdapters.add(viewAdapter);
                } else {
                    removeAlreadyArrangeMarker(viewAdapter);
                }
            }

            if (createdViewsToLayoutAdapters.size() > 0) {
                DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host.getEditingDomain(), createdViewsToLayoutAdapters, host, specificLayoutType);
                cc.add(new ICommandProxy(layoutCmd));
            }
        }
        if (borderedCreatedViews != null && borderedCreatedViews.size() > 0) {
            Map<View, EditPart> editPartRegistry = host.getRoot().getViewer().getEditPartRegistry();

            List<EditPart> editPartToLayout = borderedCreatedViews.stream() //
                    .map(adaptable -> adaptable.getAdapter(View.class)) //
                    .filter(Objects::nonNull) //
                    .map(editPartRegistry::get) //
                    .filter(Objects::nonNull) //
                    .toList();
            final BorderItemAwareLayoutProvider layoutProvider = new BorderItemAwareLayoutProvider(null, true);
            cc.add(layoutProvider.layoutEditParts(editPartToLayout, new ObjectAdapter(specificLayoutType), false));
        }

        if (cc.isEmpty()) {
            return UnexecutableCommand.INSTANCE;
        } else {
            return cc;
        }
    }

    @Override
    public Command getArrangeCreatedViewsOnOpeningCommand(final IGraphicalEditPart host) {
        CompoundCommand cc = new CompoundCommand(Messages.SiriusLayoutDataManagerImpl_createdViewsArrangCommandLabel);
        View containerView = (View) host.getModel();
        Command layoutCommand = getlayoutCommand(host, containerView);
        if (layoutCommand instanceof CompoundCommand && !((CompoundCommand) layoutCommand).isEmpty()) {
            cc.add(layoutCommand);
        }

        for (Object child : containerView.getChildren()) {
            Command cmd = getlayoutCommand((IGraphicalEditPart) host.getViewer().getEditPartRegistry().get(containerView), (View) child);
            if (cmd instanceof CompoundCommand && !((CompoundCommand) cmd).isEmpty()) {
                cc.add(cmd);
            }
        }
        if (cc.isEmpty()) {
            return UnexecutableCommand.INSTANCE;
        }
        return cc;
    }

    /**
     * Create a layout command for all created views of the <code>containerView</code>.
     * 
     * @param host
     *            edit part parent
     * @param cc
     *            compound command
     * @param containerView
     *            parent view
     * @return the layout commands
     */
    private Command getlayoutCommand(final IGraphicalEditPart host, View containerView) {
        CompoundCommand cc = new CompoundCommand(Messages.SiriusLayoutDataManagerImpl_createdViewsArrangCommandLabel);

        // get the created views and remove the layout adapter
        @SuppressWarnings("unchecked")
        Collection<View> createdViews = Collections2.filter(containerView.getChildren(), predicate);
        LinkedList<IAdaptable> createdViewstoLayout = new LinkedList<>();
        int size = createdViews.size();
        for (Object view : createdViews) {
            createdViewstoLayout.add(new EObjectAdapter((View) view));
            if (size != 1) {
                removeAlreadyArrangeMarkeronOpening((View) view);
            }
        }

        /* create layout command */
        if (!createdViewstoLayout.isEmpty()) {
            if (host != null) {
                final DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host.getEditingDomain(), createdViewstoLayout, host);
                cc.add(new ICommandProxy(layoutCmd));
            }
        }

        /* create layout command for view child */
        for (Object child : containerView.getChildren()) {
            /* host may be null here, so avoid to explode */
            if (host != null) {
                Command cmd = getlayoutCommand((IGraphicalEditPart) host.getViewer().getEditPartRegistry().get(containerView), (View) child);
                if (cmd instanceof CompoundCommand && !((CompoundCommand) cmd).isEmpty()) {
                    cc.add(cmd);
                }
            }
        }
        return cc;
    }

    /**
     * Return true if this view is already arrange, false otherwise.
     * 
     * @param viewAdapter
     *            The view to check
     * @return true if this view is already arrange, false otherwise (need arrange)
     */
    private boolean isAlreadyArrange(IAdaptable viewAdapter) {
        boolean alreadyArranged = false;
        final View view = viewAdapter.getAdapter(View.class);
        if (view != null) {
            for (Adapter adapter : view.eAdapters()) {
                if (adapter.isAdapterForType(SiriusLayoutDataManager.INSTANCE)) {
                    alreadyArranged = true;
                    break;
                }
            }
        }
        return alreadyArranged;
    }

    /**
     * Return true if this view has a center layout, false otherwise.
     * 
     * @param viewAdapter
     *            The view to check
     * @return true if this view has a center layout, false otherwise (need arrange)
     */
    private boolean hasCenterLayout(IAdaptable viewAdapter) {
        boolean centerLayout = false;
        final View view = viewAdapter.getAdapter(View.class);
        if (view != null) {
            for (Adapter adapter : view.eAdapters()) {
                if (adapter.isAdapterForType(SiriusLayoutDataManager.INSTANCE)) {
                    centerLayout = true;
                    break;
                }
            }
        }
        return centerLayout;
    }

    /**
     * Return true if this view is to arrange, false otherwise.
     * 
     * @param view
     *            The view to check
     * @return true if this view is to arrange, false otherwise
     */
    @Override
    public boolean hasToArrange(View view) {
        boolean arranged = false;
        if (view != null) {
            for (Adapter adapter : view.eAdapters()) {
                if (adapter.isAdapterForType(SiriusLayoutDataManager.INSTANCE)) {
                    arranged = true;
                    break;
                }
            }
        }
        return arranged;
    }

    /**
     * Remove the adapter marker that reveal an already arranged view.
     * 
     * @param viewAdapter
     *            The view to check
     */
    private void removeAlreadyArrangeMarker(IAdaptable viewAdapter) {
        final View view = viewAdapter.getAdapter(View.class);
        if (view != null) {
            for (Iterator<Adapter> iterator = view.eAdapters().iterator(); iterator.hasNext(); /**/) {
                Adapter adapter = iterator.next();
                if (adapter.isAdapterForType(SiriusLayoutDataManager.INSTANCE)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * Remove the adapter marker that reveal an already arranged view.
     * 
     * @param view
     *            The view to check
     */
    private void removeAlreadyArrangeMarkeronOpening(View view) {
        if (view != null) {
            for (Iterator<Adapter> iterator = view.eAdapters().iterator(); iterator.hasNext(); /**/) {
                Adapter adapter = iterator.next();
                if (adapter.isAdapterForType(SiriusLayoutDataManager.INSTANCE)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    @Override
    public Command getArrangeCommand(ArrangeRequest request, EditPart host) {
        Command arrangeCommand = null;

        // layout new created views at diagram
        // opening : launch arrange selection even if there is only one new
        // created view
        // Code from
        // org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy.getArrangeComamand(ArrangeRequest)

        String layoutDesc = request.getLayoutType() != null ? request.getLayoutType() : LayoutType.DEFAULT;
        boolean offsetFromBoundingBox = false;
        List<EditPart> editparts = new ArrayList<EditPart>();

        if (ActionIds.ACTION_ARRANGE_SELECTION.equals(request.getType())) {
            editparts = request.getPartsToArrange();
            offsetFromBoundingBox = true;
        }

        if (!editparts.isEmpty()) {
            List<LayoutNode> nodes = new ArrayList<LayoutNode>(editparts.size());
            ListIterator<EditPart> li = editparts.listIterator();
            while (li.hasNext()) {
                IGraphicalEditPart ep = (IGraphicalEditPart) li.next();
                View view = ep.getNotationView();
                if (view instanceof Node) {
                    Rectangle bounds = ep.getFigure().getBounds();
                    nodes.add(new LayoutNode((Node) view, bounds.width, bounds.height));
                }
                // remove adapters
                removeAlreadyArrangeMarkeronOpening(view);
            }

            List<Object> hints = new ArrayList<Object>(2);
            hints.add(layoutDesc);
            hints.add(host);
            IAdaptable layoutHint = new ObjectAdapter(hints);
            final Runnable layoutRun = layoutNodes(nodes, offsetFromBoundingBox, layoutHint);

            if (layoutRun instanceof IInternalLayoutRunnable) {
                arrangeCommand = ((IInternalLayoutRunnable) layoutRun).getCommand();
            } else {
                TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) host).getEditingDomain();
                arrangeCommand = new ICommandProxy(new AbstractTransactionalCommand(editingDomain, "", null) { //$NON-NLS-1$
                    @Override
                    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
                        layoutRun.run();
                        return CommandResult.newOKCommandResult();
                    }
                });
            }
        }
        return arrangeCommand;
    }

    /**
     * Getter for the SiriusLayoutDataFlusher which listens IOperationHistory events to flush the list RootsLayoutData
     * after a OperationHistoryEvent#DONE event.
     */
    private IOperationHistoryListener getSiriusLayoutDataFlusher() {
        if (viewPointLayoutDataFlusher == null) {
            viewPointLayoutDataFlusher = new SiriusLayoutDataFlusher(this);
        }
        return viewPointLayoutDataFlusher;
    }

    /**
     * package internal method to flush the list of {@link RootLayoutData}, used by the {@link SiriusLayoutDataFlusher}.
     */
    protected void flushRootLayoutDatas() {
        rootsLayoutData.clear();
    }

    /**
     * Overridden to remove the SiriusLayoutDataFlusher from the IOperationHistoryListener list of listeners.
     * 
     * @throws Throwable
     * 
     *             {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(viewPointLayoutDataFlusher);
        super.finalize();
    }

    /**
     * Layout nodes.
     * 
     * @param offsetFromBoundingBox
     *            offset
     * @param nodes
     *            list of nodes
     * @param layoutHint
     *            layout hint
     * @return runnable
     */
    public Runnable layoutNodes(List<LayoutNode> nodes, boolean offsetFromBoundingBox, IAdaptable layoutHint) {
        final Runnable layoutRun = LayoutService.getInstance().layoutLayoutNodes(nodes, offsetFromBoundingBox, layoutHint);
        return layoutRun;
    }

    @Override
    public void addCreatedViewForLayoutAll(Diagram gmfDiagram, LinkedHashSet<View> createdViewsToLayout) {
        createdViewForLayoutAll.put(gmfDiagram, createdViewsToLayout);
    }

    @Override
    public Map<Diagram, Set<View>> getCreatedViewForLayoutAll() {
        return createdViewForLayoutAll;
    }

    @Override
    public void setIgnoreConsumeState(boolean ignoreConsumeState) {
        this.ignoreConsumeState = ignoreConsumeState;
    }

    @Override
    public Option<AbstractLayoutData> getData() {
        if (!rootsLayoutData.isEmpty()) {
            return Options.newSome(rootsLayoutData.iterator().next());
        }
        return Options.newNone();
    }

    @Override
    public void removeLayoutViews(Diagram diagram) {
        createdViewForLayoutAll.remove(diagram);
        createdViewForInitPositionLayout.remove(diagram);
        createdViewAsReferenceLayout.remove(diagram);
        createdViewForBorderNodeLayout.remove(diagram);
    }

    @Override
    public Map<Diagram, Set<View>> getCreatedViewForInitPositionLayout() {
        return createdViewForInitPositionLayout;
    }

    @Override
    public void addCreatedViewForInitPositionLayout(Diagram gmfDiagram, LinkedHashSet<View> createdViewsToLayout) {
        this.createdViewForInitPositionLayout.put(gmfDiagram, createdViewsToLayout);
    }

    @Override
    public Map<Diagram, Set<View>> getCreatedViewReferenceLayout() {
        return createdViewAsReferenceLayout;
    }

    @Override
    public void addCreatedViewAsReferenceLayout(Diagram gmfDiagram, LinkedHashSet<View> views) {
        createdViewAsReferenceLayout.put(gmfDiagram, views);
    }

    @Override
    public Map<Diagram, Set<View>> getCreatedViewForBorderNodeLayout() {
        return createdViewForBorderNodeLayout;
    }

    @Override
    public void addCreatedViewForBorderNodeLayout(Diagram gmfDiagram, LinkedHashSet<View> views) {
        createdViewForBorderNodeLayout.put(gmfDiagram, views);
    }
}
