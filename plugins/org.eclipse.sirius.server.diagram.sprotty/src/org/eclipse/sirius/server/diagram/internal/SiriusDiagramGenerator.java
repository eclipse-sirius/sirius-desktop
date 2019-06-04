/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
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
package org.eclipse.sirius.server.diagram.internal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusEdge;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusFreeFormFlatContainerNode;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusLabel;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusListFlatContainerNode;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusListFlatContainerNodeBodyCompartment;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusListFlatContainerNodeLabelCompartment;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusSquareBorderNode;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusSquareNode;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusSvgBorderNode;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusSvgNode;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sprotty.LayoutOptions;
import org.eclipse.sprotty.RequestModelAction;
import org.eclipse.sprotty.SEdge;
import org.eclipse.sprotty.SGraph;
import org.eclipse.sprotty.SLabel;
import org.eclipse.sprotty.SModelElement;
import org.eclipse.sprotty.SNode;
import org.eclipse.sprotty.SPort;

/**
 * The {@link SiriusDiagramGenerator} is used to compute a Sprotty diagram from Sirius.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramGenerator {

	/**
	 * Computes the diagram.
	 *
	 * @param action
	 *            The request model action
	 *
	 * @return The diagram computed
	 */
	public SGraph computeDiagram(SiriusDiagramServer siriusDiagramServer, RequestModelAction action) {
		DDiagram dDiagram = siriusDiagramServer.getDDiagram();
		SGraph sGraph = this.convert(dDiagram);
		return sGraph;
	}

	/**
	 * Converts the given {@link DDiagram} to an {@link SGraph}.
	 *
	 * @param dDiagram
	 *            The Sirius Diagram
	 * @return The {@link SGraph} computed from the {@link DDiagram}
	 */
	private SGraph convert(DDiagram dDiagram) {
		SGraph sGraph = new SGraph((graph) -> {
			graph.setId(dDiagram.getUid());
			graph.setType("graph"); //$NON-NLS-1$
			graph.setChildren(new ArrayList<>());
		});

		// @formatter:off
		dDiagram.getOwnedDiagramElements().stream()
			.filter(DDiagramElement::isVisible)
			.filter(DNode.class::isInstance)
			.map(DNode.class::cast)
			.map(this::convertDNode)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.forEach(sGraph.getChildren()::add);

		dDiagram.getOwnedDiagramElements().stream()
			.filter(DDiagramElement::isVisible)
			.filter(DNodeContainer.class::isInstance)
			.map(DNodeContainer.class::cast)
			.map(this::convertDNodeContainer)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.forEach(sGraph.getChildren()::add);

		dDiagram.getOwnedDiagramElements().stream()
			.filter(DDiagramElement::isVisible)
			.filter(DNodeList.class::isInstance)
			.map(DNodeList.class::cast)
			.map(this::convertDNodeList)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.forEach(sGraph.getChildren()::add);

		dDiagram.getOwnedDiagramElements().stream()
			.filter(DDiagramElement::isVisible)
			.filter(DEdge.class::isInstance)
			.map(DEdge.class::cast)
			.map(this::convertDEdge)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.forEach(sGraph.getChildren()::add);
		// @formatter:on

		return sGraph;
	}

	/**
	 * Dispatch the transformation of the given diagram element to the proper method.
	 *
	 * @param element
	 *            The element to convert
	 * @return The element converted
	 */
	private Optional<SModelElement> convertDDiagramElement(DDiagramElement element) {
		Optional<SModelElement> optionalSModelElement = Optional.empty();
		if (element instanceof DNode) {
			Optional<SNode> optionalSNode = this.convertDNode((DNode) element);
			if (optionalSNode.isPresent()) {
				optionalSModelElement = Optional.of(optionalSNode.get());
			}
		} else if (element instanceof DNodeContainer) {
			Optional<SNode> optionalSNode = this.convertDNodeContainer((DNodeContainer) element);
			if (optionalSNode.isPresent()) {
				optionalSModelElement = Optional.of(optionalSNode.get());
			}
		} else if (element instanceof DNodeList) {
			Optional<SNode> optionalSNode = this.convertDNodeList((DNodeList) element);
			if (optionalSNode.isPresent()) {
				optionalSModelElement = Optional.of(optionalSNode.get());
			}
		}
		return optionalSModelElement;
	}

	/**
	 * Converts the given {@link DNode} to an {@link SNode}.
	 *
	 * @param dNode
	 *            The {@link DNode} to convert
	 * @return The {@link SNode} computed from the {@link DNode}
	 */
	private Optional<SNode> convertDNode(DNode dNode) {
		Optional<SNode> optionalSNode = Optional.empty();

		Style style = dNode.getStyle();
		if (style instanceof WorkspaceImage) {
			WorkspaceImage workspaceImage = (WorkspaceImage) style;
			optionalSNode = this.convertSVGNode(dNode, workspaceImage);
		} else if (style instanceof Square) {
			Square square = (Square) style;
			optionalSNode = this.convertSquareNode(dNode, square);
		}

		optionalSNode.ifPresent(sNode -> {
			if (style instanceof NodeStyle) {
				NodeStyle nodeStyle = (NodeStyle) style;
				this.convertLabel(sNode.getId(), dNode.getName(), nodeStyle).ifPresent(sNode.getChildren()::add);
			}
			// @formatter:off
			dNode.getOwnedBorderedNodes().stream()
				.map(this::convertBorderedNode)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.forEach(sNode.getChildren()::add);
			// @formatter:on
		});

		return optionalSNode;
	}

	/**
	 * Converts the given {@link DNode} into an {@link SNode}.
	 *
	 * @param dNode
	 *            The DNode
	 * @param square
	 *            The square
	 * @return The SNode computed from the DNode
	 */
	private Optional<SNode> convertSquareNode(DNode dNode, Square square) {
		String identifier = EcoreUtil.getURI(dNode).fragment();

		RGBValues borderColor = square.getBorderColor();
		RGBValues color = square.getColor();
		Integer borderSize = square.getBorderSize();

		JsonObject styleJsonObject = new JsonObject();
		styleJsonObject.addProperty("fill", //$NON-NLS-1$
				"rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke", //$NON-NLS-1$
				"rgb(" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke-width", borderSize); //$NON-NLS-1$

		String styleJson = new Gson().toJson(styleJsonObject);

		SiriusSquareNode siriusSquareNode = new SiriusSquareNode(identifier);
		siriusSquareNode.setStyle(styleJson);
		return Optional.of(siriusSquareNode);
	}

	/**
	 * Converts the given {@link DNode} into an {@link SNode}.
	 *
	 * @param dNode
	 *            The DNode
	 * @param workspaceImage
	 *            The workspace image used as the style of the DNode
	 * @return The SNode computed from the DNonde
	 */
	private Optional<SNode> convertSVGNode(DNode dNode, WorkspaceImage workspaceImage) {
		String identifier = EcoreUtil.getURI(dNode).fragment();

		String svgData = ""; //$NON-NLS-1$
		String workspacePath = workspaceImage.getWorkspacePath();
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath));
		if (file.exists() && file.getFileExtension().equals("svg")) { //$NON-NLS-1$
			try {
				svgData = new BufferedReader(new InputStreamReader(file.getContents())).lines().collect(Collectors.joining("\n")); //$NON-NLS-1$
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		SiriusSvgNode siriusSvgNode = new SiriusSvgNode(identifier);
		siriusSvgNode.setSvgData(svgData);

		return Optional.of(siriusSvgNode);
	}

	/**
	 * Computes the label from the given node style.
	 *
	 * @param nodeIdentifier
	 *            The identifier of the node containing the label
	 * @param label
	 *            The text to be displayed in the label
	 * @param nodeStyle
	 *            The style of the node containing the label
	 * @return The label computed
	 */
	private Optional<SLabel> convertLabel(String nodeIdentifier, String label, NodeStyle nodeStyle) {
		LabelAlignment labelAlignment = nodeStyle.getLabelAlignment();
		LabelPosition labelPosition = nodeStyle.getLabelPosition();
		RGBValues labelColor = nodeStyle.getLabelColor();

		String labelIdentifier = nodeIdentifier + "__label"; //$NON-NLS-1$
		SiriusLabel siriusLabel = new SiriusLabel(labelIdentifier, label);

		// Colors
		JsonObject styleJsonObject = new JsonObject();
		styleJsonObject.addProperty("fill", //$NON-NLS-1$
				"rgb(" + labelColor.getRed() + ", " + labelColor.getGreen() + ", " + labelColor.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		String styleJson = new Gson().toJson(styleJsonObject);
		siriusLabel.setStyle(styleJson);

		// Alignment and position
		if (LabelAlignment.LEFT_VALUE == labelAlignment.getValue() && LabelPosition.BORDER == labelPosition.getValue()) {
			siriusLabel.setType(SiriusLabel.OUTSIDE_LEFT__LABEL_TYPE);
		} else if (LabelAlignment.CENTER_VALUE == labelAlignment.getValue() && LabelPosition.BORDER == labelPosition.getValue()) {
			siriusLabel.setType(SiriusLabel.OUTSIDE_CENTER__LABEL_TYPE);
		} else if (LabelAlignment.RIGHT_VALUE == labelAlignment.getValue() && LabelPosition.BORDER == labelPosition.getValue()) {
			siriusLabel.setType(SiriusLabel.OUTSIDE_RIGHT__LABEL_TYPE);
		} else if (LabelAlignment.LEFT_VALUE == labelAlignment.getValue() && LabelPosition.NODE == labelPosition.getValue()) {
			siriusLabel.setType(SiriusLabel.INSIDE_LEFT__LABEL_TYPE);
		} else if (LabelAlignment.CENTER_VALUE == labelAlignment.getValue() && LabelPosition.NODE == labelPosition.getValue()) {
			siriusLabel.setType(SiriusLabel.INSIDE_CENTER__LABEL_TYPE);
		} else if (LabelAlignment.RIGHT_VALUE == labelAlignment.getValue() && LabelPosition.NODE == labelPosition.getValue()) {
			siriusLabel.setType(SiriusLabel.INSIDE_RIGHT__LABEL_TYPE);
		}

		return Optional.of(siriusLabel);
	}

	/**
	 * Converts the given {@link DNode} to an {@link SPort}.
	 *
	 * @param borderedNode
	 *            The bordered node
	 * @return The {@link SNode} computed from the {@link DNode}
	 */
	private Optional<SPort> convertBorderedNode(DNode borderedNode) {
		Optional<SPort> optionalSPort = Optional.empty();

		Style style = borderedNode.getStyle();
		if (style instanceof WorkspaceImage) {
			WorkspaceImage workspaceImage = (WorkspaceImage) style;
			optionalSPort = this.convertSVGBorderedNode(borderedNode, workspaceImage);
		} else if (style instanceof Square) {
			Square square = (Square) style;
			optionalSPort = this.convertSquareBorderedNode(borderedNode, square);
		}

		return optionalSPort;
	}

	/**
	 * Converts the given {@link DNode} into an {@link SPort}.
	 *
	 * @param dNode
	 *            The DNode
	 * @param square
	 *            The square
	 * @return The SNode computed from the DNode
	 */
	private Optional<SPort> convertSquareBorderedNode(DNode dNode, Square square) {

		String identifier = EcoreUtil.getURI(dNode).fragment();

		RGBValues borderColor = square.getBorderColor();
		RGBValues color = square.getColor();
		Integer borderSize = square.getBorderSize();

		JsonObject styleJsonObject = new JsonObject();
		styleJsonObject.addProperty("fill", //$NON-NLS-1$
				"rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke", //$NON-NLS-1$
				"rgb(" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke-width", borderSize); //$NON-NLS-1$

		String styleJson = new Gson().toJson(styleJsonObject);

		SiriusSquareBorderNode siriusSquareBorderNode = new SiriusSquareBorderNode(identifier);
		siriusSquareBorderNode.setStyle(styleJson);
		return Optional.of(siriusSquareBorderNode);
	}

	/**
	 * Converts the given {@link DNode} into an {@link SNode}.
	 *
	 * @param dNode
	 *            The DNode
	 * @param workspaceImage
	 *            The workspace image used as the style of the DNode
	 * @return The SNode computed from the DNonde
	 */
	private Optional<SPort> convertSVGBorderedNode(DNode dNode, WorkspaceImage workspaceImage) {
		String identifier = EcoreUtil.getURI(dNode).fragment();

		String workspacePath = workspaceImage.getWorkspacePath();
		SiriusSvgBorderNode siriusSvgBorderNode = new SiriusSvgBorderNode(identifier);
		siriusSvgBorderNode.setUrl("http://localhost:8080/images" + workspacePath); //$NON-NLS-1$

		return Optional.of(siriusSvgBorderNode);
	}

	/**
	 * Converts the given {@link DNodeContainer} to an {@link SNode}.
	 *
	 * @param dNodeContainer
	 *            The container
	 * @return The {@link SNode} computed from the {@link DNodeContainer}
	 */
	private Optional<SNode> convertDNodeContainer(DNodeContainer dNodeContainer) {
		Optional<SNode> optionalSNode = Optional.empty();

		ContainerLayout containerLayout = dNodeContainer.getChildrenPresentation();
		Style style = dNodeContainer.getStyle();
		if (ContainerLayout.FREE_FORM_VALUE == containerLayout.getValue() && style instanceof FlatContainerStyle) {
			FlatContainerStyle flatContainerStyle = (FlatContainerStyle) style;
			optionalSNode = this.convertFreeFormFlatContainer(dNodeContainer, flatContainerStyle);
		} else if (ContainerLayout.LIST_VALUE == containerLayout.getValue() && style instanceof FlatContainerStyle) {
			FlatContainerStyle flatContainerStyle = (FlatContainerStyle) style;
			optionalSNode = this.convertListFlatContainer(dNodeContainer, flatContainerStyle);
		}

		optionalSNode.ifPresent(sNode -> {
			if (style instanceof ContainerStyle) {
				ContainerStyle containerStyle = (ContainerStyle) style;
				this.convertLabel(sNode.getId(), dNodeContainer.getName(), containerStyle).ifPresent(sNode.getChildren()::add);
			}
			// @formatter:off
			dNodeContainer.getOwnedBorderedNodes().stream()
				.filter(DDiagramElement::isVisible)
				.map(this::convertBorderedNode)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.forEach(sNode.getChildren()::add);
			// @formatter:on

			// @formatter:off
			dNodeContainer.getOwnedDiagramElements().stream()
				.filter(DDiagramElement::isVisible)
				.map(this::convertDDiagramElement)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.forEach(sNode.getChildren()::add);
			// @formatter:on
		});

		return optionalSNode;
	}

	/**
	 * Converts the given {@link DDiagramElementContainer} into an {@link SNode}.
	 *
	 * @param dDiagramElementContainer
	 *            The {@link DDiagramElementContainer}
	 * @param flatContainerStyle
	 *            The style of the container
	 * @return The {@link SNode} computed from the container
	 */
	private Optional<SNode> convertFreeFormFlatContainer(DDiagramElementContainer dDiagramElementContainer, FlatContainerStyle flatContainerStyle) {
		String identifier = EcoreUtil.getURI(dDiagramElementContainer).fragment();

		RGBValues borderColor = flatContainerStyle.getBorderColor();
		RGBValues color = flatContainerStyle.getBackgroundColor();
		Integer borderSize = flatContainerStyle.getBorderSize();

		JsonObject styleJsonObject = new JsonObject();
		styleJsonObject.addProperty("fill", //$NON-NLS-1$
				"rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke", //$NON-NLS-1$
				"rgb(" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke-width", borderSize); //$NON-NLS-1$

		String styleJson = new Gson().toJson(styleJsonObject);

		SiriusFreeFormFlatContainerNode siriusFreeFormFlatContainerNode = new SiriusFreeFormFlatContainerNode(identifier);
		siriusFreeFormFlatContainerNode.setStyle(styleJson);
		return Optional.of(siriusFreeFormFlatContainerNode);
	}

	/**
	 * Converts the given {@link DNodeList} to an {@link SNode}.
	 *
	 * @param dNodeList
	 *            The container
	 * @return The {@link SNode} computed from the {@link DNodeList}
	 */
	private Optional<SNode> convertDNodeList(DNodeList dNodeList) {
		Optional<SNode> optionalSNode = Optional.empty();

		Style style = dNodeList.getStyle();
		if (style instanceof FlatContainerStyle) {
			FlatContainerStyle flatContainerStyle = (FlatContainerStyle) style;
			optionalSNode = this.convertListFlatContainer(dNodeList, flatContainerStyle);
		}

		optionalSNode.ifPresent(sNode -> {
			final Double GAP = Double.valueOf(5.0);

			LayoutOptions labelCompartmentLayoutOptions = new LayoutOptions();
			labelCompartmentLayoutOptions.setHAlign("left"); //$NON-NLS-1$
			labelCompartmentLayoutOptions.setHGap(GAP);

			SiriusListFlatContainerNodeLabelCompartment labelCompartment = new SiriusListFlatContainerNodeLabelCompartment(
					sNode.getId() + "__label-compartement"); //$NON-NLS-1$
			labelCompartment.setLayoutOptions(labelCompartmentLayoutOptions);

			LayoutOptions bodyCompartmentLayoutOptions = new LayoutOptions();
			bodyCompartmentLayoutOptions.setHAlign("left"); //$NON-NLS-1$
			bodyCompartmentLayoutOptions.setHGap(GAP);
			bodyCompartmentLayoutOptions.setVGap(GAP);

			SiriusListFlatContainerNodeBodyCompartment bodyCompartment = new SiriusListFlatContainerNodeBodyCompartment(
					sNode.getId() + "__body-compartement"); //$NON-NLS-1$
			bodyCompartment.setLayoutOptions(bodyCompartmentLayoutOptions);

			sNode.getChildren().add(labelCompartment);
			sNode.getChildren().add(bodyCompartment);

			if (style instanceof ContainerStyle) {
				ContainerStyle containerStyle = (ContainerStyle) style;
				this.convertLabel(sNode.getId(), dNodeList.getName(), containerStyle).ifPresent(labelCompartment.getChildren()::add);
			}
			// @formatter:off
			dNodeList.getOwnedBorderedNodes().stream()
				.filter(DDiagramElement::isVisible)
				.map(this::convertBorderedNode)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.forEach(sNode.getChildren()::add);
			// @formatter:on

			// @formatter:off
			dNodeList.getOwnedElements().stream()
				.filter(DNodeListElement::isVisible)
				.map(this::convertDNodeListElement)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.forEach(bodyCompartment.getChildren()::add);
			// @formatter:on
		});

		return optionalSNode;
	}

	/**
	 * Converts the given {@link DDiagramElementContainer} into an {@link SNode}.
	 *
	 * @param dDiagramElementContainer
	 *            The {@link DDiagramElementContainer}
	 * @param flatContainerStyle
	 *            The style of the container
	 * @return The {@link SNode} computed from the container
	 */
	private Optional<SNode> convertListFlatContainer(DDiagramElementContainer dDiagramElementContainer, FlatContainerStyle flatContainerStyle) {
		String identifier = EcoreUtil.getURI(dDiagramElementContainer).fragment();

		RGBValues borderColor = flatContainerStyle.getBorderColor();
		RGBValues color = flatContainerStyle.getForegroundColor();
		Integer borderSize = flatContainerStyle.getBorderSize();

		JsonObject styleJsonObject = new JsonObject();
		styleJsonObject.addProperty("fill", //$NON-NLS-1$
				"rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke", //$NON-NLS-1$
				"rgb(" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		styleJsonObject.addProperty("stroke-width", borderSize); //$NON-NLS-1$

		String styleJson = new Gson().toJson(styleJsonObject);

		SiriusListFlatContainerNode siriusListFlatContainerNode = new SiriusListFlatContainerNode(identifier);
		siriusListFlatContainerNode.setStyle(styleJson);
		return Optional.of(siriusListFlatContainerNode);
	}

	/**
	 * Converts the given {@link DNodeListElement} to an {@link SLabel}.
	 *
	 * @param dNodeList
	 *            The container
	 * @return The {@link SLabel} computed from the {@link DNodeListElement}
	 */
	private Optional<SLabel> convertDNodeListElement(DNodeListElement dNodeListElement) {
		String identifier = EcoreUtil.getURI(dNodeListElement).fragment();
		String label = dNodeListElement.getName();

		SiriusLabel siriusLabel = new SiriusLabel(identifier, label);
		siriusLabel.setType(SiriusLabel.INSIDE_LEFT__LABEL_TYPE);

		JsonObject styleJsonObject = new JsonObject();
		styleJsonObject.addProperty("fill", "black"); //$NON-NLS-1$//$NON-NLS-2$

		String styleJson = new Gson().toJson(styleJsonObject);
		siriusLabel.setStyle(styleJson);

		return Optional.of(siriusLabel);
	}

	/**
	 * Converts the label from the given container style.
	 *
	 * @param containerIdentifier
	 *            The identifier of the container
	 * @param label
	 *            The text to be displayed in the label
	 * @param containerStyle
	 *            The style of the container
	 * @return The {@link SLabel} converted
	 */
	private Optional<SLabel> convertLabel(String containerIdentifier, String label, ContainerStyle containerStyle) {
		LabelAlignment labelAlignment = containerStyle.getLabelAlignment();
		RGBValues labelColor = containerStyle.getLabelColor();

		String labelIdentifier = containerIdentifier + "__label"; //$NON-NLS-1$
		SiriusLabel siriusLabel = new SiriusLabel(labelIdentifier, label);

		// Colors
		JsonObject styleJsonObject = new JsonObject();
		styleJsonObject.addProperty("fill", //$NON-NLS-1$
				"rgb(" + labelColor.getRed() + ", " + labelColor.getGreen() + ", " + labelColor.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		String styleJson = new Gson().toJson(styleJsonObject);
		siriusLabel.setStyle(styleJson);

		// Alignment and position
		if (LabelAlignment.LEFT_VALUE == labelAlignment.getValue()) {
			siriusLabel.setType(SiriusLabel.INSIDE_LEFT__LABEL_TYPE);
		} else if (LabelAlignment.CENTER_VALUE == labelAlignment.getValue()) {
			siriusLabel.setType(SiriusLabel.INSIDE_CENTER__LABEL_TYPE);
		} else if (LabelAlignment.RIGHT_VALUE == labelAlignment.getValue()) {
			siriusLabel.setType(SiriusLabel.INSIDE_RIGHT__LABEL_TYPE);
		}

		return Optional.of(siriusLabel);
	}

	/**
	 * Converts the given {@link DEdge} into an {@link SEdge}.
	 *
	 * @param dEdge
	 *            The {@link DEdge} to convert
	 * @return The {@link SEdge} computed from the {@link DEdge}
	 */
	private Optional<SEdge> convertDEdge(DEdge dEdge) {
		SiriusEdge siriusEdge = new SiriusEdge();

		siriusEdge.setId(EcoreUtil.getURI(dEdge).fragment());
		siriusEdge.setType("edge:straight"); //$NON-NLS-1$
		siriusEdge.setSourceId(EcoreUtil.getURI(dEdge.getSourceNode()).fragment());
		siriusEdge.setTargetId(EcoreUtil.getURI(dEdge.getTargetNode()).fragment());

		Style style = dEdge.getStyle();
		if (style instanceof EdgeStyle) {
			EdgeStyle edgeStyle = (EdgeStyle) style;
			RGBValues strokeColor = edgeStyle.getStrokeColor();
			Integer size = edgeStyle.getSize();

			JsonObject styleJsonObject = new JsonObject();
			styleJsonObject.addProperty("stroke", //$NON-NLS-1$
					"rgb(" + strokeColor.getRed() + ", " + strokeColor.getGreen() + ", " + strokeColor.getBlue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			styleJsonObject.addProperty("stroke-width", size); //$NON-NLS-1$

			String styleJson = new Gson().toJson(styleJsonObject);
			siriusEdge.setStyle(styleJson);
		}

		return Optional.of(siriusEdge);
	}

}
