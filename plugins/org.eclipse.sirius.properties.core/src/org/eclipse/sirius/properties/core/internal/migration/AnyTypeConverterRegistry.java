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
package org.eclipse.sirius.properties.core.internal.migration;

import java.util.Arrays;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;

/**
 * Registry of all the AnyType converter registry.
 * 
 * @author sbegaudeau
 */
public final class AnyTypeConverterRegistry {

    /**
     * The converters ordered by priority.
     */
    private static final IAnyTypeConverter[] CONVERTERS = new IAnyTypeConverter[] { new PageAnyTypeConverter(), new PropertyValidationRuleAnyTypeConverter(), new DefaultAnyTypeConverter() };

    /**
     * The constructor.
     */
    private AnyTypeConverterRegistry() {
        // prevent instantiation
    }

    /**
     * Returns the first converter which can handle the given resource, any type
     * and eClass.
     * 
     * @param resource
     *            The resource
     * @param anyType
     *            The value to convert
     * @param eClass
     *            The EClass of the EObject to create
     * @return An optional with an {@link IAnyTypeConverter} or an empty
     *         optional if none could be found
     */
    public static Optional<IAnyTypeConverter> getConverter(Resource resource, AnyType anyType, EClass eClass) {
        return Arrays.stream(CONVERTERS).filter(converter -> converter.canConvert(resource, anyType, eClass)).findFirst();
    }
}
