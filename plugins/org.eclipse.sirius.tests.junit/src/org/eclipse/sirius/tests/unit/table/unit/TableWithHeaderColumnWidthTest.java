/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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
package org.eclipse.sirius.tests.unit.table.unit;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorPart;

/**
 * Test width initialization of header column table.
 * 
 * @author jmallet
 */
public class TableWithHeaderColumnWidthTest extends SiriusTestCase {

    private static final String CROSS_TABLE_NAME = "testCrossTable"; //$NON-NLS-1$

    private static final String EDITION_TABLE_NAME = "testEditionTable"; //$NON-NLS-1$

    private static final String VIEWPOINT_NAME = "Bugzilla-507887"; //$NON-NLS-1$

    public static final String FOLDER_PATH = "/data/table/unit/bugzilla-507887/"; //$NON-NLS-1$

    private static final String SEMANTIC_MODEL_FILENAME = "sample.ecore"; //$NON-NLS-1$

    private static final String VSM_NAME = "tables.odesign"; //$NON-NLS-1$

    private static final String SESSION_MODEL_FILENAME = "sample.aird"; //$NON-NLS-1$

    private DRepresentation drep;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, FOLDER_PATH, SEMANTIC_MODEL_FILENAME, SESSION_MODEL_FILENAME, VSM_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + VSM_NAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);

        activateViewpoint(VIEWPOINT_NAME);
    }

    /**
     * Test that positive Header column width set in VSM is taken into account
     * on edition table.
     */
    public void testWidthHeaderColumnTo50_onEditionTable() {
        int expectedSize = 50;

        // Modify VSM, set Header column size to 50
        setNewHeaderColumnWidth(expectedSize, EDITION_TABLE_NAME);

        // create Edition Table
        createTable(EDITION_TABLE_NAME);

        assertEquals("The header column width should be 50 px as specified in VSM.", expectedSize, getHeaderColumn().getWidth());
    }

    /**
     * Test that Header column width to zero in VSM shows columns auto-size on
     * edition table.
     */
    public void testWidthHeaderColumnTo0_onEditionTable() {
        // Modify VSM, set Header column size to 0
        setNewHeaderColumnWidth(0, EDITION_TABLE_NAME);

        // create Edition Table
        createTable(EDITION_TABLE_NAME);

        assertTrue("Header column size should be greater than 0 to be adapted to column content.", getHeaderColumn().getWidth() > 0); //$NON-NLS-1$
    }

    /**
     * Test that Header column is hidden if its size is set to -1 in the VSM on
     * edition table.
     */
    public void testHideHeaderColumn_onEditionTable() {
        // Modify VSM, set Header column size to -1
        setNewHeaderColumnWidth(-1, EDITION_TABLE_NAME);

        // create Edition Table
        createTable(EDITION_TABLE_NAME);

        assertEquals("Header column should be hidden.", 0, getHeaderColumn().getWidth());
    }

    /**
     * Test that Header column positive width set in VSM is taken into account
     * on Cross Table.
     */
    public void testWidthHeaderColumnTo50_onCrossTable() {
        int expectedSize = 50;

        // Modify VSM, set Header column size to 50
        setNewHeaderColumnWidth(expectedSize, CROSS_TABLE_NAME);

        // create Cross Table
        createTable(CROSS_TABLE_NAME);

        assertEquals("The header column width should be 50 px as specified in VSM.", expectedSize, getHeaderColumn().getWidth());
    }

    /**
     * Test that Header column width to zero in VSM shows columns auto-size on
     * Cross Table.
     */
    public void testWidthHeaderColumnTo0_onCrossTable() {
        // Modify VSM, set Header column size to 0
        setNewHeaderColumnWidth(0, CROSS_TABLE_NAME);

        // create Cross Table
        createTable(CROSS_TABLE_NAME);

        assertTrue("Header column size should be greater than 0 to be adapted to column content.", getHeaderColumn().getWidth() > 0); //$NON-NLS-1$
    }

    /**
     * Test that Header column width to -1 in VSM shows columns auto-size on
     * Cross Table.
     */
    public void testWidthHeaderColumnToMinus1_onCrossTable() {
        // Modify VSM, set Header column size to -1
        setNewHeaderColumnWidth(-1, CROSS_TABLE_NAME);

        // create Cross Table
        createTable(CROSS_TABLE_NAME);

        assertTrue("Header column size should be greater than 0 to be adapted to column content.", getHeaderColumn().getWidth() > 0); //$NON-NLS-1$
    }

    /**
     * Set new Header column width in VSM.
     * 
     * @param size
     *            width of the Header column to set in VSM
     * @param representationName
     *            name of the description to modify
     */
    private void setNewHeaderColumnWidth(Integer size, String representationName) {
        try {
            // Get Table description
            ResourceSet set = new ResourceSetImpl();
            Resource vsm = set.getResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + VSM_NAME, true), true);
            Group group = (Group) vsm.getContents().get(0);
            Optional<RepresentationDescription> description = group.getOwnedViewpoints().stream().flatMap(vp -> vp.getOwnedRepresentations().stream())
                    .filter(d -> d.getName().equals(representationName)).findFirst();

            if (description.isPresent()) {
                final TableDescription desc = (TableDescription) description.get();
                // Modify header column width value
                TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
                domain.getCommandStack().execute(new RecordingCommand(domain) {
                    /** {@inheritDoc} */
                    @Override
                    protected void doExecute() {
                        desc.setInitialHeaderColumnWidth(size);
                    }
                });

                // Save modification
                vsm.save(Collections.emptyMap());
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Create a table representation on the root package of the model.
     * 
     * @param representationName
     *            name of the representation description to use
     */
    private void createTable(String representationName) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                drep = DialectManager.INSTANCE.createRepresentation("new " + representationName, semanticModel, getRepDescription(representationName), session, new NullProgressMonitor()); //$NON-NLS-1$
                DialectManager.INSTANCE.refresh(drep, new NullProgressMonitor());
            }
        });
    }

    /**
     * Get representation description that the user might use to create a new
     * {@link DRepresentation}.
     * 
     * @param name
     *            name of the representation description
     * 
     * @return representation description
     */
    private RepresentationDescription getRepDescription(String name) {
        final Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), semanticModel);
        Optional<RepresentationDescription> description = descriptions.stream().filter(d -> d.getName().equals(name)).findFirst();
        if (description.isPresent()) {
            return description.get();
        } else {
            return null;
        }
    }

    /**
     * Get header column of the new table representation.
     * 
     * @return header column of the new table representation.
     */
    private TreeColumn getHeaderColumn() {
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, drep, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();
        return tree.getColumn(0);
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
