/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.PrecedingSiblingsVariables;
import org.eclipse.sirius.tree.description.TreeDragSource;
import org.eclipse.sirius.tree.description.TreeItemDragTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemMappingContainer;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tree Item Drag Tool</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getFirstModelOperation
 * <em>First Model Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getVariables
 * <em>Variables</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getOldContainer
 * <em>Old Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getNewContainer
 * <em>New Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getNewViewContainer
 * <em>New View Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getContainers
 * <em>Containers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getDragSourceType
 * <em>Drag Source Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemDragToolImpl#getPrecedingSiblings
 * <em>Preceding Siblings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@Deprecated
public class TreeItemDragToolImpl extends MappingBasedToolDescriptionImpl implements TreeItemDragTool {
    /**
     * The cached value of the '{@link #getFirstModelOperation()
     * <em>First Model Operation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFirstModelOperation()
     * @generated
     * @ordered
     */
    protected ModelOperation firstModelOperation;

    /**
     * The cached value of the '{@link #getVariables() <em>Variables</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVariables()
     * @generated
     * @ordered
     */
    protected EList<TreeVariable> variables;

    /**
     * The cached value of the '{@link #getOldContainer()
     * <em>Old Container</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOldContainer()
     * @generated
     * @ordered
     */
    protected DropContainerVariable oldContainer;

    /**
     * The cached value of the '{@link #getNewContainer()
     * <em>New Container</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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
     * The cached value of the '{@link #getContainers() <em>Containers</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getContainers()
     * @generated
     * @ordered
     */
    protected EList<TreeItemMappingContainer> containers;

    /**
     * The default value of the '{@link #getDragSourceType()
     * <em>Drag Source Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDragSourceType()
     * @generated
     * @ordered
     */
    protected static final TreeDragSource DRAG_SOURCE_TYPE_EDEFAULT = TreeDragSource.TREE;

    /**
     * The cached value of the '{@link #getDragSourceType()
     * <em>Drag Source Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDragSourceType()
     * @generated
     * @ordered
     */
    protected TreeDragSource dragSourceType = TreeItemDragToolImpl.DRAG_SOURCE_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getPrecedingSiblings()
     * <em>Preceding Siblings</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getPrecedingSiblings()
     * @generated
     * @ordered
     */
    protected PrecedingSiblingsVariables precedingSiblings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TreeItemDragToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_ITEM_DRAG_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ModelOperation getFirstModelOperation() {
        return firstModelOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetFirstModelOperation(ModelOperation newFirstModelOperation, NotificationChain msgs) {
        ModelOperation oldFirstModelOperation = firstModelOperation;
        firstModelOperation = newFirstModelOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION, oldFirstModelOperation,
                    newFirstModelOperation);
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
    public void setFirstModelOperation(ModelOperation newFirstModelOperation) {
        if (newFirstModelOperation != firstModelOperation) {
            NotificationChain msgs = null;
            if (firstModelOperation != null) {
                msgs = ((InternalEObject) firstModelOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION, null, msgs);
            }
            if (newFirstModelOperation != null) {
                msgs = ((InternalEObject) newFirstModelOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION, null, msgs);
            }
            msgs = basicSetFirstModelOperation(newFirstModelOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION, newFirstModelOperation, newFirstModelOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeVariable> getVariables() {
        if (variables == null) {
            variables = new EObjectContainmentEList<TreeVariable>(TreeVariable.class, this, DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES);
        }
        return variables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DropContainerVariable getOldContainer() {
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER, oldOldContainer, newOldContainer);
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
                msgs = ((InternalEObject) oldContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER, null, msgs);
            }
            if (newOldContainer != null) {
                msgs = ((InternalEObject) newOldContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER, null, msgs);
            }
            msgs = basicSetOldContainer(newOldContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER, newOldContainer, newOldContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DropContainerVariable getNewContainer() {
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER, oldNewContainer, newNewContainer);
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
                msgs = ((InternalEObject) newContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER, null, msgs);
            }
            if (newNewContainer != null) {
                msgs = ((InternalEObject) newNewContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER, null, msgs);
            }
            msgs = basicSetNewContainer(newNewContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER, newNewContainer, newNewContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementDropVariable getElement() {
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT, oldElement, newElement);
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
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ContainerViewVariable getNewViewContainer() {
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER, oldNewViewContainer, newNewViewContainer);
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
                msgs = ((InternalEObject) newViewContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER, null, msgs);
            }
            if (newNewViewContainer != null) {
                msgs = ((InternalEObject) newNewViewContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER, null, msgs);
            }
            msgs = basicSetNewViewContainer(newNewViewContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER, newNewViewContainer, newNewViewContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemMapping getBestTreeItemMapping() {
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
    public EList<TreeItemMappingContainer> getContainers() {
        if (containers == null) {
            containers = new EObjectResolvingEList<TreeItemMappingContainer>(TreeItemMappingContainer.class, this, DescriptionPackage.TREE_ITEM_DRAG_TOOL__CONTAINERS);
        }
        return containers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeDragSource getDragSourceType() {
        return dragSourceType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDragSourceType(TreeDragSource newDragSourceType) {
        TreeDragSource oldDragSourceType = dragSourceType;
        dragSourceType = newDragSourceType == null ? TreeItemDragToolImpl.DRAG_SOURCE_TYPE_EDEFAULT : newDragSourceType;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE, oldDragSourceType, dragSourceType));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PrecedingSiblingsVariables getPrecedingSiblings() {
        return precedingSiblings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetPrecedingSiblings(PrecedingSiblingsVariables newPrecedingSiblings, NotificationChain msgs) {
        PrecedingSiblingsVariables oldPrecedingSiblings = precedingSiblings;
        precedingSiblings = newPrecedingSiblings;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS, oldPrecedingSiblings, newPrecedingSiblings);
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
    public void setPrecedingSiblings(PrecedingSiblingsVariables newPrecedingSiblings) {
        if (newPrecedingSiblings != precedingSiblings) {
            NotificationChain msgs = null;
            if (precedingSiblings != null) {
                msgs = ((InternalEObject) precedingSiblings).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS, null, msgs);
            }
            if (newPrecedingSiblings != null) {
                msgs = ((InternalEObject) newPrecedingSiblings).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS, null, msgs);
            }
            msgs = basicSetPrecedingSiblings(newPrecedingSiblings, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS, newPrecedingSiblings, newPrecedingSiblings));
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
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION:
            return basicSetFirstModelOperation(null, msgs);
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES:
            return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER:
            return basicSetOldContainer(null, msgs);
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER:
            return basicSetNewContainer(null, msgs);
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT:
            return basicSetElement(null, msgs);
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER:
            return basicSetNewViewContainer(null, msgs);
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS:
            return basicSetPrecedingSiblings(null, msgs);
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
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION:
            return getFirstModelOperation();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES:
            return getVariables();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER:
            return getOldContainer();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER:
            return getNewContainer();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT:
            return getElement();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER:
            return getNewViewContainer();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__CONTAINERS:
            return getContainers();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE:
            return getDragSourceType();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS:
            return getPrecedingSiblings();
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
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION:
            setFirstModelOperation((ModelOperation) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES:
            getVariables().clear();
            getVariables().addAll((Collection<? extends TreeVariable>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER:
            setOldContainer((DropContainerVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER:
            setNewContainer((DropContainerVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT:
            setElement((ElementDropVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER:
            setNewViewContainer((ContainerViewVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__CONTAINERS:
            getContainers().clear();
            getContainers().addAll((Collection<? extends TreeItemMappingContainer>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE:
            setDragSourceType((TreeDragSource) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS:
            setPrecedingSiblings((PrecedingSiblingsVariables) newValue);
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
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION:
            setFirstModelOperation((ModelOperation) null);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES:
            getVariables().clear();
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER:
            setOldContainer((DropContainerVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER:
            setNewContainer((DropContainerVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT:
            setElement((ElementDropVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER:
            setNewViewContainer((ContainerViewVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__CONTAINERS:
            getContainers().clear();
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE:
            setDragSourceType(TreeItemDragToolImpl.DRAG_SOURCE_TYPE_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS:
            setPrecedingSiblings((PrecedingSiblingsVariables) null);
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
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION:
            return firstModelOperation != null;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES:
            return variables != null && !variables.isEmpty();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__OLD_CONTAINER:
            return oldContainer != null;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_CONTAINER:
            return newContainer != null;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__ELEMENT:
            return element != null;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__NEW_VIEW_CONTAINER:
            return newViewContainer != null;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__CONTAINERS:
            return containers != null && !containers.isEmpty();
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__DRAG_SOURCE_TYPE:
            return dragSourceType != TreeItemDragToolImpl.DRAG_SOURCE_TYPE_EDEFAULT;
        case DescriptionPackage.TREE_ITEM_DRAG_TOOL__PRECEDING_SIBLINGS:
            return precedingSiblings != null;
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
        if (baseClass == TreeItemTool.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION:
                return DescriptionPackage.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION;
            case DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES:
                return DescriptionPackage.TREE_ITEM_TOOL__VARIABLES;
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
        if (baseClass == TreeItemTool.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION:
                return DescriptionPackage.TREE_ITEM_DRAG_TOOL__FIRST_MODEL_OPERATION;
            case DescriptionPackage.TREE_ITEM_TOOL__VARIABLES:
                return DescriptionPackage.TREE_ITEM_DRAG_TOOL__VARIABLES;
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
        result.append(" (dragSourceType: "); //$NON-NLS-1$
        result.append(dragSourceType);
        result.append(')');
        return result.toString();
    }

} // TreeItemDragToolImpl
