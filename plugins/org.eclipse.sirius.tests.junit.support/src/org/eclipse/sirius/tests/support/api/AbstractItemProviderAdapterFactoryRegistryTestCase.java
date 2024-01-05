/**
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

import junit.framework.TestCase;

/**
 * Test exposure of item provider adapter factories.
 * 
 * @author mporhel
 */
public abstract class AbstractItemProviderAdapterFactoryRegistryTestCase extends TestCase {

    private static final String THE_ITEM_PROVIDER_ADAPTER_FACTORY_OF_THE_PACKAGE = "The item provider adapter factory of the package ";

    private final Registry registry = ComposedAdapterFactory.Descriptor.Registry.INSTANCE;

    private final Collection<EPackage> exposedItemProvidersAdapterFactories = new LinkedHashSet<>();

    private final Collection<EPackage> nonExposedItemProvidersAdapterFactories = new LinkedHashSet<>();

    private EPackage basePackage;

    private final Predicate<EPackage> shouldBeExposed = (EPackage input) -> exposedItemProvidersAdapterFactories.contains(input);

    private final Predicate<EPackage> shouldNotBeExposed = (EPackage input) -> nonExposedItemProvidersAdapterFactories.contains(input);

    private final Function<EPackage, Collection<?>> getKey = new Function<EPackage, Collection<?>>() {
        @Override
        public Collection<?> apply(EPackage from) {
            Collection<Object> key = new ArrayList<>();
            key.add(from);
            key.add(IItemLabelProvider.class);
            return key;
        };
    };

    public EPackage getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(EPackage basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * Set packages that should have their item provider adapter factory
     * exposed.
     * 
     * @param packages
     *            packages with exposed item provider adapter factory.
     */
    public void setPackagesWithExposedAdapterFactory(Collection<EPackage> packages) {
        exposedItemProvidersAdapterFactories.addAll(packages);
    }

    /**
     * Set packages that should have their item provider adapter factory
     * exposed.
     * 
     * @param packages
     *            packages with exposed item provider adapter factory.
     */
    public void setPackagesWithNonExposedAdapterFactory(Collection<EPackage> packages) {
        nonExposedItemProvidersAdapterFactories.addAll(packages);
    }

    /**
     * Allow to declare base package and registered item provider adapter
     * factories.
     */
    public abstract void initPackages();

    @Override
    protected void setUp() throws Exception {
        initPackages();
        super.setUp();
        TestCase.assertNotNull("Base package should not be null.", basePackage);
    }

    /**
     * Test that all interpreted expression has a variable documentation.
     */
    public void testExposedAdapterFactories() {
        testEPackageExposed(basePackage);
    }

    private void testEPackageExposed(EPackage pkg) {

        Descriptor descriptor = registry.getDescriptor(getKey.apply(pkg));
        String label = pkg.getName() + " (" + pkg.getNsURI() + ") ";

        boolean exposedExpected = shouldBeExposed.test(pkg);
        if (exposedExpected) {
            TestCase.assertNotNull(AbstractItemProviderAdapterFactoryRegistryTestCase.THE_ITEM_PROVIDER_ADAPTER_FACTORY_OF_THE_PACKAGE + label + "should be exposed.", descriptor);

            AdapterFactory adapterFactory = descriptor.createAdapterFactory();
            TestCase.assertNotNull("Error creating while creating the item provider adapter factory of the package " + label, adapterFactory);
        }

        boolean notExposedExpected = shouldNotBeExposed.test(pkg);
        if (notExposedExpected) {
            TestCase.assertNull(AbstractItemProviderAdapterFactoryRegistryTestCase.THE_ITEM_PROVIDER_ADAPTER_FACTORY_OF_THE_PACKAGE + label + "should not be exposed.", descriptor);
        }

        if (exposedExpected && notExposedExpected || !exposedExpected && !notExposedExpected) {
            TestCase.fail(AbstractItemProviderAdapterFactoryRegistryTestCase.THE_ITEM_PROVIDER_ADAPTER_FACTORY_OF_THE_PACKAGE + label
                    + "is not known or invalid for the current test. Please check with the team if it should be exposed or not and then complete the test.");
        }

        for (EPackage subPkg : pkg.getESubpackages()) {
            testEPackageExposed(subPkg);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        exposedItemProvidersAdapterFactories.clear();
        basePackage = null;
    }
}
