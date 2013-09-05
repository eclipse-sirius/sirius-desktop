/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.layoutdata.tools.api.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.layoutdata.tools.internal.util.LayoutHelperImpl;

/**
 * Helper to manage the layout data.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public interface LayoutHelper {

    public interface LayoutDifference<T> {

        /**
         * Get left element of difference.
         * 
         * @return Left element of difference.
         */
        T getLeftElement();

        /**
         * Get right element of difference.
         * 
         * @return Right element of difference.
         */
        T getRightElement();

        /**
         * Message of difference.
         * 
         * @return Message of difference.
         */
        String getMessage();
    }

    /**
     * Singleton.
     */
    LayoutHelper INSTANCE = new LayoutHelperImpl();

    /**
     * Check if two node layouts have the same layout.
     * 
     * @param nodeLayout1
     *            The first nodeLayout to check
     * @param nodeLayout2
     *            The second nodeLayout to check
     * @param configuration
     *            Configuration for this search.
     * @return true if the two node layouts have same layout, false otherwise.
     */
    boolean haveSameLayout(NodeLayoutData nodeLayout1, NodeLayoutData nodeLayout2, Configuration configuration);

    /**
     * Compute the first difference between two collections of node layouts.
     * <p>
     * Elements <strong>must be</strong> in the same order.
     * </p>
     * 
     * @param col1
     *            The first collection of nodeLayout to check
     * @param col2
     *            The second collection nodeLayout to check
     * @param configuration
     *            Configuration for this search.
     * @return First difference found. <code>null</code> if no difference is
     *         found.
     */
    LayoutDifference<?> computeFirstLayoutDifference(Collection<? extends EObject> col1, Collection<? extends EObject> col2, Configuration configuration);

    /**
     * Check if two collections of node layouts have the same layout.
     * <p>
     * Elements <strong>must be</strong> in the same order.
     * </p>
     * 
     * @param col1
     *            The first collection of nodeLayout to check
     * @param col2
     *            The second collection nodeLayout to check
     * @param configuration
     *            Configuration for this search.
     * @return true if the two collections EObjects contains node layouts and
     *         have same layout, false otherwise.
     */
    boolean haveSameLayout(Collection<? extends EObject> col1, Collection<? extends EObject> col2, Configuration configuration);

    /**
     * Check if two edge layouts have the same layout.
     * 
     * @param edgeLayout1
     *            The first edgeLayout to check
     * @param edgeLayout2
     *            The second edgeLayout to check
     * @param configuration
     *            Configuration for this search.
     * @return true if the two edge layouts have same layout, false otherwise.
     */
    boolean haveSameLayout(EdgeLayoutData edgeLayout1, EdgeLayoutData edgeLayout2, Configuration configuration);

    /**
     * Check if two collections of edge layouts have the same layout.
     * <p>
     * Elements <strong>must be</strong> in the same order.
     * </p>
     * 
     * @param col1
     *            The first collection of edge layout to check
     * @param col2
     *            The second collection of edge layout to check
     * @param configuration
     *            Configuration for this search.
     * @return true if the two collections of edge layouts have same layout,
     *         false otherwise.
     */
    // boolean haveSameLayout(Collection<EdgeLayoutData> col1,
    // Collection<EdgeLayoutData> col2, Configuration configuration);
}
