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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.tests.sample.component.ComponentFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "component";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/sample/component";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "component";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ComponentPackage eINSTANCE = org.eclipse.sirius.tests.sample.component.impl.ComponentPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl
     * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.tests.sample.component.impl.ComponentImpl
     * @see org.eclipse.sirius.tests.sample.component.impl.ComponentPackageImpl#getComponent()
     * @generated
     */
    int COMPONENT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__NAME = 0;

    /**
     * The feature id for the '<em><b>Payload</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__PAYLOAD = 1;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__CHILDREN = 2;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__REFERENCES = 3;

    /**
     * The feature id for the '<em><b>Reference</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__REFERENCE = 4;

    /**
     * The feature id for the '<em><b>Opposites</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__OPPOSITES = 5;

    /**
     * The feature id for the '<em><b>References2</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__REFERENCES2 = 6;

    /**
     * The feature id for the '<em><b>Aliases</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT__ALIASES = 7;

    /**
     * The number of structural features of the '<em>Component</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT_FEATURE_COUNT = 8;

    /**
     * The number of operations of the '<em>Component</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPONENT_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.component.Component
     * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Component</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component
     * @generated
     */
    EClass getComponent();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#getName()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.component.Component#isPayload
     * <em>Payload</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Payload</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#isPayload()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Payload();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getChildren
     * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Children</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#getChildren()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_Children();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getReferences
     * <em>References</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>References</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#getReferences()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_References();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getReference
     * <em>Reference</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Reference</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#getReference()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_Reference();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getOpposites
     * <em>Opposites</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Opposites</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#getOpposites()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_Opposites();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getReferences2
     * <em>References2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>References2</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#getReferences2()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_References2();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.sample.component.Component#getAliases
     * <em>Aliases</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Aliases</em>'.
     * @see org.eclipse.sirius.tests.sample.component.Component#getAliases()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Aliases();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ComponentFactory getComponentFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl
         * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.tests.sample.component.impl.ComponentImpl
         * @see org.eclipse.sirius.tests.sample.component.impl.ComponentPackageImpl#getComponent()
         * @generated
         */
        EClass COMPONENT = eINSTANCE.getComponent();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPONENT__NAME = eINSTANCE.getComponent_Name();

        /**
         * The meta object literal for the '<em><b>Payload</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPONENT__PAYLOAD = eINSTANCE.getComponent_Payload();

        /**
         * The meta object literal for the '<em><b>Children</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference COMPONENT__CHILDREN = eINSTANCE.getComponent_Children();

        /**
         * The meta object literal for the '<em><b>References</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPONENT__REFERENCES = eINSTANCE.getComponent_References();

        /**
         * The meta object literal for the '<em><b>Reference</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPONENT__REFERENCE = eINSTANCE.getComponent_Reference();

        /**
         * The meta object literal for the '<em><b>Opposites</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPONENT__OPPOSITES = eINSTANCE.getComponent_Opposites();

        /**
         * The meta object literal for the '<em><b>References2</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPONENT__REFERENCES2 = eINSTANCE.getComponent_References2();

        /**
         * The meta object literal for the '<em><b>Aliases</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMPONENT__ALIASES = eINSTANCE.getComponent_Aliases();

    }

} // ComponentPackage
