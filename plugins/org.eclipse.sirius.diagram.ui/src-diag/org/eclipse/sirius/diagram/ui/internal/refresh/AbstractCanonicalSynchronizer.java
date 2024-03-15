/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusViewProvider;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.internal.view.factories.AbstractContainerViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewSizeHint;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramUpdater;
import org.eclipse.sirius.diagram.ui.part.SiriusNodeDescriptor;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Abstract class define common behavior between all {@link CanonicalSynchronizer}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractCanonicalSynchronizer implements CanonicalSynchronizer {

    private static record LayoutDataResult( //
            boolean normalLayout, //
            boolean centerLayout, //
            boolean borderLayout, //
            boolean isAlreadylayouted, //
            boolean isLayoutReference
    ) {
    };

    private final static boolean STANDARD_LAYOUT_FOR_CREATED_REGION_CONTENT = Boolean.getBoolean("org.eclipse.sirius.diagram.ui.internal.region.content.canonical.layout.standard"); //$NON-NLS-1$

    /**
     * Say if we should store created views to layout in SiriusLayoutDataManager.
     */
    protected boolean storeViews2Arrange = true;

    /**
     * Sirius GMF notation model View factory.
     */
    protected IViewProvider viewpointViewProvider = new SiriusViewProvider();

    /**
     * Store regions containers and its impacted status:
     * <UL>
     * <LI>A regions container is considered as impacted if it contains at least one new view or if at least one of its
     * views has been deleted.</LI>
     * <LI>if impacted, the regions container must be layouted</LI>
     * </UL>
     * .
     */
    protected Map<View, Boolean> regionsContainersToLayoutWithImpactStatus = new HashMap<>();

    /**
     * True if the snap to grid is considered as activated. In this case, the returned location, by
     * {@link #getValidLocation(Rectangle, Node, Collection<Node>)}, is snapped to the grid (if possible).
     */
    private boolean snapToGrid;

    /**
     * The grid step (it is mandatory if the {@link #snapToGrid} is true.
     */
    private int gridSpacing = 0;

    /**
     * Default constructor.
     */
    public AbstractCanonicalSynchronizer() {
    }

    @Override
    public void storeViewsToArrange(boolean storeViewsToArrange) {
        this.storeViews2Arrange = storeViewsToArrange;
    }

    protected void refreshSemantic(final View view, CanonicalSynchronizerResult canonicalSynchronizerResult) {
        refreshSemanticChildren(view, ViewUtil.resolveSemanticElement(view), canonicalSynchronizerResult);
        for (View childView : Iterables.filter(view.getChildren(), View.class)) {
            if (!canonicalSynchronizerResult.getOrphanNodes().contains(childView)) {
                refreshSemantic(childView, canonicalSynchronizerResult);
            }
        }
    }

    /**
     * Refresh the specified {@link View} children with the specified semantic element.
     * 
     * @param gmfView
     *            the specified {@link View}
     * @param semanticView
     *            the semantic element of the specified {@link View}
     * @param canonicalSynchronizerResult
     *            List of created {@link View}s and detected orphan {@link View}s during the canonical refresh of the
     *            diagram.
     * @return the {@link CanonicalSynchronizerResult} for convenience
     */
    protected void refreshSemanticChildren(final View gmfView, final EObject semanticView, CanonicalSynchronizerResult canonicalSynchronizerResult) {

        // Don't try to refresh children if the semantic element
        // cannot be resolved.
        if (semanticView == null) {
            return;
        }

        // isPartOfRegionsContainer is true for regions container and all its
        // sub part as CompartmentView.
        boolean isPartOfRegionsContainer = semanticView instanceof DNodeContainer && new DNodeContainerExperimentalQuery((DNodeContainer) semanticView).isRegionContainer();
        // isRegionsContainer is true only for the real regions container view
        boolean isRegionsContainer = false;
        if (isPartOfRegionsContainer) {
            // Only consider DNodeContainerEditPart and DNodeContainer2EditPart
            // as real regionContainer potentially to layout.
            int type = SiriusVisualIDRegistry.getVisualID(gmfView.getType());
            if (type == DNodeContainerEditPart.VISUAL_ID || type == DNodeContainer2EditPart.VISUAL_ID) {
                isRegionsContainer = true;
                regionsContainersToLayoutWithImpactStatus.put(gmfView, Boolean.FALSE);
            }
        }

        // current views
        final List<View> viewChildren = getViewChildren(gmfView);
        final List<SiriusNodeDescriptor> semanticChildren = new ArrayList<SiriusNodeDescriptor>(SiriusDiagramUpdater.getSemanticChildren(gmfView));

        List<View> orphan = cleanCanonicalSemanticChildren(gmfView, viewChildren, semanticChildren);

        if (!orphan.isEmpty()) {
            canonicalSynchronizerResult.addOrphanNodes(orphan);

            // Some children views have been deleted, this container must be
            // layouted if it is a regions container or a sub part of a regions
            // container
            setRegionsContainerAsImpacted(gmfView, isRegionsContainer, isPartOfRegionsContainer);
        }

        // create a view for each remaining semantic element.
        Set<View> createdViews = createViews(semanticChildren, gmfView.getType(), gmfView);

        if (!createdViews.isEmpty()) {
            canonicalSynchronizerResult.addCreatedNodes(createdViews);
            // There is at least one new child, this container must be
            // layouted if it is a regions container or a sub part of a regions
            // container.
            setRegionsContainerAsImpacted(gmfView, isRegionsContainer, isPartOfRegionsContainer);
        }
        // Manage Nodes ordering in Compartment according to DNodeListElement
        // ordering
        if (semanticView instanceof DNodeList || isPartOfRegionsContainer) {
            refreshSemanticChildrenOrdering(gmfView);
        }
    }

    protected boolean isSnapToGrid() {
        return snapToGrid;
    }

    protected void setSnapToGrid(boolean snapToGrid) {
        this.snapToGrid = snapToGrid;
    }

    protected int getGridSpacing() {
        return gridSpacing;
    }

    protected void setGridSpacing(int gridSpacing) {
        this.gridSpacing = gridSpacing;
    }

    /**
     * Set the gmfView, or its ancestor representation the real regions container, as impacted by the changes (new
     * views, removed views or order change).
     * 
     * @param gmfView
     *            The concerned view (corresponding to the regions container itself or one of its sub-part)
     * @param isRegionsContainer
     *            true is the current view represents the regions container, false otherwise
     * @param isPartOfRegionsContainer
     *            true is the current view represents the regions container or a sub-part of the regions container (as
     *            CompartmentView for example), false otherwise
     */
    private void setRegionsContainerAsImpacted(final View gmfView, boolean isRegionsContainer, boolean isPartOfRegionsContainer) {
        if (isRegionsContainer) {
            regionsContainersToLayoutWithImpactStatus.put(gmfView, Boolean.TRUE);
        } else if (isPartOfRegionsContainer) {
            Option<View> realRegionsContainer = new ViewQuery(gmfView).getAncestor(DNodeContainerEditPart.VISUAL_ID, DNodeContainer2EditPart.VISUAL_ID);
            if (realRegionsContainer.some()) {
                regionsContainersToLayoutWithImpactStatus.put(realRegionsContainer.get(), Boolean.TRUE);
            }
        }
    }

    private void markCreatedViewsAsToLayout(Collection<View> createdViews) {
        for (View createdView : createdViews) {
            createdView.eAdapters().add(SiriusLayoutDataManager.INSTANCE.getAdapterMarker());
        }
    }

    protected boolean createdViewIsMarkedAsToLayout(View createdView) {
        return createdView.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getAdapterMarker());
    }

    private void markCreatedViewsAsToLayoutBorderNode(Collection<View> createdViews) {
        for (View createdView : createdViews) {
            createdView.eAdapters().add(SiriusLayoutDataManager.INSTANCE.getBorderNodeMarker());
        }
    }

    private void markCreatedViewsWithCenterLayout(Collection<View> createdViews) {
        for (View createdView : createdViews) {
            createdView.eAdapters().add(SiriusLayoutDataManager.INSTANCE.getCenterAdapterMarker());
        }
    }

    protected boolean createdViewIsMarkedAsToCenterLayout(View createdView) {
        return createdView.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getCenterAdapterMarker());
    }

    private void markCreatedViewsAsReferenceLayout(Set<View> createdViews) {
        for (View createdView : createdViews) {
            createdView.eAdapters().add(SiriusLayoutDataManager.INSTANCE.getReferenceAdapterMarker());
        }
    }
    protected boolean createdViewIsMarkedAsReferenceLayout(View createdView) {
        return createdView.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getReferenceAdapterMarker());
    }

    /**
     * Refresh the GMF Views ordering according to the DDiagramElements ordering. In precondition to this call, is that
     * the refreshSemantic() must be called to have same View children number as DDiagramElement children number.
     * 
     * Could be called by a ListCompartmentEditPart's or RegionContainer edit part CanonicalEditPolicy.
     * 
     * @param gmfView
     * @return true if an element was moved.
     */
    private boolean refreshSemanticChildrenOrdering(View hostView) {
        boolean moveOccur = false;
        Map<EObject, View> semantic2ViewMap = new HashMap<EObject, View>();
        List<View> views = getViewChildren(hostView);
        List<SiriusNodeDescriptor> semanticChildren = new ArrayList<SiriusNodeDescriptor>(SiriusDiagramUpdater.getSemanticChildren(hostView));
        for (int i = 0; i < semanticChildren.size(); i++) {
            SiriusNodeDescriptor viewpointNodeDescriptor = semanticChildren.get(i);
            EObject modelElement = viewpointNodeDescriptor.getModelElement();
            View view = null;
            if (semantic2ViewMap.containsKey(modelElement)) {
                view = semantic2ViewMap.get(modelElement);
            } else {
                view = getView(views, modelElement);
                if (view != null) {
                    semantic2ViewMap.put(modelElement, view);
                }
            }
            if (view != null && hostView.getPersistedChildren().indexOf(view) != i) {
                hostView.getPersistedChildren().move(i, view);
                moveOccur = true;
            }
        }
        return moveOccur;
    }

    private View getView(List<View> views, EObject modelElement) {
        for (View view : views) {
            if (modelElement.equals(view.getElement())) {
                return view;
            }
        }
        return null;
    }

    private List<View> getViewChildren(final View current) {
        return new ArrayList<>(current.getChildren());
    }

    /**
     * Creates a <code>View</code> element for each of the supplied semantic elements.
     * 
     * @param eObjects
     *            list of semantic element
     * @param factoryHint
     *            the factory hint
     * @param host
     *            the {@link View} host
     * @return an ordered set of {@link IAdaptable} that adapt to {@link View}.
     */
    protected Set<View> createViews(final List<SiriusNodeDescriptor> eObjects, final String factoryHint, final View host) {
        final List<ViewDescriptor> descriptors = new ArrayList<ViewDescriptor>();
        final Iterator<SiriusNodeDescriptor> elements = eObjects.iterator();
        while (elements.hasNext()) {
            final SiriusNodeDescriptor nodeDescriptor = elements.next();
            final EObject element = nodeDescriptor.getModelElement();
            if (element != null) {
                final CreateViewRequest.ViewDescriptor descriptor = getViewDescriptor(element, nodeDescriptor.getType());
                descriptors.add(descriptor);
            }
        }

        Set<View> createdViews = new LinkedHashSet<>();
        Set<View> createdViewsToLayout = new LinkedHashSet<>();
        Set<View> createdCenteredViewsToLayout = new LinkedHashSet<>();
        Set<View> createdBorderedViewsToLayout = new LinkedHashSet<>();
        Set<View> createdLayoutRefernceViews = new LinkedHashSet<>();
        for (ViewDescriptor viewDescriptor : descriptors) {

            Class<?> viewKind = viewDescriptor.getViewKind();
            IAdaptable semanticAdapter = viewDescriptor.getElementAdapter();
            View containerView = host;
            String semanticHint = viewDescriptor.getSemanticHint();
            int index = viewDescriptor.getIndex();
            boolean persisted = viewDescriptor.isPersisted();
            PreferencesHint preferencesHint = viewDescriptor.getPreferencesHint();

            View createdView = null;
            if (viewKind == Diagram.class) {
                createdView = viewpointViewProvider.createDiagram(semanticAdapter, semanticHint, preferencesHint);
            } else if (viewKind == Edge.class) {
                createdView = viewpointViewProvider.createEdge(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);
            } else if (viewKind == Node.class) {
                createdView = viewpointViewProvider.createNode(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);
            }
            if (createdView != null) {
                createdViews.add(createdView);
                LayoutDataResult layoutDataResult = updateLocationAndSize(createdView);
                if (layoutDataResult.isAlreadylayouted) {
                    // location and size will not be updated so we remove this
                    // marker to make sure that the next arrange all with auto
                    // size will layout this view "normally". see
                    // ArrangeAllWithAutoSize#isDefaultSizeHasJustBeenSet for
                    // more details
                    if (viewKind == Node.class) {
                        removeJustCreatedMarker(createdView);
                    }
                }
                if (layoutDataResult.normalLayout) {
                    createdViewsToLayout.add(createdView);
                }
                if (layoutDataResult.centerLayout) {
                    createdCenteredViewsToLayout.add(createdView);
                }
                if (layoutDataResult.borderLayout) {
                    createdBorderedViewsToLayout.add(createdView);
                }
                if (layoutDataResult.isLayoutReference) {
                    createdLayoutRefernceViews.add(createdView);
                }
                if (createdView instanceof Node) {
                    // Update the label location, with BorderItemLocator, if one
                    // exists and it is on border
                    for (Object view : createdView.getPersistedChildren()) {
                        if (view instanceof View && new ViewQuery((View) view).isForNameEditPartOnBorder()) {
                            LayoutDataResult layoutNameDataResult = updateLocationAndSize((View) view);

                            if (layoutNameDataResult.centerLayout) {
                                createdCenteredViewsToLayout.add(createdView);
                            }
                            if (layoutNameDataResult.borderLayout) {
                                createdBorderedViewsToLayout.add(createdView);
                            }
                        }
                    }
                }
            }

        }

        // Manage layout for newly created elements
        if (storeViews2Arrange) {
            markCreatedViewsAsToLayout(createdViewsToLayout);
            markCreatedViewsWithCenterLayout(createdCenteredViewsToLayout);
            markCreatedViewsAsToLayoutBorderNode(createdBorderedViewsToLayout);
            markCreatedViewsAsReferenceLayout(createdLayoutRefernceViews);
        }

        return createdViews;

    }

    private void removeJustCreatedMarker(View createdView) {

        // to avoid concurrent modification exception
        Set<Adapter> adapters = Sets.newLinkedHashSet(createdView.eAdapters());
        Iterator<Adapter> iterator = adapters.iterator();
        List<Adapter> adaptersToRemove = new ArrayList<Adapter>();
        while (iterator.hasNext()) {
            Adapter adapter = iterator.next();
            if (adapter.isAdapterForType(AbstractContainerViewFactory.class)) {
                adaptersToRemove.add(adapter);
            }
        }
        for (Adapter adapterToRemove : adaptersToRemove) {
            createdView.eAdapters().remove(adapterToRemove);
        }

    }

    private LayoutDataResult updateLocationAndSize(View createdView) {
        LayoutDataResult updateLocationAndSize;
        EObject element = createdView.getElement();
        if (isBorderedNode(element) || new ViewQuery(createdView).isForNameEditPartOnBorder()) {
            updateLocationAndSize = updateAbstractDNode_ownedBorderedNodes_Bounds(createdView);
        } else if (isTopLevelNode(element)) {
            updateLocationAndSize = updateDDiagramChildBounds(createdView);
        } else if (isChildNodeButNotBorderedNodeOfContainer(element)) {
            updateLocationAndSize = updateDNodeContainerChildButNotBorderedNodeBounds(createdView);
        } else {
            updateLocationAndSize = new LayoutDataResult(true, false, false, false, false);
        }
        return updateLocationAndSize;
    }

    private LayoutDataResult updateDDiagramChildBounds(View createdView) {
        Dimension size = null;
        Point location = null;

        boolean normalLayout = true;
        boolean centerLayout = false;
        boolean borderLayout = false;
        boolean isAlreadyLayouted = false;
        boolean isLayoutReference = false;

        EObject element = createdView.getElement();
        org.eclipse.sirius.diagram.business.api.query.DNodeQuery dNodeQuery = null;
        if (element instanceof DNode) {
            DNode dNode = (DNode) element;
            dNodeQuery = new org.eclipse.sirius.diagram.business.api.query.DNodeQuery(dNode);
        }
        if (element instanceof DDiagramElement) {
            if (element instanceof AbstractDNode) {
                AbstractDNode abstractDNode = (AbstractDNode) element;
                LayoutData layoutData = SiriusLayoutDataManager.INSTANCE.getData(abstractDNode);
                if (layoutData == null) {
                    layoutData = SiriusLayoutDataManager.INSTANCE.getData(abstractDNode, true);
                }
                if (layoutData != null && layoutData.getSize() != null) {
                    if (dNodeQuery == null || (dNodeQuery != null && dNodeQuery.allowsHorizontalResize() && dNodeQuery.allowsVerticalResize())) {
                        size = layoutData.getSize();
                    } else if (dNodeQuery != null && dNodeQuery.allowsHorizontalResize()) {
                        size = new Dimension(layoutData.getSize().width, getDefaultSize((AbstractDNode) element).height);
                    } else if (dNodeQuery != null && dNodeQuery.allowsVerticalResize()) {
                        size = new Dimension(getDefaultSize((AbstractDNode) element).width, layoutData.getSize().height);
                    }
                }
                if (layoutData != null && layoutData.getLocation() != null) {
                    location = layoutData.getLocation();
                    isLayoutReference = true;
                }

                // If the AbstractDNode has BorderNodes, it should be marked as to layout its BorderNodes.
                if (!abstractDNode.getOwnedBorderedNodes().isEmpty()) { // if (has border nodes)
                    borderLayout = true;
                }
            }
        } else {
            size = ViewSizeHint.getInstance().consumeSize();
        }
        if (size == null) {
            size = getDefaultSize((AbstractDNode) element);
        }
        if (createdView instanceof Node && ((Node) createdView).getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) ((Node) createdView).getLayoutConstraint();
            if (location == null) {
                normalLayout = false;
                centerLayout = true;
                isAlreadyLayouted = true;
            } else {
                bounds.setX(location.x);
                bounds.setY(location.y);
                normalLayout = false;
                isAlreadyLayouted = true;
            }
            if (size != null) {
                if (size.width != -1) {
                    bounds.setWidth(size.width);
                }
                if (size.height != -1) {
                    bounds.setHeight(size.height);
                }
            }
        }

        return new LayoutDataResult(normalLayout, centerLayout, borderLayout, isAlreadyLayouted, isLayoutReference);
    }

    private LayoutDataResult updateAbstractDNode_ownedBorderedNodes_Bounds(View createdView) {
        Node createdNode = (Node) createdView;
        AbstractDNode portNode = (AbstractDNode) createdView.getElement();
        Node parentNode = (Node) createdView.eContainer();
        LayoutData layoutData = SiriusLayoutDataManager.INSTANCE.getData(portNode);

        boolean laidOut = false;

        if (layoutData == null) {
            // Check if we are in creation mode and not in drag'n'drop
            // In creation mode we must calculate the best position for the new
            // port
            layoutData = SiriusLayoutDataManager.INSTANCE.getData(portNode, true);
            Rectangle tempBounds;
            if (layoutData != null) {
                laidOut = true;
                // We get the layoutData from the manager with the parent of the
                // node
                final Point location = layoutData.getLocation() != null ? layoutData.getLocation() : new Point(0, 0);
                Dimension size = null;
                if (layoutData.getSize() != null) {
                    size = layoutData.getSize();
                } else {
                    size = getDefaultSize(portNode);
                }
                tempBounds = new Rectangle(location, size);
            } else {
                Dimension size = ViewSizeHint.getInstance().consumeSize();
                Point location = null;

                if (size == null) {
                    if (new ViewQuery(createdView).isForNameEditPart()) {
                        Option<Rectangle> optionalRect = GMFHelper.getAbsoluteBounds(createdView);
                        if (optionalRect.some()) {
                            size = optionalRect.get().getSize();
                        }
                    }
                    if (size == null) {
                        size = getDefaultSize(portNode);
                    }
                }
                if (location == null) {
                    location = new Point(0, 0);
                }
                tempBounds = new Rectangle(location, size);
            }

            CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW, isSnapToGrid(), getGridSpacing());
            if (new ViewQuery(createdView).isForNameEditPart()) {
                locator.setBorderItemOffset(IBorderItemOffsets.NO_OFFSET);
            } else {
                if (new DDiagramElementQuery(portNode).isIndirectlyCollapsed()) {
                    locator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                } else {
                    locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }
            }

            final IFigure dummyFigure = new Figure();
            final Rectangle constraint = tempBounds.getCopy();
            locator.setConstraint(constraint);
            dummyFigure.setVisible(true);
            final Rectangle rect = new Rectangle(constraint);
            Point parentAbsoluteLocation = GMFHelper.getAbsoluteLocation(parentNode, true);
            rect.translate(parentAbsoluteLocation.x, parentAbsoluteLocation.y);
            dummyFigure.setBounds(rect);
            final Point realLocation = locator.getValidLocation(rect, createdNode, new ArrayList<Node>(Arrays.asList(createdNode)));
            final Dimension d = realLocation.getDifference(parentAbsoluteLocation);
            final Point location = new Point(d.width, d.height);
            realLocation.setLocation(location);

            locator.relocate(createdNode);

            LayoutConstraint createdNodeLayoutConstraint = createdNode.getLayoutConstraint();
            if (createdNodeLayoutConstraint instanceof Location) {
                laidOut = true;
                Location createdNodeBounds = (Location) createdNodeLayoutConstraint;
                createdNodeBounds.setX(location.x);
                createdNodeBounds.setY(location.y);
            }
            if (createdNodeLayoutConstraint instanceof Size) {
                Size createdNodeBounds = (Size) createdNodeLayoutConstraint;
                ResizeKind resizeKind = ResizeKind.NSEW_LITERAL;
                if (portNode instanceof DNode) {
                    resizeKind = ((DNode) portNode).getResizeKind();
                }
                if (constraint.width != -1 && canResizeWidth(resizeKind)) {
                    createdNodeBounds.setWidth(constraint.width);
                }
                if (constraint.height != -1 && canResizeHeight(resizeKind)) {
                    createdNodeBounds.setHeight(constraint.height);
                }
            }
        } else {
            laidOut = true;
            // We get the layoutData from the manager directly with the node
            // (drag'n'drop) but this location should be adapt to be correct
            // according to CanonicalDBorderItemLocator.
            final Point location = layoutData.getLocation() != null ? layoutData.getLocation() : new Point(0, 0);

            Dimension size = null;
            if (layoutData.getSize() != null) {
                size = layoutData.getSize();
            } else {
                size = getDefaultSize(portNode);
            }

            // Compute the best location according to other existing bordered
            // nodes.
            CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW);
            if (portNode != null) {
                if (new DDiagramElementQuery(portNode).isIndirectlyCollapsed()) {
                    locator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                } else {
                    locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }
            } else {
                locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
            }

            // CanonicalDBorderItemLocator works with absolute GMF parent
            // location so we need to translate BorderedNode absolute location.
            final org.eclipse.draw2d.geometry.Point parentAbsoluteLocation = GMFHelper.getAbsoluteBounds(parentNode).getTopLeft();
            final Point realLocation = locator.getValidLocation(new Rectangle(location.getTranslated(parentAbsoluteLocation), size), createdNode, Collections.singleton(createdNode));

            // Compute the new relative position to the parent
            realLocation.translate(parentAbsoluteLocation.negate());

            Node node = (Node) createdView;
            Bounds bounds = (Bounds) node.getLayoutConstraint();
            bounds.setX(realLocation.x);
            bounds.setY(realLocation.y);
            if (size.width != -1) {
                bounds.setWidth(size.width);
            }
            if (size.height != -1) {
                bounds.setHeight(size.height);
            }
        }
        if (laidOut) {
            return new LayoutDataResult(false, false, false, true, false);
        } else {
            return new LayoutDataResult(true, false, false, false, false);
        }
    }

    private LayoutDataResult updateDNodeContainerChildButNotBorderedNodeBounds(View createdView) {
        EObject element = createdView.getElement();
        EObject parent = element.eContainer();

        boolean normalLayout = true;
        boolean centerLayout = false;
        boolean borderLayout = false;
        boolean isAlreadylayouted = false;
        boolean isLayoutReference = false;

        if (element instanceof AbstractDNode && parent instanceof DNodeContainer) {
            Dimension size = null;
            Point location = null;

            AbstractDNode abstractDNode = (AbstractDNode) element;
            LayoutData layoutData = SiriusLayoutDataManager.INSTANCE.getData(abstractDNode);
            if (layoutData == null) {
                layoutData = SiriusLayoutDataManager.INSTANCE.getData(abstractDNode, true);
            }
            if (layoutData != null) {
                location = layoutData.getLocation();
                size = layoutData.getSize();
                normalLayout = false;
                isAlreadylayouted = true;
                isLayoutReference = true;
            }

            if (size == null) {
                size = getDefaultSize(abstractDNode);
            }
            if (location == null) {
                boolean isInRegionContainer = new DNodeContainerExperimentalQuery((DNodeContainer) parent).isRegionContainer();
                boolean skip = false;
                if (STANDARD_LAYOUT_FOR_CREATED_REGION_CONTENT && new DDiagramElementContainerExperimentalQuery((DNodeContainer) parent).isRegion()) {
                    // Get CompartmentView edit part
                    EObject dnodeContainer2_3008 = createdView.eContainer();
                    dnodeContainer2_3008 = dnodeContainer2_3008 != null ? dnodeContainer2_3008.eContainer() : null;

                    skip = dnodeContainer2_3008 != null && (dnodeContainer2_3008.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getCenterAdapterMarker())
                            || dnodeContainer2_3008.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getAdapterMarker()));
                }
                if (!skip && !isInRegionContainer) {
                    normalLayout = false;
                    centerLayout = true;
                    isAlreadylayouted = true;
                }
            }
            // If the DNodeContainer has BorderNodes, it should be marked as to layout its BorderNodes.
            if (!abstractDNode.getOwnedBorderedNodes().isEmpty()) { // if (has border nodes)
                borderLayout = true;
            }

            if (createdView instanceof Node) {
                Node createdNode = (Node) createdView;
                updateBoundsConstraint(createdNode, size, location, abstractDNode);
            }
        }
        return new LayoutDataResult(normalLayout, centerLayout, borderLayout, isAlreadylayouted, isLayoutReference);
    }

    protected void updateLocationConstraint(Bounds bounds, Point location) {
        if (location != null) {
            bounds.setX(location.x);
            bounds.setY(location.y);
        }
    }

    protected void updateSizeConstraint(Bounds bounds, Dimension size, EObject abstractDNode) {
        ResizeKind resizeKind = ResizeKind.NSEW_LITERAL;
        if (abstractDNode instanceof DNode dNode) {
            resizeKind = dNode.getResizeKind();
        }
        if (size != null) {
            if (size.width != -1 && canResizeWidth(resizeKind)) {
                bounds.setWidth(size.width);
            }
            if (size.height != -1 && canResizeHeight(resizeKind)) {
                bounds.setHeight(size.height);
            }
        }
    }

    private void updateBoundsConstraint(Node createdNode, Dimension size, Point location, AbstractDNode abstractDNode) {
        Bounds bounds = (Bounds) createdNode.getLayoutConstraint();
        updateLocationConstraint(bounds, location);
        updateSizeConstraint(bounds, size, abstractDNode);
    }

    private boolean canResizeHeight(ResizeKind resizeKind) {
        return resizeKind.getValue() == ResizeKind.NORTH_SOUTH || resizeKind.getValue() == ResizeKind.NSEW;
    }

    private boolean canResizeWidth(ResizeKind resizeKind) {
        return resizeKind.getValue() == ResizeKind.EAST_WEST || resizeKind.getValue() == ResizeKind.NSEW;
    }

    private Dimension getDefaultSize(AbstractDNode abstractDNode) {
        Dimension defaultSize = new Dimension(-1, -1);
        if (abstractDNode instanceof DNode viewNode && !new org.eclipse.sirius.diagram.business.api.query.DNodeQuery(viewNode).isAutoSize()) {
            defaultSize = new DNodeQuery(viewNode).getDefaultDimension();
        }
        return defaultSize;
    }

    /**
     * Return a create view request.
     * 
     * @param descriptors
     *            a {@link CreateViewRequest.ViewDescriptor} list.
     * @return a create request
     */
    protected CreateViewRequest getCreateViewRequest(final List<? extends ViewDescriptor> descriptors) {
        return new CreateViewRequest(descriptors);
    }

    /**
     * Check if element is a borderedNode.
     * 
     * @param element
     *            the element to check
     * @return true if the element is a bordered node, false otherwise
     */
    protected boolean isBorderedNode(EObject element) {
        boolean result = false;
        if (element instanceof DNode) {
            EObject parent = element.eContainer();
            if (parent instanceof AbstractDNode) {
                AbstractDNode parentDNode = (AbstractDNode) parent;
                result = parentDNode.getOwnedBorderedNodes().contains(element);
            }
        }
        return result;
    }

    /**
     * Check if element is a direct child of {@link DDiagram}.
     * 
     * @param element
     *            the element to check
     * 
     * @return true if the element is a direct child of {@link DDiagram}, false otherwise
     */
    protected boolean isTopLevelNode(EObject element) {
        boolean isTopLevelNode = false;
        EObject container = element.eContainer();
        if (container instanceof DDiagram) {
            DDiagram dDiagram = (DDiagram) container;
            isTopLevelNode = dDiagram.getOwnedDiagramElements().contains(element);
        }
        return isTopLevelNode;
    }

    /**
     * Check if element is a child of {@link DNodeContainer} but not a bordered node.
     * 
     * @param element
     *            the element to check
     * 
     * @return true if the element is a child of {@link DNodeContainer} but not a bordered node, false otherwise
     */
    private boolean isChildNodeButNotBorderedNodeOfContainer(EObject element) {
        boolean isChildNodeButNotBorderedNodeOfContainer = false;
        EObject container = element.eContainer();
        if (container instanceof DNodeContainer) {
            DNodeContainer dNodeContainer = (DNodeContainer) container;
            isChildNodeButNotBorderedNodeOfContainer = dNodeContainer.getOwnedDiagramElements().contains(element);
        }
        return isChildNodeButNotBorderedNodeOfContainer;
    }

    /**
     * Convenience method to create a view descriptor. Will call
     * {@link #getViewDescriptor(IAdaptable, Class, String, int)}
     * 
     * @param element
     *            semantic element.
     * @param factoryHint
     *            the factory hint
     * @return view descriptor
     */
    protected CreateViewRequest.ViewDescriptor getViewDescriptor(final EObject element, final String factoryHint) {
        //
        // create the view descritor
        // final IAdaptable elementAdapter = new EObjectAdapter(element);
        final IAdaptable elementAdapter = new CanonicalElementAdapter(element, factoryHint);

        final int pos = ViewUtil.APPEND;
        final CreateViewRequest.ViewDescriptor descriptor = getViewDescriptor(elementAdapter, Node.class, factoryHint, pos);
        return descriptor;
    }

    /**
     * Return a view descriptor.
     * 
     * @param elementAdapter
     *            semantic element
     * @param viewKind
     *            type of view to create
     * @param hint
     *            factory hint
     * @param index
     *            index
     * @return a create <i>non-persisted</i> view descriptor
     */
    protected CreateViewRequest.ViewDescriptor getViewDescriptor(final IAdaptable elementAdapter, final Class<?> viewKind, final String hint, final int index) {
        return new CreateViewRequest.ViewDescriptor(elementAdapter, viewKind, hint, index, true, DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT);
    }

    private List<View> cleanCanonicalSemanticChildren(View currentView, Collection<View> viewChildren, Collection<SiriusNodeDescriptor> semanticChildren) {
        View viewChild;
        EObject semanticChild;
        Iterator<View> viewChildrenIT = viewChildren.iterator();
        List<View> orphan = new ArrayList<View>();
        Map<EObject, View> viewToSemanticMap = new HashMap<EObject, View>();
        Map<EObject, SiriusNodeDescriptor> semanticToObjectInMap = new HashMap<EObject, SiriusNodeDescriptor>();
        final Set<EObject> realSemanticChilren = new HashSet<EObject>();
        for (SiriusNodeDescriptor object : semanticChildren) {
            realSemanticChilren.add(object.getModelElement());
            semanticToObjectInMap.put(object.getModelElement(), object);
        }
        while (viewChildrenIT.hasNext()) {
            viewChild = viewChildrenIT.next();
            semanticChild = viewChild.getElement();
            if (!new IsOrphanedSwitch(viewChild, realSemanticChilren, currentView).doSwitch(SiriusVisualIDRegistry.getVisualID(currentView))) {
                semanticChildren.remove(semanticToObjectInMap.get(semanticChild));
                viewToSemanticMap.put(semanticChild, viewChild);
            } else {
                orphan.add(viewChild);
            }

            View viewInMap = viewToSemanticMap.get(semanticChild);
            if (viewInMap != null && !viewChild.equals(viewInMap)) {
                if (viewInMap.isMutable()) {
                    orphan.remove(viewChild);
                    orphan.add(viewInMap);
                    viewToSemanticMap.put(semanticChild, viewChild);
                }
            }
        }
        return orphan;
    }
}
