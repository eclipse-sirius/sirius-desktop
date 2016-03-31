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

import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Node Style</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Style of a node. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.NodeStyle#getLabelPosition
 * <em>Label Position</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getNodeStyle()
 * @model abstract="true"
 * @generated
 */
public interface NodeStyle extends LabelStyle, Style, BorderedStyle, HideLabelCapabilityStyle {
    /**
     * Returns the value of the '<em><b>Label Position</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.diagram.LabelPosition}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The position of the label
     * : BORDER : The label is around the node, on the border. NODE : the label
     * is in the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Position</em>' attribute.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see #setLabelPosition(LabelPosition)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getNodeStyle_LabelPosition()
     * @model
     * @generated
     */
    LabelPosition getLabelPosition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.NodeStyle#getLabelPosition
     * <em>Label Position</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Position</em>' attribute.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see #getLabelPosition()
     * @generated
     */
    void setLabelPosition(LabelPosition value);

} // NodeStyle
