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
package org.eclipse.sirius.tests.swtbot.suite;

import org.eclipse.sirius.tests.swtbot.uml.UmlClassDragAndDropTest;
import org.eclipse.sirius.tests.swtbot.uml.UmlPackageDragAndDropTest;
import org.eclipse.sirius.tests.swtbot.uml.UmlPortDragAndDropTest;
import org.eclipse.sirius.tests.swtbot.uml.UmlPortMoveTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * 
 * @author lredor
 */
public class UMLSwtbotTestSuite extends TestCase {
    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("UML SWTBOT test suite");
        suite.addTestSuite(UmlClassDragAndDropTest.class);
        suite.addTestSuite(UmlPackageDragAndDropTest.class);
        suite.addTestSuite(UmlPortMoveTest.class);
        suite.addTestSuite(UmlPortDragAndDropTest.class);
        return suite;
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * disabled test.
     * 
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("UML Disabled SwtBot tests");

        return suite;
    }
}
