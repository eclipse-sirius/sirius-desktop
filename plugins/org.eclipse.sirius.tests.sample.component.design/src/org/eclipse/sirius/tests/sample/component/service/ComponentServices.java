/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.component.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.sirius.tools.api.ui.RefreshHelper;

/**
 * Component services.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class ComponentServices {
    private static Predicate<Notification> considerCollapseStateForAutomaticRefreshPredicate = new Predicate<Notification>() {
        @Override
        public boolean test(Notification notification) {
            if (notification != null) {
                if (NotationPackage.eINSTANCE.getDrawerStyle_Collapsed().equals(notification.getFeature())) {
                    if (notification.getNotifier() instanceof EObject) {
                        Option<DDiagram> optionalDDiagram = new EObjectQuery((EObject) notification.getNotifier()).getParentDiagram();
                        if (optionalDDiagram.some()) {
                            return "DiagramWithRegionAndEdges".equals(optionalDDiagram.get().getDescription().getName());
                        }
                    }
                }
            }
            return false;
        }
    };

    /**
     * Register a predicate to consider Collapse/Expand changes as requiring refresh in "DiagramWithRegionAndEdges"
     * diagram.
     */
    public static void considerCollapseStateForAutomaticRefresh(EObject context) {
        RefreshHelper.registerImpactingNotification(considerCollapseStateForAutomaticRefreshPredicate);
    }

    /**
     * Unregister the predicate to consider Collapse/Expand changes as requiring refresh in "DiagramWithRegionAndEdges"
     * diagram.
     */
    public static void doNotConsiderCollapseStateForAutomaticRefresh(EObject context) {
        RefreshHelper.unregisterImpactingNotification(considerCollapseStateForAutomaticRefreshPredicate);
    }

    /**
     * Get all component children that are not payload.
     * 
     * @param component
     * @return all component children
     */
    public Collection<Component> getAllChildren(Component component) {
        Collection<Component> allChildren = new ArrayList<Component>();
        appendChildren(component, allChildren);
        return allChildren;
    }

    /**
     * Get component in column that are related to component in the line.
     * 
     * @param line
     *            component in the selected line
     * @return related component in columns
     */
    public Collection<Component> getIntersectionColumms(Component line) {
        Collection<Component> validColumns = new HashSet<Component>();
        for (Component ref : line.getReferences()) {
            if (!ref.isPayload()) {
                validColumns.add(ref);
            }
        }

        for (Component ref : line.getReferences2()) {
            if (!ref.isPayload()) {
                validColumns.add(ref);
            }
        }

        for (Component opposite : line.getOpposites()) {
            if (!opposite.isPayload()) {
                validColumns.add(opposite);
            }
        }

        Component ref = line.getReference();
        if (ref != null && !ref.isPayload()) {
            validColumns.add(ref);
        }

        return validColumns;
    }

    /**
     * Get intersection label.
     * 
     * @param line
     *            component on the line
     * @param column
     *            column on the line
     * @return intersection label
     */
    public String getIntersectionLabel(Component line, Component column) {
        StringBuilder result = new StringBuilder();
        boolean empty = true;

        if (line.getReferences().contains(column)) {
            result.append("R");
            empty = false;
        }

        if (line.getReferences2().contains(column)) {
            if (empty) {
                result.append("R2");
            } else {
                result.append(", R2");
            }
            empty = false;
        }

        if (line.getOpposites().contains(column)) {
            if (empty) {
                result.append("O");
            } else {
                result.append(", O");
            }
            empty = false;
        }

        if (line.getReference() == column) {
            if (empty) {
                result.append("r");
            } else {
                result.append(", r");
            }
        }

        return result.toString();
    }

    /**
     * Return the list of component link to the current <code>component</code> with "reference2" reference and the same
     * for all its child.
     * 
     * @param component
     *            The concerned component
     * @return the list of component link to the current <code>component</code> with "reference2" reference
     */
    public List<Component> getReference2Hierarchy(Component component) {
        List<Component> components = new ArrayList<>();
        components.addAll(component.getReferences2());
        for (Component child : component.getChildren()) {
            components.addAll(getReference2Hierarchy(child));
        }
        return components;
    }

    /**
     * Mirror of method getReference2Hierarchy(Component) but with the list of the real source of "reference2" list.
     * 
     * @param component
     *            The concerned component
     * @return list of component
     */
    public List<Component> getReference2HierarchyOrigin(Component component) {
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < component.getReferences2().size(); i++) {
            components.add(component);
        }
        for (Component child : component.getChildren()) {
            components.addAll(getReference2HierarchyOrigin(child));
        }
        return components;
    }

    /**
     * Rename the first alias of this component, if there is one.
     * 
     * @param component
     *            The concerned component.
     * @param newName
     *            The new name of the first alias
     * @return The component for convenience
     */
    public Component renameFirstAlias(Component component, String newName) {
        if (component.getAliases().size() > 0) {
            component.getAliases().set(0, newName);
        }
        return component;
    }

    /**
     * A reference is to display when there is no "shortest reference" to display (if source or target is collapsed the
     * DEdge will exist but the corresponding figure will be hidden).
     * 
     * @param source
     *            Semantic element corresponding to the source of the reference
     * @param sourceView
     *            Graphical element corresponding to the source of the reference
     * @param targetView
     *            Graphical element corresponding to the target of the reference
     * @return true if the reference is to display, false otherwise.
     */
    public boolean isReferenceToDisplay(Component source, DNodeContainer sourceView, DNodeContainer targetView) {
        for (DDiagramElement child : sourceView.getOwnedDiagramElements()) {
            if (child instanceof DNodeContainer && (((DNodeContainer) child).getActualMapping().getName().equals("ComponentRegion"))) {
                for (DDiagramElement grandchild : ((DNodeContainer) child).getOwnedDiagramElements()) {
                    if (isReferenceDisplayedByChild((DNodeContainer) grandchild, targetView)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return true if the reference is already displayed by one of the children in all the hierarchy, false otherwise.
     * 
     * @param sourceView
     *            Graphical element corresponding to the source of the reference
     * @param targetView
     *            Graphical element corresponding to the target of the reference
     * @return true if the reference is already displayed, false otherwise.
     */
    protected boolean isReferenceDisplayedByChild(DNodeContainer sourceView, DNodeContainer targetView) {
        if (!isIndirectlyCollapsed(sourceView) && !isIndirectlyCollapsed(targetView)) {
            for (DDiagramElement child : sourceView.getOwnedDiagramElements()) {
                if (child instanceof DNodeContainer && ((DNodeContainer) child).getActualMapping().getName().equals("ComponentRegion")) {
                    for (DDiagramElement grandchild : ((DNodeContainer) child).getOwnedDiagramElements()) {
                        if (isReferenceDisplayedByChild((DNodeContainer) grandchild, targetView)) {
                            return true;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    protected boolean isIndirectlyCollapsed(DNodeContainer container) {
        if (isContainerCollapsed(container)) {
            return true;
        } else {
            return container.eContainer() instanceof DNodeContainer && isContainerCollapsed((DNodeContainer) container.eContainer());
        }
    }

    protected boolean isContainerCollapsed(DNodeContainer container) {
        Node gmfNode = SiriusGMFHelper.getGmfNode(container);
        if (gmfNode != null) {
            for (Object subNode : gmfNode.getChildren()) {
                if (subNode instanceof Node) {
                    for (Object style : ((Node) subNode).getStyles()) {
                        if (style instanceof DrawerStyle) {
                            return ((DrawerStyle) style).isCollapsed();
                        }
                    }
                }
            }
        }
        return false;
    }

    private void appendChildren(Component component, Collection<Component> allChildren) {
        for (Component child : component.getChildren()) {
            if (!child.isPayload()) {
                allChildren.add(child);
                appendChildren(child, allChildren);
            }
        }
    }
}
