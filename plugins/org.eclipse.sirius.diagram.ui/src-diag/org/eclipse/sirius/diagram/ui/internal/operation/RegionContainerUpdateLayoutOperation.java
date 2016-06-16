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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
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
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

/**
 * Update and keep consistent the GMF Bounds of a regions container and its
 * regions.
 * 
 * @author mporhel
 */
public class RegionContainerUpdateLayoutOperation extends AbstractModelChangeOperation<Void> {

    private final Node regionsContainer;

    /**
     * True if this layout operation is caused for a regions container that has
     * at least one new region or one region less, false otherwise.
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
     *            true if this layout operation is caused for a regions
     *            container that has at least one new region or one region less.
     */
    public RegionContainerUpdateLayoutOperation(Node regionsContainer, boolean containsCreatedOrDeletedRegions) {
        super(Messages.RegionContainerUpdateLayoutOperation_name);
        this.regionsContainer = extractRealRegionsContainer(regionsContainer);
        this.containsCreatedOrDeletedRegions = true;
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
        List<Node> regionsToLayout = Lists.newArrayList();
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
                List<Node> regionsToLayoutSortedByLocation = Lists.newArrayList(regionsToLayout);
                sortRegionsAccordingToLocation(dnc, regionsToLayoutSortedByLocation);
                sortRegions(dnc, regionsToLayout);
                // Check if the order from location (x axis for horizontal stack
                // and y for vertical) is the same as the stored order. If not a
                // layout must be launched.
                boolean isSameOrder = regionsToLayout.equals(regionsToLayoutSortedByLocation);
                if (!isSameOrder || containsCreatedOrDeletedRegions) {
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

        LayoutConstraint layoutConstraint = regionsContainer.getLayoutConstraint();
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
            return self.getActualMapping().getAllContainerMappings();
        }
    }
}
