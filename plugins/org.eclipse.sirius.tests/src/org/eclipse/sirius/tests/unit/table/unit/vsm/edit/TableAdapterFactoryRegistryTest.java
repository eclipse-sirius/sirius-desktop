/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.vsm.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.tests.support.api.AbstractItemProviderAdapterFactoryRegistryTestCase;

/**
 * Test exposure of item provider adapter factories.
 * 
 * @author mporhel
 */
public class TableAdapterFactoryRegistryTest extends AbstractItemProviderAdapterFactoryRegistryTestCase {

    @Override
    public void initPackages() {
        setBasePackage(TablePackage.eINSTANCE);

        List<EPackage> exposedPackages = new ArrayList<EPackage>();
        exposedPackages.add(TablePackage.eINSTANCE);
        exposedPackages.add(DescriptionPackage.eINSTANCE);

        setPackagesWithExposedAdapterFactory(exposedPackages);
    }
}
