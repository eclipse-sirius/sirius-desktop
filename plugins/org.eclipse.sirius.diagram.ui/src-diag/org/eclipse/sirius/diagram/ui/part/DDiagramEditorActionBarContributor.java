/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.diagram.ui.part;

import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;

/**
 * Specific ActionBarContributor for {@link DDiagramEditorImpl} instance to avoid potential leaks on actions for action
 * bar of this editor.
 * 
 * @author Laurent Redor
 */
public class DDiagramEditorActionBarContributor extends SiriusDiagramActionBarContributor {
    @Override
    protected Class<?> getEditorClass() {
        return DDiagramEditorImpl.class;
    }
}
