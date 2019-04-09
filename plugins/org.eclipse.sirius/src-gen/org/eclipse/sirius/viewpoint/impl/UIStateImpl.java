/**
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.ToolSectionInstance;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>UI State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl#isInverseSelectionOrder <em>Inverse Selection
 * Order</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl#getElementsToSelect <em>Elements To Select</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl#getDecorationImage <em>Decoration Image</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl#getToolSections <em>Tool Sections</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl#getSubDiagramDecorationDescriptors <em>Sub Diagram
 * Decoration Descriptors</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UIStateImpl extends MinimalEObjectImpl.Container implements UIState {
    /**
     * The default value of the '{@link #isInverseSelectionOrder() <em>Inverse Selection Order</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected static final boolean INVERSE_SELECTION_ORDER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInverseSelectionOrder() <em>Inverse Selection Order</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected boolean inverseSelectionOrder = UIStateImpl.INVERSE_SELECTION_ORDER_EDEFAULT;

    /**
     * The cached value of the '{@link #getElementsToSelect() <em>Elements To Select</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected EList<EObject> elementsToSelect;

    /**
     * The cached value of the '{@link #getDecorationImage() <em>Decoration Image</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getDecorationImage()
     * @generated
     * @ordered
     */
    protected Map<Decoration, Object> decorationImage;

    /**
     * The cached value of the '{@link #getToolSections() <em>Tool Sections</em>}' reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getToolSections()
     * @generated
     * @ordered
     */
    protected EList<ToolSectionInstance> toolSections;

    /**
     * The cached value of the '{@link #getSubDiagramDecorationDescriptors() <em>Sub Diagram Decoration
     * Descriptors</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubDiagramDecorationDescriptors()
     * @generated
     * @ordered
     */
    protected Map<Object, Object> subDiagramDecorationDescriptors;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    protected UIStateImpl() {
        super();
        setDecorationImage(new HashMap<>());
        setSubDiagramDecorationDescriptors(new HashMap<>());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.UI_STATE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER, oldInverseSelectionOrder, inverseSelectionOrder));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EObject> getElementsToSelect() {
        if (elementsToSelect == null) {
            elementsToSelect = new EObjectEList.Unsettable<EObject>(EObject.class, this, ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT);
        }
        return elementsToSelect;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetElementsToSelect() {
        if (elementsToSelect != null) {
            ((InternalEList.Unsettable<?>) elementsToSelect).unset();
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetElementsToSelect() {
        return elementsToSelect != null && ((InternalEList.Unsettable<?>) elementsToSelect).isSet();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Map<Decoration, Object> getDecorationImage() {
        return decorationImage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDecorationImage(Map<Decoration, Object> newDecorationImage) {
        Map<Decoration, Object> oldDecorationImage = decorationImage;
        decorationImage = newDecorationImage;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.UI_STATE__DECORATION_IMAGE, oldDecorationImage, decorationImage));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolSectionInstance> getToolSections() {
        if (toolSections == null) {
            toolSections = new EObjectEList.Unsettable<ToolSectionInstance>(ToolSectionInstance.class, this, ViewpointPackage.UI_STATE__TOOL_SECTIONS);
        }
        return toolSections;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetToolSections() {
        if (toolSections != null) {
            ((InternalEList.Unsettable<?>) toolSections).unset();
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetToolSections() {
        return toolSections != null && ((InternalEList.Unsettable<?>) toolSections).isSet();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Map<Object, Object> getSubDiagramDecorationDescriptors() {
        return subDiagramDecorationDescriptors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSubDiagramDecorationDescriptors(Map<Object, Object> newSubDiagramDecorationDescriptors) {
        Map<Object, Object> oldSubDiagramDecorationDescriptors = subDiagramDecorationDescriptors;
        subDiagramDecorationDescriptors = newSubDiagramDecorationDescriptors;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS, oldSubDiagramDecorationDescriptors, subDiagramDecorationDescriptors));
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
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            return isInverseSelectionOrder();
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            return getElementsToSelect();
        case ViewpointPackage.UI_STATE__DECORATION_IMAGE:
            return getDecorationImage();
        case ViewpointPackage.UI_STATE__TOOL_SECTIONS:
            return getToolSections();
        case ViewpointPackage.UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS:
            return getSubDiagramDecorationDescriptors();
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
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder((Boolean) newValue);
            return;
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            getElementsToSelect().clear();
            getElementsToSelect().addAll((Collection<? extends EObject>) newValue);
            return;
        case ViewpointPackage.UI_STATE__DECORATION_IMAGE:
            setDecorationImage((Map<Decoration, Object>) newValue);
            return;
        case ViewpointPackage.UI_STATE__TOOL_SECTIONS:
            getToolSections().clear();
            getToolSections().addAll((Collection<? extends ToolSectionInstance>) newValue);
            return;
        case ViewpointPackage.UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS:
            setSubDiagramDecorationDescriptors((Map<Object, Object>) newValue);
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
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder(UIStateImpl.INVERSE_SELECTION_ORDER_EDEFAULT);
            return;
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            unsetElementsToSelect();
            return;
        case ViewpointPackage.UI_STATE__DECORATION_IMAGE:
            setDecorationImage((Map<Decoration, Object>) null);
            return;
        case ViewpointPackage.UI_STATE__TOOL_SECTIONS:
            unsetToolSections();
            return;
        case ViewpointPackage.UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS:
            setSubDiagramDecorationDescriptors((Map<Object, Object>) null);
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
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            return inverseSelectionOrder != UIStateImpl.INVERSE_SELECTION_ORDER_EDEFAULT;
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            return isSetElementsToSelect();
        case ViewpointPackage.UI_STATE__DECORATION_IMAGE:
            return decorationImage != null;
        case ViewpointPackage.UI_STATE__TOOL_SECTIONS:
            return isSetToolSections();
        case ViewpointPackage.UI_STATE__SUB_DIAGRAM_DECORATION_DESCRIPTORS:
            return subDiagramDecorationDescriptors != null;
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
        result.append(" (inverseSelectionOrder: "); //$NON-NLS-1$
        result.append(inverseSelectionOrder);
        result.append(", decorationImage: "); //$NON-NLS-1$
        result.append(decorationImage);
        result.append(", subDiagramDecorationDescriptors: "); //$NON-NLS-1$
        result.append(subDiagramDecorationDescriptors);
        result.append(')');
        return result.toString();
    }

} // UIStateImpl
