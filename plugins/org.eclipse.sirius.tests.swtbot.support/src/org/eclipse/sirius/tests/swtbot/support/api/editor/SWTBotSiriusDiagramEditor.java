/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToGridEx;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUIServices;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.bot.SWTDesignerBot;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionSavedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.sirius.tests.swtbot.support.api.widget.SWTBotSiriusFigureCanvas;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Override some methods to GEF editor.
 * 
 * @author nlepine
 */
public class SWTBotSiriusDiagramEditor extends SWTBotGefEditor {
    static final String EXPECTED_TO_FIND_WIDGET_S = "Expected to find widget %s";

    private static final String EXPECTED_TO_FIND_WIDGET_X_Y = "Expected to find widget at %s, %s";

    private static final String EXPECTED_TO_FIND_WIDGET_OF_TYPE_T = "Expected to find widget of type %s";

    private static final String EXPECTED_TO_FIND_GRAPHICAL_EDIT_PART_S = "Expected to find widget %s of type IGraphicalEditPart";

    /**
     * Inner special SWTBot.
     */
    protected SWTDesignerBot designerBot = new SWTDesignerBot();

    /**
     * Construct a new instance.
     * 
     * @param reference
     *            the editor reference
     * @param bot
     *            the workbench bot
     * @throws WidgetNotFoundException
     *             if an exception occurs
     */
    public SWTBotSiriusDiagramEditor(final IEditorReference reference, final SWTWorkbenchBot bot) throws WidgetNotFoundException {
        super(reference, bot);

        GraphicalViewer graphicalViewer = UIThreadRunnable.syncExec(new Result<GraphicalViewer>() {
            @Override
            public GraphicalViewer run() {

                final IEditorPart editor = partReference.getEditor(true);
                return (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
            }
        });

        ReflectionHelper.setFieldValueWithoutException(this, "viewer", new SWTBotSiriusGefViewer(graphicalViewer), this.getClass().getSuperclass());
    }

    @Override
    public void save() {
        super.save();
        IEditorPart editor = partReference.getEditor(false);
        if (editor instanceof DialectEditor) {
            DRepresentation dRepresentation = ((DialectEditor) editor).getRepresentation();
            if (dRepresentation != null) {
                Session session = new EObjectQuery(dRepresentation).getSession();
                if (session != null) {
                    designerBot.waitUntil(new SessionSavedCondition(session));
                }
            }
        }
    }

    /**
     * Returns the canvas.
     * 
     * @return the canvas.
     */
    public SWTBotGefFigureCanvas getCanvas() {
        Option<Object> fieldValueWithoutException = ReflectionHelper.getFieldValueWithoutException(getSWTBotGefViewer(), "canvas", getSWTBotGefViewer().getClass().getSuperclass());
        if (fieldValueWithoutException.some() && fieldValueWithoutException.get() instanceof SWTBotGefFigureCanvas) {
            return (SWTBotGefFigureCanvas) fieldValueWithoutException.get();
        }

        return null;
    }

    @Override
    public void click(final String label) {
        final SWTBotGefEditPart selectedEP = getEditPart(label);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        final Rectangle bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds().getCopy();
        ((GraphicalEditPart) selectedEP.part()).getFigure().translateToAbsolute(bounds);
        /* add height -1 for designer */
        click(bounds.x, bounds.y + bounds.height - 1);
    }

    /**
     * Click on the edit part (of correct type) which owns the specified label
     * at the top left hand corner of its bounds.
     * 
     * @param label
     *            the label to retrieve edit part to click on
     * @param expectedEditPartType
     *            the type of the editPart to return if found one
     */
    public void click(final String label, final Class<? extends EditPart> expectedEditPartType) {
        final SWTBotGefEditPart selectedEP = getEditPart(label, expectedEditPartType);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        final Rectangle bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds().getCopy();
        ((GraphicalEditPart) selectedEP.part()).getFigure().translateToAbsolute(bounds);
        /* add height - 1 for designer */
        click(bounds.x, bounds.y + bounds.height - 1);
    }

    /**
     * Helper to click with a Point as parameter.
     * 
     * @param point
     *            the coordinate to click on described as a Point. This
     *            coordinates is relative to screen.
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor#click(int,
     *      int)
     */
    public void click(final Point point) {
        click(point.x, point.y);
    }

    /**
     * Helper to click with a Point as parameter.
     * 
     * @param point
     *            the coordinate to click on described as a Point. This
     *            coordinates is relative to screen.
     * @param displayFeedback
     *            true to display feedback, false otherwise.
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor#click(int,
     *      int)
     */
    public void click(final Point point, boolean displayFeedback) {
        ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).click(point.x, point.y, displayFeedback);
    }

    /**
     * Clicks on the center of the targeted GraphicalEditPart known by its
     * label.
     * 
     * @param label
     *            the label of the GraphicalEditPart to click on its center.
     * @return The absolute bounds of the edit part
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor#click(java.lang.String)
     */
    public Rectangle clickCentered(final String label) {
        final SWTBotGefEditPart selectedEP = getEditPart(label);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        final Rectangle bounds;
        if (selectedEP.part() instanceof AbstractDiagramNameEditPart) {
            bounds = ((GraphicalEditPart) selectedEP.part().getParent()).getFigure().getBounds().getCopy();
            ((GraphicalEditPart) selectedEP.part().getParent()).getFigure().translateToAbsolute(bounds);
        } else {
            bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds().getCopy();
            ((GraphicalEditPart) selectedEP.part()).getFigure().translateToAbsolute(bounds);
        }
        /* add height for designer */
        click(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
        return bounds;
    }

    /**
     * Clicks on the center of the targeted GraphicalEditPart known by its
     * label.
     * 
     * @param label
     *            the label of the GraphicalEditPart to click on its center.
     * @param expectedEditPartType
     *            the type of the editPart to return if found one
     * @return The absolute bounds of the edit part
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor#click(java.lang.String)
     */
    public Rectangle clickCentered(final String label, final Class<? extends EditPart> expectedEditPartType) {
        // Get the corresponding edit part.
        final SWTBotGefEditPart selectedEP = getEditPart(label, expectedEditPartType);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        return clickCentered(selectedEP);
    }

    /**
     * Clicks on the center of the targeted GraphicalEditPart known by its label
     * and then return its bounds.
     * 
     * @param partToClick
     *            GraphicalEditPart to click on its center.
     * @return The absolute bounds of the edit part
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor#click(java.lang.String)
     */
    public Rectangle clickCentered(SWTBotGefEditPart partToClick) {
        // Get the absolute bounds of the edit part.
        Assert.assertTrue("This editPart should be a GraphicalEditPart.", partToClick.part() instanceof GraphicalEditPart);
        Rectangle bounds = ((GraphicalEditPart) partToClick.part()).getFigure().getBounds().getCopy();
        ((GraphicalEditPart) partToClick.part()).getFigure().translateToAbsolute(bounds);
        // Click on its center
        click(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
        // Return the absolute bounds.
        return bounds;
    }

    /**
     * Clicks just below (+1 pixel) of the middle of the north side of the
     * targeted GraphicalEditPart and then return its bounds.
     * 
     * @param partToClick
     *            GraphicalEditPart to click on its center.
     * @return The absolute bounds of the edit part
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor#click(java.lang.String)
     */
    public Rectangle clickNorth(SWTBotGefEditPart partToClick) {
        // Get the absolute bounds of the edit part.
        Assert.assertTrue("This editPart should be a GraphicalEditPart.", partToClick.part() instanceof GraphicalEditPart);
        Rectangle bounds = ((GraphicalEditPart) partToClick.part()).getFigure().getBounds().getCopy();
        ((GraphicalEditPart) partToClick.part()).getFigure().translateToAbsolute(bounds);
        // Click on its center
        click(bounds.getTop().x, bounds.getTop().y + 1);
        // Return the absolute bounds.
        return bounds;
    }

    @Override
    public void drag(final String label, final int toXPosition, final int toYPosition) {
        final SWTBotGefEditPart selectedEP = getEditPart(label);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        final Rectangle bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds();
        drag(bounds.x, bounds.y + bounds.height / 2, toXPosition, toYPosition);
    }

    /**
     * 
     * Uses the generic mouseDrag functionality to drag the item from its
     * center.
     * 
     * @param label
     *            the label
     * @param toXPosition
     *            the relative x position within the graphical viewer to drag to
     * @param toYPosition
     *            the relative y position within the graphical viewer to drag to
     * 
     * @see org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor#drag(java.lang.String,
     *      int, int)
     */
    public void dragCentered(final String label, final int toXPosition, final int toYPosition) {
        final SWTBotGefEditPart selectedEP = getEditPart(label);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        final Rectangle bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds();
        drag(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2, toXPosition, toYPosition);
    }

    /**
     * Get the EditPartBot representing the EditPart at this location.
     * 
     * @param location
     *            the Point where to search a EditPart. This coordinates is
     *            logical (not relative to screen).
     * @param expectedEditPartType
     *            the type of the editPart to return if found one
     * @return the EditPartBot representing the EditPart at this location
     */
    public SWTBotGefEditPart getEditPart(Point location, Class<? extends EditPart> expectedEditPartType) {
        return getEditPart(mainEditPart(), location, expectedEditPartType);
    }

    /**
     * Get the EditPartBot representing the EditPart at this location.
     * 
     * @param editPartBotWhereToSearch
     *            the EditPartBot where to search.
     * @param location
     *            the Point where to search a EditPart
     * @param expectedEditPartType
     *            the type of the editPart to return if found one
     * @return the EditPartBot representing the EditPart at this location
     */
    public SWTBotGefEditPart getEditPart(SWTBotGefEditPart editPartBotWhereToSearch, Point location, Class<? extends EditPart> expectedEditPartType) {
        SWTBotGefEditPart result = null;
        if (!editPartBotWhereToSearch.children().isEmpty()) {
            for (SWTBotGefEditPart childEditPartBot : editPartBotWhereToSearch.children()) {
                SWTBotGefEditPart candidate = getEditPart(childEditPartBot, location, expectedEditPartType);
                if (candidate == null) {
                    if (getBounds(childEditPartBot).contains(location) && expectedEditPartType.isInstance(childEditPartBot.part())) {
                        candidate = childEditPartBot;
                    }
                }
                if (candidate != null && result != null) {
                    boolean sameParent = result.part().getParent().equals(candidate.part().getParent());
                    boolean indexSuperior = candidate.part().getParent().getChildren().indexOf(candidate.part()) > result.part().getParent().getChildren().indexOf(result.part());
                    if (sameParent && indexSuperior) {
                        result = candidate;
                    }
                } else if (result == null) {
                    result = candidate;
                }
            }
        } else {
            if (getBounds(editPartBotWhereToSearch).contains(location) && expectedEditPartType.isInstance(editPartBotWhereToSearch.part())) {
                result = editPartBotWhereToSearch;
            }
        }
        return result;
    }

    @Override
    public SWTBotGefEditPart getEditPart(final String label) {
        final SWTBotGefEditPart selectedEP = super.getEditPart(label);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        return selectedEP;
    }

    /**
     * Get the edit part with the label as a singleton selection. If the edit
     * part corresponding to the label is not of the expected type, we searched
     * in the parent hierarchy of this edit part.
     * 
     * @param label
     *            The searched label
     * @param expectedEditPartType
     *            The type of the expected edit part
     * @return the corresponding SWTBotGefEditPart
     */
    public SWTBotGefEditPart getEditPart(final String label, final Class<? extends EditPart> expectedEditPartType) {
        SWTBotGefEditPart selectedEP = super.getEditPart(label);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        if (!expectedEditPartType.isInstance(selectedEP.part())) {
            selectedEP = getParentEditPart(selectedEP, expectedEditPartType);
        }
        return selectedEP;
    }

    /**
     * Search in the parent hierarchy of <code>editPart</code>, the first one of
     * type <code>expectedEditPartType</code>.
     * 
     * @param editPart
     *            The start editPart
     * @param expectedEditPartType
     *            The type of the expected edit part
     * @return the corresponding SWTBotGefEditPart
     */
    private SWTBotGefEditPart getParentEditPart(SWTBotGefEditPart editPart, final Class<? extends EditPart> expectedEditPartType) {
        SWTBotGefEditPart parent = editPart.parent();
        if (parent == null || parent.part() == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_OF_TYPE_T, expectedEditPartType.getCanonicalName()));
        }
        if (!expectedEditPartType.isInstance(parent.part())) {
            parent = getParentEditPart(parent, expectedEditPartType);
        }
        return parent;
    }

    /**
     * Get the RootEditPart bot for the palette.
     * 
     * @return the corresponding SWTBotGefEditPart
     */
    public SWTBotGefEditPart getPaletteRootEditPartBot() {
        return ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).getPaletteRootEditPartBot();
    }

    /**
     * Get the GroupEditPart bot for the palette corresponding to tool provided
     * by *.odesign modeler.
     * 
     * @return the corresponding SWTBotGefEditPart
     */
    public SWTBotGefEditPart getSiriusPaletteGroupEditPartBot() {
        return ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).getSiriusPaletteGroupEditPartBot();
    }

    /**
     * Get all {@link SWTBotGefConnectionEditPart} between the edit parts.
     * 
     * @param sourceEditPart
     *            Source edit part.
     * @param targetEditPart
     *            Target edit part.
     * @return All connection edit parts (cannot be <code>null</code>)
     */
    public List<SWTBotGefConnectionEditPart> getConnectionEditPart(final SWTBotGefEditPart sourceEditPart, final SWTBotGefEditPart targetEditPart) {
        final Set<SWTBotGefConnectionEditPart> sourceConnections = new HashSet<SWTBotGefConnectionEditPart>(sourceEditPart.sourceConnections());
        final Set<SWTBotGefConnectionEditPart> targetConnections = new HashSet<SWTBotGefConnectionEditPart>(targetEditPart.targetConnections());

        sourceConnections.retainAll(targetConnections);

        return new ArrayList<SWTBotGefConnectionEditPart>(sourceConnections);
    }

    /**
     * Get all {@link SWTBotGefConnectionEditPart}.
     * 
     * @return All connection edit parts (cannot be <code>null</code>)
     */
    public List<SWTBotGefConnectionEditPart> getConnectionsEditPart() {
        Set<SWTBotGefConnectionEditPart> resultList = new HashSet<SWTBotGefConnectionEditPart>();
        resultList.addAll(mainEditPart().sourceConnections());
        resultList.addAll(mainEditPart().targetConnections());
        Iterator<SWTBotGefEditPart> iteratorchildren = getAllChildrenSWTBotGefEditPart(mainEditPart()).iterator();

        while (iteratorchildren.hasNext()) {
            SWTBotGefEditPart currentEditPart = iteratorchildren.next();
            resultList.addAll(currentEditPart.sourceConnections());
            resultList.addAll(currentEditPart.targetConnections());
        }

        return new ArrayList<SWTBotGefConnectionEditPart>(resultList);
    }

    /**
     * Returns the current selection in the active part. If the selection in the
     * active part is <em>undefined</em> (the active part has no selection
     * provider) the result will be <code>null</code>.
     * 
     * @return the current selection, or <code>null</code> if undefined
     */
    public ISelection getSelection() {
        return partReference.getPage().getSelection();
    }

    /**
     * Do direct edit part for nodes.
     * 
     * @param editPartLabel
     *            Label of edit part to edit text.
     * @param text
     *            New text to use to edit edit part name.
     */
    public void directNodeEditType(final String editPartLabel, final String text) {
        directEditType(text, getEditPart(editPartLabel));
    }

    /**
     * Tells if direct edit is possible on the specified
     * {@link SWTBotGefEditPart}.
     * 
     * @param swtBotGefEditPart
     *            the specified {@link SWTBotGefEditPart}
     * @return true if direct edit is possible on the specified
     *         {@link SWTBotGefEditPart}, false else
     */
    public boolean isDirectEditPossible(SWTBotGefEditPart swtBotGefEditPart) {
        boolean isDirectEditPossible = false;
        final EditPart editPart = swtBotGefEditPart.part();
        final DirectEditRequest request = new DirectEditRequest();

        /*
         * Workaround for GMF based modelers -> need to be in a SWTBotGMFEditor
         */
        request.getExtendedData().put(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR, '_');

        if (editPart instanceof GraphicalEditPart) {
            // Sets the location to avoid having NPE later
            GraphicalEditPart gep = (GraphicalEditPart) editPart;
            request.setLocation(gep.getFigure().getBounds().getLocation());
        }

        isDirectEditPossible = UIThreadRunnable.syncExec(new Result<Boolean>() {
            @Override
            public Boolean run() {
                editPart.performRequest(request);
                Matcher<Text> matcher = WidgetMatcherFactory.allOf(WidgetMatcherFactory.widgetOfType(Text.class));
                List<Text> findControls = bot().getFinder().findControls(matcher);
                return !findControls.isEmpty();
            }
        });
        return isDirectEditPossible;
    }

    /**
     * Do direct edit for {@link SWTBotGefEditPart}'s editPart.
     * 
     * @param text
     *            text to put
     * @param swtBotGefEditPart
     *            bot editPart to edit
     */
    public void directEditType(final String text, final SWTBotGefEditPart swtBotGefEditPart) {
        final EditPart editPart = swtBotGefEditPart.part();
        final DirectEditRequest request = new DirectEditRequest();

        /*
         * Workaround for GMF based modelers -> need to be in a SWTBotGMFEditor
         */
        request.getExtendedData().put(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR, '_');

        if (editPart instanceof GraphicalEditPart) {
            // Sets the location to avoid having NPE later
            GraphicalEditPart gep = (GraphicalEditPart) editPart;
            request.setLocation(gep.getFigure().getBounds().getLocation());
        }

        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                editPart.performRequest(request);
                directEditType(text);
            }
        });

    }

    private void directEditType(final String text, final EditPart editPart) {
        final Request request = new DirectEditRequest();

        /*
         * Workaround for GMF based modelers -> need to be in a SWTBotGMFEditor
         */
        request.getExtendedData().put(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR, '_');
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                editPart.performRequest(request);
                directEditType(text);
            }
        });
    }

    /**
     * Do direct edit part for edges.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     */
    public void directEdgeEditType(final String sourceEditPartLabel, final String targetEditPartLabel, final String text) {
        directEdgeEditType(sourceEditPartLabel, targetEditPartLabel, text, 0, DEdgeNameEditPart.class);
    }

    /**
     * Do direct edit part for edges.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     */
    public void directEdgeEditTypeBeginLabel(final String sourceEditPartLabel, final String targetEditPartLabel, final String text) {
        directEdgeEditType(sourceEditPartLabel, targetEditPartLabel, text, 0, DEdgeBeginNameEditPart.class);
    }

    /**
     * Do direct edit part for edges.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     */
    public void directEdgeEditTypeCenterLabel(final String sourceEditPartLabel, final String targetEditPartLabel, final String text) {
        directEdgeEditType(sourceEditPartLabel, targetEditPartLabel, text);
    }

    /**
     * Do direct edit part for edges.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     */
    public void directEdgeEditTypeEndLabel(final String sourceEditPartLabel, final String targetEditPartLabel, final String text) {
        directEdgeEditType(sourceEditPartLabel, targetEditPartLabel, text, 0, DEdgeEndNameEditPart.class);
    }

    /**
     * Do direct edit part for edges connected on nodes or its border nodes with
     * label sourceEditPartLabel and targetEditPartLabel.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     */
    public void directEdgeEditTypeExtendedToBorderNodes(final String sourceEditPartLabel, final String targetEditPartLabel, final String text) {
        directEdgeEditTypeExtendedToBorderNodes(sourceEditPartLabel, targetEditPartLabel, text, 0);
    }

    /**
     * Do direct edit part for edges connected on border nodes of the nodes with
     * label sourceEditPartLabel and targetEditPartLabel.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     */
    public void directEdgeEditTypeOnBorderNodesOnly(final String sourceEditPartLabel, final String targetEditPartLabel, final String text) {
        directEdgeEditTypeOnBorderNodesOnly(sourceEditPartLabel, targetEditPartLabel, text, 0);
    }

    /**
     * Do direct edit part for edges.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     * @param index
     *            Position of edge to edit.
     * @param labelClass
     *            used to choose between begin, center and end label
     */
    public void directEdgeEditType(final String sourceEditPartLabel, final String targetEditPartLabel, final String text, final int index, Class<? extends EditPart> labelClass) {
        final List<SWTBotGefConnectionEditPart> connectionEditPart = getConnectionEditPart(getEditPart(sourceEditPartLabel).parent(), getEditPart(targetEditPartLabel).parent());
        final SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = connectionEditPart.get(index);

        // Retreive first children, as this is this edit part which owns edge
        // label

        // directEditType(text, (EditPart)
        // swtBotGefConnectionEditPart.part().getChildren().get(0));
        directEditType(text, Iterables.getOnlyElement(Iterables.filter(swtBotGefConnectionEditPart.part().getChildren(), labelClass)));
    }

    /**
     * Do direct edit part for edges connected on border nodes of the nodes with
     * label sourceEditPartLabel and targetEditPartLabel.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     * @param index
     *            Position of edge to edit.
     */
    public void directEdgeEditTypeOnBorderNodesOnly(final String sourceEditPartLabel, final String targetEditPartLabel, final String text, final int index) {
        final List<SWTBotGefConnectionEditPart> connectionEditPart = new ArrayList<SWTBotGefConnectionEditPart>();
        SWTBotGefEditPart parentSource = getEditPart(sourceEditPartLabel).parent();
        SWTBotGefEditPart parentTarget = getEditPart(targetEditPartLabel).parent();
        List<SWTBotGefEditPart> sourceChildren = getAllChildrenSWTBotGefEditPart(parentSource);
        List<SWTBotGefEditPart> targetChildren = getAllChildrenSWTBotGefEditPart(parentTarget);
        for (SWTBotGefEditPart sourceChild : sourceChildren) {
            for (SWTBotGefEditPart targetChild : targetChildren) {
                connectionEditPart.addAll(getConnectionEditPart(sourceChild, targetChild));
            }
        }

        final SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = connectionEditPart.get(index);

        // Retreive first children, as this is this edit part which owns edge
        // label
        directEditType(text, (EditPart) swtBotGefConnectionEditPart.part().getChildren().get(0));
    }

    /**
     * Do direct edit part for edges connected on nodes or its border nodes with
     * label sourceEditPartLabel and targetEditPartLabel.
     * 
     * @param sourceEditPartLabel
     *            Source edit part label
     * @param targetEditPartLabel
     *            Target edit part label
     * 
     * @param text
     *            New text to use to edit edit part name.
     * @param index
     *            Position of edge to edit.
     */
    public void directEdgeEditTypeExtendedToBorderNodes(final String sourceEditPartLabel, final String targetEditPartLabel, final String text, final int index) {
        SWTBotGefEditPart parentSource = getEditPart(sourceEditPartLabel).parent();
        SWTBotGefEditPart parentTarget = getEditPart(targetEditPartLabel).parent();
        final List<SWTBotGefConnectionEditPart> connectionEditPart = getConnectionEditPart(parentSource, parentTarget);
        List<SWTBotGefEditPart> sourceChildren = getAllChildrenSWTBotGefEditPart(parentSource);
        List<SWTBotGefEditPart> targetChildren = getAllChildrenSWTBotGefEditPart(parentTarget);
        for (SWTBotGefEditPart sourceChild : sourceChildren) {
            connectionEditPart.addAll(getConnectionEditPart(sourceChild, parentTarget));
            for (SWTBotGefEditPart targetChild : targetChildren) {
                connectionEditPart.addAll(getConnectionEditPart(parentSource, targetChild));
                connectionEditPart.addAll(getConnectionEditPart(sourceChild, targetChild));
            }
        }

        final SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = connectionEditPart.get(index);

        // Retreive first children, as this is this edit part which owns edge
        // label
        directEditType(text, (EditPart) swtBotGefConnectionEditPart.part().getChildren().get(0));
    }

    /**
     * finds all SWTBotGefEditPart children in a SWTBotGefEditPart.
     * 
     * @param swtbotGefEditPart
     *            the parent
     * @return all SWTBotGefEditPart children
     */
    private List<SWTBotGefEditPart> getAllChildrenSWTBotGefEditPart(SWTBotGefEditPart swtbotGefEditPart) {
        ArrayList<SWTBotGefEditPart> swtbotGefEditPartList = Lists.newArrayList();
        swtbotGefEditPartList.addAll(swtbotGefEditPart.children());
        for (SWTBotGefEditPart swtBotGefEditPartChild : swtbotGefEditPart.children()) {
            swtbotGefEditPartList.addAll(getAllChildrenSWTBotGefEditPart(swtBotGefEditPartChild));
        }
        return swtbotGefEditPartList;
    }

    @Override
    public SWTBotGefEditor clickContextMenu(String text) throws WidgetNotFoundException {
        SWTBotGefEditor swtBotGefEditor = super.clickContextMenu(text);
        SWTBotUtils.waitAllUiEvents();
        return swtBotGefEditor;
    }

    /**
     * Right click at the specified location and choose the specified button.
     * 
     * @param rightClickLocation
     *            the location of the click
     * @param text
     *            the label of the button to click
     * 
     * @throws WidgetNotFoundException
     *             thrown if not button with the specified label is found
     */
    public void clickContextMenu(final Point rightClickLocation, String text) throws WidgetNotFoundException {
        // 1. Simulate the move
        // 1.1 one click to select the correct editPart
        click(rightClickLocation);
        // 1.1 and set the real location
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                FigureCanvas control = (FigureCanvas) rootEditPart().part().getRoot().getViewer().getControl();
                org.eclipse.swt.graphics.Point relativeSWTPoint = control.toDisplay(rightClickLocation.x, rightClickLocation.y);
                bot.getDisplay().setCursorLocation(relativeSWTPoint);

            }
        });
        SWTBotUtils.waitAllUiEvents();

        // 2. Click on the action on the contextual menu^M
        super.clickContextMenu(text);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Refresh the diagram (F5).
     * 
     * @throws WidgetNotFoundException
     *             if an exception occurs
     */
    public void refresh() throws WidgetNotFoundException {
        if (TestsUtil.isDynamicTabbar()) {
            bot.toolbarButtonWithTooltip(DiagramDialectUIServices.REFRESH_DIAGRAM).click();
        } else {
            clickContextMenu("Refresh");
        }
    }

    /**
     * Mouse drag.
     * 
     * @param from
     *            From point
     * @param to
     *            To point
     * @see #drag(int, int, int, int)
     */
    public void drag(Point from, Point to) {
        super.drag(from.x, from.y, to.x, to.y);
    }

    /**
     * Mouse drag.
     * 
     * @param from
     *            From point
     * @param toXPosition
     *            the relative x position within the graphical viewer to drag to
     * @param toYPosition
     *            the relative y position within the graphical viewer to drag to
     * @see #drag(int, int, int, int)
     */
    public void drag(Point from, final int toXPosition, final int toYPosition) {
        super.drag(from.x, from.y, toXPosition, toYPosition);
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
    public void dragWithKey(int fromXPosition, int fromYPosition, int toXPosition, int toYPosition, int keyCode) {
        ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).dragWithKey(fromXPosition, fromYPosition, toXPosition, toYPosition, keyCode);
    }

    /**
     * Drag and drop the specified edit part to the specified location.
     * 
     * @param editPartBot
     *            the edit part to drag and drop
     * @param toPosition
     *            the relative location
     */
    public void drag(final SWTBotGefEditPart editPartBot, final Point toPosition) {
        drag(editPartBot, toPosition.x, toPosition.y);
    }

    /**
     * Reveal the edit part with the label as a single selection.
     * 
     * @param label
     *            The label of the searched edit part
     */
    public void reveal(String label) {
        ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).reveal(label);
    }

    /**
     * Reveal the edit part as a single selection.
     * 
     * @param revealedEP
     *            the searched edit part
     */
    public void reveal(final EditPart revealedEP) {
        ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).reveal(revealedEP);
    }

    /**
     * Scrolls the contents to the new location.
     * 
     * @param location
     *            The location to scroll to (the new origin)
     */
    public void scrollTo(Point location) {
        ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).scrollTo(location);
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
        ((SWTBotSiriusGefViewer) getSWTBotGefViewer()).scrollTo(x, y);
    }

    /**
     * Double-click with {@link #doubleClick(String)}, but on edit part with
     * specified name.
     * 
     * @param label
     *            Label of edit part
     * @param editPartClass
     *            Class of edit part
     * @see #doubleClick(String)
     */
    public void doubleClick(String label, Class<? extends EditPart> editPartClass) {
        SWTBotGefEditPart selectedEP = getEditPart(label, editPartClass);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        Rectangle bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds();
        // 3 = MOVE in overridden code
        doubleClick(bounds.x, bounds.y + 3);
    }

    /**
     * Double-click with {@link #doubleClick(String)}, but on edit part with
     * specified name.
     * 
     * @param label
     *            Label of edit part
     * @param editPartClass
     *            Class of edit part
     * @see #doubleClick(String)
     */
    public void doubleClickCentered(String label, Class<? extends EditPart> editPartClass) {
        SWTBotGefEditPart selectedEP = getEditPart(label, editPartClass);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }
        Rectangle bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds();
        // 3 = MOVE in overridden code
        doubleClick(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2 + 3);
    }

    /**
     * Get the figure location corresponding to that name and to the editPart
     * type. The location is relative to the screen.<BR>
     * Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a position at (80, 80) return (40, 40)</LI>
     * <LI>If the vertical scroll bar is set to 40 a position at (80, 80) return
     * (80, 40)</LI>
     * </UL>
     * 
     * @param editPartName
     *            The searched name
     * @param expectedEditPartType
     *            The expected type of edit part (go up in the parent hierarchy
     *            to find this type).
     * @return A location
     */
    public Point getLocation(final String editPartName, final Class<? extends EditPart> expectedEditPartType) {
        final SWTBotGefEditPart editPartBot = getEditPart(editPartName, expectedEditPartType);
        if (editPartBot == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, editPartName));
        }
        return getLocation(editPartBot);
    }

    /**
     * Get the figure location corresponding to that <code>editPart</code>. The
     * location is relative to the screen.<BR>
     * Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a position at (80, 80) return (40, 40)</LI>
     * <LI>If the vertical scroll bar is set to 40 a position at (80, 80) return
     * (80, 40)</LI>
     * </UL>
     * 
     * @param editPart
     *            The edit part we are looking for location.
     * @return A location
     */
    public Point getLocation(SWTBotGefEditPart editPart) {
        return getBounds(editPart).getLocation();
    }

    /**
     * Get the figure bounds corresponding to that editPart bot. The location is
     * relative to the screen.<BR>
     * Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a position at (80, 80) return (40, 40)</LI>
     * <LI>If the vertical scroll bar is set to 40 a position at (80, 80) return
     * (80, 40)</LI>
     * </UL>
     * 
     * @param swtBotGefEditPart
     *            the editPart bot
     * @return A bound
     */
    public Rectangle getBounds(final SWTBotGefEditPart swtBotGefEditPart) {
        if (swtBotGefEditPart == null) {
            throw new NullPointerException();
        }
        final EditPart editPart = swtBotGefEditPart.part();
        if (!(editPart instanceof IGraphicalEditPart)) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_GRAPHICAL_EDIT_PART_S, swtBotGefEditPart));
        }
        IGraphicalEditPart part = (IGraphicalEditPart) editPart;
        Rectangle figureLocation = part.getFigure().getBounds().getCopy();
        part.getFigure().translateToAbsolute(figureLocation);
        return figureLocation;
    }

    /**
     * Get the figure bounds corresponding to that connectionEditPart bot. The
     * location is relative to the screen.<BR>
     * Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a position at (80, 80) return (40, 40)</LI>
     * <LI>If the vertical scroll bar is set to 40 a position at (80, 80) return
     * (80, 40)</LI>
     * </UL>
     * 
     * @param swtBotGefConnectionEditPart
     *            the editPart bot
     * @return A bound
     */
    public Rectangle getBounds(final SWTBotGefConnectionEditPart swtBotGefConnectionEditPart) {
        if (swtBotGefConnectionEditPart == null) {
            throw new NullPointerException();
        }
        final ConnectionEditPart connectionEditPart = swtBotGefConnectionEditPart.part();
        if (!(connectionEditPart instanceof AbstractConnectionEditPart)) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_GRAPHICAL_EDIT_PART_S, swtBotGefConnectionEditPart));
        }
        AbstractConnectionEditPart part = (AbstractConnectionEditPart) connectionEditPart;
        Rectangle figureLocation = part.getConnectionFigure().getPoints().getBounds();
        part.getFigure().translateToAbsolute(figureLocation);
        return figureLocation;
    }

    /**
     * Get the figure dimension corresponding to that name and to the editPart
     * type.<BR>
     * Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a dimension of (80, 80) return (40, 40)
     * </LI>
     * </UL>
     * 
     * @param editPartName
     *            The searched name
     * @param expectedEditPartType
     *            The expected type of edit part (go up in the parent hierarchy
     *            to find this type).
     * @return A dimension
     */
    public Dimension getDimension(final String editPartName, final Class<? extends EditPart> expectedEditPartType) {
        final SWTBotGefEditPart editPartBot = getEditPart(editPartName, expectedEditPartType);
        if (editPartBot == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, editPartName));
        }
        final EditPart editPart = editPartBot.part();
        if (!(editPart instanceof IGraphicalEditPart)) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_GRAPHICAL_EDIT_PART_S, editPartName));
        }
        IGraphicalEditPart part = (IGraphicalEditPart) editPart;
        Dimension figureDimension = part.getFigure().getBounds().getSize();
        part.getFigure().translateToAbsolute(figureDimension);
        return figureDimension;
    }

    /**
     * Get the absolute bounds of the Figure of corresponding to a
     * {@link SWTBotGefEditPart}.
     * 
     * @param swtBotGefEditPart
     *            the {@link SWTBotGefEditPart}
     * @return the absolute bounds
     */
    public Rectangle getAbsoluteBounds(SWTBotGefEditPart swtBotGefEditPart) {
        if (swtBotGefEditPart == null) {
            throw new NullPointerException();
        }
        final EditPart editPart = swtBotGefEditPart.part();
        if (!(editPart instanceof IGraphicalEditPart)) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_GRAPHICAL_EDIT_PART_S, swtBotGefEditPart));
        }
        IGraphicalEditPart part = (IGraphicalEditPart) editPart;
        Rectangle figureBounds = part.getFigure().getBounds().getCopy();
        return figureBounds;
    }

    /**
     * Get the figure location corresponding to that name and to the editPart
     * type. The location is in absolute coordinate (from the origin 0,0).
     * Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a position at (80, 80) return (80, 80)</LI>
     * <LI>If the vertical scroll bar is set to 40 a position at (80, 80) return
     * (80, 80)</LI>
     * </UL>
     * 
     * @param editPartName
     *            The searched name
     * @param expectedEditPartType
     *            The expected type of edit part (go up in the parent hierarchy
     *            to find this type).
     * @return A location
     */
    public Point getAbsoluteLocation(final String editPartName, final Class<? extends EditPart> expectedEditPartType) {
        final SWTBotGefEditPart editPartBot = getEditPart(editPartName, expectedEditPartType);
        if (editPartBot == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, editPartName));
        }
        final EditPart editPart = editPartBot.part();
        if (!(editPart instanceof IGraphicalEditPart)) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_GRAPHICAL_EDIT_PART_S, editPartName));
        }
        IGraphicalEditPart part = (IGraphicalEditPart) editPart;
        return getAbsoluteLocation(part);
    }

    /**
     * Get the figure location corresponding to that editPart type. The location
     * is in absolute coordinate (from the origin 0,0). Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a position at (80, 80) return (80, 80)</LI>
     * <LI>If the vertical scroll bar is set to 40 a position at (80, 80) return
     * (80, 80)</LI>
     * </UL>
     * 
     * @param editPart
     *            The concerned edit part
     * @return A location
     */
    public Point getAbsoluteLocation(final GraphicalEditPart editPart) {
        Point figureLocation = editPart.getFigure().getBounds().getLocation();
        FigureUtilities.translateToAbsoluteByIgnoringScrollbar(editPart.getFigure(), figureLocation);
        return figureLocation;
    }

    /**
     * Get the figure center corresponding to that editPart type. The center is
     * in absolute coordinate (from the origin 0,0). Examples :
     * <UL>
     * <LI>If the zoom is set to 50% a position at (80, 80) return (80, 80)</LI>
     * <LI>If the vertical scroll bar is set to 40 a position at (80, 80) return
     * (80, 80)</LI>
     * </UL>
     * 
     * @param editPart
     *            The concerned edit part
     * @return The center of the figure
     */
    public Point getAbsoluteCenter(final GraphicalEditPart editPart) {
        Point figureCenter = editPart.getFigure().getBounds().getCenter();
        FigureUtilities.translateToAbsoluteByIgnoringScrollbar(editPart.getFigure(), figureCenter);
        return figureCenter;
    }

    /**
     * Searched in all editPart (excepted the connection).
     * 
     * @param xPosition
     *            the relative x position within the graphical viewer
     * @param yPosition
     *            the relative y position within the graphical viewer
     * @return The nearest edit part from this coordinates.
     */
    protected SWTBotGefEditPart getNearestEditPart(final int xPosition, final int yPosition) {
        List<SWTBotGefEditPart> allEditParts = mainEditPart().children();
        allEditParts.addAll(mainEditPart().sourceConnections());
        return getNearestEditPart(new Point(xPosition, yPosition), allEditParts);
    }

    /**
     * Searched in <code>allEditParts</code>, the nearest from the
     * <code>searchedPosition</code>.
     * 
     * @param searchedPosition
     *            The searched position
     * @param allEditParts
     *            The edit parts in which search
     * @return The nearest edit part from this point.
     */
    protected SWTBotGefEditPart getNearestEditPart(final Point searchedPosition, List<SWTBotGefEditPart> allEditParts) {
        SWTBotGefEditPart nearest = null;
        int distance = Integer.MAX_VALUE;
        for (SWTBotGefEditPart child : allEditParts) {
            IFigure figure = ((GraphicalEditPart) child.part()).getFigure();
            if (distance > figure.getBounds().getCenter().getDistance2(searchedPosition)) {
                distance = figure.getBounds().getCenter().getDistance2(searchedPosition);
                nearest = child;
            }
        }
        if (nearest.children().size() > 0) {
            nearest = getNearestEditPart(searchedPosition, nearest.children());
        }
        return nearest;
    }

    /**
     * This method emits mouse events that handle a mouse move and left click to
     * the left bottom corner of the nearest edit part of the coordinates
     * <code>{xPosition, yPosition}</code> .
     * 
     * @param xPosition
     *            the relative x position within the graphical viewer
     * @param yPosition
     *            the relative y position within the graphical viewer
     */
    public void clickNearest(final int xPosition, final int yPosition) {
        final SWTBotGefEditPart selectedEP = getNearestEditPart(xPosition, yPosition);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_X_Y, xPosition, yPosition));
        }
        final Rectangle bounds = ((GraphicalEditPart) selectedEP.part()).getFigure().getBounds();
        ((GraphicalEditPart) selectedEP.part()).getFigure().translateToAbsolute(bounds);
        /* add height for designer */
        click(bounds.x, bounds.y + bounds.height);
    }

    /**
     * This method emits mouse events that handle a mouse move and left click to
     * the center of the folding figure that is nearest of the coordinates
     * <code>{xPosition, yPosition}</code> .
     * 
     * @param xPosition
     *            the relative x position within the graphical viewer
     * @param yPosition
     *            the relative y position within the graphical viewer
     */
    public void clickNearestFoldingFigure(final int xPosition, final int yPosition) {
        final IFigure foldingFigure = getNearestFoldingFigure(new Point(xPosition, yPosition));
        if (foldingFigure == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_X_Y, xPosition, yPosition));
        }
        final Rectangle bounds = foldingFigure.getBounds();
        // ((GraphicalEditPart)
        // selectedEP.part()).getFigure().translateToAbsolute(bounds);
        /* add height for designer */
        click(bounds.getCenter().x, bounds.getCenter().y);
    }

    /**
     * Searched in all editParts, the nearest folding figure from the
     * <code>searchedPosition</code>.
     * 
     * @param searchedPosition
     *            The searched position
     * @return The nearest folding figure from this point.
     */
    protected IFigure getNearestFoldingFigure(Point searchedPosition) {
        List<SWTBotGefEditPart> allEditParts = mainEditPart().children();
        allEditParts.addAll(mainEditPart().sourceConnections());
        return getNearestFoldingFigure(searchedPosition, allEditParts);
    }

    /**
     * Searched in <code>allEditParts</code>, the nearest folding figure from
     * the <code>searchedPosition</code>.
     * 
     * @param searchedPosition
     *            The searched position
     * @param allEditParts
     *            The edit parts in which search
     * @return The nearest folding figure from this point.
     */
    protected IFigure getNearestFoldingFigure(final Point searchedPosition, List<SWTBotGefEditPart> allEditParts) {
        IFigure nearestFoldingFigure = null;
        SWTBotGefEditPart nearestEditPart = null;
        int distance = Integer.MAX_VALUE;
        for (SWTBotGefEditPart child : allEditParts) {
            IFigure figure = ((GraphicalEditPart) child.part()).getFigure();
            if (distance > figure.getBounds().getCenter().getDistance2(searchedPosition)) {
                distance = figure.getBounds().getCenter().getDistance2(searchedPosition);
                nearestEditPart = child;
            }
        }
        if (nearestEditPart != null
                && (!((GraphicalEditPart) nearestEditPart.part()).getSourceConnections().isEmpty() || !((GraphicalEditPart) nearestEditPart.part()).getTargetConnections().isEmpty())) {
            List connections = new ArrayList();
            GraphicalEditPart graphicalEditPart = (GraphicalEditPart) nearestEditPart.part();
            connections.addAll(graphicalEditPart.getSourceConnections());
            connections.addAll(graphicalEditPart.getTargetConnections());
            nearestFoldingFigure = getNearestFoldingFigureInConnections(searchedPosition, connections);
        }
        return nearestFoldingFigure;
    }

    /**
     * Searched in <code>connections</code>, the nearest folding figure from the
     * <code>searchedPosition</code>.
     * 
     * @param searchedPosition
     *            The searched position
     * @param connections
     *            The connections in which search
     * @return The nearest folding figure from this point
     */
    protected IFigure getNearestFoldingFigureInConnections(final Point searchedPosition, final List connections) {
        return null;
        // IFigure nearest = null;
        // int distance = Integer.MAX_VALUE;
        // for (Iterator iterator = connections.iterator(); iterator.hasNext();
        // /* */) {
        // Object object = (Object) iterator.next();
        // if (object instanceof AbstractDiagramEdgeEditPart) {
        // ViewEdgeFigure viewEdgeFigure = (ViewEdgeFigure)
        // ((AbstractDiagramEdgeEditPart) object).getFigure();
        // if (distance >
        // viewEdgeFigure.getFoldingFigure().getBounds().getCenter().getDistance2(searchedPosition))
        // {
        // distance =
        // viewEdgeFigure.getFoldingFigure().getBounds().getCenter().getDistance2(searchedPosition);
        // nearest = viewEdgeFigure.getFoldingFigure();
        // }
        // }
        // }
        // return nearest;
    }

    /**
     * Finds the currently active tool on the Palette.
     * 
     * @return the active {@link ToolEntry}
     */
    @Override
    public ToolEntry getActiveTool() {
        return getSWTBotGefViewer().getActiveTool();
    }

    /**
     * Maximize this editor. Eclipse API is used for that.
     */
    public void maximize() {
        UIThreadRunnable.syncExec(SWTUtils.display(), new VoidResult() {
            @Override
            public void run() {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPartState(partReference, IWorkbenchPage.STATE_MAXIMIZED);
            }
        });
    }

    /**
     * Restore the editors state (if it was maximed).
     */
    public void restore() {
        UIThreadRunnable.syncExec(SWTUtils.display(), new VoidResult() {
            @Override
            public void run() {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPartState(partReference, IWorkbenchPage.STATE_RESTORED);
            }
        });
    }

    /**
     * Disable the snapToGrid option for this editor.
     * 
     * @deprecated Use {@link #setSnapToGrid(boolean)} instead.
     */
    @Deprecated
    public void disableSnapToGrid() {
        setSnapToGrid(false);
    }

    /**
     * Disable or enable the snapToGrid option for this editor.
     * 
     * @param snap
     *            true to enable, false to disable snap to grid property
     */
    public void setSnapToGrid(final boolean snap) {
        UIThreadRunnable.syncExec(SWTUtils.display(), new VoidResult() {
            @Override
            public void run() {
                ((DiagramGraphicalViewer) getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.SNAPTOGRID, snap);
            }
        });
    }

    /**
     * Disable or enable the snapToShape option for this editor.
     * 
     * @param snap
     *            true to enable, false to disable snap to shape property
     */
    public void setSnapToShape(final boolean snap) {
        UIThreadRunnable.syncExec(SWTUtils.display(), new VoidResult() {
            @Override
            public void run() {
                ((DiagramGraphicalViewer) getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.SNAPTOGEOMETRY, snap);
            }
        });
    }

    /**
     * Return the "Snap to grid" option value for this editor.
     * 
     * @return the "Snap to grid" option value for this editor.
     */
    public boolean isSnapToShape() {
        BoolResult snapToShape = new BoolResult() {

            @Override
            public Boolean run() {
                return ((DiagramGraphicalViewer) getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().getBoolean(WorkspaceViewerProperties.SNAPTOGEOMETRY);
            }
        };
        return UIThreadRunnable.syncExec(snapToShape).booleanValue();
    }

    /**
     * Disable or enable the snapToGrid option for this editor.
     * 
     * @param snap
     *            true to enable, false to disable snap to grid property
     * @param gridSpacing
     *            The grid spacing parameter
     * @param rulerUnits
     *            The ruler units value (0 for Inches, 1 for Centimeters, 2 for
     *            Pixels)
     */
    public void setSnapToGrid(final boolean snap, final double gridSpacing, final int rulerUnits) {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                ((DiagramGraphicalViewer) getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.SNAPTOGRID, snap);
                ((DiagramGraphicalViewer) getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.GRIDSPACING, gridSpacing);
                ((DiagramGraphicalViewer) getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.RULERUNIT, rulerUnits);
            }
        });
    }

    /**
     * Return the "Snap to grid" option value for this editor.
     * 
     * @return the "Snap to grid" option value for this editor.
     */
    public boolean isSnapToGrid() {
        BoolResult snapToGrid = new BoolResult() {

            @Override
            public Boolean run() {
                return ((DiagramGraphicalViewer) getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().getBoolean(WorkspaceViewerProperties.SNAPTOGRID);
            }
        };
        return UIThreadRunnable.syncExec(snapToGrid).booleanValue();
    }

    /**
     * A utility method to return the active part if it implements or adapts to
     * the <code>IDiagramWorkbenchPart</code> interface.
     * 
     * @return The current part if it implements or adapts to
     *         <code>IDiagramWorkbenchPart</code>; <code>null</code> otherwise
     */
    protected IDiagramWorkbenchPart getDiagramWorkbenchPart() {
        IDiagramWorkbenchPart diagramPart = null;
        IEditorPart editorPart = partReference.getEditor(false);

        if (editorPart instanceof IDiagramWorkbenchPart) {
            diagramPart = (IDiagramWorkbenchPart) editorPart;

        } else if (editorPart != null) {
            diagramPart = (IDiagramWorkbenchPart) editorPart.getAdapter(IDiagramWorkbenchPart.class);
        }

        return diagramPart;
    }

    /**
     * A utility method to return the active <code>DiagramEditPart</code> if the
     * current part implements <code>IDiagramWorkbenchPart</code>.
     * 
     * @return The current diagram if the parts implements
     *         <code>IDiagramWorkbenchPart</code>; <code>null</code> otherwise
     */
    protected IDiagramGraphicalViewer getDiagramGraphicalViewer() {
        IDiagramWorkbenchPart part = getDiagramWorkbenchPart();
        return part != null ? part.getDiagramGraphicalViewer() : null;
    }

    /**
     * Zoom to next zoom value. Get the zoom manager and change the zoom level.
     * 
     * @param zoomLevel
     *            Zoom level
     * 
     * @return Current representation.
     */
    public SWTBotSiriusDiagramEditor zoom(final ZoomLevel zoomLevel) {
        UIThreadRunnable.syncExec(SWTUtils.display(), new VoidResult() {
            @Override
            public void run() {
                ZoomManager zoomManager = (ZoomManager) partReference.getEditor(false).getAdapter(ZoomManager.class);
                zoomManager.setZoomAsText(zoomLevel.getLevel());
            }
        });
        return this;
    }

    /**
     * Zoom to next zoom value. Change the zoom using the combo from the tabbar
     * (or action bar if the tabbar is not active).
     * 
     * @param zoomLevel
     *            Zoom level
     * 
     * @return Current representation.
     */
    public SWTBotSiriusDiagramEditor zoomFromToolbar(final ZoomLevel zoomLevel) {
        final ToolItem item = designerBot.toolbarSpecialDropDownButtonWithTooltip(DiagramUIMessages.ZoomActionMenu_ZoomLabel);

        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                final Combo control = (Combo) item.getControl();
                control.setFocus();
                control.setText(zoomLevel.getLevel());
                control.select(control.indexOf(zoomLevel.getLevel()));
                if (useTabbar()) {
                    control.notifyListeners(SWT.DefaultSelection, new Event());
                } else {
                    control.notifyListeners(SWT.KeyDown, createCarriageReturnKeyPressedEvent(control));
                }

            }

            private Event createCarriageReturnKeyPressedEvent(final Widget control) {
                final Event keyEvent = new Event();
                keyEvent.time = (int) System.currentTimeMillis();
                keyEvent.widget = control;
                keyEvent.display = designerBot.getDisplay();
                keyEvent.stateMask = SWT.NONE;
                keyEvent.character = SWT.CR;
                keyEvent.keyCode = SWT.Selection;
                return keyEvent;
            }

        });

        return this;
    }

    /**
     * Zoom to default zoom value.
     * 
     * @return Current representation.
     */
    public SWTBotSiriusDiagramEditor zoomDefault() {
        return zoom(UIDiagramRepresentation.ZOOM_DEFAULT);
    }

    private boolean useTabbar() {
        return !DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name());
    }

    /**
     * Get the selectable edit part with the label as a singleton selection. If
     * the edit part corresponding to the label is not selectable, we searched
     * in the parent hierarchy of this edit part.
     * 
     * @param label
     *            The searched label The type of the expected edit part
     * @return the corresponding SWTBotGefEditPart
     */
    public SWTBotGefEditPart getSelectableEditPart(final String label) {
        SWTBotGefEditPart selectedEP = super.getEditPart(label);
        if (selectedEP == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_WIDGET_S, label));
        }

        if (!isSelectableEditPart(selectedEP.part())) {
            selectedEP = getSelectableParentEditPart(selectedEP);
        }
        return selectedEP;
    }

    private boolean isSelectableEditPart(EditPart editPart) {
        return editPart instanceof AbstractBorderedShapeEditPart || editPart instanceof AbstractDiagramNodeEditPart || editPart instanceof AbstractDiagramBorderNodeEditPart;
    }

    /**
     * Search in the parent hierarchy of <code>editPart</code>, the first one of
     * selectable.
     * 
     * @param editPart
     *            The start editPart
     * @param expectedEditPartType
     *            The type of the expected edit part
     * @return the corresponding SWTBotGefEditPart
     */
    private SWTBotGefEditPart getSelectableParentEditPart(SWTBotGefEditPart editPart) {
        SWTBotGefEditPart parent = editPart.parent();
        if (parent == null || parent.part() == null) {
            throw new WidgetNotFoundException(String.format(SWTBotSiriusDiagramEditor.EXPECTED_TO_FIND_GRAPHICAL_EDIT_PART_S, editPart));
        }
        if (!isSelectableEditPart(parent.part())) {
            parent = getSelectableParentEditPart(parent);
        }
        return parent;
    }

    /**
     * Type the given text into the graphical editor, presuming that it is
     * already in 'direct edit' mode. The given text is added at the end of the
     * existing one.<BR>
     * This method is duplicated from SWTBotGefEditor to avoid to call a
     * textControl.setText(""); before typing the new one.<BR>
     * Use the new method
     * {@link SWTBotSiriusFigureCanvas#typeSuffixText(Text, String)}.
     * 
     * @param text
     *            the text to type.
     * @throws WidgetNotFoundException
     *             When widget is not found.
     */
    public void directEditTypeSuffix(String text) throws WidgetNotFoundException {
        /*
         * we use 'bot()' and not 'bot' to scope the widget search to the
         * editor. Otherwise if another widget of the same type is present in
         * the workspace and is found first, the code after will fail.
         */

        /* wait until text widget appears */
        bot().text();
        /* find it now */
        List<Text> controls = bot().getFinder().findControls(getWidget(), new IsInstanceOf<Text>(Text.class), true);
        if (controls.size() == 1) {
            final Text textControl = controls.get(0);
            SWTBotGefFigureCanvas canvas = getCanvas();
            if (canvas instanceof SWTBotSiriusFigureCanvas) {
                ((SWTBotSiriusFigureCanvas) canvas).typeSuffixText(textControl, text);
            } else {
                canvas.typeText(textControl, text);
            }
        } else {
            throw new WidgetNotFoundException(String.format("Expected to find one text control, but found %s.  Is the editor in direct-edit mode?", controls.size()));
        }
    }

    /**
     * Get the diagram edit part of this editor.
     * 
     * @return The diagram edit part of this editor.
     */
    public DiagramEditPart getDiagramEditPart() {
        final List<SWTBotGefEditPart> parts = editParts(new BaseMatcher<EditPart>() {

            @Override
            public void describeTo(final Description description) {
            }

            @Override
            public boolean matches(final Object item) {
                return item instanceof DiagramEditPart;
            }

        });
        return (DiagramEditPart) parts.iterator().next().part();
    }

    /**
     * Adapt location according to SnapToHelper.
     * 
     * @param expectedAbsoluteLocation
     *            The initial expected location
     * @return The adapted location
     */
    public PrecisionPoint adaptLocationToSnap(Point expectedAbsoluteLocation) {
        IGraphicalEditPart diagramEditPart = getDiagramEditPart();
        SnapToHelper snapToHelper = new SnapToGridEx(diagramEditPart);
        PrecisionPoint preciseLocation = new PrecisionPoint(expectedAbsoluteLocation);
        diagramEditPart.getFigure().translateToAbsolute(preciseLocation);
        PrecisionPoint result = new PrecisionPoint(preciseLocation);
        snapToHelper.snapPoint(new CreateRequest(), PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, preciseLocation, result);
        diagramEditPart.getFigure().translateToRelative(result);
        return result;
    }

    /**
     * Change the activation status of this layer.
     * 
     * @param layerName
     *            The name of the layer to activate or deactivate.
     */
    public void changeLayerActivation(String layerName) {
        if (useTabbar()) {
            SWTBotToolbarDropDownButton button = designerBot.toolbarDropDownButtonWithTooltip("Layers");
            Matcher<MenuItem> withLayerName = WidgetMatcherFactory.withText(layerName);
            SWTBotMenu layerButton = button.menuItem(withLayerName);
            layerButton.click();
            layerButton.pressShortcut(KeyStroke.getInstance(SWT.ESC));
        } else {
            DesignerViews designerViews = new DesignerViews(designerBot);
            final SiriusOutlineView outlineView = designerViews.getOutlineView().layers();
            outlineView.activateLayer(layerName);
        }
    }

    /**
     * Get the {@link DRepresentation} instance available in the diagram editor.
     * 
     * @return the {@link DRepresentation} instance
     */
    public DRepresentation getDRepresentation() {
        DRepresentation dRepresentation = null;
        IEditorPart editorPart = getReference().getEditor(false);
        if (editorPart instanceof DialectEditor) {
            DialectEditor dialectEditor = (DialectEditor) editorPart;
            dRepresentation = dialectEditor.getRepresentation();
        }
        return dRepresentation;
    }
}
