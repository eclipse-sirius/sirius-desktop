/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.multipageeditor;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * An example showing how to create a multi-page editor. This example has 3
 * pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class SiriusMultiPageEditor extends MultiPageEditorPart implements IResourceChangeListener {

    /** The text editor used in page 0. */
    private SiriusDiagramEditor editor;

    /** The font chosen in page 1. */
    private Font font;

    /** The text widget used in page 2. */
    private StyledText text;

    /**
     * Creates a multi-page editor example.
     */
    public SiriusMultiPageEditor() {
        super();
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

    /**
     * Creates page 0 of the multi-page editor, which contains a text editor.
     */
    void createPage0() {
        try {
            editor = new DDiagramEditorImpl();
            int index = addPage(editor, getEditorInput());
            setPageText(index, editor.getTitle());
            editor.setInput(getEditorInput());
        } catch (PartInitException e) {
            ErrorDialog.openError(getSite().getShell(), "Error creating nested text editor", null, e.getStatus());
        }
    }

    /**
     * Creates the pages of the multi-page editor.
     */
    @Override
    protected void createPages() {
        createPage0();
    }

    /**
     * The <code>MultiPageEditorPart</code> implementation of this
     * <code>IWorkbenchPart</code> method disposes all nested editors.
     * Subclasses may extend.
     */
    @Override
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
        super.dispose();
    }

    /**
     * Saves the multi-page editor's document.
     * 
     * @param monitor
     *            used to monitor save progress.
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        getEditor(0).doSave(monitor);
    }

    /**
     * Saves the multi-page editor's document as another file. Also updates the
     * text for page 0's tab, and updates this multi-page editor's input to
     * correspond to the nested editor's.
     */
    @Override
    public void doSaveAs() {
        IEditorPart editor = getEditor(0);
        editor.doSaveAs();
        setPageText(0, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /*
     * (non-Javadoc) Method declared on IEditorPart
     */
    public void gotoMarker(IMarker marker) {
        setActivePage(0);
        IDE.gotoMarker(getEditor(0), marker);
    }

    /**
     * The <code>MultiPageEditorExample</code> implementation of this method
     * checks that the input is an instance of <code>IFileEditorInput</code>.
     */
    @Override
    public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
        super.init(site, editorInput);
    }

    /*
     * (non-Javadoc) Method declared on IEditorPart.
     */
    @Override
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * Calculates the contents of page 2 when the it is activated.
     */
    @Override
    protected void pageChange(int newPageIndex) {
        super.pageChange(newPageIndex);
    }

    /**
     * Closes all project files on project close.
     */
    @Override
    public void resourceChanged(final IResourceChangeEvent event) {
        if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
                    for (int i = 0; i < pages.length; i++) {
                        if (((FileEditorInput) editor.getEditorInput()).getFile().getProject().equals(event.getResource())) {
                            IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
                            pages[i].closeEditor(editorPart, true);
                        }
                    }
                }
            });
        }
    }

    /**
     * Sets the font related data to be applied to the text in page 2.
     */
    void setFont() {
        FontDialog fontDialog = new FontDialog(getSite().getShell());
        fontDialog.setFontList(text.getFont().getFontData());
        FontData fontData = fontDialog.open();
        if (fontData != null) {
            if (font != null)
                font.dispose();
            font = new Font(text.getDisplay(), fontData);
            text.setFont(font);
        }
    }

}
