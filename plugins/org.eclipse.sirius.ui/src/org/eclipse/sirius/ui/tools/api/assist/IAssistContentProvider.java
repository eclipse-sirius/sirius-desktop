/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.assist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * Interface of the extension point for the assist content.
 * 
 * @author cbrun
 */

public interface IAssistContentProvider extends IContentProposalProvider, IContentProposalListener2 {
    /**
     * The ID.
     */
    String ID = "org.eclipse.sirius.ui.assistContentProvider"; //$NON-NLS-1$

    /**
     * The class attribute.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * The auto activation characters for completion proposal.
     */
    char[] AUTO_ACTIVATION_CHARACTERS = new char[] {};

    /**
     * Set the context with a view. It will be used to get the element and its
     * feature to consider.
     * 
     * @param view
     *            view to set.
     */
    void setView(AbstractPropertySection view);

    /**
     * Set the context with an element and its feature to consider.
     * 
     * @param element
     *            the element to consider.
     * @param feature
     *            the feature to consider.
     * @since 0.9.0
     */
    void initContext(EObject element, EStructuralFeature feature);

}
