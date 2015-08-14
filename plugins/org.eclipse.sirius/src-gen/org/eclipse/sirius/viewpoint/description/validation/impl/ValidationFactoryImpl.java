/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.validation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.business.internal.metamodel.description.validation.ValidationSetSpec;
import org.eclipse.sirius.business.internal.metamodel.description.validation.spec.SemanticValidationRuleSpec;
import org.eclipse.sirius.business.internal.metamodel.description.validation.spec.ViewValidationRuleSpec;
import org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFactory;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ValidationFactoryImpl extends EFactoryImpl implements ValidationFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static ValidationFactory init() {
        try {
            ValidationFactory theValidationFactory = (ValidationFactory) EPackage.Registry.INSTANCE.getEFactory(ValidationPackage.eNS_URI);
            if (theValidationFactory != null) {
                return theValidationFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ValidationFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ValidationFactoryImpl() {
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
        case ValidationPackage.VALIDATION_SET:
            return createValidationSet();
        case ValidationPackage.SEMANTIC_VALIDATION_RULE:
            return createSemanticValidationRule();
        case ValidationPackage.VIEW_VALIDATION_RULE:
            return createViewValidationRule();
        case ValidationPackage.RULE_AUDIT:
            return createRuleAudit();
        case ValidationPackage.VALIDATION_FIX:
            return createValidationFix();
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
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case ValidationPackage.ERROR_LEVEL:
            return createERROR_LEVELFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case ValidationPackage.ERROR_LEVEL:
            return convertERROR_LEVELToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ValidationSet createValidationSet() {
        ValidationSetImpl validationSet = new ValidationSetSpec();
        return validationSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public SemanticValidationRule createSemanticValidationRule() {
        SemanticValidationRuleImpl semanticValidationRule = new SemanticValidationRuleSpec();
        return semanticValidationRule;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ViewValidationRule createViewValidationRule() {
        ViewValidationRuleImpl viewValidationRule = new ViewValidationRuleSpec();
        return viewValidationRule;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RuleAudit createRuleAudit() {
        RuleAuditImpl ruleAudit = new RuleAuditImpl();
        return ruleAudit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ValidationFix createValidationFix() {
        ValidationFixImpl validationFix = new ValidationFixImpl();
        return validationFix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ERROR_LEVEL createERROR_LEVELFromString(EDataType eDataType, String initialValue) {
        ERROR_LEVEL result = ERROR_LEVEL.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertERROR_LEVELToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ValidationPackage getValidationPackage() {
        return (ValidationPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ValidationPackage getPackage() {
        return ValidationPackage.eINSTANCE;
    }

} // ValidationFactoryImpl
