/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.identifiedelement;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A specific implementation of IdentifiedElementNamePropertySection to provide
 * a visual hint between Id and label properties.
 * 
 * @author fbarbin
 */
public class IdentifiedElementNamePropertySectionSpec extends IdentifiedElementNamePropertySection {
    /**
     * This flag is necessary to distinguish the cases when the text shown for
     * the label is set to update the implicit value or when the user explicitly
     * modifies it. It is true during refresh, and this disable the
     * ModifyListener which normally sets <code>setHasExplicitValue</code> when
     * the text is changed.
     */
    private AtomicBoolean duringLabelTextRefresh = new AtomicBoolean(false);

    /**
     * A label property section which knows if the value it shows is implicit
     * (i.e. there is nothing in the model and thus we show the value of the Id)
     * or explicit (i.e. the user typed an explicit value or there is one set in
     * the model).
     * 
     * @author pcdavid
     */
    private class SmartIdentifiedElementLabelPropertySection extends IdentifiedElementLabelPropertySection {
        private boolean hasExplicitValue = false;

        protected void setHasExplicitValue() {
            this.hasExplicitValue = true;
        }

        private String getIdText() {
            return IdentifiedElementNamePropertySectionSpec.this.getFeatureAsText();
        }

        @Override
        public void setInput(IWorkbenchPart part, ISelection selection) {
            super.setInput(part, selection);
            this.hasExplicitValue = eObject.eIsSet(getFeature());
            refresh();
        }

        @Override
        public void refresh() {
            duringLabelTextRefresh.set(true);
            super.refresh();
            if (!hasExplicitValue) {
                setText(getIdText());
            }
            duringLabelTextRefresh.set(false);
            refreshTextColor();
        }

        @Override
        protected void handleTextModified() {
            if (hasExplicitValue) {
                super.handleTextModified();
            }
        }

        @Override
        public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
            super.createControls(parent, tabbedPropertySheetPage);

            // Add listener to catch lost and gain focus on field label to
            // select all characters of field or to write id value if field is
            // empty. The listener for select all characters of field on click,
            // does not work on linux and Mac Os. The 'mouse down' was probably
            // override by system action.
            text.addFocusListener(new FocusListener() {

                /**
                 * Complete label field with id value if label field is empty on
                 * focus lost. {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
                 */
                public void focusLost(FocusEvent e) {
                    if ("".equals(text.getText())) {
                        setText(getIdText());
                        hasExplicitValue = false;
                        refreshTextColor();
                    }
                }

                /**
                 * Selected text in field if focus gained on field label.
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
                 */
                public void focusGained(FocusEvent e) {
                    if (getIdText().equals(text.getText())) {
                        setText("");
                    } else {
                        getText().selectAll();
                    }
                }
            });

            text.addMouseListener(new MouseListener() {

                public void mouseUp(MouseEvent e) {
                    // Do nothing
                }

                /**
                 * Selected text in field if user click on field label.
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
                 */
                public void mouseDown(MouseEvent e) {
                    if (getIdText().equals(text.getText())) {
                        setText("");
                    } else {
                        getText().selectAll();
                    }
                }

                public void mouseDoubleClick(MouseEvent e) {
                    // Do nothing
                }
            });

        }

        public void refreshTextColor() {
            Device device = text.getForeground().getDevice();
            final Color textColor;
            if (hasExplicitValue) {
                textColor = device.getSystemColor(SWT.COLOR_BLACK);
            } else {
                textColor = device.getSystemColor(SWT.COLOR_GRAY);
            }
            getText().setForeground(textColor);
        }
    }

    private static final int LABEL_WIDTH = 100;

    private SmartIdentifiedElementLabelPropertySection labelSection;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        if (labelSection != null) {
            labelSection.setInput(part, selection);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        super.refresh();
        if (labelSection != null) {
            labelSection.refresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableModelUpdating() {
        super.disableModelUpdating();
        if (labelSection != null) {
            labelSection.disableModelUpdating();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableModelUpdating() {
        super.enableModelUpdating();
        if (labelSection != null) {
            labelSection.enableModelUpdating();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        Composite topLevel = tabbedPropertySheetPage.getWidgetFactory().createComposite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = true;
        layout.marginLeft = -5;
        layout.marginTop = -5;
        layout.marginBottom = -5;
        layout.marginRight = -5;
        topLevel.setLayout(layout);
        super.createControls(topLevel, tabbedPropertySheetPage);

        createLabelSection(tabbedPropertySheetPage, topLevel);
    }

    private void createLabelSection(TabbedPropertySheetPage tabbedPropertySheetPage, Composite topLevel) {
        labelSection = new SmartIdentifiedElementLabelPropertySection();
        labelSection.createControls(topLevel, tabbedPropertySheetPage);
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        labelSection.getComposite().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        FormData oldData = (FormData) labelSection.getText().getLayoutData();
        oldData.left = new FormAttachment(0, LABEL_WIDTH);
        labelSection.getText().setLayoutData(oldData);
        labelSection.getText().addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                if (!duringLabelTextRefresh.get()) {
                    labelSection.setHasExplicitValue();
                    labelSection.refreshTextColor();
                }
            }
        });
    }
}
