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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.DialogButton;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ModelOperationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Dialog Model Operation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogModelOperationImpl#getTitleExpression <em>Title
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogModelOperationImpl#getButtons <em>Buttons</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogModelOperationImpl#getPage <em>Page</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogModelOperationImpl#getGroups <em>Groups</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DialogModelOperationImpl extends ModelOperationImpl implements DialogModelOperation {
    /**
     * The default value of the '{@link #getTitleExpression() <em>Title Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTitleExpression() <em>Title Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected String titleExpression = DialogModelOperationImpl.TITLE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getButtons() <em>Buttons</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getButtons()
     * @generated
     * @ordered
     */
    protected EList<DialogButton> buttons;

    /**
     * The cached value of the '{@link #getPage() <em>Page</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPage()
     * @generated
     * @ordered
     */
    protected PageDescription page;

    /**
     * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getGroups()
     * @generated
     * @ordered
     */
    protected EList<GroupDescription> groups;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DialogModelOperationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.DIALOG_MODEL_OPERATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTitleExpression() {
        return titleExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTitleExpression(String newTitleExpression) {
        String oldTitleExpression = titleExpression;
        titleExpression = newTitleExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_MODEL_OPERATION__TITLE_EXPRESSION, oldTitleExpression, titleExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DialogButton> getButtons() {
        if (buttons == null) {
            buttons = new EObjectContainmentEList<DialogButton>(DialogButton.class, this, PropertiesPackage.DIALOG_MODEL_OPERATION__BUTTONS);
        }
        return buttons;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PageDescription getPage() {
        return page;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetPage(PageDescription newPage, NotificationChain msgs) {
        PageDescription oldPage = page;
        page = newPage;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE, oldPage, newPage);
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
    public void setPage(PageDescription newPage) {
        if (newPage != page) {
            NotificationChain msgs = null;
            if (page != null) {
                msgs = ((InternalEObject) page).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE, null, msgs);
            }
            if (newPage != null) {
                msgs = ((InternalEObject) newPage).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE, null, msgs);
            }
            msgs = basicSetPage(newPage, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE, newPage, newPage));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GroupDescription> getGroups() {
        if (groups == null) {
            groups = new EObjectContainmentEList<GroupDescription>(GroupDescription.class, this, PropertiesPackage.DIALOG_MODEL_OPERATION__GROUPS);
        }
        return groups;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.DIALOG_MODEL_OPERATION__BUTTONS:
            return ((InternalEList<?>) getButtons()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE:
            return basicSetPage(null, msgs);
        case PropertiesPackage.DIALOG_MODEL_OPERATION__GROUPS:
            return ((InternalEList<?>) getGroups()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.DIALOG_MODEL_OPERATION__TITLE_EXPRESSION:
            return getTitleExpression();
        case PropertiesPackage.DIALOG_MODEL_OPERATION__BUTTONS:
            return getButtons();
        case PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE:
            return getPage();
        case PropertiesPackage.DIALOG_MODEL_OPERATION__GROUPS:
            return getGroups();
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
        case PropertiesPackage.DIALOG_MODEL_OPERATION__TITLE_EXPRESSION:
            setTitleExpression((String) newValue);
            return;
        case PropertiesPackage.DIALOG_MODEL_OPERATION__BUTTONS:
            getButtons().clear();
            getButtons().addAll((Collection<? extends DialogButton>) newValue);
            return;
        case PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE:
            setPage((PageDescription) newValue);
            return;
        case PropertiesPackage.DIALOG_MODEL_OPERATION__GROUPS:
            getGroups().clear();
            getGroups().addAll((Collection<? extends GroupDescription>) newValue);
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
        case PropertiesPackage.DIALOG_MODEL_OPERATION__TITLE_EXPRESSION:
            setTitleExpression(DialogModelOperationImpl.TITLE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.DIALOG_MODEL_OPERATION__BUTTONS:
            getButtons().clear();
            return;
        case PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE:
            setPage((PageDescription) null);
            return;
        case PropertiesPackage.DIALOG_MODEL_OPERATION__GROUPS:
            getGroups().clear();
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
        case PropertiesPackage.DIALOG_MODEL_OPERATION__TITLE_EXPRESSION:
            return DialogModelOperationImpl.TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null : !DialogModelOperationImpl.TITLE_EXPRESSION_EDEFAULT.equals(titleExpression);
        case PropertiesPackage.DIALOG_MODEL_OPERATION__BUTTONS:
            return buttons != null && !buttons.isEmpty();
        case PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE:
            return page != null;
        case PropertiesPackage.DIALOG_MODEL_OPERATION__GROUPS:
            return groups != null && !groups.isEmpty();
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
        result.append(" (titleExpression: "); //$NON-NLS-1$
        result.append(titleExpression);
        result.append(')');
        return result.toString();
    }

} // DialogModelOperationImpl
