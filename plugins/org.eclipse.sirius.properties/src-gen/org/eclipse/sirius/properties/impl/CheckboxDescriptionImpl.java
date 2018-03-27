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
import org.eclipse.sirius.properties.AbstractWidgetDescription;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Checkbox Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getLabelExpression <em>Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getHelpExpression <em>Help
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getIsEnabledExpression <em>Is Enabled
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl#getFilterConditionalStylesFromExtendedCheckboxExpression
 * <em>Filter Conditional Styles From Extended Checkbox Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CheckboxDescriptionImpl extends WidgetDescriptionImpl implements CheckboxDescription {
    /**
     * The default value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = CheckboxDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getHelpExpression() <em>Help Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHelpExpression()
     * @generated
     * @ordered
     */
    protected static final String HELP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHelpExpression() <em>Help Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHelpExpression()
     * @generated
     * @ordered
     */
    protected String helpExpression = CheckboxDescriptionImpl.HELP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getIsEnabledExpression() <em>Is Enabled Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIsEnabledExpression()
     * @generated
     * @ordered
     */
    protected static final String IS_ENABLED_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIsEnabledExpression() <em>Is Enabled Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIsEnabledExpression()
     * @generated
     * @ordered
     */
    protected String isEnabledExpression = CheckboxDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT;

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
    protected String valueExpression = CheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String filterConditionalStylesFromExtendedCheckboxExpression = CheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CheckboxDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CHECKBOX_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getHelpExpression() {
        return helpExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHelpExpression(String newHelpExpression) {
        String oldHelpExpression = helpExpression;
        helpExpression = newHelpExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__HELP_EXPRESSION, oldHelpExpression, helpExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIsEnabledExpression() {
        return isEnabledExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIsEnabledExpression(String newIsEnabledExpression) {
        String oldIsEnabledExpression = isEnabledExpression;
        isEnabledExpression = newIsEnabledExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION, oldIsEnabledExpression, isEnabledExpression));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE, oldStyle, newStyle);
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
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE, newStyle, newStyle));
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
            conditionalStyles = new EObjectContainmentEList<CheckboxWidgetConditionalStyle>(CheckboxWidgetConditionalStyle.class, this, PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES);
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION,
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
        case PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.CHECKBOX_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__HELP_EXPRESSION:
            return getHelpExpression();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return getIsEnabledExpression();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
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
        case PropertiesPackage.CHECKBOX_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression((String) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression((String) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE:
            setStyle((CheckboxWidgetStyle) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends CheckboxWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS:
            setExtends((CheckboxDescription) newValue);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
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
        case PropertiesPackage.CHECKBOX_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(CheckboxDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression(CheckboxDescriptionImpl.HELP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression(CheckboxDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(CheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE:
            setStyle((CheckboxWidgetStyle) null);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS:
            setExtends((CheckboxDescription) null);
            return;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
            setFilterConditionalStylesFromExtendedCheckboxExpression(CheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.CHECKBOX_DESCRIPTION__LABEL_EXPRESSION:
            return CheckboxDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !CheckboxDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.CHECKBOX_DESCRIPTION__HELP_EXPRESSION:
            return CheckboxDescriptionImpl.HELP_EXPRESSION_EDEFAULT == null ? helpExpression != null : !CheckboxDescriptionImpl.HELP_EXPRESSION_EDEFAULT.equals(helpExpression);
        case PropertiesPackage.CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return CheckboxDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT == null ? isEnabledExpression != null : !CheckboxDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT.equals(isEnabledExpression);
        case PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
            return CheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !CheckboxDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
            return CheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedCheckboxExpression != null
                    : !CheckboxDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedCheckboxExpression);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractWidgetDescription.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.CHECKBOX_DESCRIPTION__LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.CHECKBOX_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractCheckboxDescription.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
                return PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION;
            case PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE:
                return PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE;
            case PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS:
                return PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS;
            case PropertiesPackage.CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
                return PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractWidgetDescription.class) {
            switch (baseFeatureID) {
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractCheckboxDescription.class) {
            switch (baseFeatureID) {
            case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION;
            case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE;
            case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__EXTENDS;
            case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION:
                return PropertiesPackage.CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(", helpExpression: "); //$NON-NLS-1$
        result.append(helpExpression);
        result.append(", isEnabledExpression: "); //$NON-NLS-1$
        result.append(isEnabledExpression);
        result.append(", valueExpression: "); //$NON-NLS-1$
        result.append(valueExpression);
        result.append(", filterConditionalStylesFromExtendedCheckboxExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedCheckboxExpression);
        result.append(')');
        return result.toString();
    }

} // CheckboxDescriptionImpl
