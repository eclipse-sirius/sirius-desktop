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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * Implementations of this interface will be used to convert the EObjects of the
 * Sirius specific model to the EEF specific one.
 * 
 * @author sbegaudeau
 * @since 4.1.0
 */
public interface IDescriptionConverter {
    /**
     * The name of the parameter containing the input of the view.
     */
    String INPUT = "input"; //$NON-NLS-1$

    /**
     * The name of the parameter containing the view description.
     */
    String VIEW = "view"; //$NON-NLS-1$

    /**
     * Indicates if the converter can handle the given description.
     * 
     * @param description
     *            An EObject used in the Sirius model
     * @return <code>true</code> if the converter can handle it, or
     *         <code>false</code> otherwise
     */
    boolean canHandle(EObject description);

    /**
     * Converts the description EObject used in the Sirius model to an EObject
     * for the EEF model. The objects created for the given description should
     * be entered in the cache by the creator of the object.
     * 
     * @param description
     *            The EObject used in the Sirius model
     * @param parameters
     *            Additional parameters that can be used during the
     *            transformation
     * @param cache
     *            The cache of the object created for the given description.
     * @return An EObject to be used in the EEF description
     */
    EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache);
}
