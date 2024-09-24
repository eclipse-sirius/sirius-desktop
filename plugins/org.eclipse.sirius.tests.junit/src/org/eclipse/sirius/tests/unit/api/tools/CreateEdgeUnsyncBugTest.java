/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.ui.IEditorPart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import junit.framework.AssertionFailedError;

/**
 * Test the creation of edges in synchronized and unsynchronized contexts.
 * 
 * @author Séraphin Costa
 * 
 */
@RunWith(value = Parameterized.class)
public class CreateEdgeUnsyncBugTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/tool/bugEdge/";

    private static final String SEMANTIC_MODEL_NAME = "My.ecore";

    private static final String REPRESENTATION_MODEL_NAME = "representations.aird";

    private static final String MODELER_NAME = "project.odesign";

    private DDiagram diagram;

    private IEditorPart editorPart;

    private boolean autoRefresh;

    private boolean unsynchronizedDiagram;

    public CreateEdgeUnsyncBugTest(String name, boolean autoRefresh, boolean unsynchronizedDiagram) {
        this.autoRefresh = autoRefresh;
        this.unsynchronizedDiagram = unsynchronizedDiagram;
    }

    @Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        // {name, autoRefresh, unsynchronizedDiagram}
        // @formatter:off
        return Arrays.asList(new Object[][] {
            { "manual refresh, sync", false, false },
            { "manual refresh, unsync", false, true },
            { "auto refresh, sync", true, false },
            { "auto refresh, unsync", true, true },
        });
        // @formatter:on
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_NAME, MODELER_NAME, REPRESENTATION_MODEL_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATION_MODEL_NAME);
    }
    
    private void openDiagram(String representationDescName, String representationName) {
        diagram = (DDiagram) getRepresentations(representationDescName).stream() //
                .filter(rep -> representationName.equals(rep.getName())) //
                .findFirst() //
                .orElseThrow(() -> new AssertionFailedError("Cannot found the representation `" + representationName + "` of type `" + representationDescName + "`."));
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    private EReference getReference(EClassifier eClassifier, String name) {
        var notFoundError = new AssertionFailedError("Cannot found the reference with name `" + name + "` in the class `" + eClassifier.getName() + "`.");
        if (eClassifier instanceof EClass eClass) {
            return eClass.getEReferences().stream().filter(ref -> name.equals(ref.getName())).findFirst().orElseThrow(() -> notFoundError);
        } else {
            throw notFoundError;
        }
    }

    private boolean hasReference(EClassifier eClassifier, String name) {
        if (eClassifier instanceof EClass eClass) {
            return eClass.getEReferences().stream().anyMatch(ref -> name.equals(ref.getName()));
        } else {
            return false;
        }
    }

    /**
     * Check that the edge creation tool only creates the expected edge (simple case).
     */
    @Test
    public void testEdgeCreationSimpleCase() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefresh);

        openDiagram("DiagEdgeUnsync", "new DiagEdgeUnsync");

        if (unsynchronizedDiagram) {
            unsynchronizeDiagram(diagram);
        } // else, default: synchronized

        EPackage ePackage = (EPackage) semanticModel;
        EClassifier source = ePackage.getEClassifier("cls1");
        EClassifier target = ePackage.getEClassifier("cls2");
        assertNotNull("`cls1` not found in the model", source);
        assertNotNull("`cls2` not found in the model", target);

        EdgeTarget nodeSource = (EdgeTarget) getFirstDiagramElement(diagram, source);
        EdgeTarget nodeTarget = (EdgeTarget) getFirstDiagramElement(diagram, target);
        assertNotNull("`cls1` not found on the diagram", source);
        assertNotNull("`cls2` not found on the diagram", target);

        applyEdgeCreationTool("CreateEdge1", diagram, nodeSource, nodeTarget);
        assertTrue("`newRef` not found in the model (from `cls1`) after applying the edge creation tool", hasReference(source, "newRef"));

        EReference ref = getReference(source, "ref");
        EReference newRef = getReference(source, "newRef");
        DEdge refEdge = getFirstEdgeElement(diagram, ref);
        DEdge newRefEdge = getFirstEdgeElement(diagram, newRef);
        assertNotNull("`newRef` edge not found on the diagram after applying the edge creation tool", newRefEdge);
        assertNull("`ref` edge found on the diagram after applying the edge creation tool", refEdge);

        refresh(diagram);

        refEdge = getFirstEdgeElement(diagram, ref);
        newRefEdge = getFirstEdgeElement(diagram, newRef);
        assertNotNull("`newRef` edge not found on the diagram after refresh", newRefEdge);
        assertNull("`ref` edge found on the diagram after refresh", refEdge);
    }

    private boolean hasRepresentationOfMapping(final DRepresentation representation, final EObject semanticElement, String mappingName) {
        Collection<DRepresentationElement> repElements = getAllRepresentationElements(representation, semanticElement);
        return repElements.stream().anyMatch(repElement -> mappingName.equals(repElement.getMapping().getName()));
    }

    /**
     * Check the initial reference present between cl1 and cls2
     * 
     * @param message
     *            The error message
     * @param source
     *            cls1 semantic element
     */
    private void checkRef(String message, EClassifier source) {
        EReference ref = getReference(source, "ref");
        assertFalse(message + ": edge (from cls1 to cls2, mapping Relation3) found on the diagram", hasRepresentationOfMapping(diagram, ref, "Relation3"));
        assertTrue(message + ": edge (from cls1 to ref node, mapping Relation4) not found on the diagram", hasRepresentationOfMapping(diagram, ref, "Relation4"));
        assertTrue(message + ": edge (from ref node to cls2, mapping Relation5) not found on the diagram", hasRepresentationOfMapping(diagram, ref, "Relation5"));
        assertTrue(message + ": node (mapping Reference) not found on the diagram", hasRepresentationOfMapping(diagram, ref, "Reference"));
    }

    /**
     * Check that the edge creation tool only creates the expected edge (complex case with many mapping on the same
     * object).
     */
    @Test
    public void testEdgeCreationComplexCase() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefresh);

        openDiagram("DiagEdgeDepEdge", "new DiagEdgeDepEdge");

        if (unsynchronizedDiagram) {
            unsynchronizeDiagram(diagram);
        } // else, default: synchronized

        EPackage ePackage = (EPackage) semanticModel;
        EClassifier source = ePackage.getEClassifier("cls1");
        EClassifier target = ePackage.getEClassifier("cls2");
        assertNotNull("`cls1` not found in the model", source);
        assertNotNull("`cls2` not found in the model", target);

        EdgeTarget nodeSource = (EdgeTarget) getFirstDiagramElement(diagram, source);
        EdgeTarget nodeTarget = (EdgeTarget) getFirstDiagramElement(diagram, target);
        assertNotNull("`cls1` not found on the diagram", source);
        assertNotNull("`cls2` not found on the diagram", target);

        checkRef("`ref` reference misrepresented on the diagram before creation tool", source);

        applyEdgeCreationTool("CreateEdge3", diagram, nodeSource, nodeTarget);
        assertTrue("`newRef` not found in the model (from `cls1`) after applying the edge creation tool", hasReference(source, "newRef"));

        EReference newRef = getReference(source, "newRef");

        checkRef("`ref` reference misrepresented on the diagram after applying the edge creation tool", source);

        // expected values before refresh
        boolean toolEdgeIsCreated = true;
        boolean syncIsCreated = autoRefresh;
        boolean unsyncIsCreated = autoRefresh && !unsynchronizedDiagram;

        assertEquals("`newRef` edge (from cls1 to cls2, mapping Relation3) not found on the diagram after applying the edge creation tool", toolEdgeIsCreated,
                hasRepresentationOfMapping(diagram, newRef, "Relation3"));
        assertEquals("`newRef` edge (from cls1 to newRef node, mapping Relation4) not found on the diagram after applying the edge creation tool", syncIsCreated,
                hasRepresentationOfMapping(diagram, newRef, "Relation4"));
        assertEquals("`newRef` edge (from newRef node to cls2, mapping Relation5) not found on the diagram after applying the edge creation tool", unsyncIsCreated,
                hasRepresentationOfMapping(diagram, newRef, "Relation5"));
        assertEquals("`newRef` node (mapping Reference) not found on the diagram after applying the edge creation tool", syncIsCreated, hasRepresentationOfMapping(diagram, newRef, "Reference"));

        refresh(diagram);

        checkRef("`ref` reference misrepresented on the diagram after refresh", source);

        // expected values after refresh
        // toolEdgeIsCreated = true; // already true
        syncIsCreated = true;
        unsyncIsCreated = !unsynchronizedDiagram;

        assertEquals("`newRef` edge (from cls1 to cls2, mapping Relation3) not found on the diagram after applying the edge creation tool", toolEdgeIsCreated,
                hasRepresentationOfMapping(diagram, newRef, "Relation3"));
        assertEquals("`newRef` edge (from cls1 to newRef node, mapping Relation4) not found on the diagram after applying the edge creation tool", syncIsCreated,
                hasRepresentationOfMapping(diagram, newRef, "Relation4"));
        assertEquals("`newRef` edge (from newRef node to cls2, mapping Relation5) not found on the diagram after applying the edge creation tool", unsyncIsCreated,
                hasRepresentationOfMapping(diagram, newRef, "Relation5"));
        assertEquals("`newRef` node (mapping Reference) not found on the diagram after applying the edge creation tool", syncIsCreated, hasRepresentationOfMapping(diagram, newRef, "Reference"));
    }

    @After
    @Override
    public void tearDown() throws Exception {

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = null;
        editorPart = null;

        super.tearDown();
    }
}
