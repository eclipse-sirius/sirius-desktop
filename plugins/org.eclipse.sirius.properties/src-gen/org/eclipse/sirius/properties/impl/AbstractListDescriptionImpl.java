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
import org.eclipse.sirius.properties.AbstractListDescription;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract List Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getDisplayExpression <em>Display
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getOnClickOperation <em>On Click
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getFilterConditionalStylesFromExtendedListExpression
 * <em>Filter Conditional Styles From Extended List Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl#getFilterActionsFromExtendedListExpression
 * <em>Filter Actions From Extended List Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractListDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractListDescription {
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
    protected String valueExpression = AbstractListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String displayExpression = AbstractListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

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
     * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getActions()
     * @generated
     * @ordered
     */
    protected EList<WidgetAction> actions;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected ListWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<ListWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected ListDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedListExpression() <em>Filter Conditional
     * Styles From Extended List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedListExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedListExpression() <em>Filter Conditional
     * Styles From Extended List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedListExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedListExpression = AbstractListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterActionsFromExtendedListExpression() <em>Filter Actions From Extended
     * List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromExtendedListExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterActionsFromExtendedListExpression() <em>Filter Actions From Extended
     * List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromExtendedListExpression()
     * @generated
     * @ordered
     */
    protected String filterActionsFromExtendedListExpression = AbstractListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractListDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_LIST_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION, oldOnClickOperation, newOnClickOperation);
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
                msgs = ((InternalEObject) onClickOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            if (newOnClickOperation != null) {
                msgs = ((InternalEObject) newOnClickOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            msgs = basicSetOnClickOperation(newOnClickOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION, newOnClickOperation, newOnClickOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<WidgetAction> getActions() {
        if (actions == null) {
            actions = new EObjectContainmentEList<WidgetAction>(WidgetAction.class, this, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(ListWidgetStyle newStyle, NotificationChain msgs) {
        ListWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(ListWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ListWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<ListWidgetConditionalStyle>(ListWidgetConditionalStyle.class, this, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (ListDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public ListDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(ListDescription newExtends) {
        ListDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedListExpression() {
        return filterConditionalStylesFromExtendedListExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedListExpression(String newFilterConditionalStylesFromExtendedListExpression) {
        String oldFilterConditionalStylesFromExtendedListExpression = filterConditionalStylesFromExtendedListExpression;
        filterConditionalStylesFromExtendedListExpression = newFilterConditionalStylesFromExtendedListExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedListExpression, filterConditionalStylesFromExtendedListExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterActionsFromExtendedListExpression() {
        return filterActionsFromExtendedListExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterActionsFromExtendedListExpression(String newFilterActionsFromExtendedListExpression) {
        String oldFilterActionsFromExtendedListExpression = filterActionsFromExtendedListExpression;
        filterActionsFromExtendedListExpression = newFilterActionsFromExtendedListExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION, oldFilterActionsFromExtendedListExpression,
                    filterActionsFromExtendedListExpression));
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
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION:
            return basicSetOnClickOperation(null, msgs);
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS:
            return ((InternalEList<?>) getActions()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION:
            return getOnClickOperation();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS:
            return getActions();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            return getFilterConditionalStylesFromExtendedListExpression();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
            return getFilterActionsFromExtendedListExpression();
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
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends WidgetAction>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE:
            setStyle((ListWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ListWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS:
            setExtends((ListDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            setFilterConditionalStylesFromExtendedListExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
            setFilterActionsFromExtendedListExpression((String) newValue);
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
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(AbstractListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(AbstractListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) null);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS:
            getActions().clear();
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE:
            setStyle((ListWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS:
            setExtends((ListDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            setFilterConditionalStylesFromExtendedListExpression(AbstractListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
            setFilterActionsFromExtendedListExpression(AbstractListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION:
            return AbstractListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !AbstractListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            return AbstractListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null : !AbstractListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT.equals(displayExpression);
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION:
            return onClickOperation != null;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            return AbstractListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedListExpression != null
                    : !AbstractListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedListExpression);
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
            return AbstractListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT == null ? filterActionsFromExtendedListExpression != null
                    : !AbstractListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT.equals(filterActionsFromExtendedListExpression);
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
        result.append(", filterConditionalStylesFromExtendedListExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedListExpression);
        result.append(", filterActionsFromExtendedListExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromExtendedListExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractListDescriptionImpl
