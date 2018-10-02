/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.vsm.edit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.tests.support.api.AbstractItemProviderAdapterFactoryRegistryTestCase;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

/**
 * Test exposure of item provider adapter factories.
 * 
 * @author mporhel
 */
public class SiriusAdapterFactoryRegistryTest extends AbstractItemProviderAdapterFactoryRegistryTestCase {

    @Override
    public void initPackages() {
        setBasePackage(ViewpointPackage.eINSTANCE);

        List<EPackage> exposedPackages = new ArrayList<EPackage>();
        exposedPackages.add(ViewpointPackage.eINSTANCE);
        exposedPackages.add(DescriptionPackage.eINSTANCE);
        exposedPackages.add(ToolPackage.eINSTANCE);
        exposedPackages.add(StylePackage.eINSTANCE);
        exposedPackages.add(AuditPackage.eINSTANCE);
        exposedPackages.add(DiagramPackage.eINSTANCE);
        exposedPackages.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);
        exposedPackages.add(org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE);
        exposedPackages.add(org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE);
        exposedPackages.add(FilterPackage.eINSTANCE);
        exposedPackages.add(ValidationPackage.eINSTANCE);

        setPackagesWithExposedAdapterFactory(exposedPackages);

        setPackagesWithNonExposedAdapterFactory(Collections.<EPackage> singleton(ConcernPackage.eINSTANCE));
    }
}
