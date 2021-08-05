/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.properties.ExtendedPropertySource;
import org.eclipse.sirius.ecore.extender.business.api.accessor.AbstractMetamodelExtender;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtensionFeatureDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ext.emf.EReferencePredicate;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.uml2.uml.VisibilityKind;
import org.junit.Assert;

import junit.framework.TestCase;

/**
 * Test case for {@link ExtendedPropertySource}.
 * 
 * @author ymortier
 */
public class ExtendedPropertySourceTestCase extends TestCase {

    private static final String EXTENDED_FEATURE_NAME = "extendedFeature";

    /** The model. */
    private Viewpoint model;

    /** The diagram. */
    private DSemanticDiagram diagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SiriusPlugin.getDefault().getModelAccessorRegistry().setNullResourceModelAccessor(new ModelAccessor());
        model = DescriptionFactory.eINSTANCE.createViewpoint();
        diagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        diagram.setTarget(model);
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor((EObject) null);
        if (modelAccessor != null) {
            modelAccessor.dispose();
            SiriusPlugin.getDefault().getModelAccessorRegistry().setNullResourceModelAccessor(null);
        }
        model = null;
        diagram = null;
    }

    /**
     * Tests the property source with an extended feature which have the String type.
     * 
     * @throws FeatureNotFoundException
     *             if the test fails.
     * @throws LockedInstanceException
     *             if the test fails.
     */
    public void testStringExtendedFeature() throws LockedInstanceException, FeatureNotFoundException {
        StringMetaModelExtender stringModelExtender = new StringMetaModelExtender();
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).addExtender(stringModelExtender, ExtenderConstants.HIGHEST_PRIORITY);
        //
        // sets null.
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).eSet(this.model, ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME, null);

        ExtendedPropertySource propertySource = new ExtendedPropertySource(this.diagram, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
        for (IPropertyDescriptor descriptor : propertySource.getPropertyDescriptors()) {
            Object value = propertySource.getPropertyValue(descriptor.getId());
            Assert.assertEquals("The value should be the empty string", "", value);
            String text = descriptor.getLabelProvider().getText(value);
            Assert.assertEquals("The string should be the empty String", "", text);
        }

        //
        // Sets the value
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).eSet(this.model, ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME, "Test");
        for (IPropertyDescriptor descriptor : propertySource.getPropertyDescriptors()) {
            Object value = propertySource.getPropertyValue(descriptor.getId());
            Assert.assertEquals("The value should be \"Test\"", "Test", value);
            String text = descriptor.getLabelProvider().getText(value);
            Assert.assertEquals("The string should be \"Test\"", "Test", text);
        }

    }

    /**
     * Tests the set of propertySource value.
     * 
     * 
     * @throws FeatureNotFoundException
     *             if the test fails.
     * @throws LockedInstanceException
     *             if the test fails.
     */
    public void testSetpropertyValue() throws LockedInstanceException, FeatureNotFoundException {

        final String value = "TEST";
        final String value2 = "TEST2";

        CustomPropertyDescriptorExtender customPropertyDescriptorExtender = new CustomPropertyDescriptorExtender();

        final ExtendedPropertySource propertySource = new ExtendedPropertySource(this.diagram, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
        final String id = customPropertyDescriptorExtender.getExtension().getPropertyDescriptor(this.model).getId(this.model);

        propertySource.setPropertyValue(id, value);

        Object result;

        Object obj = propertySource.getPropertyValue(id);
        if (obj instanceof PropertyValueWrapper) {
            result = ((PropertyValueWrapper) obj).getEditableValue(obj);
        } else {
            result = obj;
        }

        Assert.assertEquals(value, result);

        propertySource.setPropertyValue(id, value2);

        obj = propertySource.getPropertyValue(id);
        if (obj instanceof PropertyValueWrapper) {
            result = ((PropertyValueWrapper) obj).getEditableValue(obj);
        } else {
            result = obj;
        }

        Assert.assertEquals(value2, result);

    }

    /**
     * Tests the property source with an extended feature which have a EENum. type.
     * 
     * @throws FeatureNotFoundException
     *             if the test fails.
     * @throws LockedInstanceException
     *             if the test fails.
     */
    public void testEnumExtendedFeature() throws LockedInstanceException, FeatureNotFoundException {
        EENumMetaModelExtender eEnumModelExtender = new EENumMetaModelExtender();
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).addExtender(eEnumModelExtender, ExtenderConstants.HIGHEST_PRIORITY);
        //
        // sets null.
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).eSet(this.model, ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME, null);

        ExtendedPropertySource propertySource = new ExtendedPropertySource(this.diagram, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
        for (IPropertyDescriptor descriptor : propertySource.getPropertyDescriptors()) {
            Object value = propertySource.getPropertyValue(descriptor.getId());
            Assert.assertEquals("The value should be the empty string", "", value);
            String text = descriptor.getLabelProvider().getText(value);
            Assert.assertEquals("The string should be the empty String", "", text);
        }

        //
        // Sets the value
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).eSet(this.model, ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME, VisibilityKind.PUBLIC_LITERAL);
        for (IPropertyDescriptor descriptor : propertySource.getPropertyDescriptors()) {
            Object value = propertySource.getPropertyValue(descriptor.getId());
            Assert.assertEquals("The value should be Public Visibility", VisibilityKind.PUBLIC_LITERAL, value);
            String text = descriptor.getLabelProvider().getText(value);
            Assert.assertEquals("The string should be \"Public\"", "public", text);
        }

    }

    /**
     * Tests that the property source is aware of custom property descriptor provided by the extender.
     * 
     * @throws FeatureNotFoundException
     *             if the test fails.
     * @throws LockedInstanceException
     *             if the test fails.
     * 
     */
    public void testUseOfCustomDescriptor() throws LockedInstanceException, FeatureNotFoundException {
        CustomPropertyDescriptorExtender customPropertyDescriptorExtender = new CustomPropertyDescriptorExtender();
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).addExtender(customPropertyDescriptorExtender, ExtenderConstants.HIGHEST_PRIORITY);
        //
        // sets null.
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).eSet(this.model, DescriptionPackage.eINSTANCE.getDocumentedElement_Documentation().getName(), null);

        //
        // Check that the model accessor has an extension for the documentation
        // feature.
        Collection<?> featureDescriptions = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(this.model).getAllExtensionFeatureDescriptions(this.model);
        Assert.assertEquals("The model accessor has not one extension feature", 1, featureDescriptions.size());
        ExtensionFeatureDescription theExtension = (ExtensionFeatureDescription) featureDescriptions.iterator().next();
        Assert.assertEquals("The extension is not called 'documentation'", DescriptionPackage.eINSTANCE.getDocumentedElement_Documentation().getName(), theExtension.getName());

        //
        // Try to get extended descriptors
        ExtendedPropertySource propertySource = new ExtendedPropertySource(this.diagram, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
        IPropertyDescriptor[] descriptors = propertySource.getPropertyDescriptors();
        Assert.assertEquals("Wrong number of descriptors", 1, descriptors.length);
        //
        // Chech that there is an image for the descriptor. If no image is found
        // it means that we have the wrong descriptor, otherwise it means that
        // we get the IItemPropertyDescriptor for the
        // DocumentedElement.documentation feature.
        //
        // FIXME we must find a greater test.
        Image image = descriptors[0].getLabelProvider().getImage(this.model);
        Assert.assertNotNull("Impossible to get an image", image);
    }

    /**
     * Extender that provides a custom Property Descriptor.
     * 
     * @author ymortier
     */
    public static class CustomPropertyDescriptorExtender extends BasicMetaModelExtender {

        private String value;

        private ExtensionFeatureDescription extension;

        public CustomPropertyDescriptorExtender() {
            extension = new FeatureBasedExtensionFeatureDescription(DescriptionPackage.eINSTANCE.getDocumentedElement_Documentation());
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.properties.ExtendedPropertySourceTestCase.StringMetaModelExtender#getAllExtensionFeatureDescriptions(org.eclipse.emf.ecore.EObject)
         */
        @Override
        public Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(EObject target) {
            final Collection<ExtensionFeatureDescription> result = new ArrayList<ExtensionFeatureDescription>(1);
            result.add(extension);
            return result;
        }

        public ExtensionFeatureDescription getExtension() {
            return extension;
        }

        @Override
        public Object eGet(EObject instance, String name) {
            if (name != null && name.equals(extension.getName())) {
                return value;
            }
            return null;
        }

        @Override
        public Object eSet(EObject instance, String name, Object value) {
            if (name != null && name.equals(extension.getName())) {
                this.value = value == null ? null : String.valueOf(value);
                // It's ok for us ^^
                return Boolean.TRUE;
            }
            return null;
        }

        @Override
        public boolean isExtension(EObject next, String name) {
            return name != null && name.equals(extension.getName());
        }

        @Override
        public boolean hasExtension(EObject next) {
            return true;
        }

        @Override
        public boolean eValid(EObject object, String name) {
            if (name != null && name.equals(extension.getName())) {
                return true;
            }
            return false;
        }

        @Override
        public Iterator<String> getContributedAttributeNames(EObject instance) {
            List<String> features = new ArrayList<String>(1);
            features.add(extension.getName());
            return features.iterator();
        }

        private static class FeatureBasedExtensionFeatureDescription extends ExtensionFeatureDescription {

            /** The feature which represents the extension. */
            private EStructuralFeature theFeature;

            /**
             * Creates a new extension feature description with the given EMF feature.
             * 
             * @param feature
             *            the feature which represents this extension.
             */
            public FeatureBasedExtensionFeatureDescription(EStructuralFeature feature) {
                super(feature.getName(), feature instanceof EReference, feature instanceof EReference && ((EReference) feature).isContainment(), feature.getEType().getName());
                this.theFeature = feature;
            }

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.sirius.ecore.extender.business.api.accessor.ExtensionFeatureDescription#getPropertyDescriptor(java.lang.Object)
             */
            @Override
            public IItemPropertyDescriptor getPropertyDescriptor(Object instance) {
                final AdapterFactory adapterFactory = new DescriptionItemProviderAdapterFactory();
                final IItemPropertySource propertySource = (IItemPropertySource) adapterFactory.adapt(instance, IItemPropertySource.class);
                IItemPropertyDescriptor propertyDescriptor = null;
                if (propertySource != null) {
                    final Iterator<?> iterDescriptors = propertySource.getPropertyDescriptors(instance).iterator();
                    while (iterDescriptors.hasNext() && propertyDescriptor == null) {
                        final IItemPropertyDescriptor currentPropertyDescriptor = (IItemPropertyDescriptor) iterDescriptors.next();
                        final Object currentFeature = currentPropertyDescriptor.getFeature(instance);
                        if (currentFeature != null && currentFeature.equals(theFeature)) {
                            propertyDescriptor = currentPropertyDescriptor;
                        }
                    }
                }
                if (propertyDescriptor == null) {
                    propertyDescriptor = super.getPropertyDescriptor(instance);
                }
                return propertyDescriptor;
            }

        }

    }

    /**
     * Extender to test the property source with a {@link String}.
     * 
     * @author ymortier
     */
    public static class StringMetaModelExtender extends BasicMetaModelExtender {

        private String value = null;

        private ExtensionFeatureDescription extension;

        public StringMetaModelExtender() {
            extension = new ExtensionFeatureDescription(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME, false, false, null);
        }

        public ExtensionFeatureDescription getExtension() {
            return extension;
        }

        @Override
        public Object eGet(EObject instance, String name) {
            if (name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME)) {
                return value;
            }
            return null;
        }

        @Override
        public Object eSet(EObject instance, String name, Object value) {
            if (name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME)) {
                this.value = value == null ? null : String.valueOf(value);
                // It's ok for us ^^
                return Boolean.TRUE;
            }
            return null;
        }

        @Override
        public boolean isExtension(EObject next, String name) {
            return name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME);
        }

        @Override
        public boolean hasExtension(EObject next) {
            return true;
        }

        @Override
        public boolean eValid(EObject object, String name) {
            if (name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME)) {
                return true;
            }
            return false;
        }

        @Override
        public Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(EObject target) {
            List<ExtensionFeatureDescription> list = new ArrayList<ExtensionFeatureDescription>(1);
            list.add(extension);
            return list;
        }

        @Override
        public Iterator<String> getContributedAttributeNames(EObject instance) {
            List<String> features = new ArrayList<String>(1);
            features.add(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME);
            return features.iterator();
        }
    }

    /**
     * Extender to test the property source with an {@link EEnum}.
     * 
     * @author ymortier
     */
    public static class EENumMetaModelExtender extends BasicMetaModelExtender {

        private ExtensionFeatureDescription extension;

        private VisibilityKind value;

        public EENumMetaModelExtender() {
            this.extension = new ExtensionFeatureDescription(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME, false, false, "VisibilityKind");
        }

        public ExtensionFeatureDescription getExtension() {
            return extension;
        }

        @Override
        public Object eGet(EObject instance, String name) {
            if (name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME)) {
                return value;
            }
            return null;
        }

        @Override
        public Object eSet(EObject instance, String name, Object value) {
            if (name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME)) {
                this.value = (VisibilityKind) value;
                // It's ok for us ^^
                return Boolean.TRUE;
            }
            return null;
        }

        @Override
        public boolean isExtension(EObject next, String name) {
            return name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME);
        }

        @Override
        public boolean hasExtension(EObject next) {
            return true;
        }

        @Override
        public boolean eValid(EObject object, String name) {
            if (name != null && name.equals(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME)) {
                return true;
            }
            return false;
        }

        @Override
        public Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(EObject target) {
            List<ExtensionFeatureDescription> list = new ArrayList<ExtensionFeatureDescription>(1);
            list.add(extension);
            return list;
        }

        @Override
        public Iterator<String> getContributedAttributeNames(EObject instance) {
            List<String> features = new ArrayList<String>();
            features.add(ExtendedPropertySourceTestCase.EXTENDED_FEATURE_NAME);
            return features.iterator();
        }
    }

    /**
     * This extender does nothing.
     * 
     * @author ymortier
     */
    public static class BasicMetaModelExtender extends AbstractMetamodelExtender {

        @Override
        public EObject createInstance(String name) {
            return null;
        }

        @Override
        public boolean eIsKnownType(String name) {
            return false;
        }

        @Override
        public void dispose() {
        }

        @Override
        public Object eAdd(EObject instance, String name, Object value) {
            return null;
        }

        @Override
        public Object eClear(EObject instance, String name) {
            return null;
        }

        @Override
        public EObject eContainer(EObject instance) {
            return null;
        }

        @Override
        public String eContainingFeatureName(EObject objectToRemove) {
            return null;
        }

        @Override
        public Iterator<EObject> eContents(EObject root) {
            return Collections.<EObject> emptyList().iterator();
        }

        @Override
        public EObject eDelete(EObject objectToRemove, ECrossReferenceAdapter xref) {
            return null;
        }

        @Override
        public EObject eDelete(EObject objectToRemove, ECrossReferenceAdapter xref, EReferencePredicate isReferenceToIgnorePredicate) {
            return null;
        }

        @Override
        public Collection<EObject> eRemoveInverseCrossReferences(EObject eObject, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
            return Collections.emptyList();
        }

        @Override
        public Object eGet(EObject instance, String name) {
            return null;
        }

        @Override
        public boolean eInstanceOf(EObject instance, String typeName) {
            return false;
        }

        @Override
        public Boolean eIsContainment(EObject instance, String featureName) {
            return Boolean.FALSE;
        }

        @Override
        public Boolean eIsMany(EObject instance, String featureName) {
            return Boolean.FALSE;
        }

        @Override
        public Object eRemove(EObject instance, String name, Object value) {
            return null;
        }

        @Override
        public Object eSet(EObject instance, String name, Object value) {
            return null;
        }

        @Override
        public boolean eValid(EObject object, String name) {
            return false;
        }

        @Override
        public Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(EObject target) {
            return Collections.emptyList();
        }

        @Override
        public Iterator<String> getContributedAttributeNames(EObject instance) {
            return Collections.<String> emptyList().iterator();
        }

        @Override
        public Iterator<String> getContributedReferenceNames(EObject instance) {
            return Collections.<String> emptyList().iterator();
        }

        @Override
        public String getQualifiedName(EObject element, boolean useTypeWhenNoName) {
            return null;
        }

        @Override
        public boolean hasExtension(EObject next) {
            return false;
        }

        @Override
        public void init(ResourceSet set) {
        }

        @Override
        public boolean isExtension(EObject next) {
            return false;
        }

        @Override
        public boolean isExtension(EObject next, String name) {
            return false;
        }

        @Override
        public boolean preventFromBrowsing(EObject root) {
            return false;
        }

    }

}
