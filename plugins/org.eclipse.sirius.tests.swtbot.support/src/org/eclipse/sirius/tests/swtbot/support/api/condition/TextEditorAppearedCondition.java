/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusTextEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * An {@link org.eclipse.swtbot.swt.finder.waits.ICondition} to wait that the
 * text editor appears (for a direct edit for example).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TextEditorAppearedCondition implements ICondition {

    /**
     * Current editor.
     */
    private SWTBotSiriusDiagramEditor editor;

    /**
     * The class of the edit part to wait for its direct edition.
     */
    private Class<? extends IGraphicalEditPart> editPartClass;

    /**
     * The precondition of direct edit (if any).
     */
    private String precondition;

    /**
     * The error message computed during test() method calls.
     */
    private String errorMessage;

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * @param editPartClass
     *            edit part class to wait for direct edit.
     * @param precondition
     *            the precondition of direct edit (if any)
     */
    public TextEditorAppearedCondition(SWTBotSiriusDiagramEditor editor, Class<? extends IGraphicalEditPart> editPartClass, String precondition) {
        this.editor = editor;
        this.editPartClass = editPartClass;
        this.precondition = precondition;
    }

    @Override
    public boolean test() throws Exception {
        boolean result = false;
        try {
            editor.bot().text();
            result = true;
        } catch (WidgetNotFoundException e) {
            if (editPartClass.equals(SiriusNoteEditPart.class) || editPartClass.equals(SiriusTextEditPart.class)) {
                errorMessage = "The selected element is expected to be in direct edit mode but was not: " + e.getMessage();
            } else {
                if (!("[false/]".equals(precondition))) {
                    errorMessage = "The direct edit mode is not accessible while there is no precondition: " + e.getMessage();
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public void init(SWTBot bot) {
    }

    @Override
    public String getFailureMessage() {
        return errorMessage;
    }

}
