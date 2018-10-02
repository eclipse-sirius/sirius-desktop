/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.gef.RootEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.ComparisonFailure;

/**
 * Condition testing the number of diagram elements is the expected one.
 * 
 * @author smonnier
 */
public class DiagramWithChildrensCondition extends DefaultCondition {

    private final SWTBotSiriusDiagramEditor editor;

    private final int expectedChildrenOnDiagramLevel;

    /**
     * Default constructor.
     * 
     * @param editor
     *            the current editor
     * @param expectedChildrenOnDiagramLevel
     *            expected number of children on diagram level
     */
    public DiagramWithChildrensCondition(SWTBotSiriusDiagramEditor editor, int expectedChildrenOnDiagramLevel) {
        super();
        this.editor = editor;
        this.expectedChildrenOnDiagramLevel = expectedChildrenOnDiagramLevel;
    }

    @Override
    public boolean test() throws Exception {
        return ((RootEditPart) editor.rootEditPart().part()).getContents().getChildren().size() == expectedChildrenOnDiagramLevel;
    }

    @Override
    public String getFailureMessage() {
        return new ComparisonFailure("There is not the expected number of children on the diagram level", Integer.toString(expectedChildrenOnDiagramLevel), Integer.toString(((RootEditPart) editor
                .rootEditPart().part()).getContents().getChildren().size())).getMessage();
    }
}
