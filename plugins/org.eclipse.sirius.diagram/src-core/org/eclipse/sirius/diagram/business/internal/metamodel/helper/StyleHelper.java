/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.GaugeSection;
import org.eclipse.sirius.diagram.HideLabelCapabilityStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.internal.color.DiagramStyleColorUpdater;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.CustomStyleDescription;
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.util.DiagramSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * This helper class contains utility methods to create and update (refresh)
 * concrete style instances from a style description. This helper modify
 * sometimes the diagram element to which apply the style (width, height, ...).
 * 
 * @author mchauvin
 */
public final class StyleHelper {

    // This is default size used for node height, node width or edge size when
    // everything for computing these values failed.
    // "3" is an arbitrary value extracted from this code and it doesn't seem to
    // be linked with any existing requirement.
    private static final Integer DEFAULT_SIZE = Integer.valueOf(3);

    private static final Integer AUTO_SIZE_CONTAINER = Integer.valueOf(-1);

    private IInterpreter interpreter;

    private DiagramStyleColorUpdater colorUpdater;

    /**
     * Create the helper.
     * 
     * @param interpreter
     *            interpreter used to evaluate expressions.
     */
    public StyleHelper(IInterpreter interpreter) {
        this.interpreter = interpreter;
        this.colorUpdater = new DiagramStyleColorUpdater();
    }

    /**
     * Refresh a style based on its description. This style is considered as the
     * previous style to used to look its the customizations.
     * 
     * @param style
     *            the style to refresh.
     */
    public void refreshStyle(final Style style) {
        refreshStyle(style, Options.newSome(style));
    }

    /**
     * Refresh a style based on its description.
     * 
     * @param style
     *            the style to refresh.
     * @param previousStyle
     *            the previous style (if existing) to keep compatible
     *            customization.
     */
    public void refreshStyle(final Style style, final Option<? extends Style> previousStyle) {
        if (style != null && !isWorkspaceImageStyleWithNotWorkspaceImageDescription(style)) {
            final StyleDescription description = style.getDescription();
            // Some styles are ContainerStyleDescription AND
            // NodeStyleDescription (WorkspaceImageDescription for example).
            // Verify if style is instanceof Container Style. this case is
            // only necessary for WorkspaceImageDescription who was be
            // Container Style or Node Style.
            if (description instanceof ContainerStyleDescription && style instanceof ContainerStyle) {
                updateContainerStyle((ContainerStyleDescription) description, (ContainerStyle) style, (Option<Style>) previousStyle);
            }
            if (description instanceof NodeStyleDescription && style instanceof NodeStyle) {
                updateNodeStyle((NodeStyleDescription) description, (NodeStyle) style, (Option<Style>) previousStyle);
            }
            if (description instanceof EdgeStyleDescription) {
                updateEdgeStyle((EdgeStyleDescription) description, (EdgeStyle) style, (Option<EdgeStyle>) previousStyle);
            }
            refreshColors(style, previousStyle);
        }
    }

    private boolean isWorkspaceImageStyleWithNotWorkspaceImageDescription(Style style) {
        boolean isWorkspaceImageStyleWithNotWorkspaceImageDescription = style instanceof WorkspaceImage && !(style.getDescription() instanceof WorkspaceImageDescription)
                && !style.getCustomFeatures().isEmpty();
        return isWorkspaceImageStyleWithNotWorkspaceImageDescription;
    }

    /**
     * Create a new style from its description. All the variable fields are not
     * computed during the creation. Indeed, they need a context that is not
     * known at present.
     * 
     * @param description
     *            the style description.
     * @return the created style.
     */
    public Style createStyle(final StyleDescription description) {
        Style style = null;
        if (description instanceof ContainerStyleDescription) {
            style = createContainerStyle((ContainerStyleDescription) description);
        } else if (description instanceof NodeStyleDescription) {
            style = createNodeStyle((NodeStyleDescription) description);
        } else if (description instanceof EdgeStyleDescription) {
            style = createEdgeStyle((EdgeStyleDescription) description);
        }
        return style;
    }

    /**
     * 
     * Create a new edge style from its description. All the variable fields are
     * not computed during the creation. Indeed, they need a context that is not
     * known at present.
     * 
     * @param description
     *            the edge style description
     * @return the created style
     */
    public EdgeStyle createEdgeStyle(final EdgeStyleDescription description) {
        EdgeStyle style = null;
        if (description instanceof BracketEdgeStyleDescription) {
            style = DiagramFactory.eINSTANCE.createBracketEdgeStyle();
        } else {
            style = DiagramFactory.eINSTANCE.createEdgeStyle();
        }
        if (description.getBeginLabelStyleDescription() != null) {
            style.setBeginLabelStyle(DiagramFactory.eINSTANCE.createBeginLabelStyle());
        }
        if (description.getEndLabelStyleDescription() != null) {
            style.setEndLabelStyle(DiagramFactory.eINSTANCE.createEndLabelStyle());
        }
        if (description.getCenterLabelStyleDescription() != null) {
            style.setCenterLabelStyle(DiagramFactory.eINSTANCE.createCenterLabelStyle());
        }
        Option<EdgeStyle> noPreviousStyle = Options.newNone();
        updateEdgeStyle(description, style, noPreviousStyle);
        refreshColors(style, noPreviousStyle);
        return style;
    }

    private void updateEdgeStyle(final EdgeStyleDescription edgeDescription, final EdgeStyle edgeStyle, Option<EdgeStyle> previousStyle) {
        Integer size = Integer.valueOf(1);

        if (edgeStyle.getDescription() != edgeDescription) {
            edgeStyle.setDescription(edgeDescription);
        }
        if (edgeDescription != null) {
            if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__SOURCE_ARROW.getName())) {
                edgeStyle.setSourceArrow(previousStyle.get().getSourceArrow());
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__SOURCE_ARROW.getName());
            } else {
                if (edgeStyle.getSourceArrow().getValue() != edgeDescription.getSourceArrow().getValue()
                        && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__SOURCE_ARROW.getName())) {
                    edgeStyle.setSourceArrow(edgeDescription.getSourceArrow());
                }
            }
            if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__TARGET_ARROW.getName())) {
                edgeStyle.setTargetArrow(previousStyle.get().getTargetArrow());
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__TARGET_ARROW.getName());
            } else {
                if (edgeStyle.getTargetArrow().getValue() != edgeDescription.getTargetArrow().getValue()
                        && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__TARGET_ARROW.getName())) {
                    edgeStyle.setTargetArrow(edgeDescription.getTargetArrow());
                }
            }

            if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__LINE_STYLE.getName())) {
                edgeStyle.setLineStyle(previousStyle.get().getLineStyle());
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__LINE_STYLE.getName());
            } else {
                if (edgeStyle.getLineStyle().getValue() != edgeDescription.getLineStyle().getValue()
                        && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__LINE_STYLE.getName())) {
                    edgeStyle.setLineStyle(edgeDescription.getLineStyle());
                }
            }
            if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__FOLDING_STYLE.getName())) {
                edgeStyle.setFoldingStyle(previousStyle.get().getFoldingStyle());
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__FOLDING_STYLE.getName());
            } else {
                if (edgeStyle.getFoldingStyle().getValue() != edgeDescription.getFoldingStyle().getValue()
                        && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__FOLDING_STYLE.getName())) {
                    edgeStyle.setFoldingStyle(edgeDescription.getFoldingStyle());
                }
            }
            if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__SIZE.getName())) {
                edgeStyle.setSize(previousStyle.get().getSize());
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__SIZE.getName());
            } else {
                if (edgeDescription.getSizeComputationExpression() != null && edgeStyle.eContainer() != null
                        && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__SIZE.getName())) {
                    try {
                        size = interpreter.evaluateInteger(((DSemanticDecorator) edgeStyle.eContainer()).getTarget(), edgeDescription.getSizeComputationExpression());
                    } catch (final EvaluationException e) {
                        // silent.
                    }
                    edgeStyle.setSize(size);
                }
            }
            // Get the override edge routing from the diagram preferences. If
            // the edge routing override is enable, it is more priority than the
            // VSM style but less that the manual customization.
            final IPreferencesService service = Platform.getPreferencesService();
            Option<EdgeRouting> overrideEdgeRouting = Options.newNone();
            boolean isOverideEnabled = service.getBoolean(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE_DEFAULT_VALUE, null);
            if (isOverideEnabled) {
                int routingStyle = service.getInt(DiagramPlugin.ID, SiriusDiagramCorePreferences.PREF_LINE_STYLE, SiriusDiagramCorePreferences.PREF_LINE_STYLE_DEFAULT_VALUE, null);
                overrideEdgeRouting = Options.newSome(EdgeRouting.get(routingStyle));
            }
            // If a previous style exists, we are not on a creation of an edge
            // so the potential override is ignored.
            if (previousStyle.some()) {
                // If the previous style has been manually customized we use the
                // same customization.
                if (previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName())) {
                    edgeStyle.setRoutingStyle(previousStyle.get().getRoutingStyle());
                    edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName());
                }
            } else if (overrideEdgeRouting.some()) {
                // Add the feature routingStyle as customization
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName());
                // Set the new value if different.
                if (edgeStyle.getRoutingStyle().getValue() != overrideEdgeRouting.get().getValue()) {
                    edgeStyle.setRoutingStyle(overrideEdgeRouting.get());
                }
            }
            // Use the VSM value if there is no customization (from manual
            // customization of existing style or from preference)

            if (edgeDescription.getRoutingStyle().getValue() != edgeStyle.getRoutingStyle().getValue()
                    && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName())) {
                edgeStyle.setRoutingStyle(edgeDescription.getRoutingStyle());
            }
            updateLabels(edgeDescription, edgeStyle, previousStyle);
            updateEdgeCenteringInformations(edgeDescription, edgeStyle, previousStyle);
        }
    }

    private void updateLabels(EdgeStyleDescription edgeDescription, EdgeStyle edgeStyle, Option<EdgeStyle> previousStyle) {
        if (previousStyle.some()) {
            if (previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__CENTER_LABEL_STYLE.getName())) {
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__CENTER_LABEL_STYLE.getName());
                edgeStyle.setCenterLabelStyle(previousStyle.get().getCenterLabelStyle());
            }
        }
        if (edgeDescription.getCenterLabelStyleDescription() != null && edgeStyle.getCenterLabelStyle() == null
                && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__CENTER_LABEL_STYLE.getName())) {
            CenterLabelStyle centerLabelStyle = DiagramFactory.eINSTANCE.createCenterLabelStyle();
            edgeStyle.setCenterLabelStyle(centerLabelStyle);
        }
        updateEdgeLabel(edgeDescription.getCenterLabelStyleDescription(), edgeStyle.getCenterLabelStyle());

        if (previousStyle.some()) {
            if (previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__BEGIN_LABEL_STYLE.getName())) {
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__BEGIN_LABEL_STYLE.getName());
                edgeStyle.setBeginLabelStyle(previousStyle.get().getBeginLabelStyle());
            }
        }
        if (edgeDescription.getBeginLabelStyleDescription() != null && edgeStyle.getBeginLabelStyle() == null
                && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__BEGIN_LABEL_STYLE.getName())) {
            BeginLabelStyle beginLabelStyle = DiagramFactory.eINSTANCE.createBeginLabelStyle();
            edgeStyle.setBeginLabelStyle(beginLabelStyle);
        }
        updateEdgeLabel(edgeDescription.getBeginLabelStyleDescription(), edgeStyle.getBeginLabelStyle());

        if (previousStyle.some()) {
            if (previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__END_LABEL_STYLE.getName())) {
                edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__END_LABEL_STYLE.getName());
                edgeStyle.setBeginLabelStyle(previousStyle.get().getBeginLabelStyle());
            }
        }
        if (edgeDescription.getEndLabelStyleDescription() != null && edgeStyle.getEndLabelStyle() == null
                && !edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__END_LABEL_STYLE.getName())) {
            EndLabelStyle endLabelStyle = DiagramFactory.eINSTANCE.createEndLabelStyle();
            edgeStyle.setEndLabelStyle(endLabelStyle);
        }
        updateEdgeLabel(edgeDescription.getEndLabelStyleDescription(), edgeStyle.getEndLabelStyle());
    }

    private void updateEdgeCenteringInformations(EdgeStyleDescription edgeDescription, EdgeStyle edgeStyle, Option<EdgeStyle> previousStyle) {

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__CENTERED.getName())) {
            edgeStyle.setCentered(previousStyle.get().getCentered());
            edgeStyle.getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__CENTERED.getName());
        } else {
            if (!edgeStyle.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__CENTERED.getName())) {

                computeCentered(edgeDescription, edgeStyle);
            }
        }
    }

    private void computeCentered(EdgeStyleDescription edgeDescription, EdgeStyle edgeStyle) {
        switch (edgeDescription.getEndsCentering().getValue()) {
        case CenteringStyle.BOTH_VALUE:
            edgeStyle.setCentered(CenteringStyle.BOTH);
            break;
        case CenteringStyle.SOURCE_VALUE:
            if (isTargetCentered(edgeDescription, edgeStyle)) {
                edgeStyle.setCentered(CenteringStyle.BOTH);
            } else {
                edgeStyle.setCentered(CenteringStyle.SOURCE);
            }
            break;
        case CenteringStyle.TARGET_VALUE:
            if (isSourceCentered(edgeDescription, edgeStyle)) {
                edgeStyle.setCentered(CenteringStyle.BOTH);
            } else {
                edgeStyle.setCentered(CenteringStyle.TARGET);
            }

            break;
        case CenteringStyle.NONE_VALUE:
            boolean isSourceCentered = isSourceCentered(edgeDescription, edgeStyle);
            boolean isTargetCentered = isTargetCentered(edgeDescription, edgeStyle);
            if (isSourceCentered && isTargetCentered) {
                edgeStyle.setCentered(CenteringStyle.BOTH);
            } else if (isSourceCentered) {
                edgeStyle.setCentered(CenteringStyle.SOURCE);
            } else if (isTargetCentered) {
                edgeStyle.setCentered(CenteringStyle.TARGET);
            } else {
                edgeStyle.setCentered(CenteringStyle.NONE);
            }
            break;
        default:
            // This case should not append
            break;
        }
    }

    private boolean isTargetCentered(EdgeStyleDescription edgeDescription, EdgeStyle edgeStyle) {
        EObject dEdgeEObject = edgeStyle.eContainer();

        // if the edge style is affected to a DEdge
        if (dEdgeEObject instanceof DEdge) {
            EdgeTarget edgeTarget = ((DEdge) dEdgeEObject).getTargetNode();
            if (edgeTarget instanceof DDiagramElement) {

                List<DiagramElementMapping> centeredTargetMappings = edgeDescription.getCenteredTargetMappings();
                return isHierarchyPartOfMappingsList(edgeTarget, centeredTargetMappings);

            }
        }
        return false;
    }

    private boolean isSourceCentered(EdgeStyleDescription edgeDescription, EdgeStyle edgeStyle) {
        EObject dEdgeEObject = edgeStyle.eContainer();

        // if the edge style is affected to a DEdge
        if (dEdgeEObject instanceof DEdge) {
            EdgeTarget edgeSource = ((DEdge) dEdgeEObject).getSourceNode();
            if (edgeSource instanceof DDiagramElement) {

                List<DiagramElementMapping> centeredSourceMappings = edgeDescription.getCenteredSourceMappings();
                return isHierarchyPartOfMappingsList(edgeSource, centeredSourceMappings);
            }
        }
        return false;
    }

    private boolean isHierarchyPartOfMappingsList(EdgeTarget edgeTarget, List<DiagramElementMapping> mappingsList) {

        DiagramElementMapping diagramElementMapping = ((DDiagramElement) edgeTarget).getDiagramElementMapping();
        DiagramElementMappingQuery mappingQuery = new DiagramElementMappingQuery(diagramElementMapping);
        for (DiagramElementMapping currentMapping : mappingsList) {
            if (EqualityHelper.areEquals(diagramElementMapping, currentMapping) || mappingQuery.isSubTypeOf(currentMapping)) {
                return true;
            }
        }
        return false;
    }

    private void updateEdgeLabel(BasicLabelStyleDescription description, BasicLabelStyle style) {
        if (description instanceof BeginLabelStyleDescription && style instanceof BeginLabelStyle && ((BeginLabelStyle) style).getDescription() != description) {
            ((BeginLabelStyle) style).setDescription(description);
        }

        if (description instanceof EndLabelStyleDescription && style instanceof EndLabelStyle && ((EndLabelStyle) style).getDescription() != description) {
            ((EndLabelStyle) style).setDescription(description);
        }

        if (description instanceof CenterLabelStyleDescription && style instanceof CenterLabelStyle && ((CenterLabelStyle) style).getDescription() != description) {
            ((CenterLabelStyle) style).setDescription(description);
        }

        if (description != null && style != null) {
            if (style.isShowIcon() != description.isShowIcon() && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON.getName())) {
                style.setShowIcon(description.isShowIcon());
            }
            if (style.getIconPath() != null && !style.getIconPath().equals(description.getIconPath())
                    && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH.getName())) {
                style.setIconPath(description.getIconPath());
            }

            Option<NodeStyle> noPreviousStyle = Options.newNone();
            updateBasicLabelStyleFeatures(description, style, noPreviousStyle);
        }
    }

    private void updateLabelStyleFeatures(final LabelStyleDescription description, final LabelStyle style, Option<? extends LabelStyle> previousStyle) {
        final LabelAlignment alignment = description.getLabelAlignment();

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(ViewpointPackage.Literals.LABEL_STYLE__LABEL_ALIGNMENT.getName())) {
            style.setLabelAlignment(previousStyle.get().getLabelAlignment());
            style.getCustomFeatures().add(ViewpointPackage.Literals.LABEL_STYLE__LABEL_ALIGNMENT.getName());
        } else {
            if (style.getLabelAlignment() != alignment && !style.getCustomFeatures().contains(ViewpointPackage.Literals.LABEL_STYLE__LABEL_ALIGNMENT.getName())) {
                style.setLabelAlignment(alignment);
            }
        }
        updateBasicLabelStyleFeatures(description, style, previousStyle);
    }

    private void updateBasicLabelStyleFeatures(final BasicLabelStyleDescription description, final BasicLabelStyle style, Option<? extends BasicLabelStyle> previousStyle) {
        final List<FontFormat> format = description.getLabelFormat();
        final int size = description.getLabelSize();

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON.getName())) {
            style.setShowIcon(previousStyle.get().isShowIcon());
            style.getCustomFeatures().add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON.getName());
        } else {
            if (style.isShowIcon() != description.isShowIcon() && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON.getName())) {
                style.setShowIcon(description.isShowIcon());
            }
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH.getName())) {
            style.setIconPath(previousStyle.get().getIconPath());
            style.getCustomFeatures().add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH.getName());
        } else {
            if (style.getIconPath() != null && !style.getIconPath().equals(description.getIconPath())
                    && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH.getName())) {
                style.setIconPath(description.getIconPath());
            }
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT.getName())) {
            FontFormatHelper.setFontFormat(style.getLabelFormat(), previousStyle.get().getLabelFormat());
            style.getCustomFeatures().add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT.getName());
        } else {
            if (!style.getLabelFormat().equals(format) && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT.getName())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), format);
            }
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE.getName())) {
            style.setLabelSize(Math.max(previousStyle.get().getLabelSize(), 1));
            style.getCustomFeatures().add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE.getName());
        } else {
            if (style.getLabelSize() != size && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE.getName())) {
                style.setLabelSize(Math.max(size, 1));
            }
        }
    }

    /**
     * Create a new container style from its description. All the variable
     * fields are not computed during the creation. Indeed, they need a context
     * that is not known at present.
     * 
     * @param description
     *            the container style description
     * @return the created style
     */
    public ContainerStyle createContainerStyle(final ContainerStyleDescription description) {
        ContainerStyle style = null;
        if (description instanceof FlatContainerStyleDescription) {
            style = createFlatContainerStyle((FlatContainerStyleDescription) description);
        } else if (description instanceof ShapeContainerStyleDescription) {
            style = createShapeContainerStyle((ShapeContainerStyleDescription) description);
        } else if (description instanceof WorkspaceImageDescription) {
            style = createWorkspaceImage((WorkspaceImageDescription) description);
        }
        if (style != null) {
            style.setHideLabelByDefault(description.isHideLabelByDefault());
            Option<Style> noPreviousStyle = Options.newNone();
            refreshColors(style, noPreviousStyle);
        }

        return style;
    }

    /**
     * @param description
     * @param style
     * @param previousStyle
     *            Could be a NodeStyle if the style is a
     *            WorkspaceImageDescription. This style is both a ContainerStyle
     *            and a NodeStyle (example we passed from a NodeStyle to a
     *            WorkspaceImageDescription). If the style is not a
     *            WorkspaceImageDescription, the previousStyle is inevitably a
     *            ContainerStyle.
     */
    private void updateContainerStyle(final ContainerStyleDescription description, final ContainerStyle style, Option<? extends Style> previousStyle) {
        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON.getName())) {
            if (previousStyle.get() instanceof BasicLabelStyle) {
                style.setShowIcon(((BasicLabelStyle) previousStyle.get()).isShowIcon());
            }
            style.getCustomFeatures().add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON.getName());
        } else {
            if (style.isShowIcon() != description.isShowIcon() && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON.getName())) {
                style.setShowIcon(description.isShowIcon());
            }
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH.getName())) {
            if (previousStyle.get() instanceof BasicLabelStyle) {
                style.setIconPath(((BasicLabelStyle) previousStyle.get()).getIconPath());
            }
            style.getCustomFeatures().add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH.getName());
        } else {
            if (style.getIconPath() != null && !style.getIconPath().equals(description.getIconPath())
                    && !style.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH.getName())) {
                style.setIconPath(description.getIconPath());
            }
        }

        if (description instanceof FlatContainerStyleDescription && style instanceof FlatContainerStyle) {
            updateFlatContainerStyle((FlatContainerStyle) style, (FlatContainerStyleDescription) description, (Option<ContainerStyle>) previousStyle);
        } else if (description instanceof ShapeContainerStyleDescription && style instanceof ShapeContainerStyle) {
            updateShapeContainerStyle((ShapeContainerStyle) style, (ShapeContainerStyleDescription) description, (Option<ContainerStyle>) previousStyle);
        } else if (description instanceof WorkspaceImageDescription && style instanceof WorkspaceImage) {
            updateWorkspaceImage((WorkspaceImage) style, (WorkspaceImageDescription) description, previousStyle);
        }

        if (previousStyle.get() instanceof BorderedStyle) {
            updateBorderedStyleFeatures(description, style, (Option<BorderedStyle>) previousStyle);
        } else {
            Option<BorderedStyle> noPreviousBorderedStyle = Options.newNone();
            updateBorderedStyleFeatures(description, style, noPreviousBorderedStyle);
        }

        updateHideLabelCapabilityFeature(description, style, previousStyle);
    }

    /**
     * Update the "Hide Label Capability" feature of the style.
     * 
     * @param description
     *            the style description
     * @param style
     *            the current style to update
     * @param previousStyle
     *            the previous style
     */
    private void updateHideLabelCapabilityFeature(final HideLabelCapabilityStyleDescription description, final HideLabelCapabilityStyle style, Option<? extends Style> previousStyle) {
        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT.getName())) {
            if (previousStyle.get() instanceof HideLabelCapabilityStyle) {
                style.setHideLabelByDefault(((HideLabelCapabilityStyle) previousStyle.get()).isHideLabelByDefault());
            }

            if (style instanceof Customizable) {
                ((Customizable) style).getCustomFeatures().add(DiagramPackage.Literals.HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT.getName());
            }
        } else if (style instanceof Customizable && !((Customizable) style).getCustomFeatures().contains(DiagramPackage.Literals.HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT.getName())) {
            style.setHideLabelByDefault(description.isHideLabelByDefault());
        }
    }

    /**
     * Create a new node style from its description. All the variable fields are
     * not computed during the creation. Indeed, they need a context that is not
     * known at present.
     * 
     * @param description
     *            the container style description
     * @return the created style
     */
    public NodeStyle createNodeStyle(final NodeStyleDescription description) {

        NodeStyle style = null;

        if (description instanceof CustomStyleDescription) {
            style = createCustomStyle((CustomStyleDescription) description);
        } else if (description instanceof SquareDescription) {
            style = createSquare((SquareDescription) description);
        } else if (description instanceof DotDescription) {
            style = createDot((DotDescription) description);
        } else if (description instanceof GaugeCompositeStyleDescription) {
            style = createGaugeCompositeStyle((GaugeCompositeStyleDescription) description);
        } else if (description instanceof NoteDescription) {
            style = createNote((NoteDescription) description);
        } else if (description instanceof BundledImageDescription) {
            style = createBundledImage((BundledImageDescription) description);
        } else if (description instanceof WorkspaceImageDescription) {
            style = createWorkspaceImage((WorkspaceImageDescription) description);
        } else if (description instanceof EllipseNodeDescription) {
            style = createEllipse((EllipseNodeDescription) description);
        } else if (description instanceof LozengeNodeDescription) {
            style = createLozenge((LozengeNodeDescription) description);
        }
        if (style != null) {
            style.setLabelPosition(description.getLabelPosition());
            style.setHideLabelByDefault(description.isHideLabelByDefault());
            Option<Style> noPreviousStyle = Options.newNone();
            refreshColors(style, noPreviousStyle);
        }
        return style;
    }

    /**
     * @param description
     * @param style
     * @param previousStyle
     *            Could be a ContainerStyle if the style is a
     *            WorkspaceImageDescription. This style is both a ContainerStyle
     *            and a NodeStyle (example we passed from a ContainerStyle to a
     *            WorkspaceImageDescription). If the style is not a
     *            WorkspaceImageDescription, the previousStyle is inevitably a
     *            NodeStyle.
     */
    private void updateNodeStyle(final NodeStyleDescription description, final NodeStyle style, Option<? extends Style> previousStyle) {
        boolean brokenStyle = false;

        if (previousStyle.get() instanceof BasicLabelStyle) {
            updateBasicLabelStyleFeatures(description, style, (Option<BasicLabelStyle>) previousStyle);
        } else {
            Option<BasicLabelStyle> noPreviousBasicLabelStyle = Options.newNone();
            updateBasicLabelStyleFeatures(description, style, noPreviousBasicLabelStyle);
        }

        if (description instanceof CustomStyleDescription) {
            if (style instanceof CustomStyle) {
                updateCustomStyle((CustomStyle) style, (CustomStyleDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof SquareDescription) {
            if (style instanceof Square) {
                updateSquare((Square) style, (SquareDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof DotDescription) {
            if (style instanceof Dot) {
                updateDot((Dot) style, (DotDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof GaugeCompositeStyleDescription) {
            if (style instanceof GaugeCompositeStyle) {
                updateGaugeCompositeStyle((GaugeCompositeStyle) style, (GaugeCompositeStyleDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof NoteDescription) {
            if (style instanceof Note) {
                updateNote((Note) style, (NoteDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof BundledImageDescription) {
            if (style instanceof BundledImage) {
                updateBundledImage((BundledImage) style, (BundledImageDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof WorkspaceImageDescription) {
            if (style instanceof WorkspaceImage) {
                updateWorkspaceImage((WorkspaceImage) style, (WorkspaceImageDescription) description, previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof EllipseNodeDescription) {
            if (style instanceof Ellipse) {
                updateEllipse((Ellipse) style, (EllipseNodeDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        } else if (description instanceof LozengeNodeDescription) {
            if (style instanceof Lozenge) {
                updateLozenge((Lozenge) style, (LozengeNodeDescription) description, (Option<NodeStyle>) previousStyle);
            } else {
                brokenStyle = true;
            }
        }
        if (brokenStyle && style.eContainer() instanceof AbstractDNode) {
            EObject copy = EcoreUtil.copy(createNodeStyle(description));
            final NodeStyle newStyle = (NodeStyle) copy;
            affectStyle((AbstractDNode) style.eContainer(), newStyle);
        }

        if (previousStyle.get() instanceof BorderedStyle) {
            updateBorderedStyleFeatures(description, style, (Option<BorderedStyle>) previousStyle);
        } else {
            Option<BorderedStyle> noPreviousBorderedStyle = Options.newNone();
            updateBorderedStyleFeatures(description, style, noPreviousBorderedStyle);
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.NODE_STYLE__LABEL_POSITION.getName())) {
            if (previousStyle.get() instanceof NodeStyle) {
                style.setLabelPosition(((NodeStyle) previousStyle.get()).getLabelPosition());
            }
            style.getCustomFeatures().add(DiagramPackage.Literals.NODE_STYLE__LABEL_POSITION.getName());
        } else {
            if (!style.getCustomFeatures().contains(DiagramPackage.Literals.NODE_STYLE__LABEL_POSITION.getName())) {
                style.setLabelPosition(description.getLabelPosition());
            }
        }

        updateHideLabelCapabilityFeature(description, style, previousStyle);
    }

    /**
     * @param description
     * @param style
     * @param previousStyle
     */
    private void updateBorderedStyleFeatures(BorderedStyleDescription description, BorderedStyle style, Option<BorderedStyle> previousStyle) {
        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE.getName())) {
            style.setBorderSize(previousStyle.get().getBorderSize());
            style.getCustomFeatures().add(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE.getName());
        } else {
            if (style.eContainer() instanceof AbstractDNode && style.getBorderSizeComputationExpression() != null
                    && !style.getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE.getName())) {
                try {
                    style.setBorderSize(interpreter.evaluateInteger(((AbstractDNode) style.eContainer()).getTarget(), description.getBorderSizeComputationExpression()));
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(description, StylePackage.eINSTANCE.getBorderedStyleDescription_BorderSizeComputationExpression(), e);
                }
            }
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION.getName())) {
            style.setBorderSizeComputationExpression(previousStyle.get().getBorderSizeComputationExpression());
            style.getCustomFeatures().add(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION.getName());
        } else {
            if (!style.getBorderSizeComputationExpression().equals(description.getBorderSizeComputationExpression())
                    && !style.getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION.getName())) {
                style.setBorderSizeComputationExpression(description.getBorderSizeComputationExpression());
            }
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_LINE_STYLE.getName())) {
            style.setBorderLineStyle(previousStyle.get().getBorderLineStyle());
            style.getCustomFeatures().add(DiagramPackage.Literals.BORDERED_STYLE__BORDER_LINE_STYLE.getName());
        } else {
            if (!style.getBorderLineStyle().equals(description.getBorderLineStyle()) && !style.getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_LINE_STYLE.getName())) {
                style.setBorderLineStyle(description.getBorderLineStyle());
            }
        }
    }

    private void affectStyle(final AbstractDNode container, final NodeStyle newStyle) {
        final DiagramSwitch<?> sw = new DiagramSwitch<Object>() {

            @Override
            public Object caseDNode(final DNode object) {
                object.setOwnedStyle(newStyle);
                return super.caseDNode(object);
            }

            @Override
            public Object caseDNodeListElement(final DNodeListElement object) {
                object.setOwnedStyle(newStyle);
                return super.caseDNodeListElement(object);
            }

        };
        sw.doSwitch(container);
    }

    private FlatContainerStyle createFlatContainerStyle(final FlatContainerStyleDescription description) {
        final FlatContainerStyle style = DiagramFactory.eINSTANCE.createFlatContainerStyle();
        Option<ContainerStyle> noPreviousStyle = Options.newNone();
        updateFlatContainerStyle(style, description, noPreviousStyle);
        return style;
    }

    private void updateFlatContainerStyle(final FlatContainerStyle style, final FlatContainerStyleDescription description, Option<ContainerStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE.getName())
                && previousStyle.get() instanceof FlatContainerStyle) {
            style.setBackgroundStyle(((FlatContainerStyle) previousStyle.get()).getBackgroundStyle());
            style.getCustomFeatures().add(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE.getName());
        } else {
            if (style.getBackgroundStyle().getValue() != description.getBackgroundStyle().getValue()
                    && !style.getCustomFeatures().contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE.getName())) {
                style.setBackgroundStyle(description.getBackgroundStyle());
            }
        }

        updateLabelStyleFeatures(description, style, previousStyle);
        if (style.eContainer() instanceof DDiagramElementContainer) {
            setComputedSize((DDiagramElementContainer) style.eContainer(), description);
        }
    }

    private ShapeContainerStyle createShapeContainerStyle(final ShapeContainerStyleDescription description) {
        final ShapeContainerStyle style = DiagramFactory.eINSTANCE.createShapeContainerStyle();
        Option<ContainerStyle> noPreviousStyle = Options.newNone();
        updateShapeContainerStyle(style, description, noPreviousStyle);
        return style;
    }

    private void updateShapeContainerStyle(final ShapeContainerStyle style, final ShapeContainerStyleDescription description, Option<ContainerStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__SHAPE.getName())
                && previousStyle.get() instanceof ShapeContainerStyle) {
            style.setShape(((ShapeContainerStyle) previousStyle.get()).getShape());
            style.getCustomFeatures().add(DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__SHAPE.getName());
        } else {
            if (style.getShape().getValue() != description.getShape().getValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__SHAPE.getName())) {
                style.setShape(description.getShape());
            }
        }

        if (style.eContainer() instanceof DDiagramElementContainer) {
            setComputedSize((DDiagramElementContainer) style.eContainer(), description);
        }

    }

    private void setComputedSize(DDiagramElementContainer container, SizeComputationContainerStyleDescription style) {
        if (style != null) {
            Integer computedWidth = computeExpressions(container.getTarget(), style, StylePackage.eINSTANCE.getSizeComputationContainerStyleDescription_WidthComputationExpression());
            Integer computedHeight = computeExpressions(container.getTarget(), style, StylePackage.eINSTANCE.getSizeComputationContainerStyleDescription_HeightComputationExpression());
            safeSetComputedSize(container, computedWidth, computedHeight);
        }
    }

    private void safeSetComputedSize(DDiagramElementContainer container, Integer computedWidth, Integer computedHeight) {
        if (featureCanBeSet(computedWidth, container, DiagramPackage.eINSTANCE.getDDiagramElementContainer_Width()) && !computedWidth.equals(container.getWidth())) {
            container.setWidth(computedWidth);
        }
        if (featureCanBeSet(computedHeight, container, DiagramPackage.eINSTANCE.getDDiagramElementContainer_Height()) && !computedHeight.equals(container.getHeight())) {
            container.setHeight(computedHeight);
        }

    }

    /**
     * To avoid abusive dirty because of the new "width" and "height" attributes
     * on {@link DDiagramElementContainer}, we have to check whether the VSM
     * width and height computation expression are different of the default
     * value (-1) before to set them. By default, if the width or height
     * features are unset, we keep the same auto-size behavior than before.
     * 
     * @param value
     *            the value, already evaluated.
     * @param container
     *            the {@link DDiagramElementContainer} to set width and height.
     * @param feature
     *            the concerned feature (width or height).
     * @return true if the feature can be set otherwise false.
     */
    private boolean featureCanBeSet(Integer value, DDiagramElementContainer container, EStructuralFeature feature) {
        boolean returnValue = false;

        // if the feature has already been set we can set it without verify if
        // we have the VSM default value.
        if (container.eIsSet(feature)) {
            returnValue = true;
        } else if (value.intValue() > 0) {
            returnValue = true;

        }
        return returnValue;
    }

    private Integer computeExpressions(EObject target, SizeComputationContainerStyleDescription description, EStructuralFeature feature) {
        Integer size = null;
        Object expression = description.eGet(feature);
        if (expression instanceof String && !StringUtil.isEmpty((String) expression) && !((String) expression).equals("-1")) { //$NON-NLS-1$
            try {
                size = interpreter.evaluateInteger(target, (String) expression);
                if (size == null) {
                    size = AUTO_SIZE_CONTAINER;
                }

            } catch (EvaluationException e) {
                // do nothing
            }
        } else {
            size = AUTO_SIZE_CONTAINER;
        }
        return size;

    }

    private WorkspaceImage createWorkspaceImage(final WorkspaceImageDescription description) {
        final WorkspaceImage image = DiagramFactory.eINSTANCE.createWorkspaceImage();
        Option<ContainerStyle> noPreviousStyle = Options.newNone();
        updateWorkspaceImage(image, description, noPreviousStyle);
        return image;
    }

    private void updateWorkspaceImage(final WorkspaceImage image, final WorkspaceImageDescription description, Option<? extends Style> previousStyle) {
        if (image.getDescription() != description) {
            image.setDescription(description);
        }

        if (!previousStyle.some() || (previousStyle.some() && previousStyle.get() instanceof LabelStyle)) {
            updateLabelStyleFeatures(description, image, (Option<LabelStyle>) previousStyle);
        }

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName())
                && previousStyle.get() instanceof WorkspaceImage) {
            image.setWorkspacePath(((WorkspaceImage) previousStyle.get()).getWorkspacePath());
            image.getCustomFeatures().add(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
        } else {
            if (image.getWorkspacePath() == null || !image.getWorkspacePath().equals(description.getWorkspacePath())
                    && !image.getCustomFeatures().contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName())) {
                image.setWorkspacePath(description.getWorkspacePath());
            }
        }

        // handle image size
        if (image.eContainer() instanceof DNode && description.getSizeComputationExpression() != null) {
            final DNode node = (DNode) image.eContainer();
            Integer size = computeStyleSize(node.getTarget(), description);
            if (size.intValue() == -1) {
                // real image size
                node.setHeight(size);
                node.setWidth(size);
            } else {
                safeSetComputedSize(node, size);
            }
        }
    }

    private BundledImage createBundledImage(final BundledImageDescription description) {
        final BundledImage image = DiagramFactory.eINSTANCE.createBundledImage();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateBundledImage(image, description, noPreviousStyle);
        return image;
    }

    private void updateBundledImage(final BundledImage image, final BundledImageDescription description, Option<NodeStyle> previousStyle) {
        if (image.getDescription() != description) {
            image.setDescription(description);
        }

        updateLabelStyleFeatures(description, image, previousStyle);

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.BUNDLED_IMAGE__SHAPE.getName()) && previousStyle.get() instanceof BundledImage) {
            image.setShape(((BundledImage) previousStyle.get()).getShape());
            image.getCustomFeatures().add(DiagramPackage.Literals.BUNDLED_IMAGE__SHAPE.getName());
        } else {
            if (image.getShape().getValue() != description.getShape().getValue() && !image.getCustomFeatures().contains(DiagramPackage.Literals.BUNDLED_IMAGE__SHAPE.getName())) {
                image.setShape(description.getShape());
            }
        }
    }

    private Note createNote(final NoteDescription description) {
        final Note style = DiagramFactory.eINSTANCE.createNote();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateNote(style, description, noPreviousStyle);
        return style;
    }

    private void updateNote(final Note style, final NoteDescription description, Option<NodeStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);
    }

    private GaugeCompositeStyle createGaugeCompositeStyle(final GaugeCompositeStyleDescription description) {
        final GaugeCompositeStyle style = DiagramFactory.eINSTANCE.createGaugeCompositeStyle();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateGaugeCompositeStyle(style, description, noPreviousStyle);
        return style;
    }

    private void updateGaugeCompositeStyle(final GaugeCompositeStyle style, final GaugeCompositeStyleDescription description, Option<NodeStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);

        EList<GaugeSection> newGaugeSections = new BasicEList<GaugeSection>();

        for (final GaugeSectionDescription section : description.getSections()) {
            final GaugeSection styleSection = DiagramFactory.eINSTANCE.createGaugeSection();
            styleSection.setLabel(section.getLabel());

            // The context.
            EObject context = null;
            if (style.eContainer() instanceof DSemanticDecorator) {
                context = ((DSemanticDecorator) style.eContainer()).getTarget();
            }
            if (context != null && context.eResource() != null) {
                //
                // refresh all sections.
                final Integer min = getIntFromExpression(context, section, StylePackage.eINSTANCE.getGaugeSectionDescription_MinValueExpression());
                final Integer max = getIntFromExpression(context, section, StylePackage.eINSTANCE.getGaugeSectionDescription_MaxValueExpression());
                final Integer value = getIntFromExpression(context, section, StylePackage.eINSTANCE.getGaugeSectionDescription_ValueExpression());
                styleSection.setMin(min);
                styleSection.setMax(max);
                if (value == null) {
                    styleSection.setValue(min);
                } else {
                    styleSection.setValue(value);
                }
                styleSection.setBackgroundColor(colorUpdater.getRGBValuesFromColorDescription(context, section.getBackgroundColor()));
                styleSection.setForegroundColor(colorUpdater.getRGBValuesFromColorDescription(context, section.getForegroundColor()));
            }
            newGaugeSections.add(styleSection);
        }

        org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper equalityHelper = new org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper();
        // Change sections only if new list is different from original one
        if (!equalityHelper.equals((List) style.getSections(), (List) newGaugeSections) && !style.getCustomFeatures().contains(DiagramPackage.Literals.GAUGE_COMPOSITE_STYLE__SECTIONS.getName())) {
            style.getSections().clear();
            style.getSections().addAll(newGaugeSections);
        }

        if (style.getAlignment().getValue() != description.getAlignment().getValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.GAUGE_COMPOSITE_STYLE__ALIGNMENT.getName())) {
            style.setAlignment(description.getAlignment());
        }
    }

    private Integer getIntFromExpression(final EObject context, final EObject descriptionObject, final EStructuralFeature eFeature) {
        final String computationExpression = (String) descriptionObject.eGet(eFeature);
        try {
            return interpreter.evaluateInteger(context, computationExpression);
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(descriptionObject, eFeature, e);
        }
        return Integer.valueOf(0);
    }

    private Dot createDot(final DotDescription description) {
        final Dot style = DiagramFactory.eINSTANCE.createDot();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateDot(style, description, noPreviousStyle);
        return style;
    }

    private void updateDot(final Dot style, final DotDescription description, Option<NodeStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION.getName()) && previousStyle.get() instanceof Dot) {
            style.setStrokeSizeComputationExpression(((Dot) previousStyle.get()).getStrokeSizeComputationExpression());
            style.getCustomFeatures().add(DiagramPackage.Literals.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION.getName());
        } else {
            if (!style.getStrokeSizeComputationExpression().equals(description.getStrokeSizeComputationExpression())
                    && !style.getCustomFeatures().contains(DiagramPackage.Literals.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION.getName())) {
                style.setStrokeSizeComputationExpression(description.getStrokeSizeComputationExpression());
            }
        }
    }

    private Square createSquare(final SquareDescription description) {
        final Square style = DiagramFactory.eINSTANCE.createSquare();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateSquare(style, description, noPreviousStyle);
        return style;
    }

    private void updateSquare(final Square style, final SquareDescription description, Option<NodeStyle> previousStyle) {
        // If the style's color is null or one of either the style or
        // description color is not a scale value (recomputed later), reset it
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);
        if (style.getHeight().intValue() != description.getHeight().intValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.SQUARE__HEIGHT.getName())) {
            style.setHeight(description.getHeight());
        }
        if (style.getWidth().intValue() != description.getWidth().intValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.SQUARE__WIDTH.getName())) {
            style.setWidth(description.getWidth());
        }
        if (style.eContainer() instanceof DNode) {
            final DNode node = (DNode) style.eContainer();
            if (style.getWidth() != 0 && style.getHeight() != 0) {
                if (node.getWidth() == null || node.getWidth().intValue() != style.getWidth().intValue()) {
                    node.setWidth(style.getWidth());
                }
                if (node.getHeight() == null || node.getHeight().intValue() != style.getHeight().intValue()) {
                    node.setHeight(style.getHeight());
                }
            } else {
                setComputedSize(node, description);
            }
            if (style.getBorderSizeComputationExpression() != null && !style.getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE.getName())) {
                try {
                    style.setBorderSize(interpreter.evaluateInteger(node.getTarget(), description.getBorderSizeComputationExpression()));
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(description, StylePackage.eINSTANCE.getBorderedStyleDescription_BorderSizeComputationExpression(), e);
                }
            }
        }
    }

    private CustomStyle createCustomStyle(final CustomStyleDescription description) {
        final CustomStyle style = DiagramFactory.eINSTANCE.createCustomStyle();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateCustomStyle(style, description, noPreviousStyle);
        return style;
    }

    private void updateCustomStyle(final CustomStyle style, final CustomStyleDescription description, Option<NodeStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);

        if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.CUSTOM_STYLE__ID.getName()) && previousStyle.get() instanceof CustomStyle) {
            style.setId(((CustomStyle) previousStyle.get()).getId());
            style.getCustomFeatures().add(DiagramPackage.Literals.CUSTOM_STYLE__ID.getName());
        } else {
            if (style.getId() == null || !style.getId().equals(description.getId()) && !style.getCustomFeatures().contains(DiagramPackage.Literals.CUSTOM_STYLE__ID.getName())) {
                style.setId(description.getId());
            }
        }
    }

    private Lozenge createLozenge(final LozengeNodeDescription description) {
        final Lozenge style = DiagramFactory.eINSTANCE.createLozenge();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateLozenge(style, description, noPreviousStyle);
        return style;
    }

    private void updateLozenge(final Lozenge style, final LozengeNodeDescription description, Option<NodeStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);

        if (style.eContainer() instanceof DNode) {
            final DNode node = (DNode) style.eContainer();
            Integer width = Integer.valueOf(0);
            Integer height = Integer.valueOf(0);
            if (description.getWidthComputationExpression() != null && description.getHeightComputationExpression() != null) {

                try {
                    width = interpreter.evaluateInteger(node.getTarget(), description.getWidthComputationExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(description, StylePackage.eINSTANCE.getLozengeNodeDescription_WidthComputationExpression(), e);
                }
                try {
                    height = interpreter.evaluateInteger(node.getTarget(), description.getHeightComputationExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(description, StylePackage.eINSTANCE.getLozengeNodeDescription_HeightComputationExpression(), e);
                }
                if (height != null && width != null && width.intValue() != 0 && height.intValue() != 0) {
                    if (node.getWidth() == null || node.getWidth().intValue() != width.intValue()) {
                        node.setWidth(width);
                    }
                    if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.LOZENGE__WIDTH.getName()) && previousStyle.get() instanceof Lozenge) {
                        style.setWidth(((Lozenge) previousStyle.get()).getWidth());
                        style.getCustomFeatures().add(DiagramPackage.Literals.LOZENGE__WIDTH.getName());
                    } else if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.ELLIPSE__HORIZONTAL_DIAMETER.getName())
                            && previousStyle.get() instanceof Ellipse) {
                        style.setWidth(((Ellipse) previousStyle.get()).getHorizontalDiameter());
                        style.getCustomFeatures().add(DiagramPackage.Literals.LOZENGE__WIDTH.getName());
                    } else {
                        if (style.getWidth().intValue() != width.intValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.LOZENGE__WIDTH.getName())) {
                            style.setWidth(width);
                        }
                    }
                    if (node.getHeight() == null || node.getHeight().intValue() != height.intValue()) {
                        node.setHeight(height);
                    }

                    if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.LOZENGE__HEIGHT.getName()) && previousStyle.get() instanceof Lozenge) {
                        style.setHeight(((Lozenge) previousStyle.get()).getHeight());
                        style.getCustomFeatures().add(DiagramPackage.Literals.LOZENGE__HEIGHT.getName());
                    } else if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.ELLIPSE__VERTICAL_DIAMETER.getName())
                            && previousStyle.get() instanceof Ellipse) {
                        style.setHeight(((Ellipse) previousStyle.get()).getVerticalDiameter());
                        style.getCustomFeatures().add(DiagramPackage.Literals.LOZENGE__HEIGHT.getName());
                    } else {
                        if (style.getHeight().intValue() != height.intValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.LOZENGE__HEIGHT.getName())) {
                            style.setHeight(height);
                        }
                    }
                } else {
                    setComputedSize(node, description);
                }
            } else {
                setComputedSize(node, description);
            }
        }
    }

    private NodeStyle createEllipse(final EllipseNodeDescription description) {
        final Ellipse style = DiagramFactory.eINSTANCE.createEllipse();
        Option<NodeStyle> noPreviousStyle = Options.newNone();
        updateEllipse(style, description, noPreviousStyle);
        return style;
    }

    private void updateEllipse(final Ellipse style, final EllipseNodeDescription description, Option<NodeStyle> previousStyle) {
        if (style.getDescription() != description) {
            style.setDescription(description);
        }

        updateLabelStyleFeatures(description, style, previousStyle);

        if (style.eContainer() instanceof DNode) {
            final DNode node = (DNode) style.eContainer();
            Integer width = Integer.valueOf(0);
            Integer height = Integer.valueOf(0);
            if (description.getHorizontalDiameterComputationExpression() != null && description.getVerticalDiameterComputationExpression() != null) {
                try {
                    width = interpreter.evaluateInteger(node.getTarget(), description.getHorizontalDiameterComputationExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(description, StylePackage.eINSTANCE.getEllipseNodeDescription_HorizontalDiameterComputationExpression(), e);
                }
                try {
                    height = interpreter.evaluateInteger(node.getTarget(), description.getVerticalDiameterComputationExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(description, StylePackage.eINSTANCE.getEllipseNodeDescription_VerticalDiameterComputationExpression(), e);
                }
                if (height != null && width != null && width.intValue() != 0 && height.intValue() != 0) {
                    if (node.getWidth() == null || node.getWidth().intValue() != width.intValue()) {
                        node.setWidth(width);
                    }
                    if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.LOZENGE__WIDTH.getName()) && previousStyle.get() instanceof Lozenge) {
                        style.setHorizontalDiameter(((Lozenge) previousStyle.get()).getWidth());
                        style.getCustomFeatures().add(DiagramPackage.Literals.ELLIPSE__HORIZONTAL_DIAMETER.getName());
                    } else if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.ELLIPSE__HORIZONTAL_DIAMETER.getName())
                            && previousStyle.get() instanceof Ellipse) {
                        style.setHorizontalDiameter(((Ellipse) previousStyle.get()).getHorizontalDiameter());
                        style.getCustomFeatures().add(DiagramPackage.Literals.ELLIPSE__HORIZONTAL_DIAMETER.getName());
                    } else {
                        if (style.getHorizontalDiameter().intValue() != width.intValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.ELLIPSE__HORIZONTAL_DIAMETER.getName())) {
                            style.setHorizontalDiameter(width);
                        }
                    }
                    if (node.getHeight() == null || node.getHeight().intValue() != height.intValue()) {
                        node.setHeight(height);
                    }
                    if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.LOZENGE__HEIGHT.getName()) && previousStyle.get() instanceof Lozenge) {
                        style.setVerticalDiameter(((Lozenge) previousStyle.get()).getHeight());
                        style.getCustomFeatures().add(DiagramPackage.Literals.ELLIPSE__VERTICAL_DIAMETER.getName());
                    } else if (previousStyle.some() && previousStyle.get().getCustomFeatures().contains(DiagramPackage.Literals.ELLIPSE__VERTICAL_DIAMETER.getName())
                            && previousStyle.get() instanceof Ellipse) {
                        style.setVerticalDiameter(((Ellipse) previousStyle.get()).getVerticalDiameter());
                        style.getCustomFeatures().add(DiagramPackage.Literals.ELLIPSE__VERTICAL_DIAMETER.getName());
                    } else {
                        if (style.getVerticalDiameter().intValue() != height.intValue() && !style.getCustomFeatures().contains(DiagramPackage.Literals.ELLIPSE__VERTICAL_DIAMETER.getName())) {
                            style.setVerticalDiameter(height);
                        }
                    }
                } else {
                    setComputedSize(node, description);
                }
            } else {
                setComputedSize(node, description);
            }
        }
    }

    /**
     * Refreshes all colors of the given style.
     * 
     * @param style
     *            the style.
     * @param previousStyle
     *            the previous style (if existing) to keep compatible
     *            customization.
     */
    protected void refreshColors(final Style style, Option<? extends Style> previousStyle) {
        EObject context = style.eContainer();
        /*
         * If there is no description we won't have a lot of chance to update
         * anything..
         */
        if (style.getDescription() != null) {
            if (context != null) {
                if (context instanceof DSemanticDecorator) {
                    context = ((DSemanticDecorator) context).getTarget();
                }
                colorUpdater.updateColors(context, style, style.getDescription(), previousStyle);
                refreshEdgeStyleWithContext(style, context, previousStyle);
            } else {
                colorUpdater.updateColors(style, style.getDescription(), previousStyle);
                refreshEdgeStyle(style, previousStyle);
            }
        }

    }

    /**
     * @param style
     */
    private void refreshEdgeStyle(final Style style, Option<? extends Style> previousStyle) {
        if (style instanceof EdgeStyle && (!previousStyle.some() || previousStyle.get() instanceof EdgeStyle)) {
            CenterLabelStyle centerLabelStyle = ((EdgeStyle) style).getCenterLabelStyle();
            if (centerLabelStyle != null) {
                Option<CenterLabelStyle> previousCenterLabelStyle = Options.newNone();
                if (previousStyle.some()) {
                    previousCenterLabelStyle = Options.newSome(((EdgeStyle) previousStyle.get()).getCenterLabelStyle());
                }
                colorUpdater.updateColors(centerLabelStyle, centerLabelStyle.getDescription(), previousCenterLabelStyle);
            }
            BeginLabelStyle beginLabelStyle = ((EdgeStyle) style).getBeginLabelStyle();
            if (beginLabelStyle != null) {
                Option<BeginLabelStyle> previousBeginLabelStyle = Options.newNone();
                if (previousStyle.some()) {
                    previousBeginLabelStyle = Options.newSome(((EdgeStyle) previousStyle.get()).getBeginLabelStyle());
                }
                colorUpdater.updateColors(beginLabelStyle, beginLabelStyle.getDescription(), previousBeginLabelStyle);
            }
            EndLabelStyle endLabelStyle = ((EdgeStyle) style).getEndLabelStyle();
            if (endLabelStyle != null) {
                Option<EndLabelStyle> previousEndLabelStyle = Options.newNone();
                if (previousStyle.some()) {
                    previousEndLabelStyle = Options.newSome(((EdgeStyle) previousStyle.get()).getEndLabelStyle());
                }
                colorUpdater.updateColors(endLabelStyle, endLabelStyle.getDescription(), previousEndLabelStyle);
            }
        }
    }

    /**
     * @param style
     * @param context
     */
    private void refreshEdgeStyleWithContext(final Style style, EObject context, Option<? extends Style> previousStyle) {
        if (style instanceof EdgeStyle) {
            CenterLabelStyle centerLabelStyle = ((EdgeStyle) style).getCenterLabelStyle();
            if (centerLabelStyle != null) {
                Option<CenterLabelStyle> previousCenterLabelStyle = Options.newNone();
                if (previousStyle.some()) {
                    previousCenterLabelStyle = Options.newSome(((EdgeStyle) previousStyle.get()).getCenterLabelStyle());
                }
                colorUpdater.updateColors(context, centerLabelStyle, centerLabelStyle.getDescription(), previousCenterLabelStyle);
            }
            BeginLabelStyle beginLabelStyle = ((EdgeStyle) style).getBeginLabelStyle();
            if (beginLabelStyle != null) {
                Option<BeginLabelStyle> previousBeginLabelStyle = Options.newNone();
                if (previousStyle.some()) {
                    previousBeginLabelStyle = Options.newSome(((EdgeStyle) previousStyle.get()).getBeginLabelStyle());
                }
                colorUpdater.updateColors(context, beginLabelStyle, beginLabelStyle.getDescription(), previousBeginLabelStyle);
            }
            EndLabelStyle endLabelStyle = ((EdgeStyle) style).getEndLabelStyle();
            if (endLabelStyle != null) {
                Option<EndLabelStyle> previousEndLabelStyle = Options.newNone();
                if (previousStyle.some()) {
                    previousEndLabelStyle = Options.newSome(((EdgeStyle) previousStyle.get()).getEndLabelStyle());
                }
                colorUpdater.updateColors(context, endLabelStyle, endLabelStyle.getDescription(), previousEndLabelStyle);
            }
        }
    }

    /**
     * Set the style if needed and refresh it.
     * 
     * @param diagramElement
     *            The diagram element to which apply the style
     * @param currentStyle
     *            The current style of the diagram element
     * @param bestStyle
     *            The new style to apply
     */
    public void setAndRefreshStyle(final DDiagramElement diagramElement, final Style currentStyle, final Style bestStyle) {
        if (currentStyle == null) {
            /*
             * The element has no style. Let's assign the best style.
             */
            new SetStyleSwitch(bestStyle).doSwitch(diagramElement);
            // Refresh the style
            new RefreshStyleSwitch().doSwitch(diagramElement);
        } else if (bestStyle == currentStyle) {
            // Refresh the style
            new RefreshStyleSwitch().doSwitch(diagramElement);
        } else if (bestStyle != null && (currentStyle.getDescription() == null || isDifferentDescription(bestStyle, currentStyle))) {
            /* Let's affect the new style. */
            new SetStyleSwitch(bestStyle).doSwitch(diagramElement);
            // Refresh the style
            new RefreshStyleSwitch(Options.newSome(currentStyle)).doSwitch(diagramElement);
        }
    }

    /**
     * 
     * @param bestStyle
     *            the new style
     * @param currentStyle
     *            the current style
     * @return if the description is the same type as the style
     */
    private boolean isDifferentDescription(Style bestStyle, Style currentStyle) {
        return !EqualityHelper.areEquals(bestStyle.getDescription(), currentStyle.getDescription()) || !bestStyle.getClass().equals(currentStyle.getClass())
                && currentStyle.getCustomFeatures().isEmpty();
    }

    /**
     * Switch to set the style of a diagram element.
     * 
     * @author ymortier
     */
    private final class SetStyleSwitch extends DiagramSwitch<Object> {

        /**
         * The style to affect.
         */
        private final Style style;

        /**
         * Default constructor.
         * 
         * @param style
         *            the style to affect, should be <code>null</code>.
         */
        private SetStyleSwitch(final Style style) {
            this.style = style;
        }

        @Override
        public Object caseDEdge(final DEdge object) {
            object.setOwnedStyle((EdgeStyle) this.style);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNode(org.eclipse.sirius.diagram.DNode)
         */
        @Override
        public Object caseDNode(final DNode object) {
            object.setOwnedStyle((NodeStyle) this.style);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeListElement(org.eclipse.sirius.diagram.DNodeListElement)
         */
        @Override
        public Object caseDNodeListElement(final DNodeListElement object) {
            object.setOwnedStyle((NodeStyle) this.style);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeContainer(org.eclipse.sirius.diagram.DNodeContainer)
         */
        @Override
        public Object caseDNodeContainer(final DNodeContainer object) {
            object.setOwnedStyle((ContainerStyle) this.style);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeList(org.eclipse.sirius.diagram.DNodeList)
         */
        @Override
        public Object caseDNodeList(final DNodeList object) {
            object.setOwnedStyle((ContainerStyle) this.style);
            return null;
        }

    }

    /**
     * Switch to refresh the style of a diagram element.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private final class RefreshStyleSwitch extends DiagramSwitch<Object> {
        Option<? extends Style> previousStyle = Options.newNone();

        /**
         * Default constructor.
         * 
         * @param previousStyle
         * 
         */
        private RefreshStyleSwitch() {
        }

        /**
         * Default constructor.
         * 
         * @param previousStyle
         *            the previous style (if existing) to keep compatible
         *            customization.
         * 
         */
        private RefreshStyleSwitch(Option<? extends Style> previousStyle) {
            this.previousStyle = previousStyle;
        }

        @Override
        public Object caseDEdge(final DEdge object) {
            refreshStyle(object.getOwnedStyle(), previousStyle);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNode(org.eclipse.sirius.viewpoint.DNode)
         */
        @Override
        public Object caseDNode(final DNode object) {
            refreshStyle(object.getOwnedStyle(), previousStyle);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeListElement(org.eclipse.sirius.viewpoint.DNodeListElement)
         */
        @Override
        public Object caseDNodeListElement(final DNodeListElement object) {
            refreshStyle(object.getOwnedStyle(), previousStyle);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeContainer(org.eclipse.sirius.viewpoint.DNodeContainer)
         */
        @Override
        public Object caseDNodeContainer(final DNodeContainer object) {
            refreshStyle(object.getOwnedStyle(), previousStyle);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDNodeList(org.eclipse.sirius.viewpoint.DNodeList)
         */
        @Override
        public Object caseDNodeList(final DNodeList object) {
            refreshStyle(object.getOwnedStyle(), previousStyle);
            return null;
        }

    }

    /**
     * Set width and height from "SizeComputationExpression".
     * 
     * @param node
     *            Node
     * @param style
     *            Node style. May be <code>null</code>
     */
    public void setComputedSize(DNode node, NodeStyleDescription style) {
        if (style != null && !StringUtil.isEmpty(style.getSizeComputationExpression())) {
            Integer computedSize = computeStyleSize(node.getTarget(), style);
            safeSetComputedSize(node, computedSize);
        }
    }

    private void safeSetComputedSize(DNode node, Integer computedSize) {
        if (computedSize.intValue() >= 0) {
            if (!computedSize.equals(node.getWidth())) {
                node.setWidth(computedSize);
            }
            if (!computedSize.equals(node.getHeight())) {
                node.setHeight(computedSize);
            }
        }
    }

    /**
     * Compute node style description size.
     * 
     * @param target
     *            Target object
     * @param nodeStyle
     *            Node style
     * @return Size. Cannot be <code>null</code>.
     */
    private Integer computeStyleSize(final EObject target, final NodeStyleDescription nodeStyle) {
        Integer computedSize = null;
        try {
            computedSize = interpreter.evaluateInteger(target, nodeStyle.getSizeComputationExpression());
        } catch (final EvaluationException e) {
            RuntimeLoggerManager.INSTANCE.error(nodeStyle, StylePackage.eINSTANCE.getNodeStyleDescription_SizeComputationExpression(), e);
        }
        if (computedSize == null) {
            try {
                computedSize = Integer.parseInt(StylePackage.eINSTANCE.getNodeStyleDescription_SizeComputationExpression().getDefaultValueLiteral());
            } catch (NumberFormatException e) {
                // Nothing, default magic number will be used.
                RuntimeLoggerManager.INSTANCE.error(nodeStyle, StylePackage.eINSTANCE.getNodeStyleDescription_SizeComputationExpression(), e);
            }
        }
        if (computedSize == null) {
            computedSize = DEFAULT_SIZE;
        }
        return computedSize;
    }

}
