/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.tree.vsm.color;

import org.eclipse.sirius.tests.support.api.AbstractColorReferenceTestCase;
import org.eclipse.sirius.tree.TreePackage;

/**
 * Test cardinality and initialization of Color references of meta-model Tree.
 * 
 * @author mporhel
 */
public class TreeColorTest extends AbstractColorReferenceTestCase {

    @Override
    protected void setUp() throws Exception {
        setBasePackage(TreePackage.eINSTANCE);
        super.setUp();
    }
}
