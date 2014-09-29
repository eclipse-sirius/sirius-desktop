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
package org.eclipse.sirius.tests.unit.diagram.views.session;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import junit.framework.TestCase;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

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

    private RepresentationDescriptionItem itemSameNameDifferentParent;

    private RepresentationDescription representationDescription;

    private Resource resource;

    private Object parent1 = A_STRING;

    private Object parent2 = ANOTHER_STRING;

    private Object[] mocks;

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        super.setUp();

        representationDescription = createMock(RepresentationDescription.class);
        resource = createMock(Resource.class);

        item = new RepresentationDescriptionItemImpl(null, representationDescription, resource, parent1);

        item2 = new RepresentationDescriptionItemImpl(null, representationDescription, resource, parent1);

        itemSameNameDifferentParent = new RepresentationDescriptionItemImpl(null, representationDescription, resource, parent2);

        mocks = new Object[] { representationDescription, resource };

        // start recording
        expect(representationDescription.getName()).andReturn(A_NAME).anyTimes();
    }

    /**
     * hashCode tests.
     */
    public void testHashCode() {
        replay(mocks);
        // stop recording

        assertEquals(item.hashCode(), item2.hashCode());
        assertFalse(item.hashCode() == itemSameNameDifferentParent.hashCode());
        assertFalse(item2.hashCode() == itemSameNameDifferentParent.hashCode());

        verify(mocks);
    }

    /**
     * equals tests.
     */
    public void testEqualsObject() {
        replay(mocks);
        // stop recording

        assertEquals(item, item2);
        assertFalse(item.equals(itemSameNameDifferentParent));
        assertFalse(item2.equals(itemSameNameDifferentParent));

        verify(mocks);
    }

}
