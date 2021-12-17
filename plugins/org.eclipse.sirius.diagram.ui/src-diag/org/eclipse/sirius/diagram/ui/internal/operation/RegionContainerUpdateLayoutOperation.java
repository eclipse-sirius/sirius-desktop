/*******************************************************************************
 * Copyright (c) 2013, 2021 THALES GLOBAL SERVICES and others.
 *  * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingHelper;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AlphaDropShadowBorder;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * Update and keep consistent the GMF Bounds of a regions container and its regions.
 * 
 * @author mporhel
 */
public class RegionContainerUpdateLayoutOperation extends AbstractModelChangeOperation<Void> {

    private final Node regionsContainer;

    /**
     * True if this layout operation is caused for a regions container that has at least one new region or one region
     * less, false otherwise.
     */
    private final boolean containsCreatedOrDeletedRegions;

    /**
     * Constructor.
     * 
     * @param regionsContainer
     *            The regions container view to layout.
     */
    public RegionContainerUpdateLayoutOperation(Node regionsContainer) {
        super(Messages.RegionContainerUpdateLayoutOperation_name);
        this.regionsContainer = extractRealRegionsContainer(regionsContainer);
        this.containsCreatedOrDeletedRegions = false;
    }

    /**
     * Constructor.
     * 
     * @param regionsContainer
     *            The regions container view to layout.
     * @param containsCreatedOrDeletedRegions
     *            true if this layout operation is caused for a regions container that has at least one new region or
     *            one region less.
     */
    public RegionContainerUpdateLayoutOperation(Node regionsContainer, boolean containsCreatedOrDeletedRegions) {
        super(Messages.RegionContainerUpdateLayoutOperation_name);
        this.regionsContainer = extractRealRegionsContainer(regionsContainer);
        this.containsCreatedOrDeletedRegions = containsCreatedOrDeletedRegions;
    }

    private Node extractRealRegionsContainer(Node node) {
        if (node != null && node.eContainer() instanceof Node) {
            int visualID = SiriusVisualIDRegistry.getVisualID(node);
            if (DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID == visualID || DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID == visualID) {
                return (Node) node.eContainer();
            }
        }
        return node;
    }

    private List<Node> getRegionsToLayout() {
        List<Node> regionsToLayout = new ArrayList<>();
        if (regionsContainer != null) {
            Node labelNode = SiriusGMFHelper.getLabelNode(regionsContainer);
            List<Node> nodes = Lists.newArrayList(Iterables.filter(regionsContainer.getChildren(), Node.class));
            if (labelNode != null && nodes.size() == 2) {
                nodes.remove(labelNode);
                Node compartment = nodes.iterator().next();
                Iterables.addAll(regionsToLayout, Iterables.filter(compartment.getChildren(), Node.class));
            }
        }
        return regionsToLayout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        List<Node> regionsToLayout = getRegionsToLayout();
        if (!regionsToLayout.isEmpty()) {
            EObject element = regionsContainer.getElement();
            if (element instanceof DNodeContainer) {
                DNodeContainer dnc = (DNodeContainer) element;
                List<Node> regionsToLayoutSortedByLocation = new ArrayList<Node>(regionsToLayout);
                sortRegionsAccordingToLocation(dnc, regionsToLayoutSortedByLocation);
                sortRegions(dnc, regionsToLayout);
                // Check if the order from location (x axis for horizontal stack
                // and y for vertical) is the same as the stored order. If not a
                // layout must be launched.
                boolean isSameOrder = regionsToLayout.equals(regionsToLayoutSortedByLocation);
                if (!isSameOrder || containsCreatedOrDeletedRegions) {
                    DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery(dnc);
                    if (query.isVerticalStackContainer()) {
                        updateRegionsLayoutContraints(regionsToLayout, true, new DDiagramElementContainerExperimentalQuery(dnc).isRegion());
                    } else if (query.isHorizontaltackContainer()) {
                        updateRegionsLayoutContraints(regionsToLayout, false, new DDiagramElementContainerExperimentalQuery(dnc).isRegion());
                    }
                }
            }
        }
        return null;
    }

    /*
     * @param isVertical : vertical if true, horizontal if false.
     */
    private void updateRegionsLayoutContraints(List<Node> regionsToLayout, boolean isVertical, boolean containerIsRegion) {
        Size regionContainerSize = null;
        LayoutConstraint regionContainerLayoutConstraint = regionsContainer.getLayoutConstraint();
        if (regionContainerLayoutConstraint instanceof Size) {
            regionContainerSize = (Size) regionContainerLayoutConstraint;
        } else {
            regionContainerSize = NotationFactory.eINSTANCE.createSize();
        }

        // The first item of this list is the default size of a region and a the
        // second (optional) is the size of the last region.
        List<Dimension> defaultRegionsSizeAndOptionalLastRegionSize = null;
        Dimension regionsSizeComputedFromContainer = new Dimension(-1, -1);
        // The regionsSizeComputedFromContainer is useful only when the
        // regionContainer size has been fixed, by VSM or
        // by end-user at creation.
        if (hasRegionContainerFixedSize(regionContainerSize, isVertical)) {
            defaultRegionsSizeAndOptionalLastRegionSize = computeRegionsSizeAccordingToContainerSize(regionsToLayout.size(), isVertical, containerIsRegion, regionContainerSize);
            regionsSizeComputedFromContainer = defaultRegionsSizeAndOptionalLastRegionSize.get(0);
        }

        // The current bounds of the regions.
        Map<Node, Rectangle> regionsBounds = new HashMap<>();
        for (Node node : regionsToLayout) {
            Rectangle bounds = GMFHelper.getBounds(node, true, true);
            regionsBounds.put(node, bounds);
        }
        // The default region size is computed according to the VSM defined size
        // of the corresponding mapping and to the regions size computed from
        // container size. It is then used according to stack orientation.
        Dimension defaultRegionsSize = getDefaultRegionsSize(regionsBounds, regionsSizeComputedFromContainer);

        int y = 0;
        int x = 0;
        for (Node node : regionsToLayout) {
            Rectangle bounds = regionsBounds.get(node);

            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Location) {
                Location loc = (Location) layoutConstraint;
                if (loc.getX() != x) {
                    loc.setX(x);
                }
                if (loc.getY() != y) {
                    loc.setY(y);
                }
            }

            if (layoutConstraint instanceof Size) {
                Size size = (Size) layoutConstraint;
                if (isVertical) {
                    if ((size.getWidth() != -1 || regionsSizeComputedFromContainer.width() != -1) && size.getWidth() != defaultRegionsSize.width()) {
                        size.setWidth(defaultRegionsSize.width());
                    }
                    if (regionsSizeComputedFromContainer.height() != -1) {
                        if (node.equals(regionsToLayout.get(regionsToLayout.size() - 1)) && defaultRegionsSizeAndOptionalLastRegionSize != null
                                && defaultRegionsSizeAndOptionalLastRegionSize.size() == 2) {
                            // Specific height for last region
                            bounds.setHeight(defaultRegionsSizeAndOptionalLastRegionSize.get(1).height());
                        } else {
                            bounds.setHeight(defaultRegionsSize.height());
                        }
                        size.setHeight(bounds.height);
                    }
                } else {
                    if ((size.getHeight() != -1 || regionsSizeComputedFromContainer.height() != -1) && size.getHeight() != defaultRegionsSize.height()) {
                        size.setHeight(defaultRegionsSize.height());
                    }
                    if (regionsSizeComputedFromContainer.width() != -1) {
                        if (node.equals(regionsToLayout.get(regionsToLayout.size() - 1)) && defaultRegionsSizeAndOptionalLastRegionSize != null
                                && defaultRegionsSizeAndOptionalLastRegionSize.size() == 2) {
                            // Specific width for last region
                            bounds.setWidth(defaultRegionsSizeAndOptionalLastRegionSize.get(1).width());
                        } else {
                            bounds.setWidth(defaultRegionsSize.width());
                        }
                        size.setWidth(bounds.width);
                    }
                }
            }

            if (isVertical) {
                y += bounds.height;
            } else {
                x += bounds.width;
            }

        }

        // Set the width and height of the container to -1 as the size is
        // managed by the region contained in this container.
        if (isVertical) {
            if (regionContainerSize.getHeight() != -1) {
                regionContainerSize.setHeight(-1);
            }
            if (regionContainerSize.getWidth() != -1) {
                regionContainerSize.setWidth(-1);
            }
        } else {
            if (regionContainerSize.getWidth() != -1) {
                regionContainerSize.setWidth(-1);
            }
            if (regionContainerSize.getHeight() != -1) {
                regionContainerSize.setHeight(-1);
            }
        }
    }

    /**
     * Check if the current regions container size is a fixed size (fixed in the VSM or by the end-user):
     * <ul>
     * <li>If it is fixed by the end-user both width and height will be different than -1.</li>
     * <li>If it is fixed in the VSM, it is possible that only one value is different that -1, but in this case, only
     * one size is interesting according to stack orientation.</li>
     * </ul>
     * 
     * @param regionsContainerSize
     *            The GMF size of the current regions container
     * @param isVertical
     *            the stack orientation (true for vertical, false for horizontal)
     * @return true if the <code>regionsContainerSize</code> is considered as fixed size, false otherwise.
     */
    private boolean hasRegionContainerFixedSize(Size regionsContainerSize, boolean isVertical) {
        boolean result = regionsContainerSize.getWidth() != -1 && regionsContainerSize.getHeight() != -1;
        if (!result && (regionsContainerSize.getWidth() != -1 || regionsContainerSize.getHeight() != -1)) {
            EObject element = regionsContainer.getElement();
            if (element instanceof DNodeContainer) {
                DNodeContainer dnc = (DNodeContainer) element;
                if (isVertical && regionsContainerSize.getHeight() != -1) {
                    result = dnc.getHeight() != null && regionsContainerSize.getHeight() == dnc.getHeight();
                } else if (!isVertical && regionsContainerSize.getWidth() != -1) {
                    result = dnc.getWidth() != null && regionsContainerSize.getWidth() == dnc.getWidth();
                }
            }
        }
        return result;
    }

    /**
     * This method computes the default region size according to all bounds of the region and to the
     * <code>regionSizeComputedFromContainer</code>.
     * 
     * @param regionsBounds
     *            The bounds of the regions, of the current regions container, to layout.
     * @param regionSizeComputedFromContainer
     *            The default region size computed from regions container size (if it is defined). If the regions
     *            container size is not defined, the <code>regionSizeComputedFromContainer</code> width/height will be
     *            "-1". If it is not "-1", it is priority.
     * @return The default dimension to consider for the region of this regions container.
     */
    private Dimension getDefaultRegionsSize(Map<Node, Rectangle> regionsBounds, Dimension regionSizeComputedFromContainer) {
        Dimension result = new Dimension(-1, -1);
        for (Rectangle bounds : regionsBounds.values()) {
            result.setWidth(Math.max(result.width, bounds.width));
            result.setHeight(Math.max(result.height, bounds.height));
        }
        if (regionSizeComputedFromContainer.width() != -1) {
            // The size of the regions container has been fixed, by VSM or
            // by end-user at creation, this dimension must be kept for the
            // first region creation. At the next pass in this method, the
            // size of the regions container will be {-1, -1}: this is done
            // at the end of the current layout operation.
            result.setWidth(regionSizeComputedFromContainer.width());
        }
        if (regionSizeComputedFromContainer.height() != -1) {
            // The size of the regions container has been fixed, by VSM or
            // by end-user at creation, this dimension must be kept for the
            // first region creation. At the next pass in this method, the
            // size of the regions container will be {-1, -1}: this is done
            // at the end of the current layout operation.
            result.setHeight(regionSizeComputedFromContainer.height());
        }
        return result;
    }

    /**
     * Compute the default size of regions according to regions container size. This method is called only when the
     * regions container has not an auto sized for its GMF size.
     * 
     * @param nbRegionsToLayout
     *            Number of regions to layout
     * @param vertical
     *            true if the stack is vertical, false if it is horizontal
     * @param containerIsRegion
     *            true if the container is also a region
     * @param regionsContainerSize
     *            The size, defined in the VSM or by the end-user at creation, of the regions container
     * @return list of dimension (the first is the size of a region, the second, optional, is the size of the last
     *         region if different of the others)
     */
    private List<Dimension> computeRegionsSizeAccordingToContainerSize(int nbRegionsToLayout, boolean vertical, boolean containerIsRegion, Size regionsContainerSize) {
        List<Dimension> result = new ArrayList<>();
        Dimension regionContainerContentPaneSize = new Dimension(regionsContainerSize.getWidth(), regionsContainerSize.getHeight());
        Dimension regionSize = new Dimension();
        // headerHeight includes the title height (with the icon), the label
        // offset, the 1 pixel separator line,
        int headerHeight = 0;
        Dimension labelDimension = GMFHelper.getLabelDimension(regionsContainer, new Dimension(20, 12));
        EObject element = regionsContainer.getElement();
        int borderSize = 0;
        boolean needShadowBorder = true;
        if (element instanceof DNodeContainer) {
            DNodeContainer dnc = (DNodeContainer) element;
            Style siriusStyle = dnc.getStyle();
            // Get border size
            if (siriusStyle instanceof BorderedStyle) {
                if (((BorderedStyle) siriusStyle).getBorderSize() != null) {
                    borderSize = ((BorderedStyle) siriusStyle).getBorderSize().intValue();
                }
            }
            // Get header size (title and all associated margin)
            if (!new DDiagramElementQuery(dnc).isLabelHidden()) {
                int titleHeight = labelDimension.height();
                int separatorLineHeight = 1;
                int labelOffset = IContainerLabelOffsets.LABEL_OFFSET;
                // As in
                // org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramContainerEditPartOperation.refreshBorder(AbstractDiagramElementContainerEditPart,
                // ViewNodeContainerFigureDesc, ContainerStyle)
                if (!containerIsRegion) {
                    labelOffset -= 1;
                }
                headerHeight = labelOffset + titleHeight + AbstractDiagramElementContainerEditPart.DEFAULT_SPACING + separatorLineHeight;
            }
            // Compute if the shadow border is needed (as in
            // AbstractDiagramElementContainerEditPart.addDropShadow(NodeFigure,
            // IFigure))
            needShadowBorder = !(new DDiagramElementContainerExperimentalQuery(dnc).isRegion() || dnc.getOwnedStyle() instanceof WorkspaceImage);
        }

        // Adds all the expected margin
        regionContainerContentPaneSize.setHeight(regionContainerContentPaneSize.height() - (borderSize + headerHeight + borderSize));
        regionContainerContentPaneSize.setWidth(regionContainerContentPaneSize.width() - (borderSize + borderSize));
        if (needShadowBorder) {
            // Remove the 2 pixels corresponding to the shadow insets
            // (AlphaDropShadowBorder.getInsets(IFigure))
            regionContainerContentPaneSize.setHeight(regionContainerContentPaneSize.height() - AlphaDropShadowBorder.getDefaultShadowSize());
            regionContainerContentPaneSize.setWidth(regionContainerContentPaneSize.width() - AlphaDropShadowBorder.getDefaultShadowSize());
        }
        if (vertical) {
            if (regionContainerContentPaneSize.height() != 0) {
                regionSize.setHeight(regionContainerContentPaneSize.height() / nbRegionsToLayout);
            }
            regionSize.setWidth(regionContainerContentPaneSize.width());
            result.add(regionSize);
            if (regionContainerContentPaneSize.height() % regionSize.height() != 0) {
                // Compute the precise last height (the regions container height
                // is not necessarily exactly divisible by the number of
                // regions)
                int lastRegionHeight = regionContainerContentPaneSize.height() - (regionSize.height() * (nbRegionsToLayout - 1));
                result.add(new Dimension(regionContainerContentPaneSize.width(), lastRegionHeight));
            }
        } else {
            if (regionsContainerSize.getWidth() != 0) {
                regionSize.setWidth(regionContainerContentPaneSize.width() / nbRegionsToLayout);
            }
            regionSize.setHeight(regionContainerContentPaneSize.height());
            result.add(regionSize);
            if (regionContainerContentPaneSize.width() % regionSize.width() != 0) {
                // Compute the precise last width (the regions container width
                // is not necessarily exactly divisible by the number of
                // regions)
                int lastRegionWidth = regionContainerContentPaneSize.width() - (regionSize.width() * (nbRegionsToLayout - 1));
                result.add(new Dimension(lastRegionWidth, regionContainerContentPaneSize.height()));
            }
        }
        return result;
    }

    /**
     * Sort the regions with the ddiagram element index comparator and then with a mapping comparator.
     * 
     * @param dNodeContainer
     *            the {@link DNodeContainer} regions container
     * @param modelChildren
     *            the regions
     */
    public static void sortRegions(DNodeContainer dNodeContainer, List<? extends View> modelChildren) {
        new RegionComparisonHelper(dNodeContainer).sort(modelChildren);
    }

    /**
     * Sort the regions with the current x or y location.
     * 
     * @param dNodeContainer
     *            the {@link DNodeContainer} regions container
     * @param modelChildren
     *            the regions
     */
    public static void sortRegionsAccordingToLocation(DNodeContainer dNodeContainer, List<? extends View> modelChildren) {
        final DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery(dNodeContainer);
        Function<View, Integer> locationIndex = new Function<View, Integer>() {
            @Override
            public Integer apply(View view) {
                if (view instanceof Node) {
                    LayoutConstraint constraint = ((Node) view).getLayoutConstraint();
                    if (constraint instanceof Location) {
                        if (query.isHorizontaltackContainer()) {
                            return ((Location) constraint).getX();
                        } else if (query.isVerticalStackContainer()) {
                            return ((Location) constraint).getY();
                        }
                    }
                }
                return Integer.MAX_VALUE;
            }
        };
        Collections.sort(modelChildren, Ordering.natural().onResultOf(locationIndex));
    }

    private static class RegionComparisonHelper extends ComparisonHelper {
        private DNodeContainer self;

        public RegionComparisonHelper(DNodeContainer self) {
            this.self = self;
        }

        @Override
        protected List<? extends DRepresentationElement> getDElementsToSort() {
            return self.getOwnedDiagramElements();
        }

        @Override
        protected List<? extends RepresentationElementMapping> getMappingsToSort() {
            return MappingHelper.getAllContainerMappings(self.getActualMapping());
        }
    }
}
