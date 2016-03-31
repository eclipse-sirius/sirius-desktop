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
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Node Creation Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Tool to create a ViewNode. It appears in the
 * palette. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getNodeMappings
 * <em>Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getVariable
 * <em>Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getViewVariable
 * <em>View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getExtraMappings
 * <em>Extra Mappings</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNodeCreationDescription()
 * @model
 * @generated
 */
public interface NodeCreationDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Node Mappings</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Node
     * mappings used by this tool. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Node Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNodeCreationDescription_NodeMappings()
     * @model required="true"
     * @generated
     */
    EList<NodeMapping> getNodeMappings();

    /**
     * Returns the value of the '<em><b>Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The variable container that represents the semantic
     * element of the clicked view. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Variable</em>' containment reference.
     * @see #setVariable(NodeCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNodeCreationDescription_Variable()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='container'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables type='ecore.EObject'"
     * @generated
     */
    NodeCreationVariable getVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getVariable
     * <em>Variable</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Variable</em>' containment
     *            reference.
     * @see #getVariable()
     * @generated
     */
    void setVariable(NodeCreationVariable value);

    /**
     * Returns the value of the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The variable containerView that represents the
     * clickedView (instance of ViewPoint or ViewPointElement). <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>View Variable</em>' containment reference.
     * @see #setViewVariable(ContainerViewVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNodeCreationDescription_ViewVariable()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='containerView'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables type='diagram.DDiagramElementContainer'"
     * @generated
     */
    ContainerViewVariable getViewVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getViewVariable
     * <em>View Variable</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>View Variable</em>' containment
     *            reference.
     * @see #getViewVariable()
     * @generated
     */
    void setViewVariable(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The first operation to execute. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialNodeCreationOperation)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNodeCreationDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialNodeCreationOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment
     *            reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialNodeCreationOperation value);

    /**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute. The
     * default value is <code>""</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The path of the icon to display
     * in the palette. If unset, the icon corresponding to the semantic element
     * associated with the mapping will be displayed. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNodeCreationDescription_IconPath()
     * @model default=""
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getIconPath
     * <em>Icon Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Icon Path</em>' attribute.
     * @see #getIconPath()
     * @generated
     */
    void setIconPath(String value);

    /**
     * Returns the value of the '<em><b>Extra Mappings</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Add
     * here any mapping in which you want to allow the tool execution. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Extra Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getNodeCreationDescription_ExtraMappings()
     * @model
     * @generated
     */
    EList<AbstractNodeMapping> getExtraMappings();

} // NodeCreationDescription
