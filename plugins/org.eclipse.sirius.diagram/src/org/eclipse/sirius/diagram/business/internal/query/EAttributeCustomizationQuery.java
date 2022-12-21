/*******************************************************************************
 * Copyright (c) 2012, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.query;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.BestStyleDescriptionKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;

/**
 * A query for {@link EAttributeCustomization}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EAttributeCustomizationQuery extends org.eclipse.sirius.model.business.internal.query.EAttributeCustomizationQuery {

    /**
     * Default constructor.
     * 
     * @param eAttributeCustomization
     *            the {@link EAttributeCustomization} to query
     */
    public EAttributeCustomizationQuery(EAttributeCustomization eAttributeCustomization) {
        super(eAttributeCustomization);
    }

    /**
     * Get the new value computed for the current {@link EAttributeCustomization}.
     * 
     * @param bestStyleDescriptionKey
     *            the {@link BestStyleDescriptionKey} identifying the best StyleDescription to customize
     * @param interpreter
     *            the interpreter used to get the new value
     * @return the new value
     */
    public Object getNewAttributeValue(BestStyleDescriptionKey bestStyleDescriptionKey, IInterpreter interpreter) {
        Object newAttributeValue = null;
        if (eAttributeCustomization.getValue() != null && !StringUtil.isEmpty(eAttributeCustomization.getValue().trim())) {

            Set<String> variablesToUnset = new LinkedHashSet<>();
            EObject viewVariable = bestStyleDescriptionKey.getViewVariable();
            if (viewVariable instanceof DDiagramElement) {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, bestStyleDescriptionKey.getDDiagram());
                variablesToUnset.add(IInterpreterSiriusVariables.DIAGRAM);
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, viewVariable);
                variablesToUnset.add(IInterpreterSiriusVariables.VIEW);
                if (viewVariable instanceof DEdge) {
                    EObject sourceNode = ((DEdge) viewVariable).getSourceNode();
                    EObject targetNode = ((DEdge) viewVariable).getTargetNode();
                    interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW, sourceNode);
                    variablesToUnset.add(IInterpreterSiriusVariables.SOURCE_VIEW);
                    interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW, targetNode);
                    variablesToUnset.add(IInterpreterSiriusVariables.TARGET_VIEW);
                }
                EObject eContainer = viewVariable.eContainer();
                if (eContainer != null) {
                    interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, eContainer);
                    variablesToUnset.add(IInterpreterSiriusVariables.CONTAINER_VIEW);
                    if (eContainer instanceof DSemanticDecorator) {
                        EObject semanticContainer = ((DSemanticDecorator) eContainer).getTarget();
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, semanticContainer);
                        variablesToUnset.add(IInterpreterSiriusVariables.CONTAINER);
                    }
                }
            }

            try {
                newAttributeValue = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluate(bestStyleDescriptionKey.getModelElement(), eAttributeCustomization,
                        DescriptionPackage.eINSTANCE.getEAttributeCustomization_Value());
            } finally {
                variablesToUnset.forEach(interpreter::unSetVariable);
            }
        }
        return newAttributeValue;
    }
}
