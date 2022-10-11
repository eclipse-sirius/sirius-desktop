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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.logger.InterpretationContext;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.CartesianProduct;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.collect.GSetIntersection;
import org.eclipse.sirius.ext.base.collect.MultipleCollection;
import org.eclipse.sirius.ext.base.collect.SetIntersection;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.query.DTableQuery;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.collect.Sets;

/**
 * The synchronizer for tables.
 *
 * @author cbrun
 */
public class DTableSynchronizerImpl implements DTableSynchronizer {

    private final TableDescription description;

    private final RuntimeLoggerInterpreter interpreter;

    private final ModelAccessor accessor;

    private DTable table;

    private final DTableElementSynchronizer sync;

    private RefreshIdsHolder ids;

    /**
     * Create a new {@link DTableSynchronizer} for an EditionTable.
     *
     * @param description
     *            EditionTable description.
     * @param sync
     *            element synchronizer.
     */
    public DTableSynchronizerImpl(final TableDescription description, final DTableElementSynchronizer sync) {
        this.sync = sync;
        this.accessor = sync.getAccessor();
        this.description = description;
        this.interpreter = sync.getInterpreter();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void refresh(final IProgressMonitor monitor) {
        try {
            if (description instanceof CrossTableDescription) {
                monitor.beginTask(Messages.DTableSynchronizerImpl_refreshCrossTabel, 3);
            } else {
                monitor.beginTask(Messages.DTableSynchronizerImpl_refreshEditionTabel, 2);
            }
            KeyCache.DEFAULT.clear();
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_TABLE_KEY);

            final Map<TableMapping, Collection<DTableElement>> mappingToElements = new HashMap<TableMapping, Collection<DTableElement>>();
            ECrossReferenceAdapter xref = ECrossReferenceAdapter.getCrossReferenceAdapter(table.getTarget());

            if (!table.eIsSet(TablePackage.Literals.DTABLE__HEADER_COLUMN_WIDTH)) {
                table.setHeaderColumnWidth(description.getInitialHeaderColumnWidth());
            }

            refreshLines(new SubProgressMonitor(monitor, 1), mappingToElements, xref);

            refreshColumns(new SubProgressMonitor(monitor, 1), mappingToElements, xref);

            refreshCells(new SubProgressMonitor(monitor, 1), mappingToElements, xref);

            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_TABLE_KEY);
        } finally {
            monitor.done();
        }
    }

    private void refreshLines(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final ECrossReferenceAdapter xref) {
        try {

            List<LineMapping> lMappings = new ArrayList<LineMapping>();
            if (description != null) {
                lMappings = description.getAllLineMappings();
            }
            monitor.beginTask(Messages.DTableSynchronizerImpl_refreshLineMapping, lMappings.size());

            int currentLineIndex = 0;
            for (final LineMapping lMapping : lMappings) {
                currentLineIndex = refreshLineMapping(table, lMapping, mappingToElements, currentLineIndex, xref);
                monitor.worked(1);
            }
            // if there is no more line mapping in vsm delete table rows
            // corresponding
            if (lMappings.isEmpty()) {
                for (final DLine lineToDelete : new ArrayList<DLine>(table.getLines())) {
                    doDeleteLine(lineToDelete, xref);
                }
            }
            KeyCache.DEFAULT.clear();
        } finally {
            monitor.done();
        }
    }

    private void refreshColumns(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final ECrossReferenceAdapter xref) {
        try {
            List<? extends ColumnMapping> cMappings = new ArrayList<ColumnMapping>();
            if (description instanceof EditionTableDescription) {
                cMappings = ((EditionTableDescription) description).getAllColumnMappings();
            } else if (description instanceof CrossTableDescription) {
                cMappings = ((CrossTableDescription) description).getOwnedColumnMappings();
            }
            monitor.beginTask(Messages.DTableSynchronizerImpl_refreshColumnMapping, cMappings.size());

            int currentColumnIndex = 0;
            for (final ColumnMapping cMapping : cMappings) {
                currentColumnIndex = refreshColumnMapping(cMapping, mappingToElements, currentColumnIndex, xref);
                monitor.worked(1);
            }
            // If there is no more column mapping in VSM, delete column in table
            if (cMappings.isEmpty()) {
                for (final DColumn columnToDelete : new ArrayList<DColumn>(table.getColumns())) {
                    doDeleteColumn(columnToDelete, xref);
                }
            }

            KeyCache.DEFAULT.clear();
        } finally {
            monitor.done();
        }
    }

    /**
     * @param monitor
     *            The progress monitor
     * @param mappingToElements
     *            A map that list the DTableElement (line or column) for each mapping
     * @param xref
     *            the cross referencer to use
     */
    private void refreshCells(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements, ECrossReferenceAdapter xref) {
        if (description instanceof EditionTableDescription) {
            // Clean orphan cells
            for (final DColumn column : table.getColumns()) {
                for (DCell cell : new ArrayList<DCell>(column.getCells())) {
                    if (cell.getLine() == null) {
                        doDeleteCell(cell, xref);
                    }
                }
            }

            fillTableDCells(table);
        } else if (description instanceof CrossTableDescription) {
            refreshCellsOfCrossTable(monitor, mappingToElements, xref);
        }
        KeyCache.DEFAULT.clear();
    }

    /**
     * Refresh all the cells of a crossTable :
     * <UL>
     * <LI>Remove the cells that have no corresponding intersection mapping,</LI>
     * <LI>Refresh the existing one</LI>
     * <LI>Create the new one</LI>
     * <UL>
     *
     * @param monitor
     *            The progress monitor
     * @param mappingToElements
     *            A map that list the DTableElement (line or column) for each mapping
     * @param xref
     *            the cross reference to use
     */
    private void refreshCellsOfCrossTable(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements, ECrossReferenceAdapter xref) {
        try {
            monitor.beginTask(Messages.DTableSynchronizerImpl_refreshIntersectionMapping, ((CrossTableDescription) description).getIntersection().size());
            if (((CrossTableDescription) description).getIntersection().isEmpty()) {
                // If there is no intersection mapping we must clean all the
                // invalid
                // cells of the DTable (this must normally be done in the first
                // intersectionMapping)
                for (final DCell cell : new DTableQuery(table).getCells()) {
                    doDeleteCell(cell, xref);
                }
            } else {
                for (final IntersectionMapping iMapping : ((CrossTableDescription) description).getIntersection()) {
                    refreshIntersectionMapping(iMapping, mappingToElements, xref);
                    monitor.worked(1);
                }
            }
        } finally {
            monitor.done();
        }
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

    /**
     * Creates new cells in the table.
     * 
     * @param status cells candidates
     */
    private void createNewCellsInTable(final SetIntersection<DCellCandidate> status) {
        for (final DCellCandidate toCreate : status.getNewElements()) {
            final Optional<DCell> optionalCell = createCell(toCreate.getLine(), toCreate.getColumn(), toCreate.getSemantic(), toCreate.getMapping());
            if (optionalCell.isPresent()) {
                if (refresh(optionalCell.get())) {
                    this.sync.refreshSemanticElements(optionalCell.get());
                }
            }
        }
    }

    /**
     * Updates existing cells in the table.
     * 
     * @param status cells candidates
     */
    private void updateExistingCellsInTable(final SetIntersection<DCellCandidate> status) {
        for (final DCellCandidate toUpdate : status.getKeptElements()) {
            final DCell cell = toUpdate.getOriginalElement();
            if (cell != null) {
                if (refresh(cell)) {
                    this.sync.refreshSemanticElements(cell);
                }
            }
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

    private boolean refresh(final DCell cell) {
        boolean cellStillExists = true;
        if (accessor.getPermissionAuthority().canEditInstance(cell)) {
            if (this.sync.refreshTarget(cell)) {
                if (this.sync.refreshLabel(cell)) {
                    this.sync.refreshStyle(cell);
                } else {
                    cellStillExists = false;
                }
            } else {
                cellStillExists = false;
            }
        }
        return cellStillExists;
    }

    private Optional<DCell> createCell(final DLine line, final DColumn column, final EObject semantic, final ColumnMapping mapping) {
        DCell newCell = TableFactory.eINSTANCE.createDCell();
        newCell.setTarget(semantic);
        if (mapping instanceof FeatureColumnMapping) {
            final FeatureColumnMapping cMapping = (FeatureColumnMapping) mapping;
            EObject featureParent = line.getTarget();
            final String featureParentExpression = cMapping.getFeatureParentExpression();
            if (featureParentExpression != null && featureParentExpression.length() > 0) {
                featureParent = InterpretationContext.with(interpreter, ctx -> {
                    if (line != null) {
                        ctx.setVariable(IInterpreterSiriusVariables.CONTAINER, line.getTarget());
                        ctx.setVariable(IInterpreterSiriusTableVariables.LINE, line);
                    }
                    if (table != null) {
                        ctx.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
                        ctx.setVariable(IInterpreterSiriusTableVariables.TABLE, table);
                    }

                    return interpreter.evaluateEObject(line.getTarget(), cMapping,
                            DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureParentExpression());
                });
            }
            newCell.setTarget(featureParent);
            String featureName = cMapping.getFeatureName();
            if (newCell.getTarget() == null || !(DTableElementSynchronizer.SKIP_FEATURENAME_VALIDATION.equals(featureName) || accessor.eValid(newCell.getTarget(), featureName))) {
                // We don't create a cell in this case.
                return Optional.empty();
            }
        }

        // Assign this cell to its line and column.
        newCell.setLine(line);
        newCell.setColumn(column);

        return Optional.of(newCell);
    }

    /**
     * Refresh a column mapping adding the columns.
     *
     * @param mapping
     *            mapping to refresh.
     * @param mappingToElements
     *            an holder for the mapping to element mapping.
     * @param previousCurrentIndex of column
     * @param xref
     *            the cross referencer to use
     * @return new column index 
     */
    private int refreshColumnMapping(final ColumnMapping mapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final int previousCurrentIndex, ECrossReferenceAdapter xref) {
        int result = 0;
        if (mapping instanceof FeatureColumnMapping) {
            result = refreshFeatureColumnMapping((FeatureColumnMapping) mapping, mappingToElements, previousCurrentIndex, xref);
        } else if (mapping instanceof ElementColumnMapping) {
            result = refreshElementColumnMapping((ElementColumnMapping) mapping, mappingToElements, previousCurrentIndex, xref);
        }
        return result;
    }

    private int refreshElementColumnMapping(final ElementColumnMapping mapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final int previousCurrentIndex,
            ECrossReferenceAdapter xref) {
        int currentIndex = previousCurrentIndex;
        if (accessor.getPermissionAuthority().canEditInstance(table)) {
            final SetIntersection<DTargetColumnCandidate> status = computeCurrentStatus(mapping, xref);
            final Collection<DTableElement> elementsToKeep = new ArrayList<DTableElement>();
            // Remove old elements
            for (final DTargetColumnCandidate toDelete : status.getRemovedElements()) {
                if (toDelete.getOriginalElement() != null) {
                    doDeleteColumn(toDelete.getOriginalElement(), xref);
                }
            }
            
            // Treat existing elements and new elements in the order of the
            // result of the expression
            for (final DTargetColumnCandidate targetColumnCandidate : status.getAllElements()) {
                if (targetColumnCandidate.getOriginalElement() == null) {
                    final DTargetColumn newC = createNewColumn(targetColumnCandidate.getMapping(), targetColumnCandidate.getSemantic());
                    this.sync.refresh(newC);
                    sync.refreshSemanticElements(newC, targetColumnCandidate.getMapping());
                    if (accessor.getPermissionAuthority().canCreateIn(table)) {
                        table.getColumns().add(currentIndex, newC);
                    }
                    elementsToKeep.add(newC);
                } else {
                    this.sync.refresh(targetColumnCandidate.getOriginalElement());
                    sync.refreshSemanticElements(targetColumnCandidate.getOriginalElement(), targetColumnCandidate.getMapping());
                    final DTable parentTable = targetColumnCandidate.getOriginalElement().getTable();
                    if (parentTable.getColumns().size() >= currentIndex) {
                        if (accessor.getPermissionAuthority().canEditInstance(parentTable) && !targetColumnCandidate.getOriginalElement().equals(parentTable.getColumns().get(currentIndex))) {
                            parentTable.getColumns().move(currentIndex, targetColumnCandidate.getOriginalElement());
                        }
                    } else {
                        if (accessor.getPermissionAuthority().canEditInstance(parentTable)) {
                            parentTable.getColumns().move(parentTable.getColumns().size() - 1, targetColumnCandidate.getOriginalElement());
                        }
                    }
                    elementsToKeep.add(targetColumnCandidate.getOriginalElement());
                }
                currentIndex++;
            }
            putOrAdd(mappingToElements, mapping, elementsToKeep);
        }
        return currentIndex;
    }

    private SetIntersection<DTargetColumnCandidate> computeCurrentStatus(final ElementColumnMapping mapping, ECrossReferenceAdapter xref) {
        
        removeTargetLessColumns(mapping, xref);

        final SetIntersection<DTargetColumnCandidate> status = new GSetIntersection<DTargetColumnCandidate>();
        for (final DColumn column : table.getColumns()) {
            if (column.getOriginMapping() == mapping) {
                if (column instanceof DTargetColumn) {
                    status.addInOld(new DTargetColumnCandidate((DTargetColumn) column, this.ids));
                }
            }
        }
        
        final MultipleCollection<EObject> semantics = new MultipleCollection<EObject>();
        if (TableHelper.hasSemanticCandidatesExpression(mapping)) {
            final Collection<EObject> candidates = InterpretationContext.with(interpreter, ctx -> {
                ctx.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, table);
                ctx.setVariable(IInterpreterSiriusVariables.CONTAINER, table.getTarget());
                ctx.setVariable(IInterpreterSiriusVariables.VIEWPOINT, this.table);
                ctx.setVariable(IInterpreterSiriusVariables.TABLE, this.table);

                return interpreter.evaluateCollection(table.getTarget(), mapping, 
                        DescriptionPackage.eINSTANCE.getElementColumnMapping_SemanticCandidatesExpression());

            });
            if (!candidates.isEmpty()) {
                semantics.addAll(candidates);
            }

        } else {
            semantics.addAll(this.accessor.eAllContents(table.getTarget(), mapping.getDomainClass()));
        }

        /*
         * produce the candidates states if the precondition is valid.
         */
        for (final EObject semantic : semantics) {
            if (this.accessor.eInstanceOf(semantic, mapping.getDomainClass())) {
                status.addInNew(new DTargetColumnCandidate(mapping, semantic, this.ids));
            }
        }
        return status;

    }

    /**
     * @param mapping
     * @param xref
     */
    private void removeTargetLessColumns(final ElementColumnMapping mapping, ECrossReferenceAdapter xref) {
        // Remove column with no target (target == null). This case can happen
        // when the dangling references are removed during the delete of the
        // semantic element.
        if (this.accessor.getPermissionAuthority().canEditInstance(table)) {
            List<DColumn> columnsWithoutTarget = new ArrayList<DColumn>();
            /*
             * let's analyze the existing columns.
             */
            for (final DColumn column : table.getColumns()) {
                boolean mappingIsObsolete = column.getOriginMapping().eResource() == null || column.getOriginMapping().eIsProxy();
                if (mappingIsObsolete || (column.getOriginMapping() == mapping && column instanceof DFeatureColumn)) {
                    columnsWithoutTarget.add(column);
                }
            }

            for (DColumn column : table.getColumns()) {
                if (column.getTarget() == null) {
                    columnsWithoutTarget.add(column);

                }
            }
            for (DColumn columnWithoutTarget : columnsWithoutTarget) {
                doDeleteColumn(columnWithoutTarget, xref);
            }
        }
    }

    private int refreshFeatureColumnMapping(final FeatureColumnMapping mapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final int previousCurrentIndex,
            ECrossReferenceAdapter xref) {
        int currentIndex = previousCurrentIndex;
        if (accessor.getPermissionAuthority().canCreateIn(table)) {
            final SetIntersection<DFeatureColumnCandidate> status = computeCurrentStatus(mapping);
            // Remove old elements
            for (final DFeatureColumnCandidate toDelete : status.getRemovedElements()) {
                if (toDelete.getOriginalElement() != null) {
                    doDeleteColumn(toDelete.getOriginalElement(), xref);
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
                    if (parentTable.getColumns().size() >= currentIndex) {
                        if (accessor.getPermissionAuthority().canEditInstance(parentTable) && !featureColumnCandidate.getOriginalElement().equals(parentTable.getColumns().get(currentIndex))) {
                            parentTable.getColumns().move(currentIndex, featureColumnCandidate.getOriginalElement());
                        }
                    } else {
                        if (accessor.getPermissionAuthority().canEditInstance(parentTable)) {
                            parentTable.getColumns().move(parentTable.getColumns().size() - 1, featureColumnCandidate.getOriginalElement());
                        }
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
     * Refresh a line mapping for a given container.
     *
     * @param container
     *            the line container.
     * @param mapping
     *            the mapping to refresh.
     * @param mappingToElements
     *            an holder for the mapping to element mapping.
     * @param xref
     *            the session cross referencer
     * @param previousCurrentIndex
     *            current line index
     * @return new index to refresh
     */
    private int refreshLineMapping(final LineContainer container, final LineMapping mapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final int previousCurrentIndex,
            ECrossReferenceAdapter xref) {
        int currentIndex = previousCurrentIndex;
        final SetIntersection<DLineCandidate> status = computeCurrentStatus(container, mapping, xref);
        final Collection<DLine> possibleContainers = new ArrayList<DLine>();
        final Collection<DTableElement> elementsToKeep = new ArrayList<DTableElement>();
        // Remove old elements
        if (this.accessor.getPermissionAuthority().canEditInstance(container)) {
            for (final DLineCandidate toDelete : status.getRemovedElements()) {
                if (toDelete.getOriginalElement() != null) {
                    doDeleteLine(toDelete.getOriginalElement(), xref);
                }
            }
        }
        // Treat existing elements and new elements in the order of the result
        // of the expression
        for (final DLineCandidate lineCandidate : status.getAllElements()) {
            DLine toRefresh = detectLineToRefresh(lineCandidate, container, currentIndex, elementsToKeep);
            if (toRefresh != null) {
                possibleContainers.add(toRefresh);
            }
            currentIndex++;
        }
        putOrAdd(mappingToElements, mapping, elementsToKeep);
        refreshLineCandidates(possibleContainers, mappingToElements, xref);
        return currentIndex;
    }

    /**
     * Return the line to refresh if applicable.
     * 
     * @param container of line
     * @param currentIndex to put line in
     * @param elementsToKeep 
     * @param lineCandidate candidate
     * @return line to refresh
     */
    private DLine detectLineToRefresh(final DLineCandidate lineCandidate, final LineContainer container, int currentIndex, final Collection<DTableElement> elementsToKeep) {
        DLine result = null;
        if (lineCandidate.getOriginalElement() == null) {
            if (this.accessor.getPermissionAuthority().canEditInstance(container)) {
                final DLine newL = createNewLine(lineCandidate.getMapping(), lineCandidate.getSemantic());
                container.getLines().add(currentIndex, newL);
                sync.refresh(newL);
                sync.refreshSemanticElements(newL, lineCandidate.getMapping());
                result = newL;
                elementsToKeep.add(newL);
            }
        } else {
            if (accessor.getPermissionAuthority().canEditInstance(lineCandidate.getOriginalElement())) {
                sync.refresh(lineCandidate.getOriginalElement());
                sync.refreshSemanticElements(lineCandidate.getOriginalElement(), lineCandidate.getMapping());
                // Move the line to the end to keep order
                final LineContainer lineContainer = (LineContainer) lineCandidate.getOriginalElement().eContainer();
                if (lineContainer.getLines().size() >= currentIndex) {
                    if (accessor.getPermissionAuthority().canEditInstance(lineContainer) 
                            && !lineCandidate.getOriginalElement().equals(lineContainer.getLines().get(currentIndex))) {
                        lineContainer.getLines().move(currentIndex, lineCandidate.getOriginalElement());
                    }
                } else {
                    if (accessor.getPermissionAuthority().canEditInstance(lineContainer)) {
                        lineContainer.getLines().move(lineContainer.getLines().size() - 1, lineCandidate.getOriginalElement());
                    }
                }
                // Add this line to the possible containers list
                result = lineCandidate.getOriginalElement();
            }
            elementsToKeep.add(lineCandidate.getOriginalElement());
        }
        return result;
    }


    private void refreshLineCandidates(final Collection<DLine> possibleContainers, final Map<TableMapping, Collection<DTableElement>> mappingToElements, ECrossReferenceAdapter xref) {
        for (final DLine newContainer : possibleContainers) {
            int currentSubIndex = 0;
            EList<LineMapping> allSubLines = newContainer.getOriginMapping().getAllSubLines();

            for (final LineMapping subMapping : allSubLines) {
                currentSubIndex = refreshLineMapping(newContainer, subMapping, mappingToElements, currentSubIndex, xref);
            }

            if (allSubLines.isEmpty() && !newContainer.getLines().isEmpty()) {
                // If there is no more sub lines mapping in VSM, delete table
                // corresponding sub rows, otherwise compute current status will
                // delete lines without mapping
                if (this.accessor.getPermissionAuthority().canEditInstance(newContainer)) {
                    for (final DLine lineToDelete : new ArrayList<DLine>(newContainer.getLines())) {
                        doDeleteLine(lineToDelete, xref);
                    }
                }
            }
        }
    }

    /**
     * Refresh all cells corresponding to the <code>iMapping</code>.
     *
     * @param iMapping
     *            The intersection mapping for which we want to refresh the corresponding cells.
     * @param mappingToElements
     *            A map that list the DTableElement (line or column) for each mapping
     * @param xref
     *            the cross referencer to use
     */
    private void refreshIntersectionMapping(final IntersectionMapping iMapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, ECrossReferenceAdapter xref) {
        SetIntersection<DCellCandidate> status = null;
        if (iMapping.isUseDomainClass()) {
            status = refreshIntersectionMappingWithDomain(iMapping, mappingToElements);
        } else {
            status = refreshIntersectionMappingWithoutDomain(iMapping, mappingToElements);
        }
        updateCells(iMapping, status, xref);
    }

    /**
     * Refresh all cells corresponding to the <code>iMapping</code>.
     *
     * @param iMapping
     *            The intersection mapping with domain class for which we want to refresh the corresponding cells.
     * @param mappingToElements
     *            A map that list the DTableElement (line or column) for each mapping
     * @return status of modifications
     */
    private SetIntersection<DCellCandidate> refreshIntersectionMappingWithDomain(final IntersectionMapping iMapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        final SetIntersection<DCellCandidate> status = new GSetIntersection<DCellCandidate>();

        setOldCellCandidatesWithDomain(iMapping, status);

        // Compute the cells for the current intersection mapping and add them
        // to the new values.
        Collection<EObject> targetCandidates = getTargetCanditatesWithDomain(iMapping);
        final Collection<DTableElement> columnViews = mappingToElements.get(iMapping.getColumnMapping());
        final Collection<DTableElement> lineViews = collectLineElementsWithDomain(iMapping, mappingToElements);

        // No need to do many computation if no semantic element is found.
        if (targetCandidates.size() > 0 && columnViews != null) {

            // Let's build the "semantic to mapping element" map.
            final Map<EObject, Collection<DLine>> linesSemantics = groupDecorators(lineViews, DLine.class);
            final Map<EObject, Collection<DTargetColumn>> columnSemantics = groupDecorators(lineViews, DTargetColumn.class);

            // Now we've got the semantic element from which we should try to
            // create Edges we can start evaluating possible candidates.
            for (final EObject target : targetCandidates) {
                Collection<EObject> lineSemanticCandidates = sync.evaluateLineFinderExpression(target, table, iMapping);
                Collection<EObject> columnSemanticCandidates = sync.evaluateColumnFinderExpression(target, table, iMapping, true);
                for (final EObject lineSemantic : lineSemanticCandidates) {
                    for (final EObject columnSemantic : columnSemanticCandidates) {
                        final Collection<DLine> sourceViews = linesSemantics.get(lineSemantic);
                        if (sourceViews == null) {
                            break;
                        }
                        
                        final Collection<DTargetColumn> targetViews = columnSemantics.get(columnSemantic);
                        if (targetViews == null) {
                            break;
                        }

                        setNewCellCandidatesWithDomain(iMapping, status, target, sourceViews, targetViews);
                    }
                }
            }

        }

        return status;
    }

    /**
     * @param iMapping
     * @param status
     */
    private void setOldCellCandidatesWithDomain(final IntersectionMapping iMapping, final SetIntersection<DCellCandidate> status) {
        // Add all existing cells with this mapping or with an invalid mapping
        // to the old values.
        for (final DCell cell : new DTableQuery(table).getCells()) {
            IntersectionMapping intersectionMapping = cell.getIntersectionMapping();
            boolean mappingIsObsolete = intersectionMapping == null || intersectionMapping.eResource() == null;
            if (iMapping.equals(intersectionMapping) || mappingIsObsolete) {
                status.addInOld(new DCellCandidate(cell, this.ids));
            }
        }
    }
    
    private Collection<EObject> getTargetCanditatesWithDomain(final IntersectionMapping iMapping) {
        Collection<EObject> result;
        final EObject rootContent = table.getTarget();
        if (StringUtil.isEmpty(iMapping.getSemanticCandidatesExpression())) {
            result = this.accessor.eAllContents(rootContent, iMapping.getDomainClass());
        } else {
            result = interpreter.evaluateCollection(rootContent, iMapping, DescriptionPackage.eINSTANCE.getIntersectionMapping_SemanticCandidatesExpression());
        }
        return result;
    }


    private void setNewCellCandidatesWithDomain(final IntersectionMapping iMapping, final SetIntersection<DCellCandidate> status, final EObject target, 
            final Collection<DLine> sourceViews,
            final Collection<DTargetColumn> targetViews) {
        for (final EObjectCouple viewsCouple : new CartesianProduct(sourceViews, targetViews, ids)) {
            final DLine line = (DLine) viewsCouple.getObj1();
            final DColumn column = (DColumn) viewsCouple.getObj2();

            if (sync.evaluateIntersectionPrecondition(column.getTarget(), line, column, iMapping)) {
                status.addInNew(new DCellCandidate(iMapping.getColumnMapping(), target, line, column, this.ids));
            }
        }
    }


    private Collection<DTableElement> collectLineElementsWithDomain(final IntersectionMapping iMapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        final Collection<DTableElement> lineViews = new MultipleCollection<DTableElement>();
        for (final LineMapping lMapping : iMapping.getLineMapping()) {
            final Collection<DTableElement> lines = mappingToElements.get(lMapping);
            if (lines != null) {
                lineViews.addAll(lines);
            }
        }
        return lineViews;
    }

    private <D extends DSemanticDecorator> Map<EObject, Collection<D>> groupDecorators(Collection<?> source, Class<D> expectedType) {
        final Map<EObject, Collection<D>> result = new HashMap<>(source.size());
        Function<EObject, Collection<D>> groupProvider = key -> new ArrayList<>();
        for (final Object element : source) {
            if (expectedType.isInstance(element)) {
                D decorator = expectedType.cast(element);
                result.computeIfAbsent(decorator.getTarget(), groupProvider)
                    .add(decorator);
            }
        }
        return result;
    }
    
    /**
     * Creates the new cells, update the kept ones and remove the old ones.
     *
     * @param iMapping
     *            the intersection mapping that is being refreshed
     * @param cellsToUpdate
     *            the {@link SetIntersection} containing new, kept and deleted elements
     * @param xref
     *            the cross referencer to use
     */
    private void updateCells(final IntersectionMapping iMapping, final SetIntersection<DCellCandidate> cellsToUpdate, ECrossReferenceAdapter xref) {
        // Let's now create the new cells, update the kept ones and remove the
        // old ones.
        for (final DCellCandidate toCreate : cellsToUpdate.getNewElements()) {
            final Optional<DCell> optionalCell = createCell(toCreate.getLine(), toCreate.getColumn(), toCreate.getSemantic(), toCreate.getMapping());
            if (optionalCell.isPresent()) {
                optionalCell.get().setIntersectionMapping(iMapping);
                this.sync.refresh(optionalCell.get());
            }

        }
        for (final DCellCandidate toUpdate : cellsToUpdate.getKeptElements()) {
            final DCell cell = toUpdate.getOriginalElement();

            if (cell != null) {
                if (accessor.getPermissionAuthority().canEditInstance(cell)) {
                    if (cell.getTarget() != toUpdate.getSemantic()) {
                        cell.setTarget(toUpdate.getSemantic());
                    }
                    cell.getSemanticElements().add(toUpdate.getSemantic());
                    if (cell.getIntersectionMapping() != iMapping) {
                        cell.setIntersectionMapping(iMapping);
                    }
                    this.sync.refresh(cell);
                }
            }
        }
        for (final DCellCandidate toRemove : cellsToUpdate.getRemovedElements()) {
            final DCell cell = toRemove.getOriginalElement();
            doDeleteCell(cell, xref);
        }
    }

    /**
     * Refresh all cells corresponding to the <code>iMapping</code>.
     *
     * @param iMapping
     *            The intersection mapping without domain class for which we want to refresh the corresponding cells.
     * @param mappingToElements
     *            A map that list the DTableElement (line or column) for each mapping
     * @return status of modifications
     */
    private SetIntersection<DCellCandidate> refreshIntersectionMappingWithoutDomain(final IntersectionMapping iMapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        final SetIntersection<DCellCandidate> status = new SetIntersection<DCellCandidate>();

        setOldCellCandidatesWithDomain(iMapping, status);

        // Compute the cells for the current intersection mapping and add them
        // to the new values.
        for (final LineMapping curLineMapping : iMapping.getLineMapping()) {
            final Collection<DTableElement> sourceLines = mappingToElements.get(curLineMapping);
            final Collection<DTableElement> targetColumns = mappingToElements.get(iMapping.getColumnMapping());

            final Map<DLine, Collection<EObject>> linesToColumnSemantics = new HashMap<DLine, Collection<EObject>>();
            if (sourceLines != null && targetColumns != null) {
                for (final DTableElement lineElem : sourceLines) {
                    for (final DTableElement columnElem : targetColumns) {
                        if (lineElem instanceof DLine && columnElem instanceof DTargetColumn) {
                            final DLine line = (DLine) lineElem;
                            final DTargetColumn column = (DTargetColumn) columnElem;
                            Collection<EObject> columnSemantics = linesToColumnSemantics.get(line);
                            if (columnSemantics == null) {
                                columnSemantics = sync.evaluateColumnFinderExpression(line, line, iMapping, true);
                                linesToColumnSemantics.put(line, columnSemantics);
                            }
                            if (columnSemantics.contains(column.getTarget()) 
                                    && sync.evaluateIntersectionPrecondition(column.getTarget(), line, column, iMapping)) {
                                status.addInNew(new DCellCandidate(iMapping.getColumnMapping(), line.getTarget(), line, column, this.ids));
                            }
                        }
                    }
                }
            }
        }

        return status;
    }

    /**
     * Put or add the given nodes in the Map.
     *
     * @param map
     *            the map to update.
     * @param mapping
     *            the key
     * @param elements 
     */
    private void putOrAdd(final Map<TableMapping, Collection<DTableElement>> map, final TableMapping mapping, final Collection<DTableElement> elements) {
        Collection<DTableElement> existing = map.get(mapping);
        if (existing == null) {
            existing = new MultipleCollection<DTableElement>();
            existing.addAll(elements);
            map.put(mapping, existing);
        } else {
            existing.addAll(elements);
        }
    }

    /**
     * Create a new line corresponding to the mapping.
     *
     * @param mapping
     *            the line mapping.
     * @param semantic
     *            line semantic target.
     * @return the newly created line.
     */
    private DLine createNewLine(final LineMapping mapping, final EObject semantic) {
        final DLine line = TableFactory.eINSTANCE.createDLine();
        line.setOriginMapping(mapping);
        line.setTarget(semantic);
        return line;
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
    private DFeatureColumn createNewColumn(final ColumnMapping mapping, final String featureName) {
        final DFeatureColumn column = TableFactory.eINSTANCE.createDFeatureColumn();
        column.setOriginMapping(mapping);
        column.setFeatureName(featureName);
        return column;
    }

    /**
     * Create a new column corresponding to the mapping.
     *
     * @param mapping
     *            the column mapping.
     * @param semantic
     *            the target semantic element.
     * @return the newly created column.
     */
    private DTargetColumn createNewColumn(final ColumnMapping mapping, final EObject semantic) {
        final DTargetColumn column = TableFactory.eINSTANCE.createDTargetColumn();
        column.setOriginMapping(mapping);
        column.setTarget(semantic);
        return column;
    }

    /**
     * Deletes the given lines, the contained subLines and cells and removes all references to deleted elements.
     *
     * @param lineToDelete
     *            the line to delete
     * @param xref
     *            the cross reference to use to get the elements referencing the deleted line (or its sublines and
     *            cells)
     */
    private void doDeleteLine(DLine lineToDelete, ECrossReferenceAdapter xref) {
        // Step 1: delete all sublines (and all references to these sublines)
        for (DLine line : Sets.newLinkedHashSet(lineToDelete.getLines())) {
            doDeleteLine(line, xref);
        }

        // Step 2 : delete all cells (and all references to these cells)
        for (DCell cell : Sets.newLinkedHashSet(lineToDelete.getCells())) {
            doDeleteCell(cell, xref);
        }

        // Step 3 : delete the line (and all references to it)
        if (accessor.getPermissionAuthority().canDeleteInstance(lineToDelete)) {
            accessor.eDelete(lineToDelete, xref);
        }
    }

    /**
     * Deletes the given column, and the contained cells and removes all references to deleted elements.
     *
     * @param columnToDelete
     *            the column to delete
     * @param xref
     *            the cross reference to use to get the elements referencing the deleted column (or its cells)
     */
    private void doDeleteColumn(DColumn columnToDelete, ECrossReferenceAdapter xref) {
        // Step 1: delete all cells contained in this column (and all references
        // to these cells)
        for (DCell cell : Sets.newLinkedHashSet(columnToDelete.getCells())) {
            doDeleteCell(cell, xref);
        }
        // Step 2: delete the column (and all references to this column)
        if (accessor.getPermissionAuthority().canDeleteInstance(columnToDelete)) {
            accessor.eDelete(columnToDelete, xref);
        }

    }

    /**
     * Deletes the given cell, and removes all references to deleted elements.
     *
     * @param cell
     *            the cell to delete
     * @param xref
     *            the cross reference to use to get the elements referencing the deleted cell
     */
    private void doDeleteCell(DCell cell, ECrossReferenceAdapter xref) {
        // Delegate to the DTable elements synchronize,
        // that will properly remove the cell and all references to it
        this.sync.removeUneededCell(cell);
    }

    /**
     * Compute the current status of a given line container for a given LineMapping.
     *
     * @param container
     *            current line container.
     * @param mapping
     *            current line mapping.
     * @param xref
     *            the session corss referencer
     * @return a {@link SetIntersection} representing the current status.
     */
    private SetIntersection<DLineCandidate> computeCurrentStatus(final LineContainer container, final LineMapping mapping, ECrossReferenceAdapter xref) {
        final SetIntersection<DLineCandidate> status = new SetIntersection<DLineCandidate>();
        removeTargetLessLines(container, xref);
        setOldlineCandidates(container, mapping, status);
        final MultipleCollection<EObject> semantics = new MultipleCollection<EObject>();
        if (TableHelper.hasSemanticCandidatesExpression(mapping)) {
            InterpretationContext.with(interpreter, ctx -> {
                ctx.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, container);
                ctx.setVariable(IInterpreterSiriusVariables.CONTAINER, container.getTarget());
                ctx.setVariable(IInterpreterSiriusVariables.ROOT, table.getTarget());
                ctx.setVariable(IInterpreterSiriusVariables.VIEWPOINT, this.table);
                ctx.setVariable(IInterpreterSiriusVariables.TABLE, this.table);

                final Collection<EObject> candidates = interpreter.evaluateCollection(container.getTarget(), mapping, 
                        DescriptionPackage.eINSTANCE.getLineMapping_SemanticCandidatesExpression());
                semantics.addAll(candidates);
                
            });
        } else {
            semantics.addAll(this.accessor.eAllContents(container.getTarget(), mapping.getDomainClass()));
        }

        /*
         * produce the candidates states if the precondition is valid.
         */
        for (final EObject semantic : semantics) {
            if (this.accessor.eInstanceOf(semantic, mapping.getDomainClass())) {
                status.addInNew(new DLineCandidate(mapping, semantic, container, this.ids));
            }
        }
        return status;
    }

    private void setOldlineCandidates(final LineContainer container, final LineMapping mapping, final SetIntersection<DLineCandidate> status) {
        for (final DLine line : container.getLines()) {
            LineMapping originMapping = line.getOriginMapping();
            if (originMapping == mapping || originMapping.eResource() == null) {
                status.addInOld(new DLineCandidate(line, this.ids));
            }
        }
    }

    private void removeTargetLessLines(final LineContainer container, ECrossReferenceAdapter xref) {
        // Remove line with no target (target == null). This case can happen
        // when the dangling references are removed during the delete of the
        // semantic element.
        if (this.accessor.getPermissionAuthority().canEditInstance(container)) {
            List<DLine> linesWithoutTarget = new ArrayList<DLine>();
            for (DLine line : container.getLines()) {
                if (line.getTarget() == null) {
                    linesWithoutTarget.add(line);
                }
            }
            for (DLine lineWithoutTarget : linesWithoutTarget) {
                doDeleteLine(lineWithoutTarget, xref);
            }
        }
    }

    @Override
    public void setTable(final DTable newTable) {
        this.table = newTable;
        this.ids = RefreshIdsHolder.getOrCreateHolder(table);
    }

    @Override
    public DTable getTable() {
        return table;
    }


}
