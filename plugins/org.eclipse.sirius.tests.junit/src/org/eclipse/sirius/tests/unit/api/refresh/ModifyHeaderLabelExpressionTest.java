/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;

/**
 * Test modification of header Label expression in VSM when table representation
 * is opened. Check there are no exceptions in Error log.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class ModifyHeaderLabelExpressionTest extends SiriusTestCase {

    protected IEclipsePreferences preferences;

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/labelHeaderExpression/My.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/labelHeaderExpression/My.odesign";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/labelHeaderExpression/My.aird";

    private static final String REPRESENTATION_DESC_NAME = "TableHeader";

    private static final String VIEWPOINT_NAME = "ModifyHeader";

    private DTable table;

    private IEditorPart openedTableEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Copy in temporary test project
        EclipseTestsSupportHelper.INSTANCE.copyFile(MODELER_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + "My.odesign");
        EclipseTestsSupportHelper.INSTANCE.copyFile(SEMANTIC_MODEL_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + "My.ecore");
        EclipseTestsSupportHelper.INSTANCE.copyFile(REPRESENTATIONS_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + "My.aird");
        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + "My.ecore", "/" + TEMPORARY_PROJECT_NAME + "/" + "My.odesign", "/" + TEMPORARY_PROJECT_NAME + "/" + "My.aird");
        // Activate auto refresh
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        activateViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Open table representation. Modify the label header expression in the vsm
     * and save and check there are no error in error log.
     * 
     * @throws Exception
     */
    public void testModificationOfHeaderLabelExpression() throws Exception {
        // Opened representation (table) editor
        table = (DTable) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        openedTableEditor = DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // VP-4466: clear the NPE error before the fix of this issue.
        if (errorsCount() == 1 && errors.values().iterator().next().size() == 1) {
            if (errors.values().iterator().next().iterator().next().getException() instanceof NullPointerException) {
                errors.clear();
            }
        }
        // Verify that there is one representation corresponding to table
        // representation
        assertEquals("The data is incorrect (bad number of representations).", 1, getRepresentations(REPRESENTATION_DESC_NAME).size());
        try {
            final Map<Object, Object> options = new HashMap<>();
            // Modify the VSM in another resource set (as if it was modified in
            // VSM editor)
            URI viewpointResourceURI = viewpoints.iterator().next().eResource().getURI();
            TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
            ResourceSet set = domain.getResourceSet();
            Resource vsm = set.getResource(viewpointResourceURI, true);
            final Group group = (Group) vsm.getContents().get(0);
            final FeatureColumnMapping featureColumnMapping = ((EditionTableDescription) group.getOwnedViewpoints().get(0).getOwnedRepresentations().get(0)).getOwnedColumnMappings().get(0);
            // Check header label expression value
            assertEquals("HeaderLabelExpression should be equals to 'Name'", "Name", featureColumnMapping.getHeaderLabelExpression());
            // Crate a new value for header label expression
            final String newHeaderLabelExpression = featureColumnMapping.getHeaderLabelExpression() + "Modify";

            // Modify header label expression value
            domain.getCommandStack().execute(new RecordingCommand(domain) {
                /** {@inheritDoc} */
                @Override
                protected void doExecute() {
                    featureColumnMapping.setHeaderLabelExpression(newHeaderLabelExpression);
                }
            });
            // Save modification
            vsm.save(options);
            // Wait launching of refresh job
            // (AbstractDTreeEditor.modelerDescriptionFilesLoaded)
            TestsUtil.synchronizationWithUIThread();
            // Wait job finish
            Thread.sleep(2000);
            // Wait async execution launch in handleDColumnNotification
            TestsUtil.synchronizationWithUIThread();
            // Wait getViewer().refresh() of
            // AbstractDTreeEditor.modelerDescriptionFilesLoaded
            TestsUtil.synchronizationWithUIThread();
            // Verify Header label expression value
            assertEquals("HeaderLableExpression should be equals to 'NameModify'", "NameModify", featureColumnMapping.getHeaderLabelExpression());
            assertFalse("At least one error occurs before the vsm modification: " + getErrorLoggersMessage(), doesAnErrorOccurs());
            AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedTableEditor;
            Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

            // Check attempted
            String currentHtml = TableUIHelper.toContentHTMl(tree);
            String expectedHtml = getExpectedDefaultHtml();
            assertEquals("The table doesn't correspond to attempt", expectedHtml, currentHtml);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void tearDown() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        super.tearDown();
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    private String getExpectedDefaultHtml() {
        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "NameModify", "Super types" });
        TableUIHelper.addLineToTable(expected, new String[] { "E221", "E221", "[]" });
        TableUIHelper.addLineToTable(expected, new String[] { "E2", "E2", "[]" });
        TableUIHelper.addLineToTable(expected, new String[] { "E1 -> E2, E221", "E1", "[E2, E221]" });
        return TableUIHelper.toHTML(expected);
    }

}
