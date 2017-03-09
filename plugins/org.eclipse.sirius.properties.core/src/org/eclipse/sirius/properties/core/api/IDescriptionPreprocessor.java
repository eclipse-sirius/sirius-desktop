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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;

/**
 * Implementations of this interface are used by Sirius to resolve inheritance
 * and extension relations between property view descriptions.
 * 
 * @author flatombe
 * @author mbats
 */
public interface IDescriptionPreprocessor {

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
     * Processes a property view description or part of it. The initial
     * description is not modified.
     * 
     * @param description
     *            the description to process
     * @param cache
     *            the cache of already-processed descriptions
     * @param interpreter
     *            the interpreter
     * @param variableManager
     *            the variable manager
     * @param overridesProvider
     *            Utility class used to provide the override descriptions
     * @return a semantically equivalent description to
     *         {@code originalDescription}, but with inheritance and extension
     *         relations unfolded.
     */
    EObject convert(EObject description, TransformationCache cache, IInterpreter interpreter, IVariableManager variableManager, OverridesProvider overridesProvider);
}
