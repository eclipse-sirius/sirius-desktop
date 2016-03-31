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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> An edge mapping allows to create ViewEdge. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getSourceMapping
 * <em>Source Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetMapping
 * <em>Target Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetFinderExpression
 * <em>Target Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getSourceFinderExpression
 * <em>Source Finder Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.EdgeMapping#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetExpression
 * <em>Target Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.EdgeMapping#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#isUseDomainElement
 * <em>Use Domain Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getReconnections
 * <em>Reconnections</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getPathExpression
 * <em>Path Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getPathNodeMapping
 * <em>Path Node Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping()
 * @model
 * @generated
 */
public interface EdgeMapping extends DiagramElementMapping, DocumentedElement, IEdgeMapping {
    /**
     * Returns the value of the '<em><b>Source Mapping</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc --> <!-- begin-model-doc --> The mapping
     *        that creates EdgeTargets that are the sources of the ViewEdges
     *        that are created by this EdgeMapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Source Mapping</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_SourceMapping()
     * @model required="true"
     * @generated
     */
    EList<DiagramElementMapping> getSourceMapping();

    /**
     * Returns the value of the '<em><b>Target Mapping</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc --> <!-- begin-model-doc --> The mapping
     *        that creates EdgeTargets that are the targets of the ViewEdges
     *        that are created by this EdgeMapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target Mapping</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_TargetMapping()
     * @model required="true"
     * @generated
     */
    EList<DiagramElementMapping> getTargetMapping();

    /**
     * Returns the value of the '<em><b>Target Finder Expression</b></em>'
     * attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> An expression to retrieve
     * the targets of an edge. The context of the expression depends on the
     * useDomainElement value. If useDomainElement is true, the expression will
     * be evaluated with all objects that are instances of the type represented
     * by the domainClass value. All this objects will depends on the
     * semanticCandidatesExpression. By default all objects are the objects that
     * are in the enclosing viewpoint's rootContent subtree. If the
     * semanticCandidatesExpression is filled in then all the objects will be
     * the objects of the returned list. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target Finder Expression</em>' attribute.
     * @see #setTargetFinderExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_TargetFinderExpression()
     * @model default="" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an EObject.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DDiagram.' viewpoint='diagram.DDiagram | (deprecated) the current DDiagram.' viewPoint='diagram.DDiagram | (deprecated) the current DDiagram.'"
     * @generated
     */
    String getTargetFinderExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetFinderExpression
     * <em>Target Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Finder Expression</em>'
     *            attribute.
     * @see #getTargetFinderExpression()
     * @generated
     */
    void setTargetFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> An expression to retrieve the sources of an edge. All
     * this objects will depends on the semanticCandidatesExpression. By default
     * all objects are the objects that are in the enclosing viewpoint's
     * rootContent subtree. If the semanticCandidatesExpression is filled in
     * then all the objects will be the objects of the returned list. This
     * attribute is taking in account only if the useDomainElement is true. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Source Finder Expression</em>' attribute.
     * @see #setSourceFinderExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_SourceFinderExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an EObject.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DDiagram.' viewpoint='diagram.DDiagram | (deprecated) the current DDiagram.' viewPoint='diagram.DDiagram | (deprecated) the current DDiagram.'"
     * @generated
     */
    String getSourceFinderExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getSourceFinderExpression
     * <em>Source Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source Finder Expression</em>'
     *            attribute.
     * @see #getSourceFinderExpression()
     * @generated
     */
    void setSourceFinderExpression(String value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The style of the edge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(EdgeStyleDescription)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_Style()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EdgeStyleDescription getStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(EdgeStyleDescription value);

    /**
     * Returns the value of the '<em><b>Conditionnal Styles</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All conditional styles. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Conditionnal Styles</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_ConditionnalStyles()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ConditionalEdgeStyleDescription> getConditionnalStyles();

    /**
     * Returns the value of the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An
     * expression computing the targeted semantic element of this edge. If this
     * attribut is not filled in. Then the target element will be : - The target
     * element of the source node if useDomainElement is false. - The object
     * that is an instance of domainClass value if useDomainElement is true.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target Expression</em>' attribute.
     * @see #setTargetExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_TargetExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DDiagram.' viewpoint='diagram.DDiagram | (deprecated) the current DDiagram.' viewPoint='diagram.DDiagram | (deprecated) the current DDiagram.'"
     * @generated
     */
    String getTargetExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetExpression
     * <em>Target Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Expression</em>' attribute.
     * @see #getTargetExpression()
     * @generated
     */
    void setTargetExpression(String value);

    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * type of the target elements that are represented by this edge. Useful
     * only if useDomainElement is true. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getDomainClass
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
     * Returns the value of the '<em><b>Use Domain Element</b></em>' attribute.
     * The default value is <code>"false"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Use Domain Element</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Use Domain Element</em>' attribute.
     * @see #setUseDomainElement(boolean)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_UseDomainElement()
     * @model default="false"
     * @generated
     */
    boolean isUseDomainElement();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#isUseDomainElement
     * <em>Use Domain Element</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Use Domain Element</em>' attribute.
     * @see #isUseDomainElement()
     * @generated
     */
    void setUseDomainElement(boolean value);

    /**
     * Returns the value of the '<em><b>Reconnections</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reconnections</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reconnections</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_Reconnections()
     * @model
     * @generated
     */
    EList<ReconnectEdgeDescription> getReconnections();

    /**
     * Returns the value of the '<em><b>Path Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Path Expression</em>' attribute.
     * @see #setPathExpression(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_PathExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an EObject.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DDiagram.' viewpoint='diagram.DDiagram | (deprecated) the current DDiagram.' element='ecore.EObject | the semantic element  of the current edge.' source='ecore.EObject | the semantic target of the current source node.' target='ecore.EObject | the semantic element of the current target node.'"
     * @generated
     */
    String getPathExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getPathExpression
     * <em>Path Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Path Expression</em>' attribute.
     * @see #getPathExpression()
     * @generated
     */
    void setPathExpression(String value);

    /**
     * Returns the value of the '<em><b>Path Node Mapping</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path Node Mapping</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Path Node Mapping</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getEdgeMapping_PathNodeMapping()
     * @model
     * @generated
     */
    EList<AbstractNodeMapping> getPathNodeMapping();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model sourceRequired="true" targetRequired="true"
     * @generated
     */
    @Deprecated
    DEdge createEdge(EdgeTarget source, EdgeTarget target, EObject semanticTarget);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model sourceRequired="true" targetRequired="true"
     * @generated
     */
    @Deprecated
    DEdge createEdge(EdgeTarget source, EdgeTarget target, EObject container, EObject semanticTarget);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    @Override
    EdgeStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model viewEdgeRequired="true"
     * @generated
     */
    @Deprecated
    void updateEdge(DEdge viewEdge);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    @Deprecated
    EList<EObject> getEdgeTargetCandidates(EObject semanticOrigin, DDiagram viewPoint);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    @Deprecated
    EList<EObject> getEdgeSourceCandidates(EObject semanticOrigin, DDiagram viewPoint);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    @Deprecated
    EList<EObject> getEdgeTargetCandidates(EObject semanticOrigin, EObject container, EObject containerView);

} // EdgeMapping
