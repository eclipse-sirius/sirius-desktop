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

import org.eclipse.eef.EEFTextConditionalStyle;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFTextStyle;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.core.api.AbstractDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DescriptionCache;
import org.eclipse.sirius.properties.core.internal.Messages;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * This class is used to convert the description of the text widgets.
 * 
 * @author sbegaudeau
 */
public class TextDescriptionConverter extends AbstractDescriptionConverter {

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof TextDescription;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (description instanceof TextDescription) {
            TextDescription textDescription = (TextDescription) description;

            EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();
            eefTextDescription.setIdentifier(textDescription.getIdentifier());
            eefTextDescription.setHelpExpression(textDescription.getHelpExpression());
            eefTextDescription.setLabelExpression(textDescription.getLabelExpression());
            eefTextDescription.setIsEnabledExpression(textDescription.getIsEnabledExpression());

            eefTextDescription.setValueExpression(textDescription.getValueExpression());

            InitialOperation initialOperation = textDescription.getInitialOperation();
            eefTextDescription.setEditExpression(this.getExpressionForOperation(initialOperation));

            if (description instanceof TextAreaDescription) {
                TextAreaDescription textAreaDescription = (TextAreaDescription) description;
                eefTextDescription.setLineCount(textAreaDescription.getLineCount());
            }

            cache.put(description, eefTextDescription);

            eefTextDescription.setStyle(this.convertEObject(textDescription.getStyle(), parameters, cache, EEFTextStyle.class));
            eefTextDescription.getConditionalStyles().addAll(this.convertCollection(textDescription.getConditionalStyles(), parameters, cache, EEFTextConditionalStyle.class));

            return eefTextDescription;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.IDescriptionConverter_InvalidDescriptionType, description.getClass().getName(), TextDescription.class.getName()));
        }
    }
}
