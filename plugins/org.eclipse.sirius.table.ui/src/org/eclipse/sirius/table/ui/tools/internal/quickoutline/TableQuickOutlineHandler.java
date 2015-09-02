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
package org.eclipse.sirius.table.ui.tools.internal.quickoutline;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlineAdapterFactoryLabelProvider;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlineDescriptor;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.QuickOutlinePageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.SiriusInformationPresenter;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.SiriusInformationPresenterFactory;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Handler for the quick outline of the Table editor.
 *
 * @author ymortier
 */
public class TableQuickOutlineHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        DTableEditor editor = getCurrentEditor();
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
     *            the table editor.
     * @return the quick outline descriptor.
     */
    private QuickOutlineDescriptor createDescriptor(DTableEditor editor) {
        ComposedAdapterFactory caf = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        QuickOutlineAdapterFactoryLabelProvider labelProvider = new DTableQuickOutlineLabelProvider(caf);

        TableOutlineCallback callBack = new TableOutlineCallback(editor);

        QuickOutlinePageDescriptor page = new QuickOutlinePageDescriptor(callBack, labelProvider, new DLineQuickOutlineContentProvider(), Messages.TableQuickOutlineHandler_selectLine);

        QuickOutlinePageDescriptor columnsPage = new QuickOutlinePageDescriptor(callBack, labelProvider, new DColumnOutlineContentProvider(), Messages.TableQuickOutlineHandler_selectColumn);

        QuickOutlineDescriptor descriptor = new QuickOutlineDescriptor();
        descriptor.addPage(page);
        descriptor.addPage(columnsPage);

        return descriptor;
    }

    /**
     * Returns the currently active editor if it's a {@link DTableEditor}.
     *
     * @return The currently active editor if it's a {@link DTableEditor},
     *         <code>null</code> otherwise.
     */
    private DTableEditor getCurrentEditor() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null && window.getActivePage() != null && window.getActivePage().getActiveEditor() instanceof DTableEditor) {
            return (DTableEditor) window.getActivePage().getActiveEditor();
        }
        return null;
    }

}
