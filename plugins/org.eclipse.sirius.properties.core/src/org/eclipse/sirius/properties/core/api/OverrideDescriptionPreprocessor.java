/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;

/**
 * Preprocessor for
 * {@link org.eclipse.sirius.properties.AbstractOverrideDescription}.
 * <ul>
 * <li>The {@code overrides} containment is ignored.</li>
 * </ul>
 * 
 * @author mbats
 */
public class OverrideDescriptionPreprocessor<SIRIUS extends EObject> extends PreconfiguredPreprocessor<SIRIUS> {
    /**
     * The constructor.
     * 
     * @param descriptionClass
     *            the SIRIUS class
     * @param eClass
     *            the EClass of the Sirius object
     */
    public OverrideDescriptionPreprocessor(Class<SIRIUS> descriptionClass, EClass eClass) {
        super(descriptionClass, eClass);
    }

    @Override
    protected void processMonoValuedEReference(EReference eReference, SIRIUS processedDescription, SIRIUS currentDescription, TransformationCache cache, IInterpreter interpreter,
            IVariableManager variableManager, OverridesProvider overridesProvider) {
        if (!eReference.equals(currentDescription.eClass().getEStructuralFeature("overrides"))) { //$NON-NLS-1$
            super.processMonoValuedEReference(eReference, processedDescription, currentDescription, cache, interpreter, variableManager, overridesProvider);
        }
    }
}
