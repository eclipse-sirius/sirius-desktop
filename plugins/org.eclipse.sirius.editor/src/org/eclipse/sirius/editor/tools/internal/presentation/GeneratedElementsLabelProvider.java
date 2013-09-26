/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEditManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Label provider highlighting generated model elements with a specific color.
 * 
 * @author cbrun
 * 
 */
public class GeneratedElementsLabelProvider extends DecoratingLabelProvider {

    private final Color generated = new Color(Display.getDefault(), 136, 138, 133);

    /**
     * Create the provider.
     * 
     * @param provider
     *            wrapped label provider.
     * @param decorator
     *            decorator for the labels.
     */
    public GeneratedElementsLabelProvider(ILabelProvider provider, ILabelDecorator decorator) {
        super(provider, decorator);
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
