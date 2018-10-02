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
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Table Description</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getDomainClass <em>Domain Class</em>
 * }</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedRepresentationCreationDescriptions
 * <em>Owned Representation Creation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedRepresentationCreationDescriptions
 * <em>Reused Representation Creation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllRepresentationCreationDescriptions
 * <em>All Representation Creation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedRepresentationNavigationDescriptions
 * <em>Owned Representation Navigation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedRepresentationNavigationDescriptions
 * <em>Reused Representation Navigation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllRepresentationNavigationDescriptions
 * <em>All Representation Navigation Descriptions</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedLineMappings <em>Owned Line
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedLineMappings <em>Reused
 * Line Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllLineMappings <em>All Line
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedCreateLine <em>Owned Create
 * Line</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedCreateLine <em>Reused
 * Create Line</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllCreateLine <em>All Create
 * Line</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getInitialHeaderColumnWidth
 * <em>Initial Header Column Width</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getImportedElements <em>Imported
 * Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription()
 * @model abstract="true"
 * @generated
 */
public interface TableDescription extends RepresentationDescription, DocumentedElement, EndUserDocumentedElement {

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>' attribute. The default value is
     * <code>""</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The precondition (Acceleo
     * Expression). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_PreconditionExpression()
     * @model default="" dataType="org.eclipse.sirius.description.InterpretedExpression"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition Expression</em>' attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain Class</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getDomainClass <em>Domain
     * Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the ' <em><b>Owned Representation Creation Descriptions</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription} . <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> All tools of the section. <!-- end-model-doc -->
     *
     * @return the value of the ' <em>Owned Representation Creation Descriptions</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_OwnedRepresentationCreationDescriptions()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<RepresentationCreationDescription> getOwnedRepresentationCreationDescriptions();

    /**
     * Returns the value of the ' <em><b>Reused Representation Creation Descriptions</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription} .
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All tools of the section. <!--
     * end-model-doc -->
     *
     * @return the value of the ' <em>Reused Representation Creation Descriptions</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_ReusedRepresentationCreationDescriptions()
     * @model keys="name"
     * @generated
     */
    EList<RepresentationCreationDescription> getReusedRepresentationCreationDescriptions();

    /**
     * Returns the value of the ' <em><b>All Representation Creation Descriptions</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription} .
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All tools of the section. <!--
     * end-model-doc -->
     *
     * @return the value of the ' <em>All Representation Creation Descriptions</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_AllRepresentationCreationDescriptions()
     * @model keys="name" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<RepresentationCreationDescription> getAllRepresentationCreationDescriptions();

    /**
     * Returns the value of the ' <em><b>Owned Representation Navigation Descriptions</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription} . <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> All navigation tools. <!-- end-model-doc -->
     *
     * @return the value of the ' <em>Owned Representation Navigation Descriptions</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_OwnedRepresentationNavigationDescriptions()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<RepresentationNavigationDescription> getOwnedRepresentationNavigationDescriptions();

    /**
     * Returns the value of the ' <em><b>Reused Representation Navigation Descriptions</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription} . <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> All navigation tools. <!-- end-model-doc -->
     *
     * @return the value of the ' <em>Reused Representation Navigation Descriptions</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_ReusedRepresentationNavigationDescriptions()
     * @model keys="name"
     * @generated
     */
    EList<RepresentationNavigationDescription> getReusedRepresentationNavigationDescriptions();

    /**
     * Returns the value of the ' <em><b>All Representation Navigation Descriptions</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription} .
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> All navigation tools. <!-- end-model-doc
     * -->
     *
     * @return the value of the ' <em>All Representation Navigation Descriptions</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_AllRepresentationNavigationDescriptions()
     * @model keys="name" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<RepresentationNavigationDescription> getAllRepresentationNavigationDescriptions();

    /**
     * Returns the value of the '<em><b>Owned Line Mappings</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Line Mappings</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Line Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_OwnedLineMappings()
     * @model containment="true" keys="name" required="true"
     * @generated
     */
    EList<LineMapping> getOwnedLineMappings();

    /**
     * Returns the value of the '<em><b>Reused Line Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Line Mappings</em>' reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Line Mappings</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_ReusedLineMappings()
     * @model keys="name"
     * @generated
     */
    EList<LineMapping> getReusedLineMappings();

    /**
     * Returns the value of the '<em><b>All Line Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Line Mappings</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Line Mappings</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_AllLineMappings()
     * @model keys="name" required="true" transient="true" changeable="false" volatile="true"
     * @generated
     */
    EList<LineMapping> getAllLineMappings();

    /**
     * Returns the value of the '<em><b>Owned Create Line</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.table.metamodel.table.description.CreateLineTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Create Line</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Create Line</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_OwnedCreateLine()
     * @model containment="true"
     * @generated
     */
    EList<CreateLineTool> getOwnedCreateLine();

    /**
     * Returns the value of the '<em><b>Reused Create Line</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateLineTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Create Line</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Create Line</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_ReusedCreateLine()
     * @model
     * @generated
     */
    EList<CreateLineTool> getReusedCreateLine();

    /**
     * Returns the value of the '<em><b>All Create Line</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateLineTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Create Line</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Create Line</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_AllCreateLine()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<CreateLineTool> getAllCreateLine();

    /**
     * Returns the value of the '<em><b>Initial Header Column Width</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The initial width of the column header (calculated if not available).
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Header Column Width</em>' attribute.
     * @see #setInitialHeaderColumnWidth(int)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_InitialHeaderColumnWidth()
     * @model
     * @generated
     */
    int getInitialHeaderColumnWidth();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getInitialHeaderColumnWidth
     * <em>Initial Header Column Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Header Column Width</em>' attribute.
     * @see #getInitialHeaderColumnWidth()
     * @generated
     */
    void setInitialHeaderColumnWidth(int value);

    /**
     * Returns the value of the '<em><b>Imported Elements</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.emf.ecore.EObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Imported Elements</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Imported Elements</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableDescription_ImportedElements()
     * @model containment="true"
     * @generated
     */
    EList<EObject> getImportedElements();
} // TableDescription
