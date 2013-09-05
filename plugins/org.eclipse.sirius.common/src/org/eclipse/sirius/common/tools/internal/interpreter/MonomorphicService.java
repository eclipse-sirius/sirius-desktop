/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.base.Preconditions;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

/**
 * A service which correspond exactly to a single Java method.
 * 
 * @author pcdavid
 */
class MonomorphicService implements IService {
    /**
     * An instance of the Java class which defines the service method, needed to
     * actually invoke the service method.
     */
    private final Object serviceInstance;

    /**
     * The Java method which corresponds to this service.
     */
    private final Method serviceMethod;

    public MonomorphicService(Object serviceInstance, Method serviceMethod) {
        this.serviceInstance = Preconditions.checkNotNull(serviceInstance);
        this.serviceMethod = Preconditions.checkNotNull(serviceMethod);
        Preconditions.checkArgument(ServiceInterpreter.isValidServiceMethod(serviceMethod));
    }

    public String getName() {
        return serviceMethod.getName();
    }

    public boolean appliesTo(Object target) {
        return target != null && serviceMethod.getParameterTypes()[0].isAssignableFrom(target.getClass());
    }

    public Object call(Object target) throws EvaluationException {
        Object result = null;
        try {
            result = serviceMethod.invoke(serviceInstance, target);
        } catch (IllegalArgumentException e) {
            fail(e);
        } catch (IllegalAccessException e) {
            fail(e);
        } catch (InvocationTargetException e) {
            fail(e);
        }
        return result;
    }

    private void fail(Exception e) throws EvaluationException {
        throw new EvaluationException("Exception while calling service " + this + ".", e);
    }

    @Override
    public String toString() {
        return serviceInstance.getClass().getCanonicalName() + "." + serviceMethod.getName() + "(" + serviceMethod.getParameterTypes()[0].getCanonicalName() + ")";
    }
}
