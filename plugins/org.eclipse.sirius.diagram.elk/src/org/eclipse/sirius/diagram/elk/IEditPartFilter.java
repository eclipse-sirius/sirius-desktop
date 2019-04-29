/*******************************************************************************
 * Copyright (c) 2016 Kiel University and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;

import com.google.inject.ImplementedBy;

/**
 * Interface for edit part filters. Use this to exclude certain diagram parts
 * from automatic layout.
 * 
 * Copied from org.eclipse.elk.conn.gmf.IEditPartFilter of commit
 * e99248e44c71a5a02fe45bc4cd5150cd7f50c559.
 */
@ImplementedBy(IEditPartFilter.DefaultImpl.class)
public interface IEditPartFilter {

    /**
     * Whether to accept the given edit part and include it in the layout graph.
     */
    boolean filter(EditPart editPart);

    /**
     * This implementation includes all edit parts (returns always
     * {@code true}).
     */
    class DefaultImpl implements IEditPartFilter {

        @Override
        public boolean filter(final EditPart editPart) {
            return !(editPart instanceof IStyleEditPart);
        }

    }

}
