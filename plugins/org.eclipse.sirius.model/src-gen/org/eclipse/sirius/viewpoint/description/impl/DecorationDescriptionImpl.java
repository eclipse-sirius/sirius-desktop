/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Position;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Decoration Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getPosition
 * <em>Position</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getDistributionDirection
 * <em>Distribution Direction</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getImageExpression <em>Image
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.DecorationDescriptionImpl#getTooltipExpression <em>Tooltip
 * Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DecorationDescriptionImpl extends MinimalEObjectImpl.Container implements DecorationDescription {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = DecorationDescriptionImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPosition() <em>Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected static final Position POSITION_EDEFAULT = Position.SOUTH_WEST_LITERAL;

    /**
     * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPosition()
     * @generated
     * @ordered
     */
    protected Position position = DecorationDescriptionImpl.POSITION_EDEFAULT;

    /**
     * The default value of the '{@link #getDistributionDirection() <em>Distribution Direction</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDistributionDirection()
     * @generated
     * @ordered
     */
    protected static final DecorationDistributionDirection DISTRIBUTION_DIRECTION_EDEFAULT = DecorationDistributionDirection.VERTICAL;

    /**
     * The cached value of the '{@link #getDistributionDirection() <em>Distribution Direction</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDistributionDirection()
     * @generated
     * @ordered
     */
    protected DecorationDistributionDirection distributionDirection = DecorationDescriptionImpl.DISTRIBUTION_DIRECTION_EDEFAULT;

    /**
     * The default value of the '{@link #getPreconditionExpression() <em>Precondition Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreconditionExpression() <em>Precondition Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getImageExpression() <em>Image Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getImageExpression()
     * @generated
     * @ordered
     */
    protected static final String IMAGE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImageExpression() <em>Image Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getImageExpression()
     * @generated
     * @ordered
     */
    protected String imageExpression = DecorationDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getTooltipExpression() <em>Tooltip Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected static final String TOOLTIP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTooltipExpression() <em>Tooltip Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected String tooltipExpression = DecorationDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT;

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
    public DecorationDistributionDirection getDistributionDirection() {
        return distributionDirection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDistributionDirection(DecorationDistributionDirection newDistributionDirection) {
        DecorationDistributionDirection oldDistributionDirection = distributionDirection;
        distributionDirection = newDistributionDirection == null ? DecorationDescriptionImpl.DISTRIBUTION_DIRECTION_EDEFAULT : newDistributionDirection;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DECORATION_DESCRIPTION__DISTRIBUTION_DIRECTION, oldDistributionDirection, distributionDirection));
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
    public String getImageExpression() {
        return imageExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setImageExpression(String newImageExpression) {
        String oldImageExpression = imageExpression;
        imageExpression = newImageExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION, oldImageExpression, imageExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTooltipExpression() {
        return tooltipExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTooltipExpression(String newTooltipExpression) {
        String oldTooltipExpression = tooltipExpression;
        tooltipExpression = newTooltipExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION, oldTooltipExpression, tooltipExpression));
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
        case DescriptionPackage.DECORATION_DESCRIPTION__DISTRIBUTION_DIRECTION:
            return getDistributionDirection();
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION:
            return getImageExpression();
        case DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION:
            return getTooltipExpression();
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
        case DescriptionPackage.DECORATION_DESCRIPTION__DISTRIBUTION_DIRECTION:
            setDistributionDirection((DecorationDistributionDirection) newValue);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION:
            setImageExpression((String) newValue);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression((String) newValue);
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
        case DescriptionPackage.DECORATION_DESCRIPTION__DISTRIBUTION_DIRECTION:
            setDistributionDirection(DecorationDescriptionImpl.DISTRIBUTION_DIRECTION_EDEFAULT);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION:
            setImageExpression(DecorationDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression(DecorationDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.DECORATION_DESCRIPTION__DISTRIBUTION_DIRECTION:
            return distributionDirection != DecorationDescriptionImpl.DISTRIBUTION_DIRECTION_EDEFAULT;
        case DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION:
            return DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null
                    : !DecorationDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION:
            return DecorationDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT == null ? imageExpression != null : !DecorationDescriptionImpl.IMAGE_EXPRESSION_EDEFAULT.equals(imageExpression);
        case DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION:
            return DecorationDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT == null ? tooltipExpression != null : !DecorationDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT.equals(tooltipExpression);
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", position: "); //$NON-NLS-1$
        result.append(position);
        result.append(", distributionDirection: "); //$NON-NLS-1$
        result.append(distributionDirection);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(", imageExpression: "); //$NON-NLS-1$
        result.append(imageExpression);
        result.append(", tooltipExpression: "); //$NON-NLS-1$
        result.append(tooltipExpression);
        result.append(')');
        return result.toString();
    }

} // DecorationDescriptionImpl
