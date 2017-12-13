/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
