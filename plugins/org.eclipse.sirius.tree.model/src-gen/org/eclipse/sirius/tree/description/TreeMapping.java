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
package org.eclipse.sirius.tree.description;

import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeMapping#getSemanticElements <em>Semantic Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeMapping()
 * @model
 * @generated
 */
public interface TreeMapping extends RepresentationElementMapping {
    /**
     * Returns the value of the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Elements</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Elements</em>' attribute.
     * @see #setSemanticElements(String)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeMapping_SemanticElements()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables view='tree.DTreeElement |
     *        current DTreeElement.' containerView='ecore.EObject | container of the current DTreeElement (variable is
     *        available if container is not null).' container='ecore.EObject | semantic target of $containerView (if it
     *        is a DSemanticDecorator).'"
     * @generated
     */
    String getSemanticElements();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeMapping#getSemanticElements <em>Semantic
     * Elements</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Elements</em>' attribute.
     * @see #getSemanticElements()
     * @generated
     */
    void setSemanticElements(String value);

} // TreeMapping
