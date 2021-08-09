/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.diagram.ui.business.api.image.GallerySelectable;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery.GalleryItem;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * This class helps to wait until the gallery displays the expected number of elements.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class CheckNbVisibleElementsInGallery extends DefaultCondition {

    private GallerySelectable widget;

    private String errorMessage;

    private int expected;

    /**
     * Constructor.
     * 
     * @param gallery
     *            the gallery to check
     * @param expected
     *            the number of elements that should be display in the gallery
     * @param errorMessage
     *            the message to display in case of failure
     */
    public CheckNbVisibleElementsInGallery(GallerySelectable gallery, int expected, String errorMessage) {
        this.widget = gallery;
        this.expected = expected;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean test() throws Exception {
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            @Override
            public void run() {
                widget.getDisplay().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        GalleryItem groupItem = widget.getItem(0);
                        setResult(expected == groupItem.getItemCount());
                    }
                });
            }
        };

        widget.getDisplay().syncExec(runnable);
        return runnable.getResult().booleanValue();
    }

    @Override
    public String getFailureMessage() {
        return errorMessage;
    }
}
