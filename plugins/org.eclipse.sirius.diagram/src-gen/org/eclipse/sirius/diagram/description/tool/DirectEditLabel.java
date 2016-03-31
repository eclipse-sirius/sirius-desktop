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
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Direct Edit Label</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A tool that allows to edit the label of a
 * ViewPointElement. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getMask
 * <em>Mask</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInputLabelExpression
 * <em>Input Label Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDirectEditLabel()
 * @model
 * @generated
 */
public interface DirectEditLabel extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Mask</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The edit mask. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Mask</em>' containment reference.
     * @see #setMask(EditMaskVariables)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDirectEditLabel_Mask()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    EditMaskVariables getMask();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getMask
     * <em>Mask</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mask</em>' containment reference.
     * @see #getMask()
     * @generated
     */
    void setMask(EditMaskVariables value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The first operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDirectEditLabel_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInitialOperation
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

    /**
     * Returns the value of the '<em><b>Input Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Expression that computes the name of a diagramElement
     * to edit with direct edit tool. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Input Label Expression</em>' attribute.
     * @see #setInputLabelExpression(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDirectEditLabel_InputLabelExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DDiagram | the current DSemanticDiagram.' view='diagram.DDiagramElement | the current view for which the label is calculated.'"
     * @generated
     */
    String getInputLabelExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInputLabelExpression
     * <em>Input Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Input Label Expression</em>'
     *            attribute.
     * @see #getInputLabelExpression()
     * @generated
     */
    void setInputLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Mapping</b></em> ' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping}.
     * It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getLabelDirectEdit
     * <em>Label Direct Edit</em>}' <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @model kind="operation" required="true"
     * @generated
     */
    EList<DiagramElementMapping> getMapping();

} // DirectEditLabel
