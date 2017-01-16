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

import org.eclipse.eef.EEFControlDescription;
import org.eclipse.eef.EEFGroupConditionalStyle;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFGroupStyle;
import org.eclipse.eef.EEFPropertyValidationRuleDescription;
import org.eclipse.eef.EEFSemanticValidationRuleDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.core.api.AbstractDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DescriptionCache;
import org.eclipse.sirius.properties.core.internal.Messages;

/**
 * This class is used to convert the description of a Sirius group to an EEF
 * one.
 * 
 * @author sbegaudeau
 */
public class GroupDescriptionConverter extends AbstractDescriptionConverter {

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof GroupDescription;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (description instanceof GroupDescription) {
            GroupDescription groupDescription = (GroupDescription) description;

            EEFGroupDescription eefGroup = EefFactory.eINSTANCE.createEEFGroupDescription();
            eefGroup.setIdentifier(groupDescription.getIdentifier());
            if (Util.isBlank(eefGroup.getIdentifier())) {
                eefGroup.setIdentifier(EcoreUtil.getURI(groupDescription).toString());
            }
            eefGroup.setLabelExpression(groupDescription.getLabelExpression());
            eefGroup.setDomainClass(groupDescription.getDomainClass());
            eefGroup.setSemanticCandidateExpression(groupDescription.getSemanticCandidateExpression());
            eefGroup.setPreconditionExpression(groupDescription.getPreconditionExpression());

            cache.put(description, eefGroup);

            eefGroup.setStyle(this.convertEObject(groupDescription.getStyle(), parameters, cache, EEFGroupStyle.class));
            eefGroup.getConditionalStyles().addAll(this.convertCollection(groupDescription.getConditionalStyles(), parameters, cache, EEFGroupConditionalStyle.class));
            eefGroup.getControls().addAll(this.convertCollection(groupDescription.getControls(), parameters, cache, EEFControlDescription.class));

            if (groupDescription.getValidationSet() != null) {
                GroupValidationSetDescription validationSet = groupDescription.getValidationSet();
                eefGroup.getSemanticValidationRules().addAll(this.convertCollection(validationSet.getSemanticValidationRules(), parameters, cache, EEFSemanticValidationRuleDescription.class));
                eefGroup.getPropertyValidationRules().addAll(this.convertCollection(validationSet.getPropertyValidationRules(), parameters, cache, EEFPropertyValidationRuleDescription.class));
            }

            return eefGroup;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.IDescriptionConverter_InvalidDescriptionType, description.getClass().getName(), GroupDescription.class.getName()));
        }
    }

}
