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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.business.internal.color.DiagramStyleColorUpdater;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.impl.StyleImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getStrokeColor <em>
 * Stroke Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getLineStyle <em>
 * Line Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getSourceArrow <em>
 * Source Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getTargetArrow <em>
 * Target Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getFoldingStyle <em>
 * Folding Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getSize <em>Size
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getRoutingStyle <em>
 * Routing Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getBeginLabelStyle
 * <em>Begin Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getCenterLabelStyle
 * <em>Center Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getEndLabelStyle
 * <em>End Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getCentered <em>
 * Centered</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EdgeStyleImpl extends StyleImpl implements EdgeStyle {
    /**
     * The cached value of the '{@link #getStrokeColor() <em>Stroke Color</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStrokeColor()
     * @generated
     * @ordered
     */
    protected RGBValues strokeColor;

    /**
     * The default value of the '{@link #getLineStyle() <em>Line Style</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected static final LineStyle LINE_STYLE_EDEFAULT = LineStyle.SOLID_LITERAL;

    /**
     * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected LineStyle lineStyle = LINE_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceArrow() <em>Source Arrow</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSourceArrow()
     * @generated
     * @ordered
     */
    protected static final EdgeArrows SOURCE_ARROW_EDEFAULT = EdgeArrows.NO_DECORATION_LITERAL;

    /**
     * The cached value of the '{@link #getSourceArrow() <em>Source Arrow</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSourceArrow()
     * @generated
     * @ordered
     */
    protected EdgeArrows sourceArrow = SOURCE_ARROW_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetArrow() <em>Target Arrow</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetArrow()
     * @generated
     * @ordered
     */
    protected static final EdgeArrows TARGET_ARROW_EDEFAULT = EdgeArrows.INPUT_ARROW_LITERAL;

    /**
     * The cached value of the '{@link #getTargetArrow() <em>Target Arrow</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetArrow()
     * @generated
     * @ordered
     */
    protected EdgeArrows targetArrow = TARGET_ARROW_EDEFAULT;

    /**
     * The default value of the '{@link #getFoldingStyle()
     * <em>Folding Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFoldingStyle()
     * @generated
     * @ordered
     */
    protected static final FoldingStyle FOLDING_STYLE_EDEFAULT = FoldingStyle.NONE_LITERAL;

    /**
     * The cached value of the '{@link #getFoldingStyle()
     * <em>Folding Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFoldingStyle()
     * @generated
     * @ordered
     */
    protected FoldingStyle foldingStyle = FOLDING_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final Integer SIZE_EDEFAULT = new Integer(1);

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected Integer size = SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #getRoutingStyle()
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRoutingStyle()
     * @generated
     * @ordered
     */
    protected static final EdgeRouting ROUTING_STYLE_EDEFAULT = EdgeRouting.STRAIGHT_LITERAL;

    /**
     * The cached value of the '{@link #getRoutingStyle()
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRoutingStyle()
     * @generated
     * @ordered
     */
    protected EdgeRouting routingStyle = ROUTING_STYLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBeginLabelStyle()
     * <em>Begin Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getBeginLabelStyle()
     * @generated
     * @ordered
     */
    protected BeginLabelStyle beginLabelStyle;

    /**
     * The cached value of the '{@link #getCenterLabelStyle()
     * <em>Center Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getCenterLabelStyle()
     * @generated
     * @ordered
     */
    protected CenterLabelStyle centerLabelStyle;

    /**
     * The cached value of the '{@link #getEndLabelStyle()
     * <em>End Label Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEndLabelStyle()
     * @generated
     * @ordered
     */
    protected EndLabelStyle endLabelStyle;

    /**
     * The default value of the '{@link #getCentered() <em>Centered</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCentered()
     * @generated
     * @ordered
     */
    protected static final CenteringStyle CENTERED_EDEFAULT = CenteringStyle.NONE;

    /**
     * The cached value of the '{@link #getCentered() <em>Centered</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCentered()
     * @generated
     * @ordered
     */
    protected CenteringStyle centered = CENTERED_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    protected EdgeStyleImpl() {
        super();
        new DiagramStyleColorUpdater().setDefaultValues(this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.EDGE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getStrokeColor() {
        if (strokeColor != null && strokeColor.eIsProxy()) {
            InternalEObject oldStrokeColor = (InternalEObject) strokeColor;
            strokeColor = (RGBValues) eResolveProxy(oldStrokeColor);
            if (strokeColor != oldStrokeColor) {
                InternalEObject newStrokeColor = (InternalEObject) strokeColor;
                NotificationChain msgs = oldStrokeColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__STROKE_COLOR, null, null);
                if (newStrokeColor.eInternalContainer() == null) {
                    msgs = newStrokeColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__STROKE_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.EDGE_STYLE__STROKE_COLOR, oldStrokeColor, strokeColor));
            }
        }
        return strokeColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetStrokeColor() {
        return strokeColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetStrokeColor(RGBValues newStrokeColor, NotificationChain msgs) {
        RGBValues oldStrokeColor = strokeColor;
        strokeColor = newStrokeColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__STROKE_COLOR, oldStrokeColor, newStrokeColor);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStrokeColor(RGBValues newStrokeColor) {
        if (newStrokeColor != strokeColor) {
            NotificationChain msgs = null;
            if (strokeColor != null)
                msgs = ((InternalEObject) strokeColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__STROKE_COLOR, null, msgs);
            if (newStrokeColor != null)
                msgs = ((InternalEObject) newStrokeColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__STROKE_COLOR, null, msgs);
            msgs = basicSetStrokeColor(newStrokeColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__STROKE_COLOR, newStrokeColor, newStrokeColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LineStyle getLineStyle() {
        return lineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLineStyle(LineStyle newLineStyle) {
        LineStyle oldLineStyle = lineStyle;
        lineStyle = newLineStyle == null ? LINE_STYLE_EDEFAULT : newLineStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__LINE_STYLE, oldLineStyle, lineStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeArrows getSourceArrow() {
        return sourceArrow;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSourceArrow(EdgeArrows newSourceArrow) {
        EdgeArrows oldSourceArrow = sourceArrow;
        sourceArrow = newSourceArrow == null ? SOURCE_ARROW_EDEFAULT : newSourceArrow;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__SOURCE_ARROW, oldSourceArrow, sourceArrow));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeArrows getTargetArrow() {
        return targetArrow;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetArrow(EdgeArrows newTargetArrow) {
        EdgeArrows oldTargetArrow = targetArrow;
        targetArrow = newTargetArrow == null ? TARGET_ARROW_EDEFAULT : newTargetArrow;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__TARGET_ARROW, oldTargetArrow, targetArrow));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FoldingStyle getFoldingStyle() {
        return foldingStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFoldingStyle(FoldingStyle newFoldingStyle) {
        FoldingStyle oldFoldingStyle = foldingStyle;
        foldingStyle = newFoldingStyle == null ? FOLDING_STYLE_EDEFAULT : newFoldingStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__FOLDING_STYLE, oldFoldingStyle, foldingStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSize(Integer newSize) {
        Integer oldSize = size;
        size = newSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__SIZE, oldSize, size));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeRouting getRoutingStyle() {
        return routingStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRoutingStyle(EdgeRouting newRoutingStyle) {
        EdgeRouting oldRoutingStyle = routingStyle;
        routingStyle = newRoutingStyle == null ? ROUTING_STYLE_EDEFAULT : newRoutingStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__ROUTING_STYLE, oldRoutingStyle, routingStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BeginLabelStyle getBeginLabelStyle() {
        if (beginLabelStyle != null && beginLabelStyle.eIsProxy()) {
            InternalEObject oldBeginLabelStyle = (InternalEObject) beginLabelStyle;
            beginLabelStyle = (BeginLabelStyle) eResolveProxy(oldBeginLabelStyle);
            if (beginLabelStyle != oldBeginLabelStyle) {
                InternalEObject newBeginLabelStyle = (InternalEObject) beginLabelStyle;
                NotificationChain msgs = oldBeginLabelStyle.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, null);
                if (newBeginLabelStyle.eInternalContainer() == null) {
                    msgs = newBeginLabelStyle.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, oldBeginLabelStyle, beginLabelStyle));
            }
        }
        return beginLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BeginLabelStyle basicGetBeginLabelStyle() {
        return beginLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBeginLabelStyle(BeginLabelStyle newBeginLabelStyle, NotificationChain msgs) {
        BeginLabelStyle oldBeginLabelStyle = beginLabelStyle;
        beginLabelStyle = newBeginLabelStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, oldBeginLabelStyle, newBeginLabelStyle);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBeginLabelStyle(BeginLabelStyle newBeginLabelStyle) {
        if (newBeginLabelStyle != beginLabelStyle) {
            NotificationChain msgs = null;
            if (beginLabelStyle != null)
                msgs = ((InternalEObject) beginLabelStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
            if (newBeginLabelStyle != null)
                msgs = ((InternalEObject) newBeginLabelStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
            msgs = basicSetBeginLabelStyle(newBeginLabelStyle, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, newBeginLabelStyle, newBeginLabelStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CenterLabelStyle getCenterLabelStyle() {
        if (centerLabelStyle != null && centerLabelStyle.eIsProxy()) {
            InternalEObject oldCenterLabelStyle = (InternalEObject) centerLabelStyle;
            centerLabelStyle = (CenterLabelStyle) eResolveProxy(oldCenterLabelStyle);
            if (centerLabelStyle != oldCenterLabelStyle) {
                InternalEObject newCenterLabelStyle = (InternalEObject) centerLabelStyle;
                NotificationChain msgs = oldCenterLabelStyle.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, null);
                if (newCenterLabelStyle.eInternalContainer() == null) {
                    msgs = newCenterLabelStyle.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, oldCenterLabelStyle, centerLabelStyle));
            }
        }
        return centerLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CenterLabelStyle basicGetCenterLabelStyle() {
        return centerLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCenterLabelStyle(CenterLabelStyle newCenterLabelStyle, NotificationChain msgs) {
        CenterLabelStyle oldCenterLabelStyle = centerLabelStyle;
        centerLabelStyle = newCenterLabelStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, oldCenterLabelStyle, newCenterLabelStyle);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCenterLabelStyle(CenterLabelStyle newCenterLabelStyle) {
        if (newCenterLabelStyle != centerLabelStyle) {
            NotificationChain msgs = null;
            if (centerLabelStyle != null)
                msgs = ((InternalEObject) centerLabelStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
            if (newCenterLabelStyle != null)
                msgs = ((InternalEObject) newCenterLabelStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
            msgs = basicSetCenterLabelStyle(newCenterLabelStyle, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, newCenterLabelStyle, newCenterLabelStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EndLabelStyle getEndLabelStyle() {
        if (endLabelStyle != null && endLabelStyle.eIsProxy()) {
            InternalEObject oldEndLabelStyle = (InternalEObject) endLabelStyle;
            endLabelStyle = (EndLabelStyle) eResolveProxy(oldEndLabelStyle);
            if (endLabelStyle != oldEndLabelStyle) {
                InternalEObject newEndLabelStyle = (InternalEObject) endLabelStyle;
                NotificationChain msgs = oldEndLabelStyle.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, null);
                if (newEndLabelStyle.eInternalContainer() == null) {
                    msgs = newEndLabelStyle.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, oldEndLabelStyle, endLabelStyle));
            }
        }
        return endLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EndLabelStyle basicGetEndLabelStyle() {
        return endLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEndLabelStyle(EndLabelStyle newEndLabelStyle, NotificationChain msgs) {
        EndLabelStyle oldEndLabelStyle = endLabelStyle;
        endLabelStyle = newEndLabelStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, oldEndLabelStyle, newEndLabelStyle);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEndLabelStyle(EndLabelStyle newEndLabelStyle) {
        if (newEndLabelStyle != endLabelStyle) {
            NotificationChain msgs = null;
            if (endLabelStyle != null)
                msgs = ((InternalEObject) endLabelStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
            if (newEndLabelStyle != null)
                msgs = ((InternalEObject) newEndLabelStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
            msgs = basicSetEndLabelStyle(newEndLabelStyle, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, newEndLabelStyle, newEndLabelStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CenteringStyle getCentered() {
        return centered;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCentered(CenteringStyle newCentered) {
        CenteringStyle oldCentered = centered;
        centered = newCentered == null ? CENTERED_EDEFAULT : newCentered;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__CENTERED, oldCentered, centered));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            return basicSetStrokeColor(null, msgs);
        case DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            return basicSetBeginLabelStyle(null, msgs);
        case DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            return basicSetCenterLabelStyle(null, msgs);
        case DiagramPackage.EDGE_STYLE__END_LABEL_STYLE:
            return basicSetEndLabelStyle(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            if (resolve)
                return getStrokeColor();
            return basicGetStrokeColor();
        case DiagramPackage.EDGE_STYLE__LINE_STYLE:
            return getLineStyle();
        case DiagramPackage.EDGE_STYLE__SOURCE_ARROW:
            return getSourceArrow();
        case DiagramPackage.EDGE_STYLE__TARGET_ARROW:
            return getTargetArrow();
        case DiagramPackage.EDGE_STYLE__FOLDING_STYLE:
            return getFoldingStyle();
        case DiagramPackage.EDGE_STYLE__SIZE:
            return getSize();
        case DiagramPackage.EDGE_STYLE__ROUTING_STYLE:
            return getRoutingStyle();
        case DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            if (resolve)
                return getBeginLabelStyle();
            return basicGetBeginLabelStyle();
        case DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            if (resolve)
                return getCenterLabelStyle();
            return basicGetCenterLabelStyle();
        case DiagramPackage.EDGE_STYLE__END_LABEL_STYLE:
            if (resolve)
                return getEndLabelStyle();
            return basicGetEndLabelStyle();
        case DiagramPackage.EDGE_STYLE__CENTERED:
            return getCentered();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            setStrokeColor((RGBValues) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__LINE_STYLE:
            setLineStyle((LineStyle) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__SOURCE_ARROW:
            setSourceArrow((EdgeArrows) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__TARGET_ARROW:
            setTargetArrow((EdgeArrows) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__FOLDING_STYLE:
            setFoldingStyle((FoldingStyle) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__SIZE:
            setSize((Integer) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__ROUTING_STYLE:
            setRoutingStyle((EdgeRouting) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            setBeginLabelStyle((BeginLabelStyle) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            setCenterLabelStyle((CenterLabelStyle) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__END_LABEL_STYLE:
            setEndLabelStyle((EndLabelStyle) newValue);
            return;
        case DiagramPackage.EDGE_STYLE__CENTERED:
            setCentered((CenteringStyle) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            setStrokeColor((RGBValues) null);
            return;
        case DiagramPackage.EDGE_STYLE__LINE_STYLE:
            setLineStyle(LINE_STYLE_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__SOURCE_ARROW:
            setSourceArrow(SOURCE_ARROW_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__TARGET_ARROW:
            setTargetArrow(TARGET_ARROW_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__FOLDING_STYLE:
            setFoldingStyle(FOLDING_STYLE_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__SIZE:
            setSize(SIZE_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__ROUTING_STYLE:
            setRoutingStyle(ROUTING_STYLE_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            setBeginLabelStyle((BeginLabelStyle) null);
            return;
        case DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            setCenterLabelStyle((CenterLabelStyle) null);
            return;
        case DiagramPackage.EDGE_STYLE__END_LABEL_STYLE:
            setEndLabelStyle((EndLabelStyle) null);
            return;
        case DiagramPackage.EDGE_STYLE__CENTERED:
            setCentered(CENTERED_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            return strokeColor != null;
        case DiagramPackage.EDGE_STYLE__LINE_STYLE:
            return lineStyle != LINE_STYLE_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__SOURCE_ARROW:
            return sourceArrow != SOURCE_ARROW_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__TARGET_ARROW:
            return targetArrow != TARGET_ARROW_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__FOLDING_STYLE:
            return foldingStyle != FOLDING_STYLE_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__SIZE:
            return SIZE_EDEFAULT == null ? size != null : !SIZE_EDEFAULT.equals(size);
        case DiagramPackage.EDGE_STYLE__ROUTING_STYLE:
            return routingStyle != ROUTING_STYLE_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            return beginLabelStyle != null;
        case DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            return centerLabelStyle != null;
        case DiagramPackage.EDGE_STYLE__END_LABEL_STYLE:
            return endLabelStyle != null;
        case DiagramPackage.EDGE_STYLE__CENTERED:
            return centered != CENTERED_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (lineStyle: ");
        result.append(lineStyle);
        result.append(", sourceArrow: ");
        result.append(sourceArrow);
        result.append(", targetArrow: ");
        result.append(targetArrow);
        result.append(", foldingStyle: ");
        result.append(foldingStyle);
        result.append(", size: ");
        result.append(size);
        result.append(", routingStyle: ");
        result.append(routingStyle);
        result.append(", centered: ");
        result.append(centered);
        result.append(')');
        return result.toString();
    }

} // EdgeStyleImpl
