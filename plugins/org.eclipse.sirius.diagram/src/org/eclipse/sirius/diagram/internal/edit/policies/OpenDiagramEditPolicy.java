/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.policies;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.part.Messages;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorUtil;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
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
        protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            try {
                Diagram diagram = getDiagramToOpen();
                if (diagram == null) {
                    diagram = intializeNewDiagram();
                }
                URI uri = EcoreUtil.getURI(diagram);
                EObject element = diagramFacet.getDiagramLink().getElement();
                if (element instanceof DDiagram) {
                    String editorName = ((DDiagram) element).getName();
                    IEditorInput editorInput = new URIEditorInput(uri, editorName);
                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    page.openEditor(editorInput, getEditorID());
                    return CommandResult.newOKCommandResult();
                }
                return CommandResult.newErrorCommandResult("Can't open diagram -- getElement() is not an instance of Sirius");

            } catch (Exception ex) {
                throw new ExecutionException("Can't open diagram", ex);
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
                throw new ExecutionException("Can't create diagram of '" + getDiagramKind() + "' kind");
            }
            diagramFacet.setDiagramLink(d);
            assert diagramFacet.eResource() != null;
            diagramFacet.eResource().getContents().add(d);
            try {
                new WorkspaceModifyOperation() {
                    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                        try {
                            for (Iterator<Resource> it = diagramFacet.eResource().getResourceSet().getResources().iterator(); it.hasNext();) {
                                Resource nextResource = it.next();
                                if (nextResource.isLoaded() && !getEditingDomain().isReadOnly(nextResource)) {
                                    nextResource.save(SiriusDiagramEditorUtil.getSaveOptions());
                                }
                            }
                        } catch (IOException ex) {
                            throw new InvocationTargetException(ex, "Save operation failed");
                        }
                    }
                }.run(null);
            } catch (InvocationTargetException e) {
                throw new ExecutionException("Can't create diagram of '" + getDiagramKind() + "' kind", e);
            } catch (InterruptedException e) {
                throw new ExecutionException("Can't create diagram of '" + getDiagramKind() + "' kind", e);
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
            return SiriusDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
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
