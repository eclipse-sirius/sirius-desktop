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
package org.eclipse.sirius.diagram.sequence.template;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TTransformer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TTransformer#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTTransformer()
 * @model
 * @generated
 */
public interface TTransformer extends EObject {
    /**
     * Returns the value of the '<em><b>Outputs</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Outputs</em>' reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTTransformer_Outputs()
     * @model
     * @generated
     */
    EList<EObject> getOutputs();

} // TTransformer
