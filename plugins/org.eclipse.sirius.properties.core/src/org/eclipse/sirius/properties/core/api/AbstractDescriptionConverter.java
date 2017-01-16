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
package org.eclipse.sirius.properties.core.api;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.properties.core.internal.Messages;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * Common superclass of all the description converters.
 * 
 * @author sbegaudeau
 */
public abstract class AbstractDescriptionConverter implements IDescriptionConverter {
    /**
     * Converts the given EObject.
     * 
     * @param eObject
     *            The Sirius EObject
     * @param parameters
     *            The parameters
     * @param cache
     *            The cache
     * @param expectedClass
     *            The EEF class expected
     * @return The EEF EObject or <code>null</code> if it could not be converted
     * @param <T>
     *            The type of the EEF EObject
     * 
     */
    protected <T> T convertEObject(EObject eObject, Map<String, Object> parameters, DescriptionCache cache, Class<T> expectedClass) {
        if (eObject != null) {
            IDescriptionConverter converter = SiriusPropertiesCorePlugin.getPlugin().getDescriptionConverter(eObject);
            if (converter != null) {
                EObject eefEObject = converter.convert(eObject, parameters, cache);
                if (expectedClass.isAssignableFrom(eefEObject.getClass())) {
                    return expectedClass.cast(eefEObject);
                }
            } else {
                SiriusPropertiesCorePlugin.getPlugin().error(MessageFormat.format(Messages.AbstractDescriptionConverter_noConverterFound, eObject));
            }
        }
        return null;
    }

    /**
     * Converts the given collection of EObjects.
     * 
     * @param eObjects
     *            The Sirius EObjects
     * @param parameters
     *            The parameters
     * @param cache
     *            The cache
     * @param expectedClass
     *            The EEF class expected
     * @return The collection of EEF EObjects
     * @param <T>
     *            The type of the EEF EObjects
     * 
     */
    protected <T> List<T> convertCollection(Collection<?> eObjects, Map<String, Object> parameters, DescriptionCache cache, Class<T> expectedClass) {
        List<T> convertedEObjects = new ArrayList<>();
        for (Object eObject : eObjects) {
            if (eObject instanceof EObject) {
                T convertedEObject = this.convertEObject((EObject) eObject, parameters, cache, expectedClass);
                if (convertedEObject != null) {
                    convertedEObjects.add(convertedEObject);
                }
            }
        }
        return convertedEObjects;
    }

    /**
     * Creates the AQL based expression used to execute the given initial
     * operation.
     * 
     * @param initialOperation
     *            The initial operation
     * @return The AQL based expression used to execute the given initial
     *         operation
     */
    protected String getExpressionForOperation(InitialOperation initialOperation) {
        if (initialOperation != null) {
            return MessageFormat.format("aql:input.executeOperation(self, ''{0}'')", EcoreUtil.getURI(initialOperation).toString()); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Get the rgb color expression from a color description.
     * 
     * @param colorDescription
     *            Color description
     * @param parameters
     *            The parameters
     * @return A string representing the color as 'rgb(0,0,0)'
     */
    protected String getColorExpression(ColorDescription colorDescription, Map<String, Object> parameters) {
        // To update when new ColorQuery(colorDescription).getRGBValues(EObject
        // context, IInterpreter itp) will be available
        // See bug 490661: https://bugs.eclipse.org/bugs/show_bug.cgi?id=490661

        Object object = parameters.get(IDescriptionConverter.INPUT);
        if (object instanceof SiriusInputDescriptor) {
            SiriusInputDescriptor inputDescriptor = (SiriusInputDescriptor) object;
            AbstractColorUpdater colorUpdater = new AbstractColorUpdater();
            RGBValues rgbValues = colorUpdater.getRGBValuesFromColorDescription(inputDescriptor.getSemanticElement(), colorDescription);
            if (rgbValues != null) {
                return MessageFormat.format("rgb({0},{1},{2})", rgbValues.getRed(), rgbValues.getGreen(), rgbValues.getBlue()); //$NON-NLS-1$
            }
        }
        return null;
    }
}
