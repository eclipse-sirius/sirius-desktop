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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.gmf.runtime.common.ui.util.WorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.part.ValidateAction;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionClosedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.marker.TraceabilityMarkerNavigationProvider;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IGotoMarker;
import org.junit.Assert;

import junit.framework.AssertionFailedError;

/**
 * Checks the behavior of the goToMarker method of all DialectEditors, when
 * several editors are avalaible and choices are presented to end-user.
 * 
 * @author alagarde
 */
public class GoToMarkerTraceabilityWithUserInteractionTest extends AbstractScenarioTestCase {

    /**
     * 
     */
    private static final String REPRESENTATION_EMPTY_DIAGRAM = "EmptyDiagram";

    /**
     * The {@link SWTBotSiriusDiagramEditor} to use.
     */
    protected SWTBotEditor editor;

    private static final String DATA_UNIT_DIR = "/data/unit/editors/traceability/vp1038/";

    private static final String SEMANTIC_MODEL_PATH = "vp1038.ecore";

    private static final String SESSION_PATH = "vp1038.aird";

    private static final String MODELER_PATH = "vp1038.odesign";

    private static final String VIEWPOINT_NAME = "Traceability";

    private static final String REPRESENTATION_DIAGRAM = "Trace Diagram";

    private static final String REPRESENTATION_INSTANCE_DIAGRAM1 = "diagram";

    private static final String REPRESENTATION_INSTANCE_TABLE1 = "table";

    private static final String REPRESENTATION_INSTANCE_CROSSTABLE1 = "crossTable";

    private static final String REPRESENTATION_INSTANCE_TREE = "tree";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * The icon in the outline displayed when labels are shown (not hidden).
     */
    protected Image shownImage;

    /**
     * Boolean indicating if items of the outline are correctly decorated.
     */
    protected boolean outlineIsCorrectlyDecorated;

    private EObject semanticElementForTraceability;

    private IMarker traceMarker;

    private AssertionFailedError assertionException;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_MODEL_PATH, SESSION_PATH, MODELER_PATH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        if (traceMarker != null) {
            traceMarker.delete();
        }
    }

    /**
     * Open the representation and gather the initial bounds of all the bordered
     * nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_PATH);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * Ensures that when Traceability ask users to choose the representations
     * containing the searched element to open, selected editors are correctly
     * opened and have the expected selection.
     */
    public void testTraceabilityWhenSeveralMatchInNotOpenedRepresentationsWhenUserClickOk() {
        setUpMarker(REPRESENTATION_EMPTY_DIAGRAM, "emptyDiagram", "platform:/resource/DesignerTestProject/vp1038.ecore#//p1/A");

        callGoToMarkerOnAllOpenedEditors(traceMarker);

        bot.tree().getTreeItem("diagram").select().check();
        bot.tree().getTreeItem("tree").select().check();
        bot.button("OK").click();
        SWTBotUtils.waitAllUiEvents();

        final DialectEditor dialectEditor = getDialectEditor(REPRESENTATION_INSTANCE_DIAGRAM1);
        final DialectEditor dialectEditor2 = getDialectEditor(REPRESENTATION_INSTANCE_TREE);
        Assert.assertNotNull("The editor '" + REPRESENTATION_INSTANCE_DIAGRAM1 + "' should be opened", dialectEditor);
        Assert.assertNotNull("The editor '" + REPRESENTATION_INSTANCE_TREE + "' should be opened", dialectEditor2);

        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    checkEditorHasRightSelection(dialectEditor, semanticElementForTraceability);
                    checkEditorHasRightSelection(dialectEditor2, semanticElementForTraceability);
                } catch (AssertionFailedError e) {
                    assertionException = e;
                }
            }
        });
        SWTBotUtils.waitAllUiEvents();
        if (assertionException != null) {
            throw assertionException;
        }
    }

    /**
     * Ensure that after closing a representation editor having validation
     * errors, it can be reopened using an error marker from the Problem view.
     */
    public void testTraceabilityWithNoOpenedRepresentations() {
        processEditorOpeningFromMarker(false);
    }

    /**
     * Ensure that after closing a session containing a representation having
     * validation errors, it can be reopened using an error marker from the
     * Problem view.
     */
    public void testTraceabilityWithClosedSession() {
        processEditorOpeningFromMarker(true);
    }

    private void processEditorOpeningFromMarker(boolean fromClosedSession) {
        // Open editor
        editor = openRepresentation(localSession.getOpenedSession(), REPRESENTATION_EMPTY_DIAGRAM, "emptyDiagram", DDiagram.class);

        // Run validation
        WorkbenchPartDescriptor workbenchPartDescriptor = new WorkbenchPartDescriptor(editor.getReference().getId(), editor.getReference().getClass(), editor.getReference().getPage());
        ValidateAction va = new ValidateAction(workbenchPartDescriptor);
        va.run();

        // Close editor
        editor.close();
        SWTBotUtils.waitAllUiEvents();

        if (fromClosedSession) {
            // Close session
            SessionClosedCondition sessionClosedCondition = new SessionClosedCondition(localSession.getOpenedSession());
            localSession.getOpenedSession().close(new NullProgressMonitor());
            bot.waitUntil(sessionClosedCondition);
        }

        // Reopen the editor using a marker created during the validation
        SWTBotView problemsView = bot.viewByTitle("Problems");
        problemsView.setFocus();
        SWTBotTree problemsTree = problemsView.bot().tree();
        problemsTree.getTreeItem("Errors (3 items)").expand();

        // Reopen the editor using a marker created during the validation
        final SWTBotTreeItem node = problemsTree.getTreeItem("Errors (3 items)").getNode("The namespace URI '' is not well formed");
        node.select();

        // Double click the error marker to reopen the diagram
        Assert.assertFalse("An error happened before opening an editor using an error marker: " + getErrorLoggersMessage(), doesAnErrorOccurs());
        node.doubleClick();
        Assert.assertFalse("An error happened on opening of an editor using an error marker: " + getErrorLoggersMessage(), doesAnErrorOccurs());
    }

    /**
     * Ensures that when Traceability ask users to choose the representations
     * containing the searched element to open, and user cancel the action, no
     * editor are opened.
     */
    public void testTraceabilityWhenSeveralMatchInNotOpenedRepresentationsWhenUserClickCancel() {
        setUpMarker(REPRESENTATION_EMPTY_DIAGRAM, "emptyDiagram", "platform:/resource/DesignerTestProject/vp1038.ecore#//p1/A");

        callGoToMarkerOnAllOpenedEditors(traceMarker);

        bot.tree().getTreeItem(REPRESENTATION_INSTANCE_DIAGRAM1).select().check();
        bot.tree().getTreeItem(REPRESENTATION_INSTANCE_TREE).select().check();
        bot.button("Cancel").click();
        SWTBotUtils.waitAllUiEvents();

        final DialectEditor dialectEditor = getDialectEditor(REPRESENTATION_INSTANCE_DIAGRAM1);
        final DialectEditor dialectEditor2 = getDialectEditor(REPRESENTATION_INSTANCE_TREE);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_DIAGRAM1 + "' should not have been opened", dialectEditor);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_TREE + "' should not have been opened", dialectEditor2);
    }

    /**
     * Ensures that when Traceability ask users to choose the representations
     * containing the searched element to open, selected editors are correctly
     * opened and have the expected selection (here several editors have already
     * been opened).
     */
    public void testTraceabilityWhenGoToMarkerIsCalledOnAllOpenedEditors() {
        setUpMarker(REPRESENTATION_EMPTY_DIAGRAM, "emptyDiagram", "platform:/resource/DesignerTestProject/vp1038.ecore#//p1/A");

        final SWTBotSiriusDiagramEditor emptyDiagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_EMPTY_DIAGRAM, "emptyDiagram2",
                DDiagram.class);

        final SWTBotSiriusDiagramEditor emptyDiagramEditor3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_EMPTY_DIAGRAM, "emptyDiagram3",
                DDiagram.class);

        callGoToMarkerOnAllOpenedEditors(traceMarker);

        SWTBotUtils.waitAllUiEvents();
        bot.tree().getTreeItem(REPRESENTATION_INSTANCE_TABLE1).select().check();
        bot.tree().getTreeItem(REPRESENTATION_INSTANCE_CROSSTABLE1).select().check();
        bot.button("OK").click();
        SWTBotUtils.waitAllUiEvents();

        final DialectEditor dialectEditor = getDialectEditor(REPRESENTATION_INSTANCE_DIAGRAM1);
        final DialectEditor dialectEditor2 = getDialectEditor(REPRESENTATION_INSTANCE_TREE);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_DIAGRAM1 + "' should not have been opened", dialectEditor);
        Assert.assertNull("The editor '" + REPRESENTATION_INSTANCE_TREE + "' should not have been opened", dialectEditor2);
    }

    /**
     * Ensures that the given dialectEditor's selection is corresponding to the
     * given semanticElement. For instance : if the semantic Element is
     * 'EClass1' and the editor's selection is a DNode which associated semantic
     * element is 'EClass1'.
     * 
     * @param editor
     *            the editor to test
     * @param semanticElementThatShouldBeSelected
     *            the semantic element that should match the selected element
     */
    protected void checkEditorHasRightSelection(DialectEditor editor, EObject semanticElementThatShouldBeSelected) {

        EObject selectedSemantic = null;

        if (editor instanceof SiriusDiagramEditor) {
            ISelection selection = ((IDiagramWorkbenchPart) editor).getDiagramGraphicalViewer().getSelection();
            IGraphicalEditPart firstElement = (IGraphicalEditPart) ((IStructuredSelection) selection).getFirstElement();
            if (firstElement != null) {
                View model = (View) firstElement.getModel();

                if (model.getElement() instanceof DRepresentationElement) {
                    DRepresentationElement element = (DRepresentationElement) model.getElement();
                    selectedSemantic = element.getSemanticElements().iterator().next();
                }
            }
        }
        if (editor instanceof AbstractDTreeEditor) {
            ITreeSelection selection = (ITreeSelection) ((AbstractDTreeEditor) editor).getViewer().getSelection();
            DRepresentationElement firstElement = (DRepresentationElement) selection.getFirstElement();
            if (firstElement != null) {
                selectedSemantic = firstElement.getSemanticElements().iterator().next();
            }
        }
        Assert.assertEquals("The editor '" + editor.getTitle() + "' has not the expected selection ", semanticElementThatShouldBeSelected, selectedSemantic);
    }

    /**
     * Creates a shadow marker simulating Traceability behavior.
     * 
     * @return a shadow marker simulating Traceability behavior
     * @throws CoreException
     */
    protected IMarker createShadowTraceabilityMarker() throws CoreException {

        final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        IMarker shadowMarker = workspaceRoot.createMarker(EValidator.MARKER);
        shadowMarker.setAttribute(TraceabilityMarkerNavigationProvider.TRACEABILITY_SEMANTIC_ELEMENT_URI_ATTRIBUTE, EcoreUtil.getURI(semanticElementForTraceability).toString());
        return shadowMarker;
    }

    private DialectEditor getDialectEditor(String editorTitle) {
        final IEditorReference[] editorReferences = editor.getReference().getPage().getEditorReferences();
        for (int i = 0; i < editorReferences.length; i++) {
            IEditorPart openedEditor = editorReferences[i].getEditor(false);
            // If the editor is a Dialect editor
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
     * @param semanticElementURI
     *            the semantic element URI (on which the Traceability marker
     *            will be created)
     */
    protected void setUpMarker(String representationType, String representationName, String semanticElementURI) {

        if (REPRESENTATION_DIAGRAM.equals(representationType) || REPRESENTATION_EMPTY_DIAGRAM.equals(representationType)) {
            editor = openRepresentation(localSession.getOpenedSession(), representationType, representationName, DDiagram.class);
        } else {
            editor = openRepresentation(localSession.getOpenedSession(), representationType, representationName, DTable.class);
        }

        ResourceSet resourceSet = localSession.getOpenedSession().getTransactionalEditingDomain().getResourceSet();
        URI uri = URI.createURI(semanticElementURI);
        semanticElementForTraceability = resourceSet.getEObject(uri, true);

        try {
            traceMarker = createShadowTraceabilityMarker();
        } catch (Exception e) {
            // Nothing to do, test will fail
        }
    }

    /**
     * Calls the goToMarker method on all opened Editors like Traceability would
     * do.
     * 
     * @param marker
     *            the marker to go to
     */
    protected void callGoToMarkerOnAllOpenedEditors(IMarker marker) {
        SWTBotUtils.waitAllUiEvents();
        UIThreadRunnable.asyncExec(new VoidResult() {

            @Override
            public void run() {
                for (IEditorReference ref : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()) {
                    IEditorPart part = ref.getEditor(true);
                    IEditingDomainProvider domainProvider = null;
                    if (part instanceof IEditingDomainProvider) {
                        domainProvider = (IEditingDomainProvider) part;
                    } else {
                        domainProvider = (IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class);
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
        });

    }
}
