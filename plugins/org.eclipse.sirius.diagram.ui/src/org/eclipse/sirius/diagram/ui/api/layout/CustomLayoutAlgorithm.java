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
package org.eclipse.sirius.diagram.ui.api.layout;

import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.DefaultLayoutProvider;

/**
 * A component providing all needed information to provide a custom layout algorithm that can be used to layout Sirius
 * diagrams.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class CustomLayoutAlgorithm {

    /**
     * The algorithm's id.
     */
    private String id;

    /**
     * The algorithm's label that should be used in UI to present it.
     */
    private String label;

    /**
     * A supplier of {@link DefaultLayoutProvider} that contains the layout algorithm.
     */
    private Supplier<DefaultLayoutProvider> layoutSupplier;

    /**
     * The options allowing to configure the layout algorithm behavior indexed by their name. Use the factory
     * {@link LayoutOptionFactory} to create it.
     */
    private Map<String, LayoutOption> layoutOptions;

    /**
     * Initialize the custom layout algorithm with given information.
     * 
     * @param id
     *            the algorithm's id.
     * @param label
     *            the algorithm's label that should be used in UI to present it.
     * @param layoutSupplier
     *            a supplier of {@link DefaultLayoutProvider} that contains the layout algorithm.
     * @param layoutOptions
     *            the options allowing to configure the layout algorithm behavior. Use the factory
     *            {@link LayoutOptionFactory} to create it.
     */
    public CustomLayoutAlgorithm(String id, String label, Supplier<DefaultLayoutProvider> layoutSupplier, Map<String, LayoutOption> layoutOptions) {
        super();
        this.id = id;
        this.label = label;
        this.layoutSupplier = layoutSupplier;
        this.layoutOptions = layoutOptions;
    }

    /**
     * Returns the algorithm's id.
     * 
     * @return the algorithm's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the algorithm's label that should be used in UI to present it.
     * 
     * @return the algorithm's label that should be used in UI to present it.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the options allowing to configure the layout algorithm behavior indexed by their names.
     * 
     * @return the options allowing to configure the layout algorithm behavior indexed by their names.
     */
    public Map<String, LayoutOption> getLayoutOptions() {
        return layoutOptions;
    }

    /**
     * Returns an instance of {@link DefaultLayoutProvider} that contains the layout algorithm.
     * 
     * @return an instance of {@link DefaultLayoutProvider} that contains the layout algorithm.
     */
    public DefaultLayoutProvider getLayoutAlgorithmInstance() {
        return layoutSupplier.get();
    }
}
