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
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Double Click Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Tool that describes double click behaviour. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getMappings
 * <em>Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElementView
 * <em>Element View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDoubleClickDescription()
 * @model
 * @generated
 */
public interface DoubleClickDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}. It
     * is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getDoubleClickDescription
     * <em>Double Click Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Mappings associated with this
     * deletion behavior. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDoubleClickDescription_Mappings()
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#getDoubleClickDescription
     * @model opposite="doubleClickDescription" required="true"
     * @generated
     */
    EList<DiagramElementMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The semantic element of the ViewPointElement to delete. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Element</em>' containment reference.
     * @see #setElement(ElementDoubleClickVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDoubleClickDescription_Element()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementDoubleClickVariable getElement();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementDoubleClickVariable value);

    /**
     * Returns the value of the '<em><b>Element View</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element View</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Element View</em>' containment reference.
     * @see #setElementView(ElementDoubleClickVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDoubleClickDescription_ElementView()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ElementDoubleClickVariable getElementView();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElementView
     * <em>Element View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element View</em>' containment
     *            reference.
     * @see #getElementView()
     * @generated
     */
    void setElementView(ElementDoubleClickVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The first operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDoubleClickDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment
     *            reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

} // DoubleClickDescription
