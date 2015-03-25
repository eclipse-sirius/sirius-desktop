/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;

/**
 * @was-generated
 */
public class DNodeListNameEditPart extends AbstractDiagramListNameEditPart implements ITextAwareEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 5007;

    /**
     * @was-generated
     */
    public DNodeListNameEditPart(final View view) {
        super(view);
    }

    @Override
    protected IElementType getParserElementType() {
        return SiriusElementTypes.DNodeList_2003;
    }
}
