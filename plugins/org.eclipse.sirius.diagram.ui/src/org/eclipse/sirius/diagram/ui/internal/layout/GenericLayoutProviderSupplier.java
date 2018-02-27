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
package org.eclipse.sirius.diagram.ui.internal.layout;

import java.util.function.Supplier;

import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;

/**
 * Component providing instances of {@link DefaultLayoutProvider} and associated label to use in UI.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class GenericLayoutProviderSupplier {
    /**
     * Label to use in Sirius UI to represent this layout algorithm provider.
     */
    private String label;

    /**
     * Provide instances of {@link DefaultLayoutProvider}.
     */
    private Supplier<DefaultLayoutProvider> layoutProviderSupplier;

    /**
     * Initialize the component.
     * 
     * @param label
     *            Label to use in Sirius UI to represent this layout algorithm provider.
     * @param layoutProviderSupplier
     *            Provide instances of {@link DefaultLayoutProvider}.
     */
    public GenericLayoutProviderSupplier(String label, Supplier<DefaultLayoutProvider> layoutProviderSupplier) {
        super();
        this.label = label;
        this.layoutProviderSupplier = layoutProviderSupplier;
    }

    /**
     * Returns the supplier providing {@link DefaultLayoutProvider} instances.
     * 
     * @return the supplier providing {@link DefaultLayoutProvider} instances.
     */
    public Supplier<DefaultLayoutProvider> getLayoutProviderSupplier() {
        return layoutProviderSupplier;
    }

    /**
     * Returns the label to use in Sirius UI to represent this layout algorithm provider.
     * 
     * @return the label to use in Sirius UI to represent this layout algorithm provider.
     */
    public String getLabel() {
        return label;
    }

}
