/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.command;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.common.tools.api.util.CommandStackUtil;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.command.EPackageEClassifiersAppenderRecordingCommand;
import org.eclipse.sirius.tests.unit.common.command.RepresentationDeleterRecordingCommand;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Version;

/**
 * Tests creation and deletion undo command based on entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class CreationAndDeletionUndoRedoTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DRepresentationDescriptor repDescriptor;

    private Object cmdStack;

    private DRepresentation createdRepresentation;

    private boolean emptyDiagram = true;

    private boolean containerAreListContainer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        repDescriptor = (DRepresentationDescriptor) getRepresentationDescriptors(ENTITIES_DESC_NAME).toArray()[0];
        /* on entities container are currently list */
        containerAreListContainer = true;

        TestsUtil.emptyEventsFromUIThread();
    }

    public void testCreationWithEmptyDiagram() throws Exception {

        CommandStackUtil.flushOperations(session.getTransactionalEditingDomain().getCommandStack());
        final IEditorPart editor = createNewRepresentation();
        cmdStack = session.getTransactionalEditingDomain().getCommandStack();

        launchUndos(createdRepresentation);

        checkEditorHasBeenClosed(editor);
    }

    private void checkEditorHasBeenClosed(final IEditorPart editor) {
        boolean editorFound = false;
        IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        for (IEditorReference ref : editorReferences) {
            editorFound = editorFound || editor.equals(ref.getEditor(false));
        }
        assertFalse("The editor " + editor.getTitle() + " should have been closed on its representation creation undo.", editorFound);
    }

    public void testCreationWithEmptyDiagramWithUndoFromDiagram() throws Exception {
        CommandStackUtil.flushOperations(session.getTransactionalEditingDomain().getCommandStack());
        final IEditorPart editor = createNewRepresentation();
        cmdStack = session.getTransactionalEditingDomain().getCommandStack();

        launchUndos(createdRepresentation);

        checkEditorHasBeenClosed(editor);
    }

    public void testCreation() throws Exception {

        createData();

        CommandStackUtil.flushOperations(session.getTransactionalEditingDomain().getCommandStack());
        final IEditorPart editor = createNewRepresentation();
        cmdStack = session.getTransactionalEditingDomain().getCommandStack();

        launchUndos(createdRepresentation);

        checkEditorHasBeenClosed(editor);
    }

    public void testCreationWithUndoFromDiagram() throws Exception {

        createData();

        CommandStackUtil.flushOperations(session.getTransactionalEditingDomain().getCommandStack());
        final IEditorPart editor = createNewRepresentation();
        cmdStack = session.getTransactionalEditingDomain().getCommandStack();

        launchUndos(createdRepresentation);

        checkEditorHasBeenClosed(editor);
    }

    private void createData() {
        final EPackage ePackage = (EPackage) semanticModel;
        createEClass(ePackage);
        emptyDiagram = false;
    }

    private EClass createEClass(final EPackage ePackage) {
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        session.getTransactionalEditingDomain().getCommandStack().execute(new EPackageEClassifiersAppenderRecordingCommand(session.getTransactionalEditingDomain(), ePackage, eClass));
        return eClass;
    }

    private IEditorPart createNewRepresentation() {
        final RepresentationDescription description = repDescriptor.getDescription();
        final EObject semanticElement = repDescriptor.getTarget();
        final CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session, description, semanticElement, "plop", new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(createRepresentationCommand);
        createdRepresentation = createRepresentationCommand.getCreatedRepresentation();
        assertNotNull(createdRepresentation);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, createdRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        disableUICallBackOnDialectEditor((DialectEditor) editor);

        return editor;
    }

    private void launchUndos(final DRepresentation createdRepresentation) throws Exception {

        if (!emptyDiagram && (!containerAreListContainer || isPlatformAtLeastEclipse36())) {
            /* arrange all creation command */
            assertTrue(canUndo());
            assertEquals(getArrangeAllCommandLabel(), getCommandLabel());
            undo();
            assertTrue(DialectManager.INSTANCE.getAllRepresentations(session).contains(createdRepresentation));
            TestsUtil.synchronizationWithUIThread();
        }

        /* undo refresh creation command */
        assertTrue(canUndo());
        assertEquals("Refresh diagram on opening", getCommandLabel());
        undo();
        assertTrue(DialectManager.INSTANCE.getAllRepresentations(session).contains(createdRepresentation));
        TestsUtil.synchronizationWithUIThread();

        /* undo diagram creation command */
        assertTrue(canUndo());
        assertEquals("Create representation", getCommandLabel());
        undo();
        TestsUtil.synchronizationWithUIThread();

        /* there should not remain any command */
        assertFalse(canUndo());

        /* the created representation should have been deleted */
        assertFalse(DialectManager.INSTANCE.getAllRepresentations(session).contains(createdRepresentation));
    }

    private String getArrangeAllCommandLabel() {
        /*
         * org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy. getArrangeCommand() starting from
         * Eclipse 3.6 return directly a ArrangeCommand with an empty label. But since Sirius 7.0, a name is given into
         * SiriusContainerEditPolicy.
         */
        if (isPlatformAtLeastEclipse36())
            return Messages.SiriusContainerEditPolicy_arrangeCommandLabel;
        else
            return "Arrange all";
    }

    private boolean isPlatformAtLeastEclipse36() {
        Version platformVersion = Platform.getBundle("org.eclipse.core.runtime").getVersion();
        return platformVersion.getMinor() >= 6;
    }

    private String getCommandLabel() throws Exception {
        if (cmdStack instanceof IWorkspaceCommandStack) {
            final IWorkspaceCommandStack ws = (IWorkspaceCommandStack) cmdStack;
            return ws.getOperationHistory().getUndoOperation(ws.getDefaultUndoContext()).getLabel();
        } else if (cmdStack instanceof CommandStack)
            return ((CommandStack) cmdStack).getUndoCommand().getLabel();
        else if (cmdStack instanceof org.eclipse.gef.commands.CommandStack)
            return ((org.eclipse.gef.commands.CommandStack) cmdStack).getUndoCommand().getLabel();
        else
            throw new Exception("not a valid command stack");
    }

    @Override
    protected boolean undo() throws Exception {
        if (cmdStack instanceof CommandStack)
            ((CommandStack) cmdStack).undo();
        else if (cmdStack instanceof org.eclipse.gef.commands.CommandStack)
            ((org.eclipse.gef.commands.CommandStack) cmdStack).undo();
        else
            throw new Exception("not a valid command stack");
        return true;
    }

    private boolean canUndo() throws Exception {
        if (cmdStack instanceof CommandStack)
            return ((CommandStack) cmdStack).canUndo();
        else if (cmdStack instanceof org.eclipse.gef.commands.CommandStack)
            return ((org.eclipse.gef.commands.CommandStack) cmdStack).canUndo();
        else
            throw new Exception("not a valid command stack");
    }

    public void testDeletion() throws Exception {
        assertTrue(DialectManager.INSTANCE.getAllRepresentationDescriptors(session).contains(repDescriptor));

        session.getTransactionalEditingDomain().getCommandStack().execute(new RepresentationDeleterRecordingCommand(session.getTransactionalEditingDomain(), repDescriptor, session));

        assertFalse(DialectManager.INSTANCE.getAllRepresentationDescriptors(session).contains(repDescriptor));
        session.getTransactionalEditingDomain().getCommandStack().undo();
        assertTrue(DialectManager.INSTANCE.getAllRepresentationDescriptors(session).contains(repDescriptor));
    }

    public void testDeletionWithEmptyDiagram() throws Exception {

        CommandStackUtil.flushOperations(session.getTransactionalEditingDomain().getCommandStack());

        cmdStack = session.getTransactionalEditingDomain().getCommandStack();

        CommandStackUtil.flushOperations(session.getTransactionalEditingDomain().getCommandStack());

        session.getTransactionalEditingDomain().getCommandStack().execute(new RepresentationDeleterRecordingCommand(session.getTransactionalEditingDomain(), repDescriptor, session));

        assertTrue(canUndo());
        undo();
        assertTrue(DialectManager.INSTANCE.getAllRepresentationDescriptors(session).contains(repDescriptor));
    }

    @Override
    protected void tearDown() throws Exception {
        repDescriptor = null;
        cmdStack = null;
        createdRepresentation = null;

        super.tearDown();
    }
}
