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
package org.eclipse.sirius.viewpoint.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>External Java Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionImpl#getSubModelOperations
 * <em>Sub Model Operations</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionImpl#getParameters
 * <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalJavaActionImpl extends MenuItemDescriptionImpl implements ExternalJavaAction {
    /**
     * The cached value of the '{@link #getSubModelOperations()
     * <em>Sub Model Operations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubModelOperations()
     * @generated
     * @ordered
     */
    protected EList<ModelOperation> subModelOperations;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ExternalJavaActionImpl.ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected EList<ExternalJavaActionParameter> parameters;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ExternalJavaActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.EXTERNAL_JAVA_ACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ModelOperation> getSubModelOperations() {
        if (subModelOperations == null) {
            subModelOperations = new EObjectContainmentEList.Resolving<ModelOperation>(ModelOperation.class, this, ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS);
        }
        return subModelOperations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EXTERNAL_JAVA_ACTION__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ExternalJavaActionParameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList.Resolving<ExternalJavaActionParameter>(ExternalJavaActionParameter.class, this, ToolPackage.EXTERNAL_JAVA_ACTION__PARAMETERS);
        }
        return parameters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS:
            return ((InternalEList<?>) getSubModelOperations()).basicRemove(otherEnd, msgs);
        case ToolPackage.EXTERNAL_JAVA_ACTION__PARAMETERS:
            return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS:
            return getSubModelOperations();
        case ToolPackage.EXTERNAL_JAVA_ACTION__ID:
            return getId();
        case ToolPackage.EXTERNAL_JAVA_ACTION__PARAMETERS:
            return getParameters();
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
        case ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS:
            getSubModelOperations().clear();
            getSubModelOperations().addAll((Collection<? extends ModelOperation>) newValue);
            return;
        case ToolPackage.EXTERNAL_JAVA_ACTION__ID:
            setId((String) newValue);
            return;
        case ToolPackage.EXTERNAL_JAVA_ACTION__PARAMETERS:
            getParameters().clear();
            getParameters().addAll((Collection<? extends ExternalJavaActionParameter>) newValue);
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
        case ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS:
            getSubModelOperations().clear();
            return;
        case ToolPackage.EXTERNAL_JAVA_ACTION__ID:
            setId(ExternalJavaActionImpl.ID_EDEFAULT);
            return;
        case ToolPackage.EXTERNAL_JAVA_ACTION__PARAMETERS:
            getParameters().clear();
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
        case ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS:
            return subModelOperations != null && !subModelOperations.isEmpty();
        case ToolPackage.EXTERNAL_JAVA_ACTION__ID:
            return ExternalJavaActionImpl.ID_EDEFAULT == null ? id != null : !ExternalJavaActionImpl.ID_EDEFAULT.equals(id);
        case ToolPackage.EXTERNAL_JAVA_ACTION__PARAMETERS:
            return parameters != null && !parameters.isEmpty();
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
        if (baseClass == ModelOperation.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == ContainerModelOperation.class) {
            switch (derivedFeatureID) {
            case ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS:
                return ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;
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
        if (baseClass == ModelOperation.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == ContainerModelOperation.class) {
            switch (baseFeatureID) {
            case ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS:
                return ToolPackage.EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS;
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(')');
        return result.toString();
    }

} // ExternalJavaActionImpl
