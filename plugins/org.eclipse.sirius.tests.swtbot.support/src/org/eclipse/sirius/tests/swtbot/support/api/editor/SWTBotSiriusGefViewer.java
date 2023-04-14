/**
 * Copyright (c) 2010, 2022, 2023 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.editor;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SameScreenBoundsCondition;
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
        final SWTBotGefEditPart revealedEP = getEditpart(label, mainEditPart().children());
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
        // Compute the initial bounds to use as initial value to compare with, in the waiting condition.
        Rectangle initialBounds = Rectangle.SINGLETON;
        if (revealedEP instanceof IGraphicalEditPart) {
            initialBounds = ((IGraphicalEditPart) revealedEP).getFigure().getBounds().getCopy();
            GraphicalHelper.logical2screen(initialBounds, (IGraphicalEditPart) revealedEP);
        }

        // Reveal the edit part
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                graphicalViewer.reveal(revealedEP);
            }
        });
        if (revealedEP instanceof IGraphicalEditPart) {
            // Ensure that the reveal is graphically finished (to avoid random failure), the reveal launches several
            // runnable to do a smooth scroll. We wait that the figure remains at the same location to consider that the
            // scroll is really finished.
            bot().waitUntil(new SameScreenBoundsCondition((IGraphicalEditPart) revealedEP, initialBounds));
        }
    }

    /**
     * Copy of org.eclipse.draw2d.FigureCanvas.verifyScrollBarOffset(RangeModel, int).
     */
    private int verifyScrollBarOffset(RangeModel model, int value) {
        int newValue = Math.max(model.getMinimum(), value);
        return Math.min(model.getMaximum() - model.getExtent(), newValue);
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

	@Override
	public SWTBotGefEditPart getEditPart(String label) {
		List<SWTBotGefEditPart> allEditParts = mainEditPart().children();
		allEditParts.addAll(mainEditPart().sourceConnections());
		return getEditpart(label, allEditParts);
	}

    /**
     * Override a method that used to be public to deal with Sirius WrapLabel.
     * 
     * @param label
     *            The searched label
     * @param allEditParts
     *            the list of {@link SWTBotGefEditPart} to search in
     * @return the {@link SWTBotGefEditPart} where the label was found
     */
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
            for (Iterator<? extends IFigure> iteratorFigureChildren = figure.getChildren().iterator(); iteratorFigureChildren.hasNext() && !result; /* */) {
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
     * @param dragFinished
     *            An AtomicBoolean allows to add a waiting condition. It was set
     *            to true when the drag is finished.<BR>
     *            Warning: When the drag is finished, the associated figures
     *            have not their "moved" bound. Another
     *            {@link org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils#waitAllUiEvents()}
     *            is needed.
     * @Deprecated replaced by  {@link SWTBotSiriusGefViewer#dragWithKeys(int, int, int, int, AtomicBoolean, int...)}           
     */
    @Deprecated
    public void dragWithKey(final int fromXPosition, final int fromYPosition, final int toXPosition, final int toYPosition, final int keyCode, final AtomicBoolean dragFinished) {
        if (canvas instanceof SWTBotSiriusFigureCanvas) {
            ((SWTBotSiriusFigureCanvas) canvas).mouseDragWithKey(fromXPosition, fromYPosition, toXPosition, toYPosition, keyCode, dragFinished);
        } else {
            canvas.mouseDrag(fromXPosition, fromYPosition, toXPosition, toYPosition);
        }
    }

    /**
     * Drag and drop from the specified to the specified location with keys
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
     * @param dragFinished
     *            An AtomicBoolean allows to add a waiting condition. It was set
     *            to true when the drag is finished.<BR>
     *            Warning: When the drag is finished, the associated figures
     *            have not their "moved" bound. Another
     *            {@link org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils#waitAllUiEvents()}
     *            is needed.
     * @param keyModifiers
     *            the key codes as defined by the key code constants in class
     *            <code>SWT</code> of the keys that should be pressed when doing
     *            the mouse drag.
     */
    public void dragWithKeys(final int fromXPosition, final int fromYPosition, final int toXPosition, final int toYPosition, final AtomicBoolean dragFinished, final int... keyModifiers) {
        if (canvas instanceof SWTBotSiriusFigureCanvas) {
            ((SWTBotSiriusFigureCanvas) canvas).mouseDragWithKeys(fromXPosition, fromYPosition, toXPosition, toYPosition, dragFinished, keyModifiers);
        } else {
            canvas.mouseDrag(fromXPosition, fromYPosition, toXPosition, toYPosition);
        }
    }

    /**
     * This method applies a zoom by mouse wheel scroll with the given key
     * pressed at the given coordinates.
     * 
     * This method is asynchronous so make sure you wait the finishing of all UI
     * events before testing the effect of this method.
     * 
     * @param xPosition
     *            x absolute position of the mouse from which we do the zoom by
     *            mouse wheel scroll.
     * @param yPosition
     *            y absolute position of the mouse from which we do the zoom by
     *            mouse wheel scroll.
     * @param keyCode
     *            the keyboard key that should be pressed when doing the zoom.
     * @param zoomIncrement
     *            the zoom power from original zoom. A positive value for
     *            zoom-in. A negative value for zoom out.
     * @throws UnsupportedOperationException
     *             if the canvas associated to this viewer is not an
     *             SWTBotSiriusFigureCanvas.
     */
    public void mouseScrollWithKey(final int xPosition, final int yPosition, final int keyCode, final int zoomIncrement) {
        if (canvas instanceof SWTBotSiriusFigureCanvas) {
            ((SWTBotSiriusFigureCanvas) canvas).mouseScrollWithKey(xPosition, yPosition, keyCode, zoomIncrement);
        } else {
            throw new UnsupportedOperationException("This method is supported only by SWTBotSiriusFigureCanvas canvas and not by " + canvas.getClass().getSimpleName());
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
    
    /**
     * Click on the editor at the specified location with given key modifiers used
     * at the same time.
     * 
     * @param xPosition
     *            the x relative position
     * @param yPosition
     *            the y relative position
     * @param displayFeedback
     *            true to display feedback, false otherwise.
     * @param keyModifiers
     *            the key modifiers used when doing the click.
     */
    public void click(int xPosition, int yPosition, boolean displayFeedback, int[] keyModifiers) {
        if (canvas instanceof SWTBotSiriusFigureCanvas) {
            ((SWTBotSiriusFigureCanvas) canvas).mouseMoveLeftClick(xPosition, yPosition, displayFeedback, keyModifiers);
        } else {
            canvas.mouseMoveLeftClick(xPosition, yPosition);
        }
    }
}
