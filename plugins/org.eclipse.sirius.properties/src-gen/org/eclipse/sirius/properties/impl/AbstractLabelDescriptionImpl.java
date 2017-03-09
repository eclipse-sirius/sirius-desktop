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
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetAction;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Label Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getDisplayExpression <em>Display
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getFilterConditionalStylesFromExtendedLabelExpression
 * <em>Filter Conditional Styles From Extended Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl#getFilterActionsFromExtendedLabelExpression
 * <em>Filter Actions From Extended Label Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractLabelDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractLabelDescription {
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
    protected String valueExpression = AbstractLabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String displayExpression = AbstractLabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

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
    protected String filterConditionalStylesFromExtendedLabelExpression = AbstractLabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT;

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
    protected String filterActionsFromExtendedLabelExpression = AbstractLabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractLabelDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_LABEL_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE, oldStyle, newStyle);
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
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE, newStyle, newStyle));
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
            conditionalStyles = new EObjectContainmentEList<LabelWidgetConditionalStyle>(LabelWidgetConditionalStyle.class, this, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES);
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
            actions = new EObjectContainmentEList<WidgetAction>(WidgetAction.class, this, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS);
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION,
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION,
                    oldFilterActionsFromExtendedLabelExpression, filterActionsFromExtendedLabelExpression));
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
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS:
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
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS:
            return getActions();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            return getFilterConditionalStylesFromExtendedLabelExpression();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
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
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE:
            setStyle((LabelWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends LabelWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends WidgetAction>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS:
            setExtends((LabelDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            setFilterConditionalStylesFromExtendedLabelExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
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
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(AbstractLabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(AbstractLabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE:
            setStyle((LabelWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS:
            getActions().clear();
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS:
            setExtends((LabelDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            setFilterConditionalStylesFromExtendedLabelExpression(AbstractLabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
            setFilterActionsFromExtendedLabelExpression(AbstractLabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION:
            return AbstractLabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !AbstractLabelDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION:
            return AbstractLabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null : !AbstractLabelDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT.equals(displayExpression);
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION:
            return AbstractLabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedLabelExpression != null
                    : !AbstractLabelDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedLabelExpression);
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION:
            return AbstractLabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT == null ? filterActionsFromExtendedLabelExpression != null
                    : !AbstractLabelDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION_EDEFAULT.equals(filterActionsFromExtendedLabelExpression);
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
        result.append(", displayExpression: "); //$NON-NLS-1$
        result.append(displayExpression);
        result.append(", filterConditionalStylesFromExtendedLabelExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedLabelExpression);
        result.append(", filterActionsFromExtendedLabelExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromExtendedLabelExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractLabelDescriptionImpl
