/*******************************************************************************
 * Copyright (c) 2015, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.query;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A query on {@link Customizable}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CustomizableQuery {

    private Customizable customizable;

    /**
     * Default constructor.
     * 
     * @param customizable
     *            the {@link Customizable} to query
     */
    public CustomizableQuery(Customizable customizable) {
        this.customizable = customizable;
    }

    /**
     * Get all feature names customizables.
     * 
     * @return all feature names customizables
     */
    public List<String> getCustomizableFeatureNames() {
        List<String> customizableFeatureNames = new ArrayList<String>();
        for (EStructuralFeature feature : customizable.eClass().getEAllStructuralFeatures()) {
            // CHECKSTYLE:OFF
            if (!ViewpointPackage.Literals.IDENTIFIED_ELEMENT__UID.equals(feature) && !ViewpointPackage.Literals.STYLE__DESCRIPTION.equals(feature)
                    && !ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES.equals(feature) && !isUselessFeature(feature) && !isGaugeCompositeSectionsFeature(feature)
                    && !isEdgeStyleBeginCenterEndFeature(feature) && !isProvidedShapeIdFeature(feature)) {
                // CHECKSTYLE:ON
                // TODO : BORDERED_STYLE__BORDER_SIZE is updated according to
                // borderSizeComputationExpression is this last is a copy of the
                // vsm element, the BorderStyle.borderSizeComputationExpression
                // is useless
                // TODO : same thing for NodeStyle.hideLabelByDefault
                customizableFeatureNames.add(feature.getName());
            }
        }
        return customizableFeatureNames;
    }

    private boolean isGaugeCompositeSectionsFeature(EStructuralFeature feature) {
        boolean isGaugeCompositeSectionsFeature = DiagramPackage.Literals.GAUGE_COMPOSITE_STYLE__SECTIONS.equals(feature);
        return isGaugeCompositeSectionsFeature;
    }

    private boolean isEdgeStyleBeginCenterEndFeature(EStructuralFeature feature) {
        boolean isEdgeStyleBeginCenterEndFeature = DiagramPackage.Literals.EDGE_STYLE__BEGIN_LABEL_STYLE.equals(feature) || DiagramPackage.Literals.EDGE_STYLE__CENTER_LABEL_STYLE.equals(feature)
                || DiagramPackage.Literals.EDGE_STYLE__END_LABEL_STYLE.equals(feature);
        return isEdgeStyleBeginCenterEndFeature;
    }

    private boolean isUselessFeature(EStructuralFeature feature) {
        // CHECKSTYLE:OFF
        return DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE.equals(feature) || DiagramPackage.Literals.HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT.equals(feature)
                || DiagramPackage.Literals.SQUARE__WIDTH.equals(feature) || DiagramPackage.Literals.SQUARE__HEIGHT.equals(feature) || DiagramPackage.Literals.LOZENGE__WIDTH.equals(feature)
                || DiagramPackage.Literals.LOZENGE__HEIGHT.equals(feature) || DiagramPackage.Literals.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION.equals(feature)
                || DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__SHAPE.equals(feature)
                || (customizable instanceof WorkspaceImage && customizable.eContainer() instanceof DDiagramElementContainer && DiagramPackage.Literals.NODE_STYLE__LABEL_POSITION.equals(feature))
                || (customizable instanceof BracketEdgeStyle && DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.equals(feature));
        // Only one literal choice is possible for ShapeContainerStyle.shape
        // then it is not usefull to customize it
        // The labelPosition for DDiagramElementContainer isn't taken into
        // account
        // CHECKSTYLE:ON
    }

    private boolean isProvidedShapeIdFeature(EStructuralFeature feature) {
        return DiagramPackage.Literals.BUNDLED_IMAGE__PROVIDED_SHAPE_ID.equals(feature);
    }

}
