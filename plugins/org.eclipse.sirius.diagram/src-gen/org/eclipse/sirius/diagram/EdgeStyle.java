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
package org.eclipse.sirius.diagram;

import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The style of an edge. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getLineStyle
 * <em>Line Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getSourceArrow
 * <em>Source Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getTargetArrow
 * <em>Target Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getFoldingStyle
 * <em>Folding Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getSize <em>Size</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getRoutingStyle
 * <em>Routing Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getBeginLabelStyle
 * <em>Begin Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getCenterLabelStyle
 * <em>Center Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getEndLabelStyle
 * <em>End Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getCentered <em>Centered</em>
 * }</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeStyle#getStrokeColor
 * <em>Stroke Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle()
 * @model
 * @generated
 */
public interface EdgeStyle extends Style {
    /**
     * Returns the value of the '<em><b>Stroke Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The color of the edge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Stroke Color</em>' containment reference.
     * @see #setStrokeColor(RGBValues)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_StrokeColor()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RGBValues getStrokeColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getStrokeColor
     * <em>Stroke Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Stroke Color</em>' attribute.
     * @see #getStrokeColor()
     * @generated
     */
    void setStrokeColor(RGBValues value);

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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_LineStyle()
     * @model
     * @generated
     */
    LineStyle getLineStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getLineStyle
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_SourceArrow()
     * @model default="NoDecoration" required="true"
     * @generated
     */
    EdgeArrows getSourceArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getSourceArrow
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_TargetArrow()
     * @model default="InputArrow" required="true"
     * @generated
     */
    EdgeArrows getTargetArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getTargetArrow
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
     * Returns the value of the '<em><b>Folding Style</b></em>' attribute. The
     * default value is <code>"NONE"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.description.FoldingStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Folding Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Folding Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.FoldingStyle
     * @see #setFoldingStyle(FoldingStyle)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_FoldingStyle()
     * @model default="NONE" required="true"
     * @generated
     */
    FoldingStyle getFoldingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getFoldingStyle
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
     * Returns the value of the '<em><b>Size</b></em>' attribute. The default
     * value is <code>"1"</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The line width. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_Size()
     * @model default="1"
     * @generated
     */
    Integer getSize();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getSize <em>Size</em>}'
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
     * enumeration {@link org.eclipse.sirius.diagram.EdgeRouting}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * routing style of the edge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeRouting
     * @see #setRoutingStyle(EdgeRouting)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_RoutingStyle()
     * @model default="straight" required="true"
     * @generated
     */
    EdgeRouting getRoutingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getRoutingStyle
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_BeginLabelStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    BeginLabelStyle getBeginLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getBeginLabelStyle
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_CenterLabelStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    CenterLabelStyle getCenterLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getCenterLabelStyle
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_EndLabelStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EndLabelStyle getEndLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getEndLabelStyle
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

    /**
     * Returns the value of the '<em><b>Centered</b></em>' attribute. The
     * default value is <code>"None"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.description.CenteringStyle}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Centered</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Centered</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.CenteringStyle
     * @see #setCentered(CenteringStyle)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeStyle_Centered()
     * @model default="None" required="true"
     * @generated
     */
    CenteringStyle getCentered();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getCentered <em>Centered</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Centered</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.CenteringStyle
     * @see #getCentered()
     * @generated
     */
    void setCentered(CenteringStyle value);

} // EdgeStyle
