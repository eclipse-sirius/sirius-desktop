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
package org.eclipse.sirius.tests.unit.api.navigator;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingItem;
import org.eclipse.sirius.tests.unit.perf.common.CommonPreferencesHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the GroupingContentProvider. We take care about preference
 * context with: CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE
 * 
 * This test case deals with
 * CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE at true. This
 * pref enable a specific behavior on grouping content provider tested here.
 */
public class GroupingContentProviderByContainingTest extends CommonPreferencesHelper {

    private static final String RETRIEVE_CHILD = "Retrieve child";

    private static final String RETRIEVE_CHILDREN = "Retrieve children";

    private static final String MUST_HAVE_ONE_CHILD = "Must have one child";

    private static final String MUST_HAVE_TWO_CHILDREN = "Must have two children";

    private static final String KEEP_THE_SAME_PARENT = "Keep the same parent";

    private static final String CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM = "Check the type of the grouping tree item";

    private GroupingContentProvider groupingContentProvider;

    private ITreeContentProvider mockTreeContentProvider;

    private EcoreFactory eFactory = EcoreFactory.eINSTANCE;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        setPrefGroupByContainingFeature(true);
        this.mockTreeContentProvider = createMock(ITreeContentProvider.class);
        this.groupingContentProvider = new GroupingContentProvider(mockTreeContentProvider);
    }

    /**
     * The the getChildren delegation for the GroupingItem. Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)}
     * .
     */
    @Test
    public void getElements() {
        EObject modelParent = createMock(EObject.class);
        Object[] children = new EObject[] { createMock(EObject.class), createMock(EObject.class), createMock(EObject.class), createMock(EObject.class) };
        GroupingItem group = new GroupingItem(0, modelParent, Arrays.asList(children));
        Object[] elements = this.groupingContentProvider.getElements(group);

        // we found all children without any call to mockTreeContentProvider
        for (int i = 0; i < children.length; i++) {
            Object child = children[i];
            Object element = elements[i];
            assertEquals(child, element);
        }
    }

    /**
     * Test the getElement delegation to the delegateTreeContentProvider for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)}
     * .
     */
    @Test
    public void getElementsWithGroupDisabled() {
        // Set pref values to be inactive. (active only if isGroupEnable was at
        // true)
        setPrefGroupEnable(false);
        setPrefGroupSize(1);
        setPrefGroupTrigger(1);

        Object parent = createMock(EObject.class);
        Object[] children = new EObject[] { createMock(EObject.class), createMock(EObject.class) };

        // we found all children with a delegation to the
        // delegateTreeContentProvider
        // (here the mockTreeContentProvider)
        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);

        this.groupingContentProvider.getElements(parent);

        verify(this.mockTreeContentProvider);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)}
     * .
     */
    @Test
    public void getElementsWithGroupDisabled2() {

        // Set pref values to be inactive.
        // (active only if groupTrigger< |children| )
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        Object parent = createMock(EObject.class);
        Object[] children = new EObject[] { createMock(EObject.class), createMock(EObject.class) };

        // we found all children with a delegation to the
        // delegateTreeContentProvider
        // (here the mockTreeContentProvider)
        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        this.groupingContentProvider.getElements(parent);
        verify(this.mockTreeContentProvider);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)}
     * . We need to find:
     * 
     * <pre>
     * [0..1] eClassifiers:
     * * child1
     * * child2
     * [2..3] eClassifiers:
     * * child3
     * * child4
     * 
     * <pre>
     */
    @Test
    public void getElementsWithGroupEnable() {
        // Set pref values to be active.
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        EPackage parent = eFactory.createEPackage();

        EClassifier child1 = eFactory.createEClass();
        EClassifier child2 = eFactory.createEClass();
        EClassifier child3 = eFactory.createEClass();
        EClassifier child4 = eFactory.createEClass();
        Object[] children = new Object[] { child1, child2, child3, child4 };
        for (int i = 0; i < children.length; i++) {
            parent.getEClassifiers().add((EClassifier) children[i]);
        }

        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        Object[] elements = this.groupingContentProvider.getElements(parent);
        verify(this.mockTreeContentProvider);

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
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)}
     * . We need to find:
     * 
     * <pre>
     * [0..1] eClassifiers:
     * * child1
     * * child2
     * [2..2] eClassifiers:
     * * child3
     * 
     * <pre>
     */
    @Test
    public void getElementsWithGroupEnable2() {

        // Set pref values to be active.
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        EPackage parent = eFactory.createEPackage();

        EClassifier child1 = eFactory.createEClass();
        EClassifier child2 = eFactory.createEClass();
        EClassifier child3 = eFactory.createEClass();
        Object[] children = new Object[] { child1, child2, child3 };
        for (int i = 0; i < children.length; i++) {
            parent.getEClassifiers().add((EClassifier) children[i]);
        }

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
        assertEquals(MUST_HAVE_ONE_CHILD, 1, childrenOfSecondTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child3, childrenOfSecondTreeItem[0]);
    }

    /**
     * Test method for
     * {@link org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider#getElements(java.lang.Object)}
     * . We expect:
     * 
     * <pre>
     * [0..1] eClassifiers:
     * * child1
     * * child2
     * [2..2] eClassifiers:
     * * child3
     * child4
     * 
     * <pre>
     */
    @Test
    public void getElementsWithGroupEnable3() {

        // Set pref values to be active.
        setPrefGroupEnable(true);
        setPrefGroupSize(2);
        setPrefGroupTrigger(2);

        EPackage parent = eFactory.createEPackage();

        EClassifier child1 = eFactory.createEClass();
        EClassifier child2 = eFactory.createEClass();
        EClassifier child3 = eFactory.createEClass();
        EAnnotation child4 = eFactory.createEAnnotation();

        Object[] children = new Object[] { child1, child2, child3, child4 };
        for (int i = 0; i < children.length - 1; i++) {
            parent.getEClassifiers().add((EClassifier) children[i]);
        }
        parent.getEAnnotations().add(child4);

        expect(this.mockTreeContentProvider.getElements(parent)).andReturn(children);
        replay(this.mockTreeContentProvider);
        Object[] elements = this.groupingContentProvider.getElements(parent);
        verify(this.mockTreeContentProvider);

        // test the given elements
        assertEquals("Children splitted in two groups", 3, elements.length);

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
        assertEquals(MUST_HAVE_ONE_CHILD, 1, childrenOfSecondTreeItem.length);
        assertEquals(RETRIEVE_CHILDREN, child3, childrenOfSecondTreeItem[0]);

        // test the third grouping item
        Object thirdTreeItem = elements[2];
        assertTrue(CHECK_THE_TYPE_OF_THE_GROUPING_TREE_ITEM, thirdTreeItem instanceof EObject);
        assertEquals(RETRIEVE_CHILD, child4, thirdTreeItem);
    }
}
