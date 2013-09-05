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
package org.eclipse.sirius.description;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Ordered Tree Layout</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.OrderedTreeLayout#getChildrenExpression
 * <em>Children Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.OrderedTreeLayout#getNodeMapping
 * <em>Node Mapping</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.DescriptionPackage#getOrderedTreeLayout()
 * @model
 * @generated
 */
public interface OrderedTreeLayout extends Layout {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The acceleo expression that computes the children element of this
     * element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Children Expression</em>' attribute.
     * @see #setChildrenExpression(String)
     * @see org.eclipse.sirius.description.DescriptionPackage#getOrderedTreeLayout_ChildrenExpression()
     * @model dataType="org.eclipse.sirius.description.AcceleoExpression"
     *        required="true"
     * @generated
     */
    String getChildrenExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.OrderedTreeLayout#getChildrenExpression
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Children Expression</em>' attribute.
     * @see #getChildrenExpression()
     * @generated
     */
    void setChildrenExpression(String value);

    /**
     * Returns the value of the '<em><b>Node Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * domain class of the mapping. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Node Mapping</em>' reference.
     * @see #setNodeMapping(AbstractNodeMapping)
     * @see org.eclipse.sirius.description.DescriptionPackage#getOrderedTreeLayout_NodeMapping()
     * @model required="true"
     * @generated
     */
    EList<AbstractNodeMapping> getNodeMapping();

} // OrderedTreeLayout
