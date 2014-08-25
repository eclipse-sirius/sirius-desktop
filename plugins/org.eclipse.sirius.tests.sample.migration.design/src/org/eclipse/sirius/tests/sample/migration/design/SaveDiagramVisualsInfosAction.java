/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.migration.design;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.IEditorPart;

import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;

/**
 * A {@link IExternalJavaAction} to save draw2d/swt informations of specified
 * diagram to the semantic model.
 * 
 * @author fbarbin
 */
@SuppressWarnings("restriction")
public class SaveDiagramVisualsInfosAction implements IExternalJavaAction {

    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        DSemanticDiagram dSemanticDiagram = getDSemanticDiagram(parameters);
        DDiagramEditPart dDiagramEditPart = getDDiagramEditPart();
        if (dDiagramEditPart != null && dSemanticDiagram.equals(dDiagramEditPart.resolveDDiagram().get())) {
            Diagram migrationDiagram = (Diagram) dSemanticDiagram.getTarget();
            Draw2dToSiriusModelTransformer draw2dToSiriusModelTransformer = new Draw2dToSiriusModelTransformer(dDiagramEditPart);
            draw2dToSiriusModelTransformer.updateMigrationModel(migrationDiagram);
        }
    }

    private DSemanticDiagram getDSemanticDiagram(Map<String, Object> parameters) {
        DSemanticDiagram dSemanticDiagram = null;
        Object object = parameters.get("diagram");
        if (object instanceof List<?>) {
            Object diagramTemp = ((List<?>) object).get(0);
            if (diagramTemp instanceof DSemanticDiagram) {
                dSemanticDiagram = (DSemanticDiagram) diagramTemp;
            }
        }
        return dSemanticDiagram;
    }

    private DDiagramEditPart getDDiagramEditPart() {
        DDiagramEditPart dDiagramEditPart = null;
        IEditorPart editor = EclipseUIUtil.getActiveEditor();
        if (editor instanceof DiagramEditor) {
            DiagramEditor diagramEditor = (DiagramEditor) editor;
            DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
            if (diagramEditPart instanceof DDiagramEditPart) {
                dDiagramEditPart = (DDiagramEditPart) diagramEditPart;
            }
        }
        return dDiagramEditPart;
    }

    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}
