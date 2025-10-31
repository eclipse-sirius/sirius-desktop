/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.business.api.image.WorkspaceImageHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.DesignerEditPartHelper;
import org.eclipse.sirius.diagram.ui.tools.api.migration.DiagramCrossReferencer;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.Style;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This will be used to save the current state of a given DDiagramElement. An element is identified via the URIs of both
 * its target and its mapping.
 * 
 * @param <D>
 *            Type of diagram element.
 * @author lgoubet <a href="mailto:laurent.goubet@obeo.fr">laurent.goubet@obeo.fr</a>
 */
public abstract class AbstractDiagramElementState<D extends DDiagramElement> implements IDiagramElementState<D> {

    /**
     * This cross referencer will return all the references named "element" on GMF Nodes.
     */
    protected DiagramCrossReferencer crossReferencer;

    /**
     * Saved state of the element's 'isVisible' boolean attribute, indicating whether the element is currently visible
     * on the view.
     */
    protected boolean isVisible;

    /**
     * Saved state of an element's HideFilter existence in 'graphicalFilter' reference list, indicating whether the
     * element is currently directly hidden on the view.
     */
    protected boolean isHidden;

    /**
     * Saved state of an element's HideLabelFilter existence in 'graphicalFilter' reference list, indicating whether the
     * element label is currently directly hidden on the view.
     */
    protected boolean isLabelHidden;

    /**
     * These are the GMF Nodes referencing the element which state is saved by this instance. These references will
     * later be fixed.
     */
    protected List<Node> nodes = new ArrayList<Node>();

    /**
     * These are the GMF Edges referencing the element which state is being saved. These instances will later be fixed.
     */
    protected List<Edge> edges = new ArrayList<Edge>();

    /**
     * Saved state of an element's FoldingPointFilter existence in 'graphicalFilter' reference list, indicating whether
     * the element view is directly fold.
     */
    private boolean isExplictlyFolded;

    /**
     * Saved state of an element's FoldingFilter existence in 'graphicalFilter' reference list, indicating whether the
     * element view is indirectly fold.
     */
    private boolean isIndirectlyFold;

    /** The identifier of this state. */
    private final Identifier identifier;

    private Map<Identifier, Map<String, Object>> customizableToCustomizedFeatures = new HashMap<Identifier, Map<String, Object>>();

    private List<AbsoluteBoundsFilter> boundsFilters;

    /**
     * if the node is collapsed, we keep its expanded dimension contained in its CollapseFilter.
     */
    private Dimension expandedDimension;

    /**
     * This constructor will create an instance of this class given the identifier of the element to save (both its
     * target semantic element and its Mapping) as well as the element itself.
     * 
     * @param id
     *            the id of {@link DDiagramElement}
     * @param crossReferencer
     *            the cross-referencer
     */
    public AbstractDiagramElementState(Identifier id, final DiagramCrossReferencer crossReferencer) {
        this.identifier = id;
        this.crossReferencer = crossReferencer;
        this.boundsFilters = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        nodes.clear();
        edges.clear();
        customizableToCustomizedFeatures.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeElementState(EObject target, DiagramElementMapping mapping, D element) {
        customizableToCustomizedFeatures.clear();
        isVisible = element.isVisible();

        // handle isHidden
        DDiagramElementQuery dDiagramElementQuery = new DDiagramElementQuery(element);
        isHidden = dDiagramElementQuery.isHidden();
        isLabelHidden = dDiagramElementQuery.isLabelHidden();

        storeFold(element);

        // Will now cross reference to find the GMF nodes referencing this
        // element.
        final Iterator<Map.Entry<EObject, Collection<Setting>>> referencesIterator = crossReferencer.entrySet().iterator();
        while (referencesIterator.hasNext()) {
            final Map.Entry<EObject, Collection<Setting>> reference = referencesIterator.next();
            if (reference.getKey().equals(element)) {
                final List<Setting> valueList = (List<Setting>) reference.getValue();
                for (int i = 0; i < valueList.size(); i++) {
                    final EStructuralFeature.Setting nextNodeSetting = valueList.get(i);
                    // Can only be Node or Edge. ClassCastExceptions here
                    // indicates a change in DiagramCrossReferencer
                    if (nextNodeSetting.getEObject() instanceof Node) {
                        nodes.add((Node) nextNodeSetting.getEObject());
                    } else {
                        edges.add((Edge) nextNodeSetting.getEObject());
                    }
                }
            }
        }

        final Style ownedStyle = element.getStyle();
        if (ownedStyle != null) {
            storeStyleCustomizations(ownedStyle);
        }

        Iterable<AbsoluteBoundsFilter> flags = Iterables.filter(element.getGraphicalFilters(), AbsoluteBoundsFilter.class);
        if (!Iterables.isEmpty(flags)) {
            Iterables.addAll(boundsFilters, flags);
        }

        // If DDiagramElement is bordered node and is collapsed, the stored size
        // should be the expanded one. The size will be collapsed by the refresh
        // after restore.
        Predicate<Object> predicate = new Predicate<Object>() {

            @Override
            public boolean apply(Object input) {
                if (input instanceof CollapseFilter) {
                    if (((CollapseFilter) input).eIsSet(DiagramPackage.eINSTANCE.getCollapseFilter_Height()) && ((CollapseFilter) input).eIsSet(DiagramPackage.eINSTANCE.getCollapseFilter_Width())) {
                        return true;
                    }
                }
                return false;
            }
        };

        // if the node is collapsed, we save its expanded bounds.
        Iterable<GraphicalFilter> elementCollapseFilters = Iterables.filter(element.getGraphicalFilters(), predicate);
        if (!Iterables.isEmpty(elementCollapseFilters)) {
            GraphicalFilter graphicalFilter = Iterables.get(elementCollapseFilters, 0);
            if (graphicalFilter instanceof CollapseFilter) {
                expandedDimension = new Dimension(((CollapseFilter) graphicalFilter).getWidth(), ((CollapseFilter) graphicalFilter).getHeight());
            }

        }
    }

    /**
     * Store the style customizations for the specified {@link Customizable}.
     * 
     * @param customizable
     *            the specified {@link Customizable}
     */
    private void storeStyleCustomizations(final Customizable customizable) {
        Map<String, Object> customFeatures = new HashMap<String, Object>();
        for (String featureName : customizable.getCustomFeatures()) {
            EStructuralFeature structuralFeature = customizable.eClass().getEStructuralFeature(featureName);
            if (structuralFeature != null) {
                Object value = customizable.eGet(structuralFeature);
                if (value instanceof Collection<?>) {
                    value = Lists.newArrayList((Collection<?>) value);
                }
                customFeatures.put(featureName, value);

            }
        }
        Identifier customizableIdentifier = Identifier.createCustomizableIdentifier(customizable);
        if (customizableIdentifier != null && !customFeatures.isEmpty()) {
            customizableToCustomizedFeatures.put(customizableIdentifier, customFeatures);
        }

        // and next store all contained style customizations.
        Iterator<EObject> iterator = customizable.eAllContents();
        while (iterator.hasNext()) {
            EObject currentObject = iterator.next();
            if (currentObject instanceof Customizable) {
                storeStyleCustomizations((Customizable) currentObject);
            }
        }
    }

    /**
     * Store fold.
     * 
     * @param element
     *            Diagram element.
     */
    protected void storeFold(D element) {
        DDiagramElementQuery query = new DDiagramElementQuery(element);
        isExplictlyFolded = query.isExplicitlyFolded();
        isIndirectlyFold = query.isIndirectlyFolded();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreElementState(final D element) {
        if (isHidden) {
            HideFilterHelper.INSTANCE.hide(element);
        } else {
            HideFilterHelper.INSTANCE.reveal(element);
        }
        if (isLabelHidden) {
            HideFilterHelper.INSTANCE.hideLabel(element);
        } else {
            HideFilterHelper.INSTANCE.revealLabel(element);
        }

        element.setVisible(isVisible);
        if (isExplictlyFolded) {
            addFilterType(element, DiagramFactory.eINSTANCE.createFoldingPointFilter());
        }
        if (isIndirectlyFold) {
            addFilterType(element, DiagramFactory.eINSTANCE.createFoldingFilter());
        }

        for (int i = 0; i < nodes.size(); i++) {
            (nodes.get(i)).setElement(element);
            if (element instanceof DNode) {
                final Iterator<?> iterChildren = (nodes.get(i)).getChildren().iterator();
                while (iterChildren.hasNext()) {
                    final Object next = iterChildren.next();
                    if (next instanceof Node) {
                        final Node node = (Node) next;
                        if (isStyle(node)) {
                            node.setElement(((DNode) element).getOwnedStyle());
                        }
                    }
                }
            }
        }
        for (int i = 0; i < edges.size(); i++) {
            (edges.get(i)).setElement(element);
        }

        if (!boundsFilters.isEmpty()) {
            element.getGraphicalFilters().addAll(boundsFilters);
        }

        // if the node was collapsed, we set back its expanded bounds in its new
        // collapse filter.
        Iterable<CollapseFilter> elementCollapseFilters = Iterables.filter(element.getGraphicalFilters(), CollapseFilter.class);
        Iterator<CollapseFilter> iterator = elementCollapseFilters.iterator();
        while (iterator.hasNext()) {
            CollapseFilter currentCollapseFilter = iterator.next();
            if (expandedDimension != null) {
                currentCollapseFilter.setWidth(expandedDimension.width);
                currentCollapseFilter.setHeight(expandedDimension.height);

            }
        }

        final Style ownedStyle = element.getStyle();
        if (ownedStyle != null) {
            restoreCustomFeatures(ownedStyle);
        }
    }

    /**
     * Restore the style customizations for the specified {@link Customizable}.
     * 
     * @param customizable
     *            the specified {@link Customizable}
     */
    private void restoreCustomFeatures(final Customizable customizable) {
        Customizable currentCustomizable = customizable;
        Identifier customizableIdentifier = Identifier.createCustomizableIdentifier(currentCustomizable);
        Map<String, Object> customizedFeatures = customizableToCustomizedFeatures.get(customizableIdentifier);
        if (customizedFeatures != null) {
            if (isWorkspaceImageStyleSetedByUser(currentCustomizable, customizedFeatures)) {
                Object object = customizedFeatures.get(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
                EObject eContainer = currentCustomizable.eContainer();
                EReference eContainmentFeature = currentCustomizable.eContainmentFeature();
                if (object instanceof String && currentCustomizable instanceof BasicLabelStyle && eContainer != null && eContainmentFeature != null) {
                    WorkspaceImageHelper.INSTANCE.updateStyle((BasicLabelStyle) currentCustomizable, (String) object);
                    currentCustomizable = (Customizable) eContainer.eGet(eContainmentFeature);
                }
            }
            for (Entry<String, Object> entry : customizedFeatures.entrySet()) {
                String featureName = entry.getKey();
                Object value = entry.getValue();
                EStructuralFeature eStructuralFeature = currentCustomizable.eClass().getEStructuralFeature(featureName);
                if (eStructuralFeature != null) {
                    currentCustomizable.eSet(eStructuralFeature, value);
                    currentCustomizable.getCustomFeatures().add(eStructuralFeature.getName());
                }
            }
        }

        Iterator<EObject> iterator = currentCustomizable.eAllContents();
        while (iterator.hasNext()) {
            EObject currentObject = iterator.next();
            if (currentObject instanceof Customizable) {
                restoreCustomFeatures((Customizable) currentObject);
            }
        }
    }

    private boolean isWorkspaceImageStyleSetedByUser(Customizable customizable, Map<String, Object> customizedFeatures) {
        boolean isWorkspaceImageStyleSetedByUser = customizedFeatures.keySet().contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName()) && !(customizable instanceof WorkspaceImage);
        return isWorkspaceImageStyleSetedByUser;
    }

    /**
     * Return <code>true</code> if the specified notation node references a Style.
     * 
     * @param node
     *            the notation node.
     * @return <code>true</code> if the specified notation node references a Style.
     */
    private boolean isStyle(final Node node) {
        final String typeStr = node.getType();
        int type = -1;
        try {
            type = Integer.parseInt(typeStr);
        } catch (final NumberFormatException nfe) {
            // do nothing.
        }
        return DesignerEditPartHelper.isNodeStyle(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Restore {@link GraphicalFilter}.
     * 
     * @param element
     *            the {@link DDiagramElement} on which restore the specified {@link GraphicalFilter}
     * @param filter
     *            the specified {@link GraphicalFilter} to restore
     */
    protected void addFilterType(DDiagramElement element, GraphicalFilter filter) {
        if (!Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(filter.getClass()))) {
            element.getGraphicalFilters().add(filter);
        }
    }
}
