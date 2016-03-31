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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Gauge Composite Style</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This style groups many GaugeSection. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.GaugeCompositeStyle#getAlignment
 * <em>Alignment</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.GaugeCompositeStyle#getSections
 * <em>Sections</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeCompositeStyle()
 * @model
 * @generated
 */
public interface GaugeCompositeStyle extends NodeStyle {
    /**
     * Returns the value of the '<em><b>Alignment</b></em>' attribute. The
     * default value is <code>"SQUARE"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.diagram.AlignmentKind}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * alignment of the gauges <!-- end-model-doc -->
     *
     * @return the value of the '<em>Alignment</em>' attribute.
     * @see org.eclipse.sirius.diagram.AlignmentKind
     * @see #setAlignment(AlignmentKind)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeCompositeStyle_Alignment()
     * @model default="SQUARE"
     * @generated
     */
    AlignmentKind getAlignment();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.GaugeCompositeStyle#getAlignment
     * <em>Alignment</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Alignment</em>' attribute.
     * @see org.eclipse.sirius.diagram.AlignmentKind
     * @see #getAlignment()
     * @generated
     */
    void setAlignment(AlignmentKind value);

    /**
     * Returns the value of the '<em><b>Sections</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.GaugeSection}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The sections. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Sections</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeCompositeStyle_Sections()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<GaugeSection> getSections();

} // GaugeCompositeStyle
