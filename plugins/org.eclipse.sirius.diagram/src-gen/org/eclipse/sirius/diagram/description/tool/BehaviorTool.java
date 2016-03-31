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

import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Behavior Tool</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A tool that can be launched by the user. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.BehaviorTool#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.BehaviorTool#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getBehaviorTool()
 * @model
 * @generated
 */
public interface BehaviorTool extends AbstractToolDescription {
    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. The
     * default value is <code>"EObject"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The type of elements on which
     * we want to appy the tool. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getBehaviorTool_DomainClass()
     * @model default="EObject"
     *        dataType="org.eclipse.sirius.viewpoint.description.TypeName"
     *        required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.BehaviorTool#getDomainClass
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
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The first operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getBehaviorTool_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.BehaviorTool#getInitialOperation
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

} // BehaviorTool
