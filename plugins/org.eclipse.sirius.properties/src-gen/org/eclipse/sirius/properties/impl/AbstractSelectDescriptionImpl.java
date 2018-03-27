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
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Select Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getCandidatesExpression <em>Candidates
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getCandidateDisplayExpression
 * <em>Candidate Display Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl#getFilterConditionalStylesFromExtendedSelectExpression
 * <em>Filter Conditional Styles From Extended Select Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractSelectDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractSelectDescription {
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
    protected String valueExpression = AbstractSelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String candidatesExpression = AbstractSelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT;

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
    protected String candidateDisplayExpression = AbstractSelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT;

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
    protected String filterConditionalStylesFromExtendedSelectExpression = AbstractSelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractSelectDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_SELECT_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION, oldCandidatesExpression, candidatesExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION, oldCandidateDisplayExpression,
                    candidateDisplayExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE, oldStyle, newStyle);
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
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE, newStyle, newStyle));
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
            conditionalStyles = new EObjectContainmentEList<SelectWidgetConditionalStyle>(SelectWidgetConditionalStyle.class, this, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES);
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION,
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
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            return getCandidatesExpression();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            return getCandidateDisplayExpression();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
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
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            setCandidateDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE:
            setStyle((SelectWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends SelectWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS:
            setExtends((SelectDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
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
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(AbstractSelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression(AbstractSelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            setCandidateDisplayExpression(AbstractSelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE:
            setStyle((SelectWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS:
            setExtends((SelectDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
            setFilterConditionalStylesFromExtendedSelectExpression(AbstractSelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION:
            return AbstractSelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !AbstractSelectDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION:
            return AbstractSelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT == null ? candidatesExpression != null
                    : !AbstractSelectDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT.equals(candidatesExpression);
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            return AbstractSelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT == null ? candidateDisplayExpression != null
                    : !AbstractSelectDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT.equals(candidateDisplayExpression);
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION:
            return AbstractSelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedSelectExpression != null
                    : !AbstractSelectDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedSelectExpression);
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
        result.append(", candidatesExpression: "); //$NON-NLS-1$
        result.append(candidatesExpression);
        result.append(", candidateDisplayExpression: "); //$NON-NLS-1$
        result.append(candidateDisplayExpression);
        result.append(", filterConditionalStylesFromExtendedSelectExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedSelectExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractSelectDescriptionImpl
