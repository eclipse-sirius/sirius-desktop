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

import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diagram Import Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramImportDescription#getImportedDiagram
 * <em>Imported Diagram</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramImportDescription()
 * @model
 * @generated
 */
public interface DiagramImportDescription extends RepresentationImportDescription, DiagramDescription {
    /**
     * Returns the value of the '<em><b>Imported Diagram</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Diagram representation to import. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Imported Diagram</em>' reference.
     * @see #setImportedDiagram(DiagramDescription)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramImportDescription_ImportedDiagram()
     * @model
     * @generated
     */
    DiagramDescription getImportedDiagram();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramImportDescription#getImportedDiagram
     * <em>Imported Diagram</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Imported Diagram</em>' reference.
     * @see #getImportedDiagram()
     * @generated
     */
    void setImportedDiagram(DiagramDescription value);

} // DiagramImportDescription
