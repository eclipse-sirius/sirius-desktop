/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.metamodel.description.validation.operations;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;

/**
 * Implementation of ValidationRuleImpl.java.
 * 
 * @author cbrun.
 */
public final class ValidationRuleOperations {

    /**
     * Avoid instantiation.
     */
    private ValidationRuleOperations() {
        // empty.
    }

    /**
     * Implementation of {@link ValidationRule#checkRule(EObject)}.
     * 
     * @param rule
     *            the validation rule.
     * @param eObj
     *            the element to validate.
     * @return <code>true</code> if the validation rule is checked.
     */
    public static boolean checkRule(final ValidationRule rule, final EObject eObj) {
        boolean valid = true;
        if (rule instanceof SemanticValidationRule || rule instanceof ViewValidationRule) {
            final IInterpreter acceleoInterpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(eObj);
            final Iterator<RuleAudit> iterAudits = rule.getAudits().iterator();
            while (iterAudits.hasNext() && valid) {
                final RuleAudit ruleAudit = iterAudits.next();
                final String expression = ruleAudit.getAuditExpression();
                try {
                    valid = valid && acceleoInterpreter.evaluateBoolean(eObj, expression);
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(ruleAudit, ValidationPackage.eINSTANCE.getRuleAudit_AuditExpression(), e);
                }
            }
        }
        return valid;
    }

    /**
     * Returns the message to display if the rule is not checked.
     * 
     * @param rule
     *            the validation rule.
     * @param eObj
     *            the object to validate.
     * @return the message to display if the rule is not checked.
     * @see ValidationRule#getMessage()
     */
    public static String getMessage(final ValidationRule rule, final EObject eObj) {
        final IInterpreter acceleoInterpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(eObj);
        String message = rule.getMessage();
        if (rule instanceof SemanticValidationRule || rule instanceof ViewValidationRule) {
            try {
                message = acceleoInterpreter.evaluateString(eObj, message);
            } catch (final EvaluationException e) {
                SiriusPlugin.getDefault().error(Messages.ValidationRuleSpecOperations_evaluationErrorMsg, e);
            }
        }
        return message;
    }
}
