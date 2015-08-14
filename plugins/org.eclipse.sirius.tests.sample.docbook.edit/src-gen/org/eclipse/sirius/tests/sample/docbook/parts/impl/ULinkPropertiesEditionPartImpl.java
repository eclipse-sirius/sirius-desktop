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
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.ULinkPropertiesEditionPart;
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
public class ULinkPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ULinkPropertiesEditionPart {

    protected Text url;

    protected Text data;

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public ULinkPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence uLinkStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = uLinkStep.addStep(DocbookViewsRepository.ULink.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.ULink.Properties.url);
        propertiesStep.addStep(DocbookViewsRepository.ULink.Properties.data);

        composer = new PartComposer(uLinkStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.ULink.Properties.class) {
                    return createPropertiesGroup(parent);
                }
                if (key == DocbookViewsRepository.ULink.Properties.url) {
                    return createUrlText(parent);
                }
                if (key == DocbookViewsRepository.ULink.Properties.data) {
                    return createDataText(parent);
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
        propertiesGroup.setText(DocbookMessages.ULinkPropertiesEditionPart_PropertiesGroupLabel);
        GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
        propertiesGroupData.horizontalSpan = 3;
        propertiesGroup.setLayoutData(propertiesGroupData);
        GridLayout propertiesGroupLayout = new GridLayout();
        propertiesGroupLayout.numColumns = 3;
        propertiesGroup.setLayout(propertiesGroupLayout);
        return propertiesGroup;
    }

    protected Composite createUrlText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.ULink.Properties.url, DocbookMessages.ULinkPropertiesEditionPart_UrlLabel);
        url = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData urlData = new GridData(GridData.FILL_HORIZONTAL);
        url.setLayoutData(urlData);
        url.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartImpl.this, DocbookViewsRepository.ULink.Properties.url,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, url.getText()));
                }
            }

        });
        url.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartImpl.this, DocbookViewsRepository.ULink.Properties.url,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, url.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(url, DocbookViewsRepository.ULink.Properties.url);
        EditingUtils.setEEFtype(url, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.ULink.Properties.url, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createUrlText

        // End of user code
        return parent;
    }

    protected Composite createDataText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.ULink.Properties.data, DocbookMessages.ULinkPropertiesEditionPart_DataLabel);
        data = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData dataData = new GridData(GridData.FILL_HORIZONTAL);
        data.setLayoutData(dataData);
        data.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartImpl.this, DocbookViewsRepository.ULink.Properties.data,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, data.getText()));
                }
            }

        });
        data.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartImpl.this, DocbookViewsRepository.ULink.Properties.data,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, data.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(data, DocbookViewsRepository.ULink.Properties.data);
        EditingUtils.setEEFtype(data, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.ULink.Properties.data, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createDataText

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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ULinkPropertiesEditionPart#getUrl()
     *
     */
    @Override
    public String getUrl() {
        return url.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ULinkPropertiesEditionPart#setUrl(String
     *      newValue)
     *
     */
    @Override
    public void setUrl(String newValue) {
        if (newValue != null) {
            url.setText(newValue);
        } else {
            url.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.ULink.Properties.url);
        if (eefElementEditorReadOnlyState && url.isEnabled()) {
            url.setEnabled(false);
            url.setToolTipText(DocbookMessages.ULink_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !url.isEnabled()) {
            url.setEnabled(true);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ULinkPropertiesEditionPart#getData()
     *
     */
    @Override
    public String getData() {
        return data.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.ULinkPropertiesEditionPart#setData(String
     *      newValue)
     *
     */
    @Override
    public void setData(String newValue) {
        if (newValue != null) {
            data.setText(newValue);
        } else {
            data.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.ULink.Properties.data);
        if (eefElementEditorReadOnlyState && data.isEnabled()) {
            data.setEnabled(false);
            data.setToolTipText(DocbookMessages.ULink_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !data.isEnabled()) {
            data.setEnabled(true);
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
        return DocbookMessages.ULink_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
