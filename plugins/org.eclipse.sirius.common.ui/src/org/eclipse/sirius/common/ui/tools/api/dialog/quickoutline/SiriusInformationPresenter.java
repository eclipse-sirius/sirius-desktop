/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Obeo - remove ITextViewer information
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.IViewportListener;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.information.IInformationPresenterExtension;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Information presenter for Viewpoint diagram. The initial class comes from
 * org.eclipse.jface.text.information.InformationPresenter
 *
 * @author nlepine
 */
public class SiriusInformationPresenter extends AbstractInformationControlManager implements IInformationPresenter, IInformationPresenterExtension {

    /**
     * Internal information control closer. Listens to several events issued by
     * its subject control and closes the information control when necessary.
     */
    class Closer implements IInformationControlCloser, ControlListener, MouseListener, FocusListener, IViewportListener, KeyListener {

        /** The subject control. */
        private Control fSubjectControl;

        /** The information control. */
        private IInformationControl fInformationControlToClose;

        /** Indicates whether this closer is active. */
        private boolean fIsActive;

        @Override
        public void setSubjectControl(Control control) {
            fSubjectControl = control;
        }

        @Override
        public void setInformationControl(IInformationControl control) {
            fInformationControlToClose = control;
        }

        @Override
        public void start(Rectangle informationArea) {

            if (fIsActive) {
                return;
            }
            fIsActive = true;

            if (fSubjectControl != null && !fSubjectControl.isDisposed()) {
                fSubjectControl.addControlListener(this);
                fSubjectControl.addMouseListener(this);
                fSubjectControl.addFocusListener(this);
                fSubjectControl.addKeyListener(this);
            }

            if (fInformationControlToClose != null) {
                fInformationControlToClose.addFocusListener(this);
            }
        }

        @Override
        public void stop() {

            if (!fIsActive) {
                return;
            }
            fIsActive = false;

            if (fInformationControlToClose != null) {
                fInformationControlToClose.removeFocusListener(this);
            }

            hideInformationControl();

            if (fSubjectControl != null && !fSubjectControl.isDisposed()) {
                fSubjectControl.removeControlListener(this);
                fSubjectControl.removeMouseListener(this);
                fSubjectControl.removeFocusListener(this);
                fSubjectControl.removeKeyListener(this);
            }
        }

        @Override
        public void controlResized(ControlEvent e) {
            stop();
        }

        @Override
        public void controlMoved(ControlEvent e) {
            stop();
        }

        @Override
        public void mouseDown(MouseEvent e) {
            stop();
        }

        @Override
        public void mouseUp(MouseEvent e) {
        }

        @Override
        public void mouseDoubleClick(MouseEvent e) {
            stop();
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            Display d = fSubjectControl.getDisplay();
            d.asyncExec(new Runnable() {
                @Override
                public void run() {
                    if (fInformationControlToClose == null || !fInformationControlToClose.isFocusControl()) {
                        stop();
                    }
                }
            });
        }

        @Override
        public void viewportChanged(int topIndex) {
            stop();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            stop();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /** The map of <code>IInformationProvider</code> objects */
    private Map<Object, Object> fProviders;

    /** The offset to override selection. */
    private int fOffset = -1;

    /**
     * The document partitioning for this information presenter.
     * 
     * @since 3.0
     */
    private String fPartitioning;

    /**
     * Creates a new information presenter that uses the given information
     * control creator. The presenter is not installed on any text viewer yet.
     * By default, an information control closer is set that closes the
     * information control in the event of key strokes, resizing, moves, focus
     * changes, mouse clicks, and disposal - all of those applied to the
     * information control's parent control. Also, the setup ensures that the
     * information control when made visible will request the focus. By default,
     * the default document partitioning
     * {@link IDocumentExtension3#DEFAULT_PARTITIONING} is used.
     * 
     * @param creator
     *            the information control creator to be used
     */
    public SiriusInformationPresenter(IInformationControlCreator creator) {
        super(creator);
        setCloser(new Closer());
        takesFocusWhenVisible(true);
        fPartitioning = IDocumentExtension3.DEFAULT_PARTITIONING;
    }

    /**
     * Sets the document partitioning to be used by this information presenter.
     * 
     * @param partitioning
     *            the document partitioning to be used by this information
     *            presenter
     * @since 3.0
     */
    public void setDocumentPartitioning(String partitioning) {
        Assert.isNotNull(partitioning);
        fPartitioning = partitioning;
    }

    /*
     * @seeorg.eclipse.jface.text.information.IInformationPresenterExtension#
     * getDocumentPartitioning()
     * @since 3.0
     */
    @Override
    public String getDocumentPartitioning() {
        return fPartitioning;
    }

    /**
     * Registers a given information provider for a particular content type. If
     * there is already a provider registered for this type, the new provider is
     * registered instead of the old one.
     * 
     * @param provider
     *            the information provider to register, or <code>null</code> to
     *            remove an existing one
     * @param contentType
     *            the content type under which to register
     */
    public void setInformationProvider(IInformationProvider provider, String contentType) {

        Assert.isNotNull(contentType);

        if (fProviders == null) {
            fProviders = new HashMap<Object, Object>();
        }

        if (provider == null) {
            fProviders.remove(contentType);
        } else {
            fProviders.put(contentType, provider);
        }
    }

    @Override
    public IInformationProvider getInformationProvider(String contentType) {
        if (fProviders == null) {
            return null;
        }

        return (IInformationProvider) fProviders.get(contentType);
    }

    /**
     * Sets a offset to override the selection. Setting the value to
     * <code>-1</code> will disable overriding.
     * 
     * @param offset
     *            the offset to override selection or <code>-1</code>
     */
    public void setOffset(int offset) {
        fOffset = offset;
    }

    @Override
    protected void computeInformation() {
        fOffset = -1;
        int offset = fOffset;

        IInformationProvider provider = null;
        provider = getInformationProvider(IDocument.DEFAULT_CONTENT_TYPE);
        if (provider == null) {
            return;
        }

        IRegion subject = provider.getSubject(null, offset);
        if (subject == null) {
            return;
        }

        if (provider instanceof IInformationProviderExtension2) {
            setCustomInformationControlCreator(((IInformationProviderExtension2) provider).getInformationPresenterControlCreator());
        } else {
            setCustomInformationControlCreator(null);
        }

        if (provider instanceof IInformationProviderExtension) {
            IInformationProviderExtension extension = (IInformationProviderExtension) provider;
            setInformation(extension.getInformation2(null, subject), computeArea(subject));
        } else {
            // backward compatibility code
            setInformation(provider.getInformation(null, subject), computeArea(subject));
        }
    }

    /**
     * Determines the graphical area covered by the given text region.
     * 
     * @param region
     *            the region whose graphical extend must be computed
     * @return the graphical extend of the given region
     */
    private Rectangle computeArea(IRegion region) {
        return new Rectangle(0, 0, 0, 0);
    }

    @Override
    public void install(ITextViewer textViewer) {
    }

    @Override
    public void uninstall() {
        dispose();
    }
}
