/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.dialect;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.dialect.NotYetOpenedDiagramAdapter;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.LayoutUtil;
import org.eclipse.ui.IEditorPart;

/**
 * Arrange operation is responsible of the diagram needing arrange all mark
 * mechanism, and to launch arrange all.
 * 
 * @author mchauvin
 */
public class DiagramDialectArrangeOperation {

  
    /**
     * Arrange the given representation contained in the given editor, if it was
     * marked as to arrange.
     * 
     * @param editor
     *            the editor
     * @param diagram
     *            the diagram to arrange
     */
    public void arrange(final IEditorPart editor, final DDiagram diagram) {
        if (diagram.eAdapters().contains(NotYetOpenedDiagramAdapter.INSTANCE)) {

            if (editor instanceof DiagramDocumentEditor) {
                DiagramDocumentEditor diagramDocumentEditor = (DiagramDocumentEditor) editor;
                final DiagramEditPart diagramEditPart = diagramDocumentEditor.getDiagramEditPart();
                if (diagramEditPart != null) {
                    LayoutUtil.arrange(diagramEditPart);
                }
            }
            diagram.eAdapters().remove(NotYetOpenedDiagramAdapter.INSTANCE);
        }
    }

}
