/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.uml;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;

/**
 * This test check that the pasted location of bordered nodes is the same as the
 * copied location if the location is already occupied by another port that is
 * also pasted.
 * 
 * @See VP-3099.
 * @author lredor
 */
public class CopyPasteLayoutOfPortsWithConflictWithPastedPortsTest extends AbstractUmlDragAndDropTest {
    @Override
    protected void tearDown() throws Exception {
        clearFormatDataManager();
        super.tearDown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return "conflictsWithOtherPasteElements-copy";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return "Component Diagram-DnDComponentAndPortFromModelExplorer";
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
        editor.clickContextMenu(Messages.CopyFormatAction_text);
        // Open the second editor to paste in
        final SWTBotSiriusDiagramEditor editorForPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(),
                "conflictsWithOtherPasteElements-paste", DDiagram.class);
        // Check that the location of ports to paste is the same as other ports
        String errorMessage = "The copied {0} location should have the same location of the {1} current location in diagram to paste.";
        assertSameLocation(editor, "Port1", editorForPaste, "Port2", errorMessage);
        assertSameLocation(editor, "Port2", editorForPaste, "Port4", errorMessage);
        assertSameLocation(editor, "Port3", editorForPaste, "Port1", errorMessage);
        assertSameLocation(editor, "Port4", editorForPaste, "Port3", errorMessage);
        // Launch paste layout
        editorForPaste.clickContextMenu(Messages.PasteLayoutAction_text);
        // Add a click here to avoid the problem describe in VP-3874.
        // This test fails without this click since the commit "0f72210" of
        // VP-3856 that removes a refresh of the figure execute during the
        // command made by PasteLayoutAction.
        // TODO: The editorForPaste.click() should be not necessary after the
        // fix of VP-3874.
        editorForPaste.click(0, 0);
        // Check that the location of pasted ports is the same to the original
        // copied location.
        errorMessage = "The pasted {1} location should have the same copied {0} location.";
        assertSameLocation(editor, "Port1", editorForPaste, "Port1", errorMessage);
        assertSameLocation(editor, "Port2", editorForPaste, "Port2", errorMessage);
        assertSameLocation(editor, "Port3", editorForPaste, "Port3", errorMessage);
        assertSameLocation(editor, "Port4", editorForPaste, "Port4", errorMessage);
    }
}
