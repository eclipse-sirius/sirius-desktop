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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DTable Element</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTableElement#getTableElementMapping <em>Table Element
 * Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElement()
 * @model abstract="true"
 * @generated
 */
public interface DTableElement extends DRepresentationElement {

    /**
     * Returns the value of the '<em><b>Table Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The mapping of the element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Table Element Mapping</em>' reference.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElement_TableElementMapping()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    TableMapping getTableElementMapping();
} // DTableElement
