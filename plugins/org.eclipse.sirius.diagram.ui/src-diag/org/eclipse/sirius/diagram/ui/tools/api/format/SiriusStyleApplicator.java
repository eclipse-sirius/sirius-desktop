/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * 
 * This class contains methods for applying any style to any Sirius element. However, implementations may decide to
 * apply certain styles only to certain nodes. The default implementation applies styles only if the style type matches
 * the node type:
 * <ul>
 * <li><code>NodeStyle</code> on <code>DNode</code> or <code>DNodeList</code>,</li>
 * <li><code>ContainerStyle</code> on <code>DDiagramElementContainer</code>,</li>
 * <li>and <code>EdgeStyle</code> on <code>DEdge</code>.</li>
 * </ul>
 * 
 * @author Séraphin Costa
 * 
 */
public interface SiriusStyleApplicator {

    /**
     * Apply the Sirius style contained in <code>formatData</code> on the <code>semanticDecorator</code>.
     * 
     * @param semanticDecorator
     *            The Sirius element ({@link DSemanticDecorator}) on which to apply the style.
     * @param siriusStyle
     *            The sirius style to apply
     */
    default void applySiriusStyle(DSemanticDecorator semanticDecorator, Style siriusStyle) {
        // Make a copy of the style to allow several Paste with the same FormatData.
        Style copyOfSiriusStyle = SiriusCopierHelper.copyWithNoUidDuplication(siriusStyle);
        if ((semanticDecorator instanceof DNode || semanticDecorator instanceof DNodeListElement) && copyOfSiriusStyle instanceof NodeStyle) {
            if (semanticDecorator instanceof DNode) {
                computeCustomFeatures(((DNode) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
                ((DNode) semanticDecorator).setOwnedStyle((NodeStyle) copyOfSiriusStyle);
            } else {
                computeCustomFeatures(((DNodeListElement) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
                ((DNodeListElement) semanticDecorator).setOwnedStyle((NodeStyle) copyOfSiriusStyle);
            }
        } else if (semanticDecorator instanceof DDiagramElementContainer && copyOfSiriusStyle instanceof ContainerStyle) {
            if (((DDiagramElementContainer) semanticDecorator).getOwnedStyle() != null) {
                computeCustomFeatures(((DDiagramElementContainer) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
            }
            ((DDiagramElementContainer) semanticDecorator).setOwnedStyle((ContainerStyle) copyOfSiriusStyle);
        } else if (semanticDecorator instanceof DEdge && copyOfSiriusStyle instanceof EdgeStyle) {
            computeCustomFeatures(((DEdge) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
            ((DEdge) semanticDecorator).setOwnedStyle((EdgeStyle) copyOfSiriusStyle);
        }
    }

    /**
     * Check for each attribute of newStyle if it is the same in oldStyle. On the other hand, this attribute is added to
     * the custom features of the newStyle.
     * 
     * @param oldStyle
     *            The old style to compare with
     * @param newStyle
     *            The new style in which to add custom features.
     */
    default void computeCustomFeatures(Style oldStyle, Style newStyle) {
        for (EAttribute attribute : newStyle.eClass().getEAllAttributes()) {
            if (!ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES.equals(attribute)) {
                EAttribute attributeOfOldStyle = getCorrespondingEAttribute(attribute, oldStyle);
                if (attributeOfOldStyle != null) {
                    if (newStyle.eIsSet(attribute)) {
                        if (!newStyle.eGet(attribute).equals(oldStyle.eGet(attributeOfOldStyle))) {
                            newStyle.getCustomFeatures().add(attributeOfOldStyle.getName());
                        }
                    } else if (oldStyle.eIsSet(attributeOfOldStyle)) {
                        newStyle.getCustomFeatures().add(attributeOfOldStyle.getName());
                    }
                }
            }
        }
    }

    private EAttribute getCorrespondingEAttribute(EAttribute attribute, Style style) {
        EAttribute result = null;
        if (style.eClass().getFeatureID(attribute) != -1) {
            result = attribute;
        } else {
            // This attribute does not exist in the style. Check specific
            // mapping cases.
            EStructuralFeature structuralFeature = style.eClass().getEStructuralFeature(attribute.getName());
            if (structuralFeature instanceof EAttribute) {
                result = (EAttribute) structuralFeature;
            } else if ("color".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("backgroundColor"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("backgroundColor".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("color"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("width".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("horizontalDiameter"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("horizontalDiameter".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("width"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("height".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("verticalDiameter"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("verticalDiameter".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("height"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            }
        }
        return result;
    }
}
