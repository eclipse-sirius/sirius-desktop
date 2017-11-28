/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.provider;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.LabelProviderProviderService;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Joiner;

/**
 * Label provider of the DTable tabbed property sheet page.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableLabelProvider extends LabelProvider {

    private LabelProviderProviderService labelProviderProviderService;

    /**
     * Plugin's
     * {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     * AdapterFactoryLabelProvider}.
     */
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    /**
     * Default constructor.
     */
    public DTableLabelProvider() {
        labelProviderProviderService = new LabelProviderProviderService();
    }

    @Override
    public Image getImage(final Object object) {
        Image labelImage = null;
        ILabelProvider firstProvidedLabelProvider = labelProviderProviderService.getFirstProvidedLabelProvider(object);
        if (firstProvidedLabelProvider != null) {
            labelImage = firstProvidedLabelProvider.getImage(object);
        } else {
            Object tempObject = object;

            if (object != null && !object.equals(StructuredSelection.EMPTY)) {
                if (object instanceof IStructuredSelection) {
                    List<?> semanticSelection = extractSemanticSelection(((IStructuredSelection) object).toList());
                    if (!semanticSelection.isEmpty() && !containsDifferentTypes(semanticSelection)) {
                        tempObject = semanticSelection.iterator().next();
                    }
                }
                if (tempObject instanceof EObject || tempObject instanceof Resource) {
                    labelImage = getAdapterFactoryLabelProvider().getImage(tempObject);
                }
            }
        }
        return labelImage;
    }

    @Override
    public String getText(final Object object) {
        String text = null;
        ILabelProvider firstProvidedLabelProvider = labelProviderProviderService.getFirstProvidedLabelProvider(object);
        if (firstProvidedLabelProvider != null) {
            text = firstProvidedLabelProvider.getText(object);
        } else {
            int selectionSize = 0;
            Object tempObject = object;

            List<?> semanticSelection = Collections.emptyList();
            if (object != null && !object.equals(StructuredSelection.EMPTY)) {
                if (object instanceof IStructuredSelection) {
                    semanticSelection = extractSemanticSelection(((IStructuredSelection) object).toList());
                    selectionSize = semanticSelection.size();
                    if (containsDifferentTypes(semanticSelection)) {
                        text = MessageFormat.format(Messages.DTableLabelProvider_nbSelectedItems, selectionSize);
                    } else if (!semanticSelection.isEmpty()) {
                        tempObject = semanticSelection.iterator().next();
                    }
                }
            }

            if (tempObject != null) {
                text = getAdapterFactoryLabelProvider().getText(tempObject);
            }

            if (selectionSize > 1) {
                List<String> labels = new ArrayList<>();
                for (Object selected : semanticSelection) {
                    labels.add(getAdapterFactoryLabelProvider().getText(selected));
                }
                text = MessageFormat.format(Messages.DTableLabelProvider_selectedItemsList, selectionSize, Joiner.on(", ").join(labels)); //$NON-NLS-1$
            }
        }
        return text;
    }

    /**
     * Fetches the plugin's
     * {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     * AdapterFactoryLabelProvider} .
     * 
     * @return The plugin's
     *         {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     *         AdapterFactoryLabelProvider} .
     */
    private AdapterFactoryLabelProvider getAdapterFactoryLabelProvider() {
        if (adapterFactoryLabelProvider == null) {
            final IEditorPart editor = EclipseUIUtil.getActiveEditor();
            if (editor instanceof AbstractDTreeEditor) {
                adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(((AbstractDTreeEditor) editor).getAdapterFactory());
            }
        }
        return adapterFactoryLabelProvider;
    }

    /**
     * Unwrap semantic elements : return the selection where
     * {@link DSemanticDecorator} have been replaced by their target.
     * 
     * @param selection
     *            The selected elements.
     * @return a list where the {@link DSemanticDecorator} have been replaced by
     *         their targets.
     */
    private List<?> extractSemanticSelection(List<?> selection) {
        List<Object> semanticSelection = new ArrayList<>();
        for (Object obj : selection) {
            Object sel = obj;
            if (obj instanceof DSemanticDecorator) {
                EObject target = ((DSemanticDecorator) obj).getTarget();
                if (target != null) {
                    sel = target;
                }
            }
            semanticSelection.add(sel);
        }
        return semanticSelection;
    }

    /**
     * Determines if the objects contained by a given list are of different
     * types.
     * 
     * @param selection
     *            The selected elements.
     * @return <code>True</code> if there are objects of different types in the
     *         structured selection, <code>false</code> otherwise.
     */
    private boolean containsDifferentTypes(final List<?> selection) {
        boolean areDistinct = false;

        if (selection.size() > 1) {
            final Iterator<?> iterator = selection.iterator();
            while (iterator.hasNext()) {
                final Object element = iterator.next();
                if (iterator.hasNext()) {
                    if (iterator.next().getClass() != element.getClass()) {
                        areDistinct = true;
                    }
                }
            }
        }

        return areDistinct;
    }

}
