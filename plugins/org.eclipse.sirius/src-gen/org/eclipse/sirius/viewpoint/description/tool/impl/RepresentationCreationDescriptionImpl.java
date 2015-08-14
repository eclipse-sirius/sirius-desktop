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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Representation Creation Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getTitleExpression
 * <em>Title Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getBrowseExpression
 * <em>Browse Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getRepresentationDescription
 * <em>Representation Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getContainerViewVariable
 * <em>Container View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getRepresentationNameVariable
 * <em>Representation Name Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class RepresentationCreationDescriptionImpl extends AbstractToolDescriptionImpl implements RepresentationCreationDescription {
    /**
     * The default value of the '{@link #getTitleExpression()
     * <em>Title Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getTitleExpression()
     * <em>Title Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected String titleExpression = RepresentationCreationDescriptionImpl.TITLE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getBrowseExpression()
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBrowseExpression()
     * @generated
     * @ordered
     */
    protected static final String BROWSE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBrowseExpression()
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBrowseExpression()
     * @generated
     * @ordered
     */
    protected String browseExpression = RepresentationCreationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT;

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
     * The cached value of the '{@link #getContainerViewVariable()
     * <em>Container View Variable</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContainerViewVariable()
     * @generated
     * @ordered
     */
    protected ContainerViewVariable containerViewVariable;

    /**
     * The cached value of the '{@link #getRepresentationNameVariable()
     * <em>Representation Name Variable</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRepresentationNameVariable()
     * @generated
     * @ordered
     */
    protected NameVariable representationNameVariable;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected RepresentationCreationDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.REPRESENTATION_CREATION_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION, oldTitleExpression, titleExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getBrowseExpression() {
        return browseExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBrowseExpression(String newBrowseExpression) {
        String oldBrowseExpression = browseExpression;
        browseExpression = newBrowseExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION, oldBrowseExpression, browseExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RepresentationDescription getRepresentationDescription() {
        RepresentationDescription representationDescription = basicGetRepresentationDescription();
        return representationDescription != null && representationDescription.eIsProxy() ? (RepresentationDescription) eResolveProxy((InternalEObject) representationDescription)
                : representationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RepresentationDescription basicGetRepresentationDescription() {
        // TODO: implement this method to return the 'Representation
        // Description' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
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
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION, null,
                        null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ContainerViewVariable getContainerViewVariable() {
        if (containerViewVariable != null && containerViewVariable.eIsProxy()) {
            InternalEObject oldContainerViewVariable = (InternalEObject) containerViewVariable;
            containerViewVariable = (ContainerViewVariable) eResolveProxy(oldContainerViewVariable);
            if (containerViewVariable != oldContainerViewVariable) {
                InternalEObject newContainerViewVariable = (InternalEObject) containerViewVariable;
                NotificationChain msgs = oldContainerViewVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, null);
                if (newContainerViewVariable.eInternalContainer() == null) {
                    msgs = newContainerViewVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, oldContainerViewVariable, containerViewVariable));
                }
            }
        }
        return containerViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerViewVariable basicGetContainerViewVariable() {
        return containerViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainerViewVariable(ContainerViewVariable newContainerViewVariable, NotificationChain msgs) {
        ContainerViewVariable oldContainerViewVariable = containerViewVariable;
        containerViewVariable = newContainerViewVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, oldContainerViewVariable,
                    newContainerViewVariable);
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
    public void setContainerViewVariable(ContainerViewVariable newContainerViewVariable) {
        if (newContainerViewVariable != containerViewVariable) {
            NotificationChain msgs = null;
            if (containerViewVariable != null) {
                msgs = ((InternalEObject) containerViewVariable).eInverseRemove(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, msgs);
            }
            if (newContainerViewVariable != null) {
                msgs = ((InternalEObject) newContainerViewVariable).eInverseAdd(this,
                        InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, msgs);
            }
            msgs = basicSetContainerViewVariable(newContainerViewVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, newContainerViewVariable, newContainerViewVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NameVariable getRepresentationNameVariable() {
        if (representationNameVariable != null && representationNameVariable.eIsProxy()) {
            InternalEObject oldRepresentationNameVariable = (InternalEObject) representationNameVariable;
            representationNameVariable = (NameVariable) eResolveProxy(oldRepresentationNameVariable);
            if (representationNameVariable != oldRepresentationNameVariable) {
                InternalEObject newRepresentationNameVariable = (InternalEObject) representationNameVariable;
                NotificationChain msgs = oldRepresentationNameVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, null, null);
                if (newRepresentationNameVariable.eInternalContainer() == null) {
                    msgs = newRepresentationNameVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE,
                            null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, oldRepresentationNameVariable,
                            representationNameVariable));
                }
            }
        }
        return representationNameVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NameVariable basicGetRepresentationNameVariable() {
        return representationNameVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetRepresentationNameVariable(NameVariable newRepresentationNameVariable, NotificationChain msgs) {
        NameVariable oldRepresentationNameVariable = representationNameVariable;
        representationNameVariable = newRepresentationNameVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE,
                    oldRepresentationNameVariable, newRepresentationNameVariable);
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
    public void setRepresentationNameVariable(NameVariable newRepresentationNameVariable) {
        if (newRepresentationNameVariable != representationNameVariable) {
            NotificationChain msgs = null;
            if (representationNameVariable != null) {
                msgs = ((InternalEObject) representationNameVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, null, msgs);
            }
            if (newRepresentationNameVariable != null) {
                msgs = ((InternalEObject) newRepresentationNameVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, null, msgs);
            }
            msgs = basicSetRepresentationNameVariable(newRepresentationNameVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, newRepresentationNameVariable,
                    newRepresentationNameVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationElementMapping> getMappings() {
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
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            return basicSetContainerViewVariable(null, msgs);
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
            return basicSetRepresentationNameVariable(null, msgs);
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
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION:
            return getTitleExpression();
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION:
            return getBrowseExpression();
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION:
            if (resolve) {
                return getRepresentationDescription();
            }
            return basicGetRepresentationDescription();
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            if (resolve) {
                return getContainerViewVariable();
            }
            return basicGetContainerViewVariable();
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
            if (resolve) {
                return getRepresentationNameVariable();
            }
            return basicGetRepresentationNameVariable();
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
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression((String) newValue);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION:
            setBrowseExpression((String) newValue);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            setContainerViewVariable((ContainerViewVariable) newValue);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
            setRepresentationNameVariable((NameVariable) newValue);
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
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression(RepresentationCreationDescriptionImpl.TITLE_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION:
            setBrowseExpression(RepresentationCreationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            setContainerViewVariable((ContainerViewVariable) null);
            return;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
            setRepresentationNameVariable((NameVariable) null);
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
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION:
            return RepresentationCreationDescriptionImpl.TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null : !RepresentationCreationDescriptionImpl.TITLE_EXPRESSION_EDEFAULT
                    .equals(titleExpression);
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION:
            return RepresentationCreationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT == null ? browseExpression != null : !RepresentationCreationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT
                    .equals(browseExpression);
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION:
            return basicGetRepresentationDescription() != null;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            return containerViewVariable != null;
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
            return representationNameVariable != null;
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
        result.append(", browseExpression: "); //$NON-NLS-1$
        result.append(browseExpression);
        result.append(')');
        return result.toString();
    }

} // RepresentationCreationDescriptionImpl
