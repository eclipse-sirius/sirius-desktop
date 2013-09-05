/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.emf.common.util.EList;

import org.eclipse.sirius.description.RepresentationExtensionDescription;
import org.eclipse.sirius.description.contribution.ContributionProvider;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edition Table Extension Description</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription#getOwnedLineMappings
 * <em>Owned Line Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription#getOwnedColumnMappings
 * <em>Owned Column Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription#getOwnedTools
 * <em>Owned Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription#getDomainClass
 * <em>Domain Class</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableExtensionDescription()
 * @model
 * @generated
 */
public interface EditionTableExtensionDescription extends RepresentationExtensionDescription, ContributionProvider {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Owned Line Mappings</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Line Mappings</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Owned Line Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableExtensionDescription_OwnedLineMappings()
     * @model containment="true" keys="name" required="true"
     * @generated
     */
    EList<LineMapping> getOwnedLineMappings();

    /**
     * Returns the value of the '<em><b>Owned Column Mappings</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Column Mappings</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Owned Column Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableExtensionDescription_OwnedColumnMappings()
     * @model containment="true" keys="name" required="true"
     * @generated
     */
    EList<FeatureColumnMapping> getOwnedColumnMappings();

    /**
     * Returns the value of the '<em><b>Owned Tools</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableTool}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Tools</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Owned Tools</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableExtensionDescription_OwnedTools()
     * @model containment="true"
     * @generated
     */
    EList<TableTool> getOwnedTools();

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>'
     * attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The precondition (Acceleo
     * Expression). <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableExtensionDescription_PreconditionExpression()
     * @model default=""
     *        dataType="org.eclipse.sirius.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='boolean'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Precondition Expression</em>'
     *            attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain Class</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getEditionTableExtensionDescription_DomainClass()
     * @model dataType="org.eclipse.sirius.description.TypeName"
     *        required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription#getDomainClass
     * <em>Domain Class</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

} // EditionTableExtensionDescription
