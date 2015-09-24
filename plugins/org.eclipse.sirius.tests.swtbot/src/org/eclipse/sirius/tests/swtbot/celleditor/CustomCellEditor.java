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
package org.eclipse.sirius.tests.swtbot.celleditor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.sirius.ext.emf.ui.ICellEditorProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * ICellEditorProvider implementation for the SWTBot test.
 * 
 * @author fbarbin
 */
public class CustomCellEditor implements ICellEditorProvider {

    @Override
    public boolean provides(EObject eObject, IItemPropertyDescriptor itemPropertyDescriptor) {
        Object feature = itemPropertyDescriptor.getFeature(eObject);
        if (feature == EcorePackage.eINSTANCE.getENamedElement_Name()) {
            return true;
        }
        return false;
    }

    @Override
    public CellEditor getCellEditor(EObject eObject, IItemPropertyDescriptor propertyDescriptor, Composite parent) {
        TextCellEditor cellEditor = new TextCellEditor(parent) {
            protected void doSetValue(Object value) {
                super.doSetValue(value + "custom");
            };
        };
        return cellEditor;
    }

}
