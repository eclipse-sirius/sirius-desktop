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
import org.eclipse.sirius.properties.AbstractLabelDescription;
import org.eclipse.sirius.properties.AbstractWidgetDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetAction;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Label Description</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getLabelExpression <em>Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getHelpExpression <em>Help Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getIsEnabledExpression <em>Is Enabled
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getValueExpression <em>Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getDisplayExpression <em>Display Expression</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getConditionalStyles <em>Conditional Styles</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getFilterConditionalStylesFromExtendedLabelExpression
 * <em>Filter Conditional Styles From Extended Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl#getFilterActionsFromExtendedLabelExpression
 * <em>Filter Actions From Extended Label Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LabelDescriptionImpl extends WidgetDescriptionImpl implements LabelDescription {
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
    protected String labelExpression = LabelDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

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
    protected String helpExpression = LabelDescriptionImpl.HELP_EXPRESSION_EDEFAULT;

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
    protected String isEnabledExpression = LabelDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT;

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
    protected String valueExpression = LabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getDisplayExpression() <em>Display Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDisplayExpression()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayExpression() <em>Display Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDisplayExpression()
     * @generated
     * @ordered
     */
    protected String displayExpression = LabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected LabelWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<LabelWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getActions()
     * @generated
     * @ordered
     */
    protected EList<WidgetAction> actions;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected LabelDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedLabelExpression() <em>Filter Conditional
     * Styles From Extended Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedLabelExpression() <em>Filter Conditional
     * Styles From Extended Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedLabelExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedLabelExpression = LabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterActionsFromExtendedLabelExpression() <em>Filter Actions From Extended
     * Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromExtendedLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterActionsFromExtendedLabelExpression() <em>Filter Actions From Extended
     * Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromExtendedLabelExpression()
     * @generated
     * @ordered
     */
    protected String filterActionsFromExtendedLabelExpression = LabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT;

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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__HELP_EXPRESSION, oldHelpExpression, helpExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION, oldIsEnabledExpression, isEnabledExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDisplayExpression() {
        return displayExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDisplayExpression(String newDisplayExpression) {
        String oldDisplayExpression = displayExpression;
        displayExpression = newDisplayExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
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
    public EList<WidgetAction> getActions() {
        if (actions == null) {
            actions = new EObjectContainmentEList<WidgetAction>(WidgetAction.class, this, PropertiesPackage.LABEL_DESCRIPTION__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (LabelDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.LABEL_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public LabelDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(LabelDescription newExtends) {
        LabelDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedLabelExpression() {
        return filterConditionalStylesFromExtendedLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedLabelExpression(String newFilterConditionalStylesFromExtendedLabelExpression) {
        String oldFilterConditionalStylesFromExtendedLabelExpression = filterConditionalStylesFromExtendedLabelExpression;
        filterConditionalStylesFromExtendedLabelExpression = newFilterConditionalStylesFromExtendedLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedLabelExpression, filterConditionalStylesFromExtendedLabelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterActionsFromExtendedLabelExpression() {
        return filterActionsFromExtendedLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterActionsFromExtendedLabelExpression(String newFilterActionsFromExtendedLabelExpression) {
        String oldFilterActionsFromExtendedLabelExpression = filterActionsFromExtendedLabelExpression;
        filterActionsFromExtendedLabelExpression = newFilterActionsFromExtendedLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION, oldFilterActionsFromExtendedLabelExpression,
                    filterActionsFromExtendedLabelExpression));
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
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.LABEL_DESCRIPTION__ACTIONS:
            return ((InternalEList<?>) getActions()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.LABEL_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.LABEL_DESCRIPTION__HELP_EXPRESSION:
            return getHelpExpression();
        case PropertiesPackage.LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return getIsEnabledExpression();
        case PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.LABEL_DESCRIPTION__ACTIONS:
            return getActions();
        case PropertiesPackage.LABEL_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            return getFilterConditionalStylesFromExtendedLabelExpression();
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
            return getFilterActionsFromExtendedLabelExpression();
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
        case PropertiesPackage.LABEL_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            setStyle((LabelWidgetStyle) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends LabelWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends WidgetAction>) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__EXTENDS:
            setExtends((LabelDescription) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            setFilterConditionalStylesFromExtendedLabelExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
            setFilterActionsFromExtendedLabelExpression((String) newValue);
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
        case PropertiesPackage.LABEL_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(LabelDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression(LabelDescriptionImpl.HELP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression(LabelDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(LabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(LabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            setStyle((LabelWidgetStyle) null);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__ACTIONS:
            getActions().clear();
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__EXTENDS:
            setExtends((LabelDescription) null);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            setFilterConditionalStylesFromExtendedLabelExpression(LabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
            setFilterActionsFromExtendedLabelExpression(LabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.LABEL_DESCRIPTION__LABEL_EXPRESSION:
            return LabelDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !LabelDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.LABEL_DESCRIPTION__HELP_EXPRESSION:
            return LabelDescriptionImpl.HELP_EXPRESSION_EDEFAULT == null ? helpExpression != null : !LabelDescriptionImpl.HELP_EXPRESSION_EDEFAULT.equals(helpExpression);
        case PropertiesPackage.LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return LabelDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT == null ? isEnabledExpression != null : !LabelDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT.equals(isEnabledExpression);
        case PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION:
            return LabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !LabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            return LabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null : !LabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT.equals(displayExpression);
        case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.LABEL_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
        case PropertiesPackage.LABEL_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            return LabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedLabelExpression != null
                    : !LabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedLabelExpression);
        case PropertiesPackage.LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
            return LabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT == null ? filterActionsFromExtendedLabelExpression != null
                    : !LabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT.equals(filterActionsFromExtendedLabelExpression);
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
            case PropertiesPackage.LABEL_DESCRIPTION__LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.LABEL_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractLabelDescription.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION;
            case PropertiesPackage.LABEL_DESCRIPTION__STYLE:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE;
            case PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.LABEL_DESCRIPTION__ACTIONS:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS;
            case PropertiesPackage.LABEL_DESCRIPTION__EXTENDS:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS;
            case PropertiesPackage.LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION;
            case PropertiesPackage.LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION;
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
                return PropertiesPackage.LABEL_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.LABEL_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractLabelDescription.class) {
            switch (baseFeatureID) {
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
                return PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION;
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE:
                return PropertiesPackage.LABEL_DESCRIPTION__STYLE;
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS:
                return PropertiesPackage.LABEL_DESCRIPTION__ACTIONS;
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS:
                return PropertiesPackage.LABEL_DESCRIPTION__EXTENDS;
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
                return PropertiesPackage.LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION;
            case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
                return PropertiesPackage.LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION;
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
        result.append(", displayExpression: "); //$NON-NLS-1$
        result.append(displayExpression);
        result.append(", filterConditionalStylesFromExtendedLabelExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedLabelExpression);
        result.append(", filterActionsFromExtendedLabelExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromExtendedLabelExpression);
        result.append(')');
        return result.toString();
    }

} // LabelDescriptionImpl
