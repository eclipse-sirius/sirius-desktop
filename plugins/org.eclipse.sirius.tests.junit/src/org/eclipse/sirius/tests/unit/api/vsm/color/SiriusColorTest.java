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
package org.eclipse.sirius.tests.unit.api.vsm.color;

import org.eclipse.sirius.tests.support.api.AbstractColorReferenceTestCase;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Test cardinality and initialization of Color references of meta-model Sirius.
 * 
 * @author mporhel
 */
public class SiriusColorTest extends AbstractColorReferenceTestCase {

    @Override
    protected void setUp() throws Exception {
        setBasePackage(ViewpointPackage.eINSTANCE);
        super.setUp();
    }
}
