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

package org.eclipse.sirius.ext.emf.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * Clients that want to provide their own <code>CellEditor</code> within the
 * Sirius semantic properties views should implement this interface. Customized
 * <code>CellEditor</code> can also be used in other editors properties view by
 * using the <code>ExtensiblePropertySource</code> instead of the default EMF
 * <code>PropertySource</code>.
 * 
 * @author Florian Barbin
 */
public interface ICellEditorProvider {

    /**
     * Returns whether this provider overwrites the cellEditor for the given
     * <code>IItemPropertyDescriptor</code> for the given eObject.
     * 
     * @param eObject
     *            the semantic eObject.
     * @param itemPropertyDescriptor
     *            the emf item property descriptor.
     * @return true if it provides a CellEditor, false otherwise.
     */
    boolean provides(EObject eObject, IItemPropertyDescriptor itemPropertyDescriptor);

    /**
     * Provides the CellEditor for the corresponding Property Descriptor.
     * 
     * @param eObject
     *            the semantic eObject for which the properties are displayed.
     * @param propertyDescriptor
     *            the Item Property Descriptor.
     * @param parent
     *            the parent composite.
     * @return the cell editor.
     */
    CellEditor getCellEditor(EObject eObject, IItemPropertyDescriptor propertyDescriptor, Composite parent);

}
