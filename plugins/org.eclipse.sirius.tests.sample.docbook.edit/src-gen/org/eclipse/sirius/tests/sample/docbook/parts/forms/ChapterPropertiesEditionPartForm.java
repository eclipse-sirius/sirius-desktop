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
package org.eclipse.sirius.tests.sample.docbook.parts.forms;

// Start of user code for imports
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.part.impl.SectionPropertiesEditingPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.ISection;

// End of user code

/**
 *
 *
 */
public class ChapterPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ChapterPropertiesEditionPart {

    protected ReferencesTable para;

    protected List<ViewerFilter> paraBusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> paraFilters = new ArrayList<ViewerFilter>();

    protected ReferencesTable sect1;

    protected List<ViewerFilter> sect1BusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> sect1Filters = new ArrayList<ViewerFilter>();

    protected Text id;

    /**
     * For {@link ISection} use only.
     */
    public ChapterPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public ChapterPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
        super(editionComponent);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
     *      createFigure(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     *
     */
    @Override
    public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
        ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
        Form form = scrolledForm.getForm();
        view = form.getBody();
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        view.setLayout(layout);
        createControls(widgetFactory, view);
        return scrolledForm;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
     *      createControls(org.eclipse.ui.forms.widgets.FormToolkit,
     *      org.eclipse.swt.widgets.Composite)
     *
     */
    @Override
    public void createControls(final FormToolkit widgetFactory, Composite view) {
        CompositionSequence chapterStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = chapterStep.addStep(DocbookViewsRepository.Chapter.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.Chapter.Properties.para);
        propertiesStep.addStep(DocbookViewsRepository.Chapter.Properties.sect1);
        propertiesStep.addStep(DocbookViewsRepository.Chapter.Properties.id);

        composer = new PartComposer(chapterStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.Chapter.Properties.class) {
                    return createPropertiesGroup(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Chapter.Properties.para) {
                    return createParaTableComposition(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Chapter.Properties.sect1) {
                    return createSect1TableComposition(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Chapter.Properties.id) {
                    return createIdText(widgetFactory, parent);
                }
                return parent;
            }
        };
        composer.compose(view);
    }

    /**
     *
     */
    protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
        Section propertiesSection = widgetFactory.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
        propertiesSection.setText(DocbookMessages.ChapterPropertiesEditionPart_PropertiesGroupLabel);
        GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
        propertiesSectionData.horizontalSpan = 3;
        propertiesSection.setLayoutData(propertiesSectionData);
        Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
        GridLayout propertiesGroupLayout = new GridLayout();
        propertiesGroupLayout.numColumns = 3;
        propertiesGroup.setLayout(propertiesGroupLayout);
        propertiesSection.setClient(propertiesGroup);
        return propertiesGroup;
    }

    /**
     * @param container
     *
     */
    protected Composite createParaTableComposition(FormToolkit widgetFactory, Composite parent) {
        this.para = new ReferencesTable(getDescription(DocbookViewsRepository.Chapter.Properties.para, DocbookMessages.ChapterPropertiesEditionPart_ParaLabel), new ReferencesTableListener() {
            @Override
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.para,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                para.refresh();
            }

            @Override
            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.para,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                para.refresh();
            }

            @Override
            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.para,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                para.refresh();
            }

            @Override
            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.para,
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
        this.para.setHelpText(propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Chapter.Properties.para, DocbookViewsRepository.FORM_KIND));
        this.para.createControls(parent, widgetFactory);
        this.para.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.para,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData paraData = new GridData(GridData.FILL_HORIZONTAL);
        paraData.horizontalSpan = 3;
        this.para.setLayoutData(paraData);
        this.para.setLowerBound(0);
        this.para.setUpperBound(-1);
        para.setID(DocbookViewsRepository.Chapter.Properties.para);
        para.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createParaTableComposition

        // End of user code
        return parent;
    }

    /**
     * @param container
     *
     */
    protected Composite createSect1TableComposition(FormToolkit widgetFactory, Composite parent) {
        this.sect1 = new ReferencesTable(getDescription(DocbookViewsRepository.Chapter.Properties.sect1, DocbookMessages.ChapterPropertiesEditionPart_Sect1Label), new ReferencesTableListener() {
            @Override
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.sect1,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                sect1.refresh();
            }

            @Override
            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.sect1,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                sect1.refresh();
            }

            @Override
            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.sect1,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                sect1.refresh();
            }

            @Override
            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.sect1,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                sect1.refresh();
            }

            @Override
            public void navigateTo(EObject element) {
            }
        });
        for (ViewerFilter filter : this.sect1Filters) {
            this.sect1.addFilter(filter);
        }
        this.sect1.setHelpText(propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Chapter.Properties.sect1, DocbookViewsRepository.FORM_KIND));
        this.sect1.createControls(parent, widgetFactory);
        this.sect1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.sect1,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData sect1Data = new GridData(GridData.FILL_HORIZONTAL);
        sect1Data.horizontalSpan = 3;
        this.sect1.setLayoutData(sect1Data);
        this.sect1.setLowerBound(0);
        this.sect1.setUpperBound(-1);
        sect1.setID(DocbookViewsRepository.Chapter.Properties.sect1);
        sect1.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createSect1TableComposition

        // End of user code
        return parent;
    }

    protected Composite createIdText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.Chapter.Properties.id, DocbookMessages.ChapterPropertiesEditionPart_IdLabel);
        id = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        id.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData idData = new GridData(GridData.FILL_HORIZONTAL);
        id.setLayoutData(idData);
        id.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.id,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, id.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.id,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, id.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        id.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ChapterPropertiesEditionPartForm.this, DocbookViewsRepository.Chapter.Properties.id,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, id.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(id, DocbookViewsRepository.Chapter.Properties.id);
        EditingUtils.setEEFtype(id, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Chapter.Properties.id, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createIdText

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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#initPara(EObject
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
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Chapter.Properties.para);
        if (eefElementEditorReadOnlyState && para.isEnabled()) {
            para.setEnabled(false);
            para.setToolTipText(DocbookMessages.Chapter_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !para.isEnabled()) {
            para.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#updatePara()
     *
     */
    @Override
    public void updatePara() {
        para.refresh();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#addFilterPara(ViewerFilter
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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#addBusinessFilterPara(ViewerFilter
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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#isContainedInParaTable(EObject
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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#initSect1(EObject
     *      current, EReference containingFeature, EReference feature)
     */
    @Override
    public void initSect1(ReferencesTableSettings settings) {
        if (current.eResource() != null && current.eResource().getResourceSet() != null) {
            this.resourceSet = current.eResource().getResourceSet();
        }
        ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
        sect1.setContentProvider(contentProvider);
        sect1.setInput(settings);
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Chapter.Properties.sect1);
        if (eefElementEditorReadOnlyState && sect1.isEnabled()) {
            sect1.setEnabled(false);
            sect1.setToolTipText(DocbookMessages.Chapter_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !sect1.isEnabled()) {
            sect1.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#updateSect1()
     *
     */
    @Override
    public void updateSect1() {
        sect1.refresh();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#addFilterSect1(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addFilterToSect1(ViewerFilter filter) {
        sect1Filters.add(filter);
        if (this.sect1 != null) {
            this.sect1.addFilter(filter);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#addBusinessFilterSect1(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addBusinessFilterToSect1(ViewerFilter filter) {
        sect1BusinessFilters.add(filter);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#isContainedInSect1Table(EObject
     *      element)
     *
     */
    @Override
    public boolean isContainedInSect1Table(EObject element) {
        return ((ReferencesTableSettings) sect1.getInput()).contains(element);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#getId()
     *
     */
    @Override
    public String getId() {
        return id.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ChapterPropertiesEditionPart#setId(String
     *      newValue)
     *
     */
    @Override
    public void setId(String newValue) {
        if (newValue != null) {
            id.setText(newValue);
        } else {
            id.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Chapter.Properties.id);
        if (eefElementEditorReadOnlyState && id.isEnabled()) {
            id.setEnabled(false);
            id.setToolTipText(DocbookMessages.Chapter_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !id.isEnabled()) {
            id.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
     *
     */
    @Override
    public String getTitle() {
        return DocbookMessages.Chapter_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
