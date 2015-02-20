/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.emf.ui.properties;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.sirius.ext.emf.ui.ICellEditorProvider;

/**
 * A collector provides a list of {@link ICellEditorProvider}. This interface is
 * used by the <code>ExtensiblePropertyDescriptor</code> to retrieves (lazily)
 * the registered {@link ICellEditorProvider}.
 * 
 * @author Florian Barbin
 *
 */
public interface CellEditorProviderCollector {

    /**
     * Returns all registered {@link ICellEditorProvider} providing a CellEditor
     * for the given {@link EObject} and {@link IItemPropertyDescriptor} couple.
     * 
     * @param eObject
     *            the model element for which we override one of its property
     *            cell editor.
     * @param itemPropertyDescriptor
     *            the item property descriptor.
     * @return a list of {@link ICellEditorProvider} providing a cellEditor.
     */
    List<ICellEditorProvider> getCellEditorProviders(EObject eObject, IItemPropertyDescriptor itemPropertyDescriptor);

}
