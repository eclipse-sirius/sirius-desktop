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
import org.eclipse.sirius.properties.AbstractWidgetDescription;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>List Description</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getLabelExpression <em>Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getHelpExpression <em>Help Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getIsEnabledExpression <em>Is Enabled
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getValueExpression <em>Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getDisplayExpression <em>Display Expression</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getOnClickOperation <em>On Click Operation</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getConditionalStyles <em>Conditional Styles</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getFilterConditionalStylesFromExtendedListExpression
 * <em>Filter Conditional Styles From Extended List Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl#getFilterActionsFromExtendedListExpression
 * <em>Filter Actions From Extended List Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListDescriptionImpl extends WidgetDescriptionImpl implements ListDescription {
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
    protected String labelExpression = ListDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

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
    protected String helpExpression = ListDescriptionImpl.HELP_EXPRESSION_EDEFAULT;

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
    protected String isEnabledExpression = ListDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT;

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
    protected String valueExpression = ListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String displayExpression = ListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

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
    protected String filterConditionalStylesFromExtendedListExpression = ListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT;

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
    protected String filterActionsFromExtendedListExpression = ListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ListDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.LIST_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__HELP_EXPRESSION, oldHelpExpression, helpExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__IS_ENABLED_EXPRESSION, oldIsEnabledExpression, isEnabledExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION, oldOnClickOperation, newOnClickOperation);
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
                msgs = ((InternalEObject) onClickOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            if (newOnClickOperation != null) {
                msgs = ((InternalEObject) newOnClickOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            msgs = basicSetOnClickOperation(newOnClickOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION, newOnClickOperation, newOnClickOperation));
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
            actions = new EObjectContainmentEList<WidgetAction>(WidgetAction.class, this, PropertiesPackage.LIST_DESCRIPTION__ACTIONS);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__STYLE, oldStyle, newStyle);
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
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.LIST_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.LIST_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__STYLE, newStyle, newStyle));
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
            conditionalStyles = new EObjectContainmentEList<ListWidgetConditionalStyle>(ListWidgetConditionalStyle.class, this, PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES);
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.LIST_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION,
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION, oldFilterActionsFromExtendedListExpression,
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
        case PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION:
            return basicSetOnClickOperation(null, msgs);
        case PropertiesPackage.LIST_DESCRIPTION__ACTIONS:
            return ((InternalEList<?>) getActions()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.LIST_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.LIST_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.LIST_DESCRIPTION__HELP_EXPRESSION:
            return getHelpExpression();
        case PropertiesPackage.LIST_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return getIsEnabledExpression();
        case PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION:
            return getOnClickOperation();
        case PropertiesPackage.LIST_DESCRIPTION__ACTIONS:
            return getActions();
        case PropertiesPackage.LIST_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.LIST_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            return getFilterConditionalStylesFromExtendedListExpression();
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
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
        case PropertiesPackage.LIST_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression((String) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression((String) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends WidgetAction>) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__STYLE:
            setStyle((ListWidgetStyle) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ListWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__EXTENDS:
            setExtends((ListDescription) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            setFilterConditionalStylesFromExtendedListExpression((String) newValue);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
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
        case PropertiesPackage.LIST_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(ListDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression(ListDescriptionImpl.HELP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression(ListDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(ListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(ListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) null);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__ACTIONS:
            getActions().clear();
            return;
        case PropertiesPackage.LIST_DESCRIPTION__STYLE:
            setStyle((ListWidgetStyle) null);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.LIST_DESCRIPTION__EXTENDS:
            setExtends((ListDescription) null);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            setFilterConditionalStylesFromExtendedListExpression(ListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
            setFilterActionsFromExtendedListExpression(ListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.LIST_DESCRIPTION__LABEL_EXPRESSION:
            return ListDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !ListDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.LIST_DESCRIPTION__HELP_EXPRESSION:
            return ListDescriptionImpl.HELP_EXPRESSION_EDEFAULT == null ? helpExpression != null : !ListDescriptionImpl.HELP_EXPRESSION_EDEFAULT.equals(helpExpression);
        case PropertiesPackage.LIST_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return ListDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT == null ? isEnabledExpression != null : !ListDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT.equals(isEnabledExpression);
        case PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION:
            return ListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !ListDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION:
            return ListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null : !ListDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT.equals(displayExpression);
        case PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION:
            return onClickOperation != null;
        case PropertiesPackage.LIST_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
        case PropertiesPackage.LIST_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.LIST_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
            return ListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedListExpression != null
                    : !ListDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedListExpression);
        case PropertiesPackage.LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
            return ListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT == null ? filterActionsFromExtendedListExpression != null
                    : !ListDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION_EDEFAULT.equals(filterActionsFromExtendedListExpression);
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
            case PropertiesPackage.LIST_DESCRIPTION__LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.LIST_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.LIST_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractListDescription.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION;
            case PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION;
            case PropertiesPackage.LIST_DESCRIPTION__ACTIONS:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS;
            case PropertiesPackage.LIST_DESCRIPTION__STYLE:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE;
            case PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.LIST_DESCRIPTION__EXTENDS:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS;
            case PropertiesPackage.LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION;
            case PropertiesPackage.LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
                return PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION;
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
                return PropertiesPackage.LIST_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.LIST_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.LIST_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractListDescription.class) {
            switch (baseFeatureID) {
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION:
                return PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION:
                return PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS:
                return PropertiesPackage.LIST_DESCRIPTION__ACTIONS;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE:
                return PropertiesPackage.LIST_DESCRIPTION__STYLE;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS:
                return PropertiesPackage.LIST_DESCRIPTION__EXTENDS;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION:
                return PropertiesPackage.LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION;
            case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION:
                return PropertiesPackage.LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION;
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
        result.append(", filterConditionalStylesFromExtendedListExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedListExpression);
        result.append(", filterActionsFromExtendedListExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromExtendedListExpression);
        result.append(')');
        return result.toString();
    }

} // ListDescriptionImpl
