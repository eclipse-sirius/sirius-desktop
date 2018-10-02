/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Paste Description</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl#getContainer <em>Container</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl#getContainerView <em>Container
 * View</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl#getCopiedView <em>Copied View</em>
 * }</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl#getCopiedElement <em>Copied
 * Element</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PasteDescriptionImpl extends MappingBasedToolDescriptionImpl implements PasteDescription {
    /**
     * The cached value of the '{@link #getContainer() <em>Container</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getContainer()
     * @generated
     * @ordered
     */
    protected DropContainerVariable container;

    /**
     * The cached value of the '{@link #getContainerView() <em>Container View</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContainerView()
     * @generated
     * @ordered
     */
    protected ContainerViewVariable containerView;

    /**
     * The cached value of the '{@link #getCopiedView() <em>Copied View</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCopiedView()
     * @generated
     * @ordered
     */
    protected ElementViewVariable copiedView;

    /**
     * The cached value of the '{@link #getCopiedElement() <em>Copied Element</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCopiedElement()
     * @generated
     * @ordered
     */
    protected ElementVariable copiedElement;

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
    protected PasteDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.PASTE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DropContainerVariable getContainer() {
        if (container != null && container.eIsProxy()) {
            InternalEObject oldContainer = (InternalEObject) container;
            container = (DropContainerVariable) eResolveProxy(oldContainer);
            if (container != oldContainer) {
                InternalEObject newContainer = (InternalEObject) container;
                NotificationChain msgs = oldContainer.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER, null, null);
                if (newContainer.eInternalContainer() == null) {
                    msgs = newContainer.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PASTE_DESCRIPTION__CONTAINER, oldContainer, container));
                }
            }
        }
        return container;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DropContainerVariable basicGetContainer() {
        return container;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetContainer(DropContainerVariable newContainer, NotificationChain msgs) {
        DropContainerVariable oldContainer = container;
        container = newContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__CONTAINER, oldContainer, newContainer);
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
    public void setContainer(DropContainerVariable newContainer) {
        if (newContainer != container) {
            NotificationChain msgs = null;
            if (container != null) {
                msgs = ((InternalEObject) container).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER, null, msgs);
            }
            if (newContainer != null) {
                msgs = ((InternalEObject) newContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER, null, msgs);
            }
            msgs = basicSetContainer(newContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__CONTAINER, newContainer, newContainer));
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
                NotificationChain msgs = oldContainerView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW, null, null);
                if (newContainerView.eInternalContainer() == null) {
                    msgs = newContainerView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW, oldContainerView, containerView));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW, oldContainerView, newContainerView);
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
                msgs = ((InternalEObject) containerView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW, null, msgs);
            }
            if (newContainerView != null) {
                msgs = ((InternalEObject) newContainerView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW, null, msgs);
            }
            msgs = basicSetContainerView(newContainerView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW, newContainerView, newContainerView));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementViewVariable getCopiedView() {
        if (copiedView != null && copiedView.eIsProxy()) {
            InternalEObject oldCopiedView = (InternalEObject) copiedView;
            copiedView = (ElementViewVariable) eResolveProxy(oldCopiedView);
            if (copiedView != oldCopiedView) {
                InternalEObject newCopiedView = (InternalEObject) copiedView;
                NotificationChain msgs = oldCopiedView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW, null, null);
                if (newCopiedView.eInternalContainer() == null) {
                    msgs = newCopiedView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW, oldCopiedView, copiedView));
                }
            }
        }
        return copiedView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ElementViewVariable basicGetCopiedView() {
        return copiedView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCopiedView(ElementViewVariable newCopiedView, NotificationChain msgs) {
        ElementViewVariable oldCopiedView = copiedView;
        copiedView = newCopiedView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW, oldCopiedView, newCopiedView);
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
    public void setCopiedView(ElementViewVariable newCopiedView) {
        if (newCopiedView != copiedView) {
            NotificationChain msgs = null;
            if (copiedView != null) {
                msgs = ((InternalEObject) copiedView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW, null, msgs);
            }
            if (newCopiedView != null) {
                msgs = ((InternalEObject) newCopiedView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW, null, msgs);
            }
            msgs = basicSetCopiedView(newCopiedView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW, newCopiedView, newCopiedView));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementVariable getCopiedElement() {
        if (copiedElement != null && copiedElement.eIsProxy()) {
            InternalEObject oldCopiedElement = (InternalEObject) copiedElement;
            copiedElement = (ElementVariable) eResolveProxy(oldCopiedElement);
            if (copiedElement != oldCopiedElement) {
                InternalEObject newCopiedElement = (InternalEObject) copiedElement;
                NotificationChain msgs = oldCopiedElement.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT, null, null);
                if (newCopiedElement.eInternalContainer() == null) {
                    msgs = newCopiedElement.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT, oldCopiedElement, copiedElement));
                }
            }
        }
        return copiedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ElementVariable basicGetCopiedElement() {
        return copiedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCopiedElement(ElementVariable newCopiedElement, NotificationChain msgs) {
        ElementVariable oldCopiedElement = copiedElement;
        copiedElement = newCopiedElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT, oldCopiedElement, newCopiedElement);
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
    public void setCopiedElement(ElementVariable newCopiedElement) {
        if (newCopiedElement != copiedElement) {
            NotificationChain msgs = null;
            if (copiedElement != null) {
                msgs = ((InternalEObject) copiedElement).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT, null, msgs);
            }
            if (newCopiedElement != null) {
                msgs = ((InternalEObject) newCopiedElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT, null, msgs);
            }
            msgs = basicSetCopiedElement(newCopiedElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT, newCopiedElement, newCopiedElement));
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
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PasteTargetDescription> getContainers() {
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
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER:
            return basicSetContainer(null, msgs);
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW:
            return basicSetContainerView(null, msgs);
        case ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW:
            return basicSetCopiedView(null, msgs);
        case ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT:
            return basicSetCopiedElement(null, msgs);
        case ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER:
            if (resolve) {
                return getContainer();
            }
            return basicGetContainer();
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW:
            if (resolve) {
                return getContainerView();
            }
            return basicGetContainerView();
        case ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW:
            if (resolve) {
                return getCopiedView();
            }
            return basicGetCopiedView();
        case ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT:
            if (resolve) {
                return getCopiedElement();
            }
            return basicGetCopiedElement();
        case ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
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
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER:
            setContainer((DropContainerVariable) newValue);
            return;
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW:
            setContainerView((ContainerViewVariable) newValue);
            return;
        case ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW:
            setCopiedView((ElementViewVariable) newValue);
            return;
        case ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT:
            setCopiedElement((ElementVariable) newValue);
            return;
        case ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER:
            setContainer((DropContainerVariable) null);
            return;
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW:
            setContainerView((ContainerViewVariable) null);
            return;
        case ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW:
            setCopiedView((ElementViewVariable) null);
            return;
        case ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT:
            setCopiedElement((ElementVariable) null);
            return;
        case ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER:
            return container != null;
        case ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW:
            return containerView != null;
        case ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW:
            return copiedView != null;
        case ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT:
            return copiedElement != null;
        case ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        }
        return super.eIsSet(featureID);
    }

} // PasteDescriptionImpl
