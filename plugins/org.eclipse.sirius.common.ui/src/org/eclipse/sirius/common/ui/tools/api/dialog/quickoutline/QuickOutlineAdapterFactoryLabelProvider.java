/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * The label provider for the Quick Outline.
 * 
 * @author bgrouhan
 *
 */
public class QuickOutlineAdapterFactoryLabelProvider extends AdapterFactoryLabelProvider implements IQuickOutlineLabelProvider {
    private Multimap<Object, String> multimap;

    /**
     * Contructor.
     * 
     * @param adapterFactory
     *            the adapter factory.
     */
    public QuickOutlineAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
        multimap = HashMultimap.create();
    }

    @Override
    public String getText(Object element) {
        StringBuilder sb = new StringBuilder(super.getText(element));
        if (multimap.containsKey(element)) {
            sb.append(" (found in ");
            Joiner joiner = Joiner.on(", ");
            sb.append(joiner.join(multimap.get(element).toArray()));
            sb.append(")");
        }
        return sb.toString();
    }

    @Override
    public void addMatch(Object matchingElement, String attributeName) {
        multimap.put(matchingElement, attributeName);
    }

    @Override
    public void clear() {
        multimap.clear();
    }
}
