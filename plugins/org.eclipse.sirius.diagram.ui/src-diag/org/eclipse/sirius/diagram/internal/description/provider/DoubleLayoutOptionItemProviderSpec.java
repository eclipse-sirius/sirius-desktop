/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.description.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.provider.DoubleLayoutOptionItemProvider;

/**
 * Customize the label of {@link DoubleLayoutOption} items in VSM editor.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DoubleLayoutOptionItemProviderSpec extends DoubleLayoutOptionItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            factory to use.
     */
    public DoubleLayoutOptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        DoubleLayoutOption layout = (DoubleLayoutOption) object;
        return layout.getLabel() + ": " + layout.getValue(); //$NON-NLS-1$
    }
}
