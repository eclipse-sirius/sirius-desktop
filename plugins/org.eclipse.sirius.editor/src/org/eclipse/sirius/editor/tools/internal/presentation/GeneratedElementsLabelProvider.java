/*******************************************************************************
 * Copyright (c) 2009, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.tools.internal.presentation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEditManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Label provider highlighting generated model elements with a specific color.
 * 
 * @author cbrun
 * 
 */
public class GeneratedElementsLabelProvider extends DecoratingStyledCellLabelProvider {

    private final Color generated = new Color(Display.getDefault(), 136, 138, 133);

    /**
     * Create the provider.
     * 
     * @param provider
     *            wrapped label provider.
     * @param decorator
     *            decorator for the labels.
     */
    public GeneratedElementsLabelProvider(IStyledLabelProvider provider, ILabelDecorator decorator) {
        super(provider, decorator, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getForeground(Object element) {
        if (element instanceof EObject) {
            if (RepresentationTemplateEditManager.INSTANCE.isGenerated((EObject) element)) {
                return generated;
            }
        }
        return super.getForeground(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        generated.dispose();
    }
}
