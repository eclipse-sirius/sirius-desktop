/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.tools.internal.menu.widgets;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.api.DefaultWidgetDescription;
import org.eclipse.sirius.editor.properties.api.IDefaultWidgetDescriptionFactory;
import org.eclipse.sirius.editor.properties.internal.Messages;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.viewpoint.FontFormat;

/**
 * The factory used to handle multivalued EAttributes.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle:multiplestringliterals" })
public class DefaultMultivaluedEAttributeDescriptionFactory implements IDefaultWidgetDescriptionFactory {

    @Override
    public boolean canCreate(EClass domainClass, EStructuralFeature eStructuralFeature) {
        return eStructuralFeature instanceof EAttribute && eStructuralFeature.isMany();
    }

    @Override
    public DefaultWidgetDescription create(EClass domainClass, EStructuralFeature eStructuralFeature) {
        ListDescription description = PropertiesFactory.eINSTANCE.createListDescription();

        description.setName(
                MessageFormat.format(Messages.DefaultMultivaluedEAttributeDescriptionFactory_widgetLabel, domainClass.getEPackage().getName(), domainClass.getName(), eStructuralFeature.getName()));
        description.setIsEnabledExpression("aql:self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "').changeable");
        description.setHelpExpression("aql:input.emfEditServices(self).getDescription(self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "'))");
        description.setLabelExpression("aql:input.emfEditServices(self).getText(self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "')) + ':'");
        description.setValueExpression("aql:self." + eStructuralFeature.getName());
        description.setDisplayExpression("var:value");

        if (eStructuralFeature.getLowerBound() == 1) {
            ListWidgetConditionalStyle conditionalStyle = PropertiesFactory.eINSTANCE.createListWidgetConditionalStyle();
            conditionalStyle.setPreconditionExpression("aql:self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "').lowerBound = 1");
            ListWidgetStyle widgetStyle = PropertiesFactory.eINSTANCE.createListWidgetStyle();
            widgetStyle.getLabelFontFormat().add(FontFormat.BOLD_LITERAL);
            conditionalStyle.setStyle(widgetStyle);
            description.getConditionalStyles().add(conditionalStyle);
        }

        String label = MessageFormat.format(Messages.DefaultMultivaluedEAttributeDescriptionFactory_name, eStructuralFeature.eClass().getName(), eStructuralFeature.getName());
        return new DefaultWidgetDescription(label, description);
    }

}
