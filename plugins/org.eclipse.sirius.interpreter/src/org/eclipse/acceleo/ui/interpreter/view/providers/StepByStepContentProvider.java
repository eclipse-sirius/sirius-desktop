/*******************************************************************************
 * Copyright (c) 2013, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.view.providers;

import java.util.Collection;

import org.eclipse.acceleo.ui.interpreter.language.SplitExpression;
import org.eclipse.acceleo.ui.interpreter.language.SubExpression;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This content provider is aimed to access to data contained by a split expression.
 * 
 * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
 */
public class StepByStepContentProvider extends AdapterFactoryContentProvider {
    /**
     * Instantiates this content provider given its adapter factory.
     * 
     * @param adapterFactory
     *            The adapter factory for this content provider.
     */
    public StepByStepContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object object) {
        final Object[] result;
        if (object instanceof SplitExpression) {
            result = ((SplitExpression) object).getSubSteps().toArray();
        } else if (object instanceof SubExpression) {
            result = ((SubExpression) object).getSubSteps().toArray();
        } else {
            result = super.getChildren(object);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object object) {
        final boolean hasChildren;
        if (object instanceof SplitExpression) {
            SplitExpression expression = (SplitExpression) object;
            hasChildren = expression.getSubSteps() != null && !expression.getSubSteps().isEmpty();
        } else if (object instanceof SubExpression) {
            SubExpression step = (SubExpression) object;
            hasChildren = step.getSubSteps() != null && !step.getSubSteps().isEmpty();
        } else {
            hasChildren = super.hasChildren(object);
        }
        return hasChildren;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(Object)
     */
    @Override
    public Object[] getElements(Object inputElement) {
        final Object[] elements;
        if (inputElement instanceof Collection<?>) {
            elements = ((Collection<?>) inputElement).toArray();
        } else {
            elements = getChildren(inputElement);
        }
        return elements;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(Object)
     */
    @Override
    public Object getParent(Object element) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#dispose()
     */
    @Override
    public void dispose() {
        // Empty implementation
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#inputChanged(Viewer, Object, Object)
     */
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty implementation
    }
}
