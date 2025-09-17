/*******************************************************************************
 * Copyright (c) 2008, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.business.api.viewpoint;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * A class which to show swt widgets with available viewpoints.
 *
 * @author mchauvin
 */
public final class ViewpointSelection {

    private static final String VIEWPOINT_SELECTION_WIZARD_PAGE_TITLE = Messages.ViewpointSelection_wizardTitle;

    private static final String VIEWPOINTS_SELECTION_WIZARD_PAGE_ID = "viewpointsSelection"; //$NON-NLS-1$

    private static final String[] COLUMNS = { " ", Messages.ViewpointSelection_iconColumn, Messages.ViewpointSelection_viewpointColumn }; //$NON-NLS-1$

    /**
     * Avoid instantiation.
     */
    private ViewpointSelection() {
    }

    /**
     * Return the lists of corresponding viewpoints.
     *
     * @param fileExtension
     *            The extension of the semantic model
     * @return The set of corresponding viewpoints, sorted with workspace viewpoints before plug-in viewpoints, and
     *         otherwise by name.
     */
    public static Set<Viewpoint> getViewpoints(final String fileExtension) {
        final Predicate<Viewpoint> isValidViewpoint = new Predicate<Viewpoint>() {
            @Override
            public boolean apply(final Viewpoint viewpoint) {
                return new ViewpointQuery(viewpoint).handlesSemanticModelExtension(fileExtension != null ? fileExtension : StringUtil.JOKER_STRING);
            }
        };

        final Set<Viewpoint> allViewpoints = ViewpointRegistry.getInstance().getViewpoints();
        final Set<Viewpoint> validViewpoints = new HashSet<Viewpoint>();
        validViewpoints.addAll(Collections2.filter(allViewpoints, isValidViewpoint));
        return validViewpoints;
    }

    /**
     * Return the lists of corresponding viewpoints.
     *
     * @param fileExtensions
     *            The extensions of the semantic models
     * @return The list of corresponding viewpoints
     */
    private static Set<Viewpoint> getViewpoints(final Collection<String> fileExtensions) {
        final SortedSet<Viewpoint> validViewpoints = new TreeSet<Viewpoint>(new ViewpointRegistry.ViewpointComparator());
        for (final String extension : fileExtensions) {
            validViewpoints.addAll(ViewpointSelection.getViewpoints(extension));
        }
        return validViewpoints;
    }

    /**
     * Returns the semantic extensions of the given session.
     *
     * @param session
     *            the session.
     * @return the semantic extensions of the given session.
     */
    private static Collection<String> getSemanticFileExtensions(final Session session) {
        final Collection<String> fileExtensions = new HashSet<String>();
        for (final Resource resource : session.getSemanticResources()) {
            if (resource != null && resource.getURI() != null) {
                final String currentFileExtension = resource.getURI().fileExtension();
                if (currentFileExtension != null) {
                    fileExtensions.add(currentFileExtension);
                }
            }
        }
        return fileExtensions;
    }

    /**
     * Create a selection wizard page to select a viewpoint.
     *
     * @param fileExtension
     *            the semantic file extension.
     * @param viewpointsMap
     *            an empty map, which will be filled
     * @return the wizard page
     * @since 0.9.0
     */
    public static WizardPage createWizardPage(final String fileExtension, final SortedMap<Viewpoint, Boolean> viewpointsMap) {
        final SortedSet<Viewpoint> viewpoints = new TreeSet<Viewpoint>(new ViewpointRegistry.ViewpointComparator());
        viewpoints.addAll(ViewpointSelection.getViewpoints(fileExtension));

        for (final Viewpoint viewpoint : viewpoints) {
            viewpointsMap.put(viewpoint, Boolean.FALSE);
        }

        final WizardPage page = new WizardPage(ViewpointSelection.VIEWPOINTS_SELECTION_WIZARD_PAGE_ID, ViewpointSelection.VIEWPOINT_SELECTION_WIZARD_PAGE_TITLE, null) {

            @Override
            public void createControl(final Composite parent) {
                setControl(ViewpointSelection.createViewpointsTableControl(parent, this.getContainer(), viewpointsMap));
            }

            private boolean isThereOneSelectedViewpoint() {
                return Maps.filterValues(viewpointsMap, new Predicate<Boolean>() {
                    @Override
                    public boolean apply(final Boolean input) {
                        return input.booleanValue();
                    }
                }).entrySet().iterator().hasNext();
            }

            @Override
            public boolean isPageComplete() {
                return super.isPageComplete() && isThereOneSelectedViewpoint();
            }

        };
        return page;
    }

    private static Control createViewpointsTableControl(final Composite parent, final IWizardContainer wizardContainer, final Map<Viewpoint, Boolean> viewpoints) {
        return ViewpointSelection.createViewpointsTableControl(parent, viewpoints.keySet(), new WizardViewpointsTableLazyCellModifier(viewpoints, wizardContainer),
                new ViewpointsTableLabelProvider(viewpoints));
    }

    /**
     * A cell modifier which applies to a wizard page.
     *
     * @author mchauvin
     */
    private static class WizardViewpointsTableLazyCellModifier extends ViewpointsTableLazyCellModifier {

        private final IWizardContainer wizardContainer;

        /**
         * Constructor.
         */
        WizardViewpointsTableLazyCellModifier(final Map<Viewpoint, Boolean> viewpoints, final IWizardContainer wizardContainer) {
            super(viewpoints);
            this.wizardContainer = wizardContainer;
        }

        @Override
        public void modify(final Object element, final String property, final Object value) {
            super.modify(element, property, value);

            if (property.equals(ViewpointSelection.COLUMNS[0])) {
                this.wizardContainer.updateButtons();
            }
        }
    }

    /**
     * Create a selection wizard page to select a viewpoint.
     *
     * @param semanticModel
     *            the semantic model or null.
     * @param viewpointsMap
     *            an empty map which will be filled
     * @return the wizard page
     * @since 0.9.0
     */
    public static WizardPage createWizardPage(final IFile semanticModel, final SortedMap<Viewpoint, Boolean> viewpointsMap) {

        String semanticExtension = null;

        if (semanticModel != null) {
            semanticExtension = semanticModel.getFileExtension();
        }

        return ViewpointSelection.createWizardPage(semanticExtension, viewpointsMap);
    }

    /**
     * Create and open a new dialog to change the viewpoints selection status.
     *
     * @param session
     *            the session
     */
    public static void openViewpointsSelectionDialog(final Session session) {
        ViewpointSelection.openViewpointsSelectionDialog(session, true);
    }

    /**
     * Create and open a new dialog to change the viewpoints selection status.
     *
     * @param session
     *            the session
     * @param createNewRepresentations
     *            true to create new DRepresentation for RepresentationDescription having their initialization attribute
     *            at true for selected {@link Viewpoint}.
     */
    public static void openViewpointsSelectionDialog(final Session session, boolean createNewRepresentations) {
        session.getSemanticCrossReferencer();
        final SortedMap<Viewpoint, Boolean> viewpointsMap = ViewpointSelection.getViewpointsWithMonitor(session);
        org.eclipse.sirius.ui.business.internal.viewpoint.ViewpointSelectionDialog vsd = new org.eclipse.sirius.ui.business.internal.viewpoint.ViewpointSelectionDialog(
                PlatformUI.getWorkbench().getDisplay().getActiveShell(), viewpointsMap);
        if (vsd.open() == Window.OK) {
            ViewpointHelper.applyNewViewpointSelection(viewpointsMap, vsd.getSelection(), session, createNewRepresentations, new ViewpointSelectionCallbackWithConfimation());
        }
    }

    /**
     * Compute the error message for the given missing dependencies which indicates the required viewpoinst activation
     * to complete the current selection.
     *
     * @param missingDependencies
     *            a map with an entry for each select viewpoint which has missing dependencies. The entry's key is the
     *            viewpoint name, and the value is the list of names of all the missing viewpoints it depends on.
     * @return an error message which indicates the required viewpoint activation to complete the current selection.
     */
    public static String getMissingDependenciesErrorMessage(Map<String, Collection<String>> missingDependencies) {
        Function<Collection<String>, String> toStringList = new Function<Collection<String>, String>() {
            @Override
            public String apply(java.util.Collection<String> from) {
                return Joiner.on(", ").join(from); //$NON-NLS-1$
            }
        };
        StringBuilder sb = new StringBuilder(Messages.ViewpointSelection_missingDependencies_header).append("\n"); //$NON-NLS-1$
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, Collection<String>> entry : missingDependencies.entrySet()) {
            lines.add("- " + MessageFormat.format(Messages.ViewpointSelection_missingDependencies_requirements, entry.getKey(), toStringList.apply(entry.getValue()))); //$NON-NLS-1$
        }
        sb.append(Joiner.on("\n").join(lines)); //$NON-NLS-1$
        return sb.toString();
    }

    /**
     * Compute the missing viewpoint dependencies (if any) for all the viewpoints enabled by the user.
     *
     * @param selected
     *            the viewpoints selection request by the user.
     * @return for each selected viewpoint which has missing dependencies, an entry with the selected viewpoint's name
     *         as key and the list of the missing viewpoints' names as value.
     */
    public static Map<String, Collection<String>> getMissingDependencies(Set<Viewpoint> selected) {
        Set<String> selectedURIs = Sets.newHashSet(Iterables.filter(Iterables.transform(selected, new Function<Viewpoint, String>() {
            @Override
            public String apply(Viewpoint from) {
                Option<URI> uri = new ViewpointQuery(from).getViewpointURI();
                if (uri.some()) {
                    return uri.get().toString();
                } else {
                    return null;
                }
            }
        }), Predicates.notNull()));

        Multimap<String, String> result = HashMultimap.create();
        for (Viewpoint viewpoint : selected) {
            for (RepresentationExtensionDescription extension : new ViewpointQuery(viewpoint).getAllRepresentationExtensionDescriptions()) {
                String extended = extension.getViewpointURI();
                Pattern pattern = Pattern.compile(extended);
                if (!ViewpointHelper.atLeastOneUriMatchesPattern(selectedURIs, pattern)) {
                    result.put(viewpoint.getName(), extended.trim().replaceFirst("^viewpoint:/[^/]+/", "")); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
        return result.asMap();
    }

    private static SortedMap<Viewpoint, Boolean> getViewpointsWithMonitor(final Session session) {
        final SortedSet<Viewpoint> allViewpoints = new TreeSet<Viewpoint>(new ViewpointRegistry.ViewpointComparator());
        final SortedMap<Viewpoint, Boolean> viewpointsMap = new TreeMap<>(new ViewpointRegistry.ViewpointComparator());
        final IProgressService ps = PlatformUI.getWorkbench().getProgressService();
        try {
            ps.busyCursorWhile(new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor pm) {
                    pm.beginTask(Messages.ViewpointSelection_loadingViewpointsTask, 4);

                    final Collection<String> semanticFileExtensions = ViewpointSelection.getSemanticFileExtensions(session);
                    pm.worked(1);

                    final Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints(semanticFileExtensions);
                    pm.worked(1);

                    allViewpoints.addAll(viewpoints);
                    pm.worked(1);

                    Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

                    for (final Viewpoint viewpoint : allViewpoints) {
                        boolean selected = false;

                        for (Viewpoint selectedViewpoint : selectedViewpoints) {
                            if (EqualityHelper.areEquals(selectedViewpoint, viewpoint)) {
                                selected = true;
                                break;
                            }
                        }

                        viewpointsMap.put(viewpoint, Boolean.valueOf(selected));
                    }

                    pm.done();
                }
            });
            return viewpointsMap;
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new RuntimeException(e.getCause());
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Control createViewpointsTableControl(final Composite parent, final Set<Viewpoint> viewpoints, final TableViewerAwareCellModifier cellModifier,
            final IBaseLabelProvider labelProvider) {

        final Composite control = SWTUtil.createCompositeBothFill(parent, 1, false);
        final TableViewer tableViewer = new TableViewer(control, SWT.BORDER | SWT.FULL_SELECTION);
        ColumnViewerToolTipSupport.enableFor(tableViewer, ToolTip.NO_RECREATE);

        final Table table = tableViewer.getTable();

        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        final TableColumn tc0 = new TableColumn(table, SWT.CENTER, 0);
        tc0.setWidth(30);

        final TableColumn tc1 = new TableColumn(table, SWT.CENTER, 1);
        tc1.setWidth(30);

        final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.LEFT, 2);
        column.getColumn().setWidth(450);

        table.setSize(new Point(table.getSize().x, 510));

        // Can only changes the first column - the visible column
        final CellEditor[] editors = new CellEditor[3];
        editors[0] = new CheckboxCellEditor(table);
        for (int i = 1; i < 3; i++) {
            editors[i] = null;
        }

        tableViewer.setColumnProperties(ViewpointSelection.COLUMNS);

        tableViewer.setCellEditors(editors);
        cellModifier.setViewer(tableViewer);
        tableViewer.setCellModifier(cellModifier);
        tableViewer.setContentProvider(new ViewpointsTableContentProvider());
        tableViewer.setLabelProvider(labelProvider);
        tableViewer.setComparator(new ViewerComparator());

        tableViewer.setInput(viewpoints);

        /* Lines and headers are not visible */
        table.setLinesVisible(false);
        table.setHeaderVisible(false);

        return control;
    }

    /**
     * A simple callback.
     *
     * @author mchauvin
     */
    public interface Callback {

        /**
         * Select a {@link Viewpoint}.
         *
         * @param viewpoint
         *            the {@link Viewpoint} to select
         * @param session
         *            the current session
         * @param monitor
         *            a {@link IProgressMonitor} to show progression
         */
        void selectViewpoint(Viewpoint viewpoint, Session session, IProgressMonitor monitor);

        /**
         * Select a {@link Viewpoint}.
         *
         * @param viewpoint
         *            the {@link Viewpoint} to select
         * @param session
         *            the current session
         * @param createNewRepresentations
         *            true to create new DRepresentation for RepresentationDescription having their initialization
         *            attribute at true for selected {@link Viewpoint}s.
         * @param monitor
         *            a {@link IProgressMonitor} to show progression
         */
        void selectViewpoint(Viewpoint viewpoint, Session session, boolean createNewRepresentations, IProgressMonitor monitor);

        /**
         * Select a {@link Viewpoint}.
         *
         * @param viewpoint
         *            the {@link Viewpoint} to select
         * @param session
         *            the current session
         * @param createNewRepresentations
         *            true to create new DRepresentation for RepresentationDescription having their initialization
         *            attribute at true for selected {@link Viewpoint}s.
         * @param allSelectedViewpoint
         *            all viewpoints that should be selected .
         * @param monitor
         *            a {@link IProgressMonitor} to show progression
         */
        default void selectViewpoint(Viewpoint viewpoint, Session session, boolean createNewRepresentations, Set<Viewpoint> allSelectedViewpoint, IProgressMonitor monitor) {
            selectViewpoint(viewpoint, session, createNewRepresentations, monitor);
        }

        /**
         * Deselect a viewpoint.
         *
         * @param deselectedViewpoint
         *            the deselected viewpoint
         * @param session
         *            the current session
         * @param monitor
         *            a {@link IProgressMonitor} to show progression
         */
        void deselectViewpoint(Viewpoint deselectedViewpoint, Session session, IProgressMonitor monitor);

        /**
         * Deselect a viewpoint.
         *
         * @param deselectedViewpoint
         *            the deselected viewpoint
         * @param session
         *            the current session
         * 
         * @param allDeselectedViewpoint
         *            all viewpoints that should be unselected in addition to the given one.
         * @param monitor
         *            a {@link IProgressMonitor} to show progression
         */
        default void deselectViewpoint(Viewpoint deselectedViewpoint, Session session, Set<Viewpoint> allDeselectedViewpoint, IProgressMonitor monitor) {
            deselectViewpoint(deselectedViewpoint, session, monitor);
        }

    }

    /**
     * The label provider
     *
     * @author mchauvin
     */
    private static final class ViewpointsTableLabelProvider extends ColumnLabelProvider {

        private final Map<Viewpoint, Boolean> viewpoints;

        private int columnIndex;

        /**
         * Constructor.
         *
         * @param viewpoints
         *            the viewpoints
         */
        ViewpointsTableLabelProvider(final Map<Viewpoint, Boolean> viewpoints) {
            super();
            this.viewpoints = viewpoints;
        }

        private boolean findViewpoint(final Viewpoint vp) {
            for (final Map.Entry<Viewpoint, Boolean> entry : viewpoints.entrySet()) {
                if (EqualityHelper.areEquals(entry.getKey(), vp) && entry.getValue()) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Image getImage(final Object element) {
            Image image = null;

            switch (columnIndex) {
            case 0:
                if (element instanceof Viewpoint) {
                    final Viewpoint vp = (Viewpoint) element;
                    image = SiriusEditPlugin.getPlugin().getBundledImage("/icons/full/others/checkbox_inactive.gif"); //$NON-NLS-1$
                    if (findViewpoint(vp)) {
                        image = SiriusEditPlugin.getPlugin().getBundledImage("/icons/full/others/checkbox_active.gif"); //$NON-NLS-1$
                    }
                }
                break;
            case 1:
                if (element instanceof Viewpoint) {
                    final Viewpoint vp = (Viewpoint) element;
                    image = ViewpointHelper.getImage(vp);
                }
                break;
            case 2:
                break;
            default:
                break;
            }
            return image;
        }

        @Override
        public String getText(final Object element) {
            switch (columnIndex) {
            case 2:
                if (element instanceof Viewpoint) {
                    return MessageTranslator.INSTANCE.getMessage((Viewpoint) element, new IdentifiedElementQuery((Viewpoint) element).getLabel());
                }
                break;
            default:
                break;
            }
            return null;
        }

        @Override
        public String getToolTipText(final Object element) {
            String toolTip = null;
            if (columnIndex == 2 && element instanceof Viewpoint) {
                Viewpoint viewpoint = (Viewpoint) element;
                final Resource resource = ((Viewpoint) element).eResource();
                if (resource != null) {
                    toolTip = resource.getURI().toString();
                }
                if (viewpoint.getEndUserDocumentation() != null && viewpoint.getEndUserDocumentation().trim().length() > 0) {
                    if (toolTip != null) {
                        toolTip += "\n\n"; //$NON-NLS-1$
                    } else {
                        toolTip = ""; //$NON-NLS-1$
                    }
                    toolTip += viewpoint.getEndUserDocumentation();
                }
            }
            return toolTip;
        }

        @Override
        public Point getToolTipShift(final Object object) {
            return new Point(5, 5);
        }

        @Override
        public int getToolTipDisplayDelayTime(final Object object) {
            return 200;
        }

        @Override
        public void update(final ViewerCell cell) {
            columnIndex = cell.getColumnIndex();
            super.update(cell);
        }

        @Override
        public int getToolTipStyle(final Object object) {
            return SWT.SHADOW_OUT;
        }

    }

    /**
     * The content provider.
     *
     * @author mchauvin
     */
    private static final class ViewpointsTableContentProvider implements IStructuredContentProvider {
        @Override
        @SuppressWarnings("unchecked")
        public Object[] getElements(final Object inputElement) {
            if (inputElement instanceof Set<?>) {
                final Set<Viewpoint> viewpoints = (Set<Viewpoint>) inputElement;
                return viewpoints.toArray();
            }
            return Collections.EMPTY_LIST.toArray();
        }

        @Override
        public void dispose() {
            // nothing to do
        }

        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // nothing to do
        }
    }

    /**
     * An common interface which adds a set viewer method.
     *
     * @author mchauvin
     */
    private interface TableViewerAwareCellModifier extends ICellModifier {
        /**
         * Set the table viewer to update.
         *
         * @param viewer
         *            the viewer to update
         */
        void setViewer(TableViewer viewer);
    }

    /**
     * An common abstract class for cell modifiers.
     *
     * @author mchauvin
     */
    private abstract static class AbstractViewpointsTableCellModifier implements TableViewerAwareCellModifier {

        /**
         * Table viewer of the current table cell modifier.
         */
        protected TableViewer tableViewer;

        /** ll viewpoints and there selection state. */
        protected final Map<Viewpoint, Boolean> viewpoints;

        /**
         * Cosntructor.
         *
         * @param viewpoints
         *            All viewpoints and there selection state.
         */
        AbstractViewpointsTableCellModifier(final Map<Viewpoint, Boolean> viewpoints) {
            this.viewpoints = viewpoints;
        }

        @Override
        public void setViewer(final TableViewer viewer) {
            this.tableViewer = viewer;
        }

        @Override
        public boolean canModify(final Object element, final String property) {

            if (property.equals(ViewpointSelection.COLUMNS[0])) {
                /* first column */
                return true;
            }
            return false;
        }

    }

    /**
     * The cell modifier which only modifies the input map.
     *
     * @author mchauvin
     */
    private static class ViewpointsTableLazyCellModifier extends AbstractViewpointsTableCellModifier {

        /**
         * Constructor.
         *
         * @param viewpoints
         *            All viewpoints and there selection state.
         */
        ViewpointsTableLazyCellModifier(final Map<Viewpoint, Boolean> viewpoints) {
            super(viewpoints);
        }

        @Override
        public Object getValue(final Object element, final String property) {

            final Viewpoint viewpoint = (Viewpoint) element;
            Object result = null;

            if (property.equals(ViewpointSelection.COLUMNS[0])) {
                /* first column */

                result = Boolean.FALSE;
                for (final Map.Entry<Viewpoint, Boolean> entry : viewpoints.entrySet()) {
                    if (entry.getValue().booleanValue() && EqualityHelper.areEquals(viewpoint, entry.getKey())) {
                        result = Boolean.TRUE;
                        break;
                    }
                }
            } else if (property.equals(ViewpointSelection.COLUMNS[1])) {
                /* second column */
                // do nothing as there is only an image
            } else {
                /* third column */
                result = new IdentifiedElementQuery(viewpoint).getLabel();
            }
            return result;
        }

        @Override
        public void modify(final Object element, final String property, final Object value) {

            Object objElement;

            if (element instanceof Item) {
                objElement = ((Item) element).getData();
            } else {
                objElement = element;
            }

            if (property.equals(ViewpointSelection.COLUMNS[0])) {
                final Viewpoint vp = (Viewpoint) objElement;

                // Convert Object to Boolean without instanceof
                final Boolean result = Boolean.valueOf(Boolean.TRUE.equals(value));

                for (final Viewpoint viewpoint : viewpoints.keySet()) {
                    if (EqualityHelper.areEquals(viewpoint, vp)) {
                        viewpoints.put(viewpoint, result);
                        break;
                    }
                }

                /* update the label provider */
                this.tableViewer.update(vp, null);
            }
        }
    }
}
