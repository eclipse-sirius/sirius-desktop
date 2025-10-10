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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.ui.interpreter.internal.language.EvaluationResult;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterFile;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.PlatformUI;

/**
 * This will act as the label provider for the "variables" Tree Viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ResultLabelProvider extends CellLabelProvider implements ILabelProvider {
    /** The delegate label provider. */
    private final AdapterFactoryLabelProvider delegate;

    /** Keeps track of the images we created through this label provider. */
    private Map<String, Image> images = new HashMap<String, Image>();

    /**
     * Instantiates this label provider given its adapter factory.
     * 
     * @param adapterFactory
     *            The adapter factory for this label provider.
     */
    public ResultLabelProvider(AdapterFactory adapterFactory) {
        super();
        delegate = new AdapterFactoryLabelProvider(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        for (Image image : images.values()) {
            image.dispose();
        }
        images.clear();
        super.dispose();
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
        int indexOfNewLine = text.indexOf("\n\r") - 2; //$NON-NLS-1$
        if (indexOfNewLine < 0) {
            indexOfNewLine = text.indexOf("\n") - 1; //$NON-NLS-1$
        }
        if (indexOfNewLine < 0) {
            indexOfNewLine = text.indexOf("\r") - 1; //$NON-NLS-1$
        }
        if (indexOfNewLine >= 0) {
            cell.setText(text.substring(0, indexOfNewLine) + "  (...)"); //$NON-NLS-1$
        } else {
            cell.setText(text);
        }
        cell.setImage(getImage(element));
    }

    /**
     * Search for an editor associated with the given fileName, and return its icon.
     * 
     * @param fileName
     *            The name of the file for which we search an editor icon.
     * @return The icon of the editor associated with the given file name.
     */
    private Image createEditorIcon(String fileName) {
        IEditorDescriptor[] descriptors = PlatformUI.getWorkbench().getEditorRegistry().getEditors(fileName);
        if (descriptors != null) {
            for (int i = 0; i < descriptors.length; i++) {
                IEditorDescriptor descriptor = descriptors[i];
                if (descriptor.getImageDescriptor() != null) {
                    Image image = descriptor.getImageDescriptor().createImage();
                    return image;
                }
            }
        }
        return PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(fileName).createImage();
    }

    /**
     * Returns the image to be displayed for the given element.
     * 
     * @param element
     *            Element for which we need an icon.
     * @return The image to be displayed for the given element.
     */
    public Image getImage(Object element) {
        Image result = null;
        if (element instanceof InterpreterFile) {
            String key = ((InterpreterFile) element).getFileName();
            if (key.indexOf('.') != -1) {
                key = key.substring(key.indexOf('.') + 1);
            }
            result = images.get(key);

            if (result == null) {
                result = createEditorIcon(((InterpreterFile) element).getFileName());
                if (result != null) {
                    images.put(key, result);
                }
            }
        } else if (element instanceof EvaluationResult) {
            final EvaluationResult evaluationResult = (EvaluationResult) element;
            if (evaluationResult.getEvaluationResult() != null) {
                result = delegate.getImage(evaluationResult.getEvaluationResult());
            }
        }
        if (result != null) {
            return result;
        }
        return delegate.getImage(element);
    }

    /**
     * Returns the text to be displayed for the given element.
     * 
     * @param element
     *            Element for which we need a label.
     * @return The text to be displayed for the given element.
     */
    public String getText(Object element) {
        final String text;
        if (element instanceof EvaluationResult) {
            final EvaluationResult evaluationResult = (EvaluationResult) element;
            if (evaluationResult.getEvaluationResult() != null) {
                text = delegate.getText(evaluationResult.getEvaluationResult());
            } else if (evaluationResult.getStatus() != null) {
                text = evaluationResult.getStatus().getMessage();
            } else {
                text = "Empty evaluation result"; //$NON-NLS-1$
            }
        } else if (element instanceof InterpreterFile) {
            text = ((InterpreterFile) element).getFileName();
        } else {
            text = delegate.getText(element);
        }
        return text;
    }
}
