/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.Style;

import com.google.common.collect.Iterables;

/**
 * Show the hierarchy of edit parts for the selection
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
final class ShowEditPartsHierarchy implements Runnable {
    /** Sirius debug view */
    private final SiriusDebugView view;

    /**
     * @param view
     */
    ShowEditPartsHierarchy(SiriusDebugView view) {
        this.view = view;
    }

    @Override
    public void run() {
        if (view.selection instanceof EditPart) {
            EditPart part = (EditPart) view.selection;
            StringBuilder sb = new StringBuilder();
            showEditParts(part, 0, sb);
            view.setText(sb.toString());
        }
    }

    private void showEditParts(EditPart part, int level, StringBuilder out) {
        addLevel(level, out);
        addEditPart(part, level, out);
        out.append("\n");
        for (EditPart child : Iterables.filter(part.getChildren(), EditPart.class)) {
            showEditParts(child, level + 1, out);
        }
    }

    private void addLevel(int level, StringBuilder out) {
        for (int i = 0; i < level; i++) {
            out.append("  ");
        }
    }

    private void addEditPart(EditPart part, int level, StringBuilder out) {
        out.append(part.getClass().getSimpleName());
        Object model = part.getModel();
        if (model instanceof Node) {
            if (!(part instanceof LabelEditPart || part instanceof ListCompartmentEditPart)) {
                out.append(": ");
                Node node = (Node) model;
                EObject element = node.getElement();
                if (element instanceof DDiagramElement) {
                    DDiagramElement diagramElement = (DDiagramElement) element;
                    addDDiagramElement(diagramElement, level, out);
                } else {
                    out.append(element.getClass().getSimpleName());
                }
            }
        } else {
            out.append(": ");
            out.append(model.getClass().getSimpleName());
        }
    }

    private void addDDiagramElement(DDiagramElement element, int level, StringBuilder out) {
        int subLevel = level + 1;
        out.append(element.getClass().getSimpleName());
        if (!element.isVisible()) {
            out.append("\n");
            addLevel(subLevel, out);
            out.append("invisible");
        }

        Style style = element.getStyle();
        if (style != null) {
            out.append("\n");
            addLevel(subLevel, out);
            out.append("style: ");
            out.append(style.getClass().getSimpleName());
        }

        EObject target = element.getTarget();
        List<EObject> semanticElements = element.getSemanticElements();
        if (semanticElements != null && !semanticElements.isEmpty()) {
            for (EObject semanticElement : semanticElements) {
                out.append("\n");
                addLevel(subLevel, out);
                out.append("semantic element: ");
                addSemanticElement(semanticElement, out);
            }
        } else if (target != null) {
            out.append("\n");
            addLevel(subLevel, out);
            out.append("target: ");
            addSemanticElement(target, out);
        }

        List<GraphicalFilter> filters = element.getGraphicalFilters();
        if (filters != null) {
            for (GraphicalFilter filter : filters) {
                out.append("\n");
                addLevel(subLevel, out);
                out.append("graphical filter: ");
                out.append(filter.getClass().getSimpleName());
            }
        }

        List<Decoration> decorations = element.getDecorations();
        if (decorations != null) {
            for (Decoration decoration : decorations) {
                out.append("\n");
                addLevel(subLevel, out);
                out.append("decoration: ");
                out.append(decoration.getClass().getSimpleName());
            }
        }
    }

    private void addSemanticElement(EObject element, StringBuilder out) {
        EClass eClass = element.eClass();
        EStructuralFeature feature = eClass.getEStructuralFeature("name");
        if (feature != null) {
            Object value = element.eGet(feature);
            out.append(eClass.getName());
            out.append(" (name: ");
            out.append(value);
            out.append(")");
        } else {
            out.append(element.toString());
        }
    }

}
