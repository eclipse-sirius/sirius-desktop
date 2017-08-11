/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction;
import org.eclipse.ui.IWorkbenchPage;

/**
 * The straighten menu manager. It contains all straighten-related actions.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StraightenToMenuManager extends ActionMenuManager {

    /**
     * The straighten menu action containing the UI for the straighten menu manager
     */
    private static class StraightenMenuAction extends Action {
        StraightenMenuAction() {
            setText(Messages.StraightenToMenuAction_text);
            setToolTipText(Messages.StraightenToMenuAction_tooltip);
            ImageDescriptor imageDesc = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.STRAIGHTEN_TO_TOP);
            setImageDescriptor(imageDesc);
            setHoverImageDescriptor(imageDesc);
        }
    }

    /**
     * Creates a new instance of the straighten menu manager.
     */
    public StraightenToMenuManager() {
        super(ActionIds.MENU_STRAIGHTEN_TO, new StraightenMenuAction(), true);
    }

    /**
     * Set the default action id for this menu manager.
     * 
     * @param actionId
     *            the action id to set
     */
    public void setDefaultAction(String actionId) {
        for (final IContributionItem item : getItems()) {
            if (item instanceof ActionContributionItem) {
                if (actionId.equals(((ActionContributionItem) item).getAction().getId())) {
                    final IAction defaultAction = ((ActionContributionItem) item).getAction();
                    setHandler(defaultAction);
                    super.setDefaultAction(defaultAction);
                    return;
                }
            }
        }
    }

    /**
     * We should use reflection to access the default handler method
     * 
     * @param defaultAction
     *            the default action to set
     */
    private void setHandler(final IAction defaultAction) {
        Method method;
        try {
            method = MenuCreatorAction.class.getDeclaredMethod("setActionHandler", IAction.class); //$NON-NLS-1$
            method.setAccessible(true);
            method.invoke(super.action, defaultAction);
        } catch (SecurityException e) {
            /* do nothing should not happen */
        } catch (NoSuchMethodException e) {
            /* do nothing should not happen */
        } catch (IllegalArgumentException e) {
            /* do nothing should not happen */
        } catch (IllegalAccessException e) {
            /* do nothing should not happen */
        } catch (InvocationTargetException e) {
            /* do nothing should not happen */
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isEmpty() && visible) {
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                add(StraightenToAction.createStraightenToTopAction(page));
                add(StraightenToAction.createStraightenToBottomAction(page));
                add(StraightenToAction.createStraightenLeftSidePinnedAction(page));
                add(StraightenToAction.createStraightenRightSidePinnedAction(page));
                add(new Separator());
                add(StraightenToAction.createStraightenToLeftAction(page));
                add(StraightenToAction.createStraightenToRightAction(page));
                add(StraightenToAction.createStraightenTopSidePinnedAction(page));
                add(StraightenToAction.createStraightenBottomSidePinnedAction(page));

                setDefaultAction(ActionIds.STRAIGHTEN_TO_TOP);
            }
        }
    }
}
