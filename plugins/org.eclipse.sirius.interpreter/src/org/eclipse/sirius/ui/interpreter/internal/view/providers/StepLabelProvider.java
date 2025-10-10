/*******************************************************************************
 * Copyright (c) 2013, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.providers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.ui.interpreter.internal.language.SplitExpression;
import org.eclipse.sirius.ui.interpreter.internal.language.SubExpression;

/**
 * This label provider displays content of an expression.<br />
 * Provider used in the left tree viewer of the evaluator view.
 * 
 * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
 */
public class StepLabelProvider extends LabelProvider {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object) {
        final String label;
        if (object instanceof SplitExpression) {
            label = ((SplitExpression) object).getLabel();
        } else if (object instanceof SubExpression) {
            label = ((SubExpression) object).getLabel();
        } else {
            label = super.getText(object);
        }
        return label;
    }
}
