/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog to select an image from workspace.
 * 
 * @author fmorel
 */
public class SelectDiagramElementBackgroundImageDialog extends Dialog {

    private WorkspaceImage workspaceImageForWorkspace;

    private Label workspacePathLabel;

    private Text workspacePathText;

    private Button browseButton;

    /**
     * Creates an instance of the copy to image dialog.
     * 
     * @param shell
     *            the parent shell
     * @param basicLabelStyle
     *            {@link BasicLabelStyle}
     */
    public SelectDiagramElementBackgroundImageDialog(Shell shell, EObject basicLabelStyle) {
        super(shell);
        this.workspaceImageForWorkspace = DiagramFactory.eINSTANCE.createWorkspaceImage();
        if (basicLabelStyle instanceof WorkspaceImage) {
            WorkspaceImage workspaceImage = (WorkspaceImage) basicLabelStyle;
            workspaceImageForWorkspace.setWorkspacePath(workspaceImage.getWorkspacePath());
        }
    }

    /**
     * Creates and returns the contents of the upper part of this dialog (above the button bar). {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite mainComposite = (Composite) super.createDialogArea(parent);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.marginHeight = 5;
        gridLayout.marginWidth = 5;
        mainComposite.setLayout(gridLayout);
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

        createFromWorkspaceComposite(mainComposite);
        return mainComposite;
    }

    private void createFromWorkspaceComposite(Composite mainComposite) {
        Composite fromWorkspaceComposite = new Composite(mainComposite, SWT.NONE);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        fromWorkspaceComposite.setLayout(gridLayout);
        fromWorkspaceComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

        workspacePathLabel = new Label(fromWorkspaceComposite, SWT.NONE);
        workspacePathLabel.setText(Messages.SelectDiagramElementBackgroundImageDialog_pathLabelText);

        workspacePathText = new Text(fromWorkspaceComposite, SWT.FLAT | SWT.BORDER);
        workspacePathText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        ControlDecoration controlDecoration = new ControlDecoration(workspacePathText, SWT.LEFT | SWT.TOP);
        FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
        controlDecoration.setImage(fieldDecoration.getImage());

        ISWTObservableValue workspacePathTextObservable = WidgetProperties.text(SWT.Modify).observe(workspacePathText);
        IObservableValue workspaceImageObservable = EMFProperties.value(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH).observe(workspaceImageForWorkspace);
        DataBindingContext ctx = new DataBindingContext();
        WorkspacePathValidator workspacePathValidator = new WorkspacePathValidator(controlDecoration);
        ctx.bindValue(workspacePathTextObservable, workspaceImageObservable, new UpdateValueStrategy().setAfterConvertValidator(workspacePathValidator), null);

        browseButton = new Button(fromWorkspaceComposite, SWT.NONE);
        browseButton.setText(Messages.SelectDiagramElementBackgroundImageDialog_browseButtonText);
        browseButton.addSelectionListener(new WorkspaceImagePathSelector(workspacePathText));
    }

    /**
     * Configures the shell in preparation for opening this window in it. {@inheritDoc}
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.SelectDiagramElementBackgroundImageDialog_title);
        shell.setMinimumSize(500, 100);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
    }

    /**
     * return the image path.
     * 
     * @return The image path
     */
    public String getImagePath() {
        return workspaceImageForWorkspace.getWorkspacePath();
    }
}
