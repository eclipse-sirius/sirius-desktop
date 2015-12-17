/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.SelectionDescriptionHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;
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
     * @return a map with the name/variable couples.
     * @throws InterruptedException
     *             if the user cancels the wizard
     */
    public static Map<String, EObject> askForFilterValues(final DSemanticDiagram diagram, final VariableFilter filter) throws InterruptedException {
        final EObject model = diagram.getTarget();
        final UICallBack uiCallback = SiriusEditPlugin.getPlugin().getUiCallback();
        /*
         * First let's clear the Values history corresponding to this filter in
         * the viewpoint
         */
        if (diagram.getFilterVariableHistory() != null) {
            final Iterator<VariableValue> it = diagram.getFilterVariableHistory().getOwnedValues().iterator();
            while (it.hasNext()) {
                final VariableValue value = it.next();
                if (value instanceof EObjectVariableValue) {
                    EObjectVariableValue objVariableValue = (EObjectVariableValue) value;
                    if (objVariableValue.getVariableDefinition() == null || objVariableValue.getModelElement() == null
                            || filter.getOwnedVariables().contains(objVariableValue.getVariableDefinition())) {
                        it.remove();
                    }
                }
            }
        }
        /*
         * Now lets add the new ones.
         */
        final Iterator<InteractiveVariableDescription> itVar = filter.getOwnedVariables().iterator();
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
            }
        }
        /*
         * Now changing the model structure in a Map
         */
        final Map<String, EObject> variables = new HashMap<String, EObject>();
        if (diagram.getFilterVariableHistory() != null) {
            final Iterator<VariableValue> it = diagram.getFilterVariableHistory().getOwnedValues().iterator();
            while (it.hasNext()) {
                final VariableValue value = it.next();
                if (value instanceof EObjectVariableValue) {
                    variables.put(((AbstractVariable) ((EObjectVariableValue) value).getVariableDefinition()).getName(), ((EObjectVariableValue) value).getModelElement());
                }
            }
        }
        return variables;
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
