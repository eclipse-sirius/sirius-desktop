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
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.StyleImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getLineStyle
 * <em>Line Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getSourceArrow
 * <em>Source Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getTargetArrow
 * <em>Target Arrow</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getFoldingStyle
 * <em>Folding Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getSize
 * <em>Size</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getRoutingStyle
 * <em>Routing Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getBeginLabelStyle
 * <em>Begin Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getCenterLabelStyle
 * <em>Center Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getEndLabelStyle
 * <em>End Label Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getCentered
 * <em>Centered</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl#getStrokeColor
 * <em>Stroke Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeStyleImpl extends StyleImpl implements EdgeStyle {
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
    protected LineStyle lineStyle = EdgeStyleImpl.LINE_STYLE_EDEFAULT;

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
    protected EdgeArrows sourceArrow = EdgeStyleImpl.SOURCE_ARROW_EDEFAULT;

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
    protected EdgeArrows targetArrow = EdgeStyleImpl.TARGET_ARROW_EDEFAULT;

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
     * The cached value of the '{@link #getFoldingStyle() <em>Folding Style</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFoldingStyle()
     * @generated
     * @ordered
     */
    protected FoldingStyle foldingStyle = EdgeStyleImpl.FOLDING_STYLE_EDEFAULT;

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
    protected Integer size = EdgeStyleImpl.SIZE_EDEFAULT;

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
     * The cached value of the '{@link #getRoutingStyle() <em>Routing Style</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRoutingStyle()
     * @generated
     * @ordered
     */
    protected EdgeRouting routingStyle = EdgeStyleImpl.ROUTING_STYLE_EDEFAULT;

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
    protected CenteringStyle centered = EdgeStyleImpl.CENTERED_EDEFAULT;

    /**
     * The default value of the '{@link #getStrokeColor() <em>Stroke Color</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStrokeColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues STROKE_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "136,136,136"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getStrokeColor() <em>Stroke Color</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStrokeColor()
     * @generated
     * @ordered
     */
    protected RGBValues strokeColor = EdgeStyleImpl.STROKE_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeStyleImpl() {
        super();
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
    @Override
    public RGBValues getStrokeColor() {
        return strokeColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStrokeColor(RGBValues newStrokeColor) {
        RGBValues oldStrokeColor = strokeColor;
        strokeColor = newStrokeColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__STROKE_COLOR, oldStrokeColor, strokeColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LineStyle getLineStyle() {
        return lineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLineStyle(LineStyle newLineStyle) {
        LineStyle oldLineStyle = lineStyle;
        lineStyle = newLineStyle == null ? EdgeStyleImpl.LINE_STYLE_EDEFAULT : newLineStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__LINE_STYLE, oldLineStyle, lineStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeArrows getSourceArrow() {
        return sourceArrow;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSourceArrow(EdgeArrows newSourceArrow) {
        EdgeArrows oldSourceArrow = sourceArrow;
        sourceArrow = newSourceArrow == null ? EdgeStyleImpl.SOURCE_ARROW_EDEFAULT : newSourceArrow;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__SOURCE_ARROW, oldSourceArrow, sourceArrow));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeArrows getTargetArrow() {
        return targetArrow;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTargetArrow(EdgeArrows newTargetArrow) {
        EdgeArrows oldTargetArrow = targetArrow;
        targetArrow = newTargetArrow == null ? EdgeStyleImpl.TARGET_ARROW_EDEFAULT : newTargetArrow;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__TARGET_ARROW, oldTargetArrow, targetArrow));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FoldingStyle getFoldingStyle() {
        return foldingStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFoldingStyle(FoldingStyle newFoldingStyle) {
        FoldingStyle oldFoldingStyle = foldingStyle;
        foldingStyle = newFoldingStyle == null ? EdgeStyleImpl.FOLDING_STYLE_EDEFAULT : newFoldingStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__FOLDING_STYLE, oldFoldingStyle, foldingStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSize(Integer newSize) {
        Integer oldSize = size;
        size = newSize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__SIZE, oldSize, size));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeRouting getRoutingStyle() {
        return routingStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRoutingStyle(EdgeRouting newRoutingStyle) {
        EdgeRouting oldRoutingStyle = routingStyle;
        routingStyle = newRoutingStyle == null ? EdgeStyleImpl.ROUTING_STYLE_EDEFAULT : newRoutingStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__ROUTING_STYLE, oldRoutingStyle, routingStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BeginLabelStyle getBeginLabelStyle() {
        if (beginLabelStyle != null && beginLabelStyle.eIsProxy()) {
            InternalEObject oldBeginLabelStyle = (InternalEObject) beginLabelStyle;
            beginLabelStyle = (BeginLabelStyle) eResolveProxy(oldBeginLabelStyle);
            if (beginLabelStyle != oldBeginLabelStyle) {
                InternalEObject newBeginLabelStyle = (InternalEObject) beginLabelStyle;
                NotificationChain msgs = oldBeginLabelStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, null);
                if (newBeginLabelStyle.eInternalContainer() == null) {
                    msgs = newBeginLabelStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, oldBeginLabelStyle, beginLabelStyle));
                }
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
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBeginLabelStyle(BeginLabelStyle newBeginLabelStyle) {
        if (newBeginLabelStyle != beginLabelStyle) {
            NotificationChain msgs = null;
            if (beginLabelStyle != null) {
                msgs = ((InternalEObject) beginLabelStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
            }
            if (newBeginLabelStyle != null) {
                msgs = ((InternalEObject) newBeginLabelStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, null, msgs);
            }
            msgs = basicSetBeginLabelStyle(newBeginLabelStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE, newBeginLabelStyle, newBeginLabelStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CenterLabelStyle getCenterLabelStyle() {
        if (centerLabelStyle != null && centerLabelStyle.eIsProxy()) {
            InternalEObject oldCenterLabelStyle = (InternalEObject) centerLabelStyle;
            centerLabelStyle = (CenterLabelStyle) eResolveProxy(oldCenterLabelStyle);
            if (centerLabelStyle != oldCenterLabelStyle) {
                InternalEObject newCenterLabelStyle = (InternalEObject) centerLabelStyle;
                NotificationChain msgs = oldCenterLabelStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, null);
                if (newCenterLabelStyle.eInternalContainer() == null) {
                    msgs = newCenterLabelStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, oldCenterLabelStyle, centerLabelStyle));
                }
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
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCenterLabelStyle(CenterLabelStyle newCenterLabelStyle) {
        if (newCenterLabelStyle != centerLabelStyle) {
            NotificationChain msgs = null;
            if (centerLabelStyle != null) {
                msgs = ((InternalEObject) centerLabelStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
            }
            if (newCenterLabelStyle != null) {
                msgs = ((InternalEObject) newCenterLabelStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, null, msgs);
            }
            msgs = basicSetCenterLabelStyle(newCenterLabelStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE, newCenterLabelStyle, newCenterLabelStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EndLabelStyle getEndLabelStyle() {
        if (endLabelStyle != null && endLabelStyle.eIsProxy()) {
            InternalEObject oldEndLabelStyle = (InternalEObject) endLabelStyle;
            endLabelStyle = (EndLabelStyle) eResolveProxy(oldEndLabelStyle);
            if (endLabelStyle != oldEndLabelStyle) {
                InternalEObject newEndLabelStyle = (InternalEObject) endLabelStyle;
                NotificationChain msgs = oldEndLabelStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, null);
                if (newEndLabelStyle.eInternalContainer() == null) {
                    msgs = newEndLabelStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, oldEndLabelStyle, endLabelStyle));
                }
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
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEndLabelStyle(EndLabelStyle newEndLabelStyle) {
        if (newEndLabelStyle != endLabelStyle) {
            NotificationChain msgs = null;
            if (endLabelStyle != null) {
                msgs = ((InternalEObject) endLabelStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
            }
            if (newEndLabelStyle != null) {
                msgs = ((InternalEObject) newEndLabelStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, null, msgs);
            }
            msgs = basicSetEndLabelStyle(newEndLabelStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__END_LABEL_STYLE, newEndLabelStyle, newEndLabelStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CenteringStyle getCentered() {
        return centered;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCentered(CenteringStyle newCentered) {
        CenteringStyle oldCentered = centered;
        centered = newCentered == null ? EdgeStyleImpl.CENTERED_EDEFAULT : newCentered;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.EDGE_STYLE__CENTERED, oldCentered, centered));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
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
            if (resolve) {
                return getBeginLabelStyle();
            }
            return basicGetBeginLabelStyle();
        case DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            if (resolve) {
                return getCenterLabelStyle();
            }
            return basicGetCenterLabelStyle();
        case DiagramPackage.EDGE_STYLE__END_LABEL_STYLE:
            if (resolve) {
                return getEndLabelStyle();
            }
            return basicGetEndLabelStyle();
        case DiagramPackage.EDGE_STYLE__CENTERED:
            return getCentered();
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            return getStrokeColor();
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
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            setStrokeColor((RGBValues) newValue);
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
        case DiagramPackage.EDGE_STYLE__LINE_STYLE:
            setLineStyle(EdgeStyleImpl.LINE_STYLE_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__SOURCE_ARROW:
            setSourceArrow(EdgeStyleImpl.SOURCE_ARROW_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__TARGET_ARROW:
            setTargetArrow(EdgeStyleImpl.TARGET_ARROW_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__FOLDING_STYLE:
            setFoldingStyle(EdgeStyleImpl.FOLDING_STYLE_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__SIZE:
            setSize(EdgeStyleImpl.SIZE_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__ROUTING_STYLE:
            setRoutingStyle(EdgeStyleImpl.ROUTING_STYLE_EDEFAULT);
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
            setCentered(EdgeStyleImpl.CENTERED_EDEFAULT);
            return;
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            setStrokeColor(EdgeStyleImpl.STROKE_COLOR_EDEFAULT);
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
        case DiagramPackage.EDGE_STYLE__LINE_STYLE:
            return lineStyle != EdgeStyleImpl.LINE_STYLE_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__SOURCE_ARROW:
            return sourceArrow != EdgeStyleImpl.SOURCE_ARROW_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__TARGET_ARROW:
            return targetArrow != EdgeStyleImpl.TARGET_ARROW_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__FOLDING_STYLE:
            return foldingStyle != EdgeStyleImpl.FOLDING_STYLE_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__SIZE:
            return EdgeStyleImpl.SIZE_EDEFAULT == null ? size != null : !EdgeStyleImpl.SIZE_EDEFAULT.equals(size);
        case DiagramPackage.EDGE_STYLE__ROUTING_STYLE:
            return routingStyle != EdgeStyleImpl.ROUTING_STYLE_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE:
            return beginLabelStyle != null;
        case DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE:
            return centerLabelStyle != null;
        case DiagramPackage.EDGE_STYLE__END_LABEL_STYLE:
            return endLabelStyle != null;
        case DiagramPackage.EDGE_STYLE__CENTERED:
            return centered != EdgeStyleImpl.CENTERED_EDEFAULT;
        case DiagramPackage.EDGE_STYLE__STROKE_COLOR:
            return EdgeStyleImpl.STROKE_COLOR_EDEFAULT == null ? strokeColor != null : !EdgeStyleImpl.STROKE_COLOR_EDEFAULT.equals(strokeColor);
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
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (lineStyle: "); //$NON-NLS-1$
        result.append(lineStyle);
        result.append(", sourceArrow: "); //$NON-NLS-1$
        result.append(sourceArrow);
        result.append(", targetArrow: "); //$NON-NLS-1$
        result.append(targetArrow);
        result.append(", foldingStyle: "); //$NON-NLS-1$
        result.append(foldingStyle);
        result.append(", size: "); //$NON-NLS-1$
        result.append(size);
        result.append(", routingStyle: "); //$NON-NLS-1$
        result.append(routingStyle);
        result.append(", centered: "); //$NON-NLS-1$
        result.append(centered);
        result.append(", strokeColor: "); //$NON-NLS-1$
        result.append(strokeColor);
        result.append(')');
        return result.toString();
    }

} // EdgeStyleImpl
