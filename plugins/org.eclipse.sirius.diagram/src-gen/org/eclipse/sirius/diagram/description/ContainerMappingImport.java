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

import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Container Mapping Import</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Ease the reuse of existing mappings. If the feature
 * is not defined in this instance it will re-use the feature value of the
 * imported one. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMappingImport#getImportedMapping
 * <em>Imported Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMappingImport()
 * @model
 * @generated
 */
public interface ContainerMappingImport extends ContainerMapping, AbstractMappingImport {
    /**
     * Returns the value of the '<em><b>Imported Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The imported mapping used to define default values for the current
     * mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Imported Mapping</em>' reference.
     * @see #setImportedMapping(ContainerMapping)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMappingImport_ImportedMapping()
     * @model required="true"
     * @generated
     */
    ContainerMapping getImportedMapping();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.ContainerMappingImport#getImportedMapping
     * <em>Imported Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Imported Mapping</em>' reference.
     * @see #getImportedMapping()
     * @generated
     */
    void setImportedMapping(ContainerMapping value);

} // ContainerMappingImport
