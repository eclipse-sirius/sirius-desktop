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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

public class DiagramSynchronizationTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/packages.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/ecore_with_blank.odesign";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_DESC_NAME = "Blank Entities";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramSynchronized() throws Exception {
        initViewpoint(VIEWPOINT_NAME);
        final DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;
        final EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, root).iterator().next();

        assertTrue("This DDiagram should be synchronized.", rootdiagram.isSynchronized());

        dropSemantic(root.getEClassifier("Root"), rootdiagram, null);

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);
        // We expect duplicates of the previous edges, with the new
        // representation of Child.
        assertEquals("There are not as many edges as expected.", 2, rootdiagram.getEdges().size());
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramUnsynchronized() throws Exception {
        initViewpoint(VIEWPOINT_NAME);
        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;
        final EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, root).iterator().next();

        assertTrue("Error while toggling synchronization mode.", setDDiagramAttribute(session.getTransactionalEditingDomain(), rootdiagram, "synchronized", false));

        dropSemantic(root.getEClassifier("Root"), rootdiagram, null);

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);

        EList<DEdge> edges = rootdiagram.getEdges();

        assertEquals("The diagram should not have any edges.", 0, edges.size());

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramUnsynchronizedOnDiagramCreation() throws Exception {
        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);
        initViewpoint(VIEWPOINT_NAME);
        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;
        final EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, root).iterator().next();

        assertFalse("This DDiagram should not be synchronized.", rootdiagram.isSynchronized());

        dropSemantic(root.getEClassifier("Root"), rootdiagram, null);

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);

        EList<DEdge> edges = rootdiagram.getEdges();

        assertEquals("The diagram should not have any edges.", 0, edges.size());
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
