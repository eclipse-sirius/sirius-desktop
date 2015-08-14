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
import org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart;
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
public class BookPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, BookPropertiesEditionPart {

    protected ReferencesTable chapter;

    protected List<ViewerFilter> chapterBusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> chapterFilters = new ArrayList<ViewerFilter>();

    protected Text id;

    protected Text lang;

    protected Text version;

    /**
     * For {@link ISection} use only.
     */
    public BookPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public BookPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence bookStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = bookStep.addStep(DocbookViewsRepository.Book.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.Book.Properties.chapter);
        propertiesStep.addStep(DocbookViewsRepository.Book.Properties.id);
        propertiesStep.addStep(DocbookViewsRepository.Book.Properties.lang);
        propertiesStep.addStep(DocbookViewsRepository.Book.Properties.version);

        composer = new PartComposer(bookStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.Book.Properties.class) {
                    return createPropertiesGroup(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Book.Properties.chapter) {
                    return createChapterTableComposition(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Book.Properties.id) {
                    return createIdText(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Book.Properties.lang) {
                    return createLangText(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Book.Properties.version) {
                    return createVersionText(widgetFactory, parent);
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
        propertiesSection.setText(DocbookMessages.BookPropertiesEditionPart_PropertiesGroupLabel);
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
    protected Composite createChapterTableComposition(FormToolkit widgetFactory, Composite parent) {
        this.chapter = new ReferencesTable(getDescription(DocbookViewsRepository.Book.Properties.chapter, DocbookMessages.BookPropertiesEditionPart_ChapterLabel), new ReferencesTableListener() {
            @Override
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.chapter,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                chapter.refresh();
            }

            @Override
            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.chapter,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                chapter.refresh();
            }

            @Override
            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.chapter,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                chapter.refresh();
            }

            @Override
            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.chapter,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                chapter.refresh();
            }

            @Override
            public void navigateTo(EObject element) {
            }
        });
        for (ViewerFilter filter : this.chapterFilters) {
            this.chapter.addFilter(filter);
        }
        this.chapter.setHelpText(propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Book.Properties.chapter, DocbookViewsRepository.FORM_KIND));
        this.chapter.createControls(parent, widgetFactory);
        this.chapter.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.chapter,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData chapterData = new GridData(GridData.FILL_HORIZONTAL);
        chapterData.horizontalSpan = 3;
        this.chapter.setLayoutData(chapterData);
        this.chapter.setLowerBound(0);
        this.chapter.setUpperBound(-1);
        chapter.setID(DocbookViewsRepository.Book.Properties.chapter);
        chapter.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createChapterTableComposition

        // End of user code
        return parent;
    }

    protected Composite createIdText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.Book.Properties.id, DocbookMessages.BookPropertiesEditionPart_IdLabel);
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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.id,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, id.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.id,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, id.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.id,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, id.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(id, DocbookViewsRepository.Book.Properties.id);
        EditingUtils.setEEFtype(id, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Book.Properties.id, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createIdText

        // End of user code
        return parent;
    }

    protected Composite createLangText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.Book.Properties.lang, DocbookMessages.BookPropertiesEditionPart_LangLabel);
        lang = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        lang.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData langData = new GridData(GridData.FILL_HORIZONTAL);
        lang.setLayoutData(langData);
        lang.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.lang,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, lang.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.lang,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, lang.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        lang.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.lang,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, lang.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(lang, DocbookViewsRepository.Book.Properties.lang);
        EditingUtils.setEEFtype(lang, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Book.Properties.lang, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createLangText

        // End of user code
        return parent;
    }

    protected Composite createVersionText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.Book.Properties.version, DocbookMessages.BookPropertiesEditionPart_VersionLabel);
        version = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        version.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData versionData = new GridData(GridData.FILL_HORIZONTAL);
        version.setLayoutData(versionData);
        version.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.version,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, version.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.version,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, version.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        version.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(BookPropertiesEditionPartForm.this, DocbookViewsRepository.Book.Properties.version,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, version.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(version, DocbookViewsRepository.Book.Properties.version);
        EditingUtils.setEEFtype(version, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Book.Properties.version, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createVersionText

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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#initChapter(EObject
     *      current, EReference containingFeature, EReference feature)
     */
    @Override
    public void initChapter(ReferencesTableSettings settings) {
        if (current.eResource() != null && current.eResource().getResourceSet() != null) {
            this.resourceSet = current.eResource().getResourceSet();
        }
        ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
        chapter.setContentProvider(contentProvider);
        chapter.setInput(settings);
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Book.Properties.chapter);
        if (eefElementEditorReadOnlyState && chapter.isEnabled()) {
            chapter.setEnabled(false);
            chapter.setToolTipText(DocbookMessages.Book_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !chapter.isEnabled()) {
            chapter.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#updateChapter()
     *
     */
    @Override
    public void updateChapter() {
        chapter.refresh();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#addFilterChapter(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addFilterToChapter(ViewerFilter filter) {
        chapterFilters.add(filter);
        if (this.chapter != null) {
            this.chapter.addFilter(filter);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#addBusinessFilterChapter(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addBusinessFilterToChapter(ViewerFilter filter) {
        chapterBusinessFilters.add(filter);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#isContainedInChapterTable(EObject
     *      element)
     *
     */
    @Override
    public boolean isContainedInChapterTable(EObject element) {
        return ((ReferencesTableSettings) chapter.getInput()).contains(element);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#getId()
     *
     */
    @Override
    public String getId() {
        return id.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#setId(String
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
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Book.Properties.id);
        if (eefElementEditorReadOnlyState && id.isEnabled()) {
            id.setEnabled(false);
            id.setToolTipText(DocbookMessages.Book_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !id.isEnabled()) {
            id.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#getLang()
     *
     */
    @Override
    public String getLang() {
        return lang.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#setLang(String
     *      newValue)
     *
     */
    @Override
    public void setLang(String newValue) {
        if (newValue != null) {
            lang.setText(newValue);
        } else {
            lang.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Book.Properties.lang);
        if (eefElementEditorReadOnlyState && lang.isEnabled()) {
            lang.setEnabled(false);
            lang.setToolTipText(DocbookMessages.Book_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !lang.isEnabled()) {
            lang.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#getVersion()
     *
     */
    @Override
    public String getVersion() {
        return version.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.BookPropertiesEditionPart#setVersion(String
     *      newValue)
     *
     */
    @Override
    public void setVersion(String newValue) {
        if (newValue != null) {
            version.setText(newValue);
        } else {
            version.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Book.Properties.version);
        if (eefElementEditorReadOnlyState && version.isEnabled()) {
            version.setEnabled(false);
            version.setToolTipText(DocbookMessages.Book_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !version.isEnabled()) {
            version.setEnabled(true);
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
        return DocbookMessages.Book_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
