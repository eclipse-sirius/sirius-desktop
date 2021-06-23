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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>TBasic Message Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTBasicMessageMapping()
 * @model
 * @generated
 */
public interface TBasicMessageMapping extends TSourceTargetMessageMapping {

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageExtremity}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Target</em>' reference list.
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTBasicMessageMapping_Target()
     * @model required="true"
     * @generated
     */
    EList<TMessageExtremity> getTarget();
} // TBasicMessageMapping
