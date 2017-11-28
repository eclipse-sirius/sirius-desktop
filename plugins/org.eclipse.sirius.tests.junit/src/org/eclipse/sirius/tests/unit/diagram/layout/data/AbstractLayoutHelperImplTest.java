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
package org.eclipse.sirius.tests.unit.diagram.layout.data;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.AdvancedSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SiriusLayoutDataManagerForSemanticElements;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.junit.Assert;

import junit.framework.AssertionFailedError;

/**
 * Common behavior for all LayoutHelperImpl tests.
 * 
 * @author dlecan
 * @param <T>
 *            Type of layout data.
 */
public abstract class AbstractLayoutHelperImplTest<T extends AbstractLayoutData> extends SiriusDiagramTestCase {

    /**
     * Inner class to wrap layout data.
     * 
     * @author dlecan
     */
    protected abstract class LayoutDataWrapper {
        /**
         * Constructor.
         * 
         * @param layoutData
         */
        protected LayoutDataWrapper(final T layoutData) {
            this.layoutData = layoutData;
        }

        private final T layoutData;

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object obj) {
            boolean result = false;
            if (obj instanceof AbstractLayoutHelperImplTest<?>.LayoutDataWrapper) {
                result = doEquals(((AbstractLayoutHelperImplTest<T>.LayoutDataWrapper) obj).layoutData);
            }
            return result;
        }

        protected abstract boolean doEquals(T otherLayoutData);

        /**
         * Returns the layoutData.
         * 
         * @return The layoutData.
         */
        protected T getThisLayoutData() {
            return layoutData;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "Layout data ID: " + layoutData.getId();
        }

    }

    private static final int NUM_ITERATIONS = 20;

    private static final String DATA_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layout/data/";

    private static final String SEMANTIC_MODEL_PATH = DATA_PATH + "My.ecore";

    private static final String MODELER_PATH = DATA_PATH + "My.odesign";

    private static final String REPRESENTATIONS_PATH = DATA_PATH + "My.aird";

    private static final String REPRESENTATION_DESC_NAME = "DiagType2";

    private AdvancedSiriusLayoutDataManager manager;

    private DDiagram diagram;

    private DiagramEditor editorPart;

    private T referenceLayoutData;

    private Object eq1;

    private Object eq2;

    private Object eq3;

    private Object neq;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        manager = new SiriusLayoutDataManagerForSemanticElements();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];

        editorPart = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final DiagramEditPart diagramEditPart = editorPart.getDiagramEditPart();

        manager.storeLayoutData(diagramEditPart);

        referenceLayoutData = getReferenceLayoutData();

        eq1 = createWrappedInstance(referenceLayoutData);
        eq2 = createWrappedInstance(referenceLayoutData);
        eq3 = createWrappedInstance(referenceLayoutData);
        neq = createWrappedNotEqualInstance();

        // We want these assertions to yield errors, not failures.
        try {
            assertNotNull("createInstance() returned null", eq1);
            assertNotNull("2nd createInstance() returned null", eq2);
            assertNotNull("3rd createInstance() returned null", eq3);
            assertNotNull("createNotEqualInstance() returned null", neq);

            Assert.assertNotSame(eq1, eq2);
            Assert.assertNotSame(eq1, eq3);
            Assert.assertNotSame(eq1, neq);
            Assert.assertNotSame(eq2, eq3);
            Assert.assertNotSame(eq2, neq);
            Assert.assertNotSame(eq3, neq);

            assertEquals("1st and 2nd equal instances of different classes", eq1.getClass(), eq2.getClass());
            assertEquals("1st and 3rd equal instances of different classes", eq1.getClass(), eq3.getClass());
            assertEquals("1st equal instance and not-equal instance of different classes", eq1.getClass(), neq.getClass());
        } catch (final AssertionFailedError ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    /**
     * Creates and returns an instance of the class under test.
     * 
     * @param from
     *            Data to use to create a new instance.
     * @return a new instance of the class under test; each object returned from
     *         this method should compare equal to each other.
     * @throws Exception
     *             Creation error.
     */
    protected abstract AbstractLayoutHelperImplTest<T>.LayoutDataWrapper createWrappedInstance(T from) throws Exception;

    /**
     * Creates and returns an instance of the class under test.
     * 
     * @return a new instance of the class under test; each object returned from
     *         this method should compare equal to each other, but not to the
     *         objects returned from {@link #createInstance() createInstance}.
     * @throws Exception
     *             Creation error.
     */
    protected abstract AbstractLayoutHelperImplTest<T>.LayoutDataWrapper createWrappedNotEqualInstance() throws Exception;

    /**
     * Get reference layout data.
     * 
     * @return
     */
    protected abstract T getReferenceLayoutData();

    /**
     * Tests whether <code>equals</code> holds up against a new
     * <code>Object</code> (should always be <code>false</code>).
     */
    public final void testHaveSameLayoutAgainstNewObject() {
        final Object o = new Object();

        SiriusAssert.assertNotEquals(eq1, o);
        SiriusAssert.assertNotEquals(eq2, o);
        SiriusAssert.assertNotEquals(eq3, o);
        SiriusAssert.assertNotEquals(neq, o);
    }

    /**
     * Tests whether <code>equals</code> holds up against <code>null</code>.
     */
    public final void testHaveSameLayoutAgainstNull() {
        SiriusAssert.assertNotEquals("1st vs. null", eq1, null);
        SiriusAssert.assertNotEquals("2nd vs. null", eq2, null);
        SiriusAssert.assertNotEquals("3rd vs. null", eq3, null);
        SiriusAssert.assertNotEquals("not-equal vs. null", neq, null);
    }

    /**
     * Tests whether <code>equals</code> holds up against objects that should
     * not compare equal.
     */
    public final void testHaveSameLayoutAgainstUnequalObjects() {
        SiriusAssert.assertNotEquals("1st vs. not-equal", eq1, neq);
        SiriusAssert.assertNotEquals("2nd vs. not-equal", eq2, neq);
        SiriusAssert.assertNotEquals("3rd vs. not-equal", eq3, neq);

        SiriusAssert.assertNotEquals("not-equal vs. 1st", neq, eq1);
        SiriusAssert.assertNotEquals("not-equal vs. 2nd", neq, eq2);
        SiriusAssert.assertNotEquals("not-equal vs. 3rd", neq, eq3);
    }

    /**
     * Tests whether <code>equals</code> is <em>consistent</em>.
     */
    public final void testHaveSameLayoutIsConsistentAcrossInvocations() {
        for (int i = 0; i < NUM_ITERATIONS; ++i) {
            testHaveSameLayoutAgainstNewObject();
            testHaveSameLayoutAgainstNull();
            testHaveSameLayoutAgainstUnequalObjects();
            testHaveSameLayoutIsReflexive();
            testHaveSameLayoutIsSymmetricAndTransitive();
        }
    }

    /**
     * Tests whether <code>equals</code> is <em>reflexive</em>.
     */
    public final void testHaveSameLayoutIsReflexive() {
        assertEquals("1st equal instance", eq1, eq1);
        assertEquals("2nd equal instance", eq2, eq2);
        assertEquals("3rd equal instance", eq3, eq3);
        assertEquals("not-equal instance", neq, neq);
    }

    /**
     * Tests whether <code>equals</code> is <em>symmetric</em> and
     * <em>transitive</em>.
     */
    public final void testHaveSameLayoutIsSymmetricAndTransitive() {
        assertEquals("1st vs. 2nd", eq1, eq2);
        assertEquals("2nd vs. 1st", eq2, eq1);

        assertEquals("1st vs. 3rd", eq1, eq3);
        assertEquals("3rd vs. 1st", eq3, eq1);

        assertEquals("2nd vs. 3rd", eq2, eq3);
        assertEquals("3rd vs. 2nd", eq3, eq2);
    }

    /**
     * Returns the manager.
     * 
     * @return The manager.
     */
    protected AdvancedSiriusLayoutDataManager getManager() {
        return manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.emptyEventsFromUIThread();
        super.tearDown();
    }

}
