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
package org.eclipse.sirius.server.diagram.internal.actions.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.server.diagram.internal.SiriusDiagramServer;
import org.eclipse.sirius.server.diagram.internal.actions.ISiriusActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.RequestLayersAction;
import org.eclipse.sirius.server.diagram.internal.actions.SetLayersAction;
import org.eclipse.sirius.server.diagram.internal.actions.SiriusLayer;
import org.eclipse.sprotty.Action;

/**
 * Handler for the {@link RequestLayersAction} received by the {@link SiriusDiagramServer}.
 *
 * @author gcoutable
 */
public class SiriusRequestLayersActionHandler implements ISiriusActionHandler {

	@Override
	public boolean canHandle(SiriusDiagramServer server, Action action) {
		return action instanceof RequestLayersAction;
	}

	@Override
	public void handle(SiriusDiagramServer siriusDiagramServer, Action action) {
		if (action instanceof RequestLayersAction) {
			SetLayersAction setLayersAction = this.computeLayersList(siriusDiagramServer, (RequestLayersAction) action);
			siriusDiagramServer.dispatch(setLayersAction);
		}
	}

	/**
	 * Computes the lists of sirius layers for the requested representation.
	 *
	 * @param siriusDiagramServer
	 *            The {@link SiriusDiagramServer}
	 * @param action
	 *            The action
	 * @return The sirius layers list
	 */
	private SetLayersAction computeLayersList(SiriusDiagramServer siriusDiagramServer, RequestLayersAction action) {
		DDiagram dDiagram = siriusDiagramServer.getDDiagram();

		EList<Layer> allLayers = LayerHelper.getAllLayers(dDiagram.getDescription());
		EList<Layer> activatedLayers = dDiagram.getActivatedLayers();
		//@formatter:off
		List<SiriusLayer> siriusLayers = allLayers.stream()
				.filter(AdditionalLayer.class::isInstance)
				.map(layer -> {
					boolean isActive = activatedLayers.contains(layer);
					return computeSiriusLayerFromLayer(layer, isActive);
				})
				.collect(Collectors.toList());
		//@formatter:on

		return new SetLayersAction(siriusLayers);
	}

	/**
	 * Computes the {@link SiriusLayer} for the given layer.
	 *
	 * @param layer
	 *            The layer
	 * @param isActive
	 *            Indicates if the layer is active or not
	 * @return The {@link SiriusLayer}
	 */
	private SiriusLayer computeSiriusLayerFromLayer(Layer layer, boolean isActive) {
		SiriusLayer siriusLayer = new SiriusLayer();
		siriusLayer.setId(layer.getName());
		siriusLayer.setName(layer.getLabel());
		siriusLayer.setActive(isActive);
		return siriusLayer;
	}

}
