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
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteHook;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Delete Element Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl#getElementView
 * <em>Element View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl#getContainerView
 * <em>Container View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl#getHook
 * <em>Hook</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeleteElementDescriptionImpl extends MappingBasedToolDescriptionImpl implements DeleteElementDescription {
    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementDeleteVariable element;

    /**
     * The cached value of the '{@link #getElementView() <em>Element View</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElementView()
     * @generated
     * @ordered
     */
    protected ElementDeleteVariable elementView;

    /**
     * The cached value of the '{@link #getContainerView()
     * <em>Container View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getContainerView()
     * @generated
     * @ordered
     */
    protected ContainerViewVariable containerView;

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
     * The cached value of the '{@link #getHook() <em>Hook</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHook()
     * @generated
     * @ordered
     */
    protected DeleteHook hook;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DeleteElementDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.DELETE_ELEMENT_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDeleteVariable getElement() {
        if (element != null && element.eIsProxy()) {
            InternalEObject oldElement = (InternalEObject) element;
            element = (ElementDeleteVariable) eResolveProxy(oldElement);
            if (element != oldElement) {
                InternalEObject newElement = (InternalEObject) element;
                NotificationChain msgs = oldElement.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT, null, null);
                if (newElement.eInternalContainer() == null) {
                    msgs = newElement.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT, oldElement, element));
                }
            }
        }
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ElementDeleteVariable basicGetElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElement(ElementDeleteVariable newElement, NotificationChain msgs) {
        ElementDeleteVariable oldElement = element;
        element = newElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT, oldElement, newElement);
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
    public void setElement(ElementDeleteVariable newElement) {
        if (newElement != element) {
            NotificationChain msgs = null;
            if (element != null) {
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDeleteVariable getElementView() {
        if (elementView != null && elementView.eIsProxy()) {
            InternalEObject oldElementView = (InternalEObject) elementView;
            elementView = (ElementDeleteVariable) eResolveProxy(oldElementView);
            if (elementView != oldElementView) {
                InternalEObject newElementView = (InternalEObject) elementView;
                NotificationChain msgs = oldElementView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW, null, null);
                if (newElementView.eInternalContainer() == null) {
                    msgs = newElementView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW, oldElementView, elementView));
                }
            }
        }
        return elementView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ElementDeleteVariable basicGetElementView() {
        return elementView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElementView(ElementDeleteVariable newElementView, NotificationChain msgs) {
        ElementDeleteVariable oldElementView = elementView;
        elementView = newElementView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW, oldElementView, newElementView);
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
    public void setElementView(ElementDeleteVariable newElementView) {
        if (newElementView != elementView) {
            NotificationChain msgs = null;
            if (elementView != null) {
                msgs = ((InternalEObject) elementView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW, null, msgs);
            }
            if (newElementView != null) {
                msgs = ((InternalEObject) newElementView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW, null, msgs);
            }
            msgs = basicSetElementView(newElementView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW, newElementView, newElementView));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerViewVariable getContainerView() {
        if (containerView != null && containerView.eIsProxy()) {
            InternalEObject oldContainerView = (InternalEObject) containerView;
            containerView = (ContainerViewVariable) eResolveProxy(oldContainerView);
            if (containerView != oldContainerView) {
                InternalEObject newContainerView = (InternalEObject) containerView;
                NotificationChain msgs = oldContainerView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW, null, null);
                if (newContainerView.eInternalContainer() == null) {
                    msgs = newContainerView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW, oldContainerView, containerView));
                }
            }
        }
        return containerView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerViewVariable basicGetContainerView() {
        return containerView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetContainerView(ContainerViewVariable newContainerView, NotificationChain msgs) {
        ContainerViewVariable oldContainerView = containerView;
        containerView = newContainerView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW, oldContainerView, newContainerView);
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
    public void setContainerView(ContainerViewVariable newContainerView) {
        if (newContainerView != containerView) {
            NotificationChain msgs = null;
            if (containerView != null) {
                msgs = ((InternalEObject) containerView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW, null, msgs);
            }
            if (newContainerView != null) {
                msgs = ((InternalEObject) newContainerView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW, null, msgs);
            }
            msgs = basicSetContainerView(newContainerView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW, newContainerView, newContainerView));
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
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DeleteHook getHook() {
        if (hook != null && hook.eIsProxy()) {
            InternalEObject oldHook = (InternalEObject) hook;
            hook = (DeleteHook) eResolveProxy(oldHook);
            if (hook != oldHook) {
                InternalEObject newHook = (InternalEObject) hook;
                NotificationChain msgs = oldHook.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK, null, null);
                if (newHook.eInternalContainer() == null) {
                    msgs = newHook.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK, oldHook, hook));
                }
            }
        }
        return hook;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DeleteHook basicGetHook() {
        return hook;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetHook(DeleteHook newHook, NotificationChain msgs) {
        DeleteHook oldHook = hook;
        hook = newHook;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK, oldHook, newHook);
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
    public void setHook(DeleteHook newHook) {
        if (newHook != hook) {
            NotificationChain msgs = null;
            if (hook != null) {
                msgs = ((InternalEObject) hook).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK, null, msgs);
            }
            if (newHook != null) {
                msgs = ((InternalEObject) newHook).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK, null, msgs);
            }
            msgs = basicSetHook(newHook, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK, newHook, newHook));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getMappings() {
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
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT:
            return basicSetElement(null, msgs);
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW:
            return basicSetElementView(null, msgs);
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW:
            return basicSetContainerView(null, msgs);
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK:
            return basicSetHook(null, msgs);
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
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT:
            if (resolve) {
                return getElement();
            }
            return basicGetElement();
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW:
            if (resolve) {
                return getElementView();
            }
            return basicGetElementView();
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW:
            if (resolve) {
                return getContainerView();
            }
            return basicGetContainerView();
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK:
            if (resolve) {
                return getHook();
            }
            return basicGetHook();
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
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT:
            setElement((ElementDeleteVariable) newValue);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW:
            setElementView((ElementDeleteVariable) newValue);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW:
            setContainerView((ContainerViewVariable) newValue);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK:
            setHook((DeleteHook) newValue);
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
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT:
            setElement((ElementDeleteVariable) null);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW:
            setElementView((ElementDeleteVariable) null);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW:
            setContainerView((ContainerViewVariable) null);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK:
            setHook((DeleteHook) null);
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
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT:
            return element != null;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW:
            return elementView != null;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW:
            return containerView != null;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.DELETE_ELEMENT_DESCRIPTION__HOOK:
            return hook != null;
        }
        return super.eIsSet(featureID);
    }

} // DeleteElementDescriptionImpl
