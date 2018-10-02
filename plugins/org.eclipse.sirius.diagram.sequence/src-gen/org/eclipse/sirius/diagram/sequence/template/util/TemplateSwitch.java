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
package org.eclipse.sirius.diagram.sequence.template.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.template.TAbstractMapping;
import org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TExecutionMapping;
import org.eclipse.sirius.diagram.sequence.template.TExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TLifelineMapping;
import org.eclipse.sirius.diagram.sequence.template.TLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TMessageExtremity;
import org.eclipse.sirius.diagram.sequence.template.TMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;
import org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TTransformer;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage
 * @generated
 */
public class TemplateSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static TemplatePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TemplateSwitch() {
        if (TemplateSwitch.modelPackage == null) {
            TemplateSwitch.modelPackage = TemplatePackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == TemplateSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case TemplatePackage.TTRANSFORMER: {
            TTransformer tTransformer = (TTransformer) theEObject;
            T result = caseTTransformer(tTransformer);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TABSTRACT_MAPPING: {
            TAbstractMapping tAbstractMapping = (TAbstractMapping) theEObject;
            T result = caseTAbstractMapping(tAbstractMapping);
            if (result == null) {
                result = caseTTransformer(tAbstractMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TSEQUENCE_DIAGRAM: {
            TSequenceDiagram tSequenceDiagram = (TSequenceDiagram) theEObject;
            T result = caseTSequenceDiagram(tSequenceDiagram);
            if (result == null) {
                result = caseRepresentationTemplate(tSequenceDiagram);
            }
            if (result == null) {
                result = caseTTransformer(tSequenceDiagram);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TMESSAGE_EXTREMITY: {
            TMessageExtremity tMessageExtremity = (TMessageExtremity) theEObject;
            T result = caseTMessageExtremity(tMessageExtremity);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TLIFELINE_MAPPING: {
            TLifelineMapping tLifelineMapping = (TLifelineMapping) theEObject;
            T result = caseTLifelineMapping(tLifelineMapping);
            if (result == null) {
                result = caseTAbstractMapping(tLifelineMapping);
            }
            if (result == null) {
                result = caseTMessageExtremity(tLifelineMapping);
            }
            if (result == null) {
                result = caseTTransformer(tLifelineMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TLIFELINE_STYLE: {
            TLifelineStyle tLifelineStyle = (TLifelineStyle) theEObject;
            T result = caseTLifelineStyle(tLifelineStyle);
            if (result == null) {
                result = caseTTransformer(tLifelineStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE: {
            TConditionalLifelineStyle tConditionalLifelineStyle = (TConditionalLifelineStyle) theEObject;
            T result = caseTConditionalLifelineStyle(tConditionalLifelineStyle);
            if (result == null) {
                result = caseTTransformer(tConditionalLifelineStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TEXECUTION_MAPPING: {
            TExecutionMapping tExecutionMapping = (TExecutionMapping) theEObject;
            T result = caseTExecutionMapping(tExecutionMapping);
            if (result == null) {
                result = caseTAbstractMapping(tExecutionMapping);
            }
            if (result == null) {
                result = caseTMessageExtremity(tExecutionMapping);
            }
            if (result == null) {
                result = caseTTransformer(tExecutionMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TEXECUTION_STYLE: {
            TExecutionStyle tExecutionStyle = (TExecutionStyle) theEObject;
            T result = caseTExecutionStyle(tExecutionStyle);
            if (result == null) {
                result = caseTTransformer(tExecutionStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TCONDITIONAL_EXECUTION_STYLE: {
            TConditionalExecutionStyle tConditionalExecutionStyle = (TConditionalExecutionStyle) theEObject;
            T result = caseTConditionalExecutionStyle(tConditionalExecutionStyle);
            if (result == null) {
                result = caseTTransformer(tConditionalExecutionStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TMESSAGE_MAPPING: {
            TMessageMapping tMessageMapping = (TMessageMapping) theEObject;
            T result = caseTMessageMapping(tMessageMapping);
            if (result == null) {
                result = caseTAbstractMapping(tMessageMapping);
            }
            if (result == null) {
                result = caseTTransformer(tMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TMESSAGE_STYLE: {
            TMessageStyle tMessageStyle = (TMessageStyle) theEObject;
            T result = caseTMessageStyle(tMessageStyle);
            if (result == null) {
                result = caseTTransformer(tMessageStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TCONDITIONAL_MESSAGE_STYLE: {
            TConditionalMessageStyle tConditionalMessageStyle = (TConditionalMessageStyle) theEObject;
            T result = caseTConditionalMessageStyle(tConditionalMessageStyle);
            if (result == null) {
                result = caseTTransformer(tConditionalMessageStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TBASIC_MESSAGE_MAPPING: {
            TBasicMessageMapping tBasicMessageMapping = (TBasicMessageMapping) theEObject;
            T result = caseTBasicMessageMapping(tBasicMessageMapping);
            if (result == null) {
                result = caseTSourceTargetMessageMapping(tBasicMessageMapping);
            }
            if (result == null) {
                result = caseTMessageMapping(tBasicMessageMapping);
            }
            if (result == null) {
                result = caseTAbstractMapping(tBasicMessageMapping);
            }
            if (result == null) {
                result = caseTTransformer(tBasicMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TSOURCE_TARGET_MESSAGE_MAPPING: {
            TSourceTargetMessageMapping tSourceTargetMessageMapping = (TSourceTargetMessageMapping) theEObject;
            T result = caseTSourceTargetMessageMapping(tSourceTargetMessageMapping);
            if (result == null) {
                result = caseTMessageMapping(tSourceTargetMessageMapping);
            }
            if (result == null) {
                result = caseTAbstractMapping(tSourceTargetMessageMapping);
            }
            if (result == null) {
                result = caseTTransformer(tSourceTargetMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TRETURN_MESSAGE_MAPPING: {
            TReturnMessageMapping tReturnMessageMapping = (TReturnMessageMapping) theEObject;
            T result = caseTReturnMessageMapping(tReturnMessageMapping);
            if (result == null) {
                result = caseTMessageMapping(tReturnMessageMapping);
            }
            if (result == null) {
                result = caseTAbstractMapping(tReturnMessageMapping);
            }
            if (result == null) {
                result = caseTTransformer(tReturnMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TCREATION_MESSAGE_MAPPING: {
            TCreationMessageMapping tCreationMessageMapping = (TCreationMessageMapping) theEObject;
            T result = caseTCreationMessageMapping(tCreationMessageMapping);
            if (result == null) {
                result = caseTSourceTargetMessageMapping(tCreationMessageMapping);
            }
            if (result == null) {
                result = caseTMessageMapping(tCreationMessageMapping);
            }
            if (result == null) {
                result = caseTAbstractMapping(tCreationMessageMapping);
            }
            if (result == null) {
                result = caseTTransformer(tCreationMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case TemplatePackage.TDESTRUCTION_MESSAGE_MAPPING: {
            TDestructionMessageMapping tDestructionMessageMapping = (TDestructionMessageMapping) theEObject;
            T result = caseTDestructionMessageMapping(tDestructionMessageMapping);
            if (result == null) {
                result = caseTSourceTargetMessageMapping(tDestructionMessageMapping);
            }
            if (result == null) {
                result = caseTMessageMapping(tDestructionMessageMapping);
            }
            if (result == null) {
                result = caseTAbstractMapping(tDestructionMessageMapping);
            }
            if (result == null) {
                result = caseTTransformer(tDestructionMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TSequence Diagram</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TSequence Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTSequenceDiagram(TSequenceDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TMessage Extremity</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TMessage Extremity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTMessageExtremity(TMessageExtremity object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TLifeline Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TLifeline Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTLifelineMapping(TLifelineMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TLifeline Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TLifeline Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTLifelineStyle(TLifelineStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TConditional Lifeline Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TConditional Lifeline Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTConditionalLifelineStyle(TConditionalLifelineStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TTransformer</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TTransformer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTTransformer(TTransformer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TExecution Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TExecution Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTExecutionMapping(TExecutionMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TExecution Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TExecution Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTExecutionStyle(TExecutionStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TConditional Execution Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TConditional Execution Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTConditionalExecutionStyle(TConditionalExecutionStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TBasic Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TBasic Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTBasicMessageMapping(TBasicMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TSource Target Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TSource Target Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTSourceTargetMessageMapping(TSourceTargetMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TReturn Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TReturn Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTReturnMessageMapping(TReturnMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TCreation Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TCreation Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTCreationMessageMapping(TCreationMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TDestruction Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TDestruction Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTDestructionMessageMapping(TDestructionMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TAbstract Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TAbstract Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTAbstractMapping(TAbstractMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TMessage Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TMessage Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTMessageMapping(TMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TMessage Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TMessage Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTMessageStyle(TMessageStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>TConditional Message Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>TConditional Message Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTConditionalMessageStyle(TConditionalMessageStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Template</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Template</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationTemplate(RepresentationTemplate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // TemplateSwitch
