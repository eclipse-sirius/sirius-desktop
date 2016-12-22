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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.api.DefaultWidgetDescription;
import org.eclipse.sirius.editor.properties.api.IDefaultWidgetDescriptionFactory;
import org.eclipse.sirius.editor.properties.internal.Messages;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * The factory used to handle enumerations.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle:multiplestringliterals" })
public class DefaultEnumerationDescriptionFactory implements IDefaultWidgetDescriptionFactory {

    @Override
    public boolean canCreate(EClass domainClass, EStructuralFeature eStructuralFeature) {
        return eStructuralFeature.getEType() instanceof EEnum && !eStructuralFeature.isMany();
    }

    @Override
    public DefaultWidgetDescription create(EClass domainClass, EStructuralFeature eStructuralFeature) {
        RadioDescription description = PropertiesFactory.eINSTANCE.createRadioDescription();

        description.setName(MessageFormat.format(Messages.DefaultEnumerationDescriptionFactory_name, domainClass.getEPackage().getName(), domainClass.getName(), eStructuralFeature.getName()));
        description.setIsEnabledExpression("aql:self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "').changeable");
        description.setHelpExpression("aql:input.emfEditServices(self).getDescription(self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "'))");
        description.setLabelExpression("aql:input.emfEditServices(self).getText(self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "')) + ':'");
        description.setValueExpression(
                "aql:self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "').eType.getEEnumLiteralByLiteral(self." + eStructuralFeature.getName() + ".toString())");
        description.setCandidatesExpression("aql:self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "').eType.eLiterals");
        description.setCandidateDisplayExpression("aql:candidate.name");
        description.setNumberOfColumns(5);

        InitialOperation initialOperation = ToolFactory.eINSTANCE.createInitialOperation();
        SetValue setValue = ToolFactory.eINSTANCE.createSetValue();
        setValue.setFeatureName(eStructuralFeature.getName());
        setValue.setValueExpression("aql:newValue.instance");
        initialOperation.setFirstModelOperations(setValue);

        description.setInitialOperation(initialOperation);
        if (eStructuralFeature.getLowerBound() == 1) {
            RadioWidgetConditionalStyle conditionalStyle = PropertiesFactory.eINSTANCE.createRadioWidgetConditionalStyle();
            conditionalStyle.setPreconditionExpression("aql:self.eClass().getEStructuralFeature('" + eStructuralFeature.getName() + "').lowerBound = 1");
            RadioWidgetStyle widgetStyle = PropertiesFactory.eINSTANCE.createRadioWidgetStyle();
            widgetStyle.getLabelFontFormat().add(FontFormat.BOLD_LITERAL);
            conditionalStyle.setStyle(widgetStyle);
            description.getConditionalStyles().add(conditionalStyle);
        }

        String label = MessageFormat.format(Messages.DefaultEnumerationDescriptionFactory_widgetLabel, eStructuralFeature.eClass().getName(), eStructuralFeature.getName());
        return new DefaultWidgetDescription(label, description);
    }

}
