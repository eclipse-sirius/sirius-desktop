/**
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.editor;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.tests.swtbot.support.api.widget.SWTBotSiriusFigureCanvas;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefViewer;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.junit.Assert;

/**
 * SWTBotGefViewer for Sirius.
 * 
 * @author nlepine
 * 
 */
public class SWTBotSiriusGefViewer extends SWTBotGefViewer {

    /**
     * 
     * Construct a new instance.
     * 
     * @param graphicalViewer
     *            GraphicalViewer
     * @throws WidgetNotFoundException
     *             Exception
     */
    public SWTBotSiriusGefViewer(GraphicalViewer graphicalViewer) throws WidgetNotFoundException {
        super(graphicalViewer);
        init();
    }

    /**
     * init.
     * 
     * @throws WidgetNotFoundException
     *             if widget not found
     */
    @Override
    protected void init() throws WidgetNotFoundException {
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                final Control control = graphicalViewer.getControl();
                if (control instanceof FigureCanvas) {
                    canvas = new SWTBotSiriusFigureCanvas((FigureCanvas) control);
                } else if (control instanceof Canvas) {
                    if (control instanceof IAdaptable) {
                        IAdaptable adaptable = (IAdaptable) control;
                        Object adapter = adaptable.getAdapter(LightweightSystem.class);
                        if (adapter instanceof LightweightSystem) {
                            canvas = new SWTBotSiriusFigureCanvas((Canvas) control, (LightweightSystem) adapter);
                        }
                    }
                }
                editDomain = graphicalViewer.getEditDomain();
            }
        });

        if (graphicalViewer == null) {
            throw new WidgetNotFoundException("Editor does not adapt to a GraphicalViewer");
        }
    }

    @Override
    public SWTBotGefFigureCanvas getCanvas() {
        return super.getCanvas();
    }

    /**
     * Get the RootEditPart bot for the palette.
     * 
     * @return the corresponding SWTBotGefEditPart
     */
    public SWTBotGefEditPart getPaletteRootEditPartBot() {
        Object o = UIThreadRunnable.syncExec(new Result<Object>() {
            @Override
            public Object run() {
                return createEditPart(editDomain.getPaletteViewer().getRootEditPart());
            }
        });
        if (o instanceof WidgetNotFoundException) {
            throw (WidgetNotFoundException) o;
        }
        return (SWTBotGefEditPart) o;
    }

    /**
     * Get the GroupEditPart bot for the palette corresponding to tool provided
     * by *.odesign modeler.
     * 
     * @return the corresponding SWTBotGefEditPart
     */
    public SWTBotGefEditPart getSiriusPaletteGroupEditPartBot() {
        SWTBotGefEditPart viewpointPaletteGrouEditPartBot = null;
        Object o = UIThreadRunnable.syncExec(new Result<Object>() {
            @Override
            public Object run() {
                return createEditPart(editDomain.getPaletteViewer().getRootEditPart());
            }
        });
        if (o instanceof WidgetNotFoundException) {
            throw (WidgetNotFoundException) o;
        }
        SWTBotGefEditPart paletteRootEditPartBot = (SWTBotGefEditPart) o;
        Assert.assertFalse("The rootEditPart of the palette should contains a SlidablePaletteEditPart", paletteRootEditPartBot.children().isEmpty());
        SWTBotGefEditPart slidablePaletteEditPartBot = paletteRootEditPartBot.children().get(0);
        if (slidablePaletteEditPartBot.children().size() >= 2) {
            viewpointPaletteGrouEditPartBot = slidablePaletteEditPartBot.children().get(1);
        }
        return viewpointPaletteGrouEditPartBot;
    }

    /**
     * Reveal the edit part with the label as a single selection.
     * 
     * @param label
     *            The label of the searched edit part
     */
    public void reveal(String label) {
        final SWTBotGefEditPart revealedEP = getEditPart(label);
        if (revealedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                graphicalViewer.reveal(revealedEP.part());
            }
        });
    }

    /**
     * Reveal the edit part as a single selection.
     * 
     * @param revealedEP
     *            the searched edit part
     */
    public void reveal(final EditPart revealedEP) {
        if (revealedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, revealedEP));
        }
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                graphicalViewer.reveal(revealedEP);
            }
        });
    }

    /**
     * Scrolls the contents to the new location.
     * 
     * @param location
     *            The location to scroll to (the new origin)
     */
    public void scrollTo(Point location) {
        scrollTo(location.x, location.y);
    }

    /**
     * Scrolls the contents to the new x and y location.
     * 
     * @param x
     *            the x coordinate to scroll to
     * @param y
     *            the y coordinate to scroll to
     */
    public void scrollTo(final int x, final int y) {
        if (graphicalViewer.getControl() instanceof FigureCanvas) {
            UIThreadRunnable.syncExec(new VoidResult() {
                @Override
                public void run() {
                    ((FigureCanvas) graphicalViewer.getControl()).scrollTo(x, y);
                }
            });
        }
    }

    /**
     * Override method to manage SiriusWrapLabel.
     * 
     * @param figure
     *            The figure to check
     * @param label
     *            The searched label
     * @return true if the figure is a label, false otherwise
     */
    private boolean isLabel(IFigure figure, String label) {
        boolean result = false;

        if (figure instanceof Label && ((Label) figure).getText().equals(label)) {
            // case 1 : gef label
            result = true;
        } else if (figure instanceof TextFlow && ((TextFlow) figure).getText().equals(label)) {
            // case 2 : no gef label
            result = true;
        } else if (figure instanceof SiriusWrapLabel && ((SiriusWrapLabel) figure).getText().equals(label)) {
            // case 3 : Sirius specific wrap label
            result = true;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * Override to deal with Sirius WrapLabel.
     */
    @Override
    public SWTBotGefEditPart getEditpart(String label, List<SWTBotGefEditPart> allEditParts) {
        SWTBotGefEditPart result = null;
        for (Iterator<SWTBotGefEditPart> iterator = allEditParts.iterator(); iterator.hasNext() && result == null; /* */) {
            SWTBotGefEditPart child = iterator.next();
            IFigure figure = ((GraphicalEditPart) child.part()).getFigure();

            if (isLabel(figure, label)) {
                result = child;
            } else {
                SWTBotGefEditPart childEditPart = getEditPart(child, label);
                if (childEditPart != null) {
                    result = childEditPart;
                } else

                if (findLabelFigure(figure, label)) {
                    result = child;
                }
            }

        }
        return result;
    }

    /**
     * Duplicate method because method isLabel is private.
     * 
     * @param figure
     *            The figure to check
     * @param label
     *            The figure to check
     * @return true if this figure corresponds to the searched label
     */
    private boolean findLabelFigure(IFigure figure, String label) {
        boolean result = false;
        if (isLabel(figure, label)) {
            result = true;
        } else {
            for (Iterator<Object> iteratorFigureChildren = figure.getChildren().iterator(); iteratorFigureChildren.hasNext() && !result; /* */) {
                Object figureChild = iteratorFigureChildren.next();
                if (isLabel((IFigure) figureChild, label) || findLabelFigure((IFigure) figureChild, label)) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Duplicate method because method isLabel is private. <BR>
     * get this edit part with the label as a single selection
     */
    private SWTBotGefEditPart getEditPart(SWTBotGefEditPart editPart, String label) {
        if (editPart.children().isEmpty() && findLabelFigure(((GraphicalEditPart) editPart.part()).getFigure(), label)) {
            return editPart;
        }

        List<SWTBotGefEditPart> allEditParts = editPart.children();
        allEditParts.addAll(editPart.sourceConnections());
        return getEditpart(label, allEditParts);
    }

    /**
     * Drag and drop from the specified to the specified location with a key
     * pressed during the drag'n'drop. This method also correctly handles the
     * move of a bendpoint of an edge.
     * 
     * @param fromXPosition
     *            the relative x position to drag from
     * @param fromYPosition
     *            the relative y position to drag from
     * @param toXPosition
     *            the relative x position to drag to
     * @param toYPosition
     *            the relative y position to drag to
     * @param keyCode
     *            the key code of the key that was typed, as defined by the key
     *            code constants in class <code>SWT</code>, or
     *            {@link org.eclipse.swt.SWT#None} if no key. @see
     *            org.eclipse.swt.SWT
     */
    public void dragWithKey(final int fromXPosition, final int fromYPosition, final int toXPosition, final int toYPosition, final int keyCode) {
        if (canvas instanceof SWTBotSiriusFigureCanvas) {
            ((SWTBotSiriusFigureCanvas) canvas).mouseDragWithKey(fromXPosition, fromYPosition, toXPosition, toYPosition, keyCode);
        } else {
            canvas.mouseDrag(fromXPosition, fromYPosition, toXPosition, toYPosition);
        }
    }

    /**
     * Click on the editor at the specified location.
     * 
     * @param xPosition
     *            the x relative position
     * @param yPosition
     *            the y relative position
     * @param displayFeedback
     *            true to display feedback, false otherwise.
     */
    public void click(final int xPosition, final int yPosition, boolean displayFeedback) {
        if (canvas instanceof SWTBotSiriusFigureCanvas) {
            ((SWTBotSiriusFigureCanvas) canvas).mouseMoveLeftClick(xPosition, yPosition, displayFeedback);
        } else {
            canvas.mouseMoveLeftClick(xPosition, yPosition);
        }
    }
}
