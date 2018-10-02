/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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
package org.eclipse.sirius.properties.core.internal.converter;

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.eef.EEFPropertyValidationRuleDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.core.api.TransformationCache;
import org.eclipse.sirius.properties.core.internal.Messages;

/**
 * This class is used to convert the description of a Sirius property validation
 * rule to an EEF one.
 * 
 * @author sbegaudeau
 */
public class PropertyValidationRuleDescriptionConverter extends AbstractValidationRuleDescriptionConverter {

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof PropertyValidationRule;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, TransformationCache cache) {
        if (description instanceof PropertyValidationRule) {
            PropertyValidationRule propertyValidationRule = (PropertyValidationRule) description;
            EEFPropertyValidationRuleDescription eefPropertyValidationRuleDescription = EefFactory.eINSTANCE.createEEFPropertyValidationRuleDescription();
            eefPropertyValidationRuleDescription.setSeverity(this.getValidationSeverity(propertyValidationRule.getLevel()));

            cache.put(description, eefPropertyValidationRuleDescription);

            this.convertValidationRuleContent(eefPropertyValidationRuleDescription, propertyValidationRule, parameters, cache);
            return eefPropertyValidationRuleDescription;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.IDescriptionConverter_InvalidDescriptionType, description.getClass().getName(), PropertyValidationRule.class.getName()));
        }
    }
}
