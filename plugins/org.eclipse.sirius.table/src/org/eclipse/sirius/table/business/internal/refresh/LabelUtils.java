/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;

/**
 * Util class to convert objects into text.
 * 
 * @author cbrun
 *
 */
public final class LabelUtils {
    
    private LabelUtils() {
    }

    /**
     * Returns text of provided value.
     * <p>
     * <ul>
     * <li>For collection, elements separated by comma</li>
     * <li>For EObject, EMF edit label</li>
     * <li>For null, empty text</li>
     * <li>For others, toString() result</li>
     * </ul>
     * 
     * @param value object to display.
     * @return
     */
    public static String getValueText(Object value) {
        String result = ""; //$NON-NLS-1$
        if (value instanceof Collection) {
            List<String> texts = new ArrayList<>();
            for (Object obj : (Collection<?>) value) {
                texts.add(getValueText(obj));
            }
            result = texts.toString();
        } else if (value instanceof EObject) {
            result = getLabel((EObject) value);
        } else if (value != null) {
            result = value.toString();
        }
        return result;
    }

    
    private static String getLabel(final EObject element) {
        String text = null;
        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(element, IItemLabelProvider.class);
        try {
            IItemLabelProvider labelProvider;
            if (itemLabelProvider != null) {
                labelProvider = new AdapterFactoryItemDelegator(adapterFactory);
            } else {
                labelProvider = new ReflectiveItemProvider(adapterFactory);                
            }
            text = labelProvider.getText(element);
        } finally {
            adapterFactory.dispose();
        }
        return text;
    }
    
}
