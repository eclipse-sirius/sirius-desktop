/**
 * Copyright (c) 2015 Obeo
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

import com.google.common.collect.Lists;

/**
 * Class owning some utility methods about tools.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public abstract class AbstractToolDescriptionTestCase extends SiriusTestCase {

    /**
     * Check expected selected elements.
     * 
     * @param editor
     *            the editor
     * @param objectsNameToCheck
     *            object names to check
     * @param expectedSize
     *            expected size
     */
    protected void checkExpectedElementsInSelection(DialectEditor editor, List<String> objectsNameToCheck, int expectedSize) {
        checkExpectedElementsInSelection(editor, objectsNameToCheck, expectedSize, false);
    }

    /**
     * Check expected selected elements.
     * 
     * @param editor
     *            the editor
     * @param objectsNameToCheck
     *            object names to check
     * @param expectedSize
     *            expected size
     * @param semanticObjectName
     *            true if objectsNameToCheck contains object names of the
     *            semantic object at the root of selected elements
     */
    protected void checkExpectedElementsInSelection(DialectEditor editor, List<String> objectsNameToCheck, int expectedSize, boolean semanticObjectName) {
        List<DSemanticDecorator> selections = Lists.newArrayList(DialectUIManager.INSTANCE.getSelection(editor));

        assertEquals("Bad selection size, after tool execution.", expectedSize, selections.size());

        if (objectsNameToCheck != null) {
            for (int i = 0; i < objectsNameToCheck.size(); i++) {
                String objectNameToCheck = objectsNameToCheck.get(i);
                String selectionName = "";
                if (semanticObjectName) {
                    EObject target = selections.get(i).getTarget();
                    if (target != null) {
                        selectionName = target.toString();
                    }
                } else {
                    selectionName = selections.get(i).toString();
                }
                assertTrue("Bad selection after tool execution. Missing object :" + objectNameToCheck, selectionName.contains(objectNameToCheck));
            }
        }
    }

    /**
     * Change the ElementsToSelect informations.
     * 
     * @param tool
     *            the tool
     * @param selectElementsExpression
     *            the new interpreted expression
     * @param inverseSelection
     *            the new inverse selection datum
     */
    protected void changeSelectionExpression(final AbstractToolDescription tool, final String selectElementsExpression, final boolean inverseSelection) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                tool.setElementsToSelect(selectElementsExpression);
                tool.setInverseSelectionOrder(inverseSelection);
            }
        });
    }
}
