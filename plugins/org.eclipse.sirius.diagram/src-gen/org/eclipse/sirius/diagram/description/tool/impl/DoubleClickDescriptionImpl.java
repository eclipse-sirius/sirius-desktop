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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Double Click Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl#getMappings
 * <em>Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl#getElementView
 * <em>Element View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoubleClickDescriptionImpl extends MappingBasedToolDescriptionImpl implements DoubleClickDescription {
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
     * The cached value of the '{@link #getElement() <em>Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementDoubleClickVariable element;

    /**
     * The cached value of the '{@link #getElementView() <em>Element View</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElementView()
     * @generated
     * @ordered
     */
    protected ElementDoubleClickVariable elementView;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DoubleClickDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.DOUBLE_CLICK_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectWithInverseResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS,
                    DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDoubleClickVariable getElement() {
        if (element != null && element.eIsProxy()) {
            InternalEObject oldElement = (InternalEObject) element;
            element = (ElementDoubleClickVariable) eResolveProxy(oldElement);
            if (element != oldElement) {
                InternalEObject newElement = (InternalEObject) element;
                NotificationChain msgs = oldElement.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT, null, null);
                if (newElement.eInternalContainer() == null) {
                    msgs = newElement.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT, oldElement, element));
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
    public ElementDoubleClickVariable basicGetElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElement(ElementDoubleClickVariable newElement, NotificationChain msgs) {
        ElementDoubleClickVariable oldElement = element;
        element = newElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT, oldElement, newElement);
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
    public void setElement(ElementDoubleClickVariable newElement) {
        if (newElement != element) {
            NotificationChain msgs = null;
            if (element != null) {
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementDoubleClickVariable getElementView() {
        if (elementView != null && elementView.eIsProxy()) {
            InternalEObject oldElementView = (InternalEObject) elementView;
            elementView = (ElementDoubleClickVariable) eResolveProxy(oldElementView);
            if (elementView != oldElementView) {
                InternalEObject newElementView = (InternalEObject) elementView;
                NotificationChain msgs = oldElementView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW, null, null);
                if (newElementView.eInternalContainer() == null) {
                    msgs = newElementView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW, oldElementView, elementView));
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
    public ElementDoubleClickVariable basicGetElementView() {
        return elementView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElementView(ElementDoubleClickVariable newElementView, NotificationChain msgs) {
        ElementDoubleClickVariable oldElementView = elementView;
        elementView = newElementView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW, oldElementView, newElementView);
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
    public void setElementView(ElementDoubleClickVariable newElementView) {
        if (newElementView != elementView) {
            NotificationChain msgs = null;
            if (elementView != null) {
                msgs = ((InternalEObject) elementView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW, null, msgs);
            }
            if (newElementView != null) {
                msgs = ((InternalEObject) newElementView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW, null, msgs);
            }
            msgs = basicSetElementView(newElementView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW, newElementView, newElementView));
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
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getMappings()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS:
            return ((InternalEList<?>) getMappings()).basicRemove(otherEnd, msgs);
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT:
            return basicSetElement(null, msgs);
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW:
            return basicSetElementView(null, msgs);
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS:
            return getMappings();
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT:
            if (resolve) {
                return getElement();
            }
            return basicGetElement();
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW:
            if (resolve) {
                return getElementView();
            }
            return basicGetElementView();
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION:
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
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS:
            getMappings().clear();
            getMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT:
            setElement((ElementDoubleClickVariable) newValue);
            return;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW:
            setElementView((ElementDoubleClickVariable) newValue);
            return;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS:
            getMappings().clear();
            return;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT:
            setElement((ElementDoubleClickVariable) null);
            return;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW:
            setElementView((ElementDoubleClickVariable) null);
            return;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS:
            return mappings != null && !mappings.isEmpty();
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT:
            return element != null;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW:
            return elementView != null;
        case ToolPackage.DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        }
        return super.eIsSet(featureID);
    }

} // DoubleClickDescriptionImpl
