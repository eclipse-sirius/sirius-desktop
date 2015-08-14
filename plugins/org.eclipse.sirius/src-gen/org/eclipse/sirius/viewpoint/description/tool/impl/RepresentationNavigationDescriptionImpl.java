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
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Representation Navigation Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl#getBrowseExpression
 * <em>Browse Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl#getNavigationNameExpression
 * <em>Navigation Name Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl#getRepresentationDescription
 * <em>Representation Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl#getContainerViewVariable
 * <em>Container View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl#getContainerVariable
 * <em>Container Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl#getRepresentationNameVariable
 * <em>Representation Name Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class RepresentationNavigationDescriptionImpl extends AbstractToolDescriptionImpl implements RepresentationNavigationDescription {
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
    protected String browseExpression = RepresentationNavigationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getNavigationNameExpression()
     * <em>Navigation Name Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getNavigationNameExpression()
     * @generated
     * @ordered
     */
    protected static final String NAVIGATION_NAME_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNavigationNameExpression()
     * <em>Navigation Name Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getNavigationNameExpression()
     * @generated
     * @ordered
     */
    protected String navigationNameExpression = RepresentationNavigationDescriptionImpl.NAVIGATION_NAME_EXPRESSION_EDEFAULT;

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
     * The cached value of the '{@link #getContainerVariable()
     * <em>Container Variable</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getContainerVariable()
     * @generated
     * @ordered
     */
    protected ElementSelectVariable containerVariable;

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
    protected RepresentationNavigationDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.REPRESENTATION_NAVIGATION_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION, oldBrowseExpression, browseExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getNavigationNameExpression() {
        return navigationNameExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setNavigationNameExpression(String newNavigationNameExpression) {
        String oldNavigationNameExpression = navigationNameExpression;
        navigationNameExpression = newNavigationNameExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION, oldNavigationNameExpression, navigationNameExpression));
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
    public ContainerViewVariable getContainerViewVariable() {
        if (containerViewVariable != null && containerViewVariable.eIsProxy()) {
            InternalEObject oldContainerViewVariable = (InternalEObject) containerViewVariable;
            containerViewVariable = (ContainerViewVariable) eResolveProxy(oldContainerViewVariable);
            if (containerViewVariable != oldContainerViewVariable) {
                InternalEObject newContainerViewVariable = (InternalEObject) containerViewVariable;
                NotificationChain msgs = oldContainerViewVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, null);
                if (newContainerViewVariable.eInternalContainer() == null) {
                    msgs = newContainerViewVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, oldContainerViewVariable,
                            containerViewVariable));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, oldContainerViewVariable,
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
                msgs = ((InternalEObject) containerViewVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, msgs);
            }
            if (newContainerViewVariable != null) {
                msgs = ((InternalEObject) newContainerViewVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, null, msgs);
            }
            msgs = basicSetContainerViewVariable(newContainerViewVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE, newContainerViewVariable, newContainerViewVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementSelectVariable getContainerVariable() {
        if (containerVariable != null && containerVariable.eIsProxy()) {
            InternalEObject oldContainerVariable = (InternalEObject) containerVariable;
            containerVariable = (ElementSelectVariable) eResolveProxy(oldContainerVariable);
            if (containerVariable != oldContainerVariable) {
                InternalEObject newContainerVariable = (InternalEObject) containerVariable;
                NotificationChain msgs = oldContainerVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE,
                        null, null);
                if (newContainerVariable.eInternalContainer() == null) {
                    msgs = newContainerVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE, oldContainerVariable, containerVariable));
                }
            }
        }
        return containerVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementSelectVariable basicGetContainerVariable() {
        return containerVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainerVariable(ElementSelectVariable newContainerVariable, NotificationChain msgs) {
        ElementSelectVariable oldContainerVariable = containerVariable;
        containerVariable = newContainerVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE, oldContainerVariable,
                    newContainerVariable);
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
    public void setContainerVariable(ElementSelectVariable newContainerVariable) {
        if (newContainerVariable != containerVariable) {
            NotificationChain msgs = null;
            if (containerVariable != null) {
                msgs = ((InternalEObject) containerVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE, null,
                        msgs);
            }
            if (newContainerVariable != null) {
                msgs = ((InternalEObject) newContainerVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE, null,
                        msgs);
            }
            msgs = basicSetContainerVariable(newContainerVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE, newContainerVariable, newContainerVariable));
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
                        - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, null, null);
                if (newRepresentationNameVariable.eInternalContainer() == null) {
                    msgs = newRepresentationNameVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE,
                            null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, oldRepresentationNameVariable,
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE,
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
                        - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, null, msgs);
            }
            if (newRepresentationNameVariable != null) {
                msgs = ((InternalEObject) newRepresentationNameVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE
                        - ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, null, msgs);
            }
            msgs = basicSetRepresentationNameVariable(newRepresentationNameVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE, newRepresentationNameVariable,
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
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            return basicSetContainerViewVariable(null, msgs);
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE:
            return basicSetContainerVariable(null, msgs);
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
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
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION:
            return getBrowseExpression();
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION:
            return getNavigationNameExpression();
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION:
            if (resolve) {
                return getRepresentationDescription();
            }
            return basicGetRepresentationDescription();
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            if (resolve) {
                return getContainerViewVariable();
            }
            return basicGetContainerViewVariable();
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE:
            if (resolve) {
                return getContainerVariable();
            }
            return basicGetContainerVariable();
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
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
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION:
            setBrowseExpression((String) newValue);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION:
            setNavigationNameExpression((String) newValue);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            setContainerViewVariable((ContainerViewVariable) newValue);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE:
            setContainerVariable((ElementSelectVariable) newValue);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
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
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION:
            setBrowseExpression(RepresentationNavigationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION:
            setNavigationNameExpression(RepresentationNavigationDescriptionImpl.NAVIGATION_NAME_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            setContainerViewVariable((ContainerViewVariable) null);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE:
            setContainerVariable((ElementSelectVariable) null);
            return;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
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
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION:
            return RepresentationNavigationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT == null ? browseExpression != null : !RepresentationNavigationDescriptionImpl.BROWSE_EXPRESSION_EDEFAULT
                    .equals(browseExpression);
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION:
            return RepresentationNavigationDescriptionImpl.NAVIGATION_NAME_EXPRESSION_EDEFAULT == null ? navigationNameExpression != null
                    : !RepresentationNavigationDescriptionImpl.NAVIGATION_NAME_EXPRESSION_EDEFAULT.equals(navigationNameExpression);
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION:
            return basicGetRepresentationDescription() != null;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE:
            return containerViewVariable != null;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE:
            return containerVariable != null;
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE:
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
        result.append(" (browseExpression: "); //$NON-NLS-1$
        result.append(browseExpression);
        result.append(", navigationNameExpression: "); //$NON-NLS-1$
        result.append(navigationNameExpression);
        result.append(')');
        return result.toString();
    }

} // RepresentationNavigationDescriptionImpl
