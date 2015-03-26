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
package org.eclipse.sirius.diagram.description.filter.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.FilterVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterVariableImpl#getCandidatesExpression
 * <em>Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterVariableImpl#isMultiple
 * <em>Multiple</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterVariableImpl#isTree
 * <em>Tree</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterVariableImpl#getRootExpression
 * <em>Root Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterVariableImpl#getChildrenExpression
 * <em>Children Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterVariableImpl#getMessage
 * <em>Message</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterVariableImpl#getName
 * <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterVariableImpl extends MinimalEObjectImpl.Container implements FilterVariable {
    /**
     * The default value of the '{@link #getCandidatesExpression()
     * <em>Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected static final String CANDIDATES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCandidatesExpression()
     * <em>Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected String candidatesExpression = FilterVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isMultiple() <em>Multiple</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isMultiple()
     * @generated
     * @ordered
     */
    protected static final boolean MULTIPLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMultiple() <em>Multiple</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isMultiple()
     * @generated
     * @ordered
     */
    protected boolean multiple = FilterVariableImpl.MULTIPLE_EDEFAULT;

    /**
     * The default value of the '{@link #isTree() <em>Tree</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isTree()
     * @generated
     * @ordered
     */
    protected static final boolean TREE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTree() <em>Tree</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isTree()
     * @generated
     * @ordered
     */
    protected boolean tree = FilterVariableImpl.TREE_EDEFAULT;

    /**
     * The default value of the '{@link #getRootExpression()
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRootExpression()
     * @generated
     * @ordered
     */
    protected static final String ROOT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRootExpression()
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRootExpression()
     * @generated
     * @ordered
     */
    protected String rootExpression = FilterVariableImpl.ROOT_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getChildrenExpression()
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getChildrenExpression()
     * @generated
     * @ordered
     */
    protected static final String CHILDREN_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getChildrenExpression()
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getChildrenExpression()
     * @generated
     * @ordered
     */
    protected String childrenExpression = FilterVariableImpl.CHILDREN_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected static final String MESSAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected String message = FilterVariableImpl.MESSAGE_EDEFAULT;

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
    protected String name = FilterVariableImpl.NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FilterVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FilterPackage.Literals.FILTER_VARIABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getCandidatesExpression() {
        return candidatesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCandidatesExpression(String newCandidatesExpression) {
        String oldCandidatesExpression = candidatesExpression;
        candidatesExpression = newCandidatesExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER_VARIABLE__CANDIDATES_EXPRESSION, oldCandidatesExpression, candidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isMultiple() {
        return multiple;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMultiple(boolean newMultiple) {
        boolean oldMultiple = multiple;
        multiple = newMultiple;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER_VARIABLE__MULTIPLE, oldMultiple, multiple));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isTree() {
        return tree;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTree(boolean newTree) {
        boolean oldTree = tree;
        tree = newTree;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER_VARIABLE__TREE, oldTree, tree));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getRootExpression() {
        return rootExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRootExpression(String newRootExpression) {
        String oldRootExpression = rootExpression;
        rootExpression = newRootExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER_VARIABLE__ROOT_EXPRESSION, oldRootExpression, rootExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getChildrenExpression() {
        return childrenExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setChildrenExpression(String newChildrenExpression) {
        String oldChildrenExpression = childrenExpression;
        childrenExpression = newChildrenExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER_VARIABLE__CHILDREN_EXPRESSION, oldChildrenExpression, childrenExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMessage(String newMessage) {
        String oldMessage = message;
        message = newMessage;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER_VARIABLE__MESSAGE, oldMessage, message));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER_VARIABLE__NAME, oldName, name));
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
        case FilterPackage.FILTER_VARIABLE__CANDIDATES_EXPRESSION:
            return getCandidatesExpression();
        case FilterPackage.FILTER_VARIABLE__MULTIPLE:
            return isMultiple();
        case FilterPackage.FILTER_VARIABLE__TREE:
            return isTree();
        case FilterPackage.FILTER_VARIABLE__ROOT_EXPRESSION:
            return getRootExpression();
        case FilterPackage.FILTER_VARIABLE__CHILDREN_EXPRESSION:
            return getChildrenExpression();
        case FilterPackage.FILTER_VARIABLE__MESSAGE:
            return getMessage();
        case FilterPackage.FILTER_VARIABLE__NAME:
            return getName();
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
        case FilterPackage.FILTER_VARIABLE__CANDIDATES_EXPRESSION:
            setCandidatesExpression((String) newValue);
            return;
        case FilterPackage.FILTER_VARIABLE__MULTIPLE:
            setMultiple((Boolean) newValue);
            return;
        case FilterPackage.FILTER_VARIABLE__TREE:
            setTree((Boolean) newValue);
            return;
        case FilterPackage.FILTER_VARIABLE__ROOT_EXPRESSION:
            setRootExpression((String) newValue);
            return;
        case FilterPackage.FILTER_VARIABLE__CHILDREN_EXPRESSION:
            setChildrenExpression((String) newValue);
            return;
        case FilterPackage.FILTER_VARIABLE__MESSAGE:
            setMessage((String) newValue);
            return;
        case FilterPackage.FILTER_VARIABLE__NAME:
            setName((String) newValue);
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
        case FilterPackage.FILTER_VARIABLE__CANDIDATES_EXPRESSION:
            setCandidatesExpression(FilterVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case FilterPackage.FILTER_VARIABLE__MULTIPLE:
            setMultiple(FilterVariableImpl.MULTIPLE_EDEFAULT);
            return;
        case FilterPackage.FILTER_VARIABLE__TREE:
            setTree(FilterVariableImpl.TREE_EDEFAULT);
            return;
        case FilterPackage.FILTER_VARIABLE__ROOT_EXPRESSION:
            setRootExpression(FilterVariableImpl.ROOT_EXPRESSION_EDEFAULT);
            return;
        case FilterPackage.FILTER_VARIABLE__CHILDREN_EXPRESSION:
            setChildrenExpression(FilterVariableImpl.CHILDREN_EXPRESSION_EDEFAULT);
            return;
        case FilterPackage.FILTER_VARIABLE__MESSAGE:
            setMessage(FilterVariableImpl.MESSAGE_EDEFAULT);
            return;
        case FilterPackage.FILTER_VARIABLE__NAME:
            setName(FilterVariableImpl.NAME_EDEFAULT);
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
        case FilterPackage.FILTER_VARIABLE__CANDIDATES_EXPRESSION:
            return FilterVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT == null ? candidatesExpression != null : !FilterVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT.equals(candidatesExpression);
        case FilterPackage.FILTER_VARIABLE__MULTIPLE:
            return multiple != FilterVariableImpl.MULTIPLE_EDEFAULT;
        case FilterPackage.FILTER_VARIABLE__TREE:
            return tree != FilterVariableImpl.TREE_EDEFAULT;
        case FilterPackage.FILTER_VARIABLE__ROOT_EXPRESSION:
            return FilterVariableImpl.ROOT_EXPRESSION_EDEFAULT == null ? rootExpression != null : !FilterVariableImpl.ROOT_EXPRESSION_EDEFAULT.equals(rootExpression);
        case FilterPackage.FILTER_VARIABLE__CHILDREN_EXPRESSION:
            return FilterVariableImpl.CHILDREN_EXPRESSION_EDEFAULT == null ? childrenExpression != null : !FilterVariableImpl.CHILDREN_EXPRESSION_EDEFAULT.equals(childrenExpression);
        case FilterPackage.FILTER_VARIABLE__MESSAGE:
            return FilterVariableImpl.MESSAGE_EDEFAULT == null ? message != null : !FilterVariableImpl.MESSAGE_EDEFAULT.equals(message);
        case FilterPackage.FILTER_VARIABLE__NAME:
            return FilterVariableImpl.NAME_EDEFAULT == null ? name != null : !FilterVariableImpl.NAME_EDEFAULT.equals(name);
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
        result.append(" (candidatesExpression: ");
        result.append(candidatesExpression);
        result.append(", multiple: ");
        result.append(multiple);
        result.append(", tree: ");
        result.append(tree);
        result.append(", rootExpression: ");
        result.append(rootExpression);
        result.append(", childrenExpression: ");
        result.append(childrenExpression);
        result.append(", message: ");
        result.append(message);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // FilterVariableImpl
