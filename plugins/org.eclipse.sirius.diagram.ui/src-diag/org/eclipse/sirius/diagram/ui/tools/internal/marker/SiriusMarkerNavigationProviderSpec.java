/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.marker;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusMarkerNavigationProvider;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditorUtil;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * Specification of SiriusMarkerNavigationProvider.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusMarkerNavigationProviderSpec extends SiriusMarkerNavigationProvider {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.internal.providers.SiriusMarkerNavigationProvider#doGotoMarker(org.eclipse.core.resources.IMarker)
     */
    @Override
    protected void doGotoMarker(final IMarker marker) {
        final String diagramURI = marker.getAttribute(NavigationMarkerConstants.DIAGRAM_URI, null);
        final String elementId = marker.getAttribute(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID, null);

        if (diagramURI == null || elementId == null || !(getEditor() instanceof SiriusDiagramEditor)) {
            return;
        }
        final SiriusDiagramEditor defaultEditor = (SiriusDiagramEditor) getEditor();

        URI markedResource = URI.createPlatformResourceURI(marker.getResource().getFullPath().toString(), true);
        URI markedDiagramURI = URI.createURI(diagramURI);
        Diagram markedDiagram = (Diagram) defaultEditor.getDiagram().eResource().getEObject(markedDiagramURI.fragment());
        if (markedDiagram == null) {
            markedDiagram = (Diagram) defaultEditor.getEditingDomain().getResourceSet().getEObject(markedDiagramURI, true);
        }

        if (markedDiagram != null) {
            final SiriusDiagramEditor targetEditor = switchToTargetEditor(defaultEditor, markedResource, markedDiagramURI, markedDiagram);

            if (targetEditor != null) {
                final Map editPartRegistry = targetEditor.getDiagramGraphicalViewer().getEditPartRegistry();
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
    }

    private SiriusDiagramEditor switchToTargetEditor(final SiriusDiagramEditor defaultEditor, final URI sessionResourceUri, URI markerDiagramURI, Diagram markerDiagram) {
        final SiriusDiagramEditor targetEditor;
        if (defaultEditor.getDiagram().equals(markerDiagram)) {
            targetEditor = defaultEditor;
        } else {
            URIEditorInput editorInput = null;
            String editorName = SiriusMarkerNavigationProviderSpec.resolveSemanticElement(markerDiagram).getName();
            Session session = new EObjectQuery(markerDiagram).getSession();
            if (session != null) {
                editorInput = new SessionEditorInput(markerDiagramURI, editorName, session);
            } else {
                editorInput = new URIEditorInput(markerDiagramURI, editorName);
            }
            final SiriusDiagramEditor searchEditor = searchEditor(editorInput);
            if (searchEditor != null) {
                targetEditor = searchEditor;
            } else {
                session = getOrOpenTargetSession(sessionResourceUri);

                // Open the corresponding editor
                SiriusDiagramEditor openedEditor = null;
                if (session != null) {
                    openedEditor = (SiriusDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, (DDiagram) markerDiagram.getElement(), new NullProgressMonitor());
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
            }
        }
        return targetEditor;
    }

    private Session getOrOpenTargetSession(final URI sessionResourceUri) {
        Session session = SessionManager.INSTANCE.getExistingSession(sessionResourceUri);
        if (session == null) {
            // Try to open a session.
            OpenRepresentationsFileJob.scheduleNewWhenPossible(sessionResourceUri, true);
            // Wait the end of the loading of the representations file
            OpenRepresentationsFileJob.waitOtherJobs();
            session = SessionManager.INSTANCE.getExistingSession(sessionResourceUri);
        }
        return session;
    }

    /**
     * Search a {@link SiriusDiagramEditor} with the <code>editorInput</code> in
     * the opened editors.
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
     * @param diagramURI
     *            the URI of the diagram where the element is
     * @param semanticURI
     *            the URI of the semantic element
     * @param location
     *            A human-readable (localized) string which can be used to
     *            distinguish between markers on a resource
     * @param message
     *            Describe the problem
     * @param statusSeverity
     *            A number from the set of high, normal and low priorities
     *            defined by the platform.
     * @return The added marker.
     */
    public static IMarker addMarker(final IFile file, final String elementId, final String diagramURI, final String semanticURI, final String location, final String message,
            final int statusSeverity) {
        final IMarker marker = SiriusMarkerNavigationProvider.addMarker(file, elementId, location, message, statusSeverity);
        try {
            marker.setAttribute(NavigationMarkerConstants.DIAGRAM_URI, diagramURI);
            marker.setAttribute(NavigationMarkerConstants.SEMANTIC_URI, semanticURI);
            marker.setAttribute(IDE.EDITOR_ID_ATTR, DDiagramEditor.EDITOR_ID);
        } catch (final CoreException e) {
            DiagramPlugin.getDefault().logError("Failed to create validation marker", e); //$NON-NLS-1$
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
     * @param diagramURI
     *            the URI of the diagram where the element is
     * @param semanticURI
     *            the URI of the semantic element
     * @param location
     *            A human-readable (localized) string which can be used to
     *            distinguish between markers on a resource
     * @param message
     *            Describe the problem
     * @param statusSeverity
     *            A number from the set of high, normal and low priorities
     *            defined by the platform.
     * @return The added marker.
     */
    public static IMarker addValidationRuleMarker(final ValidationRule validationRule, final IFile file, final String elementId, final String diagramURI, final String semanticURI,
            final String location, final String message, final int statusSeverity) {
        final IMarker marker = SiriusMarkerNavigationProvider.addValidationRuleMarker(validationRule, file, elementId, location, message, statusSeverity);
        try {
            marker.setAttribute(NavigationMarkerConstants.DIAGRAM_URI, diagramURI);
            marker.setAttribute(NavigationMarkerConstants.SEMANTIC_URI, semanticURI);
            marker.setAttribute(IDE.EDITOR_ID_ATTR, DDiagramEditor.EDITOR_ID);
        } catch (final CoreException e) {
            DiagramPlugin.getDefault().logError("Failed to create validation marker", e); //$NON-NLS-1$
        }
        return marker;
    }

    // CHECKSTYLE:ON

    /**
     * Returns the target value of the {@link DSemanticDiagram} that is
     * represented by the <code>diagram</code>.
     * 
     * @param diagram
     *            a GMF diagram.
     * @return the target value of the {@link DSemanticDiagram} that is
     *         represented by this diagram.
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
