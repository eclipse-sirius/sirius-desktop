/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.tree.vsm.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tests.support.api.AbstractItemProviderAdapterFactoryRegistryTestCase;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.description.DescriptionPackage;

/**
 * Test exposure of item provider adapter factories.
 * 
 * @author mporhel
 */
public class TreeAdapterFactoryRegistryTest extends AbstractItemProviderAdapterFactoryRegistryTestCase {

    @Override
    public void initPackages() {
        setBasePackage(TreePackage.eINSTANCE);

        List<EPackage> exposedPackages = new ArrayList<EPackage>();
        exposedPackages.add(TreePackage.eINSTANCE);
        exposedPackages.add(DescriptionPackage.eINSTANCE);

        setPackagesWithExposedAdapterFactory(exposedPackages);
    }
}
