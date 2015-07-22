/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.refresh;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.business.internal.internal.color.TreeStyleColorUpdater;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.impl.DTreeElementSynchronizerImpl;
import org.eclipse.sirius.tree.tools.api.interpreter.IInterpreterSiriusTreeVariables;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.FontFormat;

/**
 * Tree element synchronizer.
 * 
 * @author nlepine
 * 
 */
public class DTreeElementSynchronizerSpec extends DTreeElementSynchronizerImpl {
    private final IInterpreter interpreter;

    private ModelAccessor modelAccessor;

    /**
     * Synchronizer for table elements.
     * 
     * @param interpreter
     *            current interpreter.
     * @param modelAccessor
     *            the model accessor to use to refresh the DTreeElement
     */
    public DTreeElementSynchronizerSpec(IInterpreter interpreter, ModelAccessor modelAccessor) {
        this.interpreter = interpreter;
        this.modelAccessor = modelAccessor;
    }

    /**
     * Evaluate the semantic elements feature of the mapping and affect them to
     * the given table element.
     * 
     * @param treeElement
     *            table element to affect.
     */
    public void refreshSemanticElements(final DTreeElement treeElement) {
        refreshSemanticElements(treeElement, treeElement.getTreeElementMapping());
    }

    /**
     * Evaluate the semantic elements feature of the mapping and affect them to
     * the given table element.
     * 
     * @param treeElement
     *            table element to affect.
     * @param mapping
     *            mapping used to retrieve the semantic elements.
     */
    public void refreshSemanticElements(final DTreeElement treeElement, final TreeMapping mapping) {
        if (modelAccessor.getPermissionAuthority().canEditInstance(treeElement)) {
            if (mapping.getSemanticElements() != null && !StringUtil.isEmpty(mapping.getSemanticElements())) {

                if (treeElement.eContainer() != null) {
                    this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, treeElement.eContainer());
                    if (treeElement.eContainer() instanceof DSemanticDecorator) {
                        this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((DSemanticDecorator) treeElement.eContainer()).getTarget());
                    }
                }
                this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, treeElement);

                Collection<EObject> elements;
                if (mapping instanceof TreeItemMapping) {
                    elements = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(treeElement.getTarget(), mapping, DescriptionPackage.eINSTANCE.getTreeMapping_SemanticElements());
                } else {
                    try {
                        elements = interpreter.evaluateCollection(treeElement.getTarget(), mapping.getSemanticElements());
                    } catch (final EvaluationException e) {
                        // Silent catch
                        elements = Collections.emptyList();
                    }
                }
                synchronizeLists(treeElement.getSemanticElements(), elements);

                if (treeElement.eContainer() != null) {
                    this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
                    if (treeElement.eContainer() instanceof DSemanticDecorator) {
                        this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                    }
                }
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);

            } else if (treeElement.getTarget() != null) {
                synchronizeLists(treeElement.getSemanticElements(), Collections.singletonList(treeElement.getTarget()));
            } else {
                final Collection<EObject> elements = Collections.emptyList();
                synchronizeLists(treeElement.getSemanticElements(), elements);
            }
        }
    }

    private void synchronizeLists(final EList<EObject> semanticElements, final Collection<EObject> newElements) {
        if (!semanticElements.containsAll(newElements) || !newElements.containsAll(semanticElements)) {
            final Iterator<EObject> it = semanticElements.iterator();
            while (it.hasNext()) {
                final EObject cur = it.next();
                if (!newElements.contains(cur)) {
                    it.remove();
                }
            }
            semanticElements.addAll(newElements);
        }
    }

    /**
     * Refresh a tree item.
     * 
     * @param treeItem
     *            treeItem to refresh.
     */
    @Override
    public void refresh(final DTreeItem treeItem) {
        if (modelAccessor.getPermissionAuthority().canEditInstance(treeItem)) {
            refreshStyle(treeItem);
            refreshSemanticElements(treeItem);
        }
    }

    /**
     * Refresh a tree item and its children.
     * 
     * @param treeItem
     *            treeItem to refresh.
     */
    public void refreshItemAndChildren(final DTreeItem treeItem) {
        refresh(treeItem);
        for (DTreeItem item : treeItem.getOwnedTreeItems()) {
            if (treeItem.isExpanded()) {
                refreshItemAndChildren(item);
            }
        }
    }

    private void refreshStyle(DTreeItem treeItem) {
        if (treeItem.getOwnedStyle() == null) {
            treeItem.setOwnedStyle(TreeFactory.eINSTANCE.createTreeItemStyle());
        }

        final TreeItemStyle style = treeItem.getOwnedStyle();
        doUpdateStyle(treeItem, style);
    }

    /**
     * The columnUpdater takes priority over the lineUpdater.
     * 
     * @param treeItem
     * 
     * @param style
     */
    private void doUpdateStyle(final DTreeItem treeItem, final TreeItemStyle style) {
        final TreeStyleColorUpdater colorUpdater = new TreeStyleColorUpdater();

        final TreeItemStyleDescription bestTreeItemStyle = getBestTreeItemStyle(treeItem);
        if (bestTreeItemStyle != null) {
            colorUpdater.updateBackgroundColor(style, bestTreeItemStyle.getBackgroundColor(), treeItem.getTarget());
            colorUpdater.updateLabelColor(style, bestTreeItemStyle.getLabelColor(), treeItem.getTarget());
            if (bestTreeItemStyle.getLabelFormat() != null && !isEqual(style.getLabelFormat(), bestTreeItemStyle.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestTreeItemStyle.getLabelFormat());
            }
            if (bestTreeItemStyle.getLabelSize() > 0 && style.getLabelSize() != bestTreeItemStyle.getLabelSize()) {
                style.setLabelSize(bestTreeItemStyle.getLabelSize());
            }
            if (bestTreeItemStyle.getLabelAlignment() != null && style.getLabelAlignment() != bestTreeItemStyle.getLabelAlignment()) {
                style.setLabelAlignment(bestTreeItemStyle.getLabelAlignment());
            }
            refreshLabel(treeItem, bestTreeItemStyle, style);
            if (style.isShowIcon() != bestTreeItemStyle.isShowIcon()) {
                style.setShowIcon(bestTreeItemStyle.isShowIcon());
            }

            if (style.getIconPath() != null && !style.getIconPath().equals(bestTreeItemStyle.getIconPath())) {
                style.setIconPath(bestTreeItemStyle.getIconPath());
            }
        }

    }

    /**
     * Refresh the label.
     * 
     * @param treeItem
     * @param bestTreeItemStyle
     * @param style
     */
    private void refreshLabel(final DTreeItem treeItem, final TreeItemStyleDescription bestTreeItemStyle, TreeItemStyle style) {
        final String labelExpression = bestTreeItemStyle.getLabelExpression();
        if (!StringUtil.isEmpty(labelExpression)) {
            if (treeItem.getTarget() != null) {
                try {
                    if (treeItem != null) {
                        this.interpreter.setVariable(IInterpreterSiriusTreeVariables.TREE_ITEM, treeItem);
                        this.interpreter.setVariable(IInterpreterSiriusTreeVariables.TREE_ITEM_SEMANTIC, treeItem.getTarget());
                        this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, treeItem.getTarget());
                    }
                    final DTree tree = TreeHelper.getTree(treeItem);
                    if (tree != null) {
                        this.interpreter.setVariable(IInterpreterSiriusVariables.ROOT, tree.getTarget());
                    }
                    final String label = interpreter.evaluateString(treeItem.getTarget(), labelExpression);
                    treeItem.setName(label);
                } catch (final EvaluationException e) {
                    // Silent catch if many line mappings
                } finally {
                    if (treeItem != null) {
                        this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                        this.interpreter.unSetVariable(IInterpreterSiriusTreeVariables.TREE_ITEM);
                        this.interpreter.unSetVariable(IInterpreterSiriusTreeVariables.TREE_ITEM_SEMANTIC);
                    }
                    this.interpreter.unSetVariable(IInterpreterSiriusVariables.ROOT);
                }
            }
        }
    }

    private boolean isEqual(List<FontFormat> labelFormat, List<FontFormat> labelFormat2) {
        if (labelFormat != null && labelFormat2 != null) {
            return labelFormat.equals(labelFormat2);
        }
        return false;

    }

    /**
     * Return a value only if the current background color is the default
     * background color or one of the conditional background colors. Otherwise,
     * it means that the user has changed the value manually and therefore we
     * should not touch it.
     * 
     * @param item
     *            The current TreeItem
     * @return The best colorMapping for this item, or null otherwise
     */
    private TreeItemStyleDescription getBestTreeItemStyle(final DTreeItem item) {
        TreeItemStyleDescription bestForegroundStyleDescription = null;
        boolean bestStyleIsConditonalStyle = false;

        StyleUpdater updater = item.getStyleUpdater();
        if (!bestStyleIsConditonalStyle) {
            if (updater.getDefaultStyle() != null) {
                bestForegroundStyleDescription = updater.getDefaultStyle();
            }
        }
        for (ConditionalTreeItemStyleDescription condStyle : updater.getConditionalStyles()) {
            if (condStyle.getStyle() != null && item.getTarget() != null) {
                boolean predicate = false;
                try {
                    predicate = interpreter.evaluateBoolean(item.getTarget(), condStyle.getPredicateExpression());
                } catch (final EvaluationException e) {
                    // Silent catch
                }
                if (predicate) {
                    bestForegroundStyleDescription = condStyle.getStyle();
                    bestStyleIsConditonalStyle = true;
                }
            }
        }
        return bestForegroundStyleDescription;
    }
}
