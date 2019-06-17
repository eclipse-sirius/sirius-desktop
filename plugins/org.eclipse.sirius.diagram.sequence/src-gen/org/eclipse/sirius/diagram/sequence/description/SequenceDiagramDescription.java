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
package org.eclipse.sirius.diagram.sequence.description;

import org.eclipse.sirius.diagram.description.DiagramDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Sequence Diagram Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getEndsOrdering <em>Ends
 * Ordering</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getInstanceRolesOrdering
 * <em>Instance Roles Ordering</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getSequenceDiagramDescription()
 * @model
 * @generated
 */
public interface SequenceDiagramDescription extends DiagramDescription {
    /**
     * Returns the value of the '<em><b>Ends Ordering</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ends Ordering</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Ends Ordering</em>' attribute.
     * @see #setEndsOrdering(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getSequenceDiagramDescription_EndsOrdering()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables eventEnds='a
     *        List&lt;EObject&gt; containing the semantic event ends.'"
     * @generated
     */
    String getEndsOrdering();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getEndsOrdering <em>Ends
     * Ordering</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Ends Ordering</em>' attribute.
     * @see #getEndsOrdering()
     * @generated
     */
    void setEndsOrdering(String value);

    /**
     * Returns the value of the '<em><b>Instance Roles Ordering</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance Roles Ordering</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Instance Roles Ordering</em>' attribute.
     * @see #setInstanceRolesOrdering(String)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getSequenceDiagramDescription_InstanceRolesOrdering()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;EObject&gt; or an EObject.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getInstanceRolesOrdering();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getInstanceRolesOrdering
     * <em>Instance Roles Ordering</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Instance Roles Ordering</em>' attribute.
     * @see #getInstanceRolesOrdering()
     * @generated
     */
    void setInstanceRolesOrdering(String value);

} // SequenceDiagramDescription
