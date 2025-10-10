/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal;

import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Text;

/**
 * This will be used by the interpreter view in order to create {@link Text} widgets that know how to hide their scroll
 * bars when they are not needed.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class SWTUtil {
    /** Utility classes don't need default constructors. */
    private SWTUtil() {
        // Hides default constructor.
    }

    /**
     * Creates a {@link SourceViewer} widget that knows how to hide its scroll bars.
     * 
     * @param parent
     *            The parent composite for this viewer.
     * @param style
     *            Style of the created viewer.
     * @return The created {@link SourceViewer}.
     */
    public static SourceViewer createScrollableSourceViewer(Composite parent, int style) {
        SourceViewer viewer = new SourceViewer(parent, null, style);

        setUpScrollableListener(viewer.getTextWidget());

        return viewer;
    }

    /**
     * Creates a {@link StyledText} widget that knows how to hide its scroll bars.
     * 
     * @param parent
     *            The parent composite for this text.
     * @param style
     *            Style of the created text.
     * @return The created {@link StyledText} widget.
     */
    public static StyledText createScrollableStyledText(Composite parent, int style) {
        final StyledText text = new StyledText(parent, style);

        // If this text has no scroll bars, simply return it.
        if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) == 0) {
            return text;
        }

        // Otherwise, set up its listeners
        setUpScrollableListener(text);

        return text;
    }

    /**
     * Creates a {@link Text} widget that knows how to hide its scroll bars.
     * 
     * @param parent
     *            The parent composite for this text.
     * @param style
     *            Style of the created text.
     * @return The created {@link Text} widget.
     */
    public static Text createScrollableText(Composite parent, int style) {
        final Text text = new Text(parent, style);

        // If this text has no scroll bars, simply return it.
        if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) == 0) {
            return text;
        }

        // Otherwise, set up its listeners
        setUpScrollableListener(text);

        return text;
    }

    /**
     * Sets up the listeners allowing us to hide the scroll bars of the given scrollable when they are not needed.
     * 
     * @param scrollable
     *            The scrollable widget to setup.
     */
    public static void setUpScrollableListener(final Scrollable scrollable) {
        final ControlAdapter resizeListener = new ScrollableResizeListener(scrollable);
        scrollable.addControlListener(resizeListener);

        final ModifyListener modifyListener = new ScrollableModifyListener(scrollable);
        if (scrollable instanceof Text) {
            ((Text) scrollable).addModifyListener(modifyListener);
        } else if (scrollable instanceof StyledText) {
            ((StyledText) scrollable).addModifyListener(modifyListener);
        }

        scrollable.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                scrollable.removeControlListener(resizeListener);
                if (scrollable instanceof Text) {
                    ((Text) scrollable).removeModifyListener(modifyListener);
                } else if (scrollable instanceof StyledText) {
                    ((StyledText) scrollable).removeModifyListener(modifyListener);
                }
            }
        });
    }

    /**
     * Computes the size of the text displayed by the given {@link Text} widget.
     * 
     * @param widget
     *            The widget on which is displayed the text.
     * @param text
     *            The actual displayed text.
     * @return The actual size of the {@link Text} widget's content.
     */
    protected static Point computeTextSize(Control widget, String text) {
        String[] lines = text.split("(\r\n)|\n|\r"); //$NON-NLS-1$

        String longestLine = ""; //$NON-NLS-1$
        if (lines.length > 0) {
            longestLine = lines[0];
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].length() > longestLine.length()) {
                    longestLine = lines[i];
                }
            }
        }
        GC gc = new GC(widget);
        gc.setFont(widget.getFont());
        final int textWidth = gc.stringExtent(longestLine).x;
        final int textHeight = gc.stringExtent("W").y * lines.length; //$NON-NLS-1$
        gc.dispose();

        return new Point(textWidth, textHeight);
    }

    /**
     * This will be used as the resize listener for our scrollable text controls in order to determine whether the
     * scroll bars are needed.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected static class ScrollableModifyListener implements ModifyListener {
        /** The {@link Scrollable} widget against which this listener has been registered. */
        private final Scrollable text;

        /**
         * Instantiates our modify listener for the given text widget.
         * 
         * @param text
         *            The text widget to listen to.
         */
        public ScrollableModifyListener(Scrollable text) {
            this.text = text;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
         */
        public void modifyText(ModifyEvent e) {
            final Rectangle clientArea = text.getClientArea();
            final String currentText;
            if (text instanceof Text) {
                currentText = ((Text) text).getText();
            } else if (text instanceof StyledText) {
                currentText = ((StyledText) text).getText();
            } else {
                return;
            }
            final Point textSize = computeTextSize(text, currentText);
            if (clientArea.width > textSize.x && text.getHorizontalBar() != null) {
                text.getHorizontalBar().setVisible(false);
            } else if (text.getHorizontalBar() != null) {
                text.getHorizontalBar().setVisible(true);
            }
            if (clientArea.height > textSize.y && text.getVerticalBar() != null) {
                text.getVerticalBar().setVisible(false);
            } else if (text.getVerticalBar() != null) {
                text.getVerticalBar().setVisible(true);
            }
        }
    }

    /**
     * This will be used as the resize listener for our scrollable text controls in order to determine whether the
     * scroll bars are needed.
     * 
     * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
     */
    protected static class ScrollableResizeListener extends ControlAdapter {
        /** Keeps a reference to the last size we computed. */
        private Point lastSize;

        /** Keeps a reference to the last text we computed a size for. */
        private String lastText;

        /** The {@link Scrollable} widget against which this listener has been registered. */
        private final Scrollable text;

        /**
         * Instantiates our resize listener for the given text widget.
         * 
         * @param text
         *            The text widget to listen to.
         */
        public ScrollableResizeListener(Scrollable text) {
            this.text = text;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.swt.events.ControlAdapter#controlResized(org.eclipse.swt.events.ControlEvent)
         */
        @Override
        public void controlResized(ControlEvent e) {
            final Rectangle clientArea = text.getClientArea();
            final String currentText;
            if (text instanceof Text) {
                currentText = ((Text) text).getText();
            } else if (text instanceof StyledText) {
                currentText = ((StyledText) text).getText();
            } else {
                return;
            }
            Point textSize = lastSize;
            if (textSize == null || !lastText.equals(currentText)) {
                textSize = computeTextSize(text, currentText);
                lastText = currentText;
                lastSize = textSize;
            }
            if (clientArea.width > textSize.x && text.getHorizontalBar() != null) {
                if (clientArea.height > text.getHorizontalBar().getSize().y) {
                    text.getHorizontalBar().setVisible(false);
                }
            } else if (text.getHorizontalBar() != null) {
                text.getHorizontalBar().setVisible(true);
            }
            if (clientArea.height > textSize.y && text.getVerticalBar() != null) {
                if (clientArea.width > text.getVerticalBar().getSize().x) {
                    text.getVerticalBar().setVisible(false);
                }
            } else if (text.getVerticalBar() != null) {
                text.getVerticalBar().setVisible(true);
            }
        }
    }
}
