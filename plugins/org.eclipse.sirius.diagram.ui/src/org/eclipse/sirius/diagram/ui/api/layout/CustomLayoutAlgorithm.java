/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
     * The algorithm's label.
     */
    private String label;

    /**
     * The algorithm's description.
     */
    private String description;

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
     *            the algorithm's label.
     * @param description
     *            the algorithm's description.
     * @param layoutSupplier
     *            a supplier of {@link DefaultLayoutProvider} that contains the layout algorithm.
     * @param layoutOptions
     *            the options allowing to configure the layout algorithm behavior. Use the factory
     *            {@link LayoutOptionFactory} to create it.
     */
    public CustomLayoutAlgorithm(String id, String label, String description, Supplier<DefaultLayoutProvider> layoutSupplier, Map<String, LayoutOption> layoutOptions) {
        super();
        this.id = id;
        this.label = label;
        this.layoutSupplier = layoutSupplier;
        this.layoutOptions = layoutOptions;
        this.description = description;
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
     * Returns the algorithm's label.
     * 
     * @return the algorithm's label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the algorithm's description.
     * 
     * @return the algorithm's description.
     */
    public String getDescription() {
        return description;
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

    @Override
    public String toString() {
        return this.id;
    }

}
