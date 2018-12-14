/*******************************************************************************
 * Copyright (c) 2011-2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.api.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * An helper for all reflection managment.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public final class ReflectionHelper {

    /**
     * Default constructor to avoid instantiation.
     */
    private ReflectionHelper() {

    }

    /**
     * Modify the visibility of a constructor.
     * 
     * @param classToModify
     *            The class to modify.
     * @param parameterTypes
     *            The parameters array to find the constructor
     * @return The modify constructor
     * @throws SecurityException
     *             In case of problem
     * @throws NoSuchMethodException
     *             In case of problem
     */
    public static Constructor setConstructorVisible(Class<? extends Object> classToModify, Class<?>... parameterTypes) throws SecurityException, NoSuchMethodException {
        Constructor osConstructor = classToModify.getDeclaredConstructor(parameterTypes);
        osConstructor.setAccessible(true);
        return osConstructor;
    }

    /**
     * Modify the visibility of a field.
     * 
     * @param classToModify
     *            The class to modify.
     * @param fieldName
     *            The name of the field to modify
     * @return The modified field
     * @throws SecurityException
     *             In case of problem
     * @throws NoSuchFieldException
     *             In case of problem
     */
    public static Field setFieldVisible(Class<? extends Object> classToModify, String fieldName) throws SecurityException, NoSuchFieldException {
        Field field = null;
        try {
            field = classToModify.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (SecurityException e) {
            // DO nothing
        } catch (NoSuchFieldException e) {
            if (classToModify.getSuperclass() != null) {
                field = setFieldVisible(classToModify.getSuperclass(), fieldName);
            }
        }
        return field;
    }

    /**
     * Invoke a method.
     * 
     * @param object
     *            The object on which we must invoke method
     * @param methodName
     *            The name of the method to invoke
     * @param parameterTypes
     *            The types of the parameters
     * @param parameters
     *            The parameters of the method
     * @throws SecurityException
     *             In case of problem
     * @throws NoSuchMethodException
     *             In case of problem
     * @throws IllegalArgumentException
     *             In case of problem
     * @throws IllegalAccessException
     *             In case of problem
     * @throws InvocationTargetException
     *             In case of problem
     */
    // CHECKSTYLE:OFF
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        // CHECKSTYLE:ON
        Method method = object.getClass().getMethod(methodName, parameterTypes);
        return method.invoke(object, parameters);
    }

    /**
     * Modify the visibility of a constructor.
     * 
     * @param classToModify
     *            The class to modify.
     * @param parameterTypes
     *            The parameters array to find the constructor
     * @return an option with the Constructor if there is no exception, an empty
     *         option otherwise.
     */
    public static Optional<Constructor> setConstructorVisibleWithoutException(Class<? extends Object> classToModify, Class<?>... parameterTypes) {
        try {
            return Optional.ofNullable(setConstructorVisible(classToModify, parameterTypes));
        } catch (SecurityException | NoSuchMethodException e) {
            // DO nothing
        }
        return Optional.empty();
    }

    /**
     * Modify the visibility of a field.
     * 
     * @param classToModify
     *            The class to modify.
     * @param fieldName
     *            The name of the field to modify
     * @return an option with the Constructor if there is no exception, an empty
     *         option otherwise.
     */
    public static Optional<Field> setFieldVisibleWithoutException(Class<? extends Object> classToModify, String fieldName) {
        try {
            return Optional.ofNullable(setFieldVisible(classToModify, fieldName));
        } catch (SecurityException | NoSuchFieldException e) {
            // DO nothing
        }
        return Optional.empty();
    }

    /**
     * Invoke a method.
     * 
     * @param object
     *            The object on which we must invoke method
     * @param methodName
     *            The name of the method to invoke
     * @param parameterTypes
     *            The type of the parameters
     * @param parameters
     *            The parameters of the method
     * @return true if the method is invoke without exception, false otherwise.
     */
    public static boolean invokeMethodWithoutException(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        try {
            invokeMethod(object, methodName, parameterTypes, parameters);
            return true;
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Do nothing
        }
        return false;
    }

    /**
     * Invoke a method and return the result.
     * 
     * @param object
     *            The object on which we must invoke method
     * @param methodName
     *            The name of the method to invoke
     * @param parameterTypes
     *            The type of the parameters
     * @param parameters
     *            The parameters of the method
     * @return the result if the method is invoke without exception, null
     *         otherwise.
     */
    public static Object invokeMethodWithoutExceptionWithReturn(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        try {
            return invokeMethod(object, methodName, parameterTypes, parameters);
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Do nothing
        }
        return null;
    }

    /**
     * Invoke a method and return the result.
     * 
     * @param object
     *            The object on which we must invoke method
     * @param aClass
     *            The class which declares the method
     * @param methodName
     *            The name of the method to invoke
     * @param parameterTypes
     *            The type of the parameters
     * @param parameters
     *            The parameters of the method
     * @param setVisible
     *            true to set the method visible before calling.
     * @return the result if the method is invoke without exception, null otherwise.
     */
    public static Object invokeMethodWithoutExceptionWithReturn(Object object, Class aClass, String methodName, Class<?>[] parameterTypes, Object[] parameters, boolean setVisible) {
        try {
            return invokeMethod(object, aClass, methodName, parameterTypes, parameters, setVisible);
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Do nothing
        }
        return null;
    }

    /**
     * Get the class corresponding to his name.
     * 
     * @param className
     *            The name of the searched class
     * @return an empty option if there is no corresponding class, an option with the corresponding class otherwise.
     */
    public static Optional<Class> getClassForNameWithoutException(String className) {
        try {
            Class foundClass = Class.forName(className);
            return Optional.of(foundClass);
        } catch (ClassNotFoundException e) {
            // Do nothing
        }
        return Optional.empty();
    }

    /**
     * Return a new instance of a class.
     * 
     * @param className
     *            The name of the class to instantiate
     * @param parameterTypes
     *            The types of the parameter
     * @param parameters
     *            The parameters of the constructor to use
     * @return an empty option if the instantiation failed, an option with the
     *         new instance otherwise.
     */
    public static Optional<Object> instantiateWithoutException(String className, Class<?>[] parameterTypes, Object[] parameters) {
        Optional<Class> foundClass = ReflectionHelper.getClassForNameWithoutException(className);
        if (foundClass.isPresent()) {
            Optional<Constructor> osConstructor = ReflectionHelper.setConstructorVisibleWithoutException(foundClass.get(), parameterTypes);
            if (osConstructor.isPresent()) {
                try {
                    Object object = osConstructor.get().newInstance(parameters);
                    return Optional.of(object);
                } catch (IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    // Do nothing
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Set a new value to a field of a class.
     * 
     * @param instanceToModify
     *            The instance to modify.
     * @param fieldName
     *            The name of the field to modify
     * @param newValue
     *            The new value for this field
     * @return true if the field is set, false otherwise
     */
    public static boolean setFieldValueWithoutException(Object instanceToModify, String fieldName, int newValue) {
        Optional<Field> field = ReflectionHelper.setFieldVisibleWithoutException(instanceToModify.getClass(), fieldName);
        if (field.isPresent()) {
            try {
                field.get().setInt(instanceToModify, newValue);
                return true;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
        return false;
    }

    /**
     * Set a new value to a field of a class.
     * 
     * @param instanceToModify
     *            The instance to modify.
     * @param fieldName
     *            The name of the field to modify
     * @param newValue
     *            The new value for this field
     * @param classToModify
     *            the declared class of the field
     * @return true if the field is set, false otherwise
     */
    public static boolean setFieldValueWithoutException(Object instanceToModify, String fieldName, Object newValue, Class<? extends Object> classToModify) {
        Optional<Field> field = ReflectionHelper.setFieldVisibleWithoutException(classToModify, fieldName);
        if (field.isPresent()) {
            try {
                field.get().set(instanceToModify, newValue);
                return true;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
        return false;
    }

    /**
     * Set a new value to a field of a class.
     * 
     * @param instanceToModify
     *            The instance to modify.
     * @param fieldName
     *            The name of the field to modify
     * @param newValue
     *            The new value for this field
     * @return true if the field is set, false otherwise
     */
    public static boolean setFieldValueWithoutException(Object instanceToModify, String fieldName, Object newValue) {
        Optional<Field> field = ReflectionHelper.setFieldVisibleWithoutException(instanceToModify.getClass(), fieldName);
        if (field.isPresent()) {
            try {
                field.get().set(instanceToModify, newValue);
                return true;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
        return false;
    }

    /**
     * Get the instance value of a field of a class (even if this field is
     * protected).
     * 
     * @param instance
     *            The instance class.
     * @param fieldName
     *            The name of the field
     * @return instance value of the field or an empty option if the field does
     *         not exist.
     */
    public static Optional<Object> getFieldValueWithoutException(Object instance, String fieldName) {
        Optional<Field> field = ReflectionHelper.setFieldVisibleWithoutException(instance.getClass(), fieldName);
        if (field.isPresent()) {
            try {
                return Optional.ofNullable(field.get().get(instance));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
        return Optional.empty();
    }

    /**
     * Get the value of a field of a class (even if this field is protected).
     * 
     * @param klass
     *            The class.
     * @param fieldName
     *            The name of the field
     * @return class static value of the field or an empty option if the field
     *         does not exist.
     */
    public static Optional<Object> getFieldValueWithoutException(Class<? extends Object> klass, String fieldName) {
        Optional<Field> field = ReflectionHelper.setFieldVisibleWithoutException(klass, fieldName);
        if (field.isPresent()) {
            try {
                return Optional.ofNullable(field.get().get(null));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
        return Optional.empty();
    }

    /**
     * Get the value of a field of a class (even if this field is protected).
     * 
     * @param instance
     *            The instance class.
     * @param fieldName
     *            The name of the field
     * @param classToModify
     *            the class of the declared field
     * @return true if the field is set, false otherwise
     */
    public static Optional<Object> getFieldValueWithoutException(Object instance, String fieldName, Class<? extends Object> classToModify) {
        Optional<Field> field = ReflectionHelper.setFieldVisibleWithoutException(classToModify, fieldName);
        if (field.isPresent()) {
            try {
                return Optional.ofNullable(field.get().get(instance));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
        return Optional.empty();
    }
    

    /**
     * Invoke a method.
     * 
     * @param object
     *            The object on which we must invoke method
     * @param aClass
     *            The class which declares the method
     * @param methodName
     *            The name of the method to invoke
     * @param parameterTypes
     *            The type of the parameters
     * @param parameters
     *            The parameters of the method
     * @param setVisible
     *            true to set the method visible before calling.
     * @return true if the method is invoke without exception, false otherwise.
     */
    public static boolean invokeMethodWithoutException(Object object, Class aClass, String methodName, Class<?>[] parameterTypes, Object[] parameters, boolean setVisible) {
        try {
            invokeMethod(object, aClass, methodName, parameterTypes, parameters, setVisible);
            return true;
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Do nothing
        }
        return false;
    }

    /**
     * Invoke a method.
     * 
     * @param object
     *            The object on which we must invoke method
     * @param aClass
     *            The class which declares the method
     * @param methodName
     *            The name of the method to invoke
     * @param parameterTypes
     *            The types of the parameters
     * @param parameters
     *            The parameters of the method
     * @param setVisible
     *            true to set the method visible before calling.
     * @throws SecurityException
     *             In case of problem
     * @throws NoSuchMethodException
     *             In case of problem
     * @throws IllegalArgumentException
     *             In case of problem
     * @throws IllegalAccessException
     *             In case of problem
     * @throws InvocationTargetException
     *             In case of problem
     */
    // CHECKSTYLE:OFF
    public static Object invokeMethod(Object object, Class aClass, String methodName, Class<?>[] parameterTypes, Object[] parameters, boolean setVisible)
            throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        // CHECKSTYLE:ON
        Method method = aClass.getDeclaredMethod(methodName, parameterTypes);
        if (setVisible) {
            method.setAccessible(true);
        }
        return method.invoke(object, parameters);
    }
}
