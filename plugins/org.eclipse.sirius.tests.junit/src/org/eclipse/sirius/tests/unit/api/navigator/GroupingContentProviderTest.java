/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.navigator;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingItem;
import org.eclipse.sirius.tests.unit.perf.common.CommonPreferencesHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class tests the GroupingContentProvider. We take care about preference context with:
 * CommonPreferencesConstants.PREF_GROUP_ENABLE CommonPreferencesConstants.PREF_GROUP_SIZE
 * CommonPreferencesConstants.PREF_GROUP_TRIGGER
 * 
 * This test case does not deal with the grouping item by containing feature. In fact, GROUP_BY_CONTAINING_FEATURE is
 * only enable on instance of EObject and this test class use only basic Objects. But we need to test that a
 * GROUP_BY_CONTAINING_FEATURE at true on Object doesn't throw any platformProblemsListener.getErrors().
 * <p>
 * We use a Parameterized annotation to launch this test with GROUP_BY_CONTAINING_FEATURE at true and false.
 * 
 * The concrete PREF_GROUP_BY_CONTAINING_FEATURE feature has a specific Test Class @see GroupingContentProviderPrefTest.
 * 
 */
@RunWith(value = Parameterized.class)
public class GroupingContentProviderTest extends CommonPreferencesHelper {

    private static final String RETRIEVE_CHILDREN = "Retrieve children";

    private static final String MUST_HAVE_TWO_CHILDREN = "Must have two children";

    private static final String KEEP_THE_SAME_PARENT = "Keep the same parent";

    private static final String CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM = "Check the type of the grouping tree item";

    private GroupingContentProvider groupingContentProvider;

    private ITreeContentProvider mockTreeContentProvider;

    private boolean groupByContainingFeature;

    public GroupingContentProviderTest(boolean groupByContainingFeature) {
        this.groupByContainingFeature = groupByContainingFeature;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#setUp()
     */
    @Override
    @Before
    public void setUp() {
        super.setUp();
        setPrefGroupByContainingFeature(groupByContainingFeature);
        this.mockTreeContentProvider = createMock(ITreeContentProvider.class);
        this.groupingContentProvider = new GroupingContentProvider(mockTreeContentProvider);
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] prefs4GroupByContainingFeature = new Object[][] { { false }, { true } };
        return Arrays.asList(prefs4GroupByContainingFeature);
    }

    /**
     * Test method for {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getTriggerSize()}
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getGroupSize()} .
     */
    @Test
    public void coherenceBewteenTriggerSizeAndGroupSize2() {

        setPrefGroupSize(100);
        setPrefGroupTrigger(50);
        int currentTriggerSize = this.groupingContentProvider.getTriggerSize();
        int currentGroupSize = this.groupingContentProvider.getGroupSize();
        assertTrue("Trigger size >= Group size", currentTriggerSize >= currentGroupSize);
    }

    /**
     * The the getChildren delegation for the GroupingItem. Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)} .
     */
    @Test
    public void getElements() {

        Object child1 = new Object();
        Object child2 = new Object();
        Object child3 = new Object();
        Object child4 = new Object();
        Object[] children = new Object[] { child1, child2, child3, child4 };

        GroupingItem parent = new GroupingItem(0, null, Arrays.asList(children));

        Object[] elements = this.groupingContentProvider.getElements(parent);
        assertEquals(children.length, elements.length);
        for (int i = 0; i < children.length; i++) {
            Object child = children[i];
            Object element = elements[i];
            assertEquals(child, element);
        }
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)} .
     */
    @Test
    public void getElementsWithGroupDisabled() {

        // Set pref values to be active if isGroupEnable was at true.
        setPrefGroupEnable(false);
        setPrefGroupSize(1);
        setPrefGroupTrigger(1);

        Object parent = new Object();
        Object[] children = new Object[] { new Object() };

        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        this.groupingContentProvider.getElements(parent);
        verify(this.mockTreeContentProvider);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)} .
     */
    @Test
    public void getElementsWithGroupDisabled2() {

        // Set pref values to be inactive.
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        Object parent = new Object();
        Object[] children = new Object[] { new Object(), new Object() };

        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        this.groupingContentProvider.getElements(parent);
        verify(this.mockTreeContentProvider);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)} .
     */
    @Test
    public void getElementsWithGroupEnable() {

        // Set pref values to be active.
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        Object parent = new Object();
        Object child1 = new Object();
        Object child2 = new Object();
        Object child3 = new Object();
        Object child4 = new Object();
        Object[] children = new Object[] { child1, child2, child3, child4 };

        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        Object[] elements = this.groupingContentProvider.getElements(parent);
        verify(this.mockTreeContentProvider);

        // test the given elements
        assertEquals("Children splitted in two groups", 2, elements.length);

        // test the first grouping item
        Object firstTreeItem = elements[0];
        assertTrue(CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM, firstTreeItem instanceof GroupingItem);
        Object parentOfFirstTreeItem = ((GroupingItem) firstTreeItem).getParent();
        assertEquals(KEEP_THE_SAME_PARENT, parent, parentOfFirstTreeItem);

        Object[] childrenOfFirstTreeItem = ((GroupingItem) firstTreeItem).getChildren().toArray();
        assertEquals(MUST_HAVE_TWO_CHILDREN, 2, childrenOfFirstTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child1, childrenOfFirstTreeItem[0]);
        assertEquals(RETRIEVE_CHILDREN, child2, childrenOfFirstTreeItem[1]);

        // test the second grouping item
        Object secondTreeItem = elements[1];
        assertTrue(CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM, secondTreeItem instanceof GroupingItem);
        Object parentOfSecondTreeItem = ((GroupingItem) secondTreeItem).getParent();
        assertEquals(KEEP_THE_SAME_PARENT, parent, parentOfSecondTreeItem);
        Object[] childrenOfSecondTreeItem = ((GroupingItem) secondTreeItem).getChildren().toArray();
        assertEquals(MUST_HAVE_TWO_CHILDREN, 2, childrenOfSecondTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child3, childrenOfSecondTreeItem[0]);
        assertEquals(RETRIEVE_CHILDREN, child4, childrenOfSecondTreeItem[1]);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)} .
     */
    @Test
    public void getElementsWithGroupEnable2() {

        // Set pref values to be active.
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        Object parent = new Object();
        Object child1 = new Object();
        Object child2 = new Object();
        Object child3 = new Object();
        Object[] children = new Object[] { child1, child2, child3 };

        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        Object[] elements = this.groupingContentProvider.getElements(parent);
        verify(this.mockTreeContentProvider);

        // test the given elements
        assertEquals("Children splitted in two groups", 2, elements.length);

        // test the first grouping item
        Object firstTreeItem = elements[0];
        assertTrue(CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM, firstTreeItem instanceof GroupingItem);
        Object parentOfFirstTreeItem = ((GroupingItem) firstTreeItem).getParent();
        assertEquals(KEEP_THE_SAME_PARENT, parent, parentOfFirstTreeItem);

        Object[] childrenOfFirstTreeItem = ((GroupingItem) firstTreeItem).getChildren().toArray();
        assertEquals(MUST_HAVE_TWO_CHILDREN, 2, childrenOfFirstTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child1, childrenOfFirstTreeItem[0]);
        assertEquals(RETRIEVE_CHILDREN, child2, childrenOfFirstTreeItem[1]);

        // test the second grouping item
        Object secondTreeItem = elements[1];
        assertTrue(CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM, secondTreeItem instanceof GroupingItem);
        Object parentOfSecondTreeItem = ((GroupingItem) secondTreeItem).getParent();
        assertEquals(KEEP_THE_SAME_PARENT, parent, parentOfSecondTreeItem);
        Object[] childrenOfSecondTreeItem = ((GroupingItem) secondTreeItem).getChildren().toArray();
        assertEquals(MUST_HAVE_TWO_CHILDREN, 1, childrenOfSecondTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child3, childrenOfSecondTreeItem[0]);
    }

    /**
     * The the getChildren delegation for the GroupingItem. Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getChildren(java.lang.Object)} .
     */
    @Test
    public void getChildren() {

        Object child1 = new Object();
        Object child2 = new Object();
        Object child3 = new Object();
        Object child4 = new Object();
        Object[] children = new Object[] { child1, child2, child3, child4 };

        GroupingItem parent = new GroupingItem(0, null, Arrays.asList(children));

        Object[] elements = this.groupingContentProvider.getChildren(parent);
        assertEquals(children.length, elements.length);
        for (int i = 0; i < children.length; i++) {
            Object child = children[i];
            Object element = elements[i];
            assertEquals(child, element);
        }
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getChildren(java.lang.Object)} .
     */
    @Test
    public void getChildrenWithGroupDisabled() {

        // Set pref values to be active if defaultIsGroupEnable was at true.
        setPrefGroupEnable(false);
        setPrefGroupSize(1);
        setPrefGroupTrigger(1);

        Object parent = new Object();
        Object[] children = new Object[] { new Object(), new Object(), new Object() };

        expect(this.mockTreeContentProvider.getChildren(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        this.groupingContentProvider.getChildren(parent);
        verify(this.mockTreeContentProvider);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getChildren(java.lang.Object)} .
     */
    @Test
    public void getChildrenWithGroupDisabled2() {

        // Set pref values to be inactive.
        setPrefGroupEnable(true);
        setPrefGroupSize(1);
        setPrefGroupTrigger(3);

        Object parent = new Object();
        Object[] children = new Object[] { new Object(), new Object(), new Object() };

        expect(this.mockTreeContentProvider.getChildren(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        this.groupingContentProvider.getChildren(parent);
        verify(this.mockTreeContentProvider);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getChildren(java.lang.Object)} .
     */
    @Test
    public void getChildrenWithGroupEnable() {

        // Set pref values to be active.
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        Object parent = new Object();
        Object child1 = new Object();
        Object child2 = new Object();
        Object child3 = new Object();
        Object child4 = new Object();
        Object[] children = new Object[] { child1, child2, child3, child4 };

        expect(this.mockTreeContentProvider.getChildren(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        Object[] elements = this.groupingContentProvider.getChildren(parent);
        verify(this.mockTreeContentProvider);

        // test the given elements
        assertEquals("Children splitted in two groups", 2, elements.length);

        // test the first grouping item
        Object firstTreeItem = elements[0];
        assertTrue(CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM, firstTreeItem instanceof GroupingItem);
        Object parentOfFirstTreeItem = ((GroupingItem) firstTreeItem).getParent();
        assertEquals(KEEP_THE_SAME_PARENT, parent, parentOfFirstTreeItem);

        Object[] childrenOfFirstTreeItem = ((GroupingItem) firstTreeItem).getChildren().toArray();
        assertEquals(MUST_HAVE_TWO_CHILDREN, 2, childrenOfFirstTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child1, childrenOfFirstTreeItem[0]);
        assertEquals(RETRIEVE_CHILDREN, child2, childrenOfFirstTreeItem[1]);

        // test the second grouping item
        Object secondTreeItem = elements[1];
        assertTrue(CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM, secondTreeItem instanceof GroupingItem);
        Object parentOfSecondTreeItem = ((GroupingItem) secondTreeItem).getParent();
        assertEquals(KEEP_THE_SAME_PARENT, parent, parentOfSecondTreeItem);
        Object[] childrenOfSecondTreeItem = ((GroupingItem) secondTreeItem).getChildren().toArray();
        assertEquals(MUST_HAVE_TWO_CHILDREN, 2, childrenOfSecondTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child3, childrenOfSecondTreeItem[0]);
        assertEquals(RETRIEVE_CHILDREN, child4, childrenOfSecondTreeItem[1]);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getParent(java.lang.Object)} .
     */
    @Test
    public void getParent() {

        Object parent = new Object();
        Object child1 = new Object();
        Object child2 = new Object();
        Object child3 = new Object();
        Object child4 = new Object();
        Object[] children = new Object[] { child1, child2, child3, child4 };

        for (Object child : children) {
            expect(this.mockTreeContentProvider.getParent(child)).andReturn(parent);
        }
        replay(this.mockTreeContentProvider);
        for (Object child : children) {
            this.groupingContentProvider.getParent(child);
        }
        verify(this.mockTreeContentProvider);
    }

    /**
     * The the getChildren delegation for the GroupingItem. Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getParent(java.lang.Object)} .
     */
    @Test
    public void getParent2() {

        Object parent = new Object();
        GroupingItem child = new GroupingItem(0, parent, null);
        this.groupingContentProvider.getParent(child);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#hasChildren(java.lang.Object)} .
     */
    @Test
    public void hasChildren() {

        Object parent = new Object();
        expect(this.mockTreeContentProvider.hasChildren(parent)).andReturn(false);
        replay(this.mockTreeContentProvider);
        assertFalse(this.groupingContentProvider.hasChildren(parent));
        verify(this.mockTreeContentProvider);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#hasChildren(java.lang.Object)} .
     */
    @Test
    public void hasChildren2() {
        GroupingItem parent = new GroupingItem(0, null, null);
        assertTrue("A Groupping Item has always children", this.groupingContentProvider.hasChildren(parent));
    }
}
