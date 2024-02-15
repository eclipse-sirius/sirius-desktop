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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.matcher.DeletedDecoratorMatcher;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Some tests about the unsyncrhonized mapping and semantic deletion made outside Sirius : See VP-2351.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class UnsynchronizedMappingAndDeleteFromOutsideEditorTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PROJECT_RELATIVE_PATH = "data/unit/synchronization/unsynchronizedWithDeleteOuside.ecore";

    private static final String MODELER_PROJECT_RELATIVE_PATH = "data/unit/synchronization/unsynchronizedWithDeleteOuside.odesign";

    private static final String AIRD_PROJECT_RELATIVE_PATH = "data/unit/synchronization/unsynchronizedWithDeleteOuside.aird";

    private static final String DIAGRAM_DESCRIPTION_NAME = "unsynchroWithDeleteOutsideDiag";

    private DDiagram diagram;

    private IEditorPart editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Copy the use case files in workspace
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SEMANTIC_MODEL_PROJECT_RELATIVE_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_PROJECT_RELATIVE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, MODELER_PROJECT_RELATIVE_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_PROJECT_RELATIVE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, AIRD_PROJECT_RELATIVE_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + AIRD_PROJECT_RELATIVE_PATH);
        // Init the use case
        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_PROJECT_RELATIVE_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_PROJECT_RELATIVE_PATH,
                "/" + TEMPORARY_PROJECT_NAME + "/" + AIRD_PROJECT_RELATIVE_PATH);
        TestsUtil.emptyEventsFromUIThread();
        // Open the diagram
        diagram = (DDiagram) getRepresentations(DIAGRAM_DESCRIPTION_NAME).toArray()[0];
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Disable dialogs with an automatic reloading
        disableUICallBackOnDialectEditor((DialectEditor) editor);
    }

    @Override
    protected void tearDown() throws Exception {
        // Close the editor and clean member variables
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        diagram = null;
        editor = null;
        TestsUtil.synchronizationWithUIThread();

        super.tearDown();
    }

    /**
     * Checks that the behavior is OK in automatic refresh for DDiagramElement with mapping unsynchronized and whose
     * target is a semantic that was deleted outside of the diagram.
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the editor.
     */
    public void testRefreshOfNotSynchroMapping_AutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        assertEquals("The diagram does not contain the correct number of children.", 5, diagram.getOwnedDiagramElements().size());

        modifySemanticModelOutsideDiagram();
        // Check the number of diagram elements
        assertEquals("The diagram should only contain elements that are not deleted.", 2, diagram.getOwnedDiagramElements().size());
        // Check the number of edit parts
        assertEquals("The diagram editPart should only contain elements that are not deleted.", 2, getEditPart(diagram).getChildren().size());
    }

    /**
     * Checks that the behavior is OK in manual refresh for DDiagramElement with mapping unsynchronized and whose target
     * is a semantic that was deleted outside of the diagram.
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the editor.
     */
    public void testRefreshOfNotSynchroMapping_ManualRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        assertEquals("The diagram does not contain the correct number of children.", 5, diagram.getOwnedDiagramElements().size());

        // Keep some semantic elements for future tests
        EPackage ePackage = (EPackage) semanticModel;
        EClass testedClass = (EClass) ePackage.getESubpackages().get(2).getEClassifier("C1InSubRoot");
        EPackage testedPackage = ePackage.getESubpackages().get(2).getESubpackages().get(0);

        assertFalse("At least one error occurs before the semantic modification.", platformProblemsListener.doesAnErrorOccurs());
        modifySemanticModelOutsideDiagram();
        // Check there is no errorLog message during semantic modification and
        // diagram refresh
        assertFalse("At least one error occurs during the semantic modification (and so diagram refresh).", platformProblemsListener.doesAnErrorOccurs());
        // Check the number of diagram elements
        assertEquals("The diagram should not be modify because we are in manual refresh mode.", 5, diagram.getOwnedDiagramElements().size());
        // Check edit part for a DNode
        IGraphicalEditPart testedClassEditPart = getEditPart(testedClass);
        assertFalse("The editMode of editPart which target the class C1InSubRoot must be disabled.", testedClassEditPart.isEditModeEnabled());
        // Check edit part for a DNode is decorated with a red cross
        assertTrue("No deleted decorator found on editPart which target the class C1InSubRoot", new DeletedDecoratorMatcher().matches(testedClassEditPart));
        IGraphicalEditPart testedPackageEditPart = getEditPart(testedPackage);
        // Check edit part for a DContainer
        assertFalse("The editMode of editPart which target the package p1InSubRoot must be disabled.", testedPackageEditPart.isEditModeEnabled());
        // Check edit part for a DNode is decorated with a red cross
        assertTrue("No deleted decorator found on editPart which target the package p1InSubRoot", new DeletedDecoratorMatcher().matches(testedPackageEditPart));
        // The behind checks are done only if we are not in read only mode
        if (PermissionAuthorityRegistry.getDefault().getPermissionAuthority(diagram).canEditInstance(diagram)) {
            // Launch a manual refresh and check again the number of diagram
            // elements
            refresh(diagram);
            assertFalse("At least one error occurs during the manual refresh.", platformProblemsListener.doesAnErrorOccurs());
            assertEquals("The diagram should only contain elements that are not deleted.", 2, diagram.getOwnedDiagramElements().size());
        }
    }

    /**
     * Checks that the behavior is OK in manual refresh for DDiagramElement with mapping unsynchronized and whose target
     * is a semantic that was deleted outside of the diagram (in read only mode).
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the editor.
     */
    public void testRefreshOfNotSynchroMapping_ManualRefresh_WithReadOnlyPermissionEnabled() throws Exception {
        // activate the ReadOnlyPermission Authority on the representation
        assertTrue("The current editor should be a DialectEditor.", editor instanceof DialectEditor);
        DialectEditor dialectEditor = (DialectEditor) editor;
        ((ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dialectEditor.getRepresentation())).activate();
        testRefreshOfNotSynchroMapping_ManualRefresh();
    }

    /**
     * Checks that the "Delete from diagram" is not available for DDiagramElement that will be removed at the next
     * manual refresh.
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the editor.
     */
    public void testDeleteFromDiagramNotActivatedForDisableEditPart() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        assertEquals("The diagram does not contain the correct number of children.", 5, diagram.getOwnedDiagramElements().size());

        // Keep some semantic elements for future tests
        EPackage ePackage = (EPackage) semanticModel;
        EClass testedClass = (EClass) ePackage.getESubpackages().get(2).getEClassifier("C1InSubRoot");
        EPackage testedPackage = ePackage.getESubpackages().get(2).getESubpackages().get(0);

        assertFalse("At least one error occurs before the semantic modification.", platformProblemsListener.doesAnErrorOccurs());
        modifySemanticModelOutsideDiagram();
        // Check there is no errorLog message during semantic modification and
        // diagram refresh
        assertFalse("At least one error occurs during the semantic modification (and so diagram refresh).", platformProblemsListener.doesAnErrorOccurs());

        // Check the number of diagram elements
        assertEquals("The diagram should not be modify because we are in manual refresh mode.", 5, diagram.getOwnedDiagramElements().size());
        // Check the disablement of action DeleteFromDiagram for the DNode edit
        // part
        final StructuredSelection dNodeSelection = new StructuredSelection(getEditPart(testedClass));
        final DeleteFromDiagramAction actionDelegate = new DeleteFromDiagramAction();
        final Action mockAction = new Action() {};
        actionDelegate.selectionChanged(mockAction, dNodeSelection);
        assertFalse("As the node edit part has its target deleted then the delete from diagram should be disabled", mockAction.isEnabled());

        // Check the disablement of action DeleteFromDiagram for the
        // DNodeContainer edit part
        final StructuredSelection dNodeContainerSelection = new StructuredSelection(getEditPart(testedPackage));
        actionDelegate.selectionChanged(mockAction, dNodeContainerSelection);
        assertFalse("As the nodeContainer edit part has its target deleted then the delete from diagram should be disabled", mockAction.isEnabled());
    }

    /**
     * Check that a drag'n'drop is not available in DDiagramElement that will be removed at the next manual refresh.
     * 
     * @throws Exception
     *             In case of problem during semantic modification outside the editor.
     */
    public void testDragNDropForDisableEditPart() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        assertEquals("The diagram does not contain the correct number of children.", 5, diagram.getOwnedDiagramElements().size());

        // Keep some semantic elements for future tests
        EPackage ePackage = (EPackage) semanticModel;
        EPackage dropTargetPackage = ePackage.getESubpackages().get(2);

        assertFalse("At least one error occurs before the semantic modification.", platformProblemsListener.doesAnErrorOccurs());
        modifySemanticModelOutsideDiagram();
        // Check there is no errorLog message during semantic modification and
        // diagram refresh
        assertFalse("At least one error occurs during the semantic modification (and so diagram refresh).", platformProblemsListener.doesAnErrorOccurs());

        // Get some semantic elements for future tests
        EPackage rootPackage = (EPackage) ((DSemanticDiagram) diagram).getTarget();
        EPackage dragElementToContainer = rootPackage.getESubpackages().get(0);
        EPackage dragElementToDiagram = rootPackage.getESubpackages().get(1);

        // Try Drag'n'Drop in diagram (must be OK)
        final ContainerDropDescription dragAndDropToolInDiagram = (ContainerDropDescription) getTool(diagram, "DndPackageInDiagram");
        assertNotNull("Could not find the tool to drop a container in the diagram", dragAndDropToolInDiagram);
        DCommand command = (DCommand) getCommandFactory().buildDropInContainerCommandFromTool(diagram, dragElementToDiagram, dragAndDropToolInDiagram);
        int nbDiagElementsBeforeDnD = diagram.getOwnedDiagramElements().size();
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Check the number of diagram elements
        assertEquals("This drag'n'drop is authorized, so it should be one diagram element more.", nbDiagElementsBeforeDnD + 1, diagram.getOwnedDiagramElements().size());

        // Try Drag'n'Drop in a container with deleted target (must be KO)
        final ContainerDropDescription dragAndDropToolInContainer = (ContainerDropDescription) getTool(diagram, "DndPackageInPackage");
        assertNotNull("Could not find the tool to drop a container in the container", dragAndDropToolInContainer);
        // When user try to drag'n'drop, the command is not build because the
        // GraphicalEditPart.showTargetFeedback return before because the
        // editMode of the edit part is disabled. But for this test we try to do
        // the d'n'd.
        command = (DCommand) getCommandFactory().buildDropInContainerCommandFromTool((DNodeContainer) getFirstDiagramElement(diagram, dropTargetPackage), dragElementToContainer,
                dragAndDropToolInContainer);
        nbDiagElementsBeforeDnD = diagram.getOwnedDiagramElements().size();
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        assertTrue("A message must be logged because during the build of the command the session of the deleted object can not be reached.", platformProblemsListener.doesAnErrorOccurs());
        platformProblemsListener.clearErrors();
        // Check the number of diagram elements
        assertEquals("This drag'n'drop is not authorized, so the number of diagram element should be the same.", nbDiagElementsBeforeDnD, diagram.getOwnedDiagramElements().size());
    }

    /**
     * Get the edit part which have this target.
     * 
     * @return the corresponding edit part.
     */
    private IGraphicalEditPart getEditPart(EObject target) {
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, target);
        assertNotNull("The semantic element has no corresponding diagramElement", diagramElement);
        IGraphicalEditPart editPart = getEditPart(diagramElement);
        assertNotNull("The semantic element has no corresponding edit part.", editPart);
        return editPart;
    }

    /**
     * Delete the "subRoot" package. The modification is not made in the same resourceSet, as if this modification is
     * made in another editor not in Sirius.
     */
    private void modifySemanticModelOutsideDiagram() throws Exception {
        EPackage ePackage = (EPackage) semanticModel;

        // Load the semantic resource in another resource set, delete the
        // elements and save the resource.
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(ePackage.eResource().getURI(), set);
            assertFalse("The editing domain of each root semantic must be different.", domain.equals(TransactionUtil.getEditingDomain(ePackage)));

            domain.getCommandStack().execute(new RecordingCommand(domain, "Delete semantic elements outside the diagram") {

                @Override
                protected void doExecute() {
                    // Remove the package subRoot
                    ePackageInAnotherResourceSet.getESubpackages().remove(2);
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        // Wait job ResourceSyncClientNotifier to ensure the session has been
        // reloaded.
        if (Display.getCurrent() != null) {
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {
                @Override
                public void run(IProgressMonitor monitor) throws InterruptedException {
                    Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, monitor);
                }
            });
        } else {
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        }
    }
}
