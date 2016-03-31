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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Abstract DNode</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.AbstractDNode#getOwnedBorderedNodes
 * <em>Owned Bordered Nodes</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.AbstractDNode#getArrangeConstraints
 * <em>Arrange Constraints</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getAbstractDNode()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface AbstractDNode extends DDiagramElement {
    /**
     * Returns the value of the '<em><b>Owned Bordered Nodes</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.DNode}. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The nodes that are on the
     * border of the container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Bordered Nodes</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getAbstractDNode_OwnedBorderedNodes()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<DNode> getOwnedBorderedNodes();

    /**
     * Returns the value of the '<em><b>Arrange Constraints</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.ArrangeConstraint}. The literals are
     * from the enumeration {@link org.eclipse.sirius.diagram.ArrangeConstraint}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arrange Constraints</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Arrange Constraints</em>' attribute list.
     * @see org.eclipse.sirius.diagram.ArrangeConstraint
     * @see org.eclipse.sirius.diagram.DiagramPackage#getAbstractDNode_ArrangeConstraints()
     * @model default="KEEP_LOCATION"
     * @generated
     */
    EList<ArrangeConstraint> getArrangeConstraints();

} // AbstractDNode
