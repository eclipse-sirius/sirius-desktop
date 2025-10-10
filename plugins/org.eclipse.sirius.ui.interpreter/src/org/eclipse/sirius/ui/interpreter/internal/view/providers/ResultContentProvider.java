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
package org.eclipse.sirius.ui.interpreter.internal.view.providers;

import java.util.Collection;

import org.eclipse.sirius.ui.interpreter.internal.language.EvaluationResult;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterFile;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

/**
 * This will act as the content provider for the "result" Tree Viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ResultContentProvider extends AdapterFactoryContentProvider {
    /**
     * Instantiates this content provider given its adapter factory.
     * 
     * @param adapterFactory
     *            The adapter factory for this content provider.
     */
    public ResultContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object object) {
        final Object[] children;
        if (object instanceof EvaluationResult) {
            children = getChildren(((EvaluationResult) object).getEvaluationResult());
        } else if (object instanceof InterpreterFile) {
            children = new Object[] { ((InterpreterFile) object).getFileContent(), };
        } else {
            children = super.getChildren(object);
        }
        return children;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(Object inputElement) {
        Object[] elements = new Object[0];
        if (inputElement instanceof Collection<?>) {
            elements = ((Collection<?>) inputElement).toArray(new Object[((Collection<?>) inputElement).size()]);
        } else if (inputElement instanceof InterpreterFile) {
            elements = new Object[] { ((InterpreterFile) inputElement).getFileContent(), };
        } else {
            elements = super.getElements(inputElement);
            if (elements == null || elements.length == 0 && inputElement != null) {
                if (inputElement instanceof EObject) {
                    elements = new Object[] { inputElement, };
                } else if (inputElement != null) {
                    elements = new Object[] { inputElement.toString(), };
                } else {
                    elements = new Object[0];
                }
            }
        }
        return elements;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object object) {
        final boolean hasChildren;
        if (object instanceof EvaluationResult) {
            hasChildren = hasChildren(((EvaluationResult) object).getEvaluationResult());
        } else if (object instanceof InterpreterFile) {
            hasChildren = true;
        } else {
            hasChildren = super.hasChildren(object);
        }
        return hasChildren;
    }
}
