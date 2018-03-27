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
import org.eclipse.sirius.properties.AbstractCheckboxDescription;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Checkbox Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl#getFilterConditionalStylesFromExtendedCheckboxExpression
 * <em>Filter Conditional Styles From Extended Checkbox Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractCheckboxDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractCheckboxDescription {
    /**
     * The default value of the '{@link #getValueExpression() <em>Value Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValueExpression() <em>Value Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected String valueExpression = AbstractCheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getInitialOperation() <em>Initial Operation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation initialOperation;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected CheckboxWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<CheckboxWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected CheckboxDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedCheckboxExpression() <em>Filter
     * Conditional Styles From Extended Checkbox Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromExtendedCheckboxExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedCheckboxExpression() <em>Filter
     * Conditional Styles From Extended Checkbox Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromExtendedCheckboxExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedCheckboxExpression = AbstractCheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractCheckboxDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_CHECKBOX_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getValueExpression() {
        return valueExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setValueExpression(String newValueExpression) {
        String oldValueExpression = valueExpression;
        valueExpression = newValueExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialOperation newInitialOperation, NotificationChain msgs) {
        InitialOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation,
                    newInitialOperation);
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
    public void setInitialOperation(InitialOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CheckboxWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(CheckboxWidgetStyle newStyle, NotificationChain msgs) {
        CheckboxWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(CheckboxWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CheckboxWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<CheckboxWidgetConditionalStyle>(CheckboxWidgetConditionalStyle.class, this,
                    PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CheckboxDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (CheckboxDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public CheckboxDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(CheckboxDescription newExtends) {
        CheckboxDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedCheckboxExpression() {
        return filterConditionalStylesFromExtendedCheckboxExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedCheckboxExpression(String newFilterConditionalStylesFromExtendedCheckboxExpression) {
        String oldFilterConditionalStylesFromExtendedCheckboxExpression = filterConditionalStylesFromExtendedCheckboxExpression;
        filterConditionalStylesFromExtendedCheckboxExpression = newFilterConditionalStylesFromExtendedCheckboxExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedCheckboxExpression, filterConditionalStylesFromExtendedCheckboxExpression));
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
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
            return getFilterConditionalStylesFromExtendedCheckboxExpression();
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
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE:
            setStyle((CheckboxWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends CheckboxWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS:
            setExtends((CheckboxDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
            setFilterConditionalStylesFromExtendedCheckboxExpression((String) newValue);
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
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(AbstractCheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE:
            setStyle((CheckboxWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS:
            setExtends((CheckboxDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
            setFilterConditionalStylesFromExtendedCheckboxExpression(AbstractCheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            return AbstractCheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !AbstractCheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
            return AbstractCheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedCheckboxExpression != null
                    : !AbstractCheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedCheckboxExpression);
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
        result.append(" (valueExpression: "); //$NON-NLS-1$
        result.append(valueExpression);
        result.append(", filterConditionalStylesFromExtendedCheckboxExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedCheckboxExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractCheckboxDescriptionImpl
