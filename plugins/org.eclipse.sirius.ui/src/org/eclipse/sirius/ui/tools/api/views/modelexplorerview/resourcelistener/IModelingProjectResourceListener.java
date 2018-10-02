/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener;

import org.eclipse.core.resources.IResourceChangeListener;

/**
 * A workspace resource listener used to react to changes made on a
 * ModelingProject (add/remove resources...). Typically used to automatically
 * add new resources as semantic resources of the target ModelingProject.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public interface IModelingProjectResourceListener extends IResourceChangeListener {

    /**
     * Installs the listener.
     */
    void init();

    /**
     * Disposes the listener.
     */
    void dispose();
}
