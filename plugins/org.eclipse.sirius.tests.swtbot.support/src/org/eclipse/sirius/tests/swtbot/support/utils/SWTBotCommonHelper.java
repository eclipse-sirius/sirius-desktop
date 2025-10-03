/**
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others
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
package org.eclipse.sirius.tests.swtbot.support.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionSavedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Common helpers to simplify SWTBot.
 * 
 * @author smonnier
 */
public final class SWTBotCommonHelper {

    /**
     * Storage of edge data.
     * 
     * @author dlecan
     */
    public static class EdgeData {

        /** Source. */
        protected Point source;

        /** Target. */
        protected Point target;

        /** Points. */
        protected PointList points;

        /** SWTBot edit part. */
        protected SWTBotGefConnectionEditPart swtBotEditPart;

        /**
         * Returns the source.
         * 
         * @return The source.
         */
        public Point getSource() {
            return source;
        }

        /**
         * Returns the target.
         * 
         * @return The target.
         */
        public Point getTarget() {
            return target;
        }

        /**
         * Returns the points.
         * 
         * @return The points.
         */
        public PointList getPoints() {
            return points;
        }

        /**
         * Returns the swtBotEditPart.
         * 
         * @return The swtBotEditPart.
         */
        public SWTBotGefConnectionEditPart getSwtBotEditPart() {
            return swtBotEditPart;
        }
    }

    private static final String FILE = "File";

    /**
     * SWTWorkbenchBot
     */
    private static SWTWorkbenchBot bot = new SWTWorkbenchBot();

    /**
     * Avoid instantiation.
     */
    private SWTBotCommonHelper() {

    }

    /**
     * Close the current editor.
     */
    public static void closeCurrentEditor() {
        SWTBotSiriusHelper.menu(SWTBotCommonHelper.bot, SWTBotCommonHelper.FILE).menu("Close All Editors").click();
    }

    /**
     * Open an editor on a file.
     * 
     * @param project
     *            the current project
     * @param editorPath
     *            the file path
     */
    public static void openEditor(final String project, final String editorPath) {
        final SWTBot packageExplorerBot = SWTBotCommonHelper.bot.viewByTitle("Model Explorer").bot();

        final SWTBotTree wizardTree = packageExplorerBot.tree();
        wizardTree.expandNode(project);
        SWTBotTreeItem treeItem = wizardTree.getTreeItem(project);
        treeItem.expand();

        final String[] path = editorPath.split(Pattern.quote(File.separator));
        for (final String s : path) {
            treeItem = treeItem.expandNode(s);
        }

        treeItem.setFocus();
        treeItem.select().contextMenu("Open").click();
    }

    /**
     * Save the current editor. Warning: It is preferable to use {@link #saveCurrentEditor(Session)} because this method
     * contains a wait condition to ensure that the save is finished.
     */
    public static void saveCurrentEditor() {
        SWTBotCommonHelper.bot.menu(SWTBotCommonHelper.FILE).menu("Save").click();
    }

    /**
     * Save the current editor and wait that save is finished.
     * 
     * @param session
     *            Session to wait the saving for.
     */
    public static void saveCurrentEditor(Session session) {
        SWTBotCommonHelper.bot.menu(SWTBotCommonHelper.FILE).menu("Save").click();
        SWTBotCommonHelper.bot.waitUntil(new SessionSavedCondition(session));
    }

    /**
     * Get all edges between to edit parts.
     * 
     * @param source
     *            Source edit part.
     * @param target
     *            Target edit part.
     * @param editor
     *            Current editor.
     * @return All {@link EdgeData}.
     */
    public static List<EdgeData> getEdgeData(final SWTBotGefEditPart source, final SWTBotGefEditPart target, final SWTBotSiriusDiagramEditor editor) {
        List<SWTBotGefConnectionEditPart> connectionEditParts = editor.getConnectionEditPart(source, target);
        return connectionEditParts.stream().map((SWTBotGefConnectionEditPart partBot) -> {
            ConnectionEditPart connectionEditPart = partBot.part();
            PolylineConnectionEx connection = (PolylineConnectionEx) connectionEditPart.getFigure();
            EdgeData result = new EdgeData();
            result.swtBotEditPart = partBot;
            result.source = connection.getSourceAnchor().getReferencePoint();
            result.target = connection.getTargetAnchor().getReferencePoint();
            result.points = connection.getPoints().getCopy();
            return result;
        }).toList();
    }

    /**
     * <p>
     * Gets the outline view of the current editor and selects the "Outline" button. Returns the outline Tree of the
     * opened outline.
     * </p>
     * 
     * <p>
     * <b>Use examples : </b><br/>
     * <code> SWTBotView view = SWTBotCommonHelper.getOutlineView(designerViews);<br/>
        view.bot().tree().expandNode("p", true);<br/>
        SWTBotTreeItem nodeItem = view.bot().tree().getTreeItem("p").getNode("myNode").click();<br/>
        nodeItem.contextMenu("myContextMenu").click();
        </code>
     * </p>
     * 
     * @param designerViews
     *            if your test extends
     *            {@link org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase} , you should pass
     *            the <code>designerViews</code> attribute.
     * @return a {@link SWTBotView} allowing to manipulate the current editor's outline (focused on the outline Tree)
     * 
     * @throws NoSuchFieldException
     *             if a reflective exception occurred while trying to get the outline
     */
    public static SWTBotView getOutlineView(DesignerViews designerViews) throws NoSuchFieldException {
        try {
            // Step 1.1 : open the outline view and get its SWTBotView
            SiriusOutlineView outlineView = designerViews.openOutlineView();
            Field field;
            field = SiriusOutlineView.class.getDeclaredField("view");
            field.setAccessible(true);
            SWTBotView view;

            view = (SWTBotView) field.get(outlineView);

            // Step 1.2 : selecting the outline tree
            view.toolbarButton("Outline").click();
            return view;

        } catch (IllegalArgumentException e) {
            throw new NoSuchFieldException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new NoSuchFieldException(e.getMessage());
        }
    }

}
