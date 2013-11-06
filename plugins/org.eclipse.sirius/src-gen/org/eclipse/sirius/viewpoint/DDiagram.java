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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.Layer;
import org.eclipse.sirius.viewpoint.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.concern.ConcernDescription;
import org.eclipse.sirius.viewpoint.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.BehaviorTool;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>View Point</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> ViewPoint is the type of all diagrams in AIR. A
 * viewpoint is composed of nodes, containers and connections. It is owned by an
 * anlysis or by a parent viewpoint. In this last case the viewpoint should be
 * called detailed viewpoint. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getOwnedDiagramElements <em>
 * Owned Diagram Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getDiagramElements <em>
 * Diagram Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getDescription <em>
 * Description</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getInfo <em>Info</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getSubDiagrams <em>Sub
 * Diagrams</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getEdges <em>Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getNodeListElements <em>Node
 * List Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getContainers <em>Containers
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getCurrentConcern <em>
 * Current Concern</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getActivatedFilters <em>
 * Activated Filters</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getAllFilters <em>All
 * Filters</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getActivatedRules <em>
 * Activated Rules</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getActivateBehaviors <em>
 * Activate Behaviors</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getFilterVariableHistory
 * <em>Filter Variable History</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getActivatedLayers <em>
 * Activated Layers</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#isSynchronized <em>
 * Synchronized</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getHiddenElements <em>Hidden
 * Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#isIsInLayoutingMode <em>Is
 * In Layouting Mode</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagram#getHeaderHeight <em>Header
 * Height</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagram()
 * @model
 * @generated
 */
public interface DDiagram extends DRepresentation, DocumentedElement, DragAndDropTarget, DValidable, DContainer {
    /**
     * Returns the value of the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> The SiriusElements
     * directly owned by this viewpoint. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Owned Diagram Elements</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagram_OwnedDiagramElements()
     * @model type="org.eclipse.sirius.DDiagramElement" containment="true"
     *        resolveProxies="true"
     * @generated
     */
    EList<DDiagramElement> getOwnedDiagramElements();

    /**
     * Returns the value of the '<em><b>Diagram Elements</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> The diagram elements
     * directly and indirectly owned by this diagram. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Diagram Elements</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagram_DiagramElements()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DDiagramElement> getDiagramElements();

    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The description of the
     * viewpoint. It may be null. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(DiagramDescription)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_Description()
     * @model
     * @generated
     */
    DiagramDescription getDescription();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getDescription
     * <em>Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(DiagramDescription value);

    /**
     * Returns the value of the '<em><b>Info</b></em>' attribute. The default
     * value is <code>""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Info</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The information of the
     * viewpoint. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Info</em>' attribute.
     * @see #setInfo(String)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_Info()
     * @model default=""
     * @generated
     */
    String getInfo();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getInfo <em>Info</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Info</em>' attribute.
     * @see #getInfo()
     * @generated
     */
    void setInfo(String value);

    /**
     * Returns the value of the '<em><b>Sub Diagrams</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagram}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> Viewpoints that are owned by
     * this viewpoint. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Sub Diagrams</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagram_SubDiagrams()
     * @model type="org.eclipse.sirius.DDiagram" containment="true"
     *        resolveProxies="true"
     * @generated
     */
    EList<DDiagram> getSubDiagrams();

    /**
     * Returns the value of the '<em><b>Edges</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.DEdge}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edges</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> All edges of the
     * viewpoint. It is a subset of ownedSiriusElements <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Edges</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_Edges()
     * @model type="viewpoint.DEdge" transient="true" changeable="false"
     *        volatile="true" derived="true"
     * @generated
     */
    EList<DEdge> getEdges();

    /**
     * Returns the value of the '<em><b>Nodes</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.DNode}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nodes</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> All nodes of the
     * viewpoint. It is a subset of ownedSiriusElements <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Nodes</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_Nodes()
     * @model type="viewpoint.DNode" transient="true" changeable="false"
     *        volatile="true" derived="true"
     * @generated
     */
    EList<DNode> getNodes();

    /**
     * Returns the value of the '<em><b>Node List Elements</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DNodeListElement}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * nodes of the diagram. It is a subset of ownedDiagramElements <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Node List Elements</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagram_NodeListElements()
     * @model type="org.eclipse.sirius.DNodeListElement" transient="true"
     *        changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DNodeListElement> getNodeListElements();

    /**
     * Returns the value of the '<em><b>Containers</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Containers</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> All containers of the
     * viewpoint. It is a subset of ownedSiriusElements <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Containers</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_Containers()
     * @model type="viewpoint.DDiagramElementContainer" transient="true"
     *        changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DDiagramElementContainer> getContainers();

    /**
     * Returns the value of the '<em><b>Current Concern</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Current Concern</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The current selected
     * concer. It may be null <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Current Concern</em>' reference.
     * @see #setCurrentConcern(ConcernDescription)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_CurrentConcern()
     * @model
     * @generated
     */
    ConcernDescription getCurrentConcern();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getCurrentConcern
     * <em>Current Concern</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Current Concern</em>' reference.
     * @see #getCurrentConcern()
     * @generated
     */
    void setCurrentConcern(ConcernDescription value);

    /**
     * Returns the value of the '<em><b>Activated Filters</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.filter.FilterDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activated Filters</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Filters that are currently
     * activated for this viewpoint. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Activated Filters</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_ActivatedFilters()
     * @model type="viewpoint.description.filter.FilterDescription"
     * @generated
     */
    EList<FilterDescription> getActivatedFilters();

    /**
     * Returns the value of the '<em><b>All Filters</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.filter.FilterDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Filters</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Filters that can be
     * activated for this viewpoint. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>All Filters</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_AllFilters()
     * @model type="viewpoint.description.filter.FilterDescription"
     *        transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<FilterDescription> getAllFilters();

    /**
     * Returns the value of the '<em><b>Activated Rules</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activated Rules</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Validation rules that are
     * currently activated for this viewpoint. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Activated Rules</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_ActivatedRules()
     * @model type="viewpoint.description.validation.ValidationRule"
     * @generated
     */
    EList<ValidationRule> getActivatedRules();

    /**
     * Returns the value of the '<em><b>Activate Behaviors</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.BehaviorTool}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activate Behaviors</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Behaviors that are
     * currently activated for this viewpoint. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Activate Behaviors</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSirius_ActivateBehaviors()
     * @model type="viewpoint.description.tool.BehaviorTool"
     * @generated
     */
    EList<BehaviorTool> getActivateBehaviors();

    /**
     * Returns the value of the '<em><b>Filter Variable History</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Variable History</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Filter Variable History</em>' containment
     *         reference.
     * @see #setFilterVariableHistory(FilterVariableHistory)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagram_FilterVariableHistory()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    FilterVariableHistory getFilterVariableHistory();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getFilterVariableHistory
     * <em>Filter Variable History</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Filter Variable History</em>'
     *            containment reference.
     * @see #getFilterVariableHistory()
     * @generated
     */
    void setFilterVariableHistory(FilterVariableHistory value);

    /**
     * Returns the value of the '<em><b>Activated Layers</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.Layer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activated Layers</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Activated Layers</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagram_ActivatedLayers()
     * @model
     * @generated
     */
    EList<Layer> getActivatedLayers();

    /**
     * Returns the value of the '<em><b>Synchronized</b></em>' attribute. The
     * default value is <code>"true"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Synchronized</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Synchronized</em>' attribute.
     * @see #setSynchronized(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagram_Synchronized()
     * @model default="true"
     * @generated
     */
    boolean isSynchronized();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#isSynchronized
     * <em>Synchronized</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Synchronized</em>' attribute.
     * @see #isSynchronized()
     * @generated
     */
    void setSynchronized(boolean value);

    /**
     * Returns the value of the '<em><b>Hidden Elements</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Hidden Elements</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> List of DDiagramElement :
     * Either the DDiagramElement is hidden or its label is hidden. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Hidden Elements</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagram_HiddenElements()
     * @model transient="true"
     * @generated
     */
    EList<DDiagramElement> getHiddenElements();

    /**
     * Returns the value of the '<em><b>Is In Layouting Mode</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is In Layouting Mode</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Is In Layouting Mode</em>' attribute.
     * @see #setIsInLayoutingMode(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagram_IsInLayoutingMode()
     * @model transient="true"
     * @generated
     */
    boolean isIsInLayoutingMode();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#isIsInLayoutingMode
     * <em>Is In Layouting Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Is In Layouting Mode</em>'
     *            attribute.
     * @see #isIsInLayoutingMode()
     * @generated
     */
    void setIsInLayoutingMode(boolean value);

    /**
     * Returns the value of the '<em><b>Header Height</b></em>' attribute. The
     * default value is <code>"1"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The number of lines to display
     * the header labels (1 by default). This field is used only if the
     * IDiagramDescriptionProvider.supportHeader() return true for this
     * DDiagram. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Header Height</em>' attribute.
     * @see #setHeaderHeight(int)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagram_HeaderHeight()
     * @model default="1"
     * @generated
     */
    int getHeaderHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagram#getHeaderHeight
     * <em>Header Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Header Height</em>' attribute.
     * @see #getHeaderHeight()
     * @generated
     */
    void setHeaderHeight(int value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Clean the viewpoint. It deletes useless elements that have been deleted
     * from the semantic model. <!-- end-model-doc -->
     * 
     * @model
     * @generated
     */
    @Deprecated
    void clean();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Returns all nodes owned (directly or not) by this viewpoint that have
     * been created from the specified mapping.
     * 
     * @param mapping
     *            The mapping that has created the returned ViewNodes <!--
     *            end-model-doc -->
     * @model type="viewpoint.DNode"
     * @generated
     */
    EList<DNode> getNodesFromMapping(NodeMapping mapping);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Returns all edges owned (directly or not) by this viewpoint that have
     * been created from the specified mapping.
     * 
     * @param mapping
     *            The mapping that has created the returned ViewEdges <!--
     *            end-model-doc -->
     * @model type="viewpoint.DEdge"
     * @generated
     */
    EList<DEdge> getEdgesFromMapping(EdgeMapping mapping);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Returns all containers owned (directly or not) by this viewpoint that
     * have been created from the specified mapping.
     * 
     * @param mapping
     *            The mapping that has created the returned
     *            SiriusElementContainers <!-- end-model-doc -->
     * @model type="viewpoint.DDiagramElementContainer"
     * @generated
     */
    EList<DDiagramElementContainer> getContainersFromMapping(ContainerMapping mapping);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Finds all view point elements that have the specified semantic element
     * has target and that are instances of the specified type. <!--
     * end-model-doc -->
     * 
     * @model type="org.eclipse.sirius.DDiagramElement"
     * @generated
     */
    @Deprecated
    EList<DDiagramElement> findDiagramElements(EObject semanticElement, EClass type);

} // DDiagram
