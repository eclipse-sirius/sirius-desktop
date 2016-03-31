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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diagram Element Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represent the mapping of a ViewPointElement. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getDeletionDescription
 * <em>Deletion Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getLabelDirectEdit
 * <em>Label Direct Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#isCreateElements
 * <em>Create Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getDoubleClickDescription
 * <em>Double Click Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#isSynchronizationLock
 * <em>Synchronization Lock</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping()
 * @model abstract="true"
 * @generated
 */
public interface DiagramElementMapping extends RepresentationElementMapping, PasteTargetDescription {
    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>'
     * attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> An expression guarding the
     * effect if evaluated to false. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_PreconditionExpression()
     * @model default="" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables containerView='diagram.DragAndDropTarget | the view that should contain the potential views of the checked elements.' container='ecore.EObject | the semantic element of containerView.' viewpoint='diagram.DSemanticDiagram | (deprecated) the current DSemanticDiagram.' diagram='diagram.DSemanticDiagram | the current DSemanticDiagram.' sourceView='viewpoint.DSemanticDecorator | (edge only) the source view of the current potential edge.' source='ecore.EObject | (edge only) the semantic element of sourceView.' targetView='viewpoint.DSemanticDecorator | (edge only) the target view of the current potential edge.' target='ecore.EObject | (edge only) the semantic element of targetView.'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getPreconditionExpression
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
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The tool that describes how to delete this element.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Deletion Description</em>' reference.
     * @see #setDeletionDescription(DeleteElementDescription)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_DeletionDescription()
     * @model
     * @generated
     */
    DeleteElementDescription getDeletionDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getDeletionDescription
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
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The tool that describes what to do when the user edits the label of the
     * elements. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Direct Edit</em>' reference.
     * @see #setLabelDirectEdit(DirectEditLabel)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_LabelDirectEdit()
     * @model
     * @generated
     */
    DirectEditLabel getLabelDirectEdit();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getLabelDirectEdit
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
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> In the default case, candidates of a mapping are all
     * EObjet owned by the semantic element of the view container. The
     * semanticCandidatesExpression is an expression that returns the list of
     * EObject that are candidates of the mapping instead of the candidates of
     * the default case. The context of the expression is the semantic element
     * of the view container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Semantic Candidates Expression</em>'
     *         attribute.
     * @see #setSemanticCandidatesExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_SemanticCandidatesExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an EObject.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables containerView='diagram.DDiagram | the parent view of potential candidates.' diagram='diagram.DDiagram | the current DDiagram.' viewpoint='diagram.DDiagram | (deprecated) the current DDiagram.' viewPoint='diagram.DDiagram | (deprecated) the current DDiagram.'"
     * @generated
     */
    String getSemanticCandidatesExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticCandidatesExpression
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_CreateElements()
     * @model default="true" required="true"
     * @generated
     */
    boolean isCreateElements();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#isCreateElements
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_SemanticElements()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an EObject.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DSemanticDiagram.' view='diagram.DDiagramElement | the current view created from the current mapping.' viewpoint='diagram.DDiagram | (deprecated) the current DSemanticDiagram.'"
     * @generated
     */
    String getSemanticElements();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticElements
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
     * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Double Click Description</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Double Click Description</em>' reference.
     * @see #setDoubleClickDescription(DoubleClickDescription)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_DoubleClickDescription()
     * @see org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getMappings
     * @model opposite="mappings"
     * @generated
     */
    DoubleClickDescription getDoubleClickDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getDoubleClickDescription
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramElementMapping_SynchronizationLock()
     * @model default="false"
     * @generated
     */
    boolean isSynchronizationLock();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#isSynchronizationLock
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
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Check the precondition of the mapping. Return true if the condition is
     * checked, false otherwise.
     *
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
    boolean checkPrecondition(EObject modelElement, EObject container, EObject containerView);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all sub mappings. <!-- end-model-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    EList<DiagramElementMapping> getAllMappings();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Returns true if the given ViewPointElement has been produced from this
     * Mapping or any imported mapping.
     *
     * @param element
     *            Any ViewPointElement <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean isFrom(DMappingBased element);

} // DiagramElementMapping
