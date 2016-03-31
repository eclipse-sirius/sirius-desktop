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
package org.eclipse.sirius.diagram.description.style;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Hide Label Capability Style Description</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription#isHideLabelByDefault
 * <em>Hide Label By Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getHideLabelCapabilityStyleDescription()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface HideLabelCapabilityStyleDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Hide Label By Default</b></em>'
     * attribute. The default value is <code>"false"</code>. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> The default visibility
     * of the label (available only if labelPosition equals BORDER). A change of
     * this option does not affect already existing elements. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Hide Label By Default</em>' attribute.
     * @see #setHideLabelByDefault(boolean)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getHideLabelCapabilityStyleDescription_HideLabelByDefault()
     * @model default="false"
     * @generated
     */
    boolean isHideLabelByDefault();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription#isHideLabelByDefault
     * <em>Hide Label By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Hide Label By Default</em>'
     *            attribute.
     * @see #isHideLabelByDefault()
     * @generated
     */
    void setHideLabelByDefault(boolean value);

} // HideLabelCapabilityStyleDescription
