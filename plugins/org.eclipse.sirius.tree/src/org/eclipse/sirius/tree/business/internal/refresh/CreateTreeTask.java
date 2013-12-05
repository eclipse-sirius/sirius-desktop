/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.refresh;

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
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * This class create a tree.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class CreateTreeTask extends AbstractCommandTask {

    private TreeDescription description;

    /** the semantic element to initialize table without MappingBased. */
    private EObject semanticElement;

    private IProgressMonitor monitor;

    /**
     * Creates the command.
     * 
     * @param desc
     *            the tree description.
     * @param semanticElement
     *            semantic element on which the user requested the creation of
     *            the {@link org.eclipse.sirius.tree.metamodel.tree.DTree}
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of
     *            {@link org.eclipse.sirius.tree.metamodel.tree.DTree} creation
     */
    public CreateTreeTask(final TreeDescription desc, final EObject semanticElement, IProgressMonitor monitor) {
        this.semanticElement = semanticElement;
        this.description = desc;
        this.monitor = monitor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        if (semanticElement != null) {
            IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticElement);
            String name = new IdentifiedElementQuery(description).getLabel();
            if (!StringUtil.isEmpty(description.getTitleExpression())) {
                try {
                    name = interpreter.evaluateString(semanticElement, description.getTitleExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(description, DescriptionPackage.eINSTANCE.getRepresentationDescription_TitleExpression(), e);
                }
            }
            if (monitor == null) {
                monitor = new NullProgressMonitor();
            }
            DialectManager.INSTANCE.createRepresentation(name, semanticElement, description, SessionManager.INSTANCE.getSession(semanticElement), monitor);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return null;
    }
}
