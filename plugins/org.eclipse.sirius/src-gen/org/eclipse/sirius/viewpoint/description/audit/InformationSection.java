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
package org.eclipse.sirius.viewpoint.description.audit;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Information Section</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The section that displays informations about the
 * selected element. <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.sirius.viewpoint.description.audit.AuditPackage#getInformationSection()
 * @model abstract="true"
 * @generated
 */
public interface InformationSection extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the content of the section.
     *
     * @param eObj
     *            The selected element. <!-- end-model-doc -->
     * @model
     * @generated
     */
    String getContent(EObject eObj);

} // InformationSection
