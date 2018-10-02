/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
