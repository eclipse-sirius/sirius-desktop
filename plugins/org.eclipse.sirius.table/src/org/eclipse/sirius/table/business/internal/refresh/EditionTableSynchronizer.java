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
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.InterpretationContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.collect.GSetIntersection;
import org.eclipse.sirius.ext.base.collect.SetIntersection;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.table.tools.internal.Messages;

/**
 * Synchronizer for Edition tables.
 *
 * @author cbrun
 */
public class EditionTableSynchronizer extends AbstractTableSynchronizer<EditionTableDescription, FeatureColumnMapping> {

    /**
     * Create a new {@link DTableSynchronizer} for an EditionTable.
     *
     * @param description
     *            EditionTable description.
     * @param sync
     *            element synchronizer.
     */
    public EditionTableSynchronizer(final EditionTableDescription description, final DTableElementSynchronizer sync) {
        super(description, sync);
    }
    
    @Override
    protected void initRefreshMonitor(final IProgressMonitor monitor) {
        monitor.beginTask(Messages.DTableSynchronizerImpl_refreshEditionTabel, 2);
    }

    @Override
    protected List<FeatureColumnMapping> getColumnMappings() {
        return description.getOwnedColumnMappings();
    }

    
    @Override
    protected void refreshCells(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        // Clean orphan cells
        for (final DColumn column : table.getColumns()) {
            for (DCell cell : new ArrayList<DCell>(column.getCells())) {
                if (cell.getLine() == null) {
                    this.sync.removeUneededCell(cell);
                }
            }
        }

        fillTableDCells(table);
    }


    /**
     * Fills all cells of table for this container and sub-lines.
     * 
     * @param lContainer to fill
     */
    private void fillTableDCells(final LineContainer lContainer) {
        for (final DLine line : lContainer.getLines()) {
            fillTableDCell(line);
            /* recursive call for children */
            fillTableDCells(line);
        }
    }

    /**
     * Fills cells of table for this line.
     * 
     * @param line to fill
     */
    private void fillTableDCell(final DLine line) {
        final SetIntersection<DCellCandidate> status = createCellsStatus(line);

        createNewCellsInTable(status);
        updateExistingCellsInTable(status);
        removeOldCellsInTable(status);
    }

    private SetIntersection<DCellCandidate> createCellsStatus(final DLine line) {
        final SetIntersection<DCellCandidate> status = new GSetIntersection<DCellCandidate>();
        /*
         * let's keep the "now" status.
         */
        for (final DCell cell : line.getCells()) {
            status.addInOld(new DCellCandidate(cell, this.ids));
        }

        for (final DColumn column : table.getColumns()) {
            final ColumnMapping mapping = column.getOriginMapping();
            Option<DCell> optionalCell = TableHelper.getCell(line, column);
            EObject target;
            if (optionalCell.some()) {
                target = optionalCell.get().getTarget();
            } else {
                target = line.getTarget();
            }
            status.addInNew(new DCellCandidate(mapping, target, line, column, this.ids));
        }
        return status;
    }
    
    /**
     * Creates new cells in the table.
     * 
     * @param status cells candidates
     */
    private void createNewCellsInTable(final SetIntersection<DCellCandidate> status) {
        for (final DCellCandidate toCreate : status.getNewElements()) {
            final DCell newCell = createCell(toCreate.getLine(), toCreate.getColumn(), toCreate.getSemantic(), (FeatureColumnMapping) toCreate.getMapping());
            
            sync.refresh(newCell);
        }
    }

    /**
     * Creates a cell.
     * 
     * @param line of the new cell
     * @param column of the new cell
     * @param semantic of the cell
     * @param mapping of the column
     * @return cell if valid
     */
    @Override
    protected DCell createCell(final DLine line, final DColumn column, final EObject semantic, final FeatureColumnMapping mapping) {
        EObject target = line.getTarget();
        final String featureParentExpression = mapping.getFeatureParentExpression();
        if (featureParentExpression != null && featureParentExpression.length() > 0) {
            target = InterpretationContext.with(interpreter, ctx -> {
                if (line != null) {
                    ctx.setVariable(IInterpreterSiriusVariables.CONTAINER, line.getTarget());
                    ctx.setVariable(IInterpreterSiriusTableVariables.LINE, line);
                }
                if (table != null) {
                    ctx.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
                    ctx.setVariable(IInterpreterSiriusTableVariables.TABLE, table);
                }

                return interpreter.evaluateEObject(line.getTarget(), mapping,
                        DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureParentExpression());
            });
        }
        
        if (target == null || !isValidColumn(target, mapping)) {
            // We don't create a cell in this case.
            return null;
        }

        return super.createCell(line, column, target, mapping);
    }
    
    private boolean isValidColumn(final EObject target, final FeatureColumnMapping mapping) {
        String featureName = mapping.getFeatureName();
        return DTableElementSynchronizer.SKIP_FEATURENAME_VALIDATION.equals(featureName)
                || accessor.eValid(target, featureName);
    }
    
    /**
     * Updates existing cells in the table.
     * 
     * @param status cells candidates
     */
    private void updateExistingCellsInTable(final SetIntersection<DCellCandidate> status) {
        for (final DCellCandidate toUpdate : status.getKeptElements()) {
            final DCell cell = toUpdate.getOriginalElement();
            sync.refresh(cell);
        }
    }

    /**
     * Remove old cells in the table.
     * 
     * @param status cells candidates
     */
    private void removeOldCellsInTable(final SetIntersection<DCellCandidate> status) {
        for (final DCellCandidate toRemove : status.getRemovedElements()) {
            final DCell cell = toRemove.getOriginalElement();
            if (cell != null) {
                /*
                 * we should never reach a case were cells are deleted without having been deleted by their line or
                 * column. !
                 */
                final DLine parentLine = cell.getLine();
                final DColumn parentColumn = cell.getColumn();
                if (accessor.getPermissionAuthority().canEditInstance(parentColumn) && accessor.getPermissionAuthority().canEditInstance(parentLine)) {
                    parentLine.getCells().remove(cell);
                    if (parentColumn != null) {
                        parentColumn.getCells().remove(cell);
                    }
                }
            }
        }
    }


    @Override
    protected int refreshColumnMapping(final FeatureColumnMapping mapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final int previousCurrentIndex) {
        int currentIndex = previousCurrentIndex;
        if (accessor.getPermissionAuthority().canCreateIn(table)) {
            final SetIntersection<DFeatureColumnCandidate> status = computeCurrentStatus(mapping);
            // Remove old elements
            for (final DFeatureColumnCandidate toDelete : status.getRemovedElements()) {
                if (toDelete.getOriginalElement() != null) {
                    doDeleteColumn(toDelete.getOriginalElement());
                }
            }
            // Treat existing elements and new elements in the order of the
            // result of the expression
            for (final DFeatureColumnCandidate featureColumnCandidate : status.getAllElements()) {
                if (featureColumnCandidate.getOriginalElement() == null) {
                    final DFeatureColumn newC = createNewColumn(featureColumnCandidate.getMapping(), featureColumnCandidate.getFeatureName());
                    this.sync.refresh(newC);
                    table.getColumns().add(currentIndex, newC);
                } else {
                    this.sync.refresh(featureColumnCandidate.getOriginalElement());
                    final DTable parentTable = featureColumnCandidate.getOriginalElement().getTable();
                    
                    int newPosition = -1;  // No new position
                    if (parentTable.getColumns().size() < currentIndex) {
                        newPosition = parentTable.getColumns().size() - 1;
                    } else if (!featureColumnCandidate.getOriginalElement().equals(parentTable.getColumns().get(currentIndex))) {
                        newPosition = currentIndex;
                    }
                    
                    if (newPosition != -1 && accessor.getPermissionAuthority().canEditInstance(parentTable)) {
                        parentTable.getColumns().move(newPosition, featureColumnCandidate.getOriginalElement());
                    }
                }
                currentIndex++;
            }
        }
        return currentIndex;
    }

    private SetIntersection<DFeatureColumnCandidate> computeCurrentStatus(final FeatureColumnMapping mapping) {
        final SetIntersection<DFeatureColumnCandidate> status = new GSetIntersection<DFeatureColumnCandidate>();
        /*
         * let's analyse the existing columns.
         */
        for (final DColumn column : table.getColumns()) {
            boolean mappingIsObsolete = column.getOriginMapping().eResource() == null || column.getOriginMapping().eIsProxy();
            if (mappingIsObsolete || (column.getOriginMapping() == mapping && column instanceof DFeatureColumn)) {
                status.addInOld(new DFeatureColumnCandidate((DFeatureColumn) column, this.ids));
            }
        }
        status.addInNew(new DFeatureColumnCandidate(mapping, mapping.getFeatureName(), this.ids));
        return status;
    }


    /**
     * Create a new column corresponding to the mapping.
     *
     * @param mapping
     *            the column mapping.
     * @param featureName
     *            the target feature name
     * @return the newly created column.
     */
    protected DFeatureColumn createNewColumn(final ColumnMapping mapping, final String featureName) {
        final DFeatureColumn column = TableFactory.eINSTANCE.createDFeatureColumn();
        column.setOriginMapping(mapping);
        column.setFeatureName(featureName);
        return column;
    }

}
