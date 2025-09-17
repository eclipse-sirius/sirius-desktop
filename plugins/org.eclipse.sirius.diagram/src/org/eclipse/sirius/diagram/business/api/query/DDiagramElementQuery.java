/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.FoldingFilter;
import org.eclipse.sirius.diagram.FoldingPointFilter;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelCapabilityStyle;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.util.DiagramSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DDiagramElement} as a starting point.
 * 
 * @author mporhel
 */
public class DDiagramElementQuery {

    private DDiagramElement element;

    /**
     * Create a new query.
     * 
     * @param dDiagramElement
     *            the element to query.
     */
    public DDiagramElementQuery(DDiagramElement dDiagramElement) {
        this.element = dDiagramElement;
    }

    /**
     * Return the mapping of the current {@link DDiagramElement}.
     * 
     * @return the mapping of the current {@link DDiagramElement}.
     */
    public Option<? extends RepresentationElementMapping> getMapping() {
        ActualMappingGetter mappingGetter = new ActualMappingGetter();
        return mappingGetter.doSwitch(this.element);
    }

    /**
     * Return the mapping name of the current {@link DDiagramElement}.
     * 
     * @return the mapping name of the current {@link DDiagramElement}.
     */
    public Option<String> getMappingName() {
        String result = null;
        final Option<? extends RepresentationElementMapping> mapping = getMapping();
        if (mapping != null && mapping.some()) {
            result = mapping.get().getName();
        }
        return Options.newSome(result);
    }

    /**
     * Check if this {@link DDiagramElement} is directly hidden.
     * 
     * @return true if the given element is hidden.
     */
    public boolean isHidden() {
        return Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(HideFilter.class));
    }

    /**
     * Tells if the style of this {@link DDiagramElement} has some
     * customizations.
     * 
     * @return true if the style of this {@link DDiagramElement} has some
     *         customizations, false else
     */
    public boolean isCustomized() {
        boolean isCustomized = false;
        if (element instanceof DEdge) {
            isCustomized = new DEdgeQuery((DEdge) element).isCustomized();
        } else if (element instanceof DNode) {
            DNode dNode = (DNode) element;
            if (dNode.getOwnedStyle() != null) {
                isCustomized = !dNode.getOwnedStyle().getCustomFeatures().isEmpty();
            }
        } else if (element instanceof DDiagramElementContainer) {
            DDiagramElementContainer dDiagramElementContainer = (DDiagramElementContainer) element;
            if (dDiagramElementContainer.getOwnedStyle() != null) {
                isCustomized = !dDiagramElementContainer.getOwnedStyle().getCustomFeatures().isEmpty();
            }
        } else if (element instanceof DNodeListElement) {
            DNodeListElement dNodeElementElement = (DNodeListElement) element;
            if (dNodeElementElement.getOwnedStyle() != null) {
                isCustomized = !dNodeElementElement.getOwnedStyle().getCustomFeatures().isEmpty();
            }
        }
        return isCustomized;
    }

    /**
     * Check if the label of this {@link DDiagramElement} is directly hidden.
     * 
     * @return true if the label of the given element is hidden.
     */
    public boolean isLabelHidden() {
        if (element instanceof DEdge) {
            // Edges have multiple labels
            return areAllLabelsHidden();
        }
        return Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(HideLabelFilter.class));
    }

    /**
     * Check if at least one label of this {@link DDiagramElement} is hidden.
     * 
     * @return true if at least one label of this {@link DDiagramElement} is hidden.
     */
    public boolean hasAnyHiddenLabel() {
        if (element instanceof DEdge) {
            return Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(HideLabelFilter.class));
        }
        return isLabelHidden();
    }

    /**
     * Check if all labels of this {@link DDiagramElement} is directly hidden. There is a specific process for
     * {@link DEdge} checking each of its labels. Otherwise the result is the same as the "isLabelHidden" query.
     * 
     * @return true if the label of the given element is hidden.
     */
    public boolean areAllLabelsHidden() {
        if (element instanceof DEdge) {
            return new DEdgeQuery((DEdge) element).areAllLabelsHidden();
        } else {
            return isLabelHidden();
        }
    }

    /**
     * Check if all labels corresponding to the list of visualIds are already hidden concerning this
     * {@link DDiagramElement}. There is a specific process for {@link DEdge} checking each of its labels. Otherwise the
     * result is the same as the "isLabelHidden" query.
     * 
     * @param selectedLabelVisualIds
     *            list of visual IDs to check for hidden status concerning this {@link DEdge}
     * @return true if the label of the given element is hidden.
     */
    public boolean areAllLabelsHidden(List<Integer> selectedLabelVisualIds) {
        if (element instanceof DEdge) {
            Set<HideLabelFilter> hideLabelFilterSet = element.getGraphicalFilters().stream().filter(HideLabelFilter.class::isInstance).map(HideLabelFilter.class::cast).collect(Collectors.toSet());
            return !hideLabelFilterSet.isEmpty() && hideLabelFilterSet.iterator().next().getHiddenLabels().containsAll(selectedLabelVisualIds);
        } else {
            return isLabelHidden();
        }
    }

    /**
     * Check if a specific label of a {@link DEdge} is directly hidden.
     * 
     * @param visualID
     *            the visualID of the DEdgeNameEditPart.
     * @return true if the label of the given element is hidden.
     */
    public boolean isLabelHidden(int visualID) {
        Set<HideLabelFilter> hideLabelFilterSet = element.getGraphicalFilters().stream().filter(HideLabelFilter.class::isInstance).map(HideLabelFilter.class::cast).collect(Collectors.toSet());
        if (!hideLabelFilterSet.isEmpty()) {
            HideLabelFilter hideLabelFilter = hideLabelFilterSet.iterator().next();
            return hideLabelFilter.getHiddenLabels().isEmpty() || hideLabelFilter.getHiddenLabels().contains(visualID);
        }
        return false;
    }

    /**
     * Check if this {@link DDiagramElement} have a label that can be hidden (i.e. a style with labelPosition to
     * border).
     * 
     * @return true if the label of the diagram element can be hidden, false otherwise.
     */
    public boolean canHideLabel() {
        boolean canHideLabel = false;

        if (!isLabelEmpty()) {
            if (element instanceof DEdge) {
                canHideLabel = true;
            } else if (element instanceof DNode) {
                if (LabelPosition.BORDER_LITERAL.equals(((DNode) element).getOwnedStyle().getLabelPosition())) {
                    canHideLabel = true;
                }
            } else if (element instanceof DDiagramElementContainer) {
                // DNodeContainer & DNodeList
                canHideLabel = true;
            }
        }

        return canHideLabel;
    }

    private boolean isLabelEmpty() {

        boolean result = true;
        DiagramElementMapping mapping = element.getDiagramElementMapping();
        if (mapping == null) {
            return result;
        }

        final Style style = element.getStyle();
        if (style != null) {
            StyleDescription styleDescription = style.getDescription();
            if (styleDescription instanceof LabelStyleDescription) {
                String labelExpression = ((LabelStyleDescription) styleDescription).getLabelExpression();
                result = StringUtil.isEmpty(labelExpression);
            } else if (styleDescription instanceof EdgeStyleDescription && element instanceof DEdge) {
                result = !new DEdgeQuery((DEdge) element).hasNonEmptyNameDefinition();
            }
        }
        return result;
    }

    /**
     * Check if this {@link DDiagramElement} is indirectly hidden. It can be
     * directly hidden or hidden by its container or by its source or target not
     * for edges.
     * 
     * @return true if the given element is indirectly hidden.
     */
    public boolean isIndirectlyHidden() {
        if (isHidden()) {
            return true;
        }

        boolean isHidden = false;

        if (element.eContainer() instanceof DDiagramElement) {
            isHidden = new DDiagramElementQuery((DDiagramElement) element.eContainer()).isIndirectlyHidden();
        } else if (element instanceof DEdge) {
            final DEdge edge = (DEdge) element;
            if (edge.getSourceNode() instanceof DDiagramElement) {
                isHidden = isHidden || new DDiagramElementQuery((DDiagramElement) edge.getSourceNode()).isIndirectlyHidden();
            }
            if (edge.getTargetNode() instanceof DDiagramElement) {
                isHidden = isHidden || new DDiagramElementQuery((DDiagramElement) edge.getTargetNode()).isIndirectlyHidden();
            }
        }

        return isHidden;
    }

    /**
     * Check if this {@link DDiagramElement} is directly collapsed.
     * 
     * @return true if the given element is directly collapsed.
     */
    public boolean isCollapsed() {
        return Iterables.any(element.getGraphicalFilters(), Predicates.and(Predicates.instanceOf(CollapseFilter.class), Predicates.not(Predicates.instanceOf(IndirectlyCollapseFilter.class))));

    }

    /**
     * Check if this {@link DDiagramElement} is directly or indirectly
     * collapsed. Ie if this {@link DDiagramElement} is filtered by an activated
     * filter. It can be directly filtered or one of its container / source /
     * target is filtered.
     * 
     * @return true if the given element is indirectly filtered.
     */
    public boolean isIndirectlyCollapsed() {
        if (isCollapsed()) {
            return true;
        }
        return isOnlyIndirectlyCollapsed();
    }

    /**
     * Check if this {@link DDiagramElement} is indirectly collapsed.
     * 
     * @return true if the given element is indirectly filtered.
     */
    public boolean isOnlyIndirectlyCollapsed() {
        return Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(IndirectlyCollapseFilter.class));
    }

    /**
     * Check if this {@link DDiagramElement} is directly filtered by an
     * activated filter.
     * 
     * @return true if the given element is filtered.
     */
    public boolean isFiltered() {
        return Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(AppliedCompositeFilters.class));
    }

    /**
     * Check if this {@link DDiagramElement} is filtered by an activat// Style
     * Style = null; It can be directly filtered or one of its container /
     * source / target is filtered.
     * 
     * @return true if the given element is indireclty filtered.
     */
    public boolean isIndirectlyFiltered() {
        if (isFiltered()) {
            return true;
        }

        boolean isFiltered = false;

        if (element.eContainer() instanceof DDiagramElement) {
            isFiltered = new DDiagramElementQuery((DDiagramElement) element.eContainer()).isFiltered();
        } else if (element instanceof DEdge) {
            final DEdge edge = (DEdge) element;
            if (edge.getSourceNode() instanceof DDiagramElement) {
                isFiltered = isFiltered || new DDiagramElementQuery((DDiagramElement) edge.getSourceNode()).isFiltered();
            }
            if (edge.getTargetNode() instanceof DDiagramElement) {
                isFiltered = isFiltered || new DDiagramElementQuery((DDiagramElement) edge.getTargetNode()).isFiltered();
            }
        }

        return isFiltered;
    }

    /**
     * Check if this {@link DDiagramElement} is collapsed.
     * 
     * @return true if the given element is collapsed.
     */
    public Option<AppliedCompositeFilters> getAppliedCompositeFilters() {
        Iterable<AppliedCompositeFilters> appliedFilters = Iterables.filter(element.getGraphicalFilters(), AppliedCompositeFilters.class);
        if (Iterables.isEmpty(appliedFilters)) {
            return Options.newNone();
        } else {
            return Options.newSome(Iterables.get(appliedFilters, 0));
        }
    }

    /**
     * Get the {@link BasicLabelStyle} of the current {@link DDiagramElement}.
     * 
     * @return a {@link BasicLabelStyle}
     */
    public Option<BasicLabelStyle> getLabelStyle() {
        BasicLabelStyle labelStyle = null;
        if (element instanceof DEdge) {
            labelStyle = ((DEdge) element).getOwnedStyle().getCenterLabelStyle();
        } else if (element instanceof DNode) {
            labelStyle = ((DNode) element).getOwnedStyle();
        } else if (element instanceof DDiagramElementContainer) {
            labelStyle = ((DDiagramElementContainer) element).getOwnedStyle();
        } else if (element instanceof DNodeListElement) {
            labelStyle = ((DNodeListElement) element).getOwnedStyle();
        }
        return Options.newSome(labelStyle);
    }

    /**
     * Switch to avoid many instanceof tests to get the actual mapping of a
     * {@link DDiagramElement}.
     * 
     * @author mporhel
     */
    private static final class ActualMappingGetter extends DiagramSwitch<Option<? extends RepresentationElementMapping>> {
        @Override
        public Option<? extends RepresentationElementMapping> defaultCase(EObject object) {
            return Options.newNone();
        }

        @Override
        public Option<? extends RepresentationElementMapping> caseDDiagramElementContainer(DDiagramElementContainer object) {
            return Options.newSome(object.getActualMapping());
        }

        @Override
        public Option<? extends RepresentationElementMapping> caseDNode(DNode object) {
            return Options.newSome(object.getActualMapping());
        }

        @Override
        public Option<? extends RepresentationElementMapping> caseDNodeListElement(DNodeListElement object) {
            return Options.newSome(object.getActualMapping());
        }

        @Override
        public Option<? extends RepresentationElementMapping> caseDEdge(DEdge object) {
            return new IEdgeMappingQuery(object.getActualMapping()).getEdgeMapping();
        }
    }

    /**
     * Check if the label of the element must be hidden by default at creation.
     * 
     * @return true if the label must be hidden by default at creation, false
     *         otherwise.
     */
    public boolean isLabelVisibleByDefault() {
        boolean result = true;
        if (element.getStyle() instanceof HideLabelCapabilityStyle) {
            result = !((HideLabelCapabilityStyle) element.getStyle()).isHideLabelByDefault();
        }
        return result;
    }

    /**
     * Tests whether the dDiagramElement is explicitly folded, i.e. it is a
     * folding point.
     * 
     * @return <code>true</code> if the dDiagramElement is explicitly folded.
     */
    public boolean isExplicitlyFolded() {
        return Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(FoldingPointFilter.class));
    }

    /**
     * Tests whether the dDiagramElement is indirectly folded because it is
     * accessible from another edge which was explicitly folded.
     * 
     * @return <code>true</code> if the dDiagramElement is explicitly folded.
     */
    public boolean isIndirectlyFolded() {
        return Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(FoldingFilter.class));
    }

    /**
     * Tests whether the dDiagramElement is explicitly or indirectly folded.
     * 
     * @return <code>true</code> if the dDiagramElement is folded (explicitly or
     *         indirectly).
     */
    public boolean isFolded() {
        return Iterables.any(element.getGraphicalFilters(), Predicates.or(Predicates.instanceOf(FoldingPointFilter.class), Predicates.instanceOf(FoldingFilter.class)));
    }
}
