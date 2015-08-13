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
package org.eclipse.sirius.table.metamodel.table.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Create Cell Tool</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getPrecondition
 * <em>Precondition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#isForceRefresh
 * <em>Force Refresh</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getElementsToSelect
 * <em>Elements To Select</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#isInverseSelectionOrder
 * <em>Inverse Selection Order</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getMask
 * <em>Mask</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl#getMapping
 * <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CreateCellToolImpl extends TableToolImpl implements CreateCellTool {
    /**
     * The default value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = CreateCellToolImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = CreateCellToolImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = CreateCellToolImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getPrecondition()
     * <em>Precondition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected String precondition = CreateCellToolImpl.PRECONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #isForceRefresh()
     * <em>Force Refresh</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected static final boolean FORCE_REFRESH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isForceRefresh() <em>Force Refresh</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected boolean forceRefresh = CreateCellToolImpl.FORCE_REFRESH_EDEFAULT;

    /**
     * The cached value of the '{@link #getFilters() <em>Filters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFilters()
     * @generated
     * @ordered
     */
    protected EList<ToolFilterDescription> filters;

    /**
     * The default value of the '{@link #getElementsToSelect()
     * <em>Elements To Select</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected static final String ELEMENTS_TO_SELECT_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getElementsToSelect()
     * <em>Elements To Select</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected String elementsToSelect = CreateCellToolImpl.ELEMENTS_TO_SELECT_EDEFAULT;

    /**
     * The default value of the '{@link #isInverseSelectionOrder()
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected static final boolean INVERSE_SELECTION_ORDER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInverseSelectionOrder()
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected boolean inverseSelectionOrder = CreateCellToolImpl.INVERSE_SELECTION_ORDER_EDEFAULT;

    /**
     * The cached value of the '{@link #getMask() <em>Mask</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMask()
     * @generated
     * @ordered
     */
    protected EditMaskVariables mask;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CreateCellToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CREATE_CELL_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION, oldDocumentation, documentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPrecondition() {
        return precondition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPrecondition(String newPrecondition) {
        String oldPrecondition = precondition;
        precondition = newPrecondition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION, oldPrecondition, precondition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isForceRefresh() {
        return forceRefresh;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setForceRefresh(boolean newForceRefresh) {
        boolean oldForceRefresh = forceRefresh;
        forceRefresh = newForceRefresh;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH, oldForceRefresh, forceRefresh));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ToolFilterDescription> getFilters() {
        if (filters == null) {
            filters = new EObjectContainmentEList.Resolving<ToolFilterDescription>(ToolFilterDescription.class, this, DescriptionPackage.CREATE_CELL_TOOL__FILTERS);
        }
        return filters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getElementsToSelect() {
        return elementsToSelect;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setElementsToSelect(String newElementsToSelect) {
        String oldElementsToSelect = elementsToSelect;
        elementsToSelect = newElementsToSelect;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT, oldElementsToSelect, elementsToSelect));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isInverseSelectionOrder() {
        return inverseSelectionOrder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInverseSelectionOrder(boolean newInverseSelectionOrder) {
        boolean oldInverseSelectionOrder = inverseSelectionOrder;
        inverseSelectionOrder = newInverseSelectionOrder;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER, oldInverseSelectionOrder, inverseSelectionOrder));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EditMaskVariables getMask() {
        return mask;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetMask(EditMaskVariables newMask, NotificationChain msgs) {
        EditMaskVariables oldMask = mask;
        mask = newMask;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__MASK, oldMask, newMask);
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
    public void setMask(EditMaskVariables newMask) {
        if (newMask != mask) {
            NotificationChain msgs = null;
            if (mask != null) {
                msgs = ((InternalEObject) mask).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CREATE_CELL_TOOL__MASK, null, msgs);
            }
            if (newMask != null) {
                msgs = ((InternalEObject) newMask).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CREATE_CELL_TOOL__MASK, null, msgs);
            }
            msgs = basicSetMask(newMask, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__MASK, newMask, newMask));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntersectionMapping getMapping() {
        if (eContainerFeatureID() != DescriptionPackage.CREATE_CELL_TOOL__MAPPING) {
            return null;
        }
        return (IntersectionMapping) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetMapping(IntersectionMapping newMapping, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newMapping, DescriptionPackage.CREATE_CELL_TOOL__MAPPING, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMapping(IntersectionMapping newMapping) {
        if (newMapping != eInternalContainer() || (eContainerFeatureID() != DescriptionPackage.CREATE_CELL_TOOL__MAPPING && newMapping != null)) {
            if (EcoreUtil.isAncestor(this, newMapping)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            }
            NotificationChain msgs = null;
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            if (newMapping != null) {
                msgs = ((InternalEObject) newMapping).eInverseAdd(this, DescriptionPackage.INTERSECTION_MAPPING__CREATE, IntersectionMapping.class, msgs);
            }
            msgs = basicSetMapping(newMapping, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CREATE_CELL_TOOL__MAPPING, newMapping, newMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.CREATE_CELL_TOOL__MAPPING:
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            return basicSetMapping((IntersectionMapping) otherEnd, msgs);
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
        case DescriptionPackage.CREATE_CELL_TOOL__FILTERS:
            return ((InternalEList<?>) getFilters()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.CREATE_CELL_TOOL__MASK:
            return basicSetMask(null, msgs);
        case DescriptionPackage.CREATE_CELL_TOOL__MAPPING:
            return basicSetMapping(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
        case DescriptionPackage.CREATE_CELL_TOOL__MAPPING:
            return eInternalContainer().eInverseRemove(this, DescriptionPackage.INTERSECTION_MAPPING__CREATE, IntersectionMapping.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION:
            return getDocumentation();
        case DescriptionPackage.CREATE_CELL_TOOL__NAME:
            return getName();
        case DescriptionPackage.CREATE_CELL_TOOL__LABEL:
            return getLabel();
        case DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION:
            return getPrecondition();
        case DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH:
            return isForceRefresh();
        case DescriptionPackage.CREATE_CELL_TOOL__FILTERS:
            return getFilters();
        case DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT:
            return getElementsToSelect();
        case DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER:
            return isInverseSelectionOrder();
        case DescriptionPackage.CREATE_CELL_TOOL__MASK:
            return getMask();
        case DescriptionPackage.CREATE_CELL_TOOL__MAPPING:
            return getMapping();
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
        case DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION:
            setPrecondition((String) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH:
            setForceRefresh((Boolean) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__FILTERS:
            getFilters().clear();
            getFilters().addAll((Collection<? extends ToolFilterDescription>) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT:
            setElementsToSelect((String) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder((Boolean) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__MASK:
            setMask((EditMaskVariables) newValue);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__MAPPING:
            setMapping((IntersectionMapping) newValue);
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
        case DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION:
            setDocumentation(CreateCellToolImpl.DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__NAME:
            setName(CreateCellToolImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__LABEL:
            setLabel(CreateCellToolImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION:
            setPrecondition(CreateCellToolImpl.PRECONDITION_EDEFAULT);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH:
            setForceRefresh(CreateCellToolImpl.FORCE_REFRESH_EDEFAULT);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__FILTERS:
            getFilters().clear();
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT:
            setElementsToSelect(CreateCellToolImpl.ELEMENTS_TO_SELECT_EDEFAULT);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder(CreateCellToolImpl.INVERSE_SELECTION_ORDER_EDEFAULT);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__MASK:
            setMask((EditMaskVariables) null);
            return;
        case DescriptionPackage.CREATE_CELL_TOOL__MAPPING:
            setMapping((IntersectionMapping) null);
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
        case DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION:
            return CreateCellToolImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !CreateCellToolImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case DescriptionPackage.CREATE_CELL_TOOL__NAME:
            return CreateCellToolImpl.NAME_EDEFAULT == null ? name != null : !CreateCellToolImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.CREATE_CELL_TOOL__LABEL:
            return CreateCellToolImpl.LABEL_EDEFAULT == null ? label != null : !CreateCellToolImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION:
            return CreateCellToolImpl.PRECONDITION_EDEFAULT == null ? precondition != null : !CreateCellToolImpl.PRECONDITION_EDEFAULT.equals(precondition);
        case DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH:
            return forceRefresh != CreateCellToolImpl.FORCE_REFRESH_EDEFAULT;
        case DescriptionPackage.CREATE_CELL_TOOL__FILTERS:
            return filters != null && !filters.isEmpty();
        case DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT:
            return CreateCellToolImpl.ELEMENTS_TO_SELECT_EDEFAULT == null ? elementsToSelect != null : !CreateCellToolImpl.ELEMENTS_TO_SELECT_EDEFAULT.equals(elementsToSelect);
        case DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER:
            return inverseSelectionOrder != CreateCellToolImpl.INVERSE_SELECTION_ORDER_EDEFAULT;
        case DescriptionPackage.CREATE_CELL_TOOL__MASK:
            return mask != null;
        case DescriptionPackage.CREATE_CELL_TOOL__MAPPING:
            return getMapping() != null;
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
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.CREATE_CELL_TOOL__NAME:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case DescriptionPackage.CREATE_CELL_TOOL__LABEL:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == ToolEntry.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == AbstractToolDescription.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION:
                return ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;
            case DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH:
                return ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;
            case DescriptionPackage.CREATE_CELL_TOOL__FILTERS:
                return ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;
            case DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT:
                return ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;
            case DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER:
                return ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;
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
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return DescriptionPackage.CREATE_CELL_TOOL__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return DescriptionPackage.CREATE_CELL_TOOL__NAME;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return DescriptionPackage.CREATE_CELL_TOOL__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == ToolEntry.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == AbstractToolDescription.class) {
            switch (baseFeatureID) {
            case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION:
                return DescriptionPackage.CREATE_CELL_TOOL__PRECONDITION;
            case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH:
                return DescriptionPackage.CREATE_CELL_TOOL__FORCE_REFRESH;
            case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS:
                return DescriptionPackage.CREATE_CELL_TOOL__FILTERS;
            case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
                return DescriptionPackage.CREATE_CELL_TOOL__ELEMENTS_TO_SELECT;
            case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER:
                return DescriptionPackage.CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER;
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", precondition: "); //$NON-NLS-1$
        result.append(precondition);
        result.append(", forceRefresh: "); //$NON-NLS-1$
        result.append(forceRefresh);
        result.append(", elementsToSelect: "); //$NON-NLS-1$
        result.append(elementsToSelect);
        result.append(", inverseSelectionOrder: "); //$NON-NLS-1$
        result.append(inverseSelectionOrder);
        result.append(')');
        return result.toString();
    }

} // CreateCellToolImpl
