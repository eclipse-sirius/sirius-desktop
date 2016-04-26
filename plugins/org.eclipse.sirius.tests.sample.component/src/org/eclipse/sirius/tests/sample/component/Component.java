/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.component;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Component</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#getName
 * <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#isPayload
 * <em>Payload</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#getChildren
 * <em>Children</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#getReferences
 * <em>References</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#getReference
 * <em>Reference</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#getOpposites
 * <em>Opposites</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#getReferences2
 * <em>References2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.component.Component#getAliases
 * <em>Aliases</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Payload</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Payload</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Payload</em>' attribute.
     * @see #setPayload(boolean)
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_Payload()
     * @model required="true"
     * @generated
     */
    boolean isPayload();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.component.Component#isPayload
     * <em>Payload</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Payload</em>' attribute.
     * @see #isPayload()
     * @generated
     */
    void setPayload(boolean value);

    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.component.Component}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_Children()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<Component> getChildren();

    /**
     * Returns the value of the '<em><b>References</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.tests.sample.component.Component}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>References</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>References</em>' reference list.
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_References()
     * @model
     * @generated
     */
    EList<Component> getReferences();

    /**
     * Returns the value of the '<em><b>Reference</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Reference</em>' reference.
     * @see #setReference(Component)
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_Reference()
     * @model
     * @generated
     */
    Component getReference();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getReference
     * <em>Reference</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Reference</em>' reference.
     * @see #getReference()
     * @generated
     */
    void setReference(Component value);

    /**
     * Returns the value of the '<em><b>Opposites</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.tests.sample.component.Component}. It is
     * bidirectional and its opposite is '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getReferences
     * <em>References</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Opposites</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Opposites</em>' reference list.
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_Opposites()
     * @see org.eclipse.sirius.tests.sample.component.Component#getReferences
     * @model opposite="references"
     * @generated
     */
    EList<Component> getOpposites();

    /**
     * Returns the value of the '<em><b>References2</b></em>' reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.component.Component}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>References2</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>References2</em>' reference list.
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_References2()
     * @model
     * @generated
     */
    EList<Component> getReferences2();

    /**
     * Returns the value of the '<em><b>Aliases</b></em>' attribute list. The
     * list contents are of type {@link java.lang.String}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Aliases</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Aliases</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.component.ComponentPackage#getComponent_Aliases()
     * @model
     * @generated
     */
    EList<String> getAliases();

} // Component
