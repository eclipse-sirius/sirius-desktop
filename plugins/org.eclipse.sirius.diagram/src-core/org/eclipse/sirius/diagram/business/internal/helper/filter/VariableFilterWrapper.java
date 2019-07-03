/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.helper.filter;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.diagram.TypedVariableValue;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.TypedVariable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Wrapper handling available variable for a filter.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class VariableFilterWrapper implements Adapter {

    private VariableFilter variableFilter;

    private Multimap<String, EObject> variables;

    private Map<String, Object> typedVariables;

    private DDiagram curDiagram;

    /**
     * Initialize the wrapper with given variable filter.
     * 
     * @param variableFilter
     *            the {@link VariableFilter} wrapped.
     */
    public VariableFilterWrapper(VariableFilter variableFilter) {
        super();
        this.variableFilter = variableFilter;
    }

    /**
     * Returns true if the given element is visible. False otherwise.
     * 
     * @param element
     *            the element from which we want to know if it is visible.
     * @return true if the given element is visible. False otherwise.
     */
    public boolean isVisible(final DDiagramElement element) {
        //
        // If the element has no container it will be deleted.
        // Let's return false.
        DDiagram parentDiagram = element.getParentDiagram();
        if (parentDiagram == null) {
            return false;
        }

        /*
         * We'll init the variables using the history contained in the viewpoint.
         */
        getVariablesFromDiagram(parentDiagram);
        boolean valid = true;
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element);
        /*
         * Prepare the variables...
         */
        if (variables != null) {
            for (final String key : variables.keySet()) {
                final Collection<EObject> value = variables.get(key);
                interpreter.setVariable(key, value);
            }
        }
        if (typedVariables != null) {
            for (final String key : typedVariables.keySet()) {
                final Object value = typedVariables.get(key);
                interpreter.setVariable(key, value);
            }
        }

        if (variableFilter.getSemanticConditionExpression() != null) {
            final EObject target = ((DSemanticDecorator) element).getTarget();
            if (target == null || target.eResource() == null) {
                valid = false;
            } else {
                try {
                    valid = interpreter.evaluateBoolean(element.getTarget(), variableFilter.getSemanticConditionExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(variableFilter, FilterPackage.eINSTANCE.getVariableFilter_SemanticConditionExpression(), e);
                }
            }
        }

        // Unset the variables
        if (variables != null) {
            for (final String key : variables.keySet()) {
                interpreter.unSetVariable(key);
            }
        }
        if (typedVariables != null) {
            for (final String key : typedVariables.keySet()) {
                interpreter.unSetVariable(key);
            }
        }
        return valid;
    }

    private void getVariablesFromDiagram(final DDiagram dDiagram) {
        if (curDiagram == null || dDiagram != curDiagram) {
            variables = ArrayListMultimap.create();
            typedVariables = new LinkedHashMap<>();

            if (dDiagram.getFilterVariableHistory() != null) {
                final Iterator<VariableValue> it = dDiagram.getFilterVariableHistory().getOwnedValues().iterator();
                while (it.hasNext()) {
                    final VariableValue value = it.next();
                    if (value instanceof EObjectVariableValue) {
                        EObjectVariableValue objectVarValue = (EObjectVariableValue) value;
                        if (variableFilter.getOwnedVariables().contains(objectVarValue.getVariableDefinition())) {
                            variables.put(((AbstractVariable) objectVarValue.getVariableDefinition()).getName(), objectVarValue.getModelElement());
                        }
                    } else if (value instanceof TypedVariableValue) {
                        TypedVariableValue typeVariableValue = (TypedVariableValue) value;
                        TypedVariable variableDefinition = typeVariableValue.getVariableDefinition();
                        if (variableFilter.getOwnedVariables().contains(variableDefinition)) {
                            // Instantiate value which dataType is defined on
                            // TypedVariable.valueType.
                            Object convertedObject = EcoreUtil.createFromString(variableDefinition.getValueType(), typeVariableValue.getValue());
                            typedVariables.put(((AbstractVariable) typeVariableValue.getVariableDefinition()).getName(), convertedObject);
                        }
                    }
                }
            }
            curDiagram = dDiagram;
        }
    }

    /**
     * Reset the variables.
     */
    public void resetVariables() {
        this.curDiagram = null;
    }

    @Override
    public void notifyChanged(Notification notification) {
        // not used
    }

    @Override
    public Notifier getTarget() {
        // not used
        return null;
    }

    @Override
    public void setTarget(Notifier newTarget) {
        // not used
    }

    @Override
    public boolean isAdapterForType(Object type) {
        // not used
        return false;
    }
}
