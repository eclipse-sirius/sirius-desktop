/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
