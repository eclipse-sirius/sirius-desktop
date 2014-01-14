/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.utils;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IUpdateableItemText;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Allows the edition of the {@link org.eclipse.swt.widgets.TreeItem TreeItem}
 * double-clicked by the user.
 */
public class SelectionTreeTextEditor extends AdapterFactoryTreeEditor {
    protected EditingDomain editingDomain;

    protected ComposedAdapterFactory adapterFactory;

    /** Last value of the feature. */
    protected String featureValue;

    public SelectionTreeTextEditor(EditingDomain editingDomain, Tree tree, ComposedAdapterFactory adapterFactory) {
        super(tree, adapterFactory);
        this.editingDomain = editingDomain;
        this.adapterFactory = adapterFactory;
    }

    /**
     * @see org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor#editItem(org.eclipse.swt.widgets.TreeItem)
     */
    protected void editItem(final TreeItem treeItem) {
        final Object treeItemData = treeItem.getData();
        final IUpdateableItemText updateableItemText = (IUpdateableItemText) adapterFactory.adapt(treeItemData, IUpdateableItemText.class);

        if (updateableItemText != null) {
            final String string = updateableItemText.getUpdateableText(treeItemData);

            if (string != null) {
                horizontalAlignment = SWT.LEFT;
                minimumWidth = Math.max(50, treeItem.getBounds().width);

                featureValue = string.substring(string.indexOf(" ") + 1);

                final Text textComposite = new Text(tree, SWT.BORDER);
                setEditor(textComposite, treeItem);
                textComposite.setText(featureValue);
                textComposite.setFocus();
                textComposite.setSelection(new Point(0, featureValue.length()));

                textComposite.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent event) {
                        updateText(treeItemData, textComposite);
                        textComposite.setVisible(false);
                    }
                });

                textComposite.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent event) {
                        // Carriage return or line feed
                        if (event.character == SWT.CR || event.character == SWT.LF) {
                            updateText(treeItemData, textComposite);
                            setEditor(null);
                            textComposite.dispose();

                            // escape
                        } else if (event.character == SWT.ESC) {
                            setEditor(null);
                            textComposite.dispose();
                        }
                    }
                });
            }
        }
    }

    /**
     * Reflects the text's modification upon the attribute's value in the model.
     * 
     * @param textOwner
     *            Data object of the {@link org.eclipse.swt.widgets.TreeItem
     *            TreeItem}.
     * @param text
     *            {@link org.eclipse.swt.widgets.Text Text} composite allowing
     *            the feature's edition.
     */
    private void updateText(Object textOwner, Text text) {
        EAttribute updateableField = null;
        EList objectReferences = ((EObject) textOwner).eClass().getEAllAttributes();

        for (Iterator iterator = objectReferences.iterator(); iterator.hasNext();) {
            EAttribute attribute = (EAttribute) iterator.next();

            if (((EObject) textOwner).eGet(attribute).toString().equals(featureValue)) {
                updateableField = attribute;
                break;
            }
        }

        Object value = null;
        Class instanceClass = updateableField.getEAttributeType().getInstanceClass();

        if (instanceClass.equals(int.class))
            value = Integer.valueOf(text.getText());
        else if (instanceClass.equals(double.class))
            value = Double.valueOf(text.getText());
        else
            value = text.getText();

        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, textOwner, updateableField, value));
    }
}
