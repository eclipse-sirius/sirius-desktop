/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint;

import org.eclipse.sirius.viewpoint.description.FoldingStyle;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> The style of an edge. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getStrokeColor <em>Stroke
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getLineStyle <em>Line Style
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getSourceArrow <em>Source
 * Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getTargetArrow <em>Target
 * Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getFoldingStyle <em>Folding
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getSize <em>Size</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getRoutingStyle <em>Routing
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getBeginLabelStyle <em>
 * Begin Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getCenterLabelStyle <em>
 * Center Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeStyle#getEndLabelStyle <em>End
 * Label Style</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getEdgeStyle()
 * @model
 * @generated
 */
public interface EdgeStyle extends Style {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Stroke Color</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stroke Color</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The color of the edge.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Stroke Color</em>' containment reference.
     * @see #setStrokeColor(ColorMapping)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getEdgeStyle_StrokeColor()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RGBValues getStrokeColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getStrokeColor
     * <em>Stroke Color</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Stroke Color</em>' containment
     *            reference.
     * @see #getStrokeColor()
     * @generated
     */
    void setStrokeColor(RGBValues value);

    /**
     * Returns the value of the '<em><b>Line Style</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.LineStyle} . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Style</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The style of the line.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.LineStyle
     * @see #setLineStyle(LineStyle)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getEdgeStyle_LineStyle()
     * @model
     * @generated
     */
    LineStyle getLineStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getLineStyle
     * <em>Line Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.LineStyle
     * @see #getLineStyle()
     * @generated
     */
    void setLineStyle(LineStyle value);

    /**
     * Returns the value of the '<em><b>Source Arrow</b></em>' attribute. The
     * default value is <code>"NoDecoration"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.EdgeArrows}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * source decoration. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Source Arrow</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @see #setSourceArrow(EdgeArrows)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getEdgeStyle_SourceArrow()
     * @model default="NoDecoration" required="true"
     * @generated
     */
    EdgeArrows getSourceArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getSourceArrow
     * <em>Source Arrow</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Arrow</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @see #getSourceArrow()
     * @generated
     */
    void setSourceArrow(EdgeArrows value);

    /**
     * Returns the value of the '<em><b>Target Arrow</b></em>' attribute. The
     * default value is <code>"OutputArrow"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.EdgeArrows}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * target decoration. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Target Arrow</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @see #setTargetArrow(EdgeArrows)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getEdgeStyle_TargetArrow()
     * @model default="OutputArrow" required="true"
     * @generated
     */
    EdgeArrows getTargetArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getTargetArrow
     * <em>Target Arrow</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Arrow</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @see #getTargetArrow()
     * @generated
     */
    void setTargetArrow(EdgeArrows value);

    /**
     * Returns the value of the '<em><b>Folding Style</b></em>' attribute. The
     * default value is <code>"NONE"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.description.FoldingStyle}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Folding Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Folding Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.FoldingStyle
     * @see #setFoldingStyle(FoldingStyle)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getEdgeStyle_FoldingStyle()
     * @model default="NONE" required="true"
     * @generated
     */
    FoldingStyle getFoldingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getFoldingStyle
     * <em>Folding Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Folding Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.FoldingStyle
     * @see #getFoldingStyle()
     * @generated
     */
    void setFoldingStyle(FoldingStyle value);

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute. The default
     * value is <code>"1"</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The line width. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(Integer)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getEdgeStyle_Size()
     * @model default="1"
     * @generated
     */
    Integer getSize();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getSize <em>Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
    void setSize(Integer value);

    /**
     * Returns the value of the '<em><b>Routing Style</b></em>' attribute. The
     * default value is <code>"straight"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.EdgeRouting}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * routing style of the edge. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeRouting
     * @see #setRoutingStyle(EdgeRouting)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getEdgeStyle_RoutingStyle()
     * @model default="straight" required="true"
     * @generated
     */
    EdgeRouting getRoutingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getRoutingStyle
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeRouting
     * @see #getRoutingStyle()
     * @generated
     */
    void setRoutingStyle(EdgeRouting value);

    /**
     * Returns the value of the '<em><b>Begin Label Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Begin Label Style</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Begin Label Style</em>' containment
     *         reference.
     * @see #setBeginLabelStyle(BeginLabelStyle)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getEdgeStyle_BeginLabelStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    BeginLabelStyle getBeginLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getBeginLabelStyle
     * <em>Begin Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Begin Label Style</em>' containment
     *            reference.
     * @see #getBeginLabelStyle()
     * @generated
     */
    void setBeginLabelStyle(BeginLabelStyle value);

    /**
     * Returns the value of the '<em><b>Center Label Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Center Label Style</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Center Label Style</em>' containment
     *         reference.
     * @see #setCenterLabelStyle(CenterLabelStyle)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getEdgeStyle_CenterLabelStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    CenterLabelStyle getCenterLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getCenterLabelStyle
     * <em>Center Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Center Label Style</em>' containment
     *            reference.
     * @see #getCenterLabelStyle()
     * @generated
     */
    void setCenterLabelStyle(CenterLabelStyle value);

    /**
     * Returns the value of the '<em><b>End Label Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Label Style</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>End Label Style</em>' containment
     *         reference.
     * @see #setEndLabelStyle(EndLabelStyle)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getEdgeStyle_EndLabelStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EndLabelStyle getEndLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle#getEndLabelStyle
     * <em>End Label Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>End Label Style</em>' containment
     *            reference.
     * @see #getEndLabelStyle()
     * @generated
     */
    void setEndLabelStyle(EndLabelStyle value);
} // EdgeStyle
