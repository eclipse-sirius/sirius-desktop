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
import org.eclipse.sirius.properties.AbstractSelectDescription;
import org.eclipse.sirius.properties.AbstractWidgetDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Select Description</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getLabelExpression <em>Label Expression</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getHelpExpression <em>Help Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getIsEnabledExpression <em>Is Enabled
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getValueExpression <em>Value Expression</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getInitialOperation <em>Initial Operation</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getCandidatesExpression <em>Candidates
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getCandidateDisplayExpression <em>Candidate
 * Display Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getConditionalStyles <em>Conditional Styles</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl#getFilterConditionalStylesFromExtendedSelectExpression
 * <em>Filter Conditional Styles From Extended Select Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SelectDescriptionImpl extends WidgetDescriptionImpl implements SelectDescription {
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
    protected String labelExpression = SelectDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

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
    protected String helpExpression = SelectDescriptionImpl.HELP_EXPRESSION_EDEFAULT;

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
    protected String isEnabledExpression = SelectDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT;

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
    protected String valueExpression = SelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
     * The default value of the '{@link #getCandidatesExpression() <em>Candidates Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected static final String CANDIDATES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCandidatesExpression() <em>Candidates Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected String candidatesExpression = SelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getCandidateDisplayExpression() <em>Candidate Display Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCandidateDisplayExpression()
     * @generated
     * @ordered
     */
    protected static final String CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCandidateDisplayExpression() <em>Candidate Display Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCandidateDisplayExpression()
     * @generated
     * @ordered
     */
    protected String candidateDisplayExpression = SelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected SelectWidgetStyle style;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<SelectWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected SelectDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedSelectExpression() <em>Filter Conditional
     * Styles From Extended Select Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedSelectExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedSelectExpression() <em>Filter Conditional
     * Styles From Extended Select Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedSelectExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedSelectExpression = SelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SelectDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.SELECT_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__HELP_EXPRESSION, oldHelpExpression, helpExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION, oldIsEnabledExpression, isEnabledExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCandidatesExpression() {
        return candidatesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCandidatesExpression(String newCandidatesExpression) {
        String oldCandidatesExpression = candidatesExpression;
        candidatesExpression = newCandidatesExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION, oldCandidatesExpression, candidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCandidateDisplayExpression() {
        return candidateDisplayExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCandidateDisplayExpression(String newCandidateDisplayExpression) {
        String oldCandidateDisplayExpression = candidateDisplayExpression;
        candidateDisplayExpression = newCandidateDisplayExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION, oldCandidateDisplayExpression, candidateDisplayExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SelectWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(SelectWidgetStyle newStyle, NotificationChain msgs) {
        SelectWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(SelectWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SELECT_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SELECT_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<SelectWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<SelectWidgetConditionalStyle>(SelectWidgetConditionalStyle.class, this, PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SelectDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (SelectDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.SELECT_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public SelectDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(SelectDescription newExtends) {
        SelectDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedSelectExpression() {
        return filterConditionalStylesFromExtendedSelectExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedSelectExpression(String newFilterConditionalStylesFromExtendedSelectExpression) {
        String oldFilterConditionalStylesFromExtendedSelectExpression = filterConditionalStylesFromExtendedSelectExpression;
        filterConditionalStylesFromExtendedSelectExpression = newFilterConditionalStylesFromExtendedSelectExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedSelectExpression, filterConditionalStylesFromExtendedSelectExpression));
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
        case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.SELECT_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.SELECT_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.SELECT_DESCRIPTION__HELP_EXPRESSION:
            return getHelpExpression();
        case PropertiesPackage.SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return getIsEnabledExpression();
        case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            return getCandidatesExpression();
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            return getCandidateDisplayExpression();
        case PropertiesPackage.SELECT_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.SELECT_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
            return getFilterConditionalStylesFromExtendedSelectExpression();
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
        case PropertiesPackage.SELECT_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression((String) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression((String) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression((String) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            setCandidateDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__STYLE:
            setStyle((SelectWidgetStyle) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends SelectWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__EXTENDS:
            setExtends((SelectDescription) newValue);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
            setFilterConditionalStylesFromExtendedSelectExpression((String) newValue);
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
        case PropertiesPackage.SELECT_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(SelectDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__HELP_EXPRESSION:
            setHelpExpression(SelectDescriptionImpl.HELP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression(SelectDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(SelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression(SelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            setCandidateDisplayExpression(SelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__STYLE:
            setStyle((SelectWidgetStyle) null);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__EXTENDS:
            setExtends((SelectDescription) null);
            return;
        case PropertiesPackage.SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
            setFilterConditionalStylesFromExtendedSelectExpression(SelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.SELECT_DESCRIPTION__LABEL_EXPRESSION:
            return SelectDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !SelectDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.SELECT_DESCRIPTION__HELP_EXPRESSION:
            return SelectDescriptionImpl.HELP_EXPRESSION_EDEFAULT == null ? helpExpression != null : !SelectDescriptionImpl.HELP_EXPRESSION_EDEFAULT.equals(helpExpression);
        case PropertiesPackage.SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION:
            return SelectDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT == null ? isEnabledExpression != null : !SelectDescriptionImpl.IS_ENABLED_EXPRESSION_EDEFAULT.equals(isEnabledExpression);
        case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
            return SelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !SelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            return SelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT == null ? candidatesExpression != null : !SelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT.equals(candidatesExpression);
        case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            return SelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT == null ? candidateDisplayExpression != null
                    : !SelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT.equals(candidateDisplayExpression);
        case PropertiesPackage.SELECT_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.SELECT_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
            return SelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedSelectExpression != null
                    : !SelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedSelectExpression);
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
            case PropertiesPackage.SELECT_DESCRIPTION__LABEL_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.SELECT_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractSelectDescription.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION;
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION;
            case PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION;
            case PropertiesPackage.SELECT_DESCRIPTION__STYLE:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE;
            case PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.SELECT_DESCRIPTION__EXTENDS:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS;
            case PropertiesPackage.SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
                return PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION;
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
                return PropertiesPackage.SELECT_DESCRIPTION__LABEL_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION:
                return PropertiesPackage.SELECT_DESCRIPTION__HELP_EXPRESSION;
            case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION:
                return PropertiesPackage.SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == AbstractSelectDescription.class) {
            switch (baseFeatureID) {
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION:
                return PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION;
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION:
                return PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION;
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
                return PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION;
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
                return PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION;
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE:
                return PropertiesPackage.SELECT_DESCRIPTION__STYLE;
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES:
                return PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES;
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS:
                return PropertiesPackage.SELECT_DESCRIPTION__EXTENDS;
            case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
                return PropertiesPackage.SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION;
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
        result.append(", candidatesExpression: "); //$NON-NLS-1$
        result.append(candidatesExpression);
        result.append(", candidateDisplayExpression: "); //$NON-NLS-1$
        result.append(candidateDisplayExpression);
        result.append(", filterConditionalStylesFromExtendedSelectExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedSelectExpression);
        result.append(')');
        return result.toString();
    }

} // SelectDescriptionImpl
