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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DTable Element</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.DTableElement#getTableElementMapping
 * <em>Table Element Mapping</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElement()
 * @model abstract="true"
 * @generated
 */
public interface DTableElement extends DRepresentationElement {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Table Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The mapping of the element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Table Element Mapping</em>' reference.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElement_TableElementMapping()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    TableMapping getTableElementMapping();
} // DTableElement
