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
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Layer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getNodeMappings
 * <em>Node Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getEdgeMappings
 * <em>Edge Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getEdgeMappingImports
 * <em>Edge Mapping Imports</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getContainerMappings
 * <em>Container Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getReusedMappings
 * <em>Reused Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getAllTools
 * <em>All Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getToolSections
 * <em>Tool Sections</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getReusedTools
 * <em>Reused Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.Layer#getDecorationDescriptionsSet
 * <em>Decoration Descriptions Set</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getIcon <em>Icon</em>
 * }</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getAllEdgeMappings
 * <em>All Edge Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.Layer#getCustomization
 * <em>Customization</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer()
 * @model
 * @generated
 */
public interface Layer extends DocumentedElement, EndUserDocumentedElement, IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Node Mappings</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Node
     * mappings that are owned by this simple mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Node Mappings</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_NodeMappings()
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_EdgeMappings()
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_EdgeMappingImports()
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_ContainerMappings()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<ContainerMapping> getContainerMappings();

    /**
     * Returns the value of the '<em><b>Reused Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Add here any mapping you want to reuse from another layer or diagram.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reused Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_ReusedMappings()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getReusedMappings();

    /**
     * Returns the value of the '<em><b>All Tools</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All tools of the viewpoint. <!-- end-model-doc -->
     *
     * @return the value of the '<em>All Tools</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_AllTools()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<AbstractToolDescription> getAllTools();

    /**
     * Returns the value of the '<em><b>Tool Sections</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.tool.ToolSection}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> A tool
     * section encloses many tools <!-- end-model-doc -->
     *
     * @return the value of the '<em>Tool Sections</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_ToolSections()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ToolSection> getToolSections();

    /**
     * Returns the value of the '<em><b>Reused Tools</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Tools that are reused by this viewpoint. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reused Tools</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_ReusedTools()
     * @model
     * @generated
     */
    EList<AbstractToolDescription> getReusedTools();

    /**
     * Returns the value of the '<em><b>Decoration Descriptions Set</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Decoration Descriptions Set</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Decoration Descriptions Set</em>'
     *         containment reference.
     * @see #setDecorationDescriptionsSet(DecorationDescriptionsSet)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_DecorationDescriptionsSet()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    DecorationDescriptionsSet getDecorationDescriptionsSet();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.Layer#getDecorationDescriptionsSet
     * <em>Decoration Descriptions Set</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Decoration Descriptions Set</em>'
     *            containment reference.
     * @see #getDecorationDescriptionsSet()
     * @generated
     */
    void setDecorationDescriptionsSet(DecorationDescriptionsSet value);

    /**
     * Returns the value of the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> image
     * path to use as an icon for the layer <!-- end-model-doc -->
     *
     * @return the value of the '<em>Icon</em>' attribute.
     * @see #setIcon(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_Icon()
     * @model
     * @generated
     */
    String getIcon();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.Layer#getIcon <em>Icon</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Icon</em>' attribute.
     * @see #getIcon()
     * @generated
     */
    void setIcon(String value);

    /**
     * Returns the value of the '<em><b>All Edge Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All
     * edge mappings (including import edge mapping) of this simple mapping.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>All Edge Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_AllEdgeMappings()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<EdgeMapping> getAllEdgeMappings();

    /**
     * Returns the value of the '<em><b>Customization</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Customization</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Customization</em>' containment reference.
     * @see #setCustomization(Customization)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayer_Customization()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    Customization getCustomization();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.Layer#getCustomization
     * <em>Customization</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Customization</em>' containment
     *            reference.
     * @see #getCustomization()
     * @generated
     */
    void setCustomization(Customization value);

} // Layer
