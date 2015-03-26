/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FilterVariableValue;
import org.eclipse.sirius.diagram.description.filter.FilterVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Filter Variable Value</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.FilterVariableValueImpl#getVariableDefinition
 * <em>Variable Definition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.FilterVariableValueImpl#getModelElement
 * <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterVariableValueImpl extends MinimalEObjectImpl.Container implements FilterVariableValue {
    /**
     * The cached value of the '{@link #getVariableDefinition()
     * <em>Variable Definition</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getVariableDefinition()
     * @generated
     * @ordered
     */
    protected FilterVariable variableDefinition;

    /**
     * The cached value of the '{@link #getModelElement()
     * <em>Model Element</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getModelElement()
     * @generated
     * @ordered
     */
    protected EObject modelElement;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FilterVariableValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.FILTER_VARIABLE_VALUE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public FilterVariable getVariableDefinition() {
        if (variableDefinition != null && variableDefinition.eIsProxy()) {
            InternalEObject oldVariableDefinition = (InternalEObject) variableDefinition;
            variableDefinition = (FilterVariable) eResolveProxy(oldVariableDefinition);
            if (variableDefinition != oldVariableDefinition) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION, oldVariableDefinition, variableDefinition));
                }
            }
        }
        return variableDefinition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FilterVariable basicGetVariableDefinition() {
        return variableDefinition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setVariableDefinition(FilterVariable newVariableDefinition) {
        FilterVariable oldVariableDefinition = variableDefinition;
        variableDefinition = newVariableDefinition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION, oldVariableDefinition, variableDefinition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject getModelElement() {
        if (modelElement != null && modelElement.eIsProxy()) {
            InternalEObject oldModelElement = (InternalEObject) modelElement;
            modelElement = eResolveProxy(oldModelElement);
            if (modelElement != oldModelElement) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.FILTER_VARIABLE_VALUE__MODEL_ELEMENT, oldModelElement, modelElement));
                }
            }
        }
        return modelElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetModelElement() {
        return modelElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setModelElement(EObject newModelElement) {
        EObject oldModelElement = modelElement;
        modelElement = newModelElement;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.FILTER_VARIABLE_VALUE__MODEL_ELEMENT, oldModelElement, modelElement));
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
        case DiagramPackage.FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION:
            if (resolve) {
                return getVariableDefinition();
            }
            return basicGetVariableDefinition();
        case DiagramPackage.FILTER_VARIABLE_VALUE__MODEL_ELEMENT:
            if (resolve) {
                return getModelElement();
            }
            return basicGetModelElement();
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
        case DiagramPackage.FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION:
            setVariableDefinition((FilterVariable) newValue);
            return;
        case DiagramPackage.FILTER_VARIABLE_VALUE__MODEL_ELEMENT:
            setModelElement((EObject) newValue);
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
        case DiagramPackage.FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION:
            setVariableDefinition((FilterVariable) null);
            return;
        case DiagramPackage.FILTER_VARIABLE_VALUE__MODEL_ELEMENT:
            setModelElement((EObject) null);
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
        case DiagramPackage.FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION:
            return variableDefinition != null;
        case DiagramPackage.FILTER_VARIABLE_VALUE__MODEL_ELEMENT:
            return modelElement != null;
        }
        return super.eIsSet(featureID);
    }

} // FilterVariableValueImpl
