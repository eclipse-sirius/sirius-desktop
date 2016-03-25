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
import org.eclipse.sirius.properties.MultipleReferencesDescription;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Multiple References Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getDisplayExpression
 * <em>Display Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getCreateOperation
 * <em>Create Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getSearchOperation
 * <em>Search Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getUnsetOperation
 * <em>Unset Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getOnClickOperation
 * <em>On Click Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getUpOperation
 * <em>Up Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl#getDownOperation
 * <em>Down Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MultipleReferencesDescriptionImpl extends WidgetDescriptionImpl implements MultipleReferencesDescription {
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
    protected String valueExpression = MultipleReferencesDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

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
    protected String displayExpression = MultipleReferencesDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT;

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
     * The cached value of the '{@link #getUpOperation() <em>Up Operation</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getUpOperation()
     * @generated
     * @ordered
     */
    protected OperationDescription upOperation;

    /**
     * The cached value of the '{@link #getDownOperation()
     * <em>Down Operation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDownOperation()
     * @generated
     * @ordered
     */
    protected OperationDescription downOperation;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MultipleReferencesDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.MULTIPLE_REFERENCES_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION, oldDisplayExpression, displayExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION, oldCreateOperation, newCreateOperation);
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
                msgs = ((InternalEObject) createOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION, null,
                        msgs);
            }
            if (newCreateOperation != null) {
                msgs = ((InternalEObject) newCreateOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION, null,
                        msgs);
            }
            msgs = basicSetCreateOperation(newCreateOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION, newCreateOperation, newCreateOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION, oldSearchOperation, newSearchOperation);
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
                msgs = ((InternalEObject) searchOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION, null,
                        msgs);
            }
            if (newSearchOperation != null) {
                msgs = ((InternalEObject) newSearchOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION, null,
                        msgs);
            }
            msgs = basicSetSearchOperation(newSearchOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION, newSearchOperation, newSearchOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION, oldUnsetOperation, newUnsetOperation);
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
                msgs = ((InternalEObject) unsetOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION, null, msgs);
            }
            if (newUnsetOperation != null) {
                msgs = ((InternalEObject) newUnsetOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION, null, msgs);
            }
            msgs = basicSetUnsetOperation(newUnsetOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION, newUnsetOperation, newUnsetOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION, oldOnClickOperation,
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
                msgs = ((InternalEObject) onClickOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION, null,
                        msgs);
            }
            if (newOnClickOperation != null) {
                msgs = ((InternalEObject) newOnClickOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION, null,
                        msgs);
            }
            msgs = basicSetOnClickOperation(newOnClickOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION, newOnClickOperation, newOnClickOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationDescription getUpOperation() {
        return upOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetUpOperation(OperationDescription newUpOperation, NotificationChain msgs) {
        OperationDescription oldUpOperation = upOperation;
        upOperation = newUpOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION, oldUpOperation, newUpOperation);
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
    public void setUpOperation(OperationDescription newUpOperation) {
        if (newUpOperation != upOperation) {
            NotificationChain msgs = null;
            if (upOperation != null) {
                msgs = ((InternalEObject) upOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION, null, msgs);
            }
            if (newUpOperation != null) {
                msgs = ((InternalEObject) newUpOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION, null, msgs);
            }
            msgs = basicSetUpOperation(newUpOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION, newUpOperation, newUpOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationDescription getDownOperation() {
        return downOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDownOperation(OperationDescription newDownOperation, NotificationChain msgs) {
        OperationDescription oldDownOperation = downOperation;
        downOperation = newDownOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION, oldDownOperation, newDownOperation);
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
    public void setDownOperation(OperationDescription newDownOperation) {
        if (newDownOperation != downOperation) {
            NotificationChain msgs = null;
            if (downOperation != null) {
                msgs = ((InternalEObject) downOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION, null, msgs);
            }
            if (newDownOperation != null) {
                msgs = ((InternalEObject) newDownOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION, null, msgs);
            }
            msgs = basicSetDownOperation(newDownOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION, newDownOperation, newDownOperation));
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
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION:
            return basicSetCreateOperation(null, msgs);
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION:
            return basicSetSearchOperation(null, msgs);
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION:
            return basicSetUnsetOperation(null, msgs);
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION:
            return basicSetOnClickOperation(null, msgs);
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION:
            return basicSetUpOperation(null, msgs);
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION:
            return basicSetDownOperation(null, msgs);
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
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION:
            return getDisplayExpression();
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION:
            return getCreateOperation();
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION:
            return getSearchOperation();
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION:
            return getUnsetOperation();
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION:
            return getOnClickOperation();
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION:
            return getUpOperation();
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION:
            return getDownOperation();
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
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression((String) newValue);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION:
            setCreateOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION:
            setSearchOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION:
            setUnsetOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION:
            setUpOperation((OperationDescription) newValue);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION:
            setDownOperation((OperationDescription) newValue);
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
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(MultipleReferencesDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION:
            setDisplayExpression(MultipleReferencesDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION:
            setCreateOperation((OperationDescription) null);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION:
            setSearchOperation((OperationDescription) null);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION:
            setUnsetOperation((OperationDescription) null);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION:
            setOnClickOperation((OperationDescription) null);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION:
            setUpOperation((OperationDescription) null);
            return;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION:
            setDownOperation((OperationDescription) null);
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
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION:
            return MultipleReferencesDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !MultipleReferencesDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION:
            return MultipleReferencesDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT == null ? displayExpression != null : !MultipleReferencesDescriptionImpl.DISPLAY_EXPRESSION_EDEFAULT
            .equals(displayExpression);
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION:
            return createOperation != null;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION:
            return searchOperation != null;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION:
            return unsetOperation != null;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION:
            return onClickOperation != null;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION:
            return upOperation != null;
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION:
            return downOperation != null;
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

} // MultipleReferencesDescriptionImpl
