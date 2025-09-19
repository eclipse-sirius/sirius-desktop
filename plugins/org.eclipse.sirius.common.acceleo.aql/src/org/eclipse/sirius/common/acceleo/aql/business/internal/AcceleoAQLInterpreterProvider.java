/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.common.acceleo.aql.business.internal;

import org.eclipse.sirius.common.acceleo.aql.business.api.AQLConstants;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;

/**
 * This will be used in order to provides interpreters for Acceleo Query
 * Language expressions to the compound interpreter.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class AcceleoAQLInterpreterProvider implements IInterpreterProvider {
    /**
     * This represents the prefix of an Acceleo 3 expression.
     * 
     * @see IAcceleoConstants#DEFAULT_BEGIN
     */
    private static final String ACCELEO_EXPRESSION_PREFIX = "["; //$NON-NLS-1$

    /**
     * This represents the suffix of an Acceleo 3 expression.
     * 
     * @see IAcceleoConstants#INVOCATION_END
     */
    private static final String ACCELEO_EXPRESSION_SUFFIX = "/]"; //$NON-NLS-1$
    
    @Override
    public IInterpreter createInterpreter() {
        return new AQLSiriusInterpreter();
    }

    @Override
    public boolean provides(String expression) {
        return expression != null && (expression.startsWith(AQLConstants.AQL_PREFIX) || (expression.startsWith(ACCELEO_EXPRESSION_PREFIX) && expression.endsWith(ACCELEO_EXPRESSION_SUFFIX)));
    }
}
