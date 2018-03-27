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
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.ModelOperationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Wizard Model Operation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl#getWindowTitleExpression <em>Window Title
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl#getTitleExpression <em>Title
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl#getDescriptionExpression <em>Description
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl#getIsPageCompleteExpression <em>Is Page
 * Complete Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl#getPages <em>Pages</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl#getGroups <em>Groups</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WizardModelOperationImpl extends ModelOperationImpl implements WizardModelOperation {
    /**
     * The default value of the '{@link #getWindowTitleExpression() <em>Window Title Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWindowTitleExpression()
     * @generated
     * @ordered
     */
    protected static final String WINDOW_TITLE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWindowTitleExpression() <em>Window Title Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWindowTitleExpression()
     * @generated
     * @ordered
     */
    protected String windowTitleExpression = WizardModelOperationImpl.WINDOW_TITLE_EXPRESSION_EDEFAULT;

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
    protected String titleExpression = WizardModelOperationImpl.TITLE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getDescriptionExpression() <em>Description Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescriptionExpression()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescriptionExpression() <em>Description Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescriptionExpression()
     * @generated
     * @ordered
     */
    protected String descriptionExpression = WizardModelOperationImpl.DESCRIPTION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getIsPageCompleteExpression() <em>Is Page Complete Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIsPageCompleteExpression()
     * @generated
     * @ordered
     */
    protected static final String IS_PAGE_COMPLETE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIsPageCompleteExpression() <em>Is Page Complete Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIsPageCompleteExpression()
     * @generated
     * @ordered
     */
    protected String isPageCompleteExpression = WizardModelOperationImpl.IS_PAGE_COMPLETE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getPages() <em>Pages</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getPages()
     * @generated
     * @ordered
     */
    protected EList<PageDescription> pages;

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
     * The cached value of the '{@link #getInitialOperation() <em>Initial Operation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation initialOperation;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected WizardModelOperationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.WIZARD_MODEL_OPERATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getWindowTitleExpression() {
        return windowTitleExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWindowTitleExpression(String newWindowTitleExpression) {
        String oldWindowTitleExpression = windowTitleExpression;
        windowTitleExpression = newWindowTitleExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION, oldWindowTitleExpression, windowTitleExpression));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.WIZARD_MODEL_OPERATION__TITLE_EXPRESSION, oldTitleExpression, titleExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDescriptionExpression() {
        return descriptionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescriptionExpression(String newDescriptionExpression) {
        String oldDescriptionExpression = descriptionExpression;
        descriptionExpression = newDescriptionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION, oldDescriptionExpression, descriptionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIsPageCompleteExpression() {
        return isPageCompleteExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIsPageCompleteExpression(String newIsPageCompleteExpression) {
        String oldIsPageCompleteExpression = isPageCompleteExpression;
        isPageCompleteExpression = newIsPageCompleteExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION, oldIsPageCompleteExpression, isPageCompleteExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PageDescription> getPages() {
        if (pages == null) {
            pages = new EObjectContainmentEList<PageDescription>(PageDescription.class, this, PropertiesPackage.WIZARD_MODEL_OPERATION__PAGES);
        }
        return pages;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GroupDescription> getGroups() {
        if (groups == null) {
            groups = new EObjectContainmentEList<GroupDescription>(GroupDescription.class, this, PropertiesPackage.WIZARD_MODEL_OPERATION__GROUPS);
        }
        return groups;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getInitialOperation() {
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
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
        case PropertiesPackage.WIZARD_MODEL_OPERATION__PAGES:
            return ((InternalEList<?>) getPages()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.WIZARD_MODEL_OPERATION__GROUPS:
            return ((InternalEList<?>) getGroups()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION:
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
        case PropertiesPackage.WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION:
            return getWindowTitleExpression();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__TITLE_EXPRESSION:
            return getTitleExpression();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION:
            return getDescriptionExpression();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION:
            return getIsPageCompleteExpression();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__PAGES:
            return getPages();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__GROUPS:
            return getGroups();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION:
            return getInitialOperation();
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
        case PropertiesPackage.WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION:
            setWindowTitleExpression((String) newValue);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__TITLE_EXPRESSION:
            setTitleExpression((String) newValue);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION:
            setDescriptionExpression((String) newValue);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION:
            setIsPageCompleteExpression((String) newValue);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__PAGES:
            getPages().clear();
            getPages().addAll((Collection<? extends PageDescription>) newValue);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__GROUPS:
            getGroups().clear();
            getGroups().addAll((Collection<? extends GroupDescription>) newValue);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
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
        case PropertiesPackage.WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION:
            setWindowTitleExpression(WizardModelOperationImpl.WINDOW_TITLE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__TITLE_EXPRESSION:
            setTitleExpression(WizardModelOperationImpl.TITLE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION:
            setDescriptionExpression(WizardModelOperationImpl.DESCRIPTION_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION:
            setIsPageCompleteExpression(WizardModelOperationImpl.IS_PAGE_COMPLETE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__PAGES:
            getPages().clear();
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__GROUPS:
            getGroups().clear();
            return;
        case PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
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
        case PropertiesPackage.WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION:
            return WizardModelOperationImpl.WINDOW_TITLE_EXPRESSION_EDEFAULT == null ? windowTitleExpression != null
                    : !WizardModelOperationImpl.WINDOW_TITLE_EXPRESSION_EDEFAULT.equals(windowTitleExpression);
        case PropertiesPackage.WIZARD_MODEL_OPERATION__TITLE_EXPRESSION:
            return WizardModelOperationImpl.TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null : !WizardModelOperationImpl.TITLE_EXPRESSION_EDEFAULT.equals(titleExpression);
        case PropertiesPackage.WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION:
            return WizardModelOperationImpl.DESCRIPTION_EXPRESSION_EDEFAULT == null ? descriptionExpression != null
                    : !WizardModelOperationImpl.DESCRIPTION_EXPRESSION_EDEFAULT.equals(descriptionExpression);
        case PropertiesPackage.WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION:
            return WizardModelOperationImpl.IS_PAGE_COMPLETE_EXPRESSION_EDEFAULT == null ? isPageCompleteExpression != null
                    : !WizardModelOperationImpl.IS_PAGE_COMPLETE_EXPRESSION_EDEFAULT.equals(isPageCompleteExpression);
        case PropertiesPackage.WIZARD_MODEL_OPERATION__PAGES:
            return pages != null && !pages.isEmpty();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__GROUPS:
            return groups != null && !groups.isEmpty();
        case PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION:
            return initialOperation != null;
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
        result.append(" (windowTitleExpression: "); //$NON-NLS-1$
        result.append(windowTitleExpression);
        result.append(", titleExpression: "); //$NON-NLS-1$
        result.append(titleExpression);
        result.append(", descriptionExpression: "); //$NON-NLS-1$
        result.append(descriptionExpression);
        result.append(", isPageCompleteExpression: "); //$NON-NLS-1$
        result.append(isPageCompleteExpression);
        result.append(')');
        return result.toString();
    }

} // WizardModelOperationImpl
