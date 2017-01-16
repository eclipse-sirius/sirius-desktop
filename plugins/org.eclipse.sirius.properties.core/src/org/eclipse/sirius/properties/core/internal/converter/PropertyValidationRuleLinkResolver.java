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

import java.util.List;

import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFPropertyValidationRuleDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.core.api.DescriptionCache;
import org.eclipse.sirius.properties.core.api.IDescriptionLinkResolver;

/**
 * This class is used to resolve the links between the property validation rules
 * and the widgets.
 * 
 * @author sbegaudeau
 */
public class PropertyValidationRuleLinkResolver implements IDescriptionLinkResolver {

    @Override
    public void resolve(EEFViewDescription view, DescriptionCache cache) {
        List<EEFPageDescription> pages = view.getPages();
        for (EEFPageDescription page : pages) {
            List<EEFGroupDescription> groups = page.getGroups();
            for (EEFGroupDescription group : groups) {
                // Resolve the links for all the property validation rules
                List<EEFPropertyValidationRuleDescription> propertyValidationRules = group.getPropertyValidationRules();
                for (EEFPropertyValidationRuleDescription eefPropertyValidationRule : propertyValidationRules) {
                    this.resolve(eefPropertyValidationRule, cache);
                }
            }
        }
    }

    /**
     * Resolves the link between the property validation rule and the widgets.
     * 
     * @param eefPropertyValidationRule
     *            The property validation rule
     * @param cache
     *            The cache
     */
    private void resolve(EEFPropertyValidationRuleDescription eefPropertyValidationRule, DescriptionCache cache) {
        Object siriusDescription = cache.getSiriusDescription(eefPropertyValidationRule);
        if (siriusDescription instanceof PropertyValidationRule) {
            PropertyValidationRule siriusPropertyValidationRule = (PropertyValidationRule) siriusDescription;
            List<WidgetDescription> widgets = siriusPropertyValidationRule.getTargets();
            for (WidgetDescription siriusWidget : widgets) {
                Object eefDescription = cache.getEEFDescription(siriusWidget);
                if (eefDescription instanceof EEFWidgetDescription) {
                    eefPropertyValidationRule.getTargets().add((EEFWidgetDescription) eefDescription);
                }
            }
        }
    }

}
