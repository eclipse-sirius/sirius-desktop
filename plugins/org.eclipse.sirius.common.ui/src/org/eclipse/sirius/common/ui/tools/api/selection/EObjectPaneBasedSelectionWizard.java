/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.selection;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;

import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.page.EObjectPaneBasedSelectionWizardPage;

/**
 * A generic pane based wizard to select an EObject.
 * 
 * @author mporhel
 */
public class EObjectPaneBasedSelectionWizard extends Wizard {

    /** "Selection Wizard". */
    public static final String WIZARD_GENERIC_DIALOG_TITLE = "Selection Wizard";

    /** "Choice of values". */
    public static final String WIZARD_GENERIC_CHOICE_OF_VALUES_MESSAGE = "Choice of values";

    /** "Selected". */
    public static final String WIZARD_GENERIC_SELECTED_VALUES_MESSAGE = "Selected values";

    /** "Please select an element". */
    public static final String WIZARD_GENERIC_DIALOG_MESSAGE = "Please select an element";

    private static final String EOBJECT_PANE_BASED_SELECTION_WIZARD_PAGE_NAME = "page"; //$NON-NLS-1$

    private EObjectPaneBasedSelectionWizardPage page;

    /**
     * Default constructor.
     * 
     * @param windowTitle
     *            the window title,
     *            {@link EObjectPaneBasedSelectionWizard#WIZARD_GENERIC_DIALOG_TITLE}
     *            can be used
     * @param wizardPageTitle
     *            the dialog message,
     *            {@link EObjectPaneBasedSelectionWizard#WIZARD_GENERIC_DIALOG_MESSAGE}
     *            can be used
     * @param wizardPageTitleImage
     *            the title image
     * @param choiceOfValuesMessage
     *            the dialog message,
     *            {@link EObjectPaneBasedSelectionWizard#WIZARD_GENERIC_LEFT_PANE_MESSAGE}
     *            can be used
     * @param selectedValuesMessage
     *            the dialog message,
     *            {@link EObjectPaneBasedSelectionWizard#WIZARD_GENERIC_RIGHT_PANE_MESSAGE}
     *            can be used
     * @param factory
     *            the adapter factory to provides labels and icons for the
     *            objects
     */
    public EObjectPaneBasedSelectionWizard(final String windowTitle, final String wizardPageTitle, final ImageDescriptor wizardPageTitleImage, final String choiceOfValuesMessage,
            final String selectedValuesMessage, final AdapterFactory factory) {
        setWindowTitle(windowTitle);
        page = new EObjectPaneBasedSelectionWizardPage(EOBJECT_PANE_BASED_SELECTION_WIZARD_PAGE_NAME, wizardPageTitle, wizardPageTitleImage, choiceOfValuesMessage, selectedValuesMessage, factory);
        addPage(page);
    }

    /**
     * Initializer with a tree as input.
     * 
     * @param treeObjects
     *            the tree of objects as input
     * @param preSelectedObjects
     *            the pre-selected objects
     */
    public void init(final TreeItemWrapper treeObjects, final Collection<? extends EObject> preSelectedObjects) {
        page.init(treeObjects, preSelectedObjects);
    }

    /**
     * Initializer.
     * 
     * @param objects
     *            the candidates objects
     * @param preSelectedObjects
     *            the pre-selected objects
     */
    public void init(final Collection<? extends EObject> objects, final Collection<? extends EObject> preSelectedObjects) {
        page.init(objects, preSelectedObjects);
    }

    @Override
    public boolean performFinish() {
        return true;
    }

    /**
     * Get the selected EObject instances.
     * 
     * @return a list of the selected {@link EObject}.
     */
    public Collection<EObject> getSelectedEObjects() {
        return page.getSelectedEObjects();
    }

}
