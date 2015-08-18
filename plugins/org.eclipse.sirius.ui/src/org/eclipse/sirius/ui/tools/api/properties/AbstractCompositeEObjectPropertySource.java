/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * A property source that is able to manage several {@link EObject}s. This class
 * maintains a map of {@link EObject}s with many {@link IPropertySource}s. It
 * means that you can merge many {@link IPropertySource}s for one
 * {@link EObject}. You can also add several Objects in this property source.
 * Clients should only use the operation
 * {@link #addPropertySource(EObject, IPropertySource)}.
 * 
 * @author ymortier
 */
public abstract class AbstractCompositeEObjectPropertySource implements IPropertySource {

    /** the sources. */
    private Map<EObjectIndexer, IPropertySource> propertySources;

    /** names. */
    private Map<EObject, String> categoryNames;

    /**
     * Creates a new <code>CompositeEObjectPropertySource</code>.
     */
    public AbstractCompositeEObjectPropertySource() {
        this.propertySources = new TreeMap<EObjectIndexer, IPropertySource>();
        this.categoryNames = new HashMap<EObject, String>();
    }

    /**
     * Add a new property source.
     * 
     * @param eObject
     *            current {@link EObject}.
     * @param propertySource
     *            new {@link IPropertySource}.
     */
    public void addPropertySource(final EObject eObject, final IPropertySource propertySource) {
        //
        // We must keep the order of insertion
        EObjectIndexer eObjectIndexer = this.findEObjectIndexerFromEObject(eObject);
        if (eObjectIndexer != null) {
            final IPropertySource existedPropertySource = this.propertySources.get(eObjectIndexer);
            final DelegatingCompositePropertySource delegatingCompositePropertySource = new DelegatingCompositePropertySource();
            delegatingCompositePropertySource.addPropertySource(existedPropertySource);
            delegatingCompositePropertySource.addPropertySource(propertySource);
            this.propertySources.put(eObjectIndexer, delegatingCompositePropertySource);
        } else {
            eObjectIndexer = new EObjectIndexer(this.propertySources.size(), eObject);
            this.propertySources.put(eObjectIndexer, propertySource);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        return this.propertySources;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        final List<IPropertyDescriptor> propertyDescriptors = new LinkedList<IPropertyDescriptor>();
        final Iterator<Entry<EObjectIndexer, IPropertySource>> iterPropertySourcesEntries = this.propertySources.entrySet().iterator();
        while (iterPropertySourcesEntries.hasNext()) {
            final Entry<EObjectIndexer, IPropertySource> entry = iterPropertySourcesEntries.next();
            final IPropertySource currentSource = entry.getValue();
            final EObject editableValue = entry.getKey().getEObject();
            propertyDescriptors.addAll(AbstractCompositeEObjectPropertySource.getDecoratedPropertiesDescriptor(currentSource, buildCategoryName(editableValue), editableValue));
        }
        return propertyDescriptors.toArray(new IPropertyDescriptor[propertyDescriptors.size()]);
    }

    /**
     * Returns the property descriptors of the specified property source. The
     * property descriptors are decorated with
     * <code>PropertyDescriptorDecorator</code>.
     * 
     * @param propertySource
     *            the property source.
     * @param categoryName
     *            the name of the category.
     * @param eObject
     *            the EObject.
     * @return the property descripors of the specified property source.
     */
    private static List<IPropertyDescriptor> getDecoratedPropertiesDescriptor(final IPropertySource propertySource, final String categoryName, final EObject eObject) {
        final IPropertyDescriptor[] descriptors = propertySource.getPropertyDescriptors();
        final List<IPropertyDescriptor> descriptorsList = new ArrayList<IPropertyDescriptor>(descriptors.length);
        for (IPropertyDescriptor descriptor : descriptors) {
            descriptorsList.add(new PropertyDescriptorDecorator(descriptor, categoryName, eObject));
        }
        return descriptorsList;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(final Object id) {
        final Identifier identifier = (Identifier) id;
        return getPropertySource(identifier).getPropertyValue(identifier.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(final Object id) {
        final Identifier identifier = (Identifier) id;
        return getPropertySource(identifier).isPropertySet(identifier.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(final Object id) {
        final Identifier identifier = (Identifier) id;
        getPropertySource(identifier).resetPropertyValue(identifier.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    public void setPropertyValue(final Object id, final Object value) {
        final Identifier identifier = (Identifier) id;
        getPropertySource(identifier).setPropertyValue(identifier.getId(), value);
    }

    /**
     * Returns the name of the category of this EObject.
     * 
     * @param eObject
     *            The eObject
     * @return A category name
     */
    protected String buildCategoryName(final EObject eObject) {
        if (categoryNames.containsKey(eObject)) {
            categoryNames.remove(eObject);
        }
        /*
         * Try with AdapterFactory.
         */
        String result = null;

        final AdapterFactory factory = getItemProvidersAdapterFactory();
        final IItemLabelProvider labelProvider = (IItemLabelProvider) factory.adapt(eObject, IItemLabelProvider.class);
        if (labelProvider != null) {
            result = labelProvider.getText(eObject);
        } else {
            result = eObject.eClass().getName();
            final EStructuralFeature nameStructuralFeature = eObject.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
            if (nameStructuralFeature != null) {
                final String name = (String) eObject.eGet(nameStructuralFeature);
                if (name != null) {
                    result = result + " " + name; //$NON-NLS-1$
                }
            }
        }
        if (result == null) {
            result = ""; //$NON-NLS-1$
        }
        String resultTmp = result;
        int i = 2;
        while (categoryNames.values().contains(resultTmp)) {
            resultTmp = result + " " + i++; //$NON-NLS-1$
        }
        result = resultTmp;
        categoryNames.put(eObject, result);
        return result;
    }

    /**
     * Return the specific adapter factory.
     * 
     * @return The item providers adapter factory
     */
    protected abstract AdapterFactory getItemProvidersAdapterFactory();

    /**
     * Return the property source.
     * 
     * @param identifier
     *            The identifier
     * @return The propertySource corresponding to this identifier
     */
    protected IPropertySource getPropertySource(final Identifier identifier) {
        IPropertySource result = null;
        final EObjectIndexer indexer = this.findEObjectIndexerFromEObject(identifier.getEObject());
        if (indexer != null) {
            result = this.propertySources.get(indexer);
        }
        return result;
    }

    /**
     * Decorates an {@link IPropertyDescriptor}. Adds the ability to test if the
     * property is editable.
     * 
     * @author ymortier
     */
    private static class PropertyDescriptorDecorator implements IPropertyDescriptor {

        /** The category. */
        private String category;

        /**
         * The initial descriptor.
         */
        private IPropertyDescriptor decorated;

        /**
         * The identifier.
         */
        private Identifier identifier;

        /**
         * 
         * @param decorated
         * @param category
         * @param eObject
         */
        public PropertyDescriptorDecorator(final IPropertyDescriptor decorated, final String category, final EObject eObject) {
            this.category = category;
            this.decorated = decorated;
            this.identifier = new Identifier(eObject, this.decorated.getId());
        }

        /**
         * @param parent
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
         */
        public CellEditor createPropertyEditor(final Composite parent) {
            CellEditor cellEditor = null;
            if (getPermissionAuthority().canEditInstance(identifier.getEObject())) {
                cellEditor = decorated.createPropertyEditor(parent);
            }
            return cellEditor;
        }

        private IPermissionAuthority getPermissionAuthority() {
            final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(identifier.getEObject());
            return accessor.getPermissionAuthority();
        }

        /**
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getCategory()
         */
        public String getCategory() {
            return this.category;
        }

        /**
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDescription()
         */
        public String getDescription() {
            return decorated.getDescription();
        }

        /**
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDisplayName()
         */
        public String getDisplayName() {
            return decorated.getDisplayName();
        }

        /**
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getFilterFlags()
         */
        public String[] getFilterFlags() {
            return decorated.getFilterFlags();
        }

        /**
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getHelpContextIds()
         */
        public Object getHelpContextIds() {
            return decorated.getHelpContextIds();
        }

        /**
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getId()
         */
        public Object getId() {
            return this.identifier;
        }

        /**
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getLabelProvider()
         */
        public ILabelProvider getLabelProvider() {
            return decorated.getLabelProvider();
        }

        /**
         * @param anotherProperty
         * @return
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#isCompatibleWith(org.eclipse.ui.views.properties.IPropertyDescriptor)
         */
        public boolean isCompatibleWith(final IPropertyDescriptor anotherProperty) {
            return decorated.isCompatibleWith(anotherProperty);
        }

    }

    /**
     * A specific Identifier for EObject.
     */
    protected static class Identifier {
        /** The eObject. */
        private EObject eObject;

        /** The id. */
        private Object id;

        /**
         * Creates a new <code>Identifier</code>.
         * 
         * @param eObject
         *            the EObject.
         * @param id
         *            the feature.
         */
        public Identifier(final EObject eObject, final Object id) {
            if (eObject == null) {
                throw new IllegalArgumentException("eObject is null");
            }
            if (id == null) {
                throw new IllegalArgumentException("id is null");
            }
            this.eObject = eObject;
            this.id = id;
        }

        /**
         * Return the object.
         * 
         * @return the object
         */
        public EObject getEObject() {
            return eObject;
        }

        /**
         * Return the id.
         * 
         * @return the id.
         */
        public Object getId() {
            return id;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof Identifier) {
                final Identifier identifier = (Identifier) obj;
                return this.eObject.equals(identifier.eObject) && this.id.equals(identifier.id);
            }
            return false;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return this.eObject.hashCode();
        }
    }

    /**
     * A implementation of {@link IPropertySource} that is a composition of many
     * {@link IPropertySource}.
     */
    private static final class DelegatingCompositePropertySource implements IPropertySource {

        /** The property sources. */
        private Set<IPropertySource> propertySources = new HashSet<IPropertySource>();

        /**
         * Add a new {@link IPropertySource}
         * 
         * @param propertySource
         *            new {@link IPropertySource}.
         */
        public void addPropertySource(final IPropertySource propertySource) {
            this.propertySources.add(propertySource);
        }

        /**
         * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
         */
        public Object getEditableValue() {
            return this.propertySources;
        }

        /**
         * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
         */
        public IPropertyDescriptor[] getPropertyDescriptors() {
            final List<IPropertyDescriptor> propertyDescriptors = new LinkedList<IPropertyDescriptor>();
            final Iterator<IPropertySource> iterPropertySources = this.propertySources.iterator();
            while (iterPropertySources.hasNext()) {
                final IPropertySource propertySource = iterPropertySources.next();
                propertyDescriptors.addAll(Arrays.asList(propertySource.getPropertyDescriptors()));
            }
            return propertyDescriptors.toArray(new IPropertyDescriptor[propertyDescriptors.size()]);
        }

        /**
         * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
         */
        public Object getPropertyValue(final Object id) {
            final IPropertySource propertySource = getPropertySourceFor(id);
            if (propertySource != null) {
                return propertySource.getPropertyValue(id);
            }
            return null;
        }

        /**
         * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
         */
        public boolean isPropertySet(final Object id) {
            final IPropertySource propertySource = getPropertySourceFor(id);
            if (propertySource != null) {
                return propertySource.isPropertySet(id);
            }
            return false;
        }

        /**
         * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
         */
        public void resetPropertyValue(final Object id) {
            final IPropertySource propertySource = getPropertySourceFor(id);
            if (propertySource != null) {
                propertySource.resetPropertyValue(id);
            }
        }

        /**
         * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
         *      java.lang.Object)
         */
        public void setPropertyValue(final Object id, final Object value) {
            final IPropertySource propertySource = getPropertySourceFor(id);
            if (propertySource != null) {
                propertySource.setPropertyValue(id, value);
            }
        }

        private IPropertySource getPropertySourceFor(final Object id) {
            IPropertySource result = null;
            final Iterator<IPropertySource> iterPropertySources = this.propertySources.iterator();
            while (iterPropertySources.hasNext() && result == null) {
                final IPropertySource propertySource = iterPropertySources.next();
                final IPropertyDescriptor[] descriptors = propertySource.getPropertyDescriptors();
                for (int i = 0; i < descriptors.length && result == null; i++) {
                    if (descriptors[i].getId().equals(id)) {
                        result = propertySource;
                    }
                }
            }
            return result;
        }
    }

    /**
     * This class is able to map an {@link EObject} with an index.
     * 
     * @author ymortier
     */
    private static class EObjectIndexer implements Comparable {

        /** The index. */
        private int index;

        /** The eObject. */
        private EObject eObject;

        /**
         * Creates a new <code>EObjectIndexer</code> with the specified index
         * and eObject.
         * 
         * @param index
         *            the index.
         * @param eObject
         *            the eObject.
         * @throws IllegalArgumentException
         *             if <code>eObject</code> is <code>null</code>.
         */
        public EObjectIndexer(final int index, final EObject eObject) throws IllegalArgumentException {
            if (eObject == null) {
                throw new IllegalArgumentException();
            }
            this.index = index;
            this.eObject = eObject;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(final Object o) {
            final EObjectIndexer eObjectIndexer = (EObjectIndexer) o;
            return this.index - eObjectIndexer.index;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return this.index;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object obj) {
            boolean result = false;
            if (obj instanceof EObjectIndexer) {
                final EObjectIndexer eObjectIndexer = (EObjectIndexer) obj;
                result = this.index == eObjectIndexer.index;
            }
            return result;
        }

        /**
         * Returns the eObject.
         * 
         * @return the eObject.
         */
        public EObject getEObject() {
            return eObject;
        }
    }

    /**
     * Finds the {@link EObjectIndexer} that corresponds to the specified
     * {@link EObject}.
     * 
     * @param eObject
     *            the eObject.
     * @return the {@link EObjectIndexer} that corresponds to the specified
     *         {@link EObject}.
     */
    private EObjectIndexer findEObjectIndexerFromEObject(final EObject eObject) {
        EObjectIndexer result = null;
        final Iterator<EObjectIndexer> iterEObjectIndexer = this.propertySources.keySet().iterator();
        while (iterEObjectIndexer.hasNext() && result == null) {
            final EObjectIndexer currentIndexer = iterEObjectIndexer.next();
            if (currentIndexer.getEObject().equals(eObject)) {
                result = currentIndexer;
            }
        }
        return result;
    }

    /**
     * Return the categoryNames.
     * 
     * @return the categoryNames
     */
    protected Map<EObject, String> getCategoryNames() {
        return categoryNames;
    }
}
