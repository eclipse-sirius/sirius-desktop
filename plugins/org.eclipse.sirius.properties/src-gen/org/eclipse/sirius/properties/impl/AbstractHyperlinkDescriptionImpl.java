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
import org.eclipse.sirius.properties.AbstractHyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Hyperlink Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getDisplayExpression <em>Display
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getFilterConditionalStylesFromExtendedHyperlinkExpression
 * <em>Filter Conditional Styles From Extended Hyperlink Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl#getFilterActionsFromExtendedHyperlinkExpression
 * <em>Filter Actions From Extended Hyperlink Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractHyperlinkDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractHyperlinkDescription {
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
    protected String valueExpression = AbstractHyperlinkDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String displayExpression = AbstractHyperlinkDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

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
    protected HyperlinkWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<HyperlinkWidgetConditionalStyle> conditionalStyles;

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
    protected HyperlinkDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedHyperlinkExpression() <em>Filter
     * Conditional Styles From Extended Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromExtendedHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedHyperlinkExpression() <em>Filter
     * Conditional Styles From Extended Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromExtendedHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedHyperlinkExpression = AbstractHyperlinkDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterActionsFromExtendedHyperlinkExpression() <em>Filter Actions From
     * Extended Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromExtendedHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterActionsFromExtendedHyperlinkExpression() <em>Filter Actions From
     * Extended Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromExtendedHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected String filterActionsFromExtendedHyperlinkExpression = AbstractHyperlinkDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractHyperlinkDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_HYPERLINK_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation,
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HyperlinkWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(HyperlinkWidgetStyle newStyle, NotificationChain msgs) {
        HyperlinkWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(HyperlinkWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<HyperlinkWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<HyperlinkWidgetConditionalStyle>(HyperlinkWidgetConditionalStyle.class, this,
                    PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES);
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
            actions = new EObjectContainmentEList<WidgetAction>(WidgetAction.class, this, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HyperlinkDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (HyperlinkDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public HyperlinkDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(HyperlinkDescription newExtends) {
        HyperlinkDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedHyperlinkExpression() {
        return filterConditionalStylesFromExtendedHyperlinkExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedHyperlinkExpression(String newFilterConditionalStylesFromExtendedHyperlinkExpression) {
        String oldFilterConditionalStylesFromExtendedHyperlinkExpression = filterConditionalStylesFromExtendedHyperlinkExpression;
        filterConditionalStylesFromExtendedHyperlinkExpression = newFilterConditionalStylesFromExtendedHyperlinkExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedHyperlinkExpression, filterConditionalStylesFromExtendedHyperlinkExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterActionsFromExtendedHyperlinkExpression() {
        return filterActionsFromExtendedHyperlinkExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterActionsFromExtendedHyperlinkExpression(String newFilterActionsFromExtendedHyperlinkExpression) {
        String oldFilterActionsFromExtendedHyperlinkExpression = filterActionsFromExtendedHyperlinkExpression;
        filterActionsFromExtendedHyperlinkExpression = newFilterActionsFromExtendedHyperlinkExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION,
                    oldFilterActionsFromExtendedHyperlinkExpression, filterActionsFromExtendedHyperlinkExpression));
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
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS:
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
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS:
            return getActions();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            return getFilterConditionalStylesFromExtendedHyperlinkExpression();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            return getFilterActionsFromExtendedHyperlinkExpression();
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
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE:
            setStyle((HyperlinkWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends HyperlinkWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends WidgetAction>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS:
            setExtends((HyperlinkDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            setFilterConditionalStylesFromExtendedHyperlinkExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            setFilterActionsFromExtendedHyperlinkExpression((String) newValue);
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
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(AbstractHyperlinkDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(AbstractHyperlinkDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE:
            setStyle((HyperlinkWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS:
            getActions().clear();
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS:
            setExtends((HyperlinkDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            setFilterConditionalStylesFromExtendedHyperlinkExpression(AbstractHyperlinkDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            setFilterActionsFromExtendedHyperlinkExpression(AbstractHyperlinkDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION:
            return AbstractHyperlinkDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !AbstractHyperlinkDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION:
            return AbstractHyperlinkDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null
                    : !AbstractHyperlinkDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT.equals(displayExpression);
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            return AbstractHyperlinkDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedHyperlinkExpression != null
                    : !AbstractHyperlinkDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedHyperlinkExpression);
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION:
            return AbstractHyperlinkDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT == null ? filterActionsFromExtendedHyperlinkExpression != null
                    : !AbstractHyperlinkDescriptionImpl.FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION_EDEFAULT.equals(filterActionsFromExtendedHyperlinkExpression);
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
        result.append(", filterConditionalStylesFromExtendedHyperlinkExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedHyperlinkExpression);
        result.append(", filterActionsFromExtendedHyperlinkExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromExtendedHyperlinkExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractHyperlinkDescriptionImpl
