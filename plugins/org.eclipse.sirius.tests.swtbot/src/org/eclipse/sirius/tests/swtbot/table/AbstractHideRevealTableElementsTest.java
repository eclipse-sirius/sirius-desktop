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
package org.eclipse.sirius.tests.swtbot.table;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.test.AbstractMMEcoreBasedScenarioTestCase;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Common class for hide/reveal table elements tests.
 * 
 * @author dlecan
 * @param <T>
 *            Type
 */
public abstract class AbstractHideRevealTableElementsTest<T extends AbstractSWTBot<?>> extends AbstractMMEcoreBasedScenarioTestCase {

    /**
     * Wrapper around dialog table (can be tree or table).
     * 
     * @author dlecan
     * @param <T>
     *            Type
     */
    protected static interface DialogTable<T extends AbstractSWTBot<?>> {

        /**
         * Count elements
         * 
         * @return Number of elements
         */
        int elementCount();

        /**
         * Get String value of index position
         * 
         * @param index
         *            Position
         * @return String value
         */
        String getTableItem(int index);

        /**
         * Get current table.
         * 
         * @return Current table
         */
        T getDialogTable();
    }

    private final String[] viewpointsSelection = new String[] { "Design multi-columns" };

    private static final String DATA_UNIT_DIR = "data/unit/multiColumns/";

    private static final String VSM_FILE = "ecore.odesign";

    private UILocalSession localSession;

    /**
     * SWTBot.
     */
    protected SWTBot dialogBot;

    /**
     * SWTBot.
     */
    protected SWTBot editorBot;

    /**
     * SWTBot tree table.
     */
    protected SWTBotTree treeTable;

    /**
     * SWTBot dialog.
     */
    protected SWTBotShell hiddenElementsDialog;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();

        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + VSM_FILE, getProjectName() + "/" + VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource ecoreEcoreResource = new UIResource(designerProject, MODELS_DIR, "Ecore.ecore");

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode("ecore");
        final UITableRepresentation representation = localSession.newTableRepresentation("new Classes", "Classes").on(ecoreTreeItem).withDefaultName().ok();
        treeTable = representation.getTable();
        editorBot = representation.getEditorBot();

        assertThat("For tests, there must be at least one element to hide in this test case", getVisibleElementCount(), greaterThan(0));

        assertThat(getVisibleElementCount(), equalTo(getElementCount()));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckAllElementsAreInDialog() throws Exception {
        final int elementCount = getVisibleElementCount();

        final List<String> elements = getElementsHeader();

        final DialogTable<T> hideElementTable = openDialog();

        assertThat(hideElementTable.elementCount(), is(elementCount));

        // Check exactly if each element of the dialog is in the tree table and
        // in the same order
        for (int i = 0; i < elementCount; i++) {
            final String columnName = elements.get(i);

            final String itemValue = hideElementTable.getTableItem(i);

            assertThat(itemValue, containsString(columnName));
        }

        dialogBot.button("Cancel").click();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideElementAndCheckItIsInDialog() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        final int previousCount = getVisibleElementCount();

        final int lastElementIndex = previousCount;

        // Relative to the first element which can be hidden
        final int relativeLastElementIndex = lastElementIndex - 1;

        final String hiddenElementName = getElementsHeader().get(relativeLastElementIndex);

        final DialogTable<T> dialogTable = openDialog();

        final String label = uncheckElement(dialogTable, relativeLastElementIndex);
        assertThat("Element hides in dialog is not the same as this one selected in tree table", label, equalTo(hiddenElementName));

        dialogBot.button("OK").click();

        assertThat("One element should have disappeared", getVisibleElementCount(), is(previousCount - 1));

        for (final String columnName : getElementsHeader()) {
            assertThat(columnName, not(containsString(hiddenElementName)));
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideAllElements() throws Exception {
        final List<String> elementsHeader = getElementsHeader();

        final DialogTable<T> dialogTable = openDialog();

        final int count = getElementCount();
        final int[] indexes = new int[count];
        for (int i = 0; i < count; i++) {
            indexes[i] = i;
        }

        final List<String> labels = uncheckAllElements(dialogTable, indexes);

        final Iterator<String> iterator = elementsHeader.iterator();

        for (final String label : labels) {
            assertThat(iterator.hasNext(), is(true));
            assertThat("Element hides in dialog is not the same as this one selected in tree table", label, equalTo(iterator.next()));
        }

        dialogBot.button("OK").click();

        assertThat("One element should have disappeared", getVisibleElementCount(), is(0));
    }

    /**
     * Get visible element count.
     * 
     * @return Number of visible elements
     */
    protected abstract int getVisibleElementCount();

    /**
     * Get element count
     * 
     * @return Number of element count (visible or not)
     */
    protected abstract int getElementCount();

    /**
     * Get element names (columns or rows header)
     * 
     * @return Elements names
     */
    protected abstract List<String> getElementsHeader();

    private DialogTable<T> openDialog() {
        treeTable.setFocus();
        SWTBotUtils.clickContextMenu(treeTable, getContextualMenuLabel());

        hiddenElementsDialog = editorBot.shell(getDialogTitle());
        hiddenElementsDialog.activate();
        hiddenElementsDialog.setFocus();

        editorBot.waitUntil(Conditions.shellIsActive(getDialogTitle()));

        dialogBot = new SWTBot(hiddenElementsDialog.widget);

        return getInnerTable(dialogBot);
    }

    /**
     * Gte dialog title.
     * 
     * @return Dialog title.
     */
    protected abstract String getDialogTitle();

    /**
     * Get contextual menu label.
     * 
     * @return Contextual menu label.
     */
    protected abstract String getContextualMenuLabel();

    /**
     * Uncheck element
     * 
     * @param dialogTable
     *            Dialog table.
     * 
     * @param relativeElementIndex
     *            Index of element to uncheck
     * @return Label of the unchecked element
     */
    protected abstract String uncheckElement(DialogTable<T> dialogTable, int relativeElementIndex);

    /**
     * Uncheck all elements
     * 
     * @param dialogTable
     *            Dialog table.
     * @param indexes
     *            Indexes
     * 
     * @return Label of the unchecked elements
     */
    protected abstract List<String> uncheckAllElements(DialogTable<T> dialogTable, int[] indexes);

    /**
     * Get Inner table. Can be called only once per test.
     * 
     * @param parent
     *            Parent shell
     * 
     * @return Inner table.
     */
    protected abstract DialogTable<T> getInnerTable(SWTBot parent);
}
