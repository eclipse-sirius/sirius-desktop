/*******************************************************************************
 * Copyright (c) 2008, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.util.List;

import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This implementation is used to create the structure viewer's "Create Line"
 * action.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EditorCreateTargetColumnMenuAction extends AbstractEditorCreateMenuAction<CreateTargetColumnAction> {
    /** The Id of this action. */
    public static final String ID = "CreateTargetColumnMenu"; //$NON-NLS-1$

    /**
     * This default constructor will instantiate an action given the
     * {@link #BUNDLE bundle} resources prefixed by &quot;action.save&quot;.
     */
    public EditorCreateTargetColumnMenuAction() {
        super(Messages.EditorCreateTargetColumnMenuAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.CREATE_COLUMN));
        setId(ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<CreateTargetColumnAction> filter(List<AbstractToolAction<?>> createActionsForTable) {
        return Lists.newArrayList(Iterables.filter(createActionsForTable, CreateTargetColumnAction.class));
    }
}
