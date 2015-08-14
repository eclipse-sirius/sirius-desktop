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
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Position;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Decoration Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getPosition
 * <em>Position</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getDecoratorPath
 * <em>Decorator Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DecorationDescriptionImpl extends MinimalEObjectImpl.Container implements DecorationDescription {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = DecorationDescriptionImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPosition() <em>Position</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected static final Position POSITION_EDEFAULT = Position.SOUTH_WEST_LITERAL;

    /**
     * The cached value of the '{@link #getPosition() <em>Position</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected Position position = DecorationDescriptionImpl.POSITION_EDEFAULT;

    /**
     * The default value of the '{@link #getDecoratorPath()
     * <em>Decorator Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDecoratorPath()
     * @generated
     * @ordered
     */
    protected static final String DECORATOR_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDecoratorPath()
     * <em>Decorator Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDecoratorPath()
     * @generated
     * @ordered
     */
    protected String decoratorPath = DecorationDescriptionImpl.DECORATOR_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DecorationDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DECORATION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DECORATION_DESCRIPTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPosition(Position newPosition) {
        Position oldPosition = position;
        position = newPosition == null ? DecorationDescriptionImpl.POSITION_EDEFAULT : newPosition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DECORATION_DESCRIPTION__POSITION, oldPosition, position));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDecoratorPath() {
        return decoratorPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDecoratorPath(String newDecoratorPath) {
        String oldDecoratorPath = decoratorPath;
        decoratorPath = newDecoratorPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DECORATION_DESCRIPTION__DECORATOR_PATH, oldDecoratorPath, decoratorPath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPreconditionExpression() {
        return preconditionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPreconditionExpression(String newPreconditionExpression) {
        String oldPreconditionExpression = preconditionExpression;
        preconditionExpression = newPreconditionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
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
        case DescriptionPackage.DECORATION_DESCRIPTION__NAME:
            return getName();
        case DescriptionPackage.DECORATION_DESCRIPTION__POSITION:
            return getPosition();
        case DescriptionPackage.DECORATION_DESCRIPTION__DECORATOR_PATH:
            return getDecoratorPath();
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
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
        case DescriptionPackage.DECORATION_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__POSITION:
            setPosition((Position) newValue);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__DECORATOR_PATH:
            setDecoratorPath((String) newValue);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
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
        case DescriptionPackage.DECORATION_DESCRIPTION__NAME:
            setName(DecorationDescriptionImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__POSITION:
            setPosition(DecorationDescriptionImpl.POSITION_EDEFAULT);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__DECORATOR_PATH:
            setDecoratorPath(DecorationDescriptionImpl.DECORATOR_PATH_EDEFAULT);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.DECORATION_DESCRIPTION__NAME:
            return DecorationDescriptionImpl.NAME_EDEFAULT == null ? name != null : !DecorationDescriptionImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.DECORATION_DESCRIPTION__POSITION:
            return position != DecorationDescriptionImpl.POSITION_EDEFAULT;
        case DescriptionPackage.DECORATION_DESCRIPTION__DECORATOR_PATH:
            return DecorationDescriptionImpl.DECORATOR_PATH_EDEFAULT == null ? decoratorPath != null : !DecorationDescriptionImpl.DECORATOR_PATH_EDEFAULT.equals(decoratorPath);
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            return DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null : !DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT
                    .equals(preconditionExpression);
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", position: "); //$NON-NLS-1$
        result.append(position);
        result.append(", decoratorPath: "); //$NON-NLS-1$
        result.append(decoratorPath);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(')');
        return result.toString();
    }

} // DecorationDescriptionImpl
