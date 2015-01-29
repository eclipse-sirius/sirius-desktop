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
package org.eclipse.sirius.tests.unit.diagram.session;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.helper.display.VisibilityPropagatorAdapter;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * Validation tests.
 * 
 * @author dlecan
 */
public class MultiAirdResourcesSessionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/session/multiResources/";

    private static final String SEMANTIC_MODEL_FILENAME = "MultiResources.ecore";

    private static final String REFERENCED_SEMANTIC_MODEL_FILENAME_1 = "MultiResources_package1.ecore";

    private static final String REFERENCED_SEMANTIC_MODEL_FILENAME_2 = "MultiResources_package2.ecore";

    private static final String SESSION_MODEL_FILENAME = "MultiResources.aird";

    private static final String REFERENCED_SESSION_MODEL_FILENAME_1 = "MultiResources_package1.aird";

    private static final String REFERENCED_SESSION_MODEL_FILENAME_2 = "MultiResources_package2.aird";

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, REFERENCED_SEMANTIC_MODEL_FILENAME_1, REFERENCED_SEMANTIC_MODEL_FILENAME_2, SESSION_MODEL_FILENAME,
                REFERENCED_SESSION_MODEL_FILENAME_1, REFERENCED_SESSION_MODEL_FILENAME_2);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckNoVisibilityPropagatorAdapterWhenNoEditor() throws Exception {

        final Iterable<DDiagram> allDiagrams = Iterables.filter(DialectManager.INSTANCE.getAllRepresentations(session), DDiagram.class);

        assertTrue("There must be at least one diagram", allDiagrams.iterator().hasNext());

        boolean result = false;
        for (final DDiagram dDiagram : allDiagrams) {
            final EList<DDiagramElement> diagramElements = dDiagram.getDiagramElements();
            result = result || containsVisibilityAdapter(dDiagram.eAdapters());

            assertTrue("There must be at least one diagram element", diagramElements.iterator().hasNext());
            for (final DDiagramElement dDiagramElement : diagramElements) {
                result = result || containsVisibilityAdapter(dDiagramElement.eAdapters());
            }
        }

        assertFalse("No visibility propagator adapter should be found as there is no opened editor.", result);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckEachOpenDiagramHasItsVisibilityPropagatorAdapter() throws Exception {

        final Iterable<DDiagram> allDiagrams = Iterables.filter(DialectManager.INSTANCE.getAllRepresentations(session), DDiagram.class);

        assertTrue("There must be at least one diagram", allDiagrams.iterator().hasNext());

        for (final DDiagram dDiagram : allDiagrams) {
            IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();

            boolean result = containsVisibilityAdapter(dDiagram.eAdapters());

            final EList<DDiagramElement> diagramElements = dDiagram.getDiagramElements();
            assertTrue("There must be at least one diagram element", diagramElements.iterator().hasNext());
            for (final DDiagramElement dDiagramElement : diagramElements) {
                result = result && containsVisibilityAdapter(dDiagramElement.eAdapters());
            }
            assertTrue("At least one element in all diagrams doesn't own the VisibilityPropagatorAdapter", result);

            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckAllOpenDiagramHaveOnlyOneVisibilityPropagatorAdapter() throws Exception {

        final Iterable<DDiagram> allDiagrams = Iterables.filter(DialectManager.INSTANCE.getAllRepresentations(session), DDiagram.class);

        assertTrue("There must be at least two diagram", Iterables.size(allDiagrams) > 1);

        Map<DDiagram, IEditorPart> openEditors = Maps.newHashMap();
        for (final DDiagram dDiagram : allDiagrams) {
            IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            openEditors.put(dDiagram, editor);
        }

        for (final DDiagram dDiagram : allDiagrams) {
            boolean result = containsVisibilityAdapter(dDiagram.eAdapters());

            final EList<DDiagramElement> diagramElements = dDiagram.getDiagramElements();
            assertTrue("There must be at least one diagram element", diagramElements.iterator().hasNext());
            for (final DDiagramElement dDiagramElement : diagramElements) {
                result = result && containsVisibilityAdapter(dDiagramElement.eAdapters());
            }
            assertTrue("At least one element in all diagrams doesn't own the VisibilityPropagatorAdapter", result);

            DialectUIManager.INSTANCE.closeEditor(openEditors.get(dDiagram), false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    private boolean containsVisibilityAdapter(final List<Adapter> adapters) {
        int visibilityAdapterCount = 0;
        for (final Adapter adapter : adapters) {
            if (VisibilityPropagatorAdapter.class.isAssignableFrom(adapter.getClass())) {
                visibilityAdapterCount++;
            }
        }

        if (visibilityAdapterCount > 1) {
            fail("A DDiagramElement should not have several VisibilityPropagatorAdapter.");
        }

        return visibilityAdapterCount == 1;
    }
}
