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
import org.eclipse.sirius.tests.sample.docbook.parts.AuthorPropertiesEditionPart;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.providers.DocbookMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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
public class AuthorPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, AuthorPropertiesEditionPart {

    protected Text email;

    protected Text personname;

    protected Text address;

    /**
     * For {@link ISection} use only.
     */
    public AuthorPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public AuthorPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence authorStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = authorStep.addStep(DocbookViewsRepository.Author.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.Author.Properties.email);
        propertiesStep.addStep(DocbookViewsRepository.Author.Properties.personname);
        propertiesStep.addStep(DocbookViewsRepository.Author.Properties.address);

        composer = new PartComposer(authorStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.Author.Properties.class) {
                    return createPropertiesGroup(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Author.Properties.email) {
                    return createEmailText(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Author.Properties.personname) {
                    return createPersonnameText(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.Author.Properties.address) {
                    return createAddressText(widgetFactory, parent);
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
        propertiesSection.setText(DocbookMessages.AuthorPropertiesEditionPart_PropertiesGroupLabel);
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

    protected Composite createEmailText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.Author.Properties.email, DocbookMessages.AuthorPropertiesEditionPart_EmailLabel);
        email = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        email.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData emailData = new GridData(GridData.FILL_HORIZONTAL);
        email.setLayoutData(emailData);
        email.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.email,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, email.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.email,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, email.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        email.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.email,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, email.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(email, DocbookViewsRepository.Author.Properties.email);
        EditingUtils.setEEFtype(email, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Author.Properties.email, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createEmailText

        // End of user code
        return parent;
    }

    protected Composite createPersonnameText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.Author.Properties.personname, DocbookMessages.AuthorPropertiesEditionPart_PersonnameLabel);
        personname = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        personname.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData personnameData = new GridData(GridData.FILL_HORIZONTAL);
        personname.setLayoutData(personnameData);
        personname.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.personname,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, personname.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.personname,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, personname.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        personname.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.personname,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, personname.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(personname, DocbookViewsRepository.Author.Properties.personname);
        EditingUtils.setEEFtype(personname, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Author.Properties.personname, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createPersonnameText

        // End of user code
        return parent;
    }

    protected Composite createAddressText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.Author.Properties.address, DocbookMessages.AuthorPropertiesEditionPart_AddressLabel);
        address = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        address.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData addressData = new GridData(GridData.FILL_HORIZONTAL);
        address.setLayoutData(addressData);
        address.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.address,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, address.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.address,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, address.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        address.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartForm.this, DocbookViewsRepository.Author.Properties.address,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, address.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(address, DocbookViewsRepository.Author.Properties.address);
        EditingUtils.setEEFtype(address, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Author.Properties.address, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createAddressText

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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.AuthorPropertiesEditionPart#getEmail()
     *
     */
    @Override
    public String getEmail() {
        return email.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.AuthorPropertiesEditionPart#setEmail(String
     *      newValue)
     *
     */
    @Override
    public void setEmail(String newValue) {
        if (newValue != null) {
            email.setText(newValue);
        } else {
            email.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Author.Properties.email);
        if (eefElementEditorReadOnlyState && email.isEnabled()) {
            email.setEnabled(false);
            email.setToolTipText(DocbookMessages.Author_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !email.isEnabled()) {
            email.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.AuthorPropertiesEditionPart#getPersonname()
     *
     */
    @Override
    public String getPersonname() {
        return personname.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.AuthorPropertiesEditionPart#setPersonname(String
     *      newValue)
     *
     */
    @Override
    public void setPersonname(String newValue) {
        if (newValue != null) {
            personname.setText(newValue);
        } else {
            personname.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Author.Properties.personname);
        if (eefElementEditorReadOnlyState && personname.isEnabled()) {
            personname.setEnabled(false);
            personname.setToolTipText(DocbookMessages.Author_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !personname.isEnabled()) {
            personname.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.AuthorPropertiesEditionPart#getAddress()
     *
     */
    @Override
    public String getAddress() {
        return address.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.AuthorPropertiesEditionPart#setAddress(String
     *      newValue)
     *
     */
    @Override
    public void setAddress(String newValue) {
        if (newValue != null) {
            address.setText(newValue);
        } else {
            address.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.Author.Properties.address);
        if (eefElementEditorReadOnlyState && address.isEnabled()) {
            address.setEnabled(false);
            address.setToolTipText(DocbookMessages.Author_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !address.isEnabled()) {
            address.setEnabled(true);
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
        return DocbookMessages.Author_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
