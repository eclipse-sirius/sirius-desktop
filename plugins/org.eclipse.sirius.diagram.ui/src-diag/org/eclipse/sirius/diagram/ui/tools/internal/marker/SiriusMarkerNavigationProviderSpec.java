/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.marker;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusMarkerNavigationProvider;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditorUtil;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * Specification of SiriusMarkerNavigationProvider.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusMarkerNavigationProviderSpec extends SiriusMarkerNavigationProvider {

    @Override
    protected void doGotoMarker(final IMarker marker) {
        final String diagramDescriptorURI = marker.getAttribute(NavigationMarkerConstants.DIAGRAM_DESCRIPTOR_URI, null);
        final String elementId = marker.getAttribute(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID, null);

        if (diagramDescriptorURI == null || elementId == null || !(getEditor() instanceof SiriusDiagramEditor)) {
            return;
        }
        final SiriusDiagramEditor defaultEditor = (SiriusDiagramEditor) getEditor();

        URI markedResource = URI.createPlatformResourceURI(marker.getResource().getFullPath().toString(), true);
        URI markedDiagramDescriptorURI = URI.createURI(diagramDescriptorURI);
        final SiriusDiagramEditor targetEditor = switchToTargetEditor(defaultEditor, markedResource, markedDiagramDescriptorURI);
        if (targetEditor != null) {
            final Map<?, ?> editPartRegistry = targetEditor.getDiagramGraphicalViewer().getEditPartRegistry();
            final EObject targetView = targetEditor.getDiagram().eResource().getEObject(elementId);
            if (targetView == null) {
                return;
            }
            final EditPart targetEditPart = (EditPart) editPartRegistry.get(targetView);
            if (targetEditPart != null) {
                SiriusDiagramEditorUtil.selectElementsInDiagram(targetEditor, Arrays.asList(new EditPart[] { targetEditPart }));
            }
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(targetEditor);
        }
    }

    private SiriusDiagramEditor switchToTargetEditor(final SiriusDiagramEditor defaultEditor, final URI sessionResourceUri, URI markedDiagramDescriptorURI) {
        SiriusDiagramEditor targetEditor = null;
        DRepresentationDescriptor markedDiagramDescriptor = (DRepresentationDescriptor) defaultEditor.getDiagram().eResource().getEObject(markedDiagramDescriptorURI.fragment());
        if (markedDiagramDescriptor == null) {
            markedDiagramDescriptor = (DRepresentationDescriptor) defaultEditor.getEditingDomain().getResourceSet().getEObject(markedDiagramDescriptorURI, true);
        }
        Optional<Diagram> optionalDiagram = getGMFDiagramFromDRepresentationDescriptor(markedDiagramDescriptor);
        if (optionalDiagram.isPresent()) {
            Diagram markerDiagram = optionalDiagram.get();
            URI markerDiagramURI = EcoreUtil.getURI(markerDiagram);
            if (defaultEditor.getDiagram().equals(markerDiagram)) {
                targetEditor = defaultEditor;
            } else {
                URIEditorInput editorInput = null;
                String editorName = markedDiagramDescriptor.getName();
                Session session = new EObjectQuery(markedDiagramDescriptor).getSession();
                if (session != null) {
                    editorInput = new SessionEditorInput(markerDiagramURI, editorName, session);
                } else {
                    editorInput = new URIEditorInput(markerDiagramURI, editorName);
                }
                final SiriusDiagramEditor searchEditor = searchEditor(editorInput);
                if (searchEditor != null) {
                    targetEditor = searchEditor;
                } else {
                    targetEditor = handleClosedEditorCase(defaultEditor, sessionResourceUri, markedDiagramDescriptorURI, markerDiagram, editorInput);
                }
            }
        }
        return targetEditor;
    }

    private SiriusDiagramEditor openEditor(final SiriusDiagramEditor defaultEditor, URI markedDiagramDescriptorURI, Diagram markerDiagram, Session session) {
        DRepresentationDescriptor markedDiagramDescriptor;
        Diagram diagramToOpen = markerDiagram;
        if (session.getTransactionalEditingDomain() != null && session.getTransactionalEditingDomain().getResourceSet() != defaultEditor.getEditingDomain().getResourceSet()) {
            markedDiagramDescriptor = (DRepresentationDescriptor) session.getTransactionalEditingDomain().getResourceSet().getEObject(markedDiagramDescriptorURI, true);
            Optional<Diagram> optionDiagramToOpen = getGMFDiagramFromDRepresentationDescriptor(markedDiagramDescriptor);
            if (optionDiagramToOpen.isPresent()) {
                diagramToOpen = optionDiagramToOpen.get();
            }
        }

        if (diagramToOpen != null) {
            return (SiriusDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, (DDiagram) diagramToOpen.getElement(), new NullProgressMonitor());
        }
        return null;
    }

    private SiriusDiagramEditor handleClosedEditorCase(final SiriusDiagramEditor defaultEditor, final URI sessionResourceUri, URI markedDiagramDescriptorURI, Diagram markerDiagram,
            URIEditorInput editorInput) {
        SiriusDiagramEditor targetEditor = null;
        Session session = getOrOpenTargetSession(sessionResourceUri);

        // Open the corresponding editor
        SiriusDiagramEditor openedEditor = null;
        if (session != null) {
            openedEditor = openEditor(defaultEditor, markedDiagramDescriptorURI, markerDiagram, session);
        }

        if (openedEditor != null) {
            targetEditor = openedEditor;
            if (defaultEditor instanceof DialectEditor && ((DialectEditor) defaultEditor).getRepresentation() == null) {
                // Close the error editor.
                defaultEditor.close(false);
            }
        } else {
            // Last chance try to change the input of the default
            // editor.
            targetEditor = defaultEditor;
            targetEditor.setInput(editorInput);
        }
        return targetEditor;
    }

    private Optional<Diagram> getGMFDiagramFromDRepresentationDescriptor(DRepresentationDescriptor markedDiagramDescriptor) {
        Optional<Diagram> optional = Optional.ofNullable(markedDiagramDescriptor).map(DRepresentationDescriptor::getRepresentation).filter(DDiagram.class::isInstance).map(dd -> {
            DDiagramGraphicalQuery representationQuery = new DDiagramGraphicalQuery((DDiagram) dd);
            return representationQuery.getAssociatedGMFDiagram().get();
        });
        return optional;
    }

    private Session getOrOpenTargetSession(final URI sessionResourceUri) {
        Session session = SessionManager.INSTANCE.getExistingSession(sessionResourceUri);
        if (session == null) {
            // Try to open a session.
            ModelingProjectManager.INSTANCE.loadAndOpenRepresentationsFile(sessionResourceUri, true);
            // Wait the end of the loading of the representations file
            try {
                Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
            } catch (InterruptedException e) {
                // Do nothing;
            }
            session = SessionManager.INSTANCE.getExistingSession(sessionResourceUri);
        }
        return session;
    }

    /**
     * Search a {@link SiriusDiagramEditor} with the <code>editorInput</code> in the opened editors.
     * 
     * @param editorInput
     *            The editorInput to find.
     * @return a {@link SiriusDiagramEditor} if found, null otherwise
     */
    private SiriusDiagramEditor searchEditor(final URIEditorInput editorInput) {
        SiriusDiagramEditor result = null;
        final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        for (int i = 0; i < editorReferences.length && result == null; i++) {
            try {
                if (editorReferences[i].getEditor(false) instanceof SiriusDiagramEditor && editorReferences[i].getEditorInput() instanceof URIEditorInput) {
                    final URIEditorInput currentEditorInput = (URIEditorInput) editorReferences[i].getEditorInput();
                    if (currentEditorInput.equals(editorInput)) {
                        result = (SiriusDiagramEditor) editorReferences[i].getEditor(false);
                    }
                }
            } catch (final PartInitException e) {
                // do nothing
            }
        }
        return result;
    }

    /**
     * Add a marker.
     * 
     * @param file
     *            The file to add marker
     * @param elementId
     *            the element id (segment of URI)
     * @param diagramDescriptorURI
     *            the URI of the {@link DRepresentationDescriptor} where the element is
     * @param semanticURI
     *            the URI of the semantic element
     * @param location
     *            A human-readable (localized) string which can be used to distinguish between markers on a resource
     * @param message
     *            Describe the problem
     * @param statusSeverity
     *            A number from the set of high, normal and low priorities defined by the platform.
     * @return The added marker.
     */
    public static IMarker addMarker(final IFile file, final String elementId, final String diagramDescriptorURI, final String semanticURI, final String location, final String message,
            final int statusSeverity) {
        final IMarker marker = SiriusMarkerNavigationProvider.addMarker(file, elementId, location, message, statusSeverity);
        try {
            marker.setAttribute(NavigationMarkerConstants.DIAGRAM_DESCRIPTOR_URI, diagramDescriptorURI);
            marker.setAttribute(NavigationMarkerConstants.SEMANTIC_URI, semanticURI);
            marker.setAttribute(IDE.EDITOR_ID_ATTR, DDiagramEditor.EDITOR_ID);
        } catch (final CoreException e) {
            DiagramPlugin.getDefault().logError(Messages.SiriusMarkerNavigationProvider_validationMarkerCreationError, e);
        }
        return marker;
    }

    // CHECKSTYLE:OFF

    /**
     * Add a marker.
     * 
     * @param validationRule
     *            The validation rule
     * @param file
     *            The file to add marker
     * @param elementId
     *            the element id (segment of URI)
     * @param diagramDescriptorUri
     *            the URI of the diagram DRepresentationDescriptor where the element is
     * @param semanticURI
     *            the URI of the semantic element
     * @param location
     *            A human-readable (localized) string which can be used to distinguish between markers on a resource
     * @param message
     *            Describe the problem
     * @param statusSeverity
     *            A number from the set of high, normal and low priorities defined by the platform.
     * @return The added marker.
     */
    public static IMarker addValidationRuleMarker(final ValidationRule validationRule, final IFile file, final String elementId, final String diagramDescriptorUri, final String semanticURI,
            final String location, final String message, final int statusSeverity) {
        final IMarker marker = SiriusMarkerNavigationProvider.addValidationRuleMarker(validationRule, file, elementId, location, message, statusSeverity);
        try {
            marker.setAttribute(NavigationMarkerConstants.DIAGRAM_DESCRIPTOR_URI, diagramDescriptorUri);
            marker.setAttribute(NavigationMarkerConstants.SEMANTIC_URI, semanticURI);
            marker.setAttribute(IDE.EDITOR_ID_ATTR, DDiagramEditor.EDITOR_ID);
        } catch (final CoreException e) {
            DiagramPlugin.getDefault().logError(Messages.SiriusMarkerNavigationProvider_validationMarkerCreationError, e);
        }
        return marker;
    }

    // CHECKSTYLE:ON

    /**
     * Returns the target value of the {@link DSemanticDiagram} that is represented by the <code>diagram</code>.
     * 
     * @param diagram
     *            a GMF diagram.
     * @return the target value of the {@link DSemanticDiagram} that is represented by this diagram.
     */
    public static EObject resolveTargetSemanticElement(final Diagram diagram) {
        final EObject semanticDiagram = diagram.getElement();
        if (semanticDiagram instanceof DSemanticDiagram) {
            return ((DSemanticDiagram) semanticDiagram).getTarget();
        }
        return null;
    }

    /**
     * Returns the semantic element represented by the <code>diagram</code>.
     * 
     * @param diagram
     *            a GMF diagram.
     * @return the semantic element represented by the <code>diagram</code>..
     */
    public static DSemanticDiagram resolveSemanticElement(final Diagram diagram) {
        final EObject semanticDiagram = diagram.getElement();
        if (semanticDiagram instanceof DSemanticDiagram) {
            return (DSemanticDiagram) semanticDiagram;
        }
        return null;
    }
}
