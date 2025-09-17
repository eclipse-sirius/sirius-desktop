/******************************************************************************
 * Copyright (c) 2003, 2025 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - adaptation 
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.api.properties;

import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.gmf.runtime.emf.ui.properties.sections.UndoableModelPropertySheetEntry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetSorter;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An abstract property section that sorts the viewer by the order returned by
 * {@link IPropertySource#getPropertyDescriptors()}.
 * 
 * @author ymortier
 */
public abstract class AbstractPropertySection extends AbstractModelerPropertySection {

    /**
     * the property sheet page for this section.
     */
    protected PropertySheetPage page;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        final Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data = null;

        final String tableLabelStr = getTableLabel();
        CLabel tableLabel = null;
        if (tableLabelStr != null && tableLabelStr.length() > 0) {
            tableLabel = getWidgetFactory().createCLabel(composite, tableLabelStr);
            data = new FormData();
            data.left = new FormAttachment(0, 0);
            data.top = new FormAttachment(0, 0);
            tableLabel.setLayoutData(data);
        }

        //
        // remove the other page.
        page = new SortedPropertySheetPage();
        final UndoableModelPropertySheetEntry root = new UndoableModelPropertySheetEntry(OperationHistoryFactory.getOperationHistory());

        root.setPropertySourceProvider(getPropertySourceProvider());
        page.setRootEntry(root);

        page.createControl(composite);
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        if (tableLabel == null) {
            data.top = new FormAttachment(0, 0);
        } else {
            data.top = new FormAttachment(tableLabel, 0, SWT.BOTTOM);
        }
        data.bottom = new FormAttachment(100, 0);
        data.height = 100;
        data.width = 100;
        page.getControl().setLayoutData(data);

        setActionBars(aTabbedPropertySheetPage.getSite().getActionBars());
        ((SortedPropertySheetPage) page).setSorter(new AirPropertySorter());
    }

    private final class AirPropertySorter extends PropertySheetSorter {

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.views.properties.PropertySheetSorter#compare(org.eclipse.ui.views.properties.IPropertySheetEntry,
         *      org.eclipse.ui.views.properties.IPropertySheetEntry)
         */
        @Override
        public int compare(final IPropertySheetEntry entryA, final IPropertySheetEntry entryB) {
            return super.compare(entryA, entryB);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.views.properties.PropertySheetSorter#compareCategories(java.lang.String,
         *      java.lang.String)
         */
        @Override
        public int compareCategories(final String categoryA, final String categoryB) {
            int result = Integer.MIN_VALUE;
            IPropertySource propertySource = null;
            //
            // Gets the propertySource.
            if (getPropertySourceProvider() != null && getSelectedObject() != null) {
                propertySource = getPropertySourceProvider().getPropertySource(getSelectedObject());
            }
            if (propertySource == null) {
                result = super.compareCategories(categoryA, categoryB);
            }
            //
            // Special cases.
            if (categoryA == null) {
                result = categoryB == null ? 0 : 1;
            }
            if (categoryB == null) {
                result = categoryA == null ? 0 : -1;
            }
            if (categoryA != null && categoryA.equals(categoryB)) {
                result = 0;
            }
            //
            // general cases.
            if (propertySource != null) {
                final IPropertyDescriptor[] descriptors = propertySource.getPropertyDescriptors();
                for (int i = 0; i < descriptors.length && result == Integer.MIN_VALUE; i++) {
                    if (descriptors[i].getCategory() != null && descriptors[i].getCategory().equals(categoryA)) {
                        result = -1;
                    } else if (descriptors[i].getCategory() != null && descriptors[i].getCategory().equals(categoryB)) {
                        result = 1;
                    }
                }
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.views.properties.PropertySheetSorter#sort(org.eclipse.ui.views.properties.IPropertySheetEntry[])
         */
        @Override
        public void sort(final IPropertySheetEntry[] entries) {
            super.sort(entries);
        }

    }

    /**
     * Sets and prepares the actionBars for this section.
     * 
     * @param actionBars
     *            the action bars for this page
     * @see org.eclipse.gmf.runtime.common.ui.properties.TabbedPropertySheetPage#setActionBars(org.eclipse.ui.IActionBars)
     */
    public void setActionBars(final IActionBars actionBars) {

        actionBars.getMenuManager().removeAll();
        actionBars.getToolBarManager().removeAll();
        actionBars.getStatusLineManager().removeAll();

        page.makeContributions(actionBars.getMenuManager(), actionBars.getToolBarManager(), actionBars.getStatusLineManager());

        actionBars.getToolBarManager().update(true);

    }

    /**
     * Returns the PropertySource provider. The default implementation returns
     * static adapter factory for the properties services. If the extending
     * class needs to use a different provider then this method has to be
     * overwriten.
     * 
     * @return The PropertySource provider
     */
    protected IPropertySourceProvider getPropertySourceProvider() {
        return propertiesProvider;
    }

    /**
     * Returns the label for the table. The default implementation returns null,
     * that is, there is no label.
     * 
     * @return The label for the table
     */
    protected String getTableLabel() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        final IEditingDomainProvider provider = part.getAdapter(IEditingDomainProvider.class);
        if (provider != null) {
            final EditingDomain theEditingDomain = provider.getEditingDomain();
            if (theEditingDomain instanceof TransactionalEditingDomain) {
                setEditingDomain((TransactionalEditingDomain) theEditingDomain);
            }
        }

        // Set the eObject for the section, too. The workbench part may not
        // adapt to IEditingDomainProvider, in which case the selected EObject
        // will be used to derive the editing domain.
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
            final Object firstElement = ((IStructuredSelection) selection).getFirstElement();

            if (firstElement != null) {
                final EObject adapted = unwrap(firstElement);

                if (adapted != null) {
                    setEObject(adapted);
                }
            }
        }

        try {
            page.selectionChanged(part, selection);
            // CHECKSTYLE:OFF
        } catch (RuntimeException e) {
            // CHECKSTYLE:ON
            // can occur if the page references semantic element that have been
            // deleted on CDO
        }
    }

    @Override
    public void dispose() {
        if (page != null) {
            page.dispose();
            page = null;
        }
        super.dispose();
    }

    @Override
    public void refresh() {
        try {
            page.refresh();
            // CHECKSTYLE:OFF
        } catch (RuntimeException e) {
            // CHECKSTYLE:ON
            // can occur if the page references semantic element that have been
            // deleted on CDO
        }
    }

    @Override
    public boolean shouldUseExtraSpace() {
        return true;
    }

    /**
     * Update if nessesary, upon receiving the model event.
     * 
     * @see #aboutToBeShown()
     * @see #aboutToBeHidden()
     * @param notification
     *            - even notification
     * @param element
     *            - element that has changed
     */
    @Override
    public void update(final Notification notification, final EObject element) {
        if (!isDisposed()) {
            postUpdateRequest(new Runnable() {

                @Override
                public void run() {
                    try {
                        if (!isDisposed() && !isNotifierDeleted(notification)) {
                            refresh();
                        }
                    } catch (IllegalStateException e) {
                        if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                            // Nothing to log here, this can happen if the resource is not accessible anymore (distant
                            // resource).
                        } else {
                            throw e;
                        }
                    }
                }
            });
        }
    }

    @Override
    public NotificationFilter getFilter() {
        return NotificationFilter.createEventTypeFilter(Notification.SET).or(NotificationFilter.createEventTypeFilter(Notification.UNSET))
                .or(NotificationFilter.createEventTypeFilter(Notification.ADD)).or(NotificationFilter.createEventTypeFilter(Notification.ADD_MANY))
                .or(NotificationFilter.createEventTypeFilter(Notification.REMOVE)).or(NotificationFilter.createEventTypeFilter(Notification.REMOVE_MANY))
                .and(NotificationFilter.createNotifierTypeFilter(EObject.class));
    }

    @Override
    protected boolean addToEObjectList(final Object object) {
        /* not implemented */
        return true;
    }

    /**
     * Returns the selected object.
     * 
     * @return the selected object.
     */
    public abstract Object getSelectedObject();

    /**
     * A property sheet page that allows to change the sorter of the table
     * viewer.
     * 
     * @author ymortier
     */
    private static final class SortedPropertySheetPage extends PropertySheetPage {

        @Override
        public void setSorter(final PropertySheetSorter sorter) {
            super.setSorter(sorter);
        }
    }

}
