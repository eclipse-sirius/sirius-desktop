/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.layout.data;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SemanticNodeLayoutDataKey;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class SemanticNodeLayoutDataKeyTest extends AbstractSemanticLayoutDataKeyTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected LayoutDataKey createLayoutDataKey(final EObject eObject) {
        return new SemanticNodeLayoutDataKey(eObject);
    }

}
