/*******************************************************************************
 * Copyright (c) 2011, 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline;

import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Factory for the viewpoint information presenter.
 * 
 * @author ymortier
 */
public final class SiriusInformationPresenterFactory {

    /**
     * Avoid instantiation from external.
     */
    private SiriusInformationPresenterFactory() {
        // empty.
    }

    /**
     * Creates a new {@link SiriusInformationPresenter}.
     * 
     * @param control
     *            the main control.
     * @param quickOutlineDescriptor
     *            the descriptor of the quick outline.
     * @param rootElement
     *            the root element.
     * @return the created presenter.
     */
    public static SiriusInformationPresenter createInformationPresenter(final Control control, final QuickOutlineDescriptor quickOutlineDescriptor, Object rootElement) {
        SiriusInformationPresenter presenter = new SiriusInformationPresenter(new IInformationControlCreator() {
            @Override
            public IInformationControl createInformationControl(Shell parent) {
                return new QuickOutlineControl(parent, SWT.RESIZE, quickOutlineDescriptor);
            }
        });

        presenter.install(control);
        IInformationProvider provider = new SiriusQuickOutlineInformationProvider(rootElement);
        presenter.setInformationProvider(provider, IDocument.DEFAULT_CONTENT_TYPE);
        final int minimalWidth = 80;
        final int minimalHeight = 40;
        presenter.setSizeConstraints(minimalWidth, minimalHeight, true, false);
        presenter.setAnchor(AbstractInformationControlManager.ANCHOR_GLOBAL);
        return presenter;

    }

    /**
     * The information provider for quick outline.
     * 
     * @author ymortier
     */
    private static class SiriusQuickOutlineInformationProvider implements IInformationProvider, IInformationProviderExtension {

        private Object information2;

        /**
         * Instantiates our information provider given the editor on which it is
         * called.
         * 
         * @param information
         *            Editor on which the quick outline will be displayed.
         */
        SiriusQuickOutlineInformationProvider(Object information) {
            this.information2 = information;
        }

        @Override
        public String getInformation(ITextViewer textViewer, IRegion subject) {
            // deprecated as we implement IInformationProviderExtension
            return null;
        }

        @Override
        public Object getInformation2(ITextViewer textViewer, IRegion subject) {
            return this.information2;
        }

        @Override
        public IRegion getSubject(ITextViewer textViewer, int offset) {
            // Don't take offset into account for viewpoint quick outline.
            return new Region(offset, 0);
        }
    }
}
