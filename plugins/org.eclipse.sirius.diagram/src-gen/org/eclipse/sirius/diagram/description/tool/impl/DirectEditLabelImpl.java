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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Direct Edit Label</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DirectEditLabelImpl#getMask
 * <em>Mask</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DirectEditLabelImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DirectEditLabelImpl#getInputLabelExpression
 * <em>Input Label Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DirectEditLabelImpl extends MappingBasedToolDescriptionImpl implements DirectEditLabel {
    /**
     * The cached value of the '{@link #getMask() <em>Mask</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMask()
     * @generated
     * @ordered
     */
    protected EditMaskVariables mask;

    /**
     * The cached value of the '{@link #getInitialOperation()
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation initialOperation;

    /**
     * The default value of the '{@link #getInputLabelExpression()
     * <em>Input Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getInputLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String INPUT_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInputLabelExpression()
     * <em>Input Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getInputLabelExpression()
     * @generated
     * @ordered
     */
    protected String inputLabelExpression = DirectEditLabelImpl.INPUT_LABEL_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DirectEditLabelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.DIRECT_EDIT_LABEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EditMaskVariables getMask() {
        if (mask != null && mask.eIsProxy()) {
            InternalEObject oldMask = (InternalEObject) mask;
            mask = (EditMaskVariables) eResolveProxy(oldMask);
            if (mask != oldMask) {
                InternalEObject newMask = (InternalEObject) mask;
                NotificationChain msgs = oldMask.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__MASK, null, null);
                if (newMask.eInternalContainer() == null) {
                    msgs = newMask.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__MASK, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DIRECT_EDIT_LABEL__MASK, oldMask, mask));
                }
            }
        }
        return mask;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EditMaskVariables basicGetMask() {
        return mask;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetMask(EditMaskVariables newMask, NotificationChain msgs) {
        EditMaskVariables oldMask = mask;
        mask = newMask;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DIRECT_EDIT_LABEL__MASK, oldMask, newMask);
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
    public void setMask(EditMaskVariables newMask) {
        if (newMask != mask) {
            NotificationChain msgs = null;
            if (mask != null) {
                msgs = ((InternalEObject) mask).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__MASK, null, msgs);
            }
            if (newMask != null) {
                msgs = ((InternalEObject) newMask).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__MASK, null, msgs);
            }
            msgs = basicSetMask(newMask, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DIRECT_EDIT_LABEL__MASK, newMask, newMask));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getInitialOperation() {
        if (initialOperation != null && initialOperation.eIsProxy()) {
            InternalEObject oldInitialOperation = (InternalEObject) initialOperation;
            initialOperation = (InitialOperation) eResolveProxy(oldInitialOperation);
            if (initialOperation != oldInitialOperation) {
                InternalEObject newInitialOperation = (InternalEObject) initialOperation;
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION, oldInitialOperation, initialOperation));
                }
            }
        }
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InitialOperation basicGetInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialOperation newInitialOperation, NotificationChain msgs) {
        InitialOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
    public void setInitialOperation(InitialOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getInputLabelExpression() {
        return inputLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInputLabelExpression(String newInputLabelExpression) {
        String oldInputLabelExpression = inputLabelExpression;
        inputLabelExpression = newInputLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION, oldInputLabelExpression, inputLabelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getMapping() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.DIRECT_EDIT_LABEL__MASK:
            return basicSetMask(null, msgs);
        case ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
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
        case ToolPackage.DIRECT_EDIT_LABEL__MASK:
            if (resolve) {
                return getMask();
            }
            return basicGetMask();
        case ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION:
            return getInputLabelExpression();
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
        case ToolPackage.DIRECT_EDIT_LABEL__MASK:
            setMask((EditMaskVariables) newValue);
            return;
        case ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case ToolPackage.DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION:
            setInputLabelExpression((String) newValue);
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
        case ToolPackage.DIRECT_EDIT_LABEL__MASK:
            setMask((EditMaskVariables) null);
            return;
        case ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case ToolPackage.DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION:
            setInputLabelExpression(DirectEditLabelImpl.INPUT_LABEL_EXPRESSION_EDEFAULT);
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
        case ToolPackage.DIRECT_EDIT_LABEL__MASK:
            return mask != null;
        case ToolPackage.DIRECT_EDIT_LABEL__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION:
            return DirectEditLabelImpl.INPUT_LABEL_EXPRESSION_EDEFAULT == null ? inputLabelExpression != null : !DirectEditLabelImpl.INPUT_LABEL_EXPRESSION_EDEFAULT.equals(inputLabelExpression);
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
        result.append(" (inputLabelExpression: "); //$NON-NLS-1$
        result.append(inputLabelExpression);
        result.append(')');
        return result.toString();
    }

} // DirectEditLabelImpl
