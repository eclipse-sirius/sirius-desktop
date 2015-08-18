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
import org.eclipse.sirius.common.ui.tools.api.selection.page.EObjectSelectionWizardPage;

/**
 * A generic wizard to select an EObject.
 * 
 * @author mchauvin
 */
public class EObjectSelectionWizard extends Wizard {

    /** "Selection Wizard". */
    public static final String WIZARD_GENERIC_DIALOG_TITLE = "Selection Wizard";

    /** "Please select an element". */
    public static final String WIZARD_GENERIC_DIALOG_MESSAGE = "Please select an element";

    private static final String EOBJECT_SELECTION_WIZARD_PAGE_NAME = "page"; //$NON-NLS-1$

    private EObjectSelectionWizardPage page;

    /**
     * Default constructor.
     * 
     * @param windowTitle
     *            the window title,
     *            {@link EObjectSelectionWizard#WIZARD_GENERIC_DIALOG_TITLE} can
     *            be used
     * @param wizardPageTitle
     *            the dialog message,
     *            {@link EObjectSelectionWizard#WIZARD_GENERIC_DIALOG_MESSAGE}
     *            can be used
     * @param wizardPageTitleImage
     *            the title image
     * @param objects
     *            the list of objects as input
     * @param factory
     *            the adapter factory to provides labels and icons for the
     *            objects
     */
    public EObjectSelectionWizard(final String windowTitle, final String wizardPageTitle, final ImageDescriptor wizardPageTitleImage, final Collection<? extends EObject> objects, final AdapterFactory factory) {
        setWindowTitle(windowTitle);
        page = new EObjectSelectionWizardPage(EOBJECT_SELECTION_WIZARD_PAGE_NAME, wizardPageTitle, wizardPageTitleImage, objects, factory);
        addPage(page);
    }

    /**
     * Constructor with a tree as input.
     * 
     * @param windowTitle
     *            the window title,
     *            {@link EObjectSelectionWizard#WIZARD_GENERIC_DIALOG_TITLE} can
     *            be used
     * @param wizardPageTitle
     *            the dialog message,
     *            {@link EObjectSelectionWizard#WIZARD_GENERIC_DIALOG_MESSAGE}
     *            can be used
     * @param wizardPageTitleImage
     *            the title image
     * @param treeObjects
     *            the tree of objects as input
     * @param factory
     *            the adapter factory to provides labels and icons for the
     *            objects
     */
    public EObjectSelectionWizard(final String windowTitle, final String wizardPageTitle, final ImageDescriptor wizardPageTitleImage, final TreeItemWrapper treeObjects, final AdapterFactory factory) {
        setWindowTitle(windowTitle);
        page = new EObjectSelectionWizardPage(EOBJECT_SELECTION_WIZARD_PAGE_NAME, wizardPageTitle, wizardPageTitleImage, treeObjects, factory);
        addPage(page);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return true;
    }

    /**
     * Set if multiple selection is allowed.
     * 
     * @param many
     *            true if multiple selection is allowed, false otherwise.
     */
    public void setMany(final boolean many) {
        this.page.setMany(many);
    }

    /**
     * Get the selected EObject instance.
     * 
     * @return the selected EObject
     */
    public EObject getSelectedEObject() {
        return page.getSelectedEObject();
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
