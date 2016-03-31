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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Section</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getIcon
 * <em>Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getOwnedTools
 * <em>Owned Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getSubSections
 * <em>Sub Sections</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getPopupMenus
 * <em>Popup Menus</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getReusedTools
 * <em>Reused Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl#getGroupExtensions
 * <em>Group Extensions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ToolSectionImpl extends DocumentedElementImpl implements ToolSection {
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
    protected String name = ToolSectionImpl.NAME_EDEFAULT;

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
    protected String label = ToolSectionImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected static final String ICON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected String icon = ToolSectionImpl.ICON_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedTools() <em>Owned Tools</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedTools()
     * @generated
     * @ordered
     */
    protected EList<ToolEntry> ownedTools;

    /**
     * The cached value of the '{@link #getSubSections() <em>Sub Sections</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubSections()
     * @generated
     * @ordered
     */
    protected EList<ToolSection> subSections;

    /**
     * The cached value of the '{@link #getReusedTools() <em>Reused Tools</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReusedTools()
     * @generated
     * @ordered
     */
    protected EList<ToolEntry> reusedTools;

    /**
     * The cached value of the '{@link #getGroupExtensions()
     * <em>Group Extensions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getGroupExtensions()
     * @generated
     * @ordered
     */
    protected EList<ToolGroupExtension> groupExtensions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolSectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.TOOL_SECTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.TOOL_SECTION__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.TOOL_SECTION__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIcon() {
        return icon;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIcon(String newIcon) {
        String oldIcon = icon;
        icon = newIcon;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.TOOL_SECTION__ICON, oldIcon, icon));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolEntry> getOwnedTools() {
        if (ownedTools == null) {
            ownedTools = new EObjectContainmentEList.Resolving<ToolEntry>(ToolEntry.class, this, ToolPackage.TOOL_SECTION__OWNED_TOOLS);
        }
        return ownedTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolSection> getSubSections() {
        if (subSections == null) {
            subSections = new EObjectContainmentEList.Resolving<ToolSection>(ToolSection.class, this, ToolPackage.TOOL_SECTION__SUB_SECTIONS);
        }
        return subSections;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PopupMenu> getPopupMenus() {
        // TODO: implement this method to return the 'Popup Menus' reference
        // list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolEntry> getReusedTools() {
        if (reusedTools == null) {
            reusedTools = new EObjectResolvingEList<ToolEntry>(ToolEntry.class, this, ToolPackage.TOOL_SECTION__REUSED_TOOLS);
        }
        return reusedTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolGroupExtension> getGroupExtensions() {
        if (groupExtensions == null) {
            groupExtensions = new EObjectContainmentEList.Resolving<ToolGroupExtension>(ToolGroupExtension.class, this, ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS);
        }
        return groupExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.TOOL_SECTION__OWNED_TOOLS:
            return ((InternalEList<?>) getOwnedTools()).basicRemove(otherEnd, msgs);
        case ToolPackage.TOOL_SECTION__SUB_SECTIONS:
            return ((InternalEList<?>) getSubSections()).basicRemove(otherEnd, msgs);
        case ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS:
            return ((InternalEList<?>) getGroupExtensions()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.TOOL_SECTION__NAME:
            return getName();
        case ToolPackage.TOOL_SECTION__LABEL:
            return getLabel();
        case ToolPackage.TOOL_SECTION__ICON:
            return getIcon();
        case ToolPackage.TOOL_SECTION__OWNED_TOOLS:
            return getOwnedTools();
        case ToolPackage.TOOL_SECTION__SUB_SECTIONS:
            return getSubSections();
        case ToolPackage.TOOL_SECTION__POPUP_MENUS:
            return getPopupMenus();
        case ToolPackage.TOOL_SECTION__REUSED_TOOLS:
            return getReusedTools();
        case ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS:
            return getGroupExtensions();
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
        case ToolPackage.TOOL_SECTION__NAME:
            setName((String) newValue);
            return;
        case ToolPackage.TOOL_SECTION__LABEL:
            setLabel((String) newValue);
            return;
        case ToolPackage.TOOL_SECTION__ICON:
            setIcon((String) newValue);
            return;
        case ToolPackage.TOOL_SECTION__OWNED_TOOLS:
            getOwnedTools().clear();
            getOwnedTools().addAll((Collection<? extends ToolEntry>) newValue);
            return;
        case ToolPackage.TOOL_SECTION__SUB_SECTIONS:
            getSubSections().clear();
            getSubSections().addAll((Collection<? extends ToolSection>) newValue);
            return;
        case ToolPackage.TOOL_SECTION__REUSED_TOOLS:
            getReusedTools().clear();
            getReusedTools().addAll((Collection<? extends ToolEntry>) newValue);
            return;
        case ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS:
            getGroupExtensions().clear();
            getGroupExtensions().addAll((Collection<? extends ToolGroupExtension>) newValue);
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
        case ToolPackage.TOOL_SECTION__NAME:
            setName(ToolSectionImpl.NAME_EDEFAULT);
            return;
        case ToolPackage.TOOL_SECTION__LABEL:
            setLabel(ToolSectionImpl.LABEL_EDEFAULT);
            return;
        case ToolPackage.TOOL_SECTION__ICON:
            setIcon(ToolSectionImpl.ICON_EDEFAULT);
            return;
        case ToolPackage.TOOL_SECTION__OWNED_TOOLS:
            getOwnedTools().clear();
            return;
        case ToolPackage.TOOL_SECTION__SUB_SECTIONS:
            getSubSections().clear();
            return;
        case ToolPackage.TOOL_SECTION__REUSED_TOOLS:
            getReusedTools().clear();
            return;
        case ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS:
            getGroupExtensions().clear();
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
        case ToolPackage.TOOL_SECTION__NAME:
            return ToolSectionImpl.NAME_EDEFAULT == null ? name != null : !ToolSectionImpl.NAME_EDEFAULT.equals(name);
        case ToolPackage.TOOL_SECTION__LABEL:
            return ToolSectionImpl.LABEL_EDEFAULT == null ? label != null : !ToolSectionImpl.LABEL_EDEFAULT.equals(label);
        case ToolPackage.TOOL_SECTION__ICON:
            return ToolSectionImpl.ICON_EDEFAULT == null ? icon != null : !ToolSectionImpl.ICON_EDEFAULT.equals(icon);
        case ToolPackage.TOOL_SECTION__OWNED_TOOLS:
            return ownedTools != null && !ownedTools.isEmpty();
        case ToolPackage.TOOL_SECTION__SUB_SECTIONS:
            return subSections != null && !subSections.isEmpty();
        case ToolPackage.TOOL_SECTION__POPUP_MENUS:
            return !getPopupMenus().isEmpty();
        case ToolPackage.TOOL_SECTION__REUSED_TOOLS:
            return reusedTools != null && !reusedTools.isEmpty();
        case ToolPackage.TOOL_SECTION__GROUP_EXTENSIONS:
            return groupExtensions != null && !groupExtensions.isEmpty();
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
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case ToolPackage.TOOL_SECTION__NAME:
                return DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case ToolPackage.TOOL_SECTION__LABEL:
                return DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
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
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return ToolPackage.TOOL_SECTION__NAME;
            case DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return ToolPackage.TOOL_SECTION__LABEL;
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", icon: "); //$NON-NLS-1$
        result.append(icon);
        result.append(')');
        return result.toString();
    }

} // ToolSectionImpl
