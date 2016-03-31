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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Mapping Import</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMappingImport#getImportedMapping
 * <em>Imported Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMappingImport#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMappingImport#isInheritsAncestorFilters
 * <em>Inherits Ancestor Filters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMappingImport()
 * @model
 * @generated
 */
public interface EdgeMappingImport extends DocumentedElement, IEdgeMapping, IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Imported Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The imported mapping used to define default values for the current
     * mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Imported Mapping</em>' reference.
     * @see #setImportedMapping(IEdgeMapping)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMappingImport_ImportedMapping()
     * @model required="true"
     * @generated
     */
    IEdgeMapping getImportedMapping();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMappingImport#getImportedMapping
     * <em>Imported Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Imported Mapping</em>' reference.
     * @see #getImportedMapping()
     * @generated
     */
    void setImportedMapping(IEdgeMapping value);

    /**
     * Returns the value of the '<em><b>Conditionnal Styles</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All conditional styles. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Conditionnal Styles</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMappingImport_ConditionnalStyles()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ConditionalEdgeStyleDescription> getConditionnalStyles();

    /**
     * Returns the value of the '<em><b>Inherits Ancestor Filters</b></em>'
     * attribute. The default value is <code>"true"</code>. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> Set to true if you
     * want the filters applying on the imported mappings apply on this one.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Inherits Ancestor Filters</em>' attribute.
     * @see #setInheritsAncestorFilters(boolean)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMappingImport_InheritsAncestorFilters()
     * @model default="true"
     * @generated
     */
    boolean isInheritsAncestorFilters();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMappingImport#isInheritsAncestorFilters
     * <em>Inherits Ancestor Filters</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Inherits Ancestor Filters</em>'
     *            attribute.
     * @see #isInheritsAncestorFilters()
     * @generated
     */
    void setInheritsAncestorFilters(boolean value);

} // EdgeMappingImport
