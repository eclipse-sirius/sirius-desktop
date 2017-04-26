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
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;
import org.eclipse.sirius.properties.impl.AbstractWidgetDescriptionImpl;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Ext Reference Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl#getReferenceNameExpression
 * <em>Reference Name Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl#getReferenceOwnerExpression
 * <em>Reference Owner Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl#getOnClickOperation
 * <em>On Click Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl#getStyle
 * <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl#getExtends
 * <em>Extends</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractExtReferenceDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractExtReferenceDescription {
    /**
     * The default value of the '{@link #getReferenceNameExpression() <em>Reference Name Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReferenceNameExpression()
     * @generated
     * @ordered
     */
    protected static final String REFERENCE_NAME_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReferenceNameExpression() <em>Reference Name Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReferenceNameExpression()
     * @generated
     * @ordered
     */
    protected String referenceNameExpression = AbstractExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getReferenceOwnerExpression() <em>Reference Owner Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReferenceOwnerExpression()
     * @generated
     * @ordered
     */
    protected static final String REFERENCE_OWNER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReferenceOwnerExpression() <em>Reference Owner Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReferenceOwnerExpression()
     * @generated
     * @ordered
     */
    protected String referenceOwnerExpression = AbstractExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getOnClickOperation() <em>On Click Operation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOnClickOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation onClickOperation;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected ExtReferenceWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<ExtReferenceWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected ExtReferenceDescription extends_;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractExtReferenceDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesExtWidgetsReferencePackage.Literals.ABSTRACT_EXT_REFERENCE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION, oldReferenceNameExpression,
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION, oldReferenceOwnerExpression,
                    referenceOwnerExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getOnClickOperation() {
        return onClickOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOnClickOperation(InitialOperation newOnClickOperation, NotificationChain msgs) {
        InitialOperation oldOnClickOperation = onClickOperation;
        onClickOperation = newOnClickOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION,
                    oldOnClickOperation, newOnClickOperation);
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
    public void setOnClickOperation(InitialOperation newOnClickOperation) {
        if (newOnClickOperation != onClickOperation) {
            NotificationChain msgs = null;
            if (onClickOperation != null) {
                msgs = ((InternalEObject) onClickOperation).eInverseRemove(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            if (newOnClickOperation != null) {
                msgs = ((InternalEObject) newOnClickOperation).eInverseAdd(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            msgs = basicSetOnClickOperation(newOnClickOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, newOnClickOperation,
                    newOnClickOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE, oldStyle, newStyle);
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
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE, null,
                        msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE, null,
                        msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE, newStyle, newStyle));
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
                    PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExtReferenceDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (ExtReferenceDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS, oldExtends, extends_));
                }
            }
        }
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ExtReferenceDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(ExtReferenceDescription newExtends) {
        ExtReferenceDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return basicSetOnClickOperation(null, msgs);
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            return getReferenceNameExpression();
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            return getReferenceOwnerExpression();
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return getOnClickOperation();
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
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
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            setReferenceNameExpression((String) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            setReferenceOwnerExpression((String) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE:
            setStyle((ExtReferenceWidgetStyle) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ExtReferenceWidgetConditionalStyle>) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS:
            setExtends((ExtReferenceDescription) newValue);
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
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            setReferenceNameExpression(AbstractExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            setReferenceOwnerExpression(AbstractExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) null);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE:
            setStyle((ExtReferenceWidgetStyle) null);
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS:
            setExtends((ExtReferenceDescription) null);
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
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION:
            return AbstractExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT == null ? referenceNameExpression != null
                    : !AbstractExtReferenceDescriptionImpl.REFERENCE_NAME_EXPRESSION_EDEFAULT.equals(referenceNameExpression);
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION:
            return AbstractExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT == null ? referenceOwnerExpression != null
                    : !AbstractExtReferenceDescriptionImpl.REFERENCE_OWNER_EXPRESSION_EDEFAULT.equals(referenceOwnerExpression);
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return onClickOperation != null;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS:
            return extends_ != null;
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

} // AbstractExtReferenceDescriptionImpl
