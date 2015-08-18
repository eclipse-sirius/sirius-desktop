/**
 * <copyright>
 *
 * Copyright (c) 2002-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Obeo - Adapt for extension 
 *
 * </copyright>
 *
 * $Id: FeatureEditorDialog.java,v 1.11 2007/03/23 17:36:45 marcelop Exp $
 */

package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtensionFeatureDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog to edit an extended reference.
 * 
 * @author ymortier
 */
public class ExtendedFeatureEditorDialog extends Dialog {

    /** the list of choosen elements. */
    protected EList<EObject> result;

    /** The list of choices. */
    private List<EObject> choices;

    /** The current values of the reference. */
    private List<EObject> referenceValues;

    /** The reference to edit. */
    private ExtensionFeatureDescription extReference;

    private boolean multiLine;

    private ItemProvider values;

    private IContentProvider contentProvider;

    private ILabelProvider labelProvider;

    /**
     * Creates a new <code>ExtendedFeatureEditorDialog</code>. see
     * {@link org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog}.
     * 
     * @param parent
     *            the parent.
     * @param choices
     *            the choices.
     * @param referenceValues
     *            the reference values.
     * @param extReference
     *            the ext reference.
     */
    public ExtendedFeatureEditorDialog(final Shell parent, final List<EObject> choices, final List<EObject> referenceValues, final ExtensionFeatureDescription extReference) {
        super(parent);
        this.choices = choices;
        this.referenceValues = referenceValues;
        this.extReference = extReference;
        final List<AdapterFactory> adapterFactories = Collections.emptyList();
        final AdapterFactory adapterFactory = new ComposedAdapterFactory(adapterFactories);
        values = new ItemProvider(adapterFactory, this.referenceValues);
        contentProvider = new AdapterFactoryContentProvider(adapterFactory);
        this.choices.removeAll(this.referenceValues);
        this.labelProvider = new LabelProvider() {
            @Override
            public String getText(final Object element) {
                if (element instanceof EObject) {
                    final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor((EObject) element);
                    return accessor.getQualifiedName((EObject) element, true);
                }
                return String.valueOf(element);
            }

            @Override
            public Image getImage(final Object element) {
                ImageDescriptor descriptor = null;
                if (element instanceof EObject) {
                    final EObject target = (EObject) element;
                    final IItemLabelProvider myLabelProvider = (IItemLabelProvider) DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory().adapt(target, IItemLabelProvider.class);
                    descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(myLabelProvider.getImage(target));

                }
                if (descriptor == null) {
                    descriptor = ImageDescriptor.getMissingImageDescriptor();
                }
                return DiagramUIPlugin.getPlugin().getImage(descriptor);
            }

        };

    }

    /**
     * Creates the dialog.
     * 
     * This code is cut/paste from the
     * {@link org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog} class.
     * 
     * @param parent
     *            the parent.
     * @return the control.
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite contents = (Composite) super.createDialogArea(parent);

        final GridLayout contentsGridLayout = (GridLayout) contents.getLayout();
        contentsGridLayout.numColumns = 3;

        final GridData contentsGridData = (GridData) contents.getLayoutData();
        contentsGridData.horizontalAlignment = SWT.FILL;
        contentsGridData.verticalAlignment = SWT.FILL;

        final Composite choiceComposite = createChoiceComposite(contents);

        final Table choiceTable = this.choices == null ? null : new Table(choiceComposite, SWT.MULTI | SWT.BORDER);
        if (choiceTable != null) {
            final GridData choiceTableGridData = new GridData();
            choiceTableGridData.widthHint = Display.getCurrent().getBounds().width / 5;
            choiceTableGridData.heightHint = Display.getCurrent().getBounds().height / 3;
            choiceTableGridData.verticalAlignment = SWT.FILL;
            choiceTableGridData.horizontalAlignment = SWT.FILL;
            choiceTableGridData.grabExcessHorizontalSpace = true;
            choiceTableGridData.grabExcessVerticalSpace = true;
            choiceTable.setLayoutData(choiceTableGridData);
        }

        final TableViewer choiceTableViewer = this.choices == null ? null : new TableViewer(choiceTable);
        if (choiceTableViewer != null) {
            choiceTableViewer.setContentProvider(new AdapterFactoryContentProvider(new AdapterFactoryImpl()));
            choiceTableViewer.setLabelProvider(labelProvider);
            choiceTableViewer.setInput(new ItemProvider(this.choices));
        }

        // We use multi even for a single line because we want to respond to the
        // enter key.
        //

        int style = multiLine ? SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL : SWT.MULTI;
        style = style | SWT.BORDER;

        final Text choiceText = createChoiceText(choiceComposite, style);

        final Composite controlButtons = createControlButtonsArea(contents);

        new Label(controlButtons, SWT.NONE);

        final Button addButton = createAddButton(controlButtons);

        final Button removeButton = createRemoveButton(controlButtons);

        final Label spaceLabel = new Label(controlButtons, SWT.NONE);
        final GridData spaceLabelGridData = new GridData();
        spaceLabelGridData.verticalSpan = 2;
        spaceLabel.setLayoutData(spaceLabelGridData);

        final Button upButton = createUpButton(controlButtons);

        final Button downButton = createDownButton(controlButtons);

        final Composite featureComposite = createFeatureComposite(contents);

        final Table featureTable = new Table(featureComposite, SWT.MULTI | SWT.BORDER);
        final GridData featureTableGridData = new GridData();
        featureTableGridData.widthHint = Display.getCurrent().getBounds().width / 5;
        featureTableGridData.heightHint = Display.getCurrent().getBounds().height / 3;
        featureTableGridData.verticalAlignment = SWT.FILL;
        featureTableGridData.horizontalAlignment = SWT.FILL;
        featureTableGridData.grabExcessHorizontalSpace = true;
        featureTableGridData.grabExcessVerticalSpace = true;
        featureTable.setLayoutData(featureTableGridData);

        final TableViewer featureTableViewer = new TableViewer(featureTable);
        featureTableViewer.setContentProvider(contentProvider);
        featureTableViewer.setLabelProvider(labelProvider);
        featureTableViewer.setInput(values);
        if (!values.getChildren().isEmpty()) {
            featureTableViewer.setSelection(new StructuredSelection(values.getChildren().get(0)));
        }
        setTableViewerListener(choiceTableViewer, featureTableViewer, addButton, removeButton);

        setUpButtonListener(upButton, featureTableViewer);
        setDownButtonListener(downButton, featureTableViewer);

        setAddButtonListener(addButton, choiceTableViewer, featureTableViewer, choiceText);
        setRemoveButtonListener(removeButton, choiceTableViewer, featureTableViewer, choiceText);

        return contents;
    }

    private Text createChoiceText(final Composite parent, final int style) {
        final Text choiceText = this.choices == null ? new Text(parent, style) : null;
        if (choiceText != null) {
            final GridData choiceTextGridData = new GridData();
            choiceTextGridData.widthHint = Display.getCurrent().getBounds().width / 5;
            choiceTextGridData.verticalAlignment = SWT.BEGINNING;
            choiceTextGridData.horizontalAlignment = SWT.FILL;
            choiceTextGridData.grabExcessHorizontalSpace = true;
            if (multiLine) {
                choiceTextGridData.verticalAlignment = SWT.FILL;
                choiceTextGridData.grabExcessVerticalSpace = true;
            }
            choiceText.setLayoutData(choiceTextGridData);
        }

        if (choiceText != null) {
            choiceText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(final KeyEvent event) {
                    if (!multiLine && (event.character == '\r' || event.character == '\n')) {

                        // Object value =
                        // EcoreUtil.createFromString((EDataType)
                        // eClassifier, choiceText.getText());
                        // values.getChildren().add(value);
                        // choiceText.setText(StringUtil.EMPTY_STRING);
                        // featureTableViewer.setSelection(new
                        // StructuredSelection(value));
                        // event.doit = false;

                    } else if (event.character == '\33') {
                        choiceText.setText(StringUtil.EMPTY_STRING);
                        event.doit = false;
                    }
                }
            });
        }
        return choiceText;
    }

    private Composite createChoiceComposite(final Composite contents) {
        final Composite choiceComposite = new Composite(contents, SWT.NONE);

        final GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.horizontalAlignment = SWT.END;
        choiceComposite.setLayoutData(data);

        final GridLayout layout = new GridLayout();
        data.horizontalAlignment = SWT.FILL;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        choiceComposite.setLayout(layout);

        final Label choiceLabel = new Label(choiceComposite, SWT.NONE);
        choiceLabel.setText(this.choices == null ? EMFEditUIPlugin.INSTANCE.getString("_UI_Value_label") : EMFEditUIPlugin.INSTANCE.getString("_UI_Choices_label")); //$NON-NLS-1$ //$NON-NLS-2$
        final GridData choiceLabelGridData = new GridData();
        choiceLabelGridData.verticalAlignment = SWT.FILL;
        choiceLabelGridData.horizontalAlignment = SWT.FILL;
        choiceLabel.setLayoutData(choiceLabelGridData);

        return choiceComposite;
    }

    private Composite createFeatureComposite(final Composite contents) {
        final Composite featureComposite = new Composite(contents, SWT.NONE);

        final GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.horizontalAlignment = SWT.END;
        featureComposite.setLayoutData(data);

        final GridLayout layout = new GridLayout();
        data.horizontalAlignment = SWT.FILL;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        featureComposite.setLayout(layout);

        final Label featureLabel = new Label(featureComposite, SWT.NONE);
        featureLabel.setText(this.extReference.getName());
        final GridData featureLabelGridData = new GridData();
        featureLabelGridData.horizontalSpan = 2;
        featureLabelGridData.horizontalAlignment = SWT.FILL;
        featureLabelGridData.verticalAlignment = SWT.FILL;
        featureLabel.setLayoutData(featureLabelGridData);

        return featureComposite;
    }

    private Composite createControlButtonsArea(final Composite parent) {
        final Composite controlButtons = new Composite(parent, SWT.NONE);
        final GridData controlButtonsGridData = new GridData();
        controlButtonsGridData.verticalAlignment = SWT.FILL;
        controlButtonsGridData.horizontalAlignment = SWT.FILL;
        controlButtons.setLayoutData(controlButtonsGridData);

        final GridLayout controlsButtonGridLayout = new GridLayout();
        controlButtons.setLayout(controlsButtonGridLayout);
        return controlButtons;
    }

    private Button createAddButton(final Composite controlButtons) {
        final Button addButton = new Button(controlButtons, SWT.PUSH);
        addButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Add_label")); //$NON-NLS-1$
        final GridData addButtonGridData = new GridData();
        addButtonGridData.verticalAlignment = SWT.FILL;
        addButtonGridData.horizontalAlignment = SWT.FILL;
        addButton.setLayoutData(addButtonGridData);
        return addButton;
    }

    private Button createRemoveButton(final Composite controlButtons) {
        final Button removeButton = new Button(controlButtons, SWT.PUSH);
        removeButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Remove_label")); //$NON-NLS-1$
        final GridData removeButtonGridData = new GridData();
        removeButtonGridData.verticalAlignment = SWT.FILL;
        removeButtonGridData.horizontalAlignment = SWT.FILL;
        removeButton.setLayoutData(removeButtonGridData);
        return removeButton;
    }

    private Button createUpButton(final Composite controlButtons) {
        final Button upButton = new Button(controlButtons, SWT.PUSH);
        upButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Up_label")); //$NON-NLS-1$
        final GridData upButtonGridData = new GridData();
        upButtonGridData.verticalAlignment = SWT.FILL;
        upButtonGridData.horizontalAlignment = SWT.FILL;
        upButton.setLayoutData(upButtonGridData);
        return upButton;
    }

    private Button createDownButton(final Composite controlButtons) {
        final Button downButton = new Button(controlButtons, SWT.PUSH);
        downButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Down_label")); //$NON-NLS-1$
        final GridData downButtonGridData = new GridData();
        downButtonGridData.verticalAlignment = SWT.FILL;
        downButtonGridData.horizontalAlignment = SWT.FILL;
        downButton.setLayoutData(downButtonGridData);
        return downButton;
    }

    private void setUpButtonListener(final Button upButton, final TableViewer featureTableViewer) {
        upButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                final IStructuredSelection selection = (IStructuredSelection) featureTableViewer.getSelection();
                int minIndex = 0;
                final Iterator<?> i = selection.iterator();
                while (i.hasNext()) {
                    final Object value = i.next();
                    final int index = values.getChildren().indexOf(value);
                    values.getChildren().move(Math.max(index - 1, minIndex++), value);
                }
            }
        });
    }

    private void setDownButtonListener(final Button downButton, final TableViewer featureTableViewer) {
        downButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                final IStructuredSelection selection = (IStructuredSelection) featureTableViewer.getSelection();
                int maxIndex = values.getChildren().size() - selection.size();
                final Iterator<?> i = selection.iterator();
                while (i.hasNext()) {
                    final Object value = i.next();
                    final int index = values.getChildren().indexOf(value);
                    values.getChildren().move(Math.min(index + 1, maxIndex++), value);
                }
            }
        });
    }

    private void setAddButtonListener(final Button addButton, final TableViewer choiceTableViewer, final TableViewer featureTableViewer, final Text choiceText) {
        addButton.addSelectionListener(new SelectionAdapter() {
            // event is null when choiceTableViewer is double clicked
            @Override
            public void widgetSelected(final SelectionEvent event) {
                if (choiceTableViewer != null) {
                    final IStructuredSelection selection = (IStructuredSelection) choiceTableViewer.getSelection();
                    final Iterator<?> i = selection.iterator();
                    while (i.hasNext()) {
                        final Object value = i.next();
                        if (!values.getChildren().contains(value)) {
                            values.getChildren().add(value);
                            choiceTableViewer.remove(value);
                        }
                    }
                    featureTableViewer.setSelection(selection);
                } else if (choiceText != null) {

                    // Object value = EcoreUtil.createFromString((EDataType)
                    // eClassifier, choiceText.getText());
                    // values.getChildren().add(value);
                    // choiceText.setText(StringUtil.EMPTY_STRING);
                    // featureTableViewer.setSelection(new
                    // StructuredSelection(value));

                    // Ignore

                }
            }
        });
    }

    private void setRemoveButtonListener(final Button removeButton, final TableViewer choiceTableViewer, final TableViewer featureTableViewer, final Text choiceText) {
        removeButton.addSelectionListener(new SelectionAdapter() {
            // event is null when featureTableViewer is double clicked
            @Override
            public void widgetSelected(final SelectionEvent event) {
                final IStructuredSelection selection = (IStructuredSelection) featureTableViewer.getSelection();
                Object firstValue = null;
                final Iterator<?> i = selection.iterator();
                while (i.hasNext()) {
                    final Object value = i.next();
                    if (firstValue == null) {
                        firstValue = value;
                    }
                    values.getChildren().remove(value);
                    choiceTableViewer.add(value);
                }

                if (!values.getChildren().isEmpty()) {
                    featureTableViewer.setSelection(new StructuredSelection(values.getChildren().get(0)));
                }

                if (choiceTableViewer != null) {
                    choiceTableViewer.setSelection(selection);
                } else if (choiceText != null) {
                    if (firstValue != null) {
                        // String value = EcoreUtil.convertToString((EDataType)
                        // eClassifier, firstValue);
                        // choiceText.setText(value);
                    }
                }
            }
        });
    }

    private void setTableViewerListener(final TableViewer choiceTableViewer, final TableViewer featureTableViewer, final Button addButton, final Button removeButton) {
        if (choiceTableViewer != null) {
            choiceTableViewer.addDoubleClickListener(new IDoubleClickListener() {
                public void doubleClick(final DoubleClickEvent event) {
                    if (addButton.isEnabled()) {
                        addButton.notifyListeners(SWT.Selection, null);
                    }
                }
            });

            featureTableViewer.addDoubleClickListener(new IDoubleClickListener() {
                public void doubleClick(final DoubleClickEvent event) {
                    if (removeButton.isEnabled()) {
                        removeButton.notifyListeners(SWT.Selection, null);
                    }
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        result = new BasicEList(values.getChildren());
        super.okPressed();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#close()
     */
    @Override
    public boolean close() {
        contentProvider.dispose();
        return super.close();
    }

    /**
     * Returns the selected references.
     * 
     * @return the selected references.
     */
    public EList<EObject> getResult() {
        return result;
    }
}
