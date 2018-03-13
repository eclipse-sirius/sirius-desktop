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
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.provider.BooleanLayoutOptionItemProvider;

/**
 * Customize the label of {@link BooleanLayoutOption} items in VSM editor.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class BooleanLayoutOptionItemProviderSpec extends BooleanLayoutOptionItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            factory to use.
     */
    public BooleanLayoutOptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        BooleanLayoutOption layout = (BooleanLayoutOption) object;
        return layout.getLabel() + ": " + Boolean.valueOf(layout.isValue()); //$NON-NLS-1$
    }

}
