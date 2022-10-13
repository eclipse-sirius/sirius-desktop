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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.logger.InterpretationContext;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.collect.MultipleCollection;
import org.eclipse.sirius.ext.base.collect.SetIntersection;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;

import com.google.common.collect.Sets;

/**
 * Common methods of synchronizer for tables.
 *
 * @author cbrun
 * @param <D> type of table description
 * @param <C> type of column description
 */
public abstract class AbstractTableSynchronizer<D extends TableDescription, C extends ColumnMapping> implements DTableSynchronizer {

    /** Description of the table. */
    protected final D description;

    /** Expression interpreter. */
    protected final RuntimeLoggerInterpreter interpreter;

    /** Model acessor. */
    protected final ModelAccessor accessor;

    /** Synchronizer. */
    protected final DTableElementSynchronizer sync;

    /** Current table. */
    protected DTable table;
    
    /** Cross referencer of Current table. */
    protected ECrossReferenceAdapter xref;

    /** Id holder of current table. */
    protected RefreshIdsHolder ids;

    /**
     * Create a new {@link DTableSynchronizer} for an EditionTable.
     *
     * @param description
     *            EditionTable description.
     * @param sync
     *            element synchronizer.
     */
    protected AbstractTableSynchronizer(final D description, final DTableElementSynchronizer sync) {
        this.sync = sync;
        this.accessor = sync.getAccessor();
        this.description = description;
        this.interpreter = sync.getInterpreter();
    }

    
    /**
     * Configure monitor for refresh task.
     * 
     * @param monitor to configure
     */
    protected abstract void initRefreshMonitor(IProgressMonitor monitor);
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void refresh(final IProgressMonitor monitor) {
        try {
            initRefreshMonitor(monitor);

            KeyCache.DEFAULT.clear();
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_TABLE_KEY);

            final Map<TableMapping, Collection<DTableElement>> mappingToElements = new HashMap<>();
            
            if (!table.eIsSet(TablePackage.Literals.DTABLE__HEADER_COLUMN_WIDTH)) {
                table.setHeaderColumnWidth(description.getInitialHeaderColumnWidth());
            }

            refreshLines(new SubProgressMonitor(monitor, 1), mappingToElements);
            KeyCache.DEFAULT.clear();
            
            refreshColumns(new SubProgressMonitor(monitor, 1), mappingToElements);
            KeyCache.DEFAULT.clear();
            
            refreshCells(new SubProgressMonitor(monitor, 1), mappingToElements);
            KeyCache.DEFAULT.clear();

            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_TABLE_KEY);
        } finally {
            monitor.done();
        }
    }

    private void refreshLines(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        try {

            List<LineMapping> lMappings = new ArrayList<LineMapping>();
            if (description != null) {
                lMappings = description.getAllLineMappings();
            }
            monitor.beginTask(Messages.DTableSynchronizerImpl_refreshLineMapping, lMappings.size());

            int currentLineIndex = 0;
            for (final LineMapping lMapping : lMappings) {
                currentLineIndex = refreshLineMapping(table, lMapping, mappingToElements, currentLineIndex);
                monitor.worked(1);
            }
            // if there is no more line mapping in vsm delete table rows
            // corresponding
            if (lMappings.isEmpty()) {
                for (final DLine lineToDelete : new ArrayList<DLine>(table.getLines())) {
                    doDeleteLine(lineToDelete);
                }
            }

        } finally {
            monitor.done();
        }
    }

    private void refreshColumns(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        try {
            List<C> cMappings = getColumnMappings();
            monitor.beginTask(Messages.DTableSynchronizerImpl_refreshColumnMapping, cMappings.size());

            int currentColumnIndex = 0;
            for (final C cMapping : cMappings) {
                currentColumnIndex = refreshColumnMapping(cMapping, mappingToElements, currentColumnIndex);
                monitor.worked(1);
            }
            
            // If there is no more column mapping in VSM, delete column in table
            if (cMappings.isEmpty()) {
                for (final DColumn columnToDelete : new ArrayList<>(table.getColumns())) {
                    doDeleteColumn(columnToDelete);
                }
            }

        } finally {
            monitor.done();
        }
    }

    /**
     * Returns the column mappings of the table.
     * 
     * @return mappings
     */
    protected abstract List<C> getColumnMappings();

    /**
     * Refresh all the cells of a crossTable.
     * <p>
     * <UL>
     * <LI>Remove the cells that have no corresponding intersection mapping,</LI>
     * <LI>Refresh the existing one</LI>
     * <LI>Create the new one</LI>
     * <UL>
     * </p>
     * @param monitor
     *            The progress monitor
     * @param mappingToElements
     *            A map that list the DTableElement (line or column) for each mapping
     */
    protected abstract void refreshCells(IProgressMonitor monitor, Map<TableMapping, Collection<DTableElement>> mappingToElements);


    /**
     * Creates a cell.
     * 
     * @param line of the new cell
     * @param column of the new cell
     * @param semantic of the cell
     * @param mapping of the column
     * @return cell if valid
     */
    protected DCell createCell(final DLine line, final DColumn column, final EObject semantic, final C mapping) {
        DCell newCell = TableFactory.eINSTANCE.createDCell();
        newCell.setTarget(semantic);

        // Assign this cell to its line and column.
        newCell.setLine(line);
        newCell.setColumn(column);

        return newCell;
    }

    /**
     * Refresh a column mapping adding the columns.
     *
     * @param mapping
     *            mapping to refresh.
     * @param mappingToElements
     *            an holder for the mapping to element mapping.
     * @param previousCurrentIndex 
     *            of column
     * @return new column index 
     */
    protected abstract int refreshColumnMapping(C mapping, Map<TableMapping, Collection<DTableElement>> mappingToElements, int previousCurrentIndex);
    

    /**
     * Refresh a line mapping for a given container.
     *
     * @param container
     *            the line container.
     * @param mapping
     *            the mapping to refresh.
     * @param mappingToElements
     *            an holder for the mapping to element mapping.
     * @param previousCurrentIndex
     *            current line index
     * @return new index to refresh
     */
    private int refreshLineMapping(final LineContainer container, final LineMapping mapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final int previousCurrentIndex) {
        int currentIndex = previousCurrentIndex;
        final SetIntersection<DLineCandidate> status = computeCurrentStatus(container, mapping);
        final Collection<DLine> possibleContainers = new ArrayList<DLine>();
        final Collection<DTableElement> elementsToKeep = new ArrayList<DTableElement>();
        // Remove old elements
        if (this.accessor.getPermissionAuthority().canEditInstance(container)) {
            for (final DLineCandidate toDelete : status.getRemovedElements()) {
                if (toDelete.getOriginalElement() != null) {
                    doDeleteLine(toDelete.getOriginalElement());
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
        refreshLineCandidates(possibleContainers, mappingToElements);
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


    private void refreshLineCandidates(final Collection<DLine> possibleContainers, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        for (final DLine newContainer : possibleContainers) {
            int currentSubIndex = 0;
            EList<LineMapping> allSubLines = newContainer.getOriginMapping().getAllSubLines();

            for (final LineMapping subMapping : allSubLines) {
                currentSubIndex = refreshLineMapping(newContainer, subMapping, mappingToElements, currentSubIndex);
            }

            if (allSubLines.isEmpty() && !newContainer.getLines().isEmpty()) {
                // If there is no more sub lines mapping in VSM, delete table
                // corresponding sub rows, otherwise compute current status will
                // delete lines without mapping
                if (this.accessor.getPermissionAuthority().canEditInstance(newContainer)) {
                    for (final DLine lineToDelete : new ArrayList<DLine>(newContainer.getLines())) {
                        doDeleteLine(lineToDelete);
                    }
                }
            }
        }
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
    protected void putOrAdd(final Map<TableMapping, Collection<DTableElement>> map, final TableMapping mapping, final Collection<DTableElement> elements) {
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
    protected DLine createNewLine(final LineMapping mapping, final EObject semantic) {
        final DLine line = TableFactory.eINSTANCE.createDLine();
        line.setOriginMapping(mapping);
        line.setTarget(semantic);
        return line;
    }


    /**
     * Deletes the given lines, the contained subLines and cells and removes all references to deleted elements.
     *
     * @param lineToDelete
     *            the line to delete
     */
    protected void doDeleteLine(DLine lineToDelete) {
        // Step 1: delete all sublines (and all references to these sublines)
        for (DLine line : Sets.newLinkedHashSet(lineToDelete.getLines())) {
            doDeleteLine(line);
        }

        // Step 2 : delete all cells (and all references to these cells)
        for (DCell cell : Sets.newLinkedHashSet(lineToDelete.getCells())) {
            this.sync.removeUneededCell(cell);
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
     */
    protected void doDeleteColumn(DColumn columnToDelete) {
        // Step 1: delete all cells contained in this column (and all references
        // to these cells)
        for (DCell cell : Sets.newLinkedHashSet(columnToDelete.getCells())) {
            this.sync.removeUneededCell(cell);
        }
        // Step 2: delete the column (and all references to this column)
        if (accessor.getPermissionAuthority().canDeleteInstance(columnToDelete)) {
            accessor.eDelete(columnToDelete, xref);
        }

    }

    /**
     * Compute the current status of a given line container for a given LineMapping.
     *
     * @param container
     *            current line container.
     * @param mapping
     *            current line mapping.
     * @return a {@link SetIntersection} representing the current status.
     */
    private SetIntersection<DLineCandidate> computeCurrentStatus(final LineContainer container, final LineMapping mapping) {
        final SetIntersection<DLineCandidate> status = new SetIntersection<DLineCandidate>();
        removeTargetLessLines(container);
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

    private void removeTargetLessLines(final LineContainer container) {
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
                doDeleteLine(lineWithoutTarget);
            }
        }
    }

    @Override
    public void setTable(final DTable newTable) {
        this.table = newTable;
        this.ids = RefreshIdsHolder.getOrCreateHolder(table);
        this.xref = ECrossReferenceAdapter.getCrossReferenceAdapter(table.getTarget());
    }

    @Override
    public DTable getTable() {
        return table;
    }


}
