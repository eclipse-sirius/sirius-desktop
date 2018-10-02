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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticEdgeFormatDataKey;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class SemanticEdgeFormatDataKeyTest extends AbstractSemanticFormatDataKeyTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected FormatDataKey createFormatDataKey(final EObject eObject) {
        return new SemanticEdgeFormatDataKey(eObject);
    }

}
