/**
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * A Condition to test if a SWTBotGefEditPart's view has moved.
 * 
 * @author edugueperoux
 */
public class CheckEditPartMoved extends DefaultCondition {

    /** The edit part to wait for its move. */
    private IGraphicalEditPart graphicalEditPart;

    /** Initial position of the edit part. */
    private final Point initialLocation;

    /** Current editor. */
    private final SWTBotSiriusDiagramEditor editor;

    /** Name of the edit part to wait for its selection. */
    private String labelOfEditPart;

    /** the class of the edit part to wait for its selection. */
    private Class<? extends IGraphicalEditPart> editPartClass;

    /**
     * Default Constructor.
     * 
     * @param editPartBot
     *            bot to check if moved
     */
    public CheckEditPartMoved(SWTBotGefEditPart editPartBot) {
        this.graphicalEditPart = (IGraphicalEditPart) editPartBot.part();
        this.initialLocation = graphicalEditPart.getFigure().getBounds().getLocation().getCopy();
        this.editor = null;
    }

    /**
     * Default Constructor.
     * 
     * @param editor
     *            the current editor
     * 
     * @param labelOfEditPart
     *            name of the edit part to wait for its selection.
     * 
     * @param editPartClass
     *            edit part class to wait for its selection.
     * @param initialLocation
     *            Initial position of the edit part
     */
    public CheckEditPartMoved(SWTBotSiriusDiagramEditor editor, String labelOfEditPart, Class<? extends IGraphicalEditPart> editPartClass, Point initialLocation) {
        this.labelOfEditPart = labelOfEditPart;
        this.editPartClass = editPartClass;
        this.initialLocation = initialLocation;
        this.editor = editor;
    }

    @Override
    public boolean test() throws Exception {
        Point location;
        if (editor != null) {
            location = editor.getAbsoluteLocation((org.eclipse.gef.GraphicalEditPart) editor.getEditPart(labelOfEditPart, editPartClass).part());
        } else {
            location = graphicalEditPart.getFigure().getBounds().getLocation().getCopy();
        }
        return !initialLocation.equals(location);
    }

    @Override
    public String getFailureMessage() {
        return "Failed to find " + (graphicalEditPart != null ? graphicalEditPart.resolveSemanticElement() : labelOfEditPart) + " moved";
    }

}
