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
import org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart;
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
public class InfoPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, InfoPropertiesEditionPart {

    protected ReferencesTable author;

    protected List<ViewerFilter> authorBusinessFilters = new ArrayList<ViewerFilter>();

    protected List<ViewerFilter> authorFilters = new ArrayList<ViewerFilter>();

    protected Text date;

    protected Text pubdate;

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public InfoPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence infoStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = infoStep.addStep(DocbookViewsRepository.Info.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.Info.Properties.author);
        propertiesStep.addStep(DocbookViewsRepository.Info.Properties.date);
        propertiesStep.addStep(DocbookViewsRepository.Info.Properties.pubdate);

        composer = new PartComposer(infoStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.Info.Properties.class) {
                    return createPropertiesGroup(parent);
                }
                if (key == DocbookViewsRepository.Info.Properties.author) {
                    return createAuthorAdvancedTableComposition(parent);
                }
                if (key == DocbookViewsRepository.Info.Properties.date) {
                    return createDateText(parent);
                }
                if (key == DocbookViewsRepository.Info.Properties.pubdate) {
                    return createPubdateText(parent);
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
        propertiesGroup.setText(DocbookMessages.InfoPropertiesEditionPart_PropertiesGroupLabel);
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
    protected Composite createAuthorAdvancedTableComposition(Composite parent) {
        this.author = new ReferencesTable(getDescription(DocbookViewsRepository.Info.Properties.author, DocbookMessages.InfoPropertiesEditionPart_AuthorLabel), new ReferencesTableListener() {
            @Override
            public void handleAdd() {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.author,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
                author.refresh();
            }

            @Override
            public void handleEdit(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.author,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
                author.refresh();
            }

            @Override
            public void handleMove(EObject element, int oldIndex, int newIndex) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.author,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
                author.refresh();
            }

            @Override
            public void handleRemove(EObject element) {
                propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.author,
                        PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
                author.refresh();
            }

            @Override
            public void navigateTo(EObject element) {
            }
        });
        for (ViewerFilter filter : this.authorFilters) {
            this.author.addFilter(filter);
        }
        this.author.setHelpText(propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Info.Properties.author, DocbookViewsRepository.SWT_KIND));
        this.author.createControls(parent);
        this.author.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.item != null && e.item.getData() instanceof EObject) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.author,
                            PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
                }
            }

        });
        GridData authorData = new GridData(GridData.FILL_HORIZONTAL);
        authorData.horizontalSpan = 3;
        this.author.setLayoutData(authorData);
        this.author.setLowerBound(0);
        this.author.setUpperBound(-1);
        author.setID(DocbookViewsRepository.Info.Properties.author);
        author.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
        // Start of user code for createAuthorAdvancedTableComposition

        // End of user code
        return parent;
    }

    protected Composite createDateText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.Info.Properties.date, DocbookMessages.InfoPropertiesEditionPart_DateLabel);
        date = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData dateData = new GridData(GridData.FILL_HORIZONTAL);
        date.setLayoutData(dateData);
        date.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.date,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, date.getText()));
                }
            }

        });
        date.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.date,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, date.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(date, DocbookViewsRepository.Info.Properties.date);
        EditingUtils.setEEFtype(date, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Info.Properties.date, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createDateText

        // End of user code
        return parent;
    }

    protected Composite createPubdateText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.Info.Properties.pubdate, DocbookMessages.InfoPropertiesEditionPart_PubdateLabel);
        pubdate = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData pubdateData = new GridData(GridData.FILL_HORIZONTAL);
        pubdate.setLayoutData(pubdateData);
        pubdate.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.pubdate,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, pubdate.getText()));
                }
            }

        });
        pubdate.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(InfoPropertiesEditionPartImpl.this, DocbookViewsRepository.Info.Properties.pubdate,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, pubdate.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(pubdate, DocbookViewsRepository.Info.Properties.pubdate);
        EditingUtils.setEEFtype(pubdate, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Info.Properties.pubdate, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createPubdateText

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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#initAuthor(EObject
     *      current, EReference containingFeature, EReference feature)
     */
    @Override
    public void initAuthor(ReferencesTableSettings settings) {
        if (current.eResource() != null && current.eResource().getResourceSet() != null) {
            this.resourceSet = current.eResource().getResourceSet();
        }
        ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
        author.setContentProvider(contentProvider);
        author.setInput(settings);
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Info.Properties.author);
        if (eefElementEditorReadOnlyState && author.isEnabled()) {
            author.setEnabled(false);
            author.setToolTipText(DocbookMessages.Info_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !author.isEnabled()) {
            author.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#updateAuthor()
     *
     */
    @Override
    public void updateAuthor() {
        author.refresh();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#addFilterAuthor(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addFilterToAuthor(ViewerFilter filter) {
        authorFilters.add(filter);
        if (this.author != null) {
            this.author.addFilter(filter);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#addBusinessFilterAuthor(ViewerFilter
     *      filter)
     *
     */
    @Override
    public void addBusinessFilterToAuthor(ViewerFilter filter) {
        authorBusinessFilters.add(filter);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#isContainedInAuthorTable(EObject
     *      element)
     *
     */
    @Override
    public boolean isContainedInAuthorTable(EObject element) {
        return ((ReferencesTableSettings) author.getInput()).contains(element);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#getDate()
     *
     */
    @Override
    public String getDate() {
        return date.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#setDate(String
     *      newValue)
     *
     */
    @Override
    public void setDate(String newValue) {
        if (newValue != null) {
            date.setText(newValue);
        } else {
            date.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Info.Properties.date);
        if (eefElementEditorReadOnlyState && date.isEnabled()) {
            date.setEnabled(false);
            date.setToolTipText(DocbookMessages.Info_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !date.isEnabled()) {
            date.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#getPubdate()
     *
     */
    @Override
    public String getPubdate() {
        return pubdate.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.InfoPropertiesEditionPart#setPubdate(String
     *      newValue)
     *
     */
    @Override
    public void setPubdate(String newValue) {
        if (newValue != null) {
            pubdate.setText(newValue);
        } else {
            pubdate.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Info.Properties.pubdate);
        if (eefElementEditorReadOnlyState && pubdate.isEnabled()) {
            pubdate.setEnabled(false);
            pubdate.setToolTipText(DocbookMessages.Info_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !pubdate.isEnabled()) {
            pubdate.setEnabled(true);
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
        return DocbookMessages.Info_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
