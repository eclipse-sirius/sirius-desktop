/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.Style;

/**
 * This class stores a copy of the Sirius style and GMF view.
 * 
 * @author Séraphin Costa <seraphin.costa@obeosoft.com>
 *
 */
public final class SiriusStyleClipboard {
    private static final SiriusStyleClipboard INSTANCE = new SiriusStyleClipboard();

    private Optional<View> gmfView = Optional.empty();

    private Optional<Style> siriusStyle = Optional.empty();

    private List<Listener> listeners = new ArrayList<Listener>();

    @FunctionalInterface
    public interface Listener {
        /**
         * This method is invoked when Sirius Style Clipboard data is modified or deleted.
         * 
         * @param gmfView
         *            The GMF view of the element whose style is to be copied, containing the GMF style data.
         * @param siriusStyle
         *            The Sirius style of the element whose style you want to copy.
         */
        void onChangeData(Optional<View> gmfView, Optional<Style> siriusStyle);
    }

    private SiriusStyleClipboard() {
    }

    /**
     * Remove content of the Sirius style clipboard.
     */
    public void clear() {
        gmfView = Optional.empty();
        siriusStyle = Optional.empty();
        notifyListener();
    }

    /**
     * Stores the style parameter in the Sirius style clipboard. If another style was already present in this clipboard,
     * it is overwritten.
     * 
     * @param newGmfView
     *            The GMF view of the element whose style is to be copied, containing the GMF style data.
     * @param newSiriusStyle
     *            The Sirius style of the element whose style you want to copy.
     */
    public void store(View newGmfView, Style newSiriusStyle) {
        View copiedGmfView = SiriusCopierHelper.copyWithNoUidDuplication(newGmfView);
        copiedGmfView.setElement(null);
        gmfView = Optional.of(copiedGmfView);
        siriusStyle = Optional.of(SiriusCopierHelper.copyWithNoUidDuplication(newSiriusStyle));
        notifyListener();
    }

    public Optional<View> getGmfView() {
        return gmfView;
    }

    public Optional<Style> getSiriusStyle() {
        return siriusStyle;
    }

    /**
     * Add listener to the list. Any listener added should be removed when no longer required, to avoid memory leaks.
     * 
     * @see Listener#onChangeData(Optional, Optional)
     * @see #removeListener(Listener)
     * 
     * @param onChangeClipboard
     *            The listener to add
     */
    public void addListener(Listener onChangeClipboard) {
        listeners.add(Objects.requireNonNull(onChangeClipboard));
    }

    /**
     * Remove listener from the list.
     * 
     * @see #addListener(Listener)
     * 
     * @param onChangeClipboard
     *            The listener to remove
     */
    public void removeListener(Listener onChangeClipboard) {
        listeners.remove(onChangeClipboard);
    }

    private void notifyListener() {
        for (Listener listener : listeners) {
            listener.onChangeData(gmfView, siriusStyle);
        }
    }

    public static SiriusStyleClipboard getInstance() {
        return INSTANCE;
    }
}
