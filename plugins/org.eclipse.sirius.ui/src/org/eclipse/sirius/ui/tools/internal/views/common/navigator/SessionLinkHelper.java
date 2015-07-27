/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.util.ArrayList;
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
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.navigator.ILinkHelper;

import com.google.common.collect.Iterables;

/**
 * The current link helper is able to activate an opened editor on the selected
 * representation in a common viewer.
 * 
 * For one or several EObject selection in the Common Navigator, the selection
 * is now handled by the
 * <code>SiriusDialectLinkWithEditorSelectionListener</code>.
 * 
 * @author mporhel
 * 
 */
public class SessionLinkHelper implements ILinkHelper {
    /**
     * {@inheritDoc}
     */
    @Override
    public IStructuredSelection findSelection(IEditorInput anInput) {
        IStructuredSelection returnSelection = null;

        IFile file = ResourceUtil.getFile(anInput);
        if (file != null) {
            returnSelection = new StructuredSelection(file);
        }

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorPart editor = page.findEditor(anInput);
        if (editor instanceof DialectEditor) {

            Collection<DSemanticDecorator> semanticDecorators = DialectUIManager.INSTANCE.getSelection((DialectEditor) editor);

            // When the focus is set back on the representation by selecting
            // a representation element, we select the corresponding target
            // instead of the representation node.
            if (!semanticDecorators.isEmpty()) {
                List<EObject> elements = new ArrayList<EObject>();
                for (DSemanticDecorator currentDecorator : semanticDecorators) {
                    elements.add(currentDecorator.getTarget());
                }
                returnSelection = new StructuredSelection(elements);
            } else {
                // It is possible to get the representation, the associated
                // semantic element or even the semantic selection. The choice
                // was made to select representation.
                Object editorRepresentation = getEditorRepresentation(anInput, editor);
                if (editorRepresentation != null) {
                    returnSelection = new StructuredSelection(editorRepresentation);
                }
            }
        }
        return returnSelection == null ? StructuredSelection.EMPTY : returnSelection;
    }

    private Object getEditorRepresentation(IEditorInput anInput, IEditorPart editor) {
        Object foundElement = null;

        if (anInput instanceof SessionEditorInput) {
            SessionEditorInput sessionInput = (SessionEditorInput) anInput;
            Session session = sessionInput.getSession(false);
            if (session != null && session.isOpen() && editor instanceof DialectEditor) {
                foundElement = ((DialectEditor) editor).getRepresentation();
            }
        }
        return foundElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
}
