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
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;
import org.eclipse.sirius.properties.impl.WidgetDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Ext
 * Reference Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl#getReferenceNameExpression
 * <em>Reference Name Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl#getReferenceOwnerExpression
 * <em>Reference Owner Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl#getStyle
 * <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtReferenceDescriptionImpl extends WidgetDescriptionImpl implements ExtReferenceDescription {
    /**
     * The default value of the '{@link #getReferenceNameExpression()
     * <em>Reference Name Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getReferenceNameExpression()
     * @generated
     * @ordered
     */
    protected static final String REFERENCE_NAME_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReferenceNameExpression()
     * <em>Reference Name Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getReferenceNameExpression()
     * @generated
     * @ordered
     */
    protected String referenceNameExpression = ExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getReferenceOwnerExpression()
     * <em>Reference Owner Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getReferenceOwnerExpression()
     * @generated
     * @ordered
     */
    protected static final String REFERENCE_OWNER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReferenceOwnerExpression()
     * <em>Reference Owner Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getReferenceOwnerExpression()
     * @generated
     * @ordered
     */
    protected String referenceOwnerExpression = ExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected ExtReferenceWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional
     * Styles</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<ExtReferenceWidgetConditionalStyle> conditionalStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ExtReferenceDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesExtWidgetsReferencePackage.Literals.EXT_REFERENCE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getReferenceNameExpression() {
        return referenceNameExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setReferenceNameExpression(String newReferenceNameExpression) {
        String oldReferenceNameExpression = referenceNameExpression;
        referenceNameExpression = newReferenceNameExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION, oldReferenceNameExpression,
                    referenceNameExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getReferenceOwnerExpression() {
        return referenceOwnerExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setReferenceOwnerExpression(String newReferenceOwnerExpression) {
        String oldReferenceOwnerExpression = referenceOwnerExpression;
        referenceOwnerExpression = newReferenceOwnerExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION, oldReferenceOwnerExpression,
                    referenceOwnerExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExtReferenceWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(ExtReferenceWidgetStyle newStyle, NotificationChain msgs) {
        ExtReferenceWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(ExtReferenceWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ExtReferenceWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<ExtReferenceWidgetConditionalStyle>(ExtReferenceWidgetConditionalStyle.class, this,
                    PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES);
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            return getReferenceNameExpression();
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            return getReferenceOwnerExpression();
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            setReferenceNameExpression((String) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            setReferenceOwnerExpression((String) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE:
            setStyle((ExtReferenceWidgetStyle) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ExtReferenceWidgetConditionalStyle>) newValue);
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            setReferenceNameExpression(ExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            setReferenceOwnerExpression(ExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE:
            setStyle((ExtReferenceWidgetStyle) null);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            return ExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT == null ? referenceNameExpression != null
                    : !ExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT.equals(referenceNameExpression);
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            return ExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT == null ? referenceOwnerExpression != null
                    : !ExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT.equals(referenceOwnerExpression);
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
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
        result.append(" (referenceNameExpression: "); //$NON-NLS-1$
        result.append(referenceNameExpression);
        result.append(", referenceOwnerExpression: "); //$NON-NLS-1$
        result.append(referenceOwnerExpression);
        result.append(')');
        return result.toString();
    }

} // ExtReferenceDescriptionImpl
