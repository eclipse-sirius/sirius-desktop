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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage
 * @generated
 */
public class TemplateAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static TemplatePackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TemplateAdapterFactory() {
        if (TemplateAdapterFactory.modelPackage == null) {
            TemplateAdapterFactory.modelPackage = TemplatePackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == TemplateAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == TemplateAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TemplateSwitch<Adapter> modelSwitch = new TemplateSwitch<Adapter>() {
        @Override
        public Adapter caseTTransformer(TTransformer object) {
            return createTTransformerAdapter();
        }

        @Override
        public Adapter caseTAbstractMapping(TAbstractMapping object) {
            return createTAbstractMappingAdapter();
        }

        @Override
        public Adapter caseTSequenceDiagram(TSequenceDiagram object) {
            return createTSequenceDiagramAdapter();
        }

        @Override
        public Adapter caseTMessageExtremity(TMessageExtremity object) {
            return createTMessageExtremityAdapter();
        }

        @Override
        public Adapter caseTLifelineMapping(TLifelineMapping object) {
            return createTLifelineMappingAdapter();
        }

        @Override
        public Adapter caseTLifelineStyle(TLifelineStyle object) {
            return createTLifelineStyleAdapter();
        }

        @Override
        public Adapter caseTConditionalLifelineStyle(TConditionalLifelineStyle object) {
            return createTConditionalLifelineStyleAdapter();
        }

        @Override
        public Adapter caseTExecutionMapping(TExecutionMapping object) {
            return createTExecutionMappingAdapter();
        }

        @Override
        public Adapter caseTExecutionStyle(TExecutionStyle object) {
            return createTExecutionStyleAdapter();
        }

        @Override
        public Adapter caseTConditionalExecutionStyle(TConditionalExecutionStyle object) {
            return createTConditionalExecutionStyleAdapter();
        }

        @Override
        public Adapter caseTMessageMapping(TMessageMapping object) {
            return createTMessageMappingAdapter();
        }

        @Override
        public Adapter caseTMessageStyle(TMessageStyle object) {
            return createTMessageStyleAdapter();
        }

        @Override
        public Adapter caseTConditionalMessageStyle(TConditionalMessageStyle object) {
            return createTConditionalMessageStyleAdapter();
        }

        @Override
        public Adapter caseTBasicMessageMapping(TBasicMessageMapping object) {
            return createTBasicMessageMappingAdapter();
        }

        @Override
        public Adapter caseTSourceTargetMessageMapping(TSourceTargetMessageMapping object) {
            return createTSourceTargetMessageMappingAdapter();
        }

        @Override
        public Adapter caseTReturnMessageMapping(TReturnMessageMapping object) {
            return createTReturnMessageMappingAdapter();
        }

        @Override
        public Adapter caseTCreationMessageMapping(TCreationMessageMapping object) {
            return createTCreationMessageMappingAdapter();
        }

        @Override
        public Adapter caseTDestructionMessageMapping(TDestructionMessageMapping object) {
            return createTDestructionMessageMappingAdapter();
        }

        @Override
        public Adapter caseRepresentationTemplate(RepresentationTemplate object) {
            return createRepresentationTemplateAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram <em>TSequence Diagram</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram
     * @generated
     */
    public Adapter createTSequenceDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageExtremity <em>TMessage Extremity</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageExtremity
     * @generated
     */
    public Adapter createTMessageExtremityAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TLifelineMapping <em>TLifeline Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineMapping
     * @generated
     */
    public Adapter createTLifelineMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.template.TLifelineStyle
     * <em>TLifeline Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TLifelineStyle
     * @generated
     */
    public Adapter createTLifelineStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle <em>TConditional Lifeline
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalLifelineStyle
     * @generated
     */
    public Adapter createTConditionalLifelineStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.template.TTransformer
     * <em>TTransformer</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TTransformer
     * @generated
     */
    public Adapter createTTransformerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TExecutionMapping <em>TExecution Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionMapping
     * @generated
     */
    public Adapter createTExecutionMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.template.TExecutionStyle
     * <em>TExecution Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TExecutionStyle
     * @generated
     */
    public Adapter createTExecutionStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle <em>TConditional Execution
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalExecutionStyle
     * @generated
     */
    public Adapter createTConditionalExecutionStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping <em>TBasic Message Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping
     * @generated
     */
    public Adapter createTBasicMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping <em>TSource Target Message
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TSourceTargetMessageMapping
     * @generated
     */
    public Adapter createTSourceTargetMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping <em>TReturn Message Mapping</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping
     * @generated
     */
    public Adapter createTReturnMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping <em>TCreation Message
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TCreationMessageMapping
     * @generated
     */
    public Adapter createTCreationMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping <em>TDestruction Message
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TDestructionMessageMapping
     * @generated
     */
    public Adapter createTDestructionMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.template.TAbstractMapping <em>TAbstract Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TAbstractMapping
     * @generated
     */
    public Adapter createTAbstractMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.template.TMessageMapping
     * <em>TMessage Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageMapping
     * @generated
     */
    public Adapter createTMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle
     * <em>TMessage Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TMessageStyle
     * @generated
     */
    public Adapter createTMessageStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle <em>TConditional Message
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.template.TConditionalMessageStyle
     * @generated
     */
    public Adapter createTConditionalMessageStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationTemplate <em>Representation Template</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationTemplate
     * @generated
     */
    public Adapter createRepresentationTemplateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // TemplateAdapterFactory
