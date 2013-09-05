/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration;

/**
 * Configuration to use with
 * {@link org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper}
 * methods.
 * 
 * @author dlecan
 */
public interface Configuration {

    /**
     * Get {@link NodeConfiguration}.
     * 
     * @return {@link NodeConfiguration}.
     */
    NodeConfiguration getNodeConfiguration();

    /**
     * Get {@link EdgeConfiguration}.
     * 
     * @return {@link EdgeConfiguration}.
     */
    EdgeConfiguration getEdgeConfiguration();

    /**
     * Set if search must be recursive.
     * <p>
     * This value is <code>true</code> by default.
     * </p>
     * <p>
     * The node children and the outgoing edges will be compared if search has
     * to be recusrsive.
     * </p>
     * 
     * @param recursively
     *            Set this to <code>true</code> if search must recursive,
     *            <code>false</code> otherwise.
     */
    void setRecursively(boolean recursively);

    /**
     * Returns the recursively.
     * 
     * @return The recursively.
     */
    boolean isRecursive();

}
