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

import org.eclipse.sprotty.Action;
import org.eclipse.sprotty.CenterAction;
import org.eclipse.sprotty.CollapseExpandAction;
import org.eclipse.sprotty.CollapseExpandAllAction;
import org.eclipse.sprotty.ComputedBoundsAction;
import org.eclipse.sprotty.ExportSvgAction;
import org.eclipse.sprotty.FitToScreenAction;
import org.eclipse.sprotty.OpenAction;
import org.eclipse.sprotty.RequestBoundsAction;
import org.eclipse.sprotty.RequestExportSvgAction;
import org.eclipse.sprotty.RequestModelAction;
import org.eclipse.sprotty.RequestPopupModelAction;
import org.eclipse.sprotty.SelectAction;
import org.eclipse.sprotty.SelectAllAction;
import org.eclipse.sprotty.ServerStatusAction;
import org.eclipse.sprotty.SetBoundsAction;
import org.eclipse.sprotty.SetModelAction;
import org.eclipse.sprotty.SetPopupModelAction;
import org.eclipse.sprotty.UpdateModelAction;

/**
 * Switch used to handle the various actions.
 *
 * @author sbegaudeau
 *
 * @param <T>
 *            The type of result expected by the switch
 */
public class SiriusActionSwitch<T> {

	/**
	 * Returns the default value.
	 *
	 * @return The default value
	 */
	protected T getDefaultValue() {
		return null;
	}

	/**
	 * Dispatch the action.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T doSwitch(Action action) {
		T result = this.getDefaultValue();
		switch (action.getKind()) {
		case CenterAction.KIND:
			if (action instanceof CenterAction) {
				result = this.caseCenterAction((CenterAction) action);
			}
			break;
		case CollapseExpandAction.KIND:
			if (action instanceof CollapseExpandAction) {
				result = this.caseCollapseExpandAction((CollapseExpandAction) action);
			}
			break;
		case CollapseExpandAllAction.KIND:
			if (action instanceof CollapseExpandAllAction) {
				result = this.caseCollapseExpandAllAction((CollapseExpandAllAction) action);
			}
			break;
		case ComputedBoundsAction.KIND:
			if (action instanceof ComputedBoundsAction) {
				result = this.caseComputedBoundsAction((ComputedBoundsAction) action);
			}
			break;
		case ExecuteContainerCreationToolAction.KIND:
			if (action instanceof ExecuteContainerCreationToolAction) {
				result = this.caseExecuteContainerCreationToolAction((ExecuteContainerCreationToolAction) action);
			}
			break;
		case ExecuteNodeCreationToolAction.KIND:
			if (action instanceof ExecuteNodeCreationToolAction) {
				result = this.caseExecuteNodeCreationToolAction((ExecuteNodeCreationToolAction) action);
			}
			break;
		case ExecuteToolAction.KIND:
			if (action instanceof ExecuteToolAction) {
				result = this.caseExecuteToolAction((ExecuteToolAction) action);
			}
			break;
		case ExportSvgAction.KIND:
			if (action instanceof ExportSvgAction) {
				result = this.caseExportSvgAction((ExportSvgAction) action);
			}
			break;
		case FitToScreenAction.KIND:
			if (action instanceof FitToScreenAction) {
				result = this.caseFitToScreenAction((FitToScreenAction) action);
			}
			break;
		case OpenAction.KIND:
			if (action instanceof OpenAction) {
				result = this.caseOpenAction((OpenAction) action);
			}
			break;
		case RequestBoundsAction.KIND:
			if (action instanceof RequestBoundsAction) {
				result = this.caseRequestBoundsAction((RequestBoundsAction) action);
			}
			break;
		case RequestExportSvgAction.KIND:
			if (action instanceof RequestExportSvgAction) {
				result = this.caseRequestExportSvgAction((RequestExportSvgAction) action);
			}
			break;
		case RequestModelAction.KIND:
			if (action instanceof RequestModelAction) {
				result = this.caseRequestModelAction((RequestModelAction) action);
			}
			break;
		case RequestPopupModelAction.KIND:
			if (action instanceof RequestPopupModelAction) {
				result = this.caseRequestPopupModelAction((RequestPopupModelAction) action);
			}
			break;
		case RequestLayersAction.KIND:
			if (action instanceof RequestLayersAction) {
				result = this.caseRequestLayersAction((RequestLayersAction) action);
			}
			break;
		case RequestToolsAction.KIND:
			if (action instanceof RequestToolsAction) {
				result = this.caseRequestToolsAction((RequestToolsAction) action);
			}
			break;
		case SelectAction.KIND:
			if (action instanceof SelectAction) {
				result = this.caseSelectAction((SelectAction) action);
			}
			break;
		case SelectAllAction.KIND:
			if (action instanceof SelectAllAction) {
				result = this.caseSelectAllAction((SelectAllAction) action);
			}
			break;
		case ServerStatusAction.KIND:
			if (action instanceof ServerStatusAction) {
				result = this.caseServerStatusAction((ServerStatusAction) action);
			}
			break;
		case SetBoundsAction.KIND:
			if (action instanceof SetBoundsAction) {
				result = this.caseSetBoundsAction((SetBoundsAction) action);
			}
			break;
		case SetLayersAction.KIND:
			if (action instanceof SetLayersAction) {
				result = this.caseSetLayersAction((SetLayersAction) action);
			}
			break;
		case SetModelAction.KIND:
			if (action instanceof SetModelAction) {
				result = this.caseSetModelAction((SetModelAction) action);
			}
			break;
		case SetPopupModelAction.KIND:
			if (action instanceof SetPopupModelAction) {
				result = this.caseSetPopupModelAction((SetPopupModelAction) action);
			}
			break;
		case SetToolsAction.KIND:
			if (action instanceof SetToolsAction) {
				result = this.caseSetToolsAction((SetToolsAction) action);
			}
			break;
		case ToggleLayerAction.KIND:
			if (action instanceof ToggleLayerAction) {
				result = this.caseToggleLayerAction((ToggleLayerAction) action);
			}
			break;
		case UpdateModelAction.KIND:
			if (action instanceof UpdateModelAction) {
				result = this.caseUpdateModelAction((UpdateModelAction) action);
			}
			break;
		default:
			result = this.defaultCase(action);
		}
		return result;
	}

	/**
	 * Handles the {@link CenterAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseCenterAction(CenterAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link CollapseExpandAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseCollapseExpandAction(CollapseExpandAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link CollapseExpandAllAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseCollapseExpandAllAction(CollapseExpandAllAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link ComputedBoundsAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseComputedBoundsAction(ComputedBoundsAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link ExecuteContainerCreationToolAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseExecuteContainerCreationToolAction(ExecuteContainerCreationToolAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link ExecuteNodeCreationToolAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseExecuteNodeCreationToolAction(ExecuteNodeCreationToolAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link ExecuteToolAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseExecuteToolAction(ExecuteToolAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link ExportSvgAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseExportSvgAction(ExportSvgAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link FitToScreenAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseFitToScreenAction(FitToScreenAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link OpenAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseOpenAction(OpenAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link RequestBoundsAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseRequestBoundsAction(RequestBoundsAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link RequestExportSvgAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseRequestExportSvgAction(RequestExportSvgAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link RequestModelAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseRequestModelAction(RequestModelAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link RequestPopupModelAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseRequestPopupModelAction(RequestPopupModelAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link RequestLayersAction}.
	 *
	 * @param action
	 *            The action
	 *
	 * @return The expected result
	 */
	public T caseRequestLayersAction(RequestLayersAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link RequestToolsAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseRequestToolsAction(RequestToolsAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link SelectAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseSelectAction(SelectAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link SelectAllAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseSelectAllAction(SelectAllAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link ServerStatusAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseServerStatusAction(ServerStatusAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link SetBoundsAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseSetBoundsAction(SetBoundsAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link SetLayersAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseSetLayersAction(SetLayersAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link SetModelAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseSetModelAction(SetModelAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link SetPopupModelAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseSetPopupModelAction(SetPopupModelAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link SetToolsAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseSetToolsAction(SetToolsAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link ToggleLayerAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseToggleLayerAction(ToggleLayerAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the {@link UpdateModelAction}.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T caseUpdateModelAction(UpdateModelAction action) {
		return this.getDefaultValue();
	}

	/**
	 * Handles the default case.
	 *
	 * @param action
	 *            The action
	 * @return The expected result
	 */
	public T defaultCase(Action action) {
		return this.getDefaultValue();
	}
}
