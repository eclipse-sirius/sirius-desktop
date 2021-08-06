/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Paste Target Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> A PasteTargetDescription is a Description or Mapping that can have many PasteTools. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.PasteTargetDescription#getPasteDescriptions <em>Paste
 * Descriptions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getPasteTargetDescription()
 * @model abstract="true"
 * @generated
 */
public interface PasteTargetDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Paste Descriptions</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Paste Descriptions</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Paste Descriptions</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getPasteTargetDescription_PasteDescriptions()
     * @model
     * @generated
     */
    EList<PasteDescription> getPasteDescriptions();

} // PasteTargetDescription
