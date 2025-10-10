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
package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import java.util.Collection;

import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.sirius.ui.interpreter.internal.view.Variable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

/**
 * This will act as the content provider for the "variables" Tree Viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class VariableContentProvider extends AdapterFactoryContentProvider {
    /**
     * Instantiates this content provider given its adapter factory.
     * 
     * @param adapterFactory
     *            The adapter factory for this content provider.
     */
    public VariableContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object parentElement) {
        Object[] children = null;
        if (parentElement instanceof Variable) {
            Object variableValue = ((Variable) parentElement).getValue();
            if (variableValue instanceof Collection<?>) {
                children = ((Collection<?>) variableValue).toArray(new Object[((Collection<?>) variableValue).size()]);
            } else {
                children = new Object[] { variableValue, };
            }
            return children;
        }
        return super.getChildren(parentElement);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof Collection<?>) {
            Object[] elements = ((Collection<?>) inputElement).toArray(new Variable[((Collection<?>) inputElement).size()]);
            if (elements.length == 0) {
                elements = new String[] { InterpreterMessages.getString("interpreter.view.variable.placeholder"), }; //$NON-NLS-1$
            }
            return elements;
        }
        return super.getElements(inputElement);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof Variable) {
            return ((Variable) element).getValue() != null;
        }
        return super.hasChildren(element);
    }
}
