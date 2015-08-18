/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.dialect.description;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.util.ValidationSwitch;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A switch that will return the Target Types associated to a given element
 * (part of the
 * {@link org.eclipse.sirius.description.validation.ValidationPackage} ) and
 * feature corresponding to an Interpreted Expression. For example, for a
 * NodeMapping :
 * <p>
 * <li>if the feature is semantic candidate expression, we return the domain
 * class of the first valid container (representation element mapping or
 * representation description).</li>
 * <li>if the feature is any other interpreted expression, we return the domain
 * class associated to this mapping</li>
 * </p>
 * 
 * Can return {@link Options#newNone()} if the given expression does not require
 * any target type (for example, a Popup menu contribution only uses variables
 * in its expressions).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ValidationInterpretedExpressionTargetSwitch extends ValidationSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the
     * feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The ID of the feature containing the Interpreted expression.
     */
    protected int featureID;

    private IInterpretedExpressionTargetSwitch globalSwitch;

    private int lastFeatureID;

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression
     * @param defaultInterpretedExpressionTargetSwitch
     *            the global switch to use
     */
    public ValidationInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch defaultInterpretedExpressionTargetSwitch) {
        super();
        this.featureID = feature != null ? feature.getFeatureID() : DO_NOT_CONSIDER_FEATURE;
        this.lastFeatureID = featureID;
        this.globalSwitch = defaultInterpretedExpressionTargetSwitch;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> targets = Sets.newLinkedHashSet();
        return Options.newSome(targets);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.util.ValidationSwitch#caseRuleAudit(org.eclipse.sirius.viewpoint.description.validation.RuleAudit)
     */
    @Override
    public Option<Collection<String>> caseRuleAudit(RuleAudit object) {
        Option<Collection<String>> result = null;
        switch (featureID) {
        case ValidationPackage.RULE_AUDIT__AUDIT_EXPRESSION:
            // We get the validation rule containing this audit
            EObject validationRule = object.eContainer();
            while (validationRule != null && (!(validationRule instanceof ValidationRule))) {
                validationRule = validationRule.eContainer();
            }
            if (validationRule != null) {
                result = globalSwitch.doSwitch(validationRule, false);
            }
            break;
        default:
            break;
        }

        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.util.ValidationSwitch#caseSemanticValidationRule(org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule)
     */
    @Override
    public Option<Collection<String>> caseSemanticValidationRule(SemanticValidationRule object) {
        Option<Collection<String>> result = null;
        switch (featureID) {
        case ValidationPackage.SEMANTIC_VALIDATION_RULE__MESSAGE:
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> targets = Lists.newArrayList(object.getTargetClass());
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.util.ValidationSwitch#caseViewValidationRule(org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule)
     */
    @Override
    public Option<Collection<String>> caseViewValidationRule(ViewValidationRule object) {
        Option<Collection<String>> result = null;
        switch (featureID) {
        case ValidationPackage.VIEW_VALIDATION_RULE__MESSAGE:
        case DO_NOT_CONSIDER_FEATURE:
            // Evaluation on DDiagramElement -> see history if it changes.
            Collection<String> targetTypes = Lists.newArrayList("diagram.DDiagramElement"); //$NON-NLS-1$
            result = Options.newSome(targetTypes);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.util.ValidationSwitch#caseValidationFix(org.eclipse.sirius.viewpoint.description.validation.ValidationFix)
     */
    @Override
    public Option<Collection<String>> caseValidationFix(ValidationFix object) {
        Option<Collection<String>> result = null;
        // We get the validation rule containing this fix
        EObject validationRule = object.eContainer();
        while (validationRule != null && (!(validationRule instanceof ValidationRule))) {
            validationRule = validationRule.eContainer();
        }
        if (validationRule != null) {
            result = globalSwitch.doSwitch(validationRule, false);
        }
        return result;
    }

    /**
     * Changes the behavior of this switch : if true, then the feature will be
     * considered to calculate target types ; if false, then the feature will be
     * ignored.
     * 
     * @param considerFeature
     *            true if the feature should be considered, false otherwise
     */
    public void setConsiderFeature(boolean considerFeature) {
        if (considerFeature) {
            this.featureID = lastFeatureID;
        } else {
            lastFeatureID = this.featureID;
            this.featureID = DO_NOT_CONSIDER_FEATURE;
        }
    }

}
