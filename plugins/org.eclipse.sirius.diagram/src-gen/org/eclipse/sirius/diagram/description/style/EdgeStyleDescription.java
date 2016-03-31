/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.style;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Style Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The style of an edge. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getStrokeColor
 * <em>Stroke Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getLineStyle
 * <em>Line Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getSourceArrow
 * <em>Source Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getTargetArrow
 * <em>Target Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getSizeComputationExpression
 * <em>Size Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getRoutingStyle
 * <em>Routing Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getFoldingStyle
 * <em>Folding Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getBeginLabelStyleDescription
 * <em>Begin Label Style Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getCenterLabelStyleDescription
 * <em>Center Label Style Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getEndLabelStyleDescription
 * <em>End Label Style Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getEndsCentering
 * <em>Ends Centering</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getCenteredSourceMappings
 * <em>Centered Source Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getCenteredTargetMappings
 * <em>Centered Target Mappings</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription()
 * @model
 * @generated
 */
public interface EdgeStyleDescription extends StyleDescription {
    /**
     * Returns the value of the '<em><b>Stroke Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * color of the edge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Stroke Color</em>' reference.
     * @see #setStrokeColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_StrokeColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getStrokeColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getStrokeColor
     * <em>Stroke Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Stroke Color</em>' reference.
     * @see #getStrokeColor()
     * @generated
     */
    void setStrokeColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Line Style</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.diagram.LineStyle}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The style of the line.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.LineStyle
     * @see #setLineStyle(LineStyle)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_LineStyle()
     * @model
     * @generated
     */
    LineStyle getLineStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getLineStyle
     * <em>Line Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.LineStyle
     * @see #getLineStyle()
     * @generated
     */
    void setLineStyle(LineStyle value);

    /**
     * Returns the value of the '<em><b>Source Arrow</b></em>' attribute. The
     * default value is <code>"NoDecoration"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.EdgeArrows}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * source decoration. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Source Arrow</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see #setSourceArrow(EdgeArrows)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_SourceArrow()
     * @model default="NoDecoration" required="true"
     * @generated
     */
    EdgeArrows getSourceArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getSourceArrow
     * <em>Source Arrow</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source Arrow</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see #getSourceArrow()
     * @generated
     */
    void setSourceArrow(EdgeArrows value);

    /**
     * Returns the value of the '<em><b>Target Arrow</b></em>' attribute. The
     * default value is <code>"InputArrow"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.EdgeArrows}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * target decoration. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target Arrow</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see #setTargetArrow(EdgeArrows)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_TargetArrow()
     * @model default="InputArrow" required="true"
     * @generated
     */
    EdgeArrows getTargetArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getTargetArrow
     * <em>Target Arrow</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Arrow</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see #getTargetArrow()
     * @generated
     */
    void setTargetArrow(EdgeArrows value);

    /**
     * Returns the value of the '<em><b>Size Computation Expression</b></em>'
     * attribute. The default value is <code>"1"</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> An expression to compute
     * the thickness of the link. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Size Computation Expression</em>'
     *         attribute.
     * @see #setSizeComputationExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_SizeComputationExpression()
     * @model default="1" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DSemanticDiagram.' view='diagram.DEdge | the current edge view for which the size is calculated.' sourceView='diagram.EdgeTarget | the source view of the current edge.' targetView='diagram.EdgeTarget | the target view of the current edge.'"
     * @generated
     */
    String getSizeComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getSizeComputationExpression
     * <em>Size Computation Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Size Computation Expression</em>'
     *            attribute.
     * @see #getSizeComputationExpression()
     * @generated
     */
    void setSizeComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Routing Style</b></em>' attribute. The
     * default value is <code>"straight"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.EdgeRouting}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * routing style for your edge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeRouting
     * @see #setRoutingStyle(EdgeRouting)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_RoutingStyle()
     * @model default="straight" required="true"
     * @generated
     */
    EdgeRouting getRoutingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getRoutingStyle
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeRouting
     * @see #getRoutingStyle()
     * @generated
     */
    void setRoutingStyle(EdgeRouting value);

    /**
     * Returns the value of the '<em><b>Folding Style</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.diagram.description.FoldingStyle}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> A
     * folding style allow to collapse the elements targeted by the edge. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Folding Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.FoldingStyle
     * @see #setFoldingStyle(FoldingStyle)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_FoldingStyle()
     * @model
     * @generated
     */
    FoldingStyle getFoldingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getFoldingStyle
     * <em>Folding Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Folding Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.FoldingStyle
     * @see #getFoldingStyle()
     * @generated
     */
    void setFoldingStyle(FoldingStyle value);

    /**
     * Returns the value of the '<em><b>Begin Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Begin Label Style Description</em>'
     * containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Begin Label Style Description</em>'
     *         containment reference.
     * @see #setBeginLabelStyleDescription(BeginLabelStyleDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_BeginLabelStyleDescription()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    BeginLabelStyleDescription getBeginLabelStyleDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getBeginLabelStyleDescription
     * <em>Begin Label Style Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Begin Label Style Description</em>'
     *            containment reference.
     * @see #getBeginLabelStyleDescription()
     * @generated
     */
    void setBeginLabelStyleDescription(BeginLabelStyleDescription value);

    /**
     * Returns the value of the '<em><b>Center Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Center Label Style Description</em>'
     * containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Center Label Style Description</em>'
     *         containment reference.
     * @see #setCenterLabelStyleDescription(CenterLabelStyleDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_CenterLabelStyleDescription()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    CenterLabelStyleDescription getCenterLabelStyleDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getCenterLabelStyleDescription
     * <em>Center Label Style Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Center Label Style Description</em>'
     *            containment reference.
     * @see #getCenterLabelStyleDescription()
     * @generated
     */
    void setCenterLabelStyleDescription(CenterLabelStyleDescription value);

    /**
     * Returns the value of the '<em><b>End Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Label Style Description</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>End Label Style Description</em>'
     *         containment reference.
     * @see #setEndLabelStyleDescription(EndLabelStyleDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_EndLabelStyleDescription()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EndLabelStyleDescription getEndLabelStyleDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getEndLabelStyleDescription
     * <em>End Label Style Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>End Label Style Description</em>'
     *            containment reference.
     * @see #getEndLabelStyleDescription()
     * @generated
     */
    void setEndLabelStyleDescription(EndLabelStyleDescription value);

    /**
     * Returns the value of the '<em><b>Ends Centering</b></em>' attribute. The
     * default value is <code>"None"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.description.CenteringStyle}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Use this feature to generalize the centered edge behavior to all source
     * mappings, all target mappings or both. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Ends Centering</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.CenteringStyle
     * @see #setEndsCentering(CenteringStyle)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_EndsCentering()
     * @model default="None"
     * @generated
     */
    CenteringStyle getEndsCentering();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription#getEndsCentering
     * <em>Ends Centering</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Ends Centering</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.CenteringStyle
     * @see #getEndsCentering()
     * @generated
     */
    void setEndsCentering(CenteringStyle value);

    /**
     * Returns the value of the '<em><b>Centered Source Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The mapping that creates EdgeTargets that are the sources of the
     * ViewEdges that are created by this EdgeMapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Centered Source Mappings</em>' reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_CenteredSourceMappings()
     * @model required="true"
     * @generated
     */
    EList<DiagramElementMapping> getCenteredSourceMappings();

    /**
     * Returns the value of the '<em><b>Centered Target Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The mapping that creates EdgeTargets that are the targets of the
     * ViewEdges that are created by this EdgeMapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Centered Target Mappings</em>' reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getEdgeStyleDescription_CenteredTargetMappings()
     * @model required="true"
     * @generated
     */
    EList<DiagramElementMapping> getCenteredTargetMappings();

} // EdgeStyleDescription
