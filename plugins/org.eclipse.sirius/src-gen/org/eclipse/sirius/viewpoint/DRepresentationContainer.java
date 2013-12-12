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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DRepresentation Container</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer#getDiagramSet
 * <em>Diagram Set</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationContainer#getModels
 * <em>Models</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationContainer()
 * @model
 * @generated
 */
public interface DRepresentationContainer extends DView {
    /**
     * Returns the value of the '<em><b>Diagram Set</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> Set of diagrams grouped by
     * DiagramDescription. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Diagram Set</em>' containment reference
     *         list.
     * @see #isSetDiagramSet()
     * @see #unsetDiagramSet()
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationContainer_DiagramSet()
     * @model containment="true" resolveProxies="true" unsettable="true"
     * @generated
     */
    EList<DDiagramSet> getDiagramSet();

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer#getDiagramSet
     * <em>Diagram Set</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #isSetDiagramSet()
     * @see #getDiagramSet()
     * @generated
     */
    void unsetDiagramSet();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer#getDiagramSet
     * <em>Diagram Set</em>}' containment reference list is set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return whether the value of the '<em>Diagram Set</em>' containment
     *         reference list is set.
     * @see #unsetDiagramSet()
     * @see #getDiagramSet()
     * @generated
     */
    boolean isSetDiagramSet();

    /**
     * Returns the value of the '<em><b>Models</b></em>' reference list. The
     * list contents are of type {@link org.eclipse.emf.ecore.EObject}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Models</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Models</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationContainer_Models()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<EObject> getModels();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    @Deprecated
    void addSemanticDiagram(DSemanticDiagram diagram);

} // DRepresentationContainer
