/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.api.command.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * This class create a diagram with an initial operation.
 * 
 * @author fmorel
 */
public class CreateDiagramWithInitialOperation extends AbstractCommandTask {

    /** The ui call back. */
    private UICallBack uiCallBack;

    private DiagramDescription description;

    /** the semantic element to initialize diagram without MappingBased. */
    private EObject semanticElement;

    private IProgressMonitor monitor;

    /**
     * Creates the command.
     * 
     * @param desc
     *            the diagram description.
     * @param semanticElement
     *            semantic element on which the user requested the creation of
     *            the {@link org.eclipse.sirius.diagram.DDiagram}.
     * @param uiCallBack
     *            the ui callback used to ask for the detail name.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of
     *            representation initialization
     */
    public CreateDiagramWithInitialOperation(final DiagramDescription desc, final EObject semanticElement, final UICallBack uiCallBack, IProgressMonitor monitor) {
        this.semanticElement = semanticElement;
        this.description = desc;
        this.uiCallBack = uiCallBack;
        this.monitor = monitor;
    }

    /**
     * @param semantic
     *            The semantic root of the diagram
     * @return The new diagram name
     * @throws InterruptedException
     *             If the user pressed cancel
     */
    private String askDiagramName(final EObject semantic) throws InterruptedException {
        final IInterpreter interpreter = InterpreterUtil.getInterpreter(semantic);
        String theName = new IdentifiedElementQuery(this.description).getLabel();
        if (!StringUtil.isEmpty(this.description.getTitleExpression())) {
            try {
                theName = interpreter.evaluateString(semantic, this.description.getTitleExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(this.description, DescriptionPackage.eINSTANCE.getRepresentationDescription_TitleExpression(), e);
            }
        }
        if (uiCallBack != null) {
            theName = uiCallBack.askForDetailName(theName, theName, this.description.getEndUserDocumentation());
        }
        return theName;
    }

    @Override
    public void execute() {
        if (semanticElement != null) {
            try {
                if (monitor == null) {
                    monitor = new NullProgressMonitor();
                }
                String name = askDiagramName(semanticElement);
                DialectManager.INSTANCE.createRepresentation(name, semanticElement, description, SessionManager.INSTANCE.getSession(semanticElement), monitor);
            } catch (final InterruptedException e) {
                // the user pressed "cancel", let's do nothing
            }
        }
    }

    @Override
    public String getLabel() {
        return null;
    }
}
