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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription;
import org.eclipse.sirius.viewpoint.description.tool.DirectEditLabel;
import org.eclipse.sirius.viewpoint.description.tool.DoubleClickDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>View Point Element Mapping</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Represent the mapping of a ViewPointElement. <!--
 * end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDeletionDescription
 * <em>Deletion Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getLabelDirectEdit
 * <em>Label Direct Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#isCreateElements
 * <em>Create Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDoubleClickDescription
 * <em>Double Click Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#isSynchronizationLock
 * <em>Synchronization Lock</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDiagramElementMapping()
 * @model abstract="true"
 * @generated
 */
public interface DiagramElementMapping extends RepresentationElementMapping, PasteTargetDescription {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>'
     * attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Precondition Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The precondition (Acceleo
     * Expression). <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSiriusElementMapping_PreconditionExpression()
     * @model default=""
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Precondition Expression</em>'
     *            attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Deletion Description</b></em>'
     * reference. It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Deletion Description</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The tool that describes
     * how to delete this element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Deletion Description</em>' reference.
     * @see #setDeletionDescription(DeleteElementDescription)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSiriusElementMapping_DeletionDescription()
     * @see org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription#getMappings
     * @model opposite="mappings"
     * @generated
     */
    DeleteElementDescription getDeletionDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDeletionDescription
     * <em>Deletion Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Deletion Description</em>'
     *            reference.
     * @see #getDeletionDescription()
     * @generated
     */
    void setDeletionDescription(DeleteElementDescription value);

    /**
     * Returns the value of the '<em><b>Label Direct Edit</b></em>' reference.
     * It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DirectEditLabel#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Direct Edit</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The tool that describes
     * what to do when the user edits the label of the elements. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Label Direct Edit</em>' reference.
     * @see #setLabelDirectEdit(DirectEditLabel)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSiriusElementMapping_LabelDirectEdit()
     * @see org.eclipse.sirius.viewpoint.description.tool.DirectEditLabel#getMapping
     * @model opposite="mapping"
     * @generated
     */
    DirectEditLabel getLabelDirectEdit();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getLabelDirectEdit
     * <em>Label Direct Edit</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Label Direct Edit</em>' reference.
     * @see #getLabelDirectEdit()
     * @generated
     */
    void setLabelDirectEdit(DirectEditLabel value);

    /**
     * Returns the value of the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Candidates Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> In the default case,
     * candidates of a mapping are all EObjet owned by the semantic element of
     * the view container. The semanticCandidatesExpression is an Acceleo
     * Expression that returns the list of EObject that are candidates of the
     * mapping instead of the candidates of the default case. The context of the
     * Acceleo Expression is the semantic element of the view container. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Semantic Candidates Expression</em>'
     *         attribute.
     * @see #setSemanticCandidatesExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSiriusElementMapping_SemanticCandidatesExpression()
     * @model
     * @generated
     */
    String getSemanticCandidatesExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Semantic Candidates Expression</em>'
     *            attribute.
     * @see #getSemanticCandidatesExpression()
     * @generated
     */
    void setSemanticCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Create Elements</b></em>' attribute. The
     * default value is <code>"true"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> true if the init/refresh
     * operations should create elements for this mapping. <!-- end-model-doc
     * -->
     * 
     * @return the value of the '<em>Create Elements</em>' attribute.
     * @see #setCreateElements(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getSiriusElementMapping_CreateElements()
     * @model default="true" required="true"
     * @generated
     */
    boolean isCreateElements();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#isCreateElements
     * <em>Create Elements</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Create Elements</em>' attribute.
     * @see #isCreateElements()
     * @generated
     */
    void setCreateElements(boolean value);

    /**
     * Returns the value of the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The elements that are represented by this connection. <!-- end-model-doc
     * -->
     * 
     * @return the value of the '<em>Semantic Elements</em>' attribute.
     * @see #setSemanticElements(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDiagramElementMapping_SemanticElements()
     * @model dataType="org.eclipse.sirius.description.AcceleoExpression"
     * @generated
     */
    String getSemanticElements();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getSemanticElements
     * <em>Semantic Elements</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Semantic Elements</em>' attribute.
     * @see #getSemanticElements()
     * @generated
     */
    void setSemanticElements(String value);

    /**
     * Returns the value of the '<em><b>Double Click Description</b></em>'
     * reference. It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DoubleClickDescription#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Double Click Description</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Double Click Description</em>' reference.
     * @see #setDoubleClickDescription(DoubleClickDescription)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDiagramElementMapping_DoubleClickDescription()
     * @see org.eclipse.sirius.viewpoint.description.tool.DoubleClickDescription#getMappings
     * @model opposite="mappings"
     * @generated
     */
    DoubleClickDescription getDoubleClickDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDoubleClickDescription
     * <em>Double Click Description</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Double Click Description</em>'
     *            reference.
     * @see #getDoubleClickDescription()
     * @generated
     */
    void setDoubleClickDescription(DoubleClickDescription value);

    /**
     * Returns the value of the '<em><b>Synchronization Lock</b></em>'
     * attribute. The default value is <code>"false"</code>. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> Set to true to force
     * the synchronization of the elements of this mapping when the current
     * diagram is in an unsynchronized mode. This option is used only if
     * createElements is true and the diagram which contain the elements of this
     * mapping is unsynchronized. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Synchronization Lock</em>' attribute.
     * @see #setSynchronizationLock(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDiagramElementMapping_SynchronizationLock()
     * @model default="false"
     * @generated
     */
    boolean isSynchronizationLock();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#isSynchronizationLock
     * <em>Synchronization Lock</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Synchronization Lock</em>'
     *            attribute.
     * @see #isSynchronizationLock()
     * @generated
     */
    void setSynchronizationLock(boolean value);

    /**
     * <!-- begin-user-doc -->
     * <p>
     * Check the precondition of the mapping for the object
     * <code>modelElement</code>.
     * </p>
     * 
     * @param mapping
     *            the mapping.
     * @param modelElement
     *            the element to check.
     * @param container
     *            the semantic element of the view that contains the potential
     *            view of <code>modelElement</code>.
     * @param containerView
     *            the view that contains the potential view of
     *            <code>modelElement</code>.
     * @return <code>true</code> if the precondition is checked, false
     *         otherwise. <!-- end-user-doc --> <!-- begin-model-doc --> Check
     *         the precondition of the mapping. Return true if the condition is
     *         checked, false otherwise.
     * @param modelElement
     *            The element to test.
     * @param container
     *            The semantic element of the view container of the eventual
     *            view of the modelElement
     * @param containerView
     *            The view container of the eventual view of the modelElement
     *            <!-- end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    boolean checkPrecondition(EObject modelElement, EObject container, EObject containerView);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all sub mappings. <!-- end-model-doc -->
     * 
     * @model kind="operation"
     *        type="viewpoint.description.DiagramElementMapping"
     * @generated
     */
    EList<DiagramElementMapping> getAllMappings();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Returns true if the given DDiagramElement has been produced from this
     * Mapping or any imported mapping.
     * 
     * @param element
     *            Any DDiagramElement <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean isFrom(DMappingBased element);
} // DiagramElementMapping
