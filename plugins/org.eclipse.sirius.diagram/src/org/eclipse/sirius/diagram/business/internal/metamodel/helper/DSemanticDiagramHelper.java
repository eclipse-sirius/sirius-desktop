/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Utility class to factor customizations for DSemanticDiagram and related.
 * 
 * @author pcdavid
 */
public final class DSemanticDiagramHelper {
    private DSemanticDiagramHelper() {
        // Prevent instantiation.
    }

    /**
     * Finds the element which should be considered as the root of the diagram's content. It may differ from the
     * diagram's target element if a <em>root expression</em> has been defined.
     * 
     * @param self
     *            the diagram.
     * @return the element which should be considered as the root of the diagram's content.
     */
    public static EObject getRootContent(DSemanticDiagram self) {
        EObject rootContent = self.getTarget();
        if (self.getDescription() != null && self.getDescription().getRootExpression() != null && !StringUtil.isEmpty(self.getDescription().getRootExpression().trim())) {
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(self.getTarget());
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, self);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, self);

            try {
                rootContent = interpreter.evaluateEObject(self.getTarget(), self.getDescription().getRootExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(self.getDescription(), DescriptionPackage.eINSTANCE.getDiagramDescription_RootExpression(), e);
            }

            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }
        return rootContent;
    }
}
