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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.SingleReferenceDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Single Reference Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl#getDisplayExpression
 * <em>Display Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl#getCreateOperation
 * <em>Create Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl#getSearchOperation
 * <em>Search Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl#getUnsetOperation
 * <em>Unset Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl#getOnClickOperation
 * <em>On Click Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SingleReferenceDescriptionImpl extends WidgetDescriptionImpl implements SingleReferenceDescription {
    /**
     * The default value of the '{@link #getValueExpression()
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValueExpression()
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected String valueExpression = SingleReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getDisplayExpression()
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDisplayExpression()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayExpression()
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDisplayExpression()
     * @generated
     * @ordered
     */
    protected String displayExpression = SingleReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getCreateOperation()
     * <em>Create Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getCreateOperation()
     * @generated
     * @ordered
     */
    protected OperationDescription createOperation;

    /**
     * The cached value of the '{@link #getSearchOperation()
     * <em>Search Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getSearchOperation()
     * @generated
     * @ordered
     */
    protected OperationDescription searchOperation;

    /**
     * The cached value of the '{@link #getUnsetOperation()
     * <em>Unset Operation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getUnsetOperation()
     * @generated
     * @ordered
     */
    protected OperationDescription unsetOperation;

    /**
     * The cached value of the '{@link #getOnClickOperation()
     * <em>On Click Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getOnClickOperation()
     * @generated
     * @ordered
     */
    protected OperationDescription onClickOperation;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SingleReferenceDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.SINGLE_REFERENCE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDisplayExpression() {
        return displayExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDisplayExpression(String newDisplayExpression) {
        String oldDisplayExpression = displayExpression;
        displayExpression = newDisplayExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationDescription getCreateOperation() {
        return createOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCreateOperation(OperationDescription newCreateOperation, NotificationChain msgs) {
        OperationDescription oldCreateOperation = createOperation;
        createOperation = newCreateOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION, oldCreateOperation, newCreateOperation);
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
    public void setCreateOperation(OperationDescription newCreateOperation) {
        if (newCreateOperation != createOperation) {
            NotificationChain msgs = null;
            if (createOperation != null) {
                msgs = ((InternalEObject) createOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION, null, msgs);
            }
            if (newCreateOperation != null) {
                msgs = ((InternalEObject) newCreateOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION, null, msgs);
            }
            msgs = basicSetCreateOperation(newCreateOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION, newCreateOperation, newCreateOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationDescription getSearchOperation() {
        return searchOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSearchOperation(OperationDescription newSearchOperation, NotificationChain msgs) {
        OperationDescription oldSearchOperation = searchOperation;
        searchOperation = newSearchOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION, oldSearchOperation, newSearchOperation);
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
    public void setSearchOperation(OperationDescription newSearchOperation) {
        if (newSearchOperation != searchOperation) {
            NotificationChain msgs = null;
            if (searchOperation != null) {
                msgs = ((InternalEObject) searchOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION, null, msgs);
            }
            if (newSearchOperation != null) {
                msgs = ((InternalEObject) newSearchOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION, null, msgs);
            }
            msgs = basicSetSearchOperation(newSearchOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION, newSearchOperation, newSearchOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationDescription getUnsetOperation() {
        return unsetOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetUnsetOperation(OperationDescription newUnsetOperation, NotificationChain msgs) {
        OperationDescription oldUnsetOperation = unsetOperation;
        unsetOperation = newUnsetOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION, oldUnsetOperation, newUnsetOperation);
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
    public void setUnsetOperation(OperationDescription newUnsetOperation) {
        if (newUnsetOperation != unsetOperation) {
            NotificationChain msgs = null;
            if (unsetOperation != null) {
                msgs = ((InternalEObject) unsetOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION, null, msgs);
            }
            if (newUnsetOperation != null) {
                msgs = ((InternalEObject) newUnsetOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION, null, msgs);
            }
            msgs = basicSetUnsetOperation(newUnsetOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION, newUnsetOperation, newUnsetOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationDescription getOnClickOperation() {
        return onClickOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOnClickOperation(OperationDescription newOnClickOperation, NotificationChain msgs) {
        OperationDescription oldOnClickOperation = onClickOperation;
        onClickOperation = newOnClickOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, oldOnClickOperation,
                    newOnClickOperation);
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
    public void setOnClickOperation(OperationDescription newOnClickOperation) {
        if (newOnClickOperation != onClickOperation) {
            NotificationChain msgs = null;
            if (onClickOperation != null) {
                msgs = ((InternalEObject) onClickOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, null,
                        msgs);
            }
            if (newOnClickOperation != null) {
                msgs = ((InternalEObject) newOnClickOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, null,
                        msgs);
            }
            msgs = basicSetOnClickOperation(newOnClickOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION, newOnClickOperation, newOnClickOperation));
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
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION:
            return basicSetCreateOperation(null, msgs);
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION:
            return basicSetSearchOperation(null, msgs);
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION:
            return basicSetUnsetOperation(null, msgs);
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return basicSetOnClickOperation(null, msgs);
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
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION:
            return getCreateOperation();
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION:
            return getSearchOperation();
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION:
            return getUnsetOperation();
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return getOnClickOperation();
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
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION:
            setCreateOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION:
            setSearchOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION:
            setUnsetOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((OperationDescription) newValue);
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
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(SingleReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(SingleReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION:
            setCreateOperation((OperationDescription) null);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION:
            setSearchOperation((OperationDescription) null);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION:
            setUnsetOperation((OperationDescription) null);
            return;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((OperationDescription) null);
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
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION:
            return SingleReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !SingleReferenceDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION:
            return SingleReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null
                    : !SingleReferenceDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT.equals(displayExpression);
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION:
            return createOperation != null;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION:
            return searchOperation != null;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION:
            return unsetOperation != null;
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION:
            return onClickOperation != null;
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
        result.append(" (valueExpression: ");
        result.append(valueExpression);
        result.append(", displayExpression: ");
        result.append(displayExpression);
        result.append(')');
        return result.toString();
    }

} // SingleReferenceDescriptionImpl
