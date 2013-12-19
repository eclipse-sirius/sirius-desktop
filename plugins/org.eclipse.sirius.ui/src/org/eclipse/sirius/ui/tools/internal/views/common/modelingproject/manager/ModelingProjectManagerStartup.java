/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.manager;

import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.ui.IStartup;

/**
 * A startup to initialize the ModelingProjectManager.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ModelingProjectManagerStartup implements IStartup {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IStartup#earlyStartup()
     */
    public void earlyStartup() {
        ModelingProjectManager.INSTANCE.initializeAfterLoad();
    }
}
