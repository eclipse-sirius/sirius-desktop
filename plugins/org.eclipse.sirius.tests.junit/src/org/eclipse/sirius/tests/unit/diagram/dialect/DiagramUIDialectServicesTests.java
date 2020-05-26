/*******************************************************************************
 * Copyright (c) 2016, 2020 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.dialect;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.command.DeleteRepresentationCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUIServices;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * This class tests the services of {@link DiagramDialectUIServices}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DiagramUIDialectServicesTests extends SiriusDiagramTestCase {

    private boolean oldPropertyValue;

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.junit/data/unit/dialect/aqlDomainClassDef.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/dialect/aqlDomainClassDef.ecore";

    private DialectEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint("aqlDomainClassDef");
        oldPropertyValue = TestsUtil.isCreateRepresentationInSeparateResource();
    }

    /**
     * Tests that refresh is done with
     * {@link DialectUIManager#refreshEditor(DialectEditor, org.eclipse.core.runtime.IProgressMonitor)} for a
     * {@link DDiagramEditor}.
     * 
     */
    public void testDiagramDialectUIManagerRefresh() {
        DRepresentation newRepresentation = createRepresentation("EcoreDiag");
        editor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, newRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("Test setup is wrong.", 2, newRepresentation.getRepresentationElements().size());

        EPackage ePackage = (EPackage) semanticModel;
        Command changeNameCommand = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                ePackage.getEClassifiers().clear();

            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(changeNameCommand);
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.refreshEditor(editor, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("Refresh has failed.", 0, editor.getRepresentation().getRepresentationElements().size());

    }

    /**
     * Tests that
     * {@link DialectUIManager#openEditor(org.eclipse.sirius.business.api.session.Session, DRepresentation, org.eclipse.core.runtime.IProgressMonitor)}
     * works correctly in the following situation:
     * <ul>
     * <li>A representation is deleted.</li>
     * <li>The deletion is undone.</li>
     * <li>The representation is opened</li>
     * </ul>
     * 
     */
    public void testDiagramOpeningAfterDeletionUndo() {
        DRepresentation newRepresentation = createRepresentation("EcoreDiag");

        assertEquals("Test setup is wrong.", 2, newRepresentation.getRepresentationElements().size());

        DRepresentationQuery dRepresentationQuery = new DRepresentationQuery(newRepresentation);
        Set<DRepresentationDescriptor> hashSet = new HashSet<DRepresentationDescriptor>();
        hashSet.add(dRepresentationQuery.getRepresentationDescriptor());
        session.getTransactionalEditingDomain().getCommandStack().execute(new DeleteRepresentationCommand(session, hashSet));
        TestsUtil.synchronizationWithUIThread();
        session.getTransactionalEditingDomain().getCommandStack().undo();

        editor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, newRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue("The editor could not open normally.", editor != null);

    }

    /**
     * Checks that after removing a representation, queries on its descriptor does not raise exceptions.
     */
    public void testRepresentationDescriptorQueryAfterDeletion() {
        TestsUtil.setCreateRepresentationInSeparateResource(true);
        DRepresentation newRepresentation = createRepresentation("EcoreDiag");

        assertEquals("Test setup is wrong.", 2, newRepresentation.getRepresentationElements().size());
        DRepresentationQuery dRepresentationQuery = new DRepresentationQuery(newRepresentation);
        DRepresentationDescriptorQuery dRepresentationDescriptorQuery = new DRepresentationDescriptorQuery(dRepresentationQuery.getRepresentationDescriptor());

        Set<DRepresentationDescriptor> hashSet = new HashSet<DRepresentationDescriptor>();
        hashSet.add(dRepresentationQuery.getRepresentationDescriptor());
        session.getTransactionalEditingDomain().getCommandStack().execute(new DeleteRepresentationCommand(session, hashSet));
        TestsUtil.synchronizationWithUIThread();
        // Check that the representation descriptor is not reachable anymore and that calling this query does not throw
        // exceptions
        assertFalse("The representation should not be reachable anymore", dRepresentationDescriptorQuery.isRepresentationReachable());
    }

    @Override
    protected void tearDown() throws Exception {
        try {
            if (editor != null) {
                DialectUIManager.INSTANCE.closeEditor(editor, false);
            }
            super.tearDown();
        } finally {
            TestsUtil.setCreateRepresentationInSeparateResource(oldPropertyValue);
        }
    }
}
