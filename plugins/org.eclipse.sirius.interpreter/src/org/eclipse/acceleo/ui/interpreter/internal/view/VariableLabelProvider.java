/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.view;

import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.acceleo.ui.interpreter.view.Variable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * This will act as the label provider for the "variables" Tree Viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class VariableLabelProvider extends CellLabelProvider implements ILabelProvider {
    /** The delegate label provider. */
    private final AdapterFactoryLabelProvider delegate;

    /**
     * Instantiates this label provider given its adapter factory.
     * 
     * @param adapterFactory
     *            The adapter factory for this label provider.
     */
    public VariableLabelProvider(AdapterFactory adapterFactory) {
        super();
        delegate = new AdapterFactoryLabelProvider(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(Object element) {
        return delegate.getImage(element);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(Object element) {
        String text = ""; //$NON-NLS-1$
        if (element instanceof Variable) {
            text = ((Variable) element).getName();
        } else {
            text = delegate.getText(element);
        }
        return text;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element) {
        final String text = getText(element);
        if (text.indexOf('\n') != -1 || text.indexOf('\r') != -1) {
            return text;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.CellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
     */
    @Override
    public void update(ViewerCell cell) {
        final Object element = cell.getElement();
        final String text = getText(element);
        if (InterpreterMessages.getString("interpreter.view.variable.placeholder").equals(text)) { //$NON-NLS-1$
            cell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        } else {
            cell.setBackground(null);
        }
        cell.setText(text);
        cell.setImage(getImage(element));
    }
}
