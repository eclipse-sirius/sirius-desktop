/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.quickfix;

import java.text.MessageFormat;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.part.ValidateAction;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * QuickFix resolution executing a {@link ValidationFix} description.
 * 
 * @author cbrun
 * 
 */
public class ValidationFixResolution implements IMarkerResolution {

    private ValidationFix fix;

    /**
     * Create a new {@link ValidationFixResolution} from a {@link ValidationFix} .
     * 
     * @param fix
     *            {@link ValidationFix} to execute.
     */
    public ValidationFixResolution(ValidationFix fix) {
        this.fix = fix;
    }

    @Override
    public String getLabel() {
        return fix.getName();
    }

    @Override
    public void run(IMarker marker) {
        IResource airdFile = marker.getResource();
        // Goto marker will only work if the marker reference a IResource, and
        // Sirius marks main aird files.
        if (airdFile instanceof IFile) {
            try {
                tryToOpenEditorAndApplyFix(airdFile, marker);
            } catch (PartInitException e) {
                SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ValidationFixResolution_editorOpeningError, airdFile), e);
            }
        }
    }

    private void tryToOpenEditorAndApplyFix(IResource airdFile, IMarker marker) throws PartInitException {
        // Open the editor and select the marked element.
        IEditorPart editor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), marker);

        // Apply the fix
        if (editor != null) {
            Session currentSession = null;
            IEditorInput editorInput = editor.getEditorInput();
            if (editorInput instanceof SessionEditorInput) {
                Session editorInputSession = ((SessionEditorInput) editorInput).getSession();
                if (editorInputSession != null && editorInputSession.isOpen()) {
                    // Open editor and goto marker were able to open and
                    // activate the expected editor.
                    currentSession = editorInputSession;
                } else {
                    URI markedResource = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
                    Session existingSession = SessionManager.INSTANCE.getExistingSession(markedResource);
                    if (existingSession == null || !existingSession.isOpen()) {
                        // Goto marker was not able to open the marker and to
                        // retrieve/init the session.
                        return;
                    } else {
                        currentSession = existingSession;

                    }
                }
            }

            View markedView = getMarkedView(currentSession, marker);
            if (markedView != null) {
                EObject fixTarget = getFixTarget(markedView);
                if (fixTarget != null) {
                    Diagram diagram = markedView.getDiagram();
                    executeFix(editor, (DDiagram) diagram.getElement(), fixTarget, currentSession.getTransactionalEditingDomain());
                    revalidate(editor, diagram);
                }
            }
        }
    }

    private View getMarkedView(Session session, IMarker marker) {
        String elementID = marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, null);
        String diagramDescriptorURI = marker.getAttribute(NavigationMarkerConstants.DIAGRAM_DESCRIPTOR_URI, null);

        if (diagramDescriptorURI == null || elementID == null) {
            return null;
        }

        ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
        if (set != null) {
            EObject markedDiagramDescriptor = set.getEObject(URI.createURI(diagramDescriptorURI), true);
            EObject markedDiagram = Optional.ofNullable(markedDiagramDescriptor).filter(DRepresentationDescriptor.class::isInstance).map(d -> ((DRepresentationDescriptor) d).getRepresentation())
                    .orElse(null);
            EObject markerTarget = markedDiagram instanceof DDiagram ? markedDiagram.eResource().getEObject(elementID) : null;
            if (markerTarget instanceof View) {
                return (View) markerTarget;
            }
        }
        return null;

    }

    private void revalidate(IEditorPart editor, View view) {
        if (org.eclipse.sirius.viewpoint.provider.Messages.SessionEditorInput_defaultEditorName.equals(editor.getTitle()) || !(editor instanceof DialectEditor)) {
            // Do not execute validation on the dummy editor opened temporarily.
            ValidateAction.runNonUIValidation(view);
        } else {
            ((DialectEditor) editor).validateRepresentation();
        }
    }

    private EObject getFixTarget(View markedView) {
        EObject fixTarget = markedView.getElement();
        if (fixTarget instanceof DSemanticDecorator && !isViewValidationRule()) {
            fixTarget = ((DSemanticDecorator) fixTarget).getTarget();
        }
        return fixTarget;
    }

    private boolean isViewValidationRule() {
        return (fix.eContainer() instanceof ViewValidationRule);
    }

    private void executeFix(IEditorPart editor, DDiagram diagram, EObject fixTarget, TransactionalEditingDomain domain) {
        IDiagramCommandFactory commandFactory = getDiagramCommandFactory(editor, domain);

        if (commandFactory != null && fixTarget != null) {
            Command fixCommand = commandFactory.buildQuickFixOperation(fix, fixTarget, diagram);

            // Set the RefreshEditorsListener in forceRefresh mode
            EObject semanticTarget = getSemanticTarget(fixTarget);
            Session session = SessionManager.INSTANCE.getSession(semanticTarget);
            if (session != null) {
                session.getRefreshEditorsListener().setForceRefresh(true);
            }

            // Execute the quick fix command
            domain.getCommandStack().execute(fixCommand);
        }
    }

    private EObject getSemanticTarget(EObject fixTarget) {
        // The fix target could be the DDiagramElement or the semantic element
        // (a rule could be a ViewValidationRule or a SemanticValidationRule
        EObject semanticTarget = fixTarget;
        if (semanticTarget instanceof DSemanticDecorator) {
            semanticTarget = ((DSemanticDecorator) semanticTarget).getTarget();
        }
        return semanticTarget;
    }

    private IDiagramCommandFactory getDiagramCommandFactory(IEditorPart editor, TransactionalEditingDomain domain) {
        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider diagramCmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        return diagramCmdFactoryProvider.getCommandFactory(domain);
    }

}
