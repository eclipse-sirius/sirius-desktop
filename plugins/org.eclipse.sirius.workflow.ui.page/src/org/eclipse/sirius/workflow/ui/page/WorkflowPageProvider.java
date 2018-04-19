/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.workflow.ui.page;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;

/**
 * Provides a {@link WorkflowPage} for the session editor.
 * 
 * @author pcdavid
 */
public class WorkflowPageProvider extends PageProvider {
    /**
     * Prefix used to identify the pages.
     */
    private static final String PREFIX = "org.eclipse.sirius.workflow:"; //$NON-NLS-1$

    @Override
    public Map<String, Supplier<AbstractSessionEditorPage>> getPages(SessionEditor editor) {
        Session s = editor.getSession();
        Map<String, Supplier<AbstractSessionEditorPage>> result = new HashMap<>();
        result.put(PREFIX + s.getID(), () -> new WorkflowPage(s));
        return result;
    }

    @Override
    public boolean provides(String pageId) {
        if (pageId.startsWith(PREFIX)) {
            String sid = pageId.substring(PREFIX.length());
            return SessionManager.INSTANCE.getSessions().stream().anyMatch(s -> s.getID().equals(sid));
        }
        return false;
    }
}
