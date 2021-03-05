/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.table.celleditorfactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.ui.tools.api.editor.ITableCellEditorFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;

/**
 * A {@link ITableCellEditorFactory} for the EType of an EAttribute. It returns a combo box with all the available EType
 * for an {@link EAttribute}.
 * 
 * @author lredor
 */
public class EAttributeTypeCellEditorFactory implements ITableCellEditorFactory {

    private final AdapterFactory adapterFactory;

    /**
     * Default constructor.
     */
    public EAttributeTypeCellEditorFactory() {
        final List<ComposedAdapterFactory> factories = new ArrayList<ComposedAdapterFactory>();
        // Add all factories register plug-in registration
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        // Add all dialect factories
        factories.add(new ComposedAdapterFactory(DialectUIManager.INSTANCE.createAdapterFactory()));
        this.adapterFactory = new ComposedAdapterFactory(factories);

    }
    @Override
    public CellEditor getCellEditor(Tree tree, Map<String, Object> parameters) {
        CellEditor result = null;
        Object element = parameters.get(IInterpreterSiriusVariables.ELEMENT);
        if (element instanceof DCell) {
            DCell dCell = (DCell) element;
            if (dCell.getTarget() instanceof EAttribute) {
                EAttribute editedAttribute = (EAttribute) dCell.getTarget();
                // final EClassifier eClassifier = EcorePackage.eINSTANCE.getEAttribute_EAttributeType().getEType();
                final IItemPropertyDescriptor iItemPropertyDescriptor = getPropertyDescriptor(editedAttribute);

                // We reuse the list of the ItemPropertyDescriptor for
                // populate the combo
                result = new ExtendedComboBoxCellEditor(tree, new ArrayList<Object>(iItemPropertyDescriptor.getChoiceOfValues(editedAttribute)),
                        getLabelProvider(editedAttribute, iItemPropertyDescriptor), iItemPropertyDescriptor.isSortChoices(editedAttribute)) {
                    @Override
                    public void doSetValue(Object value) {
                        // Do not use the string value of the DCell but the current EType of the eAttribute.
                        super.doSetValue(editedAttribute.getEType());
                    }
                };
            }
        }
        return result;
    }

    /**
     * @param currentEditedAttribute
     *            the {@link EAttribute} to edit.
     * @param itemPropertyDescriptor
     *            the itemPropertyDescriptor for the eType field of the {@link EAttribute}
     * @return A label provider
     */
    private ILabelProvider getLabelProvider(final EAttribute currentEditedAttribute, IItemPropertyDescriptor itemPropertyDescriptor) {
        return new LabelProvider() {
            @Override
            public String getText(final Object object) {
                if (itemPropertyDescriptor != null && object != null) {
                    return itemPropertyDescriptor.getLabelProvider(object).getText(object);
                } else {
                    return super.getText(object);
                }
            }

            @Override
            public Image getImage(final Object object) {
                if (itemPropertyDescriptor != null) {
                    return ExtendedImageRegistry.getInstance().getImage(itemPropertyDescriptor.getLabelProvider(object).getImage(object));
                } else {
                    return null;
                }
            }
        };
    }

    /**
     * Return the propertyDescriptor corresponding to the EType feature for the instance
     *
     * @param currentEditedAttribute
     *            the {@link EAttribute} to edit.
     * @return the propertyDescriptor corresponding to the EType feature of this column for the instance or null
     */
    private IItemPropertyDescriptor getPropertyDescriptor(final EAttribute currentEditedAttribute) {
        if (currentEditedAttribute == null) {
            return null;
        }
        final EStructuralFeature structuralFeature = EcorePackage.eINSTANCE.getEAttribute_EAttributeType();
        final IItemPropertySource propertySource = (IItemPropertySource) adapterFactory.adapt(currentEditedAttribute, IItemPropertySource.class);
        IItemPropertyDescriptor propertyDescriptor = null;
        if (propertySource != null) {
            final Iterator<?> iterDescriptors = propertySource.getPropertyDescriptors(currentEditedAttribute).iterator();
            while (iterDescriptors.hasNext() && propertyDescriptor == null) {
                final IItemPropertyDescriptor currentPropertyDescriptor = (IItemPropertyDescriptor) iterDescriptors.next();
                final Object currentFeature = currentPropertyDescriptor.getFeature(currentEditedAttribute);
                if (currentFeature != null && currentFeature.equals(structuralFeature)) {
                    propertyDescriptor = currentPropertyDescriptor;
                }
            }
        }
        return propertyDescriptor;
    }
}
