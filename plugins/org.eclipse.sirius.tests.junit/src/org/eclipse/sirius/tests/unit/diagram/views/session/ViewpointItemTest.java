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

import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class ViewpointItemTest extends TestCase {

    private static final String A_STRING = "a string";

    private static final String ANOTHER_STRING = "another string";

    private static final String A_NAME = "a name";

    private Viewpoint viewpoint;

    private Object parent1 = A_STRING;

    private Object parent2 = ANOTHER_STRING;

    private ViewpointItem item;

    private ViewpointItem item2;

    private ViewpointItem itemSameNameDifferentParent;

    private Object[] mocks;

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        super.setUp();

        viewpoint = createMock(Viewpoint.class);

        item = new ViewpointItemImpl(null, viewpoint, parent1);
        item2 = new ViewpointItemImpl(null, viewpoint, parent1);
        itemSameNameDifferentParent = new ViewpointItemImpl(null, viewpoint, parent2);

        mocks = new Object[] { viewpoint };

        // Start recording
        expect(viewpoint.getName()).andReturn(A_NAME).anyTimes();
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
