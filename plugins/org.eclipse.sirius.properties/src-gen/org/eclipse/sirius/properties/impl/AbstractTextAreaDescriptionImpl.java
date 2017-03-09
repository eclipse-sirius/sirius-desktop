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
import org.eclipse.sirius.properties.AbstractTextAreaDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextWidgetConditionalStyle;
import org.eclipse.sirius.properties.TextWidgetStyle;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Text Area Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl#getLineCount <em>Line Count</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl#getFilterConditionalStylesFromExtendedTextAreaExpression
 * <em>Filter Conditional Styles From Extended Text Area Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractTextAreaDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractTextAreaDescription {
    /**
     * The default value of the '{@link #getLineCount() <em>Line Count</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLineCount()
     * @generated
     * @ordered
     */
    protected static final int LINE_COUNT_EDEFAULT = 5;

    /**
     * The cached value of the '{@link #getLineCount() <em>Line Count</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLineCount()
     * @generated
     * @ordered
     */
    protected int lineCount = AbstractTextAreaDescriptionImpl.LINE_COUNT_EDEFAULT;

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
    protected String valueExpression = AbstractTextAreaDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected TextWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<TextWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected TextAreaDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedTextAreaExpression() <em>Filter
     * Conditional Styles From Extended Text Area Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromExtendedTextAreaExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedTextAreaExpression() <em>Filter
     * Conditional Styles From Extended Text Area Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromExtendedTextAreaExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedTextAreaExpression = AbstractTextAreaDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractTextAreaDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_TEXT_AREA_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getLineCount() {
        return lineCount;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLineCount(int newLineCount) {
        int oldLineCount = lineCount;
        lineCount = newLineCount;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT, oldLineCount, lineCount));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation,
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(TextWidgetStyle newStyle, NotificationChain msgs) {
        TextWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(TextWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<TextWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<TextWidgetConditionalStyle>(TextWidgetConditionalStyle.class, this, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextAreaDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (TextAreaDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public TextAreaDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(TextAreaDescription newExtends) {
        TextAreaDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedTextAreaExpression() {
        return filterConditionalStylesFromExtendedTextAreaExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedTextAreaExpression(String newFilterConditionalStylesFromExtendedTextAreaExpression) {
        String oldFilterConditionalStylesFromExtendedTextAreaExpression = filterConditionalStylesFromExtendedTextAreaExpression;
        filterConditionalStylesFromExtendedTextAreaExpression = newFilterConditionalStylesFromExtendedTextAreaExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedTextAreaExpression, filterConditionalStylesFromExtendedTextAreaExpression));
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
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT:
            return getLineCount();
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION:
            return getFilterConditionalStylesFromExtendedTextAreaExpression();
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
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT:
            setLineCount((Integer) newValue);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE:
            setStyle((TextWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends TextWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS:
            setExtends((TextAreaDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION:
            setFilterConditionalStylesFromExtendedTextAreaExpression((String) newValue);
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
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT:
            setLineCount(AbstractTextAreaDescriptionImpl.LINE_COUNT_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(AbstractTextAreaDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE:
            setStyle((TextWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS:
            setExtends((TextAreaDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION:
            setFilterConditionalStylesFromExtendedTextAreaExpression(AbstractTextAreaDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT:
            return lineCount != AbstractTextAreaDescriptionImpl.LINE_COUNT_EDEFAULT;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION:
            return AbstractTextAreaDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !AbstractTextAreaDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION:
            return AbstractTextAreaDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedTextAreaExpression != null
                    : !AbstractTextAreaDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedTextAreaExpression);
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
        result.append(" (lineCount: "); //$NON-NLS-1$
        result.append(lineCount);
        result.append(", valueExpression: "); //$NON-NLS-1$
        result.append(valueExpression);
        result.append(", filterConditionalStylesFromExtendedTextAreaExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedTextAreaExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractTextAreaDescriptionImpl
