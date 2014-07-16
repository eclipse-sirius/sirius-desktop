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
package org.eclipse.sirius.diagram.ui.internal.refresh.factory;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.DDiagramCanonicalSynchronizer;

/**
 * Singleton implementation of {@link CanonicalSynchronizerFactory}.
 * 
 * @author edugueperoux
 */
public class CanonicalSynchronizerFactoryImpl implements CanonicalSynchronizerFactory {

    /**
     * Provides a {@link DDiagramCanonicalSynchronizer} to synchronize a {@link Diagram}.
     * 
     * {@inheritDoc}
     */
    public CanonicalSynchronizer createCanonicalSynchronizer(Diagram gmfDiagram) {
        CanonicalSynchronizer canonicalSynchronizer = new DDiagramCanonicalSynchronizer(gmfDiagram);
        return canonicalSynchronizer;
    }

}
