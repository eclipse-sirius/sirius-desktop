/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.helper.delete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.business.api.delete.IDeleteHook;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.internal.helper.delete.DeleteHookDescriptorRegistry;
import org.eclipse.sirius.business.internal.helper.delete.IDeleteHookDescriptor;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteHook;
import org.eclipse.sirius.diagram.description.tool.DeleteHookParameter;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * .
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class DeleteHookHelper {

    private Collection<DSemanticDecorator> selection;

    /**
     * the executable hooks.
     */
    private List<IDeleteHook> hooks;

    /**
     * the delete hooks.
     */
    private Collection<DeleteHook> deleteHooks;

    /**
     * Construct a new instance.
     * 
     * @param selection
     *            the current selection
     */
    public DeleteHookHelper(final Collection<DSemanticDecorator> selection) {
        this.selection = selection;
    }

    /**
     * Check if an delete hook should be called, and if necessary called it and
     * return its state.
     * 
     * @return <code>true</code> if there is no delete hook, or if delete hook
     *         returns an OK state, <code>false</code> otherwise
     */
    public boolean checkDeleteHook() {
        if (shouldCallDeleteHook()) {
            return callDeleteHooks();
        }
        return true;
    }

    private boolean shouldCallDeleteHook() {
        computeUniqueHooks();
        if (!deleteHooks.isEmpty()) {
            hooks = new ArrayList<IDeleteHook>();
            for (final DeleteHook deleteHook : deleteHooks) {
                IDeleteHookDescriptor descriptorFromId = DeleteHookDescriptorRegistry.getDescriptorFromId(deleteHook.getId());
                if (descriptorFromId != null && descriptorFromId.getIDeleteHook() != null) {
                    IDeleteHook iDeleteHook = descriptorFromId.getIDeleteHook();
                    hooks.add(iDeleteHook);
                }
            }
            return !hooks.isEmpty();
        }
        return false;
    }

    private boolean callDeleteHooks() {
        for (final DeleteHook deleteHook : deleteHooks) {
            for (final IDeleteHook hook : hooks) {
                final IStatus status = hook.beforeDeleteCommandExecution(selection, getParameters(deleteHook));
                if (status.getSeverity() == IStatus.CANCEL) {
                    return false;
                }
            }
        }
        return true;
    }

    private Map<String, Object> getParameters(final DeleteHook deleteHook) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        final IInterpreter interpreter = InterpreterUtil.getInterpreter(selection.iterator().next().getTarget());
        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
        for (final DeleteHookParameter parameter : deleteHook.getParameters()) {
            Object value = null;
            if (selection.size() == 1) {
                value = safeInterpreter.evaluate(selection.iterator().next(), parameter, ToolPackage.eINSTANCE.getExternalJavaActionParameter_Value());
            } else {
                value = new ArrayList<Object>(selection.size());
                for (final DSemanticDecorator semanticDecorator : selection) {
                    final Object val = safeInterpreter.evaluate(semanticDecorator, parameter, ToolPackage.eINSTANCE.getExternalJavaActionParameter_Value());
                    if (val != null) {
                        ((List) value).add(val);
                    }
                }
            }
            parameters.put(parameter.getName(), value);
        }
        return parameters;
    }

    /**
     * Compute a collection of different delete hook for the given selection.
     * Two delete hooks are equal if the have the same id.
     */
    private void computeUniqueHooks() {
        deleteHooks = new LinkedHashSet<DeleteHook>();
        /* collect all hooks */
        for (final DSemanticDecorator selectedElement : selection) {
            if (selectedElement instanceof DDiagramElement) {
                final DeleteElementDescription tool = getTool((DDiagramElement) selectedElement);
                if (tool != null && tool.getHook() != null && tool.getHook().getId() != null) {
                    deleteHooks.add(tool.getHook());
                }
            }
        }
        /* remove hooks if there is already one with the same id */
        final Set<String> ids = new HashSet<String>();
        final Iterator<DeleteHook> it = deleteHooks.iterator();
        while (it.hasNext()) {
            final DeleteHook deleteHook = it.next();
            if (ids.contains(deleteHook.getId())) {
                it.remove();
            } else {
                ids.add(deleteHook.getId());
            }
        }
    }

    private DeleteElementDescription getTool(final DDiagramElement diagramElement) {
        DiagramElementMapping mapping = null;
        mapping = diagramElement.getDiagramElementMapping();
        if (mapping != null) {
            return mapping.getDeletionDescription();
        }
        return null;
    }

}
