/******************************************************************************
 * Copyright (c) 2008-2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Esteban Dugueperoux (Obeo) <esteban.dugueperoux@obeo.fr> - manage also diagram print diagrams for diagram stored in any resource not only XMLResource
 *    Laurent Redor (Obeo) <laurent.redor@obeo.fr> - Backport of fix of bugzilla 416891 and 412041.
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.print;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.common.ui.util.DisplayUtils;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.printing.internal.util.PrintHelperUtil;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.JPSDiagramPrinter;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.PageData;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeTypes;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.GraphicsToGraphics2DAdaptor;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.image.ImageConverter;
import org.eclipse.gmf.runtime.draw2d.ui.text.TextUtilitiesEx;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * A specialized <code>JPSDiagramPrinter</code> that manage also diagram print
 * for diagram stored in any resource not only XMLResource.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class SiriusJPSRenderedDiagramPrinter extends JPSDiagramPrinter {

    // A constant that takes into account screen display DPI and the graphic DPI
    // 72.0 DPI is an AWT constant @see java.awt.Graphics2D
    private static final double AWT_DPI_CONST = 72.0;

    // The print service used during printing.
    private PrintService printService;

    // Tells if the current version of plugin
    // "org.eclipse.gmf.runtime.draw2d.ui.render.awt" is previous to 1.8.0
    // version.
    private Boolean isAwtPluginBeforeLunaVersion;

    /**
     * Creates a new instance.
     * 
     * @param preferencesHint
     *            the preferences hint to use for initializing the preferences
     * @param mm
     *            <code>IMapMode</code> to do the coordinate mapping
     */
    public SiriusJPSRenderedDiagramPrinter(final PreferencesHint preferencesHint, final IMapMode mm) {
        super(preferencesHint, mm);
    }

    @Override
    public void setPrinter(String printerName) {
        AttributeSet attributes = new HashPrintServiceAttributeSet(new PrinterName(printerName, Locale.getDefault()));
        PrintService[] services = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PRINTABLE, attributes);
        printService = services[0];
    }

    /**
     * Copied from org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.
     * JPSDiagramPrinter.run() with change to not use
     * DiagramEditorUtil.findOpenedDiagramEditorForID(ViewUtil.getIdStr()) which
     * works only for diagram stored in XMLResource
     */
    @Override
    public void run() {
        Iterator<Diagram> it = diagrams.iterator();
        Shell shell = new Shell();
        try {
            while (it.hasNext()) {
                Diagram diagram = it.next();

                DiagramEditor openedDiagramEditor = getDiagramEditor(diagram);
                DiagramEditPart dgrmEP = openedDiagramEditor == null ? PrintHelperUtil.createDiagramEditPart(diagram, preferencesHint, shell) : openedDiagramEditor.getDiagramEditPart();

                boolean loadedPreferences = openedDiagramEditor != null || PrintHelperUtil.initializePreferences(dgrmEP, preferencesHint);

                RootEditPart rep = dgrmEP.getRoot();
                if (rep instanceof DiagramRootEditPart) {
                    this.mapMode = ((DiagramRootEditPart) rep).getMapMode();
                }

                IPreferenceStore preferenceStore = ((DiagramGraphicalViewer) dgrmEP.getViewer()).getWorkspaceViewerPreferenceStore();
                if (preferenceStore.getBoolean(WorkspaceViewerProperties.PREF_USE_WORKSPACE_SETTINGS)) {
                    if (dgrmEP.getDiagramPreferencesHint().getPreferenceStore() != null) {
                        preferenceStore = (IPreferenceStore) dgrmEP.getDiagramPreferencesHint().getPreferenceStore();
                    }
                }
                doPrintDiagram(printService.createPrintJob(), dgrmEP, loadedPreferences, preferenceStore);
            }
        } finally {
            dispose();
            shell.dispose();
        }
    }

    private DiagramEditor getDiagramEditor(Diagram diagram) {
        DiagramEditor result = null;
        List<?> diagramEditors = EditorService.getInstance().getRegisteredEditorParts();
        Iterator<?> it = diagramEditors.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof DiagramEditor) {
                DiagramEditor diagramEditor = (DiagramEditor) obj;
                if (diagramEditor.getDiagram() == diagram) {
                    result = diagramEditor;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int print(Graphics printGraphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        // This method needs to be overridden only for version of plugin
        // org.eclipse.gmf.runtime.draw2d.ui.render.awt before 1.8.0.
        if (isAwtPluginBeforeLunaVersion()) {
            return printPreviousLuna(printGraphics, pageFormat, pageIndex);
        } else {
            return super.print(printGraphics, pageFormat, pageIndex);
        }
    }

    /**
     * Tells if the current version of plugin
     * "org.eclipse.gmf.runtime.draw2d.ui.render.awt" is previous to 1.8.0
     * version. Version 1.8.0 of this plugin (Luna version) is the first version
     * that contains the fix of bugzilla 416891 and 412041.
     * 
     * @return true if the current plugin version is previous to 1.8.0 (i.e.
     *         version of Eclipse Luna), false otherwise.
     */
    protected boolean isAwtPluginBeforeLunaVersion() {
        if (isAwtPluginBeforeLunaVersion == null) {
            Version lunaVersion = Version.parseVersion("1.8.0"); //$NON-NLS-1$
            Bundle awtBundle = Platform.getBundle("org.eclipse.gmf.runtime.draw2d.ui.render.awt"); //$NON-NLS-1$
            if (awtBundle != null) {
                isAwtPluginBeforeLunaVersion = awtBundle.getVersion().compareTo(lunaVersion) < 0;
            } else {
                isAwtPluginBeforeLunaVersion = false;
            }
        }
        return isAwtPluginBeforeLunaVersion;
    }

    /**
     * Method print overridden for version before Luna to backport fix of
     * bugzilla 416891 and 412041 in a specific version of
     * PrinterGraphicsToGraphics2DAdapter.
     * 
     * @param printGraphics
     *            the context into which the page is drawn
     * @param pageFormat
     *            the size and orientation of the page being drawn
     * @param pageIndex
     *            the zero based index of the page to be drawn
     * @return PAGE_EXISTS if the page is rendered successfully or NO_SUCH_PAGE
     *         if <code>pageIndex</code> specifies a non-existent page.
     * @exception java.awt.print.PrinterException
     *                thrown when the print job is terminated.
     */
    public int printPreviousLuna(Graphics printGraphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Optional<Object> optionPages = ReflectionHelper.getFieldValueWithoutException(this, "pages"); //$NON-NLS-1$
        if (!optionPages.isPresent() || pageIndex >= ((PageData[]) optionPages.get()).length) {
            return java.awt.print.Printable.NO_SUCH_PAGE;
        }

        try {
            printGraphics.setClip(0, 0, (int) pageFormat.getWidth(), (int) pageFormat.getHeight());

            swtGraphics = new PrinterGraphicsToGraphics2DAdapter((java.awt.Graphics2D) printGraphics, new Rectangle(0, 0, (int) pageFormat.getWidth(), (int) pageFormat.getHeight()));

            graphics = createMapModeGraphics(createPrinterGraphics(swtGraphics));
            graphics.scale(AWT_DPI_CONST / display_dpi.x);
            drawPage(((PageData[]) optionPages.get())[pageIndex]);
        } finally {
            dispose();
        }

        return java.awt.print.Printable.PAGE_EXISTS;
    }

    /**
     * A specialized graphics adapter used in printing.
     * 
     * There are several issues with the base adapter such as incorrect line
     * width settings and issues with gradient fill causing printing to be
     * offset wich are concerns specific to printing.
     * 
     * This class is duplicated from
     * JPSDiagramPrinter$PrinterGraphicsToGraphics2DAdapter (from version 1.4.1
     * of container plugin) with the addition of drawString(String ,int , int)
     * overridden method.
     * 
     * @author James Bruck (jbruck)
     * 
     */
    private class PrinterGraphicsToGraphics2DAdapter extends GraphicsToGraphics2DAdaptor {

        private final TextUtilitiesEx textUtilities = new TextUtilitiesEx(MapModeTypes.IDENTITY_MM);

        PrinterGraphicsToGraphics2DAdapter(Graphics2D graphics, Rectangle viewPort) {
            super(graphics, viewPort);
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.
         * GraphicsToGraphics2DAdaptor#setLineWidth(int)
         */
        public void setLineWidth(int width) {
            super.setLineWidth(width);

            BasicStroke scaledStroke = getStroke();
            //
            // Make a special case for line thickness to take the
            // printer resolution into account.
            //
            scaledStroke = new BasicStroke((float) (width * AWT_DPI_CONST / 100), scaledStroke.getEndCap(), scaledStroke.getLineJoin(), scaledStroke.getMiterLimit(), scaledStroke.getDashArray(), 0);

            getGraphics2D().setStroke(scaledStroke);
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.
         * GraphicsToGraphics2DAdaptor#fillGradient(int, int, int, int, boolean)
         */
        public void fillGradient(int x, int y, int w, int h, boolean vertical) {
            //
            // A bug in the draw2d layer causes printed output to be
            // offset if we use gradient fill. We will use an image
            // instead.
            //
            Image tempImage = new Image(DisplayUtils.getDisplay(), new org.eclipse.swt.graphics.Rectangle(x, y, w, h));
            GC gc = new GC(tempImage);
            SWTGraphics tempGraphics = new SWTGraphics(gc);

            tempGraphics.setForegroundColor(swtGraphics.getForegroundColor());
            tempGraphics.setBackgroundColor(swtGraphics.getBackgroundColor());
            tempGraphics.fillGradient(new org.eclipse.draw2d.geometry.Rectangle(0, 0, w, h), vertical);
            drawImage(tempImage, 0, 0, w, h, x, y, w, h);

            tempGraphics.dispose();
            gc.dispose();
            tempImage.dispose();
        }

        private boolean isFontUnderlined(Font f) {
            return false;
        }

        private boolean isFontStrikeout(Font f) {
            return false;
        }

        /**
         * This method is overridden to backport fix of bugzilla 416891 and
         * 412041 in context of Juno3 to Kepler environment.
         */
        @Override
        public void drawString(String s, int x, int y) {
            Optional<Object> optionalTransX = ReflectionHelper.getFieldValueWithoutException(this, "transX"); //$NON-NLS-1$
            Optional<Object> optionalTransY = ReflectionHelper.getFieldValueWithoutException(this, "transY"); //$NON-NLS-1$

            if (s == null || s.length() == 0 || !optionalTransX.isPresent() || !optionalTransY.isPresent())
                return;

            java.awt.FontMetrics metrics = getGraphics2D().getFontMetrics();
            int stringLength = metrics.stringWidth(s);
            Dimension swtStringSize = textUtilities.getStringExtents(s, swtGraphics.getFont());

            float xpos = x + ((Integer) optionalTransX.get()).intValue();
            float ypos = y + ((Integer) optionalTransY.get()).intValue();
            int lineWidth;

            // if (paintNotCompatibleStringsAsBitmaps &&
            // Math.abs(swtStringSize.width - stringLength) > 2) {
            if (paintNotCompatibleStringsAsBitmaps && (getGraphics2D().getFont().canDisplayUpTo(s) != -1)) {
                // create SWT bitmap of the string then
                Image image = new Image(DisplayUtils.getDisplay(), swtStringSize.width, swtStringSize.height);
                GC gc = new GC(image);
                gc.setForeground(getForegroundColor());
                gc.setBackground(getBackgroundColor());
                gc.setAntialias(getAntialias());
                gc.setFont(getFont());
                gc.drawString(s, 0, 0);
                gc.dispose();
                ImageData data = image.getImageData();
                image.dispose();
                RGB backgroundRGB = getBackgroundColor().getRGB();
                for (int i = 0; i < data.width; i++) {
                    for (int j = 0; j < data.height; j++) {
                        if (data.palette.getRGB(data.getPixel(i, j)).equals(backgroundRGB)) {
                            data.setAlpha(i, j, 0);
                        } else {
                            data.setAlpha(i, j, 255);
                        }
                    }
                }
                checkState();
                getGraphics2D().drawImage(ImageConverter.convertFromImageData(data), new AffineTransform(1f, 0f, 0f, 1f, xpos, ypos), null);
                stringLength = swtStringSize.width;
            } else {

                ypos += metrics.getAscent();

                checkState();
                getGraphics2D().setPaint(getColor(swtGraphics.getForegroundColor()));
                getGraphics2D().drawString(s, xpos, ypos);
            }

            if (isFontUnderlined(getFont())) {
                int baseline = y + metrics.getAscent();
                lineWidth = getLineWidth();

                setLineWidth(1);
                drawLine(x, baseline, x + stringLength, baseline);
                setLineWidth(lineWidth);
            }

            if (isFontStrikeout(getFont())) {
                int strikeline = y + (metrics.getHeight() / 2);
                lineWidth = getLineWidth();

                setLineWidth(1);
                drawLine(x, strikeline, x + stringLength, strikeline);
                setLineWidth(lineWidth);
            }
        }
    }
}
