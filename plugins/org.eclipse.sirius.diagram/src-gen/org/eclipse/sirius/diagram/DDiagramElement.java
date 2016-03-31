/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.Decoration;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DDiagram Element</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElement#isVisible
 * <em>Visible</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElement#getTooltipText
 * <em>Tooltip Text</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElement#getParentLayers
 * <em>Parent Layers</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElement#getDecorations
 * <em>Decorations</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.DDiagramElement#getDiagramElementMapping
 * <em>Diagram Element Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElement#getGraphicalFilters
 * <em>Graphical Filters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElement()
 * @model abstract="true"
 * @generated
 */
public interface DDiagramElement extends DRepresentationElement {
    /**
     * Returns the value of the '<em><b>Visible</b></em>' attribute. The default
     * value is <code>"true"</code>. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> True if the element is visible, false
     * otherwise. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Visible</em>' attribute.
     * @see #setVisible(boolean)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElement_Visible()
     * @model default="true"
     * @generated
     */
    boolean isVisible();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#isVisible
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
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * text to show in the element's tooltip. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Tooltip Text</em>' attribute.
     * @see #setTooltipText(String)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElement_TooltipText()
     * @model
     * @generated
     */
    String getTooltipText();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getTooltipText
     * <em>Tooltip Text</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tooltip Text</em>' attribute.
     * @see #getTooltipText()
     * @generated
     */
    void setTooltipText(String value);

    /**
     * Returns the value of the '<em><b>Parent Layers</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.Layer}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Parent Layers</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Parent Layers</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElement_ParentLayers()
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElement_Decorations()
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElement_DiagramElementMapping()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    DiagramElementMapping getDiagramElementMapping();

    /**
     * Returns the value of the '<em><b>Graphical Filters</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.GraphicalFilter}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> Graphical filters
     * allowing to handle this element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Graphical Filters</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElement_GraphicalFilters()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<GraphicalFilter> getGraphicalFilters();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the parent of this ViewPointElement. <!-- end-model-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    DDiagram getParentDiagram();

} // DDiagramElement
