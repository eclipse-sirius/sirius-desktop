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
import org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl;
import org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.EventMapping;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Frame Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl#getStartingEndFinderExpression
 * <em>Starting End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl#getFinishingEndFinderExpression
 * <em>Finishing End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl#getCoveredLifelinesExpression
 * <em>Covered Lifelines Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl#getCenterLabelExpression
 * <em>Center Label Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FrameMappingImpl extends ContainerMappingImpl implements FrameMapping {
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
    protected String startingEndFinderExpression = FrameMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT;

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
    protected String finishingEndFinderExpression = FrameMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getCoveredLifelinesExpression()
     * <em>Covered Lifelines Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getCoveredLifelinesExpression()
     * @generated
     * @ordered
     */
    protected static final String COVERED_LIFELINES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCoveredLifelinesExpression()
     * <em>Covered Lifelines Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getCoveredLifelinesExpression()
     * @generated
     * @ordered
     */
    protected String coveredLifelinesExpression = FrameMappingImpl.COVERED_LIFELINES_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getCenterLabelExpression()
     * <em>Center Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCenterLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String CENTER_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCenterLabelExpression()
     * <em>Center Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCenterLabelExpression()
     * @generated
     * @ordered
     */
    protected String centerLabelExpression = FrameMappingImpl.CENTER_LABEL_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FrameMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.FRAME_MAPPING;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION, oldStartingEndFinderExpression, startingEndFinderExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION, oldFinishingEndFinderExpression, finishingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getCoveredLifelinesExpression() {
        return coveredLifelinesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCoveredLifelinesExpression(String newCoveredLifelinesExpression) {
        String oldCoveredLifelinesExpression = coveredLifelinesExpression;
        coveredLifelinesExpression = newCoveredLifelinesExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION, oldCoveredLifelinesExpression, coveredLifelinesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getCenterLabelExpression() {
        return centerLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCenterLabelExpression(String newCenterLabelExpression) {
        String oldCenterLabelExpression = centerLabelExpression;
        centerLabelExpression = newCenterLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION, oldCenterLabelExpression, centerLabelExpression));
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
        case DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION:
            return getStartingEndFinderExpression();
        case DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            return getFinishingEndFinderExpression();
        case DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION:
            return getCoveredLifelinesExpression();
        case DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION:
            return getCenterLabelExpression();
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
        case DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION:
            setStartingEndFinderExpression((String) newValue);
            return;
        case DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            setFinishingEndFinderExpression((String) newValue);
            return;
        case DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION:
            setCoveredLifelinesExpression((String) newValue);
            return;
        case DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION:
            setCenterLabelExpression((String) newValue);
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
        case DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION:
            setStartingEndFinderExpression(FrameMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            setFinishingEndFinderExpression(FrameMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION:
            setCoveredLifelinesExpression(FrameMappingImpl.COVERED_LIFELINES_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION:
            setCenterLabelExpression(FrameMappingImpl.CENTER_LABEL_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION:
            return FrameMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT == null ? startingEndFinderExpression != null : !FrameMappingImpl.STARTING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(startingEndFinderExpression);
        case DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            return FrameMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT == null ? finishingEndFinderExpression != null : !FrameMappingImpl.FINISHING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(finishingEndFinderExpression);
        case DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION:
            return FrameMappingImpl.COVERED_LIFELINES_EXPRESSION_EDEFAULT == null ? coveredLifelinesExpression != null : !FrameMappingImpl.COVERED_LIFELINES_EXPRESSION_EDEFAULT
                    .equals(coveredLifelinesExpression);
        case DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION:
            return FrameMappingImpl.CENTER_LABEL_EXPRESSION_EDEFAULT == null ? centerLabelExpression != null : !FrameMappingImpl.CENTER_LABEL_EXPRESSION_EDEFAULT.equals(centerLabelExpression);
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
        if (baseClass == EventMapping.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DelimitedEventMapping.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION:
                return DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION;
            case DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION:
                return DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION;
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
        if (baseClass == EventMapping.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DelimitedEventMapping.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION:
                return DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION;
            case DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION:
                return DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION;
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
        result.append(" (startingEndFinderExpression: "); //$NON-NLS-1$
        result.append(startingEndFinderExpression);
        result.append(", finishingEndFinderExpression: "); //$NON-NLS-1$
        result.append(finishingEndFinderExpression);
        result.append(", coveredLifelinesExpression: "); //$NON-NLS-1$
        result.append(coveredLifelinesExpression);
        result.append(", centerLabelExpression: "); //$NON-NLS-1$
        result.append(centerLabelExpression);
        result.append(')');
        return result.toString();
    }

} // FrameMappingImpl
