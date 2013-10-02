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

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.Layer;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>View Point Element</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElement#isVisible <em>Visible
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElement#getTooltipText <em>
 * Tooltip Text</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElement#getParentLayers <em>
 * Parent Layers</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElement#getDecorations <em>
 * Decorations</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DDiagramElement#getDiagramElementMapping
 * <em>Diagram Element Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElement#getGraphicalFilters
 * <em>Graphical Filters</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagramElement()
 * @model abstract="true"
 * @generated
 */
public interface DDiagramElement extends DRepresentationElement, DValidable, DNavigable {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Visible</b></em>' attribute. The default
     * value is <code>"true"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Visible</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> True if the element is
     * visible, false otherwise. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Visible</em>' attribute.
     * @see #setVisible(boolean)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusElement_Visible()
     * @model default="true"
     * @generated
     */
    boolean isVisible();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#isVisible
     * <em>Visible</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Visible</em>' attribute.
     * @see #isVisible()
     * @generated
     */
    void setVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tooltip Text</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * 
     * @since 2.0 <!-- end-user-doc --> <!-- begin-model-doc --> The text to
     *        show in the element's tooltip. <!-- end-model-doc -->
     * @return the value of the '<em>Tooltip Text</em>' attribute.
     * @see #setTooltipText(String)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagramElement_TooltipText()
     * @model
     * @generated
     */
    String getTooltipText();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement#getTooltipText
     * <em>Tooltip Text</em>}' attribute. <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @param value
     *            the new value of the '<em>Tooltip Text</em>' attribute.
     * @see #getTooltipText()
     * @generated
     */
    void setTooltipText(String value);

    /**
     * Returns the value of the '<em><b>Parent Layers</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.Layer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Layers</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parent Layers</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagramElement_ParentLayers()
     * @model
     * @generated
     */
    EList<Layer> getParentLayers();

    /**
     * Returns the value of the '<em><b>Decorations</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.Decoration}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Decorations</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Decorations</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagramElement_Decorations()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<Decoration> getDecorations();

    /**
     * Returns the value of the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The mapping of the element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Diagram Element Mapping</em>' reference.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagramElement_DiagramElementMapping()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    DiagramElementMapping getDiagramElementMapping();

    /**
     * Returns the value of the '<em><b>Graphical Filters</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.GraphicalFilter}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> Graphical filters
     * allowing to handle this element. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Graphical Filters</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagramElement_GraphicalFilters()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<GraphicalFilter> getGraphicalFilters();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the parent of this SiriusElement. <!-- end-model-doc -->
     * 
     * @model kind="operation"
     * @generated
     */
    DDiagram getParentDiagram();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return <code>true</code> if this element is fold.
     * 
     * @param alreadyManagedElements
     *            Maps all elements with their isFold attribute value. <!--
     *            end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    boolean isFold(Map<?, ?> alreadyManagedElements);

} // DDiagramElement
