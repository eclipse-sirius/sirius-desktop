/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.command.ui;

import java.util.Collection;

import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * This interface define filters saying whether a {@link DRepresentation} should
 * be refreshed or not. It is used in the {@link EMFCommandFactory} in order to
 * filter closed viewpoints and set a value causing later refreshes.
 * 
 * @author cbrun
 * 
 */
public interface RefreshFilter {
    /**
     * Tells whether this {@link DRepresentation} should be refreshed or not.
     * 
     * @param representation
     *            current {@link DRepresentation}
     * @return true if it should automatically be refreshed, false otherwise.
     */
    boolean shouldRefresh(DRepresentation representation);

    /**
     * Get all {@link DRepresentation} that should be refreshed.
     * 
     * @return The list of representations to refresh
     */
    Collection<DRepresentation> getOpenedRepresantationsToRefresh();
}
