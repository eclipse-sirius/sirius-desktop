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
package org.eclipse.sirius.tools.api.command;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;

/**
 * .
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public abstract class AbstractCommandFactory implements ICommandFactory {

    /** The editing domain. */
    protected final TransactionalEditingDomain domain;

    /** The model accessor (used for metamodel extensions). */
    protected ModelAccessor modelAccessor;

    /**
     * This callback is used to exchange with the user interface when needed.
     */
    protected UICallBack uiCallBack;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain
     */
    public AbstractCommandFactory(final TransactionalEditingDomain domain) {
        this.domain = domain;
        this.modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(domain.getResourceSet());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ICommandFactory#buildCreateRepresentationFromDescription(org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription,
     *      org.eclipse.sirius.viewpoint.DRepresentationElement, java.lang.String)
     */
    @Override
    public CreateRepresentationCommand buildCreateRepresentationFromDescription(final RepresentationCreationDescription desc, final DRepresentationElement element, final String newDiagramName) {
        final Session session = SessionManager.INSTANCE.getSession(element.getTarget());
        final CreateRepresentationCommand cmd = new CreateRepresentationCommand(session, desc.getRepresentationDescription(), element.getTarget(), newDiagramName, new NullProgressMonitor());
        cmd.setInitialOperation(desc);
        return cmd;
    }

    /**
     * Defines the UI call back to use.
     * 
     * @param newCB
     *            the new user interface call back.
     */
    @Override
    public void setUserInterfaceCallBack(final UICallBack newCB) {
        this.uiCallBack = newCB;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ICommandFactory#getUserInterfaceCallBack()
     */
    @Override
    public UICallBack getUserInterfaceCallBack() {
        return this.uiCallBack;
    }

    /**
     * Append the refresh representation task if necessary. The refresh task is appended if the
     * <code>toolDescription.isForceRefresh()</code> is <code>true</code> or if {@link #autoRefreshView} is
     * <code>true</code>.
     * 
     * @param semanticDecorator
     *            the semantic decorator whose parent representation should be refreshed.
     * @param result
     *            the command.
     * @param toolDescription
     *            the tool.
     * @param monitor
     *            The progress monitor
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription#isForceRefresh()
     */
    protected void addRefreshTask(final DSemanticDecorator semanticDecorator, final DCommand result, final AbstractToolDescription toolDescription, final IProgressMonitor monitor) {
        if (semanticDecorator != null) {
            final EObject semanticElement = semanticDecorator.getTarget();
            final Session session = SessionManager.INSTANCE.getSession(semanticElement);
            final DRepresentation representationToRefresh;

            if (semanticDecorator instanceof DRepresentation) {
                representationToRefresh = (DRepresentation) semanticDecorator;
            } else if (semanticDecorator instanceof DRepresentationElement) {
                representationToRefresh = new DRepresentationElementQuery((DRepresentationElement) semanticDecorator).getParentRepresentation();
            } else {
                representationToRefresh = null;
            }

            if (semanticElement != null && session != null && representationToRefresh != null) {
                result.getTasks().add(new AbstractCommandTask() {
                    @Override
                    public String getLabel() {
                        return Messages.AbstractCommandFactory_refreshTasklabel;
                    }

                    @Override
                    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
                        if (toolDescription == null || toolDescription.isForceRefresh()) {
                            // Set the RefreshEditorsListener in forceRefresh
                            // mode
                            session.getRefreshEditorsListener().setForceRefresh(true);
                        }
                        // Add the current representation to be refreshed :
                        // - It could be possible that no editor is opened on it
                        // - The tool should probably made a modification only
                        // in the aird model (and this not launches a refresh)
                        if (toolDescription == null) {
                            for (DRepresentation dRepresentation : RefreshFilterManager.INSTANCE.getOpenedRepresantationsToRefresh()) {
                                session.getRefreshEditorsListener().addRepresentationToForceRefresh(dRepresentation);
                            }
                        } else {
                            session.getRefreshEditorsListener().addRepresentationToForceRefresh(representationToRefresh);
                        }
                    }
                });
            }
        }
    }

    /**
     * Append the refresh representation task if necessary. The refresh task is appended if the
     * <code>toolDescription.isForceRefresh()</code> is <code>true</code> or if {@link #autoRefreshView} is
     * <code>true</code>.
     * 
     * @param semanticDecorator
     *            the semantic decorator whose parent representation should be refreshed.
     * @param result
     *            the command.
     * @param toolDescription
     *            the tool.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription#isForceRefresh()
     */
    protected void addRefreshTask(final DSemanticDecorator semanticDecorator, final DCommand result, final AbstractToolDescription toolDescription) {
        addRefreshTask(semanticDecorator, result, toolDescription, new NullProgressMonitor());
    }
}
