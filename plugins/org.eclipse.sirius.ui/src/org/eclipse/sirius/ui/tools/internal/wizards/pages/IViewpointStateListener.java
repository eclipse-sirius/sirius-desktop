/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

/**
 * This interface is used by ViewpointsSelectionWizardPage to warn its listeners represented by this interface that the
 * user want to change the activated status of a viewpoint of the corresponding session;.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface IViewpointStateListener {

    /**
     * Warn the listener that caller want to change the viewpoint status of a viewpoint for the corresponding session..
     * 
     * @param viewpointStateChangeEvent
     *            the change event containing the viewpoint that should be updated and the activation status it should
     *            have.
     */
    void viewpointStateChange(ViewpointStateChangeEvent viewpointStateChangeEvent);
}
