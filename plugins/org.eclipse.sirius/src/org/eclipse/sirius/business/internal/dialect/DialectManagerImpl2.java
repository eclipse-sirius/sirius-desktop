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
package org.eclipse.sirius.business.internal.dialect;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.sirius.business.api.dialect.Dialect;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Temporary class to override the DialectManagerImpl and extending the non API
 * {@link DialectServices2}. This class will be merged with
 * {@link DialectManagerImpl} as soon as all the dialect implement
 * {@link DialectServices2#refreshImpactedElements(org.eclipse.sirius.viewpoint.DRepresentation, java.util.Collection, org.eclipse.core.runtime.IProgressMonitor)}
 * .
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DialectManagerImpl2 extends DialectManagerImpl implements DialectServices2 {

    /**
     * Init a default manager implementation.
     * 
     * @return a default manager implementation
     */
    public static DialectManager init() {
        final DialectManagerImpl2 manager = new DialectManagerImpl2();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<Dialect> parsedDialects = EclipseUtil.getExtensionPlugins(Dialect.class, DialectManager.ID, DialectManager.CLASS_ATTRIBUTE);
            for (final Dialect dialect : parsedDialects) {
                manager.enableDialect(dialect);
            }
        }
        return manager;
    }

    @Override
    public void refreshImpactedElements(DRepresentation representation, Collection<Notification> notifications, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Refresh impacted representation elements", 1);
            for (final Dialect dialect : dialects.values()) {
                if (dialect.getServices().canRefresh(representation)) {
                    if (dialect.getServices() instanceof DialectServices2) {
                        ((DialectServices2) dialect.getServices()).refreshImpactedElements(representation, notifications, new SubProgressMonitor(monitor, 1));
                    }
                }
            }
        } finally {
            monitor.done();
        }
    }
}
