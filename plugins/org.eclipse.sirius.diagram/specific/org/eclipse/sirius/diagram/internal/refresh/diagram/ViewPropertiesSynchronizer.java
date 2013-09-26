/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.refresh.diagram;

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
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.business.internal.metamodel.helper.GetDefaultStyle;
import org.eclipse.sirius.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.business.api.query.ViewQuery;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.BorderedStyle;
import org.eclipse.sirius.viewpoint.BundledImage;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.Dot;
import org.eclipse.sirius.viewpoint.EdgeStyle;
import org.eclipse.sirius.viewpoint.Ellipse;
import org.eclipse.sirius.viewpoint.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Lozenge;
import org.eclipse.sirius.viewpoint.NodeStyle;
import org.eclipse.sirius.viewpoint.Note;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ShapeContainerStyle;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.Square;
import org.eclipse.sirius.viewpoint.WorkspaceImage;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.swt.graphics.RGB;

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
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__FONT_COLOR, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR);
        GMF_TO_DDIAGRAMELEMENT_STYLE_FEATURES_MAPPING.put(NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
    }

    private static final String DEFAULT_FONT_STYLE = "Arial";

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
            if (dDiagramElement.getStyle() instanceof LabelStyle) {
                LabelStyle labelStyle = (LabelStyle) dDiagramElement.getStyle();
                FontStyle fontStyle = (FontStyle) createOrFindStyle(view, NotationPackage.eINSTANCE.getFontStyle());
                switch (labelStyle.getLabelFormat().getValue()) {
                case FontFormat.BOLD:
                    fontStyle.setBold(true);
                    break;
                case FontFormat.ITALIC:
                    fontStyle.setItalic(true);
                    break;
                case FontFormat.BOLD_ITALIC:
                    fontStyle.setBold(true);
                    fontStyle.setItalic(true);
                    break;
                case FontFormat.NORMAL:
                    fontStyle.setBold(false);
                    fontStyle.setItalic(false);
                    break;
                default:
                    break;
                }

                fontStyle.setFontHeight(labelStyle.getLabelSize());
                //
                // Default font (Change the code here to insert new font in the
                // case
                // of Designer allows to change the default font).
                if (fontStyle.getFontName() == null) {
                    fontStyle.setFontName(DEFAULT_FONT_STYLE);
                }
                RGBValues labelColor = labelStyle.getLabelColor();
                if (labelColor != null) {
                    fontStyle.setFontColor(FigureUtilities.RGBToInteger(new RGB(labelColor.getRed(), labelColor.getGreen(), labelColor.getBlue())));
                }
            }
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
            if (nodeStyle instanceof WorkspaceImage && !nodeStyle.getCustomFeatures().isEmpty()) {
                StyleDescription styleDescription = new GetDefaultStyle().doSwitch(dNode.getActualMapping());
                NodeStyle originalStyle = (NodeStyle) new StyleHelper(interpreter).createStyle(styleDescription);
                dNode.setOwnedStyle(originalStyle);
                nodeStyle = dNode.getOwnedStyle();
            }
            if (Properties.ID_FILLCOLOR.equals(propertyId)) {
                setColor(nodeStyle, newColor);
            } else if (Properties.ID_LINECOLOR.equals(propertyId)) {
                setBorderColor(nodeStyle, newColor);
            } else if (Properties.ID_FONTCOLOR.equals(propertyId)) {
                setFontColor(nodeStyle, newColor);
            }
        } else if (element instanceof DDiagramElementContainer) {
            DDiagramElementContainer diagramElementContainer = (DDiagramElementContainer) element;
            ContainerStyle containerStyle = diagramElementContainer.getOwnedStyle();
            if (containerStyle instanceof WorkspaceImage && !containerStyle.getCustomFeatures().isEmpty()) {
                ContainerStyle originalStyle = (ContainerStyle) new StyleHelper(interpreter).createStyle(diagramElementContainer.getActualMapping().getStyle());
                diagramElementContainer.setOwnedStyle(originalStyle);
                containerStyle = diagramElementContainer.getOwnedStyle();
            }
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
                features.add(ViewpointPackage.Literals.SQUARE__COLOR);
            } else if (style instanceof Lozenge) {
                features.add(ViewpointPackage.Literals.LOZENGE__COLOR);
            } else if (style instanceof Ellipse) {
                features.add(ViewpointPackage.Literals.ELLIPSE__COLOR);
            } else if (style instanceof Dot) {
                features.add(ViewpointPackage.Literals.DOT__BACKGROUND_COLOR);
            } else if (style instanceof Note) {
                features.add(ViewpointPackage.Literals.NOTE__COLOR);
            } else if (style instanceof BundledImage) {
                features.add(ViewpointPackage.Literals.BUNDLED_IMAGE__COLOR);
            } else if (style instanceof FlatContainerStyle) {
                features.add(ViewpointPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
                features.add(ViewpointPackage.Literals.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR);
            } else if (style instanceof ShapeContainerStyle) {
                features.add(ViewpointPackage.Literals.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR);
            }
        } else if (Properties.ID_LINECOLOR.equals(gmfPropertyID)) {
            if (style instanceof EdgeStyle) {
                features.add(ViewpointPackage.Literals.EDGE_STYLE__STROKE_COLOR);
            } else {
                features.add(ViewpointPackage.Literals.BORDERED_STYLE__BORDER_COLOR);
            }
        } else if (Properties.ID_FONTCOLOR.equals(gmfPropertyID)) {
            features.add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR);
        }
        return features;
    }

    private void updateLabelStyle(View notationView, BasicLabelStyle labelStyle) {
        if (labelStyle != null) {
            final FontStyle fontStyle = (FontStyle) createOrFindStyle(notationView, NotationPackage.eINSTANCE.getFontStyle());
            if (fontStyle.isBold() && fontStyle.isItalic()) {
                labelStyle.setLabelFormat(FontFormat.BOLD_ITALIC_LITERAL);
            } else if (fontStyle.isBold()) {
                labelStyle.setLabelFormat(FontFormat.BOLD_LITERAL);
            } else if (fontStyle.isItalic()) {
                labelStyle.setLabelFormat(FontFormat.ITALIC_LITERAL);
            } else {
                labelStyle.setLabelFormat(FontFormat.NORMAL_LITERAL);
            }
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
