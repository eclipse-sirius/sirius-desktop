/*******************************************************************************
 * Copyright (c) 2015, 2025 Obeo.
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
package org.eclipse.sirius.business.api.dialect.description;

import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContextUtils;
import org.eclipse.sirius.common.tools.api.interpreter.TypedValidation;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;

/**
 * A common entry point to get validation for all the {@link IInterpreter}. The
 * actual {@link IInterpreter} implementation which is going to be call depends
 * on the given expression. This class guarantees that when the context changes,
 * the IInterpreter's configuration regarding available metamodels or imported
 * classes will be adapted.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 *
 */
public final class MultiLanguagesValidator {

    private IInterpreter currentInterpreter;

    private IInterpreterContext currentContext;

    /**
     * Analyze the expression and provide a validation result. This method will
     * dynamically re-configure the required interpreter based on the given
     * context.
     * 
     * @param context
     *            the {@link IInterpreterContext} to use for validating this
     *            expression
     * @param expression
     *            the expression to analyze
     * @return the validation result.
     */
    public ValidationResult validateExpression(IInterpreterContext context, String expression) {
        IInterpreter interpreter = provide(context, expression);
        ValidationResult result = new ValidationResult();
        if (interpreter instanceof TypedValidation) {
            result = ((TypedValidation) interpreter).analyzeExpression(context, expression);
        } else if (interpreter != null && interpreter.supportsValidation()) {
            result.addAllStatus(interpreter.validateExpression(context, expression));
        }
        return result;
    }

    private IInterpreter provide(IInterpreterContext context, String expression) {
        if (currentContext == null) {
            createNewInterpreter(context, expression);
        } else {
            if (!IInterpreterContextUtils.haveSameScopeDefinition(context, currentContext)) {
                if (currentInterpreter != null) {
                    currentInterpreter.dispose();
                }
                createNewInterpreter(context, expression);
            }
        }

        return currentInterpreter;
    }

    void createNewInterpreter(IInterpreterContext context, String expression) {
        currentContext = context;
        currentInterpreter = CompoundInterpreter.createGenericInterpreter();
        IInterpreterContextUtils.configureInterpreter(currentInterpreter, context);
    }

    /**
     * Clear any internal state which would have been kept.
     */
    public void dispose() {
        if (this.currentInterpreter != null) {
            this.currentInterpreter.dispose();
            this.currentInterpreter = null;
        }
        if (this.currentContext != null) {
            this.currentContext = null;
        }
    }

    /**
     * Returns the shared instance.
     * 
     * @return the global viewpoints registry.
     */
    public static MultiLanguagesValidator getInstance() {
        return MultiLanguagesValidatorHolder.instance;
    }

    private static final class MultiLanguagesValidatorHolder {
        private static MultiLanguagesValidator instance;
        static {
            instance = new MultiLanguagesValidator();
        }
    }

}
