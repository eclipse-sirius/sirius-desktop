/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.keys;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNode;

/**
 * Specific key for {@link DNode}.
 * 
 * @author mporhel
 */
public class SampleNodeFormatDataKey extends AbstractSampleFormatDataKey {

    /**
     * Default constructor.
     * 
     * @param key
     *            The key
     */
    public SampleNodeFormatDataKey(final EObject key) {
        super(key);
    }
}
