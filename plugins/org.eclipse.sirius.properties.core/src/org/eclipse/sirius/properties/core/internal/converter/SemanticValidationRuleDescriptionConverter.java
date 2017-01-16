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

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.eef.EEFSemanticValidationRuleDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.core.api.DescriptionCache;
import org.eclipse.sirius.properties.core.internal.Messages;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;

/**
 * This class is used to convert the semantic validation rule descriptions.
 * 
 * @author sbegaudeau
 */
public class SemanticValidationRuleDescriptionConverter extends AbstractValidationRuleDescriptionConverter {

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof SemanticValidationRule;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (description instanceof SemanticValidationRule) {
            SemanticValidationRule semanticValidationRule = (SemanticValidationRule) description;
            EEFSemanticValidationRuleDescription eefSemanticValidationRuleDescription = EefFactory.eINSTANCE.createEEFSemanticValidationRuleDescription();
            eefSemanticValidationRuleDescription.setTargetClass(semanticValidationRule.getTargetClass());
            eefSemanticValidationRuleDescription.setSeverity(this.getValidationSeverity(semanticValidationRule.getLevel()));

            cache.put(description, eefSemanticValidationRuleDescription);

            this.convertValidationRuleContent(eefSemanticValidationRuleDescription, semanticValidationRule, parameters, cache);
            return eefSemanticValidationRuleDescription;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.IDescriptionConverter_InvalidDescriptionType, description.getClass().getName(), SemanticValidationRule.class.getName()));
        }
    }

}
