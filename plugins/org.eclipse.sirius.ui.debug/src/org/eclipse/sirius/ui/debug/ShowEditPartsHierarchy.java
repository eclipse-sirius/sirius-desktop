/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.ui.debug;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.Style;

import com.google.common.collect.Iterables;

/**
 * Show the hierarchy of edit parts for the selection
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
@SuppressWarnings("restriction")
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
            out.append(": ");
            Node node = (Node) model;
            EObject element = node.getElement();
            if (element instanceof DDiagramElement) {
                DDiagramElement diagramElement = (DDiagramElement) element;
                addDDiagramElement(part, node, diagramElement, level, out);
            } else {
                out.append(element.getClass().getSimpleName());
            }
        } else {
            out.append(": ");
            out.append(model.getClass().getSimpleName());
        }
    }

    private void addDDiagramElement(EditPart part, Node node, DDiagramElement element, int level, StringBuilder out) {
        int subLevel = level + 1;
        out.append(element.getClass().getSimpleName());

        out.append(": ");
        if (part instanceof AbstractGraphicalEditPart) {
            IFigure figure = ((AbstractGraphicalEditPart) part).getFigure();
            out.append(" Figure: " + getClassName(figure) + " Bounds: " + figure.getBounds());
        }

        Object layoutManger = ReflectionHelper.invokeMethodWithoutExceptionWithReturn(part, AbstractDNodeContainerCompartmentEditPart.class, "getLayoutManager", new Class[] {}, new Object[] {}, true);
        if (layoutManger != null) {
            out.append("\n");
            addLevel(subLevel, out);
            out.append("layoutManager: " + layoutManger.getClass().getSimpleName());
        }

        if (!element.isVisible()) {
            out.append("\n");
            addLevel(subLevel, out);
            out.append("invisible");
        }

        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            Bounds bounds = (Bounds) layoutConstraint;
            out.append("\n");
            addLevel(subLevel, out);
            out.append("layoutConstraint: ");
            out.append("width: " + bounds.getWidth() + "  height: " + bounds.getHeight());
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

    String getClassName(Object object) {
        String name = object.getClass().getSimpleName();
        if (name.isEmpty()) {
            String[] split = object.getClass().getName().split("\\.");
            if (split.length > 0) {
                name = split[split.length - 1] + ":" + object.getClass().getSuperclass().getSimpleName();
            }
        }
        return name;
    }
}
