/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.ext.widgets.reference.internal;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.api.DefaultWidgetDescription;
import org.eclipse.sirius.editor.properties.api.IDefaultWidgetDescriptionFactory;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferenceFactory;

/**
 * Creates the default configuration of the reference widget.
 * 
 * @author sbegaudeau
 */
public class DefaultReferenceWidgetDescriptionFactory implements IDefaultWidgetDescriptionFactory {

    @Override
    public boolean canCreate(EClass domainClass, EStructuralFeature eStructuralFeature) {
        return eStructuralFeature instanceof EReference;
    }

    @Override
    public DefaultWidgetDescription create(EClass domainClass, EStructuralFeature eStructuralFeature) {
        if (eStructuralFeature instanceof EReference) {
            EReference eReference = (EReference) eStructuralFeature;

            ExtReferenceDescription description = PropertiesExtWidgetsReferenceFactory.eINSTANCE.createExtReferenceDescription();
            description
                    .setName(MessageFormat.format(Messages.DefaultReferenceDescriptionFactory_widgetLabel, domainClass.getEPackage().getName(), domainClass.getName(), eStructuralFeature.getName()));
            description.setReferenceNameExpression("aql:'" + eReference.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$

            String label = MessageFormat.format(Messages.DefaultReferenceDescriptionFactory_name, eStructuralFeature.eClass().getName(), eStructuralFeature.getName());
            return new DefaultWidgetDescription(label, description);
        }
        return null;
    }

}
