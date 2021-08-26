/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.business.api.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.model.business.internal.helper.TableModelHelper;

import com.google.common.collect.Maps;

/**
 * Utility methods to handle Table models.
 * 
 * @author cbrun
 * 
 */
public final class TableHelper {

    /**
     * Cache for quick access to DCells by DLine/DColumn.
     */
    private static final Map<DLine, Map<DColumn, DCell>> CACHE = new LinkedHashMap<>();

    /**
     * The table whose cells are currently cached. May be null.
     */
    private static LineContainer currentlyCached;

    private TableHelper() {

    }

    /**
     * Caches the content of a table to optimize the performance of {@link #getCell(DLine, DColumn)}. This method must
     * be called carefully when the model is not supposed to be modified. The method {@link #clearCache()} must be
     * called just after the calls to {@link #getCell(DLine, DColumn)} are finished and that the model could be modified
     * again.<BR>
     * If the <code>table</code> is modified while the cache is enabled, the result of {@link #getCell(DLine, DColumn)}
     * will be unpredictable.
     * 
     * @param table
     *            The line container to cache
     */
    public static void fillCache(DTable table) {
        fillCacheRec(table);
    }

    private static void fillCacheRec(LineContainer container) {
        for (DLine line : container.getLines()) {
            for (DCell cell : line.getCells()) {
                if (!CACHE.containsKey(line)) {
                    CACHE.put(line, Maps.<DColumn, DCell> newLinkedHashMap());
                }
                CACHE.get(line).put(cell.getColumn(), cell);
            }
            fillCacheRec(line);
        }
        currentlyCached = container;
    }

    /**
     * Clears the cells cache.
     */
    public static void clearCache() {
        CACHE.clear();
        currentlyCached = null;
    }

    /**
     * Tell whether the mapping has a candidate expression or not.
     * 
     * @param mapping
     *            mapping to check.
     * @return true if the mapping has a candidate expression.
     */
    public static boolean hasSemanticCandidatesExpression(final LineMapping mapping) {
        return !StringUtil.isEmpty(mapping.getSemanticCandidatesExpression());
    }

    /**
     * Tell whether the mapping has a candidate expression or not.
     * 
     * @param mapping
     *            mapping to check.
     * @return true if the mapping has a candidate expression.
     */
    public static boolean hasSemanticCandidatesExpression(final ElementColumnMapping mapping) {
        return !StringUtil.isEmpty(mapping.getSemanticCandidatesExpression());
    }

    /**
     * return the {@link DTable} containing the element if there is one.
     * 
     * @param element
     *            any table element.
     * @return the {@link DTable} containing the element if there is one.
     */
    public static DTable getTable(final EObject element) {
        EObject container = element;
        while (container != null) {
            if (container instanceof DTable) {
                return (DTable) container;
            }
            container = container.eContainer();
        }
        return null;
    }

    /**
     * If found return the cell corresponding to the column and the line.
     * 
     * @param line
     *            the cell line.
     * @param column
     *            the cell column.
     * @return the option with cell corresponding to both lines and columns, empty option if not found.
     */
    public static Option<DCell> getCell(final DLine line, final DColumn column) {
        DCell found = null;
        if (currentlyCached == getTable(line) && CACHE.containsKey(line)) {
            found = CACHE.get(line).get(column);
        } else {
            final Iterator<DCell> it = line.getCells().iterator();
            while (it.hasNext() && found == null) {
                final DCell next = it.next();
                if (next.getColumn() == column) {
                    found = next;
                }
            }
        }
        if (found != null) {
            return Options.newSome(found);
        } else {
            return Options.newNone();
        }
    }

    /**
     * Get the {@link DCell} in the {@link DTable} corresponding to the intersection of this line and column.
     * 
     * @param table
     *            The table in which seek
     * @param lineIndex
     *            The index of the line
     * @param columnIndex
     *            the index of the column
     * @return the {@link DCell} in the {@link DTable} corresponding to the intersection of this line and column.
     */
    public static DCell getCell(final DTable table, final int lineIndex, final int columnIndex) {
        DCell result = null;
        if (table.getLines().size() > lineIndex && table.getColumns().size() > columnIndex) {
            // Get the line corresponding to the index
            final DLine line = table.getLines().get(lineIndex);
            // Get the column corresponding to the index
            final DColumn column = table.getColumns().get(columnIndex);
            // Get the Cell corresponding to this line and column
            Option<DCell> optionalCell = getCell(line, column);
            if (optionalCell.some()) {
                result = optionalCell.get();
            }
        }
        return result;
    }

    /**
     * Get the classifier of the attribute of the column. The column must be a DFeatureColumn
     * 
     * @param line
     *            The line for getting the column of the table
     * @param column
     *            The column
     * @return the classifier of the attribute or null if not found or wrong column type
     */
    public static EClassifier getEClassifier(final DLine line, final DColumn column) {
        EStructuralFeature feature = getEStructuralFeature(line, column);
        if (feature != null) {
            return feature.getEType();
        }
        return null;
    }

    /**
     * Get the {@link EStructuralFeature} of the column. The column must be a DFeatureColumn
     * 
     * @param line
     *            The line for getting the column of the table
     * @param column
     *            The column
     * @return The {@link EStructuralFeature} or null if not found or wrong column type
     */
    public static EStructuralFeature getEStructuralFeature(final DLine line, final DColumn column) {
        if (column instanceof DFeatureColumn) {
            final Option<DCell> cellOption = TableHelper.getCell(line, column);
            if (cellOption.some() && cellOption.get().getTarget() != null) {
                return cellOption.get().getTarget().eClass().getEStructuralFeature(((DFeatureColumn) column).getFeatureName());
            }
        }
        return null;
    }

    /**
     * Get the expanded lines of this table.
     * 
     * @param table
     *            The table
     * @return a list of DLine which are not collapsed
     */
    public static List<DLine> getExpandedLines(final DTable table) {
        final List<DLine> result = new ArrayList<DLine>();
        final EList<DLine> lines = table.getLines();
        for (final DLine line : lines) {
            if (!line.isCollapsed()) {
                result.add(line);
            }
            result.addAll(TableHelper.getExpandedLines(line));
        }
        return result;
    }

    /**
     * Get the expanded lines of this line.
     * 
     * @param parentLine
     *            The parent line
     * @return a list of DLine which are not collapsed
     */
    public static List<DLine> getExpandedLines(final DLine parentLine) {
        final List<DLine> result = new ArrayList<DLine>();
        final EList<DLine> lines = parentLine.getLines();
        for (final DLine line : lines) {
            if (!line.isCollapsed()) {
                result.add(line);
            }
            result.addAll(TableHelper.getExpandedLines(line));
        }
        return result;
    }

    /**
     * Get the variable with the name in this tool.
     * 
     * @param tool
     *            The tool in which search the variable
     * @param name
     *            The variable name
     * @return The corresponding variable or null if not found
     */
    public static TableVariable getVariable(final TableTool tool, final String name) {
        return TableModelHelper.getVariable(tool, name);
    }

    /**
     * Test if the cell can be edited. The cell can be edited if it has a CellUpdater which provides a LabelEditTool.
     * 
     * @param cell
     *            The cell to test
     * @return true if this cell can be edited, false otherwise.
     */
    public static boolean canEditCrossTableCell(final DCell cell) {
        boolean canEdit = false;
        CellUpdater updater = cell.getUpdater();
        if (updater != null && updater.getDirectEdit() != null) {
            canEdit = true;
        }
        return canEdit;
    }

    /**
     * Test if the cell can be edited. The cell can be edited if :
     * <UL>
     * <LI>The cell is blank and it has a CellUpdater which provides a CellCreateTool</LI>
     * <LI>The cell is not blank and it has a CellUpdater which provides a LabelEditTool</LI>
     * </UL>
     * 
     * @param dLine
     *            The {@link DLine} of the {@link DCell} to test
     * @param dTargetColumn
     *            The {@link DTargetColumn} of the {@link DCell} to test
     * @return true if this cell can be edited, false otherwise.
     */
    public static boolean canEditCrossTableCell(final DLine dLine, final DTargetColumn dTargetColumn) {
        Option<DCell> optionnalCell = TableHelper.getCell(dLine, dTargetColumn);
        boolean canEdit = false;
        if (optionnalCell.some()) {
            canEdit = canEditCrossTableCell(optionnalCell.get());
        } else if (TableHelper.canCreate(dLine, dTargetColumn)) {
            canEdit = true;
        }
        return canEdit;
    }

    /**
     * Search the associated createTool corresponding to the intersection of this line and column and return true if one
     * is found.
     * 
     * @param line
     *            The line for which we want to know if there is a create tool (associated with the column)
     * @param column
     *            The column for which we want to know if there is a create tool (associated with the line)
     * @return true if a createTool exists for this intersection, false otherwise
     */
    private static boolean canCreate(final DLine line, final DTargetColumn column) {
        return TableHelper.getCreateCellTool(line, column) != null;
    }

    /**
     * Return the first CreateCellTool corresponding to the intersection of this line and column.
     * 
     * @param line
     *            The line for which we want to know if there is a create tool (associated with the column)
     * @param column
     *            The column for which we want to know if there is a create tool (associated with the line)
     * @return Return an optional (the first) CreateCellTool corresponding to the intersection of this line and column
     */
    public static Option<CreateCellTool> getCreateCellTool(final DLine line, final DColumn column) {
        final DTable table = TableHelper.getTable(line);
        if (table.getDescription() instanceof CrossTableDescription) {
            final ColumnMapping columnMapping = column.getOriginMapping();
            final LineMapping lineMapping = line.getOriginMapping();
            for (IntersectionMapping intersectionMapping : ((CrossTableDescription) table.getDescription()).getIntersection()) {
                if (intersectionMapping.getCreate() != null && columnMapping.equals(intersectionMapping.getColumnMapping()) && intersectionMapping.getLineMapping() != null
                        && intersectionMapping.getLineMapping().contains(lineMapping)) {
                    return Options.newSome(intersectionMapping.getCreate());
                }
            }
        }
        return Options.newNone();
    }

    /**
     * Tell whether the table description contains only one line mapping or not.
     * 
     * @param tableElement
     *            A table element
     * @return true if the table description contains only one line mapping, false otherwise
     */
    public static boolean hasTableDescriptionOnlyOneLineMapping(final DTableElement tableElement) {
        final DTable table = TableHelper.getTable(tableElement);
        if (table != null) {
            return TableHelper.hasTableDescriptionOnlyOneLineMapping(table.getDescription());
        }
        return false;
    }

    /**
     * Tell whether the table description contains only one line mapping or not.
     * 
     * @param mapping
     *            A table mapping element
     * @return true if the table description contains only one line mapping, false otherwise
     */
    public static boolean hasTableDescriptionOnlyOneLineMapping(final TableMapping mapping) {
        return TableHelper.hasTableDescriptionOnlyOneLineMapping(TableHelper.getTableDescription(mapping));
    }

    /**
     * Tell whether the table description contains only one line mapping or not.
     * 
     * @param tableDescription
     *            The table description
     * @return true if the table description contains only one line mapping, false otherwise
     */
    private static boolean hasTableDescriptionOnlyOneLineMapping(final TableDescription tableDescription) {
        if (tableDescription != null) {
            int nbMappings = tableDescription.getOwnedLineMappings().size() + tableDescription.getReusedLineMappings().size();
            if (nbMappings <= 1) {
                // We're not sure yet, so we need to dig further
                for (LineMapping lineMapping : tableDescription.getAllLineMappings()) {
                    nbMappings += lineMapping.getAllSubLines().size();
                    if (nbMappings > 1) {
                        // Stop as soon as we've seen more than one
                        break;
                    }
                }
                return nbMappings == 1;
            }
        }
        return false;
    }

    /**
     * return the {@link TableDescription} containing the element if there is one.
     * 
     * @param mapping
     *            any table mapping element.
     * @return the {@link TableDescription} containing the element if there is one.
     */
    public static TableDescription getTableDescription(final TableMapping mapping) {
        EObject container = mapping.eContainer();
        while (container != null) {
            if (container instanceof TableDescription) {
                return (TableDescription) container;
            }
            container = container.eContainer();
        }
        return null;
    }

    /**
     * Return an optional DTableElementStyle to use for the foreground of the cell intersection of line and column. The
     * DCell corresponding to this intersection does not exist.
     * 
     * @param line
     *            The line of intersection
     * @param column
     *            The column of intersection
     * @return an optional DTableElementStyle to use for the foreground of the cell intersection of line and column.
     */
    public static Option<DTableElementStyle> getBackgroundStyleToApply(DLine line, DColumn column) {
        DTableElementStyle styleToApply = null;

        DTableElementStyle currentLineStyle = null;
        if (line != null) {
            currentLineStyle = line.getCurrentStyle();
            if (currentLineStyle != null && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor())) {
                // Ignore the current cell style because it's just for
                // foreground style
                currentLineStyle = null;
            }
        }

        DTableElementStyle currentColumnStyle = null;
        if (column != null) {
            currentColumnStyle = column.getCurrentStyle();
            if (currentColumnStyle != null && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor())) {
                // Ignore the current cell style because it's just for
                // foreground style
                currentColumnStyle = null;
            }
        }

        if (currentLineStyle != null) {
            if (!currentLineStyle.isDefaultBackgroundStyle()) {
                // We use the first conditional line style
                styleToApply = currentLineStyle;
            } else if (currentColumnStyle != null && !currentColumnStyle.isDefaultBackgroundStyle()) {
                // We use the first conditional column style
                styleToApply = currentColumnStyle;
            } else {
                // We use the default line style
                styleToApply = currentLineStyle;
            }
        } else if (currentColumnStyle != null) {
            // We use the column style (first conditional, or default)
            styleToApply = currentColumnStyle;
        }

        if (styleToApply == null) {
            // This should happens if the style used only default value
            // (unset value) so in this case we don't create a style.
            return Options.newSome(DCellQuery.DEFAULT_STYLE);
        } else {
            return Options.newSome(styleToApply);
        }
    }

    /**
     * Return an optional DTableElementStyle to use for the background of the cell intersection of line and column. The
     * DCell corresponding to this intersection does not exist.
     * 
     * @param line
     *            The line of intersection
     * @param column
     *            The column of intersection
     * @return an optional DTableElementStyle to use for the background of the cell intersection of line and column.
     */
    public static Option<DTableElementStyle> getForegroundStyleToApply(DLine line, DColumn column) {
        DTableElementStyle styleToApply = null;

        DTableElementStyle currentLineStyle = null;
        if (line != null) {
            currentLineStyle = line.getCurrentStyle();
            if (currentLineStyle != null && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor())
                    && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat())
                    && !currentLineStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelSize())) {
                // Ignore the current cell style because it's just for
                // background style
                currentLineStyle = null;
            }
        }

        DTableElementStyle currentColumnStyle = null;
        if (column != null) {
            currentColumnStyle = column.getCurrentStyle();
            if (currentColumnStyle != null && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor())
                    && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat())
                    && !currentColumnStyle.eIsSet(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getDTableElementStyle_LabelSize())) {
                // Ignore the current cell style because it's just for
                // background style
                currentColumnStyle = null;
            }
        }

        if (currentLineStyle != null) {
            if (!currentLineStyle.isDefaultForegroundStyle()) {
                // We use the first conditional line style
                styleToApply = currentLineStyle;
            } else if (currentColumnStyle != null && !currentColumnStyle.isDefaultForegroundStyle()) {
                // We use the first conditional column style
                styleToApply = currentColumnStyle;
            } else {
                // We use the default line style
                styleToApply = currentLineStyle;
            }
        } else if (currentColumnStyle != null) {
            // We use the column style (first conditional, or default)
            styleToApply = currentColumnStyle;
        }

        if (styleToApply == null) {
            return Options.newNone();
        } else {
            return Options.newSome(styleToApply);
        }
    }
}
