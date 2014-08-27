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
package org.eclipse.sirius.tests.unit.perf.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the GroupingContentProvider. We take care about preference
 * context with: CommonPreferencesConstants.PREF_GROUP_ENABLE
 * CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE
 * CommonPreferencesConstants.PREF_GROUP_SIZE
 * CommonPreferencesConstants.PREF_GROUP_TRIGGER
 */
public class CommonPreferencesTest extends CommonPreferencesHelper {

    private GroupingContentProvider groupingContentProvider;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.ViewpointTestCase#setUp()
     */
    @Before
    public void setUp() {
        super.setUp();
        this.groupingContentProvider = new GroupingContentProvider(null);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#isGroupEnabled()}
     * .
     */
    @Test
    public void isGroupEnable() {
        assertEquals(this.isGroupEnable, this.groupingContentProvider.isGroupEnabled());
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#isGroupEnabled()}
     * .
     */
    @Test
    public void isGroupEnableCompareWithDefault() {
        assertEquals(this.defaultIsGroupEnable, this.groupingContentProvider.isGroupEnabled());
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#isGroupEnabled()}
     * .
     */
    @Test
    public void isGroupByContainingFeature() {
        assertEquals(this.isGroupByContainingFeature, this.groupingContentProvider.isGroupByContainingFeature());
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#isGroupEnabled()}
     * .
     */
    @Test
    public void isGroupByContainingFeatureCompareWithDefault() {
        assertEquals(this.defaultIsGroupByContainingFeature, this.groupingContentProvider.isGroupByContainingFeature());
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getGroupSize()}
     * .
     */
    @Test
    public void getGroupSize() {
        assertEquals(this.groupSize, this.groupingContentProvider.getGroupSize());
        assertTrue("Group size has a positive value", this.groupingContentProvider.getGroupSize() > 0);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getGroupSize()}
     * .
     */
    @Test
    public void getGroupSizeCompareWithDefault() {
        assertEquals(this.defaultGroupSize, this.groupingContentProvider.getGroupSize());
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getTriggerSize()}
     * .
     */
    @Test
    public void getTriggerSize() {
        assertEquals(this.groupTrigger, this.groupingContentProvider.getTriggerSize());
        assertTrue("Trigger size has a positive value", this.groupingContentProvider.getGroupSize() > 0);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getTriggerSize()}
     * .
     */
    @Test
    public void getTriggerSizeCompareWithDefault() {
        assertEquals(this.defaultGroupTrigger, this.groupingContentProvider.getTriggerSize());
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getTriggerSize()}
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getGroupSize()}
     * .
     */
    @Test
    public void coherenceBetweenTriggerSizeAndGroupSize() {
        int currentTriggerSize = this.groupingContentProvider.getTriggerSize();
        int currentGroupSize = this.groupingContentProvider.getGroupSize();
        assertTrue("Trigger size >= Group size", currentTriggerSize >= currentGroupSize);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getTriggerSize()}
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getGroupSize()}
     * .
     */
    @Test
    public void coherenceBetweenTriggerSizeAndGroupSize2() {

        setPrefGroupSize(100);
        setPrefGroupTrigger(50);
        int currentTriggerSize = this.groupingContentProvider.getTriggerSize();
        int currentGroupSize = this.groupingContentProvider.getGroupSize();
        assertTrue("Trigger size >= Group size", currentTriggerSize >= currentGroupSize);
    }
}
