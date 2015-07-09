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
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart;
import org.eclipse.sirius.tests.sample.docbook.providers.DocbookMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

// End of user code

/**
 *
 *
 */
public class ItemizedListPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ItemizedListPropertiesEditionPart {

    protected Text mark;

    protected ReferencesTable listitem;

    protected List<ViewerFilter> listitemBusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> listitemFilters = new ArrayList<ViewerFilter>();

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public ItemizedListPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence itemizedListStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = itemizedListStep.addStep(DocbookViewsRepository.ItemizedList.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.ItemizedList.Properties.mark);
        propertiesStep.addStep(DocbookViewsRepository.ItemizedList.Properties.listitem);

        composer = new PartComposer(itemizedListStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.ItemizedList.Properties.class) {
                    return createPropertiesGroup(parent);
                }
                if (key == DocbookViewsRepository.ItemizedList.Properties.mark) {
                    return createMarkText(parent);
                }
                if (key == DocbookViewsRepository.ItemizedList.Properties.listitem) {
                    return createListitemAdvancedTableComposition(parent);
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
        propertiesGroup.setText(DocbookMessages.ItemizedListPropertiesEditionPart_PropertiesGroupLabel);
        GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
        propertiesGroupData.horizontalSpan = 3;
        propertiesGroup.setLayoutData(propertiesGroupData);
        GridLayout propertiesGroupLayout = new GridLayout();
        propertiesGroupLayout.numColumns = 3;
        propertiesGroup.setLayout(propertiesGroupLayout);
        return propertiesGroup;
    }

    protected Composite createMarkText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.ItemizedList.Properties.mark, DocbookMessages.ItemizedListPropertiesEditionPart_MarkLabel);
        mark = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData markData = new GridData(GridData.FILL_HORIZONTAL);
        mark.setLayoutData(markData);
        mark.addFocusListener(new FocusAdapter() {

            /**
             * {@inheritDoc}
             *
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ItemizedListPropertiesEditionPartImpl.this, DocbookViewsRepository.ItemizedList.Properties.mark,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, mark.getText()));
                }
            }

        });
        mark.addKeyListener(new KeyAdapter() {

            /**
             * {@inheritDoc}
             *
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ItemizedListPropertiesEditionPartImpl.this, DocbookViewsRepository.ItemizedList.Properties.mark,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, mark.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(mark, DocbookViewsRepository.ItemizedList.Properties.mark);
        EditingUtils.setEEFtype(mark, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.ItemizedList.Properties.mark, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createMarkText

        // End of user code
        return parent;
    }

    /**
     * @param container
     *
     */
    protected Composite createListitemAdvancedTableComposition(Composite parent) {
        this.listitem = new ReferencesTable(getDescription(DocbookViewsRepository.ItemizedList.Properties.listitem, DocbookMessages.ItemizedListPropertiesEditionPart_ListitemLabel),
                new ReferencesTableListener() {
            @Override
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ItemizedListPropertiesEditionPartImpl.this,
                        DocbookViewsRepository.ItemizedList.Properties.listitem, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                listitem.refresh();
            }

            @Override
            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ItemizedListPropertiesEditionPartImpl.this,
                        DocbookViewsRepository.ItemizedList.Properties.listitem, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                listitem.refresh();
            }

            @Override
            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ItemizedListPropertiesEditionPartImpl.this,
                        DocbookViewsRepository.ItemizedList.Properties.listitem, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                listitem.refresh();
            }

            @Override
            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ItemizedListPropertiesEditionPartImpl.this,
                        DocbookViewsRepository.ItemizedList.Properties.listitem, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                listitem.refresh();
            }

            @Override
            public void navigateTo(EObject element) {
            }
        });
        for (ViewerFilter filter : this.listitemFilters) {
            this.listitem.addFilter(filter);
        }
        this.listitem.setHelpText(propertiesEditionComponent.getHelpContent(DocbookViewsRepository.ItemizedList.Properties.listitem, DocbookViewsRepository.SWT_KIND));
        this.listitem.createControls(parent);
        this.listitem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ItemizedListPropertiesEditionPartImpl.this, DocbookViewsRepository.ItemizedList.Properties.listitem,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData listitemData = new GridData(GridData.FILL_HORIZONTAL);
        listitemData.horizontalSpan = 3;
        this.listitem.setLayoutData(listitemData);
        this.listitem.setLowerBound(0);
        this.listitem.setUpperBound(-1);
        listitem.setID(DocbookViewsRepository.ItemizedList.Properties.listitem);
        listitem.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createListitemAdvancedTableComposition

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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart#getMark()
     *
     */
    @Override
    public String getMark() {
        return mark.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart#setMark(String
     *      newValue)
     *
     */
    @Override
    public void setMark(String newValue) {
        if (newValue != null) {
            mark.setText(newValue);
        } else {
            mark.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.ItemizedList.Properties.mark);
        if (eefElementEditorReadOnlyState && mark.isEnabled()) {
            mark.setEnabled(false);
            mark.setToolTipText(DocbookMessages.ItemizedList_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !mark.isEnabled()) {
            mark.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart#initListitem(EObject
     *      current, EReference containingFeature, EReference feature)
     */
    @Override
    public void initListitem(ReferencesTableSettings settings) {
        if (current.eResource() != null && current.eResource().getResourceSet() != null) {
            this.resourceSet = current.eResource().getResourceSet();
        }
        ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
        listitem.setContentProvider(contentProvider);
        listitem.setInput(settings);
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.ItemizedList.Properties.listitem);
        if (eefElementEditorReadOnlyState && listitem.isEnabled()) {
            listitem.setEnabled(false);
            listitem.setToolTipText(DocbookMessages.ItemizedList_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !listitem.isEnabled()) {
            listitem.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart#updateListitem()
     *
     */
    @Override
    public void updateListitem() {
        listitem.refresh();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart#addFilterListitem(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addFilterToListitem(ViewerFilter filter) {
        listitemFilters.add(filter);
        if (this.listitem != null) {
            this.listitem.addFilter(filter);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart#addBusinessFilterListitem(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addBusinessFilterToListitem(ViewerFilter filter) {
        listitemBusinessFilters.add(filter);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart#isContainedInListitemTable(EObject
     *      element)
     *
     */
    @Override
    public boolean isContainedInListitemTable(EObject element) {
        return ((ReferencesTableSettings) listitem.getInput()).contains(element);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
     *
     */
    @Override
    public String getTitle() {
        return DocbookMessages.ItemizedList_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
