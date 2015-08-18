/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.viewpoint;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * A dialog box which allow end-users to select which viewpoints are enabled
 * inside a session.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class ViewpointSelectionDialog extends TitleAreaDialog {
    private static final String VIEWPOINT_SELECTION_SHELL_TITLE = "Viewpoints Selection";

    private static final String VIEWPOINT_SELECTION_TITLE = "Selected viewpoints";

    private static final String VIEWPOINT_SELECTION_MESSAGE = "Change viewpoints selection status (see tooltip for details about each viewpoint)";

    /**
     * Table item
     */
    private final class Item {
        private final Viewpoint viewpoint;

        /**
         * Construct from a viewpoint
         * 
         * @param vp
         *            the viewpoint
         */
        public Item(Viewpoint vp) {
            this.viewpoint = vp;
        }

        /**
         * Get the item label
         * 
         * @return viewpoint name
         */
        public String getLabel() {
            return new IdentifiedElementQuery(viewpoint).getLabel();
        }

        /**
         * Select the viewpoint corresponding to this element.
         */
        public void onChecked() {
            // add to the selection
            selection.put(this.viewpoint, true);
        }

        /**
         * Deselect the viewpoint.
         */
        public void onUnchecked() {
            // remove from the selection
            selection.put(this.viewpoint, false);
        }

        /**
         * Convert to string
         * 
         * @return the label
         */
        @Override
        public String toString() {
            return this.getLabel();
        }

        /**
         * Get the viewpoint
         * 
         * @return the viewpoint
         */
        public Viewpoint getViewpoint() {
            return viewpoint;
        }

        /**
         * Compute state
         */
        public void computeState() {
            table.setChecked(this, isSelected());
        }

        /**
         * Returns true if the viewpoint is selected
         * 
         * @return true if the viewpoint is selected
         */
        private boolean isSelected() {
            return Boolean.TRUE.equals(selection.get(this.viewpoint));
        }
    } // class

    /**
     * Viewpoint table content provider.
     */
    private final class TableContentProvider implements IStructuredContentProvider {
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Ignore.
        }

        @Override
        public void dispose() {
            // Ignore.
        }

        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof List<?> && Iterables.all((List<?>) inputElement, Predicates.instanceOf(Item.class))) {
                @SuppressWarnings("unchecked")
                List<Item> topItems = (List<Item>) inputElement;
                return topItems.toArray();
            } else {
                return null;
            }
        }
    } // class

    /**
     * Viewpoint table label provider
     */
    private final class TableLabelProvider extends ColumnLabelProvider {
        @Override
        public Image getImage(final Object element) {
            Item item = (Item) element;
            
            Image viewpointImage = null;
            Viewpoint vp = item.getViewpoint();
            if (vp.getIcon() != null && vp.getIcon().length() > 0) {
                final ImageDescriptor desc = SiriusEditPlugin.Implementation.findImageDescriptor(vp.getIcon());
                if (desc != null) {
                    viewpointImage = SiriusEditPlugin.getPlugin().getImage(desc);
                }
            }
            // Default image
            if (viewpointImage == null) {
                viewpointImage = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.getPlugin().getItemImageDescriptor(vp));
            }
            
            // Add decorator if the viewpoint comes from workspace
            if (!ViewpointRegistry.getInstance().isFromPlugin(vp)) {
                final ImageDescriptor decoratorDescriptor = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/folder_close.gif"); //$NON-NLS-1$
                viewpointImage = SiriusEditPlugin.getPlugin().getImage(new DecorationOverlayIcon(viewpointImage, decoratorDescriptor, IDecoration.BOTTOM_LEFT));
            }
            return viewpointImage;
        }

        @Override
        public String getText(final Object element) {
            Item item = (Item) element;
            return item.getLabel();
        }

        @Override
        public String getToolTipText(Object element) {
            Item item = (Item) element;

            String toolTip = ""; // no null //$NON-NLS-1$
            Viewpoint viewpoint = item.getViewpoint();
            final Resource resource = viewpoint.eResource();
            if (resource != null) {
                toolTip = resource.getURI().toString();
            }

            if (viewpoint.getEndUserDocumentation() != null) {
                String doc = viewpoint.getEndUserDocumentation().trim();
                if (!doc.isEmpty()) {
                    if (!toolTip.isEmpty()) {
                        toolTip += "\n\n"; //$NON-NLS-1$
                    }

                    toolTip += doc;
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
        public int getToolTipStyle(final Object object) {
            return SWT.SHADOW_OUT;
        }
    } // class

    /**
     * Viewpoint table check state listener
     */
    private final class TableCheckStateListener implements ICheckStateListener {
        @Override
        public void checkStateChanged(CheckStateChangedEvent event) {
            if (event.getElement() instanceof Item) {
                Item item = (Item) event.getElement();
                if (event.getChecked()) {
                    item.onChecked();
                } else {
                    item.onUnchecked();
                }

                Map<String, Collection<String>> missingDependencies = getMissingDependencies();

                if (!missingDependencies.isEmpty()) {
                    String message = getMissingDependenciesErrorMessage(missingDependencies);
                    setErrorMessage(message);
                } else {
                    setErrorMessage(null);
                }

                getButton(IDialogConstants.OK_ID).setEnabled(missingDependencies.isEmpty());
            }
        }
    } // class

    private CheckboxTableViewer table;

    private final Map<Viewpoint, Boolean> selection;

    private final List<Item> input;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent shell for the dialog box.
     * @param selection
     *            the selection of viewpoints (this list is not modified).
     */
    public ViewpointSelectionDialog(Shell parentShell, Map<Viewpoint, Boolean> selection) {
        super(parentShell);

        // CHECKSTYLE:OFF Boolean expression complexity is 5 (max allowed is 3).
        setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE);
        // CHECKSTYLE:ON

        // clone the selection in order to modify it
        final SortedMap<Viewpoint, Boolean> newSelection = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
        newSelection.putAll(Preconditions.checkNotNull(selection));
        this.selection = newSelection;

        // item list
        this.input = computeItemList();

        // block on open
        setBlockOnOpen(true);
    }

    /**
     * Get the current selection. This selection contains user modification
     * 
     * @return the current selection
     */
    public Map<Viewpoint, Boolean> getSelection() {
        return selection;
    }

    /**
     * Compute the list of items.
     * 
     * @return the new list of items
     */
    private List<Item> computeItemList() {
        return Lists.newArrayList(Iterables.transform(selection.keySet(), new Function<Viewpoint, Item>() {
            @Override
            public Item apply(Viewpoint from) {
                return new Item(from);
            }
        }));
    }

    @Override
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        setTitle(VIEWPOINT_SELECTION_TITLE);
        setMessage(VIEWPOINT_SELECTION_MESSAGE);
        Shell shell = parent.getShell();
        shell.setText(VIEWPOINT_SELECTION_SHELL_TITLE);
        shell.pack();
        return contents;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setLayout(new GridLayout(1, false));

        table = CheckboxTableViewer.newCheckList(composite, SWT.BORDER | SWT.FULL_SELECTION);
        ColumnViewerToolTipSupport.enableFor(table, ToolTip.NO_RECREATE);
        table.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        table.setContentProvider(new TableContentProvider());
        table.setLabelProvider(new TableLabelProvider());
        table.addCheckStateListener(new TableCheckStateListener());
        table.setInput(input);

        for (Item item : input) {
            item.computeState();
        }

        return composite;
    }

    /**
     * Get missing dependencies from the current selection
     * 
     * @return missing dependencies
     */
    private Map<String, Collection<String>> getMissingDependencies() {
        Set<Viewpoint> selected = Maps.filterValues(selection, Predicates.equalTo(Boolean.TRUE)).keySet();

        Multimap<String, String> result = HashMultimap.create();
        for (Viewpoint viewpoint : selected) {
            for (RepresentationExtensionDescription extension : new ViewpointQuery(viewpoint).getAllRepresentationExtensionDescriptions()) {
                String extended = extension.getViewpointURI();
                final Pattern pattern = Pattern.compile(extended);

                // Is there at least one available selected viewpoint URI ?
                if (!Iterables.any(selected, new Predicate<Viewpoint>() {
                    @Override
                    public boolean apply(Viewpoint vp) {
                        Option<URI> uri = new ViewpointQuery(vp).getViewpointURI();
                        if (uri.some()) {
                            Matcher matcher = pattern.matcher(uri.get().toString());
                            return matcher.matches();
                        } else {
                            return false;
                        }
                    }
                })) {
                    result.put(viewpoint.getName(), extended.trim().replaceFirst("^viewpoint:/[^/]+/", "")); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
        return result.asMap();
    }

    /**
     * Compute error message from missing dependencies
     * 
     * @param missingDependencies
     *            missing dependencies
     * @return error message
     */
    private static String getMissingDependenciesErrorMessage(Map<String, Collection<String>> missingDependencies) {
        return Joiner.on("\n").withKeyValueSeparator(" requires: ").join(Maps.transformValues(missingDependencies, new Function<Collection<String>, String>() { //$NON-NLS-1$
            @Override
            public String apply(Collection<String> from) {
                return Joiner.on(", ").join(from); //$NON-NLS-1$
            }
        }));
    }

}
