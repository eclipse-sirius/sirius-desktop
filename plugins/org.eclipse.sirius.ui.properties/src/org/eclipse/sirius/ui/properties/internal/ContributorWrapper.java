/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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

import java.util.regex.Pattern;

import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.eef.properties.ui.api.AbstractEEFTabbedPropertySheetPageContributorWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.eclipse.sirius.properties.core.api.SiriusInputDescriptor;
import org.eclipse.ui.forms.widgets.Form;

/**
 * This wrapper is used because the Sirius editors will not extend the interface
 * from Eclipse EEF.
 * 
 * @author sbegaudeau
 */
public class ContributorWrapper extends AbstractEEFTabbedPropertySheetPageContributorWrapper {

    /**
     * The pattern used to separate a piece of text into an array containing all
     * the lines of the text.
     */
    private static final Pattern LINE_SEPARATOR_PATTERN = Pattern.compile("\r\n|\r|\n|\u2028"); //$NON-NLS-1$

    /**
     * The constructor.
     * 
     * @param realContributor
     *            The real contributor
     * @param contributorId
     *            The contribution id
     */
    public ContributorWrapper(Object realContributor, String contributorId) {
        super(realContributor, contributorId);
    }

    @Override
    public void updateFormTitle(Form form, ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            boolean semanticElementFound = false;
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object element = structuredSelection.getFirstElement();
            if (element != null) {
                SiriusInputDescriptor inputDescriptor = new SiriusInputDescriptor(element);
                EObject semanticElement = inputDescriptor.getSemanticElement();
                if (semanticElement != null) {
                    semanticElementFound = true;
                    updateFormText(form, semanticElement);
                    form.setImage(ExtendedImageRegistry.INSTANCE.getImage(new EditingDomainServices().getLabelProviderImage(semanticElement)));
                }
            }
            if (!semanticElementFound) {
                form.setText(""); //$NON-NLS-1$
                form.setImage(null);
            }
        }
    }

    /**
     * Update the text of the title of the form.
     * 
     * @param form
     *            The form
     * @param semanticElement
     *            The current semantic element
     */
    protected void updateFormText(Form form, EObject semanticElement) {
        String text = new EditingDomainServices().getLabelProviderText(semanticElement);
        if (!Util.isBlank(text)) {
            // Keep only the first line in case of multiline labels
            String[] result = LINE_SEPARATOR_PATTERN.split(text, 2);
            if (result.length >= 1) {
                form.setText(result[0].replace("&", "&&")); //$NON-NLS-1$ //$NON-NLS-2$
            }
        } else {
            form.setText(""); //$NON-NLS-1$
        }
    }

}
