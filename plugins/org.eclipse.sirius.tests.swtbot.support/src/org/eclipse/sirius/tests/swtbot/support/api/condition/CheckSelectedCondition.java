/**
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES
 * This program and the accompanying materials
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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

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
    private EditPart editPartToWaitForSelection;

    /**
     * the class of the edit part to wait for its selection.
     */
    private Class<? extends IGraphicalEditPart> editPartClass;

    /**
     * Current editor.
     */
    private SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * 
     * @param labelOfEditPart
     *            name of the edit part to wait for selection.
     */
    public CheckSelectedCondition(SWTBotSiriusDiagramEditor editor, String labelOfEditPart) {
        this.editor = editor;
        this.labelOfEditPart = labelOfEditPart;
    }

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * @param editPartToWaitForSelection
     *            the edit part to wait for selection.
     */
    public CheckSelectedCondition(SWTBotSiriusDiagramEditor editor, EditPart editPartToWaitForSelection) {
        this.editor = editor;
        this.editPartToWaitForSelection = editPartToWaitForSelection;
    }

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * @param labelOfEditPart
     *            name of the edit part to wait for selection.
     * @param editPartClass
     *            edit part class to wait for selection.
     */
    public CheckSelectedCondition(SWTBotSiriusDiagramEditor editor, String labelOfEditPart, Class<? extends IGraphicalEditPart> editPartClass) {
        this.editor = editor;
        this.labelOfEditPart = labelOfEditPart;
        this.editPartClass = editPartClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return isSelected(getEditPart());
    }

    private boolean isSelected(EditPart part) {
        if (part instanceof DDiagramEditPart) {
            // part.getSelected() will return EditPart.SELECTED_NONE.
            ISelection selection = editor.getSelection();
            return selection instanceof IStructuredSelection && part.equals(((IStructuredSelection) selection).getFirstElement());
        }
        return part != null && part.getSelected() == EditPart.SELECTED_PRIMARY || part.getSelected() == EditPart.SELECTED;
    }

    private EditPart getEditPart() {
        EditPart part = editPartToWaitForSelection;
        if (part == null) {
            if (editPartClass != null) {
                part = editor.getEditPart(labelOfEditPart, editPartClass).part();
            } else {
                part = editor.getEditPart(labelOfEditPart).part().getParent();
            }
        }
        return part;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        String simpleClassName = "";
        if (editPartToWaitForSelection != null) {
            simpleClassName = editPartToWaitForSelection.getClass().getSimpleName();
        } else if (editPartClass != null) {
            simpleClassName = editPartClass.getSimpleName();
        }
        String partTypeMessage = simpleClassName == null ? "" : " of type \"" + simpleClassName + "\"";
        String partLabelMessage = labelOfEditPart == null ? "" : " with name \"" + labelOfEditPart + "\"";
        return "The edit part" + partTypeMessage + partLabelMessage + " has not been selected.";
    }

    /**
     * Create a compound condition to check the selection of the given parts.
     * 
     * @param editor
     *            the current editor.
     * @param editPartLabels
     *            names of the edit parts to wait for selection.
     * @return a compound condition to check the selection of the given parts.
     */
    public static CompoundCondition multipleSelection(SWTBotSiriusDiagramEditor editor, String... editPartLabels) {
        return multipleSelection(editor, null, editPartLabels);
    }

    /**
     * Create a compound condition to check the selection of the given parts.
     * 
     * @param editor
     *            the current editor.
     * @param editPartLabels
     *            names of the edit parts to wait for selection.
     * @param editPartClass
     *            edit part class to wait for selection.
     * @return a compound condition to check the selection of the given parts.
     */
    public static CompoundCondition multipleSelection(SWTBotSiriusDiagramEditor editor, Class<? extends IGraphicalEditPart> editPartClass, String... editPartLabels) {
        Collection<ICondition> conditions = new ArrayList<>();
        for (String label : editPartLabels) {
            conditions.add(new CheckSelectedCondition(editor, label, editPartClass));
        }
        return new CompoundCondition(conditions);
    }

    /**
     * Create a compound condition to check the selection of the given parts.
     * 
     * @param editor
     *            the current editor.
     * @param editPartsToWaitForSelection
     *            the edit part to wait for selection.
     * @return a compound condition to check the selection of the given parts.
     */
    public static CompoundCondition multipleSelection(SWTBotSiriusDiagramEditor editor, EditPart... editPartsToWaitForSelection) {
        Collection<ICondition> conditions = new ArrayList<>();
        for (EditPart part : editPartsToWaitForSelection) {
            conditions.add(new CheckSelectedCondition(editor, part));
        }
        return new CompoundCondition(conditions);
    }
}
