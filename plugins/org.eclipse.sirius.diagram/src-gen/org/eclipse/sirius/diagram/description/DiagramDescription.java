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
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.concern.ConcernSet;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diagram Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The description of a diagram. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllEdgeMappings
 * <em>All Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllNodeMappings
 * <em>All Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllContainerMappings
 * <em>All Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getValidationSet
 * <em>Validation Set</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getConcerns
 * <em>Concerns</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllTools
 * <em>All Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultConcern
 * <em>Default Concern</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getRootExpression
 * <em>Root Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.DiagramDescription#getInit
 * <em>Init</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getLayout
 * <em>Layout</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDiagramInitialisation
 * <em>Diagram Initialisation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultLayer
 * <em>Default Layer</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAdditionalLayers
 * <em>Additional Layers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllLayers
 * <em>All Layers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllActivatedTools
 * <em>All Activated Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getNodeMappings
 * <em>Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getEdgeMappings
 * <em>Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getEdgeMappingImports
 * <em>Edge Mapping Imports</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getContainerMappings
 * <em>Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getReusedMappings
 * <em>Reused Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getToolSection
 * <em>Tool Section</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getReusedTools
 * <em>Reused Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.DiagramDescription#isEnablePopupBars
 * <em>Enable Popup Bars</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription()
 * @model
 * @generated
 */
public interface DiagramDescription extends DragAndDropTargetDescription, RepresentationDescription, PasteTargetDescription {
    /**
     * Returns the value of the '<em><b>Filters</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.filter.FilterDescription}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Filters that are owned by this simple mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Filters</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_Filters()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<FilterDescription> getFilters();

    /**
     * Returns the value of the '<em><b>All Edge Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * edge mappings (including import edge ampping) of this simple mapping.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>All Edge Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_AllEdgeMappings()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<EdgeMapping> getAllEdgeMappings();

    /**
     * Returns the value of the '<em><b>All Node Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * node mappings of this simple mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>All Node Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_AllNodeMappings()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<NodeMapping> getAllNodeMappings();

    /**
     * Returns the value of the '<em><b>All Container Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * container mappings of this simple mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>All Container Mappings</em>' reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_AllContainerMappings()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<ContainerMapping> getAllContainerMappings();

    /**
     * Returns the value of the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The validations rules <!-- end-model-doc -->
     *
     * @return the value of the '<em>Validation Set</em>' containment reference.
     * @see #setValidationSet(ValidationSet)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_ValidationSet()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ValidationSet getValidationSet();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getValidationSet
     * <em>Validation Set</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Validation Set</em>' containment
     *            reference.
     * @see #getValidationSet()
     * @generated
     */
    void setValidationSet(ValidationSet value);

    /**
     * Returns the value of the '<em><b>Concerns</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> All concerns of the viewpoint. A concern is a set of
     * filters, validations and behaviors. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Concerns</em>' containment reference.
     * @see #setConcerns(ConcernSet)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_Concerns()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ConcernSet getConcerns();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getConcerns
     * <em>Concerns</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Concerns</em>' containment
     *            reference.
     * @see #getConcerns()
     * @generated
     */
    void setConcerns(ConcernSet value);

    /**
     * Returns the value of the '<em><b>All Tools</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All tools of the viewpoint. <!-- end-model-doc -->
     *
     * @return the value of the '<em>All Tools</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_AllTools()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<AbstractToolDescription> getAllTools();

    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * domain class of the viewpoint. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName"
     *        required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDomainClass
     * <em>Domain Class</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The predicate that allows (or not) to create a
     * viewpoint from a Meta Class. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_PreconditionExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getPreconditionExpression
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
     * Returns the value of the '<em><b>Default Concern</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The default concern to use. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Default Concern</em>' reference.
     * @see #setDefaultConcern(ConcernDescription)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_DefaultConcern()
     * @model
     * @generated
     */
    ConcernDescription getDefaultConcern();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultConcern
     * <em>Default Concern</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Concern</em>' reference.
     * @see #getDefaultConcern()
     * @generated
     */
    void setDefaultConcern(ConcernDescription value);

    /**
     * Returns the value of the '<em><b>Root Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Root Expression</em>' attribute.
     * @see #setRootExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_RootExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DDiagram.' viewpoint='diagram.DDiagram | (deprecated) the current DDiagram.'"
     * @generated
     */
    String getRootExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getRootExpression
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Root Expression</em>' attribute.
     * @see #getRootExpression()
     * @generated
     */
    void setRootExpression(String value);

    /**
     * Returns the value of the '<em><b>Init</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Init</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Init</em>' reference.
     * @see #setInit(RepresentationCreationDescription)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_Init()
     * @model
     * @generated
     */
    RepresentationCreationDescription getInit();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getInit
     * <em>Init</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Init</em>' reference.
     * @see #getInit()
     * @generated
     */
    void setInit(RepresentationCreationDescription value);

    /**
     * Returns the value of the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Layout</em>' containment reference.
     * @see #setLayout(Layout)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_Layout()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    Layout getLayout();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getLayout
     * <em>Layout</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Layout</em>' containment reference.
     * @see #getLayout()
     * @generated
     */
    void setLayout(Layout value);

    /**
     * Returns the value of the '<em><b>Diagram Initialisation</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Diagram Initialisation</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Diagram Initialisation</em>' containment
     *         reference.
     * @see #setDiagramInitialisation(InitialOperation)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_DiagramInitialisation()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    InitialOperation getDiagramInitialisation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDiagramInitialisation
     * <em>Diagram Initialisation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Diagram Initialisation</em>'
     *            containment reference.
     * @see #getDiagramInitialisation()
     * @generated
     */
    void setDiagramInitialisation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Default Layer</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Layer</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Default Layer</em>' containment reference.
     * @see #setDefaultLayer(Layer)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_DefaultLayer()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    Layer getDefaultLayer();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultLayer
     * <em>Default Layer</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Layer</em>' containment
     *            reference.
     * @see #getDefaultLayer()
     * @generated
     */
    void setDefaultLayer(Layer value);

    /**
     * Returns the value of the '<em><b>Additional Layers</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.AdditionalLayer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Additional Layers</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Additional Layers</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_AdditionalLayers()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<AdditionalLayer> getAdditionalLayers();

    /**
     * Returns the value of the '<em><b>All Layers</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.description.Layer}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>All Layers</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Layers</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_AllLayers()
     * @model transient="true" volatile="true" derived="true"
     * @generated
     */
    EList<Layer> getAllLayers();

    /**
     * Returns the value of the '<em><b>All Activated Tools</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Activated Tools</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Activated Tools</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_AllActivatedTools()
     * @model transient="true" volatile="true" derived="true"
     * @generated
     */
    EList<AbstractToolDescription> getAllActivatedTools();

    /**
     * Returns the value of the '<em><b>Node Mappings</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Node
     * mappings that are owned by this simple mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Node Mappings</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_NodeMappings()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<NodeMapping> getNodeMappings();

    /**
     * Returns the value of the '<em><b>Edge Mappings</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Edge
     * mappings that are owned by this simple mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Edge Mappings</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_EdgeMappings()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<EdgeMapping> getEdgeMappings();

    /**
     * Returns the value of the '<em><b>Edge Mapping Imports</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.EdgeMappingImport}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Edge
     * mapping imports that are owned by this simple mapping. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Edge Mapping Imports</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_EdgeMappingImports()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<EdgeMappingImport> getEdgeMappingImports();

    /**
     * Returns the value of the '<em><b>Container Mappings</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * container mappings that are owned by this simple mapping. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Container Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_ContainerMappings()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<ContainerMapping> getContainerMappings();

    /**
     * Returns the value of the '<em><b>Reused Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The reused mappings. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reused Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_ReusedMappings()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getReusedMappings();

    /**
     * Returns the value of the '<em><b>Tool Section</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> A tool section encloses many tools <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Tool Section</em>' containment reference.
     * @see #setToolSection(ToolSection)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_ToolSection()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    ToolSection getToolSection();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getToolSection
     * <em>Tool Section</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tool Section</em>' containment
     *            reference.
     * @see #getToolSection()
     * @generated
     */
    void setToolSection(ToolSection value);

    /**
     * Returns the value of the '<em><b>Reused Tools</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Tools that are reused by this viewpoint. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reused Tools</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_ReusedTools()
     * @model
     * @generated
     */
    EList<AbstractToolDescription> getReusedTools();

    /**
     * Returns the value of the '<em><b>Enable Popup Bars</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Boolean indicating whether or not to show dynamic popup bars with
     * creation tools. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Enable Popup Bars</em>' attribute.
     * @see #setEnablePopupBars(boolean)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getDiagramDescription_EnablePopupBars()
     * @model
     * @generated
     */
    boolean isEnablePopupBars();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#isEnablePopupBars
     * <em>Enable Popup Bars</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Enable Popup Bars</em>' attribute.
     * @see #isEnablePopupBars()
     * @generated
     */
    void setEnablePopupBars(boolean value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    DSemanticDiagram createDiagram();

} // DiagramDescription
