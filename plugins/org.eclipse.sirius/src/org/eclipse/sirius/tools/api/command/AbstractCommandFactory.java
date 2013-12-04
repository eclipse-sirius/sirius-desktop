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
package org.eclipse.sirius.tools.api.command;

import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.RemoveDanglingReferencesTask;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;

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

    /** This callback is used to exchange with the user interface when needed. */
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
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain
     * @param accessor
     *            the model accessor
     * 
     * @deprecated since 4.0.0 : because
     *             {@link org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessorsRegistry}
     *             allows to retrieve a ModelAccessor from its
     *             TransactionalEditingDomain, then use
     *             AbstractCommandFactory(final TransactionalEditingDomain)
     *             constructor instead.
     */
    @Deprecated
    public AbstractCommandFactory(final TransactionalEditingDomain domain, final ModelAccessor accessor) {
        this.domain = domain;
        this.modelAccessor = accessor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ICommandFactory#buildCreateRepresentationFromDescription(org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription,
     *      org.eclipse.sirius.viewpoint.DRepresentationElement, java.lang.String)
     */
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
    public void setUserInterfaceCallBack(final UICallBack newCB) {
        this.uiCallBack = newCB;
    }

    /**
     * Append the refresh representation task if necessary. The refresh task is
     * appended if the <code>toolDescription.isForceRefresh()</code> is
     * <code>true</code> or if {@link #autoRefreshView} is <code>true</code>.
     * 
     * @param semanticDecorator
     *            the semantic decorator whose parent representation should be
     *            refreshed.
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
                    public String getLabel() {
                        return "Set RefreshEditorsPrecommitListener options";
                    }

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
                        session.getRefreshEditorsListener().addRepresentationToForceRefresh(representationToRefresh);
                    }
                });
            }
        }
    }

    /**
     * Append the refresh representation task if necessary. The refresh task is
     * appended if the <code>toolDescription.isForceRefresh()</code> is
     * <code>true</code> or if {@link #autoRefreshView} is <code>true</code>.
     * 
     * @param semanticDecorator
     *            the semantic decorator whose parent representation should be
     *            refreshed.
     * @param result
     *            the command.
     * @param toolDescription
     *            the tool.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription#isForceRefresh()
     */
    protected void addRefreshTask(final DSemanticDecorator semanticDecorator, final DCommand result, final AbstractToolDescription toolDescription) {
        addRefreshTask(semanticDecorator, result, toolDescription, new NullProgressMonitor());
    }

    /**
     * Adds the remove dangling reference task if necessary.
     * 
     * @param result
     *            the command.
     * @param tool
     *            the tool.
     * @param any
     *            any semantic decorator.
     */
    @Deprecated
    protected void addRemoveDanglingReferencesTask(final DCommand result, final EObject tool, final DSemanticDecorator any) {
        boolean containsRemove = false;
        final Iterator<EObject> iterContent = tool.eAllContents();
        while (!containsRemove && iterContent.hasNext()) {
            final EObject next = iterContent.next();
            if (ToolPackage.eINSTANCE.getRemoveElement().isInstance(next)) {
                containsRemove = true;
            }
        }
        if (containsRemove) {
            result.getTasks().add(new RemoveDanglingReferencesTask(any));
            result.getTasks().add(new RemoveDanglingReferencesTask(any.getTarget()));
        }
    }

}
