/*******************************************************************************
 * Copyright (c) 2009, 2019 Kiel University and others.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.core.IGraphLayoutEngine;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.service.ElkServicePlugin;
import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.Maybe;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
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
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IWorkbenchPart;

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

    @Inject
    private IEditPartFilter editPartFilter;

    @Inject
    private IGraphLayoutEngine graphLayoutEngine;

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
     * Finds the diagram edit part of an edit part.
     * 
     * @param editPart
     *            an edit part
     * @return the diagram edit part, or {@code null} if there is no containing diagram edit part
     */
    public static DiagramEditPart getDiagramEditPart(final EditPart editPart) {
        EditPart ep = editPart;
        while (ep != null && !(ep instanceof DiagramEditPart) && !(ep instanceof RootEditPart)) {
            ep = ep.getParent();
        }
        if (ep instanceof RootEditPart) {
            // the diagram edit part is a direct child of the root edit part
            RootEditPart root = (RootEditPart) ep;
            ep = null;
            for (Object child : root.getChildren()) {
                if (child instanceof DiagramEditPart) {
                    ep = (EditPart) child;
                }
            }
        }
        return (DiagramEditPart) ep;
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
     * @return a layout graph mapping, or {@code null} if the given workbench part or diagram part is not supported
     */
    public LayoutMapping buildLayoutGraph(final DiagramEditPart diagramEditPart, final Object layoutedPart) {

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
                        EditPart parent = commonParent(layoutRootPart, (EditPart) object);
                        if (parent != null && !(parent instanceof RootEditPart)) {
                            layoutRootPart = (IGraphicalEditPart) parent;
                        }
                    } else if (!(object instanceof ConnectionEditPart)) {
                        layoutRootPart = (IGraphicalEditPart) object;
                    }
                }
            }
            // build a list of edit parts that shall be layouted completely
            if (layoutRootPart != null) {
                selectedParts = new ArrayList<ShapeNodeEditPart>(selection.size());
                for (Object object : selection) {
                    if (object instanceof IGraphicalEditPart) {
                        EditPart editPart = (EditPart) object;
                        while (editPart != null && editPart.getParent() != layoutRootPart) {
                            editPart = editPart.getParent();
                        }
                        if (editPart instanceof ShapeNodeEditPart && editPartFilter.filter(editPart) && !selectedParts.contains(editPart)) {
                            selectedParts.add((ShapeNodeEditPart) editPart);
                        }
                    }
                }
            }
        }

        // create the mapping
        LayoutMapping mapping = buildLayoutGraph(selectedParts, diagramEditPart);

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
    protected static EditPart commonParent(final EditPart editPart1, final EditPart editPart2) {
        EditPart ep1 = editPart1;
        EditPart ep2 = editPart2;
        do {
            if (isParent(ep1, ep2)) {
                return ep1;
            }
            if (isParent(ep2, ep1)) {
                return ep2;
            }
            ep1 = ep1.getParent();
            ep2 = ep2.getParent();
        } while (ep1 != null && ep2 != null);
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
     * @return a layout graph mapping
     */
    protected LayoutMapping buildLayoutGraph(final List<ShapeNodeEditPart> selection, final DiagramEditPart diagramEditPart) {

        LayoutMapping mapping = new LayoutMapping(null);
        mapping.setProperty(CONNECTIONS, new LinkedList<ConnectionEditPart>());
        mapping.setParentElement(diagramEditPart);

        // find the diagram edit part
        mapping.setProperty(DIAGRAM_EDIT_PART, diagramEditPart);

        Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOevrrideMap = constructElkOptionTargetToOptionsMap();

        // start with the whole diagram as root for layout. We cannot start from a diagram element even with a selection
        // layouting because if it has ports it is not supported. The top node is there only to support some meta data.
        ElkNode topNode = ElkGraphUtil.createGraph();
        applyOptionsRelatedToElementTarget(topNode, elkTargetToOptionsOevrrideMap);
        Rectangle rootBounds = diagramEditPart.getFigure().getBounds();
        String labelText = diagramEditPart.getDiagramView().getName();
        if (labelText.length() > 0) {
            ElkLabel label = ElkGraphUtil.createLabel(topNode);
            label.setText(labelText);
        }
        topNode.setDimensions(rootBounds.width, rootBounds.height);
        mapping.getGraphMap().put(topNode, diagramEditPart);
        // we set the ELK algorithm to use from viewpoint id defined.
        topNode.setProperty(CoreOptions.ALGORITHM, layoutConfiguration.getId().trim());

        mapping.setLayoutGraph(topNode);

        if (selection != null && !selection.isEmpty()) {
            // layout only the selected elements
            double minx = Integer.MAX_VALUE;
            double miny = Integer.MAX_VALUE;

            for (ShapeNodeEditPart editPart : selection) {
                // We use new insets the selection can have different parents.
                Maybe<ElkPadding> kinsets = new Maybe<>();
                ElkNode node = createNode(mapping, editPart, (IGraphicalEditPart) editPart.getParent(), topNode, kinsets, elkTargetToOptionsOevrrideMap);
                minx = Math.min(minx, node.getX());
                miny = Math.min(miny, node.getY());
                buildLayoutGraphRecursively(mapping, (IGraphicalEditPart) editPart.getParent(), node, editPart, elkTargetToOptionsOevrrideMap);
            }
            mapping.setProperty(COORDINATE_OFFSET, new KVector(minx, miny));
        } else {
            // traverse all children of the layout root part
            buildLayoutGraphRecursively(mapping, diagramEditPart, topNode, diagramEditPart, elkTargetToOptionsOevrrideMap);
        }

        // transform all connections in the selected area
        processConnections(mapping, elkTargetToOptionsOevrrideMap);


        return mapping;
    }

    /**
     * Construct a map associated option targets to all corresponding options that are overridden in the VSM. 
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
     */
    public void transferLayout(final LayoutMapping mapping) {
        // create a new request to change the layout
        ApplyLayoutRequest applyLayoutRequest = new ApplyLayoutRequest();
        for (Entry<ElkGraphElement, Object> entry : mapping.getGraphMap().entrySet()) {
            if (!(entry.getValue() instanceof DiagramEditPart)) {
                ElkGraphElement graphElement = entry.getKey();
                IGraphicalEditPart part = (IGraphicalEditPart) entry.getValue();
                applyLayoutRequest.addElement(graphElement, part);

            }
        }

        ElkNode layoutGraph = mapping.getLayoutGraph();
        applyLayoutRequest.setUpperBound(layoutGraph.getWidth(), layoutGraph.getHeight());

        // correct the layout by adding the offset determined from the selection
        KVector offset = mapping.getProperty(COORDINATE_OFFSET);
        if (offset != null) {
            addOffset(mapping.getLayoutGraph(), offset);
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
     * @param parentEditPart
     *            the parent edit part of the current elements
     * @param parentLayoutNode
     *            the corresponding KNode
     * @param currentEditPart
     *            the currently analyzed edit part
     * @param elkTargetToOptionsOverrideMap
     *            a map of option targets to corresponding options.
     */
    protected void buildLayoutGraphRecursively(final LayoutMapping mapping, final IGraphicalEditPart parentEditPart, final ElkNode parentLayoutNode, final IGraphicalEditPart currentEditPart,
            Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {

        Maybe<ElkPadding> kinsets = new Maybe<ElkPadding>();
        // autoSize.prepareForArrangeAll(Iterators.filter(currentEditPart.getChildren().iterator(),
        // AbstractDiagramElementContainerEditPart.class), new ArrayList<>());
        // iterate through the children of the element
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
                        buildLayoutGraphRecursively(mapping, parentEditPart, parentLayoutNode, compartment, elkTargetToOptionsOverrideMap);
                    }
                }

                // process a node, which may be a parent of ports, compartments,
                // or other nodes
            } else if (obj instanceof ShapeNodeEditPart) {
                ShapeNodeEditPart childNodeEditPart = (ShapeNodeEditPart) obj;
                if (editPartFilter.filter(childNodeEditPart)) {
                    ElkNode node = createNode(mapping, childNodeEditPart, parentEditPart, parentLayoutNode, kinsets, elkTargetToOptionsOverrideMap);
                    // Create label for Node, not Container, with name inside the node, not on border
                    final EObject eObj = childNodeEditPart.resolveSemanticElement();
                    if (eObj instanceof DNode && ((NodeStyle) ((DNode) eObj).getStyle()).getLabelPosition() == LabelPosition.NODE_LITERAL) {
                        ElkLabel newNodeLabel = createNodeLabel(mapping, (IGraphicalEditPart) obj, currentEditPart, parentLayoutNode, elkTargetToOptionsOverrideMap);
                        if (newNodeLabel != null) {
                            node.getLabels().add(newNodeLabel);
                        }
                    }
                    // process the child as new current edit part
                    buildLayoutGraphRecursively(mapping, childNodeEditPart, node, childNodeEditPart, elkTargetToOptionsOverrideMap);
                }

                // process a label of the current node
            } else if (obj instanceof IGraphicalEditPart) {
                ElkLabel newNodeLabel = createNodeLabel(mapping, (IGraphicalEditPart) obj, currentEditPart, parentLayoutNode, elkTargetToOptionsOverrideMap);
                if (newNodeLabel != null) {
                    parentLayoutNode.getLabels().add(newNodeLabel);
                }
            }
        }
    }

    /**
     * Create a node while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param nodeEditPart
     *            the node edit part
     * @param parentEditPart
     *            the parent node edit part that contains the current node
     * @param parentElkNode
     *            the corresponding parent layout node
     * @param elkinsets
     *            reference parameter for insets; the insets are calculated if this has not been done before
     * @param elkTargetToOptionsOverrideMap
     *            a map of option targets to corresponding options.
     * @return the created node
     */
    protected ElkNode createNode(final LayoutMapping mapping, final IGraphicalEditPart nodeEditPart, final IGraphicalEditPart parentEditPart, final ElkNode parentElkNode,
            final Maybe<ElkPadding> elkinsets, Map<LayoutOptionTarget, Set<LayoutOption>> elkTargetToOptionsOverrideMap) {

        IFigure nodeFigure = nodeEditPart.getFigure();
        ElkNode childLayoutNode = ElkGraphUtil.createNode(parentElkNode);
        applyOptionsRelatedToElementTarget(childLayoutNode, elkTargetToOptionsOverrideMap);

        // set location and size
        Rectangle childBounds = getAbsoluteBounds(nodeFigure);
        Rectangle containerBounds = getAbsoluteBounds(nodeFigure.getParent());
        childLayoutNode.setX(childBounds.x - containerBounds.x);
        childLayoutNode.setY(childBounds.y - containerBounds.y);
        childLayoutNode.setDimensions(childBounds.width, childBounds.height);

        // useful to debug.
        if (((View) nodeEditPart.getModel()).getElement() instanceof DDiagramElement) {
            childLayoutNode.setIdentifier(((DDiagramElement) ((View) nodeEditPart.getModel()).getElement()).getName());
        }
        // We would set the modified flag to false here, but that doesn't exist
        // anymore

        // determine minimal size of the node
        try {
            Dimension minSize = nodeFigure.getMinimumSize();
            childLayoutNode.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(minSize.width, minSize.height));
        } catch (SWTException exception) {
            // getMinimumSize() can cause this exception when fonts are disposed
            // for some reason;
            // ignore exception and leave the default minimal size
        }

        if (parentElkNode != null) {
            // set insets if not yet defined
            ElkPadding ei = null;
            if (elkinsets.get() == null) {
                Insets insets = calcSpecificInsets(parentEditPart.getFigure(), nodeFigure);
                ei = new ElkPadding(insets.top, insets.right, insets.bottom, insets.left);
                elkinsets.set(ei);
            } else {
                // padding is already computed for given parent so we reuse it.
                ei = new ElkPadding(elkinsets.get().top, elkinsets.get().right, elkinsets.get().bottom, elkinsets.get().left);
            }
            childLayoutNode.setProperty(CoreOptions.PADDING, ei);

            parentElkNode.getChildren().add(childLayoutNode);
            applyParentNodeOption(parentElkNode, elkTargetToOptionsOverrideMap);
        }
        mapping.getGraphMap().put(childLayoutNode, nodeEditPart);

        // store all the connections to process them later
        addConnections(mapping, nodeEditPart);
        return childLayoutNode;
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
        // set the port's layout, relative to the node position
        Rectangle portBounds = getAbsoluteBounds(portEditPart.getFigure());
        Rectangle nodeBounds = getAbsoluteBounds(nodeEditPart.getFigure());
        double xpos = portBounds.x - nodeBounds.x;
        double ypos = portBounds.y - nodeBounds.y;
        port.setLocation(xpos, ypos);
        port.setDimensions(portBounds.width, portBounds.height);

        // We would set the modified flag to false here, but that doesn't exist
        // anymore

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
                }

                if (text != null) {
                    ElkLabel portLabel = ElkGraphUtil.createLabel(port);
                    applyOptionsRelatedToElementTarget(portLabel, elkTargetToOptionsOverrideMap);
                    portLabel.setText(text);
                    mapping.getGraphMap().put(portLabel, portChildObj);

                    // set the port label's layout
                    Rectangle labelBounds = getAbsoluteBounds(labelFigure);
                    portLabel.setLocation(labelBounds.x - portBounds.x, labelBounds.y - portBounds.y);
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
        if (labelEditPart instanceof IAbstractDiagramNodeEditPart) {
            labelFigure = ((IAbstractDiagramNodeEditPart) labelEditPart).getNodeLabel();
        } else {
            labelFigure = labelEditPart.getFigure();
        }
        String text = null;
        Font font = null;

        if (labelFigure instanceof WrappingLabel) {
            WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
            text = wrappingLabel.getText();
            font = wrappingLabel.getFont();
        } else if (labelFigure instanceof Label) {
            Label label = (Label) labelFigure;
            text = label.getText();
            font = label.getFont();
        } else if (labelFigure instanceof SiriusWrapLabel) {
            SiriusWrapLabel label = (SiriusWrapLabel) labelFigure;
            text = label.getText();
            font = label.getFont();
        }

        if (text != null) {
            ElkLabel label = ElkGraphUtil.createLabel(elknode);
            applyOptionsRelatedToElementTarget(label, elkTargetToOptionsOverrideMap);
            label.setText(text);
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
                Dimension size = labelFigure.getPreferredSize();
                label.setDimensions(size.width, size.height);
                if (font != null && !font.isDisposed()) {
                    label.setProperty(CoreOptions.FONT_NAME, font.getFontData()[0].getName());
                    label.setProperty(CoreOptions.FONT_SIZE, font.getFontData()[0].getHeight());
                }
            } catch (SWTException exception) {
                // ignore exception and leave the label size to (0, 0)
            }

            // We would set the modified flag to false here, but that doesn't
            // exist anymore
            return label;
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
            EdgeLabelPlacement edgeLabelPlacement = EdgeLabelPlacement.UNDEFINED;
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
                    edgeLabelPlacement = EdgeLabelPlacement.TAIL;
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

        // We would set the modified flag to false here, but that doesn't exist
        // anymore
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
     *            predefined placement for all labels, or {@code UNDEFINED} if the placement shall be derived from the
     *            edit part
     * @param offset
     *            the offset for coordinates
     * @param elkTargetToOptionsOverrideMap
     *            map of option targets to corresponding options.
     */
    protected void processEdgeLabels(final LayoutMapping mapping, final ConnectionEditPart connection, final ElkEdge edge, final EdgeLabelPlacement placement, final KVector offset,
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
                    if (placement == EdgeLabelPlacement.UNDEFINED) {
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
                        label.setProperty(CoreOptions.EDGE_LABELS_PLACEMENT, placement);
                    }
                    Font font = labelFigure.getFont();
                    if (font != null && !font.isDisposed()) {
                        label.setProperty(CoreOptions.FONT_NAME, font.getFontData()[0].getName());
                        label.setProperty(CoreOptions.FONT_SIZE, font.getFontData()[0].getHeight());
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
        BasicProgressMonitor basicProgressMonitor = new BasicProgressMonitor(0, ElkServicePlugin.getInstance().getPreferenceStore().getBoolean(PREF_EXEC_TIME_MEASUREMENT));
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
