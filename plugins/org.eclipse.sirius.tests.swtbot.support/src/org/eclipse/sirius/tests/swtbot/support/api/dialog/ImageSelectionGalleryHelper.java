/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.support.api.dialog;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.hamcrest.Matchers.allOf;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.diagram.ui.business.api.image.GallerySelectable;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.TreeImagesGalleryComposite;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery.GalleryItem;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.Matcher;

/**
 * This class allow to manage the Gallery from ImageSelectionDialog.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public final class ImageSelectionGalleryHelper {

    /**
     * Title of the dialog.
     */
    public static final String DIALOG_TITLE = Messages.ImageSelectionDialog_title; // $NON-NLS-1$

    /**
     * Default constructor.
     */
    private ImageSelectionGalleryHelper() {
    }

    /**
     * Select the GalleryItem at the specified index in the Gallery.
     * 
     * @param bot
     *            the SWTBot to use for searching the Gallery
     * @param itemName
     *            the name of the item to select
     */
    public static void selectGalleryItem(SWTBot bot, String itemName) {
        GallerySelectable widget = getGallery(bot);
        try {
            Job.getJobManager().join(TreeImagesGalleryComposite.REFRESH_IMAGE_JOB_FAMILY, new NullProgressMonitor());

            widget.getDisplay().syncExec(new Runnable() {
                @Override
                public void run() {
                    GalleryItem groupItem = widget.getItem(0);
                    GalleryItem[] galleryItems = groupItem.getItems();
                    GalleryItem itemToSelect = null;
                    for (GalleryItem item : galleryItems) {
                        if (itemName.equals(item.getText())) {
                            itemToSelect = item;
                        }
                    }
                    widget.selectItem(itemToSelect, true);

                    SWTBotUtils.waitAllUiEvents();
                }
            });
        } catch (OperationCanceledException | InterruptedException e) {
        }
    }

    /**
     * Get the Gallery from the ImageSelectionDialog.
     * 
     * @param bot
     *            the SWTBot to use for searching the Gallery
     * @return the gallery
     */
    @SuppressWarnings("unchecked")
    public static GallerySelectable getGallery(SWTBot bot) {
        Matcher<GallerySelectable> matcher = allOf(widgetOfType(GallerySelectable.class));
        AbstractSWTBotControl<GallerySelectable> swtBot = new AbstractSWTBotControl<GallerySelectable>(bot.widget(matcher), matcher);
        return swtBot.widget;
    }

    /**
     * Once the dialog is open, expand nodes where the image is located to select it in the gallery and click OK to set
     * the workspace image style.
     * 
     * @param bot
     *            the SWTBot to use to select an image
     * @param pathToImage
     *            the relative path of the image in the workspace, in the form "MyProject/folder1/img.png"
     */
    public static void selectWorkspaceImage(SWTBot bot, String pathToImage) {
        bot.waitUntil(Conditions.shellIsActive(DIALOG_TITLE));
        SWTBotShell activeShell = bot.shell(DIALOG_TITLE);
        String[] pathSegments = pathToImage.split("/"); //$NON-NLS-1$
        if (pathSegments.length > 0) {
            String imageName = pathSegments[pathSegments.length - 1];

            SWTBotText text = bot.text();
            text.setFocus();
            text.setText(imageName);

            SWTBotTree tree = bot.tree();
            SWTBotTreeItem treeItem = tree.getTreeItem(pathSegments[0]);
            for (int i = 0; i < pathSegments.length - 1; i++) {
                treeItem = tree.expandNode(pathSegments[i]);
            }
            treeItem.select();
            ImageSelectionGalleryHelper.selectGalleryItem(bot, imageName);
        }
        bot.button("OK").click(); //$NON-NLS-1$
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }
}
