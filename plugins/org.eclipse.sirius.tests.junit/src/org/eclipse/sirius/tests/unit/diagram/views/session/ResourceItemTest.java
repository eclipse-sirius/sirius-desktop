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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.AnalysisResourceItemImpl;

import junit.framework.TestCase;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class ResourceItemTest extends TestCase {

    private static final String A_STRING = "a string";

    private static final String ANOTHER_STRING = "another string";

    private Resource resource;

    private Object parent1 = A_STRING;

    private Object parent2 = ANOTHER_STRING;

    private AnalysisResourceItem item;

    private AnalysisResourceItem item2;

    private AnalysisResourceItem itemSameNameDifferentParent;

    private URI uri;

    private Object[] mocks;

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        super.setUp();

        resource = createMock(Resource.class);
        ResourceSet resourceSet = new ResourceSetImpl();

        item = new AnalysisResourceItemImpl(null, resource, parent1);
        item2 = new AnalysisResourceItemImpl(null, resource, parent1);
        itemSameNameDifferentParent = new AnalysisResourceItemImpl(null, resource, parent2);

        uri = URI.createFileURI("/a path");

        mocks = new Object[] { resource };

        // Start recording
        expect(resource.getResourceSet()).andReturn(resourceSet).anyTimes();
        expect(resource.getURI()).andReturn(uri).anyTimes();
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
