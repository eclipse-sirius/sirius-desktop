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
import org.eclipse.sirius.properties.AbstractCustomDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.CustomWidgetConditionalStyle;
import org.eclipse.sirius.properties.CustomWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Custom Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl#getCustomExpressions <em>Custom
 * Expressions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl#getCustomOperations <em>Custom
 * Operations</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl#getFilterConditionalStylesFromExtendedCustomExpression
 * <em>Filter Conditional Styles From Extended Custom Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractCustomDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractCustomDescription {
    /**
     * The cached value of the '{@link #getCustomExpressions() <em>Custom Expressions</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCustomExpressions()
     * @generated
     * @ordered
     */
    protected EList<CustomExpression> customExpressions;

    /**
     * The cached value of the '{@link #getCustomOperations() <em>Custom Operations</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCustomOperations()
     * @generated
     * @ordered
     */
    protected EList<CustomOperation> customOperations;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected CustomWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<CustomWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected CustomDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedCustomExpression() <em>Filter Conditional
     * Styles From Extended Custom Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedCustomExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedCustomExpression() <em>Filter Conditional
     * Styles From Extended Custom Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedCustomExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedCustomExpression = AbstractCustomDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractCustomDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_CUSTOM_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CustomExpression> getCustomExpressions() {
        if (customExpressions == null) {
            customExpressions = new EObjectContainmentEList<CustomExpression>(CustomExpression.class, this, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS);
        }
        return customExpressions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CustomOperation> getCustomOperations() {
        if (customOperations == null) {
            customOperations = new EObjectContainmentEList<CustomOperation>(CustomOperation.class, this, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS);
        }
        return customOperations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(CustomWidgetStyle newStyle, NotificationChain msgs) {
        CustomWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(CustomWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CustomWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<CustomWidgetConditionalStyle>(CustomWidgetConditionalStyle.class, this, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (CustomDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public CustomDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(CustomDescription newExtends) {
        CustomDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedCustomExpression() {
        return filterConditionalStylesFromExtendedCustomExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedCustomExpression(String newFilterConditionalStylesFromExtendedCustomExpression) {
        String oldFilterConditionalStylesFromExtendedCustomExpression = filterConditionalStylesFromExtendedCustomExpression;
        filterConditionalStylesFromExtendedCustomExpression = newFilterConditionalStylesFromExtendedCustomExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedCustomExpression, filterConditionalStylesFromExtendedCustomExpression));
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
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            return ((InternalEList<?>) getCustomExpressions()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            return ((InternalEList<?>) getCustomOperations()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            return getCustomExpressions();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            return getCustomOperations();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION:
            return getFilterConditionalStylesFromExtendedCustomExpression();
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
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            getCustomExpressions().clear();
            getCustomExpressions().addAll((Collection<? extends CustomExpression>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            getCustomOperations().clear();
            getCustomOperations().addAll((Collection<? extends CustomOperation>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE:
            setStyle((CustomWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends CustomWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS:
            setExtends((CustomDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION:
            setFilterConditionalStylesFromExtendedCustomExpression((String) newValue);
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
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            getCustomExpressions().clear();
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            getCustomOperations().clear();
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE:
            setStyle((CustomWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS:
            setExtends((CustomDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION:
            setFilterConditionalStylesFromExtendedCustomExpression(AbstractCustomDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS:
            return customExpressions != null && !customExpressions.isEmpty();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS:
            return customOperations != null && !customOperations.isEmpty();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION:
            return AbstractCustomDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedCustomExpression != null
                    : !AbstractCustomDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedCustomExpression);
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
        result.append(" (filterConditionalStylesFromExtendedCustomExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedCustomExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractCustomDescriptionImpl
