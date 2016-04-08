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
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ReferenceDescription;
import org.eclipse.sirius.properties.ReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ReferenceWidgetStyle;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Reference Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ReferenceDescriptionImpl#isMultiple
 * <em>Multiple</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ReferenceDescriptionImpl#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ReferenceDescriptionImpl#getDisplayExpression
 * <em>Display Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ReferenceDescriptionImpl#getOnClickOperation
 * <em>On Click Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ReferenceDescriptionImpl#getActions
 * <em>Actions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ReferenceDescriptionImpl#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ReferenceDescriptionImpl#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceDescriptionImpl extends WidgetDescriptionImpl implements ReferenceDescription {
    /**
     * The default value of the '{@link #isMultiple() <em>Multiple</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isMultiple()
     * @generated
     * @ordered
     */
    protected static final boolean MULTIPLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMultiple() <em>Multiple</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isMultiple()
     * @generated
     * @ordered
     */
    protected boolean multiple = ReferenceDescriptionImpl.MULTIPLE_EDEFAULT;

    /**
     * The default value of the '{@link #getValueExpression()
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValueExpression()
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected String valueExpression = ReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getDisplayExpression()
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDisplayExpression()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayExpression()
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDisplayExpression()
     * @generated
     * @ordered
     */
    protected String displayExpression = ReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getOnClickOperation()
     * <em>On Click Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getOnClickOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation onClickOperation;

    /**
     * The cached value of the '{@link #getActions() <em>Actions</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getActions()
     * @generated
     * @ordered
     */
    protected EList<WidgetAction> actions;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected ReferenceWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles()
     * <em>Conditional Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<ReferenceWidgetConditionalStyle> conditionalStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ReferenceDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.REFERENCE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isMultiple() {
        return multiple;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMultiple(boolean newMultiple) {
        boolean oldMultiple = multiple;
        multiple = newMultiple;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.REFERENCE_DESCRIPTION__MULTIPLE, oldMultiple, multiple));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.REFERENCE_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, oldOnClickOperation, newOnClickOperation);
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
                msgs = ((InternalEObject) onClickOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            if (newOnClickOperation != null) {
                msgs = ((InternalEObject) newOnClickOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, null, msgs);
            }
            msgs = basicSetOnClickOperation(newOnClickOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, newOnClickOperation, newOnClickOperation));
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
            actions = new EObjectContainmentEList<WidgetAction>(WidgetAction.class, this, PropertiesPackage.REFERENCE_DESCRIPTION__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ReferenceWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(ReferenceWidgetStyle newStyle, NotificationChain msgs) {
        ReferenceWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.REFERENCE_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(ReferenceWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.REFERENCE_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.REFERENCE_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.REFERENCE_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ReferenceWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<ReferenceWidgetConditionalStyle>(ReferenceWidgetConditionalStyle.class, this, PropertiesPackage.REFERENCE_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return basicSetOnClickOperation(null, msgs);
        case PropertiesPackage.REFERENCE_DESCRIPTION__ACTIONS:
            return ((InternalEList<?>) getActions()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.REFERENCE_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.REFERENCE_DESCRIPTION__MULTIPLE:
            return isMultiple();
        case PropertiesPackage.REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return getOnClickOperation();
        case PropertiesPackage.REFERENCE_DESCRIPTION__ACTIONS:
            return getActions();
        case PropertiesPackage.REFERENCE_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
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
        case PropertiesPackage.REFERENCE_DESCRIPTION__MULTIPLE:
            setMultiple((Boolean) newValue);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends WidgetAction>) newValue);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__STYLE:
            setStyle((ReferenceWidgetStyle) newValue);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ReferenceWidgetConditionalStyle>) newValue);
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
        case PropertiesPackage.REFERENCE_DESCRIPTION__MULTIPLE:
            setMultiple(ReferenceDescriptionImpl.MULTIPLE_EDEFAULT);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(ReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(ReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((InitialOperation) null);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__ACTIONS:
            getActions().clear();
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__STYLE:
            setStyle((ReferenceWidgetStyle) null);
            return;
        case PropertiesPackage.REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
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
        case PropertiesPackage.REFERENCE_DESCRIPTION__MULTIPLE:
            return multiple != ReferenceDescriptionImpl.MULTIPLE_EDEFAULT;
        case PropertiesPackage.REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            return ReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !ReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            return ReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null : !ReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT.equals(displayExpression);
        case PropertiesPackage.REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return onClickOperation != null;
        case PropertiesPackage.REFERENCE_DESCRIPTION__ACTIONS:
            return actions != null && !actions.isEmpty();
        case PropertiesPackage.REFERENCE_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.REFERENCE_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
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
        result.append(" (multiple: ");
        result.append(multiple);
        result.append(", valueExpression: ");
        result.append(valueExpression);
        result.append(", displayExpression: ");
        result.append(displayExpression);
        result.append(')');
        return result.toString();
    }

} // ReferenceDescriptionImpl
