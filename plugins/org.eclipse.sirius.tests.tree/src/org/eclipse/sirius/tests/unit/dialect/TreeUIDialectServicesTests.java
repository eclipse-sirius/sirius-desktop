/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.dialect;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.junit.Assert;

/**
 * Test Multiple refresh required when several editors are open and AutoRefresh is on. Test also undo and redo with
 * several editors are open. Test VP-815 and DOREMI-2443
 * 
 * @author jdupont
 */
/**
 * Test the services provided by TreeUIDialectServices
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class TreeUIDialectServicesTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/refreshWhenSeveralEdiorsOpen/My.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/refreshWhenSeveralEdiorsOpen/EcoreExtension.odesign";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/refreshWhenSeveralEdiorsOpen/My.aird";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String VIEWPOINT_NAME_EXTENSION = "EcoreForResfreshTest";

    private static final String REPRESENTATION_DESC_NAME_TREE = "Tree";

    private DTree tree;

    private DialectEditor editorRootTree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        initViewpoint(VIEWPOINT_NAME);
        initViewpoint(VIEWPOINT_NAME_EXTENSION);
        tree = (DTree) getRepresentations(REPRESENTATION_DESC_NAME_TREE).toArray()[0];
    }

    /**
     * Tests that refresh is done with
     * {@link DialectUIManager#refreshEditor(DialectEditor, org.eclipse.core.runtime.IProgressMonitor)} for a
     * {@link DTreeEditor}.
     * 
     */
    public void testDiagramDialectUIManagerRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        EPackage ePackage = (EPackage) semanticModel;
        editorRootTree = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, tree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        Assert.assertEquals("Test setup is wrong.", 12, tree.getRepresentationElements().size());

        Command changeNameCommand = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                ePackage.getESubpackages().clear();
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(changeNameCommand);

        DialectUIManager.INSTANCE.refreshEditor(editorRootTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        Assert.assertEquals("Refresh was not applied.", 0, tree.getRepresentationElements().size());

    }

    @Override
    protected void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editorRootTree, false);
        tree = null;
        editorRootTree = null;
        super.tearDown();
    }
}
