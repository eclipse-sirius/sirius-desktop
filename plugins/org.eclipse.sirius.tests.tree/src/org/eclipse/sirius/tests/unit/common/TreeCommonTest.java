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
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.sirius.tests.support.api.TreeTestCase;

/**
 * @author jdupont
 * 
 */
public abstract class TreeCommonTest extends TreeTestCase implements TreeEcoreModeler {

    /**
     * Number elements of first level in tree representation.
     */
    protected static final int ELEMENTS_NUMBER_IN_TREE = 8;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
    }

}
