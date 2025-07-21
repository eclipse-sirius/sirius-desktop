/*******************************************************************************
 * Copyright (c) 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.style;

/**
 * No change compared to {@link NodeSizeTest}, indeed, the snapToGrid has no impact on existing elements.
 * 
 * @author mporhel
 */
public class NodeSizeWithSnapToGridTest extends NodeSizeTest {
    @Override
    protected boolean isSnapToGrid() {
        return true;
    }
}
