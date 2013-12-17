/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.operation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Update and keep consistent the GMF Bounds of a Region Container and its
 * Regions.
 * 
 * @author mporhel
 */
public class RegionContainerUpdateLayoutOperation extends AbstractModelChangeOperation<Void> {

    private final Node regionContainer;

    /**
     * Constructor.
     * 
     * @param regionContainer
     *            The Region Container view to layout.
     */
    public RegionContainerUpdateLayoutOperation(Node regionContainer) {
        super("Layout Regions Operations");
        this.regionContainer = extractRealRegionContainer(regionContainer);
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

            sortRegions(regionContainer, regionsToLayout);

            EObject element = regionContainer.getElement();
            if (element instanceof DNodeContainer) {
                DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery((DNodeContainer) element);
                if (query.isVerticalStackContainer()) {
                    updateRegionsLayoutContraints(regionsToLayout, true);
                } else if (query.isHorizontaltackContainer()) {
                    updateRegionsLayoutContraints(regionsToLayout, false);
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

        int y = getMinY();
        int x = 0;

        for (Node node : regionsToLayout) {
            Rectangle bounds = regionBounds.get(node);

            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Location) {
                Location loc = (Location) layoutConstraint;
                loc.setX(x);
                loc.setY(y);
            }

            if (vertical) {
                y += bounds.height;
            } else {
                x += bounds.width;
            }

            if (layoutConstraint instanceof Size) {
                Size size = (Size) layoutConstraint;
                if (size.getWidth() != -1 && vertical) {
                    size.setWidth(commonWidth);
                }

                if (size.getHeight() != -1 && !vertical) {
                    size.setHeight(commonHeight);
                }
            }
        }

        LayoutConstraint layoutConstraint = regionContainer.getLayoutConstraint();
        if (layoutConstraint instanceof Size) {
            Size size = (Size) layoutConstraint;
            if (vertical) {
                size.setHeight(-1);
            } else {
                size.setWidth(-1);
            }
        }

    }

    private int getMinY() {
        int y = 0;
        Node labelNode = SiriusGMFHelper.getLabelNode(regionContainer);
        if (labelNode != null) {
            y = GMFHelper.getBounds(labelNode, true).bottom();
        }
        return y;
    }

    /**
     * Sort the regions with the ddiagram element index comparator and then with
     * a mapping comparator.
     * 
     * @param containerNode
     *            the region container view
     * @param modelChildren
     *            the regions
     */
    public static void sortRegions(View containerNode, List<? extends View> modelChildren) {
        Collections.sort(modelChildren, new IndexComparator(containerNode));
        Collections.sort(modelChildren, new MappingComparator(containerNode));
    }

    private static class MappingComparator implements Comparator<View> {
        private final View containerView;

        public MappingComparator(View container) {
            this.containerView = container;
        }

        public int compare(View view0, View view1) {
            EObject element0 = view0.getElement();
            EObject element1 = view1.getElement();
            if (element0 instanceof DMappingBased && element1 instanceof DMappingBased) {
                EObject eObj = containerView.getElement();
                if (eObj instanceof DNodeContainer) {
                    DNodeContainer container = (DNodeContainer) eObj;
                    List<ContainerMapping> allMappings = container.getActualMapping().getAllContainerMappings();
                    RepresentationElementMapping origin0 = ((DMappingBased) element0).getMapping();
                    RepresentationElementMapping origin1 = ((DMappingBased) element1).getMapping();
                    return allMappings.indexOf(origin0) - allMappings.indexOf(origin1);
                }
            }
            return 0;
        }
    }

    private static class IndexComparator implements Comparator<View> {
        private final View container;

        public IndexComparator(View container) {
            this.container = container;
        }

        public int compare(View o1, View o2) {
            final EObject semantic = ViewUtil.resolveSemanticElement(container);
            if (semantic instanceof DNodeContainer) {
                final EObject sem1 = ViewUtil.resolveSemanticElement(o1);
                final EObject sem2 = ViewUtil.resolveSemanticElement(o2);
                if (sem1 instanceof DDiagramElementContainer && sem2 instanceof DDiagramElementContainer) {
                    DNodeContainer dNodeContainer = (DNodeContainer) semantic;
                    return dNodeContainer.getContainers().indexOf(sem1) - dNodeContainer.getContainers().indexOf(sem2);
                }
            }
            return 0;
        }
    };
}
