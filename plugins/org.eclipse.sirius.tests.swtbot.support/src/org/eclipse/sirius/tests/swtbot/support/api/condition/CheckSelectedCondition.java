/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotDesignerEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Class to check if the edit part is selected.
 * 
 * @author smonnier
 */
public class CheckSelectedCondition extends DefaultCondition {

    /**
     * name of the edit part to wait for its selection.
     */
    private String labelOfEditPart;

    /**
     * the edit part to wait for its selection.
     */
    private IGraphicalEditPart editPartToWaitForSelection;

    /**
     * the class of the edit part to wait for its selection.
     */
    private Class<? extends IGraphicalEditPart> editPartClass;

    /**
     * Current editor.
     */
    private final SWTBotDesignerEditor editor;

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * 
     * @param labelOfEditPart
     *            name of the edit part to wait for its selection.
     */
    public CheckSelectedCondition(SWTBotDesignerEditor editor, String labelOfEditPart) {
        this.editor = editor;
        this.labelOfEditPart = labelOfEditPart;
    }

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * 
     * @param editPartToWaitForSelection
     *            the edit part to wait for its selection.
     */
    public CheckSelectedCondition(SWTBotDesignerEditor editor, IGraphicalEditPart editPartToWaitForSelection) {
        this.editor = editor;
        this.editPartToWaitForSelection = editPartToWaitForSelection;
    }

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * 
     * @param labelOfEditPart
     *            name of the edit part to wait for its selection.
     * 
     * @param editPartClass
     *            edit part class to wait for its selection.
     */
    public CheckSelectedCondition(SWTBotDesignerEditor editor, String labelOfEditPart, Class<? extends IGraphicalEditPart> editPartClass) {
        this.editor = editor;
        this.labelOfEditPart = labelOfEditPart;
        this.editPartClass = editPartClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        boolean result = false;
        if (editPartToWaitForSelection != null) {
            result = editPartToWaitForSelection.getSelected() == EditPart.SELECTED_PRIMARY || editPartToWaitForSelection.getSelected() == EditPart.SELECTED;
        } else if (labelOfEditPart != null) {
            if (editPartClass != null) {
                result = editor.getEditPart(labelOfEditPart, editPartClass).part().getSelected() == EditPart.SELECTED_PRIMARY
                        || editor.getEditPart(labelOfEditPart, editPartClass).part().getSelected() == EditPart.SELECTED;
            } else {
                result = editor.getEditPart(labelOfEditPart).part().getParent().getSelected() == EditPart.SELECTED_PRIMARY
                        || editor.getEditPart(labelOfEditPart).part().getParent().getSelected() == EditPart.SELECTED;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "The edit part has not been selected";
    }
}
