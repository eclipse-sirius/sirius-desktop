/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.dialect.marker;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;
import org.eclipse.ui.ide.IGotoMarker;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * <p>
 * A MarkerNavigationProvider used to reveal the {@link DRepresentationElement}
 * matching with the semantic element contained in a given traceability marker.
 * </p>
 * *
 * <ul>
 * <li>
 * If the opened representation is representing S1 in some way, the element
 * representing S1 should be revealed and selected</li>
 * <li>If the opened representation is not representing S1, the code should look
 * through the representations opened for this session and find one representing
 * S1. If one if found, it should call the goToMarker method on it. If none is
 * found we should dig through all the session's representations looking for one
 * representing S1 (using the cross referencer it can be done efficiently), open
 * it (ask the user if severals are found) and then reveal the element calling
 * goToMarker on it.</li>
 * </ul>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TraceabilityMarkerNavigationProvider implements IGotoMarker {

    /**
     * Key for Sirius's Traceability Markers, representing the internal
     * representation used by editor (used only for diagrams).
     */
    public static final String INTERNAL_REPRESENTATION_URI = "DIAGRAM_URI"; //$NON-NLS-1$

    /**
     * Key for Sirius's Traceability Markers, representing the ID of element
     * inside its representation.
     */
    public static final String REPRESENTATION_ELEMENT_ID = "REPRESENTATION_ELEMENT_ID"; //$NON-NLS-1$

    /**
     * Key for Sirius's Traceability Markers, representing the URI of the
     * representation.
     */
    public static final String REPRESENTATION_URI = "REPRESENTATION_URI"; //$NON-NLS-1$

    /**
     * Key indicating that standard Traceability mechanism can be applied to the
     * marker.
     */
    public static final String TRACEABILITY_SEMANTIC_ELEMENT_URI_ATTRIBUTE = EValidator.URI_ATTRIBUTE;

    /**
     * Internal key for Sirius's Traceability Markers, indicating that this
     * marker is a ghost.
     */
    private static final String TRACEABILITY_INTERNAL_ATTRIBUTE = "traceab_viewpoint_ghost"; //$NON-NLS-1$

    /**
     * The editor that have called this MarkerNavigationProvider. It's optional
     * as we might be called and not having an editor.
     */
    private Option<DialectEditor> currentEditor = Options.newNone();

    /**
     * The editor to set the focus on.
     */
    private DialectEditor editorToFocus;

    /**
     * The representation to set the focus on.
     */
    private DRepresentation representationToFocus;

    /**
     * The representation element to select in the editorToFocus.
     */
    private DRepresentationElement representationElementToSelect;

    private Session currentSession;

    /**
     * Creates a new {@link TraceabilityMarkerNavigationProvider}.
     * 
     * @param editor
     *            the editor to focus in priority - can be null
     */
    public TraceabilityMarkerNavigationProvider(DialectEditor editor) {
        this.currentEditor = Options.newSome(editor);
        Session foundSession = null;
        for (final IEditingSession uiSession : SessionUIManager.INSTANCE.getUISessions()) {
            if (currentEditor.some() && uiSession.handleEditor(currentEditor.get())) {
                foundSession = uiSession.getSession();
            }
        }
        if (foundSession != null) {
            this.currentSession = foundSession;
        } else {
            String editorTitle = ""; //$NON-NLS-1$
            if (currentEditor.some()) {
                editorTitle = currentEditor.get().getTitle();
            }
            throw new IllegalArgumentException("We can't find a session associated to the given editor \"" + editorTitle + "\"");
        }
    }

    /**
     * Create a new {@link TraceabilityMarkerNavigationProvider} without having
     * an editor.
     * 
     * @param session
     *            session to open.
     */
    public TraceabilityMarkerNavigationProvider(Session session) {
        this.currentSession = session;
    }

    /**
     * Indicates if the given marker is a Traceability marker.
     * 
     * @param marker
     *            the marker to test
     * @return true if the given marker is a Traceability marker, false
     *         otherwise
     */
    public static boolean isTraceabilityMarker(final IMarker marker) {
        String elementURI = marker.getAttribute(TRACEABILITY_SEMANTIC_ELEMENT_URI_ATTRIBUTE, null);
        String internalAttribute = marker.getAttribute(TRACEABILITY_INTERNAL_ATTRIBUTE, null);
        return (elementURI != null) && (internalAttribute == null);
    }

    /**
     * <p>
     * Search for the best editor referencing the semantic element contained in
     * the given marker. Sets the focus on this editor and select the graphical
     * element that references this semantic element.
     * </p>
     * *
     * <ul>
     * <li>
     * If the opened representation is representing S1 in some way, the element
     * representing S1 should be revealed and selected</li>
     * <li>If the opened representation is not representing S1, the code should
     * look through the representations opened for this session and find one
     * representing S1. If one if found, it should call the goToMarker method on
     * it. If none is found we should dig through all the session's
     * representations looking for one representing S1 (using the cross
     * referencer it can be done efficiently), open it (ask the user if severals
     * are found) and then reveal the element calling goToMarker on it.</li>
     * </ul>
     * 
     * @param marker
     *            a marker containing at least an
     *            {@link EValidator#URI_ATTRIBUTE} containing the URI of the
     *            semantic element to go to
     */
    public void gotoMarker(final IMarker marker) {
        if (Display.getCurrent() == null) {
            /* we are not in a UI thread */
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    gotoMarkerInUIThread(marker);
                }
            });
        } else {
            /* Here we are in UI Thread */
            gotoMarkerInUIThread(marker);
        }

    }

    /**
     * Execute the goToMarker logic. <b>Must be executed in UIThread</b>
     * 
     * @param marker
     *            a marker containing at least an
     *            {@link EValidator#URI_ATTRIBUTE} containing the URI of the
     *            semantic element to go to
     */
    protected void gotoMarkerInUIThread(IMarker marker) {
        /*
         * Step 0 : we have to do something here only if the caller editor is
         * the MasterEditor of the workbench
         */
        if (isMasterEditorCall() || !currentEditor.some()) {

            /* Step 1 : getting the semantic element from its URI */
            EditingDomain editingDomain = currentSession.getTransactionalEditingDomain();
            EObject semanticElement = null;

            String traceabilityAttribute = marker.getAttribute(TRACEABILITY_SEMANTIC_ELEMENT_URI_ATTRIBUTE, null);
            if (traceabilityAttribute != null) {
                URI uri = URI.createURI(traceabilityAttribute);
                semanticElement = editingDomain.getResourceSet().getEObject(uri, true);

                boolean foundEditor = false;
                // Step 2 : try to find the semantic element in the
                // editor that has the focus (only if it is a dialect editor)
                IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
                if ((activeEditor instanceof DialectEditor) && (((DialectEditor) activeEditor).getRepresentation() != null)) {
                    foundEditor = searchInEditor((DialectEditor) activeEditor, semanticElement);
                }

                // Step 3 : try to find the semantic element in all the opened
                // editors on the current active Session
                if (!foundEditor) {
                    foundEditor = searchInAllOpenedEditorOnCurrentActiveSession(semanticElement);
                }

                // Step 4 : set the focus on the editor and select the
                // representation element referencing the semantic element
                if (foundEditor) {
                    setFocusOnEditor(marker);
                } else {

                    // Step 5 : try to find the semantic element in all
                    // representations
                    // contained in the current active session
                    // and ask user which representation(s) he would like to
                    // open
                    searchInAllRepresentationsOfCurrentActiveSession(marker, semanticElement);
                }

            }
        }
    }

    /**
     * <p>
     * Indicates if the caller editor is the Master Editor, i.e. the editor that
     * should try to go to marker. There is only one MasterEditor in the same
     * Workbench Page.
     * </p>
     * <b>The Master Editor is the first Dialect Editor returned by </b>
     * <code>PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()</code>
     * 
     * @return true if the caller editor is a MasterEditor, false otherwise
     */
    private boolean isMasterEditorCall() {
        IWorkbenchPage activePage = EclipseUIUtil.getActivePage();
        if (this.currentEditor.some() && activePage != null) {
            IEditorReference[] editorReferences = activePage.getEditorReferences();
            for (IEditorReference editorRef : editorReferences) {
                if (editorRef.getEditor(false) instanceof DialectEditor) {
                    return editorRef.getEditor(false).equals(this.currentEditor.get());
                }
            }
        }
        return false;
    }

    /**
     * Searches for any representation element contained in the given
     * {@link DialectEditor} that references the given semanticElement.
     * 
     * @param editor
     *            the {@link DialectEditor} in which search the semanticElement
     * @param semanticElement
     *            the searched semantic element
     * @return true if the given editor contains at least one representation
     *         element that references the given semantic element, false
     *         otherwise
     */
    private boolean searchInEditor(DialectEditor editor, EObject semanticElement) {
        boolean foundEditor = false;

        // If the Representation associated to the editor references the
        // searched
        // semantic element
        if (editor.getRepresentation().eCrossReferences().contains(semanticElement)) {
            foundEditor = true;
        } else {
            for (final DRepresentationElement representationElement : editor.getRepresentation().getRepresentationElements()) {
                if (representationElementReferencesSemanticElement(representationElement, semanticElement)) {
                    representationElementToSelect = representationElement;
                    foundEditor = true;
                    break;
                }
            }
        }

        /* If the searched semantic element has been found in the given editor */
        if (foundEditor) {
            editorToFocus = editor;
            representationToFocus = editor.getRepresentation();
        }
        return foundEditor;
    }

    /**
     * Indicates if the given representation Element references the given
     * semantic element.
     * 
     * @param representationElement
     *            the representation element to search into
     * @param semanticElement
     *            the searched semantic element
     * @return true if the given representation Element references the given
     *         semantic element, false otherwise
     */
    private boolean representationElementReferencesSemanticElement(DRepresentationElement representationElement, EObject semanticElement) {
        return representationElement.eCrossReferences().contains(semanticElement);
    }

    /**
     * Sets the focus on the found editor and select the representation element
     * referencing the search opened element.
     * 
     * @param marker
     *            the original marker
     */
    private void setFocusOnEditor(IMarker marker) {
        // Step 1 : create a shadow marker, readable by any Dialect Editor
        // This marker will be created on the resource containing the
        // representation to focus on
        Resource resource = representationToFocus.eResource();
        URI uri = resource.getURI();
        uri = resource.getResourceSet().getURIConverter().normalize(uri);
        IResource res = null;
        // If the URI is a plugin URI, we created a marker on the workspace root
        if (uri.isPlatformPlugin()) {
            String pluginResourceString = uri.toString();
            if (pluginResourceString != null) {
                res = ResourcesPlugin.getWorkspace().getRoot();
            }
        } else {
            String platformResourceString = uri.toPlatformString(true);

            if (platformResourceString != null) {
                res = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourceString));
            }
        }

        IMarker shadowMarker = null;
        try {
            shadowMarker = res.createMarker(marker.getType());
            shadowMarker.setAttribute(TRACEABILITY_SEMANTIC_ELEMENT_URI_ATTRIBUTE, EcoreUtil.getURI(this.representationToFocus).toString());
            shadowMarker.setAttribute(REPRESENTATION_ELEMENT_ID, resource.getURIFragment(representationElementToSelect).toString());
            shadowMarker.setAttribute(REPRESENTATION_URI, EcoreUtil.getURI(this.representationToFocus).toString());
            shadowMarker.setAttribute(TRACEABILITY_INTERNAL_ATTRIBUTE, "active"); //$NON-NLS-1$

            // Step 2 : we sets the focus on the editor and call the goToMarker
            // method on it
            editorToFocus.setFocus();
            editorToFocus.gotoMarker(shadowMarker);

        } catch (CoreException e) {
            // Nothing to do, goToMarker will fail.
        } finally {
            if (shadowMarker != null) {
                try {
                    shadowMarker.delete();
                } catch (CoreException e) {
                    // Nothing to do, goToMarker will fail.
                }
            }
        }
    }

    /**
     * Searches for any representation element :
     * <ul>
     * <li>contained in all opened {@link DialectEditor} that are part of the
     * current active Session</li>
     * <li>that references the given semanticElement.</li>
     * </ul>
     * 
     * @param semanticElement
     *            the searched semantic element
     * @return true if one of the opened {@link DialectEditor} that are part of
     *         the current active Session references the given semantic element
     */
    private boolean searchInAllOpenedEditorOnCurrentActiveSession(EObject semanticElement) {
        boolean found = false;
        // For all opened editors
        final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        for (int i = 0; i < editorReferences.length && !found; i++) {
            IEditorPart openedEditor = editorReferences[i].getEditor(false);
            // If the editor is a Dialect editor
            if (openedEditor instanceof DialectEditor) {
                if (currentSession != null) {
                    IEditingSession uiCurrentSession = SessionUIManager.INSTANCE.getOrCreateUISession(currentSession);
                    // If this dialect Editor is part of the editors opened by
                    // the currentSession
                    DialectEditor editor = uiCurrentSession.getEditor(((DialectEditor) openedEditor).getRepresentation());
                    if (editor != null) {
                        // We search for the element in this editor
                        found = searchInEditor((DialectEditor) openedEditor, semanticElement);
                    }
                }
            }
        }
        return found;
    }

    /**
     * <p>
     * Searches for any representation element :
     * <ul>
     * <li>contained in all representations that are part of the current active
     * Session</li>
     * <li>that references the given semanticElement.</li>
     * </ul>
     * </p>
     * <p>
     * <b>Note :</b> if several representations are found, a popup will ask to
     * end-user which one he would like to open.
     * </p>
     * 
     * @param semanticElement
     *            the searched semantic element
     */
    private void searchInAllRepresentationsOfCurrentActiveSession(IMarker initialMarker, EObject semanticElement) {

        if (currentSession != null) {

            final Set<DRepresentation> candidateRepresentations = Sets.newLinkedHashSet();
            final Map<DRepresentation, DRepresentationElement> representationToElements = Maps.newHashMap();

            /*
             * step 1 : retrieve all representations that reference the searched
             * semantic element
             */
            for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(currentSession)) {
                if (representation.eCrossReferences().contains(semanticElement)) {
                    candidateRepresentations.add(representation);
                    break;
                }
                for (final DRepresentationElement representationElement : representation.getRepresentationElements()) {

                    if (representationElementReferencesSemanticElement(representationElement, semanticElement)) {
                        candidateRepresentations.add(representation);
                        representationToElements.put(representation, representationElement);
                        break;
                    }
                }
            }

            /*
             * Step 2 : Now that we have all representations that reference the
             * searched semantic element, we ask user which one(s) he would like
             * to open in an editor
             */
            askUserForRepresentationsToOpen(initialMarker, candidateRepresentations, representationToElements, semanticElement);
        }
    }

    /**
     * Asks end-user to choose Representation(s) to open in an editor.
     * 
     * @param candidateRepresentations
     *            all representations that matched the searched element
     * @param representationToElements
     *            representations to representationElement to select in the new
     *            Editor
     */
    private void askUserForRepresentationsToOpen(final IMarker initialMarker, Set<DRepresentation> candidateRepresentations,
            final Map<DRepresentation, DRepresentationElement> representationToElements, EObject semanticElement) {

        if (!candidateRepresentations.isEmpty()) {
            Object[] selectedRepresentations = new Object[0];
            if (candidateRepresentations.size() > 1) {
                // Create a Dialog that ask users which representations he would
                // like to open
                RepresentationToOpenDialog dialog = new RepresentationToOpenDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), currentSession, candidateRepresentations,
                        semanticElement);
                dialog.setInput(currentSession);
                int result = dialog.open();
                if (result == Window.OK) {
                    selectedRepresentations = dialog.getResult();
                }
            } else {
                selectedRepresentations = candidateRepresentations.toArray();
            }

            if (selectedRepresentations.length > 0) {
                final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(currentSession);

                // For each selected representation
                for (Object selectedRepresentation2 : selectedRepresentations) {
                    final DRepresentation selectedRepresentation = (DRepresentation) selectedRepresentation2;
                    Display.getDefault().asyncExec(new Runnable() {

                        public void run() {
                            // We create a new Dialect editor
                            final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(currentSession, selectedRepresentation, new NullProgressMonitor());
                            if (editor != null && uiSession != null) {
                                // That we attach to the UI Session
                                uiSession.attachEditor((DialectEditor) editor);
                            }
                            // We then select the element related to the
                            // searched
                            // semantic element
                            editorToFocus = (DialectEditor) editor;
                            representationToFocus = selectedRepresentation;
                            representationElementToSelect = representationToElements.get(selectedRepresentation);
                            setFocusOnEditor(initialMarker);
                        }
                    });
                }
            }
        }
    }

    /**
     * TreeSelectionDialog used to allow user to select Representations he would
     * like to open.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     */
    private class RepresentationToOpenDialog extends CheckedTreeSelectionDialog {

        /**
         * Constructor.
         * 
         * @param parent
         *            The shell to parent from.
         * @param candidateRepresentations
         *            the representations to show to user
         * @param semanticElement
         * @param contentProvider
         *            the content provider to evaluate the tree structure
         */
        public RepresentationToOpenDialog(Shell parent, Session session, Set<DRepresentation> candidateRepresentations, EObject semanticElement) {
            super(parent, new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()), new CandidateRepresentationContentProvider(ViewHelper.INSTANCE.createAdapterFactory(),
                    candidateRepresentations, session));
            SessionLabelProvider labelProvider = new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory());
            String objectName = labelProvider.getText(semanticElement);
            setTitle("Open representations referencing " + objectName);
            setMessage("Select the Representations (referencing " + objectName + ") you would like to open.");
        }

    }

    /**
     * ContentProvider for {@link RepresentationToOpenDialog}.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     */
    private class CandidateRepresentationContentProvider implements ITreeContentProvider {

        private Set<DRepresentation> candidateRepresentations;

        private Session session;

        public CandidateRepresentationContentProvider(AdapterFactory createAdapterFactory, Set<DRepresentation> representations, Session session) {
            this.candidateRepresentations = representations;
            this.session = session;
        }

        public Object[] getElements(Object inputElement) {
            return candidateRepresentations.toArray();
        }

        public void dispose() {

        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

        }

        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof Session) {
                return candidateRepresentations.toArray();
            }
            return null;
        }

        public Object getParent(Object element) {
            return session;
        }

        public boolean hasChildren(Object element) {
            return element instanceof Session;
        }

    }

}
