/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.editors.traceability;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.marker.TraceabilityMarkerNavigationProvider;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IGotoMarker;
import org.junit.Assert;

/**
 * Checks the behavior of the goToMarker method of all DialectEditors. These
 * tests simulate the behavior of Traceability, which will call goToMarker on
 * all opened Editors with a shadow Validator marker referencing the semantic
 * element to reveal. More details available on
 * TraceabilityMarkerNavigationProvider's javadoc.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class GoToMarkerTests extends SiriusDiagramTestCase implements TraceabilityTestsModeler {

    private DRepresentation originalRepresentation;

    private DialectEditor originalEditor;

    private IMarker traceMarker;

    private EObject semanticElementForTraceability;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        ModelUtils.load(URI.createPlatformPluginURI(SEMANTIC_MODEL_PATH_2, true), session.getTransactionalEditingDomain().getResourceSet());
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on an Diagram
     * Editor containing a DRepresentationElement referencing the semantic
     * element (given by the Traceability marker), ensures that this element is
     * correctly selected.
     * 
     */
    public void testElementIsInCurrentOpenedEditorWithDiagramEditor() {
        setUpMarker(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2, SEMANTIC_ELEMENT_MYECLASS, true);

        /* We open 2 other editors (only the table contains also myEClass) */
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM1);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        /* When calling gotoMarker */
        originalEditor.getEditorSite().getPage().activate(originalEditor);
        TestsUtil.synchronizationWithUIThread();
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        /*
         * It should select the graphical element referencing myEClass in the
         * current editor
         */
        assertEditorSelectionEquals(originalEditor, semanticElementForTraceability);
        /* Other opened editors should not be impacted */
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on an Table
     * Editor containing a DRepresentationElement referencing the semantic
     * element (given by the Traceability marker), ensures that this element is
     * correctly selected.
     * 
     */
    public void testElementIsInCurrentOpenedEditorWithTableEditor() {
        setUpMarker(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2, SEMANTIC_ELEMENT_MYECLASS, true);

        // We open 2 other editors (only the table contains also myEClass)
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM1);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker from table2
        tableEditor.getEditorSite().getPage().activate(tableEditor);
        TestsUtil.synchronizationWithUIThread();
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        /*
         * It should select the graphical element referencing myEClass in table2
         * editor
         */
        assertEditorSelectionEquals(tableEditor, semanticElementForTraceability);
        /* Other opened editors should not be impacted */
        assertEditorSelectionEquals(originalEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on an
     * CrossTable Editor containing a DRepresentationElement referencing the
     * semantic element (given by the Traceability marker), ensures that this
     * element is correctly selected.
     * 
     */
    public void testElementIsInCurrentOpenedEditorWithCrossTableEditor() {
        setUpMarker(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM1, SEMANTIC_ELEMENT_A, true);

        // We open 2 other editors (only the crossTable contains also A)
        DRepresentation crossTable = (DRepresentation) getRepresentations(REPRESENTATION_CROSSTABLE).toArray()[0];
        DialectEditor crossTableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, crossTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker on the crossTable editor
        crossTableEditor.getEditorSite().getPage().activate(crossTableEditor);
        TestsUtil.synchronizationWithUIThread();
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        // It should select the graphical element referencing A in the
        // crossTable editor
        assertEditorSelectionEquals(crossTableEditor, semanticElementForTraceability);
        // Other opened editors should not be impacted
        assertEditorSelectionEquals(originalEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(crossTableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on an Tree
     * Editor containing a DRepresentationElement referencing the semantic
     * element (given by the Traceability marker), ensures that this element is
     * correctly selected.
     * 
     */
    public void testElementIsInCurrentOpenedEditorWithTreeEditor() {
        setUpMarker(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM1, SEMANTIC_ELEMENT_A, true);

        /* We open 2 other editors (only the tree contains also A) */
        DRepresentation tree = (DRepresentation) getRepresentations(REPRESENTATION_TREE).toArray()[0];
        DialectEditor treeEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, tree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        /* When calling gotoMarker on the tree editor */
        treeEditor.getEditorSite().getPage().activate(treeEditor);
        TestsUtil.synchronizationWithUIThread();
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        /*
         * It should select the graphical element referencing A in the tree
         * editor
         */
        assertEditorSelectionEquals(treeEditor, semanticElementForTraceability);
        assertEditorSelectionEquals(originalEditor, null);
        /* Other opened editors should not be impacted */
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(treeEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on the current
     * focused editor (not containing the searched element), ensures that the
     * element is found and correctly selected in the opened Diagram editor .
     */
    public void testElementIsInOneOfOpenedEditorWithDiagramEditor() {
        setUpMarker(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2, SEMANTIC_ELEMENT_MYECLASS, true);

        // We open 2 other editors who don't contain myEClass
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE1);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM1);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker on the table editor (that doesn't contain
        // myEClass)
        TestsUtil.synchronizationWithUIThread();
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        // It should select the graphical element referencing A in the
        // tree editor
        assertEditorSelectionEquals(originalEditor, semanticElementForTraceability);
        // Other editors should not be impacted
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on the current
     * focused editor (not containing the searched element), ensures that the
     * element is found and correctly selected in the opened Table editor .
     */
    public void testElementIsInOneOfOpenedEditorWithTableEditor() {
        setUpMarker(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2, SEMANTIC_ELEMENT_MYECLASS, true);

        // We open 2 other editors who don't contain myEClass
        DRepresentation tree = getRepresentationWithName(REPRESENTATION_TREE, REPRESENTATION_INSTANCE_TREE);
        DialectEditor treeEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, tree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM1);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker on the tree editor (that doesn't contain
        // myEClass)
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        // It should select the graphical element referencing A in the
        // tree editor
        assertEditorSelectionEquals(originalEditor, semanticElementForTraceability);
        // Other editors should not be impacted
        assertEditorSelectionEquals(treeEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(treeEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on the current
     * focused editor (not containing the searched element), ensures that the
     * element is found and correctly selected in the opened CrossTable editor .
     */
    public void testElementIsInOneOfOpenedEditorWithCrossTableEditor() {
        setUpMarker(REPRESENTATION_CROSSTABLE, REPRESENTATION_INSTANCE_CROSSTABLE1, SEMANTIC_ELEMENT_A, true);

        /* We open 2 other editors who don't contain A */
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        /*
         * When calling gotoMarker on the diagram editor (that doesn't contain
         * A)
         */
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();

        /*
         * It should select the graphical element referencing A in the tree
         * editor
         */
        assertEditorSelectionEquals(originalEditor, semanticElementForTraceability);
        /* Other editors should not be impacted */
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Simulates Traceability behavior : when calling gotoMarker on the current
     * focused editor (not containing the searched element), ensures that the
     * element is found and correctly selected in the opened Tree editor .
     */
    public void testElementIsInOneOfOpenedEditorWithTreeEditor() {
        setUpMarker(REPRESENTATION_TREE, REPRESENTATION_INSTANCE_TREE, SEMANTIC_ELEMENT_A, true);

        /* We open 2 other editors who don't contain A */
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        /*
         * When calling gotoMarker on the diagram editor (that doesn't contain
         * A)
         */
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        /*
         * It should select the graphical element referencing A in the tree
         * editor
         */
        assertEditorSelectionEquals(originalEditor, semanticElementForTraceability);
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOneMatchInNotOpenedRepresentationsWithDiagramEditor() {
        setUpMarker(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM1, "myEnum", false);
        DialectUIManager.INSTANCE.closeEditor(originalEditor, false);
        DialectEditor editorThatShouldBeClosed = getDialectEditor(REPRESENTATION_INSTANCE_DIAGRAM1);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_DIAGRAM1 + "' should not be opened", editorThatShouldBeClosed);

        // We open 2 other editors who don't contain A
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker on the diagram editor (that doesn't contain
        // A)
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        // It should select the graphical element referencing A in the
        // tree editor
        DialectEditor editorThatShouldHaveBeenOpened = getDialectEditor(REPRESENTATION_INSTANCE_DIAGRAM1);
        Assert.assertNotNull("No Dialect Editor found with title '" + REPRESENTATION_INSTANCE_DIAGRAM1 + "'", editorThatShouldHaveBeenOpened);
        assertEditorSelectionEquals(editorThatShouldHaveBeenOpened, semanticElementForTraceability);
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOneMatchInNotOpenedRepresentationsWithTableEditor() {
        setUpMarker(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE1, "theRefAnnotation", false);
        DialectUIManager.INSTANCE.closeEditor(originalEditor, false);
        DialectEditor editorThatShouldBeClosed = getDialectEditor(REPRESENTATION_INSTANCE_TABLE1);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_TABLE1 + "' should not be opened", editorThatShouldBeClosed);

        // We open 2 other editors who don't contain A
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker on the diagram editor (that doesn't contain
        // A)
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        // It should select the graphical element referencing A in the
        // tree editor
        DialectEditor editorThatShouldHaveBeenOpened = getDialectEditor(REPRESENTATION_INSTANCE_TABLE1);
        Assert.assertNotNull("No Dialect Editor found with title '" + REPRESENTATION_INSTANCE_TABLE1 + "'", editorThatShouldHaveBeenOpened);
        assertEditorSelectionEquals(editorThatShouldHaveBeenOpened, semanticElementForTraceability);
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOneMatchInNotOpenedRepresentationsWithCrossTableEditor() {
        setUpMarker(REPRESENTATION_CROSSTABLE, REPRESENTATION_INSTANCE_CROSSTABLE1, "myAttribute", false);
        DialectUIManager.INSTANCE.closeEditor(originalEditor, false);
        DialectEditor editorThatShouldBeClosed = getDialectEditor(REPRESENTATION_INSTANCE_CROSSTABLE1);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_CROSSTABLE1 + "' should not be opened", editorThatShouldBeClosed);

        /* We open 2 other editors which don't contain A */
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker on the diagram editor (that doesn't contain
        // A)
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        // It should select the graphical element referencing A in the
        // tree editor
        DialectEditor editorThatShouldHaveBeenOpened = getDialectEditor(REPRESENTATION_INSTANCE_CROSSTABLE1);
        Assert.assertNotNull("No Dialect Editor found with title '" + REPRESENTATION_INSTANCE_CROSSTABLE1 + "'", editorThatShouldHaveBeenOpened);
        assertEditorSelectionEquals(editorThatShouldHaveBeenOpened, semanticElementForTraceability);
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOneMatchInNotOpenedRepresentationsWithTreeEditor() {
        setUpMarker(REPRESENTATION_TREE, REPRESENTATION_INSTANCE_TREE, "myAnnotation", false);
        DialectUIManager.INSTANCE.closeEditor(originalEditor, false);
        DialectEditor editorThatShouldBeClosed = getDialectEditor(REPRESENTATION_INSTANCE_TREE);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_TREE + "' should not be opened", editorThatShouldBeClosed);

        // We open 2 other editors who don't contain A
        DRepresentation table = getRepresentationWithName(REPRESENTATION_TABLE, REPRESENTATION_INSTANCE_TABLE2);
        DialectEditor tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation otherDiagram = getRepresentationWithName(REPRESENTATION_DIAGRAM, REPRESENTATION_INSTANCE_DIAGRAM2);
        DialectEditor otherDiagramEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, otherDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // When calling gotoMarker on the diagram editor (that doesn't contain
        // A)
        goToMarkerOnAllOpenedEditors(traceMarker);
        TestsUtil.synchronizationWithUIThread();
        // It should select the graphical element referencing A in the
        // tree editor
        DialectEditor editorThatShouldHaveBeenOpened = getDialectEditor(REPRESENTATION_INSTANCE_TREE);
        Assert.assertNotNull("No Dialect Editor found with title '" + REPRESENTATION_INSTANCE_TREE + "'", editorThatShouldHaveBeenOpened);
        assertEditorSelectionEquals(editorThatShouldHaveBeenOpened, semanticElementForTraceability);
        assertEditorSelectionEquals(tableEditor, null);
        assertEditorSelectionEquals(otherDiagramEditor, null);

        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(otherDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Ensures that the given dialectEditor's selection is corresponding to the
     * given semanticElement. For instance : if the semantic Element is
     * 'EClass1' and the editor's selection is a DNode which associated semantic
     * element is 'EClass1'.
     * 
     * @param editor
     *            the editor to test
     * @param selection
     *            the semantic element that should match the selected element
     */
    protected void assertEditorSelectionEquals(DialectEditor editor, EObject selection) {

        EObject selectedSemantic = null;

        if (editor instanceof DDiagramEditor) {
            final ISelection currentSelection = ((IDiagramWorkbenchPart) editor).getDiagramGraphicalViewer().getSelection();
            final IGraphicalEditPart firstElement = (IGraphicalEditPart) ((IStructuredSelection) currentSelection).getFirstElement();
            if (firstElement != null) {
                View model = (View) firstElement.getModel();

                if (model.getElement() instanceof DRepresentationElement) {
                    DRepresentationElement element = (DRepresentationElement) model.getElement();
                    selectedSemantic = element.getSemanticElements().iterator().next();
                }
            }
        }
        if (editor instanceof AbstractDTreeEditor) {
            final ITreeSelection currentSelection = (ITreeSelection) ((AbstractDTreeEditor) editor).getViewer().getSelection();
            final DRepresentationElement firstElement = (DRepresentationElement) currentSelection.getFirstElement();
            if (firstElement != null) {
                selectedSemantic = firstElement.getSemanticElements().iterator().next();
            }
        }
        Assert.assertEquals("The editor '" + editor.getTitle() + "' has not the expected selection ", selection, selectedSemantic);
    }

    /**
     * Creates a shadow marker simulating Traceability behavior.
     * 
     * @return a shadow marker simulating Traceability behavior
     * @throws CoreException
     */
    protected IMarker createShadowTraceabilityMarker() throws CoreException {

        final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        // "org.eclipse.sirius.diagram.diagnostic"
        IMarker shadowMarker = workspaceRoot.createMarker(EValidator.MARKER);
        shadowMarker.setAttribute(TraceabilityMarkerNavigationProvider.TRACEABILITY_SEMANTIC_ELEMENT_URI_ATTRIBUTE, EcoreUtil.getURI(semanticElementForTraceability).toString());
        return shadowMarker;
    }

    /**
     * Calls the goToMarker method on all opened Editors like Traceability would
     * do.
     * 
     * @param marker
     *            the marker to go to
     */
    protected void goToMarkerOnAllOpenedEditors(IMarker marker) {
        for (IEditorReference ref : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()) {
            IEditorPart part = ref.getEditor(true);
            IEditingDomainProvider domainProvider = null;
            if (part instanceof IEditingDomainProvider) {
                domainProvider = (IEditingDomainProvider) part;
            } else {
                domainProvider = part.getAdapter(IEditingDomainProvider.class);
            }

            if (domainProvider != null) {
                ResourceSet rs = domainProvider.getEditingDomain().getResourceSet();
                Resource resource = rs.getResource(semanticElementForTraceability.eResource().getURI(), false);
                if (resource != null) {
                    ((IGotoMarker) part).gotoMarker(traceMarker);
                }
            }
        }
    }

    private DialectEditor getDialectEditor(String editorTitle) {
        final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        for (int i = 0; i < editorReferences.length; i++) {
            IEditorPart openedEditor = editorReferences[i].getEditor(false);
            /* If the editor is a Dialect editor */
            if (openedEditor instanceof DialectEditor) {
                String title = ((DialectEditor) openedEditor).getTitle();
                if (title.indexOf('(') > -1) {
                    title = title.substring(0, title.indexOf('('));
                }
                if (title.equals(editorTitle)) {
                    return (DialectEditor) openedEditor;
                }
            }
        }
        return null;
    }

    private DRepresentation getRepresentationWithName(String type, String diagramName) {
        for (final DRepresentation representation : getRepresentations(type)) {
            if (new DRepresentationQuery(representation).getRepresentationDescriptor().getName().equals(diagramName)) {
                return representation;
            }
        }
        throw new IllegalArgumentException("Representation with diagram name :" + diagramName + "could not be found.");
    }

    /**
     * Creates a sample Traceability marker on the semantic element with the
     * given semanticElementName, referenced by the representation with the
     * given representationName. If openEditor is true, also opens an editor on
     * this representation.
     * 
     * @param representationType
     *            the type of the representation to open
     * @param representationName
     *            the representation name
     * @param semanticElementName
     *            the semantic element name (on which the Traceability marker
     *            will be created)
     * @param openEditor
     *            indicates if an editor should be opened on the representation
     */
    protected void setUpMarker(String representationType, String representationName, String semanticElementName, boolean openEditor) {

        originalRepresentation = getRepresentationWithName(representationType, representationName);
        if (openEditor) {
            originalEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, originalRepresentation, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
        }

        final DRepresentationElement representationElementThatShouldBeSelected = getRepresentationElement(semanticElementName);

        semanticElementForTraceability = representationElementThatShouldBeSelected.getSemanticElements().get(0);

        try {
            traceMarker = createShadowTraceabilityMarker();
        } catch (Exception e) {
            // Nothing to do, test will fail
        }
    }

    private DRepresentationElement getRepresentationElement(final String semanticElementName) {

        for (final DRepresentationElement representationElement : originalRepresentation.getRepresentationElements()) {
            if (!representationElement.getSemanticElements().isEmpty()) {
                EObject firstSemanticElement = representationElement.getSemanticElements().iterator().next();
                if ((firstSemanticElement instanceof ENamedElement) && (((ENamedElement) firstSemanticElement).getName().equals(semanticElementName))) {
                    return representationElement;
                }
                if ((firstSemanticElement instanceof EAnnotation) && (semanticElementName.equals(((EAnnotation) firstSemanticElement).getSource()))) {
                    return representationElement;
                }
            }
        }

        return null;
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected void tearDown() throws Exception {
        traceMarker.delete();
        traceMarker = null;

        this.originalRepresentation = null;
        this.semanticElementForTraceability = null;

        /* close the editor */
        if (originalEditor != null) {
            DialectUIManager.INSTANCE.closeEditor(originalEditor, false);
            TestsUtil.synchronizationWithUIThread();
            originalEditor = null;
        }
        super.tearDown();
    }
}
