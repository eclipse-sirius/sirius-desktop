/**
 * Copyright (c) 2014 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.basicfamily;

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
 * @see org.eclipse.sirius.sample.basicfamily.BasicfamilyFactory
 * @model kind="package"
 * @generated
 */
public interface BasicfamilyPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "basicfamily"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/sample/basicfamily"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "basicfamily"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    BasicfamilyPackage eINSTANCE = org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.basicfamily.impl.PersonImpl
     * <em>Person</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.sample.basicfamily.impl.PersonImpl
     * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getPerson()
     * @generated
     */
    int PERSON = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PERSON__NAME = 0;

    /**
     * The feature id for the '<em><b>Children</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PERSON__CHILDREN = 1;

    /**
     * The feature id for the '<em><b>Parents</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PERSON__PARENTS = 2;

    /**
     * The feature id for the '<em><b>Mother</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PERSON__MOTHER = 3;

    /**
     * The feature id for the '<em><b>Father</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PERSON__FATHER = 4;

    /**
     * The number of structural features of the '<em>Person</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PERSON_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.basicfamily.impl.FamilyImpl
     * <em>Family</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.sample.basicfamily.impl.FamilyImpl
     * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getFamily()
     * @generated
     */
    int FAMILY = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FAMILY__NAME = 0;

    /**
     * The feature id for the '<em><b>Members</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FAMILY__MEMBERS = 1;

    /**
     * The number of structural features of the '<em>Family</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FAMILY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.basicfamily.impl.ManImpl <em>Man</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.sample.basicfamily.impl.ManImpl
     * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getMan()
     * @generated
     */
    int MAN = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAN__NAME = PERSON__NAME;

    /**
     * The feature id for the '<em><b>Children</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAN__CHILDREN = PERSON__CHILDREN;

    /**
     * The feature id for the '<em><b>Parents</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAN__PARENTS = PERSON__PARENTS;

    /**
     * The feature id for the '<em><b>Mother</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAN__MOTHER = PERSON__MOTHER;

    /**
     * The feature id for the '<em><b>Father</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAN__FATHER = PERSON__FATHER;

    /**
     * The number of structural features of the '<em>Man</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAN_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.basicfamily.impl.WomanImpl
     * <em>Woman</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.sample.basicfamily.impl.WomanImpl
     * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getWoman()
     * @generated
     */
    int WOMAN = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WOMAN__NAME = PERSON__NAME;

    /**
     * The feature id for the '<em><b>Children</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WOMAN__CHILDREN = PERSON__CHILDREN;

    /**
     * The feature id for the '<em><b>Parents</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WOMAN__PARENTS = PERSON__PARENTS;

    /**
     * The feature id for the '<em><b>Mother</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WOMAN__MOTHER = PERSON__MOTHER;

    /**
     * The feature id for the '<em><b>Father</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WOMAN__FATHER = PERSON__FATHER;

    /**
     * The number of structural features of the '<em>Woman</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WOMAN_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.basicfamily.Person <em>Person</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Person</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Person
     * @generated
     */
    EClass getPerson();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.basicfamily.Person#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Person#getName()
     * @see #getPerson()
     * @generated
     */
    EAttribute getPerson_Name();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.sample.basicfamily.Person#getChildren
     * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Children</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Person#getChildren()
     * @see #getPerson()
     * @generated
     */
    EReference getPerson_Children();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.sample.basicfamily.Person#getParents
     * <em>Parents</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Parents</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Person#getParents()
     * @see #getPerson()
     * @generated
     */
    EReference getPerson_Parents();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.basicfamily.Person#getMother
     * <em>Mother</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Mother</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Person#getMother()
     * @see #getPerson()
     * @generated
     */
    EReference getPerson_Mother();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.basicfamily.Person#getFather
     * <em>Father</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Father</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Person#getFather()
     * @see #getPerson()
     * @generated
     */
    EReference getPerson_Father();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.basicfamily.Family <em>Family</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Family</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Family
     * @generated
     */
    EClass getFamily();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.basicfamily.Family#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Family#getName()
     * @see #getFamily()
     * @generated
     */
    EAttribute getFamily_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.basicfamily.Family#getMembers
     * <em>Members</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Members</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Family#getMembers()
     * @see #getFamily()
     * @generated
     */
    EReference getFamily_Members();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.basicfamily.Man <em>Man</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Man</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Man
     * @generated
     */
    EClass getMan();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.basicfamily.Woman <em>Woman</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Woman</em>'.
     * @see org.eclipse.sirius.sample.basicfamily.Woman
     * @generated
     */
    EClass getWoman();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    BasicfamilyFactory getBasicfamilyFactory();

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
         * {@link org.eclipse.sirius.sample.basicfamily.impl.PersonImpl
         * <em>Person</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.sample.basicfamily.impl.PersonImpl
         * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getPerson()
         * @generated
         */
        EClass PERSON = eINSTANCE.getPerson();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PERSON__CHILDREN = eINSTANCE.getPerson_Children();

        /**
         * The meta object literal for the '<em><b>Parents</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PERSON__PARENTS = eINSTANCE.getPerson_Parents();

        /**
         * The meta object literal for the '<em><b>Mother</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PERSON__MOTHER = eINSTANCE.getPerson_Mother();

        /**
         * The meta object literal for the '<em><b>Father</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PERSON__FATHER = eINSTANCE.getPerson_Father();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.basicfamily.impl.FamilyImpl
         * <em>Family</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.sample.basicfamily.impl.FamilyImpl
         * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getFamily()
         * @generated
         */
        EClass FAMILY = eINSTANCE.getFamily();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FAMILY__NAME = eINSTANCE.getFamily_Name();

        /**
         * The meta object literal for the '<em><b>Members</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FAMILY__MEMBERS = eINSTANCE.getFamily_Members();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.basicfamily.impl.ManImpl
         * <em>Man</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.sample.basicfamily.impl.ManImpl
         * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getMan()
         * @generated
         */
        EClass MAN = eINSTANCE.getMan();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.basicfamily.impl.WomanImpl
         * <em>Woman</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.sample.basicfamily.impl.WomanImpl
         * @see org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyPackageImpl#getWoman()
         * @generated
         */
        EClass WOMAN = eINSTANCE.getWoman();

    }

} // BasicfamilyPackage
