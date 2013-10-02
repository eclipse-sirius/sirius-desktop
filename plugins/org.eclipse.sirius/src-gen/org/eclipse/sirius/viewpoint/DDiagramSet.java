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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>View Point Set</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A set of semantic diagrams <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramSet#getDescription <em>
 * Description</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramSet#getDiagrams <em>Diagrams
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramSet#getView <em>View</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagramSet()
 * @model
 * @generated
 */
public interface DDiagramSet extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The DiagramDescription of
     * all viewpoints that are in this set. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Description</em>' reference.
     * @see #isSetDescription()
     * @see #unsetDescription()
     * @see #setDescription(DiagramDescription)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusSet_Description()
     * @model unsettable="true"
     * @generated
     */
    DiagramDescription getDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet#getDescription
     * <em>Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #isSetDescription()
     * @see #unsetDescription()
     * @see #getDescription()
     * @generated
     */
    void setDescription(DiagramDescription value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet#getDescription
     * <em>Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isSetDescription()
     * @see #getDescription()
     * @see #setDescription(DiagramDescription)
     * @generated
     */
    void unsetDescription();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet#getDescription
     * <em>Description</em>}' reference is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return whether the value of the '<em>Description</em>' reference is set.
     * @see #unsetDescription()
     * @see #getDescription()
     * @see #setDescription(DiagramDescription)
     * @generated
     */
    boolean isSetDescription();

    /**
     * Returns the value of the '<em><b>Diagrams</b></em>' reference list. The
     * list contents are of type {@link org.eclipse.sirius.viewpoint.DDiagram}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The viewpoints referenced by this set. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Diagrams</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagramSet_Diagrams()
     * @model type="org.eclipse.sirius.DDiagram" derived="true" ordered="false"
     * @generated
     */
    EList<DDiagram> getDiagrams();

    /**
     * Returns the value of the '<em><b>View</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>View</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>View</em>' reference.
     * @see #setView(DRepresentationContainer)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagramSet_View()
     * @model transient="true"
     * @generated
     */
    DRepresentationContainer getView();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet#getView <em>View</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>View</em>' reference.
     * @see #getView()
     * @generated
     */
    void setView(DRepresentationContainer value);

} // DDiagramSet
