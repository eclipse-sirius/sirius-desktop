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
package org.eclipse.sirius.diagram.sequence.template.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.sequence.business.internal.color.DefaultColorStyleDescription;
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
import org.eclipse.sirius.diagram.sequence.template.TMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;
import org.eclipse.sirius.diagram.sequence.template.TTransformer;
import org.eclipse.sirius.diagram.sequence.template.TemplateFactory;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TemplateFactoryImpl extends EFactoryImpl implements TemplateFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TemplateFactory init() {
        try {
            TemplateFactory theTemplateFactory = (TemplateFactory) EPackage.Registry.INSTANCE.getEFactory(TemplatePackage.eNS_URI);
            if (theTemplateFactory != null) {
                return theTemplateFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TemplateFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public TemplateFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case TemplatePackage.TTRANSFORMER:
            return createTTransformer();
        case TemplatePackage.TABSTRACT_MAPPING:
            return createTAbstractMapping();
        case TemplatePackage.TSEQUENCE_DIAGRAM:
            return createTSequenceDiagram();
        case TemplatePackage.TLIFELINE_MAPPING:
            return createTLifelineMapping();
        case TemplatePackage.TLIFELINE_STYLE:
            return createTLifelineStyle();
        case TemplatePackage.TCONDITIONAL_LIFELINE_STYLE:
            return createTConditionalLifelineStyle();
        case TemplatePackage.TEXECUTION_MAPPING:
            return createTExecutionMapping();
        case TemplatePackage.TEXECUTION_STYLE:
            return createTExecutionStyle();
        case TemplatePackage.TCONDITIONAL_EXECUTION_STYLE:
            return createTConditionalExecutionStyle();
        case TemplatePackage.TMESSAGE_STYLE:
            return createTMessageStyle();
        case TemplatePackage.TCONDITIONAL_MESSAGE_STYLE:
            return createTConditionalMessageStyle();
        case TemplatePackage.TBASIC_MESSAGE_MAPPING:
            return createTBasicMessageMapping();
        case TemplatePackage.TRETURN_MESSAGE_MAPPING:
            return createTReturnMessageMapping();
        case TemplatePackage.TCREATION_MESSAGE_MAPPING:
            return createTCreationMessageMapping();
        case TemplatePackage.TDESTRUCTION_MESSAGE_MAPPING:
            return createTDestructionMessageMapping();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TSequenceDiagram createTSequenceDiagram() {
        TSequenceDiagramImpl tSequenceDiagram = new TSequenceDiagramImpl();
        return tSequenceDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TLifelineMapping createTLifelineMapping() {
        TLifelineMappingImpl tLifelineMapping = new TLifelineMappingImpl();
        return tLifelineMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TLifelineStyle createTLifelineStyle() {
        TLifelineStyleImpl tLifelineStyle = new TLifelineStyleImpl();
        new DefaultColorStyleDescription().setDefaultColors(tLifelineStyle);
        return tLifelineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TConditionalLifelineStyle createTConditionalLifelineStyle() {
        TConditionalLifelineStyleImpl tConditionalLifelineStyle = new TConditionalLifelineStyleImpl();
        return tConditionalLifelineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TTransformer createTTransformer() {
        TTransformerImpl tTransformer = new TTransformerImpl();
        return tTransformer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TExecutionMapping createTExecutionMapping() {
        TExecutionMappingImpl tExecutionMapping = new TExecutionMappingImpl();
        return tExecutionMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TExecutionStyle createTExecutionStyle() {
        TExecutionStyleImpl tExecutionStyle = new TExecutionStyleImpl();
        new DefaultColorStyleDescription().setDefaultColors(tExecutionStyle);
        return tExecutionStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TConditionalExecutionStyle createTConditionalExecutionStyle() {
        TConditionalExecutionStyleImpl tConditionalExecutionStyle = new TConditionalExecutionStyleImpl();
        return tConditionalExecutionStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public TMessageStyle createTMessageStyle() {
        TMessageStyleImpl tMessageStyle = new TMessageStyleImpl();
        new DefaultColorStyleDescription().setDefaultColors(tMessageStyle);
        return tMessageStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TConditionalMessageStyle createTConditionalMessageStyle() {
        TConditionalMessageStyleImpl tConditionalMessageStyle = new TConditionalMessageStyleImpl();
        return tConditionalMessageStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TBasicMessageMapping createTBasicMessageMapping() {
        TBasicMessageMappingImpl tBasicMessageMapping = new TBasicMessageMappingImpl();
        return tBasicMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TReturnMessageMapping createTReturnMessageMapping() {
        TReturnMessageMappingImpl tReturnMessageMapping = new TReturnMessageMappingImpl();
        return tReturnMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TCreationMessageMapping createTCreationMessageMapping() {
        TCreationMessageMappingImpl tCreationMessageMapping = new TCreationMessageMappingImpl();
        return tCreationMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TDestructionMessageMapping createTDestructionMessageMapping() {
        TDestructionMessageMappingImpl tDestructionMessageMapping = new TDestructionMessageMappingImpl();
        return tDestructionMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TAbstractMapping createTAbstractMapping() {
        TAbstractMappingImpl tAbstractMapping = new TAbstractMappingImpl();
        return tAbstractMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TemplatePackage getTemplatePackage() {
        return (TemplatePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TemplatePackage getPackage() {
        return TemplatePackage.eINSTANCE;
    }

} // TemplateFactoryImpl
