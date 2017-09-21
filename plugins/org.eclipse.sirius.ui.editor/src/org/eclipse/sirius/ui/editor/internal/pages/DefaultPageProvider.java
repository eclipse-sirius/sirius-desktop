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

package org.eclipse.sirius.ui.editor.internal.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.SessionEditorPlugin;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;

/**
 * The page provider providing the default page of the aird editor.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DefaultPageProvider extends PageProvider {

    @Override
    public Map<String, Supplier<AbstractSessionEditorPage>> getPages(SessionEditor editor) {
        Map<String, Supplier<AbstractSessionEditorPage>> resultMap = new HashMap<>();
        resultMap.put(SessionEditorPlugin.DEFAULT_PAGE_ID, () -> {
            return new DefaultSessionEditorPage(editor);
        });
        return resultMap;
    }

    @Override
    public boolean provides(String pageId) {
        return SessionEditorPlugin.DEFAULT_PAGE_ID.equals(pageId);
    }
}
