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

import org.eclipse.sirius.ui.tools.internal.views.common.item.AbstractFolderItem;

import junit.framework.TestCase;

/**
 * Common behavior for all *FolderItemTest classes.
 * 
 * @author dlecan
 * @param <T>
 */
public abstract class AbstractFolderItemTest<T extends AbstractFolderItem> extends TestCase {

    private static final String A_STRING = "a string";

    private static final String ANOTHER_STRING = "another string";

    private Object parent1 = A_STRING;

    private Object parent2 = ANOTHER_STRING;

    private AbstractFolderItem item;

    private AbstractFolderItem item2;

    private AbstractFolderItem itemSameNameDifferentParent;

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        super.setUp();

        item = createTestedItem(parent1);
        item2 = createTestedItem(parent1);
        itemSameNameDifferentParent = createTestedItem(parent2);
    }

    /**
     * Create element to test.
     * 
     * @param parent
     *            Parent.
     * @return Element to test
     */
    protected abstract T createTestedItem(Object parent);

    /**
     * hashCode tests.
     */
    public void testHashCode() {
        assertEquals(item.hashCode(), item2.hashCode());
        assertFalse(item.hashCode() == itemSameNameDifferentParent.hashCode());
        assertFalse(item2.hashCode() == itemSameNameDifferentParent.hashCode());
    }

    /**
     * equals tests.
     */
    public void testEqualsObject() {
        assertEquals(item, item2);
        assertFalse(item.equals(itemSameNameDifferentParent));
        assertFalse(item2.equals(itemSameNameDifferentParent));
    }

}
