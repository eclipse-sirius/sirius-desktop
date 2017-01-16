/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal.converter;

import java.util.Map;

import org.eclipse.eef.EEFRuleAuditDescription;
import org.eclipse.eef.EEFValidationFixDescription;
import org.eclipse.eef.EEFValidationRuleDescription;
import org.eclipse.eef.EEF_VALIDATION_SEVERITY_DESCRIPTION;
import org.eclipse.sirius.properties.core.api.AbstractDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DescriptionCache;
import org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * Common superclass used to convert validation rules.
 * 
 * @author sbegaudeau
 */
public abstract class AbstractValidationRuleDescriptionConverter extends AbstractDescriptionConverter {

    /**
     * Returns the EEF validation severity computed from the Sirius validation
     * severity.
     * 
     * @param level
     *            The Sirius validation severity
     * @return The EEF validation severity
     */
    protected EEF_VALIDATION_SEVERITY_DESCRIPTION getValidationSeverity(ERROR_LEVEL level) {
        EEF_VALIDATION_SEVERITY_DESCRIPTION severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.INFO;

        switch (level) {
        case INFO_LITERAL:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.INFO;
            break;
        case WARNING_LITERAL:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.WARNING;
            break;
        case ERROR_LITERAL:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.ERROR;
            break;
        default:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.INFO;
            break;
        }

        return severity;
    }

    /**
     * Converts the content of the validation rule.
     * 
     * @param eefValidationRuleDescription
     *            The EEF validation rule
     * @param validationRule
     *            The Sirius validation rule
     * @param parameters
     *            The parameters
     * @param cache
     *            The cache
     */
    protected void convertValidationRuleContent(EEFValidationRuleDescription eefValidationRuleDescription, ValidationRule validationRule, Map<String, Object> parameters, DescriptionCache cache) {
        eefValidationRuleDescription.setMessageExpression(validationRule.getMessage());
        eefValidationRuleDescription.getAudits().addAll(this.convertCollection(validationRule.getAudits(), parameters, cache, EEFRuleAuditDescription.class));
        eefValidationRuleDescription.getFixes().addAll(this.convertCollection(validationRule.getFixes(), parameters, cache, EEFValidationFixDescription.class));
    }

}
