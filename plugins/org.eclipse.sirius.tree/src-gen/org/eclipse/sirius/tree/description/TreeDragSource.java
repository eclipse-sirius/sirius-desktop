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
package org.eclipse.sirius.tree.description;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Tree Drag Source</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeDragSource()
 * @model
 * @generated
 */
public enum TreeDragSource implements Enumerator {
    /**
     * The '<em><b>TREE</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #TREE_VALUE
     * @generated
     * @ordered
     */
    TREE(1, "TREE", "TREE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>PROJECT EXPLORER</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #PROJECT_EXPLORER_VALUE
     * @generated
     * @ordered
     */
    PROJECT_EXPLORER(2, "PROJECT_EXPLORER", "PROJECT_EXPLORER"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>BOTH</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #BOTH_VALUE
     * @generated
     * @ordered
     */
    BOTH(3, "BOTH", "BOTH"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TREE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TREE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #TREE
     * @model
     * @generated
     * @ordered
     */
    public static final int TREE_VALUE = 1;

    /**
     * The '<em><b>PROJECT EXPLORER</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>PROJECT EXPLORER</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PROJECT_EXPLORER
     * @model
     * @generated
     * @ordered
     */
    public static final int PROJECT_EXPLORER_VALUE = 2;

    /**
     * The '<em><b>BOTH</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BOTH</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #BOTH
     * @model
     * @generated
     * @ordered
     */
    public static final int BOTH_VALUE = 3;

    /**
     * An array of all the '<em><b>Tree Drag Source</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final TreeDragSource[] VALUES_ARRAY = new TreeDragSource[] { TREE, PROJECT_EXPLORER, BOTH, };

    /**
     * A public read-only list of all the '<em><b>Tree Drag Source</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<TreeDragSource> VALUES = Collections.unmodifiableList(Arrays.asList(TreeDragSource.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Tree Drag Source</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TreeDragSource get(String literal) {
        for (TreeDragSource result : TreeDragSource.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Tree Drag Source</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TreeDragSource getByName(String name) {
        for (TreeDragSource result : TreeDragSource.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Tree Drag Source</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TreeDragSource get(int value) {
        switch (value) {
        case TREE_VALUE:
            return TREE;
        case PROJECT_EXPLORER_VALUE:
            return PROJECT_EXPLORER;
        case BOTH_VALUE:
            return BOTH;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    private TreeDragSource(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getValue() {
        return value;
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
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string
     * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // TreeDragSource
