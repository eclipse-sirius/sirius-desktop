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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.Messages;
import org.eclipse.sirius.common.tools.api.interpreter.ClassLoadingCallback;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IJavaAwareInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.JavaExtensionsManager;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;

/**
 * A specialized interpreter which can only directly invoke Java service methods.
 * 
 * @author pcdavid
 */
public class ServiceInterpreter extends VariableInterpreter implements IJavaAwareInterpreter, org.eclipse.sirius.common.tools.api.interpreter.IInterpreter, IInterpreterProvider {

    /** The Service interpreter prefix. */
    public static final String PREFIX = "service:"; //$NON-NLS-1$

    /**
     * The character used to separate receiver and service.
     */
    public static final String RECEIVER_SEPARATOR = "."; //$NON-NLS-1$

    private static final String EOBJECT_CLASS_NAME = EObject.class.getCanonicalName();

    private static final Pattern SPLIT_PATTERN = Pattern.compile("[(,)]"); //$NON-NLS-1$

    private final Map<Object, Object> properties = new HashMap<>();

    private final Map<String, PolymorphicService> services = new HashMap<>();

    /**
     * Used to retrieve the services instances we create so that we can un-register those.
     */
    private final Map<String, List<PolymorphicService>> qualifiedNameToServices = new HashMap<>();

    private final JavaExtensionsManager javaExtensions = JavaExtensionsManager.createManagerWithOverride();

    private final ClassLoadingCallback callback = new ClassLoadingCallback() {

        @Override
        public void unloaded(String qualifiedName, Class<?> clazz) {
            for (PolymorphicService service : qualifiedNameToServices.get(qualifiedName)) {
                services.remove(service.getName());
            }
            qualifiedNameToServices.remove(qualifiedName);
        }

        @Override
        public void notFound(String qualifiedName) {
            DslCommonPlugin.getDefault().warning(MessageFormat.format(Messages.ServiceInterpreter_javaClassNotFound, qualifiedName), new RuntimeException());
        }

        @Override
        public void loaded(String qualifiedName, Class<?> clazz) {
            registerServiceClass(qualifiedName, clazz);
        }
    };

    /**
     * Create an instance of {@link ServiceInterpreter}.
     */
    public ServiceInterpreter() {
        this.javaExtensions.addClassLoadingCallBack(callback);
    }

    /**
     * Get the receiver variable name if any, none {@link Optional} otherwise.
     * 
     * @param expression
     *            The expression after the {@link ServiceInterpreter#PREFIX}.
     * @return the receiver variable name if any, none Option otherwise.
     */
    public static Optional<String> getReceiverVariableName(String expression) {
        int indexOfServiceName = expression.indexOf(RECEIVER_SEPARATOR);
        if (indexOfServiceName != -1) {
            String receiverVariableName = expression.substring(0, indexOfServiceName);
            return Optional.of(receiverVariableName);
        }
        return Optional.empty();
    }

    @Override
    public IInterpreter createInterpreter() {
        return new ServiceInterpreter();
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public Object evaluate(EObject target, String expression) throws EvaluationException {
        javaExtensions.reloadIfNeeded();
        Object evaluation = null;
        if (target != null && expression != null && expression.startsWith(PREFIX)) {
            String serviceCall = expression.substring(PREFIX.length()).trim();
            Optional<String> receiverVariableName = getReceiverVariableName(serviceCall);
            EObject receiver = target;
            if (receiverVariableName.isPresent()) {
                serviceCall = serviceCall.substring(receiverVariableName.get().length() + 1);
                Object objectReceiver = evaluateVariable(target, receiverVariableName.get().trim());
                if (objectReceiver instanceof EObject) {
                    receiver = (EObject) objectReceiver;
                } else {
                    throw new EvaluationException(
                            MessageFormat.format(Messages.ServiceInterpreter_invalidReceiver, serviceCall, objectReceiver != null ? objectReceiver.getClass().getName() : "null")); //$NON-NLS-1$
                }
            }
            int indexOfParenthesis = serviceCall.indexOf("("); //$NON-NLS-1$
            String serviceName = serviceCall.substring(0, indexOfParenthesis == -1 ? serviceCall.length() : indexOfParenthesis);

            Object[] parameters = new Object[] { receiver };

            if (indexOfParenthesis != -1) {
                String[] values = SPLIT_PATTERN.split(serviceCall);
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
            throw new EvaluationException(MessageFormat.format(Messages.ServiceInterpreter_unknownService, serviceName));
        }
    }

    @Override
    public Collection<Method> getImplementation(String serviceCall) {
        javaExtensions.reloadIfNeeded();
        String serviceName = serviceCall;

        if (services.containsKey(serviceName)) {
            IService service = services.get(serviceName);
            return service.getImplementations();
        }

        return Collections.emptyList();
    }

    @Override
    public void addImport(String dependency) {
        javaExtensions.addImport(dependency);
    }

    private void registerServiceClass(String qualifiedName, Class<?> klass) {
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
                registerService(qualifiedName, new MonomorphicService(serviceInstance, m));
            }
        }
    }

    private void registerService(String qualifiedName, MonomorphicService service) {
        String name = service.getName();
        if (!services.containsKey(name)) {
            PolymorphicService newService = new PolymorphicService(name);
            services.put(name, newService);
            qualifiedNameToServices.putIfAbsent(qualifiedName, new ArrayList<>());
            qualifiedNameToServices.get(qualifiedName).add(newService);
        }
        services.get(name).addImplementer(service);
    }

    /**
     * Checks whether a Java method can be used as a service (in the sense of this interpreter).
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
        return javaExtensions.getImports();
    }

    @Override
    public void removeImport(String dependency) {
        javaExtensions.removeImport(dependency);
    }

    @Override
    public void clearImports() {
        javaExtensions.clearImports();
        services.clear();
        qualifiedNameToServices.clear();
    }

    @Override
    public void setProperty(Object key, Object value) {
        properties.put(key, value);
        if (IInterpreter.FILES.equals(key)) {
            javaExtensions.updateScope((Collection<String>) value);
        }
    }

    /**
     * Return all the known service implementations by name.
     * 
     * @return the service implementations organized by name.
     */
    public Map<String, IService> getServices() {
        /*
         * The callback registered to the java extension manager might update this.services depending on what is loaded.
         * We make sure any pending reload is done before returning this list.
         */
        javaExtensions.reloadIfNeeded();

        return new HashMap<>(services);
    }

    @Override
    public boolean supportsValidation() {
        return false;
    }

    @Override
    public ValidationResult analyzeExpression(IInterpreterContext context, String expression) {
        return new ValidationResult();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.javaExtensions.removeClassLoadingCallBack(callback);
        this.javaExtensions.dispose();
    }
}
