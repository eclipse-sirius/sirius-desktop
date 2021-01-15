/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.representation.DRepresentationDescriptorToDRepresentationLinkManager;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DRepresentation Descriptor</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getDescription <em>Description</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getTarget <em>Target</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepresentation <em>Representation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepPath <em>Rep Path</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getChangeId <em>Change Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationDescriptor()
 * @model
 * @generated
 */
public interface DRepresentationDescriptor extends IdentifiedElement, DModelElement, DocumentedElement {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The name of the representation. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationDescriptor_Name()
     * @model default=""
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The description of the representation targeted by this descriptor. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(RepresentationDescription)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationDescriptor_Description()
     * @model required="true"
     * @generated
     */
    RepresentationDescription getDescription();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getDescription
     * <em>Description</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(RepresentationDescription value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The referenced EObject. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(EObject)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationDescriptor_Target()
     * @model required="true"
     * @generated
     */
    EObject getTarget();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getTarget <em>Target</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(EObject value);

    /**
     * Returns the value of the '<em><b>Representation</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The representation targeted by this descriptor. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Representation</em>' reference.
     * @see #setRepresentation(DRepresentation)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationDescriptor_Representation()
     * @model required="true"
     * @generated
     */
    DRepresentation getRepresentation();

    /**
     * Tells if the representation associated this {@link DRepresentationDescriptor} is loaded.</br>
     * Knowing that {@code getRepresentation} will load the representation, the method is useful for client that would
     * implement the representation lazy loading.
     *
     * @return true if the representation is loaded.
     */
    default boolean isLoadedRepresentation() {
        return eIsSet(ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation());
    }

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepresentation
     * <em>Representation</em>}' reference. <!-- begin-user-doc -->If the newRepresentation value is not null, the
     * newRepresentation.eResource must not be null.<!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Representation</em>' reference.
     * @see #getRepresentation()
     * @generated
     */
    void setRepresentation(DRepresentation value);

    /**
     * Updates the repPath attribute according to the given representation. This method is intended to be called if the
     * representation instance is still the same but requires to recompute the repPath.
     *
     * @param representation
     *            the {@link DRepresentation} to update path.
     */
    default void updateRepresentation(DRepresentation representation) {
        DRepresentationDescriptorToDRepresentationLinkManager pathManager = new DRepresentationDescriptorToDRepresentationLinkManager(this);
        pathManager.setRepresentation(representation);
    }

    /**
     * Returns the value of the '<em><b>Rep Path</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rep Path</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Rep Path</em>' attribute.
     * @see #setRepPath(ResourceDescriptor)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationDescriptor_RepPath()
     * @model dataType="org.eclipse.sirius.viewpoint.ResourceDescriptor" required="true"
     * @generated
     */
    ResourceDescriptor getRepPath();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getRepPath <em>Rep
     * Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Rep Path</em>' attribute.
     * @see #getRepPath()
     * @generated
     */
    void setRepPath(ResourceDescriptor value);

    /**
     * Returns the value of the '<em><b>Change Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Change Id</em>' attribute.
     * @see #setChangeId(String)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDRepresentationDescriptor_ChangeId()
     * @model
     * @generated
     */
    String getChangeId();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DRepresentationDescriptor#getChangeId <em>Change
     * Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Change Id</em>' attribute.
     * @see #getChangeId()
     * @generated
     */
    void setChangeId(String value);

} // DRepresentationDescriptor
