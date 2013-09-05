/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.description.audit.provider.AuditItemProviderAdapterFactory;
import org.eclipse.sirius.description.filter.provider.FilterItemProviderAdapterFactory;
import org.eclipse.sirius.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.description.validation.provider.ValidationItemProviderAdapterFactory;
import org.eclipse.sirius.provider.SiriusItemProviderAdapterFactory;

/**
 * Wizard page to select the representations you want to export.
 * 
 * @author cbrun
 */
public class SelectExtractedRepresentationsPage extends WizardPage {
    /**
     * SWT table for the selected elements.
     */
    protected Table choiceTable;

    /**
     * Table for the available elements.
     */
    protected Table availableTable;

    /**
     * Viewer for the available elements.
     */
    private TableViewer availableElementsTableViewer;

    /**
     * Viewer for the selected elements.
     */
    private TableViewer selectedElementsTableViewer;

    private Button addButton;

    private Button removeButton;

    private final Set<DRepresentation> selectedRepresentations = new HashSet<DRepresentation>();

    private final Collection<DRepresentation> availableRepresentations;

    private AdapterFactoryLabelProvider labelProvider;

    private AdapterFactoryContentProvider contentProvider;

    private ComposedAdapterFactory adapterFactory;

    private final IDoubleClickListener availableElementsTableDoubleClickListener = new IDoubleClickListener() {
        public void doubleClick(final DoubleClickEvent event) {
            if (addButton.isEnabled()) {
                addButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    private final IDoubleClickListener selectedElementsTableDoubleClickListener = new IDoubleClickListener() {
        public void doubleClick(final DoubleClickEvent event) {
            if (removeButton.isEnabled()) {
                removeButton.notifyListeners(SWT.Selection, null);
            }
        }
    };

    /**
     * Create a new page to select diagrams.
     * 
     * @param pageName
     *            name of the page.
     * @param availableRepresentations
     *            representations to pick from.
     */
    public SelectExtractedRepresentationsPage(final String pageName, final Collection<DRepresentation> availableRepresentations) {
        super(pageName);
        this.availableRepresentations = availableRepresentations;
        setDescription("Please select the representations you want to externalize in a new File");
    }

    /**
     * Set default selected representation.
     * 
     * @param defaultRepresentations
     *            representations to select when opening the page.
     */
    public void setDefaultRepresentations(final Set<DRepresentation> defaultRepresentations) {
    }

    /**
     * Get the selected diagrams.
     * 
     * @return the diagrams the user selected.
     */
    public Set<DRepresentation> getSelectedRepresentations() {
        return selectedRepresentations;
    }

    private ItemProvider getItemProvider(final Collection<?> choices) {
        return new ItemProvider(getItemProvidersAdapterFactory(), choices);
    }

    /**
     * Refresh the page.
     */
    protected void refresh() {
        setPageComplete(true);
        selectedElementsTableViewer.setInput(getItemProvider(selectedRepresentations));
        if (selectedRepresentations.size() == 0) {
            setPageComplete(false);
        }

    }

    private AdapterFactory getItemProvidersAdapterFactory() {
        if (adapterFactory == null) {
            adapterFactory = createAdapterFactory();
        }
        return adapterFactory;
    }

    /**
     * Create the adapter factory.
     * 
     * @return the created factory
     */
    protected ComposedAdapterFactory createAdapterFactory() {
        final List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        fillItemProviderFactories(factories);
        return new ComposedAdapterFactory(factories);
    }

    /**
     * fill the item providers factories.
     * 
     * @param factories
     *            the factories list to fill
     */
    protected void fillItemProviderFactories(final List<AdapterFactory> factories) {
        factories.add(new SiriusItemProviderAdapterFactory());
        factories.add(new DescriptionItemProviderAdapterFactory());
        factories.add(new ToolItemProviderAdapterFactory());
        factories.add(new FilterItemProviderAdapterFactory());
        factories.add(new ValidationItemProviderAdapterFactory());
        factories.add(new AuditItemProviderAdapterFactory());
        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
    }

    private AdapterFactoryLabelProvider getLabelProvider() {
        if (labelProvider == null) {
            labelProvider = new AdapterFactoryLabelProvider(getItemProvidersAdapterFactory());
        }
        return labelProvider;
    }

    private AdapterFactoryContentProvider getContentProvider() {
        if (contentProvider == null) {
            contentProvider = new AdapterFactoryContentProvider(getItemProvidersAdapterFactory());
        }
        return contentProvider;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        final Composite composite = new Composite(parent, SWT.NONE);
        setControl(composite);

        composite.setLayout(new GridLayout(3, false));

        final Composite choiceComposite = createAvailableComposite(composite);

        choiceTable = new Table(choiceComposite, SWT.MULTI | SWT.BORDER);
        choiceTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        availableElementsTableViewer = new TableViewer(choiceTable);

        final Composite controlButtons = new Composite(composite, SWT.NONE);
        controlButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        final GridLayout controlsButtonGridLayout = new GridLayout();
        controlButtons.setLayout(controlsButtonGridLayout);

        new Label(controlButtons, SWT.NONE);

        addButton = new Button(controlButtons, SWT.PUSH);
        addButton.setText("Add >");
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        removeButton = new Button(controlButtons, SWT.PUSH);
        removeButton.setText("< Remove");
        removeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

        final Label spaceLabel = new Label(controlButtons, SWT.NONE);
        final GridData spaceLabelGridData = new GridData();
        spaceLabelGridData.verticalSpan = 2;
        spaceLabel.setLayoutData(spaceLabelGridData);

        final Composite featureComposite = createSelectedComposite(composite);

        availableTable = new Table(featureComposite, SWT.MULTI | SWT.BORDER);
        availableTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        selectedElementsTableViewer = new TableViewer(availableTable);

        addClickListeners();
        setProviders();
        availableElementsTableViewer.setInput(getItemProvider(availableRepresentations));
        refresh();
    }

    private void setProviders() {
        selectedElementsTableViewer.setLabelProvider(getLabelProvider());
        selectedElementsTableViewer.setContentProvider(getContentProvider());
        availableElementsTableViewer.setLabelProvider(getLabelProvider());
        availableElementsTableViewer.setContentProvider(getContentProvider());

    }

    private Composite createSelectedComposite(final Composite composite) {
        final Composite selectedComposite = new Composite(composite, SWT.NONE);
        selectedComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        selectedComposite.setLayout(new GridLayout());
        final Label choiceLabel = new Label(selectedComposite, SWT.NONE);
        choiceLabel.setText("Selected Views");
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        return selectedComposite;
    }

    private Composite createAvailableComposite(final Composite composite) {
        final Composite availableComposite = new Composite(composite, SWT.NONE);
        availableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        availableComposite.setLayout(new GridLayout());
        final Label choiceLabel = new Label(availableComposite, SWT.NONE);
        choiceLabel.setText("Available Views");
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        return availableComposite;
    }

    private void addClickListeners() {
        availableElementsTableViewer.addDoubleClickListener(availableElementsTableDoubleClickListener);
        selectedElementsTableViewer.addDoubleClickListener(selectedElementsTableDoubleClickListener);

        addButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {

            }

            public void widgetSelected(final SelectionEvent e) {
                final List<DDiagram> newElements = new ArrayList<DDiagram>();
                final IStructuredSelection selection = (IStructuredSelection) availableElementsTableViewer.getSelection();
                final Iterator<?> it = selection.iterator();
                while (it.hasNext()) {
                    final Object value = it.next();
                    newElements.add((DDiagram) value);
                }
                selectedRepresentations.addAll(newElements);
                refresh();
            }

        });
        removeButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(final SelectionEvent e) {

            }

            public void widgetSelected(final SelectionEvent e) {
                final List<DDiagram> newElements = new ArrayList<DDiagram>();
                final IStructuredSelection selection = (IStructuredSelection) selectedElementsTableViewer.getSelection();
                final Iterator<?> it = selection.iterator();
                while (it.hasNext()) {
                    final Object value = it.next();
                    newElements.add((DDiagram) value);
                }
                selectedRepresentations.removeAll(newElements);
                refresh();
            }

        });
    }
}
