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
package org.eclipse.sirius.diagram;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Collapse Filter</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.CollapseFilter#getWidth <em>Width</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.diagram.CollapseFilter#getHeight
 * <em>Height</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getCollapseFilter()
 * @model annotation=
 *        "http://www.eclipse.org/emf/2002/GenModel Documentation='Graphical filter specifying that the owner element has to be collapsed.'"
 * @generated
 */
public interface CollapseFilter extends GraphicalFilter {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(int)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getCollapseFilter_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.CollapseFilter#getWidth <em>Width</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(int)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getCollapseFilter_Height()
     * @model
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.CollapseFilter#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(int value);

} // CollapseFilter
