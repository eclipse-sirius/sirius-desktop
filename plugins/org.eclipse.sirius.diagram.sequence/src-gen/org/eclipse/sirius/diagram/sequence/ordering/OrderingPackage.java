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
package org.eclipse.sirius.diagram.sequence.ordering;

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
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingFactory
 * @model kind="package"
 * @generated
 */
public interface OrderingPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "ordering"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/sequence/ordering/2.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "ordering"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    OrderingPackage eINSTANCE = org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndsOrderingImpl
     * <em>Event Ends Ordering</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndsOrderingImpl
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getEventEndsOrdering()
     * @generated
     */
    int EVENT_ENDS_ORDERING = 0;

    /**
     * The feature id for the '<em><b>Sequence Diagram</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Event Ends</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EVENT_ENDS_ORDERING__EVENT_ENDS = 1;

    /**
     * The number of structural features of the '<em>Event Ends Ordering</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EVENT_ENDS_ORDERING_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndImpl
     * <em>Event End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndImpl
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getEventEnd()
     * @generated
     */
    int EVENT_END = 1;

    /**
     * The feature id for the '<em><b>Semantic End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EVENT_END__SEMANTIC_END = 0;

    /**
     * The number of structural features of the '<em>Event End</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EVENT_END_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.SingleEventEndImpl
     * <em>Single Event End</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.SingleEventEndImpl
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getSingleEventEnd()
     * @generated
     */
    int SINGLE_EVENT_END = 2;

    /**
     * The feature id for the '<em><b>Semantic End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SINGLE_EVENT_END__SEMANTIC_END = OrderingPackage.EVENT_END__SEMANTIC_END;

    /**
     * The feature id for the '<em><b>Start</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SINGLE_EVENT_END__START = OrderingPackage.EVENT_END_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Event</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SINGLE_EVENT_END__SEMANTIC_EVENT = OrderingPackage.EVENT_END_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Single Event End</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_EVENT_END_FEATURE_COUNT = OrderingPackage.EVENT_END_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.CompoundEventEndImpl
     * <em>Compound Event End</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.CompoundEventEndImpl
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getCompoundEventEnd()
     * @generated
     */
    int COMPOUND_EVENT_END = 3;

    /**
     * The feature id for the '<em><b>Semantic End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOUND_EVENT_END__SEMANTIC_END = OrderingPackage.EVENT_END__SEMANTIC_END;

    /**
     * The feature id for the '<em><b>Event Ends</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOUND_EVENT_END__EVENT_ENDS = OrderingPackage.EVENT_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Compound Event End</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOUND_EVENT_END_FEATURE_COUNT = OrderingPackage.EVENT_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.InstanceRolesOrderingImpl
     * <em>Instance Roles Ordering</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.InstanceRolesOrderingImpl
     * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getInstanceRolesOrdering()
     * @generated
     */
    int INSTANCE_ROLES_ORDERING = 4;

    /**
     * The feature id for the '<em><b>Semantic Instance Roles</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES = 0;

    /**
     * The number of structural features of the '
     * <em>Instance Roles Ordering</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLES_ORDERING_FEATURE_COUNT = 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering
     * <em>Event Ends Ordering</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Event Ends Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering
     * @generated
     */
    EClass getEventEndsOrdering();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering#getSequenceDiagram
     * <em>Sequence Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Sequence Diagram</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering#getSequenceDiagram()
     * @see #getEventEndsOrdering()
     * @generated
     */
    EReference getEventEndsOrdering_SequenceDiagram();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering#getEventEnds
     * <em>Event Ends</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Event Ends</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering#getEventEnds()
     * @see #getEventEndsOrdering()
     * @generated
     */
    EReference getEventEndsOrdering_EventEnds();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.EventEnd
     * <em>Event End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Event End</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.EventEnd
     * @generated
     */
    EClass getEventEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.EventEnd#getSemanticEnd
     * <em>Semantic End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Semantic End</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.EventEnd#getSemanticEnd()
     * @see #getEventEnd()
     * @generated
     */
    EReference getEventEnd_SemanticEnd();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd
     * <em>Single Event End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Single Event End</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd
     * @generated
     */
    EClass getSingleEventEnd();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#isStart
     * <em>Start</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Start</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#isStart()
     * @see #getSingleEventEnd()
     * @generated
     */
    EAttribute getSingleEventEnd_Start();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#getSemanticEvent
     * <em>Semantic Event</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Semantic Event</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#getSemanticEvent()
     * @see #getSingleEventEnd()
     * @generated
     */
    EReference getSingleEventEnd_SemanticEvent();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd
     * <em>Compound Event End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Compound Event End</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd
     * @generated
     */
    EClass getCompoundEventEnd();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd#getEventEnds
     * <em>Event Ends</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Event Ends</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd#getEventEnds()
     * @see #getCompoundEventEnd()
     * @generated
     */
    EReference getCompoundEventEnd_EventEnds();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering
     * <em>Instance Roles Ordering</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Instance Roles Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering
     * @generated
     */
    EClass getInstanceRolesOrdering();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering#getSemanticInstanceRoles
     * <em>Semantic Instance Roles</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Semantic Instance Roles</em>'.
     * @see org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering#getSemanticInstanceRoles()
     * @see #getInstanceRolesOrdering()
     * @generated
     */
    EReference getInstanceRolesOrdering_SemanticInstanceRoles();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    OrderingFactory getOrderingFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
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
         * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndsOrderingImpl
         * <em>Event Ends Ordering</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndsOrderingImpl
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getEventEndsOrdering()
         * @generated
         */
        EClass EVENT_ENDS_ORDERING = OrderingPackage.eINSTANCE.getEventEndsOrdering();

        /**
         * The meta object literal for the '<em><b>Sequence Diagram</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM = OrderingPackage.eINSTANCE.getEventEndsOrdering_SequenceDiagram();

        /**
         * The meta object literal for the '<em><b>Event Ends</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EVENT_ENDS_ORDERING__EVENT_ENDS = OrderingPackage.eINSTANCE.getEventEndsOrdering_EventEnds();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndImpl
         * <em>Event End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndImpl
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getEventEnd()
         * @generated
         */
        EClass EVENT_END = OrderingPackage.eINSTANCE.getEventEnd();

        /**
         * The meta object literal for the '<em><b>Semantic End</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EVENT_END__SEMANTIC_END = OrderingPackage.eINSTANCE.getEventEnd_SemanticEnd();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.SingleEventEndImpl
         * <em>Single Event End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.SingleEventEndImpl
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getSingleEventEnd()
         * @generated
         */
        EClass SINGLE_EVENT_END = OrderingPackage.eINSTANCE.getSingleEventEnd();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SINGLE_EVENT_END__START = OrderingPackage.eINSTANCE.getSingleEventEnd_Start();

        /**
         * The meta object literal for the '<em><b>Semantic Event</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SINGLE_EVENT_END__SEMANTIC_EVENT = OrderingPackage.eINSTANCE.getSingleEventEnd_SemanticEvent();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.CompoundEventEndImpl
         * <em>Compound Event End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.CompoundEventEndImpl
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getCompoundEventEnd()
         * @generated
         */
        EClass COMPOUND_EVENT_END = OrderingPackage.eINSTANCE.getCompoundEventEnd();

        /**
         * The meta object literal for the '<em><b>Event Ends</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference COMPOUND_EVENT_END__EVENT_ENDS = OrderingPackage.eINSTANCE.getCompoundEventEnd_EventEnds();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.InstanceRolesOrderingImpl
         * <em>Instance Roles Ordering</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.InstanceRolesOrderingImpl
         * @see org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl#getInstanceRolesOrdering()
         * @generated
         */
        EClass INSTANCE_ROLES_ORDERING = OrderingPackage.eINSTANCE.getInstanceRolesOrdering();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Instance Roles</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES = OrderingPackage.eINSTANCE.getInstanceRolesOrdering_SemanticInstanceRoles();

    }

} // OrderingPackage
