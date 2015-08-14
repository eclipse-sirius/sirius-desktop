/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Delimited Event Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.DelimitedEventMappingImpl#getStartingEndFinderExpression
 * <em>Starting End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.DelimitedEventMappingImpl#getFinishingEndFinderExpression
 * <em>Finishing End Finder Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DelimitedEventMappingImpl extends EventMappingImpl implements DelimitedEventMapping {
    /**
     * The default value of the '{@link #getStartingEndFinderExpression()
     * <em>Starting End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getStartingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String STARTING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStartingEndFinderExpression()
     * <em>Starting End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getStartingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String startingEndFinderExpression = DelimitedEventMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFinishingEndFinderExpression()
     * <em>Finishing End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getFinishingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String FINISHING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFinishingEndFinderExpression()
     * <em>Finishing End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getFinishingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String finishingEndFinderExpression = DelimitedEventMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DelimitedEventMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DELIMITED_EVENT_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getStartingEndFinderExpression() {
        return startingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setStartingEndFinderExpression(String newStartingEndFinderExpression) {
        String oldStartingEndFinderExpression = startingEndFinderExpression;
        startingEndFinderExpression = newStartingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION, oldStartingEndFinderExpression,
                    startingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFinishingEndFinderExpression() {
        return finishingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFinishingEndFinderExpression(String newFinishingEndFinderExpression) {
        String oldFinishingEndFinderExpression = finishingEndFinderExpression;
        finishingEndFinderExpression = newFinishingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION, oldFinishingEndFinderExpression,
                    finishingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION:
            return getStartingEndFinderExpression();
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            return getFinishingEndFinderExpression();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION:
            setStartingEndFinderExpression((String) newValue);
            return;
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            setFinishingEndFinderExpression((String) newValue);
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
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION:
            setStartingEndFinderExpression(DelimitedEventMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            setFinishingEndFinderExpression(DelimitedEventMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION:
            return DelimitedEventMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT == null ? startingEndFinderExpression != null : !DelimitedEventMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(startingEndFinderExpression);
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            return DelimitedEventMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT == null ? finishingEndFinderExpression != null
                    : !DelimitedEventMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT.equals(finishingEndFinderExpression);
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
        result.append(" (startingEndFinderExpression: "); //$NON-NLS-1$
        result.append(startingEndFinderExpression);
        result.append(", finishingEndFinderExpression: "); //$NON-NLS-1$
        result.append(finishingEndFinderExpression);
        result.append(')');
        return result.toString();
    }

} // DelimitedEventMappingImpl
