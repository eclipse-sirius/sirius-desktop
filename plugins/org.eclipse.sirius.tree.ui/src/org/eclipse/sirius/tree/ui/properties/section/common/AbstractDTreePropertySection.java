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
package org.eclipse.sirius.tree.ui.properties.section.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.tools.api.properties.DTablePropertySheetpage;
import org.eclipse.sirius.ui.tools.api.properties.UndoableModelPropertySheetEntry;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An abstract implementation of a property tab section for the property sheet.
 * <BR>
 * This implementation uses a {@link PropertySheetPage} in the section to manage
 * {@link org.eclipse.ui.views.properties.PropertySheetEntry} and
 * {@link org.eclipse.emf.edit.ui.provider.PropertySource}
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public abstract class AbstractDTreePropertySection extends AbstractPropertySection implements IPropertySourceProvider {

    /** The parent property sheet page for this section. */
    protected DTablePropertySheetpage parentPropertySheetPage;

    /**
     * the content property sheet page witch manages SheetEntry for this
     * section.
     */
    protected PropertySheetPage contentPage;

    /**
     * Current selected object or first object in the selection when multiple
     * objects are selected.
     */
    protected EObject eObject;

    /** The list of currently selected objects. */
    protected List<?> eObjectList;

    /**
     * Plugin's
     * {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     * AdapterFactoryLabelProvider}.
     */
    protected AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    /**
     * Model event listener.<BR>
     * Listen the editing domain
     */
    protected ResourceSetListener eventListener = new ResourceSetListenerImpl(getFilter()) {

        /**
         * We want only post-commit events, not pre-commit events.
         */
        @Override
        public boolean isPostcommitOnly() {
            return true;
        }

        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {
            update(event);
        }
    };

    /**
     * The editing domain of the corresponding {@link AbstractDTableEditor
     * editor}
     */
    private TransactionalEditingDomain editingDomain;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        if (aTabbedPropertySheetPage instanceof DTablePropertySheetpage) {
            this.parentPropertySheetPage = (DTablePropertySheetpage) aTabbedPropertySheetPage;
        }

        final Composite composite = getWidgetFactory().createFlatFormComposite(parent);

        //
        // remove the other page.
        contentPage = new PropertySheetPage();
        final UndoableModelPropertySheetEntry root = new UndoableModelPropertySheetEntry(OperationHistoryFactory.getOperationHistory());

        root.setPropertySourceProvider(getPropertySourceProvider());
        contentPage.setRootEntry(root);

        contentPage.createControl(composite);
        final FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        data.bottom = new FormAttachment(100, 0);
        data.height = 100;
        data.width = 100;
        contentPage.getControl().setLayoutData(data);
        // setActionBars(aTabbedPropertySheetPage.getSite().getActionBars());
        // ((SortedPropertySheetPage) page).setSorter(new AirPropertySorter());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        if (!(selection instanceof IStructuredSelection)) {
            if (selection instanceof EObject) {
                eObject = (EObject) selection;
            }
        } else if (((IStructuredSelection) selection).getFirstElement() instanceof EObject) {
            eObject = (EObject) ((IStructuredSelection) selection).getFirstElement();
            eObjectList = ((IStructuredSelection) selection).toList();
        }
        // get the editing domain of the IWorkbenchPart
        final IEditingDomainProvider provider = (IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class);
        if (provider != null) {
            final EditingDomain theEditingDomain = provider.getEditingDomain();
            if (theEditingDomain instanceof TransactionalEditingDomain) {
                setEditingDomain((TransactionalEditingDomain) theEditingDomain);
            }
        }

        contentPage.selectionChanged(part, selection);
    }

    /**
     * Fetches the
     * {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     * AdapterFactoryLabelProvider} adapted to the given object.
     * 
     * @param eObj
     *            The object
     * @return The plugin's
     *         {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     *         AdapterFactoryLabelProvider} .
     */
    protected AdapterFactoryLabelProvider getAdapterFactoryLabelProvider(final EObject eObj) {
        if (adapterFactoryLabelProvider == null) {
            if (getPart() instanceof AbstractDTreeEditor) {
                adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(((AbstractDTreeEditor) getPart()).getAdapterFactory());
            } else {
                return new AdapterFactoryLabelProvider(rescueAdapterFactory());
            }
        }
        return adapterFactoryLabelProvider;
    }

    /**
     * Return a default AdapterFactory.
     * 
     * @return a default AdapterFactory
     */
    protected AdapterFactory rescueAdapterFactory() {
        final List<ComposedAdapterFactory> factories = new ArrayList<ComposedAdapterFactory>();
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        return new ComposedAdapterFactory(factories);
    }

    /**
     * Fetches the plugin's
     * {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     * AdapterFactoryLabelProvider}.
     * 
     * @return The plugin's
     *         {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     *         AdapterFactoryLabelProvider} .
     */
    protected AdapterFactoryLabelProvider getAdapterFactoryLabelProvider() {
        return getAdapterFactoryLabelProvider(null);
    }

    /**
     * Returns the PropertySource provider.
     * 
     * @return The PropertySource provider
     */
    protected IPropertySourceProvider getPropertySourceProvider() {
        return this;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
     */
    @Override
    public void dispose() {
        parentPropertySheetPage = null;
        if (adapterFactoryLabelProvider != null) {
            adapterFactoryLabelProvider.dispose();
            adapterFactoryLabelProvider = null;
        }
        if (contentPage != null) {
            contentPage.dispose();
            contentPage = null;
        }
        if (eObjectList != null) {
            try {
                eObjectList.clear();
            } catch (final UnsupportedOperationException e) {
                // Do nothing
            }
        }
        eObject = null;
        super.dispose();
    }

    @Override
    public void refresh() {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_PROPERTIES_VIEW_SECTION_KEY);
        contentPage.refresh();
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_PROPERTIES_VIEW_SECTION_KEY);
    }

    @Override
    public void aboutToBeHidden() {
        super.aboutToBeHidden();

        final TransactionalEditingDomain theEditingDomain = getEditingDomain();
        if (theEditingDomain != null) {
            theEditingDomain.removeResourceSetListener(getEventListener());
        }
    }

    @Override
    public void aboutToBeShown() {
        super.aboutToBeShown();

        final TransactionalEditingDomain theEditingDomain = getEditingDomain();
        if (theEditingDomain != null) {
            theEditingDomain.addResourceSetListener(getEventListener());
        }
    }

    /**
     * Return the model event listener.
     * 
     * @return Returns the eventListener.
     */
    protected ResourceSetListener getEventListener() {
        return eventListener;
    }

    /**
     * Subclasses overriding this method should remember to override
     * {@link #update(TransactionalEditingDomain, Notification)} as required.
     * The default implementation of
     * {@link #update(TransactionalEditingDomain, Notification)} will only
     * update if the notifier is an <code>EObject</code>.
     * 
     * @return the filter for events used by my <code>eventListener</code>.
     */
    public NotificationFilter getFilter() {
        return NotificationFilter.NOT_TOUCH.and(NotificationFilter.createEventTypeFilter(Notification.SET)).or(NotificationFilter.createEventTypeFilter(Notification.UNSET))
                .and(NotificationFilter.createNotifierTypeFilter(EObject.class));
    }

    /**
     * Update the tree items property sections only once per transaction on post
     * commit event. Subclasses should override this method if they need to
     * update based on non-EObject notifiers.
     * 
     * @param event
     *            the event object
     */
    protected void update(final ResourceSetChangeEvent event) {
        if (parentPropertySheetPage == null || parentPropertySheetPage.isUpdateEnabled()) {
            Iterator<Notification> it = event.getNotifications().iterator();
            boolean shouldUpdate = false;
            while (it.hasNext() && !shouldUpdate) {
                shouldUpdate = it.next().getNotifier() instanceof EObject;
            }
            if (shouldUpdate) {
                if (contentPage != null && contentPage.getControl() != null) {
                    final Control control = contentPage.getControl();
                    EclipseUIUtil.displayAsyncExec(new Runnable() {
                        @Override
                        public void run() {
                            if (!control.isDisposed() && control.isVisible()) {
                                refresh();
                            }
                        }
                    });
                }
            }
        }
    }

    /**
     * Gets the editing domain from my EObject input.
     * 
     * @return my editing domain
     */
    protected TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Sets the editingDomain.
     * 
     * @param editingDomain
     *            The editingDomain to set.
     */
    protected void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /**
     * Override to use all vertical space.<BR>
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#shouldUseExtraSpace()
     */
    @Override
    public boolean shouldUseExtraSpace() {
        return true;
    }

    /**
     * Get the adapter factory.
     * 
     * @param object
     *            the object
     * @return the retrieved adapter factory
     */
    protected AdapterFactory getAdapterFactory(final Object object) {
        AdapterFactory adapterFactory = null;
        if (object != null) {
            if (getPart() instanceof DTreeEditor) {
                adapterFactory = ((DTreeEditor) getPart()).getAdapterFactory();
            } else {
                adapterFactory = TreeUIPlugin.getPlugin().getItemProvidersAdapterFactory();
            }
        }
        return adapterFactory;
    }
}
