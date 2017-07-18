/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    public boolean provides(String pageId) {
        return DebugPage.PAGE_ID.equals(pageId);
    }
}
