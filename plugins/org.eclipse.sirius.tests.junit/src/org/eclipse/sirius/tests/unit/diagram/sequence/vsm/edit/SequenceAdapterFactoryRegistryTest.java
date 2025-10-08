/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.sequence.vsm.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.tests.support.api.AbstractItemProviderAdapterFactoryRegistryTestCase;

/**
 * Test exposure of item provider adapter factories.
 * 
 * @author mporhel
 */
public class SequenceAdapterFactoryRegistryTest extends AbstractItemProviderAdapterFactoryRegistryTestCase {

    @Override
    public void initPackages() {
        setBasePackage(SequencePackage.eINSTANCE);

        List<EPackage> exposedPackages = new ArrayList<EPackage>();
        exposedPackages.add(SequencePackage.eINSTANCE);
        exposedPackages.add(DescriptionPackage.eINSTANCE);
        exposedPackages.add(ToolPackage.eINSTANCE);
        exposedPackages.add(OrderingPackage.eINSTANCE);

        setPackagesWithExposedAdapterFactory(exposedPackages);
    }
}
