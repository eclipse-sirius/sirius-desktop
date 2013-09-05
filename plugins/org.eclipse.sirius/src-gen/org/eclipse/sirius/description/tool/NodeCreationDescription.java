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
package org.eclipse.sirius.description.tool;

import org.eclipse.emf.common.util.EList;

import org.eclipse.sirius.description.AbstractNodeMapping;
import org.eclipse.sirius.description.NodeMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Node Creation Description</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Tool to create a ViewNode. It appears in the
 * palette. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getNodeMappings
 * <em>Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getVariable
 * <em>Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getViewVariable
 * <em>View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getExtraMappings
 * <em>Extra Mappings</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.tool.ToolPackage#getNodeCreationDescription()
 * @model
 * @generated
 */
public interface NodeCreationDescription extends MappingBasedToolDescription {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Node Mappings</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Mappings</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Node mappings used by this
     * tool. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Node Mappings</em>' reference list.
     * @see org.eclipse.sirius.description.tool.ToolPackage#getNodeCreationDescription_NodeMappings()
     * @model type="viewpoint.description.NodeMapping" required="true"
     * @generated
     */
    EList<NodeMapping> getNodeMappings();

    /**
     * Returns the value of the '<em><b>Variable</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variable</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The variable container
     * that represents the semantic element of the clicked view. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Variable</em>' containment reference.
     * @see #setVariable(NodeCreationVariable)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getNodeCreationDescription_Variable()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    NodeCreationVariable getVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getVariable
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
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Operation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The first operation to
     * execute. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialNodeCreationOperation)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getNodeCreationDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialNodeCreationOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getInitialOperation
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
     * default value is
     * <code>"/org.eclipse.sirius.ui/icons/full/obj16/NodeMapping.gif"</code>
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Icon Path</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The path of the icon to
     * display in the palette. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getNodeCreationDescription_IconPath()
     * @model 
     *        default="/org.eclipse.sirius.ui/icons/full/obj16/NodeMapping.gif"
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getIconPath
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
     * {@link org.eclipse.sirius.description.AbstractNodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * mappings that create views that are able to received a request to manage
     * this creation
     * 
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Extra Mappings</em>' reference list.
     * @see org.eclipse.sirius.description.tool.ToolPackage#getNodeCreationDescription_ExtraMappings()
     * @model type="viewpoint.description.AbstractNodeMapping"
     * @generated
     */
    EList<AbstractNodeMapping> getExtraMappings();

    /**
     * Returns the value of the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>View Variable</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The variable containerView
     * that represents the clickedView (instance of DDiagram or
     * DDiagramElement). <!-- end-model-doc -->
     * 
     * @return the value of the '<em>View Variable</em>' containment reference.
     * @see #setViewVariable(ContainerViewVariable)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getNodeCreationDescription_ViewVariable()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ContainerViewVariable getViewVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getViewVariable
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

} // NodeCreationDescription
