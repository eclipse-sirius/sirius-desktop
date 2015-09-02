/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * Resize a view of a delta.
 * 
 * @author edugueperoux
 */
public class ResizeViewOperation extends AbstractModelChangeOperation<Void> {

    private IAdaptable adapter;

    private Dimension sizeDelta;

    /**
     * Default constructor.
     * 
     * @param label
     *            label to display in the undo menu item
     * @param adapter
     *            adapter for the view
     * @param sizeDelta
     *            delta to resize the view
     */
    public ResizeViewOperation(String label, IAdaptable adapter, Dimension sizeDelta) {
        super(label);
        Assert.isNotNull(adapter, Messages.ResizeViewOperation_invalidNullView); 
        Assert.isNotNull(sizeDelta, Messages.ResizeViewOperation_invalidNullSizeDelta); 
        this.adapter = adapter;
        this.sizeDelta = sizeDelta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        if (adapter != null) {
            View view = (View) adapter.getAdapter(View.class);
            if (sizeDelta != null) {
                if (view instanceof Node) {
                    Node node = (Node) view;
                    LayoutConstraint constraint = node.getLayoutConstraint();
                    if (constraint != null && NotationPackage.eINSTANCE.getSize().isInstance(constraint)) {
                        Size size = (Size) constraint;
                        size.setWidth(size.getWidth() + sizeDelta.width);
                        size.setHeight(size.getHeight() + sizeDelta.height);
                    }
                }
            }
        }
        return null;
    }

}
