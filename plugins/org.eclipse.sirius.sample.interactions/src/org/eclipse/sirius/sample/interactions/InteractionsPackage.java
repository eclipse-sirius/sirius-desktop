/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions;

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
 * @see org.eclipse.sirius.sample.interactions.InteractionsFactory
 * @model kind="package"
 * @generated
 */
public interface InteractionsPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "interactions"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/sample/interactions"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "interactions"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    InteractionsPackage eINSTANCE = org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.ModelImpl
     * <em>Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.ModelImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getModel()
     * @generated
     */
    int MODEL = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MODEL__NAME = 0;

    /**
     * The feature id for the '<em><b>Owned Interactions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MODEL__OWNED_INTERACTIONS = 1;

    /**
     * The number of structural features of the '<em>Model</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MODEL_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl
     * <em>Interaction</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getInteraction()
     * @generated
     */
    int INTERACTION = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__NAME = 0;

    /**
     * The feature id for the '<em><b>Participants</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__PARTICIPANTS = 1;

    /**
     * The feature id for the '<em><b>Messages</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__MESSAGES = 2;

    /**
     * The feature id for the '<em><b>Executions</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__EXECUTIONS = 3;

    /**
     * The feature id for the '<em><b>States</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__STATES = 4;

    /**
     * The feature id for the '<em><b>Interaction Uses</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__INTERACTION_USES = 5;

    /**
     * The feature id for the '<em><b>Combined Fragments</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__COMBINED_FRAGMENTS = 6;

    /**
     * The feature id for the '<em><b>Ends</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__ENDS = 7;

    /**
     * The feature id for the '<em><b>Constraints</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION__CONSTRAINTS = 8;

    /**
     * The number of structural features of the '<em>Interaction</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.ParticipantImpl
     * <em>Participant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.ParticipantImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getParticipant()
     * @generated
     */
    int PARTICIPANT = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARTICIPANT__NAME = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARTICIPANT__TYPE = 1;

    /**
     * The number of structural features of the '<em>Participant</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARTICIPANT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.MessageImpl
     * <em>Message</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.MessageImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getMessage()
     * @generated
     */
    int MESSAGE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE__NAME = 0;

    /**
     * The feature id for the '<em><b>Sending End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE__SENDING_END = 1;

    /**
     * The feature id for the '<em><b>Receiving End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE__RECEIVING_END = 2;

    /**
     * The number of structural features of the '<em>Message</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.CallMessageImpl
     * <em>Call Message</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.CallMessageImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCallMessage()
     * @generated
     */
    int CALL_MESSAGE = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CALL_MESSAGE__NAME = InteractionsPackage.MESSAGE__NAME;

    /**
     * The feature id for the '<em><b>Sending End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CALL_MESSAGE__SENDING_END = InteractionsPackage.MESSAGE__SENDING_END;

    /**
     * The feature id for the '<em><b>Receiving End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CALL_MESSAGE__RECEIVING_END = InteractionsPackage.MESSAGE__RECEIVING_END;

    /**
     * The feature id for the '<em><b>Operation</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CALL_MESSAGE__OPERATION = InteractionsPackage.MESSAGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Call Message</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CALL_MESSAGE_FEATURE_COUNT = InteractionsPackage.MESSAGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.FeatureAccessMessageImpl
     * <em>Feature Access Message</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.FeatureAccessMessageImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getFeatureAccessMessage()
     * @generated
     */
    int FEATURE_ACCESS_MESSAGE = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_ACCESS_MESSAGE__NAME = InteractionsPackage.MESSAGE__NAME;

    /**
     * The feature id for the '<em><b>Sending End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_ACCESS_MESSAGE__SENDING_END = InteractionsPackage.MESSAGE__SENDING_END;

    /**
     * The feature id for the '<em><b>Receiving End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_ACCESS_MESSAGE__RECEIVING_END = InteractionsPackage.MESSAGE__RECEIVING_END;

    /**
     * The feature id for the '<em><b>Is Write</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_ACCESS_MESSAGE__IS_WRITE = InteractionsPackage.MESSAGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_ACCESS_MESSAGE__FEATURE = InteractionsPackage.MESSAGE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Feature Access Message</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_ACCESS_MESSAGE_FEATURE_COUNT = InteractionsPackage.MESSAGE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.CreateParticipantMessageImpl
     * <em>Create Participant Message</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.CreateParticipantMessageImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCreateParticipantMessage()
     * @generated
     */
    int CREATE_PARTICIPANT_MESSAGE = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_PARTICIPANT_MESSAGE__NAME = InteractionsPackage.MESSAGE__NAME;

    /**
     * The feature id for the '<em><b>Sending End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_PARTICIPANT_MESSAGE__SENDING_END = InteractionsPackage.MESSAGE__SENDING_END;

    /**
     * The feature id for the '<em><b>Receiving End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_PARTICIPANT_MESSAGE__RECEIVING_END = InteractionsPackage.MESSAGE__RECEIVING_END;

    /**
     * The number of structural features of the '
     * <em>Create Participant Message</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_PARTICIPANT_MESSAGE_FEATURE_COUNT = InteractionsPackage.MESSAGE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.DestroyParticipantMessageImpl
     * <em>Destroy Participant Message</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.DestroyParticipantMessageImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getDestroyParticipantMessage()
     * @generated
     */
    int DESTROY_PARTICIPANT_MESSAGE = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTROY_PARTICIPANT_MESSAGE__NAME = InteractionsPackage.MESSAGE__NAME;

    /**
     * The feature id for the '<em><b>Sending End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTROY_PARTICIPANT_MESSAGE__SENDING_END = InteractionsPackage.MESSAGE__SENDING_END;

    /**
     * The feature id for the '<em><b>Receiving End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTROY_PARTICIPANT_MESSAGE__RECEIVING_END = InteractionsPackage.MESSAGE__RECEIVING_END;

    /**
     * The number of structural features of the '
     * <em>Destroy Participant Message</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTROY_PARTICIPANT_MESSAGE_FEATURE_COUNT = InteractionsPackage.MESSAGE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.ReturnMessageImpl
     * <em>Return Message</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.ReturnMessageImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getReturnMessage()
     * @generated
     */
    int RETURN_MESSAGE = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE__NAME = InteractionsPackage.MESSAGE__NAME;

    /**
     * The feature id for the '<em><b>Sending End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE__SENDING_END = InteractionsPackage.MESSAGE__SENDING_END;

    /**
     * The feature id for the '<em><b>Receiving End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE__RECEIVING_END = InteractionsPackage.MESSAGE__RECEIVING_END;

    /**
     * The feature id for the '<em><b>Invocation Message</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE__INVOCATION_MESSAGE = InteractionsPackage.MESSAGE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Return Message</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_FEATURE_COUNT = InteractionsPackage.MESSAGE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.ExecutionImpl
     * <em>Execution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.ExecutionImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getExecution()
     * @generated
     */
    int EXECUTION = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION__NAME = 0;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION__OWNER = 1;

    /**
     * The feature id for the '<em><b>Start</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION__START = 2;

    /**
     * The feature id for the '<em><b>End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION__END = 3;

    /**
     * The number of structural features of the '<em>Execution</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.StateImpl
     * <em>State</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.StateImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getState()
     * @generated
     */
    int STATE = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE__NAME = 0;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE__OWNER = 1;

    /**
     * The feature id for the '<em><b>Start</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE__START = 2;

    /**
     * The feature id for the '<em><b>End</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE__END = 3;

    /**
     * The number of structural features of the '<em>State</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl
     * <em>Interaction Use</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getInteractionUse()
     * @generated
     */
    int INTERACTION_USE = 11;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE__TYPE = 0;

    /**
     * The feature id for the '<em><b>Interaction</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE__INTERACTION = 1;

    /**
     * The feature id for the '<em><b>Covered Participants</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE__COVERED_PARTICIPANTS = 2;

    /**
     * The feature id for the '<em><b>Start</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE__START = 3;

    /**
     * The feature id for the '<em><b>Finish</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE__FINISH = 4;

    /**
     * The number of structural features of the '<em>Interaction Use</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl
     * <em>Combined Fragment</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCombinedFragment()
     * @generated
     */
    int COMBINED_FRAGMENT = 12;

    /**
     * The feature id for the '<em><b>Operator</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__OPERATOR = 0;

    /**
     * The feature id for the '<em><b>Covered Participants</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__COVERED_PARTICIPANTS = 1;

    /**
     * The feature id for the '<em><b>Start</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__START = 2;

    /**
     * The feature id for the '<em><b>Finish</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__FINISH = 3;

    /**
     * The feature id for the '<em><b>Owned Operands</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT__OWNED_OPERANDS = 4;

    /**
     * The number of structural features of the '<em>Combined Fragment</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.OperandImpl
     * <em>Operand</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.OperandImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getOperand()
     * @generated
     */
    int OPERAND = 13;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND__NAME = 0;

    /**
     * The feature id for the '<em><b>Start</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND__START = 1;

    /**
     * The number of structural features of the '<em>Operand</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.AbstractEndImpl
     * <em>Abstract End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.AbstractEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getAbstractEnd()
     * @generated
     */
    int ABSTRACT_END = 14;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_END__NAME = 0;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_END__CONTEXT = 1;

    /**
     * The number of structural features of the '<em>Abstract End</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_END_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.MessageEndImpl
     * <em>Message End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.MessageEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getMessageEnd()
     * @generated
     */
    int MESSAGE_END = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_END__NAME = InteractionsPackage.ABSTRACT_END__NAME;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_END__CONTEXT = InteractionsPackage.ABSTRACT_END__CONTEXT;

    /**
     * The feature id for the '<em><b>Message</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_END__MESSAGE = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Message End</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_END_FEATURE_COUNT = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.ExecutionEndImpl
     * <em>Execution End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.ExecutionEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getExecutionEnd()
     * @generated
     */
    int EXECUTION_END = 16;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_END__NAME = InteractionsPackage.ABSTRACT_END__NAME;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_END__CONTEXT = InteractionsPackage.ABSTRACT_END__CONTEXT;

    /**
     * The feature id for the '<em><b>Execution</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_END__EXECUTION = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Execution End</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_END_FEATURE_COUNT = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.StateEndImpl
     * <em>State End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.StateEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getStateEnd()
     * @generated
     */
    int STATE_END = 17;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_END__NAME = InteractionsPackage.ABSTRACT_END__NAME;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_END__CONTEXT = InteractionsPackage.ABSTRACT_END__CONTEXT;

    /**
     * The feature id for the '<em><b>State</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_END__STATE = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>State End</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_END_FEATURE_COUNT = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseEndImpl
     * <em>Interaction Use End</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionUseEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getInteractionUseEnd()
     * @generated
     */
    int INTERACTION_USE_END = 18;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_END__NAME = InteractionsPackage.ABSTRACT_END__NAME;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_END__CONTEXT = InteractionsPackage.ABSTRACT_END__CONTEXT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_END__OWNER = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Interaction Use End</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_END_FEATURE_COUNT = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentEndImpl
     * <em>Combined Fragment End</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.CombinedFragmentEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCombinedFragmentEnd()
     * @generated
     */
    int COMBINED_FRAGMENT_END = 19;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_END__NAME = InteractionsPackage.ABSTRACT_END__NAME;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_END__CONTEXT = InteractionsPackage.ABSTRACT_END__CONTEXT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_END__OWNER = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Combined Fragment End</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_END_FEATURE_COUNT = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.OperandEndImpl
     * <em>Operand End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.OperandEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getOperandEnd()
     * @generated
     */
    int OPERAND_END = 20;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_END__NAME = InteractionsPackage.ABSTRACT_END__NAME;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_END__CONTEXT = InteractionsPackage.ABSTRACT_END__CONTEXT;

    /**
     * The feature id for the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_END__OWNER = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Operand End</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_END_FEATURE_COUNT = InteractionsPackage.ABSTRACT_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.MixEndImpl
     * <em>Mix End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.MixEndImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getMixEnd()
     * @generated
     */
    int MIX_END = 21;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MIX_END__NAME = InteractionsPackage.EXECUTION_END__NAME;

    /**
     * The feature id for the '<em><b>Context</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MIX_END__CONTEXT = InteractionsPackage.EXECUTION_END__CONTEXT;

    /**
     * The feature id for the '<em><b>Execution</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MIX_END__EXECUTION = InteractionsPackage.EXECUTION_END__EXECUTION;

    /**
     * The feature id for the '<em><b>Message</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MIX_END__MESSAGE = InteractionsPackage.EXECUTION_END_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Mix End</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MIX_END_FEATURE_COUNT = InteractionsPackage.EXECUTION_END_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.sample.interactions.impl.ConstraintImpl
     * <em>Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.sample.interactions.impl.ConstraintImpl
     * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getConstraint()
     * @generated
     */
    int CONSTRAINT = 22;

    /**
     * The feature id for the '<em><b>Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONSTRAINT__EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Constrained Ends</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONSTRAINT__CONSTRAINED_ENDS = 1;

    /**
     * The number of structural features of the '<em>Constraint</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONSTRAINT_FEATURE_COUNT = 2;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.Model <em>Model</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Model</em>'.
     * @see org.eclipse.sirius.sample.interactions.Model
     * @generated
     */
    EClass getModel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.Model#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.Model#getName()
     * @see #getModel()
     * @generated
     */
    EAttribute getModel_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Model#getOwnedInteractions
     * <em>Owned Interactions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Interactions</em>'.
     * @see org.eclipse.sirius.sample.interactions.Model#getOwnedInteractions()
     * @see #getModel()
     * @generated
     */
    EReference getModel_OwnedInteractions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.Interaction
     * <em>Interaction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Interaction</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction
     * @generated
     */
    EClass getInteraction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getName()
     * @see #getInteraction()
     * @generated
     */
    EAttribute getInteraction_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getParticipants
     * <em>Participants</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Participants</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getParticipants()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Participants();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getMessages
     * <em>Messages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Messages</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getMessages()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Messages();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getExecutions
     * <em>Executions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Executions</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getExecutions()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Executions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getStates
     * <em>States</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>States</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getStates()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_States();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getInteractionUses
     * <em>Interaction Uses</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Interaction Uses</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getInteractionUses()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_InteractionUses();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getCombinedFragments
     * <em>Combined Fragments</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Combined Fragments</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getCombinedFragments()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_CombinedFragments();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getEnds
     * <em>Ends</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Ends</em>
     *         '.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getEnds()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Ends();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.Interaction#getConstraints
     * <em>Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Constraints</em>'.
     * @see org.eclipse.sirius.sample.interactions.Interaction#getConstraints()
     * @see #getInteraction()
     * @generated
     */
    EReference getInteraction_Constraints();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.Participant
     * <em>Participant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Participant</em>'.
     * @see org.eclipse.sirius.sample.interactions.Participant
     * @generated
     */
    EClass getParticipant();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.Participant#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.Participant#getName()
     * @see #getParticipant()
     * @generated
     */
    EAttribute getParticipant_Name();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.Participant#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Type</em>'.
     * @see org.eclipse.sirius.sample.interactions.Participant#getType()
     * @see #getParticipant()
     * @generated
     */
    EReference getParticipant_Type();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.Message <em>Message</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.Message
     * @generated
     */
    EClass getMessage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.Message#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.Message#getName()
     * @see #getMessage()
     * @generated
     */
    EAttribute getMessage_Name();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.Message#getSendingEnd
     * <em>Sending End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Sending End</em>'.
     * @see org.eclipse.sirius.sample.interactions.Message#getSendingEnd()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_SendingEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.Message#getReceivingEnd
     * <em>Receiving End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Receiving End</em>'.
     * @see org.eclipse.sirius.sample.interactions.Message#getReceivingEnd()
     * @see #getMessage()
     * @generated
     */
    EReference getMessage_ReceivingEnd();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.CallMessage
     * <em>Call Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Call Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.CallMessage
     * @generated
     */
    EClass getCallMessage();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.CallMessage#getOperation
     * <em>Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Operation</em>'.
     * @see org.eclipse.sirius.sample.interactions.CallMessage#getOperation()
     * @see #getCallMessage()
     * @generated
     */
    EReference getCallMessage_Operation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage
     * <em>Feature Access Message</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Feature Access Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.FeatureAccessMessage
     * @generated
     */
    EClass getFeatureAccessMessage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#isIsWrite
     * <em>Is Write</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Write</em>'.
     * @see org.eclipse.sirius.sample.interactions.FeatureAccessMessage#isIsWrite()
     * @see #getFeatureAccessMessage()
     * @generated
     */
    EAttribute getFeatureAccessMessage_IsWrite();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#getFeature
     * <em>Feature</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Feature</em>'.
     * @see org.eclipse.sirius.sample.interactions.FeatureAccessMessage#getFeature()
     * @see #getFeatureAccessMessage()
     * @generated
     */
    EReference getFeatureAccessMessage_Feature();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.CreateParticipantMessage
     * <em>Create Participant Message</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Create Participant Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.CreateParticipantMessage
     * @generated
     */
    EClass getCreateParticipantMessage();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.DestroyParticipantMessage
     * <em>Destroy Participant Message</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Destroy Participant Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.DestroyParticipantMessage
     * @generated
     */
    EClass getDestroyParticipantMessage();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.ReturnMessage
     * <em>Return Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Return Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.ReturnMessage
     * @generated
     */
    EClass getReturnMessage();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.ReturnMessage#getInvocationMessage
     * <em>Invocation Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Invocation Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.ReturnMessage#getInvocationMessage()
     * @see #getReturnMessage()
     * @generated
     */
    EReference getReturnMessage_InvocationMessage();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.Execution
     * <em>Execution</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Execution</em>'.
     * @see org.eclipse.sirius.sample.interactions.Execution
     * @generated
     */
    EClass getExecution();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.Execution#getName()
     * @see #getExecution()
     * @generated
     */
    EAttribute getExecution_Name();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getOwner
     * <em>Owner</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Owner</em>'.
     * @see org.eclipse.sirius.sample.interactions.Execution#getOwner()
     * @see #getExecution()
     * @generated
     */
    EReference getExecution_Owner();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getStart
     * <em>Start</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Start</em>'.
     * @see org.eclipse.sirius.sample.interactions.Execution#getStart()
     * @see #getExecution()
     * @generated
     */
    EReference getExecution_Start();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getEnd
     * <em>End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>End</em>'.
     * @see org.eclipse.sirius.sample.interactions.Execution#getEnd()
     * @see #getExecution()
     * @generated
     */
    EReference getExecution_End();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.State <em>State</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>State</em>'.
     * @see org.eclipse.sirius.sample.interactions.State
     * @generated
     */
    EClass getState();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.State#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.State#getName()
     * @see #getState()
     * @generated
     */
    EAttribute getState_Name();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.State#getOwner
     * <em>Owner</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Owner</em>'.
     * @see org.eclipse.sirius.sample.interactions.State#getOwner()
     * @see #getState()
     * @generated
     */
    EReference getState_Owner();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.State#getStart
     * <em>Start</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Start</em>'.
     * @see org.eclipse.sirius.sample.interactions.State#getStart()
     * @see #getState()
     * @generated
     */
    EReference getState_Start();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.State#getEnd <em>End</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>End</em>'.
     * @see org.eclipse.sirius.sample.interactions.State#getEnd()
     * @see #getState()
     * @generated
     */
    EReference getState_End();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse
     * <em>Interaction Use</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Interaction Use</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUse
     * @generated
     */
    EClass getInteractionUse();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUse#getType()
     * @see #getInteractionUse()
     * @generated
     */
    EAttribute getInteractionUse_Type();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getInteraction
     * <em>Interaction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Interaction</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUse#getInteraction()
     * @see #getInteractionUse()
     * @generated
     */
    EReference getInteractionUse_Interaction();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getCoveredParticipants
     * <em>Covered Participants</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the reference list '
     *         <em>Covered Participants</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUse#getCoveredParticipants()
     * @see #getInteractionUse()
     * @generated
     */
    EReference getInteractionUse_CoveredParticipants();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getStart
     * <em>Start</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Start</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUse#getStart()
     * @see #getInteractionUse()
     * @generated
     */
    EReference getInteractionUse_Start();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getFinish
     * <em>Finish</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Finish</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUse#getFinish()
     * @see #getInteractionUse()
     * @generated
     */
    EReference getInteractionUse_Finish();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment
     * <em>Combined Fragment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Combined Fragment</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragment
     * @generated
     */
    EClass getCombinedFragment();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getOperator
     * <em>Operator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Operator</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragment#getOperator()
     * @see #getCombinedFragment()
     * @generated
     */
    EAttribute getCombinedFragment_Operator();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getCoveredParticipants
     * <em>Covered Participants</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the reference list '
     *         <em>Covered Participants</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragment#getCoveredParticipants()
     * @see #getCombinedFragment()
     * @generated
     */
    EReference getCombinedFragment_CoveredParticipants();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getStart
     * <em>Start</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Start</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragment#getStart()
     * @see #getCombinedFragment()
     * @generated
     */
    EReference getCombinedFragment_Start();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getFinish
     * <em>Finish</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Finish</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragment#getFinish()
     * @see #getCombinedFragment()
     * @generated
     */
    EReference getCombinedFragment_Finish();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getOwnedOperands
     * <em>Owned Operands</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Operands</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragment#getOwnedOperands()
     * @see #getCombinedFragment()
     * @generated
     */
    EReference getCombinedFragment_OwnedOperands();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.Operand <em>Operand</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Operand</em>'.
     * @see org.eclipse.sirius.sample.interactions.Operand
     * @generated
     */
    EClass getOperand();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.Operand#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.Operand#getName()
     * @see #getOperand()
     * @generated
     */
    EAttribute getOperand_Name();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.Operand#getStart
     * <em>Start</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Start</em>'.
     * @see org.eclipse.sirius.sample.interactions.Operand#getStart()
     * @see #getOperand()
     * @generated
     */
    EReference getOperand_Start();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.AbstractEnd
     * <em>Abstract End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract End</em>'.
     * @see org.eclipse.sirius.sample.interactions.AbstractEnd
     * @generated
     */
    EClass getAbstractEnd();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.AbstractEnd#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.sample.interactions.AbstractEnd#getName()
     * @see #getAbstractEnd()
     * @generated
     */
    EAttribute getAbstractEnd_Name();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.AbstractEnd#getContext
     * <em>Context</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Context</em>'.
     * @see org.eclipse.sirius.sample.interactions.AbstractEnd#getContext()
     * @see #getAbstractEnd()
     * @generated
     */
    EReference getAbstractEnd_Context();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.MessageEnd
     * <em>Message End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Message End</em>'.
     * @see org.eclipse.sirius.sample.interactions.MessageEnd
     * @generated
     */
    EClass getMessageEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.MessageEnd#getMessage
     * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Message</em>'.
     * @see org.eclipse.sirius.sample.interactions.MessageEnd#getMessage()
     * @see #getMessageEnd()
     * @generated
     */
    EReference getMessageEnd_Message();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.ExecutionEnd
     * <em>Execution End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Execution End</em>'.
     * @see org.eclipse.sirius.sample.interactions.ExecutionEnd
     * @generated
     */
    EClass getExecutionEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.ExecutionEnd#getExecution
     * <em>Execution</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Execution</em>'.
     * @see org.eclipse.sirius.sample.interactions.ExecutionEnd#getExecution()
     * @see #getExecutionEnd()
     * @generated
     */
    EReference getExecutionEnd_Execution();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.StateEnd
     * <em>State End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>State End</em>'.
     * @see org.eclipse.sirius.sample.interactions.StateEnd
     * @generated
     */
    EClass getStateEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.StateEnd#getState
     * <em>State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>State</em>'.
     * @see org.eclipse.sirius.sample.interactions.StateEnd#getState()
     * @see #getStateEnd()
     * @generated
     */
    EReference getStateEnd_State();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUseEnd
     * <em>Interaction Use End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Interaction Use End</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUseEnd
     * @generated
     */
    EClass getInteractionUseEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUseEnd#getOwner
     * <em>Owner</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Owner</em>'.
     * @see org.eclipse.sirius.sample.interactions.InteractionUseEnd#getOwner()
     * @see #getInteractionUseEnd()
     * @generated
     */
    EReference getInteractionUseEnd_Owner();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragmentEnd
     * <em>Combined Fragment End</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Combined Fragment End</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragmentEnd
     * @generated
     */
    EClass getCombinedFragmentEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragmentEnd#getOwner
     * <em>Owner</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Owner</em>'.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragmentEnd#getOwner()
     * @see #getCombinedFragmentEnd()
     * @generated
     */
    EReference getCombinedFragmentEnd_Owner();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.OperandEnd
     * <em>Operand End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Operand End</em>'.
     * @see org.eclipse.sirius.sample.interactions.OperandEnd
     * @generated
     */
    EClass getOperandEnd();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.sample.interactions.OperandEnd#getOwner
     * <em>Owner</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Owner</em>'.
     * @see org.eclipse.sirius.sample.interactions.OperandEnd#getOwner()
     * @see #getOperandEnd()
     * @generated
     */
    EReference getOperandEnd_Owner();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.MixEnd <em>Mix End</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Mix End</em>'.
     * @see org.eclipse.sirius.sample.interactions.MixEnd
     * @generated
     */
    EClass getMixEnd();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.sample.interactions.Constraint
     * <em>Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Constraint</em>'.
     * @see org.eclipse.sirius.sample.interactions.Constraint
     * @generated
     */
    EClass getConstraint();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.sample.interactions.Constraint#getExpression
     * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expression</em>'.
     * @see org.eclipse.sirius.sample.interactions.Constraint#getExpression()
     * @see #getConstraint()
     * @generated
     */
    EAttribute getConstraint_Expression();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.sample.interactions.Constraint#getConstrainedEnds
     * <em>Constrained Ends</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '<em>Constrained Ends</em>
     *         '.
     * @see org.eclipse.sirius.sample.interactions.Constraint#getConstrainedEnds()
     * @see #getConstraint()
     * @generated
     */
    EReference getConstraint_ConstrainedEnds();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    InteractionsFactory getInteractionsFactory();

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
         * {@link org.eclipse.sirius.sample.interactions.impl.ModelImpl
         * <em>Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.ModelImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getModel()
         * @generated
         */
        EClass MODEL = InteractionsPackage.eINSTANCE.getModel();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute MODEL__NAME = InteractionsPackage.eINSTANCE.getModel_Name();

        /**
         * The meta object literal for the '<em><b>Owned Interactions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference MODEL__OWNED_INTERACTIONS = InteractionsPackage.eINSTANCE.getModel_OwnedInteractions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl
         * <em>Interaction</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getInteraction()
         * @generated
         */
        EClass INTERACTION = InteractionsPackage.eINSTANCE.getInteraction();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INTERACTION__NAME = InteractionsPackage.eINSTANCE.getInteraction_Name();

        /**
         * The meta object literal for the '<em><b>Participants</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__PARTICIPANTS = InteractionsPackage.eINSTANCE.getInteraction_Participants();

        /**
         * The meta object literal for the '<em><b>Messages</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__MESSAGES = InteractionsPackage.eINSTANCE.getInteraction_Messages();

        /**
         * The meta object literal for the '<em><b>Executions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__EXECUTIONS = InteractionsPackage.eINSTANCE.getInteraction_Executions();

        /**
         * The meta object literal for the '<em><b>States</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__STATES = InteractionsPackage.eINSTANCE.getInteraction_States();

        /**
         * The meta object literal for the '<em><b>Interaction Uses</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__INTERACTION_USES = InteractionsPackage.eINSTANCE.getInteraction_InteractionUses();

        /**
         * The meta object literal for the '<em><b>Combined Fragments</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__COMBINED_FRAGMENTS = InteractionsPackage.eINSTANCE.getInteraction_CombinedFragments();

        /**
         * The meta object literal for the '<em><b>Ends</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__ENDS = InteractionsPackage.eINSTANCE.getInteraction_Ends();

        /**
         * The meta object literal for the '<em><b>Constraints</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION__CONSTRAINTS = InteractionsPackage.eINSTANCE.getInteraction_Constraints();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.ParticipantImpl
         * <em>Participant</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.ParticipantImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getParticipant()
         * @generated
         */
        EClass PARTICIPANT = InteractionsPackage.eINSTANCE.getParticipant();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PARTICIPANT__NAME = InteractionsPackage.eINSTANCE.getParticipant_Name();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PARTICIPANT__TYPE = InteractionsPackage.eINSTANCE.getParticipant_Type();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.MessageImpl
         * <em>Message</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.MessageImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getMessage()
         * @generated
         */
        EClass MESSAGE = InteractionsPackage.eINSTANCE.getMessage();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute MESSAGE__NAME = InteractionsPackage.eINSTANCE.getMessage_Name();

        /**
         * The meta object literal for the '<em><b>Sending End</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MESSAGE__SENDING_END = InteractionsPackage.eINSTANCE.getMessage_SendingEnd();

        /**
         * The meta object literal for the '<em><b>Receiving End</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MESSAGE__RECEIVING_END = InteractionsPackage.eINSTANCE.getMessage_ReceivingEnd();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.CallMessageImpl
         * <em>Call Message</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.CallMessageImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCallMessage()
         * @generated
         */
        EClass CALL_MESSAGE = InteractionsPackage.eINSTANCE.getCallMessage();

        /**
         * The meta object literal for the '<em><b>Operation</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CALL_MESSAGE__OPERATION = InteractionsPackage.eINSTANCE.getCallMessage_Operation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.FeatureAccessMessageImpl
         * <em>Feature Access Message</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.FeatureAccessMessageImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getFeatureAccessMessage()
         * @generated
         */
        EClass FEATURE_ACCESS_MESSAGE = InteractionsPackage.eINSTANCE.getFeatureAccessMessage();

        /**
         * The meta object literal for the '<em><b>Is Write</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FEATURE_ACCESS_MESSAGE__IS_WRITE = InteractionsPackage.eINSTANCE.getFeatureAccessMessage_IsWrite();

        /**
         * The meta object literal for the '<em><b>Feature</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference FEATURE_ACCESS_MESSAGE__FEATURE = InteractionsPackage.eINSTANCE.getFeatureAccessMessage_Feature();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.CreateParticipantMessageImpl
         * <em>Create Participant Message</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.CreateParticipantMessageImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCreateParticipantMessage()
         * @generated
         */
        EClass CREATE_PARTICIPANT_MESSAGE = InteractionsPackage.eINSTANCE.getCreateParticipantMessage();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.DestroyParticipantMessageImpl
         * <em>Destroy Participant Message</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.DestroyParticipantMessageImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getDestroyParticipantMessage()
         * @generated
         */
        EClass DESTROY_PARTICIPANT_MESSAGE = InteractionsPackage.eINSTANCE.getDestroyParticipantMessage();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.ReturnMessageImpl
         * <em>Return Message</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.ReturnMessageImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getReturnMessage()
         * @generated
         */
        EClass RETURN_MESSAGE = InteractionsPackage.eINSTANCE.getReturnMessage();

        /**
         * The meta object literal for the '<em><b>Invocation Message</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RETURN_MESSAGE__INVOCATION_MESSAGE = InteractionsPackage.eINSTANCE.getReturnMessage_InvocationMessage();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.ExecutionImpl
         * <em>Execution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.ExecutionImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getExecution()
         * @generated
         */
        EClass EXECUTION = InteractionsPackage.eINSTANCE.getExecution();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EXECUTION__NAME = InteractionsPackage.eINSTANCE.getExecution_Name();

        /**
         * The meta object literal for the '<em><b>Owner</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EXECUTION__OWNER = InteractionsPackage.eINSTANCE.getExecution_Owner();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EXECUTION__START = InteractionsPackage.eINSTANCE.getExecution_Start();

        /**
         * The meta object literal for the '<em><b>End</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EXECUTION__END = InteractionsPackage.eINSTANCE.getExecution_End();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.StateImpl
         * <em>State</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.StateImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getState()
         * @generated
         */
        EClass STATE = InteractionsPackage.eINSTANCE.getState();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute STATE__NAME = InteractionsPackage.eINSTANCE.getState_Name();

        /**
         * The meta object literal for the '<em><b>Owner</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference STATE__OWNER = InteractionsPackage.eINSTANCE.getState_Owner();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference STATE__START = InteractionsPackage.eINSTANCE.getState_Start();

        /**
         * The meta object literal for the '<em><b>End</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference STATE__END = InteractionsPackage.eINSTANCE.getState_End();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl
         * <em>Interaction Use</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getInteractionUse()
         * @generated
         */
        EClass INTERACTION_USE = InteractionsPackage.eINSTANCE.getInteractionUse();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INTERACTION_USE__TYPE = InteractionsPackage.eINSTANCE.getInteractionUse_Type();

        /**
         * The meta object literal for the '<em><b>Interaction</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION_USE__INTERACTION = InteractionsPackage.eINSTANCE.getInteractionUse_Interaction();

        /**
         * The meta object literal for the '<em><b>Covered Participants</b></em>
         * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @generated
         */
        EReference INTERACTION_USE__COVERED_PARTICIPANTS = InteractionsPackage.eINSTANCE.getInteractionUse_CoveredParticipants();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION_USE__START = InteractionsPackage.eINSTANCE.getInteractionUse_Start();

        /**
         * The meta object literal for the '<em><b>Finish</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION_USE__FINISH = InteractionsPackage.eINSTANCE.getInteractionUse_Finish();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl
         * <em>Combined Fragment</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCombinedFragment()
         * @generated
         */
        EClass COMBINED_FRAGMENT = InteractionsPackage.eINSTANCE.getCombinedFragment();

        /**
         * The meta object literal for the '<em><b>Operator</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COMBINED_FRAGMENT__OPERATOR = InteractionsPackage.eINSTANCE.getCombinedFragment_Operator();

        /**
         * The meta object literal for the '<em><b>Covered Participants</b></em>
         * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @generated
         */
        EReference COMBINED_FRAGMENT__COVERED_PARTICIPANTS = InteractionsPackage.eINSTANCE.getCombinedFragment_CoveredParticipants();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMBINED_FRAGMENT__START = InteractionsPackage.eINSTANCE.getCombinedFragment_Start();

        /**
         * The meta object literal for the '<em><b>Finish</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMBINED_FRAGMENT__FINISH = InteractionsPackage.eINSTANCE.getCombinedFragment_Finish();

        /**
         * The meta object literal for the '<em><b>Owned Operands</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference COMBINED_FRAGMENT__OWNED_OPERANDS = InteractionsPackage.eINSTANCE.getCombinedFragment_OwnedOperands();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.OperandImpl
         * <em>Operand</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.OperandImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getOperand()
         * @generated
         */
        EClass OPERAND = InteractionsPackage.eINSTANCE.getOperand();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute OPERAND__NAME = InteractionsPackage.eINSTANCE.getOperand_Name();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference OPERAND__START = InteractionsPackage.eINSTANCE.getOperand_Start();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.AbstractEndImpl
         * <em>Abstract End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.AbstractEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getAbstractEnd()
         * @generated
         */
        EClass ABSTRACT_END = InteractionsPackage.eINSTANCE.getAbstractEnd();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_END__NAME = InteractionsPackage.eINSTANCE.getAbstractEnd_Name();

        /**
         * The meta object literal for the '<em><b>Context</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_END__CONTEXT = InteractionsPackage.eINSTANCE.getAbstractEnd_Context();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.MessageEndImpl
         * <em>Message End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.MessageEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getMessageEnd()
         * @generated
         */
        EClass MESSAGE_END = InteractionsPackage.eINSTANCE.getMessageEnd();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MESSAGE_END__MESSAGE = InteractionsPackage.eINSTANCE.getMessageEnd_Message();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.ExecutionEndImpl
         * <em>Execution End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.ExecutionEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getExecutionEnd()
         * @generated
         */
        EClass EXECUTION_END = InteractionsPackage.eINSTANCE.getExecutionEnd();

        /**
         * The meta object literal for the '<em><b>Execution</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EXECUTION_END__EXECUTION = InteractionsPackage.eINSTANCE.getExecutionEnd_Execution();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.StateEndImpl
         * <em>State End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.StateEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getStateEnd()
         * @generated
         */
        EClass STATE_END = InteractionsPackage.eINSTANCE.getStateEnd();

        /**
         * The meta object literal for the '<em><b>State</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference STATE_END__STATE = InteractionsPackage.eINSTANCE.getStateEnd_State();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseEndImpl
         * <em>Interaction Use End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionUseEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getInteractionUseEnd()
         * @generated
         */
        EClass INTERACTION_USE_END = InteractionsPackage.eINSTANCE.getInteractionUseEnd();

        /**
         * The meta object literal for the '<em><b>Owner</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INTERACTION_USE_END__OWNER = InteractionsPackage.eINSTANCE.getInteractionUseEnd_Owner();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentEndImpl
         * <em>Combined Fragment End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.CombinedFragmentEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getCombinedFragmentEnd()
         * @generated
         */
        EClass COMBINED_FRAGMENT_END = InteractionsPackage.eINSTANCE.getCombinedFragmentEnd();

        /**
         * The meta object literal for the '<em><b>Owner</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMBINED_FRAGMENT_END__OWNER = InteractionsPackage.eINSTANCE.getCombinedFragmentEnd_Owner();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.OperandEndImpl
         * <em>Operand End</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.OperandEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getOperandEnd()
         * @generated
         */
        EClass OPERAND_END = InteractionsPackage.eINSTANCE.getOperandEnd();

        /**
         * The meta object literal for the '<em><b>Owner</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference OPERAND_END__OWNER = InteractionsPackage.eINSTANCE.getOperandEnd_Owner();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.MixEndImpl
         * <em>Mix End</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.MixEndImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getMixEnd()
         * @generated
         */
        EClass MIX_END = InteractionsPackage.eINSTANCE.getMixEnd();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.sample.interactions.impl.ConstraintImpl
         * <em>Constraint</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.sample.interactions.impl.ConstraintImpl
         * @see org.eclipse.sirius.sample.interactions.impl.InteractionsPackageImpl#getConstraint()
         * @generated
         */
        EClass CONSTRAINT = InteractionsPackage.eINSTANCE.getConstraint();

        /**
         * The meta object literal for the '<em><b>Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CONSTRAINT__EXPRESSION = InteractionsPackage.eINSTANCE.getConstraint_Expression();

        /**
         * The meta object literal for the '<em><b>Constrained Ends</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONSTRAINT__CONSTRAINED_ENDS = InteractionsPackage.eINSTANCE.getConstraint_ConstrainedEnds();

    }

} // InteractionsPackage
