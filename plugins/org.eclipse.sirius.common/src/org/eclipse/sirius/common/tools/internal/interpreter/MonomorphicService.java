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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

import com.google.common.base.Preconditions;

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

    public MonomorphicService(Object serviceInstance, Method serviceMethod) {
        this.serviceInstance = Preconditions.checkNotNull(serviceInstance);
        this.serviceMethod = Preconditions.checkNotNull(serviceMethod);
        Preconditions.checkArgument(ServiceInterpreter.isValidServiceMethod(serviceMethod));
    }

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

    public Object call(Object[] target) throws EvaluationException {
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
        return serviceInstance.getClass().getCanonicalName() + "." + serviceMethod.getName() + "(" + serviceMethod.getParameterTypes()[0].getCanonicalName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Override
    public List<String> getParametersTypes() {
        List<String> parametersTypes = new ArrayList<String>();
        for (Class<?> type : serviceMethod.getParameterTypes()) {
            parametersTypes.add(type.getSimpleName());
        }
        return parametersTypes;
    }

}
