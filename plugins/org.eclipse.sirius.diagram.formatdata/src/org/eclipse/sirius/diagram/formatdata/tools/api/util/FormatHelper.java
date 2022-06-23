/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.formatdata.tools.api.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.internal.util.FormatHelperImpl;

/**
 * Helper to manage the format data.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public interface FormatHelper {

    interface FormatDifference<T> {

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
    FormatHelper INSTANCE = new FormatHelperImpl();

    /**
     * Check if two node formats have the same layout.
     * 
     * @param nodeFormat1
     *            The first nodeFormat to check
     * @param nodeFormat2
     *            The second nodeFormat to check
     * @param configuration
     *            Configuration for this search.
     * @return true if the two node formats have same layout, false otherwise.
     */
    boolean haveSameLayout(NodeFormatData nodeFormat1, NodeFormatData nodeFormat2, Configuration configuration);

    /**
     * Compute the first difference between two collections of node formats.
     * <p>
     * Elements <strong>must be</strong> in the same order.
     * </p>
     * 
     * @param col1
     *            The first collection of nodeFormat to check
     * @param col2
     *            The second collection nodeFormat to check
     * @param configuration
     *            Configuration for this search.
     * @return First difference found. <code>null</code> if no difference is
     *         found.
     */
    FormatDifference<?> computeFirstFormatDifference(Collection<? extends EObject> col1, Collection<? extends EObject> col2, Configuration configuration);

    /**
     * Check if two collections of node formats have the same layout.
     * <p>
     * Elements <strong>must be</strong> in the same order.
     * </p>
     * 
     * @param col1
     *            The first collection of nodeFormat to check
     * @param col2
     *            The second collection nodeFormat to check
     * @param configuration
     *            Configuration for this search.
     * @return true if the two collections EObjects contains node formats and
     *         have same layout, false otherwise.
     */
    boolean haveSameLayout(Collection<? extends EObject> col1, Collection<? extends EObject> col2, Configuration configuration);

    /**
     * Check if two edge formats have the same layout.
     * 
     * @param edgeFormat1
     *            The first edgeFormat to check
     * @param edgeFormat2
     *            The second edgeFormat to check
     * @param configuration
     *            Configuration for this search.
     * @return true if the two edge formats have same layout, false otherwise.
     */
    boolean haveSameLayout(EdgeFormatData edgeFormat1, EdgeFormatData edgeFormat2, Configuration configuration);

    /**
     * Check if two collections of edge formats have the same layout.
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
     * @return true if the two collections of edge formats have same layout,
     *         false otherwise.
     */
    // boolean haveSameLayout(Collection<EdgeFormatData> col1,
    // Collection<EdgeFormatData> col2, Configuration configuration);
}
