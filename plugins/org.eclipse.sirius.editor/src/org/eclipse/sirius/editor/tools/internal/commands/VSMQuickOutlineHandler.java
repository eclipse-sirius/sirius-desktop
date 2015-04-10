/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlineAdapterFactoryLabelProvider;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlineDescriptor;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlinePageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.SiriusInformationPresenter;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.SiriusInformationPresenterFactory;
import org.eclipse.sirius.editor.tools.internal.outline.VSMOutlineCallback;
import org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Handler for the quick outline of the VSM editor.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 * 
 * @author bgrouhan
 */
public class VSMQuickOutlineHandler extends AbstractHandler {

    /**
     * The command has been executed, so it extracts the needed information from
     * the application context.
     * 
     * @param event
     *            the execution event.
     * @throws ExecutionException
     *             an execution exception.
     * @return <code>null</code>.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        CustomSiriusEditor editor = getCurrentEditor();
        Object root = getRoot(editor);
        if (root != null) {
            SiriusInformationPresenter presenter = SiriusInformationPresenterFactory.createInformationPresenter(editor.getControl(), createDescriptor(editor), root);
            presenter.showInformation();
        }
        return null;
    }

    /**
     * Returns the currently active editor if it's a VSM editor.
     * 
     * @return The currently active editor if it's a VSM editor,
     *         <code>null</code> otherwise.
     */
    protected CustomSiriusEditor getCurrentEditor() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null && window.getActivePage() != null && window.getActivePage().getActiveEditor() instanceof CustomSiriusEditor) {
            return (CustomSiriusEditor) window.getActivePage().getActiveEditor();
        }
        return null;
    }

    /**
     * Create the quick outline descriptor.
     * 
     * @param editor
     *            the Sirius editor.
     * @return the quick outline descriptor.
     */
    private QuickOutlineDescriptor createDescriptor(CustomSiriusEditor editor) {
        ComposedAdapterFactory caf = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        QuickOutlineAdapterFactoryLabelProvider labelProvider = new QuickOutlineAdapterFactoryLabelProvider(caf);
        AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(caf);
        VSMOutlineCallback callBack = new VSMOutlineCallback(editor);
        QuickOutlinePageDescriptor page1 = new QuickOutlinePageDescriptor(callBack, labelProvider, contentProvider, "Quick Outline");
        QuickOutlineDescriptor descriptor = new QuickOutlineDescriptor();
        descriptor.addPage(page1);
        return descriptor;
    }

    /**
     * Retrieve the root, avoiding null pointer exceptions.
     * 
     * @param editor
     *            the Sirius editor.
     * @return the root if retrieved, <code>null</code> otherwise.
     */
    private Object getRoot(CustomSiriusEditor editor) {
        Object root = null;
        if (editor != null && editor.getEditingDomain() != null) {
            ResourceSet rs = editor.getEditingDomain().getResourceSet();
            if (rs != null && rs.getResources() != null) {
                Resource resource = rs.getResources().get(0);
                if (resource != null && resource.getContents() != null) {
                    root = resource.getContents().get(0);
                }
            }
        }
        return root;
    }
}
