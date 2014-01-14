/*******************************************************************************
 * Copyright (c) 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Obeo - Table adaptation
 *******************************************************************************/

package org.eclipse.sirius.editor.properties.sections.common;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An abstract implementation of a section with a table of one column (image +
 * text).
 */
public abstract class AbstractTablePropertySection extends AbstractViewpointPropertySection {
    protected class ImageItem {
        private String imagePath;

        private Enumerator eEnum;

        /**
         * The defaul constructor
         */
        public ImageItem(Enumerator eEnum, String imagePath) {
            setImagePath(imagePath);
            setEnum(eEnum);
        }

        /**
         * @return the text
         */
        public String getText() {
            return eEnum.getLiteral();
        }

        /**
         * @return the imagePath
         */
        public String getImagePath() {
            return imagePath;
        }

        /**
         * @return the eEnum
         */
        public Enumerator getEnum() {
            return eEnum;
        }

        /**
         * @param imagePath
         *            the imagePath to set
         */
        private void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        /**
         * @param eEnum
         *            the eEnum to set
         */
        private void setEnum(Enumerator eEnum) {
            this.eEnum = eEnum;
        }
    }

    /** Table control of the section. */
    protected Table table;

    /** Label control of the section. */
    protected CLabel nameLabel;

    /** Main composite **/
    protected Composite composite;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        if (aTabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) aTabbedPropertySheetPage);
        else
            super.createControls(parent, aTabbedPropertySheetPage);
        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        table = getWidgetFactory().createTable(composite, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        data.height = 90;
        table.setLayoutData(data);

        nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(table, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(table, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        table.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                handleTableModified();
            }
        });
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#aboutToBeShown()
     * @Override
     */
    public void aboutToBeShown() {
        super.aboutToBeShown();
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, "org.eclipse.sirius." + eObject.eClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        nameLabel.setText(getLabelText());
    }

    /**
     * Handle the table modified event.
     */
    protected void handleTableModified() {
        int index = table.getSelectionIndex();
        boolean equals = isEqual(index);

        if (!equals) {
            EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
            Object value = getFeatureValue(index);
            if (eObjectList.size() == 1) {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, getFeature(), value));
            } else {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (Iterator<EObject> i = eObjectList.iterator(); i.hasNext();) {
                    EObject nextObject = i.next();
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
     */
    public void refresh() {
        // Populate the table with the items
        if (table.getItems().length == 0) {
            createTableItems();
        }
        final String currentlySelected = getFeatureAsText();
        for (TableItem tItem : table.getItems()) {
            if (tItem.getText().equals(currentlySelected))
                table.setSelection(tItem);
        }
    }

    protected void createTableItems() {
        List<ImageItem> values = getChoiceOfValues();
        int currentIndex = 0;
        for (ImageItem imageItem : values) {
            newTableItem(imageItem, currentIndex, getFeatureAsText());
            currentIndex++;
        }
    }

    /*
     * Create a new <code>TableItem</code> to represent the item.
     */
    protected org.eclipse.swt.widgets.TableItem newTableItem(ImageItem imageItem, int index, String currentSelection) {
        Image image = SiriusEditorPlugin.INSTANCE.getBundledImage(imageItem.getImagePath());

        TableItem item = new TableItem(table, SWT.NULL, index);
        if (image != null) {
            item.setImage(image);
        }
        item.setText(imageItem.getText());
        item.setData(imageItem);
        if (currentSelection.equals(imageItem.getText())) {
            table.setSelection(index);
        }

        return item;
    }

    /**
     * Get the possible values for the feature as Strings.
     * 
     * return The possible values for the feature as Strings.
     */
    protected String[] getTableValues() {
        List values = getChoiceOfValues();
        String[] array = new String[values.size()];

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) != null) {
                if (values.get(i) instanceof String) {
                    array[i] = (String) values.get(i);
                } else if (values.get(i) instanceof EObject) {
                    array[i] = getAdapterFactoryLabelProvider((EObject) values.get(i)).getText(values.get(i));
                } else if (values.get(i) instanceof Enumerator) {
                    array[i] = ((Enumerator) values.get(i)).getName();
                } else if (values.get(i) instanceof ImageItem) {
                    array[i] = ((ImageItem) values.get(i)).getText();
                }
            } else {
                array[i] = new String();
            }
        }

        return array;
    }

    /**
     * Determine if the provided index in the choice of values is equal to the
     * current setting of the property.
     * 
     * @param index
     *            The new index in the choice of values.
     * @return <code>True</code> if the new index value is equal to the current
     *         property setting, <code>False</code> otherwise.
     */
    protected abstract boolean isEqual(int index);

    /**
     * Get the feature for the table field of the section.
     * 
     * @return The feature for the text.
     */
    protected abstract EStructuralFeature getFeature();

    /**
     * Get the choice of values for the feature for the table field of the
     * section.
     * 
     * @return The list of values of the feature as text.
     */
    protected abstract List getChoiceOfValues();

    protected String getDefaultFeatureAsText() {
        String value = new String();
        if (eObject.eGet(getFeature()) != null) {
            value = eObject.eGet(getFeature()).toString();
        }
        return value;
    }

    /**
     * Get the value of the feature as text for the text field of the section.
     * 
     * @return The value of the feature as text.
     */
    protected String getFeatureAsText() {
        final EStructuralFeature eFeature = getFeature();
        final IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(eFeature);
        if (propertyDescriptor != null)
            return propertyDescriptor.getLabelProvider(eObject).getText(eObject.eGet(eFeature));
        return getDefaultFeatureAsText();
    }

    /**
     * Get the new value of the feature for the text field of the section.
     * 
     * @param index
     *            The new index in the choice of values.
     * @return The new value of the feature.
     */
    protected abstract Object getFeatureValue(int index);

    protected abstract String getDefaultLabelText();

    /**
     * Get the label for the text field of the section.
     * 
     * @return The label for the text field.
     */
    protected String getLabelText() {
        if (eObject != null) {
            final EStructuralFeature eFeature = getFeature();
            final IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(eFeature);
            if (propertyDescriptor != null)
                return propertyDescriptor.getDisplayName(eObject);
        }
        return getDefaultLabelText();
    }

    /**
     * {@inheritDoc}
     */
    protected void makeReadonly() {
        table.setEnabled(false);
    }

    /**
     * {@inheritDoc}
     */
    protected void makeWrittable() {
        table.setEnabled(true);
    }
}
