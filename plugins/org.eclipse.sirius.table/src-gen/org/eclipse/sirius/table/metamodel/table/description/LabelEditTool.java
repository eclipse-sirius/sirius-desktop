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
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Label Edit Tool</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool#getMask
 * <em>Mask</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLabelEditTool()
 * @model
 * @generated
 */
public interface LabelEditTool extends TableTool {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Mask</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The edit mask. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Mask</em>' containment reference.
     * @see #setMask(EditMaskVariables)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getLabelEditTool_Mask()
     * @model containment="true" required="true"
     * @generated
     */
    EditMaskVariables getMask();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool#getMask
     * <em>Mask</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Mask</em>' containment reference.
     * @see #getMask()
     * @generated
     */
    void setMask(EditMaskVariables value);
} // LabelEditTool
