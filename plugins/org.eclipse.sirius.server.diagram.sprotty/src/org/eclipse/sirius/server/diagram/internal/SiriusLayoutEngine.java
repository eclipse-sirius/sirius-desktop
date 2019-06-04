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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.alg.layered.options.LayeringStrategy;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.HierarchyHandling;
import org.eclipse.elk.core.options.NodeLabelPlacement;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.options.SizeOptions;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusSquareBorderNode;
import org.eclipse.sirius.server.diagram.internal.entities.SiriusSvgBorderNode;
import org.eclipse.sprotty.SGraph;
import org.eclipse.sprotty.SModelRoot;
import org.eclipse.sprotty.layout.ElkLayoutEngine;
import org.eclipse.sprotty.layout.SprottyLayoutConfigurator;

/**
 * The {@link SiriusLayoutEngine} defines the configuration of the layout that ELK should apply to a given Sprotty
 * model.
 *
 * @author sbegaudeau
 */
public class SiriusLayoutEngine extends ElkLayoutEngine {
	/**
	 * The logger.
	 */
	private static final Logger LOG = Logger.getLogger(SiriusLayoutEngine.class);

	/**
	 * The space between two nodes.
	 */
	private static final Double SPACING_NODE_NODE = Double.valueOf(50.0);

	/**
	 * The space between node and edges.
	 */
	private static final Double SPACING_NODE_EDGE = Double.valueOf(30.0);

	@Override
	public void layout(SModelRoot root) {
		if (root instanceof SGraph) {
			SprottyLayoutConfigurator configurator = new SprottyLayoutConfigurator();
			configurator.configureByType("graph") //$NON-NLS-1$
					.setProperty(CoreOptions.ALGORITHM, LayeredOptions.ALGORITHM_ID)
					.setProperty(CoreOptions.HIERARCHY_HANDLING, HierarchyHandling.INCLUDE_CHILDREN)
					.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.NETWORK_SIMPLEX)
					.setProperty(LayeredOptions.SPACING_NODE_NODE, SPACING_NODE_NODE)
					.setProperty(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS, SPACING_NODE_NODE)
					.setProperty(LayeredOptions.SPACING_EDGE_NODE, SPACING_NODE_EDGE)
					.setProperty(LayeredOptions.SPACING_EDGE_NODE_BETWEEN_LAYERS, SPACING_NODE_EDGE);

			configurator.configureByType("node:flatcontainer") //$NON-NLS-1$
					.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.free())
					.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(120, 100))
					.setProperty(CoreOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.insideTopCenter());

			configurator.configureByType("node:freeformflatcontainer") //$NON-NLS-1$
					.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.free())
					.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(120, 100))
					.setProperty(CoreOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.insideTopCenter());

			configurator.configureByType("node:svg") //$NON-NLS-1$
					.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.free())
					.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(30, 30))
					.setProperty(CoreOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.outsideBottomCenter());

			configurator.configureByType("node:listflatcontainer") //$NON-NLS-1$
					.setProperty(CoreOptions.NODE_SIZE_CONSTRAINTS, SizeConstraint.free())
					.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(120, 100))
					.setProperty(CoreOptions.NODE_SIZE_OPTIONS, EnumSet.of(SizeOptions.DEFAULT_MINIMUM_SIZE));

			configurator.configureByType(SiriusSquareBorderNode.TYPE).setProperty(LayeredOptions.PORT_SIDE, PortSide.WEST);
			configurator.configureByType(SiriusSvgBorderNode.TYPE).setProperty(LayeredOptions.PORT_SIDE, PortSide.WEST);

			this.layout((SGraph) root, configurator);
		}
	}

	@Override
	protected void applyEngine(ElkNode elkGraph) {
		/*
		 * Transform the ELK model into XMI in order to log it. This code allow us to have some information in order to
		 * be able to debug the behavior of ELK. It does not give us a full understanding of the behavior of ELK but it
		 * give us the shape of the constraints that ELK has understood from our code above.
		 **/
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI.createFileURI("output.elkg")); //$NON-NLS-1$
		resource.getContents().add(elkGraph);
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			resource.save(outputStream, new HashMap<>());
			String elkData = outputStream.toString();
			LOG.info(elkData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		super.applyEngine(elkGraph);
	}
}
