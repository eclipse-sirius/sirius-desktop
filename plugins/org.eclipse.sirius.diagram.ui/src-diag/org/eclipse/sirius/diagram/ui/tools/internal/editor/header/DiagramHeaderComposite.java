/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.header;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.Viewport;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.HeaderData;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;

import com.google.common.collect.Lists;

/**
 * A specific composite to display header diagram. This composite has 2 sub
 * composites :
 * <UL>
 * <LI>One for the header composite, headerSection, to display Labels
 * corresponding to HeaderData</LI>
 * <LI>and one for display others composite, mainSection, (currently the GMF
 * diagram).</LI>
 * </UL>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DiagramHeaderComposite extends Composite {
    /**
     * The minimum height of a line for the header.
     */
    private static final int DEFAULT_HEADER_LINE_HEIGHT = 18;

    /**
     * The height of a line computing according to the default system font.
     */
    private static int lineHeight;

    /**
     * The width of the left ruler in
     * {@link org.eclipse.gef.ui.rulers.RulerComposite}.
     */
    private static final int LEFT_RULER_WIDTH = 22;

    private static final Cursor DISABLED_CURSOR = Cursors.NO;

    private IDiagramWorkbenchPart part;

    private DDiagram dDiagram;

    private GraphicalViewer diagramViewer;

    private Composite headerSection;

    private PropertyChangeListener diagramPropertyListener;

    private PropertyChangeListener portPropertyListener;

    private ZoomListener zoomListener;

    private boolean isRulerVisible;

    private int xOrigin;

    private double zoom;

    private Sash sash;

    /**
     * Instantiate a new header composite.
     * 
     * @param parent
     *            the parent composite
     * @param part
     *            the diagram workbench part
     */
    public DiagramHeaderComposite(Composite parent, final IDiagramWorkbenchPart part) {
        super(parent, SWT.None);
        this.part = part;
        if (part instanceof DDiagramEditor) {
            dDiagram = (DDiagram) ((DDiagramEditor) part).getRepresentation();
        }
        this.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        GridLayout verticalGridLayout = new GridLayout(1, true);
        verticalGridLayout.horizontalSpacing = 0;
        verticalGridLayout.marginHeight = 0;
        verticalGridLayout.marginWidth = 0;
        verticalGridLayout.verticalSpacing = 0;
        this.setLayout(verticalGridLayout);
        Color defaultSeparatorColor = VisualBindingManager.getDefault().getLabelColorFromRGBValues(HeaderData.DEFAULT_SEPARATOR_BACKGROUND_COLOR);

        addLineSeparator(defaultSeparatorColor);

        headerSection = new Composite(this, SWT.NONE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = DiagramHeaderComposite.getDiagramHeaderLineHeight();
        // Get the height stored in dDiagram (to restore the header to its
        // previous size).
        if (dDiagram != null && dDiagram.eIsSet(DiagramPackage.eINSTANCE.getDDiagram_HeaderHeight())) {
            gd.heightHint = dDiagram.getHeaderHeight() * DiagramHeaderComposite.getDiagramHeaderLineHeight();
        }
        headerSection.setLayoutData(gd);
        headerSection.setBackground(defaultSeparatorColor);
        headerSection.setCursor(DISABLED_CURSOR);

        addLineSeparator(defaultSeparatorColor);
        addLineSeparator(ColorConstants.lightGray);

        // Add a Sash to allow to resize the height of the header.
        sash = new Sash(parent, SWT.HORIZONTAL);
        sash.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        sash.addMouseListener(new MouseListener() {

            public void mouseUp(MouseEvent e) {
            }

            public void mouseDown(MouseEvent e) {
            }

            /**
             * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
             *      The header is auto-sized when the use double-click on sash.
             */
            public void mouseDoubleClick(MouseEvent e) {
                int nbLines = getNbLinesNeeded();
                setHeaderHeight(DiagramHeaderComposite.getDiagramHeaderLineHeight() * nbLines);
                // Add a setFocus here to mask a bug under Linux. With this
                // setFocus, the feedback stays drawn but just above the new
                // sash location. Without this setFocus, the feedback stays
                // drawn on the previous location. This feedback disappears
                // after the user click on element on diagram.
                sash.setFocus();
            }
        });
        sash.addSelectionListener(new SashSelectionAdapter(this, dDiagram));
    }

    /**
     * Return the height of a line in diagram header. The height is computing
     * according to the default font of the System.
     * {@link DEFAULT_HEADER_LINE_HEIGHT} is a minimum.
     * 
     * @return The height of a line in diagram header.
     */
    public static int getDiagramHeaderLineHeight() {
        if (lineHeight == 0) {
            // We add 1 pixel to the default height to have one more space.
            lineHeight = SWTUtil.getDefaultFontTextHeight() + 1;
            if (lineHeight < DEFAULT_HEADER_LINE_HEIGHT) {
                lineHeight = DEFAULT_HEADER_LINE_HEIGHT;
            }
        }
        return lineHeight;
    }

    /**
     * Create a line separator in the header section.
     * 
     * @param color
     *            the color of this line separator
     */
    protected void addLineSeparator(Color color) {
        Composite separator = new Composite(this, SWT.NONE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 1;
        separator.setLayoutData(gd);
        separator.setBackground(color);
        separator.setCursor(DISABLED_CURSOR);
    }

    /**
     * Rebuild the label composite of the header section according to the header
     * data.
     */
    public void rebuildHeaderSection() {
        headerSection.setRedraw(false);
        try {
            for (Control child : Lists.newArrayList(headerSection.getChildren())) {
                child.dispose();
            }
            // Get the headerData of the current editor.
            LinkedList<HeaderData> headerDatas = getHeaderData();
            // Compute the number of columns needed:
            // * two for each header data (label + space between other one)
            // * one for ruler (if ruler is visible)
            int nbColumns = headerDatas.size() * 2;
            if (isRulerVisible) {
                nbColumns++;
            }
            GridLayout layout = new GridLayout(nbColumns, false);
            layout.marginHeight = 0;
            layout.marginWidth = 0;
            layout.horizontalSpacing = 0;
            layout.verticalSpacing = 0;
            headerSection.setLayout(layout);

            if (isRulerVisible) {
                // Add a blank label for the space take by the ruler.
                createLabelInHeaderSection("", DiagramHeaderComposite.LEFT_RULER_WIDTH, VisualBindingManager.getDefault().getLabelColorFromRGBValues(HeaderData.DEFAULT_SEPARATOR_BACKGROUND_COLOR), //$NON-NLS-1$
                        VisualBindingManager.getDefault().getLabelColorFromRGBValues(HeaderData.DEFAULT_LABEL_COLOR));
            }
            int previousXLocation = 0;
            int previousWidth = 0;
            for (HeaderData headerData : headerDatas) {
                int xLocation = (int) (headerData.getXLocation() * zoom);
                int width = (int) (headerData.getWidth() * zoom);
                if ((xLocation + width) > xOrigin) {
                    if (xLocation < xOrigin) {
                        width = width - (xOrigin - xLocation);
                        xLocation = 0;
                    } else {
                        xLocation = xLocation - xOrigin;
                    }
                    int blankWidth = xLocation - (previousXLocation + previousWidth);
                    if (blankWidth > 0) {
                        createLabelInHeaderSection("", blankWidth, VisualBindingManager.getDefault().getLabelColorFromRGBValues(HeaderData.DEFAULT_SEPARATOR_BACKGROUND_COLOR), VisualBindingManager //$NON-NLS-1$
                                .getDefault().getLabelColorFromRGBValues(HeaderData.DEFAULT_LABEL_COLOR));
                    }
                    Color backgroundColor;
                    if (headerData.getBackgroundColor() == null) {
                        backgroundColor = VisualBindingManager.getDefault().getLabelColorFromRGBValues(HeaderData.DEFAULT_BACKGROUND_COLOR);
                    } else {
                        backgroundColor = VisualBindingManager.getDefault().getLabelColorFromRGBValues(headerData.getBackgroundColor());
                    }
                    Color labelColor;
                    if (headerData.getLabelColor() == null) {
                        labelColor = VisualBindingManager.getDefault().getLabelColorFromRGBValues(HeaderData.DEFAULT_LABEL_COLOR);
                    } else {
                        labelColor = VisualBindingManager.getDefault().getLabelColorFromRGBValues(headerData.getLabelColor());
                    }
                    createLabelInHeaderSection(headerData.getName(), width, backgroundColor, labelColor);

                    previousXLocation = xLocation;
                    previousWidth = width;
                }
            }
            this.layout(true, true);
        } finally {
            headerSection.setRedraw(true);
        }
    }

    /**
     * Get the headerData of the current editor.
     * 
     * @return a list of {@link HeaderData}
     */
    private LinkedList<HeaderData> getHeaderData() {
        LinkedList<HeaderData> headerDatas = Lists.newLinkedList();
        if (dDiagram != null) {
            for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
                if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(dDiagram.getDescription().eClass().getEPackage())) {
                    headerDatas = diagramTypeDescriptor.getDiagramDescriptionProvider().getHeaderData(dDiagram);
                    break;
                }
            }
        }
        return headerDatas;
    }

    /**
     * Create a label in the header section.
     * 
     * @param text
     *            the text of this label
     * @param width
     *            ThecreateLabelInHeaderSection width of this label.
     * @param backgroundColor
     *            the background color of this label
     * @param labelColor
     *            the color of this label
     * @return the created label
     */
    protected Label createLabelInHeaderSection(String text, int width, Color backgroundColor, Color labelColor) {
        // Create a new parent composite to center the label vertically.
        Composite labelContainer = new Composite(headerSection, SWT.NONE);
        GridData gd = new GridData(GridData.FILL_VERTICAL);
        gd.widthHint = width;
        labelContainer.setLayoutData(gd);
        GridLayout gl = new GridLayout(1, true);
        gl.horizontalSpacing = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        gl.verticalSpacing = 0;
        labelContainer.setLayout(gl);
        labelContainer.setBackground(backgroundColor);

        final Label label = new Label(labelContainer, SWT.CENTER | SWT.HORIZONTAL | SWT.WRAP);
        label.setText(text);
        label.setToolTipText(text);
        label.setCursor(DISABLED_CURSOR);
        label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
        label.setBackground(backgroundColor);
        label.setForeground(labelColor);
        labelContainer.addControlListener(new ControlListener() {
            /**
             * When the label container is resized the displayed label is
             * recomputed to truncate it if needed.
             * 
             * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
             */
            public void controlResized(ControlEvent e) {
                String currentText = label.getText();
                String truncatedText = SWTUtil.shortenText(label.getToolTipText(), label, DiagramHeaderComposite.getDiagramHeaderLineHeight());
                if (!currentText.equals(truncatedText)) {
                    label.setText(truncatedText);
                    label.getParent().layout();
                }
            }

            public void controlMoved(ControlEvent e) {
            }
        });

        return label;
    }

    /**
     * Get number of lines that is needed to display entirely all the header
     * labels according to the current widths.
     * 
     * @return
     */
    private int getNbLinesNeeded() {
        int maxNbLines = 1;
        Control[] headerLabels = headerSection.getChildren();
        for (int i = 0; i < headerLabels.length; i++) {
            // Get the label Control
            Label label = (Label) ((Composite) headerLabels[i]).getChildren()[0];
            int nbLines = getNbLines(label.getToolTipText(), headerLabels[i]);
            if (maxNbLines < nbLines) {
                maxNbLines = nbLines;
            }
        }
        return maxNbLines;
    }

    /**
     * Get the number of lines needed to display the <code>text</code> according
     * to the font and the width of this <code>control</code>.
     * 
     * @param text
     *            The text to display
     * @param control
     *            The control (for using its font and width).
     * @return The number of lines needed.
     */
    private int getNbLines(String text, Control control) {
        int maxWidth = control.getBounds().width;
        return SWTUtil.getNbLines(control, text, maxWidth);
    }

    /**
     * Add listeners and initialize this composite for the given graphical
     * viewer.
     * <p>
     * The primaryViewer or its Control cannot be <code>null</code>. The
     * primaryViewer's Control should be a FigureCanvas and a child of this
     * Composite. This method should only be invoked once.
     * <p>
     * 
     * @param primaryViewer
     *            The graphical viewer for which this composite have to be
     *            created
     */
    public void setGraphicalViewer(ScrollingGraphicalViewer primaryViewer) {
        // pre-conditions
        Assert.isNotNull(primaryViewer);
        Assert.isNotNull(primaryViewer.getControl());
        Assert.isTrue(diagramViewer == null);

        diagramViewer = primaryViewer;

        diagramPropertyListener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                String property = evt.getPropertyName();
                if (RulerProvider.PROPERTY_RULER_VISIBILITY.equals(property))
                    setRulerVisibility(((Boolean) diagramViewer.getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY)).booleanValue());
            }
        };
        diagramViewer.addPropertyChangeListener(diagramPropertyListener);
        // Add listener on Viewport to detect change on horizontal scroll bar
        Viewport port = ((FigureCanvas) diagramViewer.getControl()).getViewport();
        portPropertyListener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                String property = evt.getPropertyName();
                if (FreeformViewport.PROPERTY_VIEW_LOCATION.equals(property) && evt.getSource() instanceof Viewport) {
                    Viewport viewport = (Viewport) evt.getSource();
                    xOrigin = viewport.getHorizontalRangeModel().getValue();

                }
                rebuildHeaderSection();
            }
        };
        port.addPropertyChangeListener(portPropertyListener);
        // Add a listener on zoom
        ZoomManager zoomManager = (ZoomManager) part.getDiagramEditPart().getViewer().getProperty(ZoomManager.class.toString());
        zoomListener = new ZoomListener() {
            public void zoomChanged(double newZoom) {
                zoom = newZoom;
                rebuildHeaderSection();
            }
        };
        zoomManager.addZoomListener(zoomListener);
        // Initialize the ruler visibility, the xOrigin and the zoom
        xOrigin = port.getHorizontalRangeModel().getValue();
        zoom = zoomManager.getZoom();
        Boolean rulerVisibility = (Boolean) diagramViewer.getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);
        if (rulerVisibility != null) {
            setRulerVisibility(rulerVisibility.booleanValue());
        }
    }

    private void setRulerVisibility(boolean isVisible) {
        if (isRulerVisible != isVisible) {
            isRulerVisible = isVisible;
            rebuildHeaderSection();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (part != null) {
            ZoomManager zoomManager = (ZoomManager) part.getDiagramEditPart().getViewer().getProperty(ZoomManager.class.toString());
            zoomManager.removeZoomListener(zoomListener);
        }
        if (diagramViewer != null) {
            Viewport port = ((FigureCanvas) diagramViewer.getControl()).getViewport();
            port.removePropertyChangeListener(portPropertyListener);
            diagramViewer.removePropertyChangeListener(diagramPropertyListener);
        }
        Listener[] selectionListener = sash.getListeners(SWT.Selection);
        for (int i = 0; i < selectionListener.length; i++) {
            sash.removeSelectionListener((SelectionListener) selectionListener[i]);
        }
        sash.dispose();
        sash = null;

        part = null;
        dDiagram = null;
        diagramViewer = null;
    }

    /**
     * Get the header section composite.
     * 
     * @return the header section composite.
     */
    public Composite getHeaderSection() {
        return headerSection;
    }

    /**
     * Set the height of the header section according to the
     * <code>newHeight</code>. The heightHeader is also set in DDiagram.
     * 
     * @param newHeight
     *            The new height in pixels.
     */
    public void setHeaderHeight(int newHeight) {
        GridData gridData = (GridData) getHeaderSection().getLayoutData();
        int currentHeightHint = gridData.heightHint;
        if (currentHeightHint != newHeight) {
            gridData.heightHint = newHeight;

            // Until the parent window does a layout, the sash (and its
            // brothers) will not be redrawn in its new location.
            getParent().layout(true, true);

            TransactionalEditingDomain ted = TransactionUtil.getEditingDomain(dDiagram);
            ted.getCommandStack().execute(new SetCommand(ted, dDiagram, DiagramPackage.eINSTANCE.getDDiagram_HeaderHeight(), newHeight / DiagramHeaderComposite.getDiagramHeaderLineHeight()));
        }
    }
}
