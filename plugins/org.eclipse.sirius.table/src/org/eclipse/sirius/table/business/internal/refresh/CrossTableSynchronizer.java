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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.InterpretationContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.CartesianProduct;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
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
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Synchronizer for Cross tables.
 *
 * @author cbrun
 */
public class CrossTableSynchronizer extends AbstractTableSynchronizer<CrossTableDescription, ElementColumnMapping> {

    /**
     * Create a new {@link DTableSynchronizer} for an EditionTable.
     *
     * @param description
     *            EditionTable description.
     * @param sync
     *            element synchronizer.
     */
    public CrossTableSynchronizer(final CrossTableDescription description, final DTableElementSynchronizer sync) {
        super(description, sync);
    }

    @Override
    protected void initRefreshMonitor(final IProgressMonitor monitor) {
        monitor.beginTask(Messages.DTableSynchronizerImpl_refreshCrossTabel, 3);
    }

    @Override
    protected List<ElementColumnMapping> getColumnMappings() {
        return description.getOwnedColumnMappings();
    }

    @Override
    protected void refreshCells(final IProgressMonitor monitor, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
       try {
            monitor.beginTask(Messages.DTableSynchronizerImpl_refreshIntersectionMapping, description.getIntersection().size());
            if (description.getIntersection().isEmpty()) {
                // If there is no intersection mapping we must clean all the
                // invalid
                // cells of the DTable (this must normally be done in the first
                // intersectionMapping)
                for (final DCell cell : new DTableQuery(table).getCells()) {
                    sync.removeUneededCell(cell);
                }
            } else {
                for (final IntersectionMapping iMapping : description.getIntersection()) {
                    refreshIntersectionMapping(iMapping, mappingToElements);
                    monitor.worked(1);
                }
            }
        } finally {
            monitor.done();
        }
    }

    @Override
    protected int refreshColumnMapping(final ElementColumnMapping mapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements, final int previousCurrentIndex) {
        int currentIndex = previousCurrentIndex;
        if (accessor.getPermissionAuthority().canEditInstance(table)) {
            final SetIntersection<DTargetColumnCandidate> status = computeCurrentStatus(mapping);
            final Collection<DTableElement> elementsToKeep = new ArrayList<DTableElement>();
            // Remove old elements
            for (final DTargetColumnCandidate toDelete : status.getRemovedElements()) {
                if (toDelete.getOriginalElement() != null) {
                    doDeleteColumn(toDelete.getOriginalElement());
                }
            }
            
            // Treat existing elements and new elements in the order of the
            // result of the expression
            for (final DTargetColumnCandidate targetColumnCandidate : status.getAllElements()) {
                refreshColumnCandidate(targetColumnCandidate, currentIndex, elementsToKeep);
                currentIndex++;
            }
            putOrAdd(mappingToElements, mapping, elementsToKeep);
        }
        return currentIndex;
    }

    private void refreshColumnCandidate(final DTargetColumnCandidate candidate, int currentIndex, final Collection<DTableElement> elementsToKeep) {
        if (candidate.getOriginalElement() == null) {
            final DTargetColumn newC = createNewColumn(candidate.getMapping(), candidate.getSemantic());
            sync.refresh(newC);
            sync.refreshSemanticElements(newC, candidate.getMapping());
            if (accessor.getPermissionAuthority().canCreateIn(table)) {
                table.getColumns().add(currentIndex, newC);
            }
            elementsToKeep.add(newC);
        } else {
            sync.refresh(candidate.getOriginalElement());
            sync.refreshSemanticElements(candidate.getOriginalElement(), candidate.getMapping());
            final DTable parentTable = candidate.getOriginalElement().getTable();
            if (parentTable.getColumns().size() >= currentIndex) {
                if (accessor.getPermissionAuthority().canEditInstance(parentTable) 
                        && !candidate.getOriginalElement().equals(parentTable.getColumns().get(currentIndex))) {
                    parentTable.getColumns().move(currentIndex, candidate.getOriginalElement());
                }
            } else {
                if (accessor.getPermissionAuthority().canEditInstance(parentTable)) {
                    parentTable.getColumns().move(parentTable.getColumns().size() - 1, candidate.getOriginalElement());
                }
            }
            elementsToKeep.add(candidate.getOriginalElement());
        }
    }

    private SetIntersection<DTargetColumnCandidate> computeCurrentStatus(final ElementColumnMapping mapping) {
        
        removeTargetLessColumns(mapping);

        final SetIntersection<DTargetColumnCandidate> status = new GSetIntersection<DTargetColumnCandidate>();
        for (final DColumn column : table.getColumns()) {
            if (column.getOriginMapping() == mapping) {
                if (column instanceof DTargetColumn) {
                    status.addInOld(new DTargetColumnCandidate((DTargetColumn) column, ids));
                }
            }
        }
        
        final MultipleCollection<EObject> semantics = new MultipleCollection<>();
        if (TableHelper.hasSemanticCandidatesExpression(mapping)) {
            final Collection<EObject> candidates = InterpretationContext.with(interpreter, ctx -> {
                ctx.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, table);
                ctx.setVariable(IInterpreterSiriusVariables.CONTAINER, table.getTarget());
                ctx.setVariable(IInterpreterSiriusVariables.VIEWPOINT, table);
                ctx.setVariable(IInterpreterSiriusVariables.TABLE, table);

                return interpreter.evaluateCollection(table.getTarget(), mapping, 
                        DescriptionPackage.eINSTANCE.getElementColumnMapping_SemanticCandidatesExpression());

            });
            if (!candidates.isEmpty()) {
                semantics.addAll(candidates);
            }

        } else {
            semantics.addAll(accessor.eAllContents(table.getTarget(), mapping.getDomainClass()));
        }

        /*
         * produce the candidates states if the precondition is valid.
         */
        for (final EObject semantic : semantics) {
            if (accessor.eInstanceOf(semantic, mapping.getDomainClass())) {
                status.addInNew(new DTargetColumnCandidate(mapping, semantic, ids));
            }
        }
        return status;

    }

    private void removeTargetLessColumns(final ElementColumnMapping mapping) {
        // Remove column with no target (target == null). This case can happen
        // when the dangling references are removed during the delete of the
        // semantic element.
        if (accessor.getPermissionAuthority().canEditInstance(table)) {
            List<DColumn> columnsWithoutTarget = new ArrayList<>();
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
                doDeleteColumn(columnWithoutTarget);
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

     */
    private void refreshIntersectionMapping(final IntersectionMapping iMapping, final Map<TableMapping, Collection<DTableElement>> mappingToElements) {
        SetIntersection<DCellCandidate> status = null;
        if (iMapping.isUseDomainClass()) {
            status = refreshIntersectionMappingWithDomain(iMapping, mappingToElements);
        } else {
            status = refreshIntersectionMappingWithoutDomain(iMapping, mappingToElements);
        }
        updateCells(iMapping, status);
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
        if (!targetCandidates.isEmpty()) {
            
            Map<EObject, Collection<DLine>> linesSemantics = groupLinesByTarget(mappingToElements, iMapping.getLineMapping());
            // Let's build the "semantic to mapping element" map.
            Map<EObject, Collection<DTargetColumn>> columnSemantics = groupDecorators(
                    mappingToElements.getOrDefault(iMapping.getColumnMapping(), Collections.emptyList()),
                    DTargetColumn.class);
            
            if (!linesSemantics.isEmpty() && !columnSemantics.isEmpty()) {
                refreshIntersectionWithDomain(iMapping, status, targetCandidates, linesSemantics, columnSemantics);
            }
        }

        return status;
    }

    private void refreshIntersectionWithDomain(final IntersectionMapping iMapping, 
            final SetIntersection<DCellCandidate> status,
            final Collection<EObject> targetCandidates,
            Map<EObject, Collection<DLine>> linesSemantics,
            Map<EObject, Collection<DTargetColumn>> columnSemantics) {
        
        // Now we've got the semantic element from which we should try to
        // create Edges we can start evaluating possible candidates.
        for (final EObject target : targetCandidates) {
            Collection<EObject> lineSemanticCandidates = sync.evaluateLineFinderExpression(target, table, iMapping);
            if (lineSemanticCandidates.isEmpty()) {
                continue;
            }
            Collection<EObject> columnSemanticCandidates = sync.evaluateColumnFinderExpression(target, table, iMapping, true);
            if (columnSemanticCandidates.isEmpty()) {
                continue;
            }
            
            for (final EObject lineSemantic : lineSemanticCandidates) {
                final Collection<DLine> sourceViews = linesSemantics.get(lineSemantic);
                if (sourceViews != null) {
                    for (final EObject columnSemantic : columnSemanticCandidates) {
                        final Collection<DTargetColumn> targetViews = columnSemantics.get(columnSemantic);
                        if (targetViews != null) {
                            setNewCellCandidatesWithDomain(iMapping, status, target, sourceViews, targetViews);
                        }
                    }
                }
            }
        }
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
                status.addInOld(new DCellCandidate(cell, ids));
            }
        }
    }
    
    private Collection<EObject> getTargetCanditatesWithDomain(final IntersectionMapping iMapping) {
        Collection<EObject> result;
        final EObject rootContent = table.getTarget();
        if (StringUtil.isEmpty(iMapping.getSemanticCandidatesExpression())) {
            result = accessor.eAllContents(rootContent, iMapping.getDomainClass());
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
                status.addInNew(new DCellCandidate(iMapping.getColumnMapping(), target, line, column, ids));
            }
        }
    }


    private Map<EObject, Collection<DLine>> groupLinesByTarget(final Map<TableMapping, Collection<DTableElement>> mappingToElements, List<LineMapping> mappings) {
        final Collection<DTableElement> lineViews = new MultipleCollection<>();
        for (final LineMapping lMapping : mappings) {
            final Collection<DTableElement> lines = mappingToElements.get(lMapping);
            if (lines != null) {
                lineViews.addAll(lines);
            }
        }
        return groupDecorators(lineViews, DLine.class);
    }

    private <D extends DSemanticDecorator> Map<EObject, Collection<D>> groupDecorators(Collection<?> source, Class<D> expectedType) {
        final Map<EObject, Collection<D>> result = new HashMap<>(source.size());
        // In most of case, 1 semantic element is associated to 1 axis value.
        Function<EObject, Collection<D>> groupProvider = key -> new ArrayList<>(1);
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
     */
    private void updateCells(final IntersectionMapping iMapping, final SetIntersection<DCellCandidate> cellsToUpdate) {
        // Let's now create the new cells, update the kept ones and remove the
        // old ones.
        for (final DCellCandidate toCreate : cellsToUpdate.getNewElements()) {
            final DCell newCell = createCell(toCreate.getLine(), toCreate.getColumn(), toCreate.getSemantic(), (ElementColumnMapping) toCreate.getMapping());
            newCell.setIntersectionMapping(iMapping);
            sync.refresh(newCell);

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
                    sync.refresh(cell);
                }
            }
        }
        for (final DCellCandidate toRemove : cellsToUpdate.getRemovedElements()) {
            final DCell cell = toRemove.getOriginalElement();
            sync.removeUneededCell(cell);
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
        final Collection<DTableElement> targetColumns = mappingToElements.getOrDefault(iMapping.getColumnMapping(),
                Collections.emptyList());
        if (targetColumns.isEmpty()) {
            return status;
        }
        
        for (final LineMapping curLineMapping : iMapping.getLineMapping()) {
            final Collection<DTableElement> sourceLines = mappingToElements.getOrDefault(curLineMapping, 
                    Collections.emptyList());

            final Map<DLine, Collection<EObject>> linesToColumnSemantics = new HashMap<>();

            for (final DTableElement lineElem : sourceLines) {
                for (final DTableElement columnElem : targetColumns) {
                    if (lineElem instanceof DLine && columnElem instanceof DTargetColumn) {
                        final DLine line = (DLine) lineElem;
                        final DTargetColumn column = (DTargetColumn) columnElem;
                        Collection<EObject> columnSemantics = linesToColumnSemantics.computeIfAbsent(line,
                                it -> sync.evaluateColumnFinderExpression(line.getTarget(), line, iMapping, true));

                        if (columnSemantics.contains(column.getTarget()) 
                                && sync.evaluateIntersectionPrecondition(column.getTarget(), line, column, iMapping)) {
                            status.addInNew(new DCellCandidate(iMapping.getColumnMapping(), line.getTarget(), line, column, ids));
                        }
                    }
                }
            }
        }

        return status;
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
    protected DTargetColumn createNewColumn(final ColumnMapping mapping, final EObject semantic) {
        final DTargetColumn column = TableFactory.eINSTANCE.createDTargetColumn();
        column.setOriginMapping(mapping);
        column.setTarget(semantic);
        return column;
    }

}
