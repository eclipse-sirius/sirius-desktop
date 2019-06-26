/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.dialect.command;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Create representation command.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class CreateRepresentationCommand extends RecordingCommand {

    private RepresentationDescription description;

    private Session session;

    private EObject semantic;

    private String name;

    private DRepresentation representation;

    private boolean isInitialOperation;

    private RepresentationCreationDescription creationDescription;

    private IProgressMonitor monitor;

    /**
     * Construct a new instance with {@link IProgressMonitor}.
     * 
     * @param session
     *            the session
     * @param description
     *            the representation description
     * @param eObject
     *            the semantic element on which to create the representation
     * @param name
     *            the representation name
     * @param monitor
     *            a {@link IProgressMonitor} to report progression of
     *            representation creation
     */
    public CreateRepresentationCommand(Session session, RepresentationDescription description, EObject eObject, String name, IProgressMonitor monitor) {
        super(session.getTransactionalEditingDomain(), Messages.CreateRepresentationCommand_label);
        this.session = session;
        this.description = description;
        this.semantic = eObject;
        this.name = name;
        this.monitor = monitor;
    }

    /**
     * Set if there is an initial operation or not.
     * 
     * @param creationDesc
     *            the creation tool
     */
    public void setInitialOperation(final RepresentationCreationDescription creationDesc) {
        this.creationDescription = creationDesc;
        isInitialOperation = creationDesc.getInitialOperation() != null && creationDesc.getInitialOperation().getFirstModelOperations() != null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
     */
    @Override
    public boolean canExecute() {
        /*
         * if there is an initial operation an semantic element could be added
         * and used after on precondition
         */
        if (isInitialOperation) {
            return super.canExecute();
        }
        final EObject root = CreateRepresentationCommand.computeRepresentationRoot(session.getInterpreter(), creationDescription, semantic);
        return DialectManager.INSTANCE.canCreate(root, description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        final EObject root = CreateRepresentationCommand.computeRepresentationRoot(session.getInterpreter(), creationDescription, semantic);
        representation = DialectManager.INSTANCE.createRepresentation(this.name, root, this.description, this.session, monitor);
        if (representation != null) {
            new DRepresentationQuery(representation).getRepresentationDescriptor().setName(name);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
     */
    @Override
    public Collection<?> getResult() {
        final Set<DRepresentation> result = new HashSet<>();
        result.add(representation);
        /* do not leak */
        clearData();
        return result;
    }

    /**
     * Get the created representation.
     * 
     * @return the created representation
     */
    public DRepresentation getCreatedRepresentation() {
        return (DRepresentation) getResult().iterator().next();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        clearData();

    }

    private void clearData() {
        this.representation = null;
        this.session = null;
        this.description = null;
        this.semantic = null;
        this.name = null;
        this.creationDescription = null;
        this.label = null;
        this.monitor = null;
    }

    private static EObject computeRepresentationRoot(final IInterpreter interpreter, final RepresentationCreationDescription desc, final EObject initialRoot) {
        EObject newRepresentationRoot = initialRoot;
        if (desc != null && !StringUtil.isEmpty(desc.getBrowseExpression())) {
            try {
                newRepresentationRoot = interpreter.evaluateEObject(newRepresentationRoot, desc.getBrowseExpression());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.warning(newRepresentationRoot, ToolPackage.eINSTANCE.getRepresentationCreationDescription_BrowseExpression(), e);
            }
            if (newRepresentationRoot == null) {
                RuntimeLoggerManager.INSTANCE.warning(initialRoot, ToolPackage.eINSTANCE.getRepresentationCreationDescription_BrowseExpression(),
                        MessageFormat.format(Messages.CreateRepresentationCommand_nullExpresionWarningMsg, desc.getBrowseExpression()));
            }
        }
        return newRepresentationRoot;
    }

}
