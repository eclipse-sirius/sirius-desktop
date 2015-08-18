/*******************************************************************************
 * Copyright (c) 2008, 2009, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.viewpoint;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDecoration;
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
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
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

    private static final String VIEWPOINT_SELECTION_WIZARD_PAGE_TITLE = "Viewpoints selection";

    private static final String VIEWPOINTS_SELECTION_WIZARD_PAGE_ID = "viewpointsSelection"; //$NON-NLS-1$

    private static final String[] COLUMNS = { " ", "icon", "Viewpoint" }; //$NON-NLS-1$

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
     * @return The set of corresponding viewpoints, sorted with workspace
     *         viewpoints before plug-in viewpoints, and otherwise by name.
     */
    public static Set<Viewpoint> getViewpoints(final String fileExtension) {
        final Predicate<Viewpoint> isValidViewpoint = new Predicate<Viewpoint>() {
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

        final WizardPage page = new WizardPage(VIEWPOINTS_SELECTION_WIZARD_PAGE_ID, VIEWPOINT_SELECTION_WIZARD_PAGE_TITLE, null) {

            public void createControl(final Composite parent) {
                setControl(ViewpointSelection.createViewpointsTableControl(parent, this.getContainer(), viewpointsMap));
            }

            private boolean isThereOneSelectedViewpoint() {
                return Maps.filterValues(viewpointsMap, new Predicate<Boolean>() {
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
        return ViewpointSelection.createViewpointsTableControl(parent, viewpoints.keySet(), new WizardViewpointsTableLazyCellModifier(viewpoints, wizardContainer), new ViewpointsTableLabelProvider(
                viewpoints));
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
        public WizardViewpointsTableLazyCellModifier(final Map<Viewpoint, Boolean> viewpoints, final IWizardContainer wizardContainer) {
            super(viewpoints);
            this.wizardContainer = wizardContainer;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void modify(final Object element, final String property, final Object value) {
            super.modify(element, property, value);

            if (property.equals(COLUMNS[0])) {
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
        openViewpointsSelectionDialog(session, true);
    }

    /**
     * Create and open a new dialog to change the viewpoints selection status.
     * 
     * @param session
     *            the session
     * @param createNewRepresentations
     *            true to create new DRepresentation for
     *            RepresentationDescription having their initialization
     *            attribute at true for selected {@link Viewpoint}.
     */
    public static void openViewpointsSelectionDialog(final Session session, boolean createNewRepresentations) {
        if (Movida.isEnabled()) {
            session.getSemanticCrossReferencer();
            org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry registry = (org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry) ViewpointRegistry.getInstance();
            org.eclipse.sirius.business.internal.movida.ViewpointSelection selection = DAnalysisSessionHelper.getViewpointSelection(registry, (DAnalysisSession) session);
            Set<URI> selectedBefore = selection.getSelected();
            ViewpointSelectionDialog vsd = new ViewpointSelectionDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), registry, selection, getSemanticFileExtensions(session));
            if (vsd.open() == Window.OK) {
                applyViewpointSelectionChange(session, selection, selectedBefore);
            }
        } else {
            session.getSemanticCrossReferencer();
            final SortedMap<Viewpoint, Boolean> viewpointsMap = ViewpointSelection.getViewpointsWithMonitor(session);
            org.eclipse.sirius.ui.business.internal.viewpoint.ViewpointSelectionDialog vsd = new org.eclipse.sirius.ui.business.internal.viewpoint.ViewpointSelectionDialog(PlatformUI.getWorkbench()
                    .getDisplay().getActiveShell(), viewpointsMap);
            if (vsd.open() == Window.OK) {
                ViewpointSelection.applyNewViewpointSelection(viewpointsMap, vsd.getSelection(), session, createNewRepresentations);
            }
        }
    }

    /**
     * Compute the error message for the given missing dependencies which
     * indicates the required viewpoinst activation to complete the current
     * selection.
     * 
     * @param missingDependencies
     *            a map with an entry for each select viewpoint which has
     *            missing dependencies. The entry's key is the viewpoint name,
     *            and the value is the list of names of all the missing
     *            viewpoints it depends on.
     * @return an error message which indicates the required viewpoint
     *         activation to complete the current selection.
     */
    public static String getMissingDependenciesErrorMessage(Map<String, Collection<String>> missingDependencies) {
        return "The list of selected viewpoints is invalid; please fix the problems:\n" + "- "
                + Joiner.on("\n- ").withKeyValueSeparator(" requires: ").join(Maps.transformValues(missingDependencies, new Function<Collection<String>, String>() {
                    public String apply(java.util.Collection<String> from) {
                        return Joiner.on(", ").join(from);
                    }
                }));
    }

    private static void applyViewpointSelectionChange(final Session session, org.eclipse.sirius.business.internal.movida.ViewpointSelection selection, Set<URI> selectedBefore) {
        Set<URI> selectedAfter = selection.getSelected();
        Set<URI> newSelected = Sets.difference(selectedAfter, selectedBefore);
        Set<URI> newDeselected = Sets.difference(selectedBefore, selectedAfter);
        final ViewpointSelection.Callback callback = new ViewpointSelectionCallbackWithConfimation();
        final Set<Viewpoint> newSelectedViewpoints = Sets.newHashSet(Iterables.transform(newSelected, new Function<URI, Viewpoint>() {
            public Viewpoint apply(URI from) {
                return SiriusResourceHelper.getCorrespondingViewpoint(session, from, true).get();
            }
        }));
        final Set<Viewpoint> newDeselectedViewpoints = Sets.newHashSet(Iterables.transform(newDeselected, new Function<URI, Viewpoint>() {
            public Viewpoint apply(URI from) {
                return SiriusResourceHelper.getCorrespondingViewpoint(session, from, true).get();
            }
        }));
        // Only if there is something to do
        if (!newSelected.isEmpty() || !newDeselected.isEmpty()) {

            try {
                Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                IRunnableContext context = new ProgressMonitorDialog(shell);
                IRunnableWithProgress runnable = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask("Apply new viewpoints selection...", 1);
                            Command command = new ChangeViewpointSelectionCommand(session, callback, newSelectedViewpoints, newDeselectedViewpoints, new SubProgressMonitor(monitor, 1));
                            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                            domain.getCommandStack().execute(command);
                        } finally {
                            monitor.done();
                        }
                    }

                };
                PlatformUI.getWorkbench().getProgressService().runInUI(context, runnable, null);
            } catch (final InvocationTargetException e) {
                if (e.getCause() instanceof RuntimeException) {
                    throw (RuntimeException) e.getCause();
                }
                throw new RuntimeException(e);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Compute the missing viewpoint dependencies (if any) for all the
     * viewpoints enabled by the user.
     * 
     * @param selected
     *            the viewpoints selection request by the user.
     * @return for each selected viewpoint which has missing dependencies, an
     *         entry with the selected viewpoint's name as key and the list of
     *         the missing viewpoints' names as value.
     */
    public static Map<String, Collection<String>> getMissingDependencies(Set<Viewpoint> selected) {
        Set<String> selectedURIs = Sets.newHashSet(Iterables.filter(Iterables.transform(selected, new Function<Viewpoint, String>() {
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
                if (!atLeastOneUriMatchesPattern(selectedURIs, pattern)) {
                    result.put(viewpoint.getName(), extended.trim().replaceFirst("^viewpoint:/[^/]+/", "")); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
        return result.asMap();
    }

    private static boolean atLeastOneUriMatchesPattern(Set<String> selectedURIs, Pattern pattern) {
        for (String uriToMatch : selectedURIs) {
            Matcher matcher = pattern.matcher(uriToMatch);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    private static void applyNewViewpointSelection(final Map<Viewpoint, Boolean> originalMap, final Map<Viewpoint, Boolean> newMap, final Session session, final boolean createNewRepresentations) {

        // newMap is a copy of originalMap with modifications on values.
        // No elements should have been added.
        if (originalMap.size() != newMap.size()) {
            throw new IllegalArgumentException("Original and new lists of viewpoints should not be 'different'");
        }

        final Set<Viewpoint> newSelectedViewpoints = Sets.newHashSet();
        final Set<Viewpoint> newDeselectedViewpoints = Sets.newHashSet();

        /*
         * newMap and originalMap are sorted with the same comparator and keys
         * haven't changed. We can iterate on the 2 maps together.
         */
        final Iterator<Entry<Viewpoint, Boolean>> originalIterator = originalMap.entrySet().iterator();
        final Iterator<Entry<Viewpoint, Boolean>> newIterator = newMap.entrySet().iterator();

        while (originalIterator.hasNext() && newIterator.hasNext()) {
            final Entry<Viewpoint, Boolean> originalEntry = originalIterator.next();
            final Entry<Viewpoint, Boolean> newEntry = newIterator.next();

            /* XOR : only if original and new booleans are different */
            if (originalEntry.getValue().booleanValue() ^ newEntry.getValue().booleanValue()) {

                // originalEntry and newEntry booleans are differents
                // Just need to test one of them

                // true : has been selected
                if (newEntry.getValue().booleanValue()) {
                    // We can use here originalEntry or newEntry indifferently
                    newSelectedViewpoints.add(originalEntry.getKey());
                } else {
                    // We can use here originalEntry or newEntry indifferently
                    newDeselectedViewpoints.add(originalEntry.getKey());
                }
            }
        }

        final ViewpointSelection.Callback callback = new ViewpointSelectionCallbackWithConfimation();

        // Only if there is something to do
        if (!newSelectedViewpoints.isEmpty() || !newDeselectedViewpoints.isEmpty()) {

            try {
                IRunnableWithProgress runnable = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) {
                        Command command = new ChangeViewpointSelectionCommand(session, callback, newSelectedViewpoints, newDeselectedViewpoints, createNewRepresentations, monitor);
                        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                        domain.getCommandStack().execute(command);
                    }

                };
                new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(true, false, runnable);
            } catch (final InvocationTargetException e) {
                if (e.getCause() instanceof RuntimeException) {
                    throw (RuntimeException) e.getCause();
                }
                throw new RuntimeException(e);
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static SortedMap<Viewpoint, Boolean> getViewpointsWithMonitor(final Session session) {
        final SortedSet<Viewpoint> allViewpoints = new TreeSet<Viewpoint>(new ViewpointRegistry.ViewpointComparator());
        final SortedMap<Viewpoint, Boolean> viewpointsMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
        final IProgressService ps = PlatformUI.getWorkbench().getProgressService();
        try {
            ps.busyCursorWhile(new IRunnableWithProgress() {
                public void run(final IProgressMonitor pm) {
                    pm.beginTask("Loading viewpoints...", 4);

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

    private static Control createViewpointsTableControl(final Composite parent, final Set<Viewpoint> viewpoints, final TableViewerAwareCellModifier cellModifier, final IBaseLabelProvider labelProvider) {

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

        tableViewer.setColumnProperties(COLUMNS);

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
         *            true to create new DRepresentation for
         *            RepresentationDescription having their initialization
         *            attribute at true for selected {@link Viewpoint}s.
         * @param monitor
         *            a {@link IProgressMonitor} to show progression
         */
        void selectViewpoint(Viewpoint viewpoint, Session session, boolean createNewRepresentations, IProgressMonitor monitor);

        /**
         * deselect a viewpoint.
         * 
         * @param deselectedViewpoint
         *            the deselected viewpoint
         * @param session
         *            the current session
         * @param monitor
         *            a {@link IProgressMonitor} to show progression
         */
        void deselectViewpoint(Viewpoint deselectedViewpoint, Session session, IProgressMonitor monitor);

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
        public ViewpointsTableLabelProvider(final Map<Viewpoint, Boolean> viewpoints) {
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

        private ImageDescriptor getOverlayedDescriptor(final Image baseImage, final String decoratorPath) {
            final ImageDescriptor decoratorDescriptor = SiriusEditPlugin.Implementation.getBundledImageDescriptor(decoratorPath);
            return new DecorationOverlayIcon(baseImage, decoratorDescriptor, IDecoration.BOTTOM_LEFT);
        }

        private Image getEnhancedImage(final Image image, final Viewpoint viewpoint) {
            if (!ViewpointRegistry.getInstance().isFromPlugin(viewpoint) && image != null) {
                return SiriusEditPlugin.getPlugin().getImage(getOverlayedDescriptor(image, "icons/full/decorator/folder_close.gif")); //$NON-NLS-1$
            }
            return image;
        }

        /**
         * {@inheritDoc}
         */
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
                    if (vp.getIcon() != null && vp.getIcon().length() > 0) {
                        final ImageDescriptor desc = SiriusEditPlugin.Implementation.findImageDescriptor(vp.getIcon());
                        if (desc != null) {
                            image = SiriusEditPlugin.getPlugin().getImage(desc);
                            image = getEnhancedImage(image, vp);
                        }
                    }
                    if (image == null) {
                        image = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.getPlugin().getItemImageDescriptor(vp));
                        image = getEnhancedImage(image, vp);
                    }
                }
                break;
            case 2:
                break;
            default:
                break;
            }
            return image;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getText(final Object element) {
            switch (columnIndex) {
            case 2:
                if (element instanceof Viewpoint) {
                    return new IdentifiedElementQuery((Viewpoint) element).getLabel();
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

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        public Object[] getElements(final Object inputElement) {
            if (inputElement instanceof Set<?>) {
                final Set<Viewpoint> viewpoints = (Set<Viewpoint>) inputElement;
                return viewpoints.toArray();
            }
            return Collections.EMPTY_LIST.toArray();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose() {
            // nothing to do
        }

        /**
         * {@inheritDoc}
         */
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
        void setViewer(final TableViewer viewer);
    }

    /**
     * An common abstract class for cell modifiers.
     * 
     * @author mchauvin
     */
    private abstract static class AbstractViewpointsTableCellModifier implements TableViewerAwareCellModifier {

        protected TableViewer tableViewer;

        /** ll viewpoints and there selection state. */
        protected final Map<Viewpoint, Boolean> viewpoints;

        /**
         * Cosntructor.
         * 
         * @param viewpoints
         *            All viewpoints and there selection state.
         */
        public AbstractViewpointsTableCellModifier(final Map<Viewpoint, Boolean> viewpoints) {
            this.viewpoints = viewpoints;
        }

        /**
         * {@inheritDoc}
         */
        public void setViewer(final TableViewer viewer) {
            this.tableViewer = viewer;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
         *      java.lang.String)
         */
        public boolean canModify(final Object element, final String property) {

            if (property.equals(COLUMNS[0])) {
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
        public ViewpointsTableLazyCellModifier(final Map<Viewpoint, Boolean> viewpoints) {
            super(viewpoints);
        }

        /**
         * {@inheritDoc}
         */
        public Object getValue(final Object element, final String property) {

            final Viewpoint viewpoint = (Viewpoint) element;
            Object result = null;

            if (property.equals(COLUMNS[0])) {
                /* first column */

                result = Boolean.FALSE;
                for (final Map.Entry<Viewpoint, Boolean> entry : viewpoints.entrySet()) {
                    if (entry.getValue().booleanValue() && EqualityHelper.areEquals(viewpoint, entry.getKey())) {
                        result = Boolean.TRUE;
                        break;
                    }
                }
            } else if (property.equals(COLUMNS[1])) {
                /* second column */
                // do nothing as there is only an image
            } else {
                /* third column */
                result = new IdentifiedElementQuery(viewpoint).getLabel();
            }
            return result;
        }

        /**
         * {@inheritDoc}
         */
        public void modify(final Object element, final String property, final Object value) {

            Object objElement;

            if (element instanceof Item) {
                objElement = ((Item) element).getData();
            } else {
                objElement = element;
            }

            if (property.equals(COLUMNS[0])) {
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
