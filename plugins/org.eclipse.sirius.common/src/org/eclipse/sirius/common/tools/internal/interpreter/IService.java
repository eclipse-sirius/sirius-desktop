/*******************************************************************************
 * Copyright (c) 2013, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.lang.reflect.Method;
import java.util.Collection;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

/**
 * Represents a callable service.
 * 
 * @author pcdavid
 */
public interface IService {
    /**
     * The name of the service, used to invoke it.
     * 
     * @return the name of the service.
     */
    String getName();

    /**
     * Tests whether the service can be invoked on the specified target object.
     * 
     * @param target
     *            a potential target object on which to invoke the service.
     * @return <code>true</code> iff this service can be invoked on the specified target object.
     */
    boolean appliesTo(Object[] target);

    /**
     * Invoke the service on the specified target.
     * 
     * @param target
     *            the target on which to invoke the service.
     * @return the result of the service invocation.
     * @throws EvaluationException
     *             if an error occurred during the invocation of the service.
     */
    Object call(Object[] target) throws EvaluationException;

    /**
     * Returns the concrete methods which implement this service.
     * 
     * @return the concrete methods which implement this service.
     */
    Collection<Method> getImplementations();
}
