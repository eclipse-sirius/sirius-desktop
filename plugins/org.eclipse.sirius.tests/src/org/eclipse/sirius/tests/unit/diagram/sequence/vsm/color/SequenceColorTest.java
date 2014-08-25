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
package org.eclipse.sirius.tests.unit.diagram.sequence.vsm.color;

import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.tests.support.api.AbstractColorReferenceTestCase;

/**
 * Test cardinality and initialization of Color references of meta-model Sequence.
 * 
 * @author mporhel
 */
public class SequenceColorTest extends AbstractColorReferenceTestCase {

    @Override
    protected void setUp() throws Exception {
        setBasePackage(SequencePackage.eINSTANCE);
        super.setUp();
    }
}
