/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.eclipse.emf.common.EMFPlugin;

/**
 * Helper class with bundle-independent code, that can be reused by other I18N
 * classes local to each plug-in.
 * 
 * @author pcdavid
 */
public final class I18N {
    /**
     * Used to mark a {@code public static String} field of a class as an
     * externalized string, whose actual value will depend on the locale used at
     * runtime. The optional value corresponds to the key in the
     * {@code ResourceLocator}; if absent, the name of the field itself is used
     * as key.
     * 
     * @author pcdavid
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface TranslatableMessage {
        /**
         * The (optional) value of the message key. If absent, the key is
         * assumed to be the same as the Java field's name.
         */
        String[] value() default {};
    }

    private I18N() {
        // Prevent instantiation
    }

    /**
     * Initializes the value of a class's {@code TranslatableMessage}s using the
     * specified plugin as {@code ResourceLocator}.
     * 
     * @param messagesClass
     *            the class which defines the fields to initialize.
     * @param plugin
     *            the plugin from which to obtain the localized value of the
     *            fields.
     */
    public static void initializeMessages(final Class<?> messagesClass, final EMFPlugin plugin) {
        if (System.getSecurityManager() == null) {
            load(plugin, messagesClass);
            return;
        }
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                load(plugin, messagesClass);
                return null;
            }
        });
    }

    private static void load(EMFPlugin plugin, Class<?> messagesClass) {
        for (Field field : messagesClass.getDeclaredFields()) {
            if (isMessageField(field)) {
                initialize(field, plugin);
            }
        }
    }

    private static boolean isMessageField(Field field) {
        int mods = field.getModifiers();
        boolean modsOK = Modifier.isPublic(mods) && Modifier.isStatic(mods) && !Modifier.isFinal(mods);
        return modsOK && field.isAnnotationPresent(I18N.TranslatableMessage.class) && field.getType() == String.class;
    }

    private static void initialize(Field field, EMFPlugin plugin) {
        String key = getKey(field);
        String value = plugin.getString(key);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(null, value);
        } catch (IllegalArgumentException e) {
            plugin.log(e);
        } catch (IllegalAccessException e) {
            plugin.log(e);
        }
    }

    private static String getKey(Field field) {
        I18N.TranslatableMessage annot = field.getAnnotation(I18N.TranslatableMessage.class);
        String[] key = annot.value();
        if (key == null || key.length == 0) {
            key = new String[] { field.getName() };
        }
        return key[0];
    }
}
