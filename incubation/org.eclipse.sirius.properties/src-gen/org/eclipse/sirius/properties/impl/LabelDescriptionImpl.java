/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Label Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getBodyExpression
 * <em>Body Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LabelDescriptionImpl extends WidgetDescriptionImpl implements LabelDescription {
    /**
     * The default value of the '{@link #getBodyExpression()
     * <em>Body Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBodyExpression()
     * @generated
     * @ordered
     */
    protected static final String BODY_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBodyExpression()
     * <em>Body Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBodyExpression()
     * @generated
     * @ordered
     */
    protected String bodyExpression = LabelDescriptionImpl.BODY_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected LabelWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles()
     * <em>Conditional Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<LabelWidgetConditionalStyle> conditionalStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LabelDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.LABEL_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getBodyExpression() {
        return bodyExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBodyExpression(String newBodyExpression) {
        String oldBodyExpression = bodyExpression;
        bodyExpression = newBodyExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__BODY_EXPRESSION, oldBodyExpression, bodyExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LabelWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetStyle(LabelWidgetStyle newStyle, NotificationChain msgs) {
        LabelWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(LabelWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.LABEL_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.LABEL_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LabelWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<LabelWidgetConditionalStyle>(LabelWidgetConditionalStyle.class, this, PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.LABEL_DESCRIPTION__BODY_EXPRESSION:
            return getBodyExpression();
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
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
        case PropertiesPackage.LABEL_DESCRIPTION__BODY_EXPRESSION:
            setBodyExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            setStyle((LabelWidgetStyle) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends LabelWidgetConditionalStyle>) newValue);
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
        case PropertiesPackage.LABEL_DESCRIPTION__BODY_EXPRESSION:
            setBodyExpression(LabelDescriptionImpl.BODY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            setStyle((LabelWidgetStyle) null);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
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
        case PropertiesPackage.LABEL_DESCRIPTION__BODY_EXPRESSION:
            return LabelDescriptionImpl.BODY_EXPRESSION_EDEFAULT == null ? bodyExpression != null : !LabelDescriptionImpl.BODY_EXPRESSION_EDEFAULT.equals(bodyExpression);
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
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
        result.append(" (bodyExpression: "); //$NON-NLS-1$
        result.append(bodyExpression);
        result.append(')');
        return result.toString();
    }

} // LabelDescriptionImpl
