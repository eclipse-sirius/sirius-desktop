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
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Query for {@link VSMElementCustomization}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class VSMElementCustomizationQuery {

    private VSMElementCustomization vsmElementCustomization;

    /**
     * Default constructor.
     * 
     * @param vsmElementCustomization
     *            the {@link VSMElementCustomization} to query
     */
    public VSMElementCustomizationQuery(VSMElementCustomization vsmElementCustomization) {
        this.vsmElementCustomization = vsmElementCustomization;
    }

    /**
     * Tells if the {@link DescriptionPackage#getVSMElementCustomization_PredicateExpression()} evaluation result.
     * 
     * @param styleDescription
     *            the {@link StyleDescription} on which Customization is applied
     * @param bestStyleDescriptionKey
     *            the {@link BestStyleDescriptionKey} identifying the best StyleDescription to customize
     * @param interpreter
     *            the {@link IInterpreter} used for the evaluation
     * @return the evaluation result
     */
    public boolean isEnabled(StyleDescription styleDescription, BestStyleDescriptionKey bestStyleDescriptionKey, IInterpreter interpreter) {
        boolean enabled = true;
        if (vsmElementCustomization.getPredicateExpression() != null && !StringUtil.isEmpty(vsmElementCustomization.getPredicateExpression().trim())) {
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
                enabled = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(bestStyleDescriptionKey.getModelElement(), vsmElementCustomization,
                        DescriptionPackage.eINSTANCE.getVSMElementCustomization_PredicateExpression());
            } finally {
                variablesToUnset.forEach(interpreter::unSetVariable);
            }
        }
        return enabled;
    }
}
