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
import org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Sequence Diagram Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl#getEndsOrdering
 * <em>Ends Ordering</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl#getInstanceRolesOrdering
 * <em>Instance Roles Ordering</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceDiagramDescriptionImpl extends DiagramDescriptionImpl implements SequenceDiagramDescription {
    /**
     * The default value of the '{@link #getEndsOrdering()
     * <em>Ends Ordering</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndsOrdering()
     * @generated
     * @ordered
     */
    protected static final String ENDS_ORDERING_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEndsOrdering()
     * <em>Ends Ordering</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndsOrdering()
     * @generated
     * @ordered
     */
    protected String endsOrdering = SequenceDiagramDescriptionImpl.ENDS_ORDERING_EDEFAULT;

    /**
     * The default value of the '{@link #getInstanceRolesOrdering()
     * <em>Instance Roles Ordering</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getInstanceRolesOrdering()
     * @generated
     * @ordered
     */
    protected static final String INSTANCE_ROLES_ORDERING_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInstanceRolesOrdering()
     * <em>Instance Roles Ordering</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getInstanceRolesOrdering()
     * @generated
     * @ordered
     */
    protected String instanceRolesOrdering = SequenceDiagramDescriptionImpl.INSTANCE_ROLES_ORDERING_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SequenceDiagramDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.SEQUENCE_DIAGRAM_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getEndsOrdering() {
        return endsOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEndsOrdering(String newEndsOrdering) {
        String oldEndsOrdering = endsOrdering;
        endsOrdering = newEndsOrdering;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING, oldEndsOrdering, endsOrdering));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getInstanceRolesOrdering() {
        return instanceRolesOrdering;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInstanceRolesOrdering(String newInstanceRolesOrdering) {
        String oldInstanceRolesOrdering = instanceRolesOrdering;
        instanceRolesOrdering = newInstanceRolesOrdering;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING, oldInstanceRolesOrdering, instanceRolesOrdering));
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
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING:
            return getEndsOrdering();
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING:
            return getInstanceRolesOrdering();
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
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING:
            setEndsOrdering((String) newValue);
            return;
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING:
            setInstanceRolesOrdering((String) newValue);
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
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING:
            setEndsOrdering(SequenceDiagramDescriptionImpl.ENDS_ORDERING_EDEFAULT);
            return;
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING:
            setInstanceRolesOrdering(SequenceDiagramDescriptionImpl.INSTANCE_ROLES_ORDERING_EDEFAULT);
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
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING:
            return SequenceDiagramDescriptionImpl.ENDS_ORDERING_EDEFAULT == null ? endsOrdering != null : !SequenceDiagramDescriptionImpl.ENDS_ORDERING_EDEFAULT.equals(endsOrdering);
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING:
            return SequenceDiagramDescriptionImpl.INSTANCE_ROLES_ORDERING_EDEFAULT == null ? instanceRolesOrdering != null : !SequenceDiagramDescriptionImpl.INSTANCE_ROLES_ORDERING_EDEFAULT
                    .equals(instanceRolesOrdering);
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
        result.append(" (endsOrdering: "); //$NON-NLS-1$
        result.append(endsOrdering);
        result.append(", instanceRolesOrdering: "); //$NON-NLS-1$
        result.append(instanceRolesOrdering);
        result.append(')');
        return result.toString();
    }

} // SequenceDiagramDescriptionImpl
