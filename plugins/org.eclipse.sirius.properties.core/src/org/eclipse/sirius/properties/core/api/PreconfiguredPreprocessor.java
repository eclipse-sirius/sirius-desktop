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
package org.eclipse.sirius.properties.core.api;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * {@link IDescriptionPreprocessor} implementation that supports most of the cases by ignoring :
 * <ul>
 * <li>the extends reference,</li>
 * <li>all the filters of the extension mechanism.</li>
 * </ul>
 * and by copying:
 * <ul>
 * <li>the actions</li>
 * <li>the initial operation,</li>
 * <li>the style,</li>
 * <li>the conditional styles.</li>
 * </ul>
 * 
 * @author mbats
 *
 * @param <SIRIUS>
 *            the type of description supported by this preprocessor.
 */
public class PreconfiguredPreprocessor<SIRIUS extends EObject> extends DefaultDescriptionPreprocessorWithFiltering<SIRIUS> {

    /**
     * The constructor.
     * 
     * @param descriptionClass
     *            the SIRIUS class
     * @param eClass
     *            the EClass of the Sirius object
     */
    public PreconfiguredPreprocessor(Class<SIRIUS> descriptionClass, EClass eClass) {
        super(descriptionClass, PreconfiguredPreprocessorUtils.getFeaturesToFilter(eClass), PreconfiguredPreprocessorUtils.getFeaturesToCopy(eClass));
    }

}
