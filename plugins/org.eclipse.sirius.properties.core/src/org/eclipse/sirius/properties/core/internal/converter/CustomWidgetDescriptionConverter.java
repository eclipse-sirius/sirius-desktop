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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.eef.EEFCustomExpression;
import org.eclipse.eef.EefPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DescriptionCache;

/**
 * This class is used to convert custom widget descriptions.
 * 
 * @author sbegaudeau
 * 
 * @param <SIRIUS>
 *            The type of the Sirius EObject
 */
public class CustomWidgetDescriptionConverter<SIRIUS extends EObject> extends DefaultDescriptionConverter<SIRIUS> {
    /**
     * The constructor.
     * 
     * @param siriusClass
     *            The class of the Sirius EObject
     * @param eefEClass
     *            The EClass of the EEF EObject
     */
    public CustomWidgetDescriptionConverter(Class<SIRIUS> siriusClass, EClass eefEClass) {
        super(siriusClass, eefEClass);
    }

    @Override
    protected void convertEReference(SIRIUS siriusEObject, EObject eefEObject, EReference eReference, Map<String, Object> parameters, DescriptionCache cache) {
        if (eReference.equals(PropertiesPackage.Literals.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS)) {
            EReference eefEReference = EefPackage.Literals.EEF_CUSTOM_WIDGET_DESCRIPTION__CUSTOM_EXPRESSIONS;
            Object siriusValue = siriusEObject.eGet(eReference);
            Object eefValue = eefEObject.eGet(eefEReference);
            if (siriusValue instanceof Collection<?> && eefValue instanceof Collection<?>) {
                Collection<?> eefCollectionValue = (Collection<?>) eefValue;
                Collection<?> siriusCollectionValue = (Collection<?>) siriusValue;
                List<EEFCustomExpression> convertedCollection = this.convertCollection(siriusCollectionValue, parameters, cache, EEFCustomExpression.class);
                eefEObject.eSet(eefEReference, this.addAll(this.addAll(new ArrayList<EObject>(), eefCollectionValue), convertedCollection));
            }
        } else {
            super.convertEReference(siriusEObject, eefEObject, eReference, parameters, cache);
        }
    }
}
