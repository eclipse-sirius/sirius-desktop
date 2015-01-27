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
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.ext.emf.ui.ICellEditorProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * The Extensible Property Descriptor allows clients to provide their own
 * CellEditor by providing a {@link CellEditorProviderCollector}.
 * 
 * @author Florian Barbin
 */
public class ExtensiblePropertyDescriptor extends PropertyDescriptor {

    private CellEditorProviderCollector collector;

    /**
     * Creates a new instance of this class.
     * 
     * @param object
     *            the semantic object.
     * @param itemPropertyDescriptor
     *            the emf itemPropertyDescriptor
     * @param collector
     *            the CellEditorProviderCollector used to retrieve the
     *            ICellEditorProviders
     */
    public ExtensiblePropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor, CellEditorProviderCollector collector) {
        super(object, itemPropertyDescriptor);
        this.collector = collector;
    }

    @Override
    public CellEditor createPropertyEditor(final Composite parent) {
        ICellEditorProvider cellEditorProvider = getFirstCellEditorProvider();
        if (cellEditorProvider == null) {
            return super.createPropertyEditor(parent);
        } else {
            return cellEditorProvider.getCellEditor((EObject) object, itemPropertyDescriptor, parent);
        }
    }

    private ICellEditorProvider getFirstCellEditorProvider() {
        List<ICellEditorProvider> cellEditorProviders = collector.getCellEditorProviders((EObject) object, itemPropertyDescriptor);
        return !cellEditorProviders.isEmpty() ? cellEditorProviders.get(0) : null;
    }
}
