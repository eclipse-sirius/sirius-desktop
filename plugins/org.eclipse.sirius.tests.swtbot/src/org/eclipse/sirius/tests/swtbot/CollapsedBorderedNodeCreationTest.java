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
package org.eclipse.sirius.tests.swtbot;

/**
 * Tests defined to ensure that COLLAPSED bordered nodes are created at expected
 * locations (collapsed or not, with zoom or not, with scroll bar or not).
 * 
 * @author lredor
 */
public class CollapsedBorderedNodeCreationTest extends BorderedNodeCreationTest {
    /**
     * Constructor to set environment.
     */
    public CollapsedBorderedNodeCreationTest() {
        setCreateCollapsedBorderedNode(true);
    }
}
