/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.selection.page;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;

/**
 * An abstract class to extend to provide selection.
 * 
 * @author mchauvin
 */
public abstract class AbstractSelectionWizardPage extends WizardPage {

    private static final String MESSAGE_FILTER_ELEMENTS = "Filter elements";

    private static final String LABEL_FILTER = "Enter prefix or pattern (? = any character, * = any String): ";

    /** The filter of the list. */
    protected final EObjectSelectionFilter myViewerfilter;

    /** The text to set the filter. */
    protected Text elementsToSelectText;

    /**
     * Constructor.
     * 
     * @param pageName
     *            the name of the page
     * @param title
     *            the title for this wizard page, or <code>null</code> if none
     * @param titleImage
     *            the image descriptor for the title of this wizard page, or
     *            <code>null</code> if none
     */
    public AbstractSelectionWizardPage(final String pageName, final String title, final ImageDescriptor titleImage) {
        super(pageName, title, titleImage);
        this.myViewerfilter = new EObjectSelectionFilter();
    }

    /**
     * Create a selection group.
     * 
     * @param parent
     *            the parent
     * @return the created composite
     */
    protected Composite createSelectionGroup(final Composite parent) {
        final Composite composite = SWTUtil.createCompositeHorizontalFill(parent, 1, false);
        SWTUtil.createLabel(composite, LABEL_FILTER);
        elementsToSelectText = new Text(composite, SWT.BORDER | SWT.SEARCH);
        elementsToSelectText.setMessage(MESSAGE_FILTER_ELEMENTS);
        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        elementsToSelectText.setLayoutData(gridData);
        elementsToSelectText.addModifyListener(new EObjectSelectionModifyAdapter());

        return composite;

    }

    /**
     * Modification adapter.
     * 
     * @author mchauvin
     */
    protected class EObjectSelectionModifyAdapter implements ModifyListener {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
         */
        public void modifyText(final ModifyEvent e) {
            myViewerfilter.setPrefix(((Text) e.widget).getText());
        }
    }
}
