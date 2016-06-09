/*******************************************************************************
 * Copyright (c) 2013, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

/**
 * Update and keep consistent the GMF Bounds of a Region Container and its
 * Regions.
 * 
 * @author mporhel
 */
public class RegionContainerUpdateLayoutOperation extends AbstractModelChangeOperation<Void> {

    private final Node regionContainer;

    private Set<View> createdNodeViews = Sets.newHashSet();

    /**
     * Constructor.
     * 
     * @param regionContainer
     *            The Region Container view to layout.
     */
    public RegionContainerUpdateLayoutOperation(Node regionContainer) {
        super(Messages.RegionContainerUpdateLayoutOperation_name);
        this.regionContainer = extractRealRegionContainer(regionContainer);
    }

    /**
     * Constructor.
     * 
     * @param regionContainer
     *            The Region Container view to layout.
     * @param createdNodeViews
     *            List of views created since the last call to the
     *            RegionContainerUpdateLayoutOperation (and that can have effect
     *            on the layout of the regions container).
     */
    public RegionContainerUpdateLayoutOperation(Node regionContainer, Set<View> createdNodeViews) {
        this(regionContainer);
        this.createdNodeViews = createdNodeViews;
    }

    private Node extractRealRegionContainer(Node node) {
        if (node != null && node.eContainer() instanceof Node) {
            int visualID = SiriusVisualIDRegistry.getVisualID(node);
            if (DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID == visualID || DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID == visualID) {
                return (Node) node.eContainer();
            }
        }
        return node;
    }

    private List<Node> getRegionsToLayout() {
        List<Node> regionsToLayout = Lists.newArrayList();
        if (regionContainer != null) {
            Node labelNode = SiriusGMFHelper.getLabelNode(regionContainer);
            List<Node> nodes = Lists.newArrayList(Iterables.filter(regionContainer.getChildren(), Node.class));
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
            EObject element = regionContainer.getElement();
            if (element instanceof DNodeContainer) {
                DNodeContainer dnc = (DNodeContainer) element;
                List<Node> regionsToLayoutSortedByLocation = Lists.newArrayList(regionsToLayout);
                sortRegionsAccordingToLocation(dnc, regionsToLayoutSortedByLocation);
                sortRegions(dnc, regionsToLayout);
                boolean isOrderChanged = !regionsToLayout.equals(regionsToLayoutSortedByLocation);
                if (isOrderChanged || isImpactedByNewViews()) {
                    DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery(dnc);
                    if (query.isVerticalStackContainer()) {
                        updateRegionsLayoutContraints(regionsToLayout, true);
                    } else if (query.isHorizontaltackContainer()) {
                        updateRegionsLayoutContraints(regionsToLayout, false);
                    }
                }
            }

        }
        return null;
    }

    /**
     * Check if at least one of the new views concerned the current region
     * container.
     * 
     * @param dnc
     *            The container to which we want to know if it is impacted
     * @return true if at least one of the created views is contained by the
     *         <code>regionContainer</code>, false otherwise.
     */
    private boolean isImpactedByNewViews() {
        return Iterables.any(createdNodeViews, new Predicate<View>() {
            @Override
            public boolean apply(View input) {
                return input instanceof Node && new NodeQuery((Node) input).isDescendantOf(regionContainer);
            }
        });
    }

    /*
     * @param vertical : vertical if true, horizontal if false.
     */
    private void updateRegionsLayoutContraints(List<Node> regionsToLayout, boolean vertical) {
        Map<Node, Rectangle> regionBounds = Maps.newHashMap();
        int commonWidth = -1;
        int commonHeight = -1;
        for (Node node : regionsToLayout) {
            Rectangle bounds = GMFHelper.getBounds(node, true, true);
            commonWidth = Math.max(commonWidth, bounds.width);
            commonHeight = Math.max(commonHeight, bounds.height);
            regionBounds.put(node, bounds);
        }

        int y = 0;
        int x = 0;
        for (Node node : regionsToLayout) {
            Rectangle bounds = regionBounds.get(node);

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

            if (vertical) {
                y += bounds.height;
            } else {
                x += bounds.width;
            }

            if (layoutConstraint instanceof Size) {
                Size size = (Size) layoutConstraint;
                if (vertical && size.getWidth() != -1 && size.getWidth() != commonWidth) {
                    size.setWidth(commonWidth);
                }

                if (!vertical && size.getHeight() != -1 && size.getHeight() != commonHeight) {
                    size.setHeight(commonHeight);
                }
            }
        }

        LayoutConstraint layoutConstraint = regionContainer.getLayoutConstraint();
        if (layoutConstraint instanceof Size) {
            Size size = (Size) layoutConstraint;
            if (vertical) {
                if (size.getHeight() != -1) {
                    size.setHeight(-1);
                }
                if (size.getWidth() != -1 && size.getWidth() < commonWidth) {
                    size.setWidth(-1);
                }
            } else {
                if (size.getWidth() != -1) {
                    size.setWidth(-1);
                }
                if (size.getHeight() != -1 && size.getHeight() < commonHeight) {
                    size.setHeight(-1);
                }
            }
        }

    }

    /**
     * Sort the regions with the ddiagram element index comparator and then with
     * a mapping comparator.
     * 
     * @param dNodeContainer
     *            the {@link DNodeContainer} region container
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
     *            the {@link DNodeContainer} region container
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
            return self.getActualMapping().getAllContainerMappings();
        }
    }
}
