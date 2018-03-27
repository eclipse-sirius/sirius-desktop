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
import org.eclipse.sirius.properties.AbstractButtonDescription;
import org.eclipse.sirius.properties.AbstractWidgetDescription;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.ButtonWidgetConditionalStyle;
import org.eclipse.sirius.properties.ButtonWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Button Description</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getLabelExpression <em>Label Expression</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getHelpExpression <em>Help Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getIsEnabledExpression <em>Is Enabled
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getButtonLabelExpression <em>Button Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getImageExpression <em>Image Expression</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getInitialOperation <em>Initial Operation</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getConditionalStyles <em>Conditional Styles</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getFilterConditionalStylesFromExtendedButtonExpression
 * <em>Filter Conditional Styles From Extended Button Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ButtonDescriptionImpl extends WidgetDescriptionImpl implements ButtonDescription {
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
    protected String labelExpression = ButtonDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

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
    protected String helpExpression = ButtonDescriptionImpl.HELP_EXPRESSION_EDEFAULT;

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
    protected String isEnabledExpression = ButtonDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getButtonLabelExpression() <em>Button Label Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getButtonLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String BUTTON_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getButtonLabelExpression() <em>Button Label Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getButtonLabelExpression()
     * @generated
     * @ordered
     */
    protected String buttonLabelExpression = ButtonDescriptionImpl.BUTTON_LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getImageExpression() <em>Image Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getImageExpression()
     * @generated
     * @ordered
     */
    protected static final String IMAGE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImageExpression() <em>Image Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getImageExpression()
     * @generated
     * @ordered
     */
    protected String imageExpression = ButtonDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT;

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
    protected ButtonWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<ButtonWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected ButtonDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedButtonExpression() <em>Filter Conditional
     * Styles From Extended Button Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedButtonExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedButtonExpression() <em>Filter Conditional
     * Styles From Extended Button Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedButtonExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedButtonExpression = ButtonDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ButtonDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.BUTTON_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__HELP_EXPRESSION, oldHelpExpression, helpExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION, oldIsEnabledExpression, isEnabledExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getButtonLabelExpression() {
        return buttonLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setButtonLabelExpression(String newButtonLabelExpression) {
        String oldButtonLabelExpression = buttonLabelExpression;
        buttonLabelExpression = newButtonLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION, oldButtonLabelExpression, buttonLabelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getImageExpression() {
        return imageExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setImageExpression(String newImageExpression) {
        String oldImageExpression = imageExpression;
        imageExpression = newImageExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__IMAGE_EXPRESSION, oldImageExpression, imageExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ButtonWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(ButtonWidgetStyle newStyle, NotificationChain msgs) {
        ButtonWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(ButtonWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.BUTTON_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.BUTTON_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ButtonWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<ButtonWidgetConditionalStyle>(ButtonWidgetConditionalStyle.class, this, PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ButtonDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (ButtonDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public ButtonDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(ButtonDescription newExtends) {
        ButtonDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedButtonExpression() {
        return filterConditionalStylesFromExtendedButtonExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedButtonExpression(String newFilterConditionalStylesFromExtendedButtonExpression) {
        String oldFilterConditionalStylesFromExtendedButtonExpression = filterConditionalStylesFromExtendedButtonExpression;
        filterConditionalStylesFromExtendedButtonExpression = newFilterConditionalStylesFromExtendedButtonExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedButtonExpression, filterConditionalStylesFromExtendedButtonExpression));
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
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.BUTTON_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.BUTTON_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.BUTTON_DESCRIPTION__HELP_EXPRESSION:
            return getHelpExpression();
        case PropertiesPackage.BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return getIsEnabledExpression();
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            return getButtonLabelExpression();
        case PropertiesPackage.BUTTON_DESCRIPTION__IMAGE_EXPRESSION:
            return getImageExpression();
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.BUTTON_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION:
            return getFilterConditionalStylesFromExtendedButtonExpression();
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
        case PropertiesPackage.BUTTON_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression((String) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression((String) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            setButtonLabelExpression((String) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__IMAGE_EXPRESSION:
            setImageExpression((String) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__STYLE:
            setStyle((ButtonWidgetStyle) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ButtonWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS:
            setExtends((ButtonDescription) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION:
            setFilterConditionalStylesFromExtendedButtonExpression((String) newValue);
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
        case PropertiesPackage.BUTTON_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(ButtonDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression(ButtonDescriptionImpl.HELP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression(ButtonDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            setButtonLabelExpression(ButtonDescriptionImpl.BUTTON_LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__IMAGE_EXPRESSION:
            setImageExpression(ButtonDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__STYLE:
            setStyle((ButtonWidgetStyle) null);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS:
            setExtends((ButtonDescription) null);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION:
            setFilterConditionalStylesFromExtendedButtonExpression(ButtonDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.BUTTON_DESCRIPTION__LABEL_EXPRESSION:
            return ButtonDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !ButtonDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.BUTTON_DESCRIPTION__HELP_EXPRESSION:
            return ButtonDescriptionImpl.HELP_EXPRESSION_EDEFAULT == null ? helpExpression != null : !ButtonDescriptionImpl.HELP_EXPRESSION_EDEFAULT.equals(helpExpression);
        case PropertiesPackage.BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return ButtonDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT == null ? isEnabledExpression != null : !ButtonDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT.equals(isEnabledExpression);
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            return ButtonDescriptionImpl.BUTTON_LABEL_EXPRESSION_EDEFAULT == null ? buttonLabelExpression != null
                    : !ButtonDescriptionImpl.BUTTON_LABEL_EXPRESSION_EDEFAULT.equals(buttonLabelExpression);
        case PropertiesPackage.BUTTON_DESCRIPTION__IMAGE_EXPRESSION:
            return ButtonDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT == null ? imageExpression != null : !ButtonDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT.equals(imageExpression);
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.BUTTON_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION:
            return ButtonDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedButtonExpression != null
                    : !ButtonDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedButtonExpression);
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
            case PropertiesPackage.BUTTON_DESCRIPTION__LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.BUTTON_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractButtonDescription.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION;
            case PropertiesPackage.BUTTON_DESCRIPTION__IMAGE_EXPRESSION:
                return PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__IMAGE_EXPRESSION;
            case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
                return PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__INITIAL_OPERATION;
            case PropertiesPackage.BUTTON_DESCRIPTION__STYLE:
                return PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__STYLE;
            case PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS:
                return PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__EXTENDS;
            case PropertiesPackage.BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION:
                return PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION;
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
                return PropertiesPackage.BUTTON_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.BUTTON_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractButtonDescription.class) {
            switch (baseFeatureID) {
            case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
                return PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION;
            case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__IMAGE_EXPRESSION:
                return PropertiesPackage.BUTTON_DESCRIPTION__IMAGE_EXPRESSION;
            case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__INITIAL_OPERATION:
                return PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION;
            case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__STYLE:
                return PropertiesPackage.BUTTON_DESCRIPTION__STYLE;
            case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__EXTENDS:
                return PropertiesPackage.BUTTON_DESCRIPTION__EXTENDS;
            case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION:
                return PropertiesPackage.BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION;
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
        result.append(", buttonLabelExpression: "); //$NON-NLS-1$
        result.append(buttonLabelExpression);
        result.append(", imageExpression: "); //$NON-NLS-1$
        result.append(imageExpression);
        result.append(", filterConditionalStylesFromExtendedButtonExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedButtonExpression);
        result.append(')');
        return result.toString();
    }

} // ButtonDescriptionImpl
