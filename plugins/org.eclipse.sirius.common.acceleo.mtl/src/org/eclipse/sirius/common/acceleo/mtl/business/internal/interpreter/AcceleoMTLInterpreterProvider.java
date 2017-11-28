/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;

/**
 * This will be used in order to provides interpreters for Acceleo 3 expressions
 * to the compound interpreter.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class AcceleoMTLInterpreterProvider implements IInterpreterProvider {
    /**
     * This represents the prefix of an Acceleo 3 expression.
     * 
     * @see IAcceleoConstants#DEFAULT_BEGIN
     */
    private static final String ACCELEO_EXPRESSION_PREFIX = IAcceleoConstants.DEFAULT_BEGIN;

    /**
     * This represents the suffix of an Acceleo 3 expression.
     * 
     * @see IAcceleoConstants#INVOCATION_END
     */
    private static final String ACCELEO_EXPRESSION_SUFFIX = IAcceleoConstants.DEFAULT_END_BODY_CHAR + IAcceleoConstants.DEFAULT_END;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider#createInterpreter()
     */
    public IInterpreter createInterpreter() {
        return new AcceleoMTLInterpreter();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider#provides(java.lang.String)
     */
    public boolean provides(String expression) {
        if (expression != null) {
            return expression.startsWith(ACCELEO_EXPRESSION_PREFIX) && expression.endsWith(ACCELEO_EXPRESSION_SUFFIX);
        }
        return false;
    }
}
