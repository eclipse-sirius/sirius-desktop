/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
