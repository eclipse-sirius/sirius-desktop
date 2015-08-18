/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.provider.DNodeContainerItemProvider;
import org.eclipse.sirius.diagram.provider.DNodeListItemProvider;
import org.eclipse.sirius.diagram.provider.DiagramItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DDiagramElementContainerLabelItemProvider;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.providers.FilteredTreeContentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineLabelProvider;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * A dialog box which allows the user to edit a boolean property/flag of a
 * sub-set of the elements in a diagram. The dialog presents all the elements in
 * the diagram and indicates their state (selected or not). The user can edit
 * this state individually for each element. When the operation is validated by
 * the user (by closing the dialog with the OK button) the specified editing
 * operations are applied to the elements whose state has been changed (i.e.
 * they have been selected or deselected).
 * <p>
 * What the notion of "selected" means can be customized through 3
 * programmer-specified functions:
 * <ul>
 * <li>a predicate to detect whether an element is selected or not (e.g.
 * "the element is hidden")</li>
 * <li>an action to apply to an element to make it selected (e.g.
 * "set the element as hidden")</li>
 * <li>an action to apply to an element to make it deselected (e.g.
 * "set the element as not-hidden/reveal the element")</li>
 * </ul>
 * 
 * @author pcdavid
 */
public class DiagramElementsSelectionDialog {

    private static final Function<Object, Void> DO_NOTHING = new Function<Object, Void>() {
        public Void apply(Object from) {
            return null;
        };
    };

    /**
     * The internal dialog used by this dialog.
     */
    protected CustomTreeSelectionDialog dialog;

    /**
     * The diagram associated to this dialog.
     */
    protected DDiagram diagram;

    /**
     * The filtering mode currently associated to the tree viewer. Can be :
     * <ul>
     * <li>All elements</li>
     * <li>Only checked elements</li>
     * <li>Only unchecked elements</li>
     * </ul>
     */
    protected FilteringMode mode = FilteringMode.SHOW_ALL;

    private final String title;

    private final String message;

    private Predicate<Object> isSelected = Predicates.alwaysTrue();

    // Grayed elements will not be selectable.
    private Predicate<Object> isGrayed = Predicates.alwaysFalse();

    private Function<Object, Void> selectedAction = DO_NOTHING;

    private Function<Object, Void> deselectedAction = DO_NOTHING;

    private FilteredTreeContentProvider contentProvider;

    /**
     * A customized version of CheckedTreeSelectionDialog with a combo to filter
     * the view to show all elements/only checked elements/only unchecked
     * elements.
     * 
     * @author pcdavid
     */
    protected final class CustomTreeSelectionDialog extends CheckedTreeSelectionDialog {

        /**
         * A String used to identify the Text allowing user to type regular
         * expression (can be used for testing).
         */
        public static final String REGEXP_TOOL_TIP = "Expression that will be used to filer elements by name (for example 'abc', 'a?c', '*c'...)";

        /**
         * The title of the Group allowing user to type Regular Expressions and
         * filter the selection.
         */
        private static final String REGEXP_TITLE = "Filter elements by name";

        /**
         * The String explaining to user how to use regular expressions.
         */
        private static final String REGEXP_EXPLANATIONS = "? = any character, * = any String";

        /**
         * A matcher used to determine if a given DDiagramElement is matching
         * the regular expression typed by user. It's updated each time the user
         * modify the regular expression.
         */
        protected DiagramElementsSelectionDialogPatternMatcher patternMatcher;

        /**
         * Collection of elements currently checked by user.
         */
        private final Set<Object> checkedElements = Sets.newHashSet();

        private CustomTreeSelectionDialog(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
            super(parent, labelProvider, contentProvider);
            patternMatcher = new DiagramElementsSelectionDialogPatternMatcher(""); //$NON-NLS-1$
        }

        @Override
        public void setInitialSelections(Object[] selectedElements) {
            setInitialElementSelections(Lists.newArrayList(selectedElements));
        }

        @Override
        public void setInitialElementSelections(List selectedElements) {
            List<Object> filteredSeletection = Lists.newArrayList(Iterables.filter(selectedElements, Predicates.not(isGrayed)));
            checkedElements.addAll(filteredSeletection);
            super.setInitialElementSelections(filteredSeletection);
        }

        /**
         * Returns the elements currently checked by user.
         * 
         * @return the elements currently checked by user
         */
        public Set<Object> getCheckedElements() {
            return checkedElements;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.dialogs.Dialog#createContents(org.eclipse.swt.widgets.Composite)
         */
        @Override
        protected Control createContents(Composite parent) {
            Control result = super.createContents(parent);
            getTreeViewer().setCheckStateProvider(new ICheckStateProvider() {

                public boolean isGrayed(Object element) {
                    return isGrayed.apply(element);
                }

                public boolean isChecked(Object element) {
                    return checkedElements.contains(element);
                }
            });
            getTreeViewer().addCheckStateListener(new ICheckStateListener() {
                public void checkStateChanged(CheckStateChangedEvent event) {
                    if (!isGrayed.apply(event.getElement())) {
                        if (event.getChecked()) {
                            checkedElements.add(event.getElement());
                        } else {
                            checkedElements.remove(event.getElement());
                        }
                    }
                }
            });
            return result;
        }

        /**
         * This method has been overridden to be able to insert selection
         * buttons between the top label and the tree viewer.
         * 
         * {@inheritDoc}
         */
        @Override
        protected Label createMessageArea(Composite composite) {
            Label createMessageArea = super.createMessageArea(composite);

            createSelectionButtonsAfterMessageArea(composite);

            // creating a text zone to allow user to type regular expressions
            createRegexpTypeZone(composite);
            return createMessageArea;
        }

        /**
         * This method has been overridden to remove the selection buttons that
         * are generically created after the tree viewer. This method should not
         * return a null value. Otherwise, in case of empty list we will have a
         * NPE.
         * 
         * {@inheritDoc}
         */
        @Override
        protected Composite createSelectionButtons(Composite composite) {
            Composite buttonComposite = new Composite(composite, SWT.RIGHT) {
                /**
                 * This method has been overridden to have an "empty" size for
                 * this composite. {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.widgets.Composite#computeSize(int, int,
                 *      boolean)
                 */
                @Override
                public Point computeSize(int wHint, int hHint, boolean b) {
                    return super.computeSize(0, 0, b);
                }
            };
            buttonComposite.setVisible(false);
            return buttonComposite;
        }

        /**
         * Creates selection buttons.
         * 
         * @param composite
         *            the parent composite
         * @return the selection buttons composite
         */
        protected Composite createSelectionButtonsAfterMessageArea(Composite composite) {
            Composite buttonComposite = new Composite(composite, SWT.RIGHT);
            GridLayout layout = new GridLayout();
            layout.numColumns = 7;
            layout.makeColumnsEqualWidth = false;
            buttonComposite.setLayout(layout);
            buttonComposite.setFont(composite.getFont());

            GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
            data.grabExcessHorizontalSpace = true;
            composite.setData(data);

            new Label(buttonComposite, SWT.LEAD).setText("Show");
            final Combo choices = new Combo(buttonComposite, SWT.READ_ONLY);
            choices.add(FilteringMode.SHOW_ALL.getName());
            choices.add(FilteringMode.SHOW_ONLY_CHECKED_ELEMENTS.getName());
            choices.add(FilteringMode.SHOW_ONLY_UNCHECKED_ELEMENTS.getName());
            choices.select(0);
            choices.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    switch (choices.getSelectionIndex()) {
                    case 0:
                        updateFilteringMode(FilteringMode.SHOW_ALL);
                        break;
                    case 1:
                        updateFilteringMode(FilteringMode.SHOW_ONLY_CHECKED_ELEMENTS);
                        break;
                    case 2:
                        updateFilteringMode(FilteringMode.SHOW_ONLY_UNCHECKED_ELEMENTS);
                        break;
                    default:
                        throw new RuntimeException();
                    }
                }
            });
            data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
            data.grabExcessHorizontalSpace = true;
            data.horizontalSpan = 2;
            choices.setLayoutData(data);

            data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
            data.grabExcessHorizontalSpace = true;
            addButton(buttonComposite, "Check All", DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.CHECK_ALL_ICON), new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    checkAll();
                    if (choices.getSelectionIndex() == 1) {
                        updateFilteringMode(FilteringMode.SHOW_ONLY_CHECKED_ELEMENTS);
                    } else if (choices.getSelectionIndex() == 2) {
                        updateFilteringMode(FilteringMode.SHOW_ONLY_UNCHECKED_ELEMENTS);
                    }
                }
            }).setLayoutData(data);

            addButton(buttonComposite, "Uncheck All", DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.UNCHECK_ALL_ICON), new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    uncheckAll();
                    if (choices.getSelectionIndex() == 1) {
                        updateFilteringMode(FilteringMode.SHOW_ONLY_CHECKED_ELEMENTS);
                    } else if (choices.getSelectionIndex() == 2) {
                        updateFilteringMode(FilteringMode.SHOW_ONLY_UNCHECKED_ELEMENTS);
                    }
                }
            }).setLayoutData(data);

            addButton(buttonComposite, "Expand All", DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.EXPAND_ALL_ICON), new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    expandAll();
                }
            }).setLayoutData(data);

            addButton(buttonComposite, "Collapse All", DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.COLLAPSE_ALL_ICON), new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    collapseAll();
                }
            }).setLayoutData(data);

            return buttonComposite;
        }

        /**
         * Creates a zone in which user will be able to type a Regular
         * Expression to filter the shown elements.
         * 
         * @param composite
         *            the parent composite
         */
        private void createRegexpTypeZone(Composite composite) {
            // Step 1 : create Group
            Group expregGroup = new Group(composite, SWT.NONE);
            expregGroup.setText(REGEXP_TITLE);
            GridLayout expregLayout = new GridLayout();
            expregGroup.setLayout(expregLayout);
            expregGroup.setFont(composite.getFont());
            expregGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

            // Step 2 : create explanations zone
            final Label explanationsLabel = new Label(expregGroup, SWT.NONE);
            explanationsLabel.setText(REGEXP_EXPLANATIONS);

            // Step 3 : create the text zone in which user will type the expreg
            final Text regularExpressionText = new Text(expregGroup, SWT.BORDER);
            regularExpressionText.setToolTipText(REGEXP_TOOL_TIP);
            regularExpressionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

            // Step 4 : add modify listener to this textZone
            regularExpressionText.addModifyListener(new ModifyListener() {

                public void modifyText(ModifyEvent e) {
                    String typedRegex = ((Text) e.getSource()).getText();
                    // Each time the regular expression is modified, the
                    // patternMatcher is updated
                    setPatternMatcher(new DiagramElementsSelectionDialogPatternMatcher(typedRegex));
                    updateFilteringMode(mode);
                }
            });
        }

        /**
         * Sets the matcher used to determine if a given DDiagramElement is
         * matching the regular expression typed by user.
         * 
         * @param patternMatcher
         *            the patternMatcher to set
         */
        public void setPatternMatcher(DiagramElementsSelectionDialogPatternMatcher patternMatcher) {
            this.patternMatcher = patternMatcher;
        }

        private Button addButton(Composite parent, String toolTipText, Image image, SelectionListener action) {
            Button button = new Button(parent, SWT.PUSH);
            button.setToolTipText(toolTipText);
            button.setImage(image);
            button.addSelectionListener(action);
            return button;
        }

        private void checkAll() {
            Object root = getTreeViewer().getInput();
            setRecursiveState(root, true);
        }

        private void uncheckAll() {
            Object root = getTreeViewer().getInput();
            setRecursiveState(root, false);
        }

        private void setRecursiveState(Object element, boolean state) {
            getTreeViewer().setChecked(element, state);
            if (!isGrayed.apply(element)) {
                if (state) {
                    checkedElements.add(element);
                } else {
                    checkedElements.remove(element);
                }
            }
            for (Object child : contentProvider.getChildren(element)) {
                setRecursiveState(child, state);
            }
        }

        private void expandAll() {
            getTreeViewer().expandAll();
        }

        private void collapseAll() {
            getTreeViewer().collapseAll();
        }

        /**
         * Updates the treeViewer after a change in the filteringMode or the
         * typed regular expression.
         * 
         * @param filteringMode
         *            the new filtering mode
         */
        public void updateFilteringMode(FilteringMode filteringMode) {
            mode = filteringMode;
            this.refresh();
            // We expand the tree so that all elements matching the regular
            // expression (i.e. all visible leafs) are correctly shown
            getTreeViewer().expandAll();
            getTreeViewer().setAllChecked(false);
            for (Object element : checkedElements) {
                getTreeViewer().setChecked(element, true);
            }
        }

        /**
         * Indicates if the given element is checked <b>AND</b> is matching the
         * currently typed regular expression.
         * 
         * @param element
         *            the element to test
         * @return true if the given element is checked <b>AND</b> is matching
         *         the currently typed regular expression, false otherwise.
         */
        public boolean isMatchingExpregOrHasMatchingExpregDescendantsCheckedMode(Object element) {
            Predicate<Object> isCheckedElementPredicate = Predicates.in(checkedElements);
            Predicate<Object> isMatchinExpregPredicate = getRegexpMatchPredicate();
            return isOrHasDescendant(element, Predicates.and(isCheckedElementPredicate, isMatchinExpregPredicate));
        }

        /**
         * Indicates if the given element is unchecked <b>AND</b> is matching
         * the currently typed regular expression.
         * 
         * @param element
         *            the element to test
         * @return true if the given element is unchecked <b>AND</b> is matching
         *         the currently typed regular expression, false otherwise.
         */
        public boolean isMatchingExpregOrHasMatchingExpregDescendantsUncheckedMode(Object element) {
            Predicate<Object> isUncheckedElementPredicate = Predicates.not(Predicates.in(checkedElements));
            Predicate<Object> isMatchinExpregPredicate = getRegexpMatchPredicate();
            return isOrHasDescendant(element, Predicates.and(isUncheckedElementPredicate, isMatchinExpregPredicate));
        }

        /**
         * Indicates if the given element is matching the currently typed
         * regular expression.
         * 
         * @param element
         *            the element to test
         * @return true if the given element is matching the currently typed
         *         regular expression, false otherwise.
         */
        public boolean isMatchingExpregOrHasMatchingExpregDescendantsAllMode(Object element) {
            return isOrHasDescendant(element, getRegexpMatchPredicate());
        }

        /**
         * Indicates if the given element or at least one of its children checks
         * the given predicate.
         * 
         * @param element
         *            the element to check
         * @param pred
         *            the predicate to sue
         * @return true if the given element or at least one of its children
         *         checks the given predicate, false otherwise
         */
        public boolean isOrHasDescendant(Object element, final Predicate<Object> pred) {
            boolean matches = pred.apply(element);
            if (matches) {
                return true;
            } else {
                return Iterables.any(Arrays.asList(contentProvider.getChildren(element)), new Predicate<Object>() {
                    public boolean apply(Object input) {
                        return isOrHasDescendant(input, pred);
                    }
                });
            }
        }

        /**
         * Refreshes this dialog's viewer.
         */
        public void refresh() {
            getTreeViewer().refresh();
        }

        /**
         * Returns a Predicate indicating if an object is matching the Regular
         * Expression currently typed by user.
         * 
         * @return a Predicate indicating if an object is matching the Regular
         *         Expression currently typed by user
         */
        public Predicate<Object> getRegexpMatchPredicate() {
            return patternMatcher.getMatchPredicate();
        }
    }

    /**
     * Represents the various kinds of filtering supported by the tree viewer.
     * 
     * @author pcdavid
     */
    protected enum FilteringMode {
        /**
         * Filtering mode in which all elements are considered.
         */
        SHOW_ALL("all elements"),
        /**
         * Filtering mode in which only checked elements are considered.
         */
        SHOW_ONLY_CHECKED_ELEMENTS("only checked elements"),
        /**
         * Filtering mode in which only unchecked elements are considered.
         */
        SHOW_ONLY_UNCHECKED_ELEMENTS("only unchecked elements");

        private final String name;

        private FilteringMode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Constructor.
     * 
     * @param title
     *            the title of the dialog window.
     * @param message
     *            the message for the dialog.
     */
    public DiagramElementsSelectionDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * Sets the predicate to use to detect which elements of the diagram are
     * selected, in the sense of the criterion to be edited.
     * 
     * @param isSelectedPredicate
     *            the predicate to used to detect selected elements of the
     *            diagram.
     */
    public void setSelectionPredicate(Predicate<Object> isSelectedPredicate) {
        this.isSelected = isSelectedPredicate;
    }

    /**
     * Sets the predicate to use to detect which elements of the diagram are
     * selected, in the sense of the criterion to be edited.
     * 
     * @param isGrayedPredicate
     *            the predicate to used to detect selected elements of the
     *            diagram.
     */
    public void setGrayedPredicate(Predicate<Object> isGrayedPredicate) {
        this.isGrayed = isGrayedPredicate != null ? isGrayedPredicate : Predicates.alwaysFalse();
    }

    /**
     * @param parent
     * @return
     */
    private Set<Object> getAllChildren(Object parent) {
        Set<Object> result = new HashSet<Object>();
        Object[] children = contentProvider.getChildren(parent);
        for (Object element : children) {
            result.add(element);
            result.addAll(getAllChildren(element));
        }
        return result;
    }

    /**
     * Sets the operation to be applied on elements which are newly selected by
     * the user.
     * 
     * @param selectedAction
     *            the operation to apply to newly selected elements.
     */
    public void setSelectedAction(Function<Object, Void> selectedAction) {
        this.selectedAction = selectedAction;
    }

    /**
     * Sets the operation to be applied on elements which are deselected by the
     * user.
     * 
     * @param deselectedAction
     *            the operation to apply to deselected elements.
     */
    public void setDeselectedAction(Function<Object, Void> deselectedAction) {
        this.deselectedAction = deselectedAction;
    }

    /**
     * Asks the end-user for a list of elements to select/de-select, and applies
     * the corresponding changes.
     * 
     * @param parent
     *            the shell to use to interact with the user, if required.
     * @param ddiagram
     *            the diagram whose elements to edit.
     * @param includeNodeLabel
     *            include node label (if there are on border) in the tree
     *            content
     * @return <code>true</code> if the operation was correctly executed,
     *         <code>false</code> if it was canceled by the user.
     */
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
        diagram = null;
        dialog = null;
        contentProvider = null;
        return result;
    }

    /**
     * Init the content provider.
     * 
     * @param includeLabel
     *            include label (containers, lists, edges and nodes if there are
     *            on border for nodes) in the tree content
     */
    protected void initContentProvider(boolean includeLabel) {
        AdapterFactory adapterFactory = getAdapterFactory(includeLabel);
        Predicate<Object> predicate = Predicates.instanceOf(DDiagramElement.class);
        if (includeLabel) {
            predicate = Predicates.or(predicate, Predicates.instanceOf(AbstractDDiagramElementLabelItemProvider.class));
        }
        contentProvider = new FilteredTreeContentProvider(adapterFactory, predicate);
    }

    /**
     * Return all selected elements of the diagram that are display in the tree.
     * 
     * @return All selected elements of the diagram that are display in the
     *         tree.
     */
    protected Set<Object> getAllSelectedElements() {
        Set<Object> treeElements = getAllChildren(diagram);
        return Sets.newHashSet(Iterators.filter(treeElements.iterator(), Predicates.and(isSelected, Predicates.not(isGrayed))));
    }

    /**
     * Asks the user to edit the set of elements which should be selected/match
     * the criterion being edited.
     * 
     * @param parent
     *            the parent shell to use if user interaction requires opening
     *            new windows.
     * @param initialSelection
     *            the set of elements to display as checked on dialog opening.
     * @return the new set of all the elements in the diagram which were
     *         selected by the user, or <code>Options.newNone()</code> if the
     *         user canceled the operation.
     */
    protected Option<Set<Object>> askUserForNewSelection(Shell parent, Set<Object> initialSelection) {
        setupDialog(parent, initialSelection);
        int result = dialog.open();
        if (result == Window.OK) {
            Set<Object> selectedAfter = getElementsSelectedAfter();
            return Options.newSome(selectedAfter);
        } else {
            return Options.newNone();
        }
    }

    /**
     * Create an configure a selection dialog which allows the user to select a
     * sub-set of the elements in the diagram.
     * 
     * @param parent
     *            the parent shell.
     * @param initialSelection
     *            the set of elements to display as checked on dialog opening.
     */
    protected void setupDialog(Shell parent, Set<Object> initialSelection) {
        dialog = new CustomTreeSelectionDialog(parent, new SelectionDialogLabelProvider(), contentProvider);
        dialog.setTitle(title);

        String msg = message;
        if (!Predicates.alwaysFalse().equals(isGrayed)) {
            StringBuilder sb = new StringBuilder(message);
            sb.append("\n"); //$NON-NLS-1$
            sb.append("The wizard will have no effect on grayed elements.");
            msg = sb.toString();
        }
        dialog.setMessage(msg);
        dialog.setInput(diagram);
        dialog.addFilter(new ModeFilter());
        dialog.setInitialElementSelections(Lists.newArrayList(initialSelection));
    }

    /**
     * Returns the elements selected when the dialog is about to close.
     * 
     * @return the elements selected when the dialog is about to close
     */
    protected Set<Object> getElementsSelectedAfter() {
        Set<Object> selectedElements = Sets.newHashSet();
        for (Object obj : dialog.checkedElements) {
            if (obj instanceof DDiagramElement) {
                selectedElements.add(obj);
            } else if (obj instanceof AbstractDDiagramElementLabelItemProvider) {
                selectedElements.add(obj);
            }
        }
        return selectedElements;
    }

    /**
     * Updates the status of the elements according to the user request.
     * 
     * @param selectedBefore
     *            all (and only) the elements in the diagram which were actually
     *            pinned before the action.
     * @param selectedAfter
     *            all (and only) the elements in the diagram which should be
     *            pinned as requested by the user.
     */
    protected void applyRequestedChanges(Set<Object> selectedBefore, Set<Object> selectedAfter) {
        for (Object dde : Sets.difference(selectedBefore, selectedAfter)) {
            deselectedAction.apply(dde);
        }
        for (Object dde : Sets.difference(selectedAfter, selectedBefore)) {
            selectedAction.apply(dde);
        }
    }

    /**
     * Returns the adapter factory used by this viewer.
     * 
     * @param includeLabel
     *            include label (containers, lists) in the tree content
     * 
     * @return The adapter factory used by this viewer.
     */
    private AdapterFactory getAdapterFactory(final boolean includeLabel) {
        List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(new ViewpointItemProviderAdapterFactory());
        factories.add(new DiagramItemProviderWithLabelAdapterFactory(includeLabel));
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
        return new ComposedAdapterFactory(factories);
    }

    /**
     * Specific diagram item provider adapter factory to create item provider
     * for DNodeContainer and DNodelist able to display their label as virtual
     * children.
     */
    private class DiagramItemProviderWithLabelAdapterFactory extends DiagramItemProviderAdapterFactory {

        /**
         * The item provider used to simulate another child for Container and
         * List regarding the node label configuration.
         */
        private final HashMap<Object, DDiagramElementContainerLabelItemProvider> labelItemProviders = Maps.newHashMap();

        private boolean includeLabel;

        public DiagramItemProviderWithLabelAdapterFactory(boolean includeLabel) {
            this.includeLabel = includeLabel;
        }

        private Collection<Object> completeChildren(Object object, Collection<Object> children, AdapterFactory adapterFactory) {
            Collection<Object> result = children;
            if (includeLabel && object instanceof DDiagramElementContainer) {
                Collection<Object> resultTemp = new ArrayList<Object>();
                if (labelItemProviders.get(object) == null) {
                    labelItemProviders.put(object, new DDiagramElementContainerLabelItemProvider(adapterFactory, (DDiagramElementContainer) object));
                }
                resultTemp.add(labelItemProviders.get(object));
                resultTemp.addAll(children);
                result = resultTemp;
            } else if (labelItemProviders.get(object) != null) {
                labelItemProviders.remove(object).dispose();
            }
            return result;
        }

        @Override
        public Adapter createDNodeContainerAdapter() {
            if (dNodeContainerItemProvider == null) {
                dNodeContainerItemProvider = new DNodeContainerItemProvider(this) {

                    @Override
                    public Collection<?> getChildren(Object object) {
                        Collection<Object> result = (Collection<Object>) super.getChildren(object);
                        return completeChildren(object, result, adapterFactory);
                    }
                };
            }
            return dNodeContainerItemProvider;
        }

        @Override
        public Adapter createDNodeListAdapter() {
            if (dNodeListItemProvider == null) {
                dNodeListItemProvider = new DNodeListItemProvider(this) {
                    @Override
                    public Collection<?> getChildren(Object object) {
                        Collection<Object> result = (Collection<Object>) super.getChildren(object);
                        return completeChildren(object, result, adapterFactory);
                    }
                };
            }
            return dNodeListItemProvider;
        }
    }

    private class ModeFilter extends ViewerFilter {

        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            boolean show = true;
            // Step 1: only showing check/unchecked element if required
            switch (mode) {
            case SHOW_ALL:
                show = dialog.isMatchingExpregOrHasMatchingExpregDescendantsAllMode(element);
                break;
            case SHOW_ONLY_CHECKED_ELEMENTS:
                show = dialog.isMatchingExpregOrHasMatchingExpregDescendantsCheckedMode(element);
                break;
            case SHOW_ONLY_UNCHECKED_ELEMENTS:
                show = dialog.isMatchingExpregOrHasMatchingExpregDescendantsUncheckedMode(element);
                break;
            default:
                show = true;
                break;
            }

            // Step 2: only showing elements which target has not been
            // deleted
            boolean isNonDangling = true;
            if (show) {
                DDiagramElement underlyingDDiagramElement = null;
                if (element instanceof DDiagramElement) {
                    underlyingDDiagramElement = (DDiagramElement) element;
                } else if (element instanceof AbstractDDiagramElementLabelItemProvider && ((AbstractDDiagramElementLabelItemProvider) element).getDiagramElementTarget().some()) {
                    underlyingDDiagramElement = ((AbstractDDiagramElementLabelItemProvider) element).getDiagramElementTarget().get();
                }

                if (underlyingDDiagramElement != null && underlyingDDiagramElement.eResource() != null) {
                    isNonDangling = underlyingDDiagramElement.getTarget() != null && underlyingDDiagramElement.getTarget().eResource() != null;
                } else {
                    isNonDangling = false;
                }
            }
            return show && isNonDangling;
        }
    }

    private class SelectionDialogLabelProvider extends OutlineLabelProvider implements IColorProvider {

        /**
         * {@inheritDoc}
         */
        public Color getForeground(final Object element) {

            Color foreground = null;
            if (isGrayed.apply(element)) {
                foreground = VisualBindingManager.getDefault().getColorFromName("light_gray"); //$NON-NLS-1$
            }
            return foreground;
        }

        /**
         * {@inheritDoc}
         */
        public Color getBackground(Object element) {
            return null;
        }
    }

}
