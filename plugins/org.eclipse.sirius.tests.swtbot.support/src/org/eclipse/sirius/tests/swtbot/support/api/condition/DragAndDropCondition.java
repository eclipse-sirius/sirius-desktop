/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
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

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Validates that the element has been dropped in the expected container.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class DragAndDropCondition extends DefaultCondition {

    private final String dropedElementName;

    private final Class<? extends EditPart> droppedElementClass;

    private final String targetContainerName;

    private final Class<? extends EditPart> targetContainerClass;

    private final SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param dropedElementName
     *            name of the dropped element
     * @param droppedElementClass
     *            type of the dropped element
     * @param targetContainerName
     *            name of the target container
     * @param targetContainerClass
     *            type of the target container
     * @param editor
     *            used editor for the drag and drop action
     */
    public DragAndDropCondition(String dropedElementName, Class<? extends EditPart> droppedElementClass, String targetContainerName, Class<? extends EditPart> targetContainerClass,
            SWTBotSiriusDiagramEditor editor) {
        this.dropedElementName = dropedElementName;
        this.droppedElementClass = droppedElementClass;
        this.targetContainerName = targetContainerName;
        this.targetContainerClass = targetContainerClass;
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "The dropped element has not been found in the target container";
    }

    /**
     * Validates that the element has been dropped in the expected container.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        SWTBotGefEditPart droppedEditPart = editor.getEditPart(dropedElementName, droppedElementClass);
        SWTBotGefEditPart targetEditPart = editor.getEditPart(targetContainerName, targetContainerClass);
        return droppedEditPart != null && droppedEditPart.parent() != null && droppedEditPart.parent().part() instanceof ShapeCompartmentEditPart
                && droppedEditPart.parent().parent().equals(targetEditPart);
    }

}
