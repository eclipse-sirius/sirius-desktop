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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Select Model Element Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl#getCandidatesExpression
 * <em>Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl#isMultiple
 * <em>Multiple</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl#isTree
 * <em>Tree</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl#getRootExpression
 * <em>Root Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl#getChildrenExpression
 * <em>Children Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl#getMessage
 * <em>Message</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelectModelElementVariableImpl extends SubVariableImpl implements SelectModelElementVariable {
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
    protected String candidatesExpression = SelectModelElementVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT;

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
    protected boolean multiple = SelectModelElementVariableImpl.MULTIPLE_EDEFAULT;

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
    protected boolean tree = SelectModelElementVariableImpl.TREE_EDEFAULT;

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
    protected String rootExpression = SelectModelElementVariableImpl.ROOT_EXPRESSION_EDEFAULT;

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
    protected String childrenExpression = SelectModelElementVariableImpl.CHILDREN_EXPRESSION_EDEFAULT;

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
    protected String message = SelectModelElementVariableImpl.MESSAGE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SelectModelElementVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.SELECT_MODEL_ELEMENT_VARIABLE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION, oldCandidatesExpression, candidatesExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE, oldMultiple, multiple));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__TREE, oldTree, tree));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION, oldRootExpression, rootExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION, oldChildrenExpression, childrenExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE, oldMessage, message));
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
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION:
            return getCandidatesExpression();
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE:
            return isMultiple();
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__TREE:
            return isTree();
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION:
            return getRootExpression();
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION:
            return getChildrenExpression();
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE:
            return getMessage();
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
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION:
            setCandidatesExpression((String) newValue);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE:
            setMultiple((Boolean) newValue);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__TREE:
            setTree((Boolean) newValue);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION:
            setRootExpression((String) newValue);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION:
            setChildrenExpression((String) newValue);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE:
            setMessage((String) newValue);
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
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION:
            setCandidatesExpression(SelectModelElementVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE:
            setMultiple(SelectModelElementVariableImpl.MULTIPLE_EDEFAULT);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__TREE:
            setTree(SelectModelElementVariableImpl.TREE_EDEFAULT);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION:
            setRootExpression(SelectModelElementVariableImpl.ROOT_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION:
            setChildrenExpression(SelectModelElementVariableImpl.CHILDREN_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE:
            setMessage(SelectModelElementVariableImpl.MESSAGE_EDEFAULT);
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
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION:
            return SelectModelElementVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT == null ? candidatesExpression != null : !SelectModelElementVariableImpl.CANDIDATES_EXPRESSION_EDEFAULT
                    .equals(candidatesExpression);
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE:
            return multiple != SelectModelElementVariableImpl.MULTIPLE_EDEFAULT;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__TREE:
            return tree != SelectModelElementVariableImpl.TREE_EDEFAULT;
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION:
            return SelectModelElementVariableImpl.ROOT_EXPRESSION_EDEFAULT == null ? rootExpression != null : !SelectModelElementVariableImpl.ROOT_EXPRESSION_EDEFAULT.equals(rootExpression);
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION:
            return SelectModelElementVariableImpl.CHILDREN_EXPRESSION_EDEFAULT == null ? childrenExpression != null : !SelectModelElementVariableImpl.CHILDREN_EXPRESSION_EDEFAULT
                    .equals(childrenExpression);
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE:
            return SelectModelElementVariableImpl.MESSAGE_EDEFAULT == null ? message != null : !SelectModelElementVariableImpl.MESSAGE_EDEFAULT.equals(message);
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
        if (baseClass == SelectionDescription.class) {
            switch (derivedFeatureID) {
            case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION:
                return DescriptionPackage.SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION;
            case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE:
                return DescriptionPackage.SELECTION_DESCRIPTION__MULTIPLE;
            case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__TREE:
                return DescriptionPackage.SELECTION_DESCRIPTION__TREE;
            case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION:
                return DescriptionPackage.SELECTION_DESCRIPTION__ROOT_EXPRESSION;
            case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION:
                return DescriptionPackage.SELECTION_DESCRIPTION__CHILDREN_EXPRESSION;
            case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE:
                return DescriptionPackage.SELECTION_DESCRIPTION__MESSAGE;
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
        if (baseClass == SelectionDescription.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION:
                return ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION;
            case DescriptionPackage.SELECTION_DESCRIPTION__MULTIPLE:
                return ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE;
            case DescriptionPackage.SELECTION_DESCRIPTION__TREE:
                return ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__TREE;
            case DescriptionPackage.SELECTION_DESCRIPTION__ROOT_EXPRESSION:
                return ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION;
            case DescriptionPackage.SELECTION_DESCRIPTION__CHILDREN_EXPRESSION:
                return ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION;
            case DescriptionPackage.SELECTION_DESCRIPTION__MESSAGE:
                return ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE;
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
        result.append(" (candidatesExpression: "); //$NON-NLS-1$
        result.append(candidatesExpression);
        result.append(", multiple: "); //$NON-NLS-1$
        result.append(multiple);
        result.append(", tree: "); //$NON-NLS-1$
        result.append(tree);
        result.append(", rootExpression: "); //$NON-NLS-1$
        result.append(rootExpression);
        result.append(", childrenExpression: "); //$NON-NLS-1$
        result.append(childrenExpression);
        result.append(", message: "); //$NON-NLS-1$
        result.append(message);
        result.append(')');
        return result.toString();
    }

} // SelectModelElementVariableImpl
