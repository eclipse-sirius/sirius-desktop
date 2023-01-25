/*******************************************************************************
 * Copyright (c) 2023 Obeo.
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
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.eef.properties.ui.api.AbstractEEFTabbedPropertySheetPageContributorWrapper;
import org.eclipse.eef.properties.ui.api.EEFTabbedPropertySheetPage;
import org.eclipse.sirius.tools.api.command.EditingDomainUndoContext;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;

/**
 * This Property sheet page provides the tabbed UI for Sirius with EEF properties view.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class SiriusEEFTabbedPropertySheetPage extends EEFTabbedPropertySheetPage {

    /**
     * The object wrapping the contributor.
     */
    private AbstractEEFTabbedPropertySheetPageContributorWrapper contributorWrapper;

    /**
     * Constructor.
     *
     * @param contributorWrapper
     *            the object wrapping the contributor.
     */
    public SiriusEEFTabbedPropertySheetPage(AbstractEEFTabbedPropertySheetPageContributorWrapper contributorWrapper) {
        super(contributorWrapper);
        this.contributorWrapper = contributorWrapper;
    }

    /**
     * Overridden to handle undo/redo actions.
     * 
     * @see org.eclipse.eef.properties.ui.api.EEFTabbedPropertySheetPage#setActionBars(org.eclipse.ui.IActionBars)
     *
     * @param actionBars
     *            the action bars for this page
     */
    @Override
    public void setActionBars(IActionBars actionBars) {
        Object realContributor = contributorWrapper.getRealContributor();
        if (realContributor instanceof DialectEditor) {
            DialectEditor editor = (DialectEditor) realContributor;

            EditingDomainUndoContext undoContext = new EditingDomainUndoContext(editor.getEditingDomain());
            UndoActionHandler undo = new UndoActionHandler(editor.getSite(), undoContext);
            RedoActionHandler redo = new RedoActionHandler(editor.getSite(), undoContext);

            actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undo);
            actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redo);
        }
    }
}
