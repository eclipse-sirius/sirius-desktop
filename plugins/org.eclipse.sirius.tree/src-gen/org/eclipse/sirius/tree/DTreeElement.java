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
package org.eclipse.sirius.tree;

import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DTree Element</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.DTreeElement#getTreeElementMapping
 * <em>Tree Element Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.TreePackage#getDTreeElement()
 * @model
 * @generated
 */
public interface DTreeElement extends DRepresentationElement {
    /**
     * Returns the value of the '<em><b>Tree Element Mapping</b></em>'
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tree Element Mapping</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Tree Element Mapping</em>' reference.
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeElement_TreeElementMapping()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    TreeMapping getTreeElementMapping();

} // DTreeElement
