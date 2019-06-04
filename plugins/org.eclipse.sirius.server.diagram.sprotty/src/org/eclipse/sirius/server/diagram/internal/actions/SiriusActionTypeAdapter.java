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
package org.eclipse.sirius.server.diagram.internal.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import org.eclipse.sprotty.Action;
import org.eclipse.sprotty.server.json.ActionTypeAdapter;
import org.eclipse.sprotty.server.json.EnumTypeAdapter;

/**
 * Gson type adapter for sirius sprotty actions.
 *
 * @author gcoutable
 *
 */
public class SiriusActionTypeAdapter extends ActionTypeAdapter {

	/**
	 * Constructor.
	 *
	 * @param gson
	 *            The {@link Gson} object
	 * @param actionKinds
	 *            The map of action kind to {@link Action}
	 */
	public SiriusActionTypeAdapter(Gson gson, Map<String, Class<? extends Action>> actionKinds) {
		super(gson, actionKinds);
	}

	/**
	 * Configure a Gson builder with sirius sprotty actions.
	 */
	public static GsonBuilder configureGson(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapterFactory(new SiriusActionTypeAdapter.SiriusFactory());
		gsonBuilder.registerTypeAdapterFactory(new EnumTypeAdapter.Factory());
		return gsonBuilder;
	}

	/**
	 * Type adapter factory for sirius sprotty actions. Action classes are registered via their {@code kind} attribute
	 * using {@link #addActionKind(String, Class)}.
	 */
	public static class SiriusFactory extends Factory {

		/**
		 * Constructor.
		 */
		public SiriusFactory() {
			super();
			addSiriusActionKinds();
		}

		/**
		 * Register sirius sprotty actions.
		 */
		private void addSiriusActionKinds() {
			addActionKind(ExecuteContainerCreationToolAction.KIND, ExecuteContainerCreationToolAction.class);
			addActionKind(ExecuteNodeCreationToolAction.KIND, ExecuteNodeCreationToolAction.class);
			addActionKind(ExecuteToolAction.KIND, ExecuteToolAction.class);
			addActionKind(RequestLayersAction.KIND, RequestLayersAction.class);
			addActionKind(RequestToolsAction.KIND, RequestToolsAction.class);
			addActionKind(SetLayersAction.KIND, SetLayersAction.class);
			addActionKind(SetToolsAction.KIND, SetToolsAction.class);
			addActionKind(ToggleLayerAction.KIND, ToggleLayerAction.class);
		}
	}
}
