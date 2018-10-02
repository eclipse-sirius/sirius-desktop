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
package org.eclipse.sirius.tests.unit.api.mappings;

import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Tests for Edge mapping.
 * 
 * @author nlepine
 */
public class EdgeMappingTest extends SiriusDiagramTestCase {
    /**
     * Test edge mapping creation
     * 
     * @throws Exception
     */
    public void testEdgemappingCreation() throws Exception {
        EdgeMapping createEdgeMapping = DescriptionFactory.eINSTANCE.createEdgeMapping();
        assertFalse(createEdgeMapping.isUseDomainElement());

        createEdgeMapping = DescriptionFactory.eINSTANCE.createEdgeMappingUsingDomainElement();
        assertTrue(createEdgeMapping.isUseDomainElement());
    }
}
