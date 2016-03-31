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
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Ordered Tree Layout</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.OrderedTreeLayout#getChildrenExpression
 * <em>Children Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.OrderedTreeLayout#getNodeMapping
 * <em>Node Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getOrderedTreeLayout()
 * @model
 * @generated
 */
public interface OrderedTreeLayout extends Layout {
    /**
     * Returns the value of the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An
     * expression returning the semantic children of an element, the result of
     * this expression is used to compute a hiearchical tree for the layout.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Children Expression</em>' attribute.
     * @see #setChildrenExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getOrderedTreeLayout_ChildrenExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an EObject.'"
     * @generated
     */
    String getChildrenExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.OrderedTreeLayout#getChildrenExpression
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
     * Returns the value of the '<em><b>Node Mapping</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * list of mappings affected by the ordered tree routing. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Node Mapping</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getOrderedTreeLayout_NodeMapping()
     * @model required="true"
     * @generated
     */
    EList<AbstractNodeMapping> getNodeMapping();

} // OrderedTreeLayout
