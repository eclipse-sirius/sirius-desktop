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
package org.eclipse.sirius.tests.swtbot;

/**
 * Tests defined to ensure that COLLAPSED bordered nodes are not created NEAR
 * COLLAPSED BORDERED NODE but at expected locations (collapsed or not, with
 * zoom or not, with scroll bar or not).
 * 
 * @author lredor
 */
public class CollapsedBorderedNodeCreationNearCollapsedTest extends BorderedNodeCreationTest {
    /**
     * Constructor to set environment.
     */
    public CollapsedBorderedNodeCreationNearCollapsedTest() {
        setNearCollapsedBorderedNode(true);
        setCreateCollapsedBorderedNode(true);
    }
}
