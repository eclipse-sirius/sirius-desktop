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
package org.eclipse.sirius.diagram.description.style.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getStrokeColor
 * <em>Stroke Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getLineStyle
 * <em>Line Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getSourceArrow
 * <em>Source Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getTargetArrow
 * <em>Target Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getSizeComputationExpression
 * <em>Size Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getRoutingStyle
 * <em>Routing Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getFoldingStyle
 * <em>Folding Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getBeginLabelStyleDescription
 * <em>Begin Label Style Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getCenterLabelStyleDescription
 * <em>Center Label Style Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getEndLabelStyleDescription
 * <em>End Label Style Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getEndsCentering
 * <em>Ends Centering</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getCenteredSourceMappings
 * <em>Centered Source Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EdgeStyleDescriptionImpl#getCenteredTargetMappings
 * <em>Centered Target Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeStyleDescriptionImpl extends MinimalEObjectImpl.Container implements EdgeStyleDescription {
    /**
     * The cached value of the '{@link #getStrokeColor() <em>Stroke Color</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStrokeColor()
     * @generated
     * @ordered
     */
    protected ColorDescription strokeColor;

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
    protected LineStyle lineStyle = EdgeStyleDescriptionImpl.LINE_STYLE_EDEFAULT;

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
    protected EdgeArrows sourceArrow = EdgeStyleDescriptionImpl.SOURCE_ARROW_EDEFAULT;

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
    protected EdgeArrows targetArrow = EdgeStyleDescriptionImpl.TARGET_ARROW_EDEFAULT;

    /**
     * The default value of the '{@link #getSizeComputationExpression()
     * <em>Size Computation Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getSizeComputationExpression()
     * <em>Size Computation Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String sizeComputationExpression = EdgeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

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
    protected EdgeRouting routingStyle = EdgeStyleDescriptionImpl.ROUTING_STYLE_EDEFAULT;

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
    protected FoldingStyle foldingStyle = EdgeStyleDescriptionImpl.FOLDING_STYLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBeginLabelStyleDescription()
     * <em>Begin Label Style Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBeginLabelStyleDescription()
     * @generated
     * @ordered
     */
    protected BeginLabelStyleDescription beginLabelStyleDescription;

    /**
     * The cached value of the '{@link #getCenterLabelStyleDescription()
     * <em>Center Label Style Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCenterLabelStyleDescription()
     * @generated
     * @ordered
     */
    protected CenterLabelStyleDescription centerLabelStyleDescription;

    /**
     * The cached value of the '{@link #getEndLabelStyleDescription()
     * <em>End Label Style Description</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEndLabelStyleDescription()
     * @generated
     * @ordered
     */
    protected EndLabelStyleDescription endLabelStyleDescription;

    /**
     * The default value of the '{@link #getEndsCentering()
     * <em>Ends Centering</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getEndsCentering()
     * @generated
     * @ordered
     */
    protected static final CenteringStyle ENDS_CENTERING_EDEFAULT = CenteringStyle.NONE;

    /**
     * The cached value of the '{@link #getEndsCentering()
     * <em>Ends Centering</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getEndsCentering()
     * @generated
     * @ordered
     */
    protected CenteringStyle endsCentering = EdgeStyleDescriptionImpl.ENDS_CENTERING_EDEFAULT;

    /**
     * The cached value of the '{@link #getCenteredSourceMappings()
     * <em>Centered Source Mappings</em>}' reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getCenteredSourceMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> centeredSourceMappings;

    /**
     * The cached value of the '{@link #getCenteredTargetMappings()
     * <em>Centered Target Mappings</em>}' reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getCenteredTargetMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> centeredTargetMappings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.EDGE_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getStrokeColor() {
        if (strokeColor != null && strokeColor.eIsProxy()) {
            InternalEObject oldStrokeColor = (InternalEObject) strokeColor;
            strokeColor = (ColorDescription) eResolveProxy(oldStrokeColor);
            if (strokeColor != oldStrokeColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR, oldStrokeColor, strokeColor));
                }
            }
        }
        return strokeColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetStrokeColor() {
        return strokeColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStrokeColor(ColorDescription newStrokeColor) {
        ColorDescription oldStrokeColor = strokeColor;
        strokeColor = newStrokeColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR, oldStrokeColor, strokeColor));
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
        lineStyle = newLineStyle == null ? EdgeStyleDescriptionImpl.LINE_STYLE_EDEFAULT : newLineStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__LINE_STYLE, oldLineStyle, lineStyle));
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
        sourceArrow = newSourceArrow == null ? EdgeStyleDescriptionImpl.SOURCE_ARROW_EDEFAULT : newSourceArrow;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW, oldSourceArrow, sourceArrow));
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
        targetArrow = newTargetArrow == null ? EdgeStyleDescriptionImpl.TARGET_ARROW_EDEFAULT : newTargetArrow;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__TARGET_ARROW, oldTargetArrow, targetArrow));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSizeComputationExpression() {
        return sizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSizeComputationExpression(String newSizeComputationExpression) {
        String oldSizeComputationExpression = sizeComputationExpression;
        sizeComputationExpression = newSizeComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION, oldSizeComputationExpression, sizeComputationExpression));
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
        routingStyle = newRoutingStyle == null ? EdgeStyleDescriptionImpl.ROUTING_STYLE_EDEFAULT : newRoutingStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE, oldRoutingStyle, routingStyle));
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
        foldingStyle = newFoldingStyle == null ? EdgeStyleDescriptionImpl.FOLDING_STYLE_EDEFAULT : newFoldingStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE, oldFoldingStyle, foldingStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BeginLabelStyleDescription getBeginLabelStyleDescription() {
        if (beginLabelStyleDescription != null && beginLabelStyleDescription.eIsProxy()) {
            InternalEObject oldBeginLabelStyleDescription = (InternalEObject) beginLabelStyleDescription;
            beginLabelStyleDescription = (BeginLabelStyleDescription) eResolveProxy(oldBeginLabelStyleDescription);
            if (beginLabelStyleDescription != oldBeginLabelStyleDescription) {
                InternalEObject newBeginLabelStyleDescription = (InternalEObject) beginLabelStyleDescription;
                NotificationChain msgs = oldBeginLabelStyleDescription.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION,
                        null, null);
                if (newBeginLabelStyleDescription.eInternalContainer() == null) {
                    msgs = newBeginLabelStyleDescription.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION, oldBeginLabelStyleDescription,
                            beginLabelStyleDescription));
                }
            }
        }
        return beginLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BeginLabelStyleDescription basicGetBeginLabelStyleDescription() {
        return beginLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetBeginLabelStyleDescription(BeginLabelStyleDescription newBeginLabelStyleDescription, NotificationChain msgs) {
        BeginLabelStyleDescription oldBeginLabelStyleDescription = beginLabelStyleDescription;
        beginLabelStyleDescription = newBeginLabelStyleDescription;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION, oldBeginLabelStyleDescription,
                    newBeginLabelStyleDescription);
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
    public void setBeginLabelStyleDescription(BeginLabelStyleDescription newBeginLabelStyleDescription) {
        if (newBeginLabelStyleDescription != beginLabelStyleDescription) {
            NotificationChain msgs = null;
            if (beginLabelStyleDescription != null) {
                msgs = ((InternalEObject) beginLabelStyleDescription).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION,
                        null, msgs);
            }
            if (newBeginLabelStyleDescription != null) {
                msgs = ((InternalEObject) newBeginLabelStyleDescription).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION,
                        null, msgs);
            }
            msgs = basicSetBeginLabelStyleDescription(newBeginLabelStyleDescription, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION, newBeginLabelStyleDescription, newBeginLabelStyleDescription));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CenterLabelStyleDescription getCenterLabelStyleDescription() {
        if (centerLabelStyleDescription != null && centerLabelStyleDescription.eIsProxy()) {
            InternalEObject oldCenterLabelStyleDescription = (InternalEObject) centerLabelStyleDescription;
            centerLabelStyleDescription = (CenterLabelStyleDescription) eResolveProxy(oldCenterLabelStyleDescription);
            if (centerLabelStyleDescription != oldCenterLabelStyleDescription) {
                InternalEObject newCenterLabelStyleDescription = (InternalEObject) centerLabelStyleDescription;
                NotificationChain msgs = oldCenterLabelStyleDescription.eInverseRemove(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, null, null);
                if (newCenterLabelStyleDescription.eInternalContainer() == null) {
                    msgs = newCenterLabelStyleDescription.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, oldCenterLabelStyleDescription,
                            centerLabelStyleDescription));
                }
            }
        }
        return centerLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CenterLabelStyleDescription basicGetCenterLabelStyleDescription() {
        return centerLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCenterLabelStyleDescription(CenterLabelStyleDescription newCenterLabelStyleDescription, NotificationChain msgs) {
        CenterLabelStyleDescription oldCenterLabelStyleDescription = centerLabelStyleDescription;
        centerLabelStyleDescription = newCenterLabelStyleDescription;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, oldCenterLabelStyleDescription,
                    newCenterLabelStyleDescription);
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
    public void setCenterLabelStyleDescription(CenterLabelStyleDescription newCenterLabelStyleDescription) {
        if (newCenterLabelStyleDescription != centerLabelStyleDescription) {
            NotificationChain msgs = null;
            if (centerLabelStyleDescription != null) {
                msgs = ((InternalEObject) centerLabelStyleDescription).eInverseRemove(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, null, msgs);
            }
            if (newCenterLabelStyleDescription != null) {
                msgs = ((InternalEObject) newCenterLabelStyleDescription).eInverseAdd(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, null, msgs);
            }
            msgs = basicSetCenterLabelStyleDescription(newCenterLabelStyleDescription, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION, newCenterLabelStyleDescription, newCenterLabelStyleDescription));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EndLabelStyleDescription getEndLabelStyleDescription() {
        if (endLabelStyleDescription != null && endLabelStyleDescription.eIsProxy()) {
            InternalEObject oldEndLabelStyleDescription = (InternalEObject) endLabelStyleDescription;
            endLabelStyleDescription = (EndLabelStyleDescription) eResolveProxy(oldEndLabelStyleDescription);
            if (endLabelStyleDescription != oldEndLabelStyleDescription) {
                InternalEObject newEndLabelStyleDescription = (InternalEObject) endLabelStyleDescription;
                NotificationChain msgs = oldEndLabelStyleDescription.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION,
                        null, null);
                if (newEndLabelStyleDescription.eInternalContainer() == null) {
                    msgs = newEndLabelStyleDescription.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION, oldEndLabelStyleDescription, endLabelStyleDescription));
                }
            }
        }
        return endLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EndLabelStyleDescription basicGetEndLabelStyleDescription() {
        return endLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetEndLabelStyleDescription(EndLabelStyleDescription newEndLabelStyleDescription, NotificationChain msgs) {
        EndLabelStyleDescription oldEndLabelStyleDescription = endLabelStyleDescription;
        endLabelStyleDescription = newEndLabelStyleDescription;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION, oldEndLabelStyleDescription,
                    newEndLabelStyleDescription);
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
    public void setEndLabelStyleDescription(EndLabelStyleDescription newEndLabelStyleDescription) {
        if (newEndLabelStyleDescription != endLabelStyleDescription) {
            NotificationChain msgs = null;
            if (endLabelStyleDescription != null) {
                msgs = ((InternalEObject) endLabelStyleDescription).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION,
                        null, msgs);
            }
            if (newEndLabelStyleDescription != null) {
                msgs = ((InternalEObject) newEndLabelStyleDescription).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION,
                        null, msgs);
            }
            msgs = basicSetEndLabelStyleDescription(newEndLabelStyleDescription, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION, newEndLabelStyleDescription, newEndLabelStyleDescription));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CenteringStyle getEndsCentering() {
        return endsCentering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEndsCentering(CenteringStyle newEndsCentering) {
        CenteringStyle oldEndsCentering = endsCentering;
        endsCentering = newEndsCentering == null ? EdgeStyleDescriptionImpl.ENDS_CENTERING_EDEFAULT : newEndsCentering;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING, oldEndsCentering, endsCentering));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getCenteredSourceMappings() {
        if (centeredSourceMappings == null) {
            centeredSourceMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_SOURCE_MAPPINGS);
        }
        return centeredSourceMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getCenteredTargetMappings() {
        if (centeredTargetMappings == null) {
            centeredTargetMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_TARGET_MAPPINGS);
        }
        return centeredTargetMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION:
            return basicSetBeginLabelStyleDescription(null, msgs);
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION:
            return basicSetCenterLabelStyleDescription(null, msgs);
        case StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION:
            return basicSetEndLabelStyleDescription(null, msgs);
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
        case StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR:
            if (resolve) {
                return getStrokeColor();
            }
            return basicGetStrokeColor();
        case StylePackage.EDGE_STYLE_DESCRIPTION__LINE_STYLE:
            return getLineStyle();
        case StylePackage.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW:
            return getSourceArrow();
        case StylePackage.EDGE_STYLE_DESCRIPTION__TARGET_ARROW:
            return getTargetArrow();
        case StylePackage.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            return getSizeComputationExpression();
        case StylePackage.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE:
            return getRoutingStyle();
        case StylePackage.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE:
            return getFoldingStyle();
        case StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION:
            if (resolve) {
                return getBeginLabelStyleDescription();
            }
            return basicGetBeginLabelStyleDescription();
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION:
            if (resolve) {
                return getCenterLabelStyleDescription();
            }
            return basicGetCenterLabelStyleDescription();
        case StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION:
            if (resolve) {
                return getEndLabelStyleDescription();
            }
            return basicGetEndLabelStyleDescription();
        case StylePackage.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING:
            return getEndsCentering();
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_SOURCE_MAPPINGS:
            return getCenteredSourceMappings();
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_TARGET_MAPPINGS:
            return getCenteredTargetMappings();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR:
            setStrokeColor((ColorDescription) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__LINE_STYLE:
            setLineStyle((LineStyle) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW:
            setSourceArrow((EdgeArrows) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__TARGET_ARROW:
            setTargetArrow((EdgeArrows) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            setSizeComputationExpression((String) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE:
            setRoutingStyle((EdgeRouting) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE:
            setFoldingStyle((FoldingStyle) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION:
            setBeginLabelStyleDescription((BeginLabelStyleDescription) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION:
            setCenterLabelStyleDescription((CenterLabelStyleDescription) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION:
            setEndLabelStyleDescription((EndLabelStyleDescription) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING:
            setEndsCentering((CenteringStyle) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_SOURCE_MAPPINGS:
            getCenteredSourceMappings().clear();
            getCenteredSourceMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_TARGET_MAPPINGS:
            getCenteredTargetMappings().clear();
            getCenteredTargetMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
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
        case StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR:
            setStrokeColor((ColorDescription) null);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__LINE_STYLE:
            setLineStyle(EdgeStyleDescriptionImpl.LINE_STYLE_EDEFAULT);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW:
            setSourceArrow(EdgeStyleDescriptionImpl.SOURCE_ARROW_EDEFAULT);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__TARGET_ARROW:
            setTargetArrow(EdgeStyleDescriptionImpl.TARGET_ARROW_EDEFAULT);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            setSizeComputationExpression(EdgeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE:
            setRoutingStyle(EdgeStyleDescriptionImpl.ROUTING_STYLE_EDEFAULT);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE:
            setFoldingStyle(EdgeStyleDescriptionImpl.FOLDING_STYLE_EDEFAULT);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION:
            setBeginLabelStyleDescription((BeginLabelStyleDescription) null);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION:
            setCenterLabelStyleDescription((CenterLabelStyleDescription) null);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION:
            setEndLabelStyleDescription((EndLabelStyleDescription) null);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING:
            setEndsCentering(EdgeStyleDescriptionImpl.ENDS_CENTERING_EDEFAULT);
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_SOURCE_MAPPINGS:
            getCenteredSourceMappings().clear();
            return;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_TARGET_MAPPINGS:
            getCenteredTargetMappings().clear();
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
        case StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR:
            return strokeColor != null;
        case StylePackage.EDGE_STYLE_DESCRIPTION__LINE_STYLE:
            return lineStyle != EdgeStyleDescriptionImpl.LINE_STYLE_EDEFAULT;
        case StylePackage.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW:
            return sourceArrow != EdgeStyleDescriptionImpl.SOURCE_ARROW_EDEFAULT;
        case StylePackage.EDGE_STYLE_DESCRIPTION__TARGET_ARROW:
            return targetArrow != EdgeStyleDescriptionImpl.TARGET_ARROW_EDEFAULT;
        case StylePackage.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            return EdgeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? sizeComputationExpression != null
                    : !EdgeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(sizeComputationExpression);
        case StylePackage.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE:
            return routingStyle != EdgeStyleDescriptionImpl.ROUTING_STYLE_EDEFAULT;
        case StylePackage.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE:
            return foldingStyle != EdgeStyleDescriptionImpl.FOLDING_STYLE_EDEFAULT;
        case StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION:
            return beginLabelStyleDescription != null;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION:
            return centerLabelStyleDescription != null;
        case StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION:
            return endLabelStyleDescription != null;
        case StylePackage.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING:
            return endsCentering != EdgeStyleDescriptionImpl.ENDS_CENTERING_EDEFAULT;
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_SOURCE_MAPPINGS:
            return centeredSourceMappings != null && !centeredSourceMappings.isEmpty();
        case StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_TARGET_MAPPINGS:
            return centeredTargetMappings != null && !centeredTargetMappings.isEmpty();
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
        result.append(", sizeComputationExpression: "); //$NON-NLS-1$
        result.append(sizeComputationExpression);
        result.append(", routingStyle: "); //$NON-NLS-1$
        result.append(routingStyle);
        result.append(", foldingStyle: "); //$NON-NLS-1$
        result.append(foldingStyle);
        result.append(", endsCentering: "); //$NON-NLS-1$
        result.append(endsCentering);
        result.append(')');
        return result.toString();
    }

} // EdgeStyleDescriptionImpl
