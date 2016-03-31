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
package org.eclipse.sirius.diagram.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ContainerModelOperationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Create View</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.CreateViewImpl#getMapping
 * <em>Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.CreateViewImpl#getContainerViewExpression
 * <em>Container View Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.CreateViewImpl#getVariableName
 * <em>Variable Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CreateViewImpl extends ContainerModelOperationImpl implements CreateView {
    /**
     * The cached value of the '{@link #getMapping() <em>Mapping</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMapping()
     * @generated
     * @ordered
     */
    protected DiagramElementMapping mapping;

    /**
     * The default value of the '{@link #getContainerViewExpression()
     * <em>Container View Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getContainerViewExpression()
     * @generated
     * @ordered
     */
    protected static final String CONTAINER_VIEW_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getContainerViewExpression()
     * <em>Container View Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getContainerViewExpression()
     * @generated
     * @ordered
     */
    protected String containerViewExpression = CreateViewImpl.CONTAINER_VIEW_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getVariableName()
     * <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    protected static final String VARIABLE_NAME_EDEFAULT = "createdView"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getVariableName() <em>Variable Name</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    protected String variableName = CreateViewImpl.VARIABLE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CreateViewImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.CREATE_VIEW;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DiagramElementMapping getMapping() {
        if (mapping != null && mapping.eIsProxy()) {
            InternalEObject oldMapping = (InternalEObject) mapping;
            mapping = (DiagramElementMapping) eResolveProxy(oldMapping);
            if (mapping != oldMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.CREATE_VIEW__MAPPING, oldMapping, mapping));
                }
            }
        }
        return mapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DiagramElementMapping basicGetMapping() {
        return mapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMapping(DiagramElementMapping newMapping) {
        DiagramElementMapping oldMapping = mapping;
        mapping = newMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_VIEW__MAPPING, oldMapping, mapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getContainerViewExpression() {
        return containerViewExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setContainerViewExpression(String newContainerViewExpression) {
        String oldContainerViewExpression = containerViewExpression;
        containerViewExpression = newContainerViewExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_VIEW__CONTAINER_VIEW_EXPRESSION, oldContainerViewExpression, containerViewExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getVariableName() {
        return variableName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVariableName(String newVariableName) {
        String oldVariableName = variableName;
        variableName = newVariableName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_VIEW__VARIABLE_NAME, oldVariableName, variableName));
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
        case ToolPackage.CREATE_VIEW__MAPPING:
            if (resolve) {
                return getMapping();
            }
            return basicGetMapping();
        case ToolPackage.CREATE_VIEW__CONTAINER_VIEW_EXPRESSION:
            return getContainerViewExpression();
        case ToolPackage.CREATE_VIEW__VARIABLE_NAME:
            return getVariableName();
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
        case ToolPackage.CREATE_VIEW__MAPPING:
            setMapping((DiagramElementMapping) newValue);
            return;
        case ToolPackage.CREATE_VIEW__CONTAINER_VIEW_EXPRESSION:
            setContainerViewExpression((String) newValue);
            return;
        case ToolPackage.CREATE_VIEW__VARIABLE_NAME:
            setVariableName((String) newValue);
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
        case ToolPackage.CREATE_VIEW__MAPPING:
            setMapping((DiagramElementMapping) null);
            return;
        case ToolPackage.CREATE_VIEW__CONTAINER_VIEW_EXPRESSION:
            setContainerViewExpression(CreateViewImpl.CONTAINER_VIEW_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.CREATE_VIEW__VARIABLE_NAME:
            setVariableName(CreateViewImpl.VARIABLE_NAME_EDEFAULT);
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
        case ToolPackage.CREATE_VIEW__MAPPING:
            return mapping != null;
        case ToolPackage.CREATE_VIEW__CONTAINER_VIEW_EXPRESSION:
            return CreateViewImpl.CONTAINER_VIEW_EXPRESSION_EDEFAULT == null ? containerViewExpression != null : !CreateViewImpl.CONTAINER_VIEW_EXPRESSION_EDEFAULT.equals(containerViewExpression);
        case ToolPackage.CREATE_VIEW__VARIABLE_NAME:
            return CreateViewImpl.VARIABLE_NAME_EDEFAULT == null ? variableName != null : !CreateViewImpl.VARIABLE_NAME_EDEFAULT.equals(variableName);
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
        result.append(" (containerViewExpression: "); //$NON-NLS-1$
        result.append(containerViewExpression);
        result.append(", variableName: "); //$NON-NLS-1$
        result.append(variableName);
        result.append(')');
        return result.toString();
    }

} // CreateViewImpl
