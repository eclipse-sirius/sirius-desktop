/*******************************************************************************
 * Copyright (c) 2008, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.tools.internal.perspectives;

import org.eclipse.sirius.editor.tools.api.perspectives.DesignPerspective;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.ui.tools.internal.wizards.ModelingProjectWizard;
import org.eclipse.sirius.ui.tools.internal.wizards.NewSessionWizard;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * A perspective for design.
 * 
 * @author mchauvin
 */
public class DesignerDesignPerspective implements IPerspectiveFactory, DesignPerspective {
    
    private static final String ORG_ECLIPSE_SIRIUS_UI_INTERPRETER_VIEW = "org.eclipse.sirius.ui.interpreter.view";

    /**
     * {@inheritDoc}
     */
    public void createInitialLayout(final IPageLayout layout) {
        defineActions(layout);
        defineLayout(layout);
    }

    /**
     * add items and actions set to the window
     * 
     * @param layout
     *            layout of the perspective
     */
    private void defineActions(final IPageLayout layout) {
        // wizards
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder"); //$NON-NLS-1$
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file"); //$NON-NLS-1$
        layout.addNewWizardShortcut(ModelingProjectWizard.ID);
        layout.addNewWizardShortcut(NewSessionWizard.ID);

        // show view shortcuts
        layout.addShowViewShortcut(IModelExplorerView.ID);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut(getInterpreterViewID());
    }

    /**
     * add views to the layout
     * 
     * @param layout
     *            layout of the perspective
     */
    private void defineLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();
        layout.addView(IModelExplorerView.ID, IPageLayout.LEFT, (float) 0.25, editorArea);
        layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.BOTTOM, 0.50f, IModelExplorerView.ID);

        // Place problem, properties and advance views to bottom of editor area.
        final IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.65, editorArea);
        bottom.addView(IPageLayout.ID_PROP_SHEET);
        bottom.addView(getInterpreterViewID());
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
    }

    private String getInterpreterViewID() {
        return ORG_ECLIPSE_SIRIUS_UI_INTERPRETER_VIEW;
    }
}
