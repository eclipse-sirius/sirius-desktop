/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;

/**
 * A default font styler used by tables and trees.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DefaultFontStyler extends Styler {
    private final Font font;

    private final Color foregroundColor;

    private final Color backgroundColor;

    private boolean strikeout;

    private boolean underline;

    /**
     * Default constructor.
     * 
     * @param font
     *            Font to use.
     * @param foregroundColor
     *            foreground color to use.
     * @param backgroundColor
     *            background color to use.
     * @param strikeout
     *            True if is struck out false otherwise.
     * @param underline
     *            True if is underlined false otherwise.
     */
    public DefaultFontStyler(Font font, Color foregroundColor, Color backgroundColor, boolean underline, boolean strikeout) {
        this.font = font;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.strikeout = strikeout;
        this.underline = underline;
    }

    @Override
    public void applyStyles(TextStyle textStyle) {
        if (font != null) {
            textStyle.font = font;
        }
        if (foregroundColor != null) {
            textStyle.foreground = foregroundColor;
        }
        if (backgroundColor != null) {
            textStyle.background = backgroundColor;
        }
        textStyle.strikeout = strikeout;
        textStyle.underline = underline;
    }
}
