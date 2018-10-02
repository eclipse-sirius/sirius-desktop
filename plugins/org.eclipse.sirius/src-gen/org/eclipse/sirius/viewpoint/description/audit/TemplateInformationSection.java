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
package org.eclipse.sirius.viewpoint.description.audit;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Template Information Section</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> This information section is based on an Acceleo template. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.audit.TemplateInformationSection#getTemplatePath <em>Template
 * Path</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.audit.AuditPackage#getTemplateInformationSection()
 * @model
 * @generated
 */
public interface TemplateInformationSection extends InformationSection {
    /**
     * Returns the value of the '<em><b>Template Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The file path of the template. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Template Path</em>' attribute.
     * @see #setTemplatePath(String)
     * @see org.eclipse.sirius.viewpoint.description.audit.AuditPackage#getTemplateInformationSection_TemplatePath()
     * @model
     * @generated
     */
    String getTemplatePath();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.audit.TemplateInformationSection#getTemplatePath <em>Template
     * Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Template Path</em>' attribute.
     * @see #getTemplatePath()
     * @generated
     */
    void setTemplatePath(String value);

} // TemplateInformationSection
