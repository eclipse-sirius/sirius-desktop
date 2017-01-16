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
import java.util.List;
import java.util.Map;

import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFSemanticValidationRuleDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.core.api.AbstractDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DescriptionCache;
import org.eclipse.sirius.properties.core.internal.Messages;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;

/**
 * This class is used to convert the Sirius description of a page to an EEF one.
 * 
 * @author sbegaudeau
 */
public class PageDescriptionConverter extends AbstractDescriptionConverter {

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof PageDescription;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (description instanceof PageDescription) {
            PageDescription pageDescription = (PageDescription) description;

            EEFPageDescription page = EefFactory.eINSTANCE.createEEFPageDescription();
            page.setIdentifier(pageDescription.getIdentifier());
            page.setLabelExpression(pageDescription.getLabelExpression());
            page.setDomainClass(pageDescription.getDomainClass());
            page.setSemanticCandidateExpression(pageDescription.getSemanticCandidateExpression());
            page.setPreconditionExpression(pageDescription.getPreconditionExpression());

            if (page.getIdentifier() == null || page.getIdentifier().trim().length() == 0) {
                page.setIdentifier(EcoreUtil.getURI(pageDescription).toString());
            }

            cache.put(description, page);

            if (pageDescription.getValidationSet() != null) {
                List<SemanticValidationRule> semanticValidationRules = pageDescription.getValidationSet().getSemanticValidationRules();
                page.getSemanticValidationRules().addAll(this.convertCollection(semanticValidationRules, parameters, cache, EEFSemanticValidationRuleDescription.class));
            }

            return page;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.IDescriptionConverter_InvalidDescriptionType, description.getClass().getName(), PageDescription.class.getName()));
        }
    }

}
