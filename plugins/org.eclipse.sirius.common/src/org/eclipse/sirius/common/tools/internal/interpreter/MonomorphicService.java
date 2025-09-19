/*******************************************************************************
 * Copyright (c) 2013, 2024 THALES GLOBAL SERVICES and others.
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.common.tools.Messages;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

/**
 * A service which correspond exactly to a single Java method.
 * 
 * @author pcdavid
 */
class MonomorphicService implements IMonomorphicService {
    /**
     * An instance of the Java class which defines the service method, needed to
     * actually invoke the service method.
     */
    private final Object serviceInstance;

    /**
     * The Java method which corresponds to this service.
     */
    private final Method serviceMethod;

    MonomorphicService(Object serviceInstance, Method serviceMethod) {
        this.serviceInstance = Objects.requireNonNull(serviceInstance);
        this.serviceMethod = Objects.requireNonNull(serviceMethod);
        if (!ServiceInterpreter.isValidServiceMethod(serviceMethod)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getName() {
        return serviceMethod.getName();
    }

    @Override
    public boolean appliesTo(Object[] target) {
        boolean apply = true;
        Class<?>[] parameters = serviceMethod.getParameterTypes();
        if (parameters.length != target.length) {
            apply = false;
        } else {
            for (int i = 0; i < target.length; i++) {
                if ((target[i] != null) && !serviceMethod.getParameterTypes()[i].isAssignableFrom(target[i].getClass())) {
                    apply = false;
                    break;
                }
            }
        }
        return apply;
    }

    @Override
    public Object call(Object[] target) throws EvaluationException {
        Object result = null;
        try {
            result = serviceMethod.invoke(serviceInstance, target);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            /*
             * These exceptions indicate problems in the method invocation
             * itself, i.e. our service invocation logic is broken and tries to
             * call inaccessible or incompatible methods.
             */
            fail(e);
        } catch (InvocationTargetException e) {
            /*
             * These exceptions on the other hand represent problems thrown from
             * inside the service method
             */
            Throwable cause = e.getTargetException();
            fail(cause);
        }
        return result;
    }

    private void fail(Throwable th) throws EvaluationException {
        throw new EvaluationException(MessageFormat.format(Messages.MonomorphicService_serviceError, this), th);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}.{1}({2})", serviceInstance.getClass().getCanonicalName(), serviceMethod.getName(), serviceMethod.getParameterTypes()[0].getCanonicalName()); //$NON-NLS-1$
    }

    @Override
    public List<String> getParametersTypes() {
        List<String> parametersTypes = new ArrayList<String>();
        for (Class<?> type : serviceMethod.getParameterTypes()) {
            parametersTypes.add(type.getSimpleName());
        }
        return parametersTypes;
    }

    @Override
    public Collection<Method> getImplementations() {
        return Collections.singleton(this.serviceMethod);
    }

}
