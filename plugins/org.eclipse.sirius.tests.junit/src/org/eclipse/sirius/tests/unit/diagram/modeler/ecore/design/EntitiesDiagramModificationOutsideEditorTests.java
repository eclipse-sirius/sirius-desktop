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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramHelper;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;

/**
 * Tests behaviors when model (semantic or session) is modified outside the editor (in the same editingDomain or not).
 * 
 * @author lredor
 */
public class EntitiesDiagramModificationOutsideEditorTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Copy the semantic file in the workspace
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_SEMANTIC_MODEL_PROJECT_RELATIVE_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + TEST_SEMANTIC_MODEL_FILENAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + TEST_SEMANTIC_MODEL_FILENAME, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

    /**
     * This test check that when we are in refresh manual mode, the node is only delete when a manual refresh is launch.
     * <BR>
     * It also check that the EditMode of the editPart is changed.<BR>
     * Corresponds to ticket #1639.
     * 
     */
    public void testDeleteSemanticElementCorrespondingToNode() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull("The editor did not open ! ", editorPart);

        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        // Create a first class
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        assertEquals("The class was not created or more elements were created", 1, ePackage.getEClassifiers().size());
        assertTrue("The class has not the right instance type", ePackage.getEClassifiers().get(0) instanceof EClass);
        final EClass eClass1 = (EClass) ePackage.getEClassifiers().get(0);
        assertEquals("The class has not the right name", "NewEClass1", eClass1.getName());

        final DDiagramElement firstClassDiagramElement = getFirstDiagramElement(diagram, eClass1);
        assertNotNull("The first class has no corresponding diagramElement", firstClassDiagramElement);
        final Node firstClassNode = getGmfNode(firstClassDiagramElement);
        assertNotNull("The first class has no corresponding GMF node", firstClassNode);
        // Create a second class
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        assertEquals("The second class was not created or more elements were created", 2, ePackage.getEClassifiers().size());
        assertTrue("The second class has not the right instance type", ePackage.getEClassifiers().get(0) instanceof EClass);
        final EClass eClass2 = (EClass) ePackage.getEClassifiers().get(1);
        assertEquals("The second class has not the right name", "NewEClass2", eClass2.getName());

        DDiagramElement secondClassDiagramElement = getFirstDiagramElement(diagram, eClass2);
        assertNotNull("The second class has no corresponding diagramElement", secondClassDiagramElement);
        IGraphicalEditPart secondClassEditPart = getEditPart(secondClassDiagramElement);
        assertNotNull("The second class has no corresponding edit part.", secondClassEditPart);
        assertTrue("The editMode of the second class must be enabled.", secondClassEditPart.isEditModeEnabled());
        Node secondClassNode = getGmfNode(secondClassDiagramElement);
        assertNotNull("The second class has no corresponding GMF node", secondClassNode);
        // Create a reference between the two classes.
        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) firstClassDiagramElement, (EdgeTarget) secondClassDiagramElement);
        assertEquals("The operation was not created or more elements were created", 1, Iterables.size(Iterables.filter(eClass1.getEStructuralFeatures(), EReference.class)));
        final EReference eReference = Iterables.filter(eClass1.getEStructuralFeatures(), EReference.class).iterator().next();
        assertEquals("The reference has not the right name", "newEReference1", eReference.getName());
        assertEquals("The reference has not the right type", eClass2, eReference.getEType());

        // Delete the semantic second eClass and set to null the eType of
        // the reference
        Command removeEClassCmd = RemoveCommand.create(session.getTransactionalEditingDomain(), ePackage, EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, eClass2);
        Command setTypeCmd = SetCommand.create(session.getTransactionalEditingDomain(), eReference, EcorePackage.Literals.ETYPED_ELEMENT__ETYPE, null);
        setTypeCmd.canExecute();

        session.getTransactionalEditingDomain().getCommandStack().execute(removeEClassCmd.chain(setTypeCmd));

        editorPart.setFocus();

        // Checks that the DDiagramElement corresponding to the deleted
        // semantic element exists yet
        assertNotNull(getFirstDiagramElement(diagram, eClass2));

        // Launch a manual refresh to delete the orphan DDiagramElement
        // corresponding to the deleted eClass2
        Command refreshCmd = new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), new NullProgressMonitor(), diagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(refreshCmd);

        // After a refresh the orphan DDiagramElement corresponding to the
        // deleted eClass2 should be deleted
        assertNull(getFirstDiagramElement(diagram, eClass2));

        // And its EditPart sould be also deleted because not usefull
        assertNull(getEditPart(secondClassDiagramElement));

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * This test check that there is not an exception thrown when calling DDiagramHelper.findParentDDiagram on a diagram
     * with error message.
     * 
     * Use the loading of an old version of the aird file to get a diagram with error message like in the steps of issue
     * VP-2198.
     */
    public void testRevertAirdFileAfterDiagramCreation() {
        // Check the entry data
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model must be empty before the tool application.", ePackage.getEClassifiers().isEmpty());

        // Save the new created session
        session.save(new NullProgressMonitor());

        // Load the aird resource in another resource set, to keep it and then
        // restore it.
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        assertFalse("The editing domain of each aird file must be different.", domain.equals(session.getTransactionalEditingDomain()));
        ResourceSet set = domain.getResourceSet();

        EObject previousSessionContent = null;
        try {
            previousSessionContent = ModelUtils.load(getDefaultRepresentationsFileURI(), set);
        } catch (IOException e) {
            fail("Pb to load the aird file in another resource set : " + e.getMessage());
        }

        // Create a new diagram with a distinct name
        final DRepresentation newEPackageRepresentation = createRepresentation(ENTITIES_DESC_NAME, ePackage);
        assertTrue("The representation should be a DDiagram", newEPackageRepresentation instanceof DDiagram);
        final DDiagram newEPackageDiagram = (DDiagram) newEPackageRepresentation;

        // Open the editor
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, newEPackageDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue("The editor should be a DiagramEditor.", editorPart instanceof DiagramEditor);
        DiagramEditor editor = (DiagramEditor) editorPart;
        // Disable dialogs with an automatic reloading
        disableUICallBackOnDialectEditor((DialectEditor) editorPart);
        // Refresh it
        refresh(newEPackageDiagram);
        // Save the session (with the new diagram)
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Revert the previous aird version (without the new diagram)
        try {
            previousSessionContent.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb to restore the previous version of aird file : " + e.getMessage());
        }
        TestsUtil.synchronizationWithUIThread();
        // Activate the error catching (to detect msg during closing)
        platformProblemsListener.setErrorCatchActive(true);

        try {
            DDiagramHelper.findParentDDiagram(editor.getDiagramEditPart());
        } catch (Exception e) {
            fail("We must not throw exception when calling DDiagramHelper.findParentDDiagram on a diagram with error message \"This diagram was not saved. You can close the editor\".");
        } catch (StackOverflowError e) {
            fail("We must not throw error when calling DDiagramHelper.findParentDDiagram on a diagram with error message \"This diagram was not saved. You can close the editor\".");
        }

        // Close the diagram
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
        // Deactivate the error catching (the error detection is done during the
        // super.tearDown)
        platformProblemsListener.setErrorCatchActive(false);
    }

    /**
     * This test check that there is no message in the error log when we select an element in the diagram that have its
     * target deleted outside the editor (in another resourceSet).<BR>
     * Issue VP-1948.
     */
    public void testNoMsgInErrorLogWhenSelectedADNodeListWithProxyTarget() {
        // Open the editor
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.emptyEventsFromUIThread();
        assertTrue("The editor should be of type DialectEditor.", editorPart instanceof DialectEditor);
        // Disable dialogs with an automatic reloading
        disableUICallBackOnDialectEditor((DialectEditor) editorPart);
        // Check the entry data
        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model must be empty before the tool application.", ePackage.getEClassifiers().isEmpty());
        // Add a class in this package with diagram tool and save the session
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        refresh(diagram);
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Load the semantic resource in another resource set, delete the
        // class and save the resource.
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(ePackage.eResource().getURI(), set);
            assertFalse("The editing domain of each root semantic must be different.", domain.equals(TransactionUtil.getEditingDomain(ePackage)));

            domain.getCommandStack().execute(new RecordingCommand(domain, "Remove all classes") {

                @Override
                protected void doExecute() {
                    ePackageInAnotherResourceSet.getEClassifiers().clear();
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        TestsUtil.synchronizationWithUIThread();

        // Activate the error catching (to detect msg during selection)
        platformProblemsListener.setErrorCatchActive(true);
        // Select the corresponding element in the diagram
        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        final IGraphicalEditPart editPart = getEditPart(diagramElement, editorPart);
        editPart.setFocus(true);

        // Deactivate the error catching (the error detection is done during the
        // super.tearDown)
        platformProblemsListener.setErrorCatchActive(false);

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

}
