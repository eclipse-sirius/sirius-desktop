/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.dialect;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUIServices;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * This class tests the services of {@link DiagramDialectUIServices}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DiagramUIDialectServicesTests extends SiriusDiagramTestCase {

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.junit/data/unit/dialect/aqlDomainClassDef.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/dialect/aqlDomainClassDef.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint("aqlDomainClassDef");
    }

    /**
     * Tests that refresh is done with
     * {@link DialectUIManager#refreshEditor(DialectEditor, org.eclipse.core.runtime.IProgressMonitor)}
     * for a {@link DDiagramEditor}.
     * 
     */
    public void testDiagramDialectUIManagerRefresh() {
        DRepresentation newRepresentation = createRepresentation("EcoreDiag");
        final DialectEditor editor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, newRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        try {
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
        } finally

        {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
        }

    }

}
