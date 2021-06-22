/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree;

import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemUpdater;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DTree Item</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.DTreeItem#isExpanded <em>Expanded</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.DTreeItem#getOwnedStyle <em>Owned Style</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.DTreeItem#getActualMapping <em>Actual Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.DTreeItem#getContainer <em>Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.DTreeItem#getStyleUpdater <em>Style Updater</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.DTreeItem#getUpdater <em>Updater</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.TreePackage#getDTreeItem()
 * @model
 * @generated
 */
public interface DTreeItem extends DTreeItemContainer, DTreeElement {
    /**
     * Returns the value of the '<em><b>Expanded</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expanded</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Expanded</em>' attribute.
     * @see #setExpanded(boolean)
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeItem_Expanded()
     * @model required="true"
     * @generated
     */
    boolean isExpanded();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.DTreeItem#isExpanded <em>Expanded</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Expanded</em>' attribute.
     * @see #isExpanded()
     * @generated
     */
    void setExpanded(boolean value);

    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Style</em>' containment reference.
     * @see #setOwnedStyle(TreeItemStyle)
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeItem_OwnedStyle()
     * @model containment="true" required="true"
     * @generated
     */
    TreeItemStyle getOwnedStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.DTreeItem#getOwnedStyle <em>Owned Style</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owned Style</em>' containment reference.
     * @see #getOwnedStyle()
     * @generated
     */
    void setOwnedStyle(TreeItemStyle value);

    /**
     * Returns the value of the '<em><b>Actual Mapping</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Actual Mapping</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Actual Mapping</em>' reference.
     * @see #setActualMapping(TreeItemMapping)
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeItem_ActualMapping()
     * @model required="true"
     * @generated
     */
    TreeItemMapping getActualMapping();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.DTreeItem#getActualMapping <em>Actual Mapping</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Actual Mapping</em>' reference.
     * @see #getActualMapping()
     * @generated
     */
    void setActualMapping(TreeItemMapping value);

    /**
     * Returns the value of the '<em><b>Container</b></em>' container reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.tree.DTreeItemContainer#getOwnedTreeItems <em>Owned Tree Items</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Container</em>' container reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Container</em>' container reference.
     * @see #setContainer(DTreeItemContainer)
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeItem_Container()
     * @see org.eclipse.sirius.tree.DTreeItemContainer#getOwnedTreeItems
     * @model opposite="ownedTreeItems" transient="false"
     * @generated
     */
    DTreeItemContainer getContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.DTreeItem#getContainer <em>Container</em>}' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container</em>' container reference.
     * @see #getContainer()
     * @generated
     */
    void setContainer(DTreeItemContainer value);

    /**
     * Returns the value of the '<em><b>Style Updater</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style Updater</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style Updater</em>' reference.
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeItem_StyleUpdater()
     * @model transient="true" changeable="false" volatile="true"
     * @generated
     */
    StyleUpdater getStyleUpdater();

    /**
     * Returns the value of the '<em><b>Updater</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Updater</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Updater</em>' reference.
     * @see #setUpdater(TreeItemUpdater)
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeItem_Updater()
     * @model transient="true" volatile="true" derived="true"
     * @generated
     */
    TreeItemUpdater getUpdater();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.DTreeItem#getUpdater <em>Updater</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Updater</em>' reference.
     * @see #getUpdater()
     * @generated
     */
    void setUpdater(TreeItemUpdater value);

} // DTreeItem
