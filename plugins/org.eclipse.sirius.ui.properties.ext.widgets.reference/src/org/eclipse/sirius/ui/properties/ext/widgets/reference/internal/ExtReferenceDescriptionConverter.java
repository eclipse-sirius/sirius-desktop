/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.properties.ext.widgets.reference.internal;

import java.util.Map;

import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EEFExtReferenceDescription;
import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EefExtWidgetsReferenceFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.ui.properties.api.DescriptionCache;
import org.eclipse.sirius.ui.properties.api.IDescriptionConverter;

/**
 * This class is used to convert the Sirius Extension reference description into
 * the EEF one.
 * 
 * @author sbegaudeau
 */
public class ExtReferenceDescriptionConverter implements IDescriptionConverter {

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof ExtReferenceDescription;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (description instanceof ExtReferenceDescription) {
            ExtReferenceDescription extReferenceDescription = (ExtReferenceDescription) description;

            EEFExtReferenceDescription eefExtReferenceDescription = EefExtWidgetsReferenceFactory.eINSTANCE.createEEFExtReferenceDescription();
            eefExtReferenceDescription.setIdentifier(extReferenceDescription.getIdentifier());
            eefExtReferenceDescription.setHelpExpression(extReferenceDescription.getHelpExpression());
            eefExtReferenceDescription.setIsEnabledExpression(extReferenceDescription.getIsEnabledExpression());
            eefExtReferenceDescription.setLabelExpression(extReferenceDescription.getLabelExpression());
            eefExtReferenceDescription.setReferenceNameExpression(extReferenceDescription.getReferenceNameExpression());
            eefExtReferenceDescription.setReferenceOwnerExpression(extReferenceDescription.getReferenceOwnerExpression());

            cache.put(extReferenceDescription, eefExtReferenceDescription);

            return eefExtReferenceDescription;
        }
        return null;
    }
}
