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

import java.util.Map;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewGradientFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorPart;

/**
 * 
 * @author fbarbin
 */
public class LayoutHelper {

    /**
     * 
     * @param edge
     * @return
     */
    public static PointList getBendpoints(DEdge edge) {
        IFigure figure = getFigure(edge);
        if (figure instanceof Connection) {
            return ((Connection) figure).getPoints();
        }
        return null;
    }

    /**
     * 
     * @param diagramElement
     * @return
     */
    public static Rectangle getDraw2DBounds(DDiagramElement diagramElement) {
        IFigure figure = getFigure(diagramElement);
        if (figure != null) {
            return figure.getBounds();
        }
        return null;
    }

    /**
     * Provides the foreground color from draw2d figure corresponding to given
     * viewpoint diagram element.
     * 
     * @param diagramElement
     *            diagram element.
     * @return return the color or null.
     */
    public static Color getForegroundColor(DDiagramElement diagramElement) {
        IFigure figure = getFigureByType(diagramElement, PolylineDecoration.class);
        if (figure != null) {
            return figure.getForegroundColor();

        }
        return null;

    }

    /**
     * 
     * @param diagramElement
     * @return
     */
    public static Color getBackgroundColor(DDiagramElement diagramElement) {
        IFigure figure = null;
        if (diagramElement instanceof DNode) {
            NodeFigure nodeFigure = getNodeFigure((DNode) diagramElement);
            if (nodeFigure != null) {
                figure = (IFigure) nodeFigure.getChildren().get(0);
            }
        }
        if (diagramElement instanceof DNodeContainer) {
            IGraphicalEditPart graphicalEditPart = getEditPart(diagramElement);

            if (graphicalEditPart instanceof DNodeContainerEditPart) {
                DNodeContainerEditPart editPart = (DNodeContainerEditPart) graphicalEditPart;
                ViewNodeContainerFigureDesc containerFigureDesc = editPart.getPrimaryShape();
                if (containerFigureDesc instanceof ViewGradientFigureDesc) {
                    return ((ViewGradientFigureDesc) containerFigureDesc).getBackgroundColor();
                }
            }
            if (graphicalEditPart instanceof DNodeContainer2EditPart) {
                DNodeContainer2EditPart editPart = (DNodeContainer2EditPart) graphicalEditPart;
                ViewNodeContainerFigureDesc containerFigureDesc = editPart.getPrimaryShape();
                if (containerFigureDesc instanceof ViewGradientFigureDesc) {
                    return ((ViewGradientFigureDesc) containerFigureDesc).getBackgroundColor();
                }
            }

        }
        if (figure != null) {
            return figure.getBackgroundColor();

        }
        return null;

    }

    /**
     * 
     * @param diagramElement
     * @return
     */
    public static Color getGradientColor(DDiagramElement diagramElement) {
        if (diagramElement instanceof DNodeContainer) {

            IGraphicalEditPart graphicalEditPart = getEditPart(diagramElement);

            if (graphicalEditPart instanceof DNodeContainerEditPart) {
                DNodeContainerEditPart editPart = (DNodeContainerEditPart) graphicalEditPart;
                ViewNodeContainerFigureDesc containerFigureDesc = editPart.getPrimaryShape();
                if (containerFigureDesc instanceof ViewGradientFigureDesc) {
                    return ((ViewGradientFigureDesc) containerFigureDesc).getGradientColor();
                }
            }

            if (graphicalEditPart instanceof DNodeContainer2EditPart) {
                DNodeContainer2EditPart editPart = (DNodeContainer2EditPart) graphicalEditPart;
                ViewNodeContainerFigureDesc containerFigureDesc = editPart.getPrimaryShape();
                if (containerFigureDesc instanceof ViewGradientFigureDesc) {
                    return ((ViewGradientFigureDesc) containerFigureDesc).getGradientColor();
                }
            }
        }
        return null;
    }

    private static NodeFigure getNodeFigure(DNode dNode) {
        AbstractNotSelectableShapeNodeEditPart shapeNodeEditPart = null;
        IGraphicalEditPart editPart = getEditPart(dNode);
        for (Object childEditPart : editPart.getChildren()) {
            if (childEditPart instanceof AbstractNotSelectableShapeNodeEditPart) {
                shapeNodeEditPart = (AbstractNotSelectableShapeNodeEditPart) childEditPart;
                break;
            }
        }
        if (shapeNodeEditPart != null) {
            IFigure expectedNodefigure = shapeNodeEditPart.getFigure();
            if (expectedNodefigure instanceof NodeFigure) {
                return (NodeFigure) expectedNodefigure;
            }
        }
        return null;
    }

    /**
     * 
     * @param diagramElement
     * @return
     */
    public static BackgroundStyle getBackgroundStyle(DDiagramElement diagramElement) {
        if (diagramElement instanceof DNodeContainer) {
            IGraphicalEditPart graphicalEditPart = getEditPart(diagramElement);

            if (graphicalEditPart instanceof DNodeContainerEditPart) {
                DNodeContainerEditPart editPart = (DNodeContainerEditPart) graphicalEditPart;
                ViewNodeContainerFigureDesc containerFigureDesc = editPart.getPrimaryShape();
                if (containerFigureDesc instanceof ViewGradientFigureDesc) {
                    return ((ViewGradientFigureDesc) containerFigureDesc).getBackgroundStyle();
                }
            }
            if (graphicalEditPart instanceof DNodeContainer2EditPart) {
                DNodeContainer2EditPart editPart = (DNodeContainer2EditPart) graphicalEditPart;
                ViewNodeContainerFigureDesc containerFigureDesc = editPart.getPrimaryShape();
                if (containerFigureDesc instanceof ViewGradientFigureDesc) {
                    return ((ViewGradientFigureDesc) containerFigureDesc).getBackgroundStyle();
                }
            }

        }
        return null;
    }

    private static View getGMFView(DDiagramElement element) {

        return SiriusGMFHelper.getGmfView(element);
    }

    private static IFigure getFigure(DDiagramElement diagramElement) {
        IGraphicalEditPart editPart = getEditPart(diagramElement);
        if (editPart != null)
            return editPart.getFigure();

        return null;
    }

    private static IFigure getFigureByType(DDiagramElement diagramElement, Class type) {
        IGraphicalEditPart editPart = getEditPart(diagramElement);
        if (editPart != null) {
            IFigure figure = editPart.getFigure();

            return getFigure(figure, type);
        }
        return null;
    }

    private static IFigure getFigure(IFigure figure, Class type) {
        if (figure.getClass() == type) {
            return figure;
        }
        for (Object currentFigure : figure.getChildren()) {
            if (currentFigure instanceof IFigure) {
                IFigure tempFigure = getFigure((IFigure) currentFigure, type);
                if (tempFigure != null) {
                    return tempFigure;
                }
            }
        }

        return null;
    }

    public static IGraphicalEditPart getEditPart(DDiagramElement diagramElement) {
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        if (editor instanceof DiagramEditor) {
            View gmfView = getGMFView(diagramElement);
            if (gmfView != null) {
                final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
                final Object editPart = editPartRegistry.get(gmfView);
                if (editPart instanceof IGraphicalEditPart) {
                    return (IGraphicalEditPart) editPart;

                }
            }
        }
        return null;
    }
}
