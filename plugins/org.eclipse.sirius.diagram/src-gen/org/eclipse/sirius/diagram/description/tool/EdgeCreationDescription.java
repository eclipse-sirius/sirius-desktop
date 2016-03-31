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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Creation Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Tools to create a ViewEdge it appears in the
 * palette. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getEdgeMappings
 * <em>Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceVariable
 * <em>Source Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetVariable
 * <em>Target Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceViewVariable
 * <em>Source View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetViewVariable
 * <em>Target View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getExtraSourceMappings
 * <em>Extra Source Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getExtraTargetMappings
 * <em>Extra Target Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getConnectionStartPrecondition
 * <em>Connection Start Precondition</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription()
 * @model
 * @generated
 */
public interface EdgeCreationDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Edge Mappings</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * EdgeMappings used by this tool. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Edge Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_EdgeMappings()
     * @model required="true"
     * @generated
     */
    EList<EdgeMapping> getEdgeMappings();

    /**
     * Returns the value of the '<em><b>Source Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The semantic element of the source view. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Source Variable</em>' containment
     *         reference.
     * @see #setSourceVariable(SourceEdgeCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_SourceVariable()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='source'"
     * @generated
     */
    SourceEdgeCreationVariable getSourceVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceVariable
     * <em>Source Variable</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source Variable</em>' containment
     *            reference.
     * @see #getSourceVariable()
     * @generated
     */
    void setSourceVariable(SourceEdgeCreationVariable value);

    /**
     * Returns the value of the '<em><b>Target Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The semantic element of the target view. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Target Variable</em>' containment
     *         reference.
     * @see #setTargetVariable(TargetEdgeCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_TargetVariable()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='target'"
     * @generated
     */
    TargetEdgeCreationVariable getTargetVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetVariable
     * <em>Target Variable</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Variable</em>' containment
     *            reference.
     * @see #getTargetVariable()
     * @generated
     */
    void setTargetVariable(TargetEdgeCreationVariable value);

    /**
     * Returns the value of the '<em><b>Source View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The source view (instance of EdgeTarget) <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Source View Variable</em>' containment
     *         reference.
     * @see #setSourceViewVariable(SourceEdgeViewCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_SourceViewVariable()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='sourceView'"
     * @generated
     */
    SourceEdgeViewCreationVariable getSourceViewVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceViewVariable
     * <em>Source View Variable</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source View Variable</em>'
     *            containment reference.
     * @see #getSourceViewVariable()
     * @generated
     */
    void setSourceViewVariable(SourceEdgeViewCreationVariable value);

    /**
     * Returns the value of the '<em><b>Target View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The target view (instance of EdgeTarget) <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Target View Variable</em>' containment
     *         reference.
     * @see #setTargetViewVariable(TargetEdgeViewCreationVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_TargetViewVariable()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='targetView'"
     * @generated
     */
    TargetEdgeViewCreationVariable getTargetViewVariable();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetViewVariable
     * <em>Target View Variable</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target View Variable</em>'
     *            containment reference.
     * @see #getTargetViewVariable()
     * @generated
     */
    void setTargetViewVariable(TargetEdgeViewCreationVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The first operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitEdgeCreationOperation)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitEdgeCreationOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment
     *            reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitEdgeCreationOperation value);

    /**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute. The
     * default value is <code>""</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The path of the icon to display
     * in the palette. If unset, the icon corresponding to the semantic element
     * associated with the mapping will be displayed. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_IconPath()
     * @model default=""
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getIconPath
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
     * Returns the value of the '<em><b>Extra Source Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All mappings that create views that are able to received a request to
     * manage this creation
     *
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Extra Source Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_ExtraSourceMappings()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getExtraSourceMappings();

    /**
     * Returns the value of the '<em><b>Extra Target Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All mappings that create views that are able to received a request to
     * manage this creation
     *
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Extra Target Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_ExtraTargetMappings()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getExtraTargetMappings();

    /**
     * Returns the value of the '<em><b>Connection Start Precondition</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The start precondition of the tool. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Connection Start Precondition</em>'
     *         attribute.
     * @see #setConnectionStartPrecondition(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getEdgeCreationDescription_ConnectionStartPrecondition()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables container='ecore.EObject | the semantic element of diagram.' preSourceView='diagram.EdgeTarget | (edge only) the source view of the current potential edge.' preSource='ecore.EObject | (edge only) the semantic element of $preSourceView.' diagram='diagram.DDiagram | the diagram of the current potential edge'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    String getConnectionStartPrecondition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getConnectionStartPrecondition
     * <em>Connection Start Precondition</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Connection Start Precondition</em>'
     *            attribute.
     * @see #getConnectionStartPrecondition()
     * @generated
     */
    void setConnectionStartPrecondition(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Returns the best mapping to use.
     *
     * @param source
     *            The source View.
     * @param target
     *            The target view.
     * @param createdElements
     *            The element that has been created by this tool. <!--
     *            end-model-doc -->
     * @model createdElementsMany="true"
     * @generated
     */
    EdgeMapping getBestMapping(EdgeTarget source, EdgeTarget target, EList<EObject> createdElements);

} // EdgeCreationDescription
