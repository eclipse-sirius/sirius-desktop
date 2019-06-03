/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;

/**
 * Framework for traversing the tree and retrieve the viewpoint tree item.
 * 
 * @author jdupont
 */
public class AbstractTreeSiriusSWTBotGefTestCase extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Properties view.
     */
    protected static final String PROPERTIES = "Properties";

    /**
     * Context menu refresh Tree Element
     */
    protected static final String REFRESH_TREE = "Refresh Tree Element";

    /**
     * Map for index color.
     */
    protected static final Map<Integer, String> MAPCOLORVALUE = new HashMap<Integer, String>() {

        /**
         * Serial Version UID
         */
        private static final long serialVersionUID = 1L;

        {
            put(0, "white");
            put(1, "black");
            put(2, "blue");
            put(3, "chocolate");
            put(4, "gray");
            put(5, "green");
            put(6, "orange");
            put(7, "purple");
            put(8, "red");
            put(9, "yellow");
            put(10, "light_blue");
            put(11, "light_chocolate");
            put(12, "light_gray");
            put(13, "light_green");
            put(14, "light_orange");
            put(15, "light_purple");
            put(16, "light_red");
            put(17, "light_yellow");
            put(18, "dark_blue");
            put(19, "dark_chocolate");
            put(20, "dark_gray");
            put(21, "dark_green");
            put(22, "dark_orange");
            put(23, "dark_purple");
            put(24, "dark_red");
            put(25, "dark_yellow");
        }
    };

    /**
     * File constants.
     */
    private static final String FILE = "File";

    /**
     * Save constants.
     */
    private static final String SAVE = "Save";

    /**
     * Gets the background color of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the background color on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public Color getWidgetBackgroundColor(final TreeItem widget) {
        return syncExec(new Result<Color>() {
            @Override
            public Color run() {
                return widget.getBackground(0);
            }
        });
    }

    /**
     * Gets the label color of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label color on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public Color getWidgetLabelColor(final TreeItem widget) {
        return syncExec(new Result<Color>() {
            @Override
            public Color run() {
                return widget.getForeground(0);
            }
        });
    }

    /**
     * 
     * Gets the label expression of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label expression on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public String getWidgetLabelExpression(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return widget.getText();
            }
        });
    }

    /**
     * 
     * Gets the image of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the image on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public Image getWidgetImage(final TreeItem widget) {
        return syncExec(new Result<Image>() {
            @Override
            public Image run() {
                return widget.getImage();
            }
        });
    }

    /**
     * 
     * Gets the size of the label of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the size of the label on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public Integer getWidgetSize(final TreeItem widget) {
        return syncExec(new Result<Integer>() {
            @Override
            public Integer run() {
                Font font = widget.getFont(0);
                if (font.getFontData().length > 0) {
                    FontData[] fontDatas = font.getFontData();
                    FontData fontData = fontDatas[0];
                    return fontData.getHeight();
                }
                return 0;
            }
        });
    }

    /**
     * Gets the label size of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label size on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public int getLabelSize(final TreeItem widget) {
        return syncExec(new Result<Integer>() {
            @Override
            public Integer run() {
                return ((DTreeItem) widget.getData()).getOwnedStyle().getLabelSize();
            }
        });
    }

    /**
     * Gets the label background color of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label background color on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public Color getLabelBackgroundColor(final TreeItem widget) {
        return syncExec(new Result<Color>() {
            @Override
            public Color run() {
                return VisualBindingManager.getDefault().getColorFromRGBValues(((DTreeItem) widget.getData()).getOwnedStyle().getBackgroundColor());
            }
        });
    }

    /**
     * Gets the label color of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label color on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public Color getLabelColor(final TreeItem widget) {
        return syncExec(new Result<Color>() {
            @Override
            public Color run() {
                return VisualBindingManager.getDefault().getColorFromRGBValues(((DTreeItem) widget.getData()).getOwnedStyle().getLabelColor());
            }
        });
    }

    /**
     * Gets if showIcon is checked.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return true if showIcon is checked, false otherwise
     * @since 1.0
     */
    public boolean isShowIcon(final TreeItem widget) {
        return syncExec(new Result<Boolean>() {
            @Override
            public Boolean run() {
                return ((DTreeItem) widget.getData()).getOwnedStyle().isShowIcon();
            }
        });
    }

    /**
     * Gets if showIcon is checked.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return true if showIcon is checked, false otherwise
     * @since 1.0
     */
    public String getIconPath(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getOwnedStyle().getIconPath();
            }
        });
    }

    /**
     * Gets the semantic element of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the semantic element on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public String getSemanticElement(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getTreeElementMapping().getSemanticElements();
            }
        });
    }

    /**
     * Gets the domain class of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the domain class on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public String getDomainClass(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getDomainClass();
            }
        });
    }

    /**
     * Gets the id of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the id on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public String getId(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getName();
            }
        });
    }

    /**
     * Gets the label of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public String getLabel(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getLabel();
            }
        });
    }

    /**
     * Gets the precondition expression of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the precondition expression on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public String getPreconditionExpression(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getPreconditionExpression();
            }
        });
    }

    /**
     * Gets the semantic candidate expression of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the semantic candidate expression on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public String getSemanticCandidatesExpression(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getSemanticCandidatesExpression();
            }
        });
    }

    /**
     * Gets the reused tree item mappings of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the reused tree item mappings on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public EList<TreeItemMapping> getReusedTreeItemMappings(final TreeItem widget) {
        return syncExec(new Result<EList<TreeItemMapping>>() {
            @Override
            public EList<TreeItemMapping> run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getReusedTreeItemMappings();
            }
        });
    }

    /**
     * Gets the detail description of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the detail description on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public EList<RepresentationCreationDescription> getDetailDescriptions(final TreeItem widget) {
        return syncExec(new Result<EList<RepresentationCreationDescription>>() {
            @Override
            public EList<RepresentationCreationDescription> run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getDetailDescriptions();
            }
        });
    }

    /**
     * Gets the navigation description of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the navigation description on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public EList<RepresentationNavigationDescription> getNavigationDescriptions(final TreeItem widget) {
        return syncExec(new Result<EList<RepresentationNavigationDescription>>() {
            @Override
            public EList<RepresentationNavigationDescription> run() {
                return ((DTreeItem) widget.getData()).getActualMapping().getNavigationDescriptions();
            }
        });
    }

    /**
     * Gets the label format of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label format on the widget, or <code>null</code> if the widget is not an instance of {@link Control}.
     * @since 1.0
     */
    public List<String> getLabelFormat(final TreeItem widget) {
        return syncExec(new Result<List<String>>() {
            @Override
            public List<String> run() {
                return getFontFormatLiterals(((DTreeItem) widget.getData()).getOwnedStyle().getLabelFormat());
            }
        });
    }

    private List<String> getFontFormatLiterals(List<FontFormat> fontFormat) {
        List<String> expected = new ArrayList<String>();
        for (FontFormat style : fontFormat) {
            expected.add(style.getName());
        }
        return expected;
    }

    /**
     * Gets the label alignment of the widget.
     * 
     * @param widget
     *            the tree item widget.
     * 
     * @return the label alignment on the widget, or <code>null</code> if the widget is not an instance of
     *         {@link Control}.
     * @since 1.0
     */
    public String getLabelAlignment(final TreeItem widget) {
        return syncExec(new Result<String>() {
            @Override
            public String run() {
                return ((DTreeItem) widget.getData()).getOwnedStyle().getLabelAlignment().getName();
            }
        });
    }

    /**
     * Open viewpoint specification model.
     * 
     * @param viewpointSpecificationModel
     *            the name of viewpoint specification model (.odesing)
     * @return odesignEditor
     */
    @Override
    public SWTBotVSMEditor openViewpointSpecificationModel(String viewpointSpecificationModel) {
        SWTBotCommonHelper.openEditor(getProjectName(), viewpointSpecificationModel);
        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(viewpointSpecificationModel);
        odesignEditor.setFocus();
        return odesignEditor;
    }

    /**
     * Close and save viewpoint specification model.
     * 
     * @param viewpointSpecificationModel
     *            the name of viewpoint specification model (.odesing)
     */
    public void closeAndSaveViewpointSpecificationModel(String viewpointSpecificationModel) {
        SWTBotEditor editorBot = bot.editorByTitle(viewpointSpecificationModel);
        editorBot.setFocus();
        SWTBotSiriusHelper.menu(editorBot.bot(), FILE).menu(SAVE).click();
        editorBot.setFocus();
        editorBot.close();
    }

    /**
     * Open viewpoint editor.
     * 
     * @param localSession
     *            the localSession
     * @param viewpointName
     *            the viewpoint name
     * @param representationName
     *            the representation name in representation per resource/category
     * @param representationInstanceName
     *            the representation instance name in representation per category/resource
     * @return treeRepresentation corresponding.
     */
    public UITreeRepresentation openEditor(UILocalSession localSession, String viewpointName, String representationName, String representationInstanceName) {
        return localSession.getLocalSessionBrowser().perCategory().selectViewpoint(viewpointName).selectRepresentation(representationName)
                .selectRepresentationInstance(representationInstanceName, UITreeRepresentation.class).open();
    }

    /**
     * Save viewpoint specification model.
     * 
     * @param viewpointSpecificationModel
     *            the name of viewpoint specification model (.odesing).
     */
    public void saveViewpointSpecificationModel(String viewpointSpecificationModel) {
        SWTBotEditor editorBot = bot.editorByTitle(viewpointSpecificationModel);
        editorBot.setFocus();
        SWTBotSiriusHelper.menu(editorBot.bot(), FILE).menu(SAVE).click();
    }

    /**
     * Close and save viewpoint specification model, close and save viewpoint editor and close and save session.
     * 
     * @param viewpointSpecificationModel
     *            the viewpoint specification model name
     * @param editor
     *            the viewpoint editor
     * @param localSession
     *            the local session
     */
    public void closeAndSaveAll(String viewpointSpecificationModel, SWTBotEditor editor, UILocalSession localSession) {
        SWTBotEditor editorBot = bot.editorByTitle(viewpointSpecificationModel);
        editorBot.setFocus();
        SWTBotSiriusHelper.menu(editorBot.bot(), FILE).menu(SAVE).click();
        editorBot.setFocus();
        editorBot.close();
    }

    /**
     * Invokes {@link Result#run()} synchronously on the UI thread.
     * 
     * @param toExecute
     *            the object to be invoked in the UI thread.
     * @return the boolean returned by toExecute
     */
    protected <T> T syncExec(Result<T> toExecute) {
        return UIThreadRunnable.syncExec(toExecute);
    }

    /**
     * Activate the auto refresh mode.
     */
    protected void enableAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    /**
     * Activate the refresh on opening representation.
     */
    protected void enableRefreshOnOpeningRepresentation() {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);
    }

    /**
     * Disable the auto refresh mode.
     */
    protected void disableAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
    }

    /**
     * Disable the refresh on opening representation.
     */
    protected void disableRefreshOnOpeningRepresentation() {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
    }

}
