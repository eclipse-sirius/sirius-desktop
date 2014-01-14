/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

/**
 * Label provider of the tabbed property sheet page.
 */
public class ViewpointLabelProvider extends LabelProvider {
    /**
     * Plugin's
     * {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     * AdapterFactoryLabelProvider}.
     */
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    /**
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(Object object) {
        Image labelImage = null;

        if (object != null && !object.equals(StructuredSelection.EMPTY)) {
            if (object instanceof IStructuredSelection) {
                IStructuredSelection structuredSelection = (IStructuredSelection) object;
                if (!containsDifferentTypes(structuredSelection))
                    object = structuredSelection.getFirstElement();
            }

            if (object instanceof EObject || object instanceof Resource) {
                labelImage = getAdapterFactoryLabelProvider().getImage(object);
            }
        }

        return labelImage;
    }

    /**
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(Object object) {
        String text = null;
        int selectionSize = 0;

        if (object != null && !object.equals(StructuredSelection.EMPTY)) {
            if (object instanceof IStructuredSelection) {
                IStructuredSelection structuredSelection = (IStructuredSelection) object;
                selectionSize = structuredSelection.size();
                if (selectionSize == 1 && structuredSelection.getFirstElement() instanceof EObject) {
                    object = structuredSelection.getFirstElement();
                }
                if (containsDifferentTypes(structuredSelection))
                    text = selectionSize + " items selected";//$NON-NLS-1$
            }
        }

        if (object != null)
            text = getAdapterFactoryLabelProvider().getText(object);
        if (selectionSize > 1) {
            text = selectionSize + " [";
            for (Iterator iterator = ((IStructuredSelection) object).iterator(); iterator.hasNext();) {
                text += getAdapterFactoryLabelProvider().getText(iterator.next());
                if (iterator.hasNext())
                    text += ", ";
            }
            text += "] selected";
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
            adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(((SiriusEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getAdapterFactory());
        }
        return adapterFactoryLabelProvider;
    }

    /**
     * Determines if the objects contained by a given
     * {@link org.eclipse.jface.viewers.IStructuredSelection structured
     * selection} are of different types.
     * 
     * @param structuredSelection
     *            The structured selection.
     * @return <code>True</code> if there are objects of different types in the
     *         structured selection, <code>false</code> otherwise.
     */
    private boolean containsDifferentTypes(IStructuredSelection structuredSelection) {
        boolean areDistinct = false;
        final List selectionList = structuredSelection.toList();

        if (selectionList.size() > 1) {
            for (Iterator iterator = selectionList.iterator(); iterator.hasNext();) {
                Object element = iterator.next();
                if (iterator.hasNext()) {
                    if (iterator.next().getClass() != element.getClass())
                        areDistinct = true;
                }
            }
        }

        return areDistinct;
    }

    // Start of user code methods

    // End of user code methods

}
