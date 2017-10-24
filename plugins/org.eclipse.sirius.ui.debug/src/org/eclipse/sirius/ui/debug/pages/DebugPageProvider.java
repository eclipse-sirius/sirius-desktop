/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.ui.debug.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;

/**
 * Provides a custom debug page for aird editor showing different information about the session and its elements.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DebugPageProvider extends PageProvider {

    private static final String DEBUG_PAGE_TITLE = "Debug";

    @Override
    public Map<String, Supplier<AbstractSessionEditorPage>> getPages(SessionEditor editor) {
        Map<String, Supplier<AbstractSessionEditorPage>> resultMap = new HashMap<>();
        resultMap.put(DebugPage.PAGE_ID, () -> {
            return new DebugPage(editor, DebugPage.PAGE_ID, DEBUG_PAGE_TITLE);
        });
        return resultMap;
    }

    @Override
    public boolean provides(String pageId, SessionEditor editor) {
        return DebugPage.PAGE_ID.equals(pageId);
    }
}
