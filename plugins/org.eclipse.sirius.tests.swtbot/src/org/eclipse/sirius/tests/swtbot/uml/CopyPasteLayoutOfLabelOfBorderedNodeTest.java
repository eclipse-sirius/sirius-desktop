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
package org.eclipse.sirius.tests.swtbot.uml;

import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This test check that a pasted Node corresponding to the label of a bordered
 * node has {@link Location} as constraint (and not {@link Bounds}).
 * 
 * @See VP-3292.
 * @author lredor
 */
public class CopyPasteLayoutOfLabelOfBorderedNodeTest extends AbstractUmlDragAndDropTest {
    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return "DnD Component Diagram";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return "Component Diagram";
    }

    /**
     * This test check that the pasted location of bordered nodes is the same as
     * the copied location if the location is already occupied by another port
     * that is also pasted.
     * 
     * @See VP-3099.
     */
    public void testCopyPaste() {
        // Launch copy layout
        editor.clickContextMenu("Copy layout");
        // Open the second editor to paste in
        final SWTBotSiriusDiagramEditor editorForPaste = openDiagram(localSession.getOpenedSession(), getRepresentationDescriptionName(), "Component Diagram - Paste to", DDiagram.class);
        // Launch paste layout
        editorForPaste.clickContextMenu("Paste layout");
        // Check that the layout constraint of the Label is a Location (and not
        // a Bounds)
        final SWTBotGefEditPart editPartBot = editorForPaste.getEditPart("DropPort", AbstractDiagramNameEditPart.class);
        assertTrue("The model of the DropPort editPart should be a Node", editPartBot.part().getModel() instanceof Node);
        assertTrue("The constraint of the Node of the label \"DropPort\" should be a Location (and not a Bounds).", !(((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Bounds)
                && ((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Location);
    }
}
