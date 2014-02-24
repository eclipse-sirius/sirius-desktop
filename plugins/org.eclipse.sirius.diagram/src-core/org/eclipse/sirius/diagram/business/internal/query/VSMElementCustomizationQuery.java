/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.query;

import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.BestStyleDescriptionKey;
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
     * Tells if the
     * {@link DescriptionPackage#getVSMElementCustomization_PredicateExpression()}
     * evaluation result.
     * 
     * @param styleDescription
     *            the {@link StyleDescription} on which Customization is applied
     * @param bestStyleDescriptionKey
     *            the {@link BestStyleDescriptionKey} identifying the best
     *            StyleDescription to customize
     * @param interpreter
     *            the {@link IInterpreter} used for the evaluation
     * @return the evaluation result
     */
    public boolean isEnabled(StyleDescription styleDescription, BestStyleDescriptionKey bestStyleDescriptionKey, IInterpreter interpreter) {
        boolean enabled = true;
        if (vsmElementCustomization.getPredicateExpression() != null && !StringUtil.isEmpty(vsmElementCustomization.getPredicateExpression().trim())) {
            interpreter.setVariable(IInterpreterSiriusVariables.VIEW, bestStyleDescriptionKey.getViewVariable());
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, bestStyleDescriptionKey.getContainerVariable());

            enabled = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(bestStyleDescriptionKey.getModelElement(), vsmElementCustomization,
                    DescriptionPackage.eINSTANCE.getVSMElementCustomization_PredicateExpression());

            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        }
        return enabled;
    }
}
