/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * This listener provides the synchronized behavior between the dialect selected element(s) and the given
 * CommonNavigator. As a {@link IPropertyListener}, it will listen to the property
 * <code>IS_LINKING_ENABLED_PROPERTY</code>. If the "link with editor" is activated, it will register itself as a
 * {@link IPartListener2}. If a new {@link DialectEditor} is opened (or closed), it will register (or remove) itself as
 * a {@link ISelectionChangedListener} to update the {@link CommonNavigator} according to the selection.</br>
 * When the "link with editor" is deactivated, it will remove itself from all {@link DialectEditor} selectionListeners
 * and from IPartListeners.
 * 
 * @author Florian Barbin
 *
 */
public class SiriusDialectLinkWithEditorSelectionListener implements ISelectionChangedListener, IPropertyListener, IPartListener2 {

    private CommonNavigator navigator;

    private IPartService partService;

    private Collection<DialectEditor> registeredEditors;

    /**
     * Default constructor.
     * 
     * @param navigator
     *            the common navigator.
     */
    public SiriusDialectLinkWithEditorSelectionListener(CommonNavigator navigator) {
        this.navigator = navigator;
        this.registeredEditors = new ArrayList<DialectEditor>();

    }

    /**
     * Initialize the Selection Listener.
     */
    public void init() {
        if (this.navigator != null) {
            this.navigator.addPropertyListener(this);
            partService = getPartService();

            // If the linking is already activated at the initialization, we
            // register the IPartListener2 and the ISelectionChangedListener on each
            // opened DialectEditor.
            if (this.navigator.isLinkingEnabled()) {
                enableSelectionListener();
            }
        }
    }

    /**
     * Removes this listener from IPartService and all editors where it is registered.
     */
    public void dispose() {
        disableSelectionListener();
        navigator = null;
        partService = null;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        ISelection selection = event.getSelection();
        if (selection instanceof IStructuredSelection) {

            // If the selection event source is different from the active part,
            // that means the selection change has not been triggered by a user
            // action. In that case we do nothing to avoid loops.
            if (event.getSource() != getActivePartSource()) {
                return;
            }

            // If the selection come from the navigator, we select the
            // corresponding representations elements.
            if (event.getSource().equals(navigator.getCommonViewer())) {
                IWorkbenchPage page = EclipseUIUtil.getActivePage();
                IEditorPart activeEditor = page.getActiveEditor();
                if (activeEditor instanceof DialectEditor) {
                    DialectEditor dialectEditor = (DialectEditor) activeEditor;
                    page.bringToTop(dialectEditor);
                    selectRepresentationElements(selection, dialectEditor);
                }
            } else {
                Set<EObject> targets = getTargetsFromSelection((IStructuredSelection) selection);
                if (!targets.isEmpty()) {
                    navigator.selectReveal(new StructuredSelection(targets.toArray()));
                }
            }
        }
    }

    private Object getActivePartSource() {
        IWorkbenchPart part = this.partService.getActivePart();
        Object toReturn = part;
        if (part instanceof DialectEditor) {
            toReturn = ((DialectEditor) part).getEditorSite().getSelectionProvider();
        } else if (part instanceof CommonNavigator) {
            toReturn = ((CommonNavigator) part).getCommonViewer();
        }
        return toReturn;
    }

    private void selectRepresentationElements(ISelection selection, DialectEditor dialectEditor) {
        List<DRepresentationElement> representationElements = getRepresentationElements(dialectEditor.getRepresentation(), ((IStructuredSelection) selection).toList());
        DialectUIManager.INSTANCE.selectAndReveal(dialectEditor, representationElements);
    }

    private List<DRepresentationElement> getRepresentationElements(final DRepresentation representation, final List<?> selection) {
        List<DRepresentationElement> result = new ArrayList<>();
        if (representation != null) {
            for (final DRepresentationElement element : representation.getRepresentationElements()) {
                if (selection != null && selection.contains(element.getTarget()))
                    result.add(element);
            }
        }
        return result;
    }

    /**
     * When the IS_LINKING_ENABLED_PROPERTY changes, we activate or deactivate this listener.
     */
    @Override
    public void propertyChanged(Object source, int propId) {
        if (propId == CommonNavigator.IS_LINKING_ENABLED_PROPERTY) {
            // if the link with editor property has changed, we install or
            // remove the synchronizer according to the new value.
            if (navigator.isLinkingEnabled()) {
                enableSelectionListener();
            } else {
                disableSelectionListener();
            }
        }
    }

    @Override
    public void partActivated(IWorkbenchPartReference partRef) {
    }

    @Override
    public void partBroughtToTop(IWorkbenchPartReference partRef) {
    }

    /**
     * When a {@link DialectEditor} is closed, we remove this instance from {@link ISelectionChangedListener}s.
     */
    @Override
    public void partClosed(IWorkbenchPartReference partRef) {
        IWorkbenchPart part = partRef.getPart(false);
        if (part instanceof DialectEditor && registeredEditors.contains(part)) {
            removeSelectionListener((DialectEditor) part);
            registeredEditors.remove(part);
        }
    }

    @Override
    public void partDeactivated(IWorkbenchPartReference partRef) {
    }

    /**
     * When a {@link DialectEditor} is opened, we register this instance as a {@link ISelectionChangedListener}.
     */
    @Override
    public void partOpened(IWorkbenchPartReference partRef) {
        IWorkbenchPart part = partRef.getPart(false);
        if (part instanceof DialectEditor && !registeredEditors.contains(part)) {
            addSelectionListener((DialectEditor) part);
            registeredEditors.add((DialectEditor) part);
        }
    }

    @Override
    public void partHidden(IWorkbenchPartReference partRef) {
    }

    @Override
    public void partVisible(IWorkbenchPartReference partRef) {
    }

    @Override
    public void partInputChanged(IWorkbenchPartReference partRef) {

    }

    private Set<EObject> getTargetsFromSelection(IStructuredSelection iStructuredSelection) {
        Set<EObject> targets = new HashSet<EObject>();
        for (Iterator<Object> iterator = iStructuredSelection.iterator(); iterator.hasNext(); /**/) {
            Object element = iterator.next();
            DSemanticDecorator decorator = null;
            if (element instanceof DSemanticDecorator) {
                decorator = (DSemanticDecorator) element;
            } else if (element instanceof IAdaptable) {
                decorator = ((IAdaptable) element).getAdapter(DSemanticDecorator.class);
            }

            if (decorator != null) {
                // If the selection is the representation, we want to select the
                // representation descriptor node and not its semantic element.
                if (decorator instanceof DRepresentation) {
                    targets.add(new DRepresentationQuery((DRepresentation) decorator).getRepresentationDescriptor());
                } else {
                    targets.add(decorator.getTarget());
                }
            }

        }
        return targets;
    }

    /**
     * Adds this instance as a {@link ISelectionChangedListener} on the given editor.
     * 
     * @param editor
     *            the {@link DialectEditor}.
     */
    private void addSelectionListener(DialectEditor editor) {
        if (editor != null && editor.getEditorSite() != null) {
            ISelectionProvider selectionProvider = editor.getEditorSite().getSelectionProvider();
            if (selectionProvider != null) {
                selectionProvider.addSelectionChangedListener(this);
            }
        }
    }

    private IPartService getPartService() {
        IViewSite site = navigator.getViewSite();
        if (site != null) {
            return site.getService(IPartService.class);
        }
        return null;
    }

    /**
     * Registers this instance as a {@link IPartListener2} and as a {@link ISelectionChangedListener} on each opened
     * {@link DialectEditor}.
     */
    private void enableSelectionListener() {
        if (partService != null) {
            // We register this listener on the current viewer to have a
            // bidirectional selection.
            navigator.getCommonViewer().addSelectionChangedListener(this);
            partService.addPartListener(this);
            IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
            for (IEditorReference editorReference : editorReferences) {
                IEditorPart editorPart = editorReference.getEditor(false);
                if (editorPart instanceof DialectEditor) {
                    addSelectionListener((DialectEditor) editorPart);
                    registeredEditors.add((DialectEditor) editorPart);
                }
            }
        }
    }

    /**
     * Removes this instance from the {@link IPartListener2}s and from all {@link DialectEditor} selectionListeners.
     */
    private void disableSelectionListener() {
        if (partService != null) {
            navigator.getCommonViewer().removeSelectionChangedListener(this);
            partService.removePartListener(this);
            for (DialectEditor dialectEditor : registeredEditors) {
                removeSelectionListener(dialectEditor);
            }
            registeredEditors.clear();
        }
    }

    /**
     * Removes this instance from the given editor selection listeners.
     * 
     * @param dialectEditor
     *            the {@link DialectEditor}.
     */
    private void removeSelectionListener(DialectEditor dialectEditor) {
        if (dialectEditor != null && dialectEditor.getEditorSite() != null) {
            ISelectionProvider selectionProvider = dialectEditor.getEditorSite().getSelectionProvider();
            if (selectionProvider != null) {
                selectionProvider.removeSelectionChangedListener(this);
            }
        }
    }
}
