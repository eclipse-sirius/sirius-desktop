/*******************************************************************************
 * Copyright (c) 2009, 2020 Kiel University and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *     Camille Letavernier (CEA LIST) - Bug 485905
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.alg.layered.options.CrossingMinimizationStrategy;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.IGraphLayoutEngine;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.options.NodeLabelPlacement;
import org.eclipse.elk.core.options.PortConstraints;
import org.eclipse.elk.core.options.PortLabelPlacement;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkConnectableShape;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TopGraphicEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.description.StringLayoutOption;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.ui.business.api.query.EditPartQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeListCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusDescriptionCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusTextEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.ResetOriginChangeModelOperation;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AlphaDropShadowBorder;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.SWTException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.BiMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Diagram layout connector that is able to generically layout diagrams generated by Sirius. The internal KGraph graph
 * structure is built from the structure of edit parts in the diagram. The new layout is applied to the diagram using
 * {@link GmfLayoutEditPolicy}, which creates a {@link GmfLayoutCommand} to directly manipulate data in the GMF notation
 * model, where layout information is stored persistently.
 * 
 * Copied from org.eclipse.elk.conn.gmf.GmfDiagramLayoutConnector of commit a54c87f94fc4a9bb9ff426311c49ada9d3e72b97.
 * 
 * This component has been added the capacity to launch diagram layout computing.
 * 
 * It also has been added the general options setup from VSM specification.
 * 
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2012-07-19 review KI-20 by cds, jjc
 * @author ars
 * @author msp
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
@SuppressWarnings("restriction")
@Singleton
public class ElkDiagramLayoutConnector implements IDiagramLayoutConnector {

    /**
     * A layout configuration that the provider should use to configure its algorithm.
     */
    protected CustomLayoutConfiguration layoutConfiguration;

    /** list of connection edit parts that were found in the diagram. */
    public static final IProperty<List<ConnectionEditPart>> CONNECTIONS = new Property<List<ConnectionEditPart>>("gmf.connections");

    /** diagram edit part of the currently layouted diagram. */
    public static final IProperty<DiagramEditPart> DIAGRAM_EDIT_PART = new Property<DiagramEditPart>("gmf.diagramEditPart");

    /** the command that applies the transferred layout to the diagram. */
    public static final IProperty<Command> LAYOUT_COMMAND = new Property<Command>("gmf.applyLayoutCommand");

    /** the offset to add for all coordinates. */
    public static final IProperty<KVector> COORDINATE_OFFSET = new Property<KVector>("gmf.coordinateOffset");

    public static final String PREF_EXEC_TIME_MEASUREMENT = "elk.exectime.measure";

    /**
     * By default, the layout provider will enlarge a container until it is large enough to contain its children. If
     * this option is set, it won't do so (during the application of the layout: {@link #transferLayout(LayoutMapping)}.
     */
    public static final IProperty<Boolean> NODE_SIZE_FIXED_SIZE = new Property<Boolean>("org.eclipse.sirius.diagram.elk.fixedNodeSize", false, null, null);

    @Inject
    private IEditPartFilter editPartFilter;

    @Inject
    private IGraphLayoutEngine graphLayoutEngine;

    /**
     * Export the given layout graph in a file. The file will be saved in the directory specified in java.io.tmpdir vm
     * argument.
     * 
     * @param graphToStore
     *            the layout graph to store.
     * @param diagramName
     *            the name if the diagram used as file name.
     * @param prefix
     *            a prefix that can be used in the file name.
     * @param openDialog
     *            whether we should indicate by a dialog that the diagram as been saved as file.
     * 
     */
    public static void storeResult(final ElkNode graphToStore, final String diagramName, String suffix, boolean openDialog) {
        String fileName;
        if (StringUtil.isEmpty(suffix)) {
            fileName = diagramName + ".elkg";
        } else {
            fileName = diagramName + "_" + suffix + ".elkg";
        }
        URI exportUri = URI.createFileURI(System.getProperty("java.io.tmpdir") + fileName);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.createResource(exportUri);
        // Disable the layout stored in this graph to avoid an automatic layout during the opening in "Layout Graph"
        // view
        graphToStore.setProperty(CoreOptions.NO_LAYOUT, true);
        resource.getContents().add(graphToStore);

        try {
            resource.save(Collections.emptyMap());
            if (openDialog) {
                MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Export diagram as ELK Graph",
                        MessageFormat.format("Current diagram has been successfully exported into {0}", URI.decode(exportUri.toString())));
            }
        } catch (IOException e) {
            System.out.println(e);
            // ignore the exception
        }
        graphToStore.setProperty(CoreOptions.NO_LAYOUT, false);
    }

    /**
     * Calculates the absolute bounds of the given figure.
     * 
     * @param figure
     *            a figure
     * @return the absolute bounds
     */
    public static Rectangle getAbsoluteBounds(final IFigure figure) {
        Rectangle bounds = new Rectangle(figure.getBounds()) {
            static final long serialVersionUID = 1;

            @Override
            public void performScale(final double factor) {
                // don't perform any scaling to avoid distortion by the zoom
                // level
            }
        };
        figure.translateToAbsolute(bounds);
        return bounds;
    }

    /**
     * Sets the layout configuration that the provider should use to configure its algorithm.
     * 
     * @param layoutConfiguration
     *            the layout configuration that the provider should use to configure its algorithm.
     */
    public void setLayoutConfiguration(CustomLayoutConfiguration layoutConfiguration) {
        this.layoutConfiguration = layoutConfiguration;
    }

    /**
     * If the editPart is a container and is not a workspace image or a regions, the default shadow border size is
     * returned. Otherwise, 0 is returned. See
     * {@link AbstractDiagramElementContainerEditPart#addDropShadow(NodeFigure,IFigure)}.
     * 
     * @param editPart
     *            an edit part
     * @return The shadow border size of the edit part
     */
    public static double getShadowBorderSize(final EditPart editPart) {
        double shadowBorderSize = 0;
        if (editPart instanceof AbstractDiagramElementContainerEditPart && ((AbstractDiagramElementContainerEditPart) editPart).isShadowBorderNeeded()) {
            shadowBorderSize = AlphaDropShadowBorder.getDefaultShadowSize();
        }
        return shadowBorderSize;
    }

    /**
     * Get the default dimension for a container edit part..
     * 
     * @param editPart
     *            an edit part
     * @return The default dimension for a container edit part
     */
    public static KVector getDefaultDimension(final AbstractDiagramElementContainerEditPart editPart) {
        Dimension defaultDimension = editPart.getDefaultDimension();
        KVector result = new KVector(defaultDimension.preciseWidth(), defaultDimension.preciseHeight());
        // Remove shadow border size for container: Indeed, the edges and border nodes use the "internal figure bounds"
        // (ie without shadow). So for ELK the shadow size must be removed. It will be added before applying the layout
        // in org.eclipse.sirius.diagram.elk.GmfLayoutEditPolicy.addShapeLayout(GmfLayoutCommand, ElkShape,
        // GraphicalEditPart, double).
        double shadowBorderSize = ElkDiagramLayoutConnector.getShadowBorderSize(editPart);
        return result.sub(shadowBorderSize, shadowBorderSize);
    }

    /**
     * If the workbench part is a diagram editor, returns that. Otherwise, returns {@code null}. This is more or less
     * just a fancy cast.
     *
     * @param workbenchPart
     *            the workbench part to check.
     * @return the workbench part as a diagram editor, or {@code null}.
     */
    protected DiagramEditor getDiagramEditor(final IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof DiagramEditor) {
            return (DiagramEditor) workbenchPart;
        }
        return null;
    }

    /**
     * Build a KGraph instance for the given diagram. The resulting layout graph should reflect the structure of the
     * original diagram. All graph elements must have {@link org.eclipse.elk.core.klayoutdata.KShapeLayout
     * KShapeLayouts} or {@link org.eclipse.elk.core.klayoutdata.KEdgeLayout KEdgeLayouts} attached, and their
     * modification flags must be set to {@code false}.
     * 
     * <p>
     * At least one of the two parameters must be non-null.
     * </p>
     * 
     * <p>
     * This method is usually called from the UI thread.
     * </p>
     * 
     * @param diagramEditPart
     *            the diagram edit part for which layout is performed
     * @param layoutedPart
     *            the part(s) for which layout is performed, or {@code null} if the whole diagram shall be layouted
     * @param isArrangeAll
     *            true if the layout concerns an arrange of all elements of the diagram, false otherwise.
     * @param isArrangeAtOpening
     *            true if this layout is launched during the opening of the diagram, false otherwise. It allows to adapt
     *            the layout (with a different result than arrange selection).
     * @return a layout graph mapping, or {@code null} if the given workbench part or diagram part is not supported
     */
    public LayoutMapping buildLayoutGraph(final DiagramEditPart diagramEditPart, final Object layoutedPart, final boolean isArrangeAll, final boolean isArrangeAtOpening) {

        // choose the layout root edit part
        IGraphicalEditPart layoutRootPart = null;
        List<ShapeNodeEditPart> selectedParts = null;
        if (layoutedPart instanceof ShapeNodeEditPart || layoutedPart instanceof DiagramEditPart) {
            layoutRootPart = (IGraphicalEditPart) layoutedPart;
        } else if (layoutedPart instanceof IGraphicalEditPart) {
            EditPart tgEditPart = ((IGraphicalEditPart) layoutedPart).getTopGraphicEditPart();
            if (tgEditPart instanceof ShapeNodeEditPart) {
                layoutRootPart = (IGraphicalEditPart) tgEditPart;
            }
        } else if (layoutedPart instanceof Collection) {
            Collection<?> selection = (Collection<?>) layoutedPart;
            // determine the layout root part from the selection
            for (Object object : selection) {
                if (object instanceof IGraphicalEditPart) {
                    if (layoutRootPart != null) {
                        EditPart parent = commonParent(layoutRootPart, (IGraphicalEditPart) object);
                        if (parent != null && !(parent instanceof RootEditPart)) {
                            layoutRootPart = (IGraphicalEditPart) parent;
                        }
                    } else if (!(object instanceof ConnectionEditPart)) {
                        if (selection.size() == 1 && isArrangeAll) {
                            // Specific case of arrange all launch on a diagram with only one children
                            layoutRootPart = getTopGraphicParentEditPartIfPresent((IGraphicalEditPart) object);
                        } else {
                            layoutRootPart = (IGraphicalEditPart) object;
                        }
                    }
                }
            }
            // build a list of edit parts that shall be layouted completely
            if (layoutRootPart != null) {
                selectedParts = new ArrayList<ShapeNodeEditPart>(selection.size());
                for (Object object : selection) {
                    if (object instanceof IGraphicalEditPart) {
                        IGraphicalEditPart editPart = (IGraphicalEditPart) object;
                        while (editPart != null && getTopGraphicParentEditPartIfPresent(editPart) != layoutRootPart) {
                            editPart = getTopGraphicParentEditPartIfPresent(editPart);
                        }
                        if (editPart instanceof ShapeNodeEditPart && editPartFilter.filter(editPart) && !selectedParts.contains(editPart)) {
                            if (editPart instanceof SiriusNoteEditPart) {
                                if (new EditPartQuery((SiriusNoteEditPart) editPart).isMovableByAutomaticLayout(Collections.EMPTY_LIST)) {
                                    selectedParts.add((ShapeNodeEditPart) editPart);
                                }
                            } else {
                                selectedParts.add((ShapeNodeEditPart) editPart);
                            }
                        }
                    }
                }
            }
        }

        if (layoutRootPart == null) {
            layoutRootPart = diagramEditPart;
        }

        // create the mapping
        LayoutMapping mapping = buildLayoutGraph(layoutRootPart, selectedParts, diagramEditPart, isArrangeAll, isArrangeAtOpening);

        return mapping;
    }

    /**
     * Determine the lowest common parent of the two edit parts.
     * 
     * @param editPart1
     *            the first edit part
     * @param editPart2
     *            the second edit part
     * @return the common parent, or {@code null} if there is none
     */
    protected static EditPart commonParent(final IGraphicalEditPart editPart1, final IGraphicalEditPart editPart2) {
        IGraphicalEditPart ep1 = editPart1;
        IGraphicalEditPart ep2 = editPart2;
        do {
            if (isParent(ep1, ep2)) {
                return ep1;
            }
            if (isParent(ep2, ep1)) {
                return ep2;
            }
            ep1 = getTopGraphicParentEditPartIfPresent(ep1);
            ep2 = getTopGraphicParentEditPartIfPresent(ep2);
        } while (ep1 != null && ep2 != null);
        return null;
    }

    /**
     * Return the {@link TopGraphicEditPart} of the parent, if it has one, or the parent itself. This avoids to consider
     * potential intermediate AbstractDNode*CompartmentEditPart as parent.
     * 
     * @return The {@link TopGraphicEditPart} of the parent, if it has one, or the parent itself.
     */
    private static IGraphicalEditPart getTopGraphicParentEditPartIfPresent(IGraphicalEditPart node) {
        EditPart parentEditPart = node.getParent();
        if (parentEditPart instanceof IGraphicalEditPart) {
            IGraphicalEditPart graphicalParentEditPart = (IGraphicalEditPart) parentEditPart;
            TopGraphicEditPart topGraphicEditPart = graphicalParentEditPart.getTopGraphicEditPart();
            return topGraphicEditPart != null ? topGraphicEditPart : graphicalParentEditPart;
        }
        return null;
    }

    /**
     * Determine whether the first edit part is a parent of or equals the second one.
     * 
     * @param parent
     *            the tentative parent
     * @param child
     *            the tentative child
     * @return true if the parent is actually a parent of the child
     */
    protected static boolean isParent(final EditPart parent, final EditPart child) {
        EditPart editPart = child;
        do {
            if (editPart == parent) {
                return true;
            }
            editPart = editPart.getParent();
        } while (editPart != null);
        return false;
    }

    public <E extends Enum<E>> EnumSet<E> of(E e, EnumSet<E> enumSet) {
        enumSet.add(e);
        return enumSet;
    }

    /**
     * Creates the actual mapping given an edit part which functions as the root for the layout.
     * 
     * @param layoutRootPart
     *            the layout root edit part
     * @param selection
     *            a selection of contained edit parts to process, or {@code null} if the whole content shall be
     *            processed
     * @param diagramEditPart
     *            the diagram edit part, or {@code null}
     * @param isArrangeAll
     *            true if the layout concerns an arrange of all elements of the diagram, false otherwise.
     * @param isArrangeAtOpening
     *            true if this layout is launched during the opening of the diagram, false otherwise. It allows to adapt
     *            the layout (with a different result than arrange selection).
     * 
     * @return a layout graph mapping
     */
    protected LayoutMapping buildLayoutGraph(final IGraphicalEditPart layoutRootPart, final List<ShapeNodeEditPart> selection, final DiagramEditPart diagramEditPart, final boolean isArrangeAll,
            final boolean isArrangeAtOpening) {

        LayoutMapping mapping = new LayoutMapping(null);
        mapping.setProperty(CONNECTIONS, new LinkedList<ConnectionEditPart>());
        mapping.setParentElement(layoutRootPart);

        // find the diagram edit part
        mapping.setProperty(DIAGRAM_EDIT_PART, diagramEditPart);

        Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOevrrideMap = constructElkOptionTargetToOptionsMap();

        ElkNode topNode = ElkGraphUtil.createGraph();
        ElkNode parentNode = topNode;
        applyOptionsRelatedToElementTarget(topNode, elkTargetToOptionsOevrrideMap);
        // The parentLocation is used when launching an arrange selection of one container contained in another
        // container
        Point parentLocation = new Point(0, 0);

        if (layoutRootPart instanceof ShapeNodeEditPart) {
            // If the root part is a ShapeNodeEditPart and the selection is empty, this implies an arrange selection on
            // only one element (ie one parent). So we want to keep it at a fixed location. For that we use the bounds
            // of the node to determine the size of the graph. We also set its identifier according to its only child.
            // We can not use directly the node as the graph root (in case of it has bordered node) .
            Rectangle childAbsoluteBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(layoutRootPart, true);
            IGraphicalEditPart parentEditPart = getTopGraphicParentEditPartIfPresent(layoutRootPart);
            if (parentEditPart != null && !(parentEditPart instanceof DiagramEditPart)) {
                // Compute the parent location origin
                parentLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentEditPart).getTopLeft();
            }
            topNode.setLocation(0, 0);
            topNode.setDimensions(childAbsoluteBounds.preciseX() + childAbsoluteBounds.preciseWidth(), childAbsoluteBounds.preciseY() + childAbsoluteBounds.preciseHeight());

            if (selection.isEmpty()) {
                // We add the node to layout to the selection (that is empty)
                selection.add((ShapeNodeEditPart) layoutRootPart);
            } else if (isArrangeAtOpening) {
                // Add an intermediate node for the parent of elements to layout, to have a correct behavior
                parentNode = createNode(mapping, layoutRootPart, topNode, elkTargetToOptionsOevrrideMap);

                // During the arrange at opening, the size is allow to be increased if the new elements layout is
                // larger than the existing one. But it is not allowed to be reduced.
                parentNode.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(parentNode.getHeight(), parentNode.getWidth()));
            } else {
                if (layoutRootPart == diagramEditPart) {
                    String labelText = diagramEditPart.getDiagramView().getName();
                    if (labelText.length() > 0) {
                        ElkLabel label = ElkGraphUtil.createLabel(topNode);
                        label.setText(labelText);
                    }
                } else {
                    topNode.setLocation(childAbsoluteBounds.x, childAbsoluteBounds.y);
                }
                topNode.setDimensions(childAbsoluteBounds.width, childAbsoluteBounds.height);
                mapping.getGraphMap().put(topNode, layoutRootPart);
                // Fix the size of the container. This option is ignored/unknown by ELK, we use it to store this
                // information. It is used later, in transfertLayout, to ignore the layout of this container as we want
                // to keep the same size and location.
                parentNode.setProperty(ElkDiagramLayoutConnector.NODE_SIZE_FIXED_SIZE, true);
            }
        } else {
            // Arrange all the diagram or arrange sub part of the diagram
            // Start with the whole diagram as root for layout. This top node is there to support some meta data.
            Rectangle rootBounds = layoutRootPart.getFigure().getBounds();
            if (layoutRootPart == diagramEditPart) {
                String labelText = diagramEditPart.getDiagramView().getName();
                if (labelText.length() > 0) {
                    ElkLabel label = ElkGraphUtil.createLabel(topNode);
                    label.setText(labelText);
                }
            } else {
                topNode.setLocation(rootBounds.x, rootBounds.y);
            }
            topNode.setDimensions(rootBounds.width, rootBounds.height);
            mapping.getGraphMap().put(topNode, layoutRootPart);
        }
        // Set the ELK algorithm to use from viewpoint id defined.
        topNode.setProperty(CoreOptions.ALGORITHM, layoutConfiguration.getId().trim());
        // Set the identifier for the graph according to the diagram name
        if (((View) diagramEditPart.getModel()).getElement() instanceof DSemanticDiagram) {
            topNode.setIdentifier(((DSemanticDiagram) ((View) diagramEditPart.getModel()).getElement()).getName() + "_graph");
        }
        mapping.setLayoutGraph(topNode);

        if (selection != null && !selection.isEmpty()) {
            // layout only the selected elements
            double minx = Integer.MAX_VALUE;
            double miny = Integer.MAX_VALUE;

            for (ShapeNodeEditPart editPart : selection) {
                ElkNode node = createNode(mapping, editPart, parentNode, elkTargetToOptionsOevrrideMap);
                minx = Math.min(minx, node.getX());
                miny = Math.min(miny, node.getY());
                boolean childrenLayouted = buildLayoutGraphRecursively(mapping, node, editPart, elkTargetToOptionsOevrrideMap);
                if (!childrenLayouted && editPart instanceof AbstractDiagramElementContainerEditPart) {
                    node.setProperty(CoreOptions.NODE_SIZE_MINIMUM, getDefaultDimension((AbstractDiagramElementContainerEditPart) editPart));
                }

            }
            if (layoutRootPart instanceof ShapeNodeEditPart) {
                Dimension topLeftInsets = GMFHelper.getContainerTopLeftInsetsAfterLabel((Node) layoutRootPart.getNotationView(), true);
                if (selection.size() == 1 && selection.get(0).equals(layoutRootPart)) {
                    if (isArrangeAtOpening) {
                        mapping.setProperty(COORDINATE_OFFSET, new KVector(minx - parentLocation.x() + topLeftInsets.width, miny - parentLocation.y() + topLeftInsets.height));
                    } else {
                        mapping.setProperty(COORDINATE_OFFSET, new KVector(minx - parentLocation.x(), miny - parentLocation.y()));
                    }
                } else if (!isArrangeAtOpening) {
                    mapping.setProperty(COORDINATE_OFFSET, new KVector(minx, miny));
                } else {
                    // Use the parent node bounds if the arrange selection concerns sub part of a container during
                    // an arrange at opening
                    mapping.setProperty(COORDINATE_OFFSET, new KVector(parentNode.getX() - parentLocation.x() - topLeftInsets.width, parentNode.getY() - parentLocation.y() - topLeftInsets.height));
                }
            } else {
                if (isArrangeAll || isArrangeAtOpening) {
                    // Use ResetOriginChangeModelOperation.MARGIN instead of minx or miny when arranging all elements of
                    // the current diagram or when layouting new elements on the diagram during opening
                    mapping.setProperty(COORDINATE_OFFSET, new KVector(ResetOriginChangeModelOperation.MARGIN, ResetOriginChangeModelOperation.MARGIN));
                } else {
                    mapping.setProperty(COORDINATE_OFFSET, new KVector(minx - parentLocation.x(), miny - parentLocation.y()));
                }
            }
        } else {
            // traverse all children of the layout root part
            buildLayoutGraphRecursively(mapping, topNode, layoutRootPart, elkTargetToOptionsOevrrideMap);
        }

        // transform all connections in the selected area
        processConnections(mapping, elkTargetToOptionsOevrrideMap);

        return mapping;
    }

    /**
     * Construct a map associated option targets to all corresponding options that are overridden in the VSM.
     * 
     * @return a map of option targets to corresponding options.
     */
    private Map<LayoutOptionTarget, Set<LayoutOption>> constructElkOptionTargetToOptionsMap() {
        Map<LayoutOptionTarget, Set<LayoutOption>> resultMap = new HashMap<>();
        resultMap.put(LayoutOptionTarget.EDGE, new HashSet<>());
        resultMap.put(LayoutOptionTarget.LABEL, new HashSet<>());
        resultMap.put(LayoutOptionTarget.NODE, new HashSet<>());
        resultMap.put(LayoutOptionTarget.PARENT, new HashSet<>());
        resultMap.put(LayoutOptionTarget.PORTS, new HashSet<>());
        if (layoutConfiguration != null) {
            EList<LayoutOption> layoutOptions = layoutConfiguration.getLayoutOptions();
            for (LayoutOption layoutOption : layoutOptions) {
                EList<LayoutOptionTarget> targets = layoutOption.getTargets();
                for (LayoutOptionTarget layoutOptionTarget : targets) {
                    Set<LayoutOption> optionsSet = resultMap.get(layoutOptionTarget);
                    optionsSet.add(layoutOption);
                }
            }
        }
        return resultMap;
    }

    private void applyOptionsRelatedToElementTarget(ElkGraphElement elkElement, Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {
        if (layoutConfiguration != null) {
            // we set the global options.
            if (elkElement instanceof ElkNode) {
                Set<LayoutOption> layoutOptionsSet = elkTargetToOptionsOverrideMap.get(LayoutOptionTarget.NODE);
                applyOptions(elkElement, layoutOptionsSet);
            } else if (elkElement instanceof ElkLabel) {
                Set<LayoutOption> layoutOptionsSet = elkTargetToOptionsOverrideMap.get(LayoutOptionTarget.LABEL);
                applyOptions(elkElement, layoutOptionsSet);
            } else if (elkElement instanceof ElkPort) {
                Set<LayoutOption> layoutOptionsSet = elkTargetToOptionsOverrideMap.get(LayoutOptionTarget.PORTS);
                applyOptions(elkElement, layoutOptionsSet);
            } else if (elkElement instanceof ElkEdge) {
                Set<LayoutOption> layoutOptionsSet = elkTargetToOptionsOverrideMap.get(LayoutOptionTarget.EDGE);
                applyOptions(elkElement, layoutOptionsSet);
            }
        }
    }

    private void applyParentNodeOption(ElkGraphElement elkElement, Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {
        Set<LayoutOption> layoutOptionsSet = elkTargetToOptionsOverrideMap.get(LayoutOptionTarget.PARENT);
        applyOptions(elkElement, layoutOptionsSet);
    }

    private void applyOptions(ElkGraphElement elkElement, Set<LayoutOption> layoutOptionsSet) {
        for (LayoutOption layoutOption : layoutOptionsSet) {
            LayoutOptionData layoutProperty = LayoutMetaDataService.getInstance().getOptionData(layoutOption.getId());
            switch (layoutOption.eClass().getClassifierID()) {
            case DescriptionPackage.ENUM_LAYOUT_OPTION:
                EnumLayoutOption enumOption = (EnumLayoutOption) layoutOption;
                int enumValueCount = layoutProperty.getEnumValueCount();
                Enum<?> elkEnum = null;
                int i = 0;
                while (i < enumValueCount && elkEnum == null) {
                    layoutProperty.getEnumValue(i);
                    Enum<?> enumValue = layoutProperty.getEnumValue(i);
                    if (enumOption.getValue().getName().equals(enumValue.name())) {
                        elkEnum = enumValue;
                    }
                    i++;
                }
                elkElement.setProperty(layoutProperty, elkEnum);
                break;

            case DescriptionPackage.ENUM_SET_LAYOUT_OPTION:
                EnumSetLayoutOption enumSetOption = (EnumSetLayoutOption) layoutOption;
                enumValueCount = layoutProperty.getEnumValueCount();

                if (enumValueCount > 0) {
                    EnumSet enumSet = EnumSet.noneOf(layoutProperty.getEnumValue(0).getDeclaringClass());
                    List<EnumLayoutValue> values = enumSetOption.getValues();
                    for (EnumLayoutValue enumLayoutValue : values) {
                        i = 0;
                        while (i < enumValueCount) {
                            Enum enumValue = layoutProperty.getEnumValue(i);
                            if (enumLayoutValue.getName().equals(enumValue.name())) {
                                enumSet = of(enumValue, enumSet);
                                break;
                            }
                            i++;
                        }
                    }
                    elkElement.setProperty(layoutProperty, enumSet);
                }
                break;
            case DescriptionPackage.BOOLEAN_LAYOUT_OPTION:
                BooleanLayoutOption booleanOption = (BooleanLayoutOption) layoutOption;
                elkElement.setProperty(layoutProperty, booleanOption.isValue());
                break;
            case DescriptionPackage.INTEGER_LAYOUT_OPTION:
                IntegerLayoutOption integerOption = (IntegerLayoutOption) layoutOption;
                elkElement.setProperty(layoutProperty, integerOption.getValue());
                break;
            case DescriptionPackage.DOUBLE_LAYOUT_OPTION:
                DoubleLayoutOption doubleOption = (DoubleLayoutOption) layoutOption;
                elkElement.setProperty(layoutProperty, doubleOption.getValue());
                break;
            case DescriptionPackage.STRING_LAYOUT_OPTION:
                StringLayoutOption stringOption = (StringLayoutOption) layoutOption;
                elkElement.setProperty(layoutProperty, stringOption.getValue().trim());
                break;
            default:
                break;
            }
        }
    }

    /**
     * Transfer all layout data from the last created KGraph instance to the original diagram. The diagram is not
     * modified yet, but all required preparations are performed. This is separated from
     * {@link #applyLayout(LayoutMapping)} to allow better code modularization.
     * 
     * @param mapping
     *            a layout mapping that was created by this layout connector
     * @param isArrangeAllOrArrangeAtOpeningOnDiagram
     *            true if the layout concerns an arrange of all elements of the diagram or an arrange launched for the
     *            diagram at the editor opening, false otherwise.
     */
    public void transferLayout(final LayoutMapping mapping, final boolean isArrangeAllOrArrangeAtOpeningOnDiagram) {
        // create a new request to change the layout
        ApplyLayoutRequest applyLayoutRequest = new ApplyLayoutRequest();
        for (Entry<ElkGraphElement, Object> entry : mapping.getGraphMap().entrySet()) {
            if (!(entry.getValue() instanceof DiagramEditPart)) {
                ElkGraphElement graphElement = entry.getKey();
                IGraphicalEditPart part = (IGraphicalEditPart) entry.getValue();
                if (!(part instanceof AbstractDNodeListCompartmentEditPart || part instanceof AbstractDNodeContainerCompartmentEditPart)) {
                    // We ignore compartment edit part that are created into ELK side just to have good layout results
                    if (!graphElement.getProperty(ElkDiagramLayoutConnector.NODE_SIZE_FIXED_SIZE)) {
                        // We do not modify layout of node/graph if the option "Fixed Graph Size" has been set before.
                        applyLayoutRequest.addElement(graphElement, part);
                    }
                }
            }
        }

        ElkNode layoutGraph = mapping.getLayoutGraph();
        applyLayoutRequest.setUpperBound(layoutGraph.getWidth(), layoutGraph.getHeight());

        // correct the layout by adding the offset determined from the selection
        KVector offset = mapping.getProperty(COORDINATE_OFFSET);
        if (offset != null) {
            addOffset(mapping.getLayoutGraph(), offset);
        }

        if (DiagramElkPlugin.getDefault().isDebugging()) {
            ElkDiagramLayoutConnector.storeResult(mapping.getLayoutGraph(), mapping.getLayoutGraph().getIdentifier(), "5_afterAddingOffset", false);
        }

        if (isArrangeAllOrArrangeAtOpeningOnDiagram) {
            // Reset origin (for edges and border nodes as the origin of the children bound is, in theory, already
            // "reset").
            resetOrigin(mapping.getLayoutGraph());

            if (DiagramElkPlugin.getDefault().isDebugging()) {
                ElkDiagramLayoutConnector.storeResult(mapping.getLayoutGraph(), mapping.getLayoutGraph().getIdentifier(), "6_afterResetOrigin", false);
            }
        }

        // check the validity of the editing domain to catch cases where it is
        // disposed
        DiagramEditPart diagramEditPart = mapping.getProperty(DIAGRAM_EDIT_PART);
        if (((InternalTransactionalEditingDomain) diagramEditPart.getEditingDomain()).getChangeRecorder() != null) {
            // retrieve a command for the request; the command is created by
            // GmfLayoutEditPolicy
            Command applyLayoutCommand = diagramEditPart.getCommand(applyLayoutRequest);
            mapping.setProperty(LAYOUT_COMMAND, applyLayoutCommand);
        }
    }

    /**
     * Reset origin for the content of <code>parentNode<code> to {20, 20}:
     * <UL>
     * <LI>The origin of the bounding box of all children of the <code>parentNode</code> are set to {20, 20},</LI>
     * <LI>this bounding box is also expanded with the ports of these children,</LI>
     * <LI>this bounding box is also expanded with the edges of parentNode.</LI>
     * </UL>
     * The result can be slightly different from the {@link ResetOriginChangeModelOperation} because in this action:
     * <UL>
     * <LI>The border nodes are ignored,</LI>
     * <LI>and a margin is considered around the edges (see
     * {@link org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx#getBounds()}:
     * <code>calculatedTolerance</code> and <code>jumpLinkSize</code>).</LI>
     * </UL>
     * 
     * @param parentNode
     *            The node to consider
     */
    public static void resetOrigin(ElkNode parentNode) {
        double minx = Integer.MAX_VALUE;
        double miny = Integer.MAX_VALUE;
        // Handle children of this parent node
        for (ElkNode child : parentNode.getChildren()) {
            minx = Math.min(minx, child.getX());
            miny = Math.min(miny, child.getY());
            // Handle ports of this child
            for (ElkPort port : child.getPorts()) {
                KVector absolutePortLocation = ElkUtil.absolutePosition(port);
                minx = Math.min(minx, absolutePortLocation.x);
                miny = Math.min(miny, absolutePortLocation.y);
                // Handle labels of this port
                for (ElkLabel label : port.getLabels()) {
                    KVector absoluteLabelLocation = ElkUtil.absolutePosition(label);
                    minx = Math.min(minx, absoluteLabelLocation.x);
                    miny = Math.min(miny, absoluteLabelLocation.y);
                }
            }
        }
        // Handle edges contains in this parent node
        for (ElkEdge edge : parentNode.getContainedEdges()) {
            for (ElkEdgeSection section : edge.getSections()) {
                minx = Math.min(minx, section.getStartX());
                miny = Math.min(miny, section.getStartY());
                for (ElkBendPoint bendPoint : section.getBendPoints()) {
                    minx = Math.min(minx, bendPoint.getX());
                    miny = Math.min(miny, bendPoint.getY());
                }
                minx = Math.min(minx, section.getEndX());
                miny = Math.min(miny, section.getEndY());
            }
            // Handle labels of this edge
            for (ElkLabel label : edge.getLabels()) {
                KVector absoluteLabelLocation = ElkUtil.absolutePosition(label);
                minx = Math.min(minx, absoluteLabelLocation.x);
                miny = Math.min(miny, absoluteLabelLocation.y);
            }
        }

        ElkUtil.translate(parentNode, ResetOriginChangeModelOperation.MARGIN - minx, ResetOriginChangeModelOperation.MARGIN - miny);
    }

    /**
     * Add the given offset to all direct children of the given graph.
     * 
     * @param parentNode
     *            the parent node
     * @param offset
     *            the offset to add
     */
    protected static void addOffset(final ElkNode parentNode, final KVector offset) {
        // correct the offset with the minimal computed coordinates
        double minx = Integer.MAX_VALUE;
        double miny = Integer.MAX_VALUE;
        for (ElkNode child : parentNode.getChildren()) {
            minx = Math.min(minx, child.getX());
            miny = Math.min(miny, child.getY());
        }

        // add the corrected offset
        offset.add(-minx, -miny);
        ElkUtil.translate(parentNode, offset.x, offset.y);
    }

    /**
     * Apply the transferred layout to the original diagram. This final step is where the actual change to the diagram
     * is done. This method is always called after {@link #transferLayout(LayoutMapping)} has been done.
     * 
     * @param mapping
     *            a layout mapping that was created by this layout connector
     */
    public Command getApplyCommand(final LayoutMapping mapping) {
        Command applyLayoutCommand = mapping.getProperty(LAYOUT_COMMAND);

        return applyLayoutCommand;
    }

    /**
     * Recursively builds a layout graph by analyzing the children of the given edit part.
     * 
     * @param mapping
     *            the layout mapping
     * @param parentLayoutNode
     *            the {@link ElkNode} corresponding to the parent edit part of the current edit part
     * @param currentEditPart
     *            the currently analyzed edit part
     * @param elkTargetToOptionsOverrideMap
     *            a map of option targets to corresponding options.
     * @return false if no child has been layouted, true otherwise
     */
    protected boolean buildLayoutGraphRecursively(final LayoutMapping mapping, final ElkNode parentLayoutNode, final IGraphicalEditPart currentEditPart,
            Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {
        boolean childrenLayouted = false;
        // iterate through the children of the element
        double maxChildShadowBorderSize = -1;
        for (Object obj : currentEditPart.getChildren()) {

            // check visibility of the child
            if (obj instanceof IGraphicalEditPart) {
                IFigure figure = ((IGraphicalEditPart) obj).getFigure();
                if (!figure.isVisible()) {
                    continue;
                }
            }

            // process a port (border item)
            if (obj instanceof AbstractBorderItemEditPart) {
                AbstractBorderItemEditPart borderItem = (AbstractBorderItemEditPart) obj;
                if (editPartFilter.filter(borderItem)) {
                    createPort(mapping, borderItem, currentEditPart, parentLayoutNode, elkTargetToOptionsOverrideMap);
                }

                // process a compartment, which may contain other elements
            } else if (obj instanceof ResizableCompartmentEditPart && ((CompartmentEditPart) obj).getChildren().size() > 0) {

                CompartmentEditPart compartment = (CompartmentEditPart) obj;
                if (editPartFilter.filter(compartment)) {
                    boolean compExp = true;
                    IFigure compartmentFigure = compartment.getFigure();
                    if (compartmentFigure instanceof ResizableCompartmentFigure) {
                        ResizableCompartmentFigure resizCompFigure = (ResizableCompartmentFigure) compartmentFigure;
                        // check whether the compartment is collapsed
                        compExp = resizCompFigure.isExpanded();
                    }

                    if (compExp) {
                        ElkNode intermediateNode = parentLayoutNode;
                        if (currentEditPart instanceof IDiagramElementEditPart) {
                            IDiagramElementEditPart ideep = (IDiagramElementEditPart) currentEditPart;
                            DDiagramElement dde = ideep.resolveDiagramElement();
                            if (dde instanceof DNodeList || (dde instanceof DNodeContainer && (new DNodeContainerExperimentalQuery((DNodeContainer) dde)).isHorizontaltackContainer()
                                    || new DNodeContainerExperimentalQuery((DNodeContainer) dde).isVerticalStackContainer())) {
                                childrenLayouted = true;
                                // Create a node representing the compartment
                                intermediateNode = createNode(mapping, compartment, parentLayoutNode, elkTargetToOptionsOverrideMap);
                                // Add some additional layout option to its container to have "fit" layout result
                                Dimension topLeftInsets = GMFHelper.getContainerTopLeftInsetsAfterLabel((Node) compartment.getNotationView(), true);
                                Dimension borderSize = GMFHelper.getBorderSize((DDiagramElementContainer) dde);
                                int separatorLineHeight = 1;
                                ElkPadding padding;
                                if (dde instanceof DNodeList) {
                                    padding = new ElkPadding(topLeftInsets.preciseHeight() + separatorLineHeight, topLeftInsets.preciseWidth(), borderSize.height(), topLeftInsets.preciseWidth());
                                } else {
                                    // For container with VStack, the label of region occupied all the width so we have
                                    // not to use insets
                                    padding = new ElkPadding(topLeftInsets.preciseHeight(), borderSize.preciseWidth(), borderSize.preciseHeight(), borderSize.preciseWidth());
                                }
                                parentLayoutNode.setProperty(CoreOptions.PADDING, padding);
                                // no space around regions
                                parentLayoutNode.setProperty(CoreOptions.SPACING_NODE_NODE, 0d);
                                // no space around labels
                                parentLayoutNode.setProperty(CoreOptions.NODE_LABELS_PADDING, new ElkPadding());
                                // Strategy set to INTERACTIVE to keep order of children
                                parentLayoutNode.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.INTERACTIVE);

                                if (dde instanceof DNodeContainer) {
                                    intermediateNode.setProperty(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0));
                                    intermediateNode.setProperty(CoreOptions.SPACING_NODE_NODE, 0d);
                                    intermediateNode.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.INTERACTIVE);
                                }
                                intermediateNode.setProperty(CoreOptions.NODE_LABELS_PADDING, new ElkPadding());
                            } else if (dde instanceof DNodeContainer) {
                                DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery((DNodeContainer) dde);
                                if (query.isHorizontaltackContainer()) {
                                } else if (query.isVerticalStackContainer()) {
                                    // Add some additional layout option to its container to have "fit" layout result
                                    Dimension topLeftInsets = GMFHelper.getContainerTopLeftInsets((Node) compartment.getNotationView(), true);
                                    ElkPadding padding = new ElkPadding(topLeftInsets.preciseHeight(), topLeftInsets.preciseWidth(), topLeftInsets.preciseHeight(), topLeftInsets.preciseWidth());
                                    parentLayoutNode.setProperty(CoreOptions.PADDING, padding);
                                    // no space around regions
                                    parentLayoutNode.setProperty(CoreOptions.SPACING_NODE_NODE, 0d);
                                    // Strategy set to INTERACTIVE to keep order of children
                                    parentLayoutNode.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.INTERACTIVE);
                                }
                            }
                        }
                        childrenLayouted = buildLayoutGraphRecursively(mapping, intermediateNode, compartment, elkTargetToOptionsOverrideMap) || childrenLayouted;
                    }
                }

                // process a node, which may be a parent of ports, compartments,
                // or other nodes
            } else if (obj instanceof ShapeNodeEditPart) {
                ShapeNodeEditPart childNodeEditPart = (ShapeNodeEditPart) obj;
                if (editPartFilter.filter(childNodeEditPart)) {
                    childrenLayouted = true;
                    ElkNode node = createNode(mapping, childNodeEditPart, parentLayoutNode, elkTargetToOptionsOverrideMap);
                    maxChildShadowBorderSize = Math.max(maxChildShadowBorderSize, ElkDiagramLayoutConnector.getShadowBorderSize(childNodeEditPart));
                    // process the child as new current edit part
                    boolean currentChildrenLayouted = buildLayoutGraphRecursively(mapping, node, childNodeEditPart, elkTargetToOptionsOverrideMap);
                    if (!currentChildrenLayouted && childNodeEditPart instanceof AbstractDiagramElementContainerEditPart) {
                        Dimension defaultDimension = ((AbstractDiagramElementContainerEditPart) childNodeEditPart).getDefaultDimension();
                        node.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(defaultDimension.width(), defaultDimension.height()));
                    }
                }

                // process a label of the current node
            } else if (obj instanceof IGraphicalEditPart) {
                ElkLabel newNodeLabel = createNodeLabel(mapping, (IGraphicalEditPart) obj, currentEditPart, parentLayoutNode, elkTargetToOptionsOverrideMap);
                if (newNodeLabel != null) {
                    parentLayoutNode.getLabels().add(newNodeLabel);
                }
            }
            if (maxChildShadowBorderSize >= 0 && currentEditPart.getNotationView() instanceof Node && !(currentEditPart instanceof ResizableCompartmentEditPart)) {
                // maxChildShadowBorderSize == 0 : There is at least one child, so we set insets of this container
                // maxChildShadowBorderSize > 0 : There is at least one child with a shadow border, we add this
                // border
                // size to the insets to avoid potential scrollbar appearance during the layout application
                // (org.eclipse.sirius.diagram.elk.GmfLayoutEditPolicy.addShapeLayout(GmfLayoutCommand, ElkShape,
                // GraphicalEditPart, double)).
                Dimension topLeftInsets = GMFHelper.getContainerTopLeftInsets((Node) currentEditPart.getNotationView(), true);
                // We directly reuse the left inset to sets the bottom and right insets.
                ElkPadding ei = new ElkPadding(topLeftInsets.preciseHeight(), topLeftInsets.preciseWidth() + maxChildShadowBorderSize, topLeftInsets.preciseWidth() + maxChildShadowBorderSize,
                        topLeftInsets.preciseWidth());
                parentLayoutNode.setProperty(CoreOptions.PADDING, ei);
            }

        }
        return childrenLayouted;
    }

    /**
     * Create a node while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param nodeEditPart
     *            the node edit part
     * @param parentElkNode
     *            the corresponding parent layout node
     * @param elkTargetToOptionsOverrideMap
     *            a map of option targets to corresponding options.
     * @return the created node
     */
    protected ElkNode createNode(final LayoutMapping mapping, final IGraphicalEditPart nodeEditPart, final ElkNode parentElkNode,
            Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {

        IFigure nodeFigure = nodeEditPart.getFigure();
        ElkNode newNode = ElkGraphUtil.createNode(parentElkNode);
        applyOptionsRelatedToElementTarget(newNode, elkTargetToOptionsOverrideMap);

        // set location and size
        Rectangle childAbsoluteBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(nodeEditPart, true);
        KVector containerAbsoluteLocation = new KVector();
        ElkUtil.toAbsolute(containerAbsoluteLocation, parentElkNode);
        newNode.setX(childAbsoluteBounds.x - containerAbsoluteLocation.x);
        newNode.setY(childAbsoluteBounds.y - containerAbsoluteLocation.y);
        // Remove shadow border size for container: Indeed, the edges and border nodes use the "internal figure bounds"
        // (ie without shadow). So for ELK the shadow size must be removed. It will be added before applying the layout
        // in org.eclipse.sirius.diagram.elk.GmfLayoutEditPolicy.addShapeLayout(GmfLayoutCommand, ElkShape,
        // GraphicalEditPart, double).
        double shadowBorderSize = ElkDiagramLayoutConnector.getShadowBorderSize(nodeEditPart);
        newNode.setDimensions(childAbsoluteBounds.width - shadowBorderSize, childAbsoluteBounds.height - shadowBorderSize);

        // useful to debug.
        if (nodeEditPart instanceof AbstractDNodeListCompartmentEditPart || nodeEditPart instanceof AbstractDNodeContainerCompartmentEditPart) {
            newNode.setIdentifier("Compartment");
        } else if (((View) nodeEditPart.getModel()).getElement() instanceof DDiagramElement) {
            newNode.setIdentifier(((DDiagramElement) ((View) nodeEditPart.getModel()).getElement()).getName());
        }

        // determine minimal size of the node
        try {
            Dimension minSize = nodeFigure.getMinimumSize();
            newNode.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(minSize.width, minSize.height));
        } catch (SWTException exception) {
            // getMinimumSize() can cause this exception when fonts are disposed
            // for some reason;
            // ignore exception and leave the default minimal size
        }
        if (nodeEditPart instanceof SiriusNoteEditPart || nodeEditPart instanceof SiriusTextEditPart) {
            // For the Note and Text, we want to have a fixed size (defined by the user)
            newNode.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.fixed());
        }

        if (parentElkNode != null) {
            parentElkNode.getChildren().add(newNode);
            applyParentNodeOption(parentElkNode, elkTargetToOptionsOverrideMap);
        }
        mapping.getGraphMap().put(newNode, nodeEditPart);

        // Create label for Node, not Container, with name inside the node, not on border
        final EObject eObj = nodeEditPart.resolveSemanticElement();
        if (eObj instanceof DNode && ((NodeStyle) ((DNode) eObj).getStyle()).getLabelPosition() == LabelPosition.NODE_LITERAL) {
            ElkLabel newNodeLabel = createNodeLabel(mapping, nodeEditPart, (IGraphicalEditPart) nodeEditPart.getParent(), parentElkNode, elkTargetToOptionsOverrideMap);
            if (newNodeLabel != null) {
                newNode.getLabels().add(newNodeLabel);
            }
        }

        // store all the connections to process them later
        addConnections(mapping, nodeEditPart);
        return newNode;
    }

    /**
     * Determines the insets for a parent figure, relative to the given child. Subclasses may override this if the
     * generic insets calculation does not work.
     * 
     * @param parent
     *            the figure of a parent edit part
     * @param child
     *            the figure of a child edit part
     * @return the insets to add to the relative coordinates of the child
     */
    protected Insets calcSpecificInsets(final IFigure parent, final IFigure child) {
        Insets result = new Insets(0);
        IFigure currentChild = child;
        IFigure currentParent = child.getParent();
        Point coordsToAdd = null;
        boolean isRelative = false;
        // follow the chain of parents in the figure hierarchy up to the given
        // parent figure
        while (currentChild != parent && currentParent != null) {
            if (currentParent.isCoordinateSystem()) {
                // the content of the current parent is relative to that
                // figure's position
                isRelative = true;
                result.add(currentParent.getInsets());
                if (coordsToAdd != null) {
                    // add the position of the previous parent with local
                    // coordinate system
                    result.left += coordsToAdd.x;
                    result.top += coordsToAdd.y;
                }
                coordsToAdd = currentParent.getBounds().getLocation();
            } else if (currentParent == parent && coordsToAdd != null) {
                // we found the top parent, and it does not have local
                // coordinate system,
                // so subtract the parent's coordinates from the previous
                // parent's position
                Point parentCoords = parent.getBounds().getLocation();
                result.left += coordsToAdd.x - parentCoords.x;
                result.top += coordsToAdd.y - parentCoords.y;
            }
            currentChild = currentParent;
            currentParent = currentChild.getParent();
        }
        if (!isRelative) {
            // there is no local coordinate system, so just subtract the
            // coordinates
            Rectangle parentBounds = parent.getBounds();
            currentParent = child.getParent();
            Rectangle containerBounds = currentParent.getBounds();
            result.left = containerBounds.x - parentBounds.x;
            result.top = containerBounds.y - parentBounds.y;
        }
        // In theory it would be better to get the bottom and right insets from
        // the size.
        // However, due to the unpredictability of Draw2D layout managers, this
        // leads to
        // bad results in many cases, so a fixed insets value is more stable.
        result.right = result.left;
        result.bottom = result.left;
        return result;
    }

    /**
     * Create a port while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param portEditPart
     *            the port edit part
     * @param nodeEditPart
     *            the parent node edit part
     * @param elknode
     *            the corresponding layout node
     * @param elkTargetToOptionsOverrideMap
     *            a map of option targets to corresponding options.
     * @return the created port
     */
    protected ElkPort createPort(final LayoutMapping mapping, final AbstractBorderItemEditPart portEditPart, final IGraphicalEditPart nodeEditPart, final ElkNode elknode,
            Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {

        ElkPort port = ElkGraphUtil.createPort(elknode);
        applyOptionsRelatedToElementTarget(port, elkTargetToOptionsOverrideMap);

        // Set the location of port label fixed (indeed the location returned by ELK is currently not compatible with
        // Sirius constraint). The label is, by default, located centered below the port.
        elknode.setProperty(CoreOptions.PORT_LABELS_PLACEMENT, PortLabelPlacement.fixed());

        // set the port's layout, relative to the node position
        Rectangle portBounds = getAbsoluteBounds(portEditPart.getFigure());
        Rectangle nodeBounds = getAbsoluteBounds(nodeEditPart.getFigure());
        double xpos = portBounds.x - nodeBounds.x;
        double ypos = portBounds.y - nodeBounds.y;
        port.setLocation(xpos, ypos);
        port.setDimensions(portBounds.width, portBounds.height);

        // Compute the border node offset from Sirius
        double borderNodeOffset = -IBorderItemOffsets.DEFAULT_OFFSET.preciseWidth();
        final EObject eObj = portEditPart.resolveSemanticElement();
        if (eObj instanceof DDiagramElement) {
            if (new DDiagramElementQuery((DDiagramElement) eObj).isIndirectlyCollapsed()) {
                borderNodeOffset = -IBorderItemOffsets.COLLAPSE_FILTER_OFFSET.preciseWidth();
            }
        }
        port.setProperty(CoreOptions.PORT_BORDER_OFFSET, borderNodeOffset);

        // If the port has location constraints, we add it
        if (eObj instanceof DNode) {
            PortSide authorizedSide = PortSide.UNDEFINED;
            DNodeQuery query = new DNodeQuery((DNode) eObj);
            List<Side> forbiddenSides = query.getForbiddenSide();
            Set<PortSide> authorizedSides = new HashSet<PortSide>(PortSide.SIDES_NORTH_EAST_SOUTH_WEST);
            for (Side side : forbiddenSides) {
                authorizedSides.remove(convertSideToPortSide(side));
            }
            if (authorizedSides.size() != 4) {
                // If the 4 sides are authorizes there is no constraint.
                if (authorizedSides.size() > 1) {
                    // Only consider the first in UI order of Sirius, ie WEST, SOUTH, EAST, NORTH (as ELK constraint
                    // only handles one side)
                    if (authorizedSides.contains(PortSide.WEST)) {
                        authorizedSide = PortSide.WEST;
                    } else if (authorizedSides.contains(PortSide.SOUTH)) {
                        authorizedSide = PortSide.SOUTH;
                    } else if (authorizedSides.contains(PortSide.EAST)) {
                        authorizedSide = PortSide.EAST;
                    } else if (authorizedSides.contains(PortSide.NORTH)) {
                        authorizedSide = PortSide.NORTH;
                    }
                } else {
                    authorizedSide = authorizedSides.iterator().next();
                }
                elknode.setProperty(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
                port.setProperty(CoreOptions.PORT_SIDE, authorizedSide);
            }
        }

        mapping.getGraphMap().put(port, portEditPart);

        // store all the connections to process them later
        addConnections(mapping, portEditPart);

        // set the port label
        for (Object portChildObj : portEditPart.getChildren()) {
            if (portChildObj instanceof IGraphicalEditPart) {
                IFigure labelFigure = ((IGraphicalEditPart) portChildObj).getFigure();
                String text = null;
                if (labelFigure instanceof WrappingLabel) {
                    text = ((WrappingLabel) labelFigure).getText();
                } else if (labelFigure instanceof Label) {
                    text = ((Label) labelFigure).getText();
                } else if (labelFigure instanceof SiriusWrapLabel) {
                    SiriusWrapLabel label = (SiriusWrapLabel) labelFigure;
                    text = label.getText();
                }

                if (text != null) {
                    ElkLabel portLabel = ElkGraphUtil.createLabel(port);
                    applyOptionsRelatedToElementTarget(portLabel, elkTargetToOptionsOverrideMap);
                    portLabel.setText(text);
                    mapping.getGraphMap().put(portLabel, portChildObj);

                    // Set the port label's layout (centered below the port)
                    Rectangle labelBounds = getAbsoluteBounds(labelFigure);
                    portLabel.setLocation((portBounds.preciseWidth() / 2) - (labelBounds.preciseWidth() / 2), portBounds.height + 1);
                    try {
                        Dimension size = labelFigure.getPreferredSize();
                        portLabel.setDimensions(size.width, size.height);
                    } catch (SWTException exception) {
                        // ignore exception and leave the label size to (0, 0)
                    }

                    // We would set the modified flag to false here, but that
                    // doesn't exist anymore
                }
            }
        }

        return port;
    }

    private PortSide convertSideToPortSide(Side side) {
        PortSide result = PortSide.UNDEFINED;
        if (Side.WEST.equals(side)) {
            result = PortSide.WEST;
        } else if (Side.EAST.equals(side)) {
            result = PortSide.EAST;
        } else if (Side.NORTH.equals(side)) {
            result = PortSide.NORTH;
        } else if (Side.SOUTH.equals(side)) {
            result = PortSide.SOUTH;
        }
        return result;
    }

    /**
     * Create a node label while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param labelEditPart
     *            the label edit part
     * @param nodeEditPart
     *            the parent node edit part
     * @param elknode
     *            the layout node for which the label is set
     * @param elkTargetToOptionsOverrideMap
     *            a map of option targets to corresponding options.
     * @return the created label
     */
    protected ElkLabel createNodeLabel(final LayoutMapping mapping, final IGraphicalEditPart labelEditPart, final IGraphicalEditPart nodeEditPart, final ElkNode elknode,
            Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {

        IFigure labelFigure;
        // We do not handle label of Note or Text (we want a fixed size for them).
        if (!(labelEditPart instanceof SiriusDescriptionCompartmentEditPart)) {
            if (labelEditPart instanceof IAbstractDiagramNodeEditPart) {
                labelFigure = ((IAbstractDiagramNodeEditPart) labelEditPart).getNodeLabel();
            } else {
                labelFigure = labelEditPart.getFigure();
            }
            String text = null;

            if (labelFigure instanceof WrappingLabel) {
                WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
                text = wrappingLabel.getText();
            } else if (labelFigure instanceof Label) {
                Label label = (Label) labelFigure;
                text = label.getText();
            } else if (labelFigure instanceof SiriusWrapLabel) {
                SiriusWrapLabel label = (SiriusWrapLabel) labelFigure;
                text = label.getText();
            }

            if (text != null) {
                ElkLabel label = ElkGraphUtil.createLabel(elknode);
                applyOptionsRelatedToElementTarget(label, elkTargetToOptionsOverrideMap);
                if (StringUtil.isEmpty(text)) {
                    // Set at least a white space character (otherwise, ELK "ignores" this label and the layout of its
                    // container is not OK (wrong width/height)
                    label.setText(" ");
                } else {
                    label.setText(text);
                }
                Rectangle labelBounds = getAbsoluteBounds(labelFigure);
                Rectangle nodeBounds;
                if (!(labelEditPart instanceof IAbstractDiagramNodeEditPart)) {
                    mapping.getGraphMap().put(label, labelEditPart);
                    nodeBounds = getAbsoluteBounds(nodeEditPart.getFigure());
                } else {
                    nodeBounds = getAbsoluteBounds(labelEditPart.getFigure());
                }
                label.setLocation(labelBounds.x - nodeBounds.x, labelBounds.y - nodeBounds.y);

                try {
                    // We use the preferred size and not the labelBounds to "reset" previous manual wrapping size
                    Dimension size = labelFigure.getPreferredSize();
                    label.setDimensions(size.width, size.height);
                } catch (SWTException exception) {
                    // ignore exception and leave the label size to (0, 0)
                }

                // Set globally the location of the label according to the style
                NodeLabelPlacement insideLabelPlacement = NodeLabelPlacement.INSIDE;
                NodeLabelPlacement verticalNodeLabelPlacement = NodeLabelPlacement.V_TOP;
                NodeLabelPlacement horizontalLabelPlacement = NodeLabelPlacement.H_CENTER;

                // For ListElement, we force H_CENTER and V_CENTER to have good layout result (fit).
                if (labelEditPart instanceof DNodeListElementEditPart) {
                    verticalNodeLabelPlacement = NodeLabelPlacement.V_CENTER;
                } else {
                    EObject siriusObject;
                    if (labelEditPart instanceof IAbstractDiagramNodeEditPart) {
                        siriusObject = labelEditPart.resolveSemanticElement();
                    } else {
                        siriusObject = nodeEditPart.resolveSemanticElement();
                    }
                    boolean forcedValue = false;
                    if (siriusObject instanceof DDiagramElement) {
                        if (siriusObject instanceof DNodeContainer) {
                            if (new DDiagramElementContainerExperimentalQuery((DNodeContainer) siriusObject).isRegionInVerticalStack()) {
                                // Keep default value (OK for this kind of container)
                                forcedValue = true;
                                verticalNodeLabelPlacement = NodeLabelPlacement.V_CENTER;
                            } else if (new DDiagramElementContainerExperimentalQuery((DNodeContainer) siriusObject).isRegionInHorizontalStack()) {
                                forcedValue = true;
                                // verticalNodeLabelPlacement = NodeLabelPlacement.V_TOP;
                                // horizontalLabelPlacement = NodeLabelPlacement.H_CENTER;
                            }
                        }
                        if (!forcedValue) {
                            DDiagramElement dde = (DDiagramElement) siriusObject;
                            Style style = dde.getStyle();
                            if (style instanceof LabelStyle) {
                                LabelAlignment labelAlignment = ((LabelStyle) style).getLabelAlignment();
                                if (labelAlignment.equals(LabelAlignment.LEFT)) {
                                    horizontalLabelPlacement = NodeLabelPlacement.H_LEFT;
                                } else if (labelAlignment.equals(LabelAlignment.RIGHT)) {
                                    horizontalLabelPlacement = NodeLabelPlacement.H_RIGHT;
                                }
                            }
                            if (style instanceof NodeStyle) {
                                if (((NodeStyle) style).getLabelPosition().equals(LabelPosition.BORDER_LITERAL)) {
                                    insideLabelPlacement = NodeLabelPlacement.OUTSIDE;
                                }
                                verticalNodeLabelPlacement = NodeLabelPlacement.V_CENTER;
                            }
                        }
                    }
                }
                EnumSet<NodeLabelPlacement> enumSet = EnumSet.of(insideLabelPlacement, horizontalLabelPlacement, verticalNodeLabelPlacement);
                label.setProperty(CoreOptions.NODE_LABELS_PLACEMENT, enumSet);

                return label;
            }
        }
        return null;
    }

    /**
     * Adds all target connections and connected connections to the list of connections that must be processed later.
     * 
     * @param mapping
     *            the layout mapping
     * @param editPart
     *            an edit part
     */
    protected void addConnections(final LayoutMapping mapping, final IGraphicalEditPart editPart) {
        for (Object targetConn : editPart.getTargetConnections()) {
            if (targetConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConn;
                if (editPartFilter.filter(connectionEditPart)) {
                    mapping.getProperty(CONNECTIONS).add(connectionEditPart);
                    addConnections(mapping, connectionEditPart);
                }
            }
        }
    }

    /**
     * Creates new edges and takes care of the labels for each connection identified in the
     * {@code buildLayoutGraphRecursively} method.
     * 
     * @param mapping
     *            the layout mapping
     * @param elkTargetToOptionsOverrideMap
     *            a map of option targets to corresponding options.
     */
    protected void processConnections(final LayoutMapping mapping, Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {
        Map<EReference, ElkEdge> reference2EdgeMap = new HashMap<>();

        for (ConnectionEditPart connection : mapping.getProperty(CONNECTIONS)) {
            boolean isOppositeEdge = false;
            Optional<EdgeLabelPlacement> edgeLabelPlacement = Optional.empty();
            ElkEdge edge;

            // Check whether the edge belongs to an Ecore reference, which may
            // have opposites.
            // This is required for the layout of Ecore diagrams, since the bend
            // points of
            // opposite references are kept synchronized by the editor.
            EObject modelObject = connection.getNotationView().getElement();
            if (modelObject instanceof EReference) {
                EReference reference = (EReference) modelObject;
                edge = reference2EdgeMap.get(reference.getEOpposite());
                if (edge != null) {
                    edgeLabelPlacement = Optional.of(EdgeLabelPlacement.TAIL);
                    isOppositeEdge = true;
                } else {
                    edge = ElkGraphUtil.createEdge(null);
                    reference2EdgeMap.put(reference, edge);
                }
            } else {
                edge = ElkGraphUtil.createEdge(null);
            }
            applyOptionsRelatedToElementTarget(edge, elkTargetToOptionsOverrideMap);
            BiMap<Object, ElkGraphElement> inverseGraphMap = mapping.getGraphMap().inverse();

            // find a proper source node and source port
            ElkGraphElement sourceElem;
            EditPart sourceObj = connection.getSource();
            if (sourceObj instanceof ConnectionEditPart) {
                sourceElem = inverseGraphMap.get(((ConnectionEditPart) sourceObj).getSource());
                if (sourceElem == null) {
                    sourceElem = inverseGraphMap.get(((ConnectionEditPart) sourceObj).getTarget());
                }
            } else {
                sourceElem = inverseGraphMap.get(sourceObj);
            }

            ElkConnectableShape sourceShape = null;
            ElkPort sourcePort = null;
            ElkNode sourceNode = null;
            if (sourceElem instanceof ElkNode) {
                sourceNode = (ElkNode) sourceElem;
                sourceShape = sourceNode;
            } else if (sourceElem instanceof ElkPort) {
                sourcePort = (ElkPort) sourceElem;
                sourceNode = sourcePort.getParent();
                sourceShape = sourcePort;
            } else {
                continue;
            }

            // find a proper target node and target port
            ElkGraphElement targetElem;
            EditPart targetObj = connection.getTarget();
            if (targetObj instanceof ConnectionEditPart) {
                targetElem = inverseGraphMap.get(((ConnectionEditPart) targetObj).getTarget());
                if (targetElem == null) {
                    targetElem = inverseGraphMap.get(((ConnectionEditPart) targetObj).getSource());
                }
            } else {
                targetElem = inverseGraphMap.get(targetObj);
            }

            ElkConnectableShape targetShape = null;
            ElkNode targetNode = null;
            ElkPort targetPort = null;
            if (targetElem instanceof ElkNode) {
                targetNode = (ElkNode) targetElem;
                targetShape = targetNode;
            } else if (targetElem instanceof ElkPort) {
                targetPort = (ElkPort) targetElem;
                targetNode = targetPort.getParent();
                targetShape = targetPort;
            } else {
                continue;
            }

            // calculate offset for edge and label coordinates
            ElkNode edgeContainment = ElkGraphUtil.findLowestCommonAncestor(sourceNode, targetNode);

            KVector offset = new KVector();
            ElkUtil.toAbsolute(offset, edgeContainment);

            if (!isOppositeEdge) {
                // set source and target
                edge.getSources().add(sourceShape);
                edge.getTargets().add(targetShape);

                // now that source and target are set, put the edge into the
                // graph
                edgeContainment.getContainedEdges().add(edge);

                mapping.getGraphMap().put(edge, connection);

                // store the current coordinates of the edge
                setEdgeLayout(edge, connection, offset);
            }

            // process edge labels
            processEdgeLabels(mapping, connection, edge, edgeLabelPlacement, offset, elkTargetToOptionsOverrideMap);
        }
    }

    /**
     * Stores the layout information of the given connection edit part into an edge layout.
     * 
     * @param edge
     *            an edge layout
     * @param connection
     *            a connection edit part
     * @param offset
     *            offset to be subtracted from coordinates
     */
    protected void setEdgeLayout(final ElkEdge edge, final ConnectionEditPart connection, final KVector offset) {
        Connection figure = connection.getConnectionFigure();
        PointList pointList = figure.getPoints();

        // our edge will have exactly one edge section
        ElkEdgeSection edgeSection = ElkGraphUtil.createEdgeSection(edge);

        // source point
        Point firstPoint = pointList.getPoint(0);
        edgeSection.setStartX(firstPoint.x - offset.x);
        edgeSection.setStartY(firstPoint.y - offset.y);

        // bend points
        for (int i = 1; i < pointList.size() - 1; i++) {
            Point point = pointList.getPoint(i);
            ElkGraphUtil.createBendPoint(edgeSection, point.x - offset.x, point.y - offset.y);
        }

        // target point
        Point lastPoint = pointList.getPoint(pointList.size() - 1);
        edgeSection.setEndX(lastPoint.x - offset.x);
        edgeSection.setEndY(lastPoint.y - offset.y);

        //
        if (figure instanceof Shape) {
            double currentSize = ((Shape) figure).getLineWidth();
            if (currentSize != CoreOptions.EDGE_THICKNESS.getDefault()) {
                edge.setProperty(CoreOptions.EDGE_THICKNESS, currentSize);
            }
        }
    }

    /**
     * Process the labels of an edge.
     * 
     * @param mapping
     *            the layout mapping
     * @param connection
     *            the connection edit part
     * @param edge
     *            the layout edge
     * @param placement
     *            predefined placement for all labels, or {@code Optional#empty()} if the placement shall be derived
     *            from the edit part
     * @param offset
     *            the offset for coordinates
     * @param elkTargetToOptionsOverrideMap
     *            map of option targets to corresponding options.
     */
    protected void processEdgeLabels(final LayoutMapping mapping, final ConnectionEditPart connection, final ElkEdge edge, final Optional<EdgeLabelPlacement> placement, final KVector offset,
            Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {
        /*
         * ars: source and target is exchanged when defining it in the gmfgen file. So if Emma sets a label to be placed
         * as target on a connection, then the label will show up next to the source node in the diagram editor. So
         * correct it here, very ugly.
         */
        for (Object obj : connection.getChildren()) {
            if (obj instanceof LabelEditPart) {
                LabelEditPart labelEditPart = (LabelEditPart) obj;
                IFigure labelFigure = labelEditPart.getFigure();

                // Check if the label is visible in the first place
                if (labelFigure == null || !labelFigure.isVisible()) {
                    continue;
                }

                Rectangle labelBounds = getAbsoluteBounds(labelFigure);
                String labelText = null;
                Dimension iconBounds = null;

                if (labelFigure instanceof WrappingLabel) {
                    WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
                    labelText = wrappingLabel.getText();
                    if (wrappingLabel.getIcon() != null) {
                        iconBounds = new Dimension();
                        iconBounds.width = wrappingLabel.getIcon().getBounds().width + wrappingLabel.getIconTextGap();
                        iconBounds.height = wrappingLabel.getIcon().getBounds().height;
                        // Add more characters to the text for layouters that
                        // need the text to
                        // determine the label size.
                        labelText = "O " + labelText;
                    }
                } else if (labelFigure instanceof Label) {
                    Label label = (Label) labelFigure;
                    labelText = label.getText();
                    if (label.getIcon() != null) {
                        iconBounds = label.getIconBounds().getSize();
                        iconBounds.width += label.getIconTextGap();
                        // Add more characters to the text for layouters that
                        // need the text to
                        // determine the label size.
                        labelText = "O " + labelText;
                    }
                } else if (labelFigure instanceof SiriusWrapLabel) {
                    SiriusWrapLabel label = (SiriusWrapLabel) labelFigure;
                    labelText = label.getText();
                    if (label.getIcon() != null) {
                        iconBounds = label.getIconBounds().getSize();
                        iconBounds.width += label.getIconTextGap();
                        // Add more characters to the text for layouters that
                        // need the text to
                        // determine the label size.
                        labelText = "O " + labelText;
                    }
                }

                if (labelText != null && labelText.length() > 0) {
                    ElkLabel label = ElkGraphUtil.createLabel(edge);
                    applyOptionsRelatedToElementTarget(label, elkTargetToOptionsOverrideMap);
                    if (!placement.isPresent()) {
                        switch (labelEditPart.getKeyPoint()) {
                        case ConnectionLocator.SOURCE:
                            label.setProperty(CoreOptions.EDGE_LABELS_PLACEMENT, EdgeLabelPlacement.HEAD);
                            break;
                        case ConnectionLocator.MIDDLE:
                            label.setProperty(CoreOptions.EDGE_LABELS_PLACEMENT, EdgeLabelPlacement.CENTER);
                            break;
                        case ConnectionLocator.TARGET:
                            label.setProperty(CoreOptions.EDGE_LABELS_PLACEMENT, EdgeLabelPlacement.TAIL);
                            break;
                        }
                    } else {
                        label.setProperty(CoreOptions.EDGE_LABELS_PLACEMENT, placement.get());
                    }
                    label.setLocation(labelBounds.x - offset.x, labelBounds.y - offset.y);
                    // The label width includes the icon width
                    label.setWidth(labelBounds.width);
                    label.setHeight(labelBounds.height);

                    // We would set the modified flag to false here, but that
                    // doesn't exist anymore

                    label.setText(labelText);
                    mapping.getGraphMap().put(label, labelEditPart);
                } else {
                    // add the label to the mapping anyway so it is reset to its
                    // reference location
                    ElkLabel label = ElkGraphUtil.createLabel(null);
                    mapping.getGraphMap().put(label, labelEditPart);
                }
            }
        }
    }

    public void layout(LayoutMapping layoutMapping) {
        BasicProgressMonitor basicProgressMonitor = new BasicProgressMonitor(0);
        graphLayoutEngine.layout(layoutMapping.getLayoutGraph(), basicProgressMonitor.subTask(1));

    }

    @Override
    public void applyLayout(LayoutMapping mapping, IPropertyHolder settings) {
        // not used
    }

    /**
     * Not used by Sirius connector. Instead we are using
     * {@link org.eclipse.sirius.diagram.elk.ElkDiagramLayoutConnector#buildLayoutGraph(IDDiagramEditPart, Object)}
     * 
     * @see org.eclipse.elk.core.service.IDiagramLayoutConnector#buildLayoutGraph(org.eclipse.ui.IWorkbenchPart,
     *      java.lang.Object)
     */
    @Override
    public LayoutMapping buildLayoutGraph(IWorkbenchPart workbenchPart, Object diagramPart) {
        return null;
    }

}
