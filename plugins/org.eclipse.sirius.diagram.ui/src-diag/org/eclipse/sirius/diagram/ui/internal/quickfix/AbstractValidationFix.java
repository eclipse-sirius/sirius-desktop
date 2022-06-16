/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES and others.
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.part.ValidateAction;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * QuickFix resolution abstract class.
 * 
 * @author lfasani
 */
public abstract class AbstractValidationFix implements IMarkerResolution {

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
        IFile markerFile = (IFile) marker.getResource();
        IEditorPart editor = null;
        try {
            String markerEditorID = (String) marker.getAttribute(IDE.EDITOR_ID_ATTR);

            if (markerFile != null && markerEditorID != null) {
                // try to retrieve the open editor with the right editorID and the right file
                List<IEditorPart> collect = Arrays.asList(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()).stream()
                        .map(editorReference -> editorReference.getEditor(true)).collect(Collectors.toList());
                for (IEditorPart iEditorPart : collect) {
                    String editorId = iEditorPart.getEditorSite().getId();
                    if (markerEditorID.equals(editorId)) {
                        IEditorInput editorInput = iEditorPart.getEditorInput();
                        if (editorInput instanceof SessionEditorInput) {
                            String fileString = ((SessionEditorInput) editorInput).getSession().getSessionResource().getURI().toPlatformString(true);
                            IResource editorIFile = ResourcesPlugin.getWorkspace().getRoot().findMember(fileString);
                            if (markerFile.equals(editorIFile)) {
                                editor = iEditorPart;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (CoreException e) {
        }
        // marker.
        if (editor == null) {
            editor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), marker);
        } else {
            IDE.gotoMarker(editor, marker);
        }

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
                doExecuteFix(marker, editor, markedView, currentSession);
            }
        }
    }

    protected abstract void doExecuteFix(IMarker marker, IEditorPart editor, View markedView, Session session);

    protected View getMarkedView(Session session, IMarker marker) {
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

    protected void revalidate(IEditorPart editor, View view) {
        if (org.eclipse.sirius.viewpoint.provider.Messages.SessionEditorInput_defaultEditorName.equals(editor.getTitle()) || !(editor instanceof DialectEditor)) {
            // Do not execute validation on the dummy editor opened temporarily.
            ValidateAction.runNonUIValidation(view);
        } else {
            ((DialectEditor) editor).validateRepresentation();
        }
    }
}
