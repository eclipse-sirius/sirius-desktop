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
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.OrderedListPropertiesEditionPart;
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
public class OrderedListPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, OrderedListPropertiesEditionPart {

    protected Text numeration;

    /**
     * Default constructor
     *
     * @param editionComponent
     *            the {@link IPropertiesEditionComponent} that manage this part
     *
     */
    public OrderedListPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
        CompositionSequence orderedListStep = new BindingCompositionSequence(propertiesEditionComponent);
        orderedListStep.addStep(DocbookViewsRepository.OrderedList.Properties.class).addStep(DocbookViewsRepository.OrderedList.Properties.numeration);

        composer = new PartComposer(orderedListStep) {

            @Override
            public Composite addToPart(Composite parent, Object key) {
                if (key == DocbookViewsRepository.OrderedList.Properties.class) {
                    return createPropertiesGroup(parent);
                }
                if (key == DocbookViewsRepository.OrderedList.Properties.numeration) {
                    return createNumerationText(parent);
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
        propertiesGroup.setText(DocbookMessages.OrderedListPropertiesEditionPart_PropertiesGroupLabel);
        GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
        propertiesGroupData.horizontalSpan = 3;
        propertiesGroup.setLayoutData(propertiesGroupData);
        GridLayout propertiesGroupLayout = new GridLayout();
        propertiesGroupLayout.numColumns = 3;
        propertiesGroup.setLayout(propertiesGroupLayout);
        return propertiesGroup;
    }

    protected Composite createNumerationText(Composite parent) {
        createDescription(parent, DocbookViewsRepository.OrderedList.Properties.numeration, DocbookMessages.OrderedListPropertiesEditionPart_NumerationLabel);
        numeration = SWTUtils.createScrollableText(parent, SWT.BORDER);
        GridData numerationData = new GridData(GridData.FILL_HORIZONTAL);
        numeration.setLayoutData(numerationData);
        numeration.addFocusListener(new FocusAdapter() {

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
                    propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OrderedListPropertiesEditionPartImpl.this, DocbookViewsRepository.OrderedList.Properties.numeration,
                            PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, numeration.getText()));
                }
            }

        });
        numeration.addKeyListener(new KeyAdapter() {

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
                        propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(OrderedListPropertiesEditionPartImpl.this,
                                DocbookViewsRepository.OrderedList.Properties.numeration, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, numeration.getText()));
                    }
                }
            }

        });
        EditingUtils.setID(numeration, DocbookViewsRepository.OrderedList.Properties.numeration);
        EditingUtils.setEEFtype(numeration, "eef::Text"); //$NON-NLS-1$
        SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(DocbookViewsRepository.OrderedList.Properties.numeration, DocbookViewsRepository.SWT_KIND), null);
        // Start of user code for createNumerationText

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
     * @see org.eclipse.sirius.tests.sample.docbook.parts.OrderedListPropertiesEditionPart#getNumeration()
     *
     */
    @Override
    public String getNumeration() {
        return numeration.getText();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.tests.sample.docbook.parts.OrderedListPropertiesEditionPart#setNumeration(String
     *      newValue)
     *
     */
    @Override
    public void setNumeration(String newValue) {
        if (newValue != null) {
            numeration.setText(newValue);
        } else {
            numeration.setText(""); //$NON-NLS-1$
        }
        boolean eefElementEditorReadOnlyState = isReadOnly(DocbookViewsRepository.OrderedList.Properties.numeration);
        if (eefElementEditorReadOnlyState && numeration.isEnabled()) {
            numeration.setEnabled(false);
            numeration.setToolTipText(DocbookMessages.OrderedList_ReadOnly);
        } else if (!eefElementEditorReadOnlyState && !numeration.isEnabled()) {
            numeration.setEnabled(true);
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
        return DocbookMessages.OrderedList_Part_Title;
    }

    // Start of user code additional methods

    // End of user code

}
