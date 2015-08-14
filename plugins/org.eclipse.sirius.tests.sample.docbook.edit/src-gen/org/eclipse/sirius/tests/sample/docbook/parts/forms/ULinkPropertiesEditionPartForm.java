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
public class ULinkPropertiesEditionPartForm extends SectionPropertiesEditingPart implements IFormPropertiesEditionPart, ULinkPropertiesEditionPart {

    protected Text url;

    protected Text data;

    /**
     * For {@link ISection} use only.
     */
    public ULinkPropertiesEditionPartForm() {
        super();
    }

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public ULinkPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence uLinkStep = new BindingCompositionSequence(propertiesEditionComponent);
        CompositionStep propertiesStep = uLinkStep.addStep(DocbookViewsRepository.ULink.Properties.class);
        propertiesStep.addStep(DocbookViewsRepository.ULink.Properties.url);
        propertiesStep.addStep(DocbookViewsRepository.ULink.Properties.data);

        composer = new PartComposer(uLinkStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.ULink.Properties.class) {
                    return createPropertiesGroup(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.ULink.Properties.url) {
                    return createUrlText(widgetFactory, parent);
                }
                if (key == DocbookViewsRepository.ULink.Properties.data) {
                    return createDataText(widgetFactory, parent);
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
        propertiesSection.setText(DocbookMessages.ULinkPropertiesEditionPart_PropertiesGroupLabel);
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

    protected Composite createUrlText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.ULink.Properties.url, DocbookMessages.ULinkPropertiesEditionPart_UrlLabel);
        url = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        url.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData urlData = new GridData(GridData.FILL_HORIZONTAL);
        url.setLayoutData(urlData);
        url.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, DocbookViewsRepository.ULink.Properties.url,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, url.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, DocbookViewsRepository.ULink.Properties.url,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, url.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        url.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, DocbookViewsRepository.ULink.Properties.url,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, url.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(url, DocbookViewsRepository.ULink.Properties.url);
        EditingUtils.setEEFtype(url, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.ULink.Properties.url, DocbookViewsRepository.FORM_KIND), null);
        // Start of user code for createUrlText

        // End of user code
        return parent;
    }

    protected Composite createDataText(FormToolkit widgetFactory, Composite parent) {
        createDescription(parent, DocbookViewsRepository.ULink.Properties.data, DocbookMessages.ULinkPropertiesEditionPart_DataLabel);
        data = widgetFactory.createText(parent, ""); //$NON-NLS-1$
        data.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        widgetFactory.paintBordersFor(parent);
        GridData dataData = new GridData(GridData.FILL_HORIZONTAL);
        data.setLayoutData(dataData);
        data.addFocusListener(new FocusAdapter() {
            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void focusLost(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, DocbookViewsRepository.ULink.Properties.data,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, data.getText()));
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, DocbookViewsRepository.ULink.Properties.data,
                            PropertiesEditionEvent.FOCUS_CHANGED, PropertiesEditionEvent.FOCUS_LOST, null, data.getText()));
                }
            }

            /**
             * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
             */
            @Override
            public void focusGained(FocusEvent e) {
                if (propertiesEditionComponent != null) {
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, null, PropertiesEditionEvent.FOCUS_CHANGED,
                            PropertiesEditionEvent.FOCUS_GAINED, null, null));
                }
            }
        });
        data.addKeyListener(new KeyAdapter() {
            /**
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             *
             */
            @Override
            @SuppressWarnings("synthetic-access")
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    if (propertiesEditionComponent != null) {
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ULinkPropertiesEditionPartForm.this, DocbookViewsRepository.ULink.Properties.data,
                                PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, data.getText()));
                    }
                }
            }
        });
        EditingUtils.setID(data, DocbookViewsRepository.ULink.Properties.data);
        EditingUtils.setEEFtype(data, "eef::Text"); //$NON-NLS-1$
        FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.ULink.Properties.data, DocbookViewsRepository.FORM_KIND), null);
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
