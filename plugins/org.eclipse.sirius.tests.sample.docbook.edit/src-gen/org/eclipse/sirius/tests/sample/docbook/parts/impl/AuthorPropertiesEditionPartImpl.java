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
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

// End of user code

/**
 *
 *
 */
public class AuthorPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, AuthorPropertiesEditionPart {

    protected Text email;

    protected Text personname;

    protected Text address;

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public AuthorPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence authorStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = authorStep.addStep(DocbookViewsRepository.Author.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.Author.Properties.email);
        propertiesStep.addStep(DocbookViewsRepository.Author.Properties.personname);
        propertiesStep.addStep(DocbookViewsRepository.Author.Properties.address);

        composer = new PartComposer(authorStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.Author.Properties.class) {
                    return createPropertiesGroup(parent);
                }
                if (key == DocbookViewsRepository.Author.Properties.email) {
                    return createEmailText(parent);
                }
                if (key == DocbookViewsRepository.Author.Properties.personname) {
                    return createPersonnameText(parent);
                }
                if (key == DocbookViewsRepository.Author.Properties.address) {
                    return createAddressText(parent);
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
        propertiesGroup.setText(DocbookMessages.AuthorPropertiesEditionPart_PropertiesGroupLabel);
        GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
        propertiesGroupData.horizontalSpan = 3;
        propertiesGroup.setLayoutData(propertiesGroupData);
        GridLayout propertiesGroupLayout = new GridLayout();
        propertiesGroupLayout.numColumns = 3;
        propertiesGroup.setLayout(propertiesGroupLayout);
        return propertiesGroup;
    }

    protected Composite createEmailText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.Author.Properties.email, DocbookMessages.AuthorPropertiesEditionPart_EmailLabel);
        email = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData emailData = new GridData(GridData.FILL_HORIZONTAL);
        email.setLayoutData(emailData);
        email.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartImpl.this, DocbookViewsRepository.Author.Properties.email,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, email.getText()));
                }
            }

        });
        email.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartImpl.this, DocbookViewsRepository.Author.Properties.email,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, email.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(email, DocbookViewsRepository.Author.Properties.email);
        EditingUtils.setEEFtype(email, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Author.Properties.email, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createEmailText

        // End of user code
        return parent;
    }

    protected Composite createPersonnameText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.Author.Properties.personname, DocbookMessages.AuthorPropertiesEditionPart_PersonnameLabel);
        personname = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData personnameData = new GridData(GridData.FILL_HORIZONTAL);
        personname.setLayoutData(personnameData);
        personname.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartImpl.this, DocbookViewsRepository.Author.Properties.personname,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, personname.getText()));
                }
            }

        });
        personname.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartImpl.this, DocbookViewsRepository.Author.Properties.personname,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, personname.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(personname, DocbookViewsRepository.Author.Properties.personname);
        EditingUtils.setEEFtype(personname, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Author.Properties.personname, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createPersonnameText

        // End of user code
        return parent;
    }

    protected Composite createAddressText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.Author.Properties.address, DocbookMessages.AuthorPropertiesEditionPart_AddressLabel);
        address = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData addressData = new GridData(GridData.FILL_HORIZONTAL);
        address.setLayoutData(addressData);
        address.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartImpl.this, DocbookViewsRepository.Author.Properties.address,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, address.getText()));
                }
            }

        });
        address.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(AuthorPropertiesEditionPartImpl.this, DocbookViewsRepository.Author.Properties.address,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, address.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(address, DocbookViewsRepository.Author.Properties.address);
        EditingUtils.setEEFtype(address, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.Author.Properties.address, DocbookViewsRepository.SWT_KIND), null);
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
