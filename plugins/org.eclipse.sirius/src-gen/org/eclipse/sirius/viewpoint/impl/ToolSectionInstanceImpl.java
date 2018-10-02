/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.ToolInstance;
import org.eclipse.sirius.viewpoint.ToolSectionInstance;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tool Section Instance</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolSectionInstanceImpl#getTools <em>Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolSectionInstanceImpl#getSection <em>Section</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolSectionInstanceImpl#getSubSections <em>Sub Sections</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ToolSectionInstanceImpl extends ToolInstanceImpl implements ToolSectionInstance {
    /**
     * The cached value of the '{@link #getTools() <em>Tools</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getTools()
     * @generated
     * @ordered
     */
    protected EList<ToolInstance> tools;

    /**
     * The cached value of the '{@link #getSection() <em>Section</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSection()
     * @generated
     * @ordered
     */
    protected EObject section;

    /**
     * The cached value of the '{@link #getSubSections() <em>Sub Sections</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubSections()
     * @generated
     * @ordered
     */
    protected EList<ToolSectionInstance> subSections;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolSectionInstanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.TOOL_SECTION_INSTANCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolInstance> getTools() {
        if (tools == null) {
            tools = new EObjectContainmentEList.Resolving<ToolInstance>(ToolInstance.class, this, ViewpointPackage.TOOL_SECTION_INSTANCE__TOOLS);
        }
        return tools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getSection() {
        if (section != null && section.eIsProxy()) {
            InternalEObject oldSection = (InternalEObject) section;
            section = eResolveProxy(oldSection);
            if (section != oldSection) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.TOOL_SECTION_INSTANCE__SECTION, oldSection, section));
                }
            }
        }
        return section;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObject basicGetSection() {
        return section;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSection(EObject newSection) {
        EObject oldSection = section;
        section = newSection;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_SECTION_INSTANCE__SECTION, oldSection, section));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolSectionInstance> getSubSections() {
        if (subSections == null) {
            subSections = new EObjectContainmentEList.Resolving<ToolSectionInstance>(ToolSectionInstance.class, this, ViewpointPackage.TOOL_SECTION_INSTANCE__SUB_SECTIONS);
        }
        return subSections;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.TOOL_SECTION_INSTANCE__TOOLS:
            return ((InternalEList<?>) getTools()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SUB_SECTIONS:
            return ((InternalEList<?>) getSubSections()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.TOOL_SECTION_INSTANCE__TOOLS:
            return getTools();
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SECTION:
            if (resolve) {
                return getSection();
            }
            return basicGetSection();
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SUB_SECTIONS:
            return getSubSections();
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
        case ViewpointPackage.TOOL_SECTION_INSTANCE__TOOLS:
            getTools().clear();
            getTools().addAll((Collection<? extends ToolInstance>) newValue);
            return;
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SECTION:
            setSection((EObject) newValue);
            return;
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SUB_SECTIONS:
            getSubSections().clear();
            getSubSections().addAll((Collection<? extends ToolSectionInstance>) newValue);
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
        case ViewpointPackage.TOOL_SECTION_INSTANCE__TOOLS:
            getTools().clear();
            return;
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SECTION:
            setSection((EObject) null);
            return;
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SUB_SECTIONS:
            getSubSections().clear();
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
        case ViewpointPackage.TOOL_SECTION_INSTANCE__TOOLS:
            return tools != null && !tools.isEmpty();
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SECTION:
            return section != null;
        case ViewpointPackage.TOOL_SECTION_INSTANCE__SUB_SECTIONS:
            return subSections != null && !subSections.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ToolSectionInstanceImpl
