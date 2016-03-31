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
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Section</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A tool section enclosed some tools. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.tool.ToolSection#getIcon
 * <em>Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getOwnedTools
 * <em>Owned Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getSubSections
 * <em>Sub Sections</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getPopupMenus
 * <em>Popup Menus</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getReusedTools
 * <em>Reused Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getGroupExtensions
 * <em>Group Extensions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolSection()
 * @model
 * @generated
 */
public interface ToolSection extends DocumentedElement, IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> image
     * path used for presenting the section with this icon in the palette <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Icon</em>' attribute.
     * @see #setIcon(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolSection_Icon()
     * @model
     * @generated
     */
    String getIcon();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getIcon
     * <em>Icon</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Icon</em>' attribute.
     * @see #getIcon()
     * @generated
     */
    void setIcon(String value);

    /**
     * Returns the value of the '<em><b>Owned Tools</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * tools of the section. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Tools</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolSection_OwnedTools()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<ToolEntry> getOwnedTools();

    /**
     * Returns the value of the '<em><b>Sub Sections</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.tool.ToolSection}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All sub
     * sections <!-- end-model-doc -->
     *
     * @return the value of the '<em>Sub Sections</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolSection_SubSections()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<ToolSection> getSubSections();

    /**
     * Returns the value of the '<em><b>Popup Menus</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.PopupMenu}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Popup
     * menus available on this layer. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Popup Menus</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolSection_PopupMenus()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<PopupMenu> getPopupMenus();

    /**
     * Returns the value of the '<em><b>Reused Tools</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Tools
     * that are reused by this viewpoint. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reused Tools</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolSection_ReusedTools()
     * @model
     * @generated
     */
    EList<ToolEntry> getReusedTools();

    /**
     * Returns the value of the '<em><b>Group Extensions</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group Extensions</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Group Extensions</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolSection_GroupExtensions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ToolGroupExtension> getGroupExtensions();

} // ToolSection
