/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.BestStyleDescriptionKey;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;

/**
 * A query for {@link EAttributeCustomization}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EAttributeCustomizationQuery extends org.eclipse.sirius.business.internal.query.model.EAttributeCustomizationQuery {

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
            interpreter.setVariable(IInterpreterSiriusVariables.VIEW, bestStyleDescriptionKey.getViewVariable());
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, bestStyleDescriptionKey.getContainerVariable());

            newAttributeValue = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluate(bestStyleDescriptionKey.getModelElement(), eAttributeCustomization,
                    DescriptionPackage.eINSTANCE.getEAttributeCustomization_Value());

            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        }
        return newAttributeValue;
    }
}
