/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.part.Messages;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditorUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @was-generated
 */
public class OpenDiagramEditPolicy extends OpenEditPolicy {

    /**
     * @was-generated
     */
    @Override
    protected Command getOpenCommand(Request request) {
        EditPart targetEditPart = getTargetEditPart(request);
        if (false == targetEditPart.getModel() instanceof View) {
            return null;
        }
        View view = (View) targetEditPart.getModel();
        Style link = view.getStyle(NotationPackage.eINSTANCE.getHintedDiagramLinkStyle());
        if (false == link instanceof HintedDiagramLinkStyle) {
            return null;
        }
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(view);
        return new ICommandProxy(new OpenDiagramCommand(transactionalEditingDomain, (HintedDiagramLinkStyle) link));
    }

    /**
     * @was-generated
     */
    private static class OpenDiagramCommand extends AbstractTransactionalCommand {

        /**
         * @was-generated
         */
        private final HintedDiagramLinkStyle diagramFacet;

        /**
         * @param transactionalEditingDomain
         * @was-generated
         */
        OpenDiagramCommand(TransactionalEditingDomain transactionalEditingDomain, HintedDiagramLinkStyle linkStyle) {
            // editing domain is taken for original diagram,
            // if we open diagram from another file, we should use another
            // editing domain
            super(transactionalEditingDomain, Messages.CommandName_OpenDiagram, null);
            diagramFacet = linkStyle;
        }

        /**
         * @not-generated
         */
        @Override
        protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            try {
                Diagram diagram = getDiagramToOpen();
                if (diagram == null) {
                    diagram = intializeNewDiagram();
                }
                URI uri = EcoreUtil.getURI(diagram);
                EObject element = diagramFacet.getDiagramLink().getElement();
                if (element instanceof DDiagram) {
                    String editorName = new DRepresentationQuery(((DDiagram) element)).getRepresentationDescriptor().getName();
                    IEditorInput editorInput = new URIEditorInput(uri, editorName);
                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    page.openEditor(editorInput, getEditorID());
                    return CommandResult.newOKCommandResult();
                }
                return CommandResult.newErrorCommandResult(org.eclipse.sirius.diagram.ui.provider.Messages.OpenDiagramCommand_notDiagramElementErrorMsg);

            } catch (Exception ex) {
                throw new ExecutionException(org.eclipse.sirius.diagram.ui.provider.Messages.OpenDiagramCommand_exceptionMsg, ex);
            }
        }

        /**
         * @was-generated
         */
        protected Diagram getDiagramToOpen() {
            return diagramFacet.getDiagramLink();
        }

        /**
         * @was-generated
         */
        protected Diagram intializeNewDiagram() throws ExecutionException {
            Diagram d = ViewService.createDiagram(getDiagramDomainElement(), getDiagramKind(), getPreferencesHint());
            if (d == null) {
                throw new ExecutionException(MessageFormat.format(org.eclipse.sirius.diagram.ui.provider.Messages.OpenDiagramCommand_creationErrorMsg, getDiagramKind()));
            }
            diagramFacet.setDiagramLink(d);
            final Resource diagramFacetResource = diagramFacet.eResource();
            assert diagramFacetResource != null;
            diagramFacetResource.getContents().add(d);
            try {
                new WorkspaceModifyOperation() {
                    @Override
                    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                        try {
                            for (Iterator<Resource> it = diagramFacetResource.getResourceSet().getResources().iterator(); it.hasNext();) {
                                Resource nextResource = it.next();
                                if (nextResource.isLoaded() && !getEditingDomain().isReadOnly(nextResource)) {
                                    nextResource.save(SiriusDiagramEditorUtil.getSaveOptions());
                                }
                            }
                        } catch (IOException ex) {
                            throw new InvocationTargetException(ex, org.eclipse.sirius.diagram.ui.provider.Messages.OpenDiagramCommand_saveFailedMsg);
                        }
                    }
                }.run(null);
            } catch (InvocationTargetException e) {
                throw new ExecutionException(MessageFormat.format(org.eclipse.sirius.diagram.ui.provider.Messages.OpenDiagramCommand_creationErrorMsg, getDiagramKind()), e);
            } catch (InterruptedException e) {
                throw new ExecutionException(MessageFormat.format(org.eclipse.sirius.diagram.ui.provider.Messages.OpenDiagramCommand_creationErrorMsg, getDiagramKind()), e);
            }
            return d;
        }

        /**
         * @was-generated
         */
        protected EObject getDiagramDomainElement() {
            // use same element as associated with EP
            return ((View) diagramFacet.eContainer()).getElement();
        }

        /**
         * @was-generated
         */
        protected PreferencesHint getPreferencesHint() {
            // XXX prefhint from target diagram's editor?
            return DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT;
        }

        /**
         * @was-generated
         */
        protected String getDiagramKind() {
            return DDiagramEditPart.MODEL_ID;
        }

        /**
         * @was-generated
         */
        protected String getEditorID() {
            return DDiagramEditor.EDITOR_ID;
        }
    }

}
