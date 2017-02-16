/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.concern.provider.ConcernItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.provider.FilterItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.provider.DiagramItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ActivateFiltersCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.DeactivateFiltersCommand;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.audit.provider.AuditItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.validation.provider.ValidationItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This Property section shows currently activated filters and helps in
 * adding/removing new ones.
 * 
 * 
 * 
 * @author cbrun
 */
public class FiltersPropertySection extends AbstractPropertySection {

    /** The editing domain. */
    protected TransactionalEditingDomain domain;

    /** the choice table. */
    protected Table choiceTable;

    /** teh feature table. */
    protected Table featureTable;

    AdapterFactoryLabelProvider myAdapterFactoryLabelProvider;

    private ComposedAdapterFactory adapterFactory;

    private DDiagram diagram;

    private TableViewer availableElementsTableViewer;

    private TableViewer selectedElementsTableViewer;

    private Button addButton;

    private Button removeButton;

    private final IDoubleClickListener availableElementsTableDoubleClickListener = new IDoubleClickListener() {
        @Override
        public void doubleClick(final DoubleClickEvent event) {
            if (addButton.isEnabled()) {
                addButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    private final IDoubleClickListener selectedElementsTableDoubleClickListener = new IDoubleClickListener() {
        @Override
        public void doubleClick(final DoubleClickEvent event) {
            if (removeButton.isEnabled()) {
                removeButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    /** The edit part to refresh. */
    private EditPart editPart;

    private final Adapter viewpointListener = new AdapterImpl() {

        @Override
        public void notifyChanged(final Notification msg) {
            super.notifyChanged(msg);
            viewpointChanged();
        }

    };

    // TODOCBR comment this.
    /**
     * .
     * 
     * @param newElements
     *            .
     */
    protected void newElementsSelected(final Collection<?> newElements) {
        domain.getCommandStack().execute(new ActivateFiltersCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(newElements, FilterDescription.class))));
    }

    // TODOCBR comment this !
    /**
     * .
     * 
     * @param oldElements
     *            .
     */
    protected void oldElementsRemoved(final Collection<?> oldElements) {
        domain.getCommandStack().execute(new DeactivateFiltersCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(oldElements, FilterDescription.class))));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#aboutToBeShown()
     */
    @Override
    public void aboutToBeShown() {
        availableElementsTableViewer.addDoubleClickListener(availableElementsTableDoubleClickListener);
        selectedElementsTableViewer.addDoubleClickListener(selectedElementsTableDoubleClickListener);

        addButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

            }

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final List<Object> newElements = new ArrayList<Object>();
                final IStructuredSelection selection = (IStructuredSelection) availableElementsTableViewer.getSelection();
                final Iterator<?> i = selection.iterator();
                while (i.hasNext()) {
                    final Object value = i.next();
                    newElements.add(value);
                }
                /*
                 * affect the viewpoint model
                 */
                newElementsSelected(newElements);
            }

        });
        removeButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

            }

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final List<Object> oldElements = new ArrayList<Object>();
                final IStructuredSelection selection = (IStructuredSelection) selectedElementsTableViewer.getSelection();
                Object firstValue = null;

                final Iterator<?> i = selection.iterator();
                while (i.hasNext()) {
                    final Object value = i.next();
                    if (firstValue == null) {
                        firstValue = value;
                    }
                    selectedElementsTableViewer.remove(value);
                    oldElements.add(value);
                }
                oldElementsRemoved(oldElements);

            }

        });
    }

    private void addSemanticListener(final DDiagram viewPoint) {
        viewPoint.eAdapters().add(viewpointListener);
    }

    /**
     * Create the adpater factory.
     * 
     * @return the created factory
     */
    protected ComposedAdapterFactory createAdapterFactory() {
        final List<ComposeableAdapterFactory> factories = new ArrayList<ComposeableAdapterFactory>();
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        fillItemProviderFactories(factories);
        return new ComposedAdapterFactory(factories);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);

        final Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(3, false));

        final Composite choiceComposite = createChoiceComposite(composite);

        choiceTable = getWidgetFactory().createTable(choiceComposite, SWT.MULTI | SWT.BORDER);
        choiceTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        availableElementsTableViewer = new TableViewer(choiceTable, SWT.VIRTUAL);

        final Composite controlButtons = getWidgetFactory().createComposite(composite, SWT.NONE);
        controlButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        final GridLayout controlsButtonGridLayout = new GridLayout();
        controlButtons.setLayout(controlsButtonGridLayout);

        new Label(controlButtons, SWT.NONE);

        addButton = getWidgetFactory().createButton(controlButtons, Messages.FiltersPropertySection_addButtonLabel, SWT.PUSH);
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        removeButton = getWidgetFactory().createButton(controlButtons, Messages.FiltersPropertySection_removeButtonLabel, SWT.PUSH);
        removeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        final Label spaceLabel = new Label(controlButtons, SWT.NONE);
        final GridData spaceLabelGridData = new GridData();
        spaceLabelGridData.verticalSpan = 2;
        spaceLabel.setLayoutData(spaceLabelGridData);

        final Composite featureComposite = createFeatureComposite(composite);

        featureTable = getWidgetFactory().createTable(featureComposite, SWT.MULTI | SWT.BORDER);
        featureTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        selectedElementsTableViewer = new TableViewer(featureTable);

    }

    private IPermissionAuthority getPermissionAuthority() {
        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(diagram);
        return accessor.getPermissionAuthority();
    }

    /**
     * Create the feature section.
     * 
     * @param composite
     *            the parent container.
     * @return the created composite
     */
    protected Composite createFeatureComposite(final Composite composite) {
        final Composite featureComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        featureComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        featureComposite.setLayout(new GridLayout());

        final Label featureLabel = getWidgetFactory().createLabel(featureComposite, Messages.FiltersPropertySection_appliedFiltersLabel, SWT.NONE);
        featureLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        return featureComposite;
    }

    /**
     * Create the choice section.
     * 
     * @param composite
     *            the parent container
     * @return teh created composite
     */
    protected Composite createChoiceComposite(final Composite composite) {
        final Composite choiceComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        choiceComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        choiceComposite.setLayout(new GridLayout());

        final Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, Messages.FiltersPropertySection_availableFiltersLabel, SWT.NONE);
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        return choiceComposite;
    }

    /**
     * fill the item provider factory list.
     * 
     * @param factories
     *            the list to fill
     */
    protected void fillItemProviderFactories(final List<ComposeableAdapterFactory> factories) {
        factories.add(new ViewpointItemProviderAdapterFactory());
        factories.add(new DiagramItemProviderAdapterFactory());

        factories.add(new org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory());
        factories.add(new org.eclipse.sirius.diagram.description.provider.DescriptionItemProviderAdapterFactory());

        factories.add(new org.eclipse.sirius.viewpoint.description.style.provider.StyleItemProviderAdapterFactory());
        factories.add(new org.eclipse.sirius.diagram.description.style.provider.StyleItemProviderAdapterFactory());

        factories.add(new org.eclipse.sirius.viewpoint.description.tool.provider.ToolItemProviderAdapterFactory());
        factories.add(new org.eclipse.sirius.diagram.description.tool.provider.ToolItemProviderAdapterFactory());

        factories.add(new AuditItemProviderAdapterFactory());
        factories.add(new ConcernItemProviderAdapterFactory());
        factories.add(new FilterItemProviderAdapterFactory());
        factories.add(new ValidationItemProviderAdapterFactory());

        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
    }

    /**
     * Get all applied elements.
     * 
     * @return the applied elements.
     */
    protected Collection<?> getAppliedElements() {
        final Collection<FilterDescription> result = new HashSet<FilterDescription>();
        if (diagram != null) {
            result.addAll(diagram.getActivatedFilters());
        }
        return result;
    }

    /**
     * Get all available elements.
     * 
     * @return the available elements
     */
    protected Collection<?> getAvailableElements() {
        final Collection<FilterDescription> result = new HashSet<FilterDescription>();

        if (diagram != null && diagram.getDescription() != null) {
            final DiagramDescription desc = diagram.getDescription();
            result.addAll(desc.getFilters());
        }
        result.removeAll(getAppliedElements());
        return result;
    }

    protected DDiagram getDiagram() {
        return diagram;
    }

    protected EditPart getEditPart() {
        return editPart;
    }

    private ItemProvider getItemProvider(final Collection<?> choices) {
        return new ItemProvider(getItemProvidersAdapterFactory(), choices);
    }

    private AdapterFactory getItemProvidersAdapterFactory() {
        if (adapterFactory == null) {
            adapterFactory = createAdapterFactory();
        }
        return adapterFactory;
    }

    private AdapterFactoryLabelProvider getLabelProvider() {
        if (myAdapterFactoryLabelProvider == null) {
            myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(getItemProvidersAdapterFactory());
        }
        return myAdapterFactoryLabelProvider;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    @Override
    public void refresh() {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                /*
                 * Set content/label provider
                 */
                if (!availableElementsTableViewer.getTable().isDisposed()) {
                    availableElementsTableViewer.setLabelProvider(getLabelProvider());
                    selectedElementsTableViewer.setLabelProvider(getLabelProvider());
                    availableElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(getItemProvidersAdapterFactory()));
                    selectedElementsTableViewer.setContentProvider(new AdapterFactoryContentProvider(getItemProvidersAdapterFactory()));

                    availableElementsTableViewer.setInput(getItemProvider(getAvailableElements()));
                    selectedElementsTableViewer.setInput(getItemProvider(getAppliedElements()));
                }
            }

        });
    }

    private void removeSemanticListener(final DDiagram dia) {
        dia.eAdapters().remove(viewpointListener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        if (selection instanceof IStructuredSelection) {
            final Object input = ((IStructuredSelection) selection).getFirstElement();
            if (input instanceof DDiagramEditPart) {
                final EObject vp = ((DDiagramEditPart) input).resolveSemanticElement();
                editPart = ((DDiagramEditPart) input).getRoot();
                if (vp instanceof DDiagram) {
                    setSirius((DDiagram) vp);
                    this.domain = ((DDiagramEditPart) input).getEditingDomain();
                }
            }
        }
        refresh();
    }

    private void setSirius(final DDiagram newDia) {
        if (!getPermissionAuthority().canEditInstance(newDia)) {
            featureTable.setEnabled(false);
            addButton.setEnabled(false);
            removeButton.setEnabled(false);
            choiceTable.setEnabled(false);
        }
        if (diagram == newDia) {
            return;
        }

        if (diagram != null) {
            removeSemanticListener(diagram);
        }
        diagram = newDia;
        refresh();

        if (diagram != null) {
            addSemanticListener(diagram);
        }

    }

    private void viewpointChanged() {
        refresh();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (getDiagram() != null) {
            removeSemanticListener(getDiagram());
        }
    }

}
