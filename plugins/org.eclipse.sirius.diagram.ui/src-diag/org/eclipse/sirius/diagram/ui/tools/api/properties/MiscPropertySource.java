/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.properties;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * This property source uses the reflection Java API to display debug
 * informations.
 * 
 * @author ymortier
 */
public class MiscPropertySource implements IPropertySource {

    /** The selected object. */
    private Object selectedObject;

    /**
     * Create a new {@link MiscPropertySource}.
     * 
     * @param selectedObject
     *            the edit part to analyse.
     */
    public MiscPropertySource(final Object selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    @Override
    public Object getEditableValue() {
        return this.selectedObject;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        final List<Field> allFields = new LinkedList<Field>();
        Field[] fields = this.selectedObject.getClass().getDeclaredFields();
        allFields.addAll(Arrays.asList(fields));
        Class<?> currentClass = this.selectedObject.getClass().getSuperclass();
        while (currentClass != Object.class) {
            fields = currentClass.getDeclaredFields();
            allFields.addAll(Arrays.asList(fields));
            currentClass = currentClass.getSuperclass();
        }
        final PropertyDescriptor[] propertyDescriptors = new ReflectPropertyDescriptor[allFields.size()];
        final String categName = this.selectedObject.getClass().getName().substring(this.selectedObject.getClass().getName().lastIndexOf('.') + 1);
        final Iterator<Field> iterFields = allFields.iterator();
        int i = 0;
        while (iterFields.hasNext()) {
            final Field currentField = iterFields.next();
            if (!Modifier.isStatic(currentField.getModifiers())) {
                final ReflectPropertyDescriptor reflectPropertyDescriptor = new ReflectPropertyDescriptor(currentField, currentField.getName(), categName);
                propertyDescriptors[i++] = reflectPropertyDescriptor;
            }
        }
        final int realSize = i;
        final IPropertyDescriptor[] descriptors = new ReflectPropertyDescriptor[realSize];
        System.arraycopy(propertyDescriptors, 0, descriptors, 0, realSize);
        return descriptors;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    @Override
    public Object getPropertyValue(final Object id) {

        Object value = null;
        if (id instanceof Field) {
            final Field field = (Field) id;
            final boolean oldAccessible = field.isAccessible();
            try {
                field.setAccessible(true);
                try {
                    value = field.get(this.selectedObject);
                } catch (final IllegalArgumentException e) {
                    SiriusTransPlugin.getPlugin().error(Messages.MiscPropertySource_errorGettingValueMsg, e);
                } catch (final IllegalAccessException e) {
                    SiriusTransPlugin.getPlugin().error(Messages.MiscPropertySource_errorGettingValueMsg, e);
                }
            } finally {
                field.setAccessible(oldAccessible);
            }
        }
        return value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    @Override
    public boolean isPropertySet(final Object id) {
        // does nothing
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    @Override
    public void resetPropertyValue(final Object id) {
        // does nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void setPropertyValue(final Object id, final Object value) {
        // does nothing.
    }

    /**
     * The reflect property descriptor. Use Reflect Java API to retrieves meta
     * informations.
     * 
     * @author ymortier
     */
    private static class ReflectPropertyDescriptor extends PropertyDescriptor {

        /** The name of the category. */
        private String category;

        /**
         * Create a new descriptor.
         * 
         * @param id
         *            the id of the property.
         * @param displayName
         *            the name to display.
         * @param category
         *            the name of the category.
         */
        ReflectPropertyDescriptor(final Object id, final String displayName, final String category) {
            super(id, displayName);
            this.category = category;
        }

        /**
         * @see org.eclipse.ui.views.properties.PropertyDescriptor#getCategory()
         */
        @Override
        public String getCategory() {
            return category;
        }

    }
}
