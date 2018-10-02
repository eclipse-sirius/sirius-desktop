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
package org.eclipse.sirius.tests.unit.diagram.views.session;

import static org.junit.Assert.assertNotEquals;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import junit.framework.TestCase;

/**
 * Test class for RepresentationDescriptionItem.
 * 
 * @author dlecan
 */
public class RepresentationDescriptionItemTest extends TestCase {

    private static final String A_STRING = "a string";

    private static final String ANOTHER_STRING = "another string";

    private static final String A_NAME = "a name";

    private RepresentationDescriptionItem item;

    private RepresentationDescriptionItem item2;

    private RepresentationDescriptionItem item3;

    private RepresentationDescription representationDescription;

    private DiagramDescription representationDescription2;

    private Resource resource;

    private Object parent1 = A_STRING;

    private Object parent2 = ANOTHER_STRING;

    private Object[] mocks;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        resource = new XMIResourceImpl(URI.createURI("uri1"));
        representationDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        representationDescription2 = DescriptionFactory.eINSTANCE.createDiagramDescription();

        resource.getContents().add(representationDescription);
        resource.getContents().add(representationDescription2);

        item = new RepresentationDescriptionItemImpl(null, representationDescription, resource, parent1);

        item2 = new RepresentationDescriptionItemImpl(null, representationDescription, resource, parent1);

        item3 = new RepresentationDescriptionItemImpl(null, representationDescription2, resource, parent2);

    }

    /**
     * hashCode tests.
     */
    public void testHashCode() {
        assertTrue(item.hashCode() == item2.hashCode());
        assertFalse(item.hashCode() == item3.hashCode());
        assertFalse(item2.hashCode() == item3.hashCode());

    }

    /**
     * equals tests.
     */
    public void testEqualsObject() {
        assertEquals(item, item2);
        assertNotEquals(item, item3);
        assertNotEquals(item2, item3);

    }

}
