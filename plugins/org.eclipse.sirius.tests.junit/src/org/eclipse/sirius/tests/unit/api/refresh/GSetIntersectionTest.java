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
package org.eclipse.sirius.tests.unit.api.refresh;

import org.eclipse.sirius.ext.base.collect.GSetIntersection;

public class GSetIntersectionTest extends SetIntersectionTest {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.biSet = new GSetIntersection<String>();
    }
}
