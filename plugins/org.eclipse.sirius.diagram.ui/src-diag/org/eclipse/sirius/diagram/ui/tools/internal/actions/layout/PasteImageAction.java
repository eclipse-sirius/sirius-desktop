/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.awt.Toolkit;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.image.ImageManager.CreateImageFileProvider;
import org.eclipse.sirius.business.api.image.ImageManagerProvider;
import org.eclipse.sirius.diagram.ui.business.api.image.WorkspaceImageHelper;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.SetStyleToWorkspaceImageAction;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

/**
 * This action provides past image from the clipboard on Sirius elements that can have workspace image style. This
 * action create image in image forder of project from clipboard and then set as workspace image on all selected nodes.
 * 
 * @author Séraphin Costa
 */
public class PasteImageAction extends Action {

    // There are no clipboard listener in SWT, so we need to use the AWT clipboard listener.
    java.awt.datatransfer.Clipboard awtClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    class ClipboardListener implements FlavorListener {
        @Override
        public void flavorsChanged(FlavorEvent e) {
            // This is an AWT event, not an SWT event,
            // so we are not in the thread UI and we need to execute this code in thread UI.
            Display.getDefault().syncExec(() -> {
                updateState();
            });
        }
    }

    /**
     * Default constructor.
     */
    public PasteImageAction() {
        super();

        setId(ActionIds.PASTE_IMAGE);
        setText(Messages.PasteImageAction_text);
        setToolTipText(Messages.PasteImageAction_toolTipText);
        // the keyboard shortcut work only on Windows
        if ("win32".equals(SWT.getPlatform())) { //$NON-NLS-1$
            setAccelerator(SWT.CTRL | SWT.ALT | 'V');
        } else {
            setAccelerator(0); // 0 for no shortcut
        }

        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_IMAGE_ICON));

        updateState();

        // listen clipboard for update state
        awtClipboard.addFlavorListener(new ClipboardListener());
    }

    /**
     * Update the state of this action.
     * 
     * Updates the active state of the action according to the environment.
     * 
     * This method must be executed in the UI thread.
     */
    public void updateState() {
        setEnabled(canBeEnabled());
    }

    @Override
    public void run() {
        List<BasicLabelStyle> selectedStyles = SetStyleToWorkspaceImageAction.getStyles();
        ImageData imgData = fetchImageFromClipboard();

        // we check if the clipboard contains image and we have at least one element selected
        if (imgData != null && !selectedStyles.isEmpty()) {
            // get the first style to provide the context for file creation and domain for command
            BasicLabelStyle firstStyle = selectedStyles.stream().findFirst().orElseThrow();
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(firstStyle);

            if (domain != null) {
                // prepare image file creation
                String filename = ImageManager.generateName("png"); //$NON-NLS-1$
                ImageManager imageManager = ImageManagerProvider.getImageManager();
                try (InputStream imageStream = getImageStream(imgData)) {
                    CreateImageFileProvider createFileFunc = imageManager.getCreateFileFunc(firstStyle, filename, imageStream);

                    // create command
                    CompoundCommand command = new CompoundCommand();
                    command.append(createFileFunc.asRecordingCommand(domain)); // command to create file

                    // commands to change workspace image style of selected elements
                    for (BasicLabelStyle style : selectedStyles) {
                        command.append(WorkspaceImageHelper.INSTANCE.getWorkspacePathChangeCommand(domain, style, createFileFunc.getFileName()));
                    }
                    domain.getCommandStack().execute(command);

                    WorkspaceImageHelper.INSTANCE.refreshStyle();
                } catch (IOException | CoreException e) {
                    SiriusPlugin.getDefault().error(MessageFormat.format(org.eclipse.sirius.tools.api.Messages.ImageManager_imageCreationFailure, filename), e);
                }

            } else {
                SiriusPlugin.getDefault().error(org.eclipse.sirius.tools.api.Messages.PastImage_TransactionalEditingDomainIsNull, null);
            }
        } else {
            SiriusPlugin.getDefault().error(org.eclipse.sirius.tools.api.Messages.PastImage_RunWhenDisabled, null);
        }
    }

    private ImageData fetchImageFromClipboard() {
        ImageTransfer imgTransfer = ImageTransfer.getInstance();
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        ImageData imgData = (ImageData) clipboard.getContents(imgTransfer);
        clipboard.dispose();

        return imgData;
    }

    private ByteArrayInputStream getImageStream(ImageData imgData) {
        ImageLoader saver = new ImageLoader();
        saver.data = new ImageData[] { imgData };
        ByteArrayOutputStream imgStreamOut = new ByteArrayOutputStream();
        saver.save(imgStreamOut, SWT.IMAGE_PNG);
        return new ByteArrayInputStream(imgStreamOut.toByteArray());
    }

    private boolean canBeEnabled() {
        // ImageTransfer.getInstance().isSupportedType does not work,
        // and clipboard.getAvailableTypeNames() is not safe so we get image and check if it is non-null
        ImageData imgData = fetchImageFromClipboard();

        return imgData != null && SetStyleToWorkspaceImageAction.selectionCanHaveWorkspaceImage();
    }

    @Override
    public boolean isEnabled() {
        updateState();
        return super.isEnabled();
    }
}
