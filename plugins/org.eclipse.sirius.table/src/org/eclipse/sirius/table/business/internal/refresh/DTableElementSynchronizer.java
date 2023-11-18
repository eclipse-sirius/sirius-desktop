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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.logger.InterpretationContext;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

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
            return LabelUtils.getValueText(header.getTarget());
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
            new DTableStyleHelper(interpreter).refresh(line);
        }
    }

    /**
     * Refresh the given cell.
     * 
     * @param cell
     *            cell to refresh. (may be null)
     */
    public void refresh(final DCell cell) {
        if (cell != null // May happen in failed creation
                && accessor.getPermissionAuthority().canEditInstance(cell) // order matters
                && refreshTarget(cell) // modifiable -> target -> label
                && refreshLabel(cell)) {
            new DTableStyleHelper(interpreter).refresh(cell);
            refreshSemanticElements(cell);            
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
        if (accessor.getPermissionAuthority().canEditInstance(column)) {
            final String label = getHeaderLabel(column, column.getOriginMapping(), 
                    DescriptionPackage.eINSTANCE.getColumnMapping_HeaderLabelExpression());
            setLabelOnUpdate(column, label);
            new DTableStyleHelper(interpreter).refresh(column);
        }
    }

    /**
     * Refresh a column label.
     * 
     * @param column
     *            column to refresh.
     */
    public void refresh(final DFeatureColumn column) {
        if (accessor.getPermissionAuthority().canEditInstance(column)) {
            setLabelOnUpdate(column, column.getOriginMapping().getHeaderLabelExpression());
            new DTableStyleHelper(interpreter).refresh(column);
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
        if (line != null && line.getTarget() != null 
    		    && column != null && column.getTarget() != null) {
            Collection<EObject> foundColumnTargets = evaluateColumnFinderExpression(cell, intersectionMapping);
            if (foundColumnTargets.contains(column.getTarget()) // Ensure the column is consistent
                    && evaluateIntersectionPrecondition(column.getTarget(), line, column, intersectionMapping)) {
                cellNeeded = updateCellLabel(cell);
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
        LineContainer container;
        if (iMapping.isUseDomainClass()) {
            source = cell;
            container = TableHelper.getTable(cell.getLine());
        } else {
            container = cell.getLine();
            source = container;
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
    Collection<EObject> evaluateColumnFinderExpression(EObject candidate, LineContainer container, IntersectionMapping iMapping, boolean logError) {
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
                label = LabelUtils.getValueText(featureObject);
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
        
        if (!StringUtil.isEmpty(mapping.getSemanticElements())) {
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
