/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.tests.sample.docbook.parts.impl;

// Start of user code for imports
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.ExamplePropertiesEditionPart;
import org.eclipse.sirius.tests.sample.docbook.providers.DocbookMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

// End of user code

/**
 *
 *
 */
public class ExamplePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ExamplePropertiesEditionPart {

    protected ReferencesTable para;

    protected List<ViewerFilter> paraBusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> paraFilters = new ArrayList<ViewerFilter>();

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public ExamplePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
     *      createFigure(org.eclipse.swt.widgets.Composite)
     *
     */
    @Override
    public Composite createFigure(final Composite parent) {
        view = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        view.setLayout(layout);
        createControls(view);
        return view;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
     *      createControls(org.eclipse.swt.widgets.Composite)
     *
     */
    @Override
    public void createControls(Composite view) {
        CompositionSequence exampleStep = new BindingCompositionSequence(propertiesEditionComponent);
        exampleStep.addStep(DocbookViewsRepository.Example.Properties.class).addStep(DocbookViewsRepository.Example.Properties.para);

        composer = new PartComposer(exampleStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.Example.Properties.class) {
                    return createPropertiesGroup(parent);
                }
                if (key == DocbookViewsRepository.Example.Properties.para) {
                    return createParaAdvancedTableComposition(parent);
                }
                return parent;
            }
        };
        composer.compose(view);
    }

    /**
     *
     */
    protected Composite createPropertiesGroup(Composite parent) {
        Group propertiesGroup = new Group(parent, SWT.NONE);
        propertiesGroup.setText(DocbookMessages.ExamplePropertiesEditionPart_PropertiesGroupLabel);
        GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
        propertiesGroupData.horizontalSpan = 3;
        propertiesGroup.setLayoutData(propertiesGroupData);
        GridLayout propertiesGroupLayout = new GridLayout();
        propertiesGroupLayout.numColumns = 3;
        propertiesGroup.setLayout(propertiesGroupLayout);
        return propertiesGroup;
    }

    /**
     * @param container
     *
     */
    protected Composite createParaAdvancedTableComposition(Composite parent) {
        this.para = new ReferencesTable(getDescription(DocbookViewsRepository.Example.Properties.para, DocbookMessages.ExamplePropertiesEditionPart_ParaLabel), new ReferencesTableListener() {
            @Override
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExamplePropertiesEditionPartImpl.this, DocbookViewsRepository.Example.Properties.para,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                para.refresh();
            }

            @Override
            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExamplePropertiesEditionPartImpl.this, DocbookViewsRepository.Example.Properties.para,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                para.refresh();
            }

            @Override
            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExamplePropertiesEditionPartImpl.this, DocbookViewsRepository.Example.Properties.para,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                para.refresh();
            }

            @Override
            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExamplePropertiesEditionPartImpl.this, DocbookViewsRepository.Example.Properties.para,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                para.refresh();
            }

            @Override
            public void navigateTo(EObject element) {
            }
        });
        for (ViewerFilter filter : this.paraFilters) {
            this.para.addFilter(filter);
        }
        this.para.setHelpText(propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Example.Properties.para, DocbookViewsRepository.SWT_KIND));
        this.para.createControls(parent);
        this.para.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExamplePropertiesEditionPartImpl.this, DocbookViewsRepository.Example.Properties.para,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData paraData = new GridData(GridData.FILL_HORIZONTAL);
        paraData.horizontalSpan = 3;
        this.para.setLayoutData(paraData);
        this.para.setLowerBound(0);
        this.para.setUpperBound(-1);
        para.setID(DocbookViewsRepository.Example.Properties.para);
        para.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createParaAdvancedTableComposition

        // End of user code
        return parent;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     *
     */
    @Override
    public void firePropertiesChanged(IPropertiesEditionEvent event) {
        // Start of user code for tab synchronization

        // End of user code
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ExamplePropertiesEditionPart#initPara(EObject
     *      current, EReference containingFeature, EReference feature)
     */
    @Override
    public void initPara(ReferencesTableSettings settings) {
        if (current.eResource() != null && current.eResource().getResourceSet() != null) {
            this.resourceSet = current.eResource().getResourceSet();
        }
        ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
        para.setContentProvider(contentProvider);
        para.setInput(settings);
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Example.Properties.para);
        if (eefElementEditorReadOnlyState && para.isEnabled()) {
            para.setEnabled(false);
            para.setToolTipText(DocbookMessages.Example_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !para.isEnabled()) {
            para.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ExamplePropertiesEditionPart#updatePara()
     *
     */
    @Override
    public void updatePara() {
        para.refresh();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ExamplePropertiesEditionPart#addFilterPara(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addFilterToPara(ViewerFilter filter) {
        paraFilters.add(filter);
        if (this.para != null) {
            this.para.addFilter(filter);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ExamplePropertiesEditionPart#addBusinessFilterPara(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addBusinessFilterToPara(ViewerFilter filter) {
        paraBusinessFilters.add(filter);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ExamplePropertiesEditionPart#isContainedInParaTable(EObject
     *      element)
     *
     */
    @Override
    public boolean isContainedInParaTable(EObject element) {
        return ((ReferencesTableSettings) para.getInput()).contains(element);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
     *
     */
    @Override
    public String getTitle() {
        return DocbookMessages.Example_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
