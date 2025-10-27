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
package org.eclipse.sirius.tests.unit.diagram.filter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.DiagramElementsSelectionDialog;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.DiagramElementsSelectionDialogPatternMatcher;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Ensures that the {@link DiagramElementsSelectionDialog} works correctly, i.e. allow end-users to filter elements
 * according to their "check" status and their names (using regular expressions) and change their status.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DiagramElementSelectionDialogTest extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/filter/vp-1191/vp-1191.ecore";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/filter/vp-1191/vp-1191.aird";

    private static final String[] allElements = { "p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY" };

    private final Predicate<Object> isVisible = new Predicate<Object>() {
        @Override
        public boolean apply(Object input) {
            boolean result = false;
            if (input instanceof DDiagramElement) {
                result = !(new DDiagramElementQuery((DDiagramElement) input).isHidden());
            } else if (input instanceof AbstractDDiagramElementLabelItemProvider) {
                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) input).getDiagramElementTarget();
                if (optionTarget.some()) {
                    result = !(new DDiagramElementQuery(optionTarget.get()).isLabelHidden());
                }
            }
            return result;
        }
    };

    private final Function<Object, Void> hideElement = new Function<Object, Void>() {
        @Override
        public Void apply(Object from) {
            if (from instanceof DDiagramElement) {
                HideFilterHelper.INSTANCE.hide((DDiagramElement) from);
            } else if (from instanceof AbstractDDiagramElementLabelItemProvider) {
                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) from).getDiagramElementTarget();
                if (optionTarget.some()) {
                    HideFilterHelper.INSTANCE.hideLabel(optionTarget.get());
                }
            }
            return null;
        }
    };

    private final Function<Object, Void> revealElement = new Function<Object, Void>() {
        @Override
        public Void apply(Object from) {
            if (from instanceof DDiagramElement) {
                HideFilterHelper.INSTANCE.reveal((DDiagramElement) from);
            } else if (from instanceof AbstractDDiagramElementLabelItemProvider) {
                HideFilterHelper.INSTANCE.revealLabel((DNode) ((AbstractDDiagramElementLabelItemProvider) from).getTarget());
            }
            return null;
        }
    };

    /**
     * Used to access protected fields of {@link DiagramElementsSelectionDialog} and test its behavior.
     */
    private DiagramElementsSelectionDialogTester dialogTester;

    private DDiagram testDiagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        testDiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        DialectUIManager.INSTANCE.openEditor(session, testDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        dialogTester = new DiagramElementsSelectionDialogTester();
        dialogTester.open(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), testDiagram, true);
    }

    /**
     * Ensures that the initial RegExp (empty String) allows to select all elements.
     */
    public void testInitialRegExp() {
        // We ensure that all elements are visible and checked
        dialogTester.checkVisibleElementByNames(getElementNamesAndLabels(allElements));
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));
    }

    /**
     * Ensures the viewer is correctly updated and checked elements don't change if user type complex regular
     * expressions.
     */
    public void testComplexRegExp() {

        dialogTester.setRegExp("E*y");
        dialogTester.checkVisibleElementByNames("p2", "Eddy", "Eddy label");
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));

        dialogTester.setRegExp("M?m");
        dialogTester.checkVisibleElementByNames("p3", "4", "Mum", "Mom", "Mummy", "mummY", "Mum label", "Mom label", "Mummy label", "mummY label");
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));
    }

    /**
     * Ensures the viewer is correctly updated and checked elements don't change if user type regular expressions with
     * spaces at the end (exact match).
     */
    public void testRegExpWithSpaces() {
        dialogTester.setRegExp("E*d ");
        dialogTester.checkVisibleElementByNames("p1", "Ed");
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));

        dialogTester.setRegExp("E ");
        dialogTester.checkVisibleElementByNames();
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));

        dialogTester.setRegExp("M?m ");
        dialogTester.checkVisibleElementByNames("p3", "Mum", "Mom");
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));
    }

    /**
     * Ensures that the viewer does not throw Exception for empty list.
     */
    public void testEmptyList() {
        platformProblemsListener.setErrorCatchActive(true);
        try {
            dialogTester.close();
            RecordingCommand cmd = new RecordingCommand(session.getTransactionalEditingDomain(), "Clean diagram") {
                @Override
                protected void doExecute() {
                    testDiagram.getOwnedDiagramElements().clear();
                }
            };
            session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
            dialogTester.open(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), testDiagram, true);
        } finally {
            platformProblemsListener.setErrorCatchActive(false);
        }
    }

    /**
     * Ensures that the viewer is correctly updated and checked elements don't change if user change the filtering mode.
     * 
     */
    public void testFilteringMode() {

        // Case 1 : all items are checked
        dialogTester.setFilteringMode(0);
        dialogTester.checkVisibleElementByNames(getElementNamesAndLabels(allElements));
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));

        dialogTester.setFilteringMode(1);
        dialogTester.checkVisibleElementByNames(getElementNamesAndLabels(allElements));
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));

        dialogTester.setFilteringMode(2);
        dialogTester.checkVisibleElementByNames();
        dialogTester.checkCheckedElementByNames(getElementNamesAndLabels(allElements));

    }

    private String[] getElementNamesAndLabels(String... elementNames) {
        List<String> namesAndLabels = new ArrayList<>();
        for (int i = 0; i < elementNames.length; i++) {
            namesAndLabels.add(elementNames[i]);
            namesAndLabels.add(elementNames[i] + " label");
        }
        return namesAndLabels.toArray(new String[0]);
    }

    /**
     * Ensures that filtering mode filters correctly checked/unchecked elements on a Root.
     */
    public void testFilteringModeWithUncheckedElementOnRoot() {
        // unchecking a top element ('p1')
        dialogTester.setChecked(false, "p1");
        dialogTester.setChecked(false, "p1 label");

        dialogTester.setFilteringMode(1);
        dialogTester.checkVisibleElementByNames(
                getElementNamesAndLabels("p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
        dialogTester.checkCheckedElementByNames(
                getElementNamesAndLabels("p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
    }

    /**
     * Ensures that filter mode filters correctly checked/unchecked elements on a Leaf.
     */
    public void testFilteringModeWithUncheckedElementOnLeaf() {
        // unchecking a leaf element ('mum' and its label)
        dialogTester.setChecked(false, "Mum");
        dialogTester.setChecked(false, "Mum label");

        dialogTester.setFilteringMode(1);
        dialogTester.checkVisibleElementByNames(
                getElementNamesAndLabels("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
        dialogTester.checkCheckedElementByNames(
                getElementNamesAndLabels("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
    }

    /**
     * Ensures that filtering mode and regular expression filters can work together correctly.
     */
    public void testFilteringModeWithUncheckedElementAndRegExpOnRoot() {
        dialogTester.setChecked(false, "p1");
        dialogTester.setChecked(false, "p1 label");

        dialogTester.setFilteringMode(1);
        dialogTester.setRegExp("p");
        dialogTester.checkVisibleElementByNames("p2", "p2 label", "p3", "p3 label");
        dialogTester.checkCheckedElementByNames(
                getElementNamesAndLabels("p2", "p3", "4", "Ed", "Eddy", "Merks", "Mum", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
    }

    /**
     * Ensures that filtering mode and regular expression filters can work together correctly.
     */
    public void testFilteringModeWithUncheckedElementAndRegExpOnLeaf() {
        dialogTester.setChecked(false, "Mum");
        dialogTester.setChecked(false, "Mum label");

        dialogTester.setFilteringMode(1);
        dialogTester.setRegExp("M?m");
        dialogTester.checkVisibleElementByNames("p3", "4", "Mom", "Mom label", "Mummy", "Mummy label", "mummY", "mummY label");
        dialogTester.checkCheckedElementByNames(
                getElementNamesAndLabels("p1", "p2", "p3", "4", "Ed", "Eddy", "Merks", "Mom", "M with 'simple' quotes", "M with spaces s", "M with \"quotes\" s", "Mummy", "mummY"));
    }

    /**
     * A class used to access protected fields of {@link DiagramElementsSelectionDialog}. Tests behavior of this Dialog.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     */
    protected class DiagramElementsSelectionDialogTester extends DiagramElementsSelectionDialog {

        /**
         * Used of reflection to access the getChildren() method on a treeViewer.
         */
        private Method treeViewerGetChildrenMethod;

        /**
         * The treeViewer associated to this dialog.
         */
        private CheckboxTreeViewer treeViewer;

        /**
         * Creates a new DiagramElementsSelectionDialogTester.
         * 
         */
        public DiagramElementsSelectionDialogTester() {
            // We initialize this tester with the predicates, actions and names
            // of the Hide/Reveal wizard
            super("Diagram elements visibility", "Visible diagram elements are checked.");
            setSelectionPredicate(isVisible);
            setSelectedAction(revealElement);
            setDeselectedAction(hideElement);

            // Getting the TreeViewer.getChildren() method
            try {
                treeViewerGetChildrenMethod = TreeViewer.class.getDeclaredMethod("getChildren", Widget.class);
                treeViewerGetChildrenMethod.setAccessible(true);
            } catch (SecurityException e) {
                assertFalse(e.getMessage(), true);
            } catch (NoSuchMethodException e) {
                assertFalse(e.getMessage(), true);
            }
        }

        /**
         * Sets a new Regular expression exactly as if user as typed it.
         * 
         * @param newRegExp
         *            the new regular expression
         */
        public void setRegExp(String newRegExp) {
            dialog.setPatternMatcher(new DiagramElementsSelectionDialogPatternMatcher(newRegExp));
            dialog.updateFilteringMode(mode);
            getDialogTreeViewer().expandAll();
        }

        /**
         * Sets the filtering mode exactly as if user as changed it.
         * 
         * @param newMode
         *            the new filtering mode
         */
        public void setFilteringMode(int newMode) {
            FilteringMode newModeAsEnum = null;
            switch (newMode) {
            case 1:
                newModeAsEnum = FilteringMode.SHOW_ONLY_CHECKED_ELEMENTS;
                break;
            case 2:
                newModeAsEnum = FilteringMode.SHOW_ONLY_UNCHECKED_ELEMENTS;
                break;
            default:
                newModeAsEnum = FilteringMode.SHOW_ALL;
                break;
            }
            mode = newModeAsEnum;
            dialog.updateFilteringMode(mode);
            getDialogTreeViewer().expandAll();
        }

        /**
         * Checks that all elements currently visible in the TreeViewer matches the given collection of element names.
         * 
         * @param elementNames
         *            the list of expected elements visible in the TreeViewer
         */
        public void checkVisibleElementByNames(String... elementNames) {
            Set<String> expectedVisibleNames = new LinkedHashSet<>();
            Set<String> actualNames = new LinkedHashSet<>();
            Object[] visibleElements = getVisibleElements();

            for (int i = 0; i < elementNames.length; i++) {
                expectedVisibleNames.add(elementNames[i]);
            }
            for (int i = 0; i < visibleElements.length; i++) {
                if (visibleElements[i] instanceof DDiagramElement) {
                    actualNames.add(((DDiagramElement) visibleElements[i]).getName());
                } else if (visibleElements[i] instanceof AbstractDDiagramElementLabelItemProvider) {
                    actualNames.add(getLabelName((AbstractDDiagramElementLabelItemProvider) visibleElements[i]));
                }
            }

            SetView<String> elementsThatShouldBeVisibleButArent = Sets.difference(expectedVisibleNames, actualNames);
            SetView<String> elementsThatShouldNotBeVisibleButAre = Sets.difference(actualNames, expectedVisibleNames);
            assertTrue("Elements " + elementsThatShouldBeVisibleButArent + " should be visible", elementsThatShouldBeVisibleButArent.isEmpty());
            assertTrue("Elements " + elementsThatShouldNotBeVisibleButAre + " should not be visible", elementsThatShouldNotBeVisibleButAre.isEmpty());
        }

        /**
         * Checks that all elements currently checked matches the given collection of element names.
         * 
         * @param elementNames
         *            the list of expected checked elements
         */
        public void checkCheckedElementByNames(String... elementNames) {
            Set<String> expectedCheckedNames = new LinkedHashSet<>();
            Set<String> actualNames = new LinkedHashSet<>();
            Set<Object> checkedElements = getElementsSelectedAfter();

            for (int i = 0; i < elementNames.length; i++) {
                expectedCheckedNames.add(elementNames[i]);
            }
            for (Object checkDiagramElement : checkedElements) {
                if (checkDiagramElement instanceof DDiagramElement) {
                    actualNames.add(((DDiagramElement) checkDiagramElement).getName());
                } else if (checkDiagramElement instanceof AbstractDDiagramElementLabelItemProvider) {
                    actualNames.add(getLabelName((AbstractDDiagramElementLabelItemProvider) checkDiagramElement));
                }
            }

            SetView<String> elementsThatShouldBeCheckedButArent = Sets.difference(expectedCheckedNames, actualNames);
            SetView<String> elementsThatShouldNotBeCheckedButAre = Sets.difference(actualNames, expectedCheckedNames);
            assertTrue("Elements " + elementsThatShouldBeCheckedButArent + " should be checked", elementsThatShouldBeCheckedButArent.isEmpty());
            assertTrue("Elements " + elementsThatShouldNotBeCheckedButAre + " should not be checked", elementsThatShouldNotBeCheckedButAre.isEmpty());
        }

        private String getLabelName(AbstractDDiagramElementLabelItemProvider checkDiagramElement) {
            Option<DDiagramElement> optionTarget = checkDiagramElement.getDiagramElementTarget();
            if (optionTarget.some()) {
                return optionTarget.get().getName() + " label";
            }
            fail("It should not be possible to have a label wrapper with no DDiagramElement.");
            return "Unknown";
        }

        /**
         * Closes the dialog.
         */
        public void close() {
            dialog.close();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.ui.tools.internal.dialogs.DiagramElementsSelectionDialog#askUserForNewSelection(org.eclipse.swt.widgets.Shell,
         *      boolean)
         */
        @Override
        protected Option<Set<Object>> askUserForNewSelection(Shell parent, Set<Object> initialSelection) {
            // We just open the dialog, without blocking execution flow
            setupDialog(parent, initialSelection);
            dialog.setBlockOnOpen(false);
            dialog.open();
            return Options.newNone();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.ui.tools.internal.dialogs.DiagramElementsSelectionDialog#open(Shell,
         *      DDiagram, boolean)
         */
        @Override
        // Overriding this method allows keep the dialog alive
        public boolean open(Shell parent, DDiagram ddiagram, boolean includeNodeLabel) {
            boolean result = false;
            diagram = ddiagram;

            initContentProvider(includeNodeLabel);

            Set<Object> allSelectedElements = Collections.unmodifiableSet(getAllSelectedElements());
            Option<Set<Object>> response = askUserForNewSelection(parent, allSelectedElements);
            if (response.some()) {
                Set<Object> selectedAfter = response.get();
                applyRequestedChanges(allSelectedElements, selectedAfter);
                assert selectedAfter.equals(allSelectedElements);
                result = true;
            }
            // Here the dialog musn't be set to null
            return result;
        }

        /**
         * Returns the dialog treeViewer (using reflection).
         * 
         * @return the dialog treeViewer
         */
        public CheckboxTreeViewer getDialogTreeViewer() {

            try {
                if (treeViewer == null) {
                    Method method = CheckedTreeSelectionDialog.class.getDeclaredMethod("getTreeViewer");
                    method.setAccessible(true);
                    treeViewer = (CheckboxTreeViewer) method.invoke(dialog);
                }
                return treeViewer;
            } catch (IllegalArgumentException e) {
                assertFalse(e.getMessage(), true);
            } catch (IllegalAccessException e) {
                assertFalse(e.getMessage(), true);
            } catch (SecurityException e) {
                assertFalse(e.getMessage(), true);
            } catch (NoSuchMethodException e) {
                assertFalse(e.getMessage(), true);
            } catch (InvocationTargetException e) {
                assertFalse(e.getMessage(), true);
            }
            // Unreachable code
            return null;
        }

        /**
         * Returns all the items contained in the Viewer.
         * 
         * @return all the items contained in the Viewer
         */
        public Object[] getVisibleElements() {
            Control tree = getDialogTreeViewer().getControl();
            Collection<Object> result = new HashSet<>();
            getVisibleChildren(result, tree);
            return result.toArray();
        }

        private void getVisibleChildren(Collection<Object> result, Widget parent) {
            Item[] items;
            try {
                items = (Item[]) treeViewerGetChildrenMethod.invoke(getDialogTreeViewer(), parent);

                for (int i = 0; i < items.length; i++) {
                    Item item = items[i];
                    if (item instanceof TreeItem && ((TreeItem) item).getChecked()) {
                        Object data = item.getData();
                        if (data != null) {
                            result.add(data);
                        }
                    }
                    getVisibleChildren(result, item);
                }
            } catch (IllegalArgumentException e) {
                fail(e.getMessage());
            } catch (IllegalAccessException e) {
                fail(e.getMessage());
            } catch (InvocationTargetException e) {
                fail(e.getMessage());
            }
        }

        /**
         * Checks/unchecks the elements with the given names.
         * 
         * @param newCheckedValue
         *            the new Checked value
         * @param elementsToCheckNames
         *            the names of the elements to check
         */
        public void setChecked(boolean newCheckedValue, String... elementsToCheckNames) {

            for (int i = 0; i < elementsToCheckNames.length; i++) {
                Object[] elementsSelectedAfter = getVisibleElements();

                for (int j = 0; j < elementsSelectedAfter.length; j++) {
                    String name = "";
                    if (elementsSelectedAfter[j] instanceof DDiagramElement) {
                        name = ((DDiagramElement) elementsSelectedAfter[j]).getName();
                    } else if (elementsSelectedAfter[j] instanceof AbstractDDiagramElementLabelItemProvider) {
                        name = getLabelName((AbstractDDiagramElementLabelItemProvider) elementsSelectedAfter[j]);
                    }
                    if (name.equals(elementsToCheckNames[i])) {
                        getDialogTreeViewer().setChecked(elementsSelectedAfter[j], newCheckedValue);
                        if (newCheckedValue) {
                            dialog.getCheckedElements().add(elementsSelectedAfter[j]);
                        } else {
                            dialog.getCheckedElements().remove(elementsSelectedAfter[j]);
                        }
                        getDialogTreeViewer().refresh();
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        dialogTester.close();
        super.tearDown();
    }

}
