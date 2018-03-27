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
import org.eclipse.sirius.properties.AbstractRadioDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Radio Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getCandidatesExpression <em>Candidates
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getCandidateDisplayExpression
 * <em>Candidate Display Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getNumberOfColumns <em>Number Of
 * Columns</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl#getFilterConditionalStylesFromExtendedRadioExpression
 * <em>Filter Conditional Styles From Extended Radio Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractRadioDescriptionImpl extends AbstractWidgetDescriptionImpl implements AbstractRadioDescription {
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
    protected String valueExpression = AbstractRadioDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String candidatesExpression = AbstractRadioDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT;

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
    protected String candidateDisplayExpression = AbstractRadioDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStyle()
     * @generated
     * @ordered
     */
    protected RadioWidgetStyle style;

    /**
     * The default value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNumberOfColumns()
     * @generated
     * @ordered
     */
    protected static final int NUMBER_OF_COLUMNS_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getNumberOfColumns()
     * @generated
     * @ordered
     */
    protected int numberOfColumns = AbstractRadioDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT;

    /**
     * The cached value of the '{@link #getConditionalStyles() <em>Conditional Styles</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<RadioWidgetConditionalStyle> conditionalStyles;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected RadioDescription extends_;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromExtendedRadioExpression() <em>Filter Conditional
     * Styles From Extended Radio Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedRadioExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromExtendedRadioExpression() <em>Filter Conditional
     * Styles From Extended Radio Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromExtendedRadioExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromExtendedRadioExpression = AbstractRadioDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractRadioDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_RADIO_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION, oldCandidatesExpression, candidatesExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION, oldCandidateDisplayExpression,
                    candidateDisplayExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RadioWidgetStyle getStyle() {
        return style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStyle(RadioWidgetStyle newStyle, NotificationChain msgs) {
        RadioWidgetStyle oldStyle = style;
        style = newStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE, oldStyle, newStyle);
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
    public void setStyle(RadioWidgetStyle newStyle) {
        if (newStyle != style) {
            NotificationChain msgs = null;
            if (style != null) {
                msgs = ((InternalEObject) style).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE, null, msgs);
            }
            if (newStyle != null) {
                msgs = ((InternalEObject) newStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE, null, msgs);
            }
            msgs = basicSetStyle(newStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE, newStyle, newStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNumberOfColumns(int newNumberOfColumns) {
        int oldNumberOfColumns = numberOfColumns;
        numberOfColumns = newNumberOfColumns;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS, oldNumberOfColumns, numberOfColumns));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<RadioWidgetConditionalStyle> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<RadioWidgetConditionalStyle>(RadioWidgetConditionalStyle.class, this, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RadioDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (RadioDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public RadioDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(RadioDescription newExtends) {
        RadioDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromExtendedRadioExpression() {
        return filterConditionalStylesFromExtendedRadioExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromExtendedRadioExpression(String newFilterConditionalStylesFromExtendedRadioExpression) {
        String oldFilterConditionalStylesFromExtendedRadioExpression = filterConditionalStylesFromExtendedRadioExpression;
        filterConditionalStylesFromExtendedRadioExpression = newFilterConditionalStylesFromExtendedRadioExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION,
                    oldFilterConditionalStylesFromExtendedRadioExpression, filterConditionalStylesFromExtendedRadioExpression));
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
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE:
            return basicSetStyle(null, msgs);
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES:
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
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION:
            return getCandidatesExpression();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            return getCandidateDisplayExpression();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE:
            return getStyle();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS:
            return getNumberOfColumns();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION:
            return getFilterConditionalStylesFromExtendedRadioExpression();
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
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            setCandidateDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE:
            setStyle((RadioWidgetStyle) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS:
            setNumberOfColumns((Integer) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends RadioWidgetConditionalStyle>) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS:
            setExtends((RadioDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION:
            setFilterConditionalStylesFromExtendedRadioExpression((String) newValue);
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
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(AbstractRadioDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression(AbstractRadioDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            setCandidateDisplayExpression(AbstractRadioDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE:
            setStyle((RadioWidgetStyle) null);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS:
            setNumberOfColumns(AbstractRadioDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS:
            setExtends((RadioDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION:
            setFilterConditionalStylesFromExtendedRadioExpression(AbstractRadioDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION:
            return AbstractRadioDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !AbstractRadioDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION:
            return AbstractRadioDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT == null ? candidatesExpression != null
                    : !AbstractRadioDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT.equals(candidatesExpression);
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION:
            return AbstractRadioDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT == null ? candidateDisplayExpression != null
                    : !AbstractRadioDescriptionImpl.CANDIDATE_DISPLAY_EXPRESSION_EDEFAULT.equals(candidateDisplayExpression);
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE:
            return style != null;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS:
            return numberOfColumns != AbstractRadioDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION:
            return AbstractRadioDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromExtendedRadioExpression != null
                    : !AbstractRadioDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromExtendedRadioExpression);
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
        result.append(", numberOfColumns: "); //$NON-NLS-1$
        result.append(numberOfColumns);
        result.append(", filterConditionalStylesFromExtendedRadioExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromExtendedRadioExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractRadioDescriptionImpl
