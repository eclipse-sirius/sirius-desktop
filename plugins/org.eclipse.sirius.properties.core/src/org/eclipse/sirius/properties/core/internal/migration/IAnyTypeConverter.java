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

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;

/**
 * Interface used to convert a value contained in AnyType into a proper EMF
 * EObject.
 * 
 * @author sbegaudeau
 */
public interface IAnyTypeConverter {

    /**
     * Indicates if the converter can convert the given value into an EObject
     * with the given EClass.
     * 
     * @param resource
     *            The resource
     * @param anyType
     *            The value
     * @param eClass
     *            The EClass of the EObject to create
     * @return <code>true</code> if the converter can handle it,
     *         <code>false</code> otherwise
     */
    boolean canConvert(Resource resource, AnyType anyType, EClass eClass);

    /**
     * Converts the data in the any type object into a proper EObject.
     * 
     * @param resource
     *            The resource
     * @param anyType
     *            The input data
     * @param eClass
     *            The EClass of the EObject to create
     * @return An optional with the EObject created or an empty optional if it
     *         could not be created (i.e. the EClass is abstract)
     */
    Optional<EObject> convert(Resource resource, AnyType anyType, EClass eClass);
}
