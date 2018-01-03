/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;

/**
 * Handler to reload VSMs of installed plug-ins.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ReloadVSMHandler extends AbstractHandler {
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ViewpointRegistry.getInstance().reloadAllFromPlugins();
        return null;
    }

    @Override
    public boolean isEnabled() {
        // This handler is enabled only if the eclipse is a runtime application launched with pde (from a launch config
        // for example)
        return Boolean.getBoolean("eclipse.pde.launch"); //$NON-NLS-1$
    }

}
