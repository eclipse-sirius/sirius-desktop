/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Interface used by interpreters capable of interpreting JAVA services.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface IJavaAwareInterpreter {
    /**
     * Returns the concrete methods which implement the service identified by the given expression.
     * 
     * @param serviceCall
     *            the service call from which we want to extract corresponding {@link Method} if such elements exist.
     * @return the concrete methods which implement the service identified by the given expression. An empty list if no
     *         such element exists.
     */
    Collection<Method> getImplementation(String serviceCall);
}
