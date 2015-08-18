/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.business.api.query.DTableElementStyleQuery;
import org.eclipse.sirius.table.business.api.query.StyleUpdaterQuery;
import org.eclipse.sirius.table.business.internal.color.TableStyleColorUpdater;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.impl.DTableElementSynchronizerImpl;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;

import com.google.common.collect.Lists;

/**
 * Synchronizer for the table elements.
 * 
 * @author cbrun
 * 
 */
public class DTableElementSynchronizerSpec extends DTableElementSynchronizerImpl {

    /**
     * '*' as feature name allows to skip the feature name validation for cell
     * creation.
     */
    protected static final String SKIP_FEATURENAME_VALIDATION = "*"; //$NON-NLS-1$

    private final ModelAccessor accessor;

    private final IInterpreter interpreter;

    /**
     * Synchronizer for table elements.
     * 
     * @param accessor
     *            current model accessor.
     * @param interpreter
     *            current interpreter.
     */
    public DTableElementSynchronizerSpec(final ModelAccessor accessor, final IInterpreter interpreter) {
        this.interpreter = interpreter;
        this.accessor = accessor;
    }

    /**
     * Refresh a line.
     * 
     * @param line
     *            line to refresh.
     */
    @Override
    public void refresh(final DLine line) {
        if (accessor.getPermissionAuthority().canEditInstance(line)) {
            final LineMapping mapping = line.getOriginMapping();
            if (!StringUtil.isEmpty(mapping.getHeaderLabelExpression())) {
                try {
                    final String label = interpreter.evaluateString(line.getTarget(), mapping.getHeaderLabelExpression());
                    if (isDifferent(line.getLabel(), label)) {
                        line.setLabel(label);
                    }
                } catch (final EvaluationException e) {
                    // silent catch.
                }
            } else {
                // If there is no headerLabelExpression, we use the label
                // provider
                // to get label to display
                final String label = getText(line.getTarget());
                // We change the value only if it's different
                if (isDifferent(line.getLabel(), label)) {
                    line.setLabel(label);
                }
            }
            // Trac #1125 Line height modification (aborted because of Windows
            // SWT
            // limitation)
            // if (mapping.getInitialHeight() != 0 && line.getHeight() == 0) {
            // line.setHeight(mapping.getInitialHeight());
            // }
            refreshStyle(line);
        }
    }

    /**
     * Refresh the given cell.
     * 
     * @param cell
     *            cell to refresh.
     */
    @Override
    public void refresh(final DCell cell) {
        if (accessor.getPermissionAuthority().canEditInstance(cell)) {
            if (refreshTarget(cell)) {
                if (refreshLabel(cell)) {
                    refreshStyle(cell);
                    refreshSemanticElements(cell);
                }
            }
        }
    }

    /**
     * Refresh a column label.
     * 
     * @param column
     *            column to refresh.
     */
    @Override
    public void refresh(final DColumn column) {
        if (accessor.getPermissionAuthority().canEditInstance(column)) {
            if (column instanceof DTargetColumn) {
                refresh((DTargetColumn) column);
            } else {
                super.refresh(column);
            }
        }
    }

    /**
     * Refresh a column label.
     * 
     * @param column
     *            column to refresh.
     */
    private void refresh(final DTargetColumn column) {
        final ColumnMapping mapping = column.getOriginMapping();
        if (accessor.getPermissionAuthority().canEditInstance(column)) {
            if (!StringUtil.isEmpty(mapping.getHeaderLabelExpression())) {
                try {
                    final String label = interpreter.evaluateString(column.getTarget(), mapping.getHeaderLabelExpression());
                    if (isDifferent(column.getLabel(), label)) {
                        column.setLabel(label);
                    }
                } catch (final EvaluationException e) {
                    // silent catch.
                }
            } else {
                // If there is no headerLabelExpression, we use the label
                // provider
                // to get label to display
                final String label = getText(column.getTarget());
                // We change the value only if it's different
                if (isDifferent(column.getLabel(), label)) {
                    column.setLabel(label);
                }
            }
            if (mapping.getInitialWidth() != 0 && column.getWidth() == 0) {
                column.setWidth(mapping.getInitialWidth());
            }
            refreshStyle(column);
        }
    }

    /**
     * Refresh a column label.
     * 
     * @param column
     *            column to refresh.
     */
    public void refresh(final DFeatureColumn column) {
        final ColumnMapping mapping = column.getOriginMapping();
        if (accessor.getPermissionAuthority().canEditInstance(column)) {
            if (isDifferent(column.getLabel(), mapping.getHeaderLabelExpression())) {
                column.setLabel(mapping.getHeaderLabelExpression());
            }
            if (mapping.getInitialWidth() != 0 && column.getWidth() == 0) {
                column.setWidth(mapping.getInitialWidth());
            }
            refreshStyle(column);
        }
    }

    /**
     * Refresh the target of the cell (only for the FeatureColumn). The target
     * can be change if the featureParentExpression is changed in the odesign
     * file.<BR>
     * If this cell now corresponds to an invalid feature, it is removed from
     * its line.
     * 
     * @param cell
     *            cell to refresh.
     * @return true if the cell still exists after this refresh, false otherwise
     *         (if the cell is deleted because of an invalid featureName for
     *         featureParent).
     */
    public boolean refreshTarget(final DCell cell) {
        boolean deletedCell = false;
        if (cell.getLine() != null) {
            EObject featureParent = cell.getLine().getTarget();
            if (cell.getColumn() instanceof DFeatureColumn) {
                final FeatureColumnMapping featureColumnMapping = (FeatureColumnMapping) cell.getColumn().getMapping();
                final String featureParentExpression = featureColumnMapping.getFeatureParentExpression();
                if (featureParentExpression != null && featureParentExpression.length() > 0) {
                    final DTable table = TableHelper.getTable(cell);

                    this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, cell.getLine().getTarget());
                    if (table != null) {
                        this.interpreter.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
                    }
                    if (TableHelper.hasTableDescriptionOnlyOneLineMapping(cell)) {
                        featureParent = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateEObject(cell.getLine().getTarget(), featureColumnMapping,
                                DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureParentExpression());
                    } else {
                        try {
                            featureParent = interpreter.evaluateEObject(cell.getLine().getTarget(), featureParentExpression);
                        } catch (final EvaluationException e) {
                            // Silent catch
                        }
                    }
                    this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                    if (table != null) {
                        this.interpreter.unSetVariable(IInterpreterSiriusVariables.ROOT);
                    }

                }
                if (featureParent == null) {
                    removeUneededCell(cell);
                    deletedCell = true;
                } else if (!featureParent.equals(cell.getTarget())) {
                    String featureName = featureColumnMapping.getFeatureName();
                    if (SKIP_FEATURENAME_VALIDATION.equals(featureName) || accessor.eValid(featureParent, featureName)) {
                        cell.setTarget(featureParent);
                    } else {
                        removeUneededCell(cell);
                        deletedCell = true;
                    }
                }
            }
        }
        return !deletedCell;
    }

    /**
     * Refresh the cell label.
     * 
     * @param cell
     *            cell to refresh.
     * @return true if the cell still exists after this refresh, false
     *         otherwise.
     */
    public boolean refreshLabel(final DCell cell) {
        boolean cellStillExists = false;
        if (cell.getColumn() != null) {
            final CellUpdater updater = cell.getUpdater();
            if (updater != null) {
                final String labelExpression = updater.getLabelComputationExpression();
                if (updater instanceof IntersectionMapping && cell.getLabel() != null) {
                    cellStillExists = refreshLabelIntersectionMapping(cell, labelExpression);
                } else {
                    cellStillExists = refreshLabel(cell, labelExpression);
                }
            }
        }
        if (!cellStillExists) {
            removeUneededCell(cell);
        }
        return cellStillExists;
    }

    /**
     * This method removed a cell from its line and column.
     * 
     * @param cell
     *            The cell to removed
     */
    protected void removeUneededCell(DCell cell) {
        final Session session = SessionManager.INSTANCE.getSession(cell.getTarget());
        final ECrossReferenceAdapter xref = session != null ? session.getSemanticCrossReferencer() : null;
        if (accessor.getPermissionAuthority().canDeleteInstance(cell)) {
            accessor.eDelete(cell, xref);
        }
    }

    /**
     * Refresh the intersection mapping label.
     * 
     * @param cell
     *            The current corresponding cell
     * @param labelExpression
     *            The label expression used to refresh the label
     * @return true if a cell is already needed for ths intersection mapping
     */
    private boolean refreshLabelIntersectionMapping(final DCell cell, final String labelExpression) {
        boolean cellNeeded = false;
        DLine line = cell.getLine();
        DColumn column = cell.getColumn();
        if (line != null && line.getTarget() != null && column != null && column.getTarget() != null) {
            Collection<EObject> foundColumnTargets = evaluateColumnFinderExpression(cell, cell.getIntersectionMapping());
            if (foundColumnTargets != null && foundColumnTargets.contains(column.getTarget())) {
                if (evaluateIntersectionPrecondition(cell.getColumn().getTarget(), cell.getLine(), cell.getColumn(), cell.getIntersectionMapping().getPreconditionExpression())) {
                    cellNeeded = refreshLabel(cell, labelExpression);
                }
            }
        }
        return cellNeeded;
    }

    /**
     * Search the column candidates for this line semantic element and this
     * intersection mapping.
     * 
     * @param cell
     *            the cell
     * @param iMapping
     *            The intersection mapping that contains the expression to
     *            evaluate
     * @return List of column candidates for this cell and this intersection
     *         mapping.
     */
    private Collection<EObject> evaluateColumnFinderExpression(DCell cell, IntersectionMapping iMapping) {
        EObject preconditionContext;
        if (iMapping.isUseDomainClass()) {
            preconditionContext = cell.getTarget();
        } else {
            preconditionContext = cell.getLine().getTarget();
        }

        try {
            return interpreter.evaluateCollection(preconditionContext, iMapping.getColumnFinderExpression());
        } catch (EvaluationException e) {
            return new ArrayList<EObject>(0);
        }
    }

    /**
     * Evaluate the precondition expression.
     * 
     * @param semanticElement
     *            The semanticElement to use as semantic element.
     * @param line
     *            The line to use as "line" variable by the interpreter
     * @param column
     *            The column to use as "column" variable by the interpreter
     * @param preconditionExpression
     *            The expression to evaluate.
     * @return The result of the expression evaluation
     */
    private boolean evaluateIntersectionPrecondition(final EObject semanticElement, final DLine line, final DColumn column, final String preconditionExpression) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        boolean result = true;
        if (!StringUtil.isEmpty(preconditionExpression)) {
            this.interpreter.setVariable(IInterpreterSiriusTableVariables.LINE, line);
            this.interpreter.setVariable(IInterpreterSiriusTableVariables.LINE_SEMANTIC, ((DSemanticDecorator) line).getTarget());
            this.interpreter.setVariable(IInterpreterSiriusTableVariables.COLUMN, column);
            this.interpreter.setVariable(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, ((DSemanticDecorator) column).getTarget());
            this.interpreter.setVariable(IInterpreterSiriusVariables.TABLE, TableHelper.getTable(line));
            try {
                result = interpreter.evaluateBoolean(semanticElement, preconditionExpression);
            } catch (final EvaluationException e) {
                // nothing special, keep silent
            }
            this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.LINE);
            this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.LINE_SEMANTIC);
            this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.COLUMN);
            this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC);
            this.interpreter.unSetVariable(IInterpreterSiriusVariables.TABLE);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        return result;
    }

    /**
     * Refresh the cell label.
     * 
     * @param cell
     *            cell to refresh.
     * @param labelExpression
     *            the new label for this Cell
     * @return
     */
    private boolean refreshLabel(final DCell cell, final String labelExpression) {
        boolean cellNeeded = false;
        if (!StringUtil.isEmpty(labelExpression)) {
            if (cell.getTarget() != null) {
                try {
                    if (cell.getLine() != null) {
                        this.interpreter.setVariable(IInterpreterSiriusTableVariables.LINE, cell.getLine());
                        this.interpreter.setVariable(IInterpreterSiriusTableVariables.LINE_SEMANTIC, cell.getLine().getTarget());
                        this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, cell.getLine().getTarget());
                    }
                    if (cell.getColumn() != null) {
                        this.interpreter.setVariable(IInterpreterSiriusTableVariables.COLUMN, cell.getColumn());
                        this.interpreter.setVariable(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, cell.getColumn().getTarget());
                    }
                    final DTable table = TableHelper.getTable(cell);
                    if (table != null) {
                        this.interpreter.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
                    }
                    final String label = interpreter.evaluateString(cell.getTarget(), labelExpression);
                    cellNeeded = true;
                    // We change the value only if it's different
                    if (isDifferent(cell.getLabel(), label)) {
                        cell.setLabel(label);
                    }
                } catch (final EvaluationException e) {
                    if (TableHelper.hasTableDescriptionOnlyOneLineMapping(cell)) {
                        RuntimeLoggerManager.INSTANCE.error(cell.getUpdater(), TablePackage.eINSTANCE.getDCell_Label(), e);
                    }
                    // Silent catch if many line mappings
                } finally {
                    if (cell.getLine() != null) {
                        this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                        this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.LINE);
                        this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.LINE_SEMANTIC);
                    }
                    if (cell.getColumn() != null) {
                        this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.COLUMN);
                        this.interpreter.unSetVariable(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC);
                    }
                    this.interpreter.unSetVariable(IInterpreterSiriusVariables.ROOT);
                }
            }
        } else {
            final ColumnMapping columnMapping = cell.getColumn().getOriginMapping();
            if (columnMapping instanceof FeatureColumnMapping) {
                if (cell.getTarget() != null) {
                    final String featureName = ((FeatureColumnMapping) columnMapping).getFeatureName();
                    if (!StringUtil.isEmpty(featureName)) {
                        cellNeeded = setLabelWithFeatureValue(cell, columnMapping, featureName);
                    } else {
                        cell.setLabel(""); //$NON-NLS-1$
                    }
                }
            } else {
                cellNeeded = true;
                // We change the value only if it's different
                if (isDifferent(cell.getLabel(), null)) {
                    cell.setLabel(null);
                }
            }
        }
        return cellNeeded;
    }

    private boolean setLabelWithFeatureValue(final DCell cell, final ColumnMapping columnMapping, final String featureName) {
        boolean init = false;
        String label = ""; //$NON-NLS-1$
        try {
            final Object featureObject = accessor.eGet(cell.getTarget(), featureName);
            if (featureObject != null) {
                // if featureObject is multivalue, return the label of all its
                // objects
                if (featureObject instanceof EList<?>) {
                    List<String> texts = Lists.newArrayList();
                    for (EObject obj : (EList<EObject>) featureObject) {
                        texts.add(getText(obj));
                    }
                    label = texts.toString();
                } else if (featureObject instanceof EObject) {
                    label = getText(featureObject);
                } else {
                    label = featureObject.toString();
                }
                init = true;
            } else if (cell.getTarget().eClass().getEStructuralFeature(featureName) != null) {
                // if the featureObject is null but the feature name exists, we
                // consider blank string as text.
                init = true;
            }
            if (init) {
                // We change the value only if it's different
                if (isDifferent(cell.getLabel(), label)) {
                    cell.setLabel(label);
                }
            }
        } catch (final FeatureNotFoundException e) {
            RuntimeLoggerManager.INSTANCE.error(columnMapping, DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureName(), e);
        }
        return init;
    }

    /**
     * Refresh the cell styles.
     * 
     * @param cell
     *            cell to refresh.
     */
    public void refreshStyle(final DCell cell) {
        DCellStyle style = cell.getCurrentStyle();
        if (style == null) {
            style = TableFactory.eINSTANCE.createDCellStyle();
        }
        doUpdateStyle(cell, style);
        if (cell.getCurrentStyle() == null && new DTableElementStyleQuery(style).isSet()) {
            cell.setCurrentStyle(style);
        }
    }

    /**
     * The columnUpdater takes priority over the lineUpdater.
     * 
     * @param cell
     * 
     * @param style
     */
    private void doUpdateStyle(final DCell cell, final DCellStyle style) {
        final TableStyleColorUpdater colorUpdater = new TableStyleColorUpdater();
        final StyleWithDefaultStatus bestBackgroundStyle = getBestBackgroundColor(cell);
        if (bestBackgroundStyle != null) {
            colorUpdater.updateBackgroundColor(style, ((BackgroundStyleDescription) bestBackgroundStyle.getStyle()).getBackgroundColor(), bestBackgroundStyle.isDefaultStyle(), cell.getTarget());
            if (new DCellQuery(cell).isStyleDescriptionInIntersectionMapping(bestBackgroundStyle.getStyle())) {
                style.setBackgroundStyleOrigin(cell.getIntersectionMapping());
            } else {
                style.setBackgroundStyleOrigin(cell.getColumn().getOriginMapping());
            }
        } else {
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle());
        }
        final StyleWithDefaultStatus bestForegroundStyle = getBestForegroundStyle(cell);
        if (bestForegroundStyle != null) {
            ForegroundStyleDescription bestForegroundStyleDesc = (ForegroundStyleDescription) bestForegroundStyle.getStyle();
            colorUpdater.updateForegroundColor(style, ((ForegroundStyleDescription) bestForegroundStyle.getStyle()).getForeGroundColor(), bestForegroundStyle.isDefaultStyle(), cell.getTarget());
            if (new DCellQuery(cell).isStyleDescriptionInIntersectionMapping(bestForegroundStyle.getStyle())) {
                style.setForegroundStyleOrigin(cell.getIntersectionMapping());
            } else {
                style.setForegroundStyleOrigin(cell.getColumn().getOriginMapping());
            }
            if (bestForegroundStyleDesc.getLabelFormat() != null && !isEqual(style.getLabelFormat(), bestForegroundStyleDesc.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyleDesc.getLabelFormat());
            }
            if (bestForegroundStyleDesc.getLabelSize() != -1 && style.getLabelSize() != bestForegroundStyleDesc.getLabelSize()) {
                style.setLabelSize(bestForegroundStyleDesc.getLabelSize());
            }
        } else {
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_LabelSize());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle());
        }
    }

    /**
     * Refresh the line styles.
     * 
     * @param line
     *            line to refresh.
     */
    protected void refreshStyle(final DLine line) {
        DTableElementStyle style = line.getCurrentStyle();
        if (style == null) {
            style = TableFactory.eINSTANCE.createDTableElementStyle();
        }
        doUpdateStyle(line, style);
        if (line.getCurrentStyle() == null && new DTableElementStyleQuery(style).isSet()) {
            line.setCurrentStyle(style);
        }
    }

    /**
     * Compute the best styles for this line. This rules are applied :
     * <UL>
     * <LI>the first conditional foreground style (with predicate expression
     * that returns true), otherwise the default foreground style</LI>
     * <LI>the first conditional background style (with predicate expression
     * that returns true), otherwise the default background style</LI>
     * <UL>
     * 
     * @param line
     *            The line to update
     * @param style
     *            the current style
     */
    private void doUpdateStyle(final DLine line, final DTableElementStyle style) {
        final TableStyleColorUpdater colorUpdater = new TableStyleColorUpdater();
        final ColorDescription bestBackgroundColor = getBestBackgroundColor(line);
        if (bestBackgroundColor != null) {
            colorUpdater.updateBackgroundColor(style, bestBackgroundColor, new StyleUpdaterQuery(line.getOriginMapping()).isDefaultBackgroundColor(bestBackgroundColor), line.getTarget());
        } else {
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor());
            }
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle());
            }
        }
        final ForegroundStyleDescription bestForegroundStyle = getBestForegroundStyle(line);
        if (bestForegroundStyle != null) {
            boolean defaultStyleDescription = new StyleUpdaterQuery(line.getOriginMapping()).isDefaultForegroundColor(bestForegroundStyle.getForeGroundColor());
            colorUpdater.updateForegroundColor(style, bestForegroundStyle.getForeGroundColor(), defaultStyleDescription, line.getTarget());
            if (bestForegroundStyle.getLabelFormat() != null && !isEqual(style.getLabelFormat(), bestForegroundStyle.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyle.getLabelFormat());
            }
            if (bestForegroundStyle.getLabelSize() != -1 && style.getLabelSize() != bestForegroundStyle.getLabelSize()) {
                style.setLabelSize(bestForegroundStyle.getLabelSize());
            }
            style.setDefaultForegroundStyle(defaultStyleDescription);
        } else {
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor());
            }
            if (style.getLabelFormat() != null && !style.getLabelFormat().isEmpty()) {
                style.getLabelFormat().clear();
            }
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_LabelSize())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_LabelSize());
            }
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle());
            }
        }
    }

    /**
     * Refresh the column styles.
     * 
     * @param column
     *            column to refresh.
     */
    protected void refreshStyle(final DColumn column) {
        DTableElementStyle style = column.getCurrentStyle();
        if (style == null) {
            style = TableFactory.eINSTANCE.createDTableElementStyle();
        }
        doUpdateStyle(column, style);
        if (column.getCurrentStyle() == null && new DTableElementStyleQuery(style).isSet()) {
            column.setCurrentStyle(style);
        }
    }

    /**
     * Compute the best styles for this column. This rules are applied :
     * <UL>
     * <LI>the default foreground style (and only if the color use for it is not
     * with variable parts: ComputedColor or InterpolatedColor)</LI>
     * <LI>the default background style (and only if the color use for it is not
     * with variable parts: ComputedColor or InterpolatedColor)</LI>
     * <UL>
     * 
     * @param column
     *            The column to update
     * @param style
     *            the current style
     */
    private void doUpdateStyle(final DColumn column, final DTableElementStyle style) {
        StyleUpdater styleUpdater = null;
        if (column.getOriginMapping() instanceof FeatureColumnMapping || column.getOriginMapping() instanceof ElementColumnMapping) {
            styleUpdater = (StyleUpdater) column.getOriginMapping();
        }
        final TableStyleColorUpdater colorUpdater = new TableStyleColorUpdater();
        final ColorDescription bestBackgroundColor = getBestBackgroundColor(column);
        if (bestBackgroundColor != null) {
            colorUpdater.updateBackgroundColor(style, bestBackgroundColor, new StyleUpdaterQuery(styleUpdater).isDefaultBackgroundColor(bestBackgroundColor), column.getTarget());
        } else {
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor());
            }
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle());
            }
        }
        final ForegroundStyleDescription bestForegroundStyle = getBestForegroundStyle(column);
        if (bestForegroundStyle != null) {
            boolean defaultStyleDescription = new StyleUpdaterQuery(styleUpdater).isDefaultForegroundColor(bestForegroundStyle.getForeGroundColor());
            colorUpdater.updateForegroundColor(style, bestForegroundStyle.getForeGroundColor(), defaultStyleDescription, column.getTarget());
            if (bestForegroundStyle.getLabelFormat() != null && !isEqual(style.getLabelFormat(), bestForegroundStyle.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyle.getLabelFormat());
            }
            if (bestForegroundStyle.getLabelSize() != -1 && style.getLabelSize() != bestForegroundStyle.getLabelSize()) {
                style.setLabelSize(bestForegroundStyle.getLabelSize());
            }
            style.setDefaultForegroundStyle(defaultStyleDescription);
        } else {
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor());
            }
            if (style.getLabelFormat() != null && !style.getLabelFormat().isEmpty()) {
                style.getLabelFormat().clear();
            }
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_LabelSize())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_LabelSize());
            }
            if (style.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle())) {
                style.eUnset(TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle());
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
     * TODO : TBD <BR>
     * The first conditional background style of the cell (with predicate
     * expression that returns true). In this case the backgroundStyleOrigin
     * references the intersection mapping and the defaultStyle is equal false. <BR>
     * Otherwise the first conditional background style of the column (with
     * predicate expression that returns true). In this case the
     * backgroundStyleOrigin references the column mapping and the defaultStyle
     * is equal false. <BR>
     * Otherwise, if it exists, the default background style of the cell. In
     * this case the backgroundStyleOrigin references the intersection mapping
     * and the defaultStyle is equal true. <BR>
     * Otherwise, if the default background style of the column uses variable
     * color, the default background style of the column. In this case the
     * backgroundStyleOrigin references the column mapping and the defaultStyle
     * is equal true.
     * 
     * @param cell
     *            The current Cell
     * @param lineUpdater
     *            The associate {@link StyleUpdater line updater}
     * @param columnUpdater
     *            The associate {@link StyleUpdater column updater}
     * @return The best colorMapping for this cell, or null otherwise
     */
    private StyleWithDefaultStatus getBestBackgroundColor(final DCell cell) {
        BackgroundStyleDescription bestBackgroundStyleDesc = null;
        boolean bestBackgroundColorIsConditonal = false;

        // Get the style updater for the cell
        StyleUpdater cellStyleUpdater = cell.getIntersectionMapping();
        // Get the style updater for the column
        StyleUpdater columnStyleUpdater = null;
        if (cell.getColumn() != null && (cell.getColumn().getOriginMapping() instanceof FeatureColumnMapping || cell.getColumn().getOriginMapping() instanceof ElementColumnMapping)) {
            columnStyleUpdater = (StyleUpdater) cell.getColumn().getOriginMapping();
        }
        // Use the default style
        if (cellStyleUpdater != null) {
            if (cellStyleUpdater.getDefaultBackground() != null && cellStyleUpdater.getDefaultBackground().getBackgroundColor() != null) {
                bestBackgroundStyleDesc = cellStyleUpdater.getDefaultBackground();
            }
        }
        // If no default style for cell use the default style of column (if it
        // deos not use FixedColor)
        if (columnStyleUpdater != null && bestBackgroundStyleDesc == null) {
            if (columnStyleUpdater.getDefaultBackground() != null && !(columnStyleUpdater.getDefaultBackground().getBackgroundColor() instanceof FixedColor)) {
                bestBackgroundStyleDesc = columnStyleUpdater.getDefaultBackground();
            }
        }
        // Replace the default style by the first true conditional style of the
        // cell
        if (cellStyleUpdater != null) {
            for (BackgroundConditionalStyle condStyle : cellStyleUpdater.getBackgroundConditionalStyle()) {
                if (!bestBackgroundColorIsConditonal) {
                    if (condStyle.getStyle() != null && cell.getTarget() != null) {
                        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
                        boolean predicate = safeInterpreter.evaluateBoolean(cell.getTarget(), condStyle, DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle_PredicateExpression());
                        if (predicate) {
                            bestBackgroundStyleDesc = condStyle.getStyle();
                            bestBackgroundColorIsConditonal = true;
                        }
                    }
                }
            }
        }
        // If no conditional style for cell, replace the default style by the
        // first true conditional style of the column
        if (columnStyleUpdater != null) {
            for (BackgroundConditionalStyle condStyle : columnStyleUpdater.getBackgroundConditionalStyle()) {
                if (!bestBackgroundColorIsConditonal) {
                    if (condStyle.getStyle() != null && cell.getTarget() != null) {
                        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
                        boolean predicate = safeInterpreter.evaluateBoolean(cell.getTarget(), condStyle, DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle_PredicateExpression());
                        if (predicate) {
                            bestBackgroundStyleDesc = condStyle.getStyle();
                            bestBackgroundColorIsConditonal = true;
                        }
                    }
                }
            }
        }
        if (bestBackgroundStyleDesc != null) {
            return new StyleWithDefaultStatus(bestBackgroundStyleDesc, !bestBackgroundColorIsConditonal);
        } else {
            return null;
        }
    }

    /**
     * Return a value only if the current background color is the default
     * background color or one of the conditional background colors.
     * 
     * @param line
     *            The current DLine
     * @return The best background color for this line, or null otherwise
     */
    private ColorDescription getBestBackgroundColor(final DLine line) {
        ColorDescription bestBackgroundColor = null;
        boolean bestBackgroundColorIsConditonal = false;

        StyleUpdater styleUpdater = line.getOriginMapping();
        if (styleUpdater.getDefaultBackground() != null && styleUpdater.getDefaultBackground().getBackgroundColor() != null) {
            bestBackgroundColor = styleUpdater.getDefaultBackground().getBackgroundColor();
        }
        for (BackgroundConditionalStyle condStyle : styleUpdater.getBackgroundConditionalStyle()) {
            if (!bestBackgroundColorIsConditonal) {
                if (condStyle.getStyle() != null && line.getTarget() != null) {
                    final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
                    boolean predicate = safeInterpreter.evaluateBoolean(line.getTarget(), condStyle, DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle_PredicateExpression());
                    if (predicate) {
                        bestBackgroundColor = condStyle.getStyle().getBackgroundColor();
                        bestBackgroundColorIsConditonal = true;
                    }
                }
            }
        }
        return bestBackgroundColor;
    }

    /**
     * The best background color is the default background color if it uses a
     * FixedColor.
     * 
     * @param column
     *            The current DLine
     * @return The default background color if it uses a FixedColor for this
     *         column, or null otherwise
     */
    private ColorDescription getBestBackgroundColor(final DColumn column) {
        ColorDescription bestBackgroundColor = null;
        StyleUpdater styleUpdater = null;
        if (column.getOriginMapping() instanceof FeatureColumnMapping || column.getOriginMapping() instanceof ElementColumnMapping) {
            styleUpdater = (StyleUpdater) column.getOriginMapping();
        }
        if (styleUpdater.getDefaultBackground() != null && styleUpdater.getDefaultBackground().getBackgroundColor() instanceof FixedColor) {
            bestBackgroundColor = styleUpdater.getDefaultBackground().getBackgroundColor();
        }
        return bestBackgroundColor;
    }

    /**
     * TODO : TBD The priority of the StyleUpdater is (the highest priority to
     * lowest priority) :
     * <UL>
     * <LI>Intersection mapping (conditional style)</LI>
     * <LI>Line mapping (conditional style)</LI>
     * <LI>Column Mapping (conditional style)</LI>
     * <LI>Intersection mapping (default style)</LI>
     * <LI>Line mapping (default style)</LI>
     * <LI>Column Mapping (default style)</LI>
     * </UL>
     * 
     * @param cell
     *            The current Cell
     * @return The best ForegroundStyleDescription for this cell, or null
     *         otherwise
     */
    private StyleWithDefaultStatus getBestForegroundStyle(final DCell cell) {
        ForegroundStyleDescription bestForegroundStyleDesc = null;
        boolean bestForegroundColorIsConditonal = false;

        // Get the style updater for the cell
        StyleUpdater cellStyleUpdater = cell.getIntersectionMapping();
        // Get the style updater for the column
        StyleUpdater columnStyleUpdater = null;
        if (cell.getColumn() != null && (cell.getColumn().getOriginMapping() instanceof FeatureColumnMapping || cell.getColumn().getOriginMapping() instanceof ElementColumnMapping)) {
            columnStyleUpdater = (StyleUpdater) cell.getColumn().getOriginMapping();
        }
        // Use the default style
        if (cellStyleUpdater != null) {
            if (cellStyleUpdater.getDefaultForeground() != null && cellStyleUpdater.getDefaultForeground().getForeGroundColor() != null) {
                bestForegroundStyleDesc = cellStyleUpdater.getDefaultForeground();
            }
        }
        // If no default style for cell use the default style of column (if it
        // deos not use FixedColor)
        if (columnStyleUpdater != null && bestForegroundStyleDesc == null) {
            if (columnStyleUpdater.getDefaultForeground() != null && !(columnStyleUpdater.getDefaultForeground().getForeGroundColor() instanceof FixedColor)) {
                bestForegroundStyleDesc = columnStyleUpdater.getDefaultForeground();
            }
        }
        // Replace the default style by the first true conditional style of the
        // cell
        if (cellStyleUpdater != null) {
            for (ForegroundConditionalStyle condStyle : cellStyleUpdater.getForegroundConditionalStyle()) {
                if (!bestForegroundColorIsConditonal) {
                    if (condStyle.getStyle() != null && cell.getTarget() != null) {
                        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
                        boolean predicate = safeInterpreter.evaluateBoolean(cell.getTarget(), condStyle, DescriptionPackage.eINSTANCE.getForegroundConditionalStyle_PredicateExpression());
                        if (predicate) {
                            bestForegroundStyleDesc = condStyle.getStyle();
                            bestForegroundColorIsConditonal = true;
                        }
                    }
                }
            }
        }
        // If no conditional style for cell, replace the default style by the
        // first true conditional style of the column
        if (columnStyleUpdater != null) {
            for (ForegroundConditionalStyle condStyle : columnStyleUpdater.getForegroundConditionalStyle()) {
                if (!bestForegroundColorIsConditonal) {
                    if (condStyle.getStyle() != null && cell.getTarget() != null) {
                        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
                        boolean predicate = safeInterpreter.evaluateBoolean(cell.getTarget(), condStyle, DescriptionPackage.eINSTANCE.getForegroundConditionalStyle_PredicateExpression());
                        if (predicate) {
                            bestForegroundStyleDesc = condStyle.getStyle();
                            bestForegroundColorIsConditonal = true;
                        }
                    }
                }
            }
        }
        if (bestForegroundStyleDesc != null) {
            return new StyleWithDefaultStatus(bestForegroundStyleDesc, !bestForegroundColorIsConditonal);
        } else {
            return null;
        }
    }

    /**
     * TODO: Doc to review.
     * 
     * The priority of the StyleUpdater is (the highest priority to lowest
     * priority) :
     * <UL>
     * <LI>Intersection mapping (conditional style)</LI>
     * <LI>Line mapping (conditional style)</LI>
     * <LI>Column Mapping (conditional style)</LI>
     * <LI>Intersection mapping (default style)</LI>
     * <LI>Line mapping (default style)</LI>
     * <LI>Column Mapping (default style)</LI>
     * </UL>
     * 
     * @param line
     *            The current DLine
     * @return The best ForegroundStyleDescription for this cell, or null
     *         otherwise
     */
    private ForegroundStyleDescription getBestForegroundStyle(final DLine line) {
        ForegroundStyleDescription bestForegroundStyleDescription = null;
        boolean bestStyleIsConditonalStyle = false;

        StyleUpdater styleUpdater = line.getOriginMapping();
        if (styleUpdater.getDefaultForeground() != null) {
            bestForegroundStyleDescription = styleUpdater.getDefaultForeground();
        }
        for (ForegroundConditionalStyle condStyle : styleUpdater.getForegroundConditionalStyle()) {
            if (!bestStyleIsConditonalStyle) {
                if (condStyle.getStyle() != null && line.getTarget() != null) {
                    final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
                    boolean predicate = safeInterpreter.evaluateBoolean(line.getTarget(), condStyle, DescriptionPackage.eINSTANCE.getForegroundConditionalStyle_PredicateExpression());
                    if (predicate) {
                        bestForegroundStyleDescription = condStyle.getStyle();
                        bestStyleIsConditonalStyle = true;
                    }
                }
            }
        }
        return bestForegroundStyleDescription;
    }

    /**
     * The best foreground style is the default foreground style if it uses a
     * FixedColor.
     * 
     * @param column
     *            The current DLine
     * @return The default foreground style if it uses a FixedColor for this
     *         column, or null otherwise
     */
    private ForegroundStyleDescription getBestForegroundStyle(final DColumn column) {
        ForegroundStyleDescription bestForegroundStyleDescription = null;
        StyleUpdater styleUpdater = null;
        if (column.getOriginMapping() instanceof FeatureColumnMapping || column.getOriginMapping() instanceof ElementColumnMapping) {
            styleUpdater = (StyleUpdater) column.getOriginMapping();
        }
        if (styleUpdater.getDefaultForeground() != null && styleUpdater.getDefaultForeground().getForeGroundColor() instanceof FixedColor) {
            bestForegroundStyleDescription = styleUpdater.getDefaultForeground();
        }
        return bestForegroundStyleDescription;
    }

    /**
     * @param oldObject
     *            The old object to test
     * @param newObject
     *            The new object to test
     * @return true if newObject is different from oldObject, false otherwise
     */
    private boolean isDifferent(final Object oldObject, final Object newObject) {
        if (newObject == null && oldObject == null) {
            return false;
        }
        return (newObject != null && !newObject.equals(oldObject)) || (newObject == null && oldObject != null);
    }

    /**
     * Affect semantic elements to a cell.
     * 
     * @param newCell
     *            cell to update.
     */
    public void refreshSemanticElements(final DCell newCell) {
        final Collection<EObject> newElements = Lists.newArrayList();
        if (newCell.getIntersectionMapping() != null) {
            refreshSemanticElements(newCell, newCell.getIntersectionMapping());
        } else {
            if (newCell.getColumn().getOriginMapping() instanceof FeatureColumnMapping) {
                if (newCell.getTarget() != null) {
                    newElements.add(newCell.getTarget());
                }
                if (newCell.getLine() != null) {
                    newElements.addAll(newCell.getLine().getSemanticElements());
                }
            } else if (newCell.getColumn() instanceof DTargetColumn) {
                if (newCell.getTarget() != null) {
                    newElements.add(newCell.getTarget());
                }
                if (newCell.getLine() != null) {
                    newElements.add(newCell.getLine().getTarget());
                    newElements.addAll(newCell.getLine().getSemanticElements());
                    newElements.add(((DTargetColumn) newCell.getColumn()).getTarget());
                    newElements.addAll(((DTargetColumn) newCell.getColumn()).getSemanticElements());
                }
            }
            synchronizeLists(newCell.getSemanticElements(), newElements);
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
     * Evaluate the semantic elements feature of the mapping and affect them to
     * the given table element.
     * 
     * @param tableElement
     *            table element to affect.
     * @param mapping
     *            mapping used to retrieve the semantic elements.
     */
    public void refreshSemanticElements(final DTableElement tableElement, final TableMapping mapping) {
        if (mapping.getSemanticElements() != null && !StringUtil.isEmpty(mapping.getSemanticElements())) {

            if (tableElement.eContainer() != null) {
                this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, tableElement.eContainer());
                if (tableElement.eContainer() instanceof DSemanticDecorator) {
                    this.interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((DSemanticDecorator) tableElement.eContainer()).getTarget());
                }
            }
            this.interpreter.setVariable(IInterpreterSiriusVariables.VIEW, tableElement);

            Collection<EObject> elements;
            if (mapping instanceof LineMapping || TableHelper.hasTableDescriptionOnlyOneLineMapping(mapping)) {
                elements = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(tableElement.getTarget(), mapping, DescriptionPackage.eINSTANCE.getTableMapping_SemanticElements());
            } else {
                try {
                    elements = interpreter.evaluateCollection(tableElement.getTarget(), mapping.getSemanticElements());
                } catch (final EvaluationException e) {
                    // Silent catch
                    elements = Collections.emptyList();
                }
            }
            synchronizeLists(tableElement.getSemanticElements(), elements);

            if (tableElement.eContainer() != null) {
                this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
                if (tableElement.eContainer() instanceof DSemanticDecorator) {
                    this.interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                }
            }
            this.interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);

        } else if (tableElement.getTarget() != null) {
            synchronizeLists(tableElement.getSemanticElements(), Collections.singletonList(tableElement.getTarget()));
        } else {
            final Collection<EObject> elements = Collections.emptyList();
            synchronizeLists(tableElement.getSemanticElements(), elements);
        }
    }

    private String getText(final Object element) {
        String text = null;
        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(element, IItemLabelProvider.class);
        try {
            if (itemLabelProvider != null) {
                AdapterFactoryItemDelegator adapterFactoryItemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
                text = adapterFactoryItemDelegator.getText(element);
            } else {
                ReflectiveItemProvider reflectiveItemProvider = new ReflectiveItemProvider(adapterFactory);
                text = reflectiveItemProvider.getText(element);
            }
        } finally {
            adapterFactory.dispose();
        }
        return text;
    }

    private void reset(EObject target, EStructuralFeature feature) {
        if (target.eIsSet(feature)) {
            target.eUnset(feature);
        }
    }
}
