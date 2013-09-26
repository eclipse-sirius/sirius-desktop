/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.actions;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Action for validation delegation.
 * 
 * @author cnotot
 * 
 */
public class ValidateAction extends Action {

    /**
     * PLUGIN_ID.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.editor"; //$NON-NLS-1$

    /**
     * ID of the action.
     */
    public static final String ID = "ValidateActionFromEditor"; //$NON-NLS-1$

    /**
     * TEXT of the action.
     */
    public static final String TEXT = "Validate"; //$NON-NLS-1$

    /**
     * TOOL_TIP_TEXT of the action.
     */
    public static final String TOOL_TIP_TEXT = "Validate Model"; //$NON-NLS-1$

    /**
     * Default image descriptor for the action.
     */
    public static final ImageDescriptor DEFAULT_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, "/icons/full/complete_task.gif"); //$NON-NLS-1$

    /**
     * Image descriptor for the action when it is put on relief.
     */
    public static final ImageDescriptor HIGHLIGHT_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, "/icons/full/hcomplete_task.gif"); //$NON-NLS-1$

    private org.eclipse.emf.edit.ui.action.ValidateAction action = new org.eclipse.emf.edit.ui.action.ValidateAction();

    private TreeViewer treeViewer;

    private SiriusEditor editor;

    /**
     * Constructor.
     * 
     * @param treeViewer
     *            The TreeViewer concerned by the action.
     * @param editor
     *            The editor concerned by the action.
     */
    public ValidateAction(TreeViewer treeViewer, SiriusEditor editor) {
        super();
        setImageDescriptor(DEFAULT_DESC);
        setText(TEXT);
        setToolTipText(TOOL_TIP_TEXT);
        setId(ID);
        this.treeViewer = treeViewer;
        this.editor = editor;
    }

    /**
     * (non-Javadoc).
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        IStructuredSelection selection = new StructuredSelection(((XMIResource) treeViewer.getTree().getItem(0).getData()).getContents().get(0));
        action.updateSelection(selection);
        action.setActiveWorkbenchPart(editor);
        action.run();
        setImageDescriptor(DEFAULT_DESC);
    }
}
