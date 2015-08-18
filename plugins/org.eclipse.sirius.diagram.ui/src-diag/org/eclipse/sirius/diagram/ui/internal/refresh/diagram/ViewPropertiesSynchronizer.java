/*******************************************************************************
 * Copyright (c) 2012-2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.color.RGBValuesProvider;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Lists;

/**
 * Update the GMF {@link View} properties according to Sirius properties and
 * vice versa.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class ViewPropertiesSynchronizer {

    /**
     * Map associating gmf style EAttribute to viewpoint style feature.
     */
    public static final Map<EStructuralFeature, EStructuralFeature> GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING = new HashMap<EStructuralFeature, EStructuralFeature>();

    static {
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__ITALIC, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__BOLD, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__UNDERLINE, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__STRIKE_THROUGH, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__FONT_COLOR, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR);
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
    }

    private static final String DEFAULT_FONT_STYLE = "Arial"; //$NON-NLS-1$

    /**
     * Update the specified GMF {@link View} properties according to Sirius
     * properties.
     * 
     * @param view
     *            the {@link View} for which to update properties.
     */
    public void synchronizeViewProperties(View view) {
        EObject element = view.getElement();
        if (element instanceof DDiagramElement) {
            // Visibility initialization, in case of invisible creation
            DDiagramElement dDiagramElement = (DDiagramElement) element;
            if (new ViewQuery(view).isForNameEditPart()) {
                view.setVisible(!new DDiagramElementQuery(dDiagramElement).isLabelHidden());
            } else {
                view.setVisible(dDiagramElement.isVisible());
            }

            // Adapt GMF font according to Viewpoint font.
            if (dDiagramElement.getStyle() instanceof BasicLabelStyle) {
                BasicLabelStyle basicLabelStyle = (BasicLabelStyle) dDiagramElement.getStyle();
                updateGMFFontStyle(view, basicLabelStyle);
            } else if (dDiagramElement.getStyle() instanceof EdgeStyle) {
                EdgeStyle edgeStyle = (EdgeStyle) dDiagramElement.getStyle();
                BeginLabelStyle beginLabelStyle = edgeStyle.getBeginLabelStyle();
                if (beginLabelStyle != null) {
                    updateGMFFontStyle(view, beginLabelStyle);
                }
                CenterLabelStyle centerLabelStyle = edgeStyle.getCenterLabelStyle();
                if (centerLabelStyle != null) {
                    updateGMFFontStyle(view, centerLabelStyle);
                }
                EndLabelStyle endLabelStyle = edgeStyle.getEndLabelStyle();
                if (endLabelStyle != null) {
                    updateGMFFontStyle(view, endLabelStyle);
                }
            }
        }
    }

    private void updateGMFFontStyle(View view, BasicLabelStyle basicLabelStyle) {
        FontStyle fontStyle = (FontStyle) createOrFindStyle(view, NotationPackage.eINSTANCE.getFontStyle());
        List<FontFormat> labelFormat = basicLabelStyle.getLabelFormat();
        if (fontStyle.isBold() != labelFormat.contains(FontFormat.BOLD_LITERAL)) {
            fontStyle.setBold(labelFormat.contains(FontFormat.BOLD_LITERAL));
        }
        if (fontStyle.isItalic() != labelFormat.contains(FontFormat.ITALIC_LITERAL)) {
            fontStyle.setItalic(labelFormat.contains(FontFormat.ITALIC_LITERAL));
        }
        if (fontStyle.isUnderline() != labelFormat.contains(FontFormat.UNDERLINE_LITERAL)) {
            fontStyle.setUnderline(labelFormat.contains(FontFormat.UNDERLINE_LITERAL));
        }
        if (fontStyle.isStrikeThrough() != labelFormat.contains(FontFormat.STRIKE_THROUGH_LITERAL)) {
            fontStyle.setStrikeThrough(labelFormat.contains(FontFormat.STRIKE_THROUGH_LITERAL));
        }

        fontStyle.setFontHeight(Math.max(basicLabelStyle.getLabelSize(), 1));
        //
        // Default font (Change the code here to insert new font in the
        // case
        // of Designer allows to change the default font).
        if (fontStyle.getFontName() == null) {
            fontStyle.setFontName(DEFAULT_FONT_STYLE);
        }
        RGBValues labelColor = basicLabelStyle.getLabelColor();
        if (labelColor != null) {
            fontStyle.setFontColor(FigureUtilities.RGBToInteger(new RGB(labelColor.getRed(), labelColor.getGreen(), labelColor.getBlue())));
        }
    }

    /**
     * Update the {@link DDiagramElement}'s style properties (labelstyle),
     * element of the specified {@link View} according to specified GMF
     * {@link View} properties.
     * 
     * @param view
     *            the specified GMF {@link View}
     */
    public void synchronizeDDiagramElementStyleProperties(View view) {
        EObject element = view.getElement();
        if (element instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) element;
            BasicLabelStyle labelStyle = null;
            if (dDiagramElement.getStyle() instanceof LabelStyle) {
                labelStyle = (LabelStyle) dDiagramElement.getStyle();
            } else if (dDiagramElement.getStyle() instanceof EdgeStyle) {
                EdgeStyle edgeStyle = (EdgeStyle) dDiagramElement.getStyle();
                labelStyle = edgeStyle.getCenterLabelStyle();
                BasicLabelStyle beginLabelStyle = edgeStyle.getBeginLabelStyle();
                BasicLabelStyle endLabelStyle = edgeStyle.getEndLabelStyle();

                updateLabelStyle(view, beginLabelStyle);
                updateLabelStyle(view, endLabelStyle);
            }
            updateLabelStyle(view, labelStyle);
        }
    }

    /**
     * Update the {@link DDiagramElement}'s style properties (color), element of
     * the specified {@link View} according to specified GMF {@link View}
     * properties and add the modify property to the custom features list of
     * this style.
     * 
     * @param view
     *            the specified GMF {@link View}
     * @param newColor
     *            the new {@link FixedColor}
     * @param propertyId
     *            propertyId of the view
     * @param interpreter
     *            the {@link IInterpreter} to use to evaluate expression
     */
    public void synchronizeDDiagramElementStyleColorProperties(View view, FixedColor newColor, String propertyId, IInterpreter interpreter) {
        // Add corresponding features to the custom features list of the style
        if (view.getElement() instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) view.getElement();
            org.eclipse.sirius.viewpoint.Style style = dDiagramElement.getStyle();
            List<EStructuralFeature> features = getFeatures(propertyId, style);
            if (Properties.ID_FONTCOLOR.equals(propertyId) && style instanceof EdgeStyle) {
                EdgeStyle edgeStyle = (EdgeStyle) style;
                if (edgeStyle.getBeginLabelStyle() != null) {
                    for (EStructuralFeature feature : features) {
                        if (!edgeStyle.getBeginLabelStyle().getCustomFeatures().contains(feature.getName())) {
                            edgeStyle.getBeginLabelStyle().getCustomFeatures().add(feature.getName());
                        }
                    }
                }
                if (edgeStyle.getCenterLabelStyle() != null) {
                    for (EStructuralFeature feature : features) {
                        if (!edgeStyle.getCenterLabelStyle().getCustomFeatures().contains(feature.getName())) {
                            edgeStyle.getCenterLabelStyle().getCustomFeatures().add(feature.getName());
                        }
                    }
                }
                if (edgeStyle.getEndLabelStyle() != null) {
                    for (EStructuralFeature feature : features) {
                        if (!edgeStyle.getEndLabelStyle().getCustomFeatures().contains(feature.getName())) {
                            edgeStyle.getEndLabelStyle().getCustomFeatures().add(feature.getName());
                        }
                    }
                }
            } else {
                for (EStructuralFeature feature : features) {
                    if (!style.getCustomFeatures().contains(feature.getName())) {
                        style.getCustomFeatures().add(feature.getName());
                    }
                }
            }
        }

        EObject element = view.getElement();
        if (element instanceof DNode) {
            DNode dNode = (DNode) element;
            NodeStyle nodeStyle = dNode.getOwnedStyle();
            if (Properties.ID_FILLCOLOR.equals(propertyId)) {
                setColor(nodeStyle, newColor);
            } else if (Properties.ID_LINECOLOR.equals(propertyId)) {
                setBorderColor(nodeStyle, newColor);
            } else if (Properties.ID_FONTCOLOR.equals(propertyId)) {
                setFontColor(nodeStyle, newColor);
            }
        } else if (element instanceof DNodeListElement) {
            DNodeListElement dNodeListElement = (DNodeListElement) element;
            NodeStyle nodeStyle = dNodeListElement.getOwnedStyle();
            if (Properties.ID_FONTCOLOR.equals(propertyId)) {
                setFontColor(nodeStyle, newColor);
            }
        } else if (element instanceof DDiagramElementContainer) {
            DDiagramElementContainer diagramElementContainer = (DDiagramElementContainer) element;
            ContainerStyle containerStyle = diagramElementContainer.getOwnedStyle();
            if (Properties.ID_FILLCOLOR.equals(propertyId)) {
                setColor(containerStyle, newColor);
            } else if (Properties.ID_LINECOLOR.equals(propertyId)) {
                setBorderColor(containerStyle, newColor);
            } else if (Properties.ID_FONTCOLOR.equals(propertyId)) {
                setFontColor(containerStyle, newColor);
            }
        } else if (element instanceof DEdge) {
            DEdge dEdge = (DEdge) element;
            EdgeStyle edgeStyle = dEdge.getOwnedStyle();
            if (Properties.ID_LINECOLOR.equals(propertyId)) {
                edgeStyle.setStrokeColor(new RGBValuesProvider().getRGBValues(newColor));
            } else if (Properties.ID_FONTCOLOR.equals(propertyId)) {
                if (edgeStyle.getBeginLabelStyle() != null) {
                    edgeStyle.getBeginLabelStyle().setLabelColor(new RGBValuesProvider().getRGBValues(newColor));
                }
                if (edgeStyle.getCenterLabelStyle() != null) {
                    edgeStyle.getCenterLabelStyle().setLabelColor(new RGBValuesProvider().getRGBValues(newColor));
                }
                if (edgeStyle.getEndLabelStyle() != null) {
                    edgeStyle.getEndLabelStyle().setLabelColor(new RGBValuesProvider().getRGBValues(newColor));
                }
            }
        }
    }

    private List<EStructuralFeature> getFeatures(String gmfPropertyID, org.eclipse.sirius.viewpoint.Style style) {
        List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
        if (Properties.ID_FILLCOLOR.equals(gmfPropertyID)) {
            if (style instanceof Square) {
                features.add(DiagramPackage.Literals.SQUARE__COLOR);
            } else if (style instanceof Lozenge) {
                features.add(DiagramPackage.Literals.LOZENGE__COLOR);
            } else if (style instanceof Ellipse) {
                features.add(DiagramPackage.Literals.ELLIPSE__COLOR);
            } else if (style instanceof Dot) {
                features.add(DiagramPackage.Literals.DOT__BACKGROUND_COLOR);
            } else if (style instanceof Note) {
                features.add(DiagramPackage.Literals.NOTE__COLOR);
            } else if (style instanceof BundledImage) {
                features.add(DiagramPackage.Literals.BUNDLED_IMAGE__COLOR);
            } else if (style instanceof FlatContainerStyle) {
                features.add(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
                features.add(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR);
            } else if (style instanceof ShapeContainerStyle) {
                features.add(DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR);
            }
        } else if (Properties.ID_LINECOLOR.equals(gmfPropertyID)) {
            if (style instanceof EdgeStyle) {
                features.add(DiagramPackage.Literals.EDGE_STYLE__STROKE_COLOR);
            } else {
                features.add(DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR);
            }
        } else if (Properties.ID_FONTCOLOR.equals(gmfPropertyID)) {
            features.add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR);
        }
        return features;
    }

    private void updateLabelStyle(View notationView, BasicLabelStyle labelStyle) {

        if (labelStyle != null) {
            List<FontFormat> newFontFormats = Lists.newArrayList();
            final FontStyle fontStyle = (FontStyle) createOrFindStyle(notationView, NotationPackage.eINSTANCE.getFontStyle());

            if (fontStyle.isBold()) {
                newFontFormats.add(FontFormat.BOLD_LITERAL);
            }
            if (fontStyle.isItalic()) {
                newFontFormats.add(FontFormat.ITALIC_LITERAL);
            }
            if (fontStyle.isUnderline()) {
                newFontFormats.add(FontFormat.UNDERLINE_LITERAL);
            }
            if (fontStyle.isStrikeThrough()) {
                newFontFormats.add(FontFormat.STRIKE_THROUGH_LITERAL);
            }

            FontFormatHelper.setFontFormat(labelStyle.getLabelFormat(), newFontFormats);
            labelStyle.setLabelSize(fontStyle.getFontHeight());
        }
    }

    /**
     * Create a new GMF style of the specified styleClass.
     * 
     * @param view
     *            the view for which create a new style
     * @param styleClass
     *            the type of the style to create
     * @return the created style added to the specified view
     */
    @SuppressWarnings("unchecked")
    private Style createOrFindStyle(final View view, final EClass styleClass) {
        Style style = view.getStyle(styleClass);
        if (style == null) {
            style = (Style) styleClass.getEPackage().getEFactoryInstance().create(styleClass);
            view.getStyles().add(style);
        }
        return style;
    }

    /**
     * set a new color to a style.
     * 
     * @param style
     *            style to change of color.
     * @param color
     *            the new color.
     */
    public void setColor(final NodeStyle style, final SystemColors color) {
        if (style instanceof Note) {
            ((Note) style).setColor(VisualBindingManager.getDefault().getRGBValuesFor(color));
        }
    }

    /**
     * set a new color to the border of a a style.
     * 
     * @param style
     *            style to change the border color.
     * @param color
     *            the new color.
     */
    public void setBorderColor(final BorderedStyle style, final FixedColor color) {
        style.setBorderColor(new RGBValuesProvider().getRGBValues(color));
    }

    /**
     * set a new color to the border of a a style.
     * 
     * @param style
     *            style to change the border color.
     * @param color
     *            the new color.
     */
    public void setFontColor(final LabelStyle style, final FixedColor color) {
        style.setLabelColor(new RGBValuesProvider().getRGBValues(color));
    }

    /**
     * set a new color to a style.
     * 
     * @param style
     *            style to change of color.
     * @param color
     *            the new color.
     */
    public void setColor(final ContainerStyle style, final SystemColors color) {
        if (style instanceof FlatContainerStyle) {
            ((FlatContainerStyle) style).setBackgroundColor(VisualBindingManager.getDefault().getRGBValuesFor(color));
            ((FlatContainerStyle) style).setForegroundColor(VisualBindingManager.getDefault().getRGBValuesFor(color));
        } else if (style instanceof ShapeContainerStyle) {
            ((ShapeContainerStyle) style).setBackgroundColor(VisualBindingManager.getDefault().getRGBValuesFor(color));
        }
    }

    /**
     * set a new color to a style.
     * 
     * @param style
     *            style to change of color.
     * @param color
     *            the new color.
     */
    public void setColor(final ContainerStyle style, final FixedColor color) {
        RGBValues rgb = new RGBValuesProvider().getRGBValues(color);
        if (style instanceof FlatContainerStyle) {
            ((FlatContainerStyle) style).setBackgroundColor(rgb);
            ((FlatContainerStyle) style).setForegroundColor(rgb);
        } else if (style instanceof ShapeContainerStyle) {
            ((ShapeContainerStyle) style).setBackgroundColor(rgb);
        }
    }

    /**
     * set a new color to a style.
     * 
     * @param style
     *            style to change of color.
     * @param color
     *            the new color.
     */
    public void setColor(final NodeStyle style, final FixedColor color) {
        RGBValues rgb = new RGBValuesProvider().getRGBValues(color);
        if (style instanceof Square) {
            ((Square) style).setColor(rgb);
        } else if (style instanceof Lozenge) {
            ((Lozenge) style).setColor(rgb);
        } else if (style instanceof Ellipse) {
            ((Ellipse) style).setColor(rgb);
        } else if (style instanceof Dot) {
            ((Dot) style).setBackgroundColor(rgb);
        } else if (style instanceof Note) {
            ((Note) style).setColor(rgb);
        } else if (style instanceof BundledImage) {
            ((BundledImage) style).setColor(rgb);
        }
    }

}
