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
package org.eclipse.sirius.tests.unit.api.session;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryFilter;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import junit.framework.TestCase;

public class SiriusRegistryTests extends TestCase {

    private static final String BASIC_FILTER = "basicFilter";

    public void testFilters() {
        if (Movida.isEnabled()) {
            return;
        }

        final int numberOfSiriuss = ViewpointRegistry.getInstance().getViewpoints().size();

        ViewpointRegistryFilter basicFilter = new ViewpointRegistryFilter() {

            public boolean filter(Viewpoint viewpoint) {
                return true;
            }

            public boolean filter(RepresentationExtensionDescription representationExtensionDescription) {
                return false;
            }

            public String getId() {
                return BASIC_FILTER;
            }

        };

        ViewpointRegistry.getInstance().addFilter(basicFilter);

        assertEquals(0, ViewpointRegistry.getInstance().getViewpoints().size());

        ViewpointRegistry.getInstance().removeFilter(BASIC_FILTER);

        assertEquals(numberOfSiriuss, ViewpointRegistry.getInstance().getViewpoints().size());
    }

}
