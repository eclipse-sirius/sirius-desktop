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
package org.eclipse.sirius.ui.editor.api.pages;

import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * This component provides instances of {@link AbstractSessionEditorPage} that
 * are pages identified by the same id used by a {@link PageProvider} instance.
 * This pages created by this component can be used in any different
 * {@link MultiPageEditorPart}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public abstract class PageProvider {
    /**
     * Returns a map of page id to the @link Supplier} initializing the
     * corresponding page {@link AbstractSessionEditorPage} to display in a
     * multi-page editor.
     * 
     * @param editor
     *            the aird editor's session from which page request is done.
     * 
     * @return a map of page id to the @link Supplier} initializing the
     *         corresponding page {@link AbstractSessionEditorPage} to display
     *         in a multi-page editor.
     */
    public abstract Map<String, Supplier<AbstractSessionEditorPage>> getPages(SessionEditor editor);
}
