/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
