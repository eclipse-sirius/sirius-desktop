/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.business.internal.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.sirius.business.api.logger.InterpretationContext;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
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
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;

/**
 * Synchronizer for the table elements.
 * 
 * @author cbrun
 * 
 */
public class DTableElementSynchronizer {

    /**
     * '*' as feature name allows to skip the feature name validation for cell creation.
     */
    protected static final String SKIP_FEATURENAME_VALIDATION = "*"; //$NON-NLS-1$

    private final ModelAccessor accessor;

    private final RuntimeLoggerInterpreter interpreter;

    /**
     * Synchronizer for table elements.
     * 
     * @param accessor
     *            current model accessor.
     * @param interpreter
     *            current interpreter.
     */
    public DTableElementSynchronizer(final ModelAccessor accessor, final IInterpreter interpreter) {
        this.interpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
        this.accessor = accessor;
    }

    private String getHeaderLabel(DTableElement header, TableMapping mapping, EStructuralFeature labelExpression) {
        if (!StringUtil.isEmpty((String) mapping.eGet(labelExpression))) {
            return InterpretationContext.with(interpreter, it -> {
                it.setLogError(false);
                return interpreter.evaluateString(header.getTarget(), mapping, labelExpression);
            });

        } else {
            // If there is no headerLabelExpression, 
            // we use the label provider to get label to display
            return getText(header.getTarget());
        }
    }
    
    /**
     * Refresh a line.
     * 
     * @param line
     *            line to refresh.
     */
    public void refresh(final DLine line) {
        if (accessor.getPermissionAuthority().canEditInstance(line)) {
            final String label = getHeaderLabel(line, line.getOriginMapping(), 
                    DescriptionPackage.eINSTANCE.getLineMapping_HeaderLabelExpression());
            setLabelOnUpdate(line, label);
            // Trac #1125 Line height modification 
            // (aborted because of Windows SWT limitation)
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
    public void refresh(final DColumn column) {
        if (accessor.getPermissionAuthority().canEditInstance(column)) {
            if (column instanceof DTargetColumn) {
                refresh((DTargetColumn) column);
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
            final String label = getHeaderLabel(column, column.getOriginMapping(), 
                    DescriptionPackage.eINSTANCE.getColumnMapping_HeaderLabelExpression());
            setLabelOnUpdate(column, label);
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
            setLabelOnUpdate(column, mapping.getHeaderLabelExpression());
            if (mapping.getInitialWidth() != 0 && column.getWidth() == 0) {
                column.setWidth(mapping.getInitialWidth());
            }
            refreshStyle(column);
        }
    }

    /**
     * Refresh the target of the cell (only for the FeatureColumn). The target can be change if the
     * featureParentExpression is changed in the odesign file.<BR>
     * If this cell now corresponds to an invalid feature, it is removed from its line.
     * 
     * @param cell
     *            cell to refresh.
     * @return true if the cell still exists after this refresh, false otherwise (if the cell is deleted because of an
     *         invalid featureName for featureParent).
     */
    public boolean refreshTarget(final DCell cell) {
        if (cell.getLine() == null) {
            return true; // decrease complexity
        }
        boolean deletedCell = false;
        EObject featureParent = cell.getLine().getTarget();
        if (cell.getColumn() instanceof DFeatureColumn) {
            final FeatureColumnMapping featureColumnMapping = (FeatureColumnMapping) cell.getColumn().getMapping();
            final String featureParentExpression = featureColumnMapping.getFeatureParentExpression();
            if (featureParentExpression != null && featureParentExpression.length() > 0) {
                final DTable table = TableHelper.getTable(cell);
                featureParent = InterpretationContext.with(interpreter, it-> {
                    it.setVariable(IInterpreterSiriusVariables.CONTAINER, cell.getLine().getTarget());
                    it.setVariable(IInterpreterSiriusTableVariables.LINE, cell.getLine());
                    it.setVariable(IInterpreterSiriusTableVariables.TABLE, table);
                    if (table != null) {
                        it.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
                    }
                    it.setLogError(TableHelper.hasTableDescriptionOnlyOneLineMapping(cell));
                    return interpreter.evaluateEObject(cell.getLine().getTarget(), featureColumnMapping,
                                DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureParentExpression());

                });

            }
            if (featureParent == null) {
                removeUneededCell(cell);
                deletedCell = true;
            } else if (!featureParent.equals(cell.getTarget())) {
                if (isValidFeatureMapping(featureParent, featureColumnMapping)) {
                    cell.setTarget(featureParent);
                } else {
                    removeUneededCell(cell);
                    deletedCell = true;
                }
            }
        }

        return !deletedCell;
    }

    private boolean isValidFeatureMapping(EObject target, FeatureColumnMapping mapping) {
        String featureName = mapping.getFeatureName();
        return SKIP_FEATURENAME_VALIDATION.equals(featureName) || accessor.eValid(target, featureName);
    }
    
    /**
     * Refresh the cell label.
     * 
     * @param cell
     *            cell to refresh.
     * @return true if the cell still exists after this refresh, false otherwise.
     */
    public boolean refreshLabel(final DCell cell) {
        boolean cellStillExists = false;
        DColumn column = cell.getColumn();
        if (column != null) {
            final CellUpdater updater = cell.getUpdater();
            if (updater instanceof IntersectionMapping && cell.getLabel() != null) {
                cellStillExists = refreshValidIntersectionLabel(cell, (IntersectionMapping) updater);
            } else {
                cellStillExists = updateCellLabel(cell);
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
        Session session = SessionManager.INSTANCE.getSession(cell.getTarget());
        if (session == null) {
            // If session is null, the semantic element might have already been
            // deleted and/or cell might be partially deleted but still
            // referenced from its column for example.
            DLine line = cell.getLine();
            DColumn column = cell.getColumn();
            EObject target = null;
            if (line != null) {
                target = line.getTarget();
            } else if (column instanceof DFeatureColumn && column.getTable() != null) {
                target = column.getTable().getTarget();
            } else if (column instanceof DTargetColumn) {
                target = column.getTarget();
            }

            if (target != null) {
                session = SessionManager.INSTANCE.getSession(target);
            }
        }

        if (accessor.getPermissionAuthority().canDeleteInstance(cell)) {
            if (session != null) {
                final ECrossReferenceAdapter xref = session.getSemanticCrossReferencer();
                accessor.eDelete(cell, xref);
            } else {
                cell.setLine(null);
                cell.setColumn(null);
            }
        }
    }

    /**
     * Refresh the intersection mapping label.
     * 
     * @param cell
     *            The current corresponding cell
     * @param intersectionMapping
     *            The intersection mapping that contains the expression to evaluate
     *  
     * @return true if a cell is already needed
     */
    private boolean refreshValidIntersectionLabel(final DCell cell, final IntersectionMapping intersectionMapping) {
        boolean cellNeeded = false;
        DLine line = cell.getLine();
        DColumn column = cell.getColumn();
        if (line != null 
    		    && line.getTarget() != null 
    		    && column != null 
    		    && column.getTarget() != null) {
            Collection<EObject> foundColumnTargets = evaluateColumnFinderExpression(cell, intersectionMapping);
            if (foundColumnTargets.contains(column.getTarget())) {
                if (evaluateIntersectionPrecondition(cell.getColumn().getTarget(), cell.getLine(), cell.getColumn(), intersectionMapping)) {
                    cellNeeded = updateCellLabel(cell);
                }
            }
        }
        return cellNeeded;
    }

    /**
     * Search the column candidates for this line semantic element and this intersection mapping.
     * 
     * @param cell
     *            the cell
     * @param iMapping
     *            The intersection mapping that contains the expression to evaluate
     * @return List of column candidates for this cell and this intersection mapping.
     */
    private Collection<EObject> evaluateColumnFinderExpression(DCell cell, IntersectionMapping iMapping) {
        DSemanticDecorator source;
        DSemanticDecorator container;
        if (iMapping.isUseDomainClass()) {
            source = cell;
            container = TableHelper.getTable(cell.getLine());
        } else {
            source = cell.getLine();
            container = source;
        }
        return evaluateColumnFinderExpression(source.getTarget(), container, iMapping, false);
    }
    
    
    /**
     * Search the column candidates for this line semantic element and this intersection mapping.
     * 
     * @param candidate
     *            Semantic element to find from
     * @param container
     *            the table or line to search from (accordingly with using domain class)
     * @param iMapping
     *            The intersection mapping that contains the expression to evaluate
     * @param logError
     *            Flag to log if an error happens
     * @return List of column candidates for this cell and this intersection mapping.
     */
    Collection<EObject> evaluateColumnFinderExpression(EObject candidate, DSemanticDecorator container, IntersectionMapping iMapping, boolean logError) {
        return InterpretationContext.with(interpreter, it -> {
            it.setLogError(logError);
            DTable table;
            if (iMapping.isUseDomainClass()) {
                table = (DTable) container;
            } else {
                DLine line = (DLine) container;
                it.setVariable(IInterpreterSiriusTableVariables.LINE, line);
                it.setVariable(IInterpreterSiriusTableVariables.LINE_SEMANTIC, line.getTarget());
                table = TableHelper.getTable(line);            
            }
                    
            it.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
            it.setVariable(IInterpreterSiriusVariables.TABLE, table);
            // Legacy
            it.setVariable(IInterpreterSiriusVariables.VIEWPOINT, table);

            
            return interpreter.evaluateCollection(candidate, iMapping, DescriptionPackage.eINSTANCE.getIntersectionMapping_ColumnFinderExpression());
        });
    }
    
    
    /**
     * Search the line candidates for this cell semantic element and this intersection mapping.
     * 
     * @param candidate
     *            Semantic element to find from
     * @param table
     *            the table to search from
     * @param iMapping
     *            The intersection mapping that contains the expression to evaluate
     * @return List of column candidates for this cell and this intersection mapping.
     */
    public Collection<EObject> evaluateLineFinderExpression(EObject candidate, DTable table, IntersectionMapping iMapping) {
        return InterpretationContext.with(interpreter, it -> {
            it.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
            it.setVariable(IInterpreterSiriusVariables.VIEWPOINT, table);
            it.setVariable(IInterpreterSiriusVariables.TABLE, table);            
            return interpreter.evaluateCollection(candidate, iMapping, DescriptionPackage.eINSTANCE.getIntersectionMapping_LineFinderExpression());
        });
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
     * @param mapping
     *            The mapping to evaluate.
     * @return The result of the expression evaluation
     */
    public boolean evaluateIntersectionPrecondition(final EObject semanticElement, final DLine line, final DColumn column, final IntersectionMapping mapping) {
        String preconditionExpression = mapping.getPreconditionExpression();
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        boolean result = true;
        if (!StringUtil.isEmpty(preconditionExpression)) {
            result = InterpretationContext.with(interpreter, it -> {
                it.setLogError(false);
                it.setVariable(IInterpreterSiriusTableVariables.LINE, line);
                it.setVariable(IInterpreterSiriusTableVariables.LINE_SEMANTIC, ((DSemanticDecorator) line).getTarget());
                it.setVariable(IInterpreterSiriusTableVariables.COLUMN, column);
                it.setVariable(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, ((DSemanticDecorator) column).getTarget());
                it.setVariable(IInterpreterSiriusVariables.TABLE, TableHelper.getTable(line));
                return interpreter.evaluateBoolean(semanticElement, mapping, DescriptionPackage.eINSTANCE.getIntersectionMapping_PreconditionExpression());
            });
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHECK_PRECONDITION_KEY);
        return result;
    }

    /**
     * Refresh the cell label.
     * 
     * @param cell
     *            cell to refresh.
     * @return true if cell is needed
     */
    private boolean updateCellLabel(final DCell cell) {
        CellUpdater updater = cell.getUpdater();

        boolean cellNeeded = false;
        if (updater instanceof IntersectionMapping) {
            cellNeeded = updateCellLabel(cell, (IntersectionMapping) updater);
        } else if (updater instanceof FeatureColumnMapping) {
            cellNeeded = updateCellLabel(cell, (FeatureColumnMapping) updater);
        }

        return cellNeeded;
    }
    
    private boolean updateCellLabel(final DCell cell, IntersectionMapping updater) {
        boolean cellNeeded = false;
        
        if (!StringUtil.isEmpty(updater.getLabelComputationExpression())) {
            cellNeeded = updateCellLabel(cell, updater, DescriptionPackage.eINSTANCE.getIntersectionMapping_LabelExpression());
        } else {
            // Even without label when may need color.
            cellNeeded = true;
            setLabelOnUpdate(cell, null);
        }

        return cellNeeded;
    }

    
    private boolean updateCellLabel(final DCell cell, FeatureColumnMapping updater) {
        boolean cellNeeded = false;
        final String featureName = updater.getFeatureName();
        
        if (isValidFeatureMapping(cell.getTarget(), updater)) {
            if (!StringUtil.isEmpty(updater.getLabelComputationExpression())) {
                cellNeeded = updateCellLabel(cell, updater, DescriptionPackage.eINSTANCE.getFeatureColumnMapping_LabelExpression());
            } else {
                cellNeeded = updateCellLabel(cell, updater, featureName);
            }
        } // ill-mapped: no cell
        return cellNeeded;
    }
    
    private boolean updateCellLabel(final DCell cell, CellUpdater updater, EStructuralFeature labelFeature) {

        if (cell.getTarget() == null) {
            return false; // cell not needed
        }
        String label = InterpretationContext.with(interpreter, it -> {
            it.setLogError(TableHelper.hasTableDescriptionOnlyOneLineMapping(cell));
            if (cell.getLine() != null) {
                it.setVariable(IInterpreterSiriusTableVariables.LINE, cell.getLine());
                it.setVariable(IInterpreterSiriusTableVariables.LINE_SEMANTIC, cell.getLine().getTarget());
                it.setVariable(IInterpreterSiriusVariables.CONTAINER, cell.getLine().getTarget());
            }
            if (cell.getColumn() != null) {
                it.setVariable(IInterpreterSiriusTableVariables.COLUMN, cell.getColumn());
                it.setVariable(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, cell.getColumn().getTarget());
            }
            final DTable table = TableHelper.getTable(cell);
            if (table != null) {
                it.setVariable(IInterpreterSiriusVariables.TABLE, table);                
                it.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
            }

            return interpreter.evaluateString(cell.getTarget(), cell.getUpdater(), labelFeature);
        });

        setLabelOnUpdate(cell, label);
        return true;

    }
    

    private boolean updateCellLabel(final DCell cell, final ColumnMapping columnMapping, final String featureName) {
        String label = null;
        try {
            final Object featureObject = accessor.eGet(cell.getTarget(), featureName);
            if (featureObject != null) {
                // if featureObject is multivalue, 
            	// return the label of all its objects
                if (featureObject instanceof Collection) {
                    List<String> texts = new ArrayList<>();
                    for (Object obj : (Collection<?>) featureObject) {
                        texts.add(getValueText(obj));
                    }
                    label = texts.toString();
                } else {
                    label = getValueText(featureObject);
                }
            } else if (cell.getTarget().eClass().getEStructuralFeature(featureName) != null) {
                // if the featureObject is null but the feature name exists, we
                // consider blank string as text.
                label = ""; //$NON-NLS-1$
            }
            if (label != null) {
                setLabelOnUpdate(cell, label);
            }
        } catch (final FeatureNotFoundException e) {
            RuntimeLoggerManager.INSTANCE.error(columnMapping, DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureName(), e);
        }
        return label != null;
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
        
        // Get the style updater for the column
        ColumnMapping columnMapping = null;
        StyleUpdater columnStyleUpdater = null;
        DColumn column = cell.getColumn();
        if (column != null) { // Suspicious
            columnMapping = column.getOriginMapping();
            if (columnMapping instanceof FeatureColumnMapping || columnMapping instanceof ElementColumnMapping) {
                columnStyleUpdater = (StyleUpdater) columnMapping;
            }
        }

        doUpdateFgStyle(cell, style, colorUpdater, columnMapping, columnStyleUpdater);
        doUpdateBgStyle(cell, style, colorUpdater, columnMapping, columnStyleUpdater);
    }


    private void doUpdateFgStyle(final DCell cell, final DCellStyle style, final TableStyleColorUpdater colorUpdater, ColumnMapping columnMapping,
            StyleUpdater columnStyleUpdater) {
        IntersectionMapping intersectionMapping = cell.getIntersectionMapping();
        final StyleWithDefaultStatus bestBackgroundStyle = getBestBackgroundColor(cell, intersectionMapping, columnStyleUpdater);
        if (bestBackgroundStyle != null) {
            colorUpdater.updateBackgroundColor(style, ((BackgroundStyleDescription) bestBackgroundStyle.getStyle()).getBackgroundColor(), 
                    bestBackgroundStyle.isDefaultStyle(), cell.getTarget());
            if (new DCellQuery(cell).isStyleDescriptionInIntersectionMapping(bestBackgroundStyle.getStyle())) {
                style.setBackgroundStyleOrigin(intersectionMapping);
            } else {
                style.setBackgroundStyleOrigin(columnMapping);
            }
        } else {
            resetStyle(style, false);
        }
    }


    private void doUpdateBgStyle(final DCell cell, final DCellStyle style, final TableStyleColorUpdater colorUpdater, ColumnMapping columnMapping,
            StyleUpdater columnStyleUpdater) {
        IntersectionMapping intersectionMapping = cell.getIntersectionMapping();
        final StyleWithDefaultStatus bestForegroundStyle = getBestForegroundStyle(cell, intersectionMapping, columnStyleUpdater);
        if (bestForegroundStyle != null) {
            ForegroundStyleDescription bestForegroundStyleDesc = (ForegroundStyleDescription) bestForegroundStyle.getStyle();
            colorUpdater.updateForegroundColor(style, ((ForegroundStyleDescription) bestForegroundStyle.getStyle()).getForeGroundColor(), 
                    bestForegroundStyle.isDefaultStyle(), cell.getTarget());
            if (new DCellQuery(cell).isStyleDescriptionInIntersectionMapping(bestForegroundStyle.getStyle())) {
                style.setForegroundStyleOrigin(intersectionMapping);
            } else {
                style.setForegroundStyleOrigin(columnMapping);
            }
            if (bestForegroundStyleDesc.getLabelFormat() != null && !Objects.equals(style.getLabelFormat(), bestForegroundStyleDesc.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyleDesc.getLabelFormat());
            }
            if (bestForegroundStyleDesc.getLabelSize() != -1 && style.getLabelSize() != bestForegroundStyleDesc.getLabelSize()) {
                style.setLabelSize(bestForegroundStyleDesc.getLabelSize());
            }
        } else {
            resetStyle(style, true);
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
     * <LI>the first conditional foreground style (with predicate expression that returns true), otherwise the default
     * foreground style</LI>
     * <LI>the first conditional background style (with predicate expression that returns true), otherwise the default
     * background style</LI>
     * <UL>
     * 
     * @param line
     *            The line to update
     * @param style
     *            the current style
     */
    private void doUpdateStyle(final DLine line, final DTableElementStyle style) {
        final TableStyleColorUpdater colorUpdater = new TableStyleColorUpdater();
        final LineMapping originMapping = line.getOriginMapping();
        final ColorDescription bestBackgroundColor = getBestBackgroundColor(line, originMapping);

        if (bestBackgroundColor != null) {
            colorUpdater.updateBackgroundColor(style, bestBackgroundColor, 
                    new StyleUpdaterQuery(originMapping).isDefaultBackgroundColor(bestBackgroundColor), 
                    line.getTarget());
        } else {
            resetStyle(style, false);
        }
        final ForegroundStyleDescription bestForegroundStyle = getBestForegroundStyle(line, originMapping);
        if (bestForegroundStyle != null) {
            boolean defaultStyleDescription = new StyleUpdaterQuery(originMapping).isDefaultForegroundColor(bestForegroundStyle.getForeGroundColor());
            colorUpdater.updateForegroundColor(style, bestForegroundStyle.getForeGroundColor(), defaultStyleDescription, line.getTarget());
            if (bestForegroundStyle.getLabelFormat() != null && !Objects.equals(style.getLabelFormat(), bestForegroundStyle.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyle.getLabelFormat());
            }
            if (bestForegroundStyle.getLabelSize() != -1 && style.getLabelSize() != bestForegroundStyle.getLabelSize()) {
                style.setLabelSize(bestForegroundStyle.getLabelSize());
            }
            style.setDefaultForegroundStyle(defaultStyleDescription);
        } else {
            resetStyle(style, true);
        }
    }

    private void resetStyle(final DTableElementStyle style, boolean foreground) {
        if (foreground) {
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_LabelSize());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat());
        } else {
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor());
            reset(style, TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle());
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
     * <LI>the default foreground style (and only if the color use for it is not with variable parts: ComputedColor or
     * InterpolatedColor)</LI>
     * <LI>the default background style (and only if the color use for it is not with variable parts: ComputedColor or
     * InterpolatedColor)</LI>
     * <UL>
     * 
     * @param column
     *            The column to update
     * @param style
     *            the current style
     */
    private void doUpdateStyle(final DColumn column, final DTableElementStyle style) {
        StyleUpdater styleUpdater = null;
        ColumnMapping originMapping = column.getOriginMapping();
        if (originMapping instanceof FeatureColumnMapping || originMapping instanceof ElementColumnMapping) {
            styleUpdater = (StyleUpdater) originMapping;
        }
        final TableStyleColorUpdater colorUpdater = new TableStyleColorUpdater();
        final ColorDescription bestBackgroundColor = getBestBackgroundColor(column, styleUpdater);
        if (bestBackgroundColor != null) {
            colorUpdater.updateBackgroundColor(style, bestBackgroundColor, new StyleUpdaterQuery(styleUpdater).isDefaultBackgroundColor(bestBackgroundColor), column.getTarget());
        } else {
            resetStyle(style, false);
        }
        final ForegroundStyleDescription bestForegroundStyle = getBestForegroundStyle(column, styleUpdater);
        if (bestForegroundStyle != null) {
            boolean defaultStyleDescription = new StyleUpdaterQuery(styleUpdater).isDefaultForegroundColor(bestForegroundStyle.getForeGroundColor());
            colorUpdater.updateForegroundColor(style, bestForegroundStyle.getForeGroundColor(), defaultStyleDescription, column.getTarget());
            if (bestForegroundStyle.getLabelFormat() != null && !Objects.equals(style.getLabelFormat(), bestForegroundStyle.getLabelFormat())) {
                FontFormatHelper.setFontFormat(style.getLabelFormat(), bestForegroundStyle.getLabelFormat());
            }
            if (bestForegroundStyle.getLabelSize() != -1 && style.getLabelSize() != bestForegroundStyle.getLabelSize()) {
                style.setLabelSize(bestForegroundStyle.getLabelSize());
            }
            style.setDefaultForegroundStyle(defaultStyleDescription);
        } else {
            resetStyle(style, true);
        }
    }

    /**
     * TODO : TBD <BR>
     * The first conditional background style of the cell (with predicate expression that returns true). In this case
     * the backgroundStyleOrigin references the intersection mapping and the defaultStyle is equal false. <BR>
     * Otherwise the first conditional background style of the column (with predicate expression that returns true). In
     * this case the backgroundStyleOrigin references the column mapping and the defaultStyle is equal false. <BR>
     * Otherwise, if it exists, the default background style of the cell. In this case the backgroundStyleOrigin
     * references the intersection mapping and the defaultStyle is equal true. <BR>
     * Otherwise, if the default background style of the column uses variable color, the default background style of the
     * column. In this case the backgroundStyleOrigin references the column mapping and the defaultStyle is equal true.
     * 
     * @param cell
     *            The current Cell
     * @param cellStyleUpdater
     *            The associate {@link StyleUpdater cell updater}
     * @param columnStyleUpdater
     *            The associate {@link StyleUpdater column updater}
     * @return The best colorMapping for this cell, or null otherwise
     */
    private StyleWithDefaultStatus getBestBackgroundColor(final DCell cell, final StyleUpdater cellStyleUpdater, final StyleUpdater columnStyleUpdater) {
        return getBestColorStyle(cell, cellStyleUpdater, columnStyleUpdater, 
                styleUpdater -> getApplicableBgConditionalStyle(cell, styleUpdater),
                styleUpdater -> getDefaulBgStyle(styleUpdater));
    }

    /**
     * TODO : TBD The priority of the StyleUpdater is (the highest priority to lowest priority).
     * <UL>
     * <LI>Intersection mapping (conditional style)</LI>
     * <LI>Line mapping (conditional style)</LI>
     * <LI>Column Mapping (conditional style)</LI>
     * <LI>Intersection mapping (default style)</LI>
     * <LI>Line mapping (default style)</LI>
     * <LI>Column Mapping (default style)</LI>
     * </UL>
     * 
     * @param <S> Style description
     * @param cell to get style for
     * @param cellStyleUpdater of the cell
     * @param columnStyleUpdater of the column of the cell
     * @param conditionalStyleGetter provider from StyleUpdater
     * @param defaultStyleGetter provider from StyleUpdater
     * @return Best style
     */
    private <S extends EObject> StyleWithDefaultStatus getBestColorStyle(final DCell cell, 
            final StyleUpdater cellStyleUpdater, 
            final StyleUpdater columnStyleUpdater,
            Function<StyleUpdater, S> conditionalStyleGetter, 
            Function<StyleUpdater, S> defaultStyleGetter) {

        // Preferred style
        S descr = conditionalStyleGetter.apply(cellStyleUpdater);

        if (descr == null) { // then conditional of column
            descr = conditionalStyleGetter.apply(columnStyleUpdater);            
        }
        boolean conditional = descr != null;

        // Use the default style
        if (descr == null) { // then default cell style
            descr = defaultStyleGetter.apply(cellStyleUpdater);
        }
        if (descr == null) { // the default column
            descr = defaultStyleGetter.apply(columnStyleUpdater);
            if (descr instanceof FixedColor) {
                descr = null;    
            }
        }
        
        if (descr != null) {
            return new StyleWithDefaultStatus(descr, !conditional);
        } else {
            return null;
        }
    }


    private BackgroundStyleDescription getDefaulBgStyle(final StyleUpdater styleUpdater) {
        if (styleUpdater != null 
                && styleUpdater.getDefaultBackground() != null 
                && styleUpdater.getDefaultBackground().getBackgroundColor() != null) {
            return styleUpdater.getDefaultBackground();
        }
        return null;
    }
    
    private BackgroundStyleDescription getApplicableBgConditionalStyle(final DSemanticDecorator element, final StyleUpdater styleUpdater) {
        return getApplicableConditionalStyle(element, styleUpdater, 
                StyleUpdater::getBackgroundConditionalStyle,
                BackgroundConditionalStyle::getStyle,
                DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle_PredicateExpression());
    }
    
    private ForegroundStyleDescription getDefaulFgStyle(final StyleUpdater styleUpdater) {
        if (styleUpdater != null 
                && styleUpdater.getDefaultForeground() != null 
                && styleUpdater.getDefaultForeground().getForeGroundColor() != null) {
            return styleUpdater.getDefaultForeground();
        }
        return null;
    }
    
    private ForegroundStyleDescription getApplicableFgConditionalStyle(final DTableElement element, final StyleUpdater styleUpdater) {
        return getApplicableConditionalStyle(element, styleUpdater, 
                StyleUpdater::getForegroundConditionalStyle,
                ForegroundConditionalStyle::getStyle,
                DescriptionPackage.eINSTANCE.getForegroundConditionalStyle_PredicateExpression());
    }
    
    private <S extends EObject, C extends EObject> S getApplicableConditionalStyle(
            final DSemanticDecorator element, final StyleUpdater styleUpdater, 
            Function<StyleUpdater, List<C>> conditionalsProvider, Function<C, S> styleProvider,
            EStructuralFeature feat) {
        EObject self = element.getTarget();
        if (self == null || styleUpdater == null) {
            return null;
        }
        
        S result = null;
        for (C condStyle : conditionalsProvider.apply(styleUpdater)) {
            if (styleProvider.apply(condStyle) != null // incomplete style are ignored
                    && interpreter.evaluateBoolean(self, condStyle, feat)) {
                result = styleProvider.apply(condStyle);
                break;
            }
        }
        return result;
    }


    /**
     * Return a value only if the current background color is the default background color or one of the conditional
     * background colors.
     * 
     * @param line
     *            The current DLine
     * @param styleUpdater
     *            style update of the line
     * @return The best background color for this line, or null otherwise
     */
    private ColorDescription getBestBackgroundColor(final DLine line, final StyleUpdater styleUpdater) {
        BackgroundStyleDescription descr = getApplicableBgConditionalStyle(line, styleUpdater);
        if (descr == null) { // then default cell style
            descr = getDefaulBgStyle(styleUpdater);
        }
        if (descr != null && descr.getBackgroundColor() != null) {
            return descr.getBackgroundColor();
        }
        return null;
    }

    /**
     * The best background color is the default background color if it uses a FixedColor.
     * 
     * @param column
     *            The current DLine
     * @param styleUpdater
     *            style update of the column
     * @return The default background color if it uses a FixedColor for this column, or null otherwise
     */
    private ColorDescription getBestBackgroundColor(final DColumn column, final StyleUpdater styleUpdater) {
        BackgroundStyleDescription descr = getDefaulBgStyle(styleUpdater);
        if (descr != null && styleUpdater.getDefaultBackground().getBackgroundColor() instanceof FixedColor) {
            return styleUpdater.getDefaultBackground().getBackgroundColor();
        }
        return null;
    }

    /**
     * TODO : TBD The priority of the StyleUpdater is (the highest priority to lowest priority).
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
     * @param cellStyleUpdater
     *            The associate {@link StyleUpdater cell updater}
     * @param columnStyleUpdater
     *            The associate {@link StyleUpdater column updater}
     * @return The best ForegroundStyleDescription for this cell, or null otherwise
     */
    private StyleWithDefaultStatus getBestForegroundStyle(final DCell cell, final StyleUpdater cellStyleUpdater, final StyleUpdater columnStyleUpdater) {
        return getBestColorStyle(cell, cellStyleUpdater, columnStyleUpdater, 
                styleUpdater -> getApplicableFgConditionalStyle(cell, styleUpdater),
                styleUpdater -> getDefaulFgStyle(styleUpdater));
    }

    /**
     * The priority of the StyleUpdater is (the highest priority to lowest priority).
     * TODO: Doc to review.
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
     * @param styleUpdater
     *            The associate {@link StyleUpdater updater}
     * @return The best ForegroundStyleDescription for this cell, or null otherwise
     */
    private ForegroundStyleDescription getBestForegroundStyle(final DLine line, final StyleUpdater styleUpdater) {
        ForegroundStyleDescription result = getApplicableFgConditionalStyle(line, styleUpdater);
        if (result == null) {
            result = styleUpdater.getDefaultForeground();
        }
        return result;
    }

    /**
     * The best foreground style is the default foreground style if it uses a FixedColor.
     * 
     * @param column
     *            The current DLine
     * @param styleUpdater
     *            The associate {@link StyleUpdater updater}
     * @return The default foreground style if it uses a FixedColor for this column, or null otherwise
     */
    private ForegroundStyleDescription getBestForegroundStyle(final DColumn column, final StyleUpdater styleUpdater) {
        ForegroundStyleDescription bestForegroundStyleDescription = null;
        if (styleUpdater.getDefaultForeground() != null && styleUpdater.getDefaultForeground().getForeGroundColor() instanceof FixedColor) {
            bestForegroundStyleDescription = styleUpdater.getDefaultForeground();
        }
        return bestForegroundStyleDescription;
    }

    /**
     * Affect semantic elements to a cell.
     * 
     * @param newCell
     *            cell to update.
     */
    public void refreshSemanticElements(final DCell newCell) {
        IntersectionMapping intersectionMapping = newCell.getIntersectionMapping();
        if (intersectionMapping != null) {
            refreshSemanticElements(newCell, intersectionMapping);
        } else {
            final Collection<EObject> newElements = new ArrayList<>();
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
     * Evaluate the semantic elements feature of the mapping and affect them to the given table element.
     * 
     * @param tableElement
     *            table element to affect.
     * @param mapping
     *            mapping used to retrieve the semantic elements.
     */
    public void refreshSemanticElements(final DTableElement tableElement, final TableMapping mapping) {
        Collection<EObject> elements;
        
        if (mapping.getSemanticElements() != null && !StringUtil.isEmpty(mapping.getSemanticElements())) {
            elements = InterpretationContext.with(interpreter, it -> {
                EObject container = tableElement.eContainer();
                if (container != null) {
                    it.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, container);
                    if (container instanceof DSemanticDecorator) {
                        it.setVariable(IInterpreterSiriusVariables.CONTAINER, ((DSemanticDecorator) container).getTarget());
                    }
                }
                it.setVariable(IInterpreterSiriusVariables.VIEW, tableElement);
                it.setLogError(mapping instanceof LineMapping 
                        || TableHelper.hasTableDescriptionOnlyOneLineMapping(mapping));
                
                EAttribute semElemFeature = DescriptionPackage.eINSTANCE.getTableMapping_SemanticElements();
                return interpreter.evaluateCollection(tableElement.getTarget(), mapping, semElemFeature);
            });

        } else if (tableElement.getTarget() != null) {
            elements = Collections.singletonList(tableElement.getTarget());
        } else {
            elements = Collections.emptyList();
        }
        synchronizeLists(tableElement.getSemanticElements(), elements);
    }

    private String getValueText(Object element) {
        if (element instanceof EObject) {
            return getText((EObject) element);
        }
        return element.toString();
    }
    
    private String getText(final EObject element) {
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
        if (target.eIsSet(feature)) { // applicable for simple or multi-value
            target.eUnset(feature);
        }
    }

    /**
     * Provides the associated accessor.
     * <p>
     * Restricted to table synchronizer.
     * </p>
     * 
     * @return accessor
     */
    ModelAccessor getAccessor() {
        return accessor;
    }

    /**
     * Provides the associated interpretor.
     * <p>
     * Restricted to table synchronizer.
     * </p>
     * 
     * @return interpretor
     */
    RuntimeLoggerInterpreter getInterpreter() {
        return interpreter;
    }
    
    private static void setLabelOnUpdate(DLine line, String value) {
        // We change the value only if it's different 
        // to prevent unnecessary dirty state.
        if (!StringUtil.equals(line.getLabel(), value)) {
            line.setLabel(value);
        }
    }
    
    private static void setLabelOnUpdate(DColumn column, String value) {
        // We change the value only if it's different 
        // to prevent unnecessary dirty state.
        if (!StringUtil.equals(column.getLabel(), value)) {
            column.setLabel(value);
        }
    }
    
    private static void setLabelOnUpdate(DCell cell, String value) {
        // We change the value only if it's different 
        // to prevent unnecessary dirty state.
        if (!StringUtil.equals(cell.getLabel(), value)) {
            cell.setLabel(value);
        }
    }
    

}
