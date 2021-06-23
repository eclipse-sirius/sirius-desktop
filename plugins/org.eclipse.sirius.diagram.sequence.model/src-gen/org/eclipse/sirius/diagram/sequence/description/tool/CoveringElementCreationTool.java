/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.description.tool;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Covering Element Creation Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool#getCoveredLifelines
 * <em>Covered Lifelines</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getCoveringElementCreationTool()
 * @model abstract="true"
 * @generated
 */
public interface CoveringElementCreationTool extends EObject {
    /**
     * Returns the value of the '<em><b>Covered Lifelines</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Covered Lifelines</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Covered Lifelines</em>' containment reference.
     * @see #setCoveredLifelines(CoveredLifelinesVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getCoveringElementCreationTool_CoveredLifelines()
     * @model containment="true" required="true"
     * @generated
     */
    CoveredLifelinesVariable getCoveredLifelines();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool#getCoveredLifelines
     * <em>Covered Lifelines</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Covered Lifelines</em>' containment reference.
     * @see #getCoveredLifelines()
     * @generated
     */
    void setCoveredLifelines(CoveredLifelinesVariable value);

} // CoveringElementCreationTool
