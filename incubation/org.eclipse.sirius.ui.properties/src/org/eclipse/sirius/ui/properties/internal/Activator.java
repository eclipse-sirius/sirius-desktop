/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Bundle activator for <code>org.eclipse.sirius.ui.properties</code>.
 */
public class Activator implements BundleActivator {
    /**
     * The adapter is stateless, use a single instance that can be easily
     * unregistered when stopped.
     */
    private final SiriusSemanticAdapter adapterFactory = new SiriusSemanticAdapter();

    @Override
    public void start(BundleContext context) throws Exception {
        IAdapterManager adapterManager = Platform.getAdapterManager();
        adapterManager.registerAdapters(adapterFactory, DSemanticDecorator.class);
        adapterManager.registerAdapters(adapterFactory, GraphicalEditPart.class);
        adapterManager.registerAdapters(adapterFactory, ConnectionEditPart.class);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        IAdapterManager adapterManager = Platform.getAdapterManager();
        adapterManager.unregisterAdapters(adapterFactory);
    }
}
