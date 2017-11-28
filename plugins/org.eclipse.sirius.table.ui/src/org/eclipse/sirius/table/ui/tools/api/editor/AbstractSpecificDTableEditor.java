/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.api.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.ui.business.api.editor.SpecificEditor;
import org.eclipse.sirius.ui.business.api.editor.SpecificEditorInputTranformer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;

/**
 * A specific editor you may extend, which includes a session. The session
 * lifecycle is directly linked the the editor. When the editor opens, it
 * automatically creates a session. When the editor closes, it automatically
 * closes the session.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public abstract class AbstractSpecificDTableEditor extends AbstractDTableEditor implements SpecificEditor {

    private SpecificEditorInputTranformer util = new SpecificEditorInputTranformer();

    @Override
    public void dispose() {
        super.dispose();
        if (util != null) {
            util.cleanEnvironment();
            util = null;
        }
    }

    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        util.init(getViewpointURI(), getDiagramDescriptionName());
        super.init(site, util.transformInput(input, getSelection(site), isSessionStoredInWorkspace()));
    }

    @Override
    public void setInput(IEditorInput input) {
        super.setInput(util.transformInput(input, getSelection(getSite()), isSessionStoredInWorkspace()));
    }

    private ISelection getSelection(IWorkbenchPartSite site) {
        return site.getWorkbenchWindow().getSelectionService().getSelection();
    }

}
