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
package org.eclipse.sirius.tree.ui.tools.internal.quickoutline;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlineAdapterFactoryLabelProvider;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlineDescriptor;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlinePageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.SiriusInformationPresenter;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.SiriusInformationPresenterFactory;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Handler for the quick outline of the Tree editor.
 * 
 * @author ymortier
 */
public class TreeQuickOutlineHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        DTreeEditor editor = getCurrentEditor();
        if (editor != null) {
            QuickOutlineDescriptor descriptor = createDescriptor(editor);

            SiriusInformationPresenter presenter = SiriusInformationPresenterFactory.createInformationPresenter(editor.getControl(), descriptor, editor.getRepresentation());
            presenter.showInformation();
        }
        return null;
    }

    /**
     * Create the quick outline descriptor.
     * 
     * @param editor
     *            the tree editor.
     * @return the quick outline descriptor.
     */
    private QuickOutlineDescriptor createDescriptor(DTreeEditor editor) {
        ComposedAdapterFactory caf = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        QuickOutlineAdapterFactoryLabelProvider labelProvider = new DTreeQuickOutlineLabelProvider(caf);
        TreeOutlineCallback callBack = new TreeOutlineCallback(editor);
        QuickOutlinePageDescriptor page1 = new QuickOutlinePageDescriptor(callBack, labelProvider, new DTreeQuickOutlineContentProvider(), Messages.TreeQuickOutlineHandler_quickOutline);
        QuickOutlineDescriptor descriptor = new QuickOutlineDescriptor();
        descriptor.addPage(page1);
        return descriptor;
    }

    private DTreeEditor getCurrentEditor() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null && window.getActivePage() != null && window.getActivePage().getActiveEditor() instanceof DTreeEditor) {
            return (DTreeEditor) window.getActivePage().getActiveEditor();
        }
        return null;
    }

}
