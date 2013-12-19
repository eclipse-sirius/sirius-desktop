/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;

/**
 * Abstract {@link TreeViewer}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractDTreeViewer extends TreeViewer {

    /**
     * Default constructor.
     * 
     * @param composite
     *            the parent {@link Composite}
     * @param style
     *            the style of this {@link TreeViewer}
     */
    public AbstractDTreeViewer(Composite composite, int style) {
        super(composite, style);
    }

    /**
     * To call to recompute label/image of specified {@link Item} and its
     * children.
     * 
     * @param item
     *            the {@link Item} to recompute
     * 
     * @param dRepresentationElement
     *            its corresponding Widget#getData()
     */
    public void refreshItem(final Item item, final DRepresentationElement dRepresentationElement) {
        preservingSelection(new Runnable() {
            public void run() {
                AbstractDTreeViewer.this.internalRefresh(item, dRepresentationElement, false, true);
            }
        });
    }
}
