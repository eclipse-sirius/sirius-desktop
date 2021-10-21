/*******************************************************************************
 * Copyright (c) 2014 Obeo.
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
package org.eclipse.sirius.common.ui.tools.internal.preference;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.sirius.common.ui.Messages;

/**
 * Helper class to manage configurable settings needed in the code but which are
 * stored in the Eclipse preferences (and which can change dynamically).
 * <p>
 * Expected usage is to create a subclass, define the appropriate
 * int/boolean/String fields in the sub-class, and call the {@code bind*()}
 * methods in the constructor to setup the links between the fields and the
 * properties in the store.
 * <p>
 * Client code must call {@link #dispose()} when they are done with this to
 * ensure all resources are properly disposed and avoid leaks.
 * 
 * @author pcdavid
 */
public class DynamicConfigurationHelper implements IPropertyChangeListener {
    private final IPreferenceStore store;

    private final Map<String, String> bindings = new HashMap<>();

    /**
     * Create a new configuration which listens to the specifed store.
     * 
     * @param store
     *            the preference store to listen to.
     */
    public DynamicConfigurationHelper(IPreferenceStore store) {
        this.store = Objects.requireNonNull(store);
        store.addPropertyChangeListener(this);
    }

    /**
     * Binds the value of a integer property from the store to a field in this
     * object.
     * 
     * @param propertyName
     *            the name of the property in the store.
     * @param fieldName
     *            the name of the int field in this object which will get the
     *            value from the store.
     */
    protected void bindInt(String propertyName, String fieldName) {
        bindings.put(propertyName, fieldName);
        int value = store.getInt(propertyName);
        setInt(fieldName, value);
    }

    /**
     * Binds the value of a boolean property from the store to a field in this
     * object.
     * 
     * @param propertyName
     *            the name of the property in the store.
     * @param fieldName
     *            the name of the boolean field in this object which will get
     *            the value from the store.
     */
    protected void bindBoolean(String propertyName, String fieldName) {
        bindings.put(propertyName, fieldName);
        boolean value = store.getBoolean(propertyName);
        setBoolean(fieldName, value);
    }

    /**
     * Binds the value of a string property from the store to a field in this
     * object.
     * 
     * @param propertyName
     *            the name of the property in the store.
     * @param fieldName
     *            the name of the String field in this object which will get the
     *            value from the store.
     */
    protected void bindString(String propertyName, String fieldName) {
        bindings.put(propertyName, fieldName);
        String value = store.getString(propertyName);
        setString(fieldName, value);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        String property = event.getProperty();
        String fieldName = bindings.get(property);
        if (fieldName != null) {
            Object rawValue = event.getNewValue();
            if (rawValue instanceof Integer) {
                setInt(fieldName, ((Integer) rawValue).intValue());
            } else if (rawValue instanceof Boolean) {
                setBoolean(fieldName, ((Boolean) rawValue).booleanValue());
            } else if (rawValue instanceof String) {
                setString(fieldName, (String) rawValue);
            }
        }
    }

    /**
     * Unregisters this object as a listener on the preference store.
     */
    public void dispose() {
        store.removePropertyChangeListener(this);
    }

    private void setInt(String field, int value) throws IllegalArgumentException {
        Field f = getAccessibleField(this.getClass(), field);
        try {
            f.setInt(this, value);
        } catch (IllegalAccessException e) {
            // Should not happen: if the field of is not accessible,
            // getAccessibleField should already have thrown a
            // SecurityException.
        }
    }

    private void setBoolean(String field, boolean value) throws IllegalArgumentException {
        Field f = getAccessibleField(this.getClass(), field);
        try {
            f.setBoolean(this, value);
        } catch (IllegalAccessException e) {
            // Should not happen: if the field of is not accessible,
            // getAccessibleField should already have thrown a
            // SecurityException.
        }
    }

    private void setString(String field, String value) throws IllegalArgumentException {
        Field f = getAccessibleField(this.getClass(), field);
        try {
            f.set(this, value);
        } catch (IllegalAccessException e) {
            // Should not happen: if the field of is not accessible,
            // getAccessibleField should already have thrown a
            // SecurityException.
        }
    }

    private Field getAccessibleField(Class<?> klass, String fieldName) {
        Field result = null;
        if (klass != null) {
            for (Field f : klass.getDeclaredFields()) {
                if (fieldName.equals(f.getName())) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    result = f;
                    break;
                }
            }
            if (result == null) {
                result = getAccessibleField(klass.getSuperclass(), fieldName);
            }
        }
        if (result != null) {
            return result;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.DynamicConfigurationHelper_unknownField, fieldName, klass.getName()));
        }
    }
}
