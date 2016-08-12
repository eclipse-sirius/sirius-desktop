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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticNodeFormatDataKey;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class SemanticNodeFormatDataKeyTest extends AbstractSemanticFormatDataKeyTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected FormatDataKey createFormatDataKey(final EObject eObject) {
        return new SemanticNodeFormatDataKey(eObject);
    }

}
