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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Container Drop Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#getMappings
 * <em>Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#getOldContainer
 * <em>Old Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#getNewContainer
 * <em>New Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#getNewViewContainer
 * <em>New View Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#getDragSource
 * <em>Drag Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl#isMoveEdges
 * <em>Move Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerDropDescriptionImpl extends MappingBasedToolDescriptionImpl implements ContainerDropDescription {
    /**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> mappings;

    /**
     * The cached value of the '{@link #getOldContainer() <em>Old Container</em>
     * }' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOldContainer()
     * @generated
     * @ordered
     */
    protected DropContainerVariable oldContainer;

    /**
     * The cached value of the '{@link #getNewContainer() <em>New Container</em>
     * }' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNewContainer()
     * @generated
     * @ordered
     */
    protected DropContainerVariable newContainer;

    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementDropVariable element;

    /**
     * The cached value of the '{@link #getNewViewContainer()
     * <em>New View Container</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getNewViewContainer()
     * @generated
     * @ordered
     */
    protected ContainerViewVariable newViewContainer;

    /**
     * The cached value of the '{@link #getInitialOperation()
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialContainerDropOperation initialOperation;

    /**
     * The default value of the '{@link #getDragSource() <em>Drag Source</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDragSource()
     * @generated
     * @ordered
     */
    protected static final DragSource DRAG_SOURCE_EDEFAULT = DragSource.DIAGRAM_LITERAL;

    /**
     * The cached value of the '{@link #getDragSource() <em>Drag Source</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDragSource()
     * @generated
     * @ordered
     */
    protected DragSource dragSource = ContainerDropDescriptionImpl.DRAG_SOURCE_EDEFAULT;

    /**
     * The default value of the '{@link #isMoveEdges() <em>Move Edges</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isMoveEdges()
     * @generated
     * @ordered
     */
    protected static final boolean MOVE_EDGES_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMoveEdges() <em>Move Edges</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isMoveEdges()
     * @generated
     * @ordered
     */
    protected boolean moveEdges = ContainerDropDescriptionImpl.MOVE_EDGES_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContainerDropDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.CONTAINER_DROP_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, ToolPackage.CONTAINER_DROP_DESCRIPTION__MAPPINGS);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DropContainerVariable getOldContainer() {
        if (oldContainer != null && oldContainer.eIsProxy()) {
            InternalEObject oldOldContainer = (InternalEObject) oldContainer;
            oldContainer = (DropContainerVariable) eResolveProxy(oldOldContainer);
            if (oldContainer != oldOldContainer) {
                InternalEObject newOldContainer = (InternalEObject) oldContainer;
                NotificationChain msgs = oldOldContainer.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER, null, null);
                if (newOldContainer.eInternalContainer() == null) {
                    msgs = newOldContainer.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER, oldOldContainer, oldContainer));
                }
            }
        }
        return oldContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DropContainerVariable basicGetOldContainer() {
        return oldContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOldContainer(DropContainerVariable newOldContainer, NotificationChain msgs) {
        DropContainerVariable oldOldContainer = oldContainer;
        oldContainer = newOldContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER, oldOldContainer, newOldContainer);
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
    public void setOldContainer(DropContainerVariable newOldContainer) {
        if (newOldContainer != oldContainer) {
            NotificationChain msgs = null;
            if (oldContainer != null) {
                msgs = ((InternalEObject) oldContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER, null, msgs);
            }
            if (newOldContainer != null) {
                msgs = ((InternalEObject) newOldContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER, null, msgs);
            }
            msgs = basicSetOldContainer(newOldContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER, newOldContainer, newOldContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DropContainerVariable getNewContainer() {
        if (newContainer != null && newContainer.eIsProxy()) {
            InternalEObject oldNewContainer = (InternalEObject) newContainer;
            newContainer = (DropContainerVariable) eResolveProxy(oldNewContainer);
            if (newContainer != oldNewContainer) {
                InternalEObject newNewContainer = (InternalEObject) newContainer;
                NotificationChain msgs = oldNewContainer.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER, null, null);
                if (newNewContainer.eInternalContainer() == null) {
                    msgs = newNewContainer.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER, oldNewContainer, newContainer));
                }
            }
        }
        return newContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DropContainerVariable basicGetNewContainer() {
        return newContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetNewContainer(DropContainerVariable newNewContainer, NotificationChain msgs) {
        DropContainerVariable oldNewContainer = newContainer;
        newContainer = newNewContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER, oldNewContainer, newNewContainer);
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
    public void setNewContainer(DropContainerVariable newNewContainer) {
        if (newNewContainer != newContainer) {
            NotificationChain msgs = null;
            if (newContainer != null) {
                msgs = ((InternalEObject) newContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER, null, msgs);
            }
            if (newNewContainer != null) {
                msgs = ((InternalEObject) newNewContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER, null, msgs);
            }
            msgs = basicSetNewContainer(newNewContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER, newNewContainer, newNewContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDropVariable getElement() {
        if (element != null && element.eIsProxy()) {
            InternalEObject oldElement = (InternalEObject) element;
            element = (ElementDropVariable) eResolveProxy(oldElement);
            if (element != oldElement) {
                InternalEObject newElement = (InternalEObject) element;
                NotificationChain msgs = oldElement.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT, null, null);
                if (newElement.eInternalContainer() == null) {
                    msgs = newElement.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT, oldElement, element));
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
    public ElementDropVariable basicGetElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElement(ElementDropVariable newElement, NotificationChain msgs) {
        ElementDropVariable oldElement = element;
        element = newElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT, oldElement, newElement);
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
    public void setElement(ElementDropVariable newElement) {
        if (newElement != element) {
            NotificationChain msgs = null;
            if (element != null) {
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerViewVariable getNewViewContainer() {
        if (newViewContainer != null && newViewContainer.eIsProxy()) {
            InternalEObject oldNewViewContainer = (InternalEObject) newViewContainer;
            newViewContainer = (ContainerViewVariable) eResolveProxy(oldNewViewContainer);
            if (newViewContainer != oldNewViewContainer) {
                InternalEObject newNewViewContainer = (InternalEObject) newViewContainer;
                NotificationChain msgs = oldNewViewContainer.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER, null, null);
                if (newNewViewContainer.eInternalContainer() == null) {
                    msgs = newNewViewContainer.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER, oldNewViewContainer, newViewContainer));
                }
            }
        }
        return newViewContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerViewVariable basicGetNewViewContainer() {
        return newViewContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetNewViewContainer(ContainerViewVariable newNewViewContainer, NotificationChain msgs) {
        ContainerViewVariable oldNewViewContainer = newViewContainer;
        newViewContainer = newNewViewContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER, oldNewViewContainer, newNewViewContainer);
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
    public void setNewViewContainer(ContainerViewVariable newNewViewContainer) {
        if (newNewViewContainer != newViewContainer) {
            NotificationChain msgs = null;
            if (newViewContainer != null) {
                msgs = ((InternalEObject) newViewContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER, null, msgs);
            }
            if (newNewViewContainer != null) {
                msgs = ((InternalEObject) newNewViewContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER, null, msgs);
            }
            msgs = basicSetNewViewContainer(newNewViewContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER, newNewViewContainer, newNewViewContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialContainerDropOperation getInitialOperation() {
        if (initialOperation != null && initialOperation.eIsProxy()) {
            InternalEObject oldInitialOperation = (InternalEObject) initialOperation;
            initialOperation = (InitialContainerDropOperation) eResolveProxy(oldInitialOperation);
            if (initialOperation != oldInitialOperation) {
                InternalEObject newInitialOperation = (InternalEObject) initialOperation;
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
    public InitialContainerDropOperation basicGetInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialContainerDropOperation newInitialOperation, NotificationChain msgs) {
        InitialContainerDropOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
    public void setInitialOperation(InitialContainerDropOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DragSource getDragSource() {
        return dragSource;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDragSource(DragSource newDragSource) {
        DragSource oldDragSource = dragSource;
        dragSource = newDragSource == null ? ContainerDropDescriptionImpl.DRAG_SOURCE_EDEFAULT : newDragSource;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE, oldDragSource, dragSource));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isMoveEdges() {
        return moveEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMoveEdges(boolean newMoveEdges) {
        boolean oldMoveEdges = moveEdges;
        moveEdges = newMoveEdges;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CONTAINER_DROP_DESCRIPTION__MOVE_EDGES, oldMoveEdges, moveEdges));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DiagramElementMapping getBestMapping(DragAndDropTarget targetContainer, EObject droppedElement) {
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
    public EList<DragAndDropTargetDescription> getContainers() {
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
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER:
            return basicSetOldContainer(null, msgs);
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER:
            return basicSetNewContainer(null, msgs);
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT:
            return basicSetElement(null, msgs);
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER:
            return basicSetNewViewContainer(null, msgs);
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MAPPINGS:
            return getMappings();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER:
            if (resolve) {
                return getOldContainer();
            }
            return basicGetOldContainer();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER:
            if (resolve) {
                return getNewContainer();
            }
            return basicGetNewContainer();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT:
            if (resolve) {
                return getElement();
            }
            return basicGetElement();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER:
            if (resolve) {
                return getNewViewContainer();
            }
            return basicGetNewViewContainer();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE:
            return getDragSource();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MOVE_EDGES:
            return isMoveEdges();
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
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MAPPINGS:
            getMappings().clear();
            getMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER:
            setOldContainer((DropContainerVariable) newValue);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER:
            setNewContainer((DropContainerVariable) newValue);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT:
            setElement((ElementDropVariable) newValue);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER:
            setNewViewContainer((ContainerViewVariable) newValue);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialContainerDropOperation) newValue);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE:
            setDragSource((DragSource) newValue);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MOVE_EDGES:
            setMoveEdges((Boolean) newValue);
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
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MAPPINGS:
            getMappings().clear();
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER:
            setOldContainer((DropContainerVariable) null);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER:
            setNewContainer((DropContainerVariable) null);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT:
            setElement((ElementDropVariable) null);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER:
            setNewViewContainer((ContainerViewVariable) null);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialContainerDropOperation) null);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE:
            setDragSource(ContainerDropDescriptionImpl.DRAG_SOURCE_EDEFAULT);
            return;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MOVE_EDGES:
            setMoveEdges(ContainerDropDescriptionImpl.MOVE_EDGES_EDEFAULT);
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
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MAPPINGS:
            return mappings != null && !mappings.isEmpty();
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER:
            return oldContainer != null;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER:
            return newContainer != null;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__ELEMENT:
            return element != null;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER:
            return newViewContainer != null;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE:
            return dragSource != ContainerDropDescriptionImpl.DRAG_SOURCE_EDEFAULT;
        case ToolPackage.CONTAINER_DROP_DESCRIPTION__MOVE_EDGES:
            return moveEdges != ContainerDropDescriptionImpl.MOVE_EDGES_EDEFAULT;
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
        result.append(" (dragSource: "); //$NON-NLS-1$
        result.append(dragSource);
        result.append(", moveEdges: "); //$NON-NLS-1$
        result.append(moveEdges);
        result.append(')');
        return result.toString();
    }

} // ContainerDropDescriptionImpl
