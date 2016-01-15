/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISaveablePart;

/**
 * Test transient session, especially according to VP-2781.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TransientSessionTests extends SiriusDiagramTestCase {

    private static final String SESSION_RESOURCE_NAME = "Test.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "Test.ecore";

    private static final URI designSiriusURI = URI.createURI("viewpoint:/org.eclipse.sirius.sample.ecore.design/Design");

    private URI transientSessionResourceURI;

    private URI semanticResourceURI;

    private IEditingSession uiSession;

    private EPackage rootEPackage;

    private Viewpoint designSirius;

    private RepresentationDescription entitiesRepresentationDescription;

    private RepresentationDescription classesRepresentationDescription;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        closeWelcomePage();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        transientSessionResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
        semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, true);

        // Create a transient session
        session = SessionManager.INSTANCE.getSession(transientSessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());

        uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();

        // Add a semantic ecore resource
        Resource semanticResource = new ResourceSetImpl().createResource(semanticResourceURI);
        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        semanticResource.getContents().add(ePackage);
        semanticResource.save(Collections.emptyMap());
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        rootEPackage = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);

        designSirius = ViewpointRegistry.getInstance().getViewpoint(designSiriusURI);

        Command changeSiriussSelectionCmd = new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(), Collections.singleton(designSirius), Collections.<Viewpoint> emptySet(),
                new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelectionCmd);

        designSirius = session.getSelectedViewpoints(false).iterator().next();
        entitiesRepresentationDescription = designSirius.getOwnedRepresentations().get(0);
        classesRepresentationDescription = designSirius.getOwnedRepresentations().get(1);
    }

    /**
     * Test transient session with a diagram representation.
     */
    public void testDiagramWithTransientSession() {
        IEditorPart diagramEditor = createAndOpenRepresentation(entitiesRepresentationDescription, "new Diagram");
        assertTrue(diagramEditor instanceof DDiagramEditor);
        DDiagramEditor dDiagramEditor = (DDiagramEditor) diagramEditor;
        assertEquals(diagramEditor, uiSession.getEditor(dDiagramEditor.getRepresentation()));
        assertEquals("one editor should be opened", 1, uiSession.getEditors().size());

        // Create a EClass
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        Command addEClassCmd = AddCommand.create(session.getTransactionalEditingDomain(), rootEPackage, EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, eClass);
        session.getTransactionalEditingDomain().getCommandStack().execute(addEClassCmd);
        TestsUtil.synchronizationWithUIThread();

        assertTrue(diagramEditor instanceof ISaveablePart);
        ISaveablePart saveablePart = diagramEditor;
        assertEquals("With a transient session adding a EClass should, the session become dirty ", SessionStatus.DIRTY, session.getStatus());
        assertTrue("With a transient session adding a EClass should, the editor become dirty ", saveablePart.isDirty());
        assertTrue("The resource status is wrong for a transient session. It should be modified after modification.", session.getSessionResource().isModified());

        // Save on diagramEditor
        saveablePart.doSave(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals("With a transient session the save should be allowed", SessionStatus.SYNC, session.getStatus());
        assertFalse("With a transient session, saving the editor, it become no dirty ", saveablePart.isDirty());
        assertFalse("The resource status is wrong for a transient session. It should be \"not modified\" after a save.", session.getSessionResource().isModified());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, true);
        TestsUtil.synchronizationWithUIThread();

        // Checks that editor is closed
        assertEquals("all editors should be closed", 0, uiSession.getEditors().size());
    }

    /**
     * Test transient session with a table representation.
     */
    public void testTableWithTransientSession() {
        IEditorPart tableEditor = createAndOpenRepresentation(classesRepresentationDescription, "new Table");
        assertTrue(tableEditor instanceof DTableEditor);
        DTableEditor dTableEditor = (DTableEditor) tableEditor;
        assertEquals(dTableEditor, uiSession.getEditor(dTableEditor.getRepresentation()));
        assertEquals("one editor should be opened", 1, uiSession.getEditors().size());

        // Create a EClass
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        Command addEClassCmd = AddCommand.create(session.getTransactionalEditingDomain(), rootEPackage, EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, eClass);
        session.getTransactionalEditingDomain().getCommandStack().execute(addEClassCmd);
        TestsUtil.synchronizationWithUIThread();

        assertTrue(tableEditor instanceof ISaveablePart);
        ISaveablePart saveablePart = tableEditor;
        assertEquals("With a transient session adding a EClass should, the session become dirty ", SessionStatus.DIRTY, session.getStatus());
        assertTrue("With a transient session adding a EClass should, the editor become dirty ", saveablePart.isDirty());

        // Save on tableEditor
        saveablePart.doSave(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals("With a transient session the save should be allowed", SessionStatus.SYNC, session.getStatus());
        assertFalse("With a transient session, saving the editor, it become no dirty ", saveablePart.isDirty());

        DialectUIManager.INSTANCE.closeEditor(tableEditor, true);
        TestsUtil.synchronizationWithUIThread();

        // Checks that editor is closed
        assertEquals("all editors should be closed", 0, uiSession.getEditors().size());
    }

    /**
     * Test transient session with a diagram and table representation.
     */
    public void testDiagramAndTableWithTransientSession() {
        IEditorPart diagramEditor = createAndOpenRepresentation(entitiesRepresentationDescription, "new Diagram");
        assertTrue(diagramEditor instanceof DDiagramEditor);
        DDiagramEditor dDiagramEditor = (DDiagramEditor) diagramEditor;
        assertEquals(diagramEditor, uiSession.getEditor(dDiagramEditor.getRepresentation()));
        assertEquals("one editor should be opened", 1, uiSession.getEditors().size());
        IEditorPart tableEditor = createAndOpenRepresentation(classesRepresentationDescription, "new Table");
        assertTrue(tableEditor instanceof DTableEditor);
        DTableEditor dTableEditor = (DTableEditor) tableEditor;
        assertEquals(dTableEditor, uiSession.getEditor(dTableEditor.getRepresentation()));
        assertEquals("two editors should be opened", 2, uiSession.getEditors().size());

        // Create a EClass on diagramEditor
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        Command addEClassCmd = AddCommand.create(session.getTransactionalEditingDomain(), rootEPackage, EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, eClass);
        session.getTransactionalEditingDomain().getCommandStack().execute(addEClassCmd);
        TestsUtil.synchronizationWithUIThread();

        assertTrue(tableEditor instanceof ISaveablePart);
        ISaveablePart tableEditorSaveable = tableEditor;
        assertTrue(diagramEditor instanceof ISaveablePart);
        ISaveablePart diagramEditorSaveable = diagramEditor;
        assertEquals("With a transient session adding a EClass should, the session become dirty ", SessionStatus.DIRTY, session.getStatus());
        assertTrue("With a transient session adding a EClass should, the table editor become dirty ", tableEditorSaveable.isDirty());
        assertTrue("With a transient session adding a EClass should, the diagram editor become dirty ", diagramEditorSaveable.isDirty());

        // Save on tableEditor
        tableEditorSaveable.doSave(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals("With a transient session the save should be allowed", SessionStatus.SYNC, session.getStatus());
        assertFalse("With a transient session, saving the table editor, it become no dirty ", tableEditorSaveable.isDirty());
        assertFalse("With a transient session, saving the table editor, the diagram editor also become no dirty ", diagramEditorSaveable.isDirty());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, true);
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(tableEditor, true);
        TestsUtil.synchronizationWithUIThread();

        // Checks that all editors are closed
        assertEquals("all editors should be closed", 0, uiSession.getEditors().size());

    }

    /**
     * Create a representation according to the specified
     * {@link RepresentationDescription} with the specified representation name
     * and open it.
     */
    private IEditorPart createAndOpenRepresentation(RepresentationDescription representationDescription, String representationName) {
        CreateRepresentationCommand createRepCmd = new CreateRepresentationCommand(session, representationDescription, rootEPackage, representationName, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(createRepCmd);
        DRepresentation dRepresentation = createRepCmd.getCreatedRepresentation();
        IEditorPart representationEditor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        return representationEditor;
    }

    @Override
    protected void tearDown() throws Exception {
        uiSession = null;
        rootEPackage = null;
        designSirius = null;
        entitiesRepresentationDescription = null;
        classesRepresentationDescription = null;
        super.tearDown();
    }

}
