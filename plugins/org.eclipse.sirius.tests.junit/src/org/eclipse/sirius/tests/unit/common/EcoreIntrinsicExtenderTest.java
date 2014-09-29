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
package org.eclipse.sirius.tests.unit.common;

import java.util.Collections;

import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.junit.Test;

/**
 * Unit tests for {@link EcoreIntrinsicExtender}.
 * 
 * @author laurent.redor@obeo.fr
 */
public class EcoreIntrinsicExtenderTest {

    /**
     * This test has been added to detect problem during the first update of the
     * metamodels.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void udpateMetamodels() {
        EcoreIntrinsicExtender extender = new EcoreIntrinsicExtender();
        extender.updateMetamodels(Collections.EMPTY_LIST);
    }
}
