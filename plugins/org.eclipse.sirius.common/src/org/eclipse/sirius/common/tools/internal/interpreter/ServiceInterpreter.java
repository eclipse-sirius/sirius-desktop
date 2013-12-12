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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.osgi.framework.Bundle;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * A specialized interpreter which can only directly invoke Java service
 * methods.
 * 
 * @author pcdavid
 */
public class ServiceInterpreter extends VariableInterpreter implements org.eclipse.sirius.common.tools.api.interpreter.IInterpreter, IInterpreterProvider {

    /** The Service interpreter prefix. */
    public static final String PREFIX = "service:";

    /**
     * The character used to separate receiver and service.
     */
    public static final String RECEIVER_SEPARATOR = ".";

    private static final String EOBJECT_CLASS_NAME = EObject.class.getCanonicalName();

    private final Map<Object, Object> properties = Maps.newHashMap();

    private final Set<Bundle> bundles = Sets.newLinkedHashSet();

    private final Set<String> imports = Sets.newLinkedHashSet();

    private final Map<String, PolymorphicService> services = Maps.newHashMap();

    private Function<String, String> file2bundleName = new Function<String, String>() {
        public String apply(String file) {
            String[] segments = file.split(Pattern.quote("/"));
            if (segments != null && segments.length > 1) {
                return segments[1];
            }
            return null;
        }
    };

    /**
     * Get the receiver variable name if any, none {@link Option} otherwise.
     * 
     * @param expression
     *            The expression after the {@link ServiceInterpreter#PREFIX}.
     * @return the receiver variable name if any, none Option otherwise.
     */
    public static Option<String> getReceiverVariableName(String expression) {
        int indexOfServiceName = expression.indexOf(RECEIVER_SEPARATOR);
        if (indexOfServiceName != -1) {
            String receiverVariableName = expression.substring(0, indexOfServiceName);
            return Options.newSome(receiverVariableName);
        }
        return Options.newNone();
    }

    /**
     * {@inheritDoc}
     */
    public IInterpreter createInterpreter() {
        return new ServiceInterpreter();
    }

    public String getPrefix() {
        return PREFIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object evaluate(EObject target, String expression) throws EvaluationException {
        Object evaluation = null;
        if (target != null && expression != null && expression.startsWith(PREFIX)) {
            String serviceCall = expression.substring(PREFIX.length());
            Option<String> receiverVariableName = getReceiverVariableName(serviceCall);
            EObject receiver = target;
            String serviceName = serviceCall;
            if (receiverVariableName.some()) {
                serviceCall = serviceCall.substring(receiverVariableName.get().length() + 1);
                Object objectReceiver = evaluateVariable(target, receiverVariableName.get().trim());
                if (objectReceiver instanceof EObject) {
                    receiver = (EObject) objectReceiver;
                } else {
                    throw new EvaluationException("The receiver of the service call " + serviceCall + " is not an EObject.");
                }
            }
            int indexOfParenthesis = serviceCall.indexOf("(");
            serviceName = serviceCall.substring(0, indexOfParenthesis == -1 ? serviceCall.length() : indexOfParenthesis);

            Object[] parameters = new Object[] { receiver };

            if (indexOfParenthesis != -1) {
                String[] values = serviceCall.split("[(,)]");
                parameters = new Object[values.length];
                parameters[0] = receiver;
                for (int i = 1; i < values.length; i++) {
                    parameters[i] = evaluateVariable(target, values[i].trim());
                }
            }
            evaluation = callService(parameters, serviceName);
        }
        return evaluation;
    }

    private Object callService(Object[] target, String serviceName) throws EvaluationException {
        if (services.containsKey(serviceName)) {
            IService service = services.get(serviceName);
            return service.call(target);
        } else {
            throw new EvaluationException("Unknown service " + serviceName);
        }
    }

    @Override
    public void addImport(String dependency) {
        Class<?> klass = loadClassFromBundlePath(dependency);
        if (klass != null) {
            registerServiceClass(klass);
        }
    }

    private void registerServiceClass(Class<?> klass) {
        Object serviceInstance = null;
        try {
            serviceInstance = klass.newInstance();
        } catch (InstantiationException e) {
            // Ignore.
        } catch (IllegalAccessException e) {
            // Ignore.
        }

        if (serviceInstance == null) {
            return;
        }

        for (Method m : klass.getMethods()) {
            if (isValidServiceMethod(m)) {
                registerService(new MonomorphicService(serviceInstance, m));
            }
        }
        imports.add(klass.getCanonicalName());
    }

    private void registerService(MonomorphicService service) {
        String name = service.getName();
        if (!services.containsKey(name)) {
            services.put(name, new PolymorphicService(name));
        }
        services.get(name).addImplementer(service);
    }

    /**
     * Checks whether a Java method can be used as a service (in the sense of
     * this interpreter).
     * 
     * @param m
     *            the method to test.
     * @return <code>true</code> iff the method can be used as a service.
     */
    public static boolean isValidServiceMethod(Method m) {
        int mods = m.getModifiers();
        Class<?>[] parameterTypes = m.getParameterTypes();
        if (!Modifier.isPublic(mods) || (parameterTypes.length < 1)) {
            return false;
        }

        boolean result = false;
        try {
            ClassLoader classLoader = m.getDeclaringClass().getClassLoader();
            if (classLoader != null) {
                Class<?> eobjectClass = classLoader.loadClass(EOBJECT_CLASS_NAME);
                result = eobjectClass.isAssignableFrom(parameterTypes[0]);
            }
        } catch (ClassNotFoundException e) {
            result = false;
        }
        return result;
    }

    @Override
    public Collection<String> getImports() {
        return Sets.newHashSet(imports);
    }

    @Override
    public void removeImport(String dependency) {
        if (imports.remove(dependency)) {
            // TODO
        }
    }

    @Override
    public void clearImports() {
        imports.clear();
        services.clear();
    }

    private Class<?> loadClassFromBundlePath(String className) {
        for (Bundle bundle : bundles) {
            try {
                return bundle.loadClass(className);
            } catch (ClassNotFoundException e) {
                // Ignore, try next bundle in the path.
            }
        }
        return null;
    }

    @Override
    public void setProperty(Object key, Object value) {
        properties.put(key, value);
        if (IInterpreter.FILES.equals(key) && (value instanceof List<?>)) {
            updateBundlePath(Iterables.filter((List<?>) value, String.class));
            services.clear();
        }
    }

    private void updateBundlePath(Iterable<String> files) {
        bundles.clear();
        for (String name : Iterables.filter(Iterables.transform(files, file2bundleName), Predicates.notNull())) {
            Bundle bundle = Platform.getBundle(name);
            if (bundle != null) {
                bundles.add(bundle);
            }
        }
    }

    public Map<String, IService> getServices() {
        return new HashMap<String, IService>(services);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsValidation() {
        return false;
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        Collection<IInterpreterStatus> interpreterStatus = new ArrayList<IInterpreterStatus>();
        // TODO : when the ServiceInterpreter will support service in workspace,
        // we could do validation
        return interpreterStatus;
    }
}
