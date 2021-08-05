/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.SelectionDescriptionHelper;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.diagram.TypedVariableValue;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.diagram.business.internal.helper.filter.VariableFilterWrapper;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;
import org.eclipse.sirius.viewpoint.description.TypedVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Utility classes for Filters user interfaces.
 *
 * @author cbrun
 *
 */
public final class FilterTools {

    /**
     * Avoid instanciation.
     */
    private FilterTools() {

    }

    /**
     * Ask for filter values in a diagram.
     *
     * @param diagram
     *            the diagram
     * @param filter
     *            the filter
     * @throws InterruptedException
     *             if the user cancels the wizard
     */
    public static void askForFilterValues(final DSemanticDiagram diagram, final VariableFilter filter) throws InterruptedException {
        final EObject model = diagram.getTarget();
        final UICallBack uiCallback = SiriusEditPlugin.getPlugin().getUiCallback();
        /*
         * First let's clear the Values history corresponding to this filter in the viewpoint
         */
        if (diagram.getFilterVariableHistory() != null) {
            final Iterator<VariableValue> it = diagram.getFilterVariableHistory().getOwnedValues().iterator();
            while (it.hasNext()) {
                final VariableValue value = it.next();
                if (value instanceof EObjectVariableValue) {
                    EObjectVariableValue objVarValue = (EObjectVariableValue) value;
                    if (objVarValue.getVariableDefinition() == null || objVarValue.getModelElement() == null || filter.getOwnedVariables().contains(objVarValue.getVariableDefinition())) {
                        it.remove();
                    }
                } else if (value instanceof TypedVariableValue) {
                    TypedVariableValue typedVarValue = (TypedVariableValue) value;
                    if (typedVarValue.getVariableDefinition() == null || filter.getOwnedVariables().contains(typedVarValue.getVariableDefinition())) {
                        it.remove();
                    }
                }
            }
        }

        /*
         * Now lets add the new ones.
         */
        final Iterator<InteractiveVariableDescription> itVar = filter.getOwnedVariables().iterator();

        List<TypedVariable> typedVariableList = new ArrayList<TypedVariable>();
        while (itVar.hasNext()) {
            final InteractiveVariableDescription currentVar = itVar.next();
            if (currentVar instanceof SelectModelElementVariable) {
                final SelectModelElementVariable var = (SelectModelElementVariable) currentVar;
                final TreeItemWrapper input = new TreeItemWrapper(null, null);
                FilterTools.computeInput(diagram, model, var, input);

                if (!var.isMultiple()) {
                    final EObject modelElement = uiCallback.askForEObject(var.getMessage(), input, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());

                    final EObjectVariableValue newValue = DiagramFactory.eINSTANCE.createEObjectVariableValue();
                    newValue.setModelElement(modelElement);
                    newValue.setVariableDefinition(var);
                    if (diagram.getFilterVariableHistory() != null) {
                        diagram.getFilterVariableHistory().getOwnedValues().add(newValue);
                    }
                } else {
                    EList<EObject> values = new BasicEList<EObject>();
                    final Collection<EObject> modelElements = uiCallback.askForEObjects(var.getMessage(), input, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());

                    values.addAll(modelElements);
                    EList<VariableValue> variables = new BasicEList<VariableValue>();
                    for (EObject modelElement : values) {
                        EObjectVariableValue newValue = DiagramFactory.eINSTANCE.createEObjectVariableValue();
                        newValue.setModelElement(modelElement);
                        newValue.setVariableDefinition(var);
                        variables.add(newValue);
                    }
                    if (diagram.getFilterVariableHistory() != null) {
                        diagram.getFilterVariableHistory().getOwnedValues().addAll(variables);
                    }
                }
            } else if (currentVar instanceof TypedVariable) {
                typedVariableList.add((TypedVariable) currentVar);
            }
        }

        List<VariableValue> variables = getTypedVariableValue(uiCallback, typedVariableList, diagram);

        if (diagram.getFilterVariableHistory() != null) {
            diagram.getFilterVariableHistory().getOwnedValues().addAll(variables);
        }

        /*
         * Now changing the model structure in a Map
         */
        Optional<VariableFilterWrapper> variableFilterWrapper = filter.eAdapters().stream().filter(VariableFilterWrapper.class::isInstance).map(VariableFilterWrapper.class::cast).findFirst();
        if (variableFilterWrapper.isPresent()) {
            variableFilterWrapper.get().resetVariables();
        }
    }

    private static List<VariableValue> getTypedVariableValue(UICallBack uiCallback, List<TypedVariable> typedVariableList, DSemanticDiagram diagram) {
        List<VariableValue> variables = new ArrayList<VariableValue>();
        if (typedVariableList.size() > 0) {
            try {
                List<String> values = null;
                List<String> defaultValues = computeDefaultValues(typedVariableList, diagram);
                values = uiCallback.askForTypedVariable(typedVariableList, defaultValues);
                for (int i = 0; i < typedVariableList.size(); i++) {
                    TypedVariable variableDef = typedVariableList.get(i);
                    String value = values.get(i);
                    if (!StringUtil.isEmpty(value)) {
                        TypedVariableValue newVariableValue = DiagramFactory.eINSTANCE.createTypedVariableValue();
                        newVariableValue.setValue(value);
                        newVariableValue.setVariableDefinition(variableDef);
                        variables.add(newVariableValue);
                    }
                }
            } catch (InterruptedException e) {
            }
        }
        return variables;
    }

    private static List<String> computeDefaultValues(List<TypedVariable> typedVariableList, DSemanticDiagram diagram) {
        List<String> defaultValues = new ArrayList<>();

        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(diagram);
        EObject diagramSemanticObject = diagram.getTarget();
        for (TypedVariable typedVariable : typedVariableList) {
            String defaultValueExpression = typedVariable.getDefaultValueExpression();
            String defaultValue = ""; //$NON-NLS-1$
            if (!StringUtil.isEmpty(defaultValueExpression)) {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
                try {
                    defaultValue = interpreter.evaluateString(diagramSemanticObject, typedVariable.getDefaultValueExpression());
                } catch (EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(typedVariable, DescriptionPackage.eINSTANCE.getTypedVariable_DefaultValueExpression(), e);
                } finally {
                    interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                }
            }
            defaultValues.add(defaultValue);
        }
        return defaultValues;
    }

    private static void computeInput(final DSemanticDiagram diagram, final EObject model, final SelectModelElementVariable var, final TreeItemWrapper input) {
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(model);
        if (var.getCandidatesExpression() != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            SelectionDescriptionHelper.computeInput(var, model, interpreter, input);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }
    }
}
