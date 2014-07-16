/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.dialect;

import java.util.List;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Factory creating the CanonicalSynchronizerFactory to use system-wide.
 * 
 * @author cedric
 */
public final class CanonicalSynchronizerFactoryImpl {

    private static final class NullCanonicalSynchronizerFactory implements CanonicalSynchronizerFactory {
        @Override
        public CanonicalSynchronizer createCanonicalSynchronizer(Diagram gmfDiagram) {
            return new CanonicalSynchronizer() {
                @Override
                public void synchronize() {
                    // Do nothing.
                }

                @Override
                public void storeViewsToArrange(boolean storeViewsToArrange) {
                    // Do nothing.
                }

                @Override
                public void postCreation() {
                    // Do nothing.
                }
            };
        }
    }

    private CanonicalSynchronizerFactoryImpl() {
        // Prevent instanticiation
    }

    /**
     * Always return a {@link CanonicalSynchronizerFactory}, either the one
     * overridden by some other plugins or a factory which will create NO-OP
     * {@link CanonicalSynchronizer}.
     * 
     * @return a CanonicalSynchronizerFactory.
     */
    public static CanonicalSynchronizerFactory init() {
        CanonicalSynchronizerFactory result = new NullCanonicalSynchronizerFactory();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            List<CanonicalSynchronizerFactory> contributedFactories = EclipseUtil.getExtensionPlugins(CanonicalSynchronizerFactory.class, CanonicalSynchronizerFactory.ID,
                    CanonicalSynchronizerFactory.CLASS_ATTRIBUTE);
            if (contributedFactories.size() > 0) {
                result = contributedFactories.get(0);
            }
        }
        return result;
    }
}
