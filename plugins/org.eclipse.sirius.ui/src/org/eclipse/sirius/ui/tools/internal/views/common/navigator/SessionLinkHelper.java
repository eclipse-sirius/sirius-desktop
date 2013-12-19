/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.navigator.ILinkHelper;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The current link helper is able to activate an opened editor on the selected
 * representation in a common viewer.
 * 
 * For an EObject selection, the selection in the activated editor is updated
 * with
 * 
 * @author mporhel
 * 
 */
public class SessionLinkHelper implements ILinkHelper {
    /**
     * {@inheritDoc}
     */
    public IStructuredSelection findSelection(IEditorInput anInput) {
        Object foundElement = null;

        IFile file = ResourceUtil.getFile(anInput);
        if (file != null) {
            foundElement = file;
        }

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorPart editor = page.findEditor(anInput);
        if (editor != null) {

            // It is possible to get the representation, the associated semantic
            // element or even the semantic selection.
            // The choice was made to select representation.
            Object editorRepresentation = getEditorRepresentation(anInput, editor);
            if (editorRepresentation != null) {
                foundElement = editorRepresentation;
            }
        }
        return foundElement == null ? StructuredSelection.EMPTY : new StructuredSelection(foundElement);
    }

    private Object getEditorRepresentation(IEditorInput anInput, IEditorPart editor) {
        Object foundElement = null;

        if (anInput instanceof SessionEditorInput) {
            SessionEditorInput sessionInput = (SessionEditorInput) anInput;
            if (sessionInput.getSession() != null && sessionInput.getSession().isOpen() && editor instanceof DialectEditor) {
                foundElement = ((DialectEditor) editor).getRepresentation();
            }
        }
        return foundElement;
    }

    /**
     * {@inheritDoc}
     */
    public void activateEditor(IWorkbenchPage aPage, IStructuredSelection aSelection) {
        if (aPage == null || aSelection == null || aSelection.isEmpty())
            return;

        DRepresentation selectedRepresentation = getSelectedRepresentation(aSelection.toList());

        IEditorPart activeEditor = aPage.getActiveEditor();

        if (activeEditor != null && selectedRepresentation != null) {
            Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) selectedRepresentation).getTarget());
            if (session != null) {
                IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
                if (uiSession != null) {
                    IEditorPart editor = uiSession.getEditor(selectedRepresentation);
                    if (editor != null && editor.getSite() != null && aPage.equals(editor.getSite().getPage())) {
                        activeEditor = editor;
                        aPage.bringToTop(activeEditor);
                    }
                }
            }
        } else if (activeEditor instanceof DialectEditor && activeEditor.getEditorInput() instanceof SessionEditorInput && aSelection.getFirstElement() instanceof EObject) {
            aPage.bringToTop(activeEditor);
            DialectEditor dialectEditor = (DialectEditor) activeEditor;
            List<DRepresentationElement> vpSelection = getRepresentationsForElement(dialectEditor.getRepresentation(), aSelection.toList());
            DialectUIManager.INSTANCE.setSelection(dialectEditor, vpSelection);
        }
    }

    private DRepresentation getSelectedRepresentation(Collection<?> selection) {
        DRepresentation rep = null;
        Iterable<DRepresentation> selectedReps = Iterables.filter(selection, DRepresentation.class);
        if (selectedReps.iterator().hasNext()) {
            rep = selectedReps.iterator().next();
        } else {
            Iterable<RepresentationItemImpl> selectedItems = Iterables.filter(selection, RepresentationItemImpl.class);
            if (selectedItems.iterator().hasNext()) {
                rep = selectedItems.iterator().next().getRepresentation();
            }
        }
        return rep;
    }

    /**
     * Get the representation element from the semantic one.
     * 
     * @param representation
     *            the representation
     * @param selection
     *            the semantic element
     * @return the first representation element which has as target the semantic
     *         element given as parameter
     */
    protected final List<DRepresentationElement> getRepresentationsForElement(final DRepresentation representation, final List<?> selection) {
        List<DRepresentationElement> result = Lists.newArrayList();
        if (representation != null) {
            for (final DRepresentationElement element : representation.getRepresentationElements()) {
                if (selection != null && selection.contains(element.getTarget()))
                    result.add(element);
            }
        }
        return result;
    }
}
